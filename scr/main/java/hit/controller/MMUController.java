package hit.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import hit.model.MMUClient;
import hit.model.MMUModel;
import hit.view.MMUView;

public class MMUController implements Controller {
	
	private final static String COMMANDS_FILE_NAME = "logs//logFromServer.txt";
	private final static String COMMANDS_FILE_AUTHENTICATE = "authenticate.txt";
	MMUModel model;
	static List<String> commands;
	public static boolean authenticate = false;
	private String content = null;
	private static boolean remote = true;
	public MMUController() {

	}

	@Override
	public void start() throws ClassNotFoundException, IOException {
		MMUView view = new MMUView();
		if(remote ==true){
			view.loginShell();
			content = new String(Files.readAllBytes(Paths.get(COMMANDS_FILE_AUTHENTICATE)));
			MMUClient client =new MMUClient(content);
			view.connector();
			client.getFile();
		}
		model = new MMUModel(COMMANDS_FILE_NAME);
		commands = model.getModel();
		view.mainview(commands);
		view.open();

	}

	public static String getCOMMANDS_FILE_AUTHENTICATE() {
		return COMMANDS_FILE_AUTHENTICATE;
	}
	
	public static boolean authenticate(){
		return authenticate;
		
	}

}
