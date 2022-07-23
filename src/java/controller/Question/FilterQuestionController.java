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
@WebServlet(name = "FilterQuestionController", urlPatterns = {"/FilterQuestion"})
public class FilterQuestionController extends HttpServlet {

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
        try {
//            HttpSession session = request.getSession();           
//            Account a = (Account) session.getAttribute("account");
            int status = Integer.parseInt(request.getParameter("statusFilter"));
            int subjectId = Integer.parseInt(request.getParameter("subjectIdFilter"));
            int lessonId = Integer.parseInt(request.getParameter("lessonIdFilter"));
            int dimId = Integer.parseInt(request.getParameter("dimensionIdFilter"));
            ArrayList<QuestionL> questionList = null;
            if(request.getParameter("statusFilter") != null){
                questionList = new QuestionDAO().getQuestionByStatus(status,subjectId,lessonId,dimId);
            if (!questionList.isEmpty()) {
                            request.setAttribute("listQuestion", questionList);
                        } else {
                            request.setAttribute("message", "Not have question!");
                        }
            } 
            
            request.setAttribute("status", status);
            request.setAttribute("subjectId", subjectId);
             request.setAttribute("listSubject", new SubjectDAO().getAllSubjects());
            request.setAttribute("lessonId", lessonId);
            request.setAttribute("listLesson", new LessonDAO().getListLessons());
            request.setAttribute("dimId", dimId);
            request.setAttribute("listDimension", new DimensionDAO().getAllDimension());
            
            
            
            
            request.getRequestDispatcher("QuestionList.jsp").forward(request, response);
            
        }catch (Exception e) {
            e.printStackTrace();
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
