package com.omgtallmonster.game.log;

import static com.omgtallmonster.game.log.LogLevel.*;

public abstract class Logger {
	public abstract void write(Class<?> src, LogLevel level, String text);
	public abstract void write(LogLevel level, String text);

	protected LogLevel level = LogLevel.DEBUG;
	
	public void setLogLevel(LogLevel level) {
		this.level = level;
	}
	
	public boolean shouldLog(LogLevel level) {
		return level.ordinal() > this.level.ordinal(); 
	}
	
	
	
	private static Logger logger = new StdOutLogger();
	
	public static void setLogger(Logger logger) {
		Logger.logger = logger;
	}
	
	public static Logger getLogger() {
		return logger;
	}
	
	public static void setLevel(LogLevel level) {
		logger.setLogLevel(level);
	}
	
	
	public static void d(Class<?> src, String text, Object... args) {
		logger.write(src, DEBUG, String.format(text, args));
	}
	
	public static void d(Object src, String text, Object... args) {
		d(src.getClass(), text, args);
	}
	
	public static void d(String text, Object... args) {
		logger.write(DEBUG, String.format(text,args));
	}
	
	
	public static void i(Class<?> src, String text, Object... args) {
		logger.write(src, INFO, String.format(text, args));
	}
	
	public static void i(Object src, String text, Object... args) {
		i(src.getClass(), text, args);
	}
	
	public static void i(String text, Object... args) {
		logger.write(INFO, String.format(text,args));
	}
	
	
	public static void w(Class<?> src, String text, Object... args) {
		logger.write(src, WARN, String.format(text, args));
	}
	
	public static void w(Object src, String text, Object... args) {
		w(src.getClass(), text, args);
	}
	
	public static void w(String text, Object... args) {
		logger.write(WARN, String.format(text,args));
	}
	
	
	public static void e(Class<?> src, String text, Object... args) {
		logger.write(src, ERROR, String.format(text, args));
	}
	
	public static void e(Object src, String text, Object... args) {
		e(src.getClass(), text, args);
	}
	
	public static void e(String text, Object... args) {
		logger.write(ERROR, String.format(text,args));
	}
	
	
	public static void f(Class<?> src, String text, Object... args) {
		logger.write(src, FATAL, String.format(text,args));
	}
	
	public static void f(Object src, String text, Object... args) {
		f(src.getClass(), text, args);
	}
	
	public static void f(String text, Object... args) {
		logger.write(FATAL, String.format(text, args));
	}
}
