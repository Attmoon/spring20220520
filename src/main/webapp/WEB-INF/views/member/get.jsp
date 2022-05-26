<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.*" %>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css" integrity="sha512-GQGU0fMMi238uA+a/bdWJfpUGKUkBdgfFdgBm72SUQ6BeyWjoY/ton0tEjH+OSH9iP4Dfh+7HM0I9f5eR0L/4w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<title>Insert title here</title>

<script>
$(document).ready(function() {
	// 중복, 암호 확인 변수
	let pwOk = false;
	let emailOk = false;
	let nickNameOk = false;
	
	$("#email1").keyup(function() {
		$("#checkEmailButton1").removeAttr("disabled");
		
	});
	
	$("#nick1").keyup(function() {
		$("#checkNickNameButton1").removeAttr("disabled");
		
	});
	
	
	$("#checkEmailButton1").click(function(e) {
		e.preventDefault(); // 원래 하던일을 진행하지 않고
		
		$(this).attr("disabled", "");
		const data = {
				email : $("#form1").find("[name=email]").val() // form1안에 name이 id인것의 값
		};
		emailOk = false;
		$.ajax({
			url : "${appRoot}/member/check",
			type : "get",
			data : data, // 서버로 보내는 data
			success : function(data) { // 서버에서 받는 data(위에 선언한 data아님)
				switch (data) {
				case "ok" :
					$("#eMailMessage1").text("이메일 확인 완료");
					emailOk = true;
					break;
				case "notOk" :
					$("#eMailMessage1").text("이메일 확인 실패");
					break;
				}
			},
			error : function() {
				$("#eMailMessage1").text("이메일 확인중 문제발생, 다시 시도해주세요.");
			},
			complete : function() {
				$("#checkEmailButton1").removeAttr("disabled");
				enableSubmit();
			}
		});
	});
	
	$("#checkNickNameButton1").click(function(e) {
		e.preventDefault(); // 원래 하던일을 진행하지 않고
		
		$(this).attr("disabled", "");
		const data = {
				nickName : $("#form1").find("[name=nickName]").val() // form1안에 name이 id인것의 값
		};
		nickNameOk = false;
		$.ajax({
			url : "${appRoot}/member/check",
			type : "get",
			data : data, // 서버로 보내는 data
			success : function(data) { // 서버에서 받는 data(위에 선언한 data아님)
				switch (data) {
				case "ok" :
					$("#nickNameMessage1").text("닉네임 확인 완료");
					nickNameOk = true;
					break;
				case "notOk" :
					$("#nickNameMessage1").text("닉네임 확인 실패");
					break;
				}
			},
			error : function() {
				$("#nickNameMessage1").text("닉네임 확인중 문제 발생, 다시 시도해 주세요.");
			},
			complete : function() {
				$("#checkNickNameButton1").removeAttr("disabled");
				enableSubmit();
			}
		});
	});
	
	// 패스워드 오타 확인
	$("#passwordInput1, #passwordInput2").keyup(function() {
		const pw1 = $("#passwordInput1").val();
		const pw2 = $("#passwordInput2").val();
		
		pwOk = false;
		if (pw1 === pw2) {
			$("#passwordMessage1").text("패스워드가 일치합니다.")
			pwOk = true;
		} else {
			$("#passwordMessage1").text("패스워드가 일치하지 않습니다.")
		}
		
		enableSubmit();
	});
	
	// 회원가입 submit 버튼 활성화/비활성화
	const enableSubmit = function() {
		if (emailOk && nickNameOk) {
			$("#modifyButton1").removeAttr("disabled");
		} else {
			$("#modifyButton1").attr("disabled", ""); // 그외엔 disabled가 그대로 남아있으면됨
		}
	}
});
</script>
</head>
<body>
	<my:navBar></my:navBar>
	<form id="form1" action="${appRoot }/member/modify" method="post">
	<div>
	아이디 : <input type="text" value="${member.id }" readonly /> <br />
	암호 : <input id="passwordInput1" type="text" value="${member.password }" /> <br />
	암호확인 : <input id="passwordInput2" type="text" value="${member.password }" /> <br />
	이메일 : <input id="email1" type="email" name="email" value="${member.email }" /> <button id="checkEmailButton1" disabled>이메일중복확인</button> <br />
	<p id="eMailMessage1"></p>
	닉네임 : <input id="nick1" type="text" name="nickName" value="${member.nickName }" /> <button id="checkNickNameButton1" disabled>닉네임중복확인</button> <br />
	<p id="nickNameMessage1"></p>
	가입일시 : <input type="datetime-local" value="${member.inserted }" readonly /> <br />
	</div>
	</form>
	
	<%-- 요구사항 
	1. 이메일 input에 변경 발생시 '이메일중복확인버튼 활성화' 
	   ->버튼 클릭시 ajax로 요청/응답, 적절한 메시지 출력
	2. 넥네임 input에 변경 발생시 '닉네임중복확인버튼 활성화'
	   ->버튼 클릭시 ajax로 요청/응답, 적절한 메시지 출력
	   
	3. 암호/암호확인일치, 이메일 중복확인 완료, 닉네임 중복확인 완료시에만
	   수정버튼 활성화
	--%>
	
	<div>
	<button form="form1" type="submit" id="modifyButton1" disabled>수정</button>
	<button data-bs-toggle="modal" data-bs-target="#modal1" >삭제</button>
	</div>
	
	<!-- Modal -->
<div class="modal fade" id="modal1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
	      <form id="form1" action="${appRoot }/member/remove" method="post">
	      	<input type="hidden" value="${member.id }" name="id" />
	        암호 : <input type="text" name="password" />
	      </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button form="form1" type="submit" class="btn btn-danger">탈퇴</button> <%-- form밖에 있어도 form의 attribute역할하기 --%>
      </div>
    </div>
  </div>
</div>
</body>
</html>