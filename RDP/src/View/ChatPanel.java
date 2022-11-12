package View;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class ChatPanel extends JPanel {
	private JTextField txtMessage;

	/**
	 * Create the panel.
	 */
	public ChatPanel() {
		setLayout(null);
		
		txtMessage = new JTextField();
		txtMessage.setBounds(10, 263, 339, 73);
		add(txtMessage);
		txtMessage.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 463, 232);
		add(scrollPane);
		
		JTextArea txtAreaChat = new JTextArea();
		scrollPane.setViewportView(txtAreaChat);
		
		JLabel lblSend = new JLabel("Send");
		lblSend.setIcon(new ImageIcon("C:\\Users\\TECHCARE\\Documents\\GitHub\\PBL4---RemoteDesktop\\RDP\\Images\\send_icon.png"));
		lblSend.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSend.setBounds(391, 263, 82, 29);
		add(lblSend);
		
		JLabel lblFile = new JLabel("File");
		lblFile.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblFile.setIcon(new ImageIcon("C:\\Users\\TECHCARE\\Documents\\GitHub\\PBL4---RemoteDesktop\\RDP\\Images\\file_icon.png"));
		lblFile.setBounds(391, 307, 82, 29);
		add(lblFile);

	}
}
