/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LessonDAO;
import dao.SliderDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Lesson;
import model.Slider;
import model.Subject;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SearchUrl", urlPatterns = {"/SearchUrl"})
public class SearchUrl extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchUrl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchUrl at " + request.getContextPath() + "</h1>");
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
//        keywordStr = (String) request.getSession().getAttribute("keyword");
//        if (keywordStr != null) {
//            keyword = keywordStr;
//        } 

        if (search.startsWith("search_subject")) {
            totalSearch = new SubjectDAO().getTotalSubject(keyword);
            List<Subject> listSubjectsByKeywordAndPagging = new SubjectDAO().getListSubjectsByKeywordAndPagging(keyword, page, PAGE_SIZE_6);
            int totalPage = totalSearch / PAGE_SIZE_6;
            if (totalSearch % PAGE_SIZE_6 != 0) {
                totalPage += 1;
            }

            request.getSession().setAttribute("listSubjectsByPagging", listSubjectsByKeywordAndPagging);
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("pagination_url", "SearchUrl?keyword=" + keyword + "&");
            request.getRequestDispatcher("SubjectList.jsp").forward(request, response);
        } else if (search.startsWith("search_slider")) {
            int status = (int) request.getSession().getAttribute("status");
            totalSearch = new SliderDAO().getTotalSlider(keyword, status);
            List<Slider> listSlidersByKeywordAndPagging = new SliderDAO().getListSlidersByKeywordAndPagging(keyword, page, PAGE_SIZE_3, status);
            int totalPage = totalSearch / PAGE_SIZE_3;
            if (totalSearch % PAGE_SIZE_3 != 0) {
                totalPage += 1;
            }

            request.getSession().setAttribute("listSlidersByPagging", listSlidersByKeywordAndPagging);
            request.getSession().setAttribute("status", status);
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("pagination_url", "SearchUrl?keyword=" + keyword + "&");
            request.getRequestDispatcher("SliderList.jsp").forward(request, response);
        } else if (search.startsWith("search_lesson")) {
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
            request.getSession().setAttribute("status", status);
            request.setAttribute("table_lesson", "lesson");
            request.setAttribute("pagination_url", "SearchUrl?keyword=" + keyword + "&status=" + status + "&");
            request.getRequestDispatcher("SubjectLesson.jsp").forward(request, response);
        }
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
//        keywordStr = (String) request.getSession().getAttribute("keyword");
//        if (keywordStr != null) {
//            keyword = keywordStr;
//        } 

        if (search.startsWith("search_subject")) {
            totalSearch = new SubjectDAO().getTotalSubject(keyword);
            List<Subject> listSubjectsByKeywordAndPagging = new SubjectDAO().getListSubjectsByKeywordAndPagging(keyword, page, PAGE_SIZE_6);
            int totalPage = totalSearch / PAGE_SIZE_6;
            if (totalSearch % PAGE_SIZE_6 != 0) {
                totalPage += 1;
            }

            request.getSession().setAttribute("listSubjectsByPagging", listSubjectsByKeywordAndPagging);
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("pagination_url", "SearchUrl?keyword=" + keyword + "&");
            request.getRequestDispatcher("SubjectList.jsp").forward(request, response);

        } else if (search.startsWith("search_slider")) {
            int status = Integer.parseInt(request.getParameter("status"));
            totalSearch = new SliderDAO().getTotalSlider(keyword, status);
            List<Slider> listSlidersByKeywordAndPagging = new SliderDAO().getListSlidersByKeywordAndPagging(keyword, page, PAGE_SIZE_3, status);
            int totalPage = totalSearch / PAGE_SIZE_3;
            if (totalSearch % PAGE_SIZE_3 != 0) {
                totalPage += 1;
            }

            request.getSession().setAttribute("listSlidersByPagging", listSlidersByKeywordAndPagging);
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("pagination_url", "SearchUrl?keyword=" + keyword + "&");
            request.getRequestDispatcher("SliderList.jsp").forward(request, response);
        } else if (search.startsWith("search_lesson")) {
            int status = Integer.parseInt(request.getParameter("status"));
            int subId = (int) request.getSession().getAttribute("subIdForLesson");

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

            request.setAttribute("table_lesson", "lesson");
            request.setAttribute("pagination_url", "SearchUrl?keyword=" + keyword + "&status=" + status + "&");
            request.getRequestDispatcher("SubjectLesson.jsp").forward(request, response);
        }

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
