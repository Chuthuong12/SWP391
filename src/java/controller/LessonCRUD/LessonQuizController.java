/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.LessonCRUD;

import dao.LessonDAO;
import dao.QuizDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Lesson;
import model.Quiz;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "LessonQuizController", urlPatterns = {"/lesson-quiz"})
public class LessonQuizController extends HttpServlet {

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
        String action = request.getParameter("action");
        final int PAGE_SIZE = 3;
        int page = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }
        int totalSearch = 0;
        int totalPage = 0;
        int lessonId = 0;
        int subId = Integer.parseInt(request.getParameter("subId"));

        if (action.equalsIgnoreCase("get")) {

            Lesson lessonByLessonId = new LessonDAO().getTop1LessonByLessonId(subId);
            List<Lesson> listLessonBySubId = new LessonDAO().getListLessonsBySubId(subId);
            lessonId = lessonByLessonId.getLessonId();

            QuizDAO quizDAO = new QuizDAO();

            boolean isDoQuizz = false;
            boolean enought = false;

            request.getSession().setAttribute("listLessonBySubId", listLessonBySubId);
            request.getSession().setAttribute("lessonIdScore", lessonId);
            request.getSession().setAttribute("subIdScore", subId);
            request.setAttribute("subId", subId);
            request.setAttribute("lesId", lessonId);
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("lessonByLessonId", lessonByLessonId);

        } else if (action.equalsIgnoreCase("post")) {
            lessonId = Integer.parseInt(request.getParameter("lessonId"));
//            int subId = Integer.parseInt(request.getParameter("subId"));

            Lesson lessonByLessonId = new LessonDAO().getLessonById(lessonId);
            List<Lesson> listLessonBySubId = new LessonDAO().getListLessonsBySubId(subId);

            request.getSession().setAttribute("listLessonBySubId", listLessonBySubId);
            request.getSession().setAttribute("lessonIdScore", lessonId);
            request.getSession().setAttribute("subIdScore", subId);
            request.setAttribute("lesId", lessonId);
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("lessonByLessonId", lessonByLessonId);

        }
        List<Quiz> listQuizByLessonId = new QuizDAO().getListQuizByLessonId(lessonId);
        request.setAttribute("listQuizByLessonId", listQuizByLessonId);
        request.setAttribute("subId", subId);

        request.getRequestDispatcher("LessonQuiz.jsp").forward(request, response);

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
