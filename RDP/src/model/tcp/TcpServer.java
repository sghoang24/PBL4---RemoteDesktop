package model.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Vector;

import View.ChatPanel;
import View.MainChatPanel;
import controller.chat.ChatController;

public class TcpServer {
	private MainChatPanel mainchat_panel;
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private String password;
	
	private boolean is_listening;
	private boolean is_has_user;
	
	public TcpServer(MainChatPanel mainchat_panel) {
		this.serverSocket = null;
		this.clientSocket = null;
		this.password = null;
		this.is_listening = false;
		this.is_has_user = false;
		this.mainchat_panel = mainchat_panel;
	}
	
	public void startListeningOnTcpServer(String host, int port, String password) throws IOException {
		if (this.is_listening == false) {
			this.password = password;
			InetSocketAddress endPoint = new InetSocketAddress(host, port); // Create a socket address for server
			this.serverSocket = new ServerSocket();
			this.serverSocket.bind(endPoint);
			this.is_listening = true;
		}
	}
	
	public void stopListeningOnTcpServer() throws IOException {
        if(this.is_listening == true) {
            this.serverSocket.close();
            if(this.clientSocket != null) this.clientSocket.close();
            this.is_listening = false;
            this.is_has_user = false;
        }
    }
	
	public void waitingForConnectionFromClient() throws IOException {
		this.clientSocket = this.serverSocket.accept();
		DataInputStream dis = new DataInputStream(this.clientSocket.getInputStream());
		DataOutputStream dos = new DataOutputStream(this.clientSocket.getOutputStream());
		String passwordFromClient = dis.readUTF();
		String res = null;
		
		if (this.password.equals(passwordFromClient)) {
			res = "true";
			this.is_has_user = true;
			ChatController chatController = new ChatController(this.clientSocket);
			this.mainchat_panel.addNewConnection(chatController);
		}
		else res = "false";
		dos.writeUTF(res);
	}
	
	public Vector<String> getAllIPv4AddressesOnLocal() throws SocketException {
		Vector<String> ipv4_Addresses = new Vector<>();
		Enumeration networks = NetworkInterface.getNetworkInterfaces();
		while(networks.hasMoreElements()) {
            NetworkInterface sub_networks = (NetworkInterface) networks.nextElement();
            Enumeration inet_addresses = sub_networks.getInetAddresses();
            while(inet_addresses.hasMoreElements()) {
                try {
                    Inet4Address ipv4 = (Inet4Address) inet_addresses.nextElement();
                    ipv4_Addresses.add(ipv4.getHostAddress());
                }
                catch(Exception e) {
                    // TODO: Pass IP version 6
                }
            }
        }
		return ipv4_Addresses;
	}
	
	public ServerSocket getServer() {
        return this.serverSocket;
    }
	
	public boolean isListening() {
        return this.is_listening;
    }

    public boolean isHasPartner() {
        return this.is_has_user;
    }

    public void setHasPartner(boolean b) {
        this.is_has_user = b;
    }
}
