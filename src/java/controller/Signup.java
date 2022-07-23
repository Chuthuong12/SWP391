/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import Base.Base;
import model.SendEmail;
import model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thuong
 */
public class Signup extends HttpServlet {

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
        request.getRequestDispatcher("Signup.jsp").forward(request, response);
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
        String user = request.getParameter("username");
        String pass = request.getParameter("pass");
        String re_pass = request.getParameter("repass");
        String email = request.getParameter("email");

        if (!pass.equals(re_pass)) {
            response.sendRedirect("Login.jsp");
        } else {
            AccountDAO dao = new AccountDAO();
            Account ac = dao.CheckAccountExit(user);
            if (ac == null) {
                // dc tao tk
                // dao.singup(user, pass, email, 0, avatar, address, gender, avatar);
                String subject = "Verify your account";
                String message = "String message = \"<!DOCTYPE html>\\n\"\n"
                    + "<html lang=\"en\">\n"
                    + "<head></head>\n"
                    + "<body style=\"color:#000;\">\n"
                    + "    <h3>Welcome to join </h3>\n"
                    + "    <p>Please click here to verify your account</p>\n"
                    + "    \n"
                    + "    <form id=\"myForm\" method=\"POST\" action=" + Base.LINK_VERIFY + ">\n"
                    + "        <input type=\"hidden\" value=" + email + " id=\"email\" name=\"email\">\n"
                    + "        <input type=\"text\" value=" + user + " id=\"email\" name=\"user\">\n"
                    + "        <input type=\"text\" value=" + pass + " id=\"password\" name=\"pass\">\n"
                    + "        <input type=\"submit\" value=\"Verify\" \n"
                    + "            style=\"padding: 10px 15px;color: #fff;background-color: rgb(0, 149, 255);border-radius: 10px;border:none\"\n"
                    + "        >\n"
                    + "    </form>\n"
                    + "\n"
                    + "    <h4>Thank you very much</h4>\n"
                    + "</body>\n"
                    + "</html>";

                SendEmail.sendMail("Thuongcmhe153150@fpt.edu.vn", subject, message, Base.USERNAME_EMAIL, Base.PASSWORD_EMAIL);
                request.setAttribute("success", "Verification link has been sent to your email");
                System.out.println("user: " + user);
               // new AccountDAO().singup(user, pass, email);
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else {
                // day ve login
                response.sendRedirect("Signup.jsp");

            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
