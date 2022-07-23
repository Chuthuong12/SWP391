<%-- 
    Document   : Login
    Created on : May 26, 2022, 9:02:11 PM
    Author     : thuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Login </title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->	
        <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="css/login/util.css">
        <link rel="stylesheet" type="text/css" href="css/login/main.css">
        <!--===============================================================================================-->
    </head>
    <body>
        
        
       <span class="form-signin" >
            <div class="limiter">
                <div class="container-login100" style="background-image: url('images/bg-01.jpg');">
                    <div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
                        <form class="login100-form validate-form" action="reset" method="post"> 
                        <span class="login100-form-title p-b-49" >
                                Reset  Password
                            </span>
                            <p class="login100-form"> </p>
                            <div class="wrap-input100 validate-input" data-validate="Password is required">
                                <span class="label-input100"><b>Email</b></span>
                                <input class="input100" type="email" name="email" placeholder="Type your email">
                                <span class="focus-input100" data-symbol="&#xf190;"></span>
                            </div>
                          
                            <div class="text-right p-t-8 p-b-31">
                                <a href="Login.jsp">
                                    Back to Login
                                </a>
                            </div>

                            <div class="container-login100-form-btn">
                                <div class="wrap-login100-form-btn">
                                    <div class="login100-form-bgbtn"></div>
                                    <button type="submit" class="login100-form-btn">
                                        Reset
                                    </button>
                                </div>
                            </div>
                        </form>
                          

                        
                    </div>
                </div>
            </div>


            <div id="dropDownSelect1"></div>
        </span>
        <!--===============================================================================================-->
        <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/animsition/js/animsition.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/bootstrap/js/popper.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/select2/select2.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/daterangepicker/moment.min.js"></script>
        <script src="vendor/daterangepicker/daterangepicker.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/countdowntime/countdowntime.js"></script>
        <!--===============================================================================================-->
        <script src="js/login/main.js"></script>
        <script src="https://apis.google.com/js/platform.js" async defer></script>
          <script>
        function onSignIn(googleUser) {
        var profile = googleUser.getBasicProfile();
        console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
        console.log('Name: ' + profile.getName());
        console.log('Image URL: ' + profile.getImageUrl());
        console.log('Email: ' + profile.getEmail());
        // This is null if the '
        console.write(profile.getEmail());
        }
    </script>
    </body>
</html>