/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.PropertyConfigurator;
/**
 *
 * @author Zakhar
 */
public class ConfigLog {
    private static Properties logProperty = new Properties();
    private static String logFile;

    @SuppressWarnings("static-access")
    public ConfigLog(String logFile) {
        this.logFile = logFile;
    }

    public void init () {
        try {
            logProperty.load(new FileInputStream(logFile));
            PropertyConfigurator.configure(logProperty);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
