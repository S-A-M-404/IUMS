package dev.aumi.IUMS;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class ForgotPasswordPage extends JPanel {

	private Image btnReturnImg = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
	private Image btnReturnImgMouseIn = new ImageIcon(this.getClass().getResource("/return(mouse in).png")).getImage();

	private Image btnDefault = new ImageIcon(this.getClass().getResource("/btn layout.png")).getImage();
	private Image btnMouseIn = new ImageIcon(this.getClass().getResource("/btn layout(mouse in).png")).getImage();

	private AnimatedLabel forgotTxt = new AnimatedLabel("", 5);
	private AnimatedLabel notiSlide = new AnimatedLabel("", 10);

	private JLabel lblEnterIdOr = new JLabel("Enter ID Or Email");
	private JLabel sendTxt = new JLabel("Send OTP");
	private JLabel OTPTxt = new JLabel("Insert OTP");
	private JLabel blurBg = new JLabel("");
	private JLabel forNoti = new JLabel("");

	private static JTextField emailIDField;
	private JTextField OTPField;

	private final JLabel OTPInsertion = new JLabel("");
	private final JLabel OKBtn = new JLabel("");
	private final JLabel OKTxt = new JLabel("OK");
	private final JLabel OTPNoti = new JLabel("");

	private static String OTPChecker = "";

	private static String whichID = "";
	private String OTPName = "";
	
	private int emailID;

	/**
	 * Create the panel.
	 */
	public ForgotPasswordPage() {

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

		// to add OTP checker

		OTPField = new JTextField();
		OTPField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				OTPField.setText("");
				OTPField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				OTPNoti.setText("");
			}
		});
		OTPField.setHorizontalAlignment(SwingConstants.CENTER);
		OTPField.setText("XXXXXXXX");
		OTPField.setBackground(new Color(28, 28, 32));
		OTPField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
		OTPField.setForeground(Color.GRAY);
		OTPField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		OTPField.setBounds(520, 400, 400, 30);
		OTPField.setColumns(10);
		OTPField.setVisible(false);
		add(OTPField);

		getOTPTxt().setForeground(new Color(118, 185, 0));
		getOTPTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
		getOTPTxt().setBounds(520, 372, 200, 20);
		getOTPTxt().setVisible(false);
		add(getOTPTxt());

		getOKTxt().setForeground(Color.WHITE);
		getOKTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 28));
		getOKTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getOKTxt().setBounds(629, 500, 162, 52);
		getOKTxt().setVisible(false);
		add(getOKTxt());

		OKBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				OKBtn.setIcon(new ImageIcon(btnMouseIn));
			}

			@Override
			public void mouseExited(MouseEvent e) {

				OKBtn.setIcon(new ImageIcon(btnDefault));
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				if (OTPField.getText().equals(getOTPChecker())) {

					if (HomePage.getLanguage().equals("bangla")) {

						Locale.setDefault(new Locale("bn", "BN"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						ForgotChangePasswordPage fcpp = new ForgotChangePasswordPage();

						fcpp.getNewPassTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
						fcpp.getNewPassTxt().setText(r.getString("newpass"));

						fcpp.getConPassTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
						fcpp.getConPassTxt().setText(r.getString("conPass"));

						fcpp.getChngTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
						fcpp.getChngTxt().setText(r.getString("change"));

						fcpp.getChangePassNoti().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
						fcpp.getChangePassNoti().setText("<html>" + r.getString("regpassnoti") + "</html>");

						removeAll();
						revalidate();
						repaint();
						add(fcpp);

					} else {

						Locale.setDefault(new Locale("en", "UK"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						ForgotChangePasswordPage fcpp = new ForgotChangePasswordPage();

						fcpp.getNewPassTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
						fcpp.getNewPassTxt().setText(r.getString("newpass"));

						fcpp.getConPassTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
						fcpp.getConPassTxt().setText(r.getString("conPass"));

						fcpp.getChngTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 28));
						fcpp.getChngTxt().setText(r.getString("change"));

						fcpp.getChangePassNoti().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
						fcpp.getChangePassNoti().setText("<html>" + r.getString("regpassnoti") + "</html>");

						removeAll();
						revalidate();
						repaint();
						add(fcpp);

					}

				} else {

					if (HomePage.getLanguage().equals("bangla")) {

						Locale.setDefault(new Locale("bn", "BN"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						OTPNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
						OTPNoti.setText(r.getString("otpnoti"));

					} else {

						Locale.setDefault(new Locale("en", "UK"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						OTPNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
						OTPNoti.setText(r.getString("otpnoti"));

					}

					OTPNoti.setForeground(Color.RED);
					OTPField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
				}
			}
		});
		OKBtn.setBounds(629, 500, 162, 52);
		OKBtn.setIcon(new ImageIcon(btnDefault));
		OKBtn.setVisible(false);
		add(OKBtn);

		OTPNoti.setBounds(520, 441, 200, 20);
		add(OTPNoti);

		OTPInsertion.setBackground(new Color(43, 44, 52));
		OTPInsertion.setOpaque(true);
		OTPInsertion.setBounds(410, 289, 600, 300);
		OTPInsertion.setVisible(false);
		add(OTPInsertion);

		getNotiSlide().setHorizontalAlignment(SwingConstants.CENTER);
		getNotiSlide().setForeground(new Color(118, 185, 0));
		getNotiSlide().setBackground(new Color(43, 44, 52));
		getNotiSlide().setOpaque(true);
		getNotiSlide().setBounds(1500, 641, 300, 150);
		notiSlide.gimmiBounds();
		add(getNotiSlide());

		blurBg.setBackground(new Color(30, 30, 36, 220));
		blurBg.setOpaque(true);
		blurBg.setVisible(false);
		blurBg.setBounds(0, 0, 1420, 800);
		add(blurBg);

		JLabel btnReturn = new JLabel("");
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				btnReturn.setIcon(new ImageIcon(btnReturnImgMouseIn.getScaledInstance(btnReturn.getWidth(),
						btnReturn.getHeight(), Image.SCALE_SMOOTH)));

			}

			@Override
			public void mouseExited(MouseEvent e) {

				btnReturn.setIcon(new ImageIcon(btnReturnImg.getScaledInstance(btnReturn.getWidth(),
						btnReturn.getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				if (HomePage.getLanguage().equals("bangla")) {

					Locale.setDefault(new Locale("bn", "BN"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

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

				} else {

					Locale.setDefault(new Locale("en", "UK"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

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

				}

			}
		});
		btnReturn.setBounds(new Rectangle(15, 50, 32, 32));
		btnReturn.setIcon(new ImageIcon(
				btnReturnImg.getScaledInstance(btnReturn.getWidth(), btnReturn.getHeight(), Image.SCALE_SMOOTH)));
		add(btnReturn);

		getForgotTxt().setBounds(410, 149, 450, 40);
		add(getForgotTxt());

		getLblEnterIdOr().setForeground(new Color(118, 185, 0));
		getLblEnterIdOr().setBounds(510, 314, 274, 40);
		add(getLblEnterIdOr());

		setEmailIDField(new JTextField());
		getEmailIDField().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				getEmailIDField().setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
				forNoti.setText("");
			}
		});
		getEmailIDField().setOpaque(false);
		getEmailIDField().setForeground(Color.GRAY);
		getEmailIDField().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		getEmailIDField().setColumns(10);
		getEmailIDField().setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
		getEmailIDField().setBounds(510, 365, 407, 30);
		add(getEmailIDField());

		getSendTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getSendTxt().setForeground(Color.WHITE);
		getSendTxt().setBounds(622, 473, 162, 52);
		add(getSendTxt());

		JLabel sendBtn = new JLabel("");
		sendBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				sendBtn.setIcon(new ImageIcon(
						btnMouseIn.getScaledInstance(sendBtn.getWidth(), sendBtn.getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseExited(MouseEvent e) {

				sendBtn.setIcon(new ImageIcon(
						btnDefault.getScaledInstance(sendBtn.getWidth(), sendBtn.getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				if (isStudentID()) {

					if (getEmailIDField().getText().charAt(7) == '4' && isStudentRegistered()) {

						readNameForEmail("select ID, Student_Name from cse_students");
						readEmailFromTables("select ID, Email, register from cse_students");

						setWhichID("students");

					} else {
						
						forNoti.setForeground(Color.RED);
						emailIDField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
						
						forNoti.setForeground(Color.RED);
						getEmailIDField().setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
						
						if (HomePage.getLanguage().equals("bangla")) {

							Locale.setDefault(new Locale("bn", "BN"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							forNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
							forNoti.setText(r.getString("fornoti"));

			
						} else {

							Locale.setDefault(new Locale("en", "UK"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							forNoti.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
							forNoti.setText(r.getString("fornoti"));
							

						}
					}

				} else if (isTeacherID()) {

					if (emailID / 1000 == 4 && isTeacherRegistered()) {

						readNameForEmail("select Teacher_ID, Teacher_name from cse_teachers");
						readEmailFromTables("select Teacher_ID, Email, register from cse_teachers");

						setWhichID("teachers");
						
					} else {
						
						forNoti.setForeground(Color.RED);
						emailIDField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
						
						forNoti.setForeground(Color.RED);
						getEmailIDField().setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
						
						if (HomePage.getLanguage().equals("bangla")) {

							Locale.setDefault(new Locale("bn", "BN"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							forNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
							forNoti.setText(r.getString("fornoti"));

			
						} else {

							Locale.setDefault(new Locale("en", "UK"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							forNoti.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
							forNoti.setText(r.getString("fornoti"));
							

						}
					}
				} else {
					
					forNoti.setForeground(Color.RED);
					getEmailIDField().setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
					
					if (HomePage.getLanguage().equals("bangla")) {

						Locale.setDefault(new Locale("bn", "BN"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						forNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
						forNoti.setText(r.getString("fornoti"));

		
					} else {

						Locale.setDefault(new Locale("en", "UK"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						forNoti.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
						forNoti.setText(r.getString("fornoti"));
						

					}
				}

			}
		});
		sendBtn.setBounds(622, 473, 162, 52);
		sendBtn.setIcon(new ImageIcon(
				btnDefault.getScaledInstance(sendBtn.getWidth(), sendBtn.getHeight(), Image.SCALE_SMOOTH)));
		add(sendBtn);
		
		forNoti.setBounds(510, 418, 250, 25);
		add(forNoti);

		JLabel forgotLbl = new JLabel("");
		forgotLbl.setOpaque(true);
		forgotLbl.setBackground(new Color(28, 28, 32));
		forgotLbl.setBounds(410, 200, 600, 420);
		add(forgotLbl);

	}

	public AnimatedLabel getForgotTxt() {
		return forgotTxt;
	}

	public void setForgotTxt(AnimatedLabel forgotTxt) {
		this.forgotTxt = forgotTxt;
	}

	public JLabel getLblEnterIdOr() {
		return lblEnterIdOr;
	}

	public void setLblEnterIdOr(JLabel lblEnterIdOr) {
		this.lblEnterIdOr = lblEnterIdOr;
	}

	public JLabel getSendTxt() {
		return sendTxt;
	}

	public void setSendTxt(JLabel sendTxt) {
		this.sendTxt = sendTxt;
	}

	public JLabel getOTPTxt() {
		return OTPTxt;
	}

	public void setOTPTxt(JLabel oTPTxt) {
		OTPTxt = oTPTxt;
	}

	public JLabel getOKTxt() {
		return OKTxt;
	}

	public JLabel getNotiSlide() {
		return notiSlide;
	}

	public void setNotiSlide(AnimatedLabel notiSlide) {
		this.notiSlide = notiSlide;
	}

	public static String getOTPChecker() {
		return OTPChecker;
	}

	public static void setOTPChecker(String oTPChecker) {
		OTPChecker = oTPChecker;
	}

	public boolean isStudentID() {

		try {

			if (getEmailIDField().getText().charAt(2) == '.' && getEmailIDField().getText().charAt(5) == '.' && getEmailIDField().getText().charAt(8) == '.' && getEmailIDField().getText().length() == 12) {

				return true;
			} else {

				return false;
			}

		} catch (Exception e) {

			return false;
		}

	}

	public boolean isStudentRegistered() {

		java.sql.Connection c = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			java.sql.Statement st = c.createStatement();
			String query = "Select * FROM cse_students";
			ResultSet rs = st.executeQuery(query);

			String ur = getEmailIDField().getText();
			while (rs.next()) {

				if (ur.equals(rs.getString(1))) {

					if (rs.getInt(14) == 1) {

						return true;
					}
				}
			}

			return false;

		} catch (Exception e) {

			System.out.println(e);
			return false;
		}
	}

	public boolean isTeacherRegistered() {

		java.sql.Connection c = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			java.sql.Statement st = c.createStatement();
			String query = "Select * FROM cse_teachers";
			ResultSet rs = st.executeQuery(query);

			String ur = getEmailIDField().getText();
			while (rs.next()) {

				if (ur.equals(rs.getString(1))) {

					if (rs.getInt(12) == 1) {

						return true;
					}
				}
			}

			return false;

		} catch (Exception e) {

			System.out.println(e);
			return false;
		}
	}

	public boolean isTeacherID() {
		try {

			emailID = Integer.parseInt(getEmailIDField().getText());

			return true;

		} catch (NumberFormatException e) {

			return false;
		}
	}

	public void readEmailFromTables(String query) {

		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			ps = c.prepareStatement(query);
			rs = ps.executeQuery();

			String id2 = getEmailIDField().getText();
			while (rs.next()) {
				
				if (id2.equals(rs.getString(1))) {
					
						getOTPTxt().setVisible(true);
						OTPField.setVisible(true);
						getOKTxt().setVisible(true);
						OKBtn.setVisible(true);
						OTPInsertion.setVisible(true);
						blurBg.setVisible(true);

						
						
						MailOTP.sendMail(rs.getString(2), OTPName);
						notiSlide.getTimer().start();

				}

			}

		} catch (Exception e) {

			System.out.println(e);
		}
	}

	public static String getWhichID() {
		return whichID;
	}

	public static void setWhichID(String whichID) {
		ForgotPasswordPage.whichID = whichID;
	}

	public static JTextField getEmailIDField() {
		return emailIDField;
	}

	public static void setEmailIDField(JTextField emailIDField) {
		ForgotPasswordPage.emailIDField = emailIDField;
	}
	
	public void readNameForEmail(String query) {

		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;	
		ResultSet rs = null;
		
		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			ps = c.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {

				if(rs.getString(1).equals(emailIDField.getText())){
					
					OTPName = rs.getString(2);
				}
			}
			
		} catch (Exception e) {

			System.out.println(e);
		}
	}
}
