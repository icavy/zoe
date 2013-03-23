<!DOCTYPE html>  
<html lang="zh_cn">
	 <head>
	 	<link rel="stylesheet" href="<@util.url relativeUrl='/resource/bootstrap/css/bootstrap.min.css' />" type="text/css" media="screen" />
		<script type="text/javascript" src="<@util.url relativeUrl='/resource/js/jquery-1.8.0.min.js' />" charset="utf-8"></script>
		<script type="text/javascript" src="<@util.url relativeUrl='/resource/bootstrap/js/bootstrap.min.js' />" charset="utf-8"></script>
		<script type="text/javascript" src="<@util.url relativeUrl='/resource/jquery-validation/jquery.validate.min.js' />" charset="utf-8"></script>
		<link rel="stylesheet" href="<@util.url relativeUrl='/resource/style.css' />" type="text/css" media="screen" />
		<script>
			$(document).ready(function(){
				$("#loginForm").validate();
			})
		</script>
    </head>
    <body>
  		<div class="container" id="login"> 
   			<div style="width:450px;margin:160px auto 0px;"> 
    			<div class="section section-large">
    				<div class="section-header">
						<h3>Login</h3>
					</div>
					<div class="section-body">
						<#if Request.message??>
							<div class="alert alert-error textcenter"><@util.message code=Request.message /></div>
						</#if>
						<form action="<@util.url relativeUrl='/login' />" method="post" id="loginForm">						
						<div class="row-fluid">
							<label for="user_login">邮箱：</label>
							<input class="span12" id="user_loginName" name="loginName" value="${Request.loginName!""}" size="30" type="text" required/>
							<label for="user_password">密码：</label>
							<input class="span12" id="user_password" name="password" size="30" type="password" required />
							<!--<p class="help-block">
								<i class="icon-user"></i>
								<a href="<@util.url relativeUrl='/login/forget' />">忘记密码?</a>
							</p>-->
						</div>
						<div class="form-actions">
							<div class="row">
								<div class="span2" style="width:65px">
									<input class="btn btn-primary btn-large" name="commit" type="submit" value="登录">
								</div>
								<!--<div class="span3" style="padding-top:10px">
									<label class="checkbox" for="user_remember_me">
										<input id="user_remember_me" name="remember_me" type="checkbox" value="1" />
										记住我
									</label>
								</div>-->
							</div>
						</div>
						</form>
					</div>
				</div>
   			</div>
		</div>
    </body>
</html>