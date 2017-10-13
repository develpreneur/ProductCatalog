<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vendor</title>
</head>
<body>
	<div align="center">
		<h1>Vendor List</h1>
		<h3>
			<a href="./newVendor">New Vendor</a>
		</h3>
		<table border="1">
			<tr>
				<th>Vendor_id</th>
				<th>Name</th>
				<th>address1</th>
				<th>address2</th>
				<th>city</th>
				<th>state</th>
				<th>zip</th>
				<th>country</th>
				<th>phone</th>
				<th>website</th>
			</tr>
			<c:forEach items="${vendors}" var="vendor">
				<tr>
					<td>${vendor.vendor_id}</td>
					<td>${vendor.name}</td>
					<td>${vendor.address1}</td>
					<td>${vendor.address2}</td>
					<td>${vendor.city}</td>
					<td>${vendor.state}</td>
					<td>${vendor.zip}</td>
					<td>${vendor.country}</td>
					<td>${vendor.phone}</td>
					<td>${vendor.website}</td>
					<td><a href="./editVendor?id=${vendor.vendor_id}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="./deleteVendor?id=${vendor.vendor_id}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<br />
	<p>
		<a href="./">&lt;&lt;-- Return to Home</a>
	</p>
</body>
</html>
