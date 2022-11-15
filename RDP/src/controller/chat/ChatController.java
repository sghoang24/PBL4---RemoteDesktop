package controller.chat;

import java.io.IOException;
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
	
	
}
