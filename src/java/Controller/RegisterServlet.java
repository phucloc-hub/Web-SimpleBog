/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Loctp.simpleblog.AccountCreateErrors;
import Loctp.simpleblog.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
public class RegisterServlet extends HttpServlet {

    private final String ERROR_PAGE = "register.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String url = ERROR_PAGE;

        try {

            String Email = request.getParameter("txtEmail");
            String Name = request.getParameter("txtUsername");
            String Password = request.getParameter("txtPassword");
            String Confirm = request.getParameter("txtConfirm");
            boolean foundERROR = false;
            AccountCreateErrors errors = new AccountCreateErrors();
            AccountDAO dao = new AccountDAO();

            if (!Email.trim().matches("\\w{1,10}[@]\\w{1,7}[.]\\w{1,7}([.]\\w{1,7}){0,1}")) {
                errors.setEmailFormat("Please follow format like abc@domain.com");
                foundERROR = true;
            }
            if (Email.length() == 0) {
                errors.setEmailLength("Email is required!");
                foundERROR = true;

            }

            boolean checkDupEmail = dao.isExistEmail(Email);
            if (checkDupEmail == true) {
                errors.setEmailDup("This Email is alreadly exist!");
                foundERROR = true;

            }

            if (Name.length() < 6 || Name.length() > 20) {
                errors.setUserNameLength("Please enter name from 6 to 20 chars!!!");
                foundERROR = true;

            }

            if (Password.length() < 6 || Password.length() > 30) {
                errors.setPasswordLength("Password length requires from 6 to 30 chars!!!");
                foundERROR = true;

            }
            if (!Confirm.equals(Password)) {
                errors.setConfirmNotMatchPassword("Not match with the password");
                foundERROR = true;

            }

            if (foundERROR == true) {
                request.setAttribute("ERROR", errors);
            } else {

                boolean inserted = dao.createAccount(Email, Name, Password, "Member", "New");
                if (inserted == true) {
                    request.setAttribute("SUCCESS", "created");
                }
                
                
//                String sql = "Select ArticleID,Title,Description,Author,DatePost,Email,Status "
//                    + "from Article where Contents like ?"
//                    + " and Status= 'Active' ORDER BY DatePost DESC offset ? rows fetch next ? rows only  ";
            }

//Pattern pattern = Pattern.compile(REGEX);
        } catch (SQLException ex) {
            log("SQLException _ RegisterServlet  :" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("ClassNotFoundException _ RegisterServlet  :" + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException _ RegisterServlet  :" + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);

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
