/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Loctp.simpleblog.ArticleDAO;
import Loctp.simpleblog.ArticleDTO;
import Loctp.simpleblog.CommentDAO;
import Loctp.simpleblog.CommentDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Loc
 */
public class ViewArticleServlet extends HttpServlet {

    private final String ARTICLEDETAILS_P = "articleDetails.jsp";

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
        PrintWriter out = response.getWriter();
        String url = ARTICLEDETAILS_P;
        response.setContentType("text/html;charset=UTF-8");
        String articleID = request.getParameter("id");

        try {

            ArticleDAO dao = new ArticleDAO();
            ArticleDTO articleObj = dao.getArticleDetails(articleID);
            CommentDAO daoComm = new CommentDAO();
            boolean haveComments = daoComm.getComments(articleID);
            if (articleObj != null) {
                HttpSession sess = request.getSession(false);
                sess.setAttribute("ARTDETAILS", articleObj);
                if (haveComments == true) {
                    request.setAttribute("LISTCOMM", daoComm.getlistCommentDTO());
                }
            }

        } catch (ClassNotFoundException ex) {
            log("ClassNotFoundException_ViewArticleServlet: " + ex.getMessage());
        } catch (SQLException ex) {
            log("SQLException_ViewArticleServlet: " + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException_ViewArticleServlet: " + ex.getMessage());

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
