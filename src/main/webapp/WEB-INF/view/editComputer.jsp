<jsp:include page="../component/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<div class="label label-default pull-right">
					<c:out value="${computer.id}" />
				</div>
				<h1>Edit Computer</h1>

				<form:form action="${pageContext.request.contextPath}/editComputer"
					method="POST" commandName="computer">
					<form:input type="hidden" path="id" id="id" name="id" />
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
									<c:choose>
										<c:when test="${computer.companyId eq  company.id}">
											<form:option value="${company.id}">${company.name}</form:option>
										</c:when>
										<c:otherwise>
											<form:option value="${company.id}">${company.name}</form:option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select>

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