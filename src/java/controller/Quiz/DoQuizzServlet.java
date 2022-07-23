/*
 * Copyright 2022 Fangl
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Fangl
 * which accompanies this distribution, and is available at
 * https://github.com/fanglong-it
 *
 * Contributors:
 *    Fangl - initial API and implementation and/or initial documentation
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
import model.Quiz;
import model.QuizzPoint;

/**
 *
 * @author Fangl
 */
@WebServlet(name = "DoQuizzServlet", urlPatterns = {"/DoQuizzServlet"})
public class DoQuizzServlet extends HttpServlet {

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
            String method = request.getParameter("method");
            if (method.equals("get")) {
                doGet(request, response);
            } else {
                doPost(request, response);
            }
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
            int subId = Integer.parseInt(request.getParameter("subId"));

            HttpSession session = request.getSession();

            QuizDAO quizDAO = new QuizDAO();
            QuestionDAO questionDAO = new QuestionDAO();
            AnswerDAO answerDAO = new AnswerDAO();

            Account account = (Account) session.getAttribute("account");

            if (account == null) {
                url = "Login.jsp";
            } else {

                Quiz quiz = quizDAO.getQuizByQuizId(quizzId);
                QuizzPoint quizzPoint = quizDAO.getQuizPointAttempt(account.getUserid(), quizzId);

                int maxAttemp = quiz.getAttempt(); //get The max attempt of quizz
                int lastAttempt = 0;
                if (quizzPoint == null) { //Have not Attemp

                } else {
                    lastAttempt = quizzPoint.getAttempt(); //get The number user have been Attempt
                }

                boolean check = true;
                if (maxAttemp <= lastAttempt) { //user attempt max
                    check = false;
                }

                if (check) {

                    int questionTime = quizDAO.getQuizByQuizId(quizzId).getDuration();

                    HashMap<Question, List<Answer>> quizz = new HashMap<>();
                    List<Question> questions = questionDAO.getQuestionByQuizId(quizzId);
                    for (Question question : questions) {
                        quizz.put(question, answerDAO.getAnswerByQuestionId(question.getQuestionId()));
                    }
                    session.setMaxInactiveInterval(questionTime * 60);

                    session.setAttribute("TIMER", questionTime);
                    session.setAttribute("DO_QUIZZ", quizz);

                    request.setAttribute("quizzId", quizzId);
//                Quiz quiz = quizDAO.getQuizByQuizId(quizzId);
                    quiz.setTotalQues(quizz.size());
                    request.setAttribute("QUIZZ", quiz);
                    request.setAttribute("attempt", lastAttempt);
                    request.setAttribute("subId", subId);
                    url = "doQuizz.jsp";
                } else {
                    request.setAttribute("WARNING", "You have done this quizz!");
                    url = "lesson-quiz?subId=" + subId + "&action=get";
                }
            }

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
            int correct = 0;
            //
            String[] questionIds = request.getParameterValues("questionId");
            AnswerDAO answerDAO = new AnswerDAO();
            int quizId = Integer.parseInt(request.getParameter("quizzId"));
            int subId = Integer.parseInt(request.getParameter("subId"));

            int attempt = Integer.parseInt(request.getParameter("attempt"));
            attempt++; //Have been do quizz the attempt will increasing
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("account");
            QuizDAO quizDAO = new QuizDAO();
            QuestionDAO questionDAO = new QuestionDAO();

            if (account == null) {
                url = "login.jsp";
            } else {
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
                            int totalCorrectAnswer = answerDAO.getTotalCorrectAnswer(Integer.parseInt(questionId));

                            float pointForMul = pointForEachQuestion / totalCorrectAnswer;
                            int mulCorrect = 0; //1
                            for (String answerId : answerIds) {
                                AnswerDetail answerDetail
                                        = new AnswerDetail(0,
                                                Integer.parseInt(questionId),
                                                Integer.parseInt(answerId),
                                                account.getUserid(),
                                                quizId,
                                                attempt);
                                quizDAO.insertAnswerDetail(answerDetail);
                                if (answerIds.length > totalCorrectAnswer) {

                                } else {
                                    if (answerDAO.checkAnswerIsCorrect(Integer.parseInt(answerId))) {
                                        mulCorrect++;
                                        multipleScore += pointForMul * mulCorrect;
                                    }
                                }

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
                                            attempt);
                            quizDAO.insertAnswerDetail(answerDetail);
                            if (answerDAO.checkAnswerIsCorrect(Integer.parseInt(request.getParameter("answer_" + questionId)))) {
                                correct++;
                            }
                        }
                    }
                }
                float point = pointForEachQuestion * correct;
                point += multipleScore;
                float pointPercent = point * 10;
                float numQuesTrueFloat = (point / 10) * questionIds.length;
                int numQuesTrue = 0;
                if (numQuesTrueFloat % 1 == 0) {
                    numQuesTrue = (int) numQuesTrueFloat;
                } else {
                    numQuesTrue = (int) (numQuesTrueFloat / 1);
                }

//            }
                // 0.2
                QuizzPoint quizzPoint = new QuizzPoint(1, account.getUserid(), point, quizId, attempt);
                if (isDoQuizz) {
                    quizDAO.insertQuizPoint(quizzPoint, pointPercent, numQuesTrue);
                } else {
                    quizDAO.insertQuizPoint(quizzPoint, pointPercent, numQuesTrue);
                }

                request.setAttribute("SCORE", point);
                request.setAttribute("attempt", attempt);
                request.setAttribute("subId", subId);
                request.setAttribute("quizzId", quizId);
                url = "scorePage.jsp";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
