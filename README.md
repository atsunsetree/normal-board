<div align=center>
  <img src="https://capsule-render.vercel.app/api?type=waving&color=E3CEF6&height=250&section=header&text=📒게시판%20제작%20프로젝트&fontSize=50" />
</div>
<div align=center><h1>📚 STACKS</h1></div>

<div align=center> 
  <img src="https://img.shields.io/badge/java 11-007396?style=for-the-badge&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/spring Security-6DB33F?style=for-the-badge&logo=spring Security&logoColor=white">
  
  <br>
  <img src="https://img.shields.io/badge/myBatis-190718?style=for-the-badge&logo=myBatis&logoColor=white">
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/JPA-58FAD0?style=for-the-badge&logo=JPA&logoColor=white">
  <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
  <br>
  <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=Thymeleaf&logoColor=white"> 
  <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">
  <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> 
  <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> 
  <br>
  
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
  <img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=for-the-badge&logo=IntelliJ IDEA&logoColor=white">
  <br>
  
  <div align=center><h2>💬 Communication</h2></div>
  <img src="https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=Slack&logoColor=white">
  <img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white">
  <img src="https://img.shields.io/badge/Zoom-2D8CFF?style=for-the-badge&logo=Zoom&logoColor=white">
  <br>

  <div align=center><h1>👨‍👩‍👧‍👦 팀원 역할</h1></div>
  <table>
    <tbody>
      <tr>
        <td align="center"><a href="https://github.com/a07224">
          <img src="https://avatars.githubusercontent.com/u/69192549?v=4" width="100px;" alt=""/><br /><sub><b>강주희 : 게시판</b></sub></a><br />
        </td>
        <td align="center"><a href="https://github.com/k1m2njun">
          <img src="https://avatars.githubusercontent.com/u/68175311?v=4" width="100px;" alt=""/><br /><sub><b>길민준 : 관리자</b></sub></a><br />
        </td>
        <td align="center"><a href="https://github.com/cxxxtxxyxx">
          <img src="https://avatars.githubusercontent.com/u/109710879?v=4" width="100px;" alt=""/><br /><sub><b>최태윤 : 게시판</b></sub></a><br />
        </td>
        <td align="center"><a href="https://github.com/atsunsetree">
          <img src="https://avatars.githubusercontent.com/u/128345842?v=4" width="100px;" alt=""/><br /><sub><b>최해솔 : 회원가입</b></sub></a><br />
        </td>
      </tr>
      <tr>
        <td>
          게시판 조회,<br />스케쥴러 리팩토링,<br />사용자 예외 페이지,<br />화면 구현<br />페이징, 검색 기능
        </td>
        <td>
          gradle multi module 적용,<br />관리자 api,<br />세션 기반 로그인,<br />예외처리 등<br />관리자<br />api 및 화면 일체
        </td>
        <td>
          댓글 기능 구현,<br />게시판 글쓰기 구현,<br />깃허브 관리
        </td>
        <td>
          security 적용<br />사용자 회원 정보<br />관련 일체,<br />화면 구현
        </td>
      </tr>
    </tbody>
  </table>
  <div align=center><h1>🐣 프로젝트 시작하는 법</h1></div>
  <div><h4>★본 프로젝트는 관리자 서버와 게시판 서버가 분리되어 있어 프로그램을 실행시키기 위해 설정이 필요합니다★</h4></div>
  </div>
  <div align=center><h3>1. yml파일 설정하기</h3></div>
  <div align=center><h4>1-1. module-board의 yml파일 설정하기 (게시판 실행시)</h4></div>

  ```yaml
server:
  port: 8080
  servlet:
    encoding:
      charset: utf-8
      force: true

spring:
  autoconfigure:
    exclude: org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
  datasource:
    username: # 본인의mysql 데이터 name
    password: # 본인의mysql 데이터 password
    url: # 본인의 mysql 데이터 url
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        ddl-auto: update
        format_sql: true
file:
  dir: C://Users//User//IdeaProjects//normal-board//module-board//src//main//resources//static//asset//upload//
# 파일 경로는  //normal-board 전까지 본인의 프로젝트 경로로 맞게 설정하세요
```
<div align=center><h4>1-2. module-admin의 yml파일 설정하기 (관리자 실행시)</h4></div>

```yaml
server:
  port: 8081
  servlet:
    encoding:
      charset: utf-8
      force: true
spring:
  datasource:
    username: # 본인의mysql 데이터 name
    password: # 본인의mysql 데이터 password
    url: # 본인의 mysql 데이터 url
    driver-class-name: com.mysql.cj.jdbc.Driver
  thymeleaf:
    prefix: classpath:templates/thymeleaf
    suffix: .html
    mode: HTML
    check-template-location: true
    cache: false
  mail:
    host: smtp.gmail.com
    port: 587
    username: # 보내는이 이름
    password: # 본인 설정 gmail 인증 비밀번호
    properties:
      mail:
        smtp:
          auth: true
          timeout: 5000
          starttls:
            enable: true
mybatis:
  mapper-locations:
    - classpath:mapper/**.xml # classpath -> resource 폴더를 찾음.
  configuration:
    map-underscore-to-camel-case: true # under_score 형식을 카멜표기법으로 변환
file:
  dir: C://Users//User//IdeaProjects//normal-board//module-board//src//main//resources//static//asset//upload//report
# 파일 경로는  //normal-board 전까지 본인의 프로젝트 경로로 맞게 설정하세요
```
<div align=center><h3>2. 포트 다르게 잡기 <br> edit configurations 화면에서 Modify options클릭 -> add VM Options추가<br>-Dserver.port=8080 (board 앱 쪽)<br>-Dserver.port=8081 (admin 앱 쪽)</h3></div>

![image](https://github.com/Spring-Board-Toy3/normal-board/assets/69192549/84d5cefe-112f-4caa-98bb-886e83b1324b)

<div align=center>
  <h3>3. 신고하기 img경로 폴더 생성 : module-board의 다음 경로에 report폴더를 생성해주세요</h3>

  ![image](https://github.com/Spring-Board-Toy3/normal-board/assets/69192549/2083f0a0-1fa5-4562-ad6c-c18748f645c2)
</div>
  <div align=center><h1>🖥 기능 소개</h1></div>
  <div align=center><h3>1. 회원가입 & 로그인<br>- 회원가입시 아이디 중복체크</h3></div>

  ![image](https://github.com/Spring-Board-Toy3/normal-board/assets/69192549/765404a2-5c3a-4478-8bd5-6d1c8d79db20)
  ![image](https://github.com/Spring-Board-Toy3/normal-board/assets/69192549/b9846e60-db50-4281-9d0d-f8c1c3b638e0)


  <div align=center><h3>2. 회원정보보기 & 수정하기</h3></div>
  
  <div align=center><h3>3. 게시글 목록보기 & 검색 <br> - 게시글은 한줄에 3개 한 페이지당 6개씩 카드형식으로 출력 <br> - 제목, 게시글, 작성자로 검색 가능 (검색어가 게시글에 포함될시 검색됨) </h3></div>

  ![image](https://github.com/Spring-Board-Toy3/normal-board/assets/69192549/49da422a-aeb0-4c14-997c-c454720753f2)

  <div align=center><h3>4. 게시글 상세보기 & 삭제, 수정하기<br> - 본인이 작성한 게시글일시 삭제, 수정하기 버튼이 뜸</h3></div>

  ![image](https://github.com/Spring-Board-Toy3/normal-board/assets/69192549/3a0eacf6-bcc2-404d-8db2-6478a1e1129e)

  <div align=center><h3>5. 게시글 댓글쓰기 & 삭제하기</h3></div>

  ![image](https://github.com/Spring-Board-Toy3/normal-board/assets/69192549/c9322784-755a-4884-b046-d9787691142a)

  <div align=center><h3>6. 관리자 회원 권한 관리 & email 전송</h3></div>
  
  ![스크린샷 2023-07-21 오후 9 20 08](https://github.com/Spring-Board-Toy3/normal-board/assets/68175311/dfdb3294-a3c0-426f-a015-e6ddb27a6e09)
  
  <div align=center><h3>7. 관리자 블랙리스트 회원 등록 & 해제</h3></div>
  
  ![스크린샷 2023-07-21 오후 9 20 38](https://github.com/Spring-Board-Toy3/normal-board/assets/68175311/1344fd45-b888-42e7-9b5f-f867bd3d15aa)
  
  <div align=center><h3>8. 관리자 게시글 통계 관리 & 게시글 삭제, 숨김, 보이기</h3></div>

  ![스크린샷 2023-07-21 오후 9 20 20](https://github.com/Spring-Board-Toy3/normal-board/assets/68175311/7f10682c-10b7-4472-8741-ee500e72f32c)

  <div align=center><h3>9. 관리자 신고 목록 페이지</h3></div>
  
  ![스크린샷 2023-07-21 오후 9 20 23](https://github.com/Spring-Board-Toy3/normal-board/assets/68175311/ccfdc705-b7e7-4fb8-b7ac-10b390592e6f)

  <div align=center><h3>10. 관리자 사용자 예외 페이지</h3></div>
  
  ![스크린샷 2023-07-21 오후 9 21 57](https://github.com/Spring-Board-Toy3/normal-board/assets/68175311/d71d5f60-c9e5-4046-b10c-727779149b48)

  

