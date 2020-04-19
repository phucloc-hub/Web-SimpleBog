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
public class PagingServlet extends HttpServlet {

    private final int NUMBER_ART_A_PAGE = 5;
    private final String MANAGE_P = "manageArticle.jsp";
    private final String BLOGS_P = "blogs.jsp";

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
        String btAction = request.getParameter("btAction");
        String contents = request.getParameter("contents");
        String url = "";
        try {
            HttpSession sess = request.getSession(false);
            String userKind = (String) sess.getAttribute("USERKIND");
            if (userKind == null) {
                url = BLOGS_P;
            } else if (userKind.equals("Admin")) {
                url = MANAGE_P;
            } else { // is user
                url = BLOGS_P;
            }

            ArticleDAO dao = new ArticleDAO();
            List<ArticleDTO> listdto = new ArrayList<>();
            int curPage = (int) sess.getAttribute("CURPAGE");

            if (btAction.equals("Older")) {

                if (curPage > 0) {
                    curPage = curPage - 1;
                    sess.setAttribute("CURPAGE", curPage);
                    String isSearched = (String) sess.getAttribute("SEARCHED");
                    if (isSearched.equals("SEARCHED")) {
                        listdto = dao.searchArticleByContentPaging(contents, curPage*NUMBER_ART_A_PAGE, NUMBER_ART_A_PAGE);

                    } else {
                        listdto = dao.getArticle(curPage * NUMBER_ART_A_PAGE, NUMBER_ART_A_PAGE);
                    }

                    sess.setAttribute("LISTARTICLE", listdto);// UPDATE LIST

                }

            } else { // btAction == Newer
                int totalPage = (int) sess.getAttribute("TOTALPAGE");
                if (curPage < (totalPage - 1)) {
                    curPage = curPage + 1;
                    sess.setAttribute("CURPAGE", curPage);// set a gaint curPage

                    String isSearched = (String) sess.getAttribute("SEARCHED");
                    if (isSearched.equals("SEARCHED")) {
                        listdto = dao.searchArticleByContentPaging(contents, curPage*NUMBER_ART_A_PAGE, NUMBER_ART_A_PAGE);

                    } else {
                        listdto = dao.getArticle(curPage * NUMBER_ART_A_PAGE, NUMBER_ART_A_PAGE);
                    }

                    sess.setAttribute("LISTARTICLE", listdto);// UPDATE LIST

                }

            }

//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet PagingServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet PagingServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        } catch (ClassNotFoundException ex) {
            log("ClassNotFoundException_PagingServlet : " + ex.getMessage());
        } catch (SQLException ex) {
            log("SQLException : " + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException_PagingServlet : " + ex.getMessage());
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
