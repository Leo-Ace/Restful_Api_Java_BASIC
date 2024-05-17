<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</head>
<body>

	<div class="container">
		<form action="product?action=onadd" method="post"
			 class="mx-auto col-8 shadow mt-4 p-3" enctype="multipart/form-data">
			<h2 class="text-center">Form</h2>
			<div class="form-group">
				<label for="name-inp"><b>Name</b></label> <input type="text"
					name="name" id="name-inp" class="form-control rounded-0"
					placeholder="Name">
			</div>
			<div class="form-group">
				<label for="category"><b>Category</b></label> <select
					name="category_id" id="category" class="form-control rounded-0">
					<c:forEach items="${ listCategory }" var="ct" varStatus="vs">
						<option value="${ ct.id }" ${ vs.count == 1 ? "selected" : "" }>${ ct.name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="price-inp"><b>Price</b></label> <input type="number"
					name="price" id="price-inp" class="form-control rounded-0"
					placeholder="Price">
			</div>
			<div class="form-group">
				<label for="description"><b>Description</b></label>
				<textarea name="description" id="description"
					class="form-control rounded-0" cols="" rows="3"></textarea>
			</div>
			<div class="form-group">
				<label for="file-inp"><b>Thumbnail</b></label> <input type="file"
					name="file" id="file-inp" class="form-control rounded-0">
					<img
					alt="" id="preview-img" src=""
					width="400px" class="d-none">
			</div>
			<button type="submit" class="btn btn-info rounded-0">Submit</button>
		</form>
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
		<script type="text/javascript">
		const fileInputElem = document.getElementById('file-inp');
		const previewElem = document.getElementById("preview-img");
		let valueImageDefault = previewElem.src;
		fileInputElem.onchange = evt => {
			  const [file] = fileInputElem.files
			  if (file) {
				  previewElem.src = URL.createObjectURL(file);
				  previewElem.setAttribute("class", "d-block");
			  } else {
				  previewElem.src = "";
				  previewElem.setAttribute("class", "d-none");
			  }
			}
		</script>
</body>
</html>