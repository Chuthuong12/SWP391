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
@WebServlet(name = "DeletePackageInSubject", urlPatterns = {"/DeletePackageInSubject"})
public class DeletePackageInSubject extends HttpServlet {

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
            int pid = Integer.parseInt(request.getParameter("pid"));
            int subjectId = (int) request.getSession().getAttribute("subId");
            PricePackageDAO packDao = new PricePackageDAO();
            PricePackage packCheck = packDao.getPricePackageById(pid);
            String message = "";
            if (packCheck != null) {
                if (packDao.deletePackageInSubject(pid,subjectId) == true) {
                    message = "Delete price package successful";
                } else {
                    message = "Delete price package failed";
                }
            } else {
                message = "Can not delete price package not existed in history";
            }
            ArrayList<Dimension> listDimension = new DimensionDAO().getAllDimensionBySubjectId(subjectId);
        ArrayList<PricePackage> listPricePackage = new PricePackageDAO().getAllPricePackageBuSubjectId(subjectId);
        request.setAttribute("listDimension", listDimension);
        request.setAttribute("id", subjectId);
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
