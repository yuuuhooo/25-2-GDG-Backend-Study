# 4주차. ERD, DB, 엔티티

### 엔티티(Entity) & DB
**Entity = 원재료**
- 모든 정보 포함 ex.유통기한, 원산지, 등급
- DB 테이블과 매핑되는 핵심 객체
- 외부 직접 노출 금지(데이터 일관성/보안)

**DB = 냉장/냉동 창고**

## 데이터베이스 모델링: ERD(Entity-Relationship Diagram)

**ERD = 데이터 청사진**
- Entity(개체): 데이터를 가진 대상
  ex. 회원, 상품, 주문 내역은 어떤 속성, 데이터를 가지고 있는지

- Relationshop(관계): 개체 사이의 연관성
  ex. 어떤 회원이 어떤 상품을 주문했는지

개체-관계 중심의 모델링 기법: ER Model(Entity-Realationship Model)

**ER Model을 시각적 표현(그림) -> ERD: 개발자 간, 클라이언트 간 소통 도구**

핵심 용어
1. 엔티티: 관리해야 할 데이터의 주체
  ex.회원, 상품, 주문
2. 속성(Attribute): 각 엔티티가 가지는 구체적 정보
  속성 = 필드(Field) = 칼럼(column)
  ex.Member: id, name, address
     Product: name, price, stock
3. 기본 키(Primary Key): id
  고유하게 식별하는 데 사용되는 하나 이상의 컬럼(필드)
4. 외래 키(Foreign Key)
  다른 테이블의 PK를 참조하는 속성
  테이블 간 연결 고리
5. 관계: 개체 사이의 연관성, 업무 규칙
  테이블 또는 외래 키로 구현
  - 다대일
  - 일대다
  - 일대일
  - 다대다


### 일대다 (1 : N)

1명의 회원(member)은 여러 개의 주문 내역(order)를 가진다.

member와 order의 관계는 1 : N

Member (1) : (N) Order

Order테이블은 member_id를 FK로 가짐

**"외래 키(FK)로 관계를 구현한다"**


### 다대다 (N : M)

Student (학생)

Course (강의) 

1. 한 명의 학생(Student)은 여러 개의 강의(Course)를 수강할 수 있다.
  (ex.'철수'는 '컴퓨터 개론'과 '자료구조'를 둘 다 들음)

2. 하나의 강의(Course)는 여러 명의 학생(Student)이 수강할 수 있다.
  (ex.'컴퓨터 개론'은 '철수'와 '영희'가 둘 다 들음)

잘못된 해결책: 외래 키(FK)로 해결해야지

**올바른 해결책: 중간 테이블(연결 엔티티) 도입**

**"테이블로 관계를 구현한다"**

- RD를 통해 DB 설계
- ERD Cloud를 활용

**식별 관계 vs. 비식별 관계**

식별: 강한 연관 관계
- 관계 대상의 PK를 자신의 PK로도 사용

비식별: 느슨한 연관관계
- 관계 대상의 PK를 자신의 FK로만 사용

보통 비식별 선택



## 설계도를 코드로: JPA 엔티티 구현

**엔티티**

- 자바와 DB가 소통하는 단위
- 엔티티 클래스를 정의 →
  JPA: 엔티티 클래스 정의를 참고하여 ‘테이블 생성 SQL문’을 작성하고 실행
- JPA는 SQL 작성 시간을 단축시켜 줌
- cf) 레포지토리 계층: CRUD SQL


**JPA & DB 의존성 추가**

- H2 데이터베이스: 자바로 작성된 관계형 데이터베이스. 가볍고, 빠르고, 별도 설치 X !
- build.gradle에 JPA, H2 Database 의존성 확인 및 추가
- 의존성 수정 후 gradle 로드 필요(intellij 코끼리 모양 클릭)




### 1. 엔티티 클래스

- @Entity, @Id 어노테이션이 필요
- Id(고유 식별자)값을 자동 생성: @GeneratedValue를 사용
  - (strategy는 IDENTITY로 설정)
  - 키 값 결정을 DB에게 위임


- @Column으로 컬럼 명, 컬럼 타입 등을 지정

### 2. 외래 키(FK)

- 엔티티 객체를 필드로 넣은 후 @JoinColumn, @ManyToOne 사용.
- @ManyToOne 에서 fetch 속성은 LAZY로 지정
- ‘엔티티 객체’를 필드로 지정 → ORM(JPA)가 외래키로 알아서 처리


- **@JoinColumn**: FK 컬럼 정보를 명시(name 등)
- **@ManyToOne** @OneToOne @OneToMany @ManyToMany: 해당 외래키로 생기는 연관관계 종류를 나타내는 어노테이션


#### @ManyToOne에 사용된 fetch type
- **EAGER** : 즉시 로딩, Order 객체 정보를 가져올 때 연결된 Member 객체의 모든 정보를 함께 한번에 가져온다.

- **LAZY** : 지연 로딩, Order 객체 정보를 가져올 때 연결된 Member 객체의 정보는 필요할 때 가져온다.


### 3. 엔티티 생성자

- 보통 id 필드를 제외하고 생성.
- JPA가 엔티티를 사용하려면 인자 없는 생성자가 필요 → @NoArgsConstructor 어노테이션으로 만들 수 있음.
- access 속성을 통해 접근 제한자를 protected로 설정. → JPA는 사용 가능, 외부 사용 차단
- 추가로 엔티티 객체에 @Getter를 추가 → 모든 필드에 getter를 생성.

