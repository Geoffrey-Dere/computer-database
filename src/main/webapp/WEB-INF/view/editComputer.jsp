<%@page import="com.excilys.cdb.model.Computer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
	</div>
	</header>
	<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<div class="label label-default pull-right">
					<c:out value="${computer.id}" />
				</div>
				<h1>Edit Computer</h1>

				<form:form action="editComputer" method="POST"
					commandName="computer">
					<form:input type="hidden" path="id" id="id" name="id" />
					<fieldset>
						<div class="form-group">
							<label for="computerName">Computer name</label>
							<form:input type="text" class="form-control" id="computerName"
								name="computerName" placeholder="Computer name" path="name" />
							<form:errors path="name" cssClass="error" />
						</div>
						<div class="form-group">
							<label for="introduced">Introduced date</label>
							<form:input type="date" class="form-control" id="introduced"
								name="introduced" placeholder="Introduced date"
								path="introduced" />
								<form:errors path="introduced" cssClass="error" />
						</div>
						<div class="form-group">
							<label for="discontinued">Discontinued date</label>
							<form:input type="date" class="form-control" id="discontinued"
								name="discontinued" placeholder="Discontinued date"
								path="discontinued" />
							<form:errors cssClass="error" />
						</div>
						<div class="form-group">


							<label for="companyId">Company</label> <select
								class="form-control" id="companyId" name="companyID">
								<option value="0"
									<c:if test="${computer.companyId eq 0}"> selected </c:if>>--</option>

								<c:forEach items="${listCompany}" var="company">
									<option value="${company.id}"
										<c:if test="${computer.companyId eq  company.id}"> 
										<c:out value="selected"/> 
										</c:if>>${company.name}
									</option>
								</c:forEach>

							</select>

						</div>
					</fieldset>
					<div class="actions pull-right">
						<input type="submit" value="Edit" class="btn btn-primary">
						or <a href="<c:url value="dashboard"/>" class="btn btn-default">Cancel</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	</section>
</body>
</html>