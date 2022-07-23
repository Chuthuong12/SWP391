/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.PackagePrice;

import dao.DimensionDAO;
import dao.PricePackageDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Dimension;
import model.PricePackage;

/**
 *
 * @author 84969
 */
@WebServlet(name = "AddPackageInSubject", urlPatterns = {"/AddPackageInSubject"})
public class AddPackageInSubject extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddPackageInSubject</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddPackageInSubject at " + request.getContextPath() + "</h1>");
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
        PricePackageDAO packageDao = new PricePackageDAO();
        ArrayList<PricePackage> listPricePackage = packageDao.getAllPricePackage();
        int subId = Integer.parseInt(request.getParameter("subId"));
        request.setAttribute("listPricePackage", listPricePackage);
        request.setAttribute("subId", subId);
        request.getRequestDispatcher("AddPackagePriceInSubject.jsp").forward(request, response);
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
        int subjectId = (int) request.getSession().getAttribute("subId");
        int packageId = Integer.parseInt(request.getParameter("packagePrice"));
        PricePackageDAO packageDao = new PricePackageDAO();
        PricePackage packageCheck = packageDao.getPricePackageByIdInSubject(packageId, subjectId);
        String message = "";

        if (packageCheck != null) {
            message = "This package price is existed.";
        } else {
            boolean check = packageDao.AddPackageInSubject(packageId, subjectId);
            if (check == true) {
                message = "Add package price successful";
            } else {
                message = "Add package price failed";
            }

        }
        ArrayList<Dimension> listDimension = new DimensionDAO().getAllDimensionBySubjectId(subjectId);
            ArrayList<PricePackage> listPricePackage = new PricePackageDAO().getAllPricePackageBuSubjectId(subjectId);
            request.setAttribute("listDimension", listDimension);
            System.out.println("list dim in ctl" + listDimension.size());
            request.setAttribute("listPricePackage", listPricePackage);
            request.setAttribute("id", subjectId);
        request.getSession().setAttribute("message", message);
        response.sendRedirect("DispatchServlet?btAction=EditSubject&id=" + subjectId);
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
