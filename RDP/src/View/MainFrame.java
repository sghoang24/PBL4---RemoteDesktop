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
	private ChatController chatController;
	private ServerPanel server_Panel;
	private ClientPanel client_Panel;
	private MainChatPanel mainchat_Panel;

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
		this.commonController = new CommonController();
	
		
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
		
		this.server_Panel = new ServerPanel(this.commonController);
		tabbedPane.addTab("Server", null, server_Panel, "Server Panel");
		
		this.client_Panel = new ClientPanel(this.commonController);
		tabbedPane.addTab("Client", null, client_Panel, "Client Panel");
		
		mainchat_Panel = new MainChatPanel(commonController);
		tabbedPane.addTab("Chat", null, mainchat_Panel, "Main Chat Panel");
		this.commonController.setChatPanel(mainchat_Panel);
	}
	
	private void mainFrameWindowClosing(WindowEvent e) throws IOException, NotBoundException {
        commonController.stopListeningOnServer();
    }
}
