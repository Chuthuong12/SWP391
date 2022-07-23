/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Quiz;

import dao.AnswerDAO;
import dao.QuestionDAO;
import dao.QuizDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Answer;
import model.AnswerDetail;
import model.Question;

/**
 *
 * @author Dell
 */
@WebServlet(name = "StimulationExam", urlPatterns = {"/simulation-exam"})
public class SimulationExam extends HttpServlet {

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
            out.println("<title>Servlet SimulationExam</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SimulationExam at " + request.getContextPath() + "</h1>");
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
        String url = "";
        try {
            int quizzId = Integer.parseInt(request.getParameter("quizzId"));
            HttpSession session = request.getSession();

            QuizDAO quizDAO = new QuizDAO();
            QuestionDAO questionDAO = new QuestionDAO();
            AnswerDAO answerDAO = new AnswerDAO();

            int questionTime = quizDAO.getQuizByQuizId(quizzId).getDuration();

            HashMap<Question, List<Answer>> quizz = (HashMap<Question, List<Answer>>) session.getAttribute("DO_QUIZZ");
            if (quizz == null) {
                quizz = new HashMap<>();
                List<Question> questions = questionDAO.getQuestionByQuizId(quizzId);
                for (Question question : questions) {
                    quizz.put(question, answerDAO.getAnswerByQuestionId(question.getQuestionId()));
                }
                session.setMaxInactiveInterval(questionTime * 60);

                session.setAttribute("TIMER", questionTime);
                session.setAttribute("DO_QUIZZ", quizz);
            } else {
                //Count time for the Minutes (Update);
                session.setAttribute("TIMER", questionTime);
                session.setAttribute("DO_QUIZZ", quizz);
            }
            request.setAttribute("quizzId", quizzId);
            url = "SimulationExam.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
        String url = "";
        try {
            float correct = 0;
            //
            String[] questionIds = request.getParameterValues("questionId");
            AnswerDAO answerDAO = new AnswerDAO();
            int quizId = Integer.parseInt(request.getParameter("quizzId"));
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("account");
            QuizDAO quizDAO = new QuizDAO();
            QuestionDAO questionDAO = new QuestionDAO();

//            if (account == null) {
//
//            } else {
            //Check question ID is mutiple
//            if (isMutipleChoice) {
            // quiz 1 (0.2)
            // A B C D E // C D // 2 answer// [C] =>  C. (0.2/2)  
//            } else {
            boolean isDoQuizz = quizDAO.isDoQuizz(account.getUserid(), quizId);
//                quizDAO.deleteAnswerDetail(account.getUserid(), quizId);

            float pointForEachQuestion = (float) 10 / questionIds.length;

            float multipleScore = 0;

            for (String questionId : questionIds) {
                //Check qquestion is Multiple choice;
                if (questionDAO.getQuestionById(Integer.parseInt(questionId)).isIsMultipleChoice()) {
                    if (request.getParameter("answer_" + questionId) == null) {

                    } else {
                        String[] answerIds = request.getParameterValues("answer_" + questionId);
                        float pointForMul = pointForEachQuestion / answerDAO.getTotalCorrectAnswer(Integer.parseInt(questionId));
                        int mulCorrect = 0;
                        for (String answerId : answerIds) {
                            AnswerDetail answerDetail
                                    = new AnswerDetail(0,
                                            Integer.parseInt(questionId),
                                            Integer.parseInt(answerId),
                                            account.getUserid(),
                                            quizId,
                                            1);
                            quizDAO.insertAnswerDetail(answerDetail);
                            if (answerDAO.checkAnswerIsCorrect(Integer.parseInt(answerId))) {
                                mulCorrect++;
                            }
                            multipleScore += pointForMul * mulCorrect;
                        }
                    }

                } else {//Not Multiple Choice

                    if (request.getParameter("answer_" + questionId) == null) {

                    } else {
                        //insert the answerDetail;
                        AnswerDetail answerDetail
                                = new AnswerDetail(0,
                                        Integer.parseInt(questionId),
                                        Integer.parseInt(request.getParameter("answer_" + questionId)),
                                        account.getUserid(),
                                        quizId,
                                        1);
                        quizDAO.insertAnswerDetail(answerDetail);
                        if (answerDAO.checkAnswerIsCorrect(Integer.parseInt(request.getParameter("answer_" + questionId)))) {
                            correct++;
                        }
                    }
                }
            }
            float point = pointForEachQuestion * correct;
            point += multipleScore;
//            }
            // 0.2
            request.setAttribute("SCORE", point);
            request.setAttribute("quizzId", quizId);
//            }
            url = "scorePage.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher("scorePage.jsp").forward(request, response);
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
