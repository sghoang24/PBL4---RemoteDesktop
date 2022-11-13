package View;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;

public class ChatPanel extends JPanel {
	private JTextField txtMessage;

	/**
	 * Create the panel.
	 */
	public ChatPanel() {
		setBackground(SystemColor.textHighlight);
		setLayout(null);
		
		txtMessage = new JTextField();
		txtMessage.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtMessage.setBounds(10, 244, 339, 54);
		add(txtMessage);
		txtMessage.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 439, 214);
		add(scrollPane);
		
		JTextArea txtAreaChat = new JTextArea();
		txtAreaChat.setEditable(false);
		txtAreaChat.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		scrollPane.setViewportView(txtAreaChat);
		
		JLabel lblSend = new JLabel("Send");
		lblSend.setIcon(new ImageIcon("D:\\DUT - Year 3\\PBL4\\PBL4---RemoteDesktop\\RDP\\Images\\send_icon.png"));
		lblSend.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblSend.setBounds(367, 234, 82, 29);
		add(lblSend);
		
		JLabel lblFile = new JLabel("File");
		lblFile.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblFile.setIcon(new ImageIcon("D:\\DUT - Year 3\\PBL4\\PBL4---RemoteDesktop\\RDP\\Images\\file_icon.png"));
		lblFile.setBounds(367, 274, 82, 29);
		add(lblFile);

	}
}
