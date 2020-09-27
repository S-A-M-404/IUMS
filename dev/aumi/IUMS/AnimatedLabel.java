package dev.aumi.IUMS;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class AnimatedLabel extends JLabel implements ActionListener {

	private static boolean soundState = true;
	
	private boolean visiblePanel = false;
	private boolean visibleFrame = false;
	private boolean visibleTxt = false;

	private int alpha = 0;
	private int checker;
	private Rectangle size;
	private Timer timer = new Timer(20, this);

	public AnimatedLabel(String txt) {

		super.setText(txt);
		
		checker = 0;

		if (super.getText().equals("Please select your language")) {

			getTimer().setInitialDelay(3400);

		} else {

			getTimer().setInitialDelay(300);

		}

		if(!HomePage.isCalledOnce()) {
			
			getTimer().start();
		}

	}
	
	public AnimatedLabel(String txt, int checker) {
		
		super.setText(txt);
		
		this.checker = checker;
		
		if(checker == 2 || checker == 5) {
			
			getTimer().setInitialDelay(300);
		} else if(checker == 3 || checker == 6) {
			
			getTimer().setInitialDelay(600);
		} else if(checker == 7 || checker == 8) {
			
			getTimer().setInitialDelay(600);
		} else if(checker == 9) {
			
			getTimer().setInitialDelay(150);
		}
		
		if(checker < 10) {
			
			getTimer().start();
		}
		
	}

	public void actionPerformed(ActionEvent e) {

		if(checker == 0) {
			
			fadeInAnim();
			moveUpAnim();
			stopIt();
		} else if (checker < 10){
			
			justFadeInAnim();
		} else if (checker == 10){
			
			moveLeftAnim();
		} else if (checker == 11) {
			
			if(!visiblePanel) {
				
				moveRightAnim();
			} else {
				
				moveLeftAnim();
			}
		} else if (checker == 12) {
			
			if(!visibleFrame) {
				
				moveRightAnim();
			} else {
				
				moveLeftAnim();
			}
		} else if (checker == 13) {
			
			if(!visibleTxt) {
				
				moveRightAnim();
			} else {
				
				moveLeftAnim();
			}
		}
		
	}

	public void gimmiBounds() {

		size = super.getBounds();

	}

	public void fadeInAnim() {

		if (getAlpha() < 255) {

			setForeground(new Color(118, 185, 0, setAlpha(getAlpha() + 3)));

		}

		if (getAlpha() >= 255 || size.height == 52) {
			setAlpha(255);
		}

	}

	public void moveUpAnim() {

		if (getAlpha() == 255 && size.width != 602) {

			setBounds(size.x, size.y -= 4, size.width, size.height);

		}

		if (getText().equals("ENGLISH") && size.y <= 500 && soundState) {

			//new PlaySound("sound//language.wav").start();
			soundState = false;
		}

	}

	public void stopIt() {

		if (size.y <= 150 || (size.y <= 500 && size.height == 52) || (size.y == 414 && getAlpha() == 255)) {

			getTimer().stop();
		}

	}
	
	public void justFadeInAnim() {
		
		if (getAlpha() < 255) {
			
			if(checker <= 3 || checker == 7) {
				
				setForeground(new Color(255, 255, 255, setAlpha(getAlpha() + 15)));
			} else {
				
				setForeground(new Color(118, 185, 0, setAlpha(getAlpha() + 15)));
			}

		}

		if (getAlpha() >= 255) {
			
			setAlpha(255);
			getTimer().stop();
			
		}
		
	}
	
	public void moveLeftAnim() {
		
		if(checker == 10) {
			
			if(size.x > 1100) {
				
				setBounds(size.x -= 10, size.y, size.width, size.height);
			} else {
				
				getTimer().stop();
			}
		} else if(checker == 11) {
			

			if(size.x > -500) {
				
				setBounds(size.x -= 25, size.y, size.width, size.height);
			} else {
				
				getTimer().stop();
				visiblePanel = false;
			}
		} else if(checker == 12) {
			
			if(size.x > -375) {
				
				setBounds(size.x -= 25, size.y, size.width, size.height);
			} else {
				
				getTimer().stop();
				visibleFrame = false;
			}
		} else if(checker == 13) {
			
			if(size.x > -450) {
				
				setBounds(size.x -= 25, size.y, size.width, size.height);
			} else {
				
				getTimer().stop();
				visibleTxt = false;
			}
		}
		
		
	}
	
	public void moveRightAnim() {
		
		if(checker == 11) {
			
			if(size.x < 0) {
				
				setBounds(size.x += 25, size.y, size.width, size.height);
			} else {
				
				getTimer().stop();
				visiblePanel = true;
			}
		} else if(checker == 12) {
			
			if(size.x < 125) {
				
				setBounds(size.x += 25, size.y, size.width, size.height);
			} else {
				
				getTimer().stop();
				visibleFrame = true;
			}
		} else if(checker == 13) {
			
			if(size.x < 50) {
				
				setBounds(size.x += 25, size.y, size.width, size.height);
			} else {
				
				getTimer().stop();
				visibleTxt = true;
			}
		}
		
	}
	
	public int getAlpha() {
		return alpha;
	}

	public int setAlpha(int alpha) {
		this.alpha = alpha;
		return alpha;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

}
