/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LessonDAO;
import dao.PostDAO;
import dao.PricePackageDAO;
import dao.SubjectDAO;
import dao.SubjectRegistrationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Lesson;
import model.Post;
import model.PricePackage;
import model.Subject;
import model.SubjectRegistration;

/**
 *
 * @author ADMIN
 */
public class SubjectDetailController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SubjectDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubjectDetailController at " + request.getContextPath() + "</h1>");
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
        int subjectId = Integer.parseInt(request.getParameter("id"));

//        String ChekckLogin = (String) request.getSession().getAttribute("ChekckLogin");
//        if (ChekckLogin.equalsIgnoreCase("Login")) {
        Account acc = (Account) request.getSession().getAttribute("account");
        List<Subject> listSubjectBySubjectId = new SubjectDAO().getListSubjectBySubjectId(subjectId);
        List<Subject> listSubjects = new SubjectDAO().getAllSubjects();
        List<Lesson> listLessonsBySubId = new LessonDAO().get3LessonsBySubId(subjectId);
        List<Post> list3FirstPosts = new PostDAO().getList3FirstPosts();
        List<PricePackage> listAllPricePackage = new PricePackageDAO().getAllPackage();
        int checkRegis = new SubjectRegistrationDAO().checkRegistration(subjectId, acc.getUserid());

        request.setAttribute("checkRegis", checkRegis);
        request.setAttribute("listAllPricePackage", listAllPricePackage);
        request.setAttribute("list3FirstPosts", list3FirstPosts);
        request.setAttribute("listSubjects", listSubjects);
        request.getSession().setAttribute("listSubjects", listSubjects);
        request.getSession().setAttribute("subId", subjectId);
        request.setAttribute("listSubjectBySubjectId", listSubjectBySubjectId);
        request.setAttribute("listLessonsBySubId", listLessonsBySubId);
        request.getSession().setAttribute("search_url", "search_subject");
        request.getRequestDispatcher("SubjectDetail.jsp").forward(request, response);
//        } else if (ChekckLogin.equalsIgnoreCase("NotLogin")) {
//        request.getRequestDispatcher("Login.jsp").forward(request, response);
//        }
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
        int subjectId = Integer.parseInt(request.getParameter("id"));
        Account acc = (Account) request.getSession().getAttribute("account");

        List<Subject> listSubjects = new SubjectDAO().getAllSubjects();
        List<Lesson> listLessonsBySubId = new LessonDAO().get3LessonsBySubId(subjectId);
        List<Post> list3FirstPosts = new PostDAO().getList3FirstPosts();
        List<PricePackage> listAllPricePackage = new PricePackageDAO().getAllPackage();
        int checkRegis = new SubjectRegistrationDAO().checkRegistration(subjectId, acc.getUserid());

        request.setAttribute("checkRegis", checkRegis);
        request.setAttribute("listAllPricePackage", listAllPricePackage);
        request.setAttribute("list3FirstPosts", list3FirstPosts);
        request.setAttribute("listSubjects", listSubjects);
        request.getSession().setAttribute("listSubjects", listSubjects);
        request.getSession().setAttribute("subId", subjectId);
        request.setAttribute("listLessonsBySubId", listLessonsBySubId);
        request.getSession().setAttribute("search_url", "search_subject");
        request.getRequestDispatcher("SubjectDetail.jsp").forward(request, response);
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
