package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.chat.ChatController;
import controller.common.CommonController;

import javax.swing.JTabbedPane;
import java.awt.SystemColor;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.rmi.NotBoundException;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	
	private CommonController commonController;
	private ServerPanel server_Panel;
	private ClientPanel client_Panel;
	private ChatPanel chat_Panel;

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
		this.initComponents();
	}
		
	private void initComponents() {
		commonController = new CommonController();
		commonController.setChatPanel(chat_Panel);
		
		setTitle("Remote Desktop Application");
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
		
		server_Panel = new ServerPanel(commonController);
		tabbedPane.addTab("Server", null, server_Panel, "Server Panel");
		
		client_Panel = new ClientPanel();
		tabbedPane.addTab("Client", null, client_Panel, "Client Panel");
		
		chat_Panel = new ChatPanel(commonController);
		tabbedPane.addTab("Chat", null, chat_Panel, "Chat Panel");
	}
	
	private void mainFrameWindowClosing(WindowEvent e) throws IOException, NotBoundException {
        commonController.stopListeningOnServer();
    }
}
