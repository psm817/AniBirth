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
  - 

<br>

### 🚌 김태우

- **UI**
  - 페이지 : 
- **기능**
  - 

<br>

### 👑 박현철

- **UI**
  - 페이지 : 
- **기능**
  - 

<br>

## 프로젝트 전체 구조

```
├── README.md
├── build.gradle
├── .gitignore
├── gradlew.bat
├── gradlew
│
└── src.main
     ├── java.com.example.Apollon
           ├── ApollonApplication.java
           ├── domain
                  ├── comment
                          ├── contorller.CommentController.java
                          ├── entity.Commnet.java
                          ├── form.CommentForm.java
                          ├── repository.CommentRepository.java
                          ├── service.CommentService.java
                  ├── email
                        ├── EmailController.java
                        ├── EmailMessage.java
                        ├── EmailResponseDto.java
                        ├── EmailService.java
                  ├── home
                        ├── contorller.HomeController.java
                  ├── member
                        ├── contorller.MemberController.java
                        ├── contorller.UsernameCheckController.java
                        ├── entity.Member.java
                        ├── repository.MemberRepository.java
                        ├── service.MemberService.java
                  ├── music
                        ├── contorller.MusicController.java
                        ├── entity.Music.java
                        ├── repository.MusicRepository.java
                        ├── service.MusicService.java
                  ├── playlist
                        ├── contorller.PlaylistController.java
                        ├── entity.Playlist.java
                        ├── repository.PlaylistRepository.java
                        ├── service.PlaylistService.java
                  ├── post
                        ├── contorller.PostController.java
                        ├── entity.BoardType.java
                        ├── entity.Post.java
                        ├── entity.PostComment.java
                        ├── entity.PostForm.java
                        ├── repository.PostCommnetRepository.java
                        ├── repository.PostRepository.java
                        ├── service.PostCommentService.java
                        ├── service.PostService.java
                  ├── studio
                        ├── contorller.StudioController.java
                        ├── entity.Studio.java
                        ├── repository.StudioRepository.java
                        ├── service.StudioService.java
           ├── global
                  ├── config
                          ├── WebMvcConfig.java
                  ├── initData
                          ├── DataFileUtils.java
                          ├── Dev.java
                          ├── TestFileUtils.java
                          ├── TestFileUtilsConfig.java
                  ├── jpa
                       ├── BaseEntity.java
                  ├── security
                          ├── CustomOAuth2UserService.java
                          ├── SecurityConfig.java
                          ├── UserSecurityService.java
                  ├── DataNotFoundException.java
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