/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.connection.constants;

/**
 *
 * @author Zakhar
 */
public class ConnectConstantsDB {
    public final static String REGISTER_DRIVER = "com.mysql.jdbc.Driver";
    public final static String URL = "jdbc:mysql://localhost:3306/periodicals";
    public final static String USERNAME = "Zakhar";
    public final static String PASSWORD = "98818467Chryst";
    
    public final static int CONNECTION_POOL_MIN_SIZE = 8;
    public final static int CONNECTION_POOL_MAX_SIZE = 16;
    public final static int DEFAULT_NUMBER = 0;
}
