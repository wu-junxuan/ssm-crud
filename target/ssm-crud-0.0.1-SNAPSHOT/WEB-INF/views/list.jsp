<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!-- web路径：
不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
		http://localhost:3306/crud
 -->
<script type="text/javascript"
	src="${APP_PATH }/static/js/jquery-1.12.4.js"></script>
<link
	href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- 搭建显示页面 -->
	<div class="container">

		<div class="row">
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary">add</button>
				<button class="btn btn-danger">delete</button>

			</div>
		</div>
		<div class="row">
			<table class="table table-hover">
				<tr>
					<th>#</th>
					<th>empname</th>
					<th>gender</th>
					<th>email</th>
					<th>department</th>
					<th>action</th>
				</tr>
				<c:forEach items="${pageInfo.list }" var="emp">
				<tr>
					<th>${emp.empId }</th>
					<th>${emp.empName}</th>
					<th>${emp.gender=="M"? "male":"female"}</th>
					<th>${emp.email}</th>
					<th>${emp.department.deptName}</th>
					<th>
						<button class="btn btn-primary btn-sm">
							<span class="glyphicon glyphicon glyphicon-pencil"
								aria-hidden="true">edit
						</button>
						<button class="btn btn-danger btn-sm">
							<span class="glyphicon glyphicon glyphicon-trash"
								aria-hidden="true">delete
						</button>
					</th>
				</c:forEach>
				
				</tr>
			</table>
		</div>
		<div class="row">
			<div class="col-md-6">
			当前${pageInfo.pageNum }页,总共${pageInfo.pages }页，总共${pageInfo.total }页
			
			</div>
			<div class="col-md-6">
				<nav aria-label="Page navigation">
				<ul class="pagination">
				
					<li><a href="${APP_PATH }/emps?pn=1">首页</a></li>	
				<c:if test="${pageInfo.hasPreviousPage }">
					<li>
					<a href="${APP_PATH }/emps?pn=${pageInfo.pageNum-1}" aria-label="Previous"> 
					<span aria-hidden="true">&laquo;</span>
					</a>
					</li>
				</c:if>


					<c:forEach items="${pageInfo.navigatepageNums}" var="pageNum">
					<c:if test="${pageNum==pageInfo.pageNum }">
					<li class="active"><a href="#">${pageNum }</a></li>
					</c:if>
					<c:if test="${pageNum!=pageInfo.pageNum }">
					<li><a href="${APP_PATH }/emps?pn=${pageNum }">${pageNum }</a></li>
					</c:if>
					</c:forEach>
					
					
<!-- 					<li><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li> -->
					<c:if test="${pageInfo.hasNextPage}">
					<li><a href="${APP_PATH }/emps?pn=${pageInfo.pageNum+1}" aria-label="Next"> 
					<span aria-hidden="true">&raquo;</span>
					</a></li>
					
				</c:if>
					<li><a href="${APP_PATH }/emps?pn=${pageInfo.pages }">末页</a></li>
				</ul>
				</nav>
			</div>

		</div>
		<div class="row"></div>


	</div>
</body>
</html>