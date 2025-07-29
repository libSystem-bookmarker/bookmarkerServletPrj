<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://hangeul.pstatic.net/hangeul_static/css/maru-buri.css" rel="stylesheet">
<link rel="stylesheet" href="/resources/css/common/common.css">
<meta charset="UTF-8">
<title>BOOKMARK</title>
</head>
<body>
<header> <%@ include file="/WEB-INF/views/common/header.jsp" %> </header>
<main>
<section class="content">
<form action="updateMember.do" method="post" enctype="multipart/form-data">
    <input type="text" name="name" value="${loginMember.name}">
    <input type="text" name="phoneNumber" value="${loginMember.phoneNumber}">
    <input type="text" name="email" value="${loginMember.email}">
    <input type="text" name="address" value="${loginMember.address}">
    
    <!-- 학과 선택은 관리자만 -->
    <c:if test="${loginMember.role == 'admin'}">
        <select name="unitId">
            <option value="101">국문과</option>
            <option value="401">컴공과</option>
            ...
        </select>
    </c:if>

    <!-- 이미지 파일 등록 -->
    <c:if test="${loginMember.role != 'admin'}">
        <input type="file" name="profileImage">
        <label><input type="checkbox" name="deleteImage" value="true"> 프로필 이미지 삭제</label>
    </c:if>

    <button type="submit">정보 수정</button>
</form>

</section>
</main>
<footer> <%@ include file="/WEB-INF/views/common/footer.jsp" %> </footer>
</body>
</html>