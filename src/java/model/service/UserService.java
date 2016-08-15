/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.dao.impl.ArticlesDao;
import model.dao.impl.UserDao;
import model.entitys.User;
import static model.service.constants.ConstantsLogic.*;

/**
 *
 * @author Zakhar
 */
public class UserService {
    private UserDao userDao = new UserDao();
    private ArticlesDao articlesDao = new ArticlesDao();
    private Pattern pattern;
    private Matcher matcher;
    
    /**
     * getRandomLogImgName
     * returns random name of login picture
     * @return random name
     */
    public String getRandomLogImgName(){
        Integer random = new Random().nextInt(LOG_IMG_NUMBER);
        return random.toString();
    }
    /**
     * createUser
     * creates new user
     * @param user User object, which contains all needed parameters
     * @return result (boolean), true in case success, false in case failure
     */
    public boolean createUser(User user){
        return userDao.insertUserIntoDb(user);
    }
    
    /**
      * replenishAccount
      * replenish user's account
      * @return result (boolean)
      */
     public boolean replenishAccount(String email, double money) {
         double newCash = userDao.getUserMoney(email) + money;
         return userDao.setMoneyToAccount(email, newCash);
     }
     
     /**
     * setArticlePermission
     * 
      * @param title
      * @return 
      */
    public boolean setArticlePermission (String title){
        String email = articlesDao.getArticleEmailAuthor(title);
        int permittedNumber = userDao.getUserPermittedNumber(email) + ARTICLE_IS_PERMITTED;
        if (permittedNumber >= USER_PERMITTED_ARTICLES_MAX){
            userDao.setAuthorRights(email);
        }
        return userDao.updatePermittedNumber(email, permittedNumber);
    }
    
    /**
     * lofinIsFree
     * check if current user is already registered
     * @return true if user is already registered, and false if no
     */
    public boolean loginIsFree(String login){
        if (userDao.getUserPassword(login) == IS_PASSWORD_DEFAULT){
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
        int passwordTrue = userDao.getUserPassword(login);
        if (passwordTrue == passwordInput){
            return true;
        } else{
            return false;
        }
    }
    
    /**
     * checkRegisterImg
     * check register if it is a human
     * @return result (boolean)
     */
     public boolean checkRegisterImg(String idImgName, String inputValue) {
        Integer idImgNameInt = Integer.parseInt(idImgName);
        String trueValue = userDao.getLoginImgValue(idImgNameInt);
        if (inputValue.equals(trueValue)){
            return true;
        } else {
            return false;
        }
    }
     
     /**
      * checkRegFields
      * registration forms validation
      * @return result (boolean)
      */
     public boolean checkRegFields(String firstName, String lastName, String fatherName, String email, String password){
        if (checkField(firstName, CHECK_NAME_REG) && checkField(lastName, CHECK_NAME_REG) 
                && (fatherName == "" ? 
                true : 
                checkField(fatherName, CHECK_NAME_REG))
                && checkLoginFields(email, password)){
            return true;
        }
        return false;
     }
     
     /**
      * checkLoginFields
      * check if login fields is correct
      * @param email
      * @param password
      * @return result (boolean)
      */
     public boolean checkLoginFields(String email, String password){
        if (checkField(email, CHECK_EMAIL_REG) && checkField(password, CHECK_PASSWORD_REG)){
            return true;
        }
        return false;
     }
     
     /**
      * getWriterRights
      * check if user has rights as writer
      * @param email
      * @return result (boolean)
      */
     public boolean getWriterRights (String email){
         if (userDao.getWriterRights(email) != IS_WRITER_DEFAULT){
             return true;
         } else {
            return false;
         }
     }
     
     /**
      * getAdminRights
      * check if user has rights as admin
      * @param email
      * @return result (boolean)
      */
     public boolean getAdminRights (String email){
        if (userDao.getAdminRights(email) != IS_ADMIN_DEFAULT){
            return true;
        } else {
            return false;
        }
     }
     
     /**
      * getFullUserName
      * get user's full name from db 
      * @param email
      * @return full name (String)
      */
     public String getFullUserName (String email){
         return userDao.getUserFirstName(email) + WHITESPACE + userDao.getUserLastName(email);
     }
     
     /**
     * checkField
     * check if given field is correct
     * @param fieldInput inputed string
     * @param regularExpression regular expression to check
     * @return result (boolean)
     */
    public boolean checkField(String fieldInput, String regularExpression) {
        pattern = Pattern.compile(regularExpression);
        matcher = pattern.matcher(fieldInput);
        if (!matcher.matches()){
            return false;
        }
        return true;
    }
    
    /**
     * getAuthorsNumber
     * get number of authors
     * @return result
     */
     public int getAuthorsNumber(){
         return userDao.getAuthorsNumber();
     }
     
     /**
      * getAuthorFullName
      * get author's full name
      * @return  name
      */
     public String getAuthorFullName (String title){
         String email = articlesDao.getArticleEmailAuthor(title);
         return userDao.getUserFirstName(email) + " " + userDao.getUserLastName(email);
     }
     
     /**
     * getReaders
     * get all readers
     * @return list of readers
     */
    public List<String> getReaders(){
        return userDao.getReaders();
    }
    
    /**
     * getAuthors
     * get all authors
     * @return list of authors
     */
    public List<String> getAuthors (){
        return userDao.getAuthors();
    }
    
    /**
     * getSimpleUsers
     * get all simple users
     * @return list of readers
     */
    public List<String> getSimpleUsers(){
        return userDao.getSimpleUsers();
    }
    
    /**
     * getAuthors
     * get all admins among users
     * @return list of authors
     */
    public List<String> getAdminUsers (){
        return userDao.getAdminUsers();
    }
    
    /**
     * getAllUsers
     * get all admins among users
     * @return list of authors
     */
    public List<String> getAllUsers (){
        return userDao.getAllUsers();
    }
    
    /**
     * deleteUser 
     * delete user from db
     * @param email 
     */
    public void deleteUser (String email){
        userDao.deleteUser(email);
    }
    
    /**
     * setBlockTrueUser
     */
    public void setBlockTrueUser (String email){
        userDao.setBlockUser(email, IS_BLOCKED);
    }
    
    /**
     * setBlockFalseUser
     */
    public void setBlockFalseUser (String email){
        userDao.setBlockUser(email, IS_BLOCKED_DEFAULT);
    }
    
    /**
     * getBlockedUsers
     * get all blocked users
     * @return list of authors
     */
    public List<String> getBlockedUsers (){
        return userDao.getBlockedUsers();
    }
    
     /**
     * getUnblockedUsers
     * get all unblocked users
     * @return list of authors
     */
    public List<String> getUnblockedUsers (){
        return userDao.getUnblockedUsers();
    }
}
