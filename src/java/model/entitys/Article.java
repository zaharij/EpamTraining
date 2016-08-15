/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entitys;

import java.sql.Timestamp;

/**
 *
 * @author Zakhar
 */
public class Article{
    private String authorsEmail;
    private String periodical;
    private Timestamp timestamp;
    private String title;
    private int permisssion;
    private String articleText;
    private String articleAnnotation;

    /**
     * @return the articleText
     */
    public String getArticleText() {
        return articleText;
    }

    /**
     * @param articleText the articleText to set
     */
    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the authorsEmail
     */
    public String getAuthorsEmail() {
        return authorsEmail;
    }

    /**
     * @param authorsEmail the authorsEmail to set
     */
    public void setAuthorsEmail(String authorsEmail) {
        this.authorsEmail = authorsEmail;
    }

    /**
     * @return the periodical
     */
    public String getPeriodical() {
        return periodical;
    }

    /**
     * @param periodical the periodical to set
     */
    public void setPeriodical(String periodical) {
        this.periodical = periodical;
    }

    /**
     * @return the timestamp
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the articleAnnotation
     */
    public String getArticleAnnotation() {
        return articleAnnotation;
    }

    /**
     * @param articleAnnotation the articleAnnotation to set
     */
    public void setArticleAnnotation(String articleAnnotation) {
        this.articleAnnotation = articleAnnotation;
    }

    /**
     * @return the permisssion
     */
    public int getPermisssion() {
        return permisssion;
    }

    /**
     * @param permisssion the permisssion to set
     */
    public void setPermisssion(int permisssion) {
        this.permisssion = permisssion;
    }
    
}
