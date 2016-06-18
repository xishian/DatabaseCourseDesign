package edu.swu.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton {

	private Image image;
	private Image plainImage;
	private Image rollImage;
	private Image pressImage;
	private String text;
	private Icon icon;
	private Font font;

	private static final long serialVersionUID = 2230298756223996927L;
	
	ImageButton(){
		
	}
	
	public ImageButton(String text){
		super();
		
		this.plainImage = new ImageIcon("button.png").getImage();
		this.rollImage = new ImageIcon("buttonup.png").getImage();
		this.pressImage = new ImageIcon("buttondown.png").getImage();
		this.text = text;
		this.image = plainImage;
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ImageButton.this.setImage(pressImage);
				System.out.println("mouse pressed");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				ImageButton.this.setImage(plainImage);
				System.out.println("mouse exited");
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageButton.this.setImage(rollImage);
				System.out.println("mouse entered");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				ImageButton.this.setImage(plainImage);
				System.out.println("mouse clicked");
			}
		});
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setSize(image.getWidth(null),image.getHeight(null));
		this.setBorder(null);
		g.drawImage(image, 0, 0, null);
		font = new Font("ËÎÌו", Font.PLAIN, 25);
		g.setColor(new Color(231,230,230));
		g.setFont(font);
		g.drawString(text, 37, 37);
		this.setBackground(new Color(0,0,0,0));
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
		repaint();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		repaint();
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}
}
