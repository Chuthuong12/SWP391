/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Year;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thuong
 */
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
//        processRequest(request, response);
//        Cookie arr[] = request.getCookies();
//        if (arr != null) {
//            for (Cookie cookie : arr) {
//                if (cookie.getName().equals("userC")) {
//                    request.setAttribute("username", cookie.getValue());
//                }
//                if (cookie.getName().equals("passC")) {
//                    request.setAttribute("password", cookie.getValue());
//                }
//            }
//        }
//
//        request.getRequestDispatcher("Login.jsp").forward(request, response);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        request.getSession().setAttribute("currentYear", currentYear);
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;
        for (Cookie cooky : cookies) {
            if (cooky.getName().equals("user")) {
                username = cooky.getValue();
            }
            if (cooky.getName().equals("pass")) {
                password = cooky.getValue();
            }
            if (username != null && password != null) {
                break;
            }
        }

        if (username != null && password != null) {
            Account account = new AccountDAO().checkLogin(username, password);
            if (account != null) { //cookie hợp lệ
                request.getSession().setAttribute("account", account);
                if (account.getRole().getRole_name().equalsIgnoreCase("ADMIN")) {
                    request.getSession().setAttribute("currentYear", currentYear);
//                    request.getSession().setAttribute("ChekckLogin", "Login");
                    response.sendRedirect("adminDashboard.jsp");
//                    response.sendRedirect("DashboardServlet?year=" + currentYear);
                } else {
                    response.sendRedirect("home-controller");
                }
                return;
            }
        }
        
        request.getRequestDispatcher("Login.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        String remember = request.getParameter("remember");

        // check username, password
        Account account = new AccountDAO().checkLogin(username, password);
//        int currentYear = Year.now().getValue();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        request.getSession().setAttribute("currentYear", currentYear);
        if (account != null) { //hợp lệ -> lưu lên session
            //remember
            if (remember != null) {
                Cookie usernameCookie = new Cookie("username", username);
                usernameCookie.setMaxAge(60 * 60 * 24 * 2);
                Cookie passwordCookie = new Cookie("password", password);
                passwordCookie.setMaxAge(60 * 60 * 24 * 2);
                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
            }
            request.getSession().setAttribute("account", account);
//            request.getSession().setAttribute("ChekckLogin", "Login");
            if (account.getRole().getRole_name().equalsIgnoreCase("ADMIN")) {
                response.sendRedirect("DashboardServlet?year=" + currentYear);
            } else {
                response.sendRedirect("subject-list");
            }

            //không remember
        } else {//Không hợp lệ -> trả về lỗi
//            request.setAttribute("classAlerts", "alert alert-danger");
//            request.setAttribute("strongAlerts", "Error");
//            request.setAttribute("alerts", "Wrong username or password");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }
}
