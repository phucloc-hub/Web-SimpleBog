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
public class LoginServlet extends HttpServlet {

    private final String INVALID_PAGE = "login.jsp";
    private final String VALID_PAGE = "blogs.jsp";
    private final String MANAGE_PAGE = "manageArticle.jsp";

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
        String url = INVALID_PAGE;
        try {

            String userEmail = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");

            AccountDAO dao = new AccountDAO();
            String username = dao.checkLogin(userEmail, password);
            if (!username.isEmpty()) {
                String Role = dao.getRoleOf(userEmail);

                url = VALID_PAGE;

//                // GET LIST ARTICLES
//                ArticleDAO daoART = new ArticleDAO();
//                List<ArticleDTO> listdto = daoART.getArticle();
//                request.setAttribute("LISTARTICLE", listdto);
                // encode username and password
                HttpSession sess = request.getSession(false);
                sess.setAttribute("FULLNAME", username);
//                request.setAttribute("Email", userEmail);
                sess.setAttribute("Email", userEmail);
                sess.setAttribute("USERKIND", Role.trim());
                if (Role.trim().equals("Admin")) {
                    url = MANAGE_PAGE;
                }

                Cookie cookie = new Cookie("email", userEmail);
                cookie.setMaxAge(60 * 3);// ton tai 60*3= 3 phut
                response.addCookie(cookie);
                cookie = new Cookie("password", password);
                cookie.setMaxAge(60 * 3);// ton tai 60*3= 3 phut
                response.addCookie(cookie);
//                sess.setAttribute("CurCo0kie", cookie);

            }
            //end if login

//            out.println("Username " + username + " - " + password + " - " + button);
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet LoginSrevlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet LoginSrevlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
            log("ClassNotFoundException_LoginServlet : " + ex.getMessage());
        } catch (SQLException ex) {
            log("SQLException_LoginServlet : " + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException_LoginServlet : " + ex.getMessage());
        } finally {
            RequestDispatcher rs = request.getRequestDispatcher(url);
            rs.forward(request, response);
//            response.sendRedirect(url);
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
