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
@WebServlet(name = "SearchSliderController", urlPatterns = {"/search-slider"})
public class SearchSliderController extends HttpServlet {

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
            out.println("<title>Servlet SearchSliderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchSliderController at " + request.getContextPath() + "</h1>");
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
        String search = (String) request.getSession().getAttribute("search_url");
        String keyword = (String) request.getSession().getAttribute("keyword");
        request.getSession().setAttribute("keyword", keyword);
        final int PAGE_SIZE_6 = 6;
        final int PAGE_SIZE_3 = 3;
        int page = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }
        int totalSearch = 0;
        String keywordStr;
//        int status = Integer.parseInt(request.getParameter("status"));
        int status = (int) request.getSession().getAttribute("status");
        totalSearch = new SliderDAO().getTotalSlider(keyword, status);
        List<Slider> listSlidersByKeywordAndPagging = new SliderDAO().getListSliderByKeywordAndPagging(keyword, page, PAGE_SIZE_3, status);
        int totalPage = totalSearch / PAGE_SIZE_3;
        if (totalSearch % PAGE_SIZE_3 != 0) {
            totalPage += 1;
        }

        request.getSession().setAttribute("listSlidersByPagging", listSlidersByKeywordAndPagging);
        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pagination_url", "search-slider?keyword=" + keyword + "&");
        request.getRequestDispatcher("SliderJSP/SliderList.jsp").forward(request, response);
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
        String search = (String) request.getSession().getAttribute("search_url");
        String keyword = request.getParameter("keyword");
        request.getSession().setAttribute("keyword", keyword);
        final int PAGE_SIZE_6 = 6;
        final int PAGE_SIZE_3 = 3;
        int page = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }
        int totalSearch = 0;
        String keywordStr;
        String statusStr = request.getParameter("status");
        int status = Integer.parseInt(statusStr);
        totalSearch = new SliderDAO().getTotalSlider(keyword, status);
        List<Slider> listSlidersByKeywordAndPagging = new SliderDAO().getListSliderByKeywordAndPagging(keyword, page, PAGE_SIZE_3, status);
        int totalPage = totalSearch / PAGE_SIZE_3;
        if (totalSearch % PAGE_SIZE_3 != 0) {
            totalPage += 1;
        }

        request.getSession().setAttribute("listSlidersByPagging", listSlidersByKeywordAndPagging);
        request.setAttribute("page", page);
        request.getSession().setAttribute("status", status);
        request.setAttribute("statusStr", statusStr);
        request.setAttribute("keyword", keyword);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pagination_url", "search-slider?keyword=" + keyword + "&");

        request.getRequestDispatcher("SliderJSP/SliderList.jsp").forward(request, response);

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
