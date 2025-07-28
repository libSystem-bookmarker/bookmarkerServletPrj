<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.bookmark.myweb.model.MemberVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
%>
<!--  jstl var -->
<c:url var="loginUrl" value="/loginForm.do" />
<c:url var="myPageUrl" value="/myPage.do" />
<c:url var="logoutUrl" value="/logout.do" />

<div class="header">
	<div>
		<strong>KCC 대학교 도서관</strong>
	</div>
	<div>
		<%
//로그인 여부에 따른 header 요소 변환
if (loginMember == null) {
%>
		<a href="${loginUrl}" style="color: white; text-decoration: none;">로그인</a>
		<%
            } else {
        %>
		<span style="color: white;"> <%= loginMember.getName() %>님이
			로그인하셨습니다.
		</span> &nbsp; <a href="${myPageUrl}"
			style="color: white; text-decoration: none;">마이 페이지</a> &nbsp; <a
			href="${logoutUrl}" style="color: white; text-decoration: none;">로그아웃</a>
		<%
            }
        %>
	</div>
</div>
