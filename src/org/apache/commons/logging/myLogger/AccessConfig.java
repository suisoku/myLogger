package org.apache.commons.logging.myLogger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * ALLOWS  to read the congif file
 */
public class AccessConfig {

    private Properties prop = new Properties();
    
    // the three value that we going to read
    public String pathFile = null;
    public boolean wantingLoginFile = false;
    public Level severityLevel = null ;
    //public boolean displayOnConsole = true;

    public AccessConfig(String propertiesFile) {
        try {
            prop = new Properties();
            prop.load(new FileInputStream(propertiesFile));
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException: "+ e.getMessage());
            e.printStackTrace();
            return;
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            e.printStackTrace();
            return;
        }
        
        
        pathFile = prop.getProperty("FILEPATH");
        severityLevel = Level.valueOf(prop.getProperty("SEVERITY")); // String to enum
        wantingLoginFile = (prop.getProperty("LOGINFILE").equals("TRUE")) ? true : false ; // String to boolean
       // displayOnConsole = (prop.getProperty("DISPLAYONCONSOLE").equals("TRUE")) ? true : false ;

    }

}
