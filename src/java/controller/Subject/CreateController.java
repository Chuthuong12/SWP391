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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Account;
import model.Category;
import model.Subject;

/**
 *
 * @author 84969
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)

public class CreateController extends HttpServlet {

    private static final String UPLOAD_DIR = "/photo";

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
            throws ServletException, IOException, Exception {
        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/html;charset=UTF-8");
        try {
//            HttpSession session = request.getSession();
//            Account u = (Account) session.getAttribute("account");
//            if (u != null && u.getRoleid() == 1) {
            String image = uploadFile(request);
            String subjectId = request.getParameter("id");
            //System.out.println("id " + request.getParameter("id"));
            String subjectName = request.getParameter("name");
            String description = request.getParameter("description");
            String tagLine = request.getParameter("tagLine");
            String status = request.getParameter("status");
            String title = request.getParameter("title");
            String price = request.getParameter("price");
            String salePrice = request.getParameter("salePrice");
            String categoryId = request.getParameter("category");
            //System.out.println("Image: " + image);
            String[] strInput = {image, subjectId, subjectName, description,
                tagLine, status, title, price, salePrice, categoryId};
            String[] ErrMsg = new String[strInput.length];
            CategoryDAO categoryDAO = new CategoryDAO();
            request.setAttribute("listCategories", categoryDAO.getAllCategories());

            boolean bIsValidate = true;
            bIsValidate = IsValidate(strInput, ErrMsg);
            //System.out.println("bIsValidate: " + bIsValidate);
            if (bIsValidate == false) {
                request.setAttribute("ErrCreateMsg", ErrMsg);
                request.getRequestDispatcher("CreateSubject.jsp").forward(request, response);
            } else {
                SubjectDAO dao = new SubjectDAO();
                //Category category = new Category(Integer.parseInt(categoryId));
                Subject subject = new Subject(Integer.parseInt(subjectId), subjectName, Integer.parseInt(categoryId), Boolean.parseBoolean(status), Integer.parseInt(tagLine), title, image, description, Integer.parseInt(salePrice), Integer.parseInt(price));
                //System.out.println("cehck insert:" + dao.insertNewSubject(subject));
                if (dao.insertNewSubject(subject) == true) {

                    String message = "Create successful";
                    response.sendRedirect("SubjectListAdmin?message=" + message);
                } else {
                    request.getRequestDispatcher("CreateSubject.jsp").forward(request, response);
                }

            }

            //           } else{
//                request.setAttribute("message", "Login first.");
//                request.getRequestDispatcher("Login.jsp").forward(request, response);
// }
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

    private boolean IsValidate(String[] strIn, String[] strOut) throws Exception {

        boolean bCheck = true;

        int iLength = strIn.length;

        for (int iIndex = 0; iIndex < iLength; iIndex++) {
            strOut[iIndex] = "Can not empty";
        }
// iCheck = 0 -> img
        int iCheck = 0;
        if (!strIn[iCheck].isEmpty()) {
            strOut[iCheck] = null;
        }
        //System.out.println("check img: " + strOut[0]);
        // iCheck =1 -> check for id
        iCheck++;
        SubjectDAO checkSubjectDAO = new SubjectDAO();
        Subject checkSubject = checkSubjectDAO.getSubjectById(strIn[iCheck]);
        if (checkSubject != null) {
            if (checkSubject.getSubjectId() == Integer.parseInt(strIn[iCheck])) {
                strOut[iCheck] = "Subject Id is already existed";
            } else {
                strOut[iCheck] = null;
            }
        } else if (!strIn[iCheck].isEmpty()) {
            String fisrt = String.valueOf(strIn[iCheck].trim().charAt(0));
            strOut[iCheck] = null;
        }
        //System.out.println("check id: " + strOut[1]);
        //Check for SubjectName iCheck =2
        iCheck++;
        if (checkSubject != null) {
            if (checkSubject.getSubjectName().equals(strIn[iCheck])) {
                strOut[iCheck] = "Subject Name is already existed";
            } else {
                strOut[iCheck] = null;
            }
        } else if (!strIn[iCheck].isEmpty()) {
            strOut[iCheck] = null;
        }
        //System.out.println("check name: " + strOut[2]);
        //Check for Description iCheck =3
        iCheck++;
        if (!strIn[iCheck].isEmpty()) {
            if (strIn[iCheck].length() < 3) {
                strOut[iCheck] = "Description must longer than 3 characters";
            } else {
                strOut[iCheck] = null;
            }
        }
        //System.out.println("check descrip: " + strOut[3]);

        //Check for Tag line iCheck =4
        iCheck++;
        if (!strIn[iCheck].isEmpty()) {
            if (!strIn[iCheck].matches("[0-9]+")) {
                strOut[iCheck] = "Tag Line is invalid value";
            } else {
                strOut[iCheck] = null;
            }
        }
        //System.out.println("check tag line: " + strOut[4]);

        //skip Check for status 
        iCheck++;
        if (!strIn[iCheck].isEmpty()) {
            strOut[iCheck] = null;
        }
        //System.out.println("check status: " + strOut[5]);

        //Check for Title iCheck =6
        iCheck++;
        if (!strIn[iCheck].isEmpty()) {
            if (strIn[iCheck].length() < 3) {
                strOut[iCheck] = "Description must longer than 3 characters";
            } else {
                strOut[iCheck] = null;
            }
        }
        //System.out.println("check tile " + strOut[6]);
        //Check for Price& SalePrice iCheck = 7
        iCheck++;
        if (!strIn[iCheck].isEmpty()) {
            if (!strIn[iCheck].matches("[0-9]+")) {
                strOut[iCheck] = "Price is invalid value";
            } else {
                strOut[iCheck] = null;
            }
        }
        //System.out.println("check price " + strOut[7]);
        iCheck++;
        if (!strIn[iCheck].isEmpty()) {
            if (!strIn[iCheck].matches("[0-9]+")) {
                strOut[iCheck] = "Sale Price is invalid value";
            } else {
                strOut[iCheck] = null;
            }
        }
        //checkCategory
        iCheck++;
        if (!strIn[iCheck].isEmpty()) {
            strOut[iCheck] = null;
        }
        //Check the exist of error message 
        for (int iIndex = 0; iIndex < iLength; iIndex++) {
            if (strOut[iIndex] != null) {
                bCheck = false;
                break;
            }
        }

        return bCheck;
    }

    //[End] <----
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            CategoryDAO categoryDAO = new CategoryDAO();
            request.setAttribute("listCategories", categoryDAO.getAllCategories());
            request.getRequestDispatcher("CreateSubject.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            log("ERROR at CreateController: " + e.getMessage());
        }

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
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CreateController.class.getName()).log(Level.SEVERE, null, ex);
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
}
