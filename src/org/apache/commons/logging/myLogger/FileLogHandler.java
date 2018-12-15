package org.apache.commons.logging.myLogger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileLogHandler {
	
	private Path pathFile;  
	
	/** Class allowing to write  in a file on the fly **/
	public FileLogHandler(String path) {
		this.pathFile = Paths.get(path);
		if(!Files.exists(this.pathFile)) // we test if the files exist ( because the write method will append only)
			try {
				Files.createFile(this.pathFile);
			} catch (IOException e) {e.printStackTrace();}
			 
	}
	
	public void write(String content) {	
		content += System.lineSeparator();
		
		try {
			Files.write(this.pathFile, content.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public List<String> read(){	
		try {
			return Files.readAllLines(pathFile);
			
		} catch (IOException e) {e.printStackTrace();}
		return null;
	}
	
}
