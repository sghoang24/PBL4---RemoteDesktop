package Rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteDesktopInterface extends Remote {
	
	//Screen
	public byte[] TakeScreen(String Quality) throws Exception;
	
	//mouse
	public void mouseMovedServer(int x, int y) throws RemoteException;
	void mousePressedServer(int buttons) throws RemoteException;
    void mouseReleasedServer(int buttons) throws RemoteException;
    void mouseWheelServer(int wheel_amt) throws RemoteException;
    
    //keyboard
    void keyPressedServer(int keycode) throws RemoteException;
    void keyReleasedServer(int keycode) throws RemoteException;
    
    //informationPC
}
