/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Subject;

import dao.CategoryDAO;
import dao.SubjectDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Account;
import model.Subject;

/**
 *
 * @author 84969
 */
@WebServlet(name = "EditSubjectController", urlPatterns = {"/EditSubjectController"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class EditSubjectController extends HttpServlet {

    private static final String UPLOAD_DIR = "photo";

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
        try {
            HttpSession session = request.getSession();
            Account u = (Account) session.getAttribute("account");
//            if (u != null && u.getRoleid() == 1) {
            String image = uploadFile(request);
            String subId = request.getParameter("id");
            System.out.println("SubId in edit controller: " + subId);
            String subjectName = request.getParameter("name");
            String description = request.getParameter("description");
            int tagLine = Integer.parseInt(request.getParameter("tagLine"));
            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            String title = request.getParameter("title");
            int catId = Integer.parseInt(request.getParameter("categoryId"));
            SubjectDAO subDao = new SubjectDAO();
            Subject sub = subDao.getSubjectById(subId);
            System.out.println("Subject have id " + subId+ ": " + sub.getSubjectName());
            request.setAttribute("s", sub);
            if (request.getParameter("name")==null) {
                subjectName = sub.getSubjectName();
            }
            if (request.getParameter("status").isEmpty()) {
                status = sub.isStatus();
            }
            if (request.getParameter("categoryId").isEmpty()) {
                catId = sub.getCategoryId();
            }
            if (request.getParameter("tagLine").isEmpty()) {
                tagLine = sub.getTagLine();
            }
            if (title.isEmpty()) {
                title = sub.getTitle();
            }
            if (image.isEmpty()) {
                image = sub.getThumbnail();
            }
            if (description.isEmpty()) {
                description = sub.getDescription();
            }
            String message = "";
            Subject subject = new Subject(Integer.parseInt(subId), subjectName, catId, status, tagLine, title, image, description);
            if (subDao.Update(subject)) {
                message = "Update sucessfull";
            } else {
                message = "Update failed";
            }
            System.out.println("message: " + message);
            request.setAttribute("message", message);
            request.getRequestDispatcher("Detail.jsp").forward(request, response);

//            }
//          else {
//                request.setAttribute("message", "Login first.");
//                request.getRequestDispatcher("Login.jsp").forward(request, response);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

  private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
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
 private String uploadFile(HttpServletRequest request) {
        String fileName = "";
        try {
            Part filePart = request.getPart("photo");
            System.out.println("Part photo: " + request.getPart("photo"));
            System.out.println("filrPart: " + filePart);
            fileName = (String) getFileName(filePart);

            int lengh = fileName.indexOf(".");
            for (int iIndex = lengh; iIndex >= 0; iIndex--) {
                if (fileName.charAt(iIndex) == 92) {
                    fileName = fileName.substring(iIndex + 1);
                    break;
                }
            }

            String applicationPath = request.getServletContext().getRealPath("");
            String basePath = applicationPath + UPLOAD_DIR + File.separator;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception e) {
                e.printStackTrace();
                fileName = "";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (Exception e) {
            fileName = "";
        }
        return fileName;
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
