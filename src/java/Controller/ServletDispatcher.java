/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Loc
 */
public class ServletDispatcher extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String STARTUP_SERVLET = "StartupServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = STARTUP_SERVLET;
        PrintWriter out= response.getWriter();
        try {
            String button = request.getParameter("btAction");

            if (button == null) {

            } else if (button.equals("Login")) {
                url = "LoginServlet";
            } else if (button.equals("Register")) {
                url = "RegisterServlet";
            } else if (button.equals("Logout")) {
                url = "LogoutServlet";
            } else if (button.equals("Search")) {
                url = "SearchServlet";
            } else if (button.equals("ViewArticle")) {
                url = "ViewArticleServlet";
            } else if (button.equals("Comment")) {
                url = "CommentServlet";
            } else if (button.equals("ViewOwnArticle")) {
                url = "ViewOwnArticleServlet";
            } else if (button.equals("Post")) {
                url = "PostServlet";
            } else if (button.equals("SearchAdmin")) {
                url = "SearchAdminServlet";
            } else if (button.equals("DeleteMul")) {
                url = "DeleteMulServlet";
            } else if (button.equals("StatusToDeleted")) {
                url = "StatusToDeletedServlet";
            } else if (button.equals("StatusToActive")) {
                url = "StatusToActiveServlet";
            } else if (button.equals("Home")) {
                url = STARTUP_SERVLET;
            } else if (button.equals("Manage")) {
                url = STARTUP_SERVLET;
            }else if(button.equals("Older")){
                url = "PagingServlet";
            }else if(button.equals("Newer")){
                url = "PagingServlet";
            }

//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ServletDispatcher</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ServletDispatcher at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
