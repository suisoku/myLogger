package org.apache.commons.logging.myLogger;

import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;

public class Logger {
	

	/** 
	 *  log object is strictly associated to a logger**/
	protected class Log {
		protected Date date;
		protected Level level ;
		protected String message;
		
		protected Log(Level l , String m) {level = l ; message = m; date = new Date();}
		
		protected Log(Level l ,Throwable e) {
			level = l ; 
			date = new Date(); 
			
			StackTraceElement[] st = e.getStackTrace();
			// will string the stacktrace array 
			for(int i = 0 ; i < st.length ; i++) {
				message += " " + st[i].toString();
			}  
		}
		
		protected Log(Level l , String m , Throwable e) {
			level = l ; 
			date = new Date(); 
			message = m;
			
			StackTraceElement[] st = e.getStackTrace();
			// will string the stacktrace array 
			for(int i = 0 ; i < st.length ; i++) {
				message += " " + st[i].toString();
			}  
		}
		
		public String toString(String classCaller) {
			String str = "[" + this.date.toString() + "]" +  "  (" + classCaller + ")  "  + this.message;
			return str;
		}
	}
	
	
	/** Here the logger class inner code starts **/
	
	public String classCaller;
	private Level severitySetting; 
	private ArrayList<Log> logs; 
	private FileLogHandler fh = null;
	
	//private static Logger singleInstance = new Logger(new Exception().getStackTrace()[1].getClassName());
	
	public Logger(String name) {
		logs = new ArrayList<Log>();
		this.severitySetting = Level.DEBUG;
		this.classCaller = name;
	}

	
	//public static Logger getInstance() {return singleInstance;}
	
	public void getConfigParameters(String path) {
		AccessConfig ac = new AccessConfig(path);
		
		if(ac.pathFile != null && ac.wantingLoginFile == true) {
			//System.out.println("ConfigFile Activated");
			this.targetLogFile(ac.pathFile);
			this.setSeverity(ac.severityLevel);
		}
		
	}
	
	
	/** 
	 * Canonic function to log  **/
	public void log(Level severity, Object data) {
		Log l = null;
		
		// log creation
		if(data instanceof String) {l= new Log(severity, (String)data);}
		else if(data instanceof Throwable) {l = new Log(severity, (Throwable)data);}
		else throw new NoSuchElementException();
		logs.add(l);
		
		//filtering and streming the message
		if(l.level.ordinal() >= this.severitySetting.ordinal()) {
			System.out.println(l.toString(this.classCaller));
			if(fh != null)this.logInFile(l);
		}
		
		
	}
	
	/** Specific log function use case Message + Exception ?* */
	
	public void log(Level severity, String m , Throwable t) {
		Log l = new Log(severity, m , t);
		
		logs.add(l);
		
		//filtering and streming the message
		if(l.level.ordinal() >= this.severitySetting.ordinal()) {
			System.out.println(l.toString(this.classCaller));
			if(fh != null)this.logInFile(l);
		}	
	}
	
	

	
	/** 
	 * optionnal nethods to display the log store in the actual logger **/
	public void displayLogOnConsole() {
		System.out.println("Format: DATE                   - LEVEL :        MESSAGE");
		//later we can display base on severity
		for(Log l : getFilteredLogs()) {
				System.out.println(l.toString(this.classCaller));
		}
	}
	public void displayAllLogsOnConsole() {
		System.out.println("Format: DATE                   - LEVEL :        MESSAGE");
		//later we can display base on severity
		for(Log l : logs) {
			System.out.println(l.toString(this.classCaller));
		}
	}
	
	public void clearLogger(){
		this.logs.clear();
	}
	
	
	/** 
	 * allows to set the severity of logging , default : debug  **/
	public void setSeverity(Level l) {
		this.severitySetting  = l;
	}
	
	// INFO < DEBUG < WARN < ERROR
	
	
	/** 
	 * getFilteredMessage **/
	protected  ArrayList<Log> getFilteredLogs() {
		ArrayList<Log> filtlogs = new ArrayList<Log>();
		
		for(Log l : logs) {
			if(l.level.ordinal() >= this.severitySetting.ordinal()) {
				filtlogs.add(l);
			}
		}
		return filtlogs;
	}
	
	/** 
	 * optiomal : specify a file to log in file  **/
	public void targetLogFile(String path){
		this.fh = new FileLogHandler(path);
	}
	
	
	/** 
	 * inner method to write in the log file **/
	protected void logInFile(Log l) {
		if(fh == null)throw new NullPointerException("not target file chosen");
		
		fh.write(l.toString(this.classCaller));
			
	}


	public Level getSeveritySetting() {
		return severitySetting;
	}
}