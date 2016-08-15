/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import static model.connection.constants.ConnectConstantsDB.*;

/**
 *
 * @author Zakhar
 */
public class ConnectionJdbc {
    private InitialContext cont;

    public ConnectionJdbc() {
        try {
            this.cont = new InitialContext();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * openDriver
     * get connection
     * @return connection
     */
    public Connection openDriver () {
        try {
            Class.forName(REGISTER_DRIVER);
            Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connect;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
