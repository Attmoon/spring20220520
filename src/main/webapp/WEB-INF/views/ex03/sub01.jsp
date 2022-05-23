<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css" integrity="sha512-GQGU0fMMi238uA+a/bdWJfpUGKUkBdgfFdgBm72SUQ6BeyWjoY/ton0tEjH+OSH9iP4Dfh+7HM0I9f5eR0L/4w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

	<script>
		$(document).ready(function() {
			$("#button1").click(function() {
				$.ajax({url : "/spr2/ex03/sub03"});
			});
			
			$("#button2").click(function() {
				$.ajax({url : "/spr2/ex03/sub04"});
			});
			
			$("#button3").click(function() {
				$.ajax({
					url : "/spr2/ex03/sub05", 
					type : "get"
				});
			});
			
			$("#button4").click(function() {
				$.ajax({
					url : "/spr2/ex03/sub06", // 어디로
					type : "post"			  // 어떤방식으로 (type혹은 method)
				});
			});
			
			$("#button5").click(function() {
				$.ajax({
					url : "/spr2/ex03/sub07",
					type : "delete"
				});
			});
			
			$("#button6").click(function() {
				$.ajax({
					url : "/spr2/ex03/sub08",
					type : "put"
				});
			});
		});
	</script>
<title>Insert title here</title>
</head>
<body>
	<button id="button1">ajax 요청 보내기</button>
	
	<%-- 이 버튼을 클릭하면 /spr2/ex03/sub04로 ajax 요청 보내기 --%>
	<%-- 컨트롤러에도 해당경로 요청에 일하는 메소드 추가 --%>
	<button id="button2">ajax 요청 보내기2</button>
	
	<br />
	
	<%-- /spr2/ex03/sub05 get 방식 요청 보내기 --%>
	<button id="button3">get 방식 요청 보내기</button>
	
	<%-- /spr2/ex03/sub06 post 방식 요청 보내기 --%>
	<button id="button4">post 방식 요청 보내기</button>
	
	<%-- /spr2/ex03/sub07 delete 방식 요청 보내기 --%>
	<button id="button5">delete 방식 요청 보내기</button>
	
	<%-- /spr2/ex03/sub08 put 방식 요청 보내기 --%>
	<button id="button6">put 방식 요청 보내기</button>
	
	
	
</body>
</html>