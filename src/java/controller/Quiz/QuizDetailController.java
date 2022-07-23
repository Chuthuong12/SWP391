/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Quiz;

import dao.LessonDAO;
import dao.QuizDAO;
import dao.SubjectDAO;
import dao.TypeDAO;
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
import model.Subject;
import model.Type;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "QuizDetailController", urlPatterns = {"/quiz-detail"})
public class QuizDetailController extends HttpServlet {

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
            out.println("<title>Servlet QuizDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuizDetailController at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");
        int message = Integer.parseInt(request.getParameter("message"));
//        if (action.equalsIgnoreCase("edit-quiz")) {
//            int quizId = Integer.parseInt(request.getParameter("quizId"));
//            Quiz quizByQuizId = new QuizDAO().getQuizByQuizId(quizId);
//
//            request.setAttribute("quizByQuizId", quizByQuizId);
//        } else if (action.equalsIgnoreCase("add-quiz")) {
//
//        }
        switch (action) {
            case "edit-quiz":
                int quizId = Integer.parseInt(request.getParameter("quizId"));
                Quiz quizByQuizId = new QuizDAO().getQuizByQuizId(quizId);

                request.setAttribute("quizByQuizId", quizByQuizId);
                request.getSession().setAttribute("quizId", quizId);
                request.getSession().setAttribute("action", "edit-quiz");
                break;

            case "add-quiz":
                request.getSession().setAttribute("action", "add-quiz");
                break;
        }

        List<Lesson> listLessons = new LessonDAO().getAllLessons();
        List<Subject> listSubjects = new SubjectDAO().getAllSubjects();
        List<Type> listTypeQuizes = new TypeDAO().getListTypeQuizes();

        request.setAttribute("listLessons", listLessons);
        request.setAttribute("listSubjects", listSubjects);
        request.setAttribute("listTypeQuizes", listTypeQuizes);
        if (message == 0) {
            request.getSession().setAttribute("messageQuiz", "");
        }

        request.getRequestDispatcher("QuizDetail.jsp").forward(request, response);
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
