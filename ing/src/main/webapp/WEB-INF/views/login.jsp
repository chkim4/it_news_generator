<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
	<%@ include file = "cdn/lib-common.jsp" %>
	<link rel="stylesheet" href="css/login.css">
</head>
<body>
    <div id="wrapper" >
        <div id="content-wrapper" >
            <div id="content">      
	            <div class="container">
                    <div class="row justify-content-center">
			            <div class="col-xl-10 col-lg-12 col-md-9">			
			                <div class="card o-hidden border-0 shadow-lg my-5">
			                    <div class="card-body p-0">
			                        <div class="row">
			                            <div class="col-lg-8 mx-auto">
			                                <div class="p-5">
			                                    <div class="text-center">
			                                       <img class="logo" src="img/logo-black.png" alt = "로고"/>
			                                    </div> 
			                                    <br/>
			                                    <form class="user" action="/login-proc" method = "post">
			                                        <div class="form-group">
			                                            <input type="email" class="form-control form-control-user"
			                                                id="exampleInputEmail" 
			                                                name="email"
			                                                aria-describedby="emailHelp"
			                                                placeholder="이메일을 입력하세요...">
			                                        </div>
			                                        <div class="form-group">
			                                            <input type="password" class="form-control form-control-user"
			                                                id="password" name="pass" placeholder="비밀번호를 입력하세요...">
			                                        </div>
			                                    
			                                        <input type="submit" class="btn btn-user btn-block" value="로그인">
			                                        <hr>
			                                    </form>
			                                    <hr>
			                                    <div class="text-center">
			                                        <a class="small" href="/register">아직 회원이 아니신가요? 가입하기</a>
			                                    </div>
			                                </div>
			                            </div>
			                        </div>
			                    </div>
			                </div>
			            </div>
	               </div>
			     </div>
			 </div>
	    </div>
	</div>
    <%@ include file = "layout/footer.jsp" %>
    
</body>
</html>