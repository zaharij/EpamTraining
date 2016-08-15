/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entitys;

/**
 *
 * @author Zakhar
 */
public class SuperAdmin{
    private String login;
    int password;
    
    public SuperAdmin (String login, int password){
        this.password = password;
        this.login = login;
    }
    
    public SuperAdmin (){
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }
}
