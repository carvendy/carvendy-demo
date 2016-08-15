<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html ng-app>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=8" />
		<meta http-equiv="content-Type" content="text/html; charset=UTF-8" >
		<title>index</title>
	</head>
	
	
	<style>
		/* CSS Document */
		/*全局样式*/
		*{padding:0; margin:0;}
		/*body, html { width:100%; height:70%; background:#eee url(bg.jpg); }*/
		body{font-family:Arial, Verdana, Tahoma, "宋体", Helvetica, sans-serif; font-size:12px;} 
	</style>

	<script src="/js/angular/angular-1.0.2.min.js"></script>


	<body>
		<form action="/reg/reg.form" method="post" ng-controller="MyController">
                	
            <div class="input-control">
                <label for="email-or-phone">邮箱/手机</label>
                <input type="text" name="loginName" for="email-or-phone" autocomplete="off" placeholder="手机或邮箱将作为登录名" maxlength="60">
                <!-- <div class="tip" style="left: 0px; width: 0px;">
                 <div class="warn-tip">请输入11位的手机号码或常用邮箱</div>
                </div> -->
            </div>
            <div>
                <label for="password1">登录密码</label>
                <input type="password" name="password" id="password1" placeholder="请输入您的密码" maxlength="16">
                <!-- <div class="tip" style="left: 0px; width: 0px;"><div class="warn-tip">请输入6-16个字符的密码</div></div> -->
            </div>
            <div class="input-control">
                <label for="repassword1">确认密码</label>
                <input type="password" name="repassword" id="repassword1" placeholder="请再次输入密码" maxlength="16">
                <!-- <div class="tip" style="left: 0px; width: 0px;"><div class="warn-tip">请再次输入密码</div></div> -->
            </div>
            <div class="input-control">
                <label for="company">企业名称</label>
                <input type="text" name="comName"  id="company" placeholder="请输入您的企业名称" maxlength="20" >
                <!-- <div class="tip" style="left: 0px; width: 0px;"><div class="warn-tip">填工商局注册的公司名，注册后不可改</div></div> -->
            </div>
           
            <div class="confirm-control">
                <input type="checkbox" name="comfirm" id="comfirm1" checked="checked">
                <label for="comfirm1">我已阅读并同意<a href="/terms.html" target="_blank">《xxxxxxxx》</a></label>
            </div>
           
            <div class="submit-control">
                <input type="submit" class="submit" value="立即注册">
            </div>
        </form>
	</body>


	<script>
		function MyController($scope){
			
		}
	</script>
</html>