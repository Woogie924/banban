# 🐥 SSAFY 공통 프로젝트
<img src="https://img.shields.io/badge/results-Responsive WebApp-blue">
<img src="https://img.shields.io/badge/framework-Vue.js 2.0-green">
<img src="https://img.shields.io/badge/framework-Spring Boot-green">
<img src="https://img.shields.io/badge/DataBase-MySQL-green">
<img src="https://img.shields.io/badge/version-v1.0.0-red">
<br>

## Core Team  😜

<table>
   <tr>
      <td>
        <img width="160px" src="https://lab.ssafy.com/webmobile1-sub1/s02p11d138/raw/develop/KakaoTalk_20200109_090135135.jpg"><br>
         [홍성욱](https://github.com/Woogie924)<br>
         <i>Project lead & Developer</i><br>
         <i>SSAFY 2기 교육생</i>
      </td>
      <td>
        <img width="160px" src="https://lab.ssafy.com/webmobile1-sub1/s02p11d138/raw/develop/Profile/%EC%9D%B4%EC%9D%91%EC%9E%AC.jpg"><br>
         [이응재](https://github.com/Woogie924)<br>
         <i>Project lead & Developer</i><br>
         <i>SSAFY 2기 교육생</i>
      </td>
      <td>
        <img width="160px" src="https://lab.ssafy.com/webmobile1-sub1/s02p11d138/raw/develop/Profile/%EA%B9%80%ED%98%84%EC%9A%B0.jpg"><br>
         [김현우](https://github.com/Woogie924)<br>
         <i>Project lead & Developer</i><br>
         <i>SSAFY 2기 교육생</i>
      </td>
      <td>
        <img width="160px" src="https://lab.ssafy.com/webmobile1-sub1/s02p11d138/raw/develop/Profile/%EB%B0%95%EA%B5%90%EC%97%B4.jpg"><br>
         [박교열](https://github.com/Woogie924)<br>
         <i>Project lead & Developer</i><br>
         <i>SSAFY 2기 교육생</i>
      </td>
      <td>
        <img width="160px" src="https://lab.ssafy.com/webmobile1-sub1/s02p11d138/raw/develop/Profile/%EA%B9%80%EC%A0%95%EB%8D%95.jpg"><br>
         [김정덕](https://github.com/Woogie924)<br>
         <i>Project lead & Developer</i><br>
         <i>SSAFY 2기 교육생</i>
      </td>
   </tr>
</table>


## 🤲 OverView<br> 
### Goal
 - SPA(Single Page Application)의 개념을 이해 
 - 반응형 웹(Responsive Web)의 특징을 파악 
 - 사용자 친화적이고 다양한 디바이스에 최적화 된 웹을 구현하는 것

### Used Technology
- Vue.js (FrontEnd)
    - 컴포넌트 단위의 관리로 유지보수와 재사용성이 높은 Vue.js 활용
- FireBase (BackEnd)
    - 구조를 단순화하고 초기 개발 시간을 단축하는 데에 도움이 되는 Firebase 활용

## ✨ Prequuisited<br>
### develop machine Spec
- Gitlab - SSAFY gitlab 사용합니다. 문제 발생시 [gitLab docs](https://lab.ssafy.com/help)를 활용합니다.
- Vue.js - VS Code 설치 및 [Vue.js 공식 웹사이트](https://kr.vuejs.org/index.html) 및 [StackOverflow](https://stackoverflow.com/)를 통해 시작합니다
- Node.js - Node.js 및 npm 패키지 관리자 다운로드 설치합니다
- Firebase - [Firebase 공식 웹사이트](https://firebase.google.com/?hl=ko)를 방문하여 공식 메뉴얼 진행합니다
- Jira - [SSAFY jira](https://jira.ssafy.com/secure/Dashboard.jspa)를 사용합니다. jira를 통해 issue 및 WorkFlow 등 전반적인 스프린트를 관리합니다.

## 🎫 프로젝트 수행 목록
<table>
<tr><td>1. 프로젝트 개발 환경 설정</tr></td>
<tr><td>2. Firebase 연동</tr></td>
<tr><td>3. About Me 리사이징</tr></td>
<tr><td>4. Portfolio 리사이징</tr></td>
<tr><td>5. 이미지 배너 리사이징</tr></td>
<tr><td>6. 네비게이션 바</tr></td>
<tr><td>7. 모바일 사이드 바</tr></td>
<tr><td>8. 사용자 정보 Drawer</tr></td>
<tr><td>9. FrontEnd 배포</tr></td>
<tr><td>10. 기능 테스트</tr></td>
</table>
<br>

## 🎲 파일/디렉터리 구조
- assets
    - 프로젝트에 필요한 정적 리소스가 위치하는 디렉토리
    - 이미지, 외부 css, 외부 js등
- components
    - 페이지 단위 컴포넌트에서 쓰이는 소규모 컴포넌트가 위치하는 디렉토리
- services
    - 프로젝트에 필요한 로직과 관련된 함수 js 파일이 위치하는 디렉토리
- views
    - 페이지 단위의 컴포넌트가 위치하는 디렉토리

- router.js : 라우터 설정 관련 파일
- store.js : vuex 파일
- App.vue : 최상위 컴포넌트
- main.js : 최상위 컴포넌트를 최초 호출하는 JavaScript 파일

- - -

## 🤦 SetUp
### NPM 설치
- [Node.js 공식 홈페이지](https://nodejs.org/ko/) 접속
- 안정성과 신뢰도가 높은 LTS 버전 선택 권장

### NPM 설치여부, 설치된 버전 확인
```cmd
> npm --version
```

### Vue.js, Vue-cli, Firebase 패키지 설치
```cmd
C:\> cd webmobile-skeleton
C:\webmobile-skeleton> npm install
C:\webmobile-skeleton> npm install -g yarn
C:\webmobile-skeleton> npm install -g @vue/cli
C:\webmobile-skeleton> npm install vue
C:\webmobile-skeleton> npm install -g firebase-tools
C:\webmobile-skeleton> npm list
```

### npm 이미지 업로더
```cmd
//1. vue-filepond filepond 설치
$ npm install vue-filepond filepond --save

//2. 플러그인 설치 옵션 사항
$ npm install filepond-plugin-file-validate-type --save
$ npm install filepond-plugin-image-preview --save
```

## 😆 Run
### 프로젝트 실행
"App running" 메시지 확인
```cmd
> npm run serve
```

### 프로젝트 로컬 실행
1. 명령 프롬프트(cmd)에서 아래 커맨드 수행
```cmd
> vuejs-paginator@1.0.0 dev <프로젝트 경로>
> node build/dev-server.js
```

2. 웹 브라우저에서 http://localhost:8080 접속



## 👌 Deploy 
### Firebase 서버 배포
1. [](https://firebase.google.com) 접속 및 로그인
2. firebase 프로젝트 시작하기 버튼 클릭
3. 프로젝트 생성
4. 생성된 프로젝트의 웹 속성 탭으로 이동
5. 본인의 firebase SDK 확인 및 태그 첨부
6. 로컬 개발 환경에서 명령 프롬프트(cmd) 실행 후 Deploy
```cmd
> firebase login
> firebase init
> firebase deploy
```
7. Firebase 대시보드에서 배포 결과 및 URL 확인
8. 해당 URL로 접속하여 웹 페이지 확인

