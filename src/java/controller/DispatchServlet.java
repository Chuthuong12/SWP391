/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 84969
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

    private final String SUBJECT_CONTROLLER = "subject-list";
    private final String CREATE_FORM_CONTROLLER = "CreateFormController";
    private final String FILTER_SUBJECT_STATUS = "FilterStatusController";
    private final String EDIT_SUBJECT_CONTROLLER = "SubjectDetailAdminController";
    private final String DELETE_DIMENSION_CONTROLLER = "DeleteDimension";
    private final String EDIT_DIMENSION_CONTROLLER = "EditDimension";
    private final String CREATE_DIMENSION_CONTROLLER = "CreateDimension";
    private final String ADD_PACKAGEPRICE_CONTROLLER = "AddPackageInSubject";
    private final String DELETE_PACKAGEPRICE_CONTROLLER = "DeletePackageInSubject";
    private final String EDIT_PACKAGEPRICE_CONTROLLER = "EditPackageInSubject";
    private final String FILTER_QUESTION_CONTROLLER = "FilterQuestion";
     private final String FILTER_USER_CONTROLLER = "FilterUser";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String button = request.getParameter("btAction");
        String url = "Home.jsp";
        try {
            if (button.equals("Subject")) {
                String index = request.getParameter("i");
                if (index == null) {
                    url = SUBJECT_CONTROLLER;
                } else {
                    url = SUBJECT_CONTROLLER + "?i=" + index;
                }
            } else if (button.equals("CreateForm")) {
                url = CREATE_FORM_CONTROLLER;
            } else if (button.equals("Filter")) {
                url = FILTER_SUBJECT_STATUS;
            } else if (button.equals("EditSubject")) {
                String id = request.getParameter("id");
                url = EDIT_SUBJECT_CONTROLLER + "?id=" + id;
            } else if (button.equals("DeleteDimension")) {
                String id = request.getParameter("id");
                String subId = request.getParameter("subId");
                url = DELETE_DIMENSION_CONTROLLER + "?id=" + id + "&subId=" + subId;
            } else if (button.equals("EditDimension")) {
                String id = request.getParameter("id");
                String subId = request.getParameter("subId");
                url = EDIT_DIMENSION_CONTROLLER + "?id=" + id + "&subId=" + subId;
            } else if (button.equals("CreateDimension")) {
                String subId = request.getParameter("subId");
                System.out.println("sub iD in dipatch " + subId);
                url = CREATE_DIMENSION_CONTROLLER + "?subId=" + subId;
            } else if (button.equals("CreatePackagePrice")) {
                String subId = request.getParameter("subId");
                url = ADD_PACKAGEPRICE_CONTROLLER + "?subId=" + subId;
            } else if (button.equals("DeletePackagePrice")) {
                String pid = request.getParameter("pid");
                String subId = request.getParameter("subId");
                url = DELETE_PACKAGEPRICE_CONTROLLER + "?pid=" + pid + "&subId=" + subId;
            } else if (button.equals("EditPackagePrice")) {
                String pid = request.getParameter("pid");
                String subId = request.getParameter("subId");
                url = EDIT_PACKAGEPRICE_CONTROLLER + "?pid=" + pid + "&subId=" + subId;
            } else if (button.equals("FilterQuestion")) {
                int statusFilter = Integer.parseInt(request.getParameter("statusFilter"));
                int subjectIdFilter = Integer.parseInt(request.getParameter("subjectIdFilter"));
                int lessonIdFilter = Integer.parseInt(request.getParameter("lessonIdFilter"));
                int dimensionIdFilter = Integer.parseInt(request.getParameter("dimensionIdFilter"));
                url = FILTER_QUESTION_CONTROLLER + "?statusFilter=" + statusFilter + "&subjectIdFilter=" + subjectIdFilter + "&lessonIdFilter=" + lessonIdFilter + "&dimensionIdFilter=" + dimensionIdFilter;

            } else if (button.equals("FilterUser")) {
                int statusFilter = Integer.parseInt(request.getParameter("statusFilter"));
                int roleIdFilter = Integer.parseInt(request.getParameter("roleIdFilter"));
                int genderFilter = Integer.parseInt(request.getParameter("genderFilter"));
                url = FILTER_USER_CONTROLLER + "?statusFilter=" + statusFilter + "&roleIdFilter=" + roleIdFilter + "&genderFilter=" + genderFilter;

            }

        } catch (Exception ex) {
            log("Error at " + this.getServletName() + ": " + ex.getMessage());
        }
        request.getRequestDispatcher(url).forward(request, response);
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
