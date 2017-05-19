<jsp:include page="../component/header.jsp" />
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<section id="main">
	<div class="container">

		<c:if test="${not empty error}">
			<p>
				<c:out value="${error}"></c:out>
		</c:if>

		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<h1>
					<spring:message code="addComputer.title" />
				</h1>


				<spring:message code="addComputer.placeholder.name"
					var="placeholderName" />
				<spring:message code="addComputer.placeholder.introduced"
					var="placeholderIntroduced" />
				<spring:message code="addComputer.placeholder.discontinued"
					var="placeholderDiscontinued" />

				<form:form action="${pageContext.request.contextPath}/add"
					method="POST" commandName="computerDTO">
					<fieldset>
						<div class="form-group">
							<label for="computerName"><spring:message
									code="addComputer.Computer.name" /></label>
							<form:input type="text" class="form-control" id="computerName"
								name="computerName" placeholder="${placeholderName}" path="name" />
							<form:errors path="name" cssClass="error" />
						</div>
						<div class="form-group">
							<label for="introduced"><spring:message
									code="addComputer.Computer.introduced" /></label>
							<form:input type="date" class="form-control" id="introduced"
								name="introduced" path="introduced"
								placeholder='${placeholderIntroduced}' />
							<form:errors path="introduced" cssClass="error" />
						</div>
						<div class="form-group">
							<label for="discontinued"><spring:message
									code="addComputer.Computer.discontinued" /></label>
							<form:input type="date" class="form-control" id="discontinued"
								name="discontinued" placeholder="${placeholderDiscontinued}"
								path="discontinued" />
							<form:errors cssClass="error" />
						</div>
						<div class="form-group">
							<label for="companyId"><spring:message
									code="addComputer.Company" /></label>
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
						<spring:message code="addComputer.button.submit"
							var="buttonSubmit" />
						<input type="submit" id="submit" value="${buttonSubmit}"
							class="btn btn-primary">
						<spring:message code="addComputer.or" />
						<a href="dashboard.html" class="btn btn-default"><spring:message
								code="addComputer.button.cancel" /></a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</section>
<script src="js/addComputer.js"></script>

</body>
</html>