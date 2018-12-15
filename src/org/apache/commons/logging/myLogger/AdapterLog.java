package org.apache.commons.logging.myLogger;

import org.apache.commons.logging.Log;

public class AdapterLog extends Logger implements Log  {
	
	

	
	


	public AdapterLog(String name) {
		super(name);
	}

	@Override
	public void debug(Object message) {
		super.log(Level.DEBUG , message);
		
	}

	@Override
	public void debug(Object message, Throwable t) {
		super.log(Level.DEBUG, (String)message, t);
	}

	@Override
	public void error(Object message) {
		super.log(Level.ERROR , message);
		
	}

	@Override
	public void error(Object message, Throwable t) {
		super.log(Level.ERROR, (String)message, t);
		
	}

	@Override
	public void fatal(Object message) {
		super.log(Level.FATAL , message);
		
	}

	@Override
	public void fatal(Object message, Throwable t) {
		super.log(Level.FATAL, (String)message, t);
		
	}

	@Override
	public void info(Object message) {
		super.log(Level.INFO , message);
		
	}

	@Override
	public void info(Object message, Throwable t) {
		super.log(Level.INFO, (String)message, t);
		
	}

	@Override
	public void trace(Object message, Throwable t) {
		super.log(Level.TRACE , (String)message, t);
		
	}
	
	@Override
	public void trace(Object message) {
		super.log(Level.TRACE, message);
		
	}
	
	@Override
	public void warn(Object message) {
		super.log(Level.WARN , message);
		
	}
	
	@Override
	public void warn(Object message, Throwable t) {
		super.log(Level.WARN, (String)message, t);
		
	}
	
	@Override
	public boolean isDebugEnabled() {
		return super.getSeveritySetting() == Level.DEBUG;
	}

	@Override
	public boolean isErrorEnabled() {
		return super.getSeveritySetting() == Level.ERROR;
	}

	@Override
	public boolean isFatalEnabled() {
		return super.getSeveritySetting() == Level.FATAL;
	}

	@Override
	public boolean isInfoEnabled() {
		return super.getSeveritySetting() == Level.INFO;
	}

	@Override
	public boolean isTraceEnabled() {
		return super.getSeveritySetting() == Level.TRACE;
	}

	@Override
	public boolean isWarnEnabled() {
		return super.getSeveritySetting() == Level.WARN;
	}



	
}
