package model.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import View.ChatPanel;

public class TcpClient {
	private ChatPanel chat_panel;
	private Socket clientSocket;
	private boolean is_connected_server;

	public TcpClient(ChatPanel chat_panel) {
		this.clientSocket = null;
		this.is_connected_server = false;
		this.chat_panel = chat_panel;
	}

	public void startConnectingToServer(String host, int port, String password) throws Exception {
		if (this.is_connected_server == false) { // Haven't connected
			this.clientSocket = new Socket(host, port);
			DataInputStream dis = new DataInputStream(this.clientSocket.getInputStream());
			DataOutputStream dos = new DataOutputStream(this.clientSocket.getOutputStream());

			dos.writeUTF(password);
			String res = dis.readUTF();
			if (res.equals("true")) {
				// ChatController chatController = new ChatController(this.clientSocket);
				// this.chat_panel.addNewConnection(chatController);
				this.is_connected_server = true;
			} else if (res.equals("false")) {
				this.clientSocket.close();
				throw new Exception("Wrong password!!!");
			}
		}
	}
	
	public void stopConnectingToTcpServer() throws IOException {
        if(this.is_connected_server = true) {
            this.clientSocket.close();
            this.is_connected_server = false;
        }
    }

	public boolean isConnectedServer() {
		return this.is_connected_server;
	}

	public void setConnectedServer(boolean b) {
		this.is_connected_server = b;
	}

	public Socket getClient() {
		return this.clientSocket;
	}
}
