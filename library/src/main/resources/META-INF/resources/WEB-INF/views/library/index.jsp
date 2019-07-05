<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书馆首页</title>
<link href="/lyf_library/library/css/main.css" rel="stylesheet"/>
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">  
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div id="library">
<div id="find">
<form method="get" class="bs-example bs-example-form" role="form" >
		<div class="row">
			<div class="col-lg-6">
				<div class="input-group">
					<input type="text" class="form-control" name='keyword' value="${param.keyword }" placeholder="请输入你想要借的书名"/>
					<span class="input-group-btn">
						<button class="btn btn-default">
							搜索
						</button>
					</span>
				</div>
			</div>
		</div>
	</form>
	</div>
	<!-- 这里应该要循环从服务器返回的数据 -->
<%-- 	<%
	Page<Book> page = request.getAttribute("page");
	List<Book> content = page.getContent();
	for( Book book : content ){
	%>
		<div>
			<%=book.getName() %>
			+
		</div>
	<%} %> --%>
	
	<!-- 循环标签库 -->
	<!-- items是标签的属性，表示要遍历哪些元素 -->
	<%-- ${ page.content } 其实相当于前面的 request.getAttribute("page") + content = page.getContent() --%>
	<c:forEach items="${page.content }" var="book">
		<%-- book.name相当于是book.getName() --%>
		<div class="item">
			<div id="pic"><img style="" alt="" src="/lyf_library/library/images/${book.image}"/></div>
			<div class="bookName"><span id="book">书名:</span><a href="#">${book.name}</a>
			</br><b style="color:#000;font-size:32px">库存:</b><i style="color:#F00">${book.storage}</i>
			</div>
			<div class="bookDescription"><span id="book">描述:</span></br><a href="#">${book.description}</a>
			</div>
			<div class="buttons">
           <a class="btn btn-success btn-xs"  onclick="document.location.href='/lyf_library/library/debit?bookId=${book.id}'">+</a>
			</div>
		</div>
	</c:forEach>
<!-- 分页的按钮 -->
<div id="page">
		<c:if test="${page.number <= 0 }">
			<a class="btn btn-success btn-xs"><</a>
		</c:if>
		<c:if test="${page.number > 0 }">
		<a class="btn btn-success btn-xs" href="?pageNumber=${page.number - 1 }&keyword=${param.keyword}"><</a>
		</c:if>
		<%-- 为什么要减一？因为number从0开始，而totalPages从1开始 --%>
		<c:if test="${page.number < page.totalPages - 1 }">
		<a class="btn btn-success btn-xs" href="?pageNumber=${page.number + 1 }&keyword=${param.keyword}">></a>
		</c:if>
		<c:if test="${page.number >= page.totalPages - 1 }">
			<a class="btn btn-success btn-xs">></a>
		</c:if>
	</div>
	</div>
</body>
</html>