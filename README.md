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
  - 페이지 : 
- **기능**
  - a

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
- 마이페이지 내 메뉴는 마이프로필(회원목록), 봉사내역, 입양내역, 후원 및 마켓내역으로 총 4가지로 분류되어 있습니다.
- 각 회원의 권한마다 메뉴별로 보여지는 화면이 상이합니다.
  - 최고 관리자 (admin)
    - 회원목록 : 승인 여부와 관계없이 애니버스 서비스에 회원가입된 모든 회원의 리스트를 보여줍니다. 최고 관리자는 회원목록 메뉴를 통해 보호소/기업 권한을 가진 회원의 승인을 허락할 수 있습니다.
    - 봉사내역 : 봉사활동에 등록된 모든 봉사활동의 내역을 보여줍니다.
    - 입양내역 : 입양하기에 등록된 모든 유기동물의 내역을 보여줍니다.
    - 후원 및 마켓내역 : 모든 후원자의 후원 내역과 애니마켓에 등록된 모든 상품의 리스트를 보여줍니다.
  - 보호소/기업 회원 (company)
      - 마이프로필 : 회원의 가입 정보와 마켓에서 결제할 수 있는 애니포인트 충전이 가능하며, 프로필 수정과 탈퇴가 가능합니다. 보호소/기업 회원의 경우 일반회원이 해당 기업의 상품을 결제했다면, 결제금액이 자동으로 기업 애니포인트에 충전됩니다.
      - 봉사내역 : 보호소/기업 회원이 봉사활동에 등록한 봉사활동의 내역을 보여줍니다.
      - 입양내역 : 보호소/기업 회원이 입양하기에 등록한 유기동물의 내역을 보여줍니다.
      - 후원 및 마켓내역 : 보호소/기업 회원이 후원한 후원 내역과 애니마켓에 등록한 상품의 리스트를 보여줍니다.
  - 일반회원 및 소셜로그인 회원 (user)
      - 마이프로필 : 회원의 가입 정보와 마켓에서 결제할 수 있는 애니포인트 충전이 가능하며, 프로필 수정과 탈퇴가 가능합니다. 소셜로그인을 통한 회원의 경우, 프로필 수정 시 비밀번호는 변경이 불가합니다.
      - 봉사내역 : 일반회원이 봉사활동에 등록한 봉사활동의 내역을 보여줍니다.
      - 입양내역 : 일반회원이 입양하기에 등록한 유기동물의 내역을 보여줍니다.
      - 후원 및 마켓내역 : 회원이 후원한 후원 내역과 애니마켓에서 구매한 구매내역을 보여줍니다.

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
- 애니버스는 기업 소개 페이지로 애니버스의 어원과 개발자 소개 및 위 사이트의 목표와 개요를 설명합니다.

| 차트목록                                                              |
|-------------------------------------------------------------------|
| <img src="src/main/resources/static/images/capture/음악 검색.png">    |

<br>

### [봉사활동]
- 봉사활동은 봉사활동과 봉사후기 두 가지의 소메뉴로 구분됩니다.
  - 봉사활동
    - 봉사활동은 사이트에 등록된 모든 봉사활동의 리스트를 보여주고, 캘린더를 통해 월간, 일간의 봉사활동을 한 눈에 확인할 수 있습니다.
    - 각 봉사활동의 사진이나 캘린더에서 일정을 클릭하면 해당 봉사활동의 상세보기 화면으로 이동이 가능합니다.
    - 상세보기 화면을 통해 일반 회원은 봉사활동 신청이 가능합니다.
    - 봉사활동은 로그인이 되어있을 때만 신청이 가능하며, 신청 시 신청 마감날짜가 지났거나 모집인원이 모두 채워졌을 경우 신청이 불가능합니다.
    - 봉사활동을 등록한 보호소/기업 회원 또는 최고 관리자는 신청인원보기를 통해 신청한 인원의 간단한 개인정보를 열람할 수 있습니다.
    - 봉사활동 등록과 수정, 삭제는 활동 글을 게시한 보호소/기업 회원 또는 최고 관리자에게만 권한이 있습니다.
  - 봉사후기
    - 봉사후기는 사이트에 등록된 모든 봉사후기의 리스트를 보여줍니다.
    - 봉사후기의 리스트를 클릭하면 해당 봉사후기의 상세보기 화면으로 이동이 가능합니다.
    - 상세보기 화면 내에서 버튼을 통해 이전, 다음글로 이동이 가능합니다.
    - 봉사후기 등록과 수정, 삭제는 후기 글을 게시한 회원에게만 권한이 있습니다.
    - 봉사후기 등록 시 대표 이미지를 제외하고 내용에 들어갈 첨부 이미지를 최대 3장까지 선택할 수 있습니다.

| 플레이리스트                                                          |
|-----------------------------------------------------------------|
| <img src="src/main/resources/static/images/capture/플레이리스트.png"> |

<br>

### [입양정보]
- 내용작성

| 커뮤니티                                                          |
|---------------------------------------------------------------|
| <img src="src/main/resources/static/images/capture/커뮤니티.png"> |

<br>

### [후원하기]
- 내용작성

| 스튜디오                                                          |
|---------------------------------------------------------------|
| <img src="src/main/resources/static/images/capture/스튜디오.png"> |

<br>

### [애니마켓]
- 애니마켓은 오픈형 마켓으로 보호소/기업 회원만이 자신들의 상품에 대해서 등록, 수정, 삭제 권한을 가지고있다.
- 최초 애니마켓 메뉴에 진입하게 되면 평점이 가장 높은 순서로 BEST 8개의 상품을 리스트하여 나열합니다.
- 전체상품보기 버튼을 통해 마켓에 등록된 모든 상품의 리스트를 볼 수 있습니다.
- 상품 리스트는 전체, 식료품, 액세서리로 카테고리가 구분되어 있습니다.
- 신상품순, 높은/낮은 가격순, 별점순, 조회순을 통해 상품의 리스팅 필터도 가능합니다.
- 각 상품 리스트의 사진이나 상품명, 가격을 클릭하면 해당 상품의 상세보기 화면으로 이동이 가능합니다.

| 스튜디오                                                          |
|---------------------------------------------------------------|
| <img src="src/main/resources/static/images/capture/스튜디오.png"> |

<br>

### [애니공지]
- 내용작성

| 스튜디오                                                          |
|---------------------------------------------------------------|
| <img src="src/main/resources/static/images/capture/스튜디오.png"> |

<br>

### [장바구니 및 결제]
- 내용작성

| 스튜디오                                                          |
|---------------------------------------------------------------|
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

#### <1> <b>이미지 업로드</b>

```문제``` 이미지 업로드와 동시에 변환을 시키는데 경로 설정, 파일 변경, 전송 실패 등등.. 여러 문제가 발생하였다. 이미지 업로드 트러블을 해결하기 위해서 디버깅 및, 검색을 이용해 문제를 해결하였다.
</br>
```해결``` Ex )enctype="multipart/form-data" 문제나 public void addResourceHandlers(ResourceHandlerRegistry registry) 파일에서의 경로 설정 혹은, file handler에서의 파일 저장 방법 등.. 을 이용하여 여러 문제를 해결하였다.
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
내용작성

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