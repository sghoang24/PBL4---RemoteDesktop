package gui.common;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class CommonLabel extends JLabel {

	public CommonLabel() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tabLabelMouseEntered(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				tabLabelMouseExited(e);
			}

		});
	}

	public void setSmallFont() {
        this.setFont(new Font("Times New Roman", Font.PLAIN, 14));
    }

    public void setBigFont() {
        this.setFont(new Font("Times New Roman", Font.PLAIN, 20));
    }

    public void resetFont() {
        int font_size = this.getFont().getSize();
        this.setFont(new Font("Times New Roman", Font.PLAIN, font_size));
    }
	
	private void tabLabelMouseEntered(MouseEvent e) {
		if (this.isEnabled()) {
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			int font_size = this.getFont().getSize();
			this.setFont(new Font("Times New Roman", Font.BOLD, font_size));
		}
	}

	private void tabLabelMouseExited(MouseEvent e) {
		if (this.isEnabled()) {
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			int font_size = this.getFont().getSize();
			this.setFont(new Font("Times New Roman", Font.PLAIN, font_size));
		}
	}
}
