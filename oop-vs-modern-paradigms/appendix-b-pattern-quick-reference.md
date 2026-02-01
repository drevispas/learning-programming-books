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

---

## 미니 코드 예제

### 1. Value Object

**[코드 B.1]** Money record
```java
1| // package: com.ecommerce.shared
2| public record Money(BigDecimal amount, Currency currency) {
3|   public Money { if (amount.signum() < 0) throw new IllegalArgumentException(); }
4|   public Money add(Money o) { return new Money(amount.add(o.amount), currency); }
5| }
```

### 2. Compact Constructor

**[코드 B.2]** Email record
```java
1| // package: com.ecommerce.shared
2| public record Email(String value) {
3|   public Email { if (!value.contains("@")) throw new IllegalArgumentException("유효하지 않은 이메일"); }
4| }
```

### 3. Wither Pattern

**[코드 B.3]** Order record
```java
1| // package: com.ecommerce.order
2| public record Order(OrderId id, OrderStatus status, Money total) {
3|   public Order withStatus(OrderStatus s) { return new Order(id, s, total); }
4| }
```

### 4. Sum Type (ADT)

**[코드 B.4]** OrderStatus interface
```java
1| // package: com.ecommerce.order
2| public sealed interface OrderStatus permits Unpaid, Paid, Shipped, Cancelled {}
3| public record Unpaid(LocalDateTime deadline) implements OrderStatus {}
4| public record Paid(LocalDateTime paidAt, TransactionId txId) implements OrderStatus {}
```

### 5. Product Type

**[코드 B.5]** OrderLine record
```java
1| // package: com.ecommerce.shared
2| public record OrderLine(ProductId productId, Quantity quantity, Money unitPrice) {
3|   public Money lineTotal() { return unitPrice.multiply(quantity.value()); }
4| }
```

### 6. Exhaustive Pattern Matching

**[코드 B.6]** Exhaustive Pattern Matching
```java
1| // package: com.ecommerce.shared
2| String label = switch (status) {
3|   case Unpaid u -> "결제 대기";
4|   case Paid p -> "결제 완료";
5|   case Shipped s -> "배송 중";
6|   case Cancelled c -> "취소됨";
7| };
```

### 7. Record Pattern

**[코드 B.7]** Record Pattern
```java
1| // package: com.ecommerce.shared
2| return switch (status) {
3|   case Paid(var at, var txId) -> "결제일: " + at + ", TX: " + txId;
4|   case Unpaid _ -> "미결제";
5|   case Shipped _, Cancelled _ -> "기타";
6| };
```

### 8. State Machine

**[코드 B.8]** State Machine
```java
1| // package: com.ecommerce.shared
2| public Result<Order, OrderError> ship(TrackingNumber tracking) {
3|   return switch (status) {
4|     case Paid p -> Result.success(withStatus(new Shipped(LocalDateTime.now(), tracking)));
5|     case Unpaid _, Shipped _, Cancelled _ -> Result.failure(new CannotShip(id));
6|   };
7| }
```

### 9. Phantom Type

**[코드 B.9]** Email record
```java
1| // package: com.ecommerce.shared
2| public record Email<S extends EmailState>(String value) {}
3| sealed interface EmailState permits Unverified, Verified {}
4| Email<Verified> verify(Email<Unverified> email, String code) { /*...*/ }
```

### 10. Total Function

**[코드 B.10]** Total Function
```java
1| // package: com.ecommerce.shared
2| public static Result<Money, DivisionError> safeDivide(Money amount, int divisor) {
3|   if (divisor == 0) return Result.failure(new DivisionByZero());
4|   return Result.success(amount.divide(divisor));
5| }
```

### 11. Result Type

**[코드 B.11]** Result interface
```java
1| // package: com.ecommerce.shared
2| public sealed interface Result<S, F> {
3|   record Success<S, F>(S value) implements Result<S, F> {}
4|   record Failure<S, F>(F error) implements Result<S, F> {}
5| }
```

### 12. Railway-Oriented Programming

**[코드 B.12]** Railway-Oriented Programming
```java
1| // package: com.ecommerce.order
2| Result<Order, OrderError> result = validate(cmd)
3|   .flatMap(v -> applyPricing(v))
4|   .flatMap(p -> processPayment(p))
5|   .map(paid -> createOrder(paid));
```

### 13. Pipeline Pattern

**[코드 B.13]** Pipeline Pattern
```java
1| // package: com.ecommerce.order
2| public Result<Order, OrderError> execute(CreateOrderCommand cmd) {
3|   return OrderDomainService.validate(cmd, member, inventory)
4|     .flatMap(validated -> applyPricing(validated, coupon))
5|     .flatMap(priced -> charge(priced))
6|     .map(paid -> save(paid));
7| }
```

### 14. Functional Core / Imperative Shell

**[코드 B.14]** Core (pure): static Money calculateTotal(List<Item> items, Discount d) { ... }
```java
1| // package: com.ecommerce.coupon
2| // Core (pure): static Money calculateTotal(List<Item> items, Discount d) { ... }
3| // Shell (I/O): items = repo.findAll(); total = calculateTotal(items, d); repo.save(order);
```

### 15. Validation (Applicative)

**[코드 B.15]** Validation (Applicative)
```java
1| // package: com.ecommerce.auth
2| Validation<User, List<Error>> user = Validation.combine3(
3|   validateName(name), validateEmail(email), validatePhone(phone), User::new
4| ); // 모든 에러 수집
```

### 16. Monoid (Assoc + Identity)

**[코드 B.16]** Monoid (Assoc + Identity)
```java
1| // package: com.ecommerce.shared
2| Money total = orderTotals.parallelStream()
3|   .reduce(Money.zero(Currency.KRW), Money::add);
4| // 항등원 + 결합법칙 -> 병렬 안전
```

### 17. Idempotent Operation

**[코드 B.17]** Idempotent Operation
```java
1| // package: com.ecommerce.payment
2| public Result<Payment, Error> process(PaymentId id, PaymentRequest req) {
3|   if (paymentRepo.exists(id)) return Result.success(paymentRepo.findById(id).get());
4|   return doProcess(req).map(p -> { paymentRepo.save(id, p); return p; });
5| }
```

### 18. Rule as Data

**[코드 B.18]** Rule interface
```java
1| // package: com.ecommerce.rule
2| public sealed interface Rule {
3|   record Equals(String attr, String value) implements Rule {}
4|   record GTE(String attr, int threshold) implements Rule {}
5|   record And(Rule left, Rule right) implements Rule {}
6| }
```

### 19. Rule Engine

**[코드 B.19]** Rule Engine
```java
1| // package: com.ecommerce.rule
2| public static boolean evaluate(Rule rule, Customer c) {
3|   return switch (rule) {
4|     case Equals(var a, var v) -> getAttr(c, a).equals(v);
5|     case GTE(var a, var t) -> getInt(c, a) >= t;
6|     case And(var l, var r) -> evaluate(l, c) && evaluate(r, c);
7|   };
8| }
```

### 20. Interpreter Separation

**[코드 B.20]** Rule = WHAT (data), Interpreter = HOW (function)
```java
1| // package: com.ecommerce.rule
2| // Rule = WHAT (data), Interpreter = HOW (function)
3| boolean result = RuleEvaluator.evaluate(rule, customer);   // 평가
4| String desc = RuleExplainer.explain(rule);                 // 설명
5| String sql = RuleToSql.toWhere(rule);                      // SQL 변환
```

### 21. Entity-Record Mapper

**[코드 B.21]** Entity-Record Mapper
```java
1| // package: com.ecommerce.order
2| public static Order toDomain(OrderEntity e) {
3|   OrderStatus status = switch (e.getStatus()) {
4|     case PAID -> new Paid(e.getPaidAt(), new TransactionId(e.getPaymentId()));
5|     case PENDING -> new Unpaid(e.getCreatedAt());
6|   };
7|   return new Order(new OrderId(e.getId().toString()), mapItems(e.getItems()), status);
8| }
```

### 22. Gradual Migration

**[코드 B.22]** Step 1: 순수 함수 추출
```java
1| // package: com.ecommerce.shared
2| // Step 1: 순수 함수 추출
3| public class PriceCalc { static Money total(List<Item> items, Discount d) { ... } }
4| // Step 2: Service에서 호출
5| Money total = PriceCalc.total(items, discount); // 기존 Service 구조 유지!
```

### 23. Bounded Context Model

**[코드 B.23]** DisplayProduct record
```java
1| // package: com.ecommerce.product
2| public record DisplayProduct(ProductId id, String name, Money price) {}     // 전시용
3| public record InventoryProduct(ProductId id, int stock) {}                  // 재고용
4| public record SettlementProduct(ProductId id, Money supplyPrice) {}         // 정산용
```

### 24. Defensive Copy

**[코드 B.24]** Cart record
```java
1| // package: com.ecommerce.shared
2| public record Cart(List<CartItem> items) {
3|   public Cart { items = List.copyOf(items); } // 외부 변경 차단
4| }
```

### 25. Null Object via ADT

**[코드 B.25]** Discount interface
```java
1| // package: com.ecommerce.coupon
2| public sealed interface Discount {
3|   record NoDiscount() implements Discount {}    // null 대신 항등원
4|   record Percentage(int rate) implements Discount {}
5|   record FixedAmount(Money amt) implements Discount {}
6| }
```

### 26. Event Store

**[코드 B.26]** OrderEvent interface
```java
1| // package: com.ecommerce.order
2| public sealed interface OrderEvent {
3|   record OrderPlaced(OrderId id, List<OrderItem> items, LocalDateTime at) implements OrderEvent {}
4|   record OrderPaid(OrderId id, PaymentId paymentId, LocalDateTime at) implements OrderEvent {}
5|   record OrderShipped(OrderId id, TrackingNumber tracking, LocalDateTime at) implements OrderEvent {}
6| }
```

### 27. Aggregate Reconstitution

**[코드 B.27]** Aggregate Reconstitution
```java
1| // package: com.ecommerce.order
2| public static OrderState reconstitute(List<OrderEvent> events) {
3|   return events.stream()
4|     .reduce(OrderState.initial(), OrderAggregate::apply, (s1, s2) -> s2);
5| }
```

### 28. Decider Pattern

**[코드 B.28]** Decider Pattern
```java
1| // package: com.ecommerce.order
2| public static Result<List<OrderEvent>, OrderError> decide(OrderState state, OrderCommand cmd) {
3|   return switch (cmd) {
4|     case PlaceOrder c -> handlePlace(state, c);
5|     case PayOrder c -> handlePay(state, c);
6|   };
7| }
```

### 29. Projection (CQRS)

**[코드 B.29]** Projection (CQRS)
```java
1| // package: com.ecommerce.order
2| public void on(OrderEvent event) {
3|   switch (event) {
4|     case OrderPlaced e -> summaryRepo.save(new OrderSummary(e.id(), "PLACED", e.at()));
5|     case OrderPaid e -> summaryRepo.updateStatus(e.id(), "PAID", e.at());
6|   }
7| }
```

### 30. Aggregate Root Pattern

**[코드 B.30]** Aggregate Root Pattern
```java
1| // package: com.ecommerce.order
2| public record Order(OrderId id, List<OrderItem> items, OrderStatus status) {
3|   public Order addItem(ProductId p, Quantity q, Money price) {
4|     var newItem = new OrderItem(ItemId.generate(), p, q, price);
5|     return new Order(id, List.copyOf(append(items, newItem)), status);
6|   }
7| }
```

### 31. Repository Pattern (DDD)

**[코드 B.31]** Repository Pattern (DDD)
```java
1| // package: com.ecommerce.order
2| public interface OrderRepository {
3|   Optional<Order> findById(OrderId id);  // Domain type 반환
4|   void save(Order order);                 // Aggregate 단위 저장
5| }
```

### 32. Domain Service Pattern

**[코드 B.32]** Domain Service Pattern
```java
1| // package: com.ecommerce.order
2| public class OrderDomainService {
3|   public static Result<Order, OrderError> validateAndCreate(
4|     CreateOrderCommand cmd, Customer customer, StockInfo stock) {
5|     return validateStock(cmd.items(), stock).map(items -> new Order(...));
6|   }
7| }
```

### 33. First-Class Function

**[코드 B.33]** First-Class Function
```java
1| // package: com.example.pattern
2| // 함수를 변수에 할당
3| Function<Order, Money> totalFn = Order::calculateTotal;
4| // 함수를 인자로 전달
5| Money result = applyToOrder(order, totalFn);
6| // 함수를 반환값으로 사용
7| Function<Order, Boolean> filter = createFilter("VIP");
```

### 34. Higher-Order Function

**[코드 B.34]** Higher-Order Function
```java
1| // package: com.example.pattern
2| // 함수를 인자로 받음
3| public static <T, R> List<R> map(List<T> list, Function<T, R> fn) {
4|   return list.stream().map(fn).toList();
5| }
6| // 함수를 반환
7| public static Predicate<Order> minAmount(Money threshold) {
8|   return order -> order.total().isGreaterThan(threshold);
9| }
```

### 35. Function Composition

**[코드 B.35]** Function Composition
```java
1| // package: com.example.pattern
2| Function<String, String> trim = String::trim;
3| Function<String, String> lower = String::toLowerCase;
4| Function<String, String> normalize = trim.andThen(lower);
5|
6| String result = normalize.apply("  HELLO  ");  // "hello"
```

### 36. Functor

**[코드 B.36]** Functor
```java
1| // package: com.ecommerce.shared
2| // Optional은 Functor - map으로 내부 값 변환
3| Optional<Order> order = findOrder(id);
4| Optional<Money> total = order.map(Order::calculateTotal);
5|
6| // Result도 Functor
7| Result<Order, Error> result = validate(cmd).map(Order::new);
```

### 37. Monad

**[코드 B.37]** Monad
```java
1| // package: com.ecommerce.shared
2| // flatMap으로 중첩 컨테이너 평탄화
3| Optional<Order> order = findOrder(id);
4| Optional<Payment> payment = order.flatMap(o -> findPayment(o.paymentId()));
5| // flatMap 없이: Optional<Optional<Payment>> - 중첩됨
6|
7| // Result 체이닝
8| Result<Order, Error> result = validate(cmd)
9|   .flatMap(v -> createOrder(v))
10|   .flatMap(o -> processPayment(o));
```

### 38. Applicative

**[코드 B.38]** Applicative
```java
1| // package: com.ecommerce.shared
2| // 독립적인 검증을 병렬로 수행, 모든 에러 수집
3| Validation<User, List<Error>> user = Validation.combine(
4|   validateName(name),      // 독립적
5|   validateEmail(email),    // 독립적
6|   validateAge(age),        // 독립적
7|   User::new                // 모두 성공시 결합
8| );
```

### 39. Lens Pattern

**[코드 B.39]** Lens Pattern
```java
1| // package: com.ecommerce.shared
2| record Lens<S, A>(Function<S, A> get, BiFunction<S, A, S> set) {
3|   public S modify(S s, Function<A, A> fn) { return set.apply(s, fn.apply(get.apply(s))); }
4|   public <B> Lens<S, B> andThen(Lens<A, B> other) {
5|     return new Lens<>(s -> other.get.apply(get.apply(s)),
6|       (s, b) -> set.apply(s, other.set.apply(get.apply(s), b)));
7|   }
8| }
9| // 중첩 필드 업데이트: order.address.city
10| Lens<Order, String> cityLens = orderAddressLens.andThen(addressCityLens);
```

### 40. Anti-Corruption Layer

**[코드 B.40]** Anti-Corruption Layer
```java
1| // package: com.ecommerce.infra
2| // 외부 API 응답을 도메인 모델로 변환하는 ACL
3| public class PaymentGatewayAcl {
4|   public Result<Payment, PaymentError> process(Order order) {
5|     ExternalPaymentResponse ext = externalApi.charge(toExternal(order));
6|     return toDomain(ext);  // 외부 모델 -> 도메인 모델 변환
7|   }
8|   private Payment toDomain(ExternalPaymentResponse r) {
9|     return new Payment(new PaymentId(r.id()), Money.of(r.amount(), r.currency()));
10|   }
11| }
```

### 41. Typestate Pattern

**[코드 B.41]** Typestate Pattern
```java
1| // package: com.ecommerce.order
2| // 각 상태가 별도 타입
3| record DraftOrder(OrderId id, List<OrderItem> items) {
4|   SubmittedOrder submit() { return new SubmittedOrder(id, items, LocalDateTime.now()); }
5| }
6| record SubmittedOrder(OrderId id, List<OrderItem> items, LocalDateTime at) {
7|   PaidOrder pay(PaymentId paymentId) { return new PaidOrder(id, items, paymentId); }
8| }
9| // 컴파일 타임에 잘못된 전이 방지: draftOrder.pay() -> 컴파일 에러!
```

### 42. Memoization

**[코드 B.42]** Memoization
```java
1| // package: com.example.pattern
2| public class Memoizer<T, R> {
3|   private final Map<T, R> cache = new ConcurrentHashMap<>();
4|   private final Function<T, R> fn;
5|   public Memoizer(Function<T, R> fn) { this.fn = fn; }
6|   public R apply(T t) { return cache.computeIfAbsent(t, fn); }
7| }
8| // 사용: 순수 함수만 메모이제이션 가능
9| var memoizedFib = new Memoizer<>(this::fibonacci);
```

### 43. Lazy Evaluation

**[코드 B.43]** Lazy Evaluation
```java
1| // package: com.example.pattern
2| record Lazy<T>(Supplier<T> supplier) {
3|   private T value;
4|   private boolean evaluated = false;
5|   public T get() {
6|     if (!evaluated) { value = supplier.get(); evaluated = true; }
7|     return value;
8|   }
9| }
10| // 비용이 큰 연산을 필요할 때까지 지연
11| Lazy<Report> report = new Lazy<>(() -> generateExpensiveReport());
```

### 44. Currying

**[코드 B.44]** Currying
```java
1| // package: com.example.pattern
2| // 다인자 함수를 단인자 함수 체인으로 변환
3| BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
4| // Curried version
5| Function<Integer, Function<Integer, Integer>> curriedAdd = a -> b -> a + b;
6|
7| Function<Integer, Integer> add5 = curriedAdd.apply(5);  // 부분 적용
8| int result = add5.apply(3);  // 8
```

### 45. Fold / Reduce

**[코드 B.45]** Fold / Reduce
```java
1| // package: com.ecommerce.shared
2| // 컬렉션을 단일 값으로 축약
3| Money total = items.stream()
4|   .map(OrderItem::lineTotal)
5|   .reduce(Money.zero(Currency.KRW), Money::add);
6|
7| // Event Sourcing에서 상태 재구성
8| OrderState state = events.stream()
9|   .reduce(OrderState.initial(), OrderState::apply, (s1, s2) -> s2);
```

### 46. Parse Don't Validate

**[코드 B.46]** Parse Don't Validate
```java
1| // package: com.ecommerce.shared
2| // [X] 검증만 하고 결과를 버림
3| boolean isValid(String email) { return email.contains("@"); }
4|
5| // [O] 검증 결과를 새 타입으로 캡처
6| public static Result<Email, EmailError> parse(String input) {
7|   if (!input.contains("@")) return Result.failure(new InvalidEmail(input));
8|   return Result.success(new Email(input));  // 검증된 타입 반환
9| }
```

### 47. Make Illegal States Unrepresentable

**[코드 B.47]** Make Illegal States Unrepresentable
```java
1| // package: com.ecommerce.order
2| // [X] 불가능한 상태 표현 가능
3| record Order(boolean isPaid, boolean isShipped, PaymentId paymentId) {}
4| // isPaid=false, isShipped=true 가능! (불가능한 상태)
5|
6| // [O] ADT로 불가능한 상태 자체를 타입으로 표현 불가
7| sealed interface Order permits Unpaid, Paid, Shipped {}
8| record Unpaid(OrderId id) implements Order {}
9| record Paid(OrderId id, PaymentId paymentId) implements Order {}
10| record Shipped(OrderId id, PaymentId paymentId, TrackingNo tracking) implements Order {}
```

### 48. Onion Architecture

**[코드 B.48]** Onion Architecture
```java
1| // package: com.ecommerce
2| // 바깥에서 안으로: Infra -> Application -> Domain
3| // Domain (core): 순수한 비즈니스 로직, 외부 의존성 없음
4| // com.ecommerce.order.domain.Order, OrderDomainService
5|
6| // Application: 유스케이스 조율, 트랜잭션 경계
7| // com.ecommerce.order.application.PlaceOrderUseCase
8|
9| // Infrastructure: DB, 외부 API, Framework
10| // com.ecommerce.order.infra.JpaOrderRepository
```

### 49. Ubiquitous Language

**[코드 B.49]** Ubiquitous Language
```java
1| // package: com.ecommerce.order
2| // 도메인 전문가와 개발자가 같은 용어 사용
3| // [X] 개발자 용어: processOrder(), setStatus(), item_qty
4| // [O] 도메인 용어: placeOrder(), shipOrder(), orderQuantity
5|
6| public record Order(OrderId id, List<OrderLine> lines, OrderStatus status) {
7|   public Order place() { /* "주문하다" */ }
8|   public Order ship(TrackingNumber tracking) { /* "배송하다" */ }
9|   public Order cancel(CancellationReason reason) { /* "취소하다" */ }
10| }
```

---

## 패턴 조합 가이드

**[표 B.2]** 패턴 조합 가이드

| 상황 | 추천 패턴 조합 |
|------|---------------|
| 단순 CRUD | Value Object + Compact Constructor |
| 다중 상태 도메인 | Sum Type + State Machine + Exhaustive Matching |
| 실패 가능 연산 | Result + flatMap Pipeline |
| 폼 검증 | Validation (Applicative) |
| 복잡한 워크플로우 | Pipeline + Functional Core / Imperative Shell |
| 자주 변경되는 규칙 | Rule Engine + Dynamic Loading |
| JPA 기존 코드 | Entity-Record Mapper + Gradual Migration |
| 분산/병렬 처리 | Monoid + Idempotent Operation |
| 도메인 모델링 | Bounded Context + ADT + Value Object |
| 이력 추적 필수 도메인 | Event Store + Aggregate Reconstitution + Decider |
| 복잡한 조회 요구 | Event Sourcing + Projection (CQRS) |
| FP 함수 설계 | First-Class Function + Higher-Order Function + Function Composition |
| 컨테이너 체이닝 | Functor + Monad + Applicative |
| 불변 객체 업데이트 | Lens Pattern + Wither Pattern |
| 외부 시스템 통합 | Anti-Corruption Layer + Entity-Record Mapper |
| 상태별 타입 안전성 | Typestate Pattern + Make Illegal States Unrepresentable |
| 성능 최적화 | Memoization + Lazy Evaluation |
| 입력 검증 | Parse Don't Validate + Value Object + Compact Constructor |
| 도메인 설계 기반 | Ubiquitous Language + Bounded Context + Onion Architecture |
