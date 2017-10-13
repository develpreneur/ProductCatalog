<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product</title>
</head>
<body>
	<div align="center">
		<h1>Product List</h1>
		<h3>
			<a href="./newProduct">New Product</a>
		</h3>
		<table border="1">
			<tr>
				<th>Product_id</th>
				<th>Category_id</th>
				<th>Name</th>
				<th>Summary</th>
				<th>Description</th>
				<th>Price</th>
				<th>Qty</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${products}" var="product">
				<tr>
					<td>${product.product_id}</td>
					<td>${product.category_id}</td>
					<td>${product.name}</td>
					<td>${product.summary}</td>
					<td>${product.description}</td>
					<td>${product.price}</td>
					<td>${product.qty}</td>
					<td><a href="./editProduct?id=${product.product_id}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="./deleteProduct?id=${product.product_id}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<br/>
	<p><a href="./">&lt;&lt;-- Return to Home</a></p>
</body>
</html>
