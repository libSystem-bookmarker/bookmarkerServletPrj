<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>관리자 대시보드</title>
</head>
<body>
    <h2>관리자 대시보드</h2>
    
    <ul>
        <li><a href="${pageContext.request.contextPath}/insertMemberForm.do">회원 등록</a></li>
        <li><a href="${pageContext.request.contextPath}/selectAllMember.do">회원 전체 조회</a></li>
        <li><a href="${pageContext.request.contextPath}/updateMemberForm.do">회원 수정 (예정된 기능)</a></li>
        <li><a href="${pageContext.request.contextPath}/deleteMemberForm.do">회원 삭제</a></li>
        <li><a href="${pageContext.request.contextPath}/logout.do">로그아웃</a></li>
    </ul>
    
    <c:if test="${not empty loginUser}">
        <p>환영합니다, ${loginUser.name} (${loginUser.role})님</p>
    </c:if>
</body>
</html>
