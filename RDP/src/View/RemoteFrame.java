package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
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
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
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
	private JLabel lablescreen;
	
	private Thread ScreenThread;
	
	public RemoteFrame(ClientPanel clientpanel, CommonController comoncontroller, String quality) throws HeadlessException, RemoteException {
		this.clientpanel = clientpanel;
		this.comoncontroller = comoncontroller;
		Quality = quality;
		this.IRemote = this.comoncontroller.getRmiClient().getRemoteObject();
		
		//set up frame
		this.getContentPane().setBackground(Color.WHITE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // add listener
        this.setVisible(true);
        
        
        
        SetupScreenPanel();
        
//        this.initComponents();
        this.ScreenThread = new Thread(this);
        this.ScreenThread.start();

	}




	@Override
//	public void run() {
//		System.out.println("co share man hinh");
//		Dimension Screensize = Toolkit.getDefaultToolkit().getScreenSize();
//		int W =(int) Screensize.getWidth();
//		int H =(int) Screensize.getHeight();
//		try {
//			while(true) {
//				byte[] datascreen = this.IRemote.TakeScreen(Quality);
//				ByteArrayInputStream bis = new ByteArrayInputStream(datascreen);
//				BufferedImage image = ImageIO.read(bis);
//				
//				Graphics graphis = Screenpanel.getGraphics();
//				graphis.drawImage(image, 0, 0, W, H, Screenpanel);
//			}
//			
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}
//		
//
//	}
	public void run(){
		try{
			// Read screenshots of the client and then draw them
			System.out.println("in man hinh");
			while(true){
				byte[] bytes = this.IRemote.TakeScreen(Quality);
				ByteArrayInputStream input = new ByteArrayInputStream(bytes);
                BufferedImage image = ImageIO.read(input);
                ImageIO.write(image, Quality, new File("C:\\Users\\HP\\eclipse-workspace\\RMITCPDemo\\src\\Screen\\nhan.PNG"));
				System.out.println("da luu man hinh");
                //Draw the received screenshots
				Graphics graphics = Screenpanel.getGraphics();
				graphics.drawImage(image, 0, 0, Screenpanel.getWidth(), Screenpanel.getHeight(), Screenpanel);
			}

		} catch(IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void SetupScreenPanel() {
		this.Screenpanel = new JPanel();
		this.Screenpanel.setBackground(Color.GREEN);
		
		
		
		//add mouse even
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
        
        this.add(this.Screenpanel, BorderLayout.CENTER);
	}
	
	private void initComponents() throws RemoteException {
        // TODO: constructor
        this.lablescreen = new JLabel();

        // TODO: style lablescreen
        this.lablescreen.addMouseListener(new MouseAdapter() {
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
        this.lablescreen.addMouseMotionListener(new MouseMotionAdapter() {
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
        this.add(this.lablescreen);
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
