package Rmi;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

import javax.imageio.ImageIO;

public class RemoteDesktopImplement extends UnicastRemoteObject implements RemoteDesktopInterface {
	
	private Robot mr_robot;
	
	

	public RemoteDesktopImplement() throws RemoteException, AWTException {
		this.mr_robot = new Robot();
	}



	@Override
	public byte[] TakeScreen(String Quality) throws Exception {
		Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle bounds = new Rectangle(screen_size);
		BufferedImage Screenshot = this.mr_robot.createScreenCapture(bounds);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(Screenshot, Quality, bos);
		byte[] data = bos.toByteArray();
		return data;
	}



	@Override
	public void mouseMovedServer(int x, int y) throws RemoteException {
		this.mr_robot.mouseMove(x, y);	
	}



	@Override
	public void mousePressedServer(int buttons) throws RemoteException {
		this.mr_robot.mousePress(buttons);
		
	}



	@Override
	public void mouseReleasedServer(int buttons) throws RemoteException {
		this.mr_robot.mouseRelease(buttons);
	}



	@Override
	public void mouseWheelServer(int wheel_amt) throws RemoteException {
		this.mr_robot.mouseWheel(wheel_amt);
		
	}



	@Override
	public void keyPressedServer(int keycode) throws RemoteException {
		this.mr_robot.keyPress(keycode);
	}



	@Override
	public void keyReleasedServer(int keycode) throws RemoteException {
		this.mr_robot.keyRelease(keycode);
	}

}
