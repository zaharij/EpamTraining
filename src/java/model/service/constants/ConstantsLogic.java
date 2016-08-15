/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.constants;

/**
 *
 * @author Zakhar
 */
public class ConstantsLogic {
    public final static int LOG_IMG_NUMBER = 9;
    public final static String CHECK_NAME_REG = "([A-ZА-ЯІ]){1}([a-zа-яі']){1,20}";
    public final static String CHECK_EMAIL_REG = "([a-z0-9._-]){1,30}([@]){1}([a-z]){2,10}([.]){1}([a-z.]){0,20}";
    public final static String CHECK_PASSWORD_REG = "([a-zA-Z0-9@#%$^&!*]){3,30}";
    public final static String CHECK_ADMIN_LOGIN_REG = "([a-zA-Z0-9@#$%&]){3,20}";
    public final static String WHITESPACE = " ";
    public final static String PASSWORD_SALT = "GtXcvpo$%#97&";
    
    public final static int FIRST_ARRAY_ELEMENT = 0;
    public final static int IS_PASSWORD_DEFAULT = 0;
    public final static int IS_ADMIN_DEFAULT = 0;
    public final static int IS_WRITER_DEFAULT = 0;
    public final static int IS_WRITER = 1;
    public final static int IS_ADMIN = 1;
    public final static int IS_BLOCKED_DEFAULT = 0;
    public final static int IS_BLOCKED = 1;
    public final static int SUBSCRIPTION_MAX_MOUNTH_NUMBER = 12;
    public final static int SUBSCRIPTION_MIN_MOUNTH_NUMBER = 1;
    public final static int ARTICLE_IS_PERMITTED = 1;
    public final static int ARTICLE_IS_NOT_PERMITTED = 0;
    
    public final static int USER_PERMITTED_ARTICLES_MAX = 5;
}
