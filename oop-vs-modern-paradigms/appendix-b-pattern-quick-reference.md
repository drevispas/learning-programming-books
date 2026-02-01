# Appendix B: Pattern Quick Reference (패턴 요약표)

> 전체 문서에서 다룬 모든 패턴의 빠른 참조표. 각 패턴의 이름, 한 줄 설명, Java 25 미니 코드 예제, 관련 토픽을 포함한다.

---

## 핵심 패턴 요약

**[표 B.1]** 핵심 패턴 요약

| # | Pattern | One-line Description | Topic |
|---|---------|---------------------|-------|
| 1 | Value Object | 원시 타입 대신 도메인 의미를 가진 불변 래퍼 타입 | 03 |
| 2 | Compact Constructor | Record 생성 시 불변 조건을 검증하는 축약 생성자 | 03 |
| 3 | Wither Pattern | 불변 객체의 일부 필드만 변경한 새 인스턴스 반환 | 03 |
| 4 | Sum Type (ADT) | sealed interface로 가능한 상태를 OR로 열거 | 04 |
| 5 | Product Type | record로 필수 필드를 AND로 결합 | 04 |
| 6 | Exhaustive Pattern Matching | sealed interface의 모든 case를 switch로 망라 처리 | 04 |
| 7 | Record Pattern | switch에서 Record 필드를 분해하여 바인딩 | 04 |
| 8 | State Machine | sealed interface로 상태 전이를 타입으로 강제 | 05 |
| 9 | Phantom Type | 런타임 데이터 없이 컴파일 타임 상태를 추적하는 타입 | 05 |
| 10 | Total Function | 모든 입력에 대해 유효한 결과를 반환 (예외 대신 Result) | 06 |
| 11 | Result Type | Success/Failure로 연산 결과를 명시적 표현 | 06 |
| 12 | Railway-Oriented Programming | flatMap 체인으로 성공/실패 경로 분리 | 06, 10 |
| 13 | Pipeline Pattern | 순수 함수들을 flatMap/map으로 순차 연결 | 07 |
| 14 | Functional Core / Imperative Shell | 순수 로직(Core)과 I/O(Shell)를 분리 | 08, 14 |
| 15 | Validation (Applicative) | 모든 에러를 수집하는 병렬 검증 패턴 | 08, 10 |
| 16 | Monoid (Assoc + Identity) | 결합법칙 + 항등원으로 안전한 reduce 보장 | 12 |
| 17 | Idempotent Operation | 멱등성 키로 중복 실행 방지 | 12 |
| 18 | Rule as Data | 비즈니스 규칙을 sealed interface(ADT)로 데이터화 | 13 |
| 19 | Rule Engine | 규칙 데이터를 패턴 매칭으로 평가하는 순수 함수 | 13 |
| 20 | Interpreter Separation | 규칙 정의(data)와 실행(interpreter)을 분리 | 13 |
| 21 | Entity-Record Mapper | JPA Entity와 Domain Record 간 변환 통역사 | 14 |
| 22 | Gradual Migration | 복잡한 로직부터 순수 함수로 점진 추출 | 14 |
| 23 | Bounded Context Model | 같은 도메인을 컨텍스트별 독립 모델로 분리 | 01, 14 |
| 24 | Defensive Copy | Record의 컬렉션 필드를 List.copyOf로 보호 | 03 |
| 25 | Null Object via ADT | null 대신 NoDiscount(), Empty() 등 ADT case 사용 | 04, 12 |
| 26 | Event Store | 상태 변화를 이벤트로 저장하여 완전한 이력 보존 | 15 |
| 27 | Aggregate Reconstitution | fold(initialState, events)로 현재 상태 재구성 | 15 |
| 28 | Decider Pattern | (State, Command) -> Result<Events, Error> 순수 함수 | 15 |
| 29 | Projection (CQRS) | 이벤트 스트림을 읽기 전용 모델로 변환 | 15 |
| 30 | Aggregate Root Pattern | 외부에서는 Root만 참조, 내부 Entity 보호 | 01 |
| 31 | Repository Pattern (DDD) | Aggregate 단위 저장/조회 추상화 (DAO와 구분) | 01 |
| 32 | Domain Service Pattern | 여러 Aggregate 협력이 필요한 상태 없는 순수 함수 | 01 |
| 33 | First-Class Function | 함수를 변수에 할당, 인자로 전달, 반환값으로 사용 | 09 |
| 34 | Higher-Order Function | 함수를 인자로 받거나 반환하는 함수 (map, flatMap, filter) | 09 |
| 35 | Function Composition | andThen/compose로 함수들을 순차적으로 연결 | 09 |
| 36 | Functor | map 연산으로 컨테이너 내부 값을 변환하는 패턴 | 10 |
| 37 | Monad | flatMap으로 중첩된 컨테이너를 평탄화하는 패턴 | 10 |
| 38 | Applicative | 여러 컨테이너의 값을 독립적으로 결합하는 패턴 | 10 |
| 39 | Lens Pattern | 불변 객체의 중첩 필드를 함수형으로 업데이트 | 11 |
| 40 | Anti-Corruption Layer | 외부 모델이 도메인을 오염시키는 것을 방지하는 변환 계층 | 01 |
| 41 | Typestate Pattern | 각 상태가 별도 타입, 상태 전이가 함수 시그니처로 강제 | 05 |
| 42 | Memoization | 순수 함수 결과를 캐싱하여 성능 최적화 | 11 |
| 43 | Lazy Evaluation | Supplier로 계산을 지연하여 불필요한 연산 회피 | 11 |
| 44 | Currying | 다인자 함수를 단인자 함수 체인으로 변환 | 09 |
| 45 | Fold / Reduce | 컬렉션을 단일 값으로 축약하는 재귀적 패턴 | 12, 15 |
| 46 | Parse Don't Validate | 검증 결과를 새로운 타입으로 캡처 (isValid -> parse) | 03 |
| 47 | Make Illegal States Unrepresentable | 불가능한 상태를 타입으로 표현 불가능하게 설계 | 05 |
| 48 | Onion Architecture | Core를 여러 계층으로 감싼 DMMF 권장 구조 | 08 |
| 49 | Ubiquitous Language | 도메인 전문가와 개발자가 사용하는 공통 용어 체계 | 02 |
| 50 | Smart Constructor | 팩토리 메서드로 불변조건 강제 + Result 반환 (예외 대신) | 03, 06 |
| 51 | Opaque Type (Newtype) | 같은 기반 타입이지만 호환 불가능한 래퍼로 타입 혼동 방지 | 03 |
| 52 | Gatherer Pattern | Java 22+ Stream Gatherers로 커스텀 중간 연산 구현 | 09 |
| 53 | Structural Sharing | 불변 컬렉션 업데이트 시 변경 부분만 새로 생성 | 11 |
| 54 | Either Type | Left/Right로 두 가지 타입 중 하나를 표현하는 범용 합타입 | 06 |
| 55 | Visitor via Pattern Matching | 전통적 Visitor 패턴을 switch 패턴 매칭으로 대체 | 04 |

---

## 미니 코드 예제

### 1. Value Object

**[코드 B.1]** Money record
```java
// package: com.ecommerce.shared
public record Money(BigDecimal amount, Currency currency) {
  public Money { if (amount.signum() < 0) throw new IllegalArgumentException("음수 금액"); }
  public Money add(Money o) {
    if (!currency.equals(o.currency)) throw new IllegalArgumentException("통화 불일치");
    return new Money(amount.add(o.amount), currency);
  }
}
```

### 2. Compact Constructor

**[코드 B.2]** Email record
```java
// package: com.ecommerce.shared
public record Email(String value) {
  public Email { if (!value.contains("@")) throw new IllegalArgumentException("유효하지 않은 이메일"); }
}
```

### 3. Wither Pattern

**[코드 B.3]** Order record
```java
// package: com.ecommerce.order
public record Order(OrderId id, OrderStatus status, Money total) {
  public Order withStatus(OrderStatus s) { return new Order(id, s, total); }
}
```

### 4. Sum Type (ADT)

**[코드 B.4]** OrderStatus interface
```java
// package: com.ecommerce.order
public sealed interface OrderStatus permits Unpaid, Paid, Shipped, Cancelled {}
public record Unpaid(LocalDateTime deadline) implements OrderStatus {}
public record Paid(LocalDateTime paidAt, TransactionId txId) implements OrderStatus {}
public record Shipped(LocalDateTime shippedAt, TrackingNumber tracking) implements OrderStatus {}
public record Cancelled(LocalDateTime cancelledAt, String reason) implements OrderStatus {}
```

### 5. Product Type

**[코드 B.5]** OrderLine record
```java
// package: com.ecommerce.shared
public record OrderLine(ProductId productId, Quantity quantity, Money unitPrice) {
  public Money lineTotal() { return unitPrice.multiply(quantity.value()); }
}
```

### 6. Exhaustive Pattern Matching

**[코드 B.6]** Exhaustive Pattern Matching
```java
// package: com.ecommerce.shared
String label = switch (status) {
  case Unpaid u -> "결제 대기";
  case Paid p -> "결제 완료";
  case Shipped s -> "배송 중";
  case Cancelled c -> "취소됨";
};
```

### 7. Record Pattern

**[코드 B.7]** Record Pattern
```java
// package: com.ecommerce.order
// 4번 Paid(paidAt, txId) 정의와 필드명 일치
return switch (status) {
  case Paid(var paidAt, var txId) -> "결제일: " + paidAt + ", TX: " + txId;
  case Unpaid(var deadline) -> "결제 기한: " + deadline;
  case Shipped _, Cancelled _ -> "기타";
};
```

### 8. State Machine

**[코드 B.8]** State Machine
```java
// package: com.ecommerce.shared
public Result<Order, OrderError> ship(TrackingNumber tracking) {
  return switch (status) {
    case Paid p -> Result.success(withStatus(new Shipped(LocalDateTime.now(), tracking)));
    case Unpaid _, Shipped _, Cancelled _ -> Result.failure(new CannotShip(id));
  };
}
```

### 9. Phantom Type

**[코드 B.9]** Email record
```java
// package: com.ecommerce.shared
public record Email<S extends EmailState>(String value) {}
sealed interface EmailState permits Unverified, Verified {}
Email<Verified> verify(Email<Unverified> email, String code) { /*...*/ }
```

### 10. Total Function

**[코드 B.10]** Total Function
```java
// package: com.ecommerce.shared
public static Result<Money, DivisionError> safeDivide(Money amount, int divisor) {
  if (divisor == 0) return Result.failure(new DivisionByZero());
  return Result.success(amount.divide(divisor));
}
```

### 11. Result Type

**[코드 B.11]** Result interface
```java
// package: com.ecommerce.shared
public sealed interface Result<S, F> {
  record Success<S, F>(S value) implements Result<S, F> {}
  record Failure<S, F>(F error) implements Result<S, F> {}

  default <T> Result<T, F> map(Function<S, T> fn) {
    return switch (this) {
      case Success(var v) -> new Success<>(fn.apply(v));
      case Failure(var e) -> new Failure<>(e);
    };
  }
  default <T> Result<T, F> flatMap(Function<S, Result<T, F>> fn) {
    return switch (this) {
      case Success(var v) -> fn.apply(v);
      case Failure(var e) -> new Failure<>(e);
    };
  }
  static <S, F> Result<S, F> success(S value) { return new Success<>(value); }
  static <S, F> Result<S, F> failure(F error) { return new Failure<>(error); }
}
```

### 12. Railway-Oriented Programming

**[코드 B.12]** Railway-Oriented Programming
```java
// package: com.ecommerce.order
// ROP: 성공(Success track)과 실패(Failure track) 두 레일 비유
// Pipeline(#13)과 차이: ROP는 '에러 전파' 메커니즘에 초점
// 어느 단계에서 실패해도 이후 단계 건너뛰고 Failure 최종 반환
Result<Order, OrderError> result = validate(cmd)        // 검증 실패 -> 바로 Failure
  .flatMap(v -> applyPricing(v))                        // 가격 오류 -> 바로 Failure
  .flatMap(p -> processPayment(p))                      // 결제 실패 -> 바로 Failure
  .map(paid -> createOrder(paid));                      // 모두 성공시만 실행
```

### 13. Pipeline Pattern

**[코드 B.13]** Pipeline Pattern
```java
// package: com.ecommerce.order
public Result<Order, OrderError> execute(CreateOrderCommand cmd) {
  return OrderDomainService.validate(cmd, member, inventory)
    .flatMap(validated -> applyPricing(validated, coupon))
    .flatMap(priced -> charge(priced))
    .map(paid -> save(paid));
}
```

### 14. Functional Core / Imperative Shell

**[코드 B.14]** Functional Core / Imperative Shell
```java
// package: com.ecommerce.order
// Core (pure): 외부 의존성 없는 순수 함수
public static Money calculateTotal(List<OrderItem> items, Discount discount) {
  Money subtotal = items.stream()
    .map(OrderItem::lineTotal)
    .reduce(Money.ZERO, Money::add);
  return discount.apply(subtotal);
}

// Shell (I/O): 외부 세계와 상호작용
List<OrderItem> items = orderRepo.findItems(orderId);  // I/O
Money total = calculateTotal(items, discount);          // Pure
orderRepo.updateTotal(orderId, total);                  // I/O
```

### 15. Validation (Applicative)

**[코드 B.15]** Validation (Applicative)
```java
// package: com.ecommerce.shared
// Validation: 성공 시 값, 실패 시 에러 목록 수집
public sealed interface Validation<S, E> {
  record Valid<S, E>(S value) implements Validation<S, E> {}
  record Invalid<S, E>(List<E> errors) implements Validation<S, E> {}

  static <A, B, C, R, E> Validation<R, E> combine3(
      Validation<A, E> v1, Validation<B, E> v2, Validation<C, E> v3,
      TriFunction<A, B, C, R> fn) {
    List<E> errors = new ArrayList<>();
    if (v1 instanceof Invalid<A, E> i) errors.addAll(i.errors());
    if (v2 instanceof Invalid<B, E> i) errors.addAll(i.errors());
    if (v3 instanceof Invalid<C, E> i) errors.addAll(i.errors());
    if (!errors.isEmpty()) return new Invalid<>(errors);
    return new Valid<>(fn.apply(
      ((Valid<A, E>) v1).value(), ((Valid<B, E>) v2).value(), ((Valid<C, E>) v3).value()));
  }
}
// 사용: 모든 에러 수집
Validation<User, Error> user = Validation.combine3(
  validateName(name), validateEmail(email), validatePhone(phone), User::new);
```

### 16. Monoid (Assoc + Identity)

**[코드 B.16]** Monoid (Assoc + Identity)
```java
// package: com.ecommerce.shared
Money total = orderTotals.parallelStream()
  .reduce(Money.zero(Currency.KRW), Money::add);
// 항등원 + 결합법칙 -> 병렬 안전
```

### 17. Idempotent Operation

**[코드 B.17]** Idempotent Operation
```java
// package: com.ecommerce.payment
public Result<Payment, Error> process(PaymentId id, PaymentRequest req) {
  if (paymentRepo.exists(id)) return Result.success(paymentRepo.findById(id).get());
  return doProcess(req).map(p -> { paymentRepo.save(id, p); return p; });
}
```

### 18. Rule as Data

**[코드 B.18]** Rule interface
```java
// package: com.ecommerce.rule
public sealed interface Rule {
  record Equals(String attr, String value) implements Rule {}
  record GTE(String attr, int threshold) implements Rule {}
  record And(Rule left, Rule right) implements Rule {}
}
```

### 19. Rule Engine

**[코드 B.19]** Rule Engine
```java
// package: com.ecommerce.rule
public static boolean evaluate(Rule rule, Customer c) {
  return switch (rule) {
    case Equals(var a, var v) -> getAttr(c, a).equals(v);
    case GTE(var a, var t) -> getInt(c, a) >= t;
    case And(var l, var r) -> evaluate(l, c) && evaluate(r, c);
  };
}
```

### 20. Interpreter Separation

**[코드 B.20]** Rule = WHAT (data), Interpreter = HOW (function)
```java
// package: com.ecommerce.rule
// Rule = WHAT (data), Interpreter = HOW (function)
boolean result = RuleEvaluator.evaluate(rule, customer);   // 평가
String desc = RuleExplainer.explain(rule);                 // 설명
String sql = RuleToSql.toWhere(rule);                      // SQL 변환
```

### 21. Entity-Record Mapper

**[코드 B.21]** Entity-Record Mapper
```java
// package: com.ecommerce.order
public static Order toDomain(OrderEntity e) {
  OrderStatus status = switch (e.getStatus()) {
    case PAID -> new Paid(e.getPaidAt(), new TransactionId(e.getPaymentId()));
    case PENDING -> new Unpaid(e.getCreatedAt());
  };
  return new Order(new OrderId(e.getId().toString()), mapItems(e.getItems()), status);
}
```

### 22. Gradual Migration

**[코드 B.22]** Step 1: 순수 함수 추출
```java
// package: com.ecommerce.shared
// Step 1: 순수 함수 추출
public class PriceCalc { static Money total(List<Item> items, Discount d) { ... } }
// Step 2: Service에서 호출
Money total = PriceCalc.total(items, discount); // 기존 Service 구조 유지!
```

### 23. Bounded Context Model

**[코드 B.23]** DisplayProduct record
```java
// package: com.ecommerce.product
public record DisplayProduct(ProductId id, String name, Money price) {}     // 전시용
public record InventoryProduct(ProductId id, int stock) {}                  // 재고용
public record SettlementProduct(ProductId id, Money supplyPrice) {}         // 정산용
```

### 24. Defensive Copy

**[코드 B.24]** Cart record
```java
// package: com.ecommerce.shared
public record Cart(List<CartItem> items) {
  public Cart { items = List.copyOf(items); } // 외부 변경 차단
}
```

### 25. Null Object via ADT

**[코드 B.25]** Discount interface
```java
// package: com.ecommerce.coupon
public sealed interface Discount {
  record NoDiscount() implements Discount {}    // null 대신 항등원
  record Percentage(int rate) implements Discount {}
  record FixedAmount(Money amt) implements Discount {}
}
```

### 26. Event Store

**[코드 B.26]** OrderEvent interface
```java
// package: com.ecommerce.order
public sealed interface OrderEvent {
  record OrderPlaced(OrderId id, List<OrderItem> items, LocalDateTime at) implements OrderEvent {}
  record OrderPaid(OrderId id, PaymentId paymentId, LocalDateTime at) implements OrderEvent {}
  record OrderShipped(OrderId id, TrackingNumber tracking, LocalDateTime at) implements OrderEvent {}
}
```

### 27. Aggregate Reconstitution

**[코드 B.27]** Aggregate Reconstitution
```java
// package: com.ecommerce.order
public static OrderState reconstitute(List<OrderEvent> events) {
  return events.stream()
    .reduce(OrderState.initial(), OrderAggregate::apply, (s1, s2) -> s2);
}
```

### 28. Decider Pattern

**[코드 B.28]** Decider Pattern
```java
// package: com.ecommerce.order
public static Result<List<OrderEvent>, OrderError> decide(OrderState state, OrderCommand cmd) {
  return switch (cmd) {
    case PlaceOrder c -> handlePlace(state, c);
    case PayOrder c -> handlePay(state, c);
  };
}
```

### 29. Projection (CQRS)

**[코드 B.29]** Projection (CQRS)
```java
// package: com.ecommerce.order
public void on(OrderEvent event) {
  switch (event) {
    case OrderPlaced e -> summaryRepo.save(new OrderSummary(e.id(), "PLACED", e.at()));
    case OrderPaid e -> summaryRepo.updateStatus(e.id(), "PAID", e.at());
  }
}
```

### 30. Aggregate Root Pattern

**[코드 B.30]** Aggregate Root Pattern
```java
// package: com.ecommerce.order
public record Order(OrderId id, List<OrderItem> items, OrderStatus status) {
  public Order addItem(ProductId p, Quantity q, Money price) {
    var newItem = new OrderItem(ItemId.generate(), p, q, price);
    return new Order(id, List.copyOf(append(items, newItem)), status);
  }
}
```

### 31. Repository Pattern (DDD)

**[코드 B.31]** Repository Pattern (DDD)
```java
// package: com.ecommerce.order
public interface OrderRepository {
  Optional<Order> findById(OrderId id);  // Domain type 반환
  void save(Order order);                 // Aggregate 단위 저장
}
```

### 32. Domain Service Pattern

**[코드 B.32]** Domain Service Pattern
```java
// package: com.ecommerce.order
public class OrderDomainService {
  public static Result<Order, OrderError> validateAndCreate(
    CreateOrderCommand cmd, Customer customer, StockInfo stock) {
    return validateStock(cmd.items(), stock).map(items -> new Order(...));
  }
}
```

### 33. First-Class Function

**[코드 B.33]** First-Class Function
```java
// package: com.example.pattern
// 함수를 변수에 할당
Function<Order, Money> totalFn = Order::calculateTotal;
// 함수를 인자로 전달
Money result = applyToOrder(order, totalFn);
// 함수를 반환값으로 사용
Function<Order, Boolean> filter = createFilter("VIP");
```

### 34. Higher-Order Function

**[코드 B.34]** Higher-Order Function
```java
// package: com.example.pattern
// 함수를 인자로 받음
public static <T, R> List<R> map(List<T> list, Function<T, R> fn) {
  return list.stream().map(fn).toList();
}
// 함수를 반환
public static Predicate<Order> minAmount(Money threshold) {
  return order -> order.total().isGreaterThan(threshold);
}
```

### 35. Function Composition

**[코드 B.35]** Function Composition
```java
// package: com.example.pattern
Function<String, String> trim = String::trim;
Function<String, String> lower = String::toLowerCase;
Function<String, String> normalize = trim.andThen(lower);

String result = normalize.apply("  HELLO  ");  // "hello"
```

### 36. Functor

**[코드 B.36]** Functor
```java
// package: com.ecommerce.shared
// Optional은 Functor - map으로 내부 값 변환
Optional<Order> order = findOrder(id);
Optional<Money> total = order.map(Order::calculateTotal);

// Result도 Functor
Result<Order, Error> result = validate(cmd).map(Order::new);
```

### 37. Monad

**[코드 B.37]** Monad
```java
// package: com.ecommerce.shared
// flatMap으로 중첩 컨테이너 평탄화
Optional<Order> order = findOrder(id);
Optional<Payment> payment = order.flatMap(o -> findPayment(o.paymentId()));
// flatMap 없이: Optional<Optional<Payment>> - 중첩됨

// Result 체이닝
Result<Order, Error> result = validate(cmd)
  .flatMap(v -> createOrder(v))
  .flatMap(o -> processPayment(o));
```

### 38. Applicative

**[코드 B.38]** Applicative
```java
// package: com.ecommerce.shared
// Applicative: 독립적인 컨테이너 값들을 결합 (Validation 타입 정의는 #15 참조)
// Monad(flatMap)와 차이: 순차 의존 vs 병렬 독립
// Monad: 앞 결과가 다음 연산에 필요 (validateA -> validateB(a))
// Applicative: 결과 독립, 동시 수행 가능 (validateA, validateB, validateC -> combine)

// 독립적인 검증을 병렬로 수행, 모든 에러 수집
Validation<User, Error> user = Validation.combine3(
  validateName(name),      // 독립적
  validateEmail(email),    // 독립적
  validatePhone(phone),    // 독립적
  User::new                // 모두 성공시 결합
);
```

### 39. Lens Pattern

**[코드 B.39]** Lens Pattern
```java
// package: com.ecommerce.shared
record Lens<S, A>(Function<S, A> get, BiFunction<S, A, S> set) {
  public S modify(S s, Function<A, A> fn) { return set.apply(s, fn.apply(get.apply(s))); }
  public <B> Lens<S, B> andThen(Lens<A, B> other) {
    return new Lens<>(s -> other.get.apply(get.apply(s)),
      (s, b) -> set.apply(s, other.set.apply(get.apply(s), b)));
  }
}
// 중첩 필드 업데이트: order.address.city
Lens<Order, String> cityLens = orderAddressLens.andThen(addressCityLens);
```

### 40. Anti-Corruption Layer

**[코드 B.40]** Anti-Corruption Layer
```java
// package: com.ecommerce.infra
// 외부 API 응답을 도메인 모델로 변환하는 ACL
public class PaymentGatewayAcl {
  public Result<Payment, PaymentError> process(Order order) {
    ExternalPaymentResponse ext = externalApi.charge(toExternal(order));
    return toDomain(ext);  // 외부 모델 -> 도메인 모델 변환
  }
  private Payment toDomain(ExternalPaymentResponse r) {
    return new Payment(new PaymentId(r.id()), Money.of(r.amount(), r.currency()));
  }
}
```

### 41. Typestate Pattern

**[코드 B.41]** Typestate Pattern
```java
// package: com.ecommerce.order
// 각 상태가 별도 타입
record DraftOrder(OrderId id, List<OrderItem> items) {
  SubmittedOrder submit() { return new SubmittedOrder(id, items, LocalDateTime.now()); }
}
record SubmittedOrder(OrderId id, List<OrderItem> items, LocalDateTime at) {
  PaidOrder pay(PaymentId paymentId) { return new PaidOrder(id, items, paymentId); }
}
// 컴파일 타임에 잘못된 전이 방지: draftOrder.pay() -> 컴파일 에러!
```

### 42. Memoization

**[코드 B.42]** Memoization
```java
// package: com.example.pattern
public class Memoizer<T, R> {
  private final Map<T, R> cache = new ConcurrentHashMap<>();
  private final Function<T, R> fn;
  public Memoizer(Function<T, R> fn) { this.fn = fn; }
  public R apply(T t) { return cache.computeIfAbsent(t, fn); }
}
// 사용: 순수 함수만 메모이제이션 가능
var memoizedFib = new Memoizer<>(this::fibonacci);
```

### 43. Lazy Evaluation

**[코드 B.43]** Lazy Evaluation
```java
// package: com.example.pattern
// record는 mutable 필드를 가질 수 없으므로 class 사용
public final class Lazy<T> {
  private final Supplier<T> supplier;
  private T value;
  private boolean evaluated;
  public Lazy(Supplier<T> supplier) { this.supplier = supplier; }
  public synchronized T get() {
    if (!evaluated) { value = supplier.get(); evaluated = true; }
    return value;
  }
}
// 비용이 큰 연산을 필요할 때까지 지연
Lazy<Report> report = new Lazy<>(() -> generateExpensiveReport());
```

### 44. Currying

**[코드 B.44]** Currying
```java
// package: com.example.pattern
// 다인자 함수를 단인자 함수 체인으로 변환
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
// Curried version
Function<Integer, Function<Integer, Integer>> curriedAdd = a -> b -> a + b;

Function<Integer, Integer> add5 = curriedAdd.apply(5);  // 부분 적용
int result = add5.apply(3);  // 8
```

### 45. Fold / Reduce

**[코드 B.45]** Fold / Reduce
```java
// package: com.ecommerce.shared
// 컬렉션을 단일 값으로 축약
Money total = items.stream()
  .map(OrderItem::lineTotal)
  .reduce(Money.zero(Currency.KRW), Money::add);

// Event Sourcing에서 상태 재구성
OrderState state = events.stream()
  .reduce(OrderState.initial(), OrderState::apply, (s1, s2) -> s2);
```

### 46. Parse Don't Validate

**[코드 B.46]** Parse Don't Validate
```java
// package: com.ecommerce.shared
// [X] 검증만 하고 결과를 버림
boolean isValid(String email) { return email.contains("@"); }

// [O] 검증 결과를 새 타입으로 캡처
public static Result<Email, EmailError> parse(String input) {
  if (!input.contains("@")) return Result.failure(new InvalidEmail(input));
  return Result.success(new Email(input));  // 검증된 타입 반환
}
```

### 47. Make Illegal States Unrepresentable

**[코드 B.47]** Make Illegal States Unrepresentable
```java
// package: com.ecommerce.order
// [X] 불가능한 상태 표현 가능
record Order(boolean isPaid, boolean isShipped, PaymentId paymentId) {}
// isPaid=false, isShipped=true 가능! (불가능한 상태)

// [O] ADT로 불가능한 상태 자체를 타입으로 표현 불가
sealed interface Order permits Unpaid, Paid, Shipped {}
record Unpaid(OrderId id) implements Order {}
record Paid(OrderId id, PaymentId paymentId) implements Order {}
record Shipped(OrderId id, PaymentId paymentId, TrackingNo tracking) implements Order {}
```

### 48. Onion Architecture

**[코드 B.48]** Onion Architecture
```java
// package: com.ecommerce
// 바깥에서 안으로: Infra -> Application -> Domain
// Domain (core): 순수한 비즈니스 로직, 외부 의존성 없음
// com.ecommerce.order.domain.Order, OrderDomainService

// Application: 유스케이스 조율, 트랜잭션 경계
// com.ecommerce.order.application.PlaceOrderUseCase

// Infrastructure: DB, 외부 API, Framework
// com.ecommerce.order.infra.JpaOrderRepository
```

### 49. Ubiquitous Language

**[코드 B.49]** Ubiquitous Language
```java
// package: com.ecommerce.order
// 도메인 전문가와 개발자가 같은 용어 사용
// [X] 개발자 용어: processOrder(), setStatus(), item_qty
// [O] 도메인 용어: placeOrder(), shipOrder(), orderQuantity

public record Order(OrderId id, List<OrderLine> lines, OrderStatus status) {
  public Order place() { /* "주문하다" */ }
  public Order ship(TrackingNumber tracking) { /* "배송하다" */ }
  public Order cancel(CancellationReason reason) { /* "취소하다" */ }
}
```

### 50. Smart Constructor

**[코드 B.50]** Smart Constructor
```java
// package: com.ecommerce.shared
// Compact Constructor와 차이: 예외 대신 Result 반환
public final class Email {
  private final String value;
  private Email(String value) { this.value = value; }
  public String value() { return value; }

  public static Result<Email, EmailError> of(String input) {
    if (input == null || input.isBlank()) return Result.failure(new EmptyEmail());
    if (!input.contains("@")) return Result.failure(new InvalidEmail(input));
    return Result.success(new Email(input.toLowerCase()));
  }
}
// Parse Don't Validate의 구현 메커니즘
Result<Email, EmailError> email = Email.of(userInput);
```

### 51. Opaque Type (Newtype)

**[코드 B.51]** Opaque Type (Newtype)
```java
// package: com.ecommerce.shared
// 같은 UUID 기반이지만 상호 호환 불가
public record UserId(UUID value) {}
public record ProductId(UUID value) {}
public record OrderId(UUID value) {}

// 타입 안전성: 컴파일 타임에 혼동 방지
void processUser(UserId id) { /* ... */ }
void processProduct(ProductId id) { /* ... */ }

UserId userId = new UserId(UUID.randomUUID());
ProductId productId = new ProductId(UUID.randomUUID());
// processUser(productId);  // 컴파일 에러! 타입 불일치
```

### 52. Gatherer Pattern

**[코드 B.52]** Gatherer Pattern
```java
// package: com.example.pattern
// Java 22+ Stream Gatherer: 커스텀 중간 연산
import java.util.stream.Gatherers;

// 슬라이딩 윈도우로 이동 평균 계산
List<Double> movingAverages = prices.stream()
  .gather(Gatherers.windowSliding(3))
  .map(window -> window.stream().mapToDouble(d -> d).average().orElse(0))
  .toList();
```

### 53. Structural Sharing

**[코드 B.53]** Structural Sharing
```java
// package: com.example.pattern
// 불변 리스트의 효율적 업데이트 (개념적 예시)
record PersistentList<T>(T head, PersistentList<T> tail) {
  static <T> PersistentList<T> empty() { return null; }

  PersistentList<T> prepend(T elem) {
    return new PersistentList<>(elem, this);  // O(1), 기존 tail 재사용
  }

  // tail은 공유되므로 메모리 효율적
  // list1 = [1, 2, 3]
  // list2 = list1.prepend(0)  // [0, 1, 2, 3] - [1,2,3] 부분 공유
}
// 실무에서는 Vavr, Eclipse Collections 등 라이브러리 사용 권장
```

### 54. Either Type

**[코드 B.54]** Either Type
```java
// package: com.ecommerce.shared
// 두 가지 타입 중 하나를 표현하는 범용 합타입
public sealed interface Either<L, R> {
  record Left<L, R>(L value) implements Either<L, R> {}
  record Right<L, R>(R value) implements Either<L, R> {}

  default <T> T fold(Function<L, T> onLeft, Function<R, T> onRight) {
    return switch (this) {
      case Left(var l) -> onLeft.apply(l);
      case Right(var r) -> onRight.apply(r);
    };
  }
}
// Result<S,F>는 Either<F,S>의 도메인 특화 버전 (의미 명확)
// Either: 범용 (Left/Right), Result: 도메인 특화 (Success/Failure)
```

### 55. Visitor via Pattern Matching

**[코드 B.55]** Visitor via Pattern Matching
```java
// package: com.example.pattern
// [X] OOP Visitor: accept(Visitor v) 메서드 + visit(ConcreteElement e) 필요
// [O] DOP: sealed interface + switch 패턴 매칭으로 대체
sealed interface Expr permits Num, Add, Mul {}
record Num(int value) implements Expr {}
record Add(Expr left, Expr right) implements Expr {}
record Mul(Expr left, Expr right) implements Expr {}

// 외부 함수로 연산 정의 (데이터와 행위 분리)
static int eval(Expr expr) {
  return switch (expr) {
    case Num(var n) -> n;
    case Add(var l, var r) -> eval(l) + eval(r);
    case Mul(var l, var r) -> eval(l) * eval(r);
  };
}
// 새 연산 추가가 쉬움: print(Expr), optimize(Expr) 등
```

---

## 패턴 조합 가이드

**[표 B.2]** 패턴 조합 가이드

| # | 상황 | 추천 패턴 조합 | 관련 패턴 # |
|---|------|---------------|-------------|
| 1 | 단순 CRUD | Value Object + Compact Constructor | 1, 2 |
| 2 | 다중 상태 도메인 | Sum Type + State Machine + Exhaustive Matching | 4, 6, 8 |
| 3 | 실패 가능 연산 | Result + flatMap Pipeline | 11, 12 |
| 4 | 폼 검증 | Validation (Applicative) | 15 |
| 5 | 복잡한 워크플로우 | Pipeline + Functional Core / Imperative Shell | 13, 14 |
| 6 | 자주 변경되는 규칙 | Rule Engine + Interpreter Separation | 19, 20 |
| 7 | JPA 기존 코드 | Entity-Record Mapper + Gradual Migration | 21, 22 |
| 8 | 분산/병렬 처리 | Monoid + Idempotent Operation | 16, 17 |
| 9 | 도메인 모델링 | Bounded Context + ADT + Value Object | 1, 4, 23 |
| 10 | 이력 추적 필수 도메인 | Event Store + Aggregate Reconstitution + Decider | 26, 27, 28 |
| 11 | 복잡한 조회 요구 | Event Sourcing + Projection (CQRS) | 26, 29 |
| 12 | FP 함수 설계 | First-Class Function + Higher-Order Function + Function Composition | 33, 34, 35 |
| 13 | 컨테이너 체이닝 | Functor + Monad + Applicative | 36, 37, 38 |
| 14 | 불변 객체 업데이트 | Lens Pattern + Wither Pattern | 3, 39 |
| 15 | 외부 시스템 통합 | Anti-Corruption Layer + Entity-Record Mapper | 21, 40 |
| 16 | 상태별 타입 안전성 | Typestate Pattern + Make Illegal States Unrepresentable | 41, 47 |
| 17 | 성능 최적화 | Memoization + Lazy Evaluation | 42, 43 |
| 18 | 입력 검증 | Parse Don't Validate + Smart Constructor + Value Object | 1, 46, 50 |
| 19 | 도메인 설계 기반 | Ubiquitous Language + Bounded Context + Onion Architecture | 23, 48, 49 |
| 20 | OOP 디자인 패턴 대체 | Visitor via Pattern Matching + Sum Type | 4, 55 |
| 21 | 대용량 불변 데이터 | Structural Sharing + Defensive Copy | 24, 53 |
| 22 | 타입 안전 ID 관리 | Opaque Type + Smart Constructor | 50, 51 |
| 23 | 스트림 커스텀 연산 | Gatherer Pattern + Higher-Order Function + Fold/Reduce | 34, 45, 52 |
| 24 | 범용 분기 처리 | Either Type + Exhaustive Pattern Matching | 6, 54 |
