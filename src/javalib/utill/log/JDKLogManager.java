package javalib.utill.log;

import java.util.logging.*;

/**
 * Created by DV21 on 27-10-2015.
 */
public class JDKLogManager {

    public static Logger getLogger( Class cls ) {
        Logger logger = Logger.getLogger( cls.toString() );
        return logger;
    }

    public static Logger getLogger( String clsName ) {
        Logger logger = Logger.getLogger( clsName );
        return logger;
    }

    private Handler getConsoleHandler() {
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel( Level.ALL );
        return consoleHandler;
    }

    private Handler getFileHandler() {
        Handler fileHandler = new FileHandler( "" );
    }
}
