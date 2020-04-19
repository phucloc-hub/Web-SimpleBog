/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Loctp.simpleblog.CommentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Loc
 */
public class CommentServlet extends HttpServlet {

    private final String ART_P = "articleDetails.jsp";
    private final String ERROR_P = "errorComment.html";

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
        PrintWriter out = response.getWriter();
        String url = ART_P;
        try {

            String content = request.getParameter("txtComment");
            String author = request.getParameter("author");
            String ArticleID = request.getParameter("articleID");
            String datePost = LocalDateTime.now().toString();
            int commentID = 0;
            CommentDAO dao = new CommentDAO();

            if (content.trim().length() != 0) {
                commentID = dao.getTotalComment();

                boolean successed = dao.insertComment(commentID, content, ArticleID, author, datePost);
                if (successed == false) {
                    url = ERROR_P;
                }

            }
            boolean getlistcomment = dao.getComments(ArticleID);

            if (getlistcomment == true) {
                request.setAttribute("LISTCOMM", dao.getlistCommentDTO());
            }

        } catch (SQLException ex) {
            log("SQLException_CommentServlet: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("ClassNotFoundException_CommentServlet: " + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException_CommentServlet: " + ex.getMessage());
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
