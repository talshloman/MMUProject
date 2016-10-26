package hit.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import hit.controller.MMUController;

public class MMUClient {
	String messageFromServer=null;
	ObjectInputStream input;
	ObjectOutputStream output;
	Socket myServer;
	InetAddress address;
	public final static String FILE_TO_RECEIVED = "logs//logFromServer.txt";
	
	public MMUClient(String user){
	try{
		 address=InetAddress.getByName("127.0.0.1");
		 myServer = new Socket(address, 12345);
		 output=new ObjectOutputStream(myServer.getOutputStream());
		 input=new ObjectInputStream(myServer.getInputStream());
		 output.writeObject(user);
		 output.flush();
		 messageFromServer=(String)input.readObject();
		 if(messageFromServer.equals("you are connected to the server")){
			   MMUController.authenticate = true;
	     }
		 else if(messageFromServer.equals("The user or password is incorrect")){
        	 MMUController.authenticate = false;
         }

		}catch (Exception e) {}

}
	
	public void getFile() throws ClassNotFoundException, IOException{
		try{
			
			messageFromServer=(String)input.readObject();
			if(messageFromServer.equals("getReady")){ 
				File file = new File(FILE_TO_RECEIVED);
				FileWriter fileWriter = new FileWriter(file);
				while((messageFromServer=(String)input.readObject())!=null){
                	fileWriter.write(messageFromServer+"\n");
                	fileWriter.flush();
				}
				fileWriter.close();
			}
        } catch (Exception e) {
    		e.printStackTrace();
    	}	
        output.writeObject("bye");
        output.flush();
        messageFromServer=(String)input.readObject();
        if(messageFromServer.equals("bye")){
        	output.close();
   		 	input.close();
   		 	myServer.close();
        }
	}
}
