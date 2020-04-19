/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loctp.simpleblog;

import Loctp.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Loc
 */
public class CommentDAO {

    private List<CommentDTO> listCommentDTO;

    public List<CommentDTO> getlistCommentDTO() {
        return listCommentDTO;
    }

    public int getTotalComment() throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        int count=0;
        try {
            con= DBUtils.makeCon();
            
            String sql = "Select COUNT(CommentID) AS totalComment from Comment";

            stm = con.createStatement();

            rs = stm.executeQuery(sql);
            if (rs.next()) {
                count=rs.getInt("totalComment");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
            return count;
    }

    public boolean insertComment(int CommentID, String Contents, String ArticleID, String Author, String DatePost) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection con = null;
        PreparedStatement prs = null;
        int result = 0;
        try {
            con = DBUtils.makeCon();

            if (con != null) {

                String sql = "Insert into Comment(CommentID, Contents,ArticleID,Author ,DatePost) values(?,?,?,?,?)";

                //        
                prs = con.prepareStatement(sql);
                prs.setInt(1, CommentID);
                prs.setString(2, Contents);
                prs.setString(3, ArticleID);
                prs.setString(4, Author);
                prs.setString(5, DatePost);

                //
                result = prs.executeUpdate();
                if (result == 1) {
                    check = true;
                }

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

    public boolean getComments(String idOfArticle) throws ClassNotFoundException, SQLException, NamingException {
        listCommentDTO = new ArrayList<>();
        Connection con = null;
        PreparedStatement prs = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            con = DBUtils.makeCon();

            if (con != null) {
                String sql = "Select CommentID, Contents,ArticleID,Author ,DatePost"
                        + " From Comment "
                        + " Where ArticleID= ? "
                        + " Order by DatePost DESC";

                //        
//                stm= con.createStatement();
                prs = con.prepareStatement(sql);
                prs.setString(1, idOfArticle);

                //
//                stm.executeQuery(sql)
                rs = prs.executeQuery();
                while (rs.next()) {
                    String CommentID = rs.getString("CommentID");
                    String Contents = rs.getString("Contents");
                    String ArticleID = rs.getString("ArticleID");
                    String Author = rs.getString("Author");
                    String DatePost = rs.getString("DatePost");

                    check = true;
//                    boolean isAdmin = rs.getBoolean("isAdmin");
                    CommentDTO dto = new CommentDTO(CommentID, Contents, ArticleID, Author, DatePost);

                    this.listCommentDTO.add(dto);
                }

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
}
