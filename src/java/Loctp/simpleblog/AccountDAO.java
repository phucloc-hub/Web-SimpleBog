/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loctp.simpleblog;

import Loctp.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Loc
 */
public class AccountDAO implements Serializable {

    public String getRoleOf(String Email) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement prs = null;
        ResultSet rs = null;
        String Role="";
        try {
            con= DBUtils.makeCon();
            
            String sql="Select Role from Account where Email= ?";
            
            prs=con.prepareStatement(sql);
            prs.setString(1, Email);
            rs= prs.executeQuery();
            if(rs.next()){
                Role= rs.getString("Role");
                
            }
            

        } finally {
            if (rs != null) {
                rs.close();
            }

            if (prs != null) {
                prs.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
        return Role;

    }

    public boolean createAccount(String Email, String Name, String Password, String Role, String Status) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement prs = null;
//        ResultSet rs = null;
        int count = 0;
        boolean check = false;
        try {
            con = DBUtils.makeCon();

//            String sql="Select Email from Account where Email=?";
            String sql = "Insert into Account(Email,Name,Password,Role,Status) values(?,?,?,?,?) ";
            prs = con.prepareStatement(sql);

            prs.setString(1, Email);
            prs.setString(2, Name);
            String pass;
            pass= DigestUtils.sha256Hex(Password);
// SHA-256 hash -> String to array byte -> array byte to BigInteger -> BigInteger to String(16) by StringBuilder -> IF this string.length <32 => insert(0,'0');
            prs.setString(3, pass);
            prs.setString(4, Role);
            prs.setString(5, Status);

//            rs = prs.executeQuery();
            count = prs.executeUpdate();
            if (count == 1) {
                check = true;
            }

        } finally {

            if (prs != null) {
                prs.close();
            }
            if (con != null) {
                con.close();
            }

        }

        return check;
    }

    public boolean isExistEmail(String userEmail) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement prs = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            con = DBUtils.makeCon();

            String sql = "Select Email from Account where Email=?";

            prs = con.prepareStatement(sql);

            prs.setString(1, userEmail);

            rs = prs.executeQuery();
            if (rs.next()) {
                check = true;
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (prs != null) {
                prs.close();
            }
            if (con != null) {
                con.close();
            }

        }

        return check;
    }

    public String checkLogin(String userEmail, String pass) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement prs = null;
        ResultSet rs = null;
        String userName = "";
        try {
            con = DBUtils.makeCon();

            String sqlStatement = "Select Name from Account where Email=? and Password=?";

            prs = con.prepareStatement(sqlStatement);

            prs.setString(1, userEmail);
            pass= DigestUtils.sha256Hex(pass);
            prs.setString(2, pass);

            rs = prs.executeQuery();
            if (rs.next()) {
                userName = rs.getString("Name");

            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (prs != null) {
                prs.close();
            }
            if (con != null) {
                con.close();
            }

        }

        return userName;
    }

}
