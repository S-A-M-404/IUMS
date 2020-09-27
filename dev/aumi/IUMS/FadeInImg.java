package dev.aumi.IUMS;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class FadeInImg extends JLabel implements ActionListener {

	private Image image;

	private Image btnDefault = new ImageIcon(this.getClass().getResource("/btn layout.png")).getImage();
	private Image regNow = new ImageIcon(this.getClass().getResource("/regNow.png")).getImage();
	
	private Image imgStudent = new ImageIcon(this.getClass().getResource("/student.png")).getImage();
	private Image imgTeacher = new ImageIcon(this.getClass().getResource("/teacher.png")).getImage();
	private Image imgAdmin = new ImageIcon(this.getClass().getResource("/admin.png")).getImage();

	private Timer timer = new Timer(20, this);

	private float alpha = 0f;

	public FadeInImg(Image image) {

		this.image = image;

		if (image.equals(imgTeacher)) {

			timer.setInitialDelay(400);
		} else if (image.equals(imgAdmin)) {

			timer.setInitialDelay(700);
		} else if (image.equals(imgStudent)) {

			timer.setInitialDelay(100);
		}

		timer.start();
	}

	public FadeInImg(Image image, int checker) {

		this.image = image;

		if (checker == 1) {

			timer.setInitialDelay(300);
			
		} else if (checker == 2) {

			timer.setInitialDelay(600);
			
		} else if (checker == 3) {
			
			timer.setInitialDelay(900);
		} else if (checker == 4) {
			
			this.image = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			timer.setInitialDelay(150);
		}
		
		timer.start();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		try {
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			g2d.drawImage(image, 0, 0, null);

		} catch (IllegalArgumentException e) {

		}
	}

	public void actionPerformed(ActionEvent e) {

		if (alpha < 0.95000017f) {

			alpha += 0.03f;
		} else {

			alpha = 1f;

			if (image.equals(btnDefault)) {

				image = null;
				super.setIcon(new ImageIcon(btnDefault));
				timer.stop();
			} else if(image.equals(regNow)) {

				image = null;
				super.setIcon(new ImageIcon(regNow));
				timer.stop();
			} else {
				
				timer.stop();
			}

		}

		repaint();
	}

}
