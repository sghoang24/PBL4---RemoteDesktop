package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Rmi.RemoteDesktopInterface;
import controller.common.CommonController;

public class RemoteFrame extends JFrame implements Runnable {
	
	private ClientPanel clientpanel;
	private CommonController comoncontroller;
	private String Quality;
	
	private RemoteDesktopInterface IRemote;
	private JPanel Screenpanel;
	
	public RemoteFrame(ClientPanel clientpanel, CommonController comoncontroller, String quality) throws HeadlessException {
		this.clientpanel = clientpanel;
		this.comoncontroller = comoncontroller;
		Quality = quality;
		
		//set up frame
		this.getContentPane().setBackground(Color.BLACK);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // add listener
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    remoteFrameWindowClosing(e);
                }
                catch(Exception exception) {
                    dispose();
                    JOptionPane.showMessageDialog(null, "Can't close connection");
                }
            }

            @Override
            public void windowOpened(WindowEvent e) {
                remoteFrameWindowOpened(e);
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    remoteFrameKeyPressed(e);
                }
                catch(RemoteException remoteException) {
                    dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    remoteFrameKeyReleased(e);
                }
                catch(RemoteException remoteException) {
                    dispose();
                }
            }
        });
        this.setVisible(true);

        
        this.IRemote = this.comoncontroller.getRmiClient().getRemoteObject();
        SetupScreenPanel();
        
        
        //this.SizeScreen = Toolkit.getDefaultToolkit().getScreenSize();
        //this.frame_insets = this.getInsets();
        //this.taskbar_insets = Toolkit.getDefaultToolkit().getScreenInsets(this.getGraphicsConfiguration());
        
//        this.Screen_Thread = new Thread(this);
//        this.Screen_Thread.setDaemon(true);
//        this.Screen_Thread.start();
//        
	}




	@Override
	public void run() {
		Dimension Screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int W =(int) Screensize.getWidth();
		int H =(int) Screensize.getHeight();
		try {
			while(this.comoncontroller.getTcpClient().isConnectedServer()) {
				byte[] datascreen = this.IRemote.TakeScreen(Quality);
				ByteArrayInputStream bis = new ByteArrayInputStream(datascreen);
				BufferedImage image = ImageIO.read(bis);
				
				Graphics graphis = Screenpanel.getGraphics();
				graphis.drawImage(image, 0, 0, W, H, Screenpanel);
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		

	}
	
	private void SetupScreenPanel() {
		this.Screenpanel = new JPanel();
		this.Screenpanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    screenLabelMousePressed(e);
                }
                catch(RemoteException remoteException) {
                    dispose();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    screenLabelMouseReleased(e);
                }
                catch(RemoteException remoteException) {
                    dispose();
                }
            }
        });
        this.addMouseWheelListener((e) -> {
            try {
                screenLabelMouseWheelMoved(e);
            }
            catch(RemoteException remoteException) {
                dispose();
            }
        });
        this.Screenpanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                try {
                    screenLabelMouseMoved(e);
                }
                catch(RemoteException remoteException) {
                    dispose();
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                try {
                    screenLabelMouseDragged(e);
                }
                catch(RemoteException remoteException) {
                    dispose();
                }
            }
        });
        this.add(this.Screenpanel);
		
	}
	
	private void remoteFrameWindowClosing(WindowEvent e) {
        this.dispose();
    }

    private void remoteFrameWindowOpened(WindowEvent e) {
        this.clientpanel.setEnabled(false);
    }

    // TODO: remote keyboard of server
    private void remoteFrameKeyPressed(KeyEvent e) throws RemoteException {
        this.IRemote.keyPressedServer(e.getKeyCode());
    }

    private void remoteFrameKeyReleased(KeyEvent e) throws RemoteException {
        this.IRemote.keyReleasedServer(e.getKeyCode());
    }
    private void screenLabelMousePressed(MouseEvent e) throws RemoteException {
        this.IRemote.mousePressedServer(InputEvent.getMaskForButton(e.getButton()));
    }

    private void screenLabelMouseReleased(MouseEvent e) throws RemoteException {
        this.IRemote.mouseReleasedServer(InputEvent.getMaskForButton(e.getButton()));
    }

    private void screenLabelMouseMoved(MouseEvent e) throws RemoteException {
        float x = e.getX() ;
        float y = e.getY() ;
        this.IRemote.mouseMovedServer((int) x, (int) y);
    }

    private void screenLabelMouseDragged(MouseEvent e) throws RemoteException {
        float x = e.getX() ;
        float y = e.getY() ;
        this.IRemote.mouseMovedServer((int) x, (int) y);
    }

    private void screenLabelMouseWheelMoved(MouseWheelEvent e) throws RemoteException {
        this.IRemote.mouseWheelServer(e.getWheelRotation());
    }
}
