package dev.aumi.IUMS;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class FadeOut extends JPanel implements ActionListener{

	Image myImage; 

	public Timer timer = new Timer(20, this);

  	private float alpha = 1f;
  	
  	public FadeOut(Image image) {
		  
		  myImage = image;
		  timer.start();
	  }

	  public void paint(Graphics g) {
		  super.paint(g);
		  Graphics2D g2d = (Graphics2D) g;

		  try {
			   g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			   g2d.drawImage(myImage, 0, 0, null);
			   
		   }catch(IllegalArgumentException e) {
			   
			   g2d.drawImage(myImage, 0, 0, null);
			   this.setVisible(false);
		   }
	  }

	  public void actionPerformed(ActionEvent e) {
		  alpha -= 0.01f;
		  if (alpha <= -1) {
			  alpha = -1;
			  timer.stop();
		  }
		  repaint();
	  }
	
}
