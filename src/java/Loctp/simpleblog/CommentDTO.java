/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loctp.simpleblog;

import java.io.Serializable;

/**
 *
 * @author Loc
 */
public class CommentDTO implements Serializable{
    private String commentID;
    private String contents;
    private String articleID;
    private String author;
    private String datePost;

    public CommentDTO() {
    }
    
    public CommentDTO(String commentID, String contents, String articleID, String author, String datePost) {
        this.commentID = commentID;
        this.contents = contents;
        this.articleID = articleID;
        this.author = author;
        this.datePost = datePost;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDatePost() {
        return datePost;
    }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }
    
    
    
}
