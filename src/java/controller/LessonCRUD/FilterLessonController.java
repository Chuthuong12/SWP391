/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.LessonCRUD;

import dao.LessonDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Lesson;

/**
 *
 * @author KDIchigo
 */
@WebServlet(name = "FilterLessonController", urlPatterns = {"/filter-lesson"})
public class FilterLessonController extends HttpServlet {

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
        int subId = (int) request.getSession().getAttribute("subIdForLesson");
        int status = Integer.parseInt(request.getParameter("status"));

        if (status != 2) {
            totalSearch = new LessonDAO().getTotalLesson(keyword, status, subId);
            List<Lesson> listLessonsByKeywordAndPagging = new LessonDAO().getListLessonsByKeywordAndPagging(keyword, page, PAGE_SIZE_6, status, subId);
            int totalPage = totalSearch / PAGE_SIZE_6;
            if (totalSearch % PAGE_SIZE_6 != 0) {
                totalPage += 1;
            }
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.getSession().setAttribute("listLessonBySubId", listLessonsByKeywordAndPagging);
        } else {
            totalSearch = new LessonDAO().getTotalLesson(keyword, subId);
            List<Lesson> listLessonsByKeywordAndPagging = new LessonDAO().getListLessonsByKeywordAndPagging(keyword, page, PAGE_SIZE_6, subId);
            int totalPage = totalSearch / PAGE_SIZE_6;
            if (totalSearch % PAGE_SIZE_6 != 0) {
                totalPage += 1;
            }

            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.getSession().setAttribute("listLessonBySubId", listLessonsByKeywordAndPagging);
        }
        request.setAttribute("status", status);
        request.setAttribute("table_lesson", "lesson");
        request.setAttribute("pagination_url", "SearchUrl?keyword=" + keyword + "&status=" + status + "&");
        request.getRequestDispatcher("SubjectLesson.jsp").forward(request, response);
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
