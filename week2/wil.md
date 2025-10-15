# 2주차. 계층형 아키텍처와 컨트롤러, 서비스

## Spring의 핵심 설계

### 스프링 계층형 아키텍처(Layered Archietecture)

**Browser(Frontend)** = 손님


**Controller(컨트롤러 계층)** = 웨이터
- Browser와 소통
- 고기 굽는 법 모름(비즈니스 로직 x)
- Service에게 시킴킴


B -> C : 스테이크 줘


**Service** = 주방장
- 스테이크 조리법(비즈니스 로직) 다 알고 있음
- 그러나 재료(데이터)가 없음


C -> S : 스테이크 구워줘


**DAO(Data Access Object)/Repository** = 창고 관리인
- 창고 어느 선반에 한우 등심(데이터) 있는지 위치 알고 있음.
- 재료(데이터) 입고되면 정리까지 함
- 출고도 잘 함


**DB(Database)** = 냉장/냉동 창고


Repository -> DB : 한우 꺼내옴


S -> D : 한우 등심 A+ 200g 줘


**DTO** = 주문서, 영수증
- 주문에 꼭 필요한 정보만 포함 ex. 메뉴 이름, 수량
- 데이터 전송 객체(Data Transer Object)
- 소통 목적에 맞는, 필요한 정보만 전달


**Entity** = 원재료
- 모든 정보 포함 ex. 유통기한, 원산지, 등급
- DB 테이블과 맵핑되는 핵심 객체
- 외부 직접 노출 금지(데이터 일관성/보안)


### 컨트롤러 계층(Contorller Layer)

**Controller** = 웨이터
- HTTP 요청 / 응답
- 특정 endpoint(URL)로 요청을 가장 먼저 처리
- DTO (Data Transfer Object)를 사용하여 서비스 계층과 데이터 주고받음


---


ex1. GET /posts/100 -> 글 조회 요청 보냄

돌아오는 응답:

200 OK

...

{

  "title": "제목에 해당하는 데이터",

  "content": "본문 내용에 해당하는 데이터"

}

---

ex2. POST /posts

...

{

  "title": "제목 데이터",

  "content": "본문 내용 데이터"

}  -> 글 작성 요청 보냄


돌아오는 응답:

201 Created


### 패키지 구조: 계층형 vs 도메인형

**계층형 구조**
- 애플리케이션을 기능별로 나눔
- Controller는 Controller에, Service는 Service에

**도메인형 구조**

- 도메인 관련 모든 클래스를 포함


- 특정 도메인의 코드를 한 곳에! -> 코드 탐색 easy

- 도메인 단위 개발, 유지보수하기 용이.

- New 도메인 추가 시 다른 곳에 영향 없음.

### 실습
@RequestBody MemberCreateRequest request

-> body에 들어있는 JSON을 객체로 변환하는 부분 담당


.created(URI.create("/members") + memberId).build()

-> 이 과정을 수행하게 해주는게 ResponseEntity

상태코드 204 No content[요청 성공 but 보내줄 콘텐츠 없음] 

= delete 메소드 사용 시 성공적으로 삭제 됐을 때 보내는 응답 코드.


### 서비스 계층(Service Layer)
- 애플리케이션의 비즈니스 로직이 담기는 계층
- 엔티티 또는 DTO를 통해 컨트롤러와 레포지토리 사이에서 중계를 함

ex. member 데이터를 수정하려면?

1. member를 조회
2. member 데이터 수정

만약 메소드의 실행으로 3가지 작업이 발생한다면

Spring에서는 **셋 다 실행 되거나 셋 다 실행이 안 되거나** 둘 중에 하나로 이루어져야 함.


**원자성(atomicity)을 가진다.**

해결방법: 트랙잭션 단위로 처리.

**=> 서비스 계층 메소드 위에 @transactional 어노테이션 추가**




## 스프링 빈&의존성 주입

### 스프링(Spring)
- 대표적인 Java 백엔드 애플리케이션 프레임워크
- 객체지향 원리를 잘 지키게 해줌

### 스프링부트(Spring Boot)
- 스프링을 쉽고 편하게 쓸 수 있게 돕는 도구
- 스프링 짝꿍

### 스프링 애플리케이션 구조
스프링 안에 내장 톰켓 서버가 먼저 HTTP 리퀘스트를 받게 됨 -> 스프링 컨테이너라는 곳으로 들어감

#### Spring Container
- 스프링 빈 저장소
- 어플리케이션 컨텍스트(Application Context)

#### Spring Bean
- 어플리케이션 전역에서 사용할 공용 객체
- 스프링 컨테이너(공용 창고)에 빈을 저장, 필요한 빈을 컨테이너에서 받아 사용한다.
- 필요한 빈은 스프링 프레임워크가 자동으로 가져다 준다.
- 빈을 요구하는 객체 역시 스프링 빈이다.
- 빈은 빈만 요구할 수 있음.


### 빈 등록 방법
- 설정 파일 작성(수동 등록)
- 컴포넌트 스캔(자동 등록)

#### 컴포넌트 스캔(자동 등록)
**@Component, @ComponentScan**
1. 컴포넌트 스캔(@ComponentScan)
- @SpringBootApplication에 포함됨
- 어떤 클래스들이 Spring Bean인지 찾아서 등록
2. 컴포넌트(@Component)
- @Controller, @Service, @Repositor, @Entity 등에 포함됨.
- 이 클래스가 Spring Bean이야! 라고 표시

-> 빈으로 등록하고 싶은 클래스에 @Component 붙이면 된다


### 의존성 주입(Dependency Injection, DI)
- 내가 의존하는 객체를 직접 생성하지 않고 밖에서 주입 받는 것
- 스프링한테 스프링 컨테이너 안에 있는 빈을 달라고 하면 빈을 가져올 수 있는 것

#### 의존성 주입을 사용하는 이유
-> 매번 필요한 객체를 생성하는 대신, 생성해둔 객체를 사용하므로 **메모리를 효율적으로 사용**

### 의존성 주입 방법
1. 생성자 주입
2. 필드 주입
3. 수정자 주입(세터 주입)

#### 생성자 주입 방법(간단ver)
1. 필요한 의존성을 final 키워드를 사용해 추가
2. @RequiredArgsConstructor를 사용해 생성자를 추가한다


---

### java 구문 추가 정리

1. **ResponseEntity.created(URI.create("/orders" + orderId))**
- HTTP 상태 코드: 201 Created를 설정함(자원이 성공적으로 생성되었음을 의미)
- 헤더 설정: 응답 헤더에 Location 필드를 추가하고, 생성된 자원의 URI("/orders" + orderId)를 값으로 설정함

2. **.build()**
- ResponseEntity 객체를 최종적으로 완성하여 반환하는 역할
- 응답 본문(Body)이 없을 때(Void) 사용됨

3. **.ok()**
- HTTP 상태 코드를 200 OK(요청이 성공적으로 처리됨)로 설정함
- 괄호 안에 전달된 객체를 **응답 본문(Body)** 에 담는 역할
- *@RestController* 에 의해 Spring은 이 order 객체를 **JSON 또는 XML 형식으로 자동 변환(직렬화)** 하여 클라이언트에게 전송

4. **@RequestBody**
- *요청 본문 데이터 수신:*
클라이언트가 POST나 PATCH 요청을 보낼 때, 주문 생성에 필요한 데이터(예: 상품 목록, 수량, 배송지 주소 등)는 요청 본문(Body)에 JSON이나 XML 형태로 담겨 서버로 전송됨.
- @RequestBody는 Spring에게 "이 메서드의 인자는 HTTP 요청 본문에 있는 데이터를 사용해야 한다"고 지시함

- *자동 객체 변환 (역직렬화):* 
Spring은 OrderCreateRequest와 같은 자바 객체를 인자로 받으면, HTTP Message Converter (주로 Jackson 라이브러리를 사용하여 JSON 처리)를 내부적으로 호출함
- 이 컨버터는 요청 본문에 있는 JSON 문자열을 읽어, 그 필드 이름과 일치하는 속성을 가진 새로운 OrderCreateRequest 자바 객체 인스턴스를 생성하고 값을 채워 넣음. 이 과정을 **역직렬화(Deserialization)** 라고 함


- 개발자는 request.getDeliveryAddress()나 request.getItems()와 같이 자바 객체 메서드를 사용하여 데이터를 편리하게 접근하고 비즈니스 로직에 사용할 수 있게 됨
- 수동으로 JSON 파싱 코드를 작성해야 하는 번거로움을 없애주고, 코드를 간결하게 만들어 줌