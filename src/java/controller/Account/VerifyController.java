/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Account;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author thuong
 */
@WebServlet(name = "VerifyController", urlPatterns = {"/Verify"})
public class VerifyController extends HttpServlet {

 
    
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       AccountDAO accountDao = new AccountDAO();
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String re_pass = request.getParameter("repass");
        String email = request.getParameter("email");
       
        
        accountDao.singup(user, pass, email);
   
        
        int userid = accountDao.getAccountIdByEmail(email);
        accountDao.insertPassword(userid, pass);
        //passwordDao.insertPassword(accountId, password);
        
        Account account = accountDao.checkLogin(email, pass);
        
        request.getSession().setAttribute("account", account);

        response.sendRedirect("home");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
