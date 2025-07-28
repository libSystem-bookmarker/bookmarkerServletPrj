<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.bookmark.myweb.model.MemberVO" %>

<nav class="side-menu">
  <ul class="menu">

    <!-- 공통 메뉴 -->
    <li class="menu-item" onclick="selectMenu(this)">
      <a href="/memberInfoForm.do">내 정보</a>
    </li>

    <!-- 관리자(admin) 메뉴 -->
    <c:if test="${loginMember.role == 'admin'}">
      <li class="menu-item" onclick="selectMenu(this)">
        <a href="/insertMemberForm.do">회원 등록</a>
      </li>
      <li class="menu-item" onclick="selectMenu(this)">
        <a href="/selectAllMember.do">회원 조회</a>
      </li>
    </c:if>

    <!-- 사서(librarian) 메뉴 -->
    <c:if test="${loginMember.role == 'librarian'}">
      <li class="menu-item" onclick="selectMenu(this)">
        <a href="/insertBookform.do">도서 등록</a>
      </li>
      <li class="menu-item" onclick="selectMenu(this)">
        <a href="/selectBooks.do">도서 조회</a>
      </li>
      <li class="menu-item" onclick="selectMenu(this)">
        <a href="/overdueList.do">연체자 조회</a>
      </li>
    </c:if>

    <!-- 학생(student) 메뉴 -->
    <c:if test="${loginMember.role == 'student'}">
      <li class="menu-item" onclick="selectMenu(this)">
        <a href="/bookLoanList.do">도서 대출 내역</a>
      </li>
      <li class="menu-item" onclick="selectMenu(this)">
        <a href="/overdueList.do">연체된 도서 목록</a>
      </li>
      <li class="menu-item" onclick="selectMenu(this)">
        <a href="/bookedList.do">예약 도서 목록</a>
      </li>
      <li class="menu-item" onclick="selectMenu(this)">
        <a href="/selectCart.do">도서 장바구니</a>
      </li>
    </c:if>

  </ul>
</nav>
