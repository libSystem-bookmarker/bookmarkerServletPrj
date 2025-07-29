<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common/findpwform.css">
    <link href="https://hangeul.pstatic.net/hangeul_static/css/maru-buri.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>비밀번호 찾기</title>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
</header>

<main>
<section class="content">
    <h2>비밀번호 찾기</h2>

    <form action="${pageContext.request.contextPath}/findpwresult.do" method="post" class="pw-form">
        <label for="userId">회원 번호</label>
        <input type="number" name="userId" id="userId" placeholder="회원 번호 입력" required />

        <button type="submit">비밀번호 찾기</button>
    </form>

    <div class="back-link">
        <a href="${pageContext.request.contextPath}/loginForm.do">로그인 화면으로 돌아가기</a>
    </div>
</section>
</main>

<footer>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</footer>
</body>
</html>
