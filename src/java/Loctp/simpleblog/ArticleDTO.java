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
public class ArticleDTO implements Serializable{
    private String articleID;
    private String title;
    private String description;
    private String contents;
    private String author;
    private String datePost;
    private String status;

    public ArticleDTO(String articleID, String title, String description, String contents, String author, String datePost, String status) {
        this.articleID = articleID;
        this.title = title;
        this.description = description;
        this.contents = contents;
        this.author = author;
        this.datePost = datePost;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    public ArticleDTO() {
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
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
