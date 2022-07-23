/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.User;

import dao.AccountDAO;
import dao.RoleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author 84969
 */
@WebServlet(name = "FilterUserController", urlPatterns = {"/FilterUser"})
public class FilterUserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
//            HttpSession session = request.getSession();           
//            Account a = (Account) session.getAttribute("account");
            int status = Integer.parseInt(request.getParameter("statusFilter"));
            int roleIdFilter = Integer.parseInt(request.getParameter("roleIdFilter"));
            int genderFilter = Integer.parseInt(request.getParameter("genderFilter"));
            if (request.getParameter("statusFilter") != null) {
                ArrayList<Account> listUser = new AccountDAO().getAccountByStatus(status, genderFilter, roleIdFilter);
                if (!listUser.isEmpty()) {
                    request.setAttribute("listUser", listUser);
                } else {
                    request.setAttribute("message", "Not have user!");
                }
            }
            request.setAttribute("status", status);
            request.setAttribute("listRole", new RoleDAO().getAllRole());
            request.setAttribute("roleId", roleIdFilter);
            request.setAttribute("gender", genderFilter);

            request.getRequestDispatcher("UserList.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
