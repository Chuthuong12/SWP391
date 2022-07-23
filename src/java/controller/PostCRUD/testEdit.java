/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.PostCRUD;

import dao.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Post;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "testEdit", urlPatterns = {"/testEdit"})
@MultipartConfig(location = "/uploads", fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 11)

public class testEdit extends HttpServlet {

    private static final String UPLOAD_DIR = "/photo";

    private static final long serialVerionUID = 1L;

    public testEdit() {
        super();
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditPostControler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditPostControler at " + request.getContextPath() + "</h1>");
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
        int postId = Integer.parseInt(request.getParameter("postId"));
        Post post = new PostDAO().getPostById(postId);

        request.setAttribute("post", post);

        request.getRequestDispatcher("EditPost.jsp").forward(request, response);
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
        String url = "";
        try {
            // Check that we have a file upload request

            int postId = 0;
            String title = "";
            String status = "";
            String thumbnail = "";
            String briefInfor = "";
            String content = "";
            String fileName = "";
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart) {
                // Create a factory for disk-based file items
                FileItemFactory factory = new DiskFileItemFactory();

                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);

                // Parse the request
                List<FileItem> items = upload.parseRequest(request);

                // Process the uploaded items
                Iterator iter = items.iterator();
                HashMap<String, String> fields = new HashMap<>();
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();

                    if (item.isFormField()) {
                        // Process form field.
                        String name = item.getFieldName();
                        String value = item.getString();
                        fields.put(name, value);
                    } else {
                        // Process uploaded file.
                        fileName = item.getName();
                        System.out.println("FileName: " + fileName);
                        if (fileName == null || fileName.equals("")) {
                            break;
                        } else {
                            Path path = Paths.get(fileName);
                            String storePath = request.getServletContext().getRealPath("/uploads");
                            File uploadFile = new File(storePath + "/" + path.getFileName());
                            if (uploadFile.exists()) {

                            } else {
                                item.write(uploadFile);
                            }
                            System.out.println(storePath + "/" + path.getFileName());
                        }
                    }
                }
//                            String sliderName, subId, title, status,content,notes = "";
                Set<String> keys = fields.keySet();
                for (String key : keys) {
                    if (key.equals("postId")) {
                        postId = Integer.parseInt(fields.get(key));
                    } else if (key.equals("title")) {
                        title = fields.get(key);
                    } else if (key.equals("status")) {
                        status = fields.get(status);
                    } else if (key.equals("briefInfor")) {
                        briefInfor = fields.get(key);
                    } else if (key.equals("content")) {
                        content = fields.get(key);
                    }
                }

                boolean check = true;
                if (status.equals("0")) {
                    check = false;
                }
                new PostDAO().updatePost(postId, title, check, fileName, briefInfor, content);
                Post post = new PostDAO().getPostById(postId);

                request.setAttribute("post", post);

//                request.getRequestDispatcher("EditPost.jsp").forward(request, response);
                url = "edit-post?postId=" + postId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.sendRedirect(url);
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

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
