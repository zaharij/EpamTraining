/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.dao.impl.PeriodicalsDao;
import model.dao.impl.UserDao;
import model.entitys.Periodical;
import static model.service.constants.ConstantsLogic.*;

/**
 *
 * @author Zakhar
 */
public class PeriodicalsService {
    private PeriodicalsDao periodicalsDao = new PeriodicalsDao();
    private UserDao userDao = new UserDao();
 
    
    /**
      * getAllPeriodicalsNames
      * get all periodicals names 
      * @return array list of names
      */
     public List<String> getAllPeriodicalsNames (){
         List<String> resultListNames = periodicalsDao.getAllPeriodicals();
         Collections.reverse(resultListNames);
         return resultListNames;
     }
     
     /**
      * getAllPeriodicalsNames
      * get payed periodicals names 
      * @return array list of names
      */
     public List<String> getAllPeriodicalsNames (String email){
         List<String> resultListNames = periodicalsDao.getPayedPeriodicals(email);
         Collections.reverse(resultListNames);
         return resultListNames;
     }
     
     /**
      * getPeriodicalPrice
      * get periodical price
      * @param subject
      * @return price
      */
     public double getPeriodicalPrice(String subject){
         return periodicalsDao.getPeriodicalPrice(subject);
     }
     
     /**
      * getDateEndSubscription
      * get end date subscription
      * @return date (String)
      */
     public String getDateEndSubscription (String email, String periodical){
         return periodicalsDao.getPeriodicalSubscribedDate(email, periodical).toLocaleString();
     }
     
     /**
      * getPeriodicalsMonthesCost
      * get periodical monthes cost
      * @return list of monthes with prices
      */
     public List<String> getPeriodicalsMonthesCost(String subject){
         List<String> resultList = new ArrayList<>();
         double monthPrice = getPeriodicalPrice(subject);
         double resultPrice; 
         for (int monthCount = SUBSCRIPTION_MIN_MOUNTH_NUMBER; monthCount <= SUBSCRIPTION_MAX_MOUNTH_NUMBER; monthCount++){
             resultPrice = monthCount * monthPrice;
             resultList.add(String.valueOf(monthCount) + " (" + resultPrice + ")");
         }
         return resultList;
     }
     
     /**
      * setExpirationDate
      * set Expiration Date
      * @param monthesNum - number of monthes
      */
     public Timestamp getExpirationDate(int mounthesNum){
         Timestamp date = new Timestamp(System.currentTimeMillis());
         date.setMonth(date.getMonth() + mounthesNum);
         return date;
     }
     
     /**
      * signedUpPeriodical
      * @param email
      * @param periodical
      * @param mounthesNum
      * @param money
      * @param mounthPrice 
      */
     public void signedUpPeriodical (String email, String periodical, int mounthesNum, double money, double mounthPrice){
        userDao.insertUserToPeriodicalsTableIntoDb(email, periodical, getExpirationDate(mounthesNum), money, mounthPrice);
     }
     
     /**
      * getAuthorsNumberBySubject
      * get authors number in given subject
      * @param subject
      * @return 
      */
     public int getAuthorsNumberBySubject (String subject) {
         return periodicalsDao.getAuthorsNumberBySubject(subject);
     }
     
     /**
      * getArticlesNumberBySubject
      * get articles number in given subject
      * @param subject
      * @return 
      */
     public int getArticlesNumberBySubject (String subject) {
         return periodicalsDao.getArticlesNumberBySubject(subject);
     }
     
     /**
      * getFollowersNumberBySubject
      * get followers number by the subject
      * @return 
      */
     public int getFollowersNumberBySubject (String subject){
         return periodicalsDao.getFollowersNumberBySubject(subject);
     }
     
     /**
     * setPeriodical
     */
    public void setPeriodical (Periodical periodical){
        periodicalsDao.insertPeriodicalIntoDb(periodical);
    }
    
    /**
     * deletePeriodical
     * delete periodical
     * @param subject 
     */
    public void deletePeriodical (String subject){
        periodicalsDao.deletePeriodical(subject);
    }
}
