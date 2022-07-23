/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Subject;

import dao.BlogDAO;
import dao.CategoryDAO;
import dao.SliderDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Blog;
import model.Slider;

/**
 *
 * @author 84969
 */
@WebServlet(name = "SubjectListAdmin", urlPatterns = {"/SubjectListAdmin"})
public class SubjectListAdmin extends HttpServlet {

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
            final int PAGE_SIZE = 3;
            int page = 1;
            String pageStr = request.getParameter("page");
            if (pageStr != null) {
                page = Integer.parseInt(pageStr);
            }
            int totalSearch = new SubjectDAO().getTotalSubjectAdmin();
            int totalPage = totalSearch / PAGE_SIZE;
            if (totalSearch % PAGE_SIZE != 0) {
                totalPage += 1;
            }
            SubjectDAO subjectDAO = new SubjectDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            List<Blog> listBlogs = new BlogDAO().getListBlogs();
            List<Slider> listSliders = new SliderDAO().getAllSliders();
            //System.out.println("List: " + subjectDAO.getAllSubjects().toString());
            String message = request.getParameter("message");
                if (message != null) {
                    request.setAttribute("message", message);
                }
            request.getSession().setAttribute("listSliders", listSliders);
            request.getSession().setAttribute("listSubjects", subjectDAO.getAllSubjects());          
//            request.setAttribute("listCategories", categoryDAO.getAllCategories());
            request.setAttribute("listSubjects", subjectDAO.getListSubjectsByPagging(page, PAGE_SIZE));
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.getSession().setAttribute("listBlogs", listBlogs);
            request.getRequestDispatcher("SubjectListAdmin.jsp").forward(request, response);
        } catch (Exception e) {
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
        try {
            request.getSession().setAttribute("search_url", "search_subject");
            request.setAttribute("pagination_url", "SubjectListAdmin?");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        try {
            request.getSession().setAttribute("search_url", "search_subject");
        } catch (Exception e) {
            e.printStackTrace();

        }
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
