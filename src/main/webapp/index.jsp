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
        <title>Library Management</title>
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
            .login-success {
                text-align: center !important;
                color: green;
                font-size: 18px;
            }
            .signup-button   {
                padding-top: 18px;
                text-align: center;

            }
            .txt1 {
                font-size: 19px;
            }

            hr{

                margin-top: 19px;

            }

            .login80-form-btn {
                display: -webkit-box;
                display: -webkit-flex;
                display: -moz-box;
                display: -ms-flexbox;
                display: flex;
                justify-content: center;
                align-items: center;
                padding: 0 20px;
                width: 60%;
                height: 50px;
                border-radius: 10px;
                /*background: #24b156;*/
                background: #303c90;
                /*#303c90*/
                margin-left:20%;

                font-family: Montserrat-Bold;
                font-size: 12px;
                color: #fff;
                line-height: 1.2;
                text-transform: uppercase;
                letter-spacing: 1px;

                -webkit-transition: all 0.4s;
                -o-transition: all 0.4s;
                -moz-transition: all 0.4s;
                transition: all 0.4s;
            }
            .login80-form-btn:hover {
                background: #078faf;
            }
            .login100-form-btn { 
                background: #27ab56;
            }
            .hidden {display:none}
        </style>


    </head>
    <body>
        <div class="limiter">
            <div class="container-login100">
                <div class="wrap-login100">
                    <form class="login100-form validate-form" method="post" id="login">
                        <span class="login100-form-title p-b-43" id="title">
                            LogIn to Library
                        </span>
                        <div class="w-full p-t-3 p-b-32 login-error">

                        </div>

                        <div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
                            <input class="input100" type="text" id="user" name="userName" required="" placeholder="Enter your Email Id" >
                        </div>


                        <div class="wrap-input100 validate-input" data-validate="Password is required">
                            <input class="input100" type="password" required="" name="password" id="pwd" placeholder="Enter your Password" >
                        </div>

                        <div class="wrap-input100  hidden" data-validate="Confirm Password is required" id="confimPassword" >
                            <input class="input100" type="password" name="confpwd" id="confpwd" placeholder="Enter your Confirm Password" >
                        </div>

                        <div class="flex-sb-m w-full p-t-3 p-b-32">

                        </div>
                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn" type="submit" id="button1">
                                Login
                            </button>
                        </div>

                        <hr>

                        <div class="w-full p-t-3 p-b-32 signup-button">
                            <div>
                                <button class="login80-form-btn" type="button" id="button2">
                                    Create a Account
                                </button>
                            </div>
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
    <!--<script src="Assets/js/main.js" type="text/javascript"></script>-->
</html>
