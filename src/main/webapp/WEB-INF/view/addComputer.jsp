<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">


<script type="text/javascript" src="webjars/jquery/2.1.4/jquery.js"></script>
<script type="text/javascript" src="js/addComputer.js"></script>
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand" href="dashboard.html"> Application -
			Computer Database </a>
	</div>
	</header>

	<section id="main">
	<div class="container">

		<c:if test="${not empty error}">
			<p>
				<c:out value="${error}"></c:out>
		</c:if>

		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<h1>
					<spring:message code="AddComputer.title" />
				</h1>


				<spring:message code="AddComputer.placeholder.name"
					var="placeholder.name" />
				<spring:message code="AddComputer.placeholder.introduced"
					var="placeholder.introduced" />
				<spring:message code="AddComputer.placeholder.discontinued"
					var="placeholder.discontinued" />

				<form:form action="${pageContext.request.contextPath}/add"
					method="POST" commandName="computerDTO">
					<fieldset>
						<div class="form-group">
							<label for="computerName"><spring:message
									code="AddComputer.Computer.name" /></label>
							<form:input type="text" class="form-control" id="computerName"
								name="computerName" placeholder="${placeholder.name}"
								path="name" />
							<form:errors path="name" cssClass="error" />
						</div>
						<div class="form-group">
							<label for="introduced"><spring:message
									code="AddComputer.Computer.introduced" /></label>
							<form:input type="date" class="form-control" id="introduced"
								name="introduced" placeholder="${placeholder.introduced}"
								path="introduced" />
							<form:errors path="introduced" cssClass="error" />
						</div>
						<div class="form-group">
							<label for="discontinued"><spring:message
									code="AddComputer.Computer.discontinued" /></label>
							<form:input type="date" class="form-control" id="discontinued"
								name="discontinued" placeholder="${placeholder.discontinued}"
								path="discontinued" />
							<form:errors cssClass="error" />
						</div>
						<div class="form-group">
							<label for="companyId"><spring:message
									code="AddComputer.Company" /></label>
							<form:select class="form-control" id="companyId" name="companyID"
								path="companyId">
								<form:option value="0">--</form:option>
								<c:forEach items="${listCompany}" var="company">
									<form:option value="${company.id}">${company.name}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<input type="submit" id="submit" value="Add"
							class="btn btn-primary"> or <a href="dashboard.html"
							class="btn btn-default">Cancel</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	</section>
</body>
</html>