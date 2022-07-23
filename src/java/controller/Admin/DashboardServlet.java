/*
 * Copyright 2022 Fangl
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Fangl
 * which accompanies this distribution, and is available at
 * https://github.com/fanglong-it
 *
 * Contributors:
 *    Fangl - initial API and implementation and/or initial documentation
 */
package controller.Admin;

import dao.AccountDAO;
import dao.RegistrationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.RegistrationBuilder;
import model.RegistrationDTO;

/**
 *
 * @author Fangl
 */
@WebServlet(name = "DashboardServlet", urlPatterns = {"/DashboardServlet"})
public class DashboardServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DashboardServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashboardServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String url = "";
        try {
            RegistrationDAO registrationDAO = new RegistrationDAO();
//            int currentYear = (int) request.getSession().getAttribute("currentYear");
            List<RegistrationBuilder> listYear = registrationDAO.getListYear();
            int year = Integer.parseInt(request.getParameter("year"));
            int currentYear= (int) request.getSession().getAttribute("currentYear");
//            if(year != currentYear){
//                year = current 
//            }
            //Count the Price for Admin
            
            request.setAttribute("TOTAL_MONTH", registrationDAO.getTotalMonthPrice(currentYear));
            request.setAttribute("TOTAL_ALL", registrationDAO.getTotalPrice());
            int month1 = 1, month2 = 2, month3 = 3, month4 = 4, month5 = 5, month6 = 6, month7 = 7, month8 = 8, month9 = 9, month10 = 10, month11 = 11, month12 = 12;
            int totalAccount = new AccountDAO().getTotalAccount();
            
            request.setAttribute("totalAccount", totalAccount);
            
            request.setAttribute("TOTAL_JANUARY", registrationDAO.getTotalPriceEachMonth(month1, year));
            request.setAttribute("TOTAL_FEBRUARY", registrationDAO.getTotalPriceEachMonth(month2, year));
            request.setAttribute("TOTAL_MARCH", registrationDAO.getTotalPriceEachMonth(month3, year));
            request.setAttribute("TOTAL_APRIL", registrationDAO.getTotalPriceEachMonth(month4, year));
            request.setAttribute("TOTAL_MAY", registrationDAO.getTotalPriceEachMonth(month5, year));
            request.setAttribute("TOTAL_JUNE", registrationDAO.getTotalPriceEachMonth(month6, year));
            request.setAttribute("TOTAL_JULY", registrationDAO.getTotalPriceEachMonth(month7, year));
            request.setAttribute("TOTAL_AUGUST", registrationDAO.getTotalPriceEachMonth(month8, year));
            request.setAttribute("TOTAL_SEPTEMBER", registrationDAO.getTotalPriceEachMonth(month9, year));
            request.setAttribute("TOTAL_OCTOBER", registrationDAO.getTotalPriceEachMonth(month10, year));
            request.setAttribute("TOTAL_NOVEMBER", registrationDAO.getTotalPriceEachMonth(month11, year));
            request.setAttribute("TOTAL_DECEMBER", registrationDAO.getTotalPriceEachMonth(month12, year));
            
            request.setAttribute("listYear", listYear);
            request.setAttribute("yearTable", year);
//            int totalPriceJune = registrationDAO.getTotalPriceEachMonth(moth6);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
        }
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
