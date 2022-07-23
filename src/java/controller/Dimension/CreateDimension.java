/*
 * To change this license header, choose License Headers in Project Properties.
 * To changDe this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Dimension;

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
import model.Type;

/**
 *
 * @author 84969
 */
@WebServlet(name = "CreateDimension", urlPatterns = {"/CreateDimension"})
public class CreateDimension extends HttpServlet {

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
            out.println("<title>Servlet CreateDimension</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateDimension at " + request.getContextPath() + "</h1>");
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
        int subId = Integer.parseInt(request.getParameter("subId"));
        ArrayList<Type> listType = new DimensionDAO().getAllTypeDimension();
        request.setAttribute("listType", listType);
        request.setAttribute("subId", subId);
        request.getRequestDispatcher("CreateDimension.jsp").forward(request, response);
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
        String typeId = request.getParameter("typeId");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        DimensionDAO dimDao = new DimensionDAO();
        Dimension dim = new Dimension(typeId, name, description);
        dimDao.createDimension(dim);
        Dimension dimAdd = dimDao.getDimensionByInfo(name, typeId, description);
        boolean check = dimDao.createDimensionInSubject(subjectId, dimAdd.getDimId());
        String message = "";
        if (check == true) {
            message = "Create dimension successful";
        } else {
            message = "Create dimension failed";
        }

        ArrayList<Dimension> listDimension = new DimensionDAO().getAllDimensionBySubjectId(subjectId);
        ArrayList<PricePackage> listPricePackage = new PricePackageDAO().getAllPricePackageBuSubjectId(subjectId);
        request.setAttribute("listDimension", listDimension);
        request.setAttribute("id", subjectId);
        request.setAttribute("listPricePackage", listPricePackage);
        request.setAttribute("message", message);
        request.getRequestDispatcher("Detail.jsp").forward(request, response);
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
