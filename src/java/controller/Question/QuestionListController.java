/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Question;

import dao.DimensionDAO;
import dao.LessonDAO;
import dao.QuestionDAO;
import dao.SubjectDAO;
import dao.TopicDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Question;
import model.QuestionL;

/**
 *
 * @author 84969
 */
@WebServlet(name = "QuestionListController", urlPatterns = {"/QuestionList"})
public class QuestionListController extends HttpServlet {

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
            out.println("<title>Servlet QuestionListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuestionListController at " + request.getContextPath() + "</h1>");
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
        try {
            final int PAGE_SIZE = 6;
            int page = 1;
            String pageStr = request.getParameter("page");
            if (pageStr != null) {
                page = Integer.parseInt(pageStr);
            }
            int totalSearch = new QuestionDAO().getTotalQuestion();
            int totalPage = totalSearch / PAGE_SIZE;
            if (totalSearch % PAGE_SIZE != 0) {
                totalPage += 1;
            }
            QuestionDAO questionDao = new QuestionDAO();
            request.getSession().setAttribute("listQuestion", questionDao.getAllQuestion());
            ArrayList<QuestionL> listQuestionByPagging = questionDao.getListQuestionsByPagging(page, PAGE_SIZE);
            request.setAttribute("listQuestion",listQuestionByPagging );
            request.setAttribute("listSubject", new SubjectDAO().getAllSubjects());
            request.setAttribute("listLesson", new LessonDAO().getListLessons());
            request.setAttribute("listDimension", new DimensionDAO().getAllDimension());
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.getSession().setAttribute("search_url", "search_question");
            request.setAttribute("pagination_url", "QuestionList?");

            request.getRequestDispatcher("QuestionList.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();

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
