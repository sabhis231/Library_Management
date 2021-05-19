<%-- Document : index Created on : May 11, 2021, 5:41:46 PM Author : sabhis231 --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<input type="hidden" value="${contextPath}" name="contextPath" id="contextpath"/>


<c:if test="${not empty sessionScope.Role}">
    <META http-equiv="refresh" content="0;URL=views/${sessionScope.Role}/index.jsp">
</c:if>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Login V18</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->	
        <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>


        <link href="Assets/css/util.css" rel="stylesheet" type="text/css"/>
        <link href="Assets/css/main.css" rel="stylesheet" type="text/css"/>
        <style>
            .dark-edition {
                background: #1f283e;
                opacity: .94;
            }
            .dark-theme {
                background-color: #1f283e;
                opacity: 7;
            }
            .login-error {
                text-align: center !important;
                color: red;
                font-size: 18px;
            }

        </style>


    </head>
    <body>
        <div class="limiter">
            <div class="container-login100">
                <div class="wrap-login100">
                    <form class="login100-form validate-form" method="post" id="login">
                        <span class="login100-form-title p-b-43">
                            Login to Library
                        </span>
                        <div class="w-full p-t-3 p-b-32 login-error">
                            Invalid Credentials
                        </div>

                        <div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
                            <input class="input100" type="text" id="user" name="userName" required="" placeholder="Enter your Email Id" >
                        </div>


                        <div class="wrap-input100 validate-input" data-validate="Password is required">
                            <input class="input100" type="password" required="" name="password" id="pwd" placeholder="Enter your Password" >
                        </div>

                        <div class="flex-sb-m w-full p-t-3 p-b-32">

                        </div>
                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn" type="submit" data-spinner="">
                                Login
                            </button>
                        </div>
                    </form>
                    <div class="login100-more" dark-theme" style="background-image: url('Assets/image/bg-01.jpg');">
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="Assets/plugins/jquery/jquery.min.js" type="text/javascript"></script>
    <script src="Assets/plugins/notifyjs/notify.js" type="text/javascript"></script>
    <script src="Assets/js/login.js" type="text/javascript"></script>
    <script src="Assets/js/main.js" type="text/javascript"></script>
</html>
