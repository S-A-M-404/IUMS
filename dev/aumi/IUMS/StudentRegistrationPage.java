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
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class StudentRegistrationPage extends JPanel {

	private AnimatedLabel txtReg = new AnimatedLabel("Student's Registration", 9);
	private AnimatedLabel notiSlide = new AnimatedLabel("", 10);

	private JLabel IDTxt = new JLabel("ID");
	private JLabel EmailTxt = new JLabel("Email");
	private JLabel regTxt = new JLabel("Register Now");
	private JLabel passTxt = new JLabel("Password");
	private JLabel conPassTxt = new JLabel("Confirm Password");
	private JLabel OTPTxt = new JLabel("Insert OTP");
	private JLabel regPassNotiIcn = new JLabel();
	private JLabel regPassNoti = new JLabel();
	private JLabel strengthTxt = new JLabel();

	private Image imgStudent = new ImageIcon(this.getClass().getResource("/student.png")).getImage();

	private Image btnReturnImg = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
	private Image btnReturnImgMouseIn = new ImageIcon(this.getClass().getResource("/return(mouse in).png")).getImage();

	private Image btnDefault = new ImageIcon(this.getClass().getResource("/btn layout.png")).getImage();
	private Image btnMouseIn = new ImageIcon(this.getClass().getResource("/btn layout(mouse in).png")).getImage();

	private Image noticeIcon = new ImageIcon(this.getClass().getResource("/notice.png")).getImage();
	private Image openNoticeIcon = new ImageIcon(this.getClass().getResource("/opennotice.png")).getImage();

	private JTextField stdIDTxtField;
	private JTextField stdEmailTxtField;
	private JTextField OTPField;

	private JPasswordField stdPassField;
	private JPasswordField stdConPassField;

	private JProgressBar passBar = new JProgressBar();

	private final JLabel OTPInsertion = new JLabel("");
	private final JLabel regNoti = new JLabel("");
	private final JLabel OKBtn = new JLabel("");
	private final JLabel OKTxt = new JLabel("OK");
	private final JLabel OTPNoti = new JLabel("");

	private static String OTPChecker = "";
	private String OTPName = "";

	private boolean strengthFlag = false;

	/**
	 * Create the panel.
	 */
	public StudentRegistrationPage() {

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

			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {

				if (OTPField.getText().equals(getOTPChecker())) {

					if (stdIDTxtField.getText().charAt(7) == '4') {

						updateData(stdEmailTxtField.getText(), stdPassField.getText(), stdIDTxtField.getText());

						HomePage.setCalledOnce(true);

						HomePage hp = new HomePage();

						removeAll();
						revalidate();
						repaint();
						add(hp);
					

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

		JLabel blurBg = new JLabel("");
		blurBg.setBackground(new Color(30, 30, 36, 220));
		blurBg.setOpaque(true);
		blurBg.setVisible(false);
		blurBg.setBounds(0, 0, 1420, 800);
		add(blurBg);

		getIDTxt().setBounds(510, 189, 70, 40);
		add(getIDTxt());

		stdIDTxtField = new JTextField();
		stdIDTxtField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				regNoti.setText("");
				stdIDTxtField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
			}
		});
		stdIDTxtField.setForeground(Color.GRAY);
		stdIDTxtField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		stdIDTxtField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
		stdIDTxtField.setOpaque(false);
		stdIDTxtField.setBounds(510, 240, 407, 30);
		add(stdIDTxtField);
		stdIDTxtField.setColumns(10);

		getEmailTxt().setBounds(510, 305, 70, 40);
		add(getEmailTxt());

		stdEmailTxtField = new JTextField();
		stdEmailTxtField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				regNoti.setText("");
				stdEmailTxtField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
			}
		});
		stdEmailTxtField.setForeground(Color.GRAY);
		stdEmailTxtField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		stdEmailTxtField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
		stdEmailTxtField.setOpaque(false);
		stdEmailTxtField.setBounds(510, 356, 407, 30);
		add(stdEmailTxtField);
		stdEmailTxtField.setColumns(10);

		getPassTxt().setBounds(510, 423, 121, 40);
		add(getPassTxt());

		stdPassField = new JPasswordField();
		stdPassField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				regNoti.setText("");
				stdPassField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
			}
		});

		// this is to show the strength of password

		stdPassField.addKeyListener(new KeyAdapter() {
			int flag = 0;

			@Override
			public void keyTyped(KeyEvent e) {

				int len = stdPassField.getPassword().length;

				for (int i = 0; i < len; i++) {

					char c = stdPassField.getPassword()[i];

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
		stdPassField.setForeground(Color.GRAY);
		stdPassField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		stdPassField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
		stdPassField.setOpaque(false);
		stdPassField.setBounds(510, 474, 407, 30);
		add(stdPassField);

		passBar.setBorderPainted(false);
		passBar.setBorder(null);
		passBar.setForeground(Color.BLACK);
		passBar.setBackground(new Color(28, 28, 32));
		passBar.setBounds(510, 515, 407, 5);
		add(passBar);

		strengthTxt.setForeground(Color.GREEN);
		strengthTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 15));
		strengthTxt.setBounds(510, 531, 100, 19);
		add(strengthTxt);

		getConPassTxt().setBounds(510, 559, 231, 40);
		add(getConPassTxt());

		stdConPassField = new JPasswordField();
		stdConPassField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				regNoti.setText("");
				stdConPassField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
			}
		});
		stdConPassField.setForeground(Color.GRAY);
		stdConPassField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		stdConPassField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
		stdConPassField.setOpaque(false);
		stdConPassField.setBounds(510, 610, 407, 30);
		add(stdConPassField);

		JLabel regBtn = new JLabel("");
		regBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				regBtn.setIcon(new ImageIcon(btnMouseIn));
			}

			@Override
			public void mouseExited(MouseEvent e) {

				regBtn.setIcon(new ImageIcon(btnDefault));
			}

			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {

				// TODO codes wanted to get back to home after registration is done

				if (isStudentID() && strengthFlag && stdPassField.getText().equals(stdConPassField.getText()) && !isRegistered()) {

					String email_pattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

					Pattern p = Pattern.compile(email_pattern);
					java.util.regex.Matcher m = p.matcher(stdEmailTxtField.getText());

					if (m.matches()) {

						getOTPTxt().setVisible(true);
						OTPField.setVisible(true);
						getOKTxt().setVisible(true);
						OKBtn.setVisible(true);
						OTPInsertion.setVisible(true);
						blurBg.setVisible(true);
						
						readNameForEmail("select ID, Student_Name from cse_students");

						MailOTP.sendMail(stdEmailTxtField.getText(), OTPName);
						notiSlide.getTimer().start();

					} else {

						if (HomePage.getLanguage().equals("bangla")) {

							Locale.setDefault(new Locale("bn", "BN"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							regNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
							regNoti.setText(r.getString("invalid"));

						} else {

							Locale.setDefault(new Locale("en", "UK"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							regNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 15));
							regNoti.setText(r.getString("invalid"));
						}

						stdEmailTxtField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
					}

				}

				if (!isStudentID()) {

					if (HomePage.getLanguage().equals("bangla")) {

						Locale.setDefault(new Locale("bn", "BN"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						regNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
						regNoti.setText(r.getString("invalid"));

					} else {

						Locale.setDefault(new Locale("en", "UK"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						regNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 15));
						regNoti.setText(r.getString("invalid"));
					}

					stdIDTxtField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
				}

				if (!strengthFlag) {

					if (HomePage.getLanguage().equals("bangla")) {

						Locale.setDefault(new Locale("bn", "BN"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						regNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
						regNoti.setText(r.getString("invalid"));

					} else {

						Locale.setDefault(new Locale("en", "UK"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						regNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 15));
						regNoti.setText(r.getString("invalid"));
					}

					stdPassField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
				}

				if (!stdPassField.getText().equals(stdConPassField.getText())) {

					if (HomePage.getLanguage().equals("bangla")) {

						Locale.setDefault(new Locale("bn", "BN"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						regNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
						regNoti.setText(r.getString("invalid"));

					} else {

						Locale.setDefault(new Locale("en", "UK"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						regNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 15));
						regNoti.setText(r.getString("invalid"));
					}

					stdPassField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
					stdConPassField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
				}

			}

		});

		regNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 18));
		regNoti.setForeground(Color.RED);
		regNoti.setBounds(510, 651, 300, 20);
		add(regNoti);

		getRegTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getRegTxt().setBounds(629, 690, 162, 52);
		add(getRegTxt());
		regBtn.setIcon(new ImageIcon(btnDefault));
		regBtn.setBounds(629, 690, 162, 52);
		add(regBtn);

		regPassNotiIcn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				regPassNotiIcn.setIcon(new ImageIcon(openNoticeIcon.getScaledInstance(regPassNotiIcn.getWidth(),
						regPassNotiIcn.getHeight(), Image.SCALE_SMOOTH)));
				getRegPassNoti().setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {

				regPassNotiIcn.setIcon(new ImageIcon(noticeIcon.getScaledInstance(regPassNotiIcn.getWidth(),
						regPassNotiIcn.getHeight(), Image.SCALE_SMOOTH)));
				getRegPassNoti().setVisible(false);
			}
		});

		getRegPassNoti().setHorizontalAlignment(SwingConstants.CENTER);
		getRegPassNoti().setForeground(new Color(118, 185, 0));
		getRegPassNoti().setBackground(new Color(43, 44, 52));
		getRegPassNoti().setOpaque(true);
		getRegPassNoti().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
		getRegPassNoti().setBounds(1002, 414, 197, 138);
		getRegPassNoti().setVisible(false);
		add(getRegPassNoti());

		regPassNotiIcn.setBounds(950, 465, 30, 30);
		regPassNotiIcn.setIcon(new ImageIcon(noticeIcon.getScaledInstance(regPassNotiIcn.getWidth(),
				regPassNotiIcn.getHeight(), Image.SCALE_SMOOTH)));
		add(regPassNotiIcn);

		JLabel regPanel = new JLabel("");
		regPanel.setBounds(410, 155, 600, 600);
		regPanel.setOpaque(true);
		regPanel.setBackground(new Color(28, 28, 32));
		add(regPanel);

		FadeInImg stdImg = new FadeInImg(imgStudent, 4);
		stdImg.setBounds(410, 50, 100, 100);
		add(stdImg);

		getTxtReg().setHorizontalAlignment(SwingConstants.LEFT);
		getTxtReg().setBounds(510, 100, 500, 50);
		add(getTxtReg());

		// Return button

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

					RegistrationPage rp = new RegistrationPage();

					rp.getBtnTxtStudent().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					rp.getBtnTxtStudent().setText(r.getString("register"));
					rp.getBtnTxtStudent().setForeground(new Color(30, 30, 36));

					rp.getBtnTxtTeacher().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					rp.getBtnTxtTeacher().setText(r.getString("register"));
					rp.getBtnTxtTeacher().setForeground(new Color(30, 30, 36));

					rp.getLblStudentTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 30));
					rp.getLblStudentTxt().setText(r.getString("studentTxt"));
					rp.getLblStudentTxt().setForeground(new Color(30, 30, 36));

					rp.getLblTeacherTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 30));
					rp.getLblTeacherTxt().setText(r.getString("teacherTxt"));
					rp.getLblTeacherTxt().setForeground(new Color(30, 30, 36));

					removeAll();
					revalidate();
					repaint();
					add(rp);

				} else {

					Locale.setDefault(new Locale("en", "UK"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					RegistrationPage rp = new RegistrationPage();

					rp.getBtnTxtStudent().setFont(new Font("Eras Light ITC", Font.PLAIN, 24));
					rp.getBtnTxtStudent().setText(r.getString("register"));
					rp.getBtnTxtStudent().setForeground(new Color(30, 30, 36));

					rp.getBtnTxtTeacher().setFont(new Font("Eras Light ITC", Font.PLAIN, 24));
					rp.getBtnTxtTeacher().setText(r.getString("register"));
					rp.getBtnTxtTeacher().setForeground(new Color(30, 30, 36));

					rp.getLblStudentTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 40));
					rp.getLblStudentTxt().setText(r.getString("studentTxt"));
					rp.getLblStudentTxt().setForeground(new Color(30, 30, 36));

					rp.getLblTeacherTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 40));
					rp.getLblTeacherTxt().setText(r.getString("teacherTxt"));
					rp.getLblTeacherTxt().setForeground(new Color(30, 30, 36));

					removeAll();
					revalidate();
					repaint();
					add(rp);

				}

			}
		});
		btnReturn.setBounds(new Rectangle(15, 50, 32, 32));
		btnReturn.setIcon(new ImageIcon(
				btnReturnImg.getScaledInstance(btnReturn.getWidth(), btnReturn.getHeight(), Image.SCALE_SMOOTH)));
		add(btnReturn);

	}

	public JLabel getTxtReg() {
		return txtReg;
	}

	public void setTxtReg(AnimatedLabel txtReg) {
		this.txtReg = txtReg;
	}

	public JLabel getIDTxt() {
		return IDTxt;
	}

	public void setIDTxt(JLabel iDTxt) {
		IDTxt = iDTxt;
	}

	public JLabel getEmailTxt() {
		return EmailTxt;
	}

	public void setEmailTxt(JLabel emailTxt) {
		EmailTxt = emailTxt;
	}

	public JLabel getRegTxt() {
		return regTxt;
	}

	public void setRegTxt(JLabel regTxt) {
		this.regTxt = regTxt;
	}

	public JLabel getPassTxt() {
		return passTxt;
	}

	public void setPassTxt(JLabel passTxt) {
		this.passTxt = passTxt;
	}

	public JLabel getConPassTxt() {
		return conPassTxt;
	}

	public void setConPassTxt(JLabel conPassTxt) {
		this.conPassTxt = conPassTxt;
	}

	public JLabel getRegPassNoti() {
		return regPassNoti;
	}

	public void setRegPassNoti(JLabel regPassNoti) {
		this.regPassNoti = regPassNoti;
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

	public JLabel getOTPTxt() {
		return OTPTxt;
	}

	public void setOTPTxt(JLabel oTPTxt) {
		OTPTxt = oTPTxt;
	}

	public JLabel getOKTxt() {
		return OKTxt;
	}

	public boolean isStudentID() {

		try {

			if (stdIDTxtField.getText().charAt(2) == '.' && stdIDTxtField.getText().charAt(5) == '.' && stdIDTxtField.getText().charAt(8) == '.' && stdIDTxtField.getText().length() == 12) {

				return true;
			} else {

				return false;
			}

		} catch (Exception e) {

			return false;
		}

	}

	public void updateData(String email, String password, String id) {

		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			ps = c.prepareStatement("UPDATE cse_students SET Email = ?, Pass_word = DES_ENCRYPT(?,'SAM404'), register = ? WHERE ID = ?");

			ps.setString(1, email);
			ps.setString(2, password);
			ps.setInt(3, 1);
			ps.setString(4, id);
			ps.executeUpdate();

		} catch (Exception e) {

			System.out.println(e);
		}
	}

	public boolean isRegistered() {

		java.sql.Connection c = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			java.sql.Statement st = c.createStatement();
			String query = "Select * FROM cse_students";
			ResultSet rs = st.executeQuery(query);

			String ur = stdIDTxtField.getText();
			while (rs.next()) {

				if (ur.equals(rs.getString(1))) {

					if (rs.getInt(14) == 1) {

						regNoti.setText("*Already Registered");
						regNoti.setForeground(Color.YELLOW);
						stdIDTxtField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.YELLOW));
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

				if(rs.getString(1).equals(stdIDTxtField.getText())){
					
					OTPName = rs.getString(2);
				}
			}
			
		} catch (Exception e) {

			System.out.println(e);
		}
	}
}
