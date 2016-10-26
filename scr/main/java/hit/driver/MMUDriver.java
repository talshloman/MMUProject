package hit.driver;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import hit.algorithm.LRUAlgoCacheLmpl;
import hit.controller.MMUController;
import hit.memoryunits.MemoryManagementUnit;
import hit.processes.ProcessCycles;
import hit.processes.Process;
import hit.processes.RunConfiguration;
import hit.util.MMULogger;

public class MMUDriver {

	private final static int appIds = 5;
	private final static String CONFIG_FILE_NAME = "scr//main//resources//configuration//Configuration.json";
	private final static String DEFAULT_FILE_NAME = "scr//main//resources//harddisk//HD.txt";
	private final static int ramCapacity = 5;
	public MMUDriver() {

	}

	public static void main(String[] args) throws JsonIOException, JsonSyntaxException, InterruptedException, ClassNotFoundException, IOException {

			MMULogger logger = MMULogger.getInstance();
			MemoryManagementUnit mmu = new MemoryManagementUnit(ramCapacity, new LRUAlgoCacheLmpl<Long, Long>(ramCapacity));
			logger.write("RC " + ramCapacity+ "\r\n" , Level.INFO);
			RunConfiguration runConfig = readConfigurationFile();
			List<ProcessCycles> processCycles = runConfig.getProcessesCycles();
			logger.write("PN " + processCycles.size() + "\r\n", Level.INFO);
			List<Process> processes = createProcesses(processCycles, mmu);
			runProcesses(processes);
			Thread.sleep(5000);
			System.out.println("done!");
			MMUController controller = new MMUController();
			controller.start();
			
	
	}

	private static void runProcesses(List<Process> processes) {
		for (Process p : processes) {
			new Thread(p).start();
		}
	}

	private static RunConfiguration readConfigurationFile() {
		try {
			return new Gson().fromJson(new JsonReader(new FileReader(CONFIG_FILE_NAME)), RunConfiguration.class);
		} catch (Exception e) {
			MMULogger.getInstance().write("There was an error reading the JSON file: " + e , Level.SEVERE);
			System.out.println("There was an error reading the JSON file");
		}
		return new RunConfiguration(new ArrayList<ProcessCycles>());
	}

	private static List<Process> createProcesses(List<ProcessCycles> processCycles, MemoryManagementUnit mmu) {
		List<Process> processList = new ArrayList<Process>();

		for (int i = 0; i < processCycles.size(); i++) {
			processList.add(new Process(i + 1, mmu, processCycles.get(i)));
		}

		return processList;
	}

	public static int getAppids() {
		return appIds;
	}

	public static String getDefaultFileName() {
		return DEFAULT_FILE_NAME;
	}

}
