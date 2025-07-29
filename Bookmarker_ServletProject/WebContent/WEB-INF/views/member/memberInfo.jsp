<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/common/common.css">
    <link rel="stylesheet" href="/resources/css/common/sideNav.css">
    <link rel="stylesheet" href="/resources/css/member/profile.css">
    <meta charset="UTF-8">
    <title>BOOKMARK</title>
</head>
<body>
<header> <jsp:include page="/WEB-INF/views/common/header.jsp"/> </header>
<main>
    <jsp:include page="/WEB-INF/views/common/sidebar.jsp"/>
    <section id = "main-section" class="content">
    <p>현재 포함되는 페이지: ${includePage}</p>
    <c:if test="${not empty includePage}">
    	<jsp:include page="${includePage}" />
    </c:if>
    </section>
</main>
<footer> <jsp:include page="/WEB-INF/views/common/footer.jsp"/> </footer>
<script src="/resources/js/common/sidebar.js" defer></script>
</body>
</html>
