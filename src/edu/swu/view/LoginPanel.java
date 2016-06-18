package edu.swu.view;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class LoginPanel extends JPanel {
	
	private static final long serialVersionUID = 5346764762861110013L;
	protected ImageIcon icon;
	public int width,height;
	
	public LoginPanel() {
		super();
		icon = new ImageIcon("res/login.png");
		width = icon.getIconWidth();
		height = icon.getIconHeight();
		setSize(width, height);
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image img = icon.getImage();
		g.drawImage(img, 0, 0,getParent());
	}
}