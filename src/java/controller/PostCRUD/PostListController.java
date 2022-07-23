/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.PostCRUD;

import dao.BlogDAO;
import dao.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Blog;
import model.Post;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "PostSliderController", urlPatterns = {"/post-list"})
public class PostListController extends HttpServlet {

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
        final int PAGE_SIZE = 3;
        int page = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }
        int totalSearch = 0;
        int totalPage = 0;
        String blogAction = request.getParameter("blogAction");
        if (blogAction.equalsIgnoreCase("get")) {
            totalSearch = new PostDAO().getTotalPost();
            totalPage = totalSearch / PAGE_SIZE;
            if (totalSearch % PAGE_SIZE != 0) {
                totalPage += 1;
            }
            List<Post> listPosts = new PostDAO().getPostsAndPagging(page, PAGE_SIZE);
            List<Blog> listBlogs = new BlogDAO().getListBlogs();
            Post lastPost = new PostDAO().getLastPost();

            request.setAttribute("lastPost", lastPost);
            request.setAttribute("listPosts", listPosts);
            request.setAttribute("listBlogs", listBlogs);
            request.setAttribute("pagination_url", "post-list?&blogAction=get&");
        } else if (blogAction.equalsIgnoreCase("post")) {
            int blogId = Integer.parseInt(request.getParameter("blogId"));
            totalSearch = new PostDAO().getTotalPostByBlogId(blogId);
            totalPage = totalSearch / PAGE_SIZE;
            if (totalSearch % PAGE_SIZE != 0) {
                totalPage += 1;
            }
            List<Post> listPosts = new PostDAO().getPostsByBlogIdAndPagging(blogId, page, PAGE_SIZE);
            List<Blog> listBlogs = new BlogDAO().getListBlogs();
            Post lastPost = new PostDAO().getLastPost();

            request.setAttribute("blogId", blogId);
            request.setAttribute("lastPost", lastPost);
            request.setAttribute("listPosts", listPosts);
            request.setAttribute("listBlogs", listBlogs);
            request.setAttribute("pagination_url", "post-list?&blogAction=post&blogId=" + blogId + "&");
        }

        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);

        request.getRequestDispatcher("BlogList.jsp").forward(request, response);
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
