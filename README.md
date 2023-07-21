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
        <td align="center"><a href="https://github.com/a07224"><img src="https://avatars.githubusercontent.com/u/69192549?v=4" width="100px;" alt=""/><br /><sub><b>강주희 : 게시판</b></sub></a><br /></td>
        <td align="center"><a href="https://github.com/k1m2njun"><img src="https://avatars.githubusercontent.com/u/68175311?v=4" width="100px;" alt=""/><br /><sub><b>길민준 : 관리자</b></sub></a><br /></td>
        <td align="center"><a href="https://github.com/cxxxtxxyxx"><img src="https://avatars.githubusercontent.com/u/109710879?v=4" width="100px;" alt=""/><br /><sub><b>최태윤 : 게시판</b></sub></a><br /></td>
        <td align="center"><a href="https://github.com/atsunsetree"><img src="https://avatars.githubusercontent.com/u/128345842?v=4" width="100px;" alt=""/><br /><sub><b>최해솔 : 회원가입</b></sub></a><br /></td>
      </tr>
    </tbody>
  </table>
  <div align=center><h1>🐣 프로젝트 시작하는 법</h1></div>
  </div>
  <div align=center><h3>1. yml파일 설정하기 : module-board의 yml파일을 설정하세요</h3></div>

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
  <div align=center><h1>🖥 기능 소개</h1></div>
  <div align=center><h3>1. 회원가입 & 로그인</h3></div>
  <div align=center><h3>2. 회원정보보기 & 수정하기</h3></div>
  <div align=center><h3>3. 게시글 목록보기 & 검색</h3></div>
  <div align=center><h3>4. 게시글 상세보기 & 삭제, 수정하기</h3></div>
  <div align=center><h3>5. 게시글 댓글쓰기 & 삭제하기</h3></div>
  <div align=center><h3>6. 관리자 회원 권한 관리 & email 전송</h3></div>
  <div align=center><h3>7. 관리자 블랙리스트 회원 등록 & 해제</h3></div>
  <div align=center><h3>8. 관리자 게시글 통계 관리 & 게시글 삭제, 숨김, 보이기</h3></div>

