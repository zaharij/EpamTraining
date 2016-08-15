/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.connection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.connection.constants.ConnectConstantsDB.*;

/**
 *ConnectionPool
 * @author Zakhar
 */
public class ConnectionPool {
    private List<Connection> connectionPool = new ArrayList<>();
    
    /**
     * getConnection
     * get connection from connection pool
     * if connections number is in standard range (between constant min and max values),
     * the method returns existing connection;
     * if connection numbers is less - creates new connection and return connection;
     * if connections number is greater - deletes connections to normal size
     * @return connection to data base
     */
    public Connection getConnection (){
        int random;
        if (connectionPool.size() <= CONNECTION_POOL_MIN_SIZE){
            setConnection();
            random = new Random().nextInt(connectionPool.size());
            return connectionPool.get(random);
        }else if (connectionPool.size() <= CONNECTION_POOL_MAX_SIZE){
            random = new Random().nextInt(connectionPool.size());
            return connectionPool.get(random);
        }else {
            int newSize = getNewPoolSize();
            int poolSize = connectionPool.size();
            for (--poolSize; poolSize > newSize; poolSize--){
                try {
                    connectionPool.get(poolSize).close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            random = new Random().nextInt(connectionPool.size());
                return connectionPool.get(random);
        }
    }
    
    /**
     * setConnection
     * set new connection to connection pool
     */
    private void setConnection (){
        ConnectionJdbc connectionJdbc = new ConnectionJdbc();
        Connection connection = connectionJdbc.openDriver();
        connectionPool.add(connection);
    }
    
    /**
     * getNewPoolSize
     * get new pool size
     * @param numbers values to calculate
     * @return pool size number
     */
    private int getNewPoolSize (int ... numbers){
        int sum = DEFAULT_NUMBER;
        for (int size: numbers){
            sum += size;
        }
        return sum/numbers.length;
    }
}
