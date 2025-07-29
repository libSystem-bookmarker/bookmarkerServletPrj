<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- JSTL URL 설정 -->
<c:url var="loginUrl" value="/loginForm.do" />
<c:url var="myPageUrl" value="/myPage.do" />
<c:url var="logoutUrl" value="/logout.do" />

<div class="header">
	<div>
		<strong>KCC 대학교 도서관</strong>
	</div>
	<div>
		<c:choose>
			<c:when test="${empty sessionScope.loginMember}">
				<a href="${loginUrl}" style="color: white; text-decoration: none;">로그인</a>
			</c:when>
			<c:otherwise>
				<span style="color: white;">
					${sessionScope.loginMember.name}님이 로그인하셨습니다.
				</span>
				&nbsp;
				<a href="${myPageUrl}" style="color: white; text-decoration: none;">마이 페이지</a>
				&nbsp;
				<a href="${logoutUrl}" style="color: white; text-decoration: none;">로그아웃</a>
			</c:otherwise>
		</c:choose>
	</div>
</div>
