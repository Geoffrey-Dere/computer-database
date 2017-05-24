<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<title>Computer Database</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand" href="dashboard"> Application - Computer
			Database </a>

		<ul class="nav navbar-nav navbar-right">
			<c:choose>
				<c:when test="${isUserLogged eq  false}">
					<li><a href="#"><span class="glyphicon glyphicon-user"></span>
							Sign Up</a></li>
					<li><a href="login"><span
							class="glyphicon glyphicon-log-in"></span> <spring:message
								code="dashboard.login" /></a></li>
				</c:when>
				<c:otherwise>

					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"> ${user.username} <span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="logout"><span
							class="glyphicon glyphicon-log-out"></span> <spring:message
								code="dashboard.logout" /></a></li>
						</ul>
				</c:otherwise>
			</c:choose>
		</ul>


	</div>
	</header>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>