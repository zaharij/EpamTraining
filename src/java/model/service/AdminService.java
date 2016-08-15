/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import model.dao.impl.AdminDao;
import model.dao.impl.ArticlesDao;
import static model.service.constants.ConstantsLogic.*;

/**
 * AdminService
 * @author Zakhar
 */
public class AdminService {
    private AdminDao adminDao = new AdminDao();
    private ArticlesDao articleDao = new ArticlesDao();
    private UserService userService = new UserService();
    
    /**
     * payForArticles
     * salaries for authors
     */
    public void payForArticles(){
        Timestamp dateCurrent = new Timestamp(System.currentTimeMillis());
        List<String> authorsForPayList = null;         
        HashMap<String, Double> freezedMap = adminDao.getFreezedListByDate(dateCurrent);
        Set mapKeysPeriodicals = freezedMap.keySet();
        for (Object periodicalObject: mapKeysPeriodicals){
            String periodical = periodicalObject.toString();
            articleDao.getAuthorsForPayList(periodical, dateCurrent);
            double moneyPart = freezedMap.get(periodical)/authorsForPayList.size();
            for(String email: authorsForPayList){
                userService.replenishAccount(email, moneyPart);
            }
        }
        adminDao.deleteFreezed (dateCurrent);
        adminDao.updateFreezed(dateCurrent);
    }
    
    /**
      * checkLoginFields
      * check if login fields is correct
      * @param login
      * @param password
      * @return result (boolean)
      */
     public boolean checkLoginFields(String login, String password){
        if (userService.checkField(login, CHECK_ADMIN_LOGIN_REG) && userService.checkField(password, CHECK_PASSWORD_REG)){
            return true;
        }
        return false;
     }
     
      /**
     * checkLogin
     * check if password for user is true
     * @return result (boolean)
     */
    public boolean checkLogin(String login, int passwordInput){
        int passwordTrue = adminDao.getSuperAdminPassword(login);
        if (passwordTrue == passwordInput){
            return true;
        } else{
            return false;
        }
    }
    
    /**
     * setAdminRightsTrue
     * @param email 
     */
    public void setAdminRightsTrue (String email){
        adminDao.setAdminRights(email, IS_ADMIN);
    }
    
    /**
     * setAdminRightsFalse
     * @param email 
     */
    public void setAdminRightsFalse (String email){
        adminDao.setAdminRights(email, IS_ADMIN_DEFAULT);
    }
}
