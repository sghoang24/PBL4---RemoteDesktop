package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
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
	
	private Thread ScreenThread;
	private Dimension My_screen_size;
	private Insets taskbar_insets;
	private Insets frame_insets;	
	//Screen
	private float dx;
	private float dy;
	
	
//----------------------------------------------------
	public RemoteFrame(ClientPanel clientpanel, CommonController comoncontroller, String quality) throws Exception {
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
        
        // add listener key even
        this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					remoteFrameKeyReleased(e);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					remoteFrameKeyPressed(e);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
        
        
        //display Frame
        this.setVisible(true);
        this.My_screen_size = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame_insets = this.getInsets();
        this.taskbar_insets = Toolkit.getDefaultToolkit().getScreenInsets(this.getGraphicsConfiguration());
        
        SetupScreenPanel();
        
       
        this.ScreenThread = new Thread(this);
        this.ScreenThread.start();
        setupWindow();

	}
//----------------------------------------------------



	@Override
	public void run(){
		try{
			// Read screenshots of the client and then draw them
			while(this.comoncontroller.getTcpServer().isHasPartner() || this.comoncontroller.getTcpClient().isConnectedServer()){			
				byte[] bytes = this.IRemote.TakeScreen(Quality);
				ByteArrayInputStream input = new ByteArrayInputStream(bytes);
	            BufferedImage image = ImageIO.read(input);
	            //Draw the received screenshots
				Graphics graphics = Screenpanel.getGraphics();
				graphics.drawImage(image, 0, 0, Screenpanel.getWidth(), Screenpanel.getHeight(), Screenpanel);
			}
			this.dispose();
	
		}catch(IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//----------------------------------------------------
	
	
	
	
	private void SetupScreenPanel() {
		//set up panel
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
        this.addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowClosing(WindowEvent e) {
        		remoteFrameWindowClosing(e);
        	}
		});
	}
	
	private void setupWindow() throws Exception {
		
		
		//get screenhost server
        ImageIO.setUseCache(false);
        byte[] dgram = this.IRemote.TakeScreen(Quality);
        ByteArrayInputStream bis = new ByteArrayInputStream(dgram);
        BufferedImage screenshot = ImageIO.read(bis);
        
        
        this.My_screen_size.height -= (this.taskbar_insets.top + this.taskbar_insets.bottom + this.frame_insets.top);
        
        
        //scale man hinh
        if(this.My_screen_size.width == screenshot.getWidth() && this.My_screen_size.height == screenshot.getHeight()) {
        	//bang nhau hoac lơn hon server
	          this.dx = 1;
	          this.dy = 1;
        }
        else {
        	//scale man hinh neu client nho hơn
        	this.dx = (float) screenshot.getWidth() / this.My_screen_size.width;
        	this.dy = (float) screenshot.getHeight() / this.My_screen_size.height;
        	System.out.println(dx);
        }
    }
//----------------------------------------------------	
	
	
	
	
	
	
	
	
	//listener even:
	// TODO: remote keyboard of server
    private void remoteFrameKeyPressed(KeyEvent e) throws RemoteException {
        this.IRemote.keyPressedServer(e.getKeyCode());
    }

    private void remoteFrameKeyReleased(KeyEvent e) throws RemoteException {
        this.IRemote.keyReleasedServer(e.getKeyCode());
    }
    
    
    
    // TODO: remote mouse of server
    private void screenLabelMousePressed(MouseEvent e) throws RemoteException {
        this.IRemote.mousePressedServer(InputEvent.getMaskForButton(e.getButton()));
    }

    private void screenLabelMouseReleased(MouseEvent e) throws RemoteException {
        this.IRemote.mouseReleasedServer(InputEvent.getMaskForButton(e.getButton()));
    }

    private void screenLabelMouseMoved(MouseEvent e) throws RemoteException {
        float x = e.getX() *dx;
        float y = e.getY() *dy;
        this.IRemote.mouseMovedServer((int) x, (int) y);
    }

    private void screenLabelMouseDragged(MouseEvent e) throws RemoteException {
        float x = e.getX() *dx;
        float y = e.getY() *dy;
        this.IRemote.mouseMovedServer((int) x, (int) y);
    }

    private void screenLabelMouseWheelMoved(MouseWheelEvent e) throws RemoteException {
        this.IRemote.mouseWheelServer(e.getWheelRotation());
    }
    
    @Override
    public void dispose() {
    	try {
            super.setVisible(false);
            super.dispose();
            this.clientpanel.setEnabled(true);
            this.comoncontroller.getRmiClient().setRemoteServer(false);
            this.comoncontroller.getTcpClient().setConnectedServer(false);
            this.comoncontroller.getTcpClient().getClient().close();
            if(!this.ScreenThread.isInterrupted())
                this.ScreenThread.isInterrupted();
        }
        catch(Exception exception) {
            JOptionPane.showMessageDialog(null, "Can't close connection");
        }
    }
    
    private void remoteFrameWindowClosing(WindowEvent e) {
        this.dispose();
    }

}
