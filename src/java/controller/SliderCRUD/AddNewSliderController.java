/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.SliderCRUD;

import dao.SliderDAO;
import dao.SubjectDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Slider;
import model.Subject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddNewSliderController", urlPatterns = {"/add-slider"})
@MultipartConfig(location = "/uploads", fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 11)
public class AddNewSliderController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddNewSliderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewSliderController at " + request.getContextPath() + "</h1>");
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
        List<Subject> listSubjects = new SubjectDAO().getAllSubjects();

        request.setAttribute("listSubjects", listSubjects);

        request.getRequestDispatcher("SliderJSP/AddNewSlider.jsp").forward(request, response);
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
//        int subId = Integer.parseInt(request.getParameter("subId"));
//        String title = request.getParameter("title");
//        boolean status = Boolean.parseBoolean(request.getParameter("status"));
//        String image = request.getParameter("image");
//        String content = request.getParameter("content");
//        String notes = request.getParameter("notes");

//        new SliderDAO().insertSlider(title, status, image, content, notes, subId);
//        List<Slider> listSliders = new SliderDAO().getAllSliders();

//        request.getSession().setAttribute("backlink", "slider-list");
//        request.getSession().setAttribute("listSliders", listSliders);
//
//        response.sendRedirect("slider-list");
//        
        String url = "";
        try {
            // Check that we have a file upload request

            int sliderId = 0;
            String sliderName = "";
            String subId = "";
            String title = "";
            String status = "";
            String content = "";
            String notes = "";
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
                    if (key.equals("sliderId")) {
                        sliderId = Integer.parseInt(fields.get(key));
                    } else if (key.equals("subId")) {
                        subId = fields.get(key);
                    } else if (key.equals("title")) {
                        title = fields.get(key);
                    } else if (key.equals("status")) {
                        status = fields.get(key);
                    } else if (key.equals("content")) {
                        content = fields.get(key);
                    } else if (key.equals("notes")) {
                        notes = fields.get(key);
                    }
                }

                boolean check = true;
                if (status.equals("0")) {
                    check = false;
                }
                SliderDAO sliderDAO = new SliderDAO();
                sliderDAO.insertSlider(title, check, fileName, content, notes, Integer.valueOf(subId));
                List<Slider> listSliders = new SliderDAO().getAllSliders();

                request.getSession().setAttribute("backlink", "slider-list");
                request.getSession().setAttribute("listSliders", listSliders);

                url = "slider-list";
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

}
