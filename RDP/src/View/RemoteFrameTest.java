package View;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Rmi.RemoteDesktopInterface;
import controller.common.CommonController;

public class RemoteFrameTest extends JFrame implements Runnable {
	
	private CommonController comoncontroller;
	private RemoteDesktopInterface IRemote;
	private JPanel ScreenPanel;

	public RemoteFrameTest() throws HeadlessException {
		ScreenPanel = new JPanel();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
	
	private void Drawimage(byte[] data) throws IOException {
		ByteArrayInputStream input = new ByteArrayInputStream(data);
        BufferedImage image = ImageIO.read(input);
        Graphics graphics = ScreenPanel.getGraphics();
		graphics.drawImage(image, 0, 0, ScreenPanel.getWidth(), ScreenPanel.getHeight(), ScreenPanel);
	}

}
