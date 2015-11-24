package javalib.utill.log;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by DV21 on 23-10-2015.
 */
public class LoggingExample {
    private static Logger logger = Logger.getLogger( LoggingExample.class.getName() );

    public static void main(String[] args) {
        logger.info( "Info Msg...." );
        logger.warning( "Warning Msg..." );
        logger.log( Level.SEVERE , "Severe Msg..." );
    }
}
