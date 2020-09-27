package dev.aumi.IUMS;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.util.ResourceBundle;

@SuppressWarnings("serial")
public class HomePage extends JPanel {

	private static boolean soundState = true;
	private static boolean calledOnce = false;

	private static String language;

	private Image btnDefault = new ImageIcon(this.getClass().getResource("/btn layout.png")).getImage();
	private Image btnMouseIn = new ImageIcon(this.getClass().getResource("/btn layout(mouse in).png")).getImage();

	public HomePage() {
		
		setLayout(null);
		setBounds(0, 0, 1420, 800);
		setBackground(new Color(30, 30, 36));

		// Close button

		JLabel lblClose = new JLabel("X");
		lblClose.setBounds(1375, 0, 45, 30);
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				System.exit(0); // to exit the program

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				lblClose.setBackground(Color.DARK_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				lblClose.setBackground(new Color(43, 44, 52));
			}
		});
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
		lblClose.setOpaque(true);
		lblClose.setBackground(new Color(43, 44, 52));
		lblClose.setForeground(Color.WHITE);
		add(lblClose);

		AnimatedLabel lblWelcome = new AnimatedLabel("Welcome to, IUMS");

		if (!isCalledOnce()) {

			lblWelcome.setForeground(new Color(118, 185, 0, 0));
			lblWelcome.setBounds(520, 350, 402, 116);
		} else {

			lblWelcome.setForeground(new Color(118, 185, 0));
			lblWelcome.setBounds(520, 150, 402, 116);
		}
		
		// to add the title
		
		JLabel title = new JLabel("IUMS");
		title.setOpaque(true);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(new Color(30, 30, 36));
		title.setBackground(new Color(43, 44, 52));
		title.setFont(new Font("Eras Medium ITC", Font.PLAIN, 26));
		title.setBounds(0, 0, 1420, 30);
		add(title);

		add(lblWelcome);
		lblWelcome.setFont(new Font("Eras Light ITC", Font.PLAIN, 50));

		AnimatedLabel txtEng = new AnimatedLabel("ENGLISH");
		add(txtEng);
		txtEng.setAlpha(255);

		// to stop the animation

		if (!isCalledOnce()) {

			txtEng.setBounds(410, 1100, 162, 52);
		} else {

			txtEng.setBounds(410, 500, 162, 52);
		}

		txtEng.setHorizontalAlignment(SwingConstants.CENTER);
		txtEng.setForeground(Color.WHITE);
		txtEng.setFont(new Font("Eras Light ITC", Font.PLAIN, 24));

		AnimatedLabel btnEnglish = new AnimatedLabel("");
		add(btnEnglish);

		// to stop the animation

		if (!isCalledOnce()) {

			btnEnglish.setBounds(410, 1100, 162, 52);

		} else {

			btnEnglish.setBounds(410, 500, 162, 52);
		}

		btnEnglish.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				btnEnglish.setIcon(new ImageIcon(btnMouseIn));
			}

			@Override
			public void mouseExited(MouseEvent e) {

				btnEnglish.setIcon(new ImageIcon(btnDefault));
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				Locale.setDefault(new Locale("en", "UK"));
				ResourceBundle r = ResourceBundle.getBundle("bundle");

				soundState = false;

				setLanguage("english");

				LogInPage lip = new LogInPage();

				lip.getLogInTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 30));
				lip.getLogInTxt().setText(r.getString("account"));
				lip.getLogInTxt().setForeground(new Color(30, 30, 36));
				
				lip.getIDTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
				lip.getIDTxt().setText(r.getString("ID"));
				
				lip.getPassTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
				lip.getPassTxt().setText(r.getString("password"));
				
				lip.getRememberMe().setFont(new Font("Eras Light ITC", Font.PLAIN, 15));
				lip.getRememberMe().setText(r.getString("remember"));
				
				lip.getLogInBtnTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 24));
				lip.getLogInBtnTxt().setText(r.getString("login"));

				lip.getLblRegedOrNot().setFont(new Font("Eras Medium ITC", Font.PLAIN, 17));
				lip.getLblRegedOrNot().setBounds(new Rectangle(550, 750, 200, 50));
				lip.getLblRegedOrNot().setText(r.getString("regTxt"));
				lip.getLblRegedOrNot().setForeground(new Color(30, 30, 36));

				lip.getLblRegNowTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 15));
				lip.getLblRegNowTxt().setText(r.getString("regNow"));
				lip.getLblRegNowTxt().setForeground(new Color(30, 30, 36));
				
				lip.getForPassTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 15));
				lip.getForPassTxt().setText(r.getString("forgot"));

				removeAll();
				revalidate();
				repaint();
				add(lip);

				if (!isCalledOnce()) {

					//new PlaySound("sound//choice.wav").start();
				}

			}
		});
		btnEnglish.setIcon(new ImageIcon(btnDefault));

		AnimatedLabel txtBan = new AnimatedLabel("বাংলা");
		add(txtBan);

		if (!isCalledOnce()) {

			txtBan.setBounds(850, 1100, 162, 52);
		} else {

			txtBan.setBounds(850, 500, 162, 52);
		}

		txtBan.setAlpha(255);
		txtBan.setForeground(Color.WHITE);
		txtBan.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 24));

		txtBan.setHorizontalAlignment(SwingConstants.CENTER);

		AnimatedLabel btnBangla = new AnimatedLabel("");
		add(btnBangla);

		if (!isCalledOnce()) {

			btnBangla.setBounds(850, 1100, 162, 52);
		} else {

			btnBangla.setBounds(850, 500, 162, 52);
		}

		btnBangla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				btnBangla.setIcon(new ImageIcon(btnMouseIn));
			}

			@Override
			public void mouseExited(MouseEvent e) {

				btnBangla.setIcon(new ImageIcon(btnDefault));
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				Locale.setDefault(new Locale("bn", "BN"));
				ResourceBundle r = ResourceBundle.getBundle("bundle");

				soundState = false;

				setLanguage("bangla");

				LogInPage lip = new LogInPage();

				lip.getLogInTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 30));
				lip.getLogInTxt().setText(r.getString("account"));
				lip.getLogInTxt().setForeground(new Color(30, 30, 36));
				
				lip.getIDTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 25));
				lip.getIDTxt().setText(r.getString("ID"));
				
				lip.getPassTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 25));
				lip.getPassTxt().setText(r.getString("password"));
				
				lip.getRememberMe().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
				lip.getRememberMe().setText(r.getString("remember"));
				
				lip.getLogInBtnTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 24));
				lip.getLogInBtnTxt().setText(r.getString("login"));

				lip.getLblRegedOrNot().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 17));
				lip.getLblRegedOrNot().setBounds(new Rectangle(500, 750, 200, 50));
				lip.getLblRegedOrNot().setText(r.getString("regTxt"));
				lip.getLblRegedOrNot().setForeground(new Color(30, 30, 36));

				lip.getLblRegNowTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
				lip.getLblRegNowTxt().setText(r.getString("regNow"));
				lip.getLblRegNowTxt().setForeground(new Color(30, 30, 36));
				
				lip.getForPassTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
				lip.getForPassTxt().setText(r.getString("forgot"));

				removeAll();
				revalidate();
				repaint();
				add(lip);
				
				if (!isCalledOnce()) {

					//new PlaySound("sound//IUMS_bangla.wav").start();
				}

			}
		});

		btnBangla.gimmiBounds();

		btnBangla.setIcon(new ImageIcon(btnDefault));

		AnimatedLabel lblLanguage = new AnimatedLabel("Please select your language");
		lblLanguage.setBounds(410, 414, 602, 75);

		// to call the animation once

		if (!isCalledOnce()) {

			lblLanguage.setForeground(new Color(118, 185, 0, 0));
		} else {

			lblLanguage.setForeground(new Color(118, 185, 0, 255));
		}

		add(lblLanguage);
		lblLanguage.setHorizontalAlignment(SwingConstants.CENTER);
		lblLanguage.setFont(new Font("Eras Light ITC", Font.PLAIN, 24));
		lblLanguage.gimmiBounds();

		txtBan.gimmiBounds();

		btnEnglish.gimmiBounds();

		txtEng.gimmiBounds();

		lblWelcome.gimmiBounds();

		if (soundState) {

			//new PlaySound("sound//welcome.wav").start();
		}

	}

	public static String getLanguage() {
		return language;
	}

	public static void setLanguage(String language) {
		HomePage.language = language;
	}

	public static boolean isCalledOnce() {
		return calledOnce;
	}

	public static void setCalledOnce(boolean calledOnce) {
		HomePage.calledOnce = calledOnce;
	}
}
