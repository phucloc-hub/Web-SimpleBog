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
public class SearchAdminServlet extends HttpServlet {

    private final String MANAGE_P = "manageArticle.jsp";

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
        String status = request.getParameter("statusOption").trim();
        String typeSearch = request.getParameter("Bywhat").trim();
        String searchVal = request.getParameter("txtSearchAdmin").trim();
        String url = MANAGE_P;
        try {
            if (status != null) {
                if (typeSearch != null) {
                    if (!searchVal.isEmpty()) {

                        ArticleDAO dao = new ArticleDAO();
                        List<ArticleDTO> listdto = dao.searchArticleByAd(status, typeSearch, searchVal);
                        if (listdto != null) {
                            // LOAD article
                            
                            HttpSession sess= request.getSession(false);
                            sess.setAttribute("LISTARTICLE", listdto);
                            sess.setAttribute("SEARCHEDADMIN", "SEARCHEDADMIN");
                            // -> SEARCH -> NO BUTTON OLDER or NEWER
                            
                        }

                    }

                }

//               
            }

//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet SearchAdminServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet SearchAdminServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        } catch (SQLException ex) {
            log("SQLException_SearchAdminServlet  :" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("ClassNotFoundException_SearchAdminServlet  :" + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException_SearchAdminServlet  :" + ex.getMessage());
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
