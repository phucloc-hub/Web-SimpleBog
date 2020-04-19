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
public class ArticlePostErrors implements Serializable{
    private String titleLength;
    private String descriptionLength;
    private String contentsLength;

    public ArticlePostErrors() {
    }

    public String getTitleLength() {
        return titleLength;
    }

    public void setTitleLength(String titleLength) {
        this.titleLength = titleLength;
    }

    public String getDescriptionLength() {
        return descriptionLength;
    }

    public void setDescriptionLength(String descriptionLength) {
        this.descriptionLength = descriptionLength;
    }

    public String getContentsLength() {
        return contentsLength;
    }

    public void setContentsLength(String contentsLength) {
        this.contentsLength = contentsLength;
    }
    
    
    
    
    
}
