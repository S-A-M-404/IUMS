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
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class LogInPage extends JPanel {

	private AnimatedLabel logInTxt = new AnimatedLabel("Log in to your account", 5);
	private JLabel IDTxt = new JLabel("ID");
	private JLabel passTxt = new JLabel("Password");
	private JCheckBox rememberMe = new JCheckBox("Remember Me");
	private JLabel logInBtnTxt = new JLabel("Log In");
	private JLabel forPassTxt = new JLabel("Forgot Password?");

	private AnimatedLabel lblRegedOrNot = new AnimatedLabel("Not registered yet?", 8);
	private AnimatedLabel lblRegNowTxt = new AnimatedLabel("Register Now!", 7);

	private Image btnReturnImg = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
	private Image btnReturnImgMouseIn = new ImageIcon(this.getClass().getResource("/return(mouse in).png")).getImage();

	private Image btnDefault = new ImageIcon(this.getClass().getResource("/btn layout.png")).getImage();
	private Image btnMouseIn = new ImageIcon(this.getClass().getResource("/btn layout(mouse in).png")).getImage();

	private Image regNow = new ImageIcon(this.getClass().getResource("/regNow.png")).getImage();
	private Image regNowMouseIn = new ImageIcon(this.getClass().getResource("/regNow(mouseIn).png")).getImage();

	private static JTextField logInID;
	private JPasswordField logInPass;
	
	private String id;
	private String pass;
	
	private static int teacherID;

	/**
	 * Create the panel.
	 */
	public LogInPage() {

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

		getLblRegedOrNot().setBounds(new Rectangle(530, 750, 200, 50));
		add(getLblRegedOrNot());

		getLblRegNowTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getLblRegNowTxt().setBounds(new Rectangle(720, 765, 160, 20));
		add(getLblRegNowTxt());

		FadeInImg btnRegNow = new FadeInImg(regNow, 2);
		btnRegNow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				btnRegNow.setIcon(new ImageIcon(regNowMouseIn));
			}

			@Override
			public void mouseExited(MouseEvent e) {

				btnRegNow.setIcon(new ImageIcon(regNow));
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
		btnRegNow.setBounds(new Rectangle(720, 765, 160, 20));
		add(btnRegNow);

		// Return button

		JLabel btnReturn = new JLabel("");
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				btnReturn.setIcon(new ImageIcon(btnReturnImgMouseIn.getScaledInstance(btnReturn.getWidth(), btnReturn.getHeight(), Image.SCALE_SMOOTH)));

			}

			@Override
			public void mouseExited(MouseEvent e) {

				btnReturn.setIcon(new ImageIcon(btnReturnImg.getScaledInstance(btnReturn.getWidth(), btnReturn.getHeight(), Image.SCALE_SMOOTH)));
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
		btnReturn.setIcon(new ImageIcon(
				btnReturnImg.getScaledInstance(btnReturn.getWidth(), btnReturn.getHeight(), Image.SCALE_SMOOTH)));
		add(btnReturn);
		
		JLabel logInNoti = new JLabel("");
		logInNoti.setForeground(Color.RED);
		logInNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
		logInNoti.setBounds(510, 459, 300, 20);
		add(logInNoti);

		getIDTxt().setForeground(new Color(118, 185, 0));
		getIDTxt().setBounds(510, 210, 70, 40);
		add(getIDTxt());

		setLogInID(new JTextField());
		getLogInID().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				getLogInID().setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
				logInPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
				logInNoti.setText("");
			}
		});
		getLogInID().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		getLogInID().setForeground(Color.GRAY);
		getLogInID().setOpaque(false);
		getLogInID().setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
		getLogInID().setBounds(510, 261, 407, 30);
		add(getLogInID());
		getLogInID().setColumns(10);

		getPassTxt().setForeground(new Color(118, 185, 0));
		getPassTxt().setBounds(510, 355, 121, 40);
		add(getPassTxt());

		logInPass = new JPasswordField();
		logInPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				getLogInID().setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
				logInPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
				logInNoti.setText("");
			}
		});
		logInPass.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		logInPass.setForeground(Color.GRAY);
		logInPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(118, 185, 0)));
		logInPass.setOpaque(false);
		logInPass.setBounds(510, 406, 407, 30);
		add(logInPass);

		JLabel logInBtn = new JLabel("");
		logInBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				logInBtn.setIcon(new ImageIcon(btnMouseIn));
			}

			@Override
			public void mouseExited(MouseEvent e) {

				logInBtn.setIcon(new ImageIcon(btnDefault));
			}

			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(rememberMe.isSelected()) {
					
					deleteData("delete from remember_me");
					insertData("insert into remember_me(ID,Password) values(?,DES_ENCRYPT(?,'SAM404'))", getLogInID().getText(), logInPass.getText());
				} else {
					
					deleteData("delete from remember_me");
				}
				
				if(getLogInID().getText().equals("") && logInPass.getText().equals("")) {
					
					getLogInID().setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
					logInPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));

					if (HomePage.getLanguage().equals("bangla")) {

						Locale.setDefault(new Locale("bn", "BN"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						logInNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
						logInNoti.setText(r.getString("incorrect"));

					} else {

						Locale.setDefault(new Locale("en", "UK"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						logInNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
						logInNoti.setText(r.getString("incorrect"));
					}
					
				} else if(logInPass.getText().equals("")) {
					
					logInPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
					
					if (HomePage.getLanguage().equals("bangla")) {

						Locale.setDefault(new Locale("bn", "BN"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						logInNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
						logInNoti.setText(r.getString("incorrect"));

					} else {

						Locale.setDefault(new Locale("en", "UK"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						logInNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
						logInNoti.setText(r.getString("incorrect"));
					}
					
				} else if(logInID.getText().charAt(0) == 'S' && logInID.getText().charAt(1) == 'A' && logInID.getText().charAt(2) == 'M') {
					
					readDataFromAdminTable();
					
					if(logInID.getText().equals(id) && logInPass.getText().equals(pass)) {
						
						if (HomePage.getLanguage().equals("bangla")) {

							Locale.setDefault(new Locale("bn", "BN"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							AdminProfilePage app = new AdminProfilePage();
							
							app.showPunishmentTable();
							
							app.getLogOutTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
							app.getLogOutTxt().setText(r.getString("logout"));
							
							app.getCourseTitle().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 35));
							app.getCourseTitle().setText(r.getString("addfine"));
							
							app.getCourseTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
							app.getCourseTxt().setText(r.getString("serial"));
							
							app.getPunTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
							app.getPunTxt().setText(r.getString("submit"));
							
							app.getSecTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
							app.getSecTxt().setText(r.getString("fine"));
							
							removeAll();
							revalidate();
							repaint();
							add(app);

						} else {

							Locale.setDefault(new Locale("en", "UK"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							AdminProfilePage app = new AdminProfilePage();
							
							app.showPunishmentTable();
							
							app.getLogOutTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
							app.getLogOutTxt().setText(r.getString("logout"));
							
							app.getCourseTitle().setFont(new Font("Eras Medium ITC", Font.PLAIN, 40));
							app.getCourseTitle().setText(r.getString("addfine"));
							
							app.getCourseTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
							app.getCourseTxt().setText(r.getString("serial"));
							
							app.getPunTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
							app.getPunTxt().setText(r.getString("submit"));
							
							app.getSecTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
							app.getSecTxt().setText(r.getString("fine"));
							
							removeAll();
							revalidate();
							repaint();
							add(app);

						}
						
					} else if(!getLogInID().getText().equals(id) || !logInPass.getText().equals(pass)) {
						
						getLogInID().setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
						logInPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
						
						if (HomePage.getLanguage().equals("bangla")) {

							Locale.setDefault(new Locale("bn", "BN"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							logInNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
							logInNoti.setText(r.getString("incorrect"));

						} else {

							Locale.setDefault(new Locale("en", "UK"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							logInNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
							logInNoti.setText(r.getString("incorrect"));
						}
						
					}
					
					
				} else if(isStudentID()) {
					
					StudentProfilePage spp = new StudentProfilePage();
					
					if(getLogInID().getText().charAt(7) == '4' && isRegistered("Select * FROM cse_students")) {
						
						readDataFromStudentsTable();
						
						if(getLogInID().getText().equals(id) && logInPass.getText().equals(pass)) {
							
							spp.readImagefromStudentsTable();
							
							if (HomePage.getLanguage().equals("bangla")) {

								Locale.setDefault(new Locale("bn", "BN"));
								ResourceBundle r = ResourceBundle.getBundle("bundle");

								spp.getClearenceFormTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
								spp.getClearenceFormTxt().setText(r.getString("form"));
								
								spp.getClearancePunTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
								spp.getClearancePunTxt().setText(r.getString("clrpun"));
								
								spp.getSettingsTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
								spp.getSettingsTxt().setText(r.getString("settings"));
								
								spp.getLogOutTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
								spp.getLogOutTxt().setText(r.getString("logout"));
								
								spp.getLblPersonalInformation().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 35));
								spp.getLblPersonalInformation().setText(r.getString("welpro"));
								
								spp.getNameTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								spp.getNameTxt().setText(r.getString("name"));
								
								spp.getIDTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								spp.getIDTxt().setText(r.getString("id"));
								
								spp.getLblSection().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								spp.getLblSection().setText(r.getString("sect"));
								
								spp.getLblSemester().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								spp.getLblSemester().setText(r.getString("sem"));
								
								removeAll();
								revalidate();
								repaint();
								add(spp);

							} else {

								Locale.setDefault(new Locale("en", "UK"));
								ResourceBundle r = ResourceBundle.getBundle("bundle");
								
								spp.getClearenceFormTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
								spp.getClearenceFormTxt().setText(r.getString("form"));
								
								spp.getClearancePunTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
								spp.getClearancePunTxt().setText(r.getString("clrpun"));
								
								spp.getSettingsTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
								spp.getSettingsTxt().setText(r.getString("settings"));
								
								spp.getLogOutTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
								spp.getLogOutTxt().setText(r.getString("logout"));
								
								spp.getLblPersonalInformation().setFont(new Font("Eras Medium ITC", Font.PLAIN, 40));
								spp.getLblPersonalInformation().setText(r.getString("welpro"));
								
								spp.getNameTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
								spp.getNameTxt().setText(r.getString("name"));
								
								spp.getIDTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
								spp.getIDTxt().setText(r.getString("id"));
								
								spp.getLblSection().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
								spp.getLblSection().setText(r.getString("sect"));
								
								spp.getLblSemester().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
								spp.getLblSemester().setText(r.getString("sem"));

								removeAll();
								revalidate();
								repaint();
								add(spp);

							}
						} else if(!getLogInID().getText().equals(id) || !logInPass.getText().equals(pass)) {
							
							getLogInID().setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
							logInPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
							
							if (HomePage.getLanguage().equals("bangla")) {

								Locale.setDefault(new Locale("bn", "BN"));
								ResourceBundle r = ResourceBundle.getBundle("bundle");

								logInNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								logInNoti.setText(r.getString("incorrect"));

							} else {

								Locale.setDefault(new Locale("en", "UK"));
								ResourceBundle r = ResourceBundle.getBundle("bundle");

								logInNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
								logInNoti.setText(r.getString("incorrect"));
							}
							
						}
							
					}
					
				} else if (isTeacherID()){
					
					TeacherProfilePage tpp = new TeacherProfilePage();
					
					if(getTeacherID() / 1000 == 4 && isRegistered("Select * FROM cse_teachers")) {
						
						readDataFromTeachersTable("select Teacher_ID,DES_DECRYPT(Pass_word,'SAM404'),Teacher_Name,register from cse_teachers");
						
						if(getLogInID().getText().equals(id) && logInPass.getText().equals(pass)) {
							
							tpp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from cse_teachers");
							
							if (HomePage.getLanguage().equals("bangla")) {

								Locale.setDefault(new Locale("bn", "BN"));
								ResourceBundle r = ResourceBundle.getBundle("bundle");

								tpp.getAllottedCorseTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
								tpp.getAllottedCorseTxt().setText(r.getString("allotted"));
								
								tpp.getSettingsTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
								tpp.getSettingsTxt().setText(r.getString("settings"));
								
								tpp.getLogOutTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
								tpp.getLogOutTxt().setText(r.getString("logout"));
								
								tpp.getTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 35));
								tpp.getTxt().setText(r.getString("welpro"));
								
								tpp.getNameTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								tpp.getNameTxt().setText(r.getString("name"));
								
								tpp.getIdTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								tpp.getIdTxt().setText(r.getString("id"));
								
								tpp.getSemTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								tpp.getSemTxt().setText(r.getString("desi"));
								
								tpp.getSecTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								tpp.getSecTxt().setText(r.getString("cont"));
								
								removeAll();
								revalidate();
								repaint();
								add(tpp);

							} else {

								Locale.setDefault(new Locale("en", "UK"));
								ResourceBundle r = ResourceBundle.getBundle("bundle");

								tpp.getAllottedCorseTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
								tpp.getAllottedCorseTxt().setText(r.getString("allotted"));
								
								tpp.getSettingsTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
								tpp.getSettingsTxt().setText(r.getString("settings"));
								
								tpp.getLogOutTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
								tpp.getLogOutTxt().setText(r.getString("logout"));
								
								tpp.getTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 40));
								tpp.getTxt().setText(r.getString("welpro"));
								
								tpp.getNameTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
								tpp.getNameTxt().setText(r.getString("name"));
								
								tpp.getIdTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
								tpp.getIdTxt().setText(r.getString("id"));
								
								tpp.getSemTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
								tpp.getSemTxt().setText(r.getString("desi"));
								
								tpp.getSecTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
								tpp.getSecTxt().setText(r.getString("cont"));

								removeAll();
								revalidate();
								repaint();
								add(tpp);

							}
							
						} else if(!getLogInID().getText().equals(id) || !logInPass.getText().equals(pass)) {
							
							getLogInID().setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
							logInPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
							
							if (HomePage.getLanguage().equals("bangla")) {

								Locale.setDefault(new Locale("bn", "BN"));
								ResourceBundle r = ResourceBundle.getBundle("bundle");

								logInNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								logInNoti.setText(r.getString("incorrect"));

							} else {

								Locale.setDefault(new Locale("en", "UK"));
								ResourceBundle r = ResourceBundle.getBundle("bundle");

								logInNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
								logInNoti.setText(r.getString("incorrect"));
							}
							
						}
						
					} else if(getTeacherID() / 1000 == 5 && isRegistered("Select * FROM eee_teachers")) {
						
						readDataFromTeachersTable("select Teacher_ID,DES_DECRYPT(Pass_word,'SAM404'),Teacher_Name,register from eee_teachers");
						
						if(getLogInID().getText().equals(id) && logInPass.getText().equals(pass)) {
							
							tpp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from eee_teachers");
							
							if (HomePage.getLanguage().equals("bangla")) {

								Locale.setDefault(new Locale("bn", "BN"));
								ResourceBundle r = ResourceBundle.getBundle("bundle");

								tpp.getAllottedCorseTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
								tpp.getAllottedCorseTxt().setText(r.getString("allotted"));
								
								tpp.getSettingsTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
								tpp.getSettingsTxt().setText(r.getString("settings"));
								
								tpp.getLogOutTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
								tpp.getLogOutTxt().setText(r.getString("logout"));
								
								tpp.getTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 35));
								tpp.getTxt().setText(r.getString("welpro"));
								
								tpp.getNameTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								tpp.getNameTxt().setText(r.getString("name"));
								
								tpp.getIdTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								tpp.getIdTxt().setText(r.getString("id"));
								
								tpp.getSemTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								tpp.getSemTxt().setText(r.getString("desi"));
								
								tpp.getSecTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								tpp.getSecTxt().setText(r.getString("cont"));

								removeAll();
								revalidate();
								repaint();
								add(tpp);

							} else {

								Locale.setDefault(new Locale("en", "UK"));
								ResourceBundle r = ResourceBundle.getBundle("bundle");

								tpp.getAllottedCorseTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
								tpp.getAllottedCorseTxt().setText(r.getString("allotted"));
								
								tpp.getSettingsTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
								tpp.getSettingsTxt().setText(r.getString("settings"));
								
								tpp.getLogOutTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
								tpp.getLogOutTxt().setText(r.getString("logout"));
								
								tpp.getTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 40));
								tpp.getTxt().setText(r.getString("welpro"));
								
								tpp.getNameTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
								tpp.getNameTxt().setText(r.getString("name"));
								
								tpp.getIdTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
								tpp.getIdTxt().setText(r.getString("id"));
								
								tpp.getSemTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
								tpp.getSemTxt().setText(r.getString("desi"));
								
								tpp.getSecTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
								tpp.getSecTxt().setText(r.getString("cont"));

								removeAll();
								revalidate();
								repaint();
								add(tpp);

							}
							
						} else if(!getLogInID().getText().equals(id) || !logInPass.getText().equals(pass)) {
							
							getLogInID().setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
							logInPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
							
							if (HomePage.getLanguage().equals("bangla")) {

								Locale.setDefault(new Locale("bn", "BN"));
								ResourceBundle r = ResourceBundle.getBundle("bundle");

								logInNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								logInNoti.setText(r.getString("incorrect"));

							} else {

								Locale.setDefault(new Locale("en", "UK"));
								ResourceBundle r = ResourceBundle.getBundle("bundle");

								logInNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
								logInNoti.setText(r.getString("incorrect"));
							}
							
						}
						
					} else if(getTeacherID() / 1000 == 10 && isRegistered("Select * FROM arts_science_teachers")) {
						
						readDataFromTeachersTable("select Teacher_ID,DES_DECRYPT(Pass_word,'SAM404'),Teacher_Name,register from arts_science_teachers");
						
						if(getLogInID().getText().equals(id) && logInPass.getText().equals(pass)) {
							
							tpp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from arts_science_teachers");
							
							if (HomePage.getLanguage().equals("bangla")) {

								Locale.setDefault(new Locale("bn", "BN"));
								ResourceBundle r = ResourceBundle.getBundle("bundle");

								tpp.getAllottedCorseTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
								tpp.getAllottedCorseTxt().setText(r.getString("allotted"));
								
								tpp.getSettingsTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
								tpp.getSettingsTxt().setText(r.getString("settings"));
								
								tpp.getLogOutTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
								tpp.getLogOutTxt().setText(r.getString("logout"));
								
								tpp.getTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 35));
								tpp.getTxt().setText(r.getString("welpro"));
								
								tpp.getNameTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								tpp.getNameTxt().setText(r.getString("name"));
								
								tpp.getIdTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								tpp.getIdTxt().setText(r.getString("id"));
								
								tpp.getSemTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								tpp.getSemTxt().setText(r.getString("desi"));
								
								tpp.getSecTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								tpp.getSecTxt().setText(r.getString("cont"));

								removeAll();
								revalidate();
								repaint();
								add(tpp);

							} else {

								Locale.setDefault(new Locale("en", "UK"));
								ResourceBundle r = ResourceBundle.getBundle("bundle");

								tpp.getAllottedCorseTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
								tpp.getAllottedCorseTxt().setText(r.getString("allotted"));
								
								tpp.getSettingsTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
								tpp.getSettingsTxt().setText(r.getString("settings"));
								
								tpp.getLogOutTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
								tpp.getLogOutTxt().setText(r.getString("logout"));
								
								tpp.getTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 40));
								tpp.getTxt().setText(r.getString("welpro"));
								
								tpp.getNameTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
								tpp.getNameTxt().setText(r.getString("name"));
								
								tpp.getIdTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
								tpp.getIdTxt().setText(r.getString("id"));
								
								tpp.getSemTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
								tpp.getSemTxt().setText(r.getString("desi"));
								
								tpp.getSecTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
								tpp.getSecTxt().setText(r.getString("cont"));

								removeAll();
								revalidate();
								repaint();
								add(tpp);

							}
							
						} else if(!getLogInID().getText().equals(id) || !logInPass.getText().equals(pass)) {
							
							getLogInID().setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
							logInPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
							
							if (HomePage.getLanguage().equals("bangla")) {

								Locale.setDefault(new Locale("bn", "BN"));
								ResourceBundle r = ResourceBundle.getBundle("bundle");

								logInNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								logInNoti.setText(r.getString("incorrect"));

							} else {

								Locale.setDefault(new Locale("en", "UK"));
								ResourceBundle r = ResourceBundle.getBundle("bundle");

								logInNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
								logInNoti.setText(r.getString("incorrect"));
							}
							
						}
						
					} else if(getTeacherID() / 1000 == 8 && isRegistered("Select * FROM me_teachers")) {
						
						readDataFromTeachersTable("select Teacher_ID,DES_DECRYPT(Pass_word,'SAM404'),Teacher_Name,register from me_teachers");
						
						if(getLogInID().getText().equals(id) && logInPass.getText().equals(pass)) {
							
							tpp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from me_teachers");
							
							if (HomePage.getLanguage().equals("bangla")) {

								Locale.setDefault(new Locale("bn", "BN"));
								ResourceBundle r = ResourceBundle.getBundle("bundle");

								tpp.getAllottedCorseTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
								tpp.getAllottedCorseTxt().setText(r.getString("allotted"));
								
								tpp.getSettingsTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
								tpp.getSettingsTxt().setText(r.getString("settings"));
								
								tpp.getLogOutTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
								tpp.getLogOutTxt().setText(r.getString("logout"));
								
								tpp.getTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 35));
								tpp.getTxt().setText(r.getString("welpro"));
								
								tpp.getNameTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								tpp.getNameTxt().setText(r.getString("name"));
								
								tpp.getIdTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								tpp.getIdTxt().setText(r.getString("id"));
								
								tpp.getSemTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								tpp.getSemTxt().setText(r.getString("desi"));
								
								tpp.getSecTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								tpp.getSecTxt().setText(r.getString("cont"));

								removeAll();
								revalidate();
								repaint();
								add(tpp);

							} else {

								Locale.setDefault(new Locale("en", "UK"));
								ResourceBundle r = ResourceBundle.getBundle("bundle");

								tpp.getAllottedCorseTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
								tpp.getAllottedCorseTxt().setText(r.getString("allotted"));
								
								tpp.getSettingsTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
								tpp.getSettingsTxt().setText(r.getString("settings"));
								
								tpp.getLogOutTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
								tpp.getLogOutTxt().setText(r.getString("logout"));
								
								tpp.getTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 40));
								tpp.getTxt().setText(r.getString("welpro"));
								
								tpp.getNameTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
								tpp.getNameTxt().setText(r.getString("name"));
								
								tpp.getIdTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
								tpp.getIdTxt().setText(r.getString("id"));
								
								tpp.getSemTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
								tpp.getSemTxt().setText(r.getString("desi"));
								
								tpp.getSecTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
								tpp.getSecTxt().setText(r.getString("cont"));

								removeAll();
								revalidate();
								repaint();
								add(tpp);

							}
							
						} else if(!getLogInID().getText().equals(id) || !logInPass.getText().equals(pass)) {
							
							getLogInID().setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
							logInPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
							
							if (HomePage.getLanguage().equals("bangla")) {

								Locale.setDefault(new Locale("bn", "BN"));
								ResourceBundle r = ResourceBundle.getBundle("bundle");

								logInNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
								logInNoti.setText(r.getString("incorrect"));

							} else {

								Locale.setDefault(new Locale("en", "UK"));
								ResourceBundle r = ResourceBundle.getBundle("bundle");

								logInNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
								logInNoti.setText(r.getString("incorrect"));
							}
							
						}
						
					}
					
				} else {
					
					getLogInID().setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.RED));
					
					if (HomePage.getLanguage().equals("bangla")) {

						Locale.setDefault(new Locale("bn", "BN"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						logInNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
						logInNoti.setText(r.getString("incorrect"));

					} else {

						Locale.setDefault(new Locale("en", "UK"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						logInNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
						logInNoti.setText(r.getString("incorrect"));
					}
				}
				
			}
		});

		getLogInBtnTxt().setForeground(Color.WHITE);
		getLogInBtnTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getLogInBtnTxt().setBounds(629, 540, 162, 52);
		add(getLogInBtnTxt());
		logInBtn.setBounds(629, 540, 162, 52);
		logInBtn.setIcon(new ImageIcon(btnDefault));
		add(logInBtn);

		getRememberMe().setHorizontalAlignment(SwingConstants.LEFT);
		getRememberMe().setForeground(new Color(118, 185, 0));
		getRememberMe().setOpaque(false);
		getRememberMe().setBounds(510, 500, 162, 23);
		add(getRememberMe());
		
		forPassTxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (HomePage.getLanguage().equals("bangla")) {

					Locale.setDefault(new Locale("bn", "BN"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					ForgotPasswordPage fpp = new ForgotPasswordPage();
					
					fpp.getForgotTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 30));
					fpp.getForgotTxt().setText(r.getString("forgot"));
					fpp.getForgotTxt().setForeground(new Color(30, 30, 36));
					
					fpp.getLblEnterIdOr().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 25));
					fpp.getLblEnterIdOr().setText(r.getString("idormail"));
					
					fpp.getSendTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					fpp.getSendTxt().setText(r.getString("otp"));
					
					fpp.getNotiSlide().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 19));
					fpp.getNotiSlide().setText("<html>" + r.getString("mailnoti") + "</html>");
					
					removeAll();
					revalidate();
					repaint();
					add(fpp);

				} else {

					Locale.setDefault(new Locale("en", "UK"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");
					
					ForgotPasswordPage fpp = new ForgotPasswordPage();
					
					fpp.getForgotTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 30));
					fpp.getForgotTxt().setText(r.getString("forgot"));
					fpp.getForgotTxt().setForeground(new Color(30, 30, 36));
					
					fpp.getLblEnterIdOr().setFont(new Font("Eras Medium ITC", Font.PLAIN, 25));
					fpp.getLblEnterIdOr().setText(r.getString("idormail"));
					
					fpp.getSendTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 24));
					fpp.getSendTxt().setText(r.getString("otp"));
					
					fpp.getNotiSlide().setFont(new Font("Eras Light ITC", Font.PLAIN, 28));
					fpp.getNotiSlide().setText("<html>" + r.getString("mailnoti") + "</html>");
					
					removeAll();
					revalidate();
					repaint();
					add(fpp);
					
				}
			}
		});
		
		getForPassTxt().setHorizontalAlignment(SwingConstants.TRAILING);
		getForPassTxt().setForeground(new Color(118, 185, 0));
		getForPassTxt().setBounds(720, 500, 200, 23);
		add(getForPassTxt());

		JLabel logInPanel = new JLabel("");
		logInPanel.setBackground(new Color(28, 28, 32));
		logInPanel.setOpaque(true);
		logInPanel.setBounds(410, 200, 600, 420);
		add(logInPanel);

		getLogInTxt().setBounds(410, 160, 450, 40);
		add(getLogInTxt());
		
		
		readData("select ID,DES_DECRYPT(Password,'SAM404') from remember_me where Sl_No='1'");

	}

	public AnimatedLabel getLblRegedOrNot() {
		return lblRegedOrNot;
	}

	public void setLblRegedOrNot(AnimatedLabel lblRegedOrNot) {
		this.lblRegedOrNot = lblRegedOrNot;
	}

	public AnimatedLabel getLblRegNowTxt() {
		return lblRegNowTxt;
	}

	public void setLblRegNowTxt(AnimatedLabel lblRegNowTxt) {
		this.lblRegNowTxt = lblRegNowTxt;
	}

	public JLabel getLogInTxt() {
		return logInTxt;
	}

	public void setLogInTxt(AnimatedLabel logInTxt) {
		this.logInTxt = logInTxt;
	}

	public JLabel getIDTxt() {
		return IDTxt;
	}

	public void setIDTxt(JLabel iDTxt) {
		IDTxt = iDTxt;
	}

	public JLabel getPassTxt() {
		return passTxt;
	}

	public void setPassTxt(JLabel passTxt) {
		this.passTxt = passTxt;
	}

	public JCheckBox getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(JCheckBox rememberMe) {
		this.rememberMe = rememberMe;
	}

	public JLabel getLogInBtnTxt() {
		return logInBtnTxt;
	}

	public void setLogInBtnTxt(JLabel logInBtnTxt) {
		this.logInBtnTxt = logInBtnTxt;
	}
	
	public boolean isTeacherID() { 
	  try {  
	    
		  setTeacherID(Integer.parseInt(getLogInID().getText()));
		  
		  if(getTeacherID() / 1000 == 4 || getTeacherID() / 1000 == 5 || getTeacherID() / 1000 == 10 || getTeacherID() / 1000 == 8) {
			  
			  return true;
		  } else {
			  
			  return false;
		  }
		  
	  } catch(NumberFormatException e){  
		  
		  return false;  
	  }  
	}
	
	public boolean isStudentID() {
		
		try {
			
			if(getLogInID().getText().charAt(2) == '.' && getLogInID().getText().charAt(5) == '.' && getLogInID().getText().charAt(8) == '.' && getLogInID().getText().length() == 12) {
				
				return true;
			} else {
				
				return false;
			}
			
		} catch (Exception e) {
			
			return false;
		}
		
	}
	
	public void insertData(String query, String ID, String password) {
		
		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			
			ps = c.prepareStatement(query);
			
			ps.setString(1, ID);
			ps.setString(2, password);
			ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void readData(String query) {
		
		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;	
		ResultSet rs = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			
			ps = c.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				getLogInID().setText(rs.getString("ID"));
				logInPass.setText(rs.getString("DES_DECRYPT(Password,'SAM404')"));
			}
			
		} catch (Exception e) {
			
			System.out.println(e);
		}
		
	}
	
	public void deleteData(String query) {
		
		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;	
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			
			ps = c.prepareStatement(query);
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			
			System.out.println(e);
		}
		
	}
	
	public boolean isRegistered(String query) {

		java.sql.Connection c = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			java.sql.Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) {

				if (getLogInID().getText().equals(rs.getString(1))) {

					if (rs.getInt("register") == 1) {

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
	
	public void readDataFromStudentsTable() {

		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;	
		ResultSet rs = null;
		
		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			String query = "select ID,DES_DECRYPT(Pass_word,'SAM404'),Student_Name,register from cse_students";
			ps = c.prepareStatement(query);
			rs = ps.executeQuery();

			String id2 = getLogInID().getText();
			while (rs.next()) {

				if (id2.equals(rs.getString(1))) {

					if (rs.getInt("register") == 1) {

						id = rs.getString("ID");
						pass = rs.getString("DES_DECRYPT(Pass_word,'SAM404')");
						StudentProfilePage.getProName().setText(rs.getString("Student_Name"));

					}
				}
			
			}

		} catch (Exception e) {

			System.out.println(e);
		}
	}
	
	public void readDataFromTeachersTable(String query) {

		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;	
		ResultSet rs = null;
		
		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			ps = c.prepareStatement(query);
			rs = ps.executeQuery();

			String id2 = getLogInID().getText();
			while (rs.next()) {

				if (id2.equals(rs.getString(1))) {

					if (rs.getInt("register") == 1) {

						id = rs.getString("Teacher_ID");
						pass = rs.getString("DES_DECRYPT(Pass_word,'SAM404')");
						TeacherProfilePage.getProName().setText(rs.getString("Teacher_Name"));

					}
				}
			
			}
			
		} catch (Exception e) {

			System.out.println(e);
		}
	}
	
	public void readDataFromAdminTable() {

		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;	
		ResultSet rs = null;
		
		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			String query = "select Admin_ID,Admin_name, Pass_word from admin";
			ps = c.prepareStatement(query);
			rs = ps.executeQuery();

			String id2 = getLogInID().getText();
			while (rs.next()) {

				if (id2.equals(rs.getString(1))) {

					id = rs.getString("Admin_ID");
					pass = rs.getString("Pass_word");
					AdminProfilePage.getProName().setText(rs.getString(2));
					
				}
			
			}

		} catch (Exception e) {

			System.out.println(e);
		}
	}

	public JLabel getForPassTxt() {
		return forPassTxt;
	}

	public void setForPassTxt(JLabel forPassTxt) {
		this.forPassTxt = forPassTxt;
	}

	public static JTextField getLogInID() {
		return logInID;
	}

	public static void setLogInID(JTextField logInID) {
		LogInPage.logInID = logInID;
	}

	public static int getTeacherID() {
		return teacherID;
	}

	public static void setTeacherID(int teacherID) {
		LogInPage.teacherID = teacherID;
	}
}
