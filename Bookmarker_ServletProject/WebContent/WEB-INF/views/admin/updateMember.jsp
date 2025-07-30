<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="/updateMemberByAdmin.do" method="post" enctype="multipart/form-data">
<h2>회원 상세 정보 변경하기</h2>
    <input type="hidden" name="userId" value="${member.userId}">
    <input type=text name="role" value="${member.role}">
    <input type="text" name="name" value="${member.name}">
    <input type="text" name="phoneNumber" value="${member.phoneNumber}">
    <input type="text" name="email" value="${member.email}" placeholder="이메일">
    <input type="text" name="address" value="${member.address}">
    
    <!-- 학과 선택은 관리자만 -->
    <c:if test="${loginMember.role == 'admin'}">
        <select name="unitId">
            <option value="2">국문과</option>
            <option value="20">컴공과</option>
        </select>
    </c:if>

    <!-- 이미지 파일 등록 -->
    <c:if test="${loginMember.role != 'admin'}">
        <input type="file" name="profileImage">
        <label><input type="checkbox" name="deleteImage" value="true"> 프로필 이미지 삭제</label>
    </c:if>

    <button type="submit">정보 수정</button>
</form>
