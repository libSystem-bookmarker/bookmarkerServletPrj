<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common/findpwresult.css">
    <link href="https://hangeul.pstatic.net/hangeul_static/css/maru-buri.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>비밀번호 결과</title>
</head>
<body>
<header>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
</header>

<main>
<section class="content">
    <h2>비밀번호 찾기 결과</h2>

    <c:if test="${not empty successMsg}">
        <div class="result-message success">${successMsg}</div>
    </c:if>

    <c:if test="${not empty errorMsg}">
        <div class="result-message error">${errorMsg}</div>
    </c:if>

    <c:if test="${not empty userId}">
        <form action="${pageContext.request.contextPath}/updatepw.do" method="post" class="pw-update-form">
            <input type="hidden" name="userId" value="${userId}" />
            <label for="newPw">새 비밀번호</label>
            <input type="password" name="newPw" id="newPw" placeholder="새 비밀번호 입력" required />
            <button type="submit">비밀번호 변경</button>
        </form>
    </c:if>

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
