/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author ADMIN
 */
@WebServlet(name = "SubjectLessonDetailController", urlPatterns = {"/subject-lesson-detail"})
public class SubjectLessonDetailController extends HttpServlet {

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
            out.println("<title>Servlet SubjectLessonDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubjectLessonDetailController at " + request.getContextPath() + "</h1>");
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
        int subId = Integer.parseInt(request.getParameter("subId"));
        final int PAGE_SIZE_6 = 6;
        int page = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }
        int totalSearch = new LessonDAO().getTotalLesson(subId);
        int totalPage = totalSearch / PAGE_SIZE_6;
        if (totalSearch % PAGE_SIZE_6 != 0) {
            totalPage += 1;
        }

//        List<Lesson> listLessons = new LessonDAO().getAllLessons();
        List<Lesson> listLessonsByPagging = new LessonDAO().getListLessonsByPagging(page, PAGE_SIZE_6, subId);
        List<Lesson> listLessonBySubId = new LessonDAO().getListLessonsBySubId(subId);

        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        request.getSession().setAttribute("search_url", "search_lesson");
//        request.getSession().setAttribute("listLessons", listLessons);
        request.getSession().setAttribute("listLessonBySubId", listLessonsByPagging);
        request.setAttribute("pagination_url", "subject-lesson-detail?");
        request.setAttribute("table_lesson", "lesson");
        request.getSession().setAttribute("subIdForLesson", subId);
//        request.getSession().setAttribute("listLessonBySubId", listLessonBySubId);

        request.getRequestDispatcher("SubjectLesson.jsp").forward(request, response);
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
        int subId = Integer.parseInt(request.getParameter("subId"));
        final int PAGE_SIZE_6 = 6;
        int page = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }
        int totalSearch = new LessonDAO().getTotalLesson(subId);
        int totalPage = totalSearch / PAGE_SIZE_6;
        if (totalSearch % PAGE_SIZE_6 != 0) {
            totalPage += 1;
        }

//        List<Lesson> listLessons = new LessonDAO().getAllLessons();
        List<Lesson> listLessonsByPagging = new LessonDAO().getListLessonsByPagging(page, PAGE_SIZE_6, subId);
        List<Lesson> listLessonBySubId = new LessonDAO().getListLessonsBySubId(subId);

        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        request.getSession().setAttribute("search_url", "search_lesson");
//        request.getSession().setAttribute("listLessons", listLessons);
        request.getSession().setAttribute("listLessonBySubId", listLessonsByPagging);
        request.setAttribute("pagination_url", "subject-lesson-detail?");
        request.setAttribute("table_lesson", "lesson");
        request.getSession().setAttribute("subIdForLesson", subId);
//        request.getSession().setAttribute("listLessonBySubId", listLessonBySubId);

        request.getRequestDispatcher("SubjectLesson.jsp").forward(request, response);
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
