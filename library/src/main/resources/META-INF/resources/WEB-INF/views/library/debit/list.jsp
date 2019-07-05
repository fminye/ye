<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>借阅列表</title>
<link href="/lyf_library/library/css/main.css" rel="stylesheet"/>
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">  
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#submit").click(function(){
	    var arr =[];
	    $("input[id='bookId']").each(function(){
            arr.push($(this).val());
        });
	    alert(arr);
	    $.ajax({
	    	type:"post",
	    	url:"/lyf_library/library/debit/addList",
	    	data:{arr},
	    	success:function(data){
	    		alert(data);
	    	}
	    	
	    });
	});
	
});

</script>
</head>
<body>
<div id="library">
<div id="back">
<a class="btn btn-success btn-xs" href="/lyf_library"><</a>
</div>
<h1>借阅列表</h1>
<form method="post" enctype="multipart/form-data">
	<c:forEach items="${debitList.items }" var="item">
	<div class="item">
	<div id="pic">
	<img style="" alt="" src="/lyf_library/library/images/${item.book.image }"/></div>
<div class="bookName"><span id="book">书名:</span><a href="#">${item.book.name}</a>
<input  type="hidden"  id="bookId" value="${item.book.id}" readonly="readonly"> 
			</div>
			<div class="bookDescription"><span id="book">描述:</span></br><a href="#">${item.book.description}</a>
			</div>
	<div class="buttons"><a class="btn btn-danger btn-xs" href="/wjq_library/library/debit/remove/${item.book.id }" style="float: right;">-</a>
	</div>
	</div>
	</c:forEach> 
	</form>
	<div id="button">
	<input type="button" id="submit" class="btn btn-success btn-xs" href="/lyf_library/submit" value="确定借阅"/>
	<a class="btn btn-success btn-xs" href="/wjq_library">继续借阅</a>
	</div>
	</div>
</body>
</html>