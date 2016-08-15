/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entitys;

import java.util.GregorianCalendar;

/**
 *
 * @author Zakhar
 */
public class Periodical{
    private String theme;
    private int readersNumber;
    private double mounthPrice;
    private double allMoney;
    private GregorianCalendar date;
    
    public Periodical (String theme, double mounthPrice){
        this.theme = theme;
        this.mounthPrice = mounthPrice;
    }
    
    public Periodical (){
    }

    /**
     * @return the theme
     */
    public String getTheme() {
        return theme;
    }

    /**
     * @param theme the theme to set
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     * @return the readersNumber
     */
    public int getReadersNumber() {
        return readersNumber;
    }

    /**
     * @param readersNumber the readersNumber to set
     */
    public void setReadersNumber(int readersNumber) {
        this.readersNumber = readersNumber;
    }

    /**
     * @return the mounthPrice
     */
    public double getMounthPrice() {
        return mounthPrice;
    }

    /**
     * @param mounthPrice the mounthPrice to set
     */
    public void setMounthPrice(double mounthPrice) {
        this.mounthPrice = mounthPrice;
    }

    /**
     * @return the allMoney
     */
    public double getAllMoney() {
        return allMoney;
    }

    /**
     * @param allMoney the allMoney to set
     */
    public void setAllMoney(double allMoney) {
        this.allMoney = allMoney;
    }

    /**
     * @return the date
     */
    public GregorianCalendar getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(GregorianCalendar date) {
        this.date = date;
    }
}
