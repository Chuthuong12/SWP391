
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

@WebServlet(name = "ChangePasswordController", urlPatterns = {"/ChangePassword"})
public class ChangePasswordController extends HttpServlet {

    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        request.getRequestDispatcher("change-password.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          response.setContentType("text/html;charset=UTF-8");
           request.setCharacterEncoding("utf-8");
       
        AccountDAO accountDao = new AccountDAO();
        Account oldAccount=(Account) request.getSession().getAttribute("account");
        int userid = oldAccount.getUserid();
        String newPassword = request.getParameter("newPassword");
        accountDao.changePassword(userid, newPassword);
        String isNoti = "yes";
        request.setAttribute("isNoti", isNoti);
        
        oldAccount.setPassword(newPassword);
        request.getSession().setAttribute("account", oldAccount);
        //request.getRequestDispatcher("home.jsp").forward(request, response);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
