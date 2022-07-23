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
import model.Subject;
import model.Type;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SearchQuizController", urlPatterns = {"/search-quiz"})
public class SearchQuizController extends HttpServlet {

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
        String typeId = request.getParameter("typeId");
        int subId = Integer.parseInt(request.getParameter("subId"));
        String keyword = request.getParameter("keyword");

        final int PAGE_SIZE_6 = 6;
        int page = 1;
        String pageStr = request.getParameter("page");

        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }
        int totalSearch = 0;
        int totalPage = 0;
        if (subId == 0 && typeId.equalsIgnoreCase("Q0")) {
            totalSearch = new QuizDAO().getTotalQuizOnlySearch(keyword);
            totalPage = totalSearch / PAGE_SIZE_6;
            if (totalSearch % PAGE_SIZE_6 != 0) {
                totalPage += 1;
            }

            List<Quiz> listQuizzesByPagging = new QuizDAO().getListQuizzesBySearchAndPagging(page, PAGE_SIZE_6, keyword);

            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.getSession().setAttribute("listQuizzesByPagging", listQuizzesByPagging);
        } else if (subId != 0 && typeId.equalsIgnoreCase("Q0")) {
            totalSearch = new QuizDAO().getTotalQuizBySubIdAndSearch(keyword, subId);
            totalPage = totalSearch / PAGE_SIZE_6;
            if (totalSearch % PAGE_SIZE_6 != 0) {
                totalPage += 1;
            }

            List<Quiz> listQuizzesByPagging = new QuizDAO().getListQuizzesBySubIdAndSearchAndPagging(page, PAGE_SIZE_6, keyword, subId);

            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.getSession().setAttribute("listQuizzesByPagging", listQuizzesByPagging);
        } else if (subId == 0 && typeId.equalsIgnoreCase("Q0") == false) {
            totalSearch = new QuizDAO().getTotalQuizByTypeIdAndSearch(keyword, typeId);
            totalPage = totalSearch / PAGE_SIZE_6;
            if (totalSearch % PAGE_SIZE_6 != 0) {
                totalPage += 1;
            }

            List<Quiz> listQuizzesByPagging = new QuizDAO().getListQuizzesByTypeIdSearchAndPagging(page, PAGE_SIZE_6, keyword, typeId);

            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.getSession().setAttribute("listQuizzesByPagging", listQuizzesByPagging);
        } else if (subId != 0 && typeId.equalsIgnoreCase("Q0") == false) {
            totalSearch = new QuizDAO().getTotalQuizByTypeIdAndSubIdAndSearch(keyword, subId, typeId);
            totalPage = totalSearch / PAGE_SIZE_6;
            if (totalSearch % PAGE_SIZE_6 != 0) {
                totalPage += 1;
            }

            List<Quiz> listQuizzesByPagging = new QuizDAO().getListQuizzesBySubIdAndTypeIdAndSearchAndPagging(page, PAGE_SIZE_6, keyword, subId, typeId);

            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.getSession().setAttribute("listQuizzesByPagging", listQuizzesByPagging);
        }

        List<Subject> listSubjects = new SubjectDAO().getAllSubjects();
        List<Type> listTypeQuizes = new TypeDAO().getListTypeQuizes();

        request.setAttribute("typeId", typeId);
        request.setAttribute("subId", subId);
        request.setAttribute("keyword", keyword);
        request.setAttribute("listSubjects", listSubjects);
        request.setAttribute("listTypeQuizes", listTypeQuizes);
        request.setAttribute("pagination_url", "search-quiz?typeId=" + typeId + "&subId=" + subId + "&keyword=" + keyword + "&");

        request.getRequestDispatcher("QuizList.jsp").forward(request, response);
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
