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
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;

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
		panel.add(cbxIP);
		
		tfPort = new JTextField();
		tfPort.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfPort.setBounds(149, 72, 155, 27);
		panel.add(tfPort);
		tfPort.setColumns(10);
		
		tfPassword = new JTextField();
		tfPassword.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfPassword.setColumns(10);
		tfPassword.setBounds(149, 111, 155, 27);
		panel.add(tfPassword);
		
		JLabel lbStatus = new JLabel("New label");
		lbStatus.setForeground(new Color(255, 0, 51));
		lbStatus.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lbStatus.setBounds(31, 149, 298, 33);
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
