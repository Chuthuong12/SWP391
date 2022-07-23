/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Dimension;

import dao.DimensionDAO;
import dao.PricePackageDAO;
import dao.SubjectRegistrationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Dimension;
import model.PricePackage;
import model.SubjectRegistration;

/**
 *
 * @author 84969
 */
@WebServlet(name = "DeleteDimension", urlPatterns = {"/DeleteDimension"})
public class DeleteDimension extends HttpServlet {

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
//            Account u = (Account) session.getAttribute("account");
//            if (u != null && u.getRoleid() == 1) {
           int id = Integer.parseInt(request.getParameter("id"));
            int subjectId = (int) request.getSession().getAttribute("subId");
            DimensionDAO dimDao = new DimensionDAO();
            Dimension dimCheck = dimDao.getDimensionById(id);
            List<SubjectRegistration> regisCheck = new SubjectRegistrationDAO().getRegistrationByDimension(id, subjectId);
            String message = "";
            if (regisCheck != null) {
                message = "Can't delete because had registration in dimension";
            } else {
                if (dimCheck != null) {
                    if (dimDao.deleteDimensionInSubject(id, subjectId) == true) {
                        message = "Delete successful";
                    } else {
                        message = "Delete failed";
                    }
                } else {
                    message = "Can not delete dimension not existed in history";
                }
            }
            ArrayList<Dimension> listDimension = new DimensionDAO().getAllDimensionBySubjectId(subjectId);
            ArrayList<PricePackage> listPricePackage = new PricePackageDAO().getAllPricePackageBuSubjectId(subjectId);
            request.setAttribute("listDimension", listDimension);
            request.setAttribute("id", subjectId);
            System.out.println("list dim in ctl" + listDimension.size());
            request.setAttribute("listPricePackage", listPricePackage);
            request.setAttribute("message", message);
            request.getRequestDispatcher("Detail.jsp").forward(request, response);
//                else {
//                request.setAttribute("message", "Login first.");
//                request.getRequestDispatcher("login.jsp").forward(request, response);
//            }
        } catch (Exception e) {
            log("ERROR at DeleteServlet: " + e.getMessage());
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
