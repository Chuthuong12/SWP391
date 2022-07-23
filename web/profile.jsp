<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Account" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="base-view/baseTag.jsp"></jsp:include>
            <link href="css/base.css" rel="stylesheet" type="text/css"/>
            <link href="css/profile.css" rel="stylesheet" type="text/css"/>

            <title>Profile</title>
        </head>
        <body>
        <jsp:include page="base-view/headerUser.jsp"></jsp:include>

            <div id="container">
                <form action="profile" method="POST" >
                    <div id="table-header">
                        <span>My profile</span>
                    </div>

                    <div>
                        <table>                   

                        <c:if test="${isNoti != null}">
                            <tr>
                                <td colspan="2">
                                    <div id="notification">
                                        <span>Profile has been changed successfully!</span>
                                    </div>
                                </td>
                            </tr>
                        </c:if>
                        <tr><td colspan="2" id="error-fname"></td></tr>

                        <tr>
                            <td>Full name</td>
                            <td><input type="text"  name="fullname" value="${acc.fullname}" required/></td>
                        </tr>
                        <tr><td colspan="2" id="error-lname" ></td></tr>
                        <tr>
                            <td>Avatar</td>
                            <td><input type="file" name="avatar" placeholder=""></td>
                        </tr>
                        <tr>
                            <td>Phone</td>
                            <td><input type="text" name="phone" value="${acc.phone}" required/></td>
                        </tr>
                        <tr><td colspan="2" ></td></tr>

                        <tr>
                            <td>Email</td>
                            <td><input type="text" name="email" value="${acc.email}" disabled="disabled"/></td>
                        </tr>
                        <tr><td colspan="2"></td></tr>

                        <tr>
                            <td>Password</td>
                            <td><a href="ChangePassword">Change password</a></td>
                        </tr>
                        <tr><td></td></tr>

                        <tr>
                            <td>Address</td>
                            <td><input type="text" name="address" value="${acc.address}" /></td>
                        </tr>
                        <tr><td colspan="2"></td></tr>



                        <tr>
                            <td colspan="2"><input id="submit-btn" type="submit" value="Save" /></td>
                        </tr>
                    </table>
                </div>
            </form>

            <div id="avatar">
                <img src="uploads/${acc.avatar}" alt="Avatar" width="250" height="250">
            </div>  
        </div>
    </body>

</html>