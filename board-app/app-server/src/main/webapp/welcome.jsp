<%@page import="com.bitcamp.board.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bitcamp</title>
</head>
<body>
<!-- As a link -->
<nav class="navbar navbar-light bg-light">
  <a class="navbar-brand" href="#">두창이의 노예생활</a>
</nav>
<h1>환영합니다!-JSP</h1>
<p>비트캠프 게시판 관리 시스템 프로젝트입니다.</p>
<ul>
  <li><a href='${contextPath}/app/board/list'>게시글</a></li>
  <li><a href='${contextPath}/app/member/list'>회원</a></li>
<c:choose>
  <c:when test="${not empty sessionScope.loginMember}">
    <li><a href="${contextPath}/app/auth/logout">${sessionScope.loginMember.name}(로그아웃)</a></li>
  </c:when>
  <c:otherwise>
    <li><a href='${contextPath}/app/auth/form'>로그인</a></li>
  </c:otherwise>
</c:choose>
</ul>
</body>
</html>




