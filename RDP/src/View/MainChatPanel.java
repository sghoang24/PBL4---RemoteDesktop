package View;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.chat.ChatController;
import controller.common.CommonController;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

public class MainChatPanel extends JPanel {

	private CommonController commonController;
	/**
	 * Create the panel.
	 */
	public MainChatPanel(CommonController commonController) {
		this.commonController = commonController;
		this.initComponent();

	}
	
	private void initComponent() {
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, 
				new Color(255, 255, 255), new Color(160, 160, 160)), "Server Listening", 
				TitledBorder.LEADING, TitledBorder.TOP, new Font("Times New Roman", Font.BOLD, 14), Color.BLACK));
		panel.setBounds(40, 41, 367, 200);
		add(panel);
		panel.setLayout(null);
	}
	
	public void addNewConnection(ChatController chatController) {
		ChatPanel chatPanel = new ChatPanel(commonController, chatController);
		this.add(chatPanel);
	}

}
