<%-- 
    Document   : ResetPass
    Created on : Jun 17, 2022, 1:46:37 AM
    Author     : thuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <jsp:include page="base-view/baseTag.jsp"></jsp:include>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        
        <link rel="stylesheet" href="./css/login.css">
        
        <title>Forgot password</title>
    </head>

    <body>

        <jsp:include page="base-view/headerLogin.jsp"></jsp:include>

            <main class="login-form">
                <div class="cotainer">
                    <div class="row justify-content-center">
                        <div class="col-md-8">
                            <div class="card">
                                <div class="card-header">Forgot password</div>
                                <div class="card-body">

                                <c:if test="${success != null}">
                                <div class="forn-group row success">
                                    <div class="col-md-2"></div>
                                    <p class="col-md-10 margin-0">${success}</p>
                                </div>
                                </c:if>

                                <form action="reset" method="POST" onsubmit="return submitForm()">
                                    <div class="form-group row">
                                        <label for="email_address" class="col-md-4 col-form-label text-md-right">E-Mail
                                            Address</label>
                                        <div class="col-md-6">
                                            <input type="text" id="email_address" class="form-control" name="email-address" required>
                                        </div>                                      
                                    </div>
                                    <div class="form-group row margin-bottom-0">
                                        <p class="col-md-6 offset-md-4 error" >${error}</p>
                                    </div>

                                    <div class="col-md-6 offset-md-4">
                                        <button type="submit" class="btn btn-primary">
                                            Send
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>    

    </body>
</html>
