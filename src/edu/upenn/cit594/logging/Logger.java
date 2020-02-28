package edu.upenn.cit594.logging;

import java.io.File;
import java.io.PrintWriter;

public class Logger {
	private PrintWriter out;

	private Logger(String fileName) {
		try {
			out = new PrintWriter(new File(fileName));
		} catch (Exception e) {
		}
	}

	private static Logger instance;

	public static Logger getInstance() {
		return instance;
	}

	public static void setFileName(String fileName) {
		assert (instance == null);
		instance = new Logger(fileName);
	}

	public void log(String msg) {
		long ms = System.currentTimeMillis();
		out.println(ms + " " + msg);
		out.flush();
	}

	public void close() {
		out.close();
	}
}
