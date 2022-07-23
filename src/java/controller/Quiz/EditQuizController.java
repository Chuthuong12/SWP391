/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Quiz;

import dao.QuestionDAO;
import dao.QuizDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Quiz;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "EditQuizController", urlPatterns = {"/edit-quiz"})
public class EditQuizController extends HttpServlet {

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
            out.println("<title>Servlet EditQuizController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditQuizController at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
        int quizId = (int) request.getSession().getAttribute("quizId");
        String title = request.getParameter("title");
        int subId = Integer.parseInt(request.getParameter("subId"));
        String description = request.getParameter("description");
        String level = request.getParameter("level");
        int durarion = Integer.parseInt(request.getParameter("duration"));
        Float rate = Float.parseFloat(request.getParameter("rate"));
        String typeId = request.getParameter("typeId");
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        int totalQues = Integer.parseInt(request.getParameter("totalQues"));
        int attempt = Integer.parseInt(request.getParameter("attempt"));
//        int totalQues = Integer.parseInt(request.getParameter("totalQues"));

        Quiz quizByQuizId = new QuizDAO().getQuizByQuizId(quizId);
        boolean checkExistDoQuiz = new QuizDAO().checkExistDoQuiz(quizId);
        int totalQuesExist = new QuestionDAO().getTotalQuestionExist(quizId);

        if (quizByQuizId.getTitle().equalsIgnoreCase(title) && quizByQuizId.getSubId() == subId && quizByQuizId.getDescription().equalsIgnoreCase(description)
                && quizByQuizId.getLevel().equalsIgnoreCase(level) && quizByQuizId.getDuration() == durarion && quizByQuizId.getRate() == rate
                && quizByQuizId.getTypeId().equalsIgnoreCase(typeId) && quizByQuizId.getLessonId() == lessonId) {
            if (totalQues > 30) {
                request.getSession().setAttribute("messageQuiz", "Total Question must no more 30!!!");
                request.getSession().setAttribute("CheckEditQuiz", "false");
                response.sendRedirect("quiz-detail?quizId=" + quizId + "&action=edit-quiz&message=1");
            } else if (totalQuesExist > totalQues) {
                request.getSession().setAttribute("messageQuiz", "Total Question must more than " + totalQuesExist + "!!!");
                request.getSession().setAttribute("CheckEditQuiz", "false");
                response.sendRedirect("quiz-detail?quizId=" + quizId + "&action=edit-quiz&message=1");
            } else if (totalQues < 30 && totalQuesExist < totalQues) {
                new QuizDAO().updateQuiz(title, subId, description, durarion, level, rate, typeId, quizId, lessonId, totalQues, attempt);
                request.getSession().setAttribute("CheckEditQuiz", "true");
                request.getSession().setAttribute("messageQuiz", "Edit Successful!!!");
                response.sendRedirect("quiz-detail?quizId=" + quizId + "&action=edit-quiz&message=1");
            }
        } else {
            request.getSession().setAttribute("messageQuiz", "Edit Error: You can only change Total Ques!!!");
            request.getSession().setAttribute("CheckEditQuiz", "false");
            response.sendRedirect("quiz-detail?quizId=" + quizId + "&action=edit-quiz&message=1");
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
