package View;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainProgram {
	private MainFrame mainFrame;

	public MainProgram() {
		mainFrame = new MainFrame();
	}
	
	public void createtrayicon() {
		if(SystemTray.isSupported()) {
			SystemTray tray = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().getImage(MainProgram.class.getResource("window_icon.png"));
			
			PopupMenu popup = new PopupMenu();
			
			final MenuItem menuexit = new MenuItem("Exit");
			menuexit.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Runtime r = Runtime.getRuntime();
					r.exit(0);
					
				}
			});
			
			final MenuItem menuopen = new MenuItem("Open");
			menuopen.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					mainFrame.setVisible(true);
				}
			});
			
			popup.add(menuexit);
			popup.add(menuopen);
			
			final TrayIcon trayicon = new TrayIcon(image, "Remote Desktop", popup);
			trayicon.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					trayicon.displayMessage("Remote Desktop", "PBL04 By Long-Nhan-Hoang", MessageType.INFO);
					
				}
			});
			trayicon.setImageAutoSize(true);
			
			try {
				tray.add(trayicon);
			} catch (AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		}
		
	}

	public static void main(String[] args) {
		MainProgram myProgram = new MainProgram();
		myProgram.createtrayicon();

	}

}
