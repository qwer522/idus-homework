# 백패커/아이디어스 개발과제

### Introduction

    클라이언트(ios, android, web app)에서 사용할 API를 개발하세요.
    
#### Develop spec
    
    java 11
    Spring 5.3
    Spring boot 2.5

#### Code convention
* [구글스타일](https://github.com/google/styleguide) (intellij-java-google-style.xml)

#### Api Documentation
* [Swagger](http://localhost:8080/swagger-ui.html) (서버를 시작하고 링크를 눌러주세요.)

# 서비스 분석 및 해결방법

[기능요구사항]

    1. 회원 가입
    2. 회원 로그인(인증)
    3. 회원 로그아웃
    4. 단일 회원 상세 정보 조회
    5. 단일 회원의 주문 목록 조회
    6. 여러 회원 목록 조회
        페이지네이션으로 일정 단위로 조회합니다.
        이름, 이메일을 이용하여 검색 기능이 필요합니다.
        각 회원의 마지막 주문 정보

[api 사용 방법]

    1. 회원 가입 (swagger에 작성)
    2. 회원 로그인
        (
            type : POST
            , path: /login
            , data-type: x-www-form-urlencoded
            , data {
                        username : 'email@domain'
                        , password : 'password'
                    }
        )
    3. 회원 로그아웃 (type : POST, path: /logout)
    4. 단일 회원 상세 정보 조회 (swagger에 작성)
    5. 단일 회원의 주문 목록 조회 (swagger에 작성)
    6. 여러 회원 목록 조회 (swagger에 작성)

[Service architecture]

    * api 부분과 service domain을 module화를해서 분리함
    * CQRS 패턴을 사용
        (쿼리와 커맨드를 분리해도 같은 DB 테이블을 써서 해당 패턴이 불필요할수 도 있지만 로직을 분리함으로 가독성과 의도가 명확해짐)

[DB 설계]

    * h2 db 사용
    * query는 db -> migration에 위치해 있습니다.
    * Naming Rule: idus_{table_name}
    * 더욱 상세하게 분리할 수 있지만 요구사항에 중점을 두고 설계를 진행.
    * TODO: 상품 테이블을 따로 만들어야함.

[Exception 정리]
 
    1. Exception은 커스텀하게 만들어서 처리
    2. error핸들러를 만들어서 전송데이터 양식을 정상적인 데이터 양식과 공통화

# Start
    
    1. cd /homework
    (메인 프로젝트 디렉토리로 이동)
    
    2. ./gradlew clean compileQueryDsl
    (컴파일)
    
    3. cd ./idus-api
    (실행할 디렉토리로 이동)
    
    4. ./gradlew bootRun
    (실행)

# 후기

    처음 과제가 주어지고 떨리는 마음으로 과제를 확인하는데 생각보다 쉬운거 같아서 처음에는 내심 좋았지만
    진행을 할수록 정말 많은 "생각", "규칙", "과제에서 무엇을 평가를 진행하실지"를 생각하며 많이 고민하게 되었습니다.
    설계단계에서는 "많은걸 생각하고 확장설계를 할까?", "있는 요구사항만 충족시키게 할까?"라는 많은 생각을 하며
    저 또한 과제를 하는 도중에도 성장을 했던것 같습니다. 
    
    마지막으로 짧은 시간에 모든걸 보여드리고 싶었지만 업무와 시간이 부족해서 너무 아쉬운 과제지만 
    과제를 하면서 배움의 성장이 있어 좋은 경험이였던거 같습니다.

# idus-homework
