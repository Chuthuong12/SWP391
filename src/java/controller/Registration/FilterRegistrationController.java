/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Registration;

import dao.SubjectDAO;
import dao.SubjectRegistrationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.RegistrationBuilder;
import model.Subject;

/**
 *
 * @author KDIchigo
 */
@WebServlet(name = "FilterRegistrationController", urlPatterns = {"/filter-registration"})
public class FilterRegistrationController extends HttpServlet {

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
        int subId = Integer.parseInt(request.getParameter("subId"));
        String date_from = request.getParameter("date_from");
        String date_to = request.getParameter("date_to");
        String email = request.getParameter("keyword");

        if (date_from.equalsIgnoreCase("") && date_to.equalsIgnoreCase("")) {
            if (subId == 0) {
                List<RegistrationBuilder> listRegisSearchByEmail = new SubjectRegistrationDAO().getListRegisSearchByEmail(email);

                request.getSession().setAttribute("listRegistration", listRegisSearchByEmail);
            } else {
                List<RegistrationBuilder> listRegisBySubIdAndSearchByEmail = new SubjectRegistrationDAO().getListRegisBySubIdAndSearchByEmail(email, subId);

                request.getSession().setAttribute("listRegistration", listRegisBySubIdAndSearchByEmail);
            }
            request.getSession().setAttribute("messageRegis", "");
        } else if (date_from != null && date_to != null) {
//            String date_from = sdf.format(date_fromStr);
//            String date_to = sdf.format(date_toStr);
            if (subId == 0) {
                List<RegistrationBuilder> listRegisByDateAndSearchByEmail = new SubjectRegistrationDAO().getListRegisByDateAndSearchByEmail(email, date_from, date_to);

                request.getSession().setAttribute("listRegistration", listRegisByDateAndSearchByEmail);
            } else {
                List<RegistrationBuilder> listRegisByDateAndBySubIdAndSearchByEmail = new SubjectRegistrationDAO().getListRegisByDateAndBySubIdAndSearchByEmail(email, subId, date_from, date_to);

                request.getSession().setAttribute("listRegistration", listRegisByDateAndBySubIdAndSearchByEmail);
            }
            request.getSession().setAttribute("messageRegis", "");
        } else if (date_from != null && date_to.equalsIgnoreCase("")) {
            List<RegistrationBuilder> listAllRegistration = new SubjectRegistrationDAO().getAllRegistration();

            request.getSession().setAttribute("messageRegis", "Fill on Date From an Date To");
            request.getSession().setAttribute("listRegistration", listAllRegistration);
        } else if (date_from.equalsIgnoreCase("") && date_to != null) {
            List<RegistrationBuilder> listAllRegistration = new SubjectRegistrationDAO().getAllRegistration();

            request.getSession().setAttribute("messageRegis", "Fill on Date From an Date To");
            request.getSession().setAttribute("listRegistration", listAllRegistration);
        }

        List<Subject> listSubjects = new SubjectDAO().getAllSubjects();

        request.setAttribute("listSubjects", listSubjects);
        request.setAttribute("subId", subId);
        request.setAttribute("date_from", date_from);
        request.setAttribute("date_to", date_to);
        request.setAttribute("email", email);

        request.getRequestDispatcher("RegistrationList.jsp").forward(request, response);
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
