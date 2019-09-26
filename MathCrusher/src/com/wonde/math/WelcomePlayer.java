package com.wonde.math;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class WelcomePlayer extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final int FRAME_WIDTH = 1000;
	private final int FRAME_HEIGHT = 650;
	
	private final int DELAY = 6;
	
	
	String[] imageSrc = {"src/Image/times.jpg", 
						"src/Image/plus.jpg", 
						"src/Image/minus.jpg", 
						"src/Image/number_4.PNG", 
						"src/Image/number_5.png", 
						"src/Image/div.jpg", 
						"src/Image/number_7.PNG", 
						"src/Image/number_1.jpg", 
						"src/Image/welcome.jpg"};
	
	int length = imageSrc.length;
	
	private Random rand;
	private Location location[] = new Location[length];
	private Image image[] = new Image[length];
	ImageIcon imageIcon[] = new ImageIcon[length];
	
	private Thread animator;
	
	private int[] x_dir = {-1, -2, -2, -3, -2, -1, -1, -3, -2};
	private int[] y_dir = {-2, -2, -1, -2, -3, -1, -3, -1, 0};
	
	public WelcomePlayer() {
		initWindow();
	}
	
	private void loadImage() {
	
		rand = new Random();
		
		for (int i = 0; i < length; i++) 
		{
			image[i] = new ImageIcon(imageSrc[i]).getImage();
		}
		
		for (int i = 0; i < length; i++) 
		{
			location[i] = new Location();
			location[i].x = rand.nextInt(800);
			location[i].y = rand.nextInt(430) + 70;
		}
			location[8] = new Location();
			location[8].x = 0;
			location[8].y = 0;
	}
	
	private void initWindow() {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		
		loadImage();
		animator = new Thread(this);
		animator.start();
		
	}
	
//	public void addNotify() {
//		super.addNotify();
//
//		animator = new Thread(this);
//		animator.start();
//	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawMovingImage(g);
	}
	
	private void drawMovingImage(Graphics g) {

		for (int i = 0; i < length; i++)
		{
			g.drawImage(image[i], location[i].x, location[i].y, (ImageObserver) this);
		}
		Toolkit.getDefaultToolkit().sync();
	}

	private void imageMover() {
		for (int i = 0; i < length; i++)
		{
			location[i].x += x_dir[i];
			location[i].y += y_dir[i];
			
			if ((location[i].y > (FRAME_HEIGHT - 130)) || (location[i].y < 70))
			{
				y_dir[i] = -y_dir[i];
			}
			if ((location[i].x > (FRAME_WIDTH - 150)) || (location[i].x < 0))
			{
				x_dir[i] = -x_dir[i];
			}
		}
		
		repaint();
	}
	
	@Override
	public void run() {

		while (true) {

			imageMover();

			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {

				String msg = String.format("Thread interrupted: %s",
						e.getMessage());

				JOptionPane.showMessageDialog(this, msg, "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		}
	}
}

class Location{
	int x;
	int y;
}