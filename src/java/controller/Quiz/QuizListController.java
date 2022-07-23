/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Quiz;

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
import model.Quiz;
import model.Type;
import model.Subject;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "QuizListController", urlPatterns = {"/quiz-list"})
public class QuizListController extends HttpServlet {

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
            out.println("<title>Servlet QuizListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuizListController at " + request.getContextPath() + "</h1>");
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
        final int PAGE_SIZE_6 = 6;
        int page = 1;
        String pageStr = request.getParameter("page");

        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }
        int totalSearch = new QuizDAO().getTotalQuiz();
        int totalPage = totalSearch / PAGE_SIZE_6;
        if (totalSearch % PAGE_SIZE_6 != 0) {
            totalPage += 1;
        }

        List<Subject> listSubjects = new SubjectDAO().getAllSubjects();
        List<Type> listTypeQuizes = new TypeDAO().getListTypeQuizes();
        List<Quiz> listQuizzesByPagging = new QuizDAO().getListQuizzesByPagging(page, PAGE_SIZE_6);

        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pagination_url", "quiz-list?");
        request.setAttribute("listSubjects", listSubjects);
        request.setAttribute("listTypeQuizes", listTypeQuizes);
        request.getSession().setAttribute("listQuizzesByPagging", listQuizzesByPagging);

        request.getRequestDispatcher("QuizList.jsp").forward(request, response);
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
        final int PAGE_SIZE_6 = 6;
        int page = 1;
        String pageStr = request.getParameter("page");

        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }
        int totalSearch = new QuizDAO().getTotalQuiz();
        int totalPage = totalSearch / PAGE_SIZE_6;
        if (totalSearch % PAGE_SIZE_6 != 0) {
            totalPage += 1;
        }

        List<Subject> listSubjects = new SubjectDAO().getAllSubjects();
        List<Type> listTypeQuizes = new TypeDAO().getListTypeQuizes();
        List<Quiz> listQuizzesByPagging = new QuizDAO().getListQuizzesByPagging(page, PAGE_SIZE_6);

        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pagination_url", "quiz-list?");
        request.setAttribute("listSubjects", listSubjects);
        request.setAttribute("listTypeQuizes", listTypeQuizes);
        request.getSession().setAttribute("listQuizzesByPagging", listQuizzesByPagging);

        request.getRequestDispatcher("QuizList.jsp").forward(request, response);
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
