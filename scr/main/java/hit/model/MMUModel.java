package hit.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MMUModel implements Model{
	private List<String> commands = new ArrayList<String>();
	private String filePath;
	
	public MMUModel(String filePath) {
		this.filePath = filePath;
		readData();
	}
	
	public List<String> getModel(){
		return commands;
	}
	
	@Override
	public void readData() {
		try {
			FileReader file = new FileReader(filePath);
			BufferedReader reader = new BufferedReader(file);
			String line = null;
			while((line = reader.readLine()) != null) {
				line = line.trim();
				if (line.length() > 0){
					commands.add(line);
				}
			}
			reader.close();
			
		} catch (Exception e) {
			System.out.println("There was an error with the log file, MMUModel cannot read: " + e.getMessage());
		}
	}
}
