/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
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

/**
 *
 * @author yentt
 */
@WebServlet(name = "UpdateLessonDetailServlet", urlPatterns = {"/UpdateLessonDetailServlet"})
public class UpdateLessonDetailServlet extends HttpServlet {

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
        String url = LESSONDETAIL_PAGE;
        String msg = "";
        try {
            int lessonId = Integer.parseInt(request.getParameter("txtLessonId"));
            String lessonName = request.getParameter("txtName");
            String type = request.getParameter("txtType");
            int topic = Integer.parseInt(request.getParameter("txtTopic"));
            int order = Integer.parseInt(request.getParameter("txtOrder"));
            String videoUrl = request.getParameter("txtVideoLink");
            String content = request.getParameter("txtContent");

            LessonDAO lessonDAO = new LessonDAO();
            if (lessonDAO.updateLesson(lessonName, type, topic, order, videoUrl, content, lessonId)) {
                msg = "Update Success!";
                url = "ViewLessonDetailServlet?txtLessonId=" + lessonId;
            } else {
                url = "ViewLessonDetailServlet?txtLessonId=" + lessonId;
                msg = "Update Fail!";
            }
            request.setAttribute("UPDATELESSON_MSG", msg);
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
