package Rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class RmiClient {
	private RemoteDesktopInterface Remote_Obj;
	private boolean is_remote_server;
	
	public RmiClient() {
        this.Remote_Obj = null;
        this.is_remote_server = false;
    }
	
	public void startConnectingToRmiServer(String host, int port) throws RemoteException, NotBoundException, MalformedURLException {
        if(this.is_remote_server == false) {
            String url = "rmi://" + host + ":" + port + "/remote";
            this.Remote_Obj = (RemoteDesktopInterface) Naming.lookup(url);
            this.is_remote_server = true;
        }
    }
	
	public void stopConnectingToRmiServer() {
        if(this.is_remote_server == true) {
            this.Remote_Obj = null;
            this.is_remote_server = false;
        }
    }
	
	public RemoteDesktopInterface getRemoteObject() {
        return this.Remote_Obj;
    }
	
	public boolean isRemoteServer() {
        return this.is_remote_server;
    }
	
	public void setRemoteServer(boolean b) {
        this.is_remote_server = b;
    }

}
