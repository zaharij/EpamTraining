/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import model.dao.impl.ArticlesDao;
import model.entitys.Article;

/**
 *
 * @author Zakhar
 */
public class ArticlesService {
    private ArticlesDao articlesDao = new ArticlesDao();
    
    /**
      * getArticleNamesByPeriodical
      * get article's names in periodical
      * get payed periodicals names 
      * @return array list of names
      */
     public List<String> getArticleNamesByPeriodical (String periodical){
         List<String> resultListNames = articlesDao.getAllArticleNamesByPeriodical(periodical);
         Collections.reverse(resultListNames);
         return resultListNames;
     }
          
     /**
      * getAboutArticleText
       get text about current article
     * @param title of the article
     * @return text about article
      */
     public String getAboutArticleText (String title){
         return articlesDao.getAboutArticleText(title);
     }
     
     /**
      * getArticleText
       get current article text
     * @param title of the article
     * @return text
      */
     public String getArticleText (String title){
         return articlesDao.getArticleText(title);
     }
     
     /**
      * getArticleDate
      * get article Date
      * @return 
      */
     public String getArticleDate (String title){
         return articlesDao.getArticleDate(title).toLocaleString();
     }
         
     /**
      * setArticleToDB
      * @param article 
      */
     public void setArticleToDB (Article article){
         Timestamp timestamp = new Timestamp(System.currentTimeMillis());
         article.setTimestamp(timestamp);
         articlesDao.insertNewArticle(article);
     }
     
     /**
     * deleteArticle
     * delete article
     * @param title
     * @param periodical 
     */
    public void deleteArticle (String title){
        articlesDao.deleteArticle(title);
    }
    
    /**
     * getNotPermittedArticles
     * @return 
     */
    public List<String> getNotPermittedArticles (){
        List<String> resultListNames = articlesDao.getNotPermittedArticles();
        Collections.reverse(resultListNames);
        return resultListNames;
    }
    
    /**
     * setBlockUser
     * @param email 
     */
    public void setArticlePermissionTrue (String title){
        articlesDao.setArticlePermission(title);
    }
}
