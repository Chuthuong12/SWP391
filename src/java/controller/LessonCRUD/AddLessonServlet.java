/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.LessonCRUD;

import dao.LessonDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Lesson;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddLessonServlet", urlPatterns = {"/AddLessonServlet"})
public class AddLessonServlet extends HttpServlet {

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
        String url = "";
        String msg = "";
        try {
            
            String lessonName = request.getParameter("txtName");
            String type = request.getParameter("txtType");
            int topic = Integer.parseInt(request.getParameter("txtTopic"));
            String videoUrl = request.getParameter("txtVideoLink");
            String content = request.getParameter("txtContent");
            HttpSession session = request.getSession();
            int subjectId = (int) session.getAttribute("subIdForLesson");
            
            //Check Valid
            

            LessonDAO lessonDAO = new LessonDAO();
            Lesson lesson = new Lesson(0, lessonName, type, 1, videoUrl, content, topic, Boolean.TRUE, subjectId, content, "");
            if (lessonDAO.insertLesson(lesson)) {
                msg = "Insert Success!";
                url = "subject-lesson-detail?subId=" + subjectId;
            } else {
                msg = "Insert Fail!";
                url = "addLesson.jsp";
            }
            request.setAttribute("ADDLESSON_MSG", msg);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
