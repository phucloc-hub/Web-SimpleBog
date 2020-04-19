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
public class SearchServlet extends HttpServlet {

    private final int NUMBER_ART_A_PAGE = 5;
    private final String SHOW_P = "blogs.jsp";

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
        String url = SHOW_P;
        String contents = request.getParameter("txtSearch");
        HttpSession sess = request.getSession(false);
        int curPage = (int) sess.getAttribute("CURPAGE");
        curPage = 0;// Set curPage to 0
        sess.setAttribute("CURPAGE", curPage);// set it back to session
        // set a gain totalPage -> chia trang -> lay totalArt

        try {

            if (!contents.isEmpty()) {
                ArticleDAO dao = new ArticleDAO();
                List<ArticleDTO> listdto = dao.searchArticleByContentPaging(contents, 0, NUMBER_ART_A_PAGE);
                if (listdto.size() > 0) { // listdto.size bay gio chinh la totalArt
                    int totalArt = dao.getTotalArticleSearchUser(contents);
                    int totalPage;
                    if (totalArt % NUMBER_ART_A_PAGE == 0) {
                        totalPage = totalArt / NUMBER_ART_A_PAGE;
                    } else {
                        totalPage = (totalArt / NUMBER_ART_A_PAGE) + 1;
                    }

                    sess.setAttribute("SEARCHED", "SEARCHED");
                    sess.setAttribute("TOTALPAGE", totalPage);

                    sess.setAttribute("LISTARTICLE", listdto);
                } else {
                    sess.setAttribute("LISTARTICLE", null);
                }
            }
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet SearchServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet SearchServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        } catch (ClassNotFoundException ex) {
            log("ClassNotFoundException _ SearchServlet  :" + ex.getMessage());

        } catch (SQLException ex) {
            log("SQLException _ SearchServlet  :" + ex.getMessage());

        } catch (NamingException ex) {
            log("NamingException _ SearchServlet  :" + ex.getMessage());

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
