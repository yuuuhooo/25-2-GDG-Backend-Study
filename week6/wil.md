# 6주차. 예외 처리와 Swagger

## 데이터의 무결성 보장: DTO와 유효성 검사


### 유효성 검사
의도하지 않은 상황이 발생했을 때(ex.password 100자 이상 입력 시)

잘못한 것은 잘못된 데이터를 삽입한 클라이언트,

그러나 현재는 500 서버 내부 에러를 띄움.


-> 그래서 클라이언트의 문제라면 4xx의 상태코드 응답이 필요하다!!


**유효성 검사**

- 요청으로 들어오는 데이터가 올바른 형식인지 검사하는 것
- Spring: DTO에서 유효성을 검사
- '올바른 형식'에 주목.

  → 유효성 검사 = 입력 데이터 형식을 검사

  → 존재하는 member인지 여부 판단 못함. (DB 조회를 통해 판단 필요)



- @NotNull : 비어있으면 안 됨
- @Pattern: 특정 형식을 준수해야 함
- @Size: 크기 제한을 준수해야 함


Controller 메서드에 @Valid 추가까지 해줘야 됨.


유효성 검사 어노테이션: 제약 사항 & 에러 메시지 명시

@Valid → 제약 조건에 맞는지 검사




### 기존 updateInfo의 문제점?
-> 엔티티 계층의 메서드가 두 가지 책임을 가지고 있었음.


1. 도메인 모델 책임: 데이터와 도메인 로직 (값 설정)

2. 비즈니스 로직 책임: "어떤 필드를 업데이트할지 결정"

  (null 체크를 통한 조건부 업데이트)





→ SOLID 원칙 중 SRP(단일 책임 원칙) 위반!!


## 체계적인 오류 관리: 전역 예외 처리(Global Exception Handling)


### 예외 처리
- 잘못 들어간 게 무엇인지 알 수 없다.


→ 에러가 발생했을 때, 원인을 알려주는 에러 메시지를 담도록 직접 응답 객체 생성


### 예외 처리.zip
1. Global Exception Handler
  - 공통 예외 처리 핸들러(member, order, product..)
  - 에러 정보 반환용 DTO
  - AOP(관점 지향 프로그래밍)


2. 커스텀 예외 처리
  - 커스텀 예외 클래스. 근데 이제 RuntimeException을 상속한.
  - Global Exception Handler에 등록 → 에러 원인 명확히 알 수 있음!


3. 에러 메시지 클래스
  - 예외 메시지 문자열 중복 사용됨 → 추가/수정하기 힘들다.
  - 따라서 상수로 정의



- Spring에서 제공.
- 예외 종류에 따라 response를 설정 가능.
- Global Exception을 처리 = 스프링 애플리케이션 전역의 모든 에러 처리 방법을 결정



**Global Exception Handler(전역 예외 핸들러)**
- GlobalExceptionHandler의 handleUnknownError
- @ExceptionHandler(Exception.class)

  Exception 타입 에러 발생? → handleUnknownError 메서드 실행


[일반화]

**@ExceptionHandler(A.class)**

A 타입 에러 발생?

→ 해당 에러 타입을 다루는 핸들러가 Controller 메서드 대신 Response Body를 생성 & 응답




**[handleUnknownError의 Exception.class]**

Exception.class: 모든 에러 클래스의 공통 부모


**@ControllerAdvice?**

모든 컨트롤러에서 발생하는 예외를 중앙에서 처리


우리가 만든 특정 핸들러에서 처리 못한 예외는

전부 handleUnknownError 메서드가 처리.




**AOP(Aspect-Oriented Programming)**

- 관점 지향 프로그래밍
- 객체 지향 프로그래밍 보완 개념


**OOP(객체 지향) vs. AOP(관점 지향)**


OOP = 핵심 기능을 모듈화
  - 비즈니스 로직을 클래스와 메서드로 구조화
  - 예: 회원 관리, 주문 처리, 상품 관리 등



AOP = 부가 기능을 모듈화
  - 여러 클래스에 걸쳐 반복되는 공통 기능을 분리
  - 예: 로깅, 트랜잭션, 보안, 예외 처리 등




### Global Exception Handler(전역 예외 핸들러)
#### @ControllerAdvice?
- 모든 컨트롤러에서 발생하는 예외를 중앙에서 처리
  = 모든 컨트롤러의 공통 관심사(에러처리)를 별도의 클래스로 분리하여 구현


#### 커스텀 예외 처리
- 커스텀 예외 클래스 생성
- 실행 중 예외?
  -> RumtimeException 상속!
- 커스텀 예외 처리 핸들러 생성
- handleNotFoundException



### 예외 메시지 클래스
- 예외 메시지 문자열 중복 사용됨 → 추가/수정하기 힘들다.
- 따라서 상수로 정의



## 협업을 위한 API 명세: Swagger(OpenAPI)

### API 문서화
- API 사용 설명서 공유
- 백엔드 API 명세를 문서로 공유
- 클라이언트(프론트엔드)와 소통/협업 시 API 문서를 공유


1. spring doc 사용 → OpenAPI 규격의 API 문서 생성
- OpenAPI: API 표준 명세

2. Swagger UI 사용 → API 문서에 Swagger 디자인 적용



#### API 문서화 작업 위한 어노테이션

- @Tag: API 그룹화 (컨트롤러 레벨)
- @Operation: API의 요약과 설명 (메서드 레벨)
- @ApiResponse: 단일 응답 코드 설명 (메서드 레벨)