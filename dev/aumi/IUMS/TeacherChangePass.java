package dev.aumi.IUMS;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class TeacherChangePass extends JPanel {

	private Image btnReturnImg = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
	private Image btnReturnImgMouseIn = new ImageIcon(this.getClass().getResource("/return(mouse in).png")).getImage();
	
	private Image btnDefault = new ImageIcon(this.getClass().getResource("/btn layout.png")).getImage();
	private Image btnMouseIn = new ImageIcon(this.getClass().getResource("/btn layout(mouse in).png")).getImage();
	
	private Image dpFrameIcon = new ImageIcon(this.getClass().getResource("/dp.png")).getImage();
	private Image Dp = new ImageIcon(this.getClass().getResource("/tempdp.png")).getImage();
	private Image dpFrameBigIcon = new ImageIcon(this.getClass().getResource("/dpbig.png")).getImage();
	private Image notiNull = new ImageIcon(this.getClass().getResource("/notification null.png")).getImage();
	private Image menuIcon = new ImageIcon(this.getClass().getResource("/menu.png")).getImage();
	private Image openMenuIcon = new ImageIcon(this.getClass().getResource("/openmenu.png")).getImage();
	private Image courseIcon = new ImageIcon(this.getClass().getResource("/result.png")).getImage();
	private Image openCourseIcon = new ImageIcon(this.getClass().getResource("/openresult.png")).getImage();
	private Image logOutIcon = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
	private Image openLogOutIcon = new ImageIcon(this.getClass().getResource("/openlogout.png")).getImage();
	private Image noticeIcon = new ImageIcon(this.getClass().getResource("/notice.png")).getImage();
	private Image openNoticeIcon = new ImageIcon(this.getClass().getResource("/opennotice.png")).getImage();
	
	private JLabel proPic = new JLabel("");
	private JLabel passTxt = new JLabel("Change Password");
	private JLabel oldPassTxt = new JLabel("Old Password");
	private JLabel newPassTxt = new JLabel("New Password");
	private JLabel chngTxt = new JLabel("Change");
	private JLabel conPassTxt = new JLabel("Confirm Password");
	private JLabel passNoti = new JLabel("");
	private JLabel strengthTxt = new JLabel();
	private JLabel changePassNoti = new JLabel();
	private JLabel passNotiIcn = new JLabel();
	
	private ImageIcon imageSmall; 
	private ImageIcon imageBig;
	
	private JProgressBar passBar = new JProgressBar();
	
	private AnimatedLabel menuBar = new AnimatedLabel("", 11);
	private AnimatedLabel dpFrameBig = new AnimatedLabel("", 12);
	private AnimatedLabel proPicBig = new AnimatedLabel("", 12);
	private AnimatedLabel proName = new AnimatedLabel("", 13);
	private AnimatedLabel allottedCorseTxt = new AnimatedLabel("Allotted Courses", 13);
	private AnimatedLabel logOutTxt = new AnimatedLabel("Log Out", 13);
	
	private boolean strengthFlag = false;
	
	private String path;
	private String pass;
	
	private JPasswordField oldPassField = new JPasswordField();
	private JPasswordField newPassField = new JPasswordField();
	private JPasswordField conPassField = new JPasswordField();
	
	/**
	 * Create the panel.
	 */
	public TeacherChangePass() {

		setLayout(null);
		setBounds(0, 0, 1420, 800);
		setBackground(new Color(30, 30, 36));
		
		if(LogInPage.getTeacherID() / 1000 == 4) {
			
			readPasswordFromTeachersTable("select Teacher_name, DES_DECRYPT(Pass_word,'SAM404') from cse_teachers");
			
		} else if(LogInPage.getTeacherID() / 1000 == 5) {
				
			readPasswordFromTeachersTable("select Teacher_name, DES_DECRYPT(Pass_word,'SAM404') from eee_teachers");
			
		} else if(LogInPage.getTeacherID() / 1000 == 10) {
			
			readPasswordFromTeachersTable("select Teacher_name, DES_DECRYPT(Pass_word,'SAM404') from arts_science_teachers");
			
		} else if(LogInPage.getTeacherID() / 1000 == 8) {
			
			readPasswordFromTeachersTable("select Teacher_name, DES_DECRYPT(Pass_word,'SAM404') from me_teachers");
		}

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

				Image btnReturnImgMouseInScaled = btnReturnImgMouseIn.getScaledInstance(btnReturn.getWidth(), btnReturn.getHeight(), Image.SCALE_SMOOTH);
				btnReturn.setIcon(new ImageIcon(btnReturnImgMouseInScaled));
			}

			@Override
			public void mouseExited(MouseEvent e) {

				Image btnReturnImgScaled = btnReturnImg.getScaledInstance(btnReturn.getWidth(), btnReturn.getHeight(), Image.SCALE_SMOOTH);
				btnReturn.setIcon(new ImageIcon(btnReturnImgScaled));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (HomePage.getLanguage().equals("bangla")) {

					Locale.setDefault(new Locale("bn", "BN"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					TeacherSettingsPage tsp = new TeacherSettingsPage();
					
					if(LogInPage.getTeacherID() / 1000 == 4) {
						
						tsp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from cse_teachers");
						
					} else if(LogInPage.getTeacherID() / 1000 == 5) {
							
						tsp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from eee_teachers");
						
					} else if(LogInPage.getTeacherID() / 1000 == 10) {
						
						tsp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from arts_science_teachers");
						
					} else if(LogInPage.getTeacherID() / 1000 == 8) {
						
						tsp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from me_teachers");
					}

					tsp.getAllottedCorseTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					tsp.getAllottedCorseTxt().setText(r.getString("allotted"));
					
					tsp.getLogOutTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					tsp.getLogOutTxt().setText(r.getString("logout"));
					
					tsp.getLblSettings().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 35));
					tsp.getLblSettings().setText(r.getString("settings"));

					tsp.getEditTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
					tsp.getEditTxt().setText(r.getString("edittxt"));
					
					tsp.getLblChangePassword().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
					tsp.getLblChangePassword().setText(r.getString("changepass"));
					
					removeAll();
					revalidate();
					repaint();
					add(tsp);

				} else {

					Locale.setDefault(new Locale("en", "UK"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					TeacherSettingsPage tsp = new TeacherSettingsPage();
					
					if(LogInPage.getTeacherID() / 1000 == 4) {
						
						tsp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from cse_teachers");
						
					} else if(LogInPage.getTeacherID() / 1000 == 5) {
							
						tsp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from eee_teachers");
						
					} else if(LogInPage.getTeacherID() / 1000 == 10) {
						
						tsp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from arts_science_teachers");
						
					} else if(LogInPage.getTeacherID() / 1000 == 8) {
						
						tsp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from me_teachers");
					}

					tsp.getAllottedCorseTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					tsp.getAllottedCorseTxt().setText(r.getString("allotted"));
					
					tsp.getLogOutTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					tsp.getLogOutTxt().setText(r.getString("logout"));
					
					tsp.getLblSettings().setFont(new Font("Eras Medium ITC", Font.PLAIN, 40));
					tsp.getLblSettings().setText(r.getString("settings"));
					
					tsp.getEditTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					tsp.getEditTxt().setText(r.getString("edittxt"));
					
					tsp.getLblChangePassword().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					tsp.getLblChangePassword().setText(r.getString("changepass"));
					
					removeAll();
					revalidate();
					repaint();
					add(tsp);

				}

			}
		});
		btnReturn.setBounds(new Rectangle(15, 31, 28, 28));
		Image btnReturnImgScaled = btnReturnImg.getScaledInstance(btnReturn.getWidth(), btnReturn.getHeight(), Image.SCALE_SMOOTH);
		btnReturn.setIcon(new ImageIcon(btnReturnImgScaled));
		add(btnReturn);

		JLabel dpFrame = new JLabel("");
		dpFrame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// This is to open profile picture

				JFileChooser file = new JFileChooser();

				file.setCurrentDirectory(new File(System.getProperty("user.home")));

				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
				file.addChoosableFileFilter(filter);
				int result = file.showSaveDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {

					File selectedFile = file.getSelectedFile();
					path = selectedFile.getAbsolutePath();
					
					updateImageIntoTeachersTable();
					
					readImagefromTeachersTable();
					
				}

			}
		});
		dpFrame.setBounds(1290, 30, 30, 30);
		dpFrame.setIcon(new ImageIcon(dpFrameIcon.getScaledInstance(dpFrame.getWidth(), dpFrame.getHeight(), Image.SCALE_SMOOTH)));
		add(dpFrame);

		proPic.setBounds(1292, 30, 25, 25);
		proPic.setIcon(new ImageIcon(Dp.getScaledInstance(proPic.getWidth(), proPic.getHeight(), Image.SCALE_SMOOTH)));
		add(proPic);

		JLabel notiIcon = new JLabel("");
		notiIcon.setBounds(1345, 32, 25, 25);
		notiIcon.setIcon(new ImageIcon(notiNull.getScaledInstance(notiIcon.getWidth(), notiIcon.getHeight(), Image.SCALE_SMOOTH)));
		add(notiIcon);

		JLabel notificationBar = new JLabel("");
		notificationBar.setBackground(new Color(52, 52, 61));
		notificationBar.setOpaque(true);
		notificationBar.setBounds(0, 30, 1420, 30);
		add(notificationBar);
		
		JLabel menuIcn = new JLabel("");
		menuIcn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				menuIcn.setIcon(new ImageIcon(openMenuIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseExited(MouseEvent e) {

				menuIcn.setIcon(new ImageIcon(menuIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				logOutTxt.getTimer().start();
				allottedCorseTxt.getTimer().start();
				proName.getTimer().start();
				dpFrameBig.getTimer().start();
				proPicBig.getTimer().start();
				menuBar.getTimer().start();	
			}
		});
		menuIcn.setBounds(4, 80, 40, 40);
		menuIcn.setIcon(new ImageIcon(menuIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
		add(menuIcn);
		
		JLabel courceIcn = new JLabel("");
		courceIcn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				if (menuBar.getBounds().x == -500) {

					allottedCorseTxt.setBounds(50, 450, 350, 40);
					allottedCorseTxt.setOpaque(true);
					allottedCorseTxt.setBackground(new Color(43, 44, 52));
				}

				courceIcn.setIcon(new ImageIcon(
						openCourseIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseExited(MouseEvent e) {

				if (menuBar.getBounds().x == -500) {

					allottedCorseTxt.setBounds(-450, 450, 350, 40);
					allottedCorseTxt.setOpaque(false);
				}

				courceIcn.setIcon(new ImageIcon(
						courseIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				if (HomePage.getLanguage().equals("bangla")) {

					Locale.setDefault(new Locale("bn", "BN"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					AllottedCoursePage acp = new AllottedCoursePage();
					
					if(LogInPage.getTeacherID() / 1000 == 4) {
						
						acp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from cse_teachers");
						acp.showAllottedCourses("SELECT * from cse_lab_faculty_map inner join cse_lab on cse_lab_faculty_map.Course_No = cse_lab.Course_No inner join cse_teachers on cse_lab_faculty_map.Teacher_ID = cse_teachers.Teacher_ID ");
						
					} else if(LogInPage.getTeacherID() / 1000 == 5) {
							
						acp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from eee_teachers");
						acp.showAllottedCourses("SELECT * from cse_lab_faculty_map inner join cse_lab on cse_lab_faculty_map.Course_No = cse_lab.Course_No inner join eee_teachers on cse_lab_faculty_map.Teacher_ID = eee_teachers.Teacher_ID ");
						
					} else if(LogInPage.getTeacherID() / 1000 == 10) {
						
						acp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from arts_science_teachers");
						acp.showAllottedCourses("SELECT * from cse_lab_faculty_map inner join cse_lab on cse_lab_faculty_map.Course_No = cse_lab.Course_No inner join arts_science_teachers on cse_lab_faculty_map.Teacher_ID = arts_science_teachers.Teacher_ID ");
						
					} else if(LogInPage.getTeacherID() / 1000 == 8) {
						
						acp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from me_teachers");
						acp.showAllottedCourses("SELECT * from cse_lab_faculty_map inner join cse_lab on cse_lab_faculty_map.Course_No = cse_lab.Course_No inner join me_teachers on cse_lab_faculty_map.Teacher_ID = me_teachers.Teacher_ID ");
					}
					
					acp.getSettingsTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					acp.getSettingsTxt().setText(r.getString("settings"));
					
					acp.getLogOutTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					acp.getLogOutTxt().setText(r.getString("logout"));
					
					acp.getCourseTitle().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 35));
					acp.getCourseTitle().setText(r.getString("allotted"));
					
					acp.getCourseTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
					acp.getCourseTxt().setText(r.getString("courseno"));
					
					acp.getPunTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
					acp.getPunTxt().setText(r.getString("puntxt"));
					
					acp.getSecTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
					acp.getSecTxt().setText(r.getString("sec"));
					
					removeAll();
					revalidate();
					repaint();
					add(acp);

				} else {

					Locale.setDefault(new Locale("en", "UK"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					AllottedCoursePage acp = new AllottedCoursePage();
					
					if(LogInPage.getTeacherID() / 1000 == 4) {
						
						acp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from cse_teachers");
						acp.showAllottedCourses("SELECT * from cse_lab_faculty_map inner join cse_lab on cse_lab_faculty_map.Course_No = cse_lab.Course_No inner join cse_teachers on cse_lab_faculty_map.Teacher_ID = cse_teachers.Teacher_ID ");
						
					} else if(LogInPage.getTeacherID() / 1000 == 5) {
							
						acp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from eee_teachers");
						acp.showAllottedCourses("SELECT * from cse_lab_faculty_map inner join cse_lab on cse_lab_faculty_map.Course_No = cse_lab.Course_No inner join eee_teachers on cse_lab_faculty_map.Teacher_ID = eee_teachers.Teacher_ID ");
						
					} else if(LogInPage.getTeacherID() / 1000 == 10) {
						
						acp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from arts_science_teachers");
						acp.showAllottedCourses("SELECT * from cse_lab_faculty_map inner join cse_lab on cse_lab_faculty_map.Course_No = cse_lab.Course_No inner join arts_science_teachers on cse_lab_faculty_map.Teacher_ID = arts_science_teachers.Teacher_ID ");
						
					} else if(LogInPage.getTeacherID() / 1000 == 8) {
						
						acp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from me_teachers");
						acp.showAllottedCourses("SELECT * from cse_lab_faculty_map inner join cse_lab on cse_lab_faculty_map.Course_No = cse_lab.Course_No inner join me_teachers on cse_lab_faculty_map.Teacher_ID = me_teachers.Teacher_ID ");
					}
					
					acp.getSettingsTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					acp.getSettingsTxt().setText(r.getString("settings"));
					
					acp.getLogOutTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					acp.getLogOutTxt().setText(r.getString("logout"));
					
					acp.getCourseTitle().setFont(new Font("Eras Medium ITC", Font.PLAIN, 40));
					acp.getCourseTitle().setText(r.getString("allotted"));
					
					acp.getCourseTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					acp.getCourseTxt().setText(r.getString("courseno"));
					
					acp.getPunTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					acp.getPunTxt().setText(r.getString("puntxt"));
					
					acp.getSecTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					acp.getSecTxt().setText(r.getString("sec"));
					
					removeAll();
					revalidate();
					repaint();
					add(acp);

				}
			}
		});
		courceIcn.setBounds(5, 450, 40, 40);
		courceIcn.setIcon(new ImageIcon(
				courseIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
		add(courceIcn);

		JLabel logOutIcn = new JLabel("");
		logOutIcn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				if (menuBar.getBounds().x == -500) {

					logOutTxt.setBounds(50, 720, 350, 40);
					logOutTxt.setOpaque(true);
					logOutTxt.setBackground(new Color(43, 44, 52));
				}

				logOutIcn.setIcon(new ImageIcon(openLogOutIcon.getScaledInstance(logOutIcn.getWidth(),
						logOutIcn.getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseExited(MouseEvent e) {

				if (menuBar.getBounds().x == -500) {

					logOutTxt.setBounds(-450, 720, 350, 40);
					logOutTxt.setOpaque(false);
				}

				logOutIcn.setIcon(new ImageIcon(
						logOutIcon.getScaledInstance(logOutIcn.getWidth(), logOutIcn.getHeight(), Image.SCALE_SMOOTH)));
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
		logOutIcn.setBounds(4, 720, 40, 40);
		logOutIcn.setIcon(new ImageIcon(
				logOutIcon.getScaledInstance(logOutIcn.getWidth(), logOutIcn.getHeight(), Image.SCALE_SMOOTH)));
		add(logOutIcn);
		
		JLabel sideBar = new JLabel("");
		sideBar.setBackground(new Color(43, 44, 52));
		sideBar.setOpaque(true);
		sideBar.setBounds(0, 60, 50, 740);
		add(sideBar);
		
		dpFrameBig.setBounds(-375, 90, 200, 200);
		dpFrameBig.gimmiBounds();
		dpFrameBig.setIcon(new ImageIcon(dpFrameBigIcon.getScaledInstance(dpFrameBig.getWidth(), dpFrameBig.getHeight(), Image.SCALE_SMOOTH)));
		add(dpFrameBig);
		
		proPicBig.setBounds(-375, 90, 200, 200);
		proPicBig.gimmiBounds();
		proPicBig.setIcon(new ImageIcon(Dp.getScaledInstance(proPicBig.getWidth(), proPicBig.getHeight(), Image.SCALE_SMOOTH)));
		add(proPicBig);
		
		proName.setForeground(new Color(118,185,0));
		proName.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		proName.setHorizontalAlignment(SwingConstants.CENTER);
		proName.setBounds(-450, 291, 350, 60);
		proName.gimmiBounds();
		add(proName);
		
		getAllottedCorseTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getAllottedCorseTxt().setForeground(new Color(118, 185, 0));
		getAllottedCorseTxt().setBounds(-450, 450, 350, 40);
		getAllottedCorseTxt().gimmiBounds();
		add(getAllottedCorseTxt());
		
		getLogOutTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getLogOutTxt().setForeground(new Color(118, 185, 0));
		getLogOutTxt().setBounds(-450, 720, 350, 40);
		getLogOutTxt().gimmiBounds();
		add(getLogOutTxt());
		
		menuBar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
		menuBar.setBackground(new Color(27, 27, 33));
		menuBar.setOpaque(true);
		menuBar.setBounds(-500, 60, 400, 740);
		menuBar.gimmiBounds();
		add(menuBar);
		
		getPassTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getPassTxt().setForeground(new Color(118, 185, 0));
		getPassTxt().setBounds(410, 71, 600, 60);
		add(getPassTxt());
		
		getOldPassTxt().setForeground(new Color(118, 185, 0));
		getOldPassTxt().setBounds(560, 223, 150, 20);
		add(getOldPassTxt());
		
		oldPassField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				oldPassField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				newPassField.setBorder(null);
				conPassField.setBorder(null);
				
				passNoti.setText("");
			}
		});
		oldPassField.setForeground(Color.GRAY);
		oldPassField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		oldPassField.setCaretColor(Color.GRAY);
		oldPassField.setBorder(null);
		oldPassField.setBackground(new Color(52, 52, 61));
		oldPassField.setBounds(560, 254, 300, 30);
		add(oldPassField);
		
		getNewPassTxt().setForeground(new Color(118, 185, 0));
		getNewPassTxt().setBounds(560, 355, 150, 20);
		add(getNewPassTxt());
		newPassField.addKeyListener(new KeyAdapter() {
			
			int flag = 0;
			@Override
			public void keyPressed(KeyEvent e) {
				
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
				
				oldPassField.setBorder(null);
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
		newPassField.setBounds(560, 386, 300, 30);
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
		getChangePassNoti().setBounds(937, 332, 197, 138);
		getChangePassNoti().setVisible(false);
		add(getChangePassNoti());
		
		passNotiIcn.setBounds(897, 386, 30, 30);
		passNotiIcn.setIcon(new ImageIcon(noticeIcon.getScaledInstance(passNotiIcn.getWidth(),
				passNotiIcn.getHeight(), Image.SCALE_SMOOTH)));
		add(passNotiIcn);
		
		passBar.setBorderPainted(false);
		passBar.setBorder(null);
		passBar.setForeground(Color.BLACK);
		passBar.setBackground(new Color(30, 30, 36));
		passBar.setBounds(560, 427, 300, 5);
		add(passBar);
		
		strengthTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 15));
		strengthTxt.setBounds(560, 437, 100, 19);
		add(strengthTxt);
		
		getConPassTxt().setForeground(new Color(118, 185, 0));
		getConPassTxt().setBounds(560, 487, 170, 20);
		add(getConPassTxt());
		
		conPassField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				oldPassField.setBorder(null);
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
		conPassField.setBounds(560, 518, 300, 30);
		add(conPassField);
		
		JLabel chngBtn = new JLabel("");
		chngBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				chngBtn.setIcon(new ImageIcon(btnMouseIn.getScaledInstance(chngBtn.getWidth(), chngBtn.getHeight(), Image.SCALE_SMOOTH)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				chngBtn.setIcon(new ImageIcon(btnDefault.getScaledInstance(chngBtn.getWidth(), chngBtn.getHeight(), Image.SCALE_SMOOTH)));
			}
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(oldPassField.getText().equals(pass) && newPassField.getText().equals(conPassField.getText()) && strengthFlag) {
					
					updatePasswordIntoTeachersTable();
					
					oldPassField.setText("");
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
					
					if(!oldPassField.getText().equals(pass)) {
						
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

						oldPassField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
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
		getPassNoti().setBounds(560, 559, 250, 20);
		add(getPassNoti());
		
		getChngTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getChngTxt().setForeground(Color.WHITE);
		getChngTxt().setBounds(629, 639, 162, 52);
		add(getChngTxt());
		chngBtn.setBounds(629, 639, 162, 52);
		chngBtn.setIcon(new ImageIcon(btnDefault.getScaledInstance(chngBtn.getWidth(), chngBtn.getHeight(), Image.SCALE_SMOOTH)));
		add(chngBtn);
		
	}
	
	public AnimatedLabel getAllottedCorseTxt() {
		return allottedCorseTxt;
	}

	public void setAllottedCorseTxt(AnimatedLabel allottedCorseTxt) {
		this.allottedCorseTxt = allottedCorseTxt;
	}

	public AnimatedLabel getLogOutTxt() {
		return logOutTxt;
	}

	public void setLogOutTxt(AnimatedLabel logOutTxt) {
		this.logOutTxt = logOutTxt;
	}

	public JLabel getPassTxt() {
		return passTxt;
	}

	public void setPassTxt(JLabel passTxt) {
		this.passTxt = passTxt;
	}

	public JLabel getOldPassTxt() {
		return oldPassTxt;
	}

	public void setOldPassTxt(JLabel oldPassTxt) {
		this.oldPassTxt = oldPassTxt;
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
	public void updatePasswordIntoTeachersTable() {

		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			ps = c.prepareStatement("UPDATE cse_teachers SET Pass_word = DES_ENCRYPT(?,'SAM404') WHERE Teacher_name = ?");
			
			ps.setString(1, newPassField.getText());
			ps.setString(2, proName.getText());
			ps.executeUpdate();

		} catch (Exception e) {

			System.out.println(e);
		}
	}
	
	public void readPasswordFromTeachersTable(String query) {

		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;	
		ResultSet rs = null;
		
		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			ps = c.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {

				if (TeacherProfilePage.getProName().getText().equals(rs.getString(1))) {

					pass = rs.getString(2);
					proName.setText(rs.getString(1));
				}
			
			}

		} catch (Exception e) {

			System.out.println(e);
		}
	}
	
	public void updateImageIntoTeachersTable() {

		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			ps = c.prepareStatement("UPDATE cse_teachers SET Pro_Pic = ? WHERE Teacher_name = ?");
			
			InputStream is = new FileInputStream(new File(path));
			
			ps.setBlob(1, is);
			ps.setString(2, proName.getText());
			ps.executeUpdate();

		} catch (Exception e) {

			System.out.println(e);
		}
	}
	
	public void readImagefromTeachersTable() {

		java.sql.Connection c = null;
		java.sql.Statement ps = null;	
		ResultSet rs = null;
		
		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			String query = "select Teacher_name, Pro_Pic from cse_teachers";
			ps = c.createStatement();
			rs = ps.executeQuery(query);

			while (rs.next()) {

				if (TeacherProfilePage.getProName().getText().equals(rs.getString(1))) {
					
					byte[] bImg = rs.getBytes("Pro_Pic");
					
					ImageIcon MyImage = new ImageIcon(bImg);
					Image img = MyImage.getImage();
					Dp = img.getScaledInstance(proPic.getWidth(), proPic.getHeight(), Image.SCALE_SMOOTH);
					imageSmall = new ImageIcon(Dp);
					
					if(imageSmall != null) {
						
						proPic.setIcon(imageSmall);
					} else {
						
						proPic.setIcon(new ImageIcon(Dp.getScaledInstance(proPic.getWidth(), proPic.getHeight(), Image.SCALE_SMOOTH)));
					}
					
					Image Dp1 = img.getScaledInstance(proPicBig.getWidth(), proPicBig.getHeight(), Image.SCALE_SMOOTH);
					imageBig = new ImageIcon(Dp1);
					
					if(imageBig != null) {
						
						proPicBig.setIcon(imageBig);
					} else {
						
						proPicBig.setIcon(new ImageIcon(Dp.getScaledInstance(proPicBig.getWidth(), proPicBig.getHeight(), Image.SCALE_SMOOTH)));
					}
					
				}
			
			}

		} catch (Exception e) {

			System.out.println(e);
		}
	}
}
