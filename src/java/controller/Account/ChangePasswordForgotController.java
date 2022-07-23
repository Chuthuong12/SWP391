/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author thuong
 */
@WebServlet(name = "ChangePasswordForgotController", urlPatterns = {"/ChangePasswordForgot"})
public class ChangePasswordForgotController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String token = request.getParameter("txtToken");
        HttpSession session = request.getSession();
        String checkTokenExist = (String) session.getAttribute(token);
        if (checkTokenExist == null) {
            request.setAttribute("WARNING", "The Code is not Valid");
            request.getRequestDispatcher("reset-password.jsp").forward(request, response);
        } else {

            if (token == null) {
                request.setAttribute("WARNING", "You can't perform this action");
                request.getRequestDispatcher("change-password-forgot.jsp").forward(request, response);
            } else {
//           request.setCharacterEncoding("utf-8");
                AccountDAO accountDAO = new AccountDAO();
                Account account = accountDAO.getAccountWithPasswordToken(token);
                if (account != null) {
                    request.setAttribute("ACCOUNT", account);
                    request.setAttribute("token", token);
                    request.getRequestDispatcher("change-password-forgot.jsp").forward(request, response);
                } else {
                    request.setAttribute("WARNING", "You can't perform this action");
                    request.getRequestDispatcher("change-password-forgot.jsp").forward(request, response);
                }

            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String token = request.getParameter("txtToken");
        String newPass = request.getParameter("new-password");
        int userId = Integer.parseInt(request.getParameter("txtUserId"));

        AccountDAO accountDAO = new AccountDAO();
        boolean isChange = accountDAO.updateNewPasswordForgoted("" + userId, newPass);
        if (isChange) {
            request.setAttribute("WARNING", "Password has been changed succesfully!");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else {
            request.setAttribute("WARNING", "Something is wrong!");
            request.getRequestDispatcher("change-password-forgot.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
