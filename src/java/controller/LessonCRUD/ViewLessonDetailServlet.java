/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.LessonCRUD;

import model.Topic;
import dao.TopicDAO;
import dao.TypeDAO;
import dao.LessonDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Lesson;
import model.Type;

/**
 *
 * @author yentt
 */
@WebServlet(name = "ViewLessonDetailServlet", urlPatterns = {"/ViewLessonDetailServlet"})
public class ViewLessonDetailServlet extends HttpServlet {

    private final String LOGIN_PAGE = "Login.jsp";
    private final String LESSONDETAIL_PAGE = "lessonDetail.jsp";

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
        String url = LOGIN_PAGE;
        String msg = "";
        try {
            int lessonId = Integer.parseInt(request.getParameter("txtLessonId"));
            LessonDAO lessonDAO = new LessonDAO();
            TypeDAO typeDAO = new TypeDAO();
            TopicDAO topicDAO = new TopicDAO();

            Lesson lesson = lessonDAO.getLessonById(lessonId);
            List<Type> types = typeDAO.getTypes();
            HttpSession session = request.getSession();
            int subjectId = (int) session.getAttribute("subIdForLesson");
            List<Topic> topics = topicDAO.getTopics();
            request.setAttribute("LESSON", lesson);
            request.setAttribute("TYPE", types);
            request.setAttribute("TOPIC", topics);
            url = LESSONDETAIL_PAGE;
        } catch (Exception e) {
            log("ERROR AT VIEWLESSON DETAIL SERVLET: " + e.getMessage());
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
