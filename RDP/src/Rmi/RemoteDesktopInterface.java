package Rmi;

import java.rmi.Remote;

public interface RemoteDesktopInterface extends Remote {
	public byte[] TakeScreen(String Quality) throws Exception;

}
