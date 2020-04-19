/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Loctp.simpleblog.AccountDAO;
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Loc
 */
public class StartupServlet extends HttpServlet {

    private final String SHOW_P = "blogs.jsp";
    private final String MANAGE_P="manageArticle.jsp";
    private final int NUMBER_ART_A_PAGE=5;

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
        String userEmail="",password="";
        String url = SHOW_P;
        int totalPage=0;
        try {

            Cookie[] cookies = request.getCookies();
            //2.Check cookie
            HttpSession sess = request.getSession(true);
            

            if (cookies != null) {

                //3. de get username va password
                for (Cookie cooky : cookies) {
                    if(cooky.getName().equals("email")){
                       userEmail=cooky.getValue();
                    }
                    if(cooky.getName().equals("password")){
                       password=cooky.getValue();
                    }
                    
                    AccountDAO dao = new AccountDAO();
                    String username = dao.checkLogin(userEmail, password);
                    if (!username.isEmpty()) {
                        String Role= dao.getRoleOf(userEmail);
                        sess.setAttribute("Email", userEmail);
                        sess.setAttribute("USERKIND", Role.trim());
                        sess.setAttribute("FULLNAME", username);
                        if(Role.trim().equals("Admin")){
                            url=MANAGE_P;
                        }
                        
//                        url = SEARCH_PAGE;
                        break;

                    }

                }

            }
            // DONE cookie

            // LOAD article
            ArticleDAO dao = new ArticleDAO();
            int totalArt= dao.getTotalArticle();
//            totalArt%kichthuoctrang=0
//                    -> tong so trang= totalArt/kichthuoctrang
//            -> tong so trang= (totalArt/kich thuong trang)+1;
            if(totalArt%NUMBER_ART_A_PAGE==0){
                totalPage= totalArt/NUMBER_ART_A_PAGE;
            }else{
                totalPage= (totalArt/NUMBER_ART_A_PAGE)+1;
            }
            sess.setAttribute("SEARCHEDADMIN", "");
            sess.setAttribute("SEARCHED", "");// SET SEARCHED TO "" IF CLICK HOME AND TELL PAGING_SERVLET THAT GET ALL ARTICLE
            sess.setAttribute("CURPAGE", 0);
            sess.setAttribute("TOTALPAGE", totalPage);
            List<ArticleDTO> listdto = dao.getArticle(0,NUMBER_ART_A_PAGE);
            sess.setAttribute("LISTARTICLE", listdto);
            
           

            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet StartupServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet StartupServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        } catch (SQLException ex) {
            log("SQLException _ StartupServlet  :" + ex.getMessage());

        } catch (ClassNotFoundException ex) {
            log("ClassNotFoundException _ StartupServlet  :" + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException _ StartupServlet  :" + ex.getMessage());
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
