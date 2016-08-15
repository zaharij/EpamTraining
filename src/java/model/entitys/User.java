/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entitys;

import static model.service.constants.ConstantsLogic.WHITESPACE;

/**
 *
 * @author Zakhar
 */
public class User{
    private String email;
    private int password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String fullName;
    private double moneyAccount;
    private boolean isLogined;
    private boolean isBlocked;
    private boolean isAdmin;
    private boolean isWriter;
    private String language;
    
    public User (String firstName, String middleName, String lastName, String email, int password){
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName == "" ? null : middleName;
        this.lastName = lastName;
        this.email = email;
        this.moneyAccount = moneyAccount;
        this.fullName = firstName + WHITESPACE + lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    public User (){
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the moneyAccount
     */
    public double getMoneyAccount() {
        return moneyAccount;
    }

    /**
     * @param moneyAccount the moneyAccount to set
     */
    public void setMoneyAccount(double moneyAccount) {
        this.moneyAccount = moneyAccount;
    }

    /**
     * @return the isLogined
     */
    public boolean isIsLogined() {
        return isLogined;
    }

    /**
     * @param isLogined the isLogined to set
     */
    public void setIsLogined(boolean isLogined) {
        this.isLogined = isLogined;
    }

    /**
     * @return the isAdmin
     */
    public boolean isIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * @return the isWriter
     */
    public boolean isIsWriter() {
        return isWriter;
    }

    /**
     * @param isWriter the isWriter to set
     */
    public void setIsWriter(boolean isWriter) {
        this.isWriter = isWriter;
    }

    /**
     * @return the password
     */
    public int getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(int password) {
        this.password = password;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @return the isBlocked
     */
    public boolean isIsBlocked() {
        return isBlocked;
    }

    /**
     * @param isBlocked the isBlocked to set
     */
    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }
}
