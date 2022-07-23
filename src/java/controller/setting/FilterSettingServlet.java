/*
 * Copyright 2022 Fangl
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Fangl
 * which accompanies this distribution, and is available at
 * https://github.com/fanglong-it
 *
 * Contributors:
 *    Fangl - initial API and implementation and/or initial documentation
 */
package controller.setting;

import dao.QuestionDAO;
import dao.SettingDAO;
import dao.TypeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Setting;
import model.Type;

/**
 *
 * @author Fangl
 */
@WebServlet(name = "FilterSettingServlet", urlPatterns = {"/FilterSetting"})
public class FilterSettingServlet extends HttpServlet {

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
            out.println("<title>Servlet FilterSettingServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FilterSettingServlet at " + request.getContextPath() + "</h1>");
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
        String url = "";
        try {
            Boolean status = Boolean.parseBoolean(request.getParameter("status"));
            String typeId = request.getParameter("type");

            //
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
            
            SettingDAO settingDAO = new SettingDAO();
            List<Setting> settings = settingDAO.getSettingsByTypeAndStatus(page, PAGE_SIZE, typeId, status);
            
            TypeDAO typeDAO = new TypeDAO();
            List<Type> types = typeDAO.getListAllType();
            request.setAttribute("typeList", types);
            request.setAttribute("settingList", settings);
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            url = "settingList.jsp";
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
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
