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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Loc
 */
public class ArticleDAO implements Serializable {

    private List<ArticleDTO> listArticleDTO;

    public List<ArticleDTO> getListArticleDTO() {
        return listArticleDTO;
    }


    public boolean ActiveArticle(String ArticleID) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = true;
        Connection con = null;
        PreparedStatement prs = null;
        int result = 0;
        try {
            con = DBUtils.makeCon();

            String sql = "Update Article Set Status='Active' where ArticleID = ?";

            prs = con.prepareStatement(sql);
            prs.setString(1, ArticleID);

            result = prs.executeUpdate();
            if (result == 0) {
                check = false;
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
    
    public boolean deletedArticle(String ArticleID) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = true;
        Connection con = null;
        PreparedStatement prs = null;
        int result = 0;
        try {
            con = DBUtils.makeCon();

            String sql = "Update Article Set Status='Deleted' where ArticleID = ?";

            prs = con.prepareStatement(sql);
            prs.setString(1, ArticleID);

            result = prs.executeUpdate();
            if (result == 0) {
                check = false;
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

    public List<ArticleDTO> getArticleByUserEmail(String userEmail) throws ClassNotFoundException, SQLException, NamingException {
        listArticleDTO = new ArrayList<>();
        Connection con = null;
        PreparedStatement prs = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeCon();

            if (con != null) {
                String sql = "Select ArticleID, Title, Description, Contents,Author,DatePost,Status"
                        + " From Article "
                        + " Where Author like ? ";

                //        
                prs = con.prepareStatement(sql);
//                = con.createStatement();
//                prs = con.prepareStatement(sql);
                prs.setString(1, userEmail);
//                prs.setString(2, "Actice");
                //
                rs = prs.executeQuery();
//                stm.executeQuery(sql)
//                rs = stm.executeQuery(sql);
                while (rs.next()) {
                    String ArticleID = rs.getString("ArticleID");
                    String Title = rs.getString("Title");
                    String Description = rs.getString("Description");
                    String Contents = rs.getString("Contents");
                    String Author = rs.getString("Author");
                    String DatePost = rs.getString("DatePost");
                    String Status = rs.getString("Status");
//                    boolean isAdmin = rs.getBoolean("isAdmin");
                    ArticleDTO dto = new ArticleDTO(ArticleID, Title, Description, Contents, Author, DatePost, Status);

                    this.listArticleDTO.add(dto);
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

        return listArticleDTO;
    }

    public boolean insertArticle(int ArticleID, String Title, String Author, String Contents, String Description, String DatePost, String Status) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection con = null;
        PreparedStatement prs = null;
        int result = 0;
        try {
            con = DBUtils.makeCon();

            String sql = "Insert into Article(ArticleID,Title,Description,Contents,Author,DatePost,Status) "
                    + "values(?,?,?,?,?,?,?) ";

            prs = con.prepareStatement(sql);

            prs.setInt(1, ArticleID);
            prs.setString(2, Title);
            prs.setString(3, Description);
            prs.setString(4, Contents);
            prs.setString(5, Author);
            prs.setString(6, DatePost);
            prs.setString(7, Status);

            result = prs.executeUpdate();
            if (result == 1) {
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

    public int getTotalArticle() throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = DBUtils.makeCon();

            String sql = "Select COUNT(ArticleID) AS totalArticle from Article";

            stm = con.createStatement();

            rs = stm.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("totalArticle");
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

    public ArticleDTO getArticleDetails(String articleID) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement prs = null;
        ResultSet rs = null;
        ArticleDTO dto = new ArticleDTO();
        try {
            con = DBUtils.makeCon();

            if (con != null) {
                String sql = "Select ArticleID, Title, Description, Contents,Author,DatePost,Status"
                        + " From Article "
                        + " Where ArticleID like ? ";

                //        
                prs = con.prepareStatement(sql);
//                = con.createStatement();
//                prs = con.prepareStatement(sql);
                prs.setString(1, articleID);

                //
                rs = prs.executeQuery();
//                stm.executeQuery(sql)
//                rs = stm.executeQuery(sql);
                if (rs.next()) {
                    String ArticleID = rs.getString("ArticleID");
                    String Title = rs.getString("Title");
                    String Description = rs.getString("Description");
                    String Contents = rs.getString("Contents");
                    String Author = rs.getString("Author");
                    String DatePost = rs.getString("DatePost");
                    String Status = rs.getString("Status");
//                    boolean isAdmin = rs.getBoolean("isAdmin");
                    dto = new ArticleDTO(ArticleID, Title, Description, Contents, Author, DatePost, Status);

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

        return dto;
    }
    
    
    //get total rows of result by search
    public int getTotalArticleSearchUser(String contents) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement prs = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = DBUtils.makeCon();

            String sql = "Select COUNT(ArticleID) AS totalArticle from Article where Contents LIKE ?";

            prs= con.prepareStatement(sql);
            prs.setString(1, "%"+contents+"%");
            

            rs = prs.executeQuery();
            if (rs.next()) {
                count = rs.getInt("totalArticle");
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
        return count;
    }

    public List<ArticleDTO> searchArticleByContentPaging(String contents,int curPage,int numOfArt) throws ClassNotFoundException, SQLException, NamingException {
        listArticleDTO = new ArrayList<>();
        Connection con = null;
        PreparedStatement prs = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeCon();

            if (con != null) {
                String sql = "Select ArticleID, Title, Description, Contents,Author,DatePost,Status"
                        + " From Article "
                        + " Where Contents like ? and Status=? "
                        + " Order by DatePost DESC "
                        + " offset ? rows fetch next ? rows only";

                //        
                prs = con.prepareStatement(sql);
//                = con.createStatement();
//                prs = con.prepareStatement(sql);
                prs.setString(1, "%" + contents + "%");
                prs.setString(2, "Active");
                prs.setInt(3, curPage);
                prs.setInt(4, numOfArt);
                //
                rs = prs.executeQuery();
//                stm.executeQuery(sql)
//                rs = stm.executeQuery(sql);
                while (rs.next()) {
                    String ArticleID = rs.getString("ArticleID");
                    String Title = rs.getString("Title");
                    String Description = rs.getString("Description");
                    String Contents = rs.getString("Contents");
                    String Author = rs.getString("Author");
                    String DatePost = rs.getString("DatePost");
                    String Status = rs.getString("Status");
//                    boolean isAdmin = rs.getBoolean("isAdmin");
                    ArticleDTO dto = new ArticleDTO(ArticleID, Title, Description, Contents, Author, DatePost, Status);

                    this.listArticleDTO.add(dto);
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

        return listArticleDTO;
    }

    public List<ArticleDTO> getArticle(int curPage,int numOfArt) throws ClassNotFoundException, SQLException, NamingException {
        listArticleDTO = new ArrayList<>();
        Connection con = null;
        PreparedStatement prs = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeCon();

            if (con != null) {
                String sql = "Select ArticleID, Title, Description, Contents,Author,DatePost,Status"
                        + " From Article "
                        + " Where Status='Active' "
                        + " Order by DatePost DESC "
                        + " offset ? rows fetch next ? rows only";

                //        
                prs= con.prepareStatement(sql);
                prs.setInt(1, curPage);
                prs.setInt(2, numOfArt);
//                stm = con.createStatement();
//                prs = con.prepareStatement(sql);
//                prs.setString(1, "%" + searchValue + "%");

                //
//                stm.executeQuery(sql)
                rs = prs.executeQuery();
                while (rs.next()) {
                    String ArticleID = rs.getString("ArticleID");
                    String Title = rs.getString("Title");
                    String Description = rs.getString("Description");
                    String Contents = rs.getString("Contents");
                    String Author = rs.getString("Author");
                    String DatePost = rs.getString("DatePost");
                    String Status = rs.getString("Status");
//                    boolean isAdmin = rs.getBoolean("isAdmin");
                    ArticleDTO dto = new ArticleDTO(ArticleID, Title, Description, Contents, Author, DatePost, Status);

                    this.listArticleDTO.add(dto);
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

        return listArticleDTO;
    }

    public List<ArticleDTO> searchArticleByAd(String status, String typeSearch, String searchVal) throws SQLException, ClassNotFoundException, NamingException {
        listArticleDTO = new ArrayList<>();

        Connection con = null;
        PreparedStatement prs = null;
        ResultSet rs = null;
        String sql="";
        try {
            con = DBUtils.makeCon();

            if (con != null) {
                if (typeSearch.equals("Contents")) {
                    sql = "Select ArticleID, Title, Description, Contents,Author,DatePost,Status"
                            + " From Article "
                            + " Where Status = ? and Contents LIKE ?"
                            + " Order by DatePost DESC ";
                } else {
                    sql = "Select ArticleID, Title, Description, Contents,Author,DatePost,Status"
                            + " From Article "
                            + " Where Status = ? and Title LIKE ?"
                            + " Order by DatePost DESC ";
                }

                //        
                prs = con.prepareStatement(sql);
//                = con.createStatement();
//                prs = con.prepareStatement(sql);
                prs.setString(1, status);
                prs.setString(2, "%" + searchVal + "%");
                //
                rs = prs.executeQuery();
//                stm.executeQuery(sql)
//                rs = stm.executeQuery(sql);
                while (rs.next()) {
                    String ArticleID = rs.getString("ArticleID");
                    String Title = rs.getString("Title");
                    String Description = rs.getString("Description");
                    String Contents = rs.getString("Contents");
                    String Author = rs.getString("Author");
                    String DatePost = rs.getString("DatePost");
                    String Status = rs.getString("Status");
//                    boolean isAdmin = rs.getBoolean("isAdmin");
                    ArticleDTO dto = new ArticleDTO(ArticleID, Title, Description, Contents, Author, DatePost, Status);

                    this.listArticleDTO.add(dto);
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
        return listArticleDTO;
    }

}
