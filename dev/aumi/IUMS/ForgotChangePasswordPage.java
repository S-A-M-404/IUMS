package dev.aumi.IUMS;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class ForgotChangePasswordPage extends JPanel {
	
	private Image btnReturnImg = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
	private Image btnReturnImgMouseIn = new ImageIcon(this.getClass().getResource("/return(mouse in).png")).getImage();

	private Image btnDefault = new ImageIcon(this.getClass().getResource("/btn layout.png")).getImage();
	private Image btnMouseIn = new ImageIcon(this.getClass().getResource("/btn layout(mouse in).png")).getImage();

	private Image noticeIcon = new ImageIcon(this.getClass().getResource("/notice.png")).getImage();
	private Image openNoticeIcon = new ImageIcon(this.getClass().getResource("/opennotice.png")).getImage();
	
	private JLabel passTxt = new JLabel("Change Password");
	private JLabel newPassTxt = new JLabel("New Password");
	private JLabel conPassTxt = new JLabel("Confirm Password");
	private JLabel chngTxt = new JLabel("Change");
	private JLabel passNoti = new JLabel("");
	private JLabel strengthTxt = new JLabel();
	private JLabel changePassNoti = new JLabel();
	private JLabel passNotiIcn = new JLabel();
	
	private JProgressBar passBar = new JProgressBar();

	private boolean strengthFlag = false;
	
	private JPasswordField newPassField = new JPasswordField();
	private JPasswordField conPassField = new JPasswordField();

	/**
	 * Create the panel.
	 */
	public ForgotChangePasswordPage() {
		
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

		// to add the title

		JLabel title = new JLabel("IUMS");
		title.setOpaque(true);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(new Color(30, 30, 36));
		title.setBackground(new Color(43, 44, 52));
		title.setFont(new Font("Eras Medium ITC", Font.PLAIN, 26));
		title.setBounds(0, 0, 1420, 30);
		add(title);

		// to add return button

		JLabel btnReturn = new JLabel("");
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				Image btnReturnImgMouseInScaled = btnReturnImgMouseIn.getScaledInstance(btnReturn.getWidth(),
						btnReturn.getHeight(), Image.SCALE_SMOOTH);
				btnReturn.setIcon(new ImageIcon(btnReturnImgMouseInScaled));
			}

			@Override
			public void mouseExited(MouseEvent e) {

				Image btnReturnImgScaled = btnReturnImg.getScaledInstance(btnReturn.getWidth(), btnReturn.getHeight(),
						Image.SCALE_SMOOTH);
				btnReturn.setIcon(new ImageIcon(btnReturnImgScaled));
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				HomePage.setCalledOnce(true);

				HomePage hp = new HomePage();

				removeAll();
				revalidate();
				repaint();
				add(hp);

			}
		});
		btnReturn.setBounds(new Rectangle(15, 50, 32, 32));
		Image btnReturnImgScaled = btnReturnImg.getScaledInstance(btnReturn.getWidth(), btnReturn.getHeight(),
				Image.SCALE_SMOOTH);
		btnReturn.setIcon(new ImageIcon(btnReturnImgScaled));
		add(btnReturn);

		passTxt.setHorizontalAlignment(SwingConstants.CENTER);
		passTxt.setForeground(new Color(118, 185, 0));
		passTxt.setFont(new Font("Eras Medium ITC", Font.PLAIN, 40));
		passTxt.setBounds(410, 71, 600, 60);
		add(passTxt);

		newPassTxt.setForeground(new Color(118, 185, 0));
		newPassTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
		newPassTxt.setBounds(560, 231, 150, 20);
		add(newPassTxt);
		newPassField.addKeyListener(new KeyAdapter() {
		
			int flag = 0;
			@Override
			public void keyTyped(KeyEvent e) {
				
				int len = newPassField.getPassword().length;

				for (int i = 0; i < len; i++) {

					char c = newPassField.getPassword()[i];

					if (flag == 0 && Character.isUpperCase(c)) {

						flag = 1;

					}

					if (flag == 0 && Character.isDigit(c)) {

						flag = 2;

					}

					if (flag == 1 && Character.isDigit(c)) {

						flag = 3;
					}

					if (flag == 2 && Character.isUpperCase(c)) {

						flag = 4;
					}

					if (len < 8) {

						flag = 0;
						passBar.setValue(25);
						passBar.setForeground(Color.RED);

						if (HomePage.getLanguage().equals("bangla")) {

							Locale.setDefault(new Locale("bn", "BN"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							strengthTxt.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
							strengthTxt.setText(r.getString("weak"));
							strengthTxt.setForeground(Color.RED);

						} else {

							Locale.setDefault(new Locale("en", "UK"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							strengthTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 15));
							strengthTxt.setText(r.getString("weak"));
							strengthTxt.setForeground(Color.RED);
						}

					} else if (len >= 8 && flag == 0) {

						passBar.setValue(50);
						passBar.setForeground(Color.ORANGE);

						if (HomePage.getLanguage().equals("bangla")) {

							Locale.setDefault(new Locale("bn", "BN"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							strengthTxt.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
							strengthTxt.setText(r.getString("medium"));
							strengthTxt.setForeground(Color.ORANGE);

						} else {

							Locale.setDefault(new Locale("en", "UK"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							strengthTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 15));
							strengthTxt.setText(r.getString("medium"));
							strengthTxt.setForeground(Color.ORANGE);
						}

					} else if (len >= 8 && (flag == 1 || flag == 2)) {

						passBar.setValue(75);
						passBar.setForeground(Color.YELLOW);

						if (HomePage.getLanguage().equals("bangla")) {

							Locale.setDefault(new Locale("bn", "BN"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							strengthTxt.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
							strengthTxt.setText(r.getString("moderate"));
							strengthTxt.setForeground(Color.YELLOW);

						} else {

							Locale.setDefault(new Locale("en", "UK"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							strengthTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 15));
							strengthTxt.setText(r.getString("moderate"));
							strengthTxt.setForeground(Color.YELLOW);
						}

					} else if (len >= 8 && (flag == 3 || flag == 4)) {

						passBar.setValue(100);
						passBar.setForeground(Color.GREEN);

						if (HomePage.getLanguage().equals("bangla")) {

							Locale.setDefault(new Locale("bn", "BN"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							strengthTxt.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
							strengthTxt.setText(r.getString("strong"));
							strengthTxt.setForeground(Color.GREEN);
							strengthFlag = true;
						} else {

							Locale.setDefault(new Locale("en", "UK"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							strengthTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 15));
							strengthTxt.setText(r.getString("strong"));
							strengthTxt.setForeground(Color.GREEN);
							strengthFlag = true;
						}

					}
				}
				
			}
		});

		newPassField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				newPassField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				conPassField.setBorder(null);
				
				passNoti.setText("");
			}
		});
		newPassField.setForeground(Color.GRAY);
		newPassField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		newPassField.setCaretColor(Color.GRAY);
		newPassField.setBorder(null);
		newPassField.setBackground(new Color(52, 52, 61));
		newPassField.setBounds(560, 262, 300, 30);
		add(newPassField);
		
		passNotiIcn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				passNotiIcn.setIcon(new ImageIcon(openNoticeIcon.getScaledInstance(passNotiIcn.getWidth(),
						passNotiIcn.getHeight(), Image.SCALE_SMOOTH)));
				getChangePassNoti().setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {

				passNotiIcn.setIcon(new ImageIcon(noticeIcon.getScaledInstance(passNotiIcn.getWidth(),
						passNotiIcn.getHeight(), Image.SCALE_SMOOTH)));
				getChangePassNoti().setVisible(false);
			}
		});

		getChangePassNoti().setHorizontalAlignment(SwingConstants.CENTER);
		getChangePassNoti().setForeground(new Color(118, 185, 0));
		getChangePassNoti().setBackground(new Color(43, 44, 52));
		getChangePassNoti().setOpaque(true);
		getChangePassNoti().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
		getChangePassNoti().setBounds(937, 208, 197, 138);
		getChangePassNoti().setVisible(false);
		add(getChangePassNoti());

		passNotiIcn.setBounds(897, 262, 30, 30);
		passNotiIcn.setIcon(new ImageIcon(noticeIcon.getScaledInstance(passNotiIcn.getWidth(),
				passNotiIcn.getHeight(), Image.SCALE_SMOOTH)));
		add(passNotiIcn);
		
		passBar.setBorderPainted(false);
		passBar.setBorder(null);
		passBar.setForeground(Color.BLACK);
		passBar.setBackground(new Color(30, 30, 36));
		passBar.setBounds(560, 303, 300, 5);
		add(passBar);

		strengthTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 15));
		strengthTxt.setBounds(560, 319, 100, 19);
		add(strengthTxt);

		conPassTxt.setForeground(new Color(118, 185, 0));
		conPassTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
		conPassTxt.setBounds(560, 391, 170, 20);
		add(conPassTxt);

		conPassField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				newPassField.setBorder(null);
				conPassField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				
				passNoti.setText("");
			}
		});
		conPassField.setForeground(Color.GRAY);
		conPassField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		conPassField.setCaretColor(Color.GRAY);
		conPassField.setBorder(null);
		conPassField.setBackground(new Color(52, 52, 61));
		conPassField.setBounds(560, 422, 300, 30);
		add(conPassField);

		JLabel chngBtn = new JLabel("");
		chngBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				chngBtn.setIcon(new ImageIcon(
						btnMouseIn.getScaledInstance(chngBtn.getWidth(), chngBtn.getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseExited(MouseEvent e) {

				chngBtn.setIcon(new ImageIcon(
						btnDefault.getScaledInstance(chngBtn.getWidth(), chngBtn.getHeight(), Image.SCALE_SMOOTH)));
			}
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(newPassField.getText().equals(conPassField.getText()) && strengthFlag) {
					
					if(ForgotPasswordPage.getWhichID().equals("students")) {
						
						updatePasswordIntoTables("UPDATE cse_students SET Pass_word = DES_ENCRYPT(?,'SAM404') WHERE ID = '" + ForgotPasswordPage.getEmailIDField().getText() + "'");
						
					} else if(ForgotPasswordPage.getWhichID().equals("teachers")) {
						
						updatePasswordIntoTables("UPDATE cse_teachers SET Pass_word = DES_ENCRYPT(?,'SAM404') WHERE Teacher_ID = '" + ForgotPasswordPage.getEmailIDField().getText() + "'");
					}
					
					newPassField.setText("");
					conPassField.setText("");
					passBar.setValue(0);
					strengthTxt.setText("");
					
					if (HomePage.getLanguage().equals("bangla")) {

						Locale.setDefault(new Locale("bn", "BN"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						getPassNoti().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
						getPassNoti().setText(r.getString("successful"));
						getPassNoti().setForeground(Color.GREEN);

					} else {

						Locale.setDefault(new Locale("en", "UK"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						getPassNoti().setFont(new Font("Eras Light ITC", Font.PLAIN, 18));
						getPassNoti().setText(r.getString("successful"));
						getPassNoti().setForeground(Color.GREEN);
					}
					
				} else {
					
					if (!strengthFlag) {

						if (HomePage.getLanguage().equals("bangla")) {

							Locale.setDefault(new Locale("bn", "BN"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							getPassNoti().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
							getPassNoti().setText(r.getString("invalid"));

						} else {

							Locale.setDefault(new Locale("en", "UK"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							getPassNoti().setFont(new Font("Eras Light ITC", Font.PLAIN, 18));
							getPassNoti().setText(r.getString("invalid"));
						}

						newPassField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
					}
					
					
					if(!newPassField.getText().equals(conPassField.getText())) {
						
						if (HomePage.getLanguage().equals("bangla")) {

							Locale.setDefault(new Locale("bn", "BN"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							getPassNoti().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
							getPassNoti().setText(r.getString("invalid"));

						} else {

							Locale.setDefault(new Locale("en", "UK"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							getPassNoti().setFont(new Font("Eras Light ITC", Font.PLAIN, 18));
							getPassNoti().setText(r.getString("invalid"));
						}

						newPassField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
						conPassField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
					}
				}
				
			}
		});
		
		getPassNoti().setForeground(Color.RED);
		getPassNoti().setBounds(560, 463, 250, 20);
		add(getPassNoti());

		chngTxt.setHorizontalAlignment(SwingConstants.CENTER);
		chngTxt.setForeground(Color.WHITE);
		chngTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 28));
		chngTxt.setBounds(629, 520, 162, 52);
		add(chngTxt);
		chngBtn.setBounds(629, 520, 162, 52);
		chngBtn.setIcon(new ImageIcon(
				btnDefault.getScaledInstance(chngBtn.getWidth(), chngBtn.getHeight(), Image.SCALE_SMOOTH)));
		add(chngBtn);

	}

	public JLabel getNewPassTxt() {
		return newPassTxt;
	}

	public void setNewPassTxt(JLabel newPassTxt) {
		this.newPassTxt = newPassTxt;
	}

	public JLabel getConPassTxt() {
		return conPassTxt;
	}

	public void setConPassTxt(JLabel conPassTxt) {
		this.conPassTxt = conPassTxt;
	}

	public JLabel getChngTxt() {
		return chngTxt;
	}

	public void setChngTxt(JLabel chngTxt) {
		this.chngTxt = chngTxt;
	}
	
	public JLabel getPassNoti() {
		return passNoti;
	}

	public void setPassNoti(JLabel passNoti) {
		this.passNoti = passNoti;
	}

	public JLabel getChangePassNoti() {
		return changePassNoti;
	}

	public void setChangePassNoti(JLabel changePassNoti) {
		this.changePassNoti = changePassNoti;
	}
	
	@SuppressWarnings("deprecation")
	public void updatePasswordIntoTables(String query) {

		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			ps = c.prepareStatement(query);
			
			ps.setString(1, newPassField.getText());
			ps.executeUpdate();

		} catch (Exception e) {

			System.out.println(e);
		}
	}

}
