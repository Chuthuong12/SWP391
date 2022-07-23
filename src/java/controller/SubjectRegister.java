/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BlogDAO;
import dao.SliderDAO;
import dao.SubjectDAO;
import dao.SubjectRegistrationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static java.util.concurrent.ThreadLocalRandom.current;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Blog;
import model.Slider;
import model.Subject;
import model.SubjectRegistration;

/**
 *
 * @author Dell
 */
@WebServlet(name = "SubjectRegister", urlPatterns = {"/subject-resgister"})
public class SubjectRegister extends HttpServlet {

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
        int subId = (int) request.getSession().getAttribute("subId");
        int priceId = Integer.parseInt(request.getParameter("priceId"));
        String regisAction = request.getParameter("regisAction");
//        if (regisAction.equalsIgnoreCase("get")) {
//
//        } else if (regisAction.equalsIgnoreCase("post")) {
        Account account = (Account) request.getSession().getAttribute("account");
        int userId = account.getUserid();
        String statis = "true";
        LocalDate current = java.time.LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String regisDate = current.format(formatter);

        SubjectRegistration sr = new SubjectRegistration(0, regisDate, statis, subId, priceId, userId);
        new SubjectRegistrationDAO().createSubjectRegister(sr);

            response.sendRedirect("lesson-quiz?subId=" + subId + "&action=get");
//        request.getRequestDispatcher("SubjectList.jsp").forward(request, response);
//        }

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
//             request.getRequestDispatcher("SubjectRegister.jsp").forward(request, response);            
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
