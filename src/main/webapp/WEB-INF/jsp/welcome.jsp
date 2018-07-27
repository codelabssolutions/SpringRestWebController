<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<!-- 
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<style>
table {
    table-layout: fixed;
    width: 100%;
    border:1px solid;
    margin-top:20px;
    border-collapse: collapse;
}
td {
    min-width: 250px;
    border:1px solid;
}
</style>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Hotfix</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="home.html">Home</a></li>
					<!--  <li><a href="product.html">Add Product</a></li>-->
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<div class="starter-template">
		 <table>
		  <tr>
	                 <td>ID</td>
	                 <td>Name</td>
	                 <td>Description</td>
	                 <td>Type</td>
	                 <td>Price</td>
	                 <td>Unit</td>
	                  <td>Remove</td>
	               </tr>
		   <tbody>
			 <c:if test="${not empty productResults}">
              <c:forEach var="product" items="${productResults}">
           		  <tr>
	                 <td>${product.id}</td>
	                 <td>${product.name}</td>
	                 <td>${product.description}</td>
	                 <td>${product.type}</td>
	                 <td>${product.price}</td>
	                <td>${product.unit}</td>
	                 <td><a href="delete.html?id=${product.id}">Remove</a></td>
	              </tr>
       		   </c:forEach>
        	</c:if>
          </tbody>
      </table>
	</div>

	</div>
	<!-- /.container -->

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
