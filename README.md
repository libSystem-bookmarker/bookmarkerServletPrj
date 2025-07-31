# 📚 Bookmarker - 대학 도서관 웹 시스템 (JSP/Servlet 기반)

Java Servlet과 JSP를 기반으로 구축된 대학 도서관 웹 시스템입니다.  
도서 대출, 회원 관리, 공지사항 게시판 등 도서관의 핵심 기능을 포함하고 있으며, 관리자/사서/학생 별 역할 기반 접근 제어를 지원합니다.


## 🔧 기술 스택

| 구성 요소 | 기술 |
|-----------|------|
| Language | Java (JDK 21) |
| Frontend | JSP, HTML, CSS, JavaScript |
| Backend | Servlet, JDBC |
| Database | Oracle 21g |
| Build Tool | Apache Tomcat 9 |
| 기타 | JSTL, EL, AJAX, MVC 패턴 구조 |



## 🧩 주요 기능

### 📖 공통 기능
- 로그인/로그아웃
- 세션 기반 권한 처리
- 프로필 조회 및 수정 (프로필 이미지 포함)

### 👤 관리자
- 회원 전체 목록 조회 및 검색 (이름, 학과명, 역할)
- 회원 등록/수정/삭제 (학부/학과 선택 포함)
- 공지사항 CRUD ((향후 확장 예정)
- 대출 순위, 학과별 대출 통계 조회 (향후 확장 예정)

### 📗 사서
- 공지사항 작성 및 수정
- 학생 정보 열람
- 도서 정보 관리 (향후 확장 예정)

### 👨‍🎓 학생 (향후 확장 예졍)
- 내 정보 조회 및 수정
- 공지사항 열람
- 대출 통계 확인



## 📁 프로젝트 구조
bookmarkerServletPrj/
├── src/
│ ├── com.bookmark.myweb.controller/ # Controller 계층
│ ├── com.bookmark.myweb.model/ # VO, DAO 클래스
│ └── com.bookmark.myweb.common/ # DispatcherServlet, 공통 유틸
├── WebContent/
│ ├── css/, js/, images/ # 정적 리소스
│ ├── common/, member/, admin/ # JSP 뷰 디렉토리
│ └── WEB-INF/
│ ├── lib/ # JSTL 라이브러리 등
│ └── web.xml # 배포 서술자
└── command.properties # URI - Controller 매핑




## ⚙️ DB 기본 테이블

- `MEMBER` : 사용자 정보 테이블
- `ACADEMIC_UNIT` : 학부/학과 정보 테이블 (계층 구조)
- `NOTICE` : 공지사항 (향후 확장 예정)
- `BOOK`, `BORROW`



## 🙋‍♀️ 개발자

| 이름 | 역할 |
|------|------|
| 김연수 | 백엔드 개발, 설계, ui 디자인,  전체 구조 구현 |
| 차윤하 | 백엔드 개발, ui 디자인 |
| 김병석 | 백엔드 개발, ui 디자인 |



## 📌 향후 개선 사항

- 도서 CRUD 기능 완성 및 대출 프로세스 구현
- 관리자/사서 권한 분리 강화
- 테스트 데이터 자동 삽입 스크립트 작성
- 사용자 피드백 기반 UI 개선


