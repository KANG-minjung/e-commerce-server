# e-Commerce Service

## 목차
1. [개요](###개요)
2. [요구사항 분석 문서](###요구사항-분석-문서)
3. [ERD](###ERD)
4. [시퀀스 다이어그램](###시퀀스-다이어그램)
5. [STEP6 아키텍처 개발](###아키텍처)
6. [STEP7 조회성능 최적화 보고서](###조회성능최적화)
7. [STEP8 동시성 이슈 해결 보고서](###동시성이슈)
---
### 개요
- 이커머스 서비스를 개발하며 요구사항 분석, 시퀀스 다이어그램, ERD를 작성함.
- 항해 9기 2팀 강민정 / 2025.07.18
---
### 요구사항 분석 문서
#### 기능
##### 1. 잔액관리
1) 잔액 조회 ( GET /balance/{userId} )
   -Return 잔액
2) 잔액 충전 ( POST /balance/charge )
   - Return SUCCESS / FAIL

##### 2. 상품관리
1) 상품 목록 조회 ( GET /item/itemList )
   - Return 상품정보 List (상품ID, 상품명, 가격, 잔고)
2) 상품 단일 조회 ( GET /item/{itemId} )
   - Return 상품 정보(상품ID, 상품명, 가격, 잔고)
3) 인기 상품 조회 ( GET /item/popularList )
   - Return 상품정보 List (상품ID, 상품명, 판매 수량, 잔고)

##### 3. 쿠폰관리
1) 보유 쿠폰 조회 ( GET /coupon/{userId} )
   - Return 쿠폰정보 List (쿠폰ID, 쿠폰명, 할인율, 상태)
2) 미보유 쿠폰 조회 ( GET /coupon/couponList )
   - Return 쿠폰정보 List (쿠폰ID, 쿠폰명, 할인율, 상태)
3) 쿠폰 발급 ( POST /coupon/issuance )
   - Return SUCCESS / FAIL

##### 4. 주문 / 결제 관리
1) 주문 요청 ( POST /order/orderRequest )
   - Return SUCCESS / FAIL
2) 주문 조회 ( GET /order/{orderId} )
   - Return 주문정보 (주문 ID, ITEM 목록, 총 가격, 할인금액)
3) 주문 취소 ( POST /order/{orderId}/cancel )
   - Return SUCCESS / FAIL
4) 결제 요청 ( POST /order/paymentRequest )
   - Return SUCCESS / FAIL

#### 제약조건
1. 충전은 5,000원 단위로 가능.
2. 마이너스 충전은 불가능.
3. 충전금액의 최대금액은 200,000원
4. 충전금액 < 상품금액 일 경우 상품 구매 못함.
5. 재고 이상 주문 할 경우 실패
6. 쿠폰은 5%, 7%, 10% 쿠폰이 존재함.
   - 3만원 이상 상품을 구매할 시 5% 할인
   - 5만원 이상 상품을 구매할 시 7% 할인
   - 10만원 이상 상품을 구매할 시 10% 할인
---
### ERD
[ERD](/docs/ERD_20250807.pdf) < 클릭하여 ERD 문서 확인
> 2025.07.31 변경 완료

> 2025.08.07 변경 완료

---
### 시퀀스 다이어그램
#### 잔액
![잔액조회 충전](/docs/잔액조회_충전.png)
#### 상품
![상품조회 / 인기상품조회](/docs/상품조회_인기상품.png)
#### 쿠폰
![쿠폰조회](/docs/쿠폰조회.png)
![쿠폰발급](/docs/쿠폰발급.png)
#### 주문/결제
![주문요청](/docs/주문요청.png)
![주문조회](/docs/주문조회.png)
![주문취소](/docs/주문취소.png)
![결제요청](/docs/결제요청.png)

### 아키텍처
#### 선택한 아키텍처 패턴 : 클린 아키텍처

#### 아키텍쳐 구조
adapter - 구현체 / API 기능 / DTO 변환, 요청 수신/응답 전송
- controller - API(클라이언트의 요청) / 단순 요청
- repository - 외부 DB와 연결된 Repository를 등록
  domain
- model
    - Entity 생성/변경 시 값 검증
    - 모든 비즈니스 규칙 포함, ErrorCode + BusinessException 사용
    - JPA 기반 구현
- repository - Data 인터페이스 (Port)
  facade
    - 사용자 기준 흐름 작성
    - 여러 UseCase 조합 및 검증 중심
      useCase
    - 단일 책임 원칙에 따라 하나의 UseCase는 하나의 기능만 담당.
    - 외부 호출 없음
    - 실제
- dto - Request / Response

### 조회성능최적화
[조회성능 최적화 보고서.pdf](docs/조회성능_최적화_보고서.pdf)

### 동시성이슈
[Step9_동시성_보고서.pdf](docs/Step9_동시성_보고서.pdf)