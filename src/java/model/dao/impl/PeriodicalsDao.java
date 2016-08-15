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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.connection.ConnectionPool;
import model.dao.impl.AdminDao.*;
import model.entitys.Periodical;
import model.dao.impl.UserDao.*;

/**
 *
 * @author Zakhar
 */
public class PeriodicalsDao {
    public final static String INSERT_INTO_PERIODICALS_SQL = "INSERT INTO periodicals (subject, price, followersnum, articlenum, money, authorsnum) VALUES (?,?,?,?,?,?)";
    public final static int SUBJECT_INSERT_INTO_PERIODICALS_SQL_NUMBER = 1;
    public final static int PRICE_INSERT_INTO_PERIODICALS_SQL_NUMBER = 2;
    
    public final static String DELETE_PERIODICAL_SIGNED_SQL = "DELETE FROM followers WHERE periodical = ?";
    public final static int PERIODICAL_DELETE_PERIODICAL_SIGNED_SQL_NUMBER = 1;
    
    public final static String DELETE_PERIODICAL_ARTICLES_SQL = "DELETE FROM articles WHERE periodical = ?";
    public final static int PERIODICAL_DELETE_PERIODICAL_ARTICLES_SQL_NUMBER = 1;
    
    public final static String DELETE_PERIODICAL_SQL = "DELETE FROM periodicals WHERE subject = ?";
    public final static int SUBJECT_DELETE_PERIODICAL_SQL_NUMBER = 1;
    
    public final static String GET_ALL_PERIODICALS_SQL = "SELECT * FROM periodicals";
    
    public final static String GET_PERIODICALS_BY_USER_EMAIL_SQL = "SELECT * FROM followers WHERE email = ?";
    public final static int EMAIL_GET_PERIODICALS_BY_USER_EMAIL_SQL_NUMBER = 1;
    
    public final static String GET_PERIODICAL_BY_SUBJECT_SQL = "SELECT * FROM periodicals WHERE subject = ?";
    public final static int SUBJECT_GET_PERIODICAL_BY_SUBJECT_SQL_NUMBER = 1;
    
    public final static String PERIODICALS_SUBJECT_COLUMN_NUMBER = "subject";
    public final static String PERIODICALS_PRICE_COLUMN_NUMBER = "price";
    public final static String PERIODICALS_FOLLOWERSNUM_COLUMN_NUMBER = "followersnum";
    public final static String PERIODICALS_ARTICLENUM_COLUMN_NUMBER = "articlenum";
    public final static String PERIODICALS_MONEY_COLUMN_NUMBER = "money";
    public final static String PERIODICALS_AUTHORSNUM_COLUMN_NUMBER = "authorsnum";
    
    private ConnectionPool connectionPool = new ConnectionPool();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private AdminDao adminConstantsDao = new AdminDao();
    private UserDao userConstantsDao = new UserDao();
    
    /**
     * insertPeriodicalIntoDb
     * inserts new periodical to the data base 
     * @param periodical - Periodical object, contains all info about new periodical
     * @return result (boolean), true in case success, false in case failure
     */
    public boolean insertPeriodicalIntoDb(Periodical periodical){        
        try (Connection connection = connectionPool.getConnection();){            
            try {
                connection.setAutoCommit(false);
                preparedStatement = connection.prepareStatement(INSERT_INTO_PERIODICALS_SQL);
                preparedStatement.setString(SUBJECT_INSERT_INTO_PERIODICALS_SQL_NUMBER, periodical.getTheme());
                preparedStatement.setDouble(PRICE_INSERT_INTO_PERIODICALS_SQL_NUMBER, periodical.getMounthPrice());
                preparedStatement.execute();
                connection.commit();
            }catch (SQLException ex) {
                connection.rollback();
                Logger.getLogger(PeriodicalsDao.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeriodicalsDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    /**
     * deletePeriodical
     * deletes periodical from the data base
     * @param subject - periodical identifier
     * @return result (boolean), true in case success, false in case failure
     */
    public boolean deletePeriodical (String subject){
        deletePeriodicalSigned(subject);
        deletePeriodicalArticles(subject);
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(DELETE_PERIODICAL_SQL);
            preparedStatement.setString(SUBJECT_DELETE_PERIODICAL_SQL_NUMBER, subject);
            preparedStatement.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(PeriodicalsDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    /**
     * deletePeriodicalSigned
     * deletes signed periodical
     * @param subject - periodical identifier
     * @return result (boolean), true in case success, false in case failure
     */
    public boolean deletePeriodicalSigned (String subject){
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(DELETE_PERIODICAL_SIGNED_SQL);
            preparedStatement.setString(PERIODICAL_DELETE_PERIODICAL_SIGNED_SQL_NUMBER, subject);
            preparedStatement.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(PeriodicalsDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    /**
     * deletePeriodicalArticles
     * deletes all periodical articles
     * @param subject - periodical identifier
     * @return result (boolean), true in case success, false in case failure
     */
    public void deletePeriodicalArticles (String subject){
        Connection connection = connectionPool.getConnection();
        try {
            preparedStatement = connection.prepareStatement(DELETE_PERIODICAL_ARTICLES_SQL);
            preparedStatement.setString(PERIODICAL_DELETE_PERIODICAL_ARTICLES_SQL_NUMBER, subject);
            preparedStatement.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(PeriodicalsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * getAllPeriodicals
     * returns all periodical names from data base
     * @return list of names, or null in case failure
     */
    public List<String> getAllPeriodicals (){
        List<String> namesList = new ArrayList<>();
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(GET_ALL_PERIODICALS_SQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                namesList.add(resultSet.getString(PERIODICALS_SUBJECT_COLUMN_NUMBER));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeriodicalsDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return namesList;
    }
    
    /**
     * getPayedPeriodicals
     * returns payed periodicals names from data base
     * @return list of names, or null in case failure
     */
    public List<String> getPayedPeriodicals (String email){
        List<String> namesList = new ArrayList<>();
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(GET_PERIODICALS_BY_USER_EMAIL_SQL);
            preparedStatement.setString(EMAIL_GET_PERIODICALS_BY_USER_EMAIL_SQL_NUMBER, email);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                namesList.add(resultSet.getString(adminConstantsDao.FOLLOWERS_PERIODICAL_COLUMN_NAME));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeriodicalsDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return namesList;
    }
    
    /**
     * getAuthorsNumberBySubject
     * returns authors number in given periodical
     * @param subject periodical identifier
     * @return number, or (-1) in case failure
     */
    public int getAuthorsNumberBySubject (String subject){
        int result = userConstantsDao.RESULT_SQL_EXCEPTION;
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(GET_PERIODICAL_BY_SUBJECT_SQL);
            preparedStatement.setString(SUBJECT_GET_PERIODICAL_BY_SUBJECT_SQL_NUMBER, subject);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result += resultSet.getInt(PERIODICALS_AUTHORSNUM_COLUMN_NUMBER);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeriodicalsDao.class.getName()).log(Level.SEVERE, null, ex);
            return result;
        }
        return result;
    }
    
    /**
     * getArticlesNumberBySubject
     * returns articles number in given periodical
     * @param subject - periodical identifier
     * @return number, or (-1) in case failure
     */
    public int getArticlesNumberBySubject (String subject){
        int result = userConstantsDao.RESULT_SQL_EXCEPTION;
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(GET_PERIODICAL_BY_SUBJECT_SQL);
            preparedStatement.setString(SUBJECT_GET_PERIODICAL_BY_SUBJECT_SQL_NUMBER, subject);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result += resultSet.getInt(PERIODICALS_ARTICLENUM_COLUMN_NUMBER);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeriodicalsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    /**
     * getPeriodicalPrice
     * returns periodical price by subject
     * @param subject - periodical identifier
     * @return price, or (-1) in case failure
     */
    public double getPeriodicalPrice(String subject){
        double result = userConstantsDao.RESULT_SQL_EXCEPTION;
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(GET_PERIODICAL_BY_SUBJECT_SQL);
            preparedStatement.setString(SUBJECT_GET_PERIODICAL_BY_SUBJECT_SQL_NUMBER, subject);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getDouble(PERIODICALS_PRICE_COLUMN_NUMBER);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeriodicalsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    /**
     * getPeriodicalSubscribedDate
     * returns the date end subscription
     * @param subject - periodical identifier
     * @return date (Timestamp), or null in case failure
     */
    public Timestamp getPeriodicalSubscribedDate(String email, String periodical){
        Timestamp result = null;
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(adminConstantsDao.GET_SUBSCRIPED_PERIODICAL_SQL);
            preparedStatement.setString(adminConstantsDao.EMAIL_GET_SUBSCRIPED_PERIODICAL_SQL_NUMBER, email);
            preparedStatement.setString(adminConstantsDao.PERIODICAL_GET_SUBSCRIPED_PERIODICAL_SQL_NUMBER, periodical);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (periodical.equals(resultSet.getString(adminConstantsDao.FOLLOWERS_PERIODICAL_COLUMN_NAME))){
                    result = resultSet.getTimestamp(adminConstantsDao.FOLLOWERS_DATE_COLUMN_NAME);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeriodicalsDao.class.getName()).log(Level.SEVERE, null, ex);
            return result;
        }
        return result;
    }
    
    /**
     * getFollowersNumberBySubject
     * returns followers number by given periodical
     * @param subject - periodical identifier
     * @return number, or (-1) in case failure
     */
    public int getFollowersNumberBySubject (String subject){
        int result = userConstantsDao.RESULT_SQL_EXCEPTION;
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(GET_PERIODICAL_BY_SUBJECT_SQL);
            preparedStatement.setString(SUBJECT_GET_PERIODICAL_BY_SUBJECT_SQL_NUMBER, subject);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result += resultSet.getInt(PERIODICALS_FOLLOWERSNUM_COLUMN_NUMBER);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeriodicalsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
