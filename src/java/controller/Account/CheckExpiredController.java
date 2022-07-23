/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Account;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thuong
 */
@WebServlet(name = "CheckExpiredController", urlPatterns = {"/check-expired"})
public class CheckExpiredController extends HttpServlet {
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        Timestamp timeNow = new Timestamp(System.currentTimeMillis());
        Timestamp timeModify = new AccountDAO().getTimeModify(email);
        
        // link change password expired 1 minute
        if(timeNow.getTime() - timeModify.getTime() >= 60000) {
            response.sendRedirect("404");
        }
        else {
            int userId = new AccountDAO().getAccountIdByEmail(email);
            request.getSession().setAttribute("account", userId);
            response.sendRedirect("ChangePasswordForgot");
        }
        
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
