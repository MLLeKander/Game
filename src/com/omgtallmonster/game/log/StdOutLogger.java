package com.omgtallmonster.game.log;

import java.io.PrintStream;

public class StdOutLogger extends Logger {
	PrintStream out;
	
	public StdOutLogger() {
		this(System.out);
	}
	
	public StdOutLogger(PrintStream out) {
		this.out = out;
	}
	

	@Override
	public void write(LogLevel level, String text) {
		if (!shouldLog(level)) return;
		out.println(String.format("[%s] %s", level, text));
	}
	
	@Override
	public void write(Class<?> src, LogLevel level, String text) {
		if (!shouldLog(level)) return;
		String srcName= src.getSimpleName();
		out.println(String.format("[%s|%s] %s", srcName, level, text));
	}

}
