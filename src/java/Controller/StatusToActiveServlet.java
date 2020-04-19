/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Loctp.simpleblog.ArticleDAO;
import Loctp.simpleblog.ArticleDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class StatusToActiveServlet extends HttpServlet {

    private final String ERROR_P = "errorDeleteAdmin.html";
    private final String MANAGE_P = "manageArticle.jsp";
    private final int NUMBER_ART_A_PAGE = 5;

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
        String ArticleID = request.getParameter("ArticleID");

        String url = MANAGE_P;
        try {

            ArticleDAO dao = new ArticleDAO();
            boolean result = dao.ActiveArticle(ArticleID);
            if (result == false) {
                url = ERROR_P;

            }

            //Load again data
            HttpSession sess = request.getSession(false);
            int totalArt = dao.getTotalArticle();
            int totalPage;
            if (totalArt % NUMBER_ART_A_PAGE == 0) {
                totalPage = totalArt / NUMBER_ART_A_PAGE;
            } else {
                totalPage = (totalArt / NUMBER_ART_A_PAGE) + 1;
            }

            sess.setAttribute("TOTALPAGE", totalPage);

            List<ArticleDTO> listdto = dao.getArticle(0, NUMBER_ART_A_PAGE);
            sess.setAttribute("LISTARTICLE", listdto);

//            //Load again data
//            HttpSession sess= request.getSession(false);
//            List<ArticleDTO> listdto = dao.searchArticleByAd(status, typeSearch, searchVal);
//            sess.setAttribute("LISTARTICLE", listdto);
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet StatusToDeletedServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet StatusToDeletedServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        } catch (SQLException ex) {
            log("SQLException_StatusToActiveServlet:  " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("ClassNotFoundException_StatusToActiveServlet:  " + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException_StatusToActiveServlet:  " + ex.getMessage());
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
