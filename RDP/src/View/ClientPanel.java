package View;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorListener;

import controller.common.CommonController;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClientPanel extends JPanel {
	private JTextField txtRemoteIP;
	private JTextField txtRemotePort;
	private JPasswordField txtpassword;
	private JLabel lbConnectNow;
	private CommonController common_Controller;
	/**
	 * Create the panel.
	 */
	public ClientPanel() {
		setBackground(SystemColor.textHighlight);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
				new Color(255, 255, 255), new Color(160, 160, 160)), "Connect to Server", 
				TitledBorder.LEADING, TitledBorder.TOP, new Font("Times New Roman", Font.BOLD, 14), Color.BLACK));
		panel.setBounds(40, 41, 367, 204);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblRemoteIP = new JLabel("Remote IP: ");
		lblRemoteIP.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblRemoteIP.setBounds(22, 27, 94, 28);
		panel.add(lblRemoteIP);
		
		JLabel lblRemotePort = new JLabel("Remote Port: ");
		lblRemotePort.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblRemotePort.setBounds(22, 69, 92, 28);
		panel.add(lblRemotePort);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblPassword.setBounds(22, 107, 94, 28);
		panel.add(lblPassword);
		
		txtRemoteIP = new JTextField();
		txtRemoteIP.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtRemoteIP.setBounds(139, 28, 155, 27);
		panel.add(txtRemoteIP);
		txtRemoteIP.setColumns(10);
		
		txtRemotePort = new JTextField();
		txtRemotePort.setText("9999");
		txtRemotePort.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtRemotePort.setColumns(10);
		txtRemotePort.setBounds(139, 70, 155, 27);
		panel.add(txtRemotePort);
		
		txtpassword = new JPasswordField();
		txtpassword.setEchoChar('*');
		txtpassword.setToolTipText("");
		txtpassword.setBounds(139, 107, 155, 28);
		panel.add(txtpassword);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setForeground(new Color(255, 0, 51));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setBounds(22, 146, 303, 36);
		panel.add(lblNewLabel);
		
		lbConnectNow = new JLabel("Connect now");
		lbConnectNow.setIcon(new ImageIcon("D:\\DUT - Year 3\\PBL4\\PBL4---RemoteDesktop\\RDP\\Images\\connect_icon.png"));
		lbConnectNow.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lbConnectNow.setBounds(291, 253, 116, 36);
		lbConnectNow.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
		});
		add(lbConnectNow);

	}
	private boolean isFormatIpv4(String host) {
        int count = 0;
        for(int i = 0; i < host.length(); ++i) {
            if(host.charAt(i) == '.') ++count;
        }
        // TODO: count = 3 for ipv4
        // TODO: count = 0 for host name
        return count == 3 || count == 0;
    }
	
	private void connectLabelMousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1 && lbConnectNow.isEnabled()) {
            this.setEnabled(false);
            //this.loader_label.setVisible(true);

            Thread connect_thread = new Thread(() -> {
                try {
                    String host = txtRemoteIP.getText().trim();
                    int port = Integer.parseInt(txtRemotePort.getText().trim());
                    String password = txtpassword.getText().trim();
                    // TODO: check format ipv4
                    if(!this.isFormatIpv4(host)) throw new Exception("Wrong format IPV4");

                    // TODO: start connect
                    this.common_Controller.startConnectingToServer(host, port, password);

                    // TODO: show remote screen
                    EventQueue.invokeLater(() -> {
                        try {
//                            if(this.low_radio.isSelected()) {
//                                new RemoteFrame(this, this.common_bus, "jpg");
//                            }
//                            else 
                            //if(this.high_radio.isSelected()) {
                                new RemoteFrame(this, this.common_Controller, "png");
                            //}
                        }
                        catch(Exception exception) {
                            JOptionPane.showMessageDialog(this, "Can't start remoting to server:\n" + exception.getMessage());
                        }
                    });
                }
                catch(Exception exception) {
                    JOptionPane.showMessageDialog(this, "Can't connect to server:\n" + exception.getMessage());
                }
                this.setEnabled(true);
                //this.loader_label.setVisible(false);
            });
            connect_thread.setDaemon(true);
            connect_thread.start();
        }
    }
}
