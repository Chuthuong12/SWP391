/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.SliderCRUD;

import dao.SliderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Slider;

/**
 *
 * @author ADMIN
 */
@WebServlet(urlPatterns = {"/hide-slider"})
public class HideSliderController extends HttpServlet {

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
            out.println("<title>Servlet HideShowSliderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HideShowSliderController at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
        final int PAGE_SIZE = 3;
        int page = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }
        int totalSearch = new SliderDAO().getTotalSlider();
        int totalPage = totalSearch / PAGE_SIZE;
        if (totalSearch % PAGE_SIZE != 0) {
            totalPage += 1;
        }

        int sliderId = Integer.parseInt(request.getParameter("id"));
        boolean status = new SliderDAO().getStatusBySliderId(sliderId);
        new SliderDAO().updateSliderHide(sliderId);

        List<Slider> listSliders = new SliderDAO().getAllSliders();
        List<Slider> listSlidersByPagging = new SliderDAO().getListSlidersByPagging(page, PAGE_SIZE);

        request.getSession().setAttribute("listSliders", listSliders);
        request.getSession().setAttribute("listSlidersByPagging", listSlidersByPagging);
        request.getSession().setAttribute("status", status);

        response.sendRedirect("slider-list");
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
//        processRequest(request, response);
        final int PAGE_SIZE = 3;
        int page = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }
        int totalSearch = new SliderDAO().getTotalSlider();
        int totalPage = totalSearch / PAGE_SIZE;
        if (totalSearch % PAGE_SIZE != 0) {
            totalPage += 1;
        }

        int sliderId = Integer.parseInt(request.getParameter("id"));
        boolean status = new SliderDAO().getStatusBySliderId(sliderId);
        new SliderDAO().updateSliderHide(sliderId);

        List<Slider> listSliders = new SliderDAO().getAllSliders();
        List<Slider> listSlidersByPagging = new SliderDAO().getListSlidersByPagging(page, PAGE_SIZE);

        request.getSession().setAttribute("listSliders", listSliders);
        request.getSession().setAttribute("listSlidersByPagging", listSlidersByPagging);
        request.getSession().setAttribute("status", status);

        response.sendRedirect("slider-list");
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
