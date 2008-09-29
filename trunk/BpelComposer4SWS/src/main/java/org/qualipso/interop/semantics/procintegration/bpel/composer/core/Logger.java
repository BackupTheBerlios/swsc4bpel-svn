package org.qualipso.interop.semantics.procintegration.bpel.composer.core;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;

/**
 * Central logger for Semantic Web Service Composer.
 *
 */
public class Logger {
	
	public static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Logger.class);
	
	public static void configure() {
		String pattern = "%p: %m%n";
		PatternLayout layout = new PatternLayout(pattern);
		ConsoleAppender appender = new ConsoleAppender(layout);
		logger.setAdditivity(false);
		logger.removeAllAppenders();
		logger.addAppender(appender);
		logger.setLevel(Level.DEBUG);
	}
	
	public static void info(String s){
		logger.info(s);
	}
	
	public static void warn(String s){
		logger.warn(s);
	}
	
	public static void debug(String s){
		logger.debug(s);
	}
	
	public static void error(String s){
		logger.error(s);
	}

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }
}
