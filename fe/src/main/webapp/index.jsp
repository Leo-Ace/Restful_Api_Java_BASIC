<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<html lang="en">
<head>
<title>Title</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<div class="container">
		<h2 class="text-center my-3">Product</h2>
		<div class="d-flex align-items-center pb-3">
			<a href="product?action=add" class="btn btn-info rounded-0 mr-2">Add
				Product</a>
			<form class="d-flex align-items-center">
				<input type="text" name="q" value="${ param.q }"
					class="form-control rounded-0" placeholder="search name...">
				<button type="submit" class="btn btn-success rounded-0">
					Search</button>
			</form>
		</div>
		<c:if test="${ !empty responseData }">
			<div
				class="alert alert-${ responseData.code == 200 ? 'success' : 'danger' }"
				role="alert">
				<strong>${ responseData.message }</strong>
			</div>
		</c:if>
		<div>
			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th>Name</th>
						<th>Price</th>
						<th>Description</th>
						<th>Thumbnail</th>
						<th>Category</th>
						<th>Created_at</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ pagination.data }" var="dt" varStatus="vs">
						<tr>
							<td scope="row">${ vs.count }</td>
							<td>${ dt.name }</td>
							<td>${ dt.price }</td>
							<td>${ dt.description }</td>
							<td><img alt="http://localhost:8080/be/${ dt.thumbnail }"
								src="http://localhost:8080/be/${ dt.thumbnail }" width="100px"></td>
							<td>${ dt.category.name }</td>
							<td>${ dt.created_at }</td>
							<td><a
								href="product?action=edit&id=${fn:substringBefore(dt.id, '.')}"
								class="btn btn-info rounded-0 mr-2">Edit</a> <a
								href="product?action=delete&id=${fn:substringBefore(dt.id, '.')}"
								class="btn btn-danger rounded-0"
								onclick="return confirm('Are you sure?')">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div>
				<nav aria-label="Page navigation">
					<ul class="pagination">
						<li
							class="page-item ${ pagination.currentPage == 1 ? 'disabled' : '' }">
							<c:choose>
								<c:when test="${ !empty param.q }">
									<a class="page-link"
										href="product?q=${ param.q }&page=${ pagination.currentPage - 1 }"
										aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
										<span class="sr-only">Previous</span>
									</a>
								</c:when>
								<c:otherwise>
									<a class="page-link" href="product?page=${ pagination.currentPage - 1 }"
										aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
										<span class="sr-only">Previous</span>
									</a>
								</c:otherwise>
							</c:choose>
						</li>
						<c:forEach begin="1" end="${ pagination.totalPages }" var="page">
							<li
								class="page-item ${ page == pagination.currentPage ? 'active' : '' }">
								<c:choose>
									<c:when test="${ !empty param.q }">
										<a class="page-link"
											href="product?q=${ param.q }&page=${ page }">${ page }</a>
									</c:when>
									<c:otherwise>
										<a class="page-link" href="product?page=${ page }">${ page }</a>
									</c:otherwise>
								</c:choose>
							</li>
						</c:forEach>
						<li class="page-item ${ pagination.currentPage == pagination.totalPages ? 'disabled' : '' }"><c:choose>
								<c:when test="${ !empty param.q }">
									<a class="page-link"
										href="product?q=${ param.q }&page=${ pagination.currentPage + 1 }"
										aria-label="Next"> <span aria-hidden="true">&raquo;</span>
										<span class="sr-only">Next</span>
									</a>
								</c:when>
								<c:otherwise>
									<a class="page-link" href="product?page=${ pagination.currentPage + 1 }"
										aria-label="Next"> <span aria-hidden="true">&raquo;</span>
										<span class="sr-only">Next</span>
									</a>
								</c:otherwise>
							</c:choose></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>