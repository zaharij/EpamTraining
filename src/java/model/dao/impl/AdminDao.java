/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;
import model.connection.ConnectionPool;
import model.dao.impl.UserDao.*;

/**
 * AdminDao
 * @author Zakhar
 */
public class AdminDao {
    public final static String GET_ADMIN_BY_LOGIN_SQL = "SELECT * FROM superadmin WHERE login = ?";
    public final static int LOGIN_SQL_GET_ADMIN_BY_LOGIN_NUMBER = 1;
    
    public final static String SET_ADMIN_RIGHTS_SQL = "UPDATE user SET is_admin = ? WHERE email = ?";
    public final static int IS_ADMIN_SET_ADMIN_RIGHTS_SQL_NUMBER = 1;
    public final static int EMAIL_SET_ADMIN_RIGHTS_SQL_NUMBER = 2;
    
    public final static String SET_NEW_NEXT_DATE_TO_FOLLOWERS_SQL = "UPDATE followers SET nextdate = ? WHERE email = ? AND periodical = ?";
    public final static int NEXTDATE_SET_NEW_NEXT_DATE_TO_FOLLOWERS_SQL_NUMBER = 1;
    public final static int EMAIL_SET_NEW_NEXT_DATE_TO_FOLLOWERS_SQL_NUMBER = 2;
    public final static int PERIODICAL_SET_NEW_NEXT_DATE_TO_FOLLOWERS_SQL_NUMBER = 3;
    
    public final static String GET_FOLLOWERS_ALL_SQL = "SELECT * FROM followers";
    
    public final static String SET_MONEY_TO_FOLLOWERS_SQL = "UPDATE followers SET money = ? WHERE email = ? AND periodical = ?";
    public final static int MONEY_SET_MONEY_TO_FOLLOWERS_SQL_NUMBER = 1;
    public final static int EMAIL_SET_MONEY_TO_FOLLOWERS_SQL_NUMBER = 2;
    public final static int PERIODICAL_SET_MONEY_TO_FOLLOWERS_SQL_NUMBER = 3;
    
    public final static String GET_SUBSCRIPED_PERIODICAL_SQL = "SELECT * FROM followers WHERE email = ? AND periodical = ?";
    public final static int EMAIL_GET_SUBSCRIPED_PERIODICAL_SQL_NUMBER = 1;
    public final static int PERIODICAL_GET_SUBSCRIPED_PERIODICAL_SQL_NUMBER = 2;
    
    public final static String DELETE_FOLLOWERS_BY_END_DATE_SQL = "DELETE FROM followers WHERE date < ?";
    public final static int DATE_DELETE_FOLLOWERS_BY_END_DATE_SQL_NUMBER = 1;
    
    public final static String ADMIN_LOGIN_COLUMN_NUMBER = "login";
    public final static String ADMIN_PASSWORD_COLUMN_NUMBER = "password";
    
    public final static String FOLLOWERS_EMAIL_COLUMN_NAME = "email";
    public final static String FOLLOWERS_PERIODICAL_COLUMN_NAME = "periodical";
    public final static String FOLLOWERS_MONEY_COLUMN_NAME = "money";
    public final static String FOLLOWERS_DATE_COLUMN_NAME = "date";
    public final static String FOLLOWERS_MOUNTHPRICE_COLUMN_NAME = "monthprice";
    public final static String FOLLOWERS_DATE_FREEZ_COLUMN_NAME = "nextdate";
    
    private ConnectionPool connectionPool = new ConnectionPool();
    private PreparedStatement preparedStatement;
    Statement statement;
    private ResultSet resultSet;
    private UserDao userConstantsDao;
    
    /**
     * getSuperAdminPassword
     * <p>
     * returns administrator's password as a hash code
     * </p>
     * @param login - administrator's identifier
     * @return hash code of the password, or (-1) if it is SQLException
     */
    public int getSuperAdminPassword(String login){
        int value = userConstantsDao.RESULT_SQL_EXCEPTION;
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(GET_ADMIN_BY_LOGIN_SQL);
            preparedStatement.setString(LOGIN_SQL_GET_ADMIN_BY_LOGIN_NUMBER, login);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                value = resultSet.getInt(ADMIN_PASSWORD_COLUMN_NUMBER);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            return value;
        }
        return value;
    }
    
    /**
     * setAdminRights
     * <p>
     * sets administrator rights to current user
     * </p>
     * @param email - user's identifier
     * @return result (boolean), true in case success, and false in case failure
     */
    public boolean setAdminRights (String email, int rights){
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(SET_ADMIN_RIGHTS_SQL);
            preparedStatement.setInt(IS_ADMIN_SET_ADMIN_RIGHTS_SQL_NUMBER, userConstantsDao.IS_ADMIN);
            preparedStatement.setInt(IS_ADMIN_SET_ADMIN_RIGHTS_SQL_NUMBER, rights);
            preparedStatement.setString(EMAIL_SET_ADMIN_RIGHTS_SQL_NUMBER, email);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    /**
     * updateDateFreezed
     * <p>
     * updates date of freezing money
     * </p>
     * @param email - user's identifier
     * @param periodical - periodical identifier
     * @param newDate new date to set 
     */
    public boolean updateDateFreezed (String email, String periodical, Timestamp newDate){
        //Timestamp newDateFreez = date;
        //newDateFreez.setMonth(newDateFreez.getMonth() + 1);
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(SET_NEW_NEXT_DATE_TO_FOLLOWERS_SQL);
            preparedStatement.setTimestamp(NEXTDATE_SET_NEW_NEXT_DATE_TO_FOLLOWERS_SQL_NUMBER, newDate);
            preparedStatement.setString(EMAIL_SET_NEW_NEXT_DATE_TO_FOLLOWERS_SQL_NUMBER, email);
            preparedStatement.setString(PERIODICAL_SET_NEW_NEXT_DATE_TO_FOLLOWERS_SQL_NUMBER, periodical);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    /**
     * updateFreezed
     * <p>
     * updates "freezed" parameters - money and date
     * </p>
     * @param dateCurrent - current date
     * @return result (boolean) - true in case success, false in case failure
     */
    public boolean updateFreezed(Timestamp dateCurrent){
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(GET_FOLLOWERS_ALL_SQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getTimestamp(FOLLOWERS_DATE_FREEZ_COLUMN_NAME).before(dateCurrent)){
                    updateDateFreezed(resultSet.getString(FOLLOWERS_EMAIL_COLUMN_NAME)
                            , resultSet.getString(FOLLOWERS_PERIODICAL_COLUMN_NAME)
                            , resultSet.getTimestamp(FOLLOWERS_DATE_FREEZ_COLUMN_NAME));
                    updateMounthFreezMoney(resultSet.getString(FOLLOWERS_EMAIL_COLUMN_NAME)
                            , resultSet.getString(FOLLOWERS_PERIODICAL_COLUMN_NAME));
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    /**
     * updateMounthFreezMoney
     * <p>
     * updates user's money, payed for the periodical
     * </p>
     * @param email - user's identifier
     * @param periodical - periodical identifier
     */
    public void updateMounthFreezMoney(String email, String periodical){
        double currentAllPayedMoney = getPayedMoneyForPeriodical (email, periodical);
        double monthPrice = getMonthPriceForPeriodical (email, periodical);
        double newMoney;
        if (currentAllPayedMoney >= monthPrice){
            newMoney = currentAllPayedMoney - monthPrice;
            try {
                preparedStatement = connectionPool.getConnection().prepareStatement(SET_MONEY_TO_FOLLOWERS_SQL);
                preparedStatement.setDouble(MONEY_SET_MONEY_TO_FOLLOWERS_SQL_NUMBER, newMoney);
                preparedStatement.setString(EMAIL_SET_MONEY_TO_FOLLOWERS_SQL_NUMBER, email);
                preparedStatement.setString(PERIODICAL_SET_MONEY_TO_FOLLOWERS_SQL_NUMBER, periodical);
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
    }
    
    /**
     * getMonthPriceForPeriodical
     * <p>
     * returns user's month price for periodical
     * </p>
     * @param email - user's identifier
     * return money (double), if SQLException returns (-1)
     */
    public double getMonthPriceForPeriodical (String email, String periodical){
        double result = userConstantsDao.RESULT_SQL_EXCEPTION;
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(GET_SUBSCRIPED_PERIODICAL_SQL);
            preparedStatement.setString(EMAIL_GET_SUBSCRIPED_PERIODICAL_SQL_NUMBER, email);
            preparedStatement.setString(PERIODICAL_GET_SUBSCRIPED_PERIODICAL_SQL_NUMBER, periodical);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getDouble(FOLLOWERS_MOUNTHPRICE_COLUMN_NAME);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            return result;
        }
        return result;
    }
    
    /**
     * getPayedMoneyForPeriodical
     * <p>
     * returns all payed money for periodical
     * </p>
     * @param email - user's identifier
     * return money (double), in case failure returns (-1)
     */
    public double getPayedMoneyForPeriodical (String email, String periodical){
        double result = userConstantsDao.RESULT_SQL_EXCEPTION;
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(GET_SUBSCRIPED_PERIODICAL_SQL);
            preparedStatement.setString(EMAIL_GET_SUBSCRIPED_PERIODICAL_SQL_NUMBER, email);
            preparedStatement.setString(PERIODICAL_GET_SUBSCRIPED_PERIODICAL_SQL_NUMBER, periodical);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getDouble(FOLLOWERS_MONEY_COLUMN_NAME);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            return result;
        }
        return result;
    }
       
    /**
     * getFreezedListByDate
     * <p>
     * returns hash map with periodicals names and moneys, which date was ended
     * </p>
     * @param dateCurrent - current date (Timestamp)
     * @return hash map with periodicals names and moneys
     */
    public HashMap<String, Double> getFreezedListByDate(Timestamp dateCurrent){
        HashMap<String, Double> freezedMap = new HashMap<>();
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(GET_FOLLOWERS_ALL_SQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getTimestamp(FOLLOWERS_DATE_FREEZ_COLUMN_NAME).before(dateCurrent)){
                    if (freezedMap.containsKey(resultSet.getString(FOLLOWERS_PERIODICAL_COLUMN_NAME))){
                        String keyPeriodical = resultSet.getString(FOLLOWERS_PERIODICAL_COLUMN_NAME);
                        double existMoney = freezedMap.get(keyPeriodical);
                        double putMoney = existMoney + resultSet.getDouble(FOLLOWERS_MOUNTHPRICE_COLUMN_NAME);
                        freezedMap.put(keyPeriodical, putMoney);
                    } else {
                        freezedMap.put(resultSet.getString(FOLLOWERS_PERIODICAL_COLUMN_NAME)
                                , resultSet.getDouble(FOLLOWERS_MOUNTHPRICE_COLUMN_NAME));
                    }                   
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return freezedMap;
    }
    
    /**
     * deleteFreezed
     * <p>
     * deletes followers which date was ended
     * </p>
     * @param dateCurrent - current date (Timestamp)
     * @return - result (boolean), true in case success, false in case failure
     */
    public boolean deleteFreezed (Timestamp dateCurrent){
        try {
            preparedStatement = connectionPool.getConnection().prepareStatement(DELETE_FOLLOWERS_BY_END_DATE_SQL);
            preparedStatement.setTimestamp(DATE_DELETE_FOLLOWERS_BY_END_DATE_SQL_NUMBER, dateCurrent);
            preparedStatement.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
}
