package Rmi;

import java.awt.AWTException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


public class RmiServer {
	private String url;
	private boolean is_binding;

    public RmiServer() {
        this.url = null;
        this.is_binding = false;
    }

    public void startBindingOnRmiServer(String host, int port) throws RemoteException, MalformedURLException, AWTException {
        if(this.is_binding == false) {
            try {
                this.url = "rmi://" + host + ":" + port + "/remote";
                this.is_binding = true;
                System.setProperty("java.rmi.server.hostname", host);
                LocateRegistry.createRegistry(port);
                Naming.rebind(this.url, new RemoteDesktopImplement());
                System.out.println("co dang ky");
            }
            catch(Exception e) {
                // TODO: rebind when port already bound
                Naming.rebind(this.url, new RemoteDesktopImplement());
            }
        }
    }

    public void stopBindingOnRmiServer() throws RemoteException, MalformedURLException, NotBoundException {
        if(this.is_binding == true) {
            Naming.unbind(this.url);
            this.url = null;
            this.is_binding = false;
            System.out.println(" da ngat ket noi");
        }
    }

    public boolean isBinding() {
        return this.is_binding;
    }
}
