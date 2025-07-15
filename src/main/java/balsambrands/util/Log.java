package balsambrands.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LoggerUtil is a utility class that provides standardized logging methods
 * for Selenium-based test frameworks using SLF4J.
 */
public class Log {

    /**
     * Returns a logger instance for the given class.
     *
     * @param clazz the class requesting a logger
     * @return a Logger instance
     */
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    // Optional: Predefined logging actions
    public static void logInfo(Class<?> clazz, String message) {
        getLogger(clazz).info(message);
    }

    public static void logDebug(Class<?> clazz, String message) {
        getLogger(clazz).debug(message);
    }

    public static void logError(Class<?> clazz, String message, Throwable t) {
        getLogger(clazz).error(message, t);
    }

    public static void logWarn(Class<?> clazz, String message) {
        getLogger(clazz).warn(message);
    }
}