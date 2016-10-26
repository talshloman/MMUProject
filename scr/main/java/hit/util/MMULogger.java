package hit.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class MMULogger {
	final static String DEFAULT_FILE_NAME = "logs//log.txt";
	private FileHandler handler;
	private static MMULogger logger = new MMULogger();
	
	private MMULogger(){
		try {
			handler=new FileHandler(DEFAULT_FILE_NAME);
			handler.setFormatter(new onlyMessegeFormatter());
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void write (String commend, Level level){
		handler.publish(new LogRecord(level, commend));
	}
	
	public class onlyMessegeFormatter extends Formatter{
		public onlyMessegeFormatter(){
			super();
		}
		
		@Override
		public String format(final LogRecord record){
			return record.getMessage();
		}
	}
	
	public FileHandler getHandler() {
		return handler;
	}

	public void setHandler(FileHandler handler) {
		this.handler = handler;
	}

	public static MMULogger getInstance() {
		return logger;
	}

	public static void setInstance(MMULogger instance) {
		MMULogger.logger = instance;
	}	
}