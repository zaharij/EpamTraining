/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.connection.ConnectionPool;
import model.entitys.Article;

/**
 *
 * @author Zakhar
 */
public class ArticlesDao {
    public final static String GET_ARTICLES_FOR_PAY_SQL = "SELECT * FROM articles WHERE periodical = ?";
    public final static int PERIODICAL_GET_ARTICLES_FOR_PAY_SQL_NUMBER = 1;
    
    public final static String SET_ARTICLE_PERMISSION_SQL = "UPDATE articles SET permission = ? WHERE title = ?";
    public final static int PERMISSION_SET_ARTICLE_PERMISSION_SQL_NUMBER = 1;
    public final static int TITLE_SET_ARTICLE_PERMISSION_SQL_NUMBER = 2;
    
    public final static String DELETE_ARTICLE_SQL = "DELETE FROM articles WHERE title=?";
    public final static int TITLE_DELETE_ARTICLE_SQL_NUMBER = 1;
    
    public final static String GET_ARTICLE_BY_PERIODICAL_SQL = "SELECT * FROM articles WHERE periodical = ?";
    public final static int PERIODICAL_GET_ARTICLE_BY_PERIODICAL_SQL_NUMBER = 1;
    
    public final static String GET_ARTICLES_SQL = "SELECT * FROM articles";
    
    public final static String GET_ARTICLE_SQL = "SELECT * FROM articles WHERE title = ?";
    public final static int TITLE_GET_ARTICLE_SQL_NUMBER = 1;
    
    public final static String INSERT_INTO_ARTICLES_SQL = "INSERT INTO articles (periodical, title, author, date, about, text, permission) VALUES (?,?,?,?,?,?,?)";
    public final static int PERIODICAL_INSERT_INTO_ARTICLES_SQL_NUMBER = 1;
    public final static int TITLE_INSERT_INTO_ARTICLES_SQL_NUMBER = 2;
    public final static int AUTHOR_INSERT_INTO_ARTICLES_SQL_NUMBER = 3;
    public final static int DATE_INSERT_INTO_ARTICLES_SQL_NUMBER = 4;
    public final static int ABOUT_INSERT_INTO_ARTICLES_SQL_NUMBER = 5;
    public final static int TEXT_INSERT_INTO_ARTICLES_SQL_NUMBER = 6;
    public final static int PERMISSION_INSERT_INTO_ARTICLES_SQL_NUMBER = 7;
    
    public final static String ARTICLES_PERIODICAL_COLUMN_NAME = "periodical";
    public final static String ARTICLES_TITLE_COLUMN_NAME = "title";
    public final static String ARTICLES_AUTHOR_COLUMN_NAME = "author";
    public final static String ARTICLES_DATE_COLUMN_NAME = "date";
    public final static String ARTICLES_ABOUT_ARTICLE_COLUMN_NAME = "about";
    public final static String ARTICLES_TEXT_COLUMN_NAME = "text";
    public final static String ARTICLES_PERMISSION_COLUMN_NAME = "permission";
    
    public final static int ARTICLE_IS_PERMITTED = 1;
    public final static int ARTICLE_IS_NOT_PERMITTED = 0;
    
    private ConnectionPool connectionPool = new ConnectionPool();
    private PreparedStatement preparedStatement;
    Statement statement;
    private ResultSet resultSet;
    private UserDao userConstantsDao = new UserDao();
    
    /**
     * getAuthorsForPayList
     * <p>
     * get all authors of given periodical
     * , which articles was posted before current date
     * </p>
     * @param periodical - periodical identifier
     * @param dateCurrent - current date (Timestamp)
     * @return list of authors, or null in case failure
     */
    public List<String> getAuthorsForPayList (String periodical, Timestamp dateCurrent){
        List<String> authorsForPayList = new ArrayList<>();
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(GET_ARTICLES_FOR_PAY_SQL);
            preparedStatement.setString(PERIODICAL_GET_ARTICLES_FOR_PAY_SQL_NUMBER, periodical);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getTimestamp(ARTICLES_DATE_COLUMN_NAME).before(dateCurrent)){
                    authorsForPayList.add(resultSet.getString(ARTICLES_AUTHOR_COLUMN_NAME));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticlesDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return authorsForPayList;
    }
    
    /**
     * setArticlePermission
     * <p>
     * sets article permission (true)
     * </p>
     * @param title - article's identifier
     * @return result (boolean), true in case success, false in case failure
     */
    public boolean setArticlePermission (String title){
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(SET_ARTICLE_PERMISSION_SQL);
            preparedStatement.setInt(PERMISSION_SET_ARTICLE_PERMISSION_SQL_NUMBER, ARTICLE_IS_PERMITTED);
            preparedStatement.setString(TITLE_SET_ARTICLE_PERMISSION_SQL_NUMBER, title);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArticlesDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
     /**
     * deleteArticle
     * <p>
     * deletes article by title and subject
     * </p>
     * @param title - article's identifier
     * @return result (boolean), true in case success, false in case failure
     */
    public boolean deleteArticle (String title){
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(DELETE_ARTICLE_SQL);
            preparedStatement.setString(TITLE_DELETE_ARTICLE_SQL_NUMBER, title);
            preparedStatement.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(ArticlesDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    /**
     * getNotPermittedArticlesInPeriodical
     * <p>
     * returns all article's names in periodical, which is not permitted
     * </p>
     * @param periodical - periodical identifier
     * @return list of not permitted articles in periodical
     */
    public List<String> getNotPermittedArticlesInPeriodical (String periodical){
        List<String> namesList = new ArrayList<>();
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(GET_ARTICLE_BY_PERIODICAL_SQL);
            preparedStatement.setString(PERIODICAL_GET_ARTICLE_BY_PERIODICAL_SQL_NUMBER, periodical);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(ARTICLES_PERMISSION_COLUMN_NAME) == ARTICLE_IS_NOT_PERMITTED){
                    namesList.add(resultSet.getString(ARTICLES_TITLE_COLUMN_NAME));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticlesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return namesList;
    }
    
    /**
     * getNotPermittedArticles
     * <p>
     * returns all not permitted articles
     * </p>
     * @return list of not permitted articles, or null in case failure
     */
    public List<String> getNotPermittedArticles (){
        List<String> namesList = new ArrayList<>();
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(GET_ARTICLES_SQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(ARTICLES_PERMISSION_COLUMN_NAME) == ARTICLE_IS_NOT_PERMITTED){
                    namesList.add(resultSet.getString(ARTICLES_TITLE_COLUMN_NAME));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticlesDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return namesList;
    }
    
    /**
     * getAboutArticleText
     * returns the annotation of current article
     * @param title - articles identifier
     * @return annotation (String), or null in case failure
     */
    public String getAboutArticleText(String title){
        String result = null;
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(GET_ARTICLE_SQL);
            preparedStatement.setString(TITLE_GET_ARTICLE_SQL_NUMBER, title);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getString(ARTICLES_ABOUT_ARTICLE_COLUMN_NAME);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticlesDao.class.getName()).log(Level.SEVERE, null, ex);
            return result;
        }
        return result;
    }
    
    /**
     * getArticleText
     * returns the text of current article
     * @param title - article's identifier
     * @return article's text, or null in case failure
     */
    public String getArticleText(String title){
        String result = null;
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(GET_ARTICLE_SQL);
            preparedStatement.setString(TITLE_GET_ARTICLE_SQL_NUMBER, title);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getString(ARTICLES_TEXT_COLUMN_NAME);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticlesDao.class.getName()).log(Level.SEVERE, null, ex);
            return result;
        }
        return result;
    }
    
    /**
     * getArticleEmailAuthor
     * returns the email of author of the current article
     * @param title - article's identifier
     * @return author's email, or null in case failure
     */
    public String getArticleEmailAuthor(String title){
        String result = null;
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(GET_ARTICLE_SQL);
            preparedStatement.setString(TITLE_GET_ARTICLE_SQL_NUMBER, title);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getString(ARTICLES_AUTHOR_COLUMN_NAME);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticlesDao.class.getName()).log(Level.SEVERE, null, ex);
            return result;
        }
        return result;
    }
    
    /**
     * getArticleDate
     * returns article's publication date
     * @param title - article's identifier
     * @return publication date (Timestamp)
     */
    public Timestamp getArticleDate(String title){
        Timestamp result = null;
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(GET_ARTICLE_SQL);
            preparedStatement.setString(TITLE_GET_ARTICLE_SQL_NUMBER, title);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getTimestamp(ARTICLES_DATE_COLUMN_NAME);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticlesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
        
    /**
     * insertNewArticle
     * inserts new article into the data base
     * @param article Article object, which contains all needed parameters
     * @return result (boolean), true in case success, false in case failure
     */
    public boolean insertNewArticle (Article article){
        try (Connection connection = connectionPool.getConnection();){          
            try {
                connection.setAutoCommit(false);
                preparedStatement = connection.prepareStatement(INSERT_INTO_ARTICLES_SQL);
                preparedStatement.setString(PERIODICAL_INSERT_INTO_ARTICLES_SQL_NUMBER, article.getPeriodical());
                preparedStatement.setString(TITLE_INSERT_INTO_ARTICLES_SQL_NUMBER, article.getTitle());
                preparedStatement.setString(AUTHOR_INSERT_INTO_ARTICLES_SQL_NUMBER, article.getAuthorsEmail());
                preparedStatement.setTimestamp(DATE_INSERT_INTO_ARTICLES_SQL_NUMBER, article.getTimestamp());
                preparedStatement.setString(ABOUT_INSERT_INTO_ARTICLES_SQL_NUMBER, article.getArticleAnnotation());
                preparedStatement.setString(TEXT_INSERT_INTO_ARTICLES_SQL_NUMBER, article.getArticleText());
                preparedStatement.setInt(PERMISSION_INSERT_INTO_ARTICLES_SQL_NUMBER, article.getPermisssion());
                preparedStatement.execute();
                connection.commit();
            }catch (SQLException ex) {
                connection.rollback();
                Logger.getLogger(ArticlesDao.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticlesDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
     /**
     * getAllArticleNamesByPeriodical
     * get all article's names in periodical
     * get all periodicals names from db
     * @return list of names
     */
    public List<String> getAllArticleNamesByPeriodical (String periodical){
        List<String> namesList = new ArrayList<>();
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(GET_ARTICLE_BY_PERIODICAL_SQL);
            preparedStatement.setString(PERIODICAL_GET_ARTICLE_BY_PERIODICAL_SQL_NUMBER, periodical);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(ARTICLES_PERMISSION_COLUMN_NAME) == ARTICLE_IS_PERMITTED){
                    namesList.add(resultSet.getString(ARTICLES_TITLE_COLUMN_NAME));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticlesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return namesList;
    }
}
