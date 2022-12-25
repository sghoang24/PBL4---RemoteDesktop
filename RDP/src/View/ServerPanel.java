package View;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import controller.common.CommonController;
import gui.common.CommonLabel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import java.net.SocketException;
import java.util.Vector;

import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;

public class ServerPanel extends JPanel implements Runnable {
	private JTextField tfPort;
	private JTextField tfPassword;
	private JLabel lbStatus;
	private CommonLabel lbStartListen;
	private CommonLabel lbStopListen;
	private JComboBox<String> cbxIP;
	private CommonController commonController;
	
	private Thread listen_thread;

	/**
	 * Create the panel.
	 */
	public ServerPanel(CommonController commonController) {
		this.commonController = commonController;
		this.initComponents();
	}
		
	public void initComponents() {
		lbStartListen = new CommonLabel();
		lbStopListen = new CommonLabel();
		lbStatus = new JLabel();
		setBackground(SystemColor.textHighlight);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
				new Color(255, 255, 255), new Color(160, 160, 160)), "Server Listening", 
				TitledBorder.LEADING, TitledBorder.TOP, new Font("Times New Roman", Font.BOLD, 14), Color.BLACK));
		panel.setBounds(40, 41, 367, 200);
		add(panel);
		panel.setLayout(null);
		
		JLabel lbServerIP = new JLabel("Server IP");
		lbServerIP.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbServerIP.setBounds(32, 34, 64, 14);
		panel.add(lbServerIP);
		
		JLabel lbPort = new JLabel("Port");
		lbPort.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbPort.setBounds(32, 78, 64, 14);
		panel.add(lbPort);
		
		JLabel lbPassword = new JLabel("Set Password");
		lbPassword.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbPassword.setBounds(32, 117, 80, 14);
		panel.add(lbPassword);
		
		cbxIP = new JComboBox();
		cbxIP.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cbxIP.setBounds(149, 30, 155, 27);
		cbxIP.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				try {
					cbxIP.removeAllItems();
					cbxIP.addItem("0.0.0.0");
					Vector<String> ipv4_Addresses = commonController.getTcpServer().getAllIPv4AddressesOnLocal();
					for (String ipv4 : ipv4_Addresses) 
						cbxIP.addItem(ipv4);
				} catch (SocketException ex) {}
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {}
			
		});
		panel.add(cbxIP);
		
		tfPort = new JTextField();
		tfPort.setText("9999");
		tfPort.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfPort.setBounds(149, 72, 155, 27);
		panel.add(tfPort);
		tfPort.setColumns(10);
		
		tfPassword = new JTextField();
		tfPassword.setText("123456");
		tfPassword.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfPassword.setColumns(10);
		tfPassword.setBounds(149, 111, 155, 27);
		panel.add(tfPassword);
		
		lbStatus.setText("Status: Stopped");
		lbStatus.setForeground(new Color(255, 140, 0));
		lbStatus.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lbStatus.setBounds(31, 149, 298, 33);
		panel.add(lbStatus);
		
		lbStartListen.setText("Start Listening");
		lbStartListen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lbStartListenMousePressed(e);
			}
		});
		lbStartListen.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		lbStartListen.setBounds(71, 254, 131, 28);
		lbStartListen.setIcon(new ImageIcon("Images\\listen_icon.png"));
		add(lbStartListen);
		
		lbStopListen.setText("Stop Listening");
		lbStopListen.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		lbStopListen.setBounds(258, 254, 131, 28);
		lbStopListen.setIcon(new ImageIcon("Images\\stop_icon.png"));
		lbStopListen.setEnabled(false);
		lbStopListen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lbStopListenMousePressed(e);
            }
        });
		add(lbStopListen);

	}
	
	// TODO: process listen event
	private void lbStartListenMousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 && lbStartListen.isEnabled()) {
			try {
				String host = cbxIP.getSelectedItem().toString().trim();
				int port = Integer.parseInt(tfPort.getText().trim());
				String password = tfPassword.getText().trim();
				commonController.startListeningOnServer(host, port, password);
				
				// TODO: start listen_thread
				listen_thread = new Thread(this);
				listen_thread.setDaemon(true);
				listen_thread.start();
				
				// TODO: set status
				lbStatus.setText("Status: Listening...");
				this.setEnabled(false);
				lbStartListen.setEnabled(false);
				lbStartListen.resetFont();
				lbStopListen.setEnabled(true);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Can't start listening on server:\n" + ex.getMessage());
			}
		}
	}
	
	// TODO: process stop event
	private void lbStopListenMousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1 && lbStopListen.isEnabled()) {
            try {
            	System.out.println("btn stop");
                commonController.stopListeningOnServer();

                // TODO: stop listen_thread
                this.listen_thread.interrupt();

                // TODO: set status
                lbStatus.setText("Status: Stopped");
                this.setEnabled(true);
                lbStopListen.resetFont();
                lbStopListen.setEnabled(false);
                lbStartListen.setEnabled(true);
            }
            catch(Exception exception) {
                System.out.println(exception.getMessage());
                JOptionPane.showMessageDialog(this, "Can't stop listening on server:\n" + exception.getMessage());
            }
        }
	}

	@Override
	public void run() {
		while (commonController.getTcpServer().isListening())
			try {
				commonController.getTcpServer().waitingForConnectionFromClient();
			} catch (Exception e) {
				// TODO: handle exception
			}
		
	}
}
