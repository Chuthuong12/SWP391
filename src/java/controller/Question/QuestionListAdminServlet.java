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
package controller.Question;

import dao.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Question;

/**
 *
 * @author Fangl
 */
@WebServlet(name = "QuestionListAdminServlet", urlPatterns = {"/QuestionListAdminServlet"})
public class QuestionListAdminServlet extends HttpServlet {

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
            out.println("<title>Servlet QuestionListAdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuestionListAdminServlet at " + request.getContextPath() + "</h1>");
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
            int subId = Integer.parseInt(request.getParameter("subId"));
            int quizId = Integer.parseInt(request.getParameter("quizId"));
            int lessonId = Integer.parseInt(request.getParameter("lessonId"));
            QuestionDAO questionDAO = new QuestionDAO();
            List<Question> questions = questionDAO.getQuestionByQuizId(quizId);
            request.setAttribute("QUESTIONS", questions);
            request.setAttribute("quizId", quizId);
            request.setAttribute("subId", subId);
            request.setAttribute("lessonId", lessonId);

            url = "QuestionListAdmin.jsp";
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
