package Test;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import View.ChatPanel;
import View.MainFrame;
import controller.chat.ChatController;
import controller.chat.Message;
import controller.chat.StringMessage;
import controller.common.CommonController;
import gui.common.CommonLabel;

public class testChatPanel extends JPanel implements Runnable {
    public final static String CONTENT_BACKGROUND = "0xFFFFFF";
    public final static int MAX_LENGTH_LINE = 20;

    private JTextField txtMessage;
    private JLabel lblSend;
	private String FilePath;
	private ChatController chat_bus;
	private JScrollPane scrollPane;
	private JPanel contentPanel;
    private GroupLayout layout;
    private GroupLayout.ParallelGroup h_parallel;
    private GroupLayout.SequentialGroup v_sequential;
	//private GroupLayout.ParallelGroup h_parallel;
	/**
	 * Create the panel.
	 */
	public testChatPanel() {
		this.setBackground(SystemColor.textHighlight);
		this.setLayout(null);
		this.initComponents();
	
	}
		
	private void initComponents() {
		
		txtMessage = new JTextField();
		txtMessage.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtMessage.setBounds(10, 244, 339, 54);
		add(txtMessage);
		txtMessage.setColumns(10);
		
//		scrollPane.setBounds(10, 10, 439, 214);
//		add(scrollPane);
		
//		JTextArea txtAreaChat = new JTextArea();
//		txtAreaChat.setEditable(false);
//		txtAreaChat.setFont(new Font("Times New Roman", Font.PLAIN, 13));
//		scrollPane.setViewportView(txtAreaChat);
		this.contentPanel = new JPanel();
		this.scrollPane = new JScrollPane();
		
		this.layout = new GroupLayout(this.contentPanel);
        this.h_parallel = this.layout.createParallelGroup();
        this.v_sequential = this.layout.createSequentialGroup();
        this.layout.setHorizontalGroup(h_parallel);
        this.layout.setVerticalGroup(v_sequential);
        
        this.contentPanel.setBackground(Color.WHITE);
        this.contentPanel.setLayout(layout);
        
        this.scrollPane.setViewportView(this.contentPanel);
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.scrollPane.setBounds(10, 10, 439, 214);
        add(scrollPane);
        
		
		
		lblSend = new JLabel("Send");
		lblSend.setIcon(new ImageIcon("D:\\DUT - Year 3\\PBL4\\PBL4---RemoteDesktop\\RDP\\Images\\send_icon.png"));
		lblSend.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblSend.setBounds(367, 234, 82, 29);
		lblSend.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                sendLabelMousePressed(e);
            }
        });
		add(lblSend);
		
		JLabel lblFile = new JLabel("File");
		lblFile.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblFile.setIcon(new ImageIcon("D:\\DUT - Year 3\\PBL4\\PBL4---RemoteDesktop\\RDP\\Images\\file_icon.png"));
		lblFile.setBounds(367, 274, 82, 29);
		lblFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChooseFile();
				super.mouseClicked(e);
			}
		});
		add(lblFile);

	}
	
	public void  ChooseFile() {
		final JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(this);
		if (fc.getSelectedFile() != null) {
			FilePath = fc.getSelectedFile().getPath();
		}
		txtMessage.setText(FilePath);
	}
	
	private void sendLabelMousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1 && this.lblSend.isEnabled()) {
            this.sendMessage();
        }
    }
	
	private void sendMessage() {
        try {
            String content = this.txtMessage.getText();
            if(!content.trim().equals("")) {
//                StringMessage str_message = new StringMessage(InetAddress.getLocalHost().getHostName(), content);
//                this.chat_bus.sendMessage(str_message);
                int gap = this.scrollPane.getWidth() - 180;

                JLabel label = new JLabel("You send a message:" + content);
                label.setFont(new Font("consolas", Font.PLAIN, 14));
                label.setForeground(Color.BLACK);
                this.addMessageToPanel(label, gap, "green");
            }
            this.txtMessage.setText("");
        }
        catch(Exception exception) {
            JOptionPane.showMessageDialog(this, "Can't send message:\n" + exception.getMessage());
        }
    }
	
	public void addMessageToPanel(JLabel label, int gap, String color_header) {
        EventQueue.invokeLater(() -> {
            label.setText(this.handleMessage(label.getText(), color_header));

            this.h_parallel.addGroup(
                this.layout.createSequentialGroup()
                    .addContainerGap(gap, gap)
                    .addComponent(label)
            );
            this.v_sequential.addComponent(label).addGap(10, 10, 10);
            this.scrollPane.revalidate();

            // TODO: move scroll to bottom
            this.scrollPane.getViewport().setViewPosition(new Point(0, this.scrollPane.getVerticalScrollBar().getMaximum()));
            this.scrollPane.getViewport().setViewPosition(new Point(0, this.scrollPane.getVerticalScrollBar().getMaximum()));
        });
    }
	
	private String handleMessage(String message, String color_header) {
        String formatted_message = "<html>";
        formatted_message += this.getHeaderName(message, color_header);
        formatted_message += this.multiLinesString(message);
        formatted_message += "</html>";
        return formatted_message;
    }
	
	private String getHeaderName(String message, String color_header) {
        if(message.contains(":")) {
            String header_name = "<font color='" + color_header + "'>";
            header_name += message.substring(0, message.indexOf(':') + 1) + "</font><br>";
            return header_name;
        }
        return "";
    }
	
	private String multiLinesString(String message) {
        message = message.substring(message.indexOf(':') + 1);
        if(message.length() > ChatPanel.MAX_LENGTH_LINE) {
            int loops = message.length() / ChatPanel.MAX_LENGTH_LINE;
            int index = 0;
            String content = "";
            for(int i = 0; i < loops; ++i) {
                content += message.substring(index, index + ChatPanel.MAX_LENGTH_LINE) + "<br>";
                index += ChatPanel.MAX_LENGTH_LINE;
            }
            content += message.substring(index);
            return content;
        }
        return message;
    }
    

    // TODO: recv messages
    @Override
    public void run() {
        while(true) {
            try {
                if(true) {
                    Message obj_message = this.chat_bus.recvMessage();
                    if(obj_message != null) {
                        if(obj_message.getCurrentType() == Message.STRING_MESSAGE) {
                            StringMessage str_message = (StringMessage) obj_message;

                            JLabel label = new JLabel(str_message.getSender() + " send a message:" + str_message.getContent());
                            label.setFont(new Font("consolas", Font.PLAIN, 14));
                            label.setForeground(Color.BLACK);
                            this.addMessageToPanel(label, 0, "blue");
                        }
                        
                    }
                    continue; // TODO: pass sleep if connected
                }
                Thread.sleep(1000); // TODO: update status of client and server
            }
            catch(Exception e) {


                 

                try {
                    this.chat_bus.getSocket().close();
                }
                catch(Exception exception) {}

                break;
            }
        }
    }
    
    public static void main(String[] args) {
		JFrame f =new JFrame();
		testChatPanel a = new testChatPanel();
		f.add(a);
		
		f.setVisible(true);
	}

}
