<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/common/common.css">
    <link rel="stylesheet" href="/resources/css/common/sideNav.css">
    <link rel="stylesheet" href="/resources/css/member/profile.css">
    <link rel="stylesheet" href="/resources/css/member/insertmember.css"> 
    <link rel="stylesheet" href="/resources/css/member/memberlist.css"> 
    <meta charset="UTF-8">
    <title>BOOKMARK</title>
</head>
<body>
<header> <jsp:include page="/WEB-INF/views/common/header.jsp"/> </header>
<main>
    <jsp:include page="/WEB-INF/views/common/sidebar.jsp"/>
    <section id = "main-section" class="content">
    <c:if test="${not empty sessionScope.msg}">
    <script>alert('${sessionScope.msg}');</script>
    <c:remove var="msg" scope="session"/>
</c:if>
    
    <c:if test="${not empty includePage}">
    	<jsp:include page="${includePage}" />
    </c:if>
    </section>
</main>
<footer> <jsp:include page="/WEB-INF/views/common/footer.jsp"/> </footer>
<script src="/resources/js/common/sidebar.js" defer></script>
<script src="/resources/js/member/memberlist.js" defer></script>
</body>
</html>
