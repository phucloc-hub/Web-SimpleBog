/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Loctp.simpleblog.ArticleDAO;
import Loctp.simpleblog.ArticlePostErrors;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Loc
 */
public class PostServlet extends HttpServlet {
    private final String SHOW_P= "blogs.jsp";
    private final String INVALID_P="post.jsp";
    private final String ERROR_P="errorPost.html";
    private final String STATUS="New";
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
        String url= SHOW_P;
        boolean foundERROR=false;
        
        try {
            HttpSession sess= request.getSession(false);
            ArticleDAO dao = new ArticleDAO();
            ArticlePostErrors errors= new ArticlePostErrors();
            
            String Contents= request.getParameter("txtContents");
            if(Contents.length()==0){
                errors.setContentsLength("Content is required!");
                foundERROR=true;
            }
            
            
            String Title = request.getParameter("txtTitle");
            if(Title.length()==0){
                errors.setTitleLength("Title is required!");
                foundERROR=true;
            }
            
            
            String Description= request.getParameter("txtDescription");
            if(Description.length()==0){
                errors.setDescriptionLength("Description is required!");
                foundERROR=true;
            }
            
            int ArticleID=dao.getTotalArticle();
            String Author= (String)sess.getAttribute("Email");
            if(Author==null){
                url= ERROR_P;
            }
            String DatePost = LocalDateTime.now().toString();
            if(foundERROR==false){
                 boolean result= dao.insertArticle(ArticleID, Title, Author, Contents, Description, DatePost,STATUS);
            if(result==false){
                url= ERROR_P;
            }
              
            }
            if(foundERROR==true){
                url= INVALID_P;
                request.setAttribute("ERROR", errors);
            }
            
         
            
            
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet PostServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet PostServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        } catch (SQLException ex) {
            log("SQLException_PostServlet"+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("ClassNotFoundException_PostServlet"+ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException_PostServlet"+ex.getMessage());
        }finally{
            RequestDispatcher rd= request.getRequestDispatcher(url);
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
