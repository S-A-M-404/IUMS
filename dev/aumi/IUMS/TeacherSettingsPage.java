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

@SuppressWarnings("serial")
public class TeacherSettingsPage extends JPanel {

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

	private JLabel proPic = new JLabel("");
	private JLabel lblSettings = new JLabel("Settings");
	private JLabel editTxt = new JLabel("Edit Personal Information");
	private JLabel lblChangePassword = new JLabel("Change Password");
	
	private ImageIcon imageSmall; 
	private ImageIcon imageBig;

	private AnimatedLabel menuBar = new AnimatedLabel("", 11);
	private AnimatedLabel dpFrameBig = new AnimatedLabel("", 12);
	private AnimatedLabel proPicBig = new AnimatedLabel("", 12);
	private AnimatedLabel proName = new AnimatedLabel("", 13);
	private AnimatedLabel allottedCorseTxt = new AnimatedLabel("Allotted Courses", 13);
	private AnimatedLabel logOutTxt = new AnimatedLabel("Log Out", 13);

	private String path;

	/**
	 * Create the panel.
	 */
	public TeacherSettingsPage() {

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

					TeacherProfilePage tpp = new TeacherProfilePage();
					
					if(LogInPage.getTeacherID() / 1000 == 4) {
						
						tpp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from cse_teachers");
						
					} else if(LogInPage.getTeacherID() / 1000 == 5) {
							
						tpp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from eee_teachers");
						
					} else if(LogInPage.getTeacherID() / 1000 == 10) {
						
						tpp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from arts_science_teachers");
						
					}  else if(LogInPage.getTeacherID() / 1000 == 8) {
						
						tpp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from me_teachers");
					}

					tpp.getAllottedCorseTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					tpp.getAllottedCorseTxt().setText(r.getString("allotted"));
					
					tpp.getSettingsTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					tpp.getSettingsTxt().setText(r.getString("settings"));
					
					tpp.getLogOutTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					tpp.getLogOutTxt().setText(r.getString("logout"));

					removeAll();
					revalidate();
					repaint();
					add(tpp);

				} else {

					Locale.setDefault(new Locale("en", "UK"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					TeacherProfilePage tpp = new TeacherProfilePage();
					
					if(LogInPage.getTeacherID() / 1000 == 4) {
						
						tpp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from cse_teachers");
						
					} else if(LogInPage.getTeacherID() / 1000 == 5) {
							
						tpp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from eee_teachers");
						
					} else if(LogInPage.getTeacherID() / 1000 == 10) {
						
						tpp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from arts_science_teachers");
						
					} else if(LogInPage.getTeacherID() / 1000 == 8) {
						
						tpp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from me_teachers");
					}

					tpp.getAllottedCorseTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					tpp.getAllottedCorseTxt().setText(r.getString("allotted"));
					
					tpp.getSettingsTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					tpp.getSettingsTxt().setText(r.getString("settings"));
					
					tpp.getLogOutTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					tpp.getLogOutTxt().setText(r.getString("logout"));

					removeAll();
					revalidate();
					repaint();
					add(tpp);

				}
			}
		});
		btnReturn.setBounds(new Rectangle(15, 31, 28, 28));
		Image btnReturnImgScaled = btnReturnImg.getScaledInstance(btnReturn.getWidth(), btnReturn.getHeight(),
				Image.SCALE_SMOOTH);
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
					
					if(LogInPage.getTeacherID() / 1000 == 4) {
						
						updateImageIntoTeachersTable("UPDATE cse_teachers SET Pro_Pic = ? WHERE Teacher_ID = ?");		
						readImagefromTeachersTable("select Teacher_ID, Pro_Pic from cse_teachers");
						
					} else if(LogInPage.getTeacherID() / 1000 == 5) {
						
						updateImageIntoTeachersTable("UPDATE eee_teachers SET Pro_Pic = ? WHERE Teacher_ID = ?");		
						readImagefromTeachersTable("select Teacher_ID, Pro_Pic from eee_teachers");
						
					} else if(LogInPage.getTeacherID() / 1000 == 10) {
						
						updateImageIntoTeachersTable("UPDATE arts_science_teachers SET Pro_Pic = ? WHERE Teacher_ID = ?");	
						readImagefromTeachersTable("select Teacher_ID, Pro_Pic from arts_science_teachers");
						
					} else if(LogInPage.getTeacherID() / 1000 == 8) {
						
						updateImageIntoTeachersTable("UPDATE me_teachers SET Pro_Pic = ? WHERE Teacher_ID = ?");
						readImagefromTeachersTable("select Teacher_ID, Pro_Pic from me_teachers");
					}
					
				}

			}
		});
		dpFrame.setBounds(1290, 30, 30, 30);
		dpFrame.setIcon(new ImageIcon(
				dpFrameIcon.getScaledInstance(dpFrame.getWidth(), dpFrame.getHeight(), Image.SCALE_SMOOTH)));
		add(dpFrame);

		proPic.setBounds(1292, 30, 25, 25);
		proPic.setIcon(new ImageIcon(Dp.getScaledInstance(proPic.getWidth(), proPic.getHeight(), Image.SCALE_SMOOTH)));
		add(proPic);

		JLabel notiIcon = new JLabel("");
		notiIcon.setBounds(1345, 32, 25, 25);
		notiIcon.setIcon(new ImageIcon(
				notiNull.getScaledInstance(notiIcon.getWidth(), notiIcon.getHeight(), Image.SCALE_SMOOTH)));
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

				menuIcn.setIcon(new ImageIcon(
						openMenuIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseExited(MouseEvent e) {

				menuIcn.setIcon(new ImageIcon(
						menuIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
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
		menuIcn.setIcon(
				new ImageIcon(menuIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
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
		dpFrameBig.setIcon(new ImageIcon(
				dpFrameBigIcon.getScaledInstance(dpFrameBig.getWidth(), dpFrameBig.getHeight(), Image.SCALE_SMOOTH)));
		add(dpFrameBig);

		proPicBig.setBounds(-375, 90, 200, 200);
		proPicBig.gimmiBounds();
		proPicBig.setIcon(
				new ImageIcon(Dp.getScaledInstance(proPicBig.getWidth(), proPicBig.getHeight(), Image.SCALE_SMOOTH)));
		add(proPicBig);

		proName.setForeground(new Color(118, 185, 0));
		proName.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		proName.setHorizontalAlignment(SwingConstants.CENTER);
		proName.setBounds(-450, 291, 350, 60);
		proName.setText(TeacherProfilePage.getProName().getText());
		proName.gimmiBounds();
		add(proName);

		allottedCorseTxt.setHorizontalAlignment(SwingConstants.CENTER);
		allottedCorseTxt.setForeground(new Color(118, 185, 0));
		allottedCorseTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
		allottedCorseTxt.setBounds(-450, 450, 350, 40);
		allottedCorseTxt.gimmiBounds();
		add(allottedCorseTxt);

		logOutTxt.setHorizontalAlignment(SwingConstants.CENTER);
		logOutTxt.setForeground(new Color(118, 185, 0));
		logOutTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
		logOutTxt.setBounds(-450, 720, 350, 40);
		logOutTxt.gimmiBounds();
		add(logOutTxt);

		menuBar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
		menuBar.setBackground(new Color(27, 27, 33));
		menuBar.setOpaque(true);
		menuBar.setBounds(-500, 60, 400, 740);
		menuBar.gimmiBounds();
		add(menuBar);

		JLabel editPersonalBtn = new JLabel("");
		editPersonalBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				editPersonalBtn.setIcon(new ImageIcon(btnMouseIn.getScaledInstance(editPersonalBtn.getWidth(),
						editPersonalBtn.getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseExited(MouseEvent e) {

				editPersonalBtn.setIcon(new ImageIcon(btnDefault.getScaledInstance(editPersonalBtn.getWidth(),
						editPersonalBtn.getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				if (HomePage.getLanguage().equals("bangla")) {

					Locale.setDefault(new Locale("bn", "BN"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					TeacherPersonalInfo tfi = new TeacherPersonalInfo();
					
					tfi.readImagefromTeachersTable();

					tfi.getAllottedCorseTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					tfi.getAllottedCorseTxt().setText(r.getString("allotted"));
					
					tfi.getLogOutTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					tfi.getLogOutTxt().setText(r.getString("logout"));

					tfi.getPersonalTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 35));
					tfi.getPersonalTxt().setText(r.getString("edittxt"));
					
					tfi.getSignTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
					tfi.getSignTxt().setText(r.getString("signature"));
					
					tfi.getEmailTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
					tfi.getEmailTxt().setText(r.getString("Email"));
					
					tfi.getContactTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
					tfi.getContactTxt().setText(r.getString("contact"));
					
					tfi.getGenderTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
					tfi.getGenderTxt().setText(r.getString("gender"));
					
					tfi.getBloodTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
					tfi.getBloodTxt().setText(r.getString("blood"));
					
					tfi.getReligionTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
					tfi.getReligionTxt().setText(r.getString("religion"));
					
					tfi.getAddressTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
					tfi.getAddressTxt().setText(r.getString("address"));
					
					tfi.getDesignationTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
					tfi.getDesignationTxt().setText(r.getString("designation"));
					
					tfi.getEditTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
					tfi.getEditTxt().setText(r.getString("edit"));
					
					removeAll();
					revalidate();
					repaint();
					add(tfi);

				} else {

					Locale.setDefault(new Locale("en", "UK"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					TeacherPersonalInfo tfi = new TeacherPersonalInfo();
					
					tfi.readImagefromTeachersTable();

					tfi.getAllottedCorseTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					tfi.getAllottedCorseTxt().setText(r.getString("allotted"));
					
					tfi.getLogOutTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					tfi.getLogOutTxt().setText(r.getString("logout"));
					
					tfi.getPersonalTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 40));
					tfi.getPersonalTxt().setText(r.getString("edittxt"));
					
					tfi.getSignTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					tfi.getSignTxt().setText(r.getString("signature"));
					
					tfi.getEmailTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					tfi.getEmailTxt().setText(r.getString("Email"));
					
					tfi.getContactTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					tfi.getContactTxt().setText(r.getString("contact"));
					
					tfi.getGenderTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					tfi.getGenderTxt().setText(r.getString("gender"));
					
					tfi.getBloodTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					tfi.getBloodTxt().setText(r.getString("blood"));
					
					tfi.getReligionTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					tfi.getReligionTxt().setText(r.getString("religion"));
					
					tfi.getAddressTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					tfi.getAddressTxt().setText(r.getString("address"));
					
					tfi.getDesignationTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					tfi.getDesignationTxt().setText(r.getString("designation"));
					
					tfi.getEditTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 28));
					tfi.getEditTxt().setText(r.getString("edit"));
					
					removeAll();
					revalidate();
					repaint();
					add(tfi);

				}

			}
		});

		getEditTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getEditTxt().setForeground(Color.WHITE);
		getEditTxt().setBounds(589, 300, 242, 60);
		add(getEditTxt());

		editPersonalBtn.setBounds(589, 300, 242, 60);
		editPersonalBtn.setIcon(new ImageIcon(btnDefault.getScaledInstance(editPersonalBtn.getWidth(),
				editPersonalBtn.getHeight(), Image.SCALE_SMOOTH)));
		add(editPersonalBtn);

		JLabel changePassBtn = new JLabel("");
		changePassBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				changePassBtn.setIcon(new ImageIcon(btnMouseIn.getScaledInstance(editPersonalBtn.getWidth(),
						editPersonalBtn.getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseExited(MouseEvent e) {

				changePassBtn.setIcon(new ImageIcon(btnDefault.getScaledInstance(editPersonalBtn.getWidth(),
						editPersonalBtn.getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (HomePage.getLanguage().equals("bangla")) {

					Locale.setDefault(new Locale("bn", "BN"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					TeacherChangePass tcp = new TeacherChangePass();
					
					tcp.readImagefromTeachersTable();

					tcp.getAllottedCorseTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					tcp.getAllottedCorseTxt().setText(r.getString("allotted"));
					
					tcp.getLogOutTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					tcp.getLogOutTxt().setText(r.getString("logout"));
					
					tcp.getPassTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 35));
					tcp.getPassTxt().setText(r.getString("changepass"));
					
					tcp.getOldPassTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
					tcp.getOldPassTxt().setText(r.getString("oldpass"));
					
					tcp.getNewPassTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
					tcp.getNewPassTxt().setText(r.getString("newpass"));
					
					tcp.getConPassTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
					tcp.getConPassTxt().setText(r.getString("conPass"));
					
					tcp.getChngTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					tcp.getChngTxt().setText(r.getString("change"));
					
					tcp.getChangePassNoti().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
					tcp.getChangePassNoti().setText("<html>" + r.getString("regpassnoti") + "</html>");

					removeAll();
					revalidate();
					repaint();
					add(tcp);

				} else {

					Locale.setDefault(new Locale("en", "UK"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					TeacherChangePass tcp = new TeacherChangePass();
					
					tcp.readImagefromTeachersTable();

					tcp.getAllottedCorseTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					tcp.getAllottedCorseTxt().setText(r.getString("allotted"));
					
					tcp.getLogOutTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					tcp.getLogOutTxt().setText(r.getString("logout"));
					
					tcp.getPassTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 40));
					tcp.getPassTxt().setText(r.getString("changepass"));

					tcp.getOldPassTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					tcp.getOldPassTxt().setText(r.getString("oldpass"));
					
					tcp.getNewPassTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					tcp.getNewPassTxt().setText(r.getString("newpass"));
					
					tcp.getConPassTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					tcp.getConPassTxt().setText(r.getString("conPass"));
					
					tcp.getChngTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 28));
					tcp.getChngTxt().setText(r.getString("change"));
					
					tcp.getChangePassNoti().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					tcp.getChangePassNoti().setText("<html>" + r.getString("regpassnoti") + "</html>");
					
					removeAll();
					revalidate();
					repaint();
					add(tcp);

				}

			}
		});

		getLblChangePassword().setForeground(Color.WHITE);
		getLblChangePassword().setHorizontalAlignment(SwingConstants.CENTER);
		getLblChangePassword().setBounds(589, 500, 242, 60);
		add(getLblChangePassword());
		changePassBtn.setBounds(589, 500, 242, 60);
		changePassBtn.setIcon(new ImageIcon(btnDefault.getScaledInstance(editPersonalBtn.getWidth(),
				editPersonalBtn.getHeight(), Image.SCALE_SMOOTH)));
		add(changePassBtn);

		getLblSettings().setHorizontalAlignment(SwingConstants.CENTER);
		getLblSettings().setForeground(new Color(118, 185, 0));
		getLblSettings().setBounds(410, 71, 600, 60);
		add(getLblSettings());

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

	public JLabel getLblSettings() {
		return lblSettings;
	}

	public void setLblSettings(JLabel lblSettings) {
		this.lblSettings = lblSettings;
	}

	public JLabel getEditTxt() {
		return editTxt;
	}

	public void setEditTxt(JLabel editTxt) {
		this.editTxt = editTxt;
	}

	public JLabel getLblChangePassword() {
		return lblChangePassword;
	}

	public void setLblChangePassword(JLabel lblChangePassword) {
		this.lblChangePassword = lblChangePassword;
	}
	
	public void updateImageIntoTeachersTable(String query) {

		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			ps = c.prepareStatement(query);
			
			InputStream is = new FileInputStream(new File(path));
			
			ps.setBlob(1, is);
			ps.setString(2, LogInPage.getLogInID().getText());
			ps.executeUpdate();

		} catch (Exception e) {

			System.out.println(e);
		}
	}
	
	public void readImagefromTeachersTable(String query) {

		java.sql.Connection c = null;
		java.sql.Statement ps = null;	
		ResultSet rs = null;
		
		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			ps = c.createStatement();
			rs = ps.executeQuery(query);

			while (rs.next()) {

				if (LogInPage.getLogInID().getText().equals(rs.getString(1))) {
					
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
