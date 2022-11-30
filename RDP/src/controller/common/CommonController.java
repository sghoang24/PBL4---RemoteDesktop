package controller.common;

import java.awt.AWTException;
import java.io.IOException;
import java.net.InetAddress;
import java.rmi.NotBoundException;

import Rmi.RmiClient;
import Rmi.RmiServer;
import View.ChatPanel;
import View.MainChatPanel;
import model.tcp.TcpClient;
import model.tcp.TcpServer;

public class CommonController {
	// TODO: for RMI/TCP Server
	private TcpServer TCPServer;
	private RmiServer RMIServer;
	
	// TODO: for RMI/TCP Client
	private TcpClient TCPClient;
	private RmiClient RMIClient;
	
	public CommonController() {
		this.RMIServer = new RmiServer();
		this.RMIClient = new RmiClient();
	}
	
	public void setChatPanel(MainChatPanel mainchat_panel) {
		System.out.println("Set chat panel");
        this.TCPServer = new TcpServer(mainchat_panel);
        this.TCPClient = new TcpClient(mainchat_panel);
    }
	
	public TcpServer getTcpServer() {
        return this.TCPServer;
    }

    public RmiServer getRmiServer() {
        return this.RMIServer;
    }

    public TcpClient getTcpClient() {
        return this.TCPClient;
    }

    public RmiClient getRmiClient() {
        return this.RMIClient;
    }
    
    public void startListeningOnServer(String host, int port, String password) throws IOException, AWTException {
    	if (!this.TCPServer.isListening() && !this.RMIServer.isBinding()) {
    		// Port RMI = Port TCP + 1
    		this.TCPServer.startListeningOnTcpServer(host, port, password);
    		this.RMIServer.startBindingOnRmiServer(host, port + 1);
    	}
    }
    
    public void stopListeningOnServer() throws IOException, NotBoundException {
    	if (this.TCPServer.isListening() && this.RMIServer.isBinding()) {
    		this.TCPServer.stopListeningOnTcpServer();
    		this.RMIServer.stopBindingOnRmiServer();
    	}
    }
    
    public void startConnectingToServer(String host, int port, String password) throws Exception {
    	// TODO: check server is listening?
    	System.out.println("ngoai if");
    	if (this.TCPServer.isListening()) {
    		System.out.println("trong if");
    		String IP_Server = this.TCPServer.getServer().getInetAddress().getHostAddress();
    		if (host.equals(IP_Server)) throw new Exception("Cannot remote yourself!");
    		System.out.println(IP_Server + " | " + host);
    	}
    	
    	if (this.TCPClient.isConnectedServer()) throw new Exception("You has already remoted!");
//    	 Not connected
    	this.TCPClient.startConnectingToServer(host, port, password);
    	this.RMIClient.startConnectingToRmiServer(host, port + 1);
    	
    }
}


