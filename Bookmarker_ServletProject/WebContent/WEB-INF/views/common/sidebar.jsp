<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="side-menu">
  <ul class="menu">

    <!-- 공통 메뉴 -->
  	<li class="menu-item menu-item-active" onclick="selectMenu(this, 'profile')">
 		 <a href="javascript:void(0);">내 정보</a>
	</li>
    <!-- 관리자(admin) 메뉴 -->
<c:if test="${loginMember.role == 'admin'}">
  <li class="menu-item" onclick="selectMenu(this, 'insertMember')">
    <a href="javascript:void(0);">회원 등록</a>
  </li>
  <li class="menu-item" onclick="selectMenu(this, 'memberList')">
    <a href="javascript:void(0);">회원 조회</a>
  </li>
</c:if>

    <!-- 사서(librarian) 메뉴 -->
    <c:if test="${loginMember.role == 'librarian'}">
    <li class="menu-item" onclick="selectMenu(this, 'selectBooks')">
        <a href="javascript:void(0)">도서 조회</a>
      </li>
      <li class="menu-item" onclick="selectMenu(this, 'insertBookform')">
        <a href="javascript:void(0)">도서 등록</a>
      </li>
      <li class="menu-item" onclick="selectMenu(this, 'loanBooks')">
        <a href="javascript:void(0)">대출 내역</a>
      </li>
    </c:if>

    <!-- 학생(student) 메뉴 -->
    <c:if test="${loginMember.role == 'student'}">
      <li class="menu-item" onclick="selectMenu(this)">
        <a href="#">도서 대출 내역</a>
      </li>
      <li class="menu-item" onclick="selectMenu(this)">
        <a href="#">도서 장바구니</a>
      </li>
    </c:if>
  </ul>
</nav>