<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/common/common.css">
<link rel="stylesheet" href="/resources/css/common/loginForm.css"> 
<link rel="stylesheet" href="/resources/css/common/sideNav.css"> 
<meta charset="UTF-8">
<title>BOOKMARK</title>
</head>
<body>
<header> <%@ include file="/WEB-INF/views/common/header.jsp" %> </header>
<main>
<%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
<section class="contetnt">
<%
    String tab = request.getParameter("tab");
    if (tab == null || tab.isEmpty()) {
        tab = "profile"; // 기본값
    }

    String pageToInclude = null;

    switch (tab) {
        case "profile":
            pageToInclude = "memberTab/profile.jsp";
            break;
        case "password":
            pageToInclude = "memberTab/password.jsp";
            break;
        case "image":
            pageToInclude = "memberTab/image.jsp";
            break;
        default:
            pageToInclude = "memberTab/profile.jsp"; // 잘못된 값도 기본 탭으로
    }
%>

<section>
    <jsp:include page="<%= pageToInclude %>" />
</section>

</section>
</main>
<footer> <%@ include file="/WEB-INF/views/common/footer.jsp" %> </footer>
<script src="/resources/js/common/sidebar.js" defer></script>
</body>
</html>