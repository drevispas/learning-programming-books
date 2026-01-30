# Modern Programming Pilot Code Guide

> A compact reference for generating pilot code with AI. This guide provides the **HOW** (code structure/patterns) while PRD provides the **WHAT** (business requirements).

---

## 1. Core Principles (~5%)

### One-Line Definition

**Modern approach = Immutable data (records) + Pure functions + Type-safe error handling (Result)**

### Three Constraints

| Constraint | Pattern | Example |
|------------|---------|---------|
| No mutable state | Wither pattern | `order.withStatus(new Paid(...))` |
| No exceptions for business logic | Result<S, F> | `Result.failure(new InvalidEmail())` |
| No impossible states | Sealed interfaces | `sealed interface OrderStatus permits Unpaid, Paid, Shipped` |

---

## 2. Domain Modeling Templates (~30%)

### 2.1 Value Object (Wrapped Primitive)

```java
public record Email(String value) {
  public Email {
    if (value == null || !value.contains("@"))
      throw new IllegalArgumentException("Invalid email");
  }
}

public record Money(BigDecimal amount, Currency currency) {
  public Money {
    if (amount == null) throw new IllegalArgumentException("Amount required");
    if (amount.compareTo(BigDecimal.ZERO) < 0)
      throw new IllegalArgumentException("Negative amount not allowed");
  }

  public Money add(Money other) {
    return new Money(amount.add(other.amount), currency);
  }

  public static Money zero(Currency c) {
    return new Money(BigDecimal.ZERO, c);
  }
}
```

### 2.2 Sum Type (Sealed Interface)

```java
public sealed interface OrderStatus {
  record Unpaid(LocalDateTime deadline) implements OrderStatus {}
  record Paid(LocalDateTime paidAt, PaymentId txId) implements OrderStatus {}
  record Shipped(LocalDateTime shippedAt, TrackingNumber tracking) implements OrderStatus {}
  record Cancelled(LocalDateTime at, CancelReason reason) implements OrderStatus {}
}

// State-specific data is enforced by type:
// - Shipped MUST have TrackingNumber
// - Cancelled MUST have CancelReason
// - Unpaid cannot have either
```

### 2.3 Entity with Status

```java
public record Order(
  OrderId id,
  CustomerId customerId,
  List<OrderLine> lines,
  Money totalAmount,
  OrderStatus status
) {
  public Order {
    lines = List.copyOf(lines);  // Defensive copy
  }

  // Wither for status change
  public Order withStatus(OrderStatus newStatus) {
    return new Order(id, customerId, lines, totalAmount, newStatus);
  }
}
```

### 2.4 Domain Error (Sealed Interface)

```java
public sealed interface OrderError {
  record EmptyOrder() implements OrderError {}
  record OutOfStock(ProductId productId) implements OrderError {}
  record InvalidCoupon(CouponCode code, String reason) implements OrderError {}
  record PaymentFailed(String reason) implements OrderError {}
  record InvalidStateTransition(String from, String to) implements OrderError {}
}
```

### 2.5 Wither Pattern

```java
public record Member(MemberId id, String name, MemberGrade grade, Points points) {
  public Member withGrade(MemberGrade g) {
    return new Member(id, name, g, points);
  }
  public Member withPoints(Points p) {
    return new Member(id, name, grade, p);
  }
}

// Usage: immutable update
Member upgraded = member.withGrade(MemberGrade.VIP);
// Original 'member' is unchanged
```

### 2.6 Defensive Copy (Collections)

```java
public record Cart(List<CartItem> items) {
  public Cart {
    items = List.copyOf(items);  // Block external mutation
  }
}

public record Order(OrderId id, List<OrderLine> lines, OrderStatus status) {
  public Order {
    lines = List.copyOf(lines);
  }

  public Order addLine(OrderLine newLine) {
    var newLines = new ArrayList<>(lines);
    newLines.add(newLine);
    return new Order(id, List.copyOf(newLines), status);
  }
}
```

### 2.7 Null Object via ADT

```java
public sealed interface Discount {
  record NoDiscount() implements Discount {}  // Instead of null
  record Percentage(int rate) implements Discount {}
  record FixedAmount(Money amount) implements Discount {}
}

// Usage: no null checks needed
Money apply(Discount discount, Money price) {
  return switch (discount) {
    case NoDiscount() -> price;
    case Percentage(var rate) -> price.multiply(1 - rate / 100.0);
    case FixedAmount(var amt) -> price.subtract(amt);
  };
}
```

---

## 3. Result Type & Error Handling (~20%)

### 3.1 Result<S, F> Definition

```java
public sealed interface Result<S, F> {
  record Success<S, F>(S value) implements Result<S, F> {}
  record Failure<S, F>(F error) implements Result<S, F> {}

  static <S, F> Result<S, F> success(S value) {
    return new Success<>(value);
  }

  static <S, F> Result<S, F> failure(F error) {
    return new Failure<>(error);
  }

  default <T> Result<T, F> map(Function<S, T> mapper) {
    return switch (this) {
      case Success(var value) -> Result.success(mapper.apply(value));
      case Failure(var error) -> Result.failure(error);
    };
  }

  default <T> Result<T, F> flatMap(Function<S, Result<T, F>> mapper) {
    return switch (this) {
      case Success(var value) -> mapper.apply(value);
      case Failure(var error) -> Result.failure(error);
    };
  }

  default <T> T fold(Function<S, T> onSuccess, Function<F, T> onFailure) {
    return switch (this) {
      case Success(var value) -> onSuccess.apply(value);
      case Failure(var error) -> onFailure.apply(error);
    };
  }
}
```

### 3.2 flatMap Chaining (Railway-Oriented Programming)

```java
public Result<OrderPlaced, OrderError> execute(PlaceOrderCommand cmd) {
  return validateOrder(cmd)           // Result<ValidatedOrder, OrderError>
    .flatMap(this::checkInventory)    // Result<ValidatedOrder, OrderError>
    .flatMap(this::applyCoupon)       // Result<PricedOrder, OrderError>
    .flatMap(this::processPayment)    // Result<PaidOrder, OrderError>
    .map(this::createEvent);          // Result<OrderPlaced, OrderError>
}

// Each step:
private Result<ValidatedOrder, OrderError> validateOrder(PlaceOrderCommand cmd) {
  if (cmd.lines().isEmpty()) {
    return Result.failure(new OrderError.EmptyOrder());
  }
  return Result.success(new ValidatedOrder(cmd));
}

private Result<ValidatedOrder, OrderError> checkInventory(ValidatedOrder order) {
  for (var line : order.lines()) {
    if (!inventoryService.isAvailable(line.productId(), line.quantity())) {
      return Result.failure(new OrderError.OutOfStock(line.productId()));
    }
  }
  return Result.success(order);
}
```

### 3.3 map vs flatMap Selection

| Function returns | Use |
|------------------|-----|
| `A -> B` (plain value) | `map` |
| `A -> Result<B, E>` (can fail) | `flatMap` |

```java
// map: transform that cannot fail
.map(order -> new OrderConfirmation(order.id(), order.total()))

// flatMap: operation that can fail
.flatMap(order -> paymentGateway.charge(order.total()))
```

### 3.4 fold for Final Conversion

```java
Result<Order, OrderError> result = processOrder(cmd);

HttpResponse response = result.fold(
  success -> HttpResponse.ok(OrderDto.from(success)),
  error -> switch (error) {
    case OrderError.EmptyOrder() -> HttpResponse.badRequest("Empty order");
    case OrderError.OutOfStock(var pid) -> HttpResponse.conflict("Out of stock: " + pid);
    case OrderError.InvalidCoupon(var code, var reason) -> HttpResponse.badRequest(reason);
    case OrderError.PaymentFailed(var reason) -> HttpResponse.paymentRequired(reason);
  }
);
```

---

## 4. Pattern Matching (~15%)

### 4.1 Exhaustive Switch (NO default)

```java
// GOOD: Compiler forces all cases
String label = switch (status) {
  case Unpaid u -> "Pending payment";
  case Paid p -> "Paid";
  case Shipped s -> "Shipping";
  case Cancelled c -> "Cancelled";
};
// Adding new status = compile error here

// BAD: default hides new cases
String label = switch (status) {
  case Paid p -> "Paid";
  default -> "Other";  // New status silently falls here!
};
```

### 4.2 Record Pattern Deconstruction

```java
return switch (status) {
  case Paid(var at, var txId) -> "Paid at " + at + ", TX: " + txId;
  case Unpaid(var deadline) -> "Due by " + deadline;
  case Shipped(var at, var tracking) -> "Tracking: " + tracking.value();
  case Cancelled(var at, var reason) -> "Cancelled: " + reason;
};
```

### 4.3 Guard Conditions (when)

```java
String classify(OrderStatus status) {
  return switch (status) {
    case Paid p when p.paidAt().plusHours(24).isAfter(LocalDateTime.now())
      -> "Cancellable";
    case Paid p -> "Cannot cancel (24h passed)";
    case Unpaid u when u.deadline().isBefore(LocalDateTime.now())
      -> "Expired";
    case Unpaid u -> "Awaiting payment";
    case Shipped s -> "In transit";
    case Cancelled c -> "Cancelled";
  };
}
```

### 4.4 Unnamed Patterns (_)

```java
// When you don't need all fields
String getPaymentId(OrderStatus status) {
  return switch (status) {
    case Paid(_, var txId) -> txId.value();  // Ignore paidAt
    case Unpaid _ -> "N/A";                   // Ignore entire record
    case Shipped _, Cancelled _ -> "N/A";
  };
}
```

---

## 5. Architecture Pattern (~20%)

### 5.1 Functional Core (Pure Logic)

```java
// Pure functions: static, no I/O, no side effects
public class OrderCalculations {

  public static Money calculateTotal(List<OrderLine> lines) {
    return lines.stream()
      .map(line -> line.unitPrice().multiply(line.quantity()))
      .reduce(Money.zero(), Money::add);
  }

  public static PricedOrder applyDiscount(ValidatedOrder order, Discount discount) {
    Money subtotal = calculateTotal(order.lines());
    Money discounted = switch (discount) {
      case NoDiscount() -> subtotal;
      case Percentage(var rate) -> subtotal.multiply(1 - rate / 100.0);
      case FixedAmount(var amt) -> subtotal.subtract(amt);
    };
    return new PricedOrder(order.customerId(), order.lines(), subtotal, discounted);
  }

  public static boolean canCancel(Order order) {
    return switch (order.status()) {
      case Unpaid _, Paid _ -> true;
      case Shipped _, Cancelled _ -> false;
    };
  }
}
```

### 5.2 Imperative Shell (UseCase)

```java
@Service
@Transactional(readonly = true)
@RequiredArgsConstructor
public class PlaceOrderUseCase {
  private final OrderRepository orderRepository;      // I/O
  private final PaymentGateway paymentGateway;        // I/O
  private final CouponRepository couponRepository;    // I/O

  @Transactional
  public Result<OrderPlaced, OrderError> execute(PlaceOrderCommand cmd) {
    // === Shell: Collect data ===
    var validated = validateOrder(cmd);
    if (validated.isFailure()) return validated.mapError(e -> e);

    Optional<Coupon> coupon = cmd.couponCode()
      .flatMap(couponRepository::findByCode);

    // === Core: Pure calculation ===
    PricedOrder priced = OrderCalculations.applyDiscount(
      validated.value(),
      coupon.map(Coupon::toDiscount).orElse(new NoDiscount())
    );

    // === Shell: Side effects ===
    var paymentResult = paymentGateway.charge(priced.total());
    if (paymentResult.isFailure()) {
      return Result.failure(new OrderError.PaymentFailed(paymentResult.error().message()));
    }

    Order order = new Order(
      OrderId.generate(),
      priced.customerId(),
      priced.lines(),
      priced.total(),
      new Paid(LocalDateTime.now(), paymentResult.value().txId())
    );
    orderRepository.save(order);

    return Result.success(new OrderPlaced(order.id(), priced.total()));
  }
}
```

### 5.3 Package Structure

```
com.example.order/
├── domain/
│   ├── Order.java              # Record (Entity)
│   ├── OrderId.java            # Value Object
│   ├── OrderLine.java          # Value Object
│   ├── OrderStatus.java        # Sealed Interface (Sum Type)
│   ├── OrderError.java         # Sealed Interface (Domain Errors)
│   └── OrderRepository.java    # Interface (Port)
├── application/
│   ├── PlaceOrderUseCase.java  # Imperative Shell
│   ├── CancelOrderUseCase.java
│   └── OrderCalculations.java  # Functional Core (Pure)
└── infrastructure/
    ├── JpaOrderRepository.java # Implementation
    ├── OrderEntity.java        # JPA Entity
    └── OrderMapper.java        # Entity <-> Domain conversion
```

---

## 6. Quick Reference Cheat Sheet (~10%)

| Situation | Pattern | Code |
|-----------|---------|------|
| Wrap primitive | Value Object | `record Email(String value) { compact constructor }` |
| Multiple exclusive states | Sum Type | `sealed interface Status permits A, B, C {}` |
| State-specific data | Record per state | `record Paid(LocalDateTime at, PaymentId tx) implements Status {}` |
| Failable operation | Result return | `Result<Order, OrderError>` |
| State change | Wither | `order.withStatus(new Paid(...))` |
| Protect collection | Defensive copy | `items = List.copyOf(items)` |
| Replace null | ADT variant | `record NoDiscount() implements Discount {}` |
| Branch on type | Pattern match | `switch (x) { case A a -> ...; case B b -> ...; }` |
| Extract fields | Record pattern | `case Paid(var at, var tx) -> ...` |
| Conditional match | Guard | `case Paid p when p.at().isAfter(x) -> ...` |
| Ignore fields | Unnamed | `case Paid(_, var tx) -> ...` |
| Chain failable ops | flatMap | `validate().flatMap(this::check).flatMap(this::pay)` |
| Transform success | map | `.map(order -> new Dto(order))` |
| Extract final value | fold | `result.fold(s -> ok(s), e -> error(e))` |
| Pure business logic | Static function | `static Money calculate(List<Item> items)` |
| I/O orchestration | UseCase class | `@Transactional public Result<...> execute(...)` |

---

## 7. Anti-Pattern Checklist

| ❌ Avoid | ✅ Use Instead |
|----------|----------------|
| Throwing exceptions for business errors | `Result.failure(...)` |
| `null` return for "not found" | `Optional<T>` or `NoXxx()` ADT variant |
| Boolean flags for states | Sealed interface with records |
| `default` in sealed switch | Exhaustive case listing |
| Mutable setters | Wither methods returning new instance |
| Business logic in JPA Entity | Pure functions in `*Calculations` class |
| Mixed I/O and logic | Sandwich: I/O → Pure → I/O |

---

## 8. Validation vs Result

| Use Case | Pattern | When |
|----------|---------|------|
| Form validation | `Validation.combine()` | Collect ALL errors at once |
| Pipeline processing | `Result.flatMap()` | Stop at FIRST error |

```java
// Validation: collect all errors
Validation<User, List<Error>> user = Validation.combine3(
  validateName(name),
  validateEmail(email),
  validatePassword(password),
  User::new
);

// Result: fail fast
Result<Order, Error> order = validate(cmd)
  .flatMap(this::checkStock)
  .flatMap(this::charge);
```

---

## 9. Testing Pure Functions

```java
@Test
void calculateTotal_multipleItems() {
  var items = List.of(
    new OrderLine(pid1, Quantity.of(2), Money.krw(10000)),
    new OrderLine(pid2, Quantity.of(1), Money.krw(5000))
  );

  Money result = OrderCalculations.calculateTotal(items);

  assertEquals(Money.krw(25000), result);
  // No mocks needed!
}

@Test
void canCancel_shippedOrder_returnsFalse() {
  var order = new Order(id, cid, lines, total, new Shipped(now, tracking));

  assertFalse(OrderCalculations.canCancel(order));
  // No repository, no database, just pure input/output
}
```

---

## 10. Migration Strategy (Existing Codebase)

1. **Identify** complex business logic in Entity/Service
2. **Extract** to `*Calculations` class as static pure function
3. **Test** with simple input/output assertions (no mocks)
4. **Call** from UseCase, keeping Entity for JPA only
5. **Repeat** for next complex logic

```java
// Step 1-2: Extract pure logic
public class PriceCalculations {
  public static Money calculateTotal(List<Item> items, Discount d) {
    // Pure calculation extracted from OrderService
  }
}

// Step 4: Call from existing service
public class OrderService {
  public Order createOrder(OrderRequest req) {
    // ... existing code ...
    Money total = PriceCalculations.calculateTotal(items, discount);
    // ... rest of existing code ...
  }
}
```

---

*This guide + PRD → AI → Pilot Code*
