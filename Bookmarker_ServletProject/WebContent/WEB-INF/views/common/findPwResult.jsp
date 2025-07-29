<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>비밀번호 조회 및 변경</title>
</head>
<body>
    <h2>비밀번호 조회 결과</h2>

   <c:if test="${not empty pw}">
    <p>찾은 비밀번호는 <strong>${pw}</strong> 입니다.</p>

    <h3>비밀번호 변경</h3>
    <form action="${pageContext.request.contextPath}/updatepw.do" method="post">
        <input type="hidden" name="userId" value="${param.userId}" />
        <label>새 비밀번호: </label>
        <input type="password" name="newPw" required />
        <br/><br/>
        <button type="submit">비밀번호 변경</button>
    </form>
</c:if>

    <c:if test="${not empty successMsg}">
        <p style="color:green;">${successMsg}</p>
    </c:if>

    <c:if test="${not empty errorMsg}">
        <p style="color:red;">${errorMsg}</p>
    </c:if>

    <br/>
    <a href="${pageContext.request.contextPath}/loginForm.do">로그인 페이지로</a>
</body>
</html>
