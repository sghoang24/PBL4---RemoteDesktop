package controller.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.common.FileInfo;

public class ChatController {
	private Socket socket;

	public ChatController(Socket socket) {
		this.socket = socket;
	}
	
	public void Sendfile(FileInfo Myfile) throws IOException {
		ObjectOutputStream bos = new  ObjectOutputStream(socket.getOutputStream());
		bos.writeObject(Myfile);
	}
	
	public FileInfo ReceiveFile() throws IOException, ClassNotFoundException {
		FileInfo myfile = null;
		ObjectInputStream dis = new ObjectInputStream(this.socket.getInputStream());
		myfile = (FileInfo) dis.readObject();
		return myfile;
		
	}
	
}
public class ChatController {
	 private Socket socket;

	    public ChatController(Socket socket) {
	        this.socket = socket;
	    }

	    public void sendMessage(Message obj_message) throws IOException {
	        ObjectOutputStream dos = new ObjectOutputStream(this.socket.getOutputStream());
	        dos.writeObject(obj_message);
	    }

	    public Message recvMessage() throws IOException, ClassNotFoundException {
	        Message obj_message = null;
	        ObjectInputStream dis = new ObjectInputStream(this.socket.getInputStream());
	        obj_message = (Message) dis.readObject();
	        return obj_message;
	    }

	    public Socket getSocket() {
	        return this.socket;
	    }
} 
