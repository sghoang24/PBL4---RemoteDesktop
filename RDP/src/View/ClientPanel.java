package View;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

public class ClientPanel extends JPanel {
	private JTextField txtRemoteIP;
	private JTextField txtRemotePort;
	private JPasswordField txtpassword;

	/**
	 * Create the panel.
	 */
	public ClientPanel() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
				new Color(255, 255, 255), new Color(160, 160, 160)), "Connect to Server", 
				TitledBorder.LEADING, TitledBorder.TOP, new Font("Times New Roman", Font.BOLD, 14), Color.BLACK));
		panel.setBounds(63, 34, 330, 206);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblRemoteIP = new JLabel("Remote IP: ");
		lblRemoteIP.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblRemoteIP.setBounds(22, 27, 94, 28);
		panel.add(lblRemoteIP);
		
		JLabel lblRemotePort = new JLabel("Remote Port: ");
		lblRemotePort.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblRemotePort.setBounds(22, 69, 92, 28);
		panel.add(lblRemotePort);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblPassword.setBounds(22, 107, 94, 28);
		panel.add(lblPassword);
		
		txtRemoteIP = new JTextField();
		txtRemoteIP.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtRemoteIP.setBounds(126, 28, 155, 27);
		panel.add(txtRemoteIP);
		txtRemoteIP.setColumns(10);
		
		txtRemotePort = new JTextField();
		txtRemotePort.setText("9999");
		txtRemotePort.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtRemotePort.setColumns(10);
		txtRemotePort.setBounds(126, 70, 155, 27);
		panel.add(txtRemotePort);
		
		txtpassword = new JPasswordField();
		txtpassword.setEchoChar('*');
		txtpassword.setToolTipText("");
		txtpassword.setBounds(126, 108, 155, 28);
		panel.add(txtpassword);
		
		JLabel lblConnectNow = new JLabel("Connect now");
		lblConnectNow.setIcon(new ImageIcon("C:\\Users\\TECHCARE\\Documents\\GitHub\\PBL4---RemoteDesktop\\RDP\\Images\\connect_icon.png"));
		lblConnectNow.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblConnectNow.setBounds(277, 255, 116, 36);
		add(lblConnectNow);

	}
}
