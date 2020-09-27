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
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class TeacherRegistrationPage extends JPanel {

	private AnimatedLabel txtReg = new AnimatedLabel("Teacher's Registration", 9);
	private AnimatedLabel notiSlide = new AnimatedLabel("",10);
	
	private JLabel IDTxt = new JLabel("ID");
	private JLabel EmailTxt = new JLabel("Email");
	private JLabel regTxt = new JLabel("Register Now");
	private JLabel passTxt = new JLabel("Password");
	private JLabel conPassTxt = new JLabel("Confirm Password");
	private JLabel OTPTxt = new JLabel("Insert OTP");
	private JLabel regPassNotiIcn = new JLabel();
	private JLabel regPassNoti = new JLabel();
	private JLabel strengthTxt = new JLabel();

	private Image imgTeacher = new ImageIcon(this.getClass().getResource("/teacher.png")).getImage();

	private Image btnReturnImg = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
	private Image btnReturnImgMouseIn = new ImageIcon(this.getClass().getResource("/return(mouse in).png")).getImage();

	private Image btnDefault = new ImageIcon(this.getClass().getResource("/btn layout.png")).getImage();
	private Image btnMouseIn = new ImageIcon(this.getClass().getResource("/btn layout(mouse in).png")).getImage();

	private Image noticeIcon = new ImageIcon(this.getClass().getResource("/notice.png")).getImage();
	private Image openNoticeIcon = new ImageIcon(this.getClass().getResource("/opennotice.png")).getImage();

	private JTextField tcrIDTxtField;
	private JTextField tcrEmailTxtField;
	
	private JPasswordField tcrPassField;
	private JPasswordField tcrConPassField;

	private JProgressBar passBar = new JProgressBar();
	
	private final JLabel OTPInsertion = new JLabel("");
	private final JLabel OKBtn = new JLabel("");
	private final JLabel OKTxt = new JLabel("OK");
	private final JLabel OTPNoti = new JLabel("");
	
	private JTextField OTPField;

	private static String OTPChecker = "";
	private String OTPName = "";
	
	private int teacherID;
	
	private boolean strengthFlag = false;
	private final JLabel regNoti = new JLabel("");
	
	/**
	 * Create the panel.
	 */
	public TeacherRegistrationPage() {

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
		
		getNotiSlide().setHorizontalAlignment(SwingConstants.CENTER);
		getNotiSlide().setForeground(new Color(118, 185, 0));
		getNotiSlide().setBackground(new Color(43, 44, 52));
		getNotiSlide().setOpaque(true);
		getNotiSlide().setBounds(1500, 641, 300, 150);
		notiSlide.gimmiBounds();
		add(getNotiSlide());
		
		//to add OTP checker
		
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
		OKTxt.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(teacherID / 1000 == 4) {
					
					//TODO enters CSE teachers database
					

					updateDataIntoCseTeachers(tcrEmailTxtField.getText(), tcrPassField.getText(), tcrIDTxtField.getText());

					HomePage.setCalledOnce(true);

					HomePage hp = new HomePage();

					removeAll();
					revalidate();
					repaint();
					add(hp);
					
				} else if(teacherID / 1000 == 5) {
					
					updateDataIntoEeeTeachers(tcrEmailTxtField.getText(), tcrPassField.getText(), tcrIDTxtField.getText());
					
					HomePage.setCalledOnce(true);

					HomePage hp = new HomePage();

					removeAll();
					revalidate();
					repaint();
					add(hp);
					
				} else if(teacherID / 1000 == 8) {
					
					
					updateDataIntoMeTeachers(tcrEmailTxtField.getText(), tcrPassField.getText(), tcrIDTxtField.getText());
					
					HomePage.setCalledOnce(true);

					HomePage hp = new HomePage();

					removeAll();
					revalidate();
					repaint();
					add(hp);
					
				} else if(teacherID / 1000 == 10) {
					
					
					updateDataIntoAnsTeachers(tcrEmailTxtField.getText(), tcrPassField.getText(), tcrIDTxtField.getText());
					
					HomePage.setCalledOnce(true);

					HomePage hp = new HomePage();

					removeAll();
					revalidate();
					repaint();
					add(hp);
				}
				
			}
		});
		
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
				
				if(OTPField.getText().equals(getOTPChecker())) {
					
					HomePage.setCalledOnce(true);

					HomePage hp = new HomePage();

					removeAll();
					revalidate();
					repaint();
					add(hp);
					
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
		
		OTPNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
		OTPNoti.setBounds(520, 441, 200, 20);
		add(OTPNoti);
		
		OTPInsertion.setBackground(new Color(43, 44, 52));
		OTPInsertion.setOpaque(true);
		OTPInsertion.setBounds(410, 289, 600, 300);
		OTPInsertion.setVisible(false);
		add(OTPInsertion);
		
		JLabel blurBg = new JLabel("");
		blurBg.setBackground(new Color(30, 30, 36, 220));
		blurBg.setOpaque(true);
		blurBg.setBounds(0, 0, 1420, 800);
		blurBg.setVisible(false);
		add(blurBg);

		getIDTxt().setBounds(510, 189, 70, 40);
		add(getIDTxt());

		tcrIDTxtField = new JTextField();
		tcrIDTxtField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				tcrIDTxtField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
			}
		});
		tcrIDTxtField.setForeground(Color.GRAY);
		tcrIDTxtField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		tcrIDTxtField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
		tcrIDTxtField.setOpaque(false);
		tcrIDTxtField.setBounds(510, 240, 407, 30);
		add(tcrIDTxtField);
		tcrIDTxtField.setColumns(10);

		getEmailTxt().setBounds(510, 305, 70, 40);
		add(getEmailTxt());

		tcrEmailTxtField = new JTextField();
		tcrEmailTxtField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				regNoti.setText("");
				tcrEmailTxtField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
			}
		});
		tcrEmailTxtField.setForeground(Color.GRAY);
		tcrEmailTxtField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		tcrEmailTxtField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
		tcrEmailTxtField.setOpaque(false);
		tcrEmailTxtField.setBounds(510, 356, 407, 30);
		add(tcrEmailTxtField);
		tcrEmailTxtField.setColumns(10);

		getPassTxt().setBounds(510, 423, 121, 40);
		add(getPassTxt());

		tcrPassField = new JPasswordField();
		tcrPassField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				regNoti.setText("");
				tcrPassField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
			}
		});

		// this is to show the strength of password

		tcrPassField.addKeyListener(new KeyAdapter() {
			int flag = 0;

			@Override
			public void keyTyped(KeyEvent e) {

				int len = tcrPassField.getPassword().length;

				for (int i = 0; i < len; i++) {

					char c = tcrPassField.getPassword()[i];

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
		tcrPassField.setForeground(Color.GRAY);
		tcrPassField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		tcrPassField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
		tcrPassField.setOpaque(false);
		tcrPassField.setBounds(510, 474, 407, 30);
		add(tcrPassField);

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

		tcrConPassField = new JPasswordField();
		tcrConPassField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				regNoti.setText("");
				tcrConPassField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
			}
		});
		tcrConPassField.setForeground(Color.GRAY);
		tcrConPassField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		tcrConPassField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
		tcrConPassField.setOpaque(false);
		tcrConPassField.setBounds(510, 610, 407, 30);
		add(tcrConPassField);

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
				
				if(isTeacherID() && strengthFlag && tcrPassField.getText().equals(tcrConPassField.getText()) && !isRegistered()) {
					
					String email_pattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
					
					Pattern p = Pattern.compile(email_pattern);
					java.util.regex.Matcher m = p.matcher(tcrEmailTxtField.getText());
					
					if(m.matches()) {
						
						getOTPTxt().setVisible(true);
						OTPField.setVisible(true);
						getOKTxt().setVisible(true);
						OKBtn.setVisible(true);
						OTPInsertion.setVisible(true);
						blurBg.setVisible(true);
						
						if(Integer.parseInt(tcrIDTxtField.getText()) / 1000 == 4) {
							
							readNameForEmail("select Teacher_ID, Teacher_name from cse_teachers");
							
						} else if(Integer.parseInt(tcrIDTxtField.getText()) / 1000 == 5) {
								
							readNameForEmail("select Teacher_ID, Teacher_name from eee_teachers");
							
						} else if(Integer.parseInt(tcrIDTxtField.getText()) / 1000 == 10) {
							
							readNameForEmail("select Teacher_ID, Teacher_name from arts_science_teachers");
						
						} else if(Integer.parseInt(tcrIDTxtField.getText()) / 1000 == 8) {
							
							readNameForEmail("select Teacher_ID, Teacher_name from me_teachers");
						
						}
						
						MailOTP.sendMail(tcrEmailTxtField.getText(), OTPName);
						
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
						
						tcrEmailTxtField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
					}
					
				}
					
				if(!isTeacherID()) {
					
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
					
					tcrIDTxtField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
				} 
				
				if(!strengthFlag) {
					
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
					
					tcrPassField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
				}
				
				if(!tcrPassField.getText().equals(tcrConPassField.getText())) {
					
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
					
					tcrPassField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
					tcrConPassField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
				}
					
			}

		});
		regNoti.setForeground(Color.RED);
		regNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 18));
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

		FadeInImg tcrImg = new FadeInImg(imgTeacher, 4);
		tcrImg.setBounds(410, 50, 100, 100);
		add(tcrImg);

		getTxtReg().setHorizontalAlignment(SwingConstants.LEFT);
		getTxtReg().setBounds(515, 100, 500, 50);
		add(getTxtReg());

		// Return button

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
		Image btnReturnImgScaled = btnReturnImg.getScaledInstance(btnReturn.getWidth(), btnReturn.getHeight(),
				Image.SCALE_SMOOTH);
		btnReturn.setIcon(new ImageIcon(btnReturnImgScaled));
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
	
	public boolean isTeacherID() { 
		try {  
		    
			teacherID = Integer.parseInt(tcrIDTxtField.getText());
			  
			if(teacherID / 1000 == 4 || teacherID / 1000 == 5 || teacherID/ 1000 == 10 || teacherID/ 1000 == 8) {
				  
				return true;
			} else {
				  
				return false;
			}
			  
		} catch(NumberFormatException e){  
			  
		  	return false;  
		}  
	}
	
	public void updateDataIntoCseTeachers(String email, String password, String id) {

		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			ps = c.prepareStatement("UPDATE cse_teachers SET Email = ?, Pass_word = DES_ENCRYPT(?,'SAM404'), register = ? WHERE Teacher_ID = ?");

			ps.setString(1, email);
			ps.setString(2, password);
			ps.setInt(3, 1);
			ps.setString(4, id);
			ps.executeUpdate();

		} catch (Exception e) {

			System.out.println(e);
		}
	}
	
	public void updateDataIntoEeeTeachers(String email, String password, String id) {

		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			ps = c.prepareStatement("UPDATE eee_teachers SET Email = ?, Pass_word = DES_ENCRYPT(?,'SAM404'), register = ? WHERE Teacher_ID = ?");

			ps.setString(1, email);
			ps.setString(2, password);
			ps.setInt(3, 1);
			ps.setString(4, id);
			ps.executeUpdate();

		} catch (Exception e) {

			System.out.println(e);
		}
	}
	
	public void updateDataIntoAnsTeachers(String email, String password, String id) {

		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			ps = c.prepareStatement("UPDATE arts_science_teachers SET Email = ?, Pass_word = DES_ENCRYPT(?,'SAM404'), register = ? WHERE Teacher_ID = ?");

			ps.setString(1, email);
			ps.setString(2, password);
			ps.setInt(3, 1);
			ps.setString(4, id);
			ps.executeUpdate();

		} catch (Exception e) {

			System.out.println(e);
		}
	}
	
	public void updateDataIntoMeTeachers(String email, String password, String id) {

		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			ps = c.prepareStatement("UPDATE me_teachers SET Email = ?, Pass_word = DES_ENCRYPT(?,'SAM404'), register = ? WHERE Teacher_ID = ?");

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
			String query = "Select * FROM cse_teachers";
			ResultSet rs = st.executeQuery(query);

			String ur = tcrIDTxtField.getText();
			while (rs.next()) {

				if (ur.equals(rs.getString(1))) {

					if (rs.getInt(12) == 1) {

						regNoti.setText("*Already Registered");
						regNoti.setForeground(Color.YELLOW);
						tcrIDTxtField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.YELLOW));
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

				if(rs.getString(1).equals(tcrIDTxtField.getText())){
					
					OTPName = rs.getString(2);
				}
			}
			
		} catch (Exception e) {

			System.out.println(e);
		}
	}
}
