/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Subject;

import dao.CategoryDAO;
import dao.DimensionDAO;
import dao.PricePackageDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Dimension;
import model.PricePackage;
import model.Subject;

/**
 *
 * @author 84969
 */
@WebServlet(name = "SubjectDetailAdminController", urlPatterns = {"/SubjectDetailAdminController"})
public class SubjectDetailAdminController extends HttpServlet {

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
        int subjectId = Integer.parseInt(request.getParameter("id"));
        CategoryDAO categoryDao = new CategoryDAO();
        ArrayList<Category> listCategories = categoryDao.getAllCategories();
        ArrayList<Dimension> listDimension = new DimensionDAO().getAllDimensionBySubjectId(subjectId);
        ArrayList<PricePackage> listPricePackage = new PricePackageDAO().getAllPricePackageBuSubjectId(subjectId);
        Subject getSubjectBySubId = new SubjectDAO().getSubjectById(subjectId);
        System.out.println("lit dim " + listDimension.size());
        request.setAttribute("id", subjectId);
        request.getSession().setAttribute("subId", subjectId);
        request.setAttribute("listDimension", listDimension);
        System.out.println("list dim in ctl" + listDimension.size());
        request.setAttribute("listPricePackage", listPricePackage);
        request.setAttribute("listCategories", listCategories);
        request.getSession().setAttribute("SubjectBySubId", getSubjectBySubId);
        request.getRequestDispatcher("Detail.jsp").forward(request, response);

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
