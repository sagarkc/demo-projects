/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.ws.invoker.log;

import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author sabuj.das
 */
public class MainLogger {

    public static Logger logger = Logger.getLogger("Logger");
    private static boolean init = false;

    public static void init(Appender appender, Level l) {
        if (init != true) {
            logger.addAppender(appender);
            logger.setLevel(l);
        }
        init = true;
    }

    public static void logError(String string, Exception exception) {
        logger.error(string, exception);
    }

    public static void logWarn(final String s) {
        logger.warn(s);
    }

    public static void logError(final String s) {
        logger.error(s);
    }

    public static void logError(final Throwable ex) {
        logger.fatal(new String(), ex);
    }

    public static void logTrace(final String s) {
        logger.trace(s);
    }

    public static void logInfo(final String s) {
        logger.info(s);
    }
}
