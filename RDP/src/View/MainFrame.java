package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.SystemColor;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 399);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(21, 10, 461, 337);
		contentPane.add(tabbedPane);
		
//		JPanel pnl_Server = new JPanel();
		ServerPanel server_Panel = new ServerPanel();
		tabbedPane.addTab("Server", null, server_Panel, "Server Panel");
		
//		JPanel pnl_Client = new JPanel();
		ClientPanel client_Panel = new ClientPanel();
		tabbedPane.addTab("Client", null, client_Panel, "Client Panel");
		
//		JPanel pnl_Chat = new JPanel();
		ChatPanel chat_Panel = new ChatPanel();
		tabbedPane.addTab("Chat", null, chat_Panel, "Chat Panel");
	}
}
