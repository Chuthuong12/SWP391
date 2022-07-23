/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Practice;

import dao.DimensionDAO;
import dao.PracticeDAO;
import dao.QuizDAO;
import dao.SubjectDAO;
import dao.TopicDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Dimension;
import model.Practice;
import model.Quiz;
import model.Subject;
import model.Topic;

/**
 *
 * @author 84969
 */
@WebServlet(name = "PraticeDetailController", urlPatterns = {"/PraticeDetail"})
public class PraticeDetailController extends HttpServlet {

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
            out.println("<title>Servlet PraticeDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PraticeDetailController at " + request.getContextPath() + "</h1>");
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
        Account acc = (Account) request.getSession().getAttribute("account");
        int userId = acc.getUserid();
        String action = request.getParameter("action");
        List<Subject> listSubjects = new SubjectDAO().getAllSubjects();
        //ArrayList<Quiz> listQuizs = new QuizDAO().getListQuizzesBySubId(subId);
        ArrayList<Quiz> listQuizs = new QuizDAO().getAllQuiz();
        switch (action) {
            case "add":
                request.getSession().setAttribute("action", "add");
                break;
            case "detail":
                int quizId = Integer.parseInt(request.getParameter("quizId"));
                int attempt = Integer.parseInt(request.getParameter("attempt"));
                Practice practice = new PracticeDAO().getPracticeDetail(userId, quizId, attempt);
                //ArrayList<Quiz> listQuizs = new QuizDAO().getListQuizzesBySubId(subId);
                request.getSession().setAttribute("action", "detail");
                request.setAttribute("practice", practice);  
                break;
        }
        
        request.setAttribute("listQuizs", listQuizs);
        request.setAttribute("listSubjects", listSubjects);
        request.getRequestDispatcher("PracticeDetail.jsp").forward(request, response);
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
