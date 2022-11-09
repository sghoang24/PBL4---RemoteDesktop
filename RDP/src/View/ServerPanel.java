package View;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ServerPanel extends JPanel {
	private JTextField tfPort;
	private JTextField tfPassword;
	private JLabel lbStartListen;
	private JLabel lbStopListen;
	private JComboBox<String> cbxIP;

	/**
	 * Create the panel.
	 */
	public ServerPanel() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Server Listening", TitledBorder.LEADING, TitledBorder.TOP, 
					new Font("Times New Roman", Font.BOLD, 14), Color.BLACK));
		panel.setBounds(40, 41, 367, 193);
		add(panel);
		panel.setLayout(null);
		
		JLabel lbServerIP = new JLabel("Server IP");
		lbServerIP.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lbServerIP.setBounds(32, 34, 64, 14);
		panel.add(lbServerIP);
		
		JLabel lbPort = new JLabel("Port");
		lbPort.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lbPort.setBounds(32, 71, 64, 14);
		panel.add(lbPort);
		
		JLabel lbPassword = new JLabel("Set Password");
		lbPassword.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lbPassword.setBounds(32, 106, 80, 14);
		panel.add(lbPassword);
		
		cbxIP = new JComboBox();
		cbxIP.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cbxIP.setBounds(149, 30, 155, 22);
		panel.add(cbxIP);
		
		tfPort = new JTextField();
		tfPort.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfPort.setBounds(149, 66, 155, 25);
		panel.add(tfPort);
		tfPort.setColumns(10);
		
		tfPassword = new JTextField();
		tfPassword.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfPassword.setColumns(10);
		tfPassword.setBounds(149, 101, 155, 25);
		panel.add(tfPassword);
		
		JLabel lbStatus = new JLabel("New label");
		lbStatus.setForeground(Color.RED);
		lbStatus.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lbStatus.setBounds(31, 157, 65, 14);
		panel.add(lbStatus);
		
		lbStartListen = new JLabel("Start Listening");
		lbStartListen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lbStartListenMousePressed(e);
			}
		});
		lbStartListen.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lbStartListen.setBounds(71, 252, 122, 28);
		lbStartListen.setIcon(new ImageIcon("D:\\DUT - Year 3\\PBL4\\PBL4---RemoteDesktop\\RDP\\Images\\listen_icon.png"));
		add(lbStartListen);
		
		lbStopListen = new JLabel("Stop Listening");
		lbStopListen.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lbStopListen.setBounds(258, 252, 122, 28);
		lbStopListen.setIcon(new ImageIcon("D:\\DUT - Year 3\\PBL4\\PBL4---RemoteDesktop\\RDP\\Images\\stop_icon.png"));
		add(lbStopListen);

	}
	
	private void lbStartListenMousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 && lbStartListen.isEnabled()) {
			try {
				String host = cbxIP.getSelectedItem().toString().trim();
				int port = Integer.parseInt(tfPort.getText().trim());
				String password = tfPassword.getText().trim();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
