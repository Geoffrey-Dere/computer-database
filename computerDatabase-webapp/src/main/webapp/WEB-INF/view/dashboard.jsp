<jsp:include page="../component/header.jsp" />
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mylib" uri="../mylib.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<spring:message code="dashboard.placeholder.search"
	var="placeholderSearch" />
<spring:message code="dashboard.button.filter" var="buttonFilter" />




<section id="main">
	<div class="container">
		<h1 id="homeTitle">${fn:length(listComputer)}
			<spring:message code="dashboard.computer.found" />
		</h1>

		<input id="representante_legal" type="hidden" value="${val}" />

		Language : <a href="?lang=en">English</a> | <a href="?lang=fr">French</a>

		<div id="actions" class="form-horizontal">
			<div class="pull-left">
				<form id="searchForm" action="#" method="GET" class="form-inline">

					<input type="search" id="searchbox" name="search"
						class="form-control" placeholder="${placeholderSearch}" /> <input
						type="submit" id="searchsubmit" value="${buttonFilter}"
						class="btn btn-primary" />
				</form>
			</div>
			<div class="pull-right">
				<a class="btn btn-success" id="addComputer" href="add"><spring:message
						code="dashboard.add.computer" /> </a> <a class="btn btn-default"
					id="editComputer" href="#" onclick="$.fn.toggleEditMode();"> <spring:message
						code="dashboard.edit" /></a>
			</div>
		</div>
	</div>

	<form id="deleteForm"
		action="${pageContext.request.contextPath}/deleteComputer"
		method="POST">
		<input type="hidden" name="selection" value=""> <input
			type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

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
					<th><spring:message code="dashboard.computer.name" /></th>
					<th><spring:message code="dashboard.computer.introduced" /></th>
					<th><spring:message code="dashboard.computeur.discontinued" /></th>
					<th><spring:message code="dashboard.computer.company" /></th>
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
<script src="js/dashboard.js"></script>

</body>
</html>