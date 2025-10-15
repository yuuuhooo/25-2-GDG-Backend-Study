웹이란 무엇인지? HTTP? REST API?

### 인터넷
: 전 세계 컴퓨터와 기기를 연결하는 거대한 글로벌 네트워크. 인터네트워크.
### 웹
: 인터넷 위에서 동작하는 서비스 중 하나. 인터넷에 연결된 전 세계 사용자들이 서로의 정보를 공유할 수 있는 장소


**컴퓨터끼리 통신 방법**

: 클라이언트 - 서버 모델

**클라이언트** : 요청을 보내고 서버의 응답 결과를 받아 사용

**서버** : 클라이언트의 요청을 받아 처리하고 응답을 반환

클라이언트가 서버에 요청을 보낼 때도 고유한 주소가 필요.
웹에서 사용하는 주소 체계가 바로 URL(Uniform Resource Locator).



## URL의 구조

http://www.example.com:5883/category/food.html?topic=pizza&size=large


### Host
: 리소스가 위치한 서버의 IP 주소 혹은 도메인

www.example.com 에 해당됨

### Port
: 서버의 특정 네트워크 포트 번호 (일반적으로 생략)

:5883 에 해당됨



### Path
: 서버 내에서 원하는 리소스의 경로

/category/food.html 에 해당됨

category 아래에 food.html 자원을 달라는 의미

### Query
: 서버에 추가적인 정보를 보내는 파라미터로, ? 뒤에 key-value 형식으로 나열

?topic=pizza&size=large 에 해당됨

key는 topic, value는 pizza / key는 size, value는 large. 여러 파라미터를 보낼 땐 &으로 연결

### Scheme(Protocol)
: 컴퓨터와 같은 장치들 사이에서 데이터를 주고받는 방식, 통신을 하기 위한 규칙

http 에 해당됨



## HTTP (HyperText Transper Protocol)
웹에서 데이터를 주고받는 서버-클라이언트 모델의 프로토콜

클라이언트의 요청(request)과 서버의 응답(response)을 통해 작동

### HTTP의 주요 특징 2가지
**무상태성(Stateless)** : 서버는 클라이언트의 이전 요청을 저장하지 않고, 매 요청을 독립적으로 처리

**비연결성(Connectionless)** : 클라이언트가 요청을 보내고 응답을 받은 후 서버와 연결을 유지하지 않음


### HTTP 요청 Request 구조

**start line**

요청 메서드, 요청할 경로, HTTP 버전 정보 포함

ex) GET /test.html HTTP/1.1

**headers**

요청에 대한 부가정보

**body**

실제 전송할 데이터


### HTTP 주요 메서드

GET: 리소스를 조회

POST: 리소스를 추가, 등록

PUT: 리소스를 교체, 없으면 새로 생성

РАТСН: 리소스의 일부를 수정

DELETE: 리소스를 삭제

PUT과 PATCH 모두 수정할 때 사용, PATCH는 일부만 수정할 때!




### HTTP 응답 Response 구조

**status line**

HTTP 버전, HTTP 상태 코드, 상태 메시지

ex) НТТР/1.1 200 ОK

**headers**

응답에 대한 부가정보

**body**

실제 응답 데이터



### HTTP 주요 상태 코드

200 OK: 요청이 성공적으로 처리됨

201 Created: 요청이 성공적으로 처리되어 새로운 리소스가 생성됨

400 Bad Request: 클라이언트의 요청이 잘못되어 서버가 이해하지 못함

404 Not Found: 지정한 리소스를 찾을 수 없음

500 Internal Server Error: 서버 내부 오류로 요청을 처리할 수 없음


### 통신 과정
클라이언트 컴퓨터가 서버 컴퓨터로 요청을 보냄 -> 서버 컴퓨터는 요청받은 페이지를 찾음 -> 응답 코드와 함께 해당 페이지 코드를 보냄 -> 클라이언트 컴퓨터가 그 코드로 화면에 렌더링을 함


매번 서버에서 HTML 페이지 자체를 보내주게 되면 서버에 부하가 발생, 사용자 입장에서도 뷸편 => 화면의 뼈대는 재사용하고, 필요한 데이터만 서버에서 받아와 부분적으로 다시 그리자

클라이언트 쪽에서 렌더링 해서 **클라이언트 사이드 렌더링** 이라고 함.



### 프론트엔드
사용자가 직접 보고 상호작용하는 화면, 사용자 인터페이스(Ul)를 개발

### 백엔드
사용자의 요청을 받아 실제 동작을 처리하고 데이터를 저장, 관리


## 데이터베이스(Database, DB)
데이터를 체계적으로 모아둔 저장소

일반적으로 컴퓨터 시스템에 전자적으로 저장

데이터베이스 관리 시스템(DBMS)으로 데이터베이스를 관리, 조작
-> 데이터 중복을 해결, 독립성을 확보, 무결성을 유지
대표적인 DBMS: MySQL, PostgresQL, MongoDB


ex) 블로그 포스트 작성

클라이언트: POST 메소드로 포스트 등록 요청을 서버에 보냄 -> 서버 컴퓨터는 이를 받아 데이터베이스에 저장 -> 성공했다면 201 Created 응답을 클라이언트에게 전달. 


요청과 응답이 어떤 형식인지, 어떤 구조인지, HTTP만으론 알 수 없음.

그래서 API를 사용!


## API (Application Programming |nterface)
한 프로그램이 다른 프로그램의 기능이나 데이터를 사용할 수 있도록 미리 정해놓은 약속(규칙)이자 소통 창구
클라이언트와 서버가 소통할 때 어떻게 요청을 보내고, 어떻게 응답할지 등을 미리 정해놓은 규칙과 기능의 목록



REST는 API 설계 방식 중 하나.


### REST 구성 요소
1. 자원 (Resource) - URI
모든 자원은 고유한 ID를 가지며, 이 ID는 /student/1 같은 HTTP URI이다

2. 행위 (Verb) - Method
자원을 조작하기 위해 HTTP Method를 사용한다

3. 표현 (Representation)
서버와 클라이언트가 데이터를 주고 받는 형식으로, JSON 형식이 일반적이다


- URI: URL을 포괄하는 개념. 자원을 식별하는 문자열.
- URL: 자원의 위치까지 알려주는 주소.


### JSON(JavaScript Object Notation)
자바 스크립트 객체 문법 기반. 매우 가벼운 데이터 형식.


REST는 **HTTP를 잘 활용하기 위한 원칙**이고,

REST API는 이 원칙을 준수해 만든 API이자 HTTP의 모범 사례.


### API 작성 실습
회원: 이름, 전화번호, 주소


(명세서에서는 겹치는 주소 제외하고 path만 간결하게 표현!)

1. 회원 등록
HTTP Method : POST

URI: /members

2. 회원 리스트 조회
   
HTTP Method : GET

URI:/members

3. 회원 상세 조회

HTTP Method : GET

URI: /members/{memberld}

4. 회원 정보 수정


HTTP Method : PATCH


URI:/members/{memberld}

5. 회원 삭제

HTTP Method: DELETE

URI: /members/{memberld}



- path variable
URI 일부를 변수처럼 사용해서 특정 자원을 식별하는 방식

/members/1 : 1번 멤버만 지목

/members/2 : 2번 멤버만 지목






### Spring
Java 엔터프라이즈 애플리케이션 개발에 사용되는 오픈소스 경량급 애플리케이션 프레임워크

Java 언어의 가장 큰 특징인 **객체 지향**을 잘 살려내서, 좋은 객체 지향 프로그램을 개발할 수 있도록 도와주는 프레임워크

**프레임워크** : 애플리케이션 개발을 쉽고 효율적으로 할 수 있도록 필요한 기본 구조와 공통 기능을 제공하는 뼈대(틀)

->스프링은 자바로 백엔드 애플리케이션을 빠르고 안정적으로 만들 수 있도록 기본 구조와 규칙을 제공하는 틀


### Spring Boot
복잡한 초기 설정 없이도 스프링 프레임워크를 아주 빠르고 쉽게 사용할 수있게 해주는 도구.



# API 작성

### 회원 기능
1. 회원 등록

HTTP Method : POST

URI: /members

2. 회원 리스트 조회

HTTP Method : GET

URI:/members

3. 회원 상세 조회

HTTP Method : GET

URI: /members/{memberld}

4. 회원 정보 수정

HTTP Method : PATCH

URI:/members/{memberld}

5. 회원 삭제

HTTP Method: DELETE

URI: /members/{memberld}



### 상품 기능
1. 상품 정보 등록

HTTP Method: POST

URI: /products

2. 상품 목록 조회

HTTP Method: GET

URI: /products

3. 개별 상품 정보 상세 조회

HTTP Method: GET

URI: /products/{productId}

4. 상품 정보 수정

HTTP Method: PATCH

URI: /products/{productId}

5. 상품 삭제

HTTP Method: PATCH

URI: /products/{productId}

(상품 판매 상태 변경, 만약 DELETE를 할 경우 추후 이전 주문 조회를 할 때 문제가 생길 수 있음)



### 주문 기능
1. 주문 정보 생성

HTTP Method: POST

URI: /orders

2. 주문 목록 조회

HTTP Method: GET

URI: /orders

3. 개별 주문 정보 상세 조회

HTTP Method: GET

URI: /orders/{orderId}

4. 주문 취소

HTTP Method: PATCH

URI: /orders/{orderId}

(주문 상태 변경. 포인트 적립 취소 등 작업이 필요할 수 있으니)