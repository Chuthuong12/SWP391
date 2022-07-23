/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Account;

import Base.Base;
import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.SendEmail;
import utils.generator;

/**
 *
 * @author thuong
 */
@WebServlet(name = "ResetPassworldController", urlPatterns = {"/reset"})
public class ResetPassworldController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("reset-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");

        boolean isExistAccount = new AccountDAO().isExistAccount(email);

        if (!isExistAccount) {
            request.setAttribute("error", "Email is not exist. Please try again!");
        } else {
            String token = generator.generateRandomToken(45);

            boolean updatePasswordToken = new AccountDAO().updatePasswordTokenWithEmail(email, token);
            if (updatePasswordToken) {

                String subject = "Change your password";
                String message = "<!DOCTYPE html>\n"
                        + "<html lang=\"en\">\n"
                        + "<head></head>\n"
                        + "<body style=\"color:#000;\">\n"
                        + "    <h3>Quiz Pracite system</h3>\n"
                        + "    <p>Please click here to change your password| The Code Valid in 5 Minutes</p>\n"
                        //                        + "    <form id=\"myForm\" method=\"POST\" action=" + Base.LINK_CHANGE_PASSWORD + ">\n"
                        //                        + "        <input type=\"hidden\" value=" + email + " id=\"email\" name=\"email\">\n"
                        //                        + "        <input type=\"submit\" value=\"Change password\" \n"
                        //                        + "            style=\"padding: 10px 15px;color: #fff;background-color: rgb(0, 149, 255);border-radius: 10px;border:none\">\n"
                        //                        + "    </form>\n"
                        + "<a href=\"http://localhost:8080/SWP391/ChangePasswordForgot?txtToken=" + token + "\""
                        + "style=\"padding: 10px 15px;color: #fff;background-color: rgb(0, 149, 255);border-radius: 10px;border:none\">Change Password</a>"
                        + "    <h4>Thank you very much</h4>\n"
                        + "</body>\n"
                        + "</html>";

                SendEmail.sendMail(email, subject, message, Base.USERNAME_EMAIL, Base.PASSWORD_EMAIL);
//                new AccountDAO().updateDateModify(email);
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(60*1); // 5p
                session.setAttribute(token, "isExist");
                request.setAttribute("success", "Change password link has been sent to your email");
            }
        }

        request.getRequestDispatcher("reset-password.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
