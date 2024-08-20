# <img src="src/main/resources/static/images/logo/logo.png">

## 🚀프로젝트 명 : AniBirth(애니버스)
- 웹 URL : www.anibirth.shop
- DB PORT : 3306
- DB username : root
- 데이터베이스 이름 : anibirth_dev

## 📢 프로젝트 목표
- 유기동물의 삶의 질 개선 및 입양률 상승
- 후원을 통한 유기동물 보호소에 대한 관심도 상승과 지원 강화
- 유기동물 봉사를 통한 유기동물들의 건강과 복지 증진
- 유기동물 문제에 대한 인식 높이기

## ⏱️개발 기간
- 전체 개발 기간 : 2024-07-15 ~ 2024-09-02
- 프로젝트 주제 선정 기간 : 2024-07-15 ~ 2024-07-19
- UI 구현 : 2024-07-20 ~ 2024-09-02
- 기능 구현 : 2024-07-20 ~ 2024-09-02

## ⚙ 개발 환경
- 운영체제 : Windows 11
- 통합개발환경(IDE) : IntelliJ
- JDK 버전 : JDK 17
- 데이터 베이스 : MySQL
- 빌드 툴 : Gradle
- 관리 툴 : GitHub


## 🔌 Dependencies
- Spring Boot DevTools
- Lombok
- Spring Data JPA
- MariaDB Driver
- Spring Security
- Spring Web
- Oauth2-client
- Thymeleaf
- Validation
- Jackson (공공데이터 OpenAPI)
- Toss Payments
- FullCalendar
- Kakao Maps


## 💻 기술 스택
- 백엔드
    - SpringBoot, Spring Security, Spring Data JPA
- 프론트엔드
    - HTML, CSS, Javascript, Bootstrap, Thymeleaf, jQuery, Tailwind
- 데이터베이스
    - MariaDB, MySQL Workbench
    - MySQL, SQLyog, DBeaver

## 🛠 DB 테이블 설계
- Member (회원)
- account (계좌)
- adopt (입양)
- adoptApply (입양신청)
- adoptReview (입양후기)
- animal (유기동물)
- article (공지사항)
- calendar (캘린더)
- cartItem (장바구니)
- category (동물 카테고리)
- donation (후원)
- member_applied_volunteers (봉사 신청 회원)
- orderItem (주문)
- product (상품)
- product_order (결제내역)
- qa (게시판 질문사항)
- qa_admin_comments (게시판 답변)
- qa_comment_authors (게시판 댓글 작성자)
- review (상품 리뷰)
- volunteer (봉사활동)
- volunteer_application (봉사활동 신청 회원)
- volunteer_review (봉사활동 후기)
- volunteer_review_sub_images (봉사활동 후기 첨부 사진)

<br>

| E-R 다이어그램                                                     |
|---------------------------------------------------------------|
| <img src="src/main/resources/static/images/anibirth_erd.png"> |

<br>

## 👨‍👩‍👧‍👦 조원 소개

<div align="center">

|                                                           **박상민 (조장)**                                                            |                                                               **김지영**                                                                |                                                             **김태우**                                                              |                                                                **박현철**                                                                |
|:---------------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------------------:|
| [<img src="https://avatars.githubusercontent.com/u/150203036?v=4" height=150 width=150> <br/> @psm817](https://github.com/psm817) | [<img src="https://avatars.githubusercontent.com/u/132266117?v=4" height=150 width=150> <br/> @jiyoung-0y0](https://github.com/jiyoung-0y0) | [<img src="https://avatars.githubusercontent.com/u/155604554?v=4" height=150 width=150> <br/> @taewoo922](https://github.com/taewoo922) | [<img src="https://avatars.githubusercontent.com/u/161570121?v=4" height=150 width=150> <br/> @2251341](https://github.com/2251341) |

</div>

## 🧑‍🏫 역할 분담

### 🍋‍🟩 박상민 (조장)

- **UI**
  - 페이지 : 메인페이지, 로그인, 회원가입, 마이페이지, 봉사활동, 애니마켓
- **기능**
  1. 서버 배포
     - 네이버 클라우드 플랫폼을 이용하여 서버 생성
     - Docker 이미지 생성을 통해 서버 배포 완료
     - 젠킨스를 통한 CI/CD 배포 자동화 세팅
  2. 회원 (로그인, 회원가입, 마이페이지)
     - 일반 회원 및 카카오, 네이버, 구글을 통한 소셜 로그인 구현
     - 아이디 찾기, 임시 비밀번호 발급 구현 (이메일 발송)
     - 회원가입 구현
     - 마이페이지를 통한 회원 정보 열람, 프로필 수정, 회원 탈퇴 구현
  3. 메인페이지
     - 봉사활동 요약 기능 구현
     - 유기동물 공고 요약 기능 구현
     - 애니마켓 베스트 상품 요약 기능 구현
     - 공지사항, 봉사후기, 입양후기, 바로가기 버튼 구현
  4. 봉사활동
     - 봉사활동 등록, 수정, 삭제 구현
     - 봉사활동 전체 리스트와 상세보기 구현
     - 봉사활동 상세보기 중 봉사 장소에 대한 지도 표시 구현
     - 봉사활동 신청 구현
     - 등록된 각각의 봉사활동을 캘린더에 표시되도록 구현
     - 봉사활동 후기 등록, 수정, 삭제 구현
  5. 애니마켓
     - 마켓 상품 등록, 수정, 삭제 구현
     - 마켓 상품에 대한 리뷰 등록, 수정, 삭제 구현
     - 마켓 상품 전체 리스트와 상세보기 구현
     - 마켓 내 카테고리 구분 (식료품, 액세서리)
     - 신상품순, 높은(낮은)가격순, 별점순, 조회순으로 리스트 구현
<br>

### 🐶 김지영

- **UI**
  - 페이지 : 
- **기능**
  - a

<br>

### 🚌 김태우

- **UI**
  - 페이지 : 
- **기능**
  - a

<br>

### 👑 박현철

- **UI**
    - 페이지 : 후원하기, 공지사항, Q&A(반응형), 애니마켓, 장바구니, 주문 결제, 포인트 충전
- **기능**
    1. 후원
        - 중간관리자 및 최고관리자에게 후원하기 기능
        - 상위 후원자 3순위 리스트 기능 구현
        - 후원하기 기능 사용 시 애니포인트 차감 
    2. 마켓
        - 마켓 상품 수정, 삭제 기능 구현
        - 리뷰(별점) 등록 기능 구현
        - 상품 상세 보기 기능 구현
        - 장바구니 담기 기능 구현
        - 상품 결제 시 상품 등록한 기업 관리자에게 포인트 적립 기능 구현
    3. 장바구니
        - 장바구니 기능 구현(이미지, 상품명, 가격, 수량)
        - 체크박스 기능 구현
        - 수량 변경 기능 구현
        - 수량 변경 or 체크박스 부분 선택 시 총 가격 자동 업데이트
    4. 주문 결제
       - 주문 상품 목록, 배송지 정보, 결제 가격 구현
       - 배송지 정보(받는사람, 주소, 전화번호)
       - 결제 가겨(상품 가격, 배송비, 합계)
       - 결제 시 포인트 차감 기능 구현
    5. 포인트 충전
       - 1,000원/5,000원/10,000원/50,000원/100,000원 단위로 포인트 충전 기능 구현
       - 직접 입력으로 포인트 충전 기능 구현
    6. 결제 기능(토스페이먼트)
        - TossPayments API 결제 연동
        - 포인트 충전 결제
    7. 마이페이지(후원/마켓)
        - 후원 리스트 기능 구현(권한별 리스트 항목 구분)
            - 일반 회원 - 로그인 한 회원의 후원 내역
            - 중간 관리자 - 본기업의 후원 받은 내역
            - 최고 관리자 - 후원내역 전체 리스트
        - 마켓 리스트 기능 구현(권한별 리스트 항목 구분)
          - 일반 회원 - 로그인 한 회원의 구매 내역
          - 중간 관리자 - 본기업의 상품 등록 내역
          - 최고 관리자 - 등록된 상품의 전체 리스트

<br>

## 📝 프로젝트 전체 구조

```
├── README.md
├── build.gradle
├── .gitignore
├── gradlew.bat
├── gradlew
│
└── src.main
     ├── java.com.cod.AniBirth
           ├── AniBirthApplication.java
           ├── AniBirth
                  ├── account
                          ├── entity.Account.java
                          ├── repository.AccountRepository.java
                          ├── service.AccountService.java
                  ├── adopt
                        ├── controller.AdoptController.java
                        ├── entity
                               ├── Adopt.java
                               ├── AdoptApply.java
                               ├── AdoptReview.java
                        ├── form
                              ├── AdoptForm.java
                              ├── AdoptionnoticeForm.java
                              ├── createReviewForm.java
                        ├── repository
                                 ├── AdoptApplyRepository.java
                                 ├── AdoptRepository.java
                                 ├── AdoptReviewRepository.java
                        ├── service
                                ├── AdoptReviewService.java
                                ├── AdoptService.java
                  ├── animal
                        ├── contorller.AnimalController.java
                        ├── entity.Animal.java
                        ├── repository.AnimalRepository.java
                        ├── service.AnimalService.java
                        ├── AnimalSearchDTO.java
                        ├── AnimalSpecification.java
                  ├── article
                        ├── contorller.ArticleController.java
                        ├── contorller.QaController.java
                        ├── entity.Article.java
                        ├── entity.Qa.java
                        ├── repository.ArticleRepository.java
                        ├── repository.QaRepository.java
                        ├── service.ArticleService.java
                        ├── service.QaService.java
                  ├── base
                        ├── entity.BaseEntity.java
                        ├── WebMvcConfig.java
                  ├── calendar
                        ├── contorller.CalendarController.java
                        ├── entity.Calendar.java
                        ├── repository.CalendarRepository.java
                        ├── service.CalendarService.java
                  ├── cart
                        ├── contorller.CartController.java
                        ├── entity.CartItem.java
                        ├── repository.CartRepository.java
                        ├── service.CartService.java
                  ├── category
                        ├── contorller.CategoryController.java
                        ├── entity.Category.java
                        ├── repository.CategoryRepository.java
                        ├── service.CategoryService.java
                        ├── CategoryInitializer.java
                  ├── donation
                        ├── contorller.DonationController.java
                        ├── entity.Donation.java
                        ├── repository.DonationRepository.java
                        ├── service.DonationService.java
                  ├── email
                        ├── service.EmailService.java
                  ├── global
                        ├── initData.Dev.java
                        ├── Message.Message.java
                        ├── security
                                ├── exception.MemberNotFoundException.java
                                ├── exception.OAuthTypeMatchNotFoundException.java
                                ├── CustomAuthenticationFailureHandler.java
                                ├── CustomUserDetailsService.java
                                ├── DataNotFoundException.java
                                ├── MemberContext.java
                                ├── OAuth2UserService.java
                                ├── SecurityConfig.java
                                ├── UserNotActiveException.java
                  ├── home
                        ├── contorller.HomeController.java
                        ├── contorller.IntroduceController.java
                  ├── member
                        ├── contorller.MemberController.java
                        ├── contorller.MyPageMemberController.java
                        ├── contorller.UsernameCheckController.java
                        ├── entity.Member.java
                        ├── form.MemberForm.java
                        ├── repository.MemberRepository.java
                        ├── service.MemberService.java
                  ├── order
                        ├── contorller.OrderController.java
                        ├── entity.Order.java
                        ├── entity.OrderItem.java
                        ├── repository.OrderItemRepository.java
                        ├── repository.OrderRepository.java
                        ├── service.OrderService.java
                  ├── point
                        ├── contorller.PointController.java
                        ├── service.PointService.java
                  ├── product
                        ├── contorller.ProductController.java
                        ├── entity.Product.java
                        ├── repository.ProductRepository.java
                        ├── service.ProductService.java
                  ├── review
                        ├── contorller.ReviewController.java
                        ├── entity.Review.java
                        ├── repository.ReviewRepository.java
                        ├── service.ReviewService.java
                  ├── volunteer
                        ├── contorller.VolunteerController.java
                        ├── entity.Volunteer.java
                        ├── entity.VolunteerApplication.java
                        ├── entity.VolunteerReview.java
                        ├── repository.VolunteerRepository.java
                        ├── repository.VolunteerApplicationRepository.java
                        ├── repository.VolunteerReviewRepository.java
                        ├── service.VolunteerService.java
                        ├── service.VolunteerApplicationService.java
                        ├── service.VolunteerReviewService.java
                  ├── ApiExploer.java
                  ├── ApiResponse.java
                  ├── XmlToJsonConverter.java
           ├── global
                  ├── util
                        ├── HtmlUtils.java
                        ├── Util.java
    ├── resource
            ├── static
                   ├── adopt
                          ├── 입양신청서폼.docx
                          ├── 카테고리.svg
                   ├── images
                          ├── 샘플데이터 사진 및 프로젝트 로고 사진
                   ├── javascript
                          ├── adopt
                                ├── zipcode.js
                          ├── article
                                ├── detail.js
                          ├── commont
                                ├── common.js
                          ├── donation
                                ├── donationscript.js
                          ├── home
                                ├── main.js
                          ├── member
                                ├── myProfile.js
                                ├── agreement.js
                                ├── login.js
                                ├── modify.js
                                ├── signup.js
                                ├── social_modify.js
                          ├── product
                                ├── create.js
                                ├── detail.js
                          ├── qa
                               ├── list.js
                          ├── volunteer
                                  ├── create.js
                                  ├── detail.js
                                  ├── list.js
                                  ├── reviewCreate.js
                   ├── resource
                         ├── adopt
                                ├── apply.css
                                ├── create_review.css
                                ├── detail.css
                                ├── list.css
                                ├── review.css
                                ├── review_detail.css
                         ├── article
                                ├── detail.css
                                ├── form.css
                                ├── list.css
                         ├── common
                                ├── common.css
                         ├── donation
                                ├── donationPage.css
                         ├── home
                               ├── main.css
                         ├── introduce
                                  ├── agreement.css
                         ├── member
                                ├── myPage
                                      ├── adopt.css
                                      ├── donation.css
                                      ├── market.css
                                      ├── myProfile.css
                                      ├── volunteer.css
                                ├── agreement.css
                                ├── login.css
                                ├── modify.css
                                ├── signup.css
                                ├── social_modify.css
                         ├── order
                               ├── checkout.css
                         ├── points
                                ├── recharge.css
                         ├── product
                                ├── accessory.css
                                ├── create.css
                                ├── detail.css
                                ├── food.css
                                ├── list.css
                                ├── main.css
                         ├── qa
                              ├── detail.css
                              ├── form.css
                              ├── list.css
                         ├── volunteer
                                  ├── create.css
                                  ├── detail.css
                                  ├── list.css
                                  ├── modify.css
                                  ├── review.css
                                  ├── reviewCreate.css
                                  ├── reviewDetail.css
                                  ├── reviewModify.css
            ├── templates
                    ├── adopt
                          ├── adoption_noticeForm.html
                          ├── create_review_form.html
                          ├── detail.html
                          ├── form.html
                          ├── list.html
                          ├── review.html
                          ├── review_detail.html
                    ├── article
                            ├── detail.html
                            ├── form.html
                            ├── list.html
                    ├── cart
                           ├── list.html
                    ├── donation
                           ├── donation.html
                    ├── home
                           ├── main.html
                    ├── introduce
                           ├── agreement.html
                           ├── anibirth.html
                    ├── layout
                          ├── layout.html
                    ├── member
                          ├── myPage
                                 ├── adopt.html
                                 ├── donation.html
                                 ├── market.html
                                 ├── myProfile.html
                                 ├── volunteer.html
                          ├── agreement.html
                          ├── login.html
                          ├── modify.html
                          ├── signup.html
                          ├── social_modify.html
                    ├── order
                          ├── checkout.html
                    ├── points
                          ├── recharge.html
                    ├── product
                          ├── accessory
                                  ├── high_hit.html
                                  ├── high_price.html
                                  ├── high_rating.html
                                  ├── low_price.html
                          ├── food
                                ├── high_hit.html
                                ├── high_price.html
                                ├── high_rating.html
                                ├── low_price.html
                          ├── list
                                ├── high_hit.html
                                ├── high_price.html
                                ├── high_rating.html
                                ├── low_price.html
                          ├── accessory.html
                          ├── create.html
                          ├── detail.html
                          ├── food.html
                          ├── list.html
                          ├── main.html
                          ├── modify.html
                    ├── qa
                         ├── detail.html
                         ├── form.html
                         ├── list.html
                    ├── review
                           ├── modify.html
                    ├── volunteer
                            ├── create.html
                            ├── detail.html
                            ├── list.html
                            ├── modify.html
                            ├── review.html
                            ├── reviewCreate.html
                            ├── reviewDetail.html
                            ├── reviewModify.html
                    ├── message.html
            ├── application.yml
            ├── application-dev.yml
            ├── application-prod.yml (배포용)
            ├── application-secret.yml (비공개)
            ├── application-secret.yml.default
            ├── application-test.yml (테스트용)
```

## 🧜‍♀️ 작업 관리 방법

- GitHub Projects와 Issues를 사용하여 진행 상황을 공유했습니다.
- 매일 본인의 작업 양을 소화하고 각자 구현한 기능을 서로 테스트하며 프로그램의 신뢰성을 높였습니다.

## ⭐ 페이지별 기능 소개

### [메인화면]
- 홈페이지 접속 시 초기화면으로 화면의 기본 구조는 상단 메뉴바, 중간 본문, 하단 footer로 구분되어 있습니다.
  - 상단 메뉴바는 애니버스, 봉사활동, 입양정보, 후원하기, 애니마켓, 애니공지로 총 6개의 메뉴로 구성되어 있습니다.
  - 상단 메뉴바의 최상단 부분은 로그인, 회원가입도 함께 포함되어 있습니다.
  - 중간 본문에는 광고 및 홍보 배너와 봉사활동, 입양공고, 마켓 Best 상품을 요약한 리스트가 나열되어 있습니다.
  - 추가로 공지사항, 봉사 및 입양 후기, 각 메뉴의 바로가기 버튼이 중간 본문에 포함되어 있습니다.
  - 각 요약된 정보 옆에는 전체보기를 통해 해당 메뉴 페이지로 이동이 가능합니다.
  - 하단 footer는 이용약관과 개인정보처리방침을 비롯한 애니버스의 기본 정보를 나타냅니다.
- 로그인과 미로그인 시 화면에 나타나는 메뉴가 상이합니다.
    - 로그인이 되어 있지 않은 경우 : 로그인, 회원가입
    - 로그인이 되어 있는 경우 : 마이페이지, 장바구니, 로그아웃

| 메인화면                                                          |
|---------------------------------------------------------------|
| <img src="src/main/resources/static/images/capture/메인화면.png"> |

<br>

### [회원가입]
- 회원가입 버튼과 동시에 이용약관 및 개인정보 수집에 대한 동의서 제출이 요구됩니다.
- 약관 동의 후 일반 회원가입을 진행할 수 있습니다.
- 회원가입의 모든 항목에 대한 유효성 검사를 적용하여 입력하지 않으면 회원가입이 진행되지 않습니다.
- ID는 중복확인을 필수로, 비밀번호는 비밀번호 확인절차를 거칩니다.
- 회원의 권한은 크게 두 가지로, 회원가입 시 보호소/기업 또는 일반회원을 선택할 수 있습니다.
- 보호소/기업을 선택하여 회원가입을 진행할 시, 서비스를 바로 이용할 수 없으며 최고 관리자의 승인이 필요합니다.
- 회원가입이 완료되면 로그인 화면으로 이동과 동시에 회원가입 시 입력한 이메일 주소로 환영 메일이 전송됩니다.
- 최고 관리자의 승인을 받은 보호소/기업 권한의 회원 역시 승인과 동시에 입력한 이메일 주소로 승인완료 메일이 전송됩니다.

| 회원가입                                                               |
|--------------------------------------------------------------------|
| <img src="src/main/resources/static/images/capture/회원가입-약관동의.png"> |
| <img src="src/main/resources/static/images/capture/회원가입-정보입력.png"> |

<br>

### [로그인]
- 회원가입을 통해 생성된 ID와 PW로 로그인을 수행합니다.
- 카카오, 네이버, 구글을 통한 소셜 로그인은 버튼을 눌러 각 플랫폼에 로그인하면 자동으로 계정 생성과 동시에 로그인을 수행합니다.
- 로그인에 성공하면 메인화면으로 이동합니다.

| 로그인                                                          |
|--------------------------------------------------------------|
| <img src="src/main/resources/static/images/capture/로그인.png"> |

<br>

### [아이디 찾기, 임시비밀번호 발급]
- 아이디 찾기 버튼을 통해 회원가입 시 입력한 이메일 주소를 입력하면 해당 이메일 주소로 회원의 아이디가 전송됩니다.
- 임시비밀번호 발급 버튼을 통해 회원가입 시 입력한 아이디와 이메일 주소를 입력하면 해당 이메일 주소로 임시비밀번호가 전송됩니다.
- 임시비밀번호를 발급받은 회원은 기존의 비밀번호가 아닌 발급받은 임시비밀번호를 통해서만 로그인이 가능합니다.

| 차트목록                                                               |
|--------------------------------------------------------------------|
| <img src="src/main/resources/static/images/capture/차트목록-탑100.png"> |
| <img src="src/main/resources/static/images/capture/차트목록-장르별.png">  |

<br>

### [마이페이지]
- 로그인이 되어있는 사용자만 마이페이지에 진입할 수 있습니다.
- 회원의 ID, 닉네임, 이메일, 프로필사진 정보를 제공합니다.
- 회원탈퇴 버튼을 통해 실제 회원 탈퇴가 진행됩니다.
- 프로필 수정 버튼을 통해 ID를 제외한 모든 정보를 변경할 수 있습니다.
- 소셜로그인의 경우 프로필 수정은 닉네임과 프로필 사진만 가능합니다.
- 프로필 사진은 등록되지 않을 경우 기본 이미지가 등록됩니다.

| 마이페이지                                                          |
|----------------------------------------------------------------|
| <img src="src/main/resources/static/images/capture/마이페이지.png"> |

<br>

### [로그아웃]
- 상단 헤더의 로그아웃 버튼을 클릭하면 로그아웃과 동시에 메인페이지로 이동합니다.

| 로그아웃                                                          |
|---------------------------------------------------------------|
| <img src="src/main/resources/static/images/capture/로그아웃.png"> |

<br>

### [애니버스]
- 상단 헤더바를 통해 전체 노래를 검색할 수 있습니다.
- 노래 검색은 곡 제목을 통해서만 가능하며 제목의 일부만 검색해도 해당 문자가 포함된 모든 결과를 화면에 보여줍니다.
- 차트목록과 마찬가지로 음악 상세 정보와 음악 담기가 가능하지만, 로그인이 되어있지 않았다면 모두 실행할 수 없습니다.

| 차트목록                                                              |
|-------------------------------------------------------------------|
| <img src="src/main/resources/static/images/capture/음악 검색.png">    |

<br>

### [봉사활동]
- 차트목록에서 '담기' 버튼을 통해 개인 플레이리스트에 노래를 저장합니다.
- 플레이리스트에 순서는 담기 순서대로 위에서 아래로 나열됩니다.
- 재생 버튼을 통해 음악을 재생할 수 있으며, 이전곡과 다음곡 버튼을 통해 플레이리스트 컨트롤이 가능합니다.
- 반복 재생 버튼 클릭 시 음악 반복 재생이 가능합니다.
- 볼륨 버튼을 통해 볼륨 조절이 가능합니다.
- 실수로 음악을 잘못 담았다면 삭제 버튼을 통해 플레이리스트에서 해당 곡을 제외시킬 수 있습니다.

| 플레이리스트                                                          |
|-----------------------------------------------------------------|
| <img src="src/main/resources/static/images/capture/플레이리스트.png"> |

<br>

### [입양정보]
- 커뮤니티의 분류는 공지, 자유, 친목 게시판으로 구분되어 있습니다.
- 커뮤니티는 로그인 여부와 관계없이 게시물 열람이 가능합니다.
- 최초 커뮤니티 진입 시 전체 게시판이 보여집니다.
- 관리자의 의해 작성된 공지사항이 커뮤니티 상단에 나열됩니다.
- 커뮤니티 좌측에는 로그인되어 있는 사용자의 기본 정보와 +프로필을 통해 커뮤니티 내에서 해당 회원이 활동한 이력을 한 번에 볼 수 있습니다.
- 로그인을 하지 않았을 때는 커뮤니티 좌측에 로그인 페이지와 연결된 버튼이 존재합니다.
- 커뮤니티 우측에는 현재 시간과 오늘 가장 인기있는 게시물 10개를 나열합니다.
- 로그인한 사용자는 본인 게시물의 한해서 작성, 수정, 삭제가 가능합니다.
- 관리자는 모든 회원 게시물에 삭제에 대한 권한을 가지고 있습니다.

| 커뮤니티                                                          |
|---------------------------------------------------------------|
| <img src="src/main/resources/static/images/capture/커뮤니티.png"> |

<br>

### [후원하기]
- 애니버스의 후원은 로그인한 사용자에 한해서만 후원이 가능합니다.
- 후원하기 클릭하면 후원 모달창 나옵니다.
- 후원은 애니버스에 가입한 보호센터 목록에서 지정하여 후원 할 수 있습니다. 
- 금액을 직접 입력하여 후원 할 수 있습니다.
- 후원을 하면 상위 3명의 리스트가 가장 많이 후원한 사람의 등 수로 업데이트 됩니다.


| 후원                                                            |
|---------------------------------------------------------------|
| <img src="src/main/resources/static/images/capture/스튜디오.png"> |

<br>

### [애니마켓]
- 애니마켓는 비로그인 시에도 진입이 가능합니다.
- 장바구니, 리뷰는 오직 로그인 한 사용자에 한해서만 가능합니다. 
- 애니마켓 메인페이지에서는 인기상품을 확인할 수 있고 전체 상품으로 이동할 수 있습니다.
- 상품은 전체/ 식료품/ 악세서리로 구분하여 상품을 확인 할 수 있습니다.
- 신상품순/ 높은 가격순 /낮은 가격순 /별점순 /조회순 별로 상품을 정렬 할 수 있습니다.
- 중간관리자(기업)의 사용자만 상품의 등록, 수정, 삭제를 할 수 있습니다.
- 리스트에는 이미지, 상품명, 가격으로 확인 할 수 있습니다.
- 페이지네이션으로 다음 상품을 볼 수 있습니다.
- 이미지, 상품명, 가격을 클릭하면 상품 상세 페이지로 이동 할 수 있습니다.
- 상세 페이지에서 상품의 상세 정보 및 설명을 확인 할 수 있고 장바구니 담기와 리뷰를 작성할 수 있습니다.

| 애니마켓                                                          |
|---------------------------------------------------------------|
| <img src="src/main/resources/static/images/capture/스튜디오.png"> |

<br>

### [애니공지]
- 애니공지는 공지사항과 Q&A로 구분하고 있습니다.
- 공지사항은 애니버스 최고관리자만 등록, 수정, 삭제를 할 수 있습니다.
- 공지사항의 목록은 번호, 제목, 작성일, 조회수,관리(최고관리자만)로 표시됩니다.
- 공지사항의 상세페이지는 제목과 정보, 내용으로 표시되고 페이네이션으로 이전/다음 공지사항을 확인할 수 있습니다.
- Q&A는 중간관리자(기업, 보호소) 및 최고관리자만 질문에 답변할 수 있습니다.
- Q&A는 로그인한 모든 회원이 질문을 등록할 수 있습니다.
- Q&A는 자주 찾는 질문과 사용자가 등록한 질문의 목록으로 표시됩니다.
- 자주 찾는 질문의 제목을 클릭 시 질문의 답변이 제목의 하단에 표시되어 자주 찾는 질문의 내용을 간편하게 확인 할 수 있습니다.
- Q&A는 중간관리자(기업, 보호소) 및 최고관리자만 질문에 답변할 수 있습니다.
- Q&A은 로그인한 모든 회원이 질문을 등록할 수 있습니다.
- 질문은 번호, 제목, 작성자, 작성일, 조회수 표시 됩니다.
- 제목을 클릭하면 질문의 상세 페이지로 이동합니다.
- 질문의 상세페이지는 제목과 정보, 내용으로 표시되고 페이네이션으로 이전/다음 질문을 확인할 수 있습니다.


| 애니 공지                                                         |
|---------------------------------------------------------------|
| <img src="src/main/resources/static/images/capture/스튜디오.png"> |

<br>

### [장바구니 및 결제]
- 장바구니에 담긴 상품 목록을 상품 이미지, 이름, 가격, 수량 등의 세부정보와 함께 표시합니다.
- 사용자는 품목을 선택/선택 취소하고, 수량을 직접 업데이트하고, 장바구니에서 품목을 삭제할 수 있습니다.
- 사용자가 장바구니 기능 수행 시(항목 선택/선택 취소, 수량 변경) 실시간으로 선택한 항목의 총 가격을 계산합니다.
- 결제하기 버튼을 이용하면 선택한 상품을 결제를 진행 할 수 있습니다.
- 결제하기는 결제(checkout)페이지로 이동합니다.
- 결제는 상품정보(상품명, 수량, 가격)으로 표시됩니다.
- 배송지정보(받는사람, 주소, 전화번호)로 로그인한 회원의 기본 정보로 표시 됩니다. 
- 결제가격(상품가격, 배송비, 합계)로 상품가격과 배송비가 합한 합계로 표시 됩니다.
- 포인트로 결제하기 버튼을 누르면 포인트가 차감됩니다.
- 결제는 포인트 충전 후 이용할 수 있습니다.

| 장바구니 및 결제                                                |
|----------------------------------------------------------|
| <img src="src/main/resources/static/images/capture/스튜디오.png"> |

<br>

## 💥 트러블 슈팅
<details>
<summary> 박상민 </summary>
    </br>

#### <1> <b>이미지 업로드</b>

```문제``` 이미지 업로드와 동시에 변환을 시키는데 경로 설정, 파일 변경, 전송 실패 등등.. 여러 문제가 발생하였다. 이미지 업로드 트러블을 해결하기 위해서 디버깅 및, 검색을 이용해 문제를 해결하였다.
</br>
```해결``` Ex )enctype="multipart/form-data" 문제나 public void addResourceHandlers(ResourceHandlerRegistry registry) 파일에서의 경로 설정 혹은, file handler에서의 파일 저장 방법 등.. 을 이용하여 여러 문제를 해결하였다.
</br>
</details>

<details>
<summary> 김지영 </summary>
    </br>

#### <1> <b>이미지 업로드</b>

```문제``` 이미지 업로드와 동시에 변환을 시키는데 경로 설정, 파일 변경, 전송 실패 등등.. 여러 문제가 발생하였다. 이미지 업로드 트러블을 해결하기 위해서 디버깅 및, 검색을 이용해 문제를 해결하였다.
</br>
```해결``` Ex )enctype="multipart/form-data" 문제나 public void addResourceHandlers(ResourceHandlerRegistry registry) 파일에서의 경로 설정 혹은, file handler에서의 파일 저장 방법 등.. 을 이용하여 여러 문제를 해결하였다.
</br>
</details>

<details>
<summary> 김태우 </summary>
    </br>

#### <1> <b>이미지 업로드</b>

```문제``` 이미지 업로드와 동시에 변환을 시키는데 경로 설정, 파일 변경, 전송 실패 등등.. 여러 문제가 발생하였다. 이미지 업로드 트러블을 해결하기 위해서 디버깅 및, 검색을 이용해 문제를 해결하였다.
</br>
```해결``` Ex )enctype="multipart/form-data" 문제나 public void addResourceHandlers(ResourceHandlerRegistry registry) 파일에서의 경로 설정 혹은, file handler에서의 파일 저장 방법 등.. 을 이용하여 여러 문제를 해결하였다.
</br>
</details>

<details>
<summary> 박현철 </summary>
    </br>

#### <1> <b>CSRF 토큰 오류</b>

```문제``` csrf token forbidden(403)오류로 인해 후원 기능 사용 시에 일시적인 오류가 나고 새로고침 하면 정상작동하는 문제가 발생하였다. 코드에서 문제점을 찾을 수 없었기에 간단한 방법으로 해결하였다.
</br>
```해결``` 후원 기능에 csrf.token 기능을 빼고 SecurityConfig 에서도 csrf를 사용하징 않고 로그인한 사용자에게만 후원하기 버튼이 보일 수 있도록 수정하여 해결하였다.
</br>
</details>

## 🫸 개선해야 할 점

- 중복되는 코드 제거
    - layout.html 이라는 공통된 템플릿을 두고 사용하다보니 각 html에 대한 css을 작성할 때 혹시라도 겹치는 클래스명이나 태그가 있으면 서로 css 적용이 안되는 경우가 있었습니다.
    - 하나의 기능을 위해서 각 컨트롤러에서 중복된 코드를 사용한 경우가 있는데 프로그램의 속도 향상을 위해 코드를 간소화할 방법을 찾아봐야 할 것 같습니다.

- 차트 목록에서 음악 재생
    - 로그인 여부와 관계없이 차트목록에서 듣기 버튼을 누르면 해당 음악의 1분 미리듣기 서비스를 제공하지만 로그인이 되었을 때도 1분 미리듣기가 재생되고 있습니다.
    - 듣기 버튼을 누름과 동시에 개인 플레이리스트에 추가가 되고 전 곡 재생이 되도록 구현을 했어야하는 데 기간 내에 수행하지는 못했습니다. 해당 부분은 추후에 수정할 예정입니다.

<br>

## 🧑‍🎓 프로젝트를 마치며..

### 🍋‍🟩 박상민
내용작

<br>

### 🐶 김지영
내용작성

<br>

### 🚌 김태우
내용작성

<br>

### 👑 박현철
내용작성



## 🔗Link

[프로젝트 완성 및 시연 영상](주소작성)