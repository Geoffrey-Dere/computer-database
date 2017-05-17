<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="mylib" uri="../mylib.tld"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>dashboard</title>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
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
	</div>
	</header>

	<section id="main">
	<div class="container">
		<h1 id="homeTitle">${fn:length(listComputer)}Computersfound</h1>

		<h1>Spring MVC Internationalization i18n Example</h1>
		
		<c:set var="val"><spring:message code="edit"/></c:set>
	<input id="representante_legal" type="hidden" value="${val}"/>
	
	
		Language : <a href="?lang=en">English</a> | <a href="?lang=fr">French</a>

		<div id="actions" class="form-horizontal">
			<div class="pull-left">
				<form id="searchForm" action="#" method="GET" class="form-inline">

					<input type="search" id="searchbox" name="search"
						class="form-control" placeholder="Search name" /> <input
						type="submit" id="searchsubmit" value="Filter by name"
						class="btn btn-primary" />
				</form>
			</div>
			<div class="pull-right">
				<a class="btn btn-success" id="addComputer" href="add"><spring:message
						code="add.computer" /> </a> <a class="btn btn-default"
					id="editComputer" href="#" onclick="$.fn.toggleEditMode();"> <spring:message
						code="edit" /></a>
			</div>
		</div>
	</div>

	<form id="deleteForm"
		action="${pageContext.request.contextPath}/deleteComputer"
		method="POST">
		<input type="hidden" name="selection" value="">
	</form>

	<div class="container" style="margin-top: 10px;">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th class="editMode" style="width: 60px; height: 22px;"><input
						type="checkbox" id="selectall" /> <span
						style="vertical-align: top;"> - <a href="#"
							id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
								class="fa fa-trash-o fa-lg"></i>
						</a>
					</span></th>
					<th><spring:message code="computer.name" /></th>
					<th><spring:message code="computer.introduced" /></th>
					<!-- Table header for Discontinued Date -->
					<th><spring:message code="computeur.discontinued" /></th>
					<!-- Table header for Company -->
					<th><spring:message code="computer.company" /></th>

				</tr>
			</thead>
			<!-- Browse attribute computers -->
			<tbody id="results">
				<c:forEach items="${listComputer}" var="computer">

					<c:url value="editComputer" var="myURL">
						<c:param name="id" value="${computer.id}" />
s					</c:url>

					<tr>
						<td class="editMode"><input type="checkbox" name="cb"
							class="cb" value=${computer.id}></td>
						<td><a href="<c:out value="${myURL}"/>" onclick=""> <c:out
									value="${computer.name}" />
						</a></td>
						<td><c:out value="${computer.introduced}" /></td>
						<td><c:out value="${computer.discontinued}" /></td>
						<td><c:out value="${computer.companyName}" /></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</section>

	<footer class="navbar-fixed-bottom">
	<div class="container text-center">

		<mylib:pagination currentPage="${currentPage}" limit="${limit}"
			maxPages="${maxPages}" uri="${uriPage}" uriLimit="${uriLimit}"
			uriSearch="${uriSearch}" search="${search}" />

	</div>
	</footer>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>