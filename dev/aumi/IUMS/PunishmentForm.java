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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class PunishmentForm extends JPanel {

	private Image btnReturnImg = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
	private Image btnReturnImgMouseIn = new ImageIcon(this.getClass().getResource("/return(mouse in).png")).getImage();
	
	private Image btnDefault = new ImageIcon(this.getClass().getResource("/btn layout.png")).getImage();
	private Image btnMouseIn = new ImageIcon(this.getClass().getResource("/btn layout(mouse in).png")).getImage();
	
	private Image dpFrameIcon = new ImageIcon(this.getClass().getResource("/dp.png")).getImage();
	private Image dpFrameBigIcon = new ImageIcon(this.getClass().getResource("/dpbig.png")).getImage();
	private Image Dp = new ImageIcon(this.getClass().getResource("/tempdp.png")).getImage();
	private Image notiNull = new ImageIcon(this.getClass().getResource("/notification null.png")).getImage();
	private Image menuIcon = new ImageIcon(this.getClass().getResource("/menu.png")).getImage();
	private Image openMenuIcon = new ImageIcon(this.getClass().getResource("/openmenu.png")).getImage();
	private Image settingsIcon = new ImageIcon(this.getClass().getResource("/settings.png")).getImage();
	private Image openSettingsIcon = new ImageIcon(this.getClass().getResource("/opensettings.png")).getImage();
	private Image logOutIcon = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
	private Image openLogOutIcon = new ImageIcon(this.getClass().getResource("/openlogout.png")).getImage();
	
	private JLabel proPic = new JLabel("");
	private JLabel punTitle = new JLabel("Punishment Form");
	private JLabel stdIDTxt = new JLabel("Student's ID");
	private JLabel dateTxt = new JLabel("Occurrence Date");
	private JLabel offenceTxt = new JLabel("Offence");
	private JLabel remarksTxt = new JLabel("Remarks");
	private JLabel submitTxt = new JLabel("Submit");
	private JLabel punNoti = new JLabel("");
	
	private ImageIcon imageSmall; 
	private ImageIcon imageBig;
	
	private String path;
	
	private JTextField stdIDField;
	private JTextField day;
	private JTextField month;
	private JTextField year;
	private JTextField remarksField;
	
	private JTextArea offenceArea = new JTextArea();
	
	private AnimatedLabel menuBar = new AnimatedLabel("", 11);
	private AnimatedLabel dpFrameBig = new AnimatedLabel("", 12);
	private AnimatedLabel proPicBig = new AnimatedLabel("", 12);
	private AnimatedLabel proName = new AnimatedLabel("", 13);
	private AnimatedLabel allottedCorseTxt = new AnimatedLabel("Allotted Courses", 13);
	private AnimatedLabel settingsTxt = new AnimatedLabel("Settings", 13);
	private AnimatedLabel logOutTxt = new AnimatedLabel("Log Out", 13);
	
	/**
	 * Create the panel.
	 */
	public PunishmentForm() {

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

					removeAll();
					revalidate();
					repaint();
					add(acp);

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
				settingsTxt.getTimer().start();
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
		
		JLabel settingsIcn = new JLabel("");
		settingsIcn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				if (menuBar.getBounds().x == -500) {
					
					getSettingsTxt().setBounds(50, 630, 350, 40);
					getSettingsTxt().setOpaque(true);
					getSettingsTxt().setBackground(new Color(43, 44, 52));
				}

				settingsIcn.setIcon(new ImageIcon(openSettingsIcon.getScaledInstance(settingsIcn.getWidth(), settingsIcn.getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
				if (menuBar.getBounds().x == -500) {
					
					getSettingsTxt().setBounds(-450, 630, 350, 40);
					getSettingsTxt().setOpaque(false);
				}

				settingsIcn.setIcon(new ImageIcon(settingsIcon.getScaledInstance(settingsIcn.getWidth(), settingsIcn.getHeight(), Image.SCALE_SMOOTH)));
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
						
					}  else if(LogInPage.getTeacherID() / 1000 == 10) {
						
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
		settingsIcn.setBounds(4, 630, 40, 40);
		settingsIcn.setIcon(new ImageIcon(settingsIcon.getScaledInstance(settingsIcn.getWidth(), settingsIcn.getHeight(), Image.SCALE_SMOOTH)));
		add(settingsIcn);

		JLabel logOutIcn = new JLabel("");
		logOutIcn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				if (menuBar.getBounds().x == -500) {
					
					getLogOutTxt().setBounds(50, 720, 350, 40);
					getLogOutTxt().setOpaque(true);
					getLogOutTxt().setBackground(new Color(43, 44, 52));
				}

				logOutIcn.setIcon(new ImageIcon(openLogOutIcon.getScaledInstance(logOutIcn.getWidth(), logOutIcn.getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
				if (menuBar.getBounds().x == -500) {
					
					getLogOutTxt().setBounds(-450, 720, 350, 40);
					getLogOutTxt().setOpaque(false);
				}

				logOutIcn.setIcon(new ImageIcon(logOutIcon.getScaledInstance(logOutIcn.getWidth(), logOutIcn.getHeight(), Image.SCALE_SMOOTH)));
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
		logOutIcn.setIcon(new ImageIcon(logOutIcon.getScaledInstance(logOutIcn.getWidth(), logOutIcn.getHeight(), Image.SCALE_SMOOTH)));
		add(logOutIcn);
		
		JLabel sideBar = new JLabel("");
		sideBar.setBackground(new Color(43, 44, 52));
		sideBar.setOpaque(true);
		sideBar.setBounds(0, 60, 50, 740);
		add(sideBar);
		
		stdIDField = new JTextField();
		stdIDField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				stdIDField.setText("");
				punNoti.setText("");
				
				stdIDField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				day.setBorder(null);
				month.setBorder(null);
				year.setBorder(null);
				remarksField.setBorder(null);
				offenceArea.setBorder(null);
			}
		});
		
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
		proName.setText(TeacherProfilePage.getProName().getText());
		proName.gimmiBounds();
		add(proName);
		
		allottedCorseTxt.setHorizontalAlignment(SwingConstants.CENTER);
		allottedCorseTxt.setForeground(new Color(118, 185, 0));
		allottedCorseTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
		allottedCorseTxt.setBounds(-450, 450, 350, 40);
		allottedCorseTxt.gimmiBounds();
		add(allottedCorseTxt);
		
		settingsTxt.setHorizontalAlignment(SwingConstants.CENTER);
		settingsTxt.setForeground(new Color(118, 185, 0));
		settingsTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
		settingsTxt.setBounds(-450, 630, 350, 40);
		settingsTxt.gimmiBounds();
		add(settingsTxt);
		
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
		
		getPunTitle().setForeground(new Color(118, 185, 0));
		getPunTitle().setHorizontalAlignment(SwingConstants.CENTER);
		getPunTitle().setBounds(510, 70, 400, 40);
		add(getPunTitle());
		
		stdIDField.setHorizontalAlignment(SwingConstants.CENTER);
		stdIDField.setText("XX.XX.XX.XXX");
		stdIDField.setForeground(Color.GRAY);
		stdIDField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		stdIDField.setBorder(null);
		stdIDField.setCaretColor(Color.GRAY);
		stdIDField.setBackground(new Color(52, 52, 61));
		stdIDField.setBounds(150, 180, 300, 30);
		add(stdIDField);
		stdIDField.setColumns(10);
		
		getStdIDTxt().setForeground(new Color(118, 185, 0));
		getStdIDTxt().setBounds(150, 155, 150, 20);
		add(getStdIDTxt());
		
		getDateTxt().setForeground(new Color(118, 185, 0));
		getDateTxt().setBounds(150, 310, 160, 20);
		add(getDateTxt());
		
		day = new JTextField();
		day.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				day.setText("");
				punNoti.setText("");
				
				stdIDField.setBorder(null);
				day.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				month.setBorder(null);
				year.setBorder(null);
				remarksField.setBorder(null);
				offenceArea.setBorder(null);
			}
		});
		day.setHorizontalAlignment(SwingConstants.CENTER);
		day.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		day.setText("DD");
		day.setCaretColor(Color.GRAY);
		day.setForeground(Color.GRAY);
		day.setBorder(null);
		day.setBackground(new Color(52, 52, 61));
		day.setBounds(150, 340, 50, 30);
		add(day);
		day.setColumns(10);
		
		month = new JTextField();
		month.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				month.setText("");
				punNoti.setText("");
				
				stdIDField.setBorder(null);
				day.setBorder(null);
				month.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				year.setBorder(null);
				remarksField.setBorder(null);
				offenceArea.setBorder(null);
			}
		});
		month.setText("MM");
		month.setHorizontalAlignment(SwingConstants.CENTER);
		month.setCaretColor(Color.GRAY);
		month.setForeground(Color.GRAY);
		month.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		month.setColumns(10);
		month.setBorder(null);
		month.setBackground(new Color(52, 52, 61));
		month.setBounds(240, 340, 50, 30);
		add(month);
		
		year = new JTextField();
		year.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				year.setText("");
				punNoti.setText("");
				
				stdIDField.setBorder(null);
				day.setBorder(null);
				month.setBorder(null);
				year.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				remarksField.setBorder(null);
				offenceArea.setBorder(null);
			}
		});
		year.setText("YYYY");
		year.setHorizontalAlignment(SwingConstants.CENTER);
		year.setCaretColor(Color.GRAY);
		year.setForeground(Color.GRAY);
		year.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		year.setColumns(10);
		year.setBorder(null);
		year.setBackground(new Color(52, 52, 61));
		year.setBounds(330, 340, 70, 30);
		add(year);
		
		getOffenceTxt().setForeground(new Color(118, 185, 0));
		getOffenceTxt().setBounds(950, 310, 150, 20);
		add(getOffenceTxt());
		offenceArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				punNoti.setText("");
				
				stdIDField.setBorder(null);
				day.setBorder(null);
				month.setBorder(null);
				year.setBorder(null);
				remarksField.setBorder(null);
				offenceArea.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
			}
		});
		
		offenceArea.setBorder(null);
		offenceArea.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		offenceArea.setCaretColor(Color.GRAY);
		offenceArea.setForeground(Color.GRAY);
		offenceArea.setLineWrap(true);
		offenceArea.setWrapStyleWord(true);
		offenceArea.setBackground(new Color(52, 52, 61));
		offenceArea.setBounds(950, 340, 400, 200);
		add(offenceArea);
		
		getRemarksTxt().setForeground(new Color(118, 185, 0));
		getRemarksTxt().setBounds(150, 479, 150, 20);
		add(getRemarksTxt());
		
		remarksField = new JTextField();
		remarksField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				punNoti.setText("");
				
				stdIDField.setBorder(null);
				day.setBorder(null);
				month.setBorder(null);
				year.setBorder(null);
				remarksField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				offenceArea.setBorder(null);
			}
		});
		remarksField.setForeground(Color.GRAY);
		remarksField.setCaretColor(Color.GRAY);
		remarksField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		remarksField.setColumns(10);
		remarksField.setBorder(null);
		remarksField.setBackground(new Color(52, 52, 61));
		remarksField.setBounds(150, 510, 300, 30);
		add(remarksField);
		
		JLabel submitBtn = new JLabel("");
		submitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				submitBtn.setIcon(new ImageIcon(btnMouseIn.getScaledInstance(submitBtn.getWidth(), submitBtn.getHeight(), Image.SCALE_SMOOTH)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				submitBtn.setIcon(new ImageIcon(btnDefault.getScaledInstance(submitBtn.getWidth(), submitBtn.getHeight(), Image.SCALE_SMOOTH)));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				if(stdIDField.getText().equals("XX.XX.XX.XXX") || stdIDField.getText().equals("")) {
					
					stdIDField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
					
					if (HomePage.getLanguage().equals("bangla")) {

						Locale.setDefault(new Locale("bn", "BN"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						punNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
						punNoti.setText(r.getString("invalid"));

					} else {

						Locale.setDefault(new Locale("en", "UK"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						punNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
						punNoti.setText(r.getString("invalid"));
					}
				}
				
				if(day.getText().equals("DD") || day.getText().equals("")) {
				
					day.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
					
					if (HomePage.getLanguage().equals("bangla")) {

						Locale.setDefault(new Locale("bn", "BN"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						punNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
						punNoti.setText(r.getString("invalid"));

					} else {

						Locale.setDefault(new Locale("en", "UK"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						punNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
						punNoti.setText(r.getString("invalid"));
					}
				}
				
				if(month.getText().equals("MM") || month.getText().equals("")) {
					
					month.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
					
					if (HomePage.getLanguage().equals("bangla")) {

						Locale.setDefault(new Locale("bn", "BN"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						punNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
						punNoti.setText(r.getString("invalid"));

					} else {

						Locale.setDefault(new Locale("en", "UK"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						punNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
						punNoti.setText(r.getString("invalid"));
					}
				}

				if(year.getText().equals("YYYY") || year.getText().equals("")) {
	
					year.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
					
					if (HomePage.getLanguage().equals("bangla")) {

						Locale.setDefault(new Locale("bn", "BN"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						punNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
						punNoti.setText(r.getString("invalid"));

					} else {

						Locale.setDefault(new Locale("en", "UK"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						punNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
						punNoti.setText(r.getString("invalid"));
					}
				}
				
				if(remarksField.getText().equals("")) {
					
					remarksField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
					
					if (HomePage.getLanguage().equals("bangla")) {

						Locale.setDefault(new Locale("bn", "BN"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						punNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
						punNoti.setText(r.getString("invalid"));

					} else {

						Locale.setDefault(new Locale("en", "UK"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						punNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
						punNoti.setText(r.getString("invalid"));
					}
				}
				
				if(offenceArea.getText().equals("")) {
					
					offenceArea.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
					
					if (HomePage.getLanguage().equals("bangla")) {

						Locale.setDefault(new Locale("bn", "BN"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						punNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
						punNoti.setText(r.getString("invalid"));

					} else {

						Locale.setDefault(new Locale("en", "UK"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						punNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
						punNoti.setText(r.getString("invalid"));
					}
				}
				
				if(!(stdIDField.getText().equals("XX.XX.XX.XXX") || stdIDField.getText().equals("")) && !(day.getText().equals("DD") || day.getText().equals("")) && !(month.getText().equals("MM") || month.getText().equals("")) && !(year.getText().equals("YYYY") || year.getText().equals("")) && !remarksField.getText().equals("") && !offenceArea.getText().equals("")) {
					
					createPunishmentForm();
					
				}
			}
		});
		
		getSubmitTxt().setForeground(Color.WHITE);
		getSubmitTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getSubmitTxt().setBounds(1069, 680, 162, 52);
		add(getSubmitTxt());
		submitBtn.setBounds(1069, 680, 162, 52);
		submitBtn.setIcon(new ImageIcon(btnDefault.getScaledInstance(submitBtn.getWidth(), submitBtn.getHeight(), Image.SCALE_SMOOTH)));
		add(submitBtn);
		
		punNoti.setHorizontalAlignment(SwingConstants.CENTER);
		punNoti.setForeground(Color.RED);
		punNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
		punNoti.setBounds(748, 692, 300, 25);
		add(punNoti);
		
	}
	
	public AnimatedLabel getAllottedCorseTxt() {
		return allottedCorseTxt;
	}

	public void setAllottedCorseTxt(AnimatedLabel allottedCorseTxt) {
		this.allottedCorseTxt = allottedCorseTxt;
	}

	public AnimatedLabel getSettingsTxt() {
		return settingsTxt;
	}

	public void setSettingsTxt(AnimatedLabel settingsTxt) {
		this.settingsTxt = settingsTxt;
	}

	public AnimatedLabel getLogOutTxt() {
		return logOutTxt;
	}

	public void setLogOutTxt(AnimatedLabel logOutTxt) {
		this.logOutTxt = logOutTxt;
	}

	public JLabel getPunTitle() {
		return punTitle;
	}

	public void setPunTitle(JLabel punTitle) {
		this.punTitle = punTitle;
	}

	public JLabel getStdIDTxt() {
		return stdIDTxt;
	}

	public void setStdIDTxt(JLabel stdIDTxt) {
		this.stdIDTxt = stdIDTxt;
	}

	public JLabel getDateTxt() {
		return dateTxt;
	}

	public void setDateTxt(JLabel dateTxt) {
		this.dateTxt = dateTxt;
	}

	public JLabel getRemarksTxt() {
		return remarksTxt;
	}

	public void setRemarksTxt(JLabel remarksTxt) {
		this.remarksTxt = remarksTxt;
	}

	public JLabel getOffenceTxt() {
		return offenceTxt;
	}

	public void setOffenceTxt(JLabel offenceTxt) {
		this.offenceTxt = offenceTxt;
	}

	public JLabel getSubmitTxt() {
		return submitTxt;
	}

	public void setSubmitTxt(JLabel submitTxt) {
		this.submitTxt = submitTxt;
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
	
	public void createPunishmentForm() {

		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			ps = c.prepareStatement("insert into punishment_table(ID, Course_no, offence, Offence_Date, remarks, Submitted_Date, submitted_by) values(?, ?, ?, ?, ?, ?, ?)");
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
			LocalDateTime now = LocalDateTime.now();  
			
			if(isStudentValid("select ID, Section from cse_students")) {
				
				ps.setString(1, stdIDField.getText());
				ps.setString(2, AllottedCoursePage.getCourseField().getText() + " " + AllottedCoursePage.getSecField().getText());
				ps.setString(3, offenceArea.getText());
				ps.setString(4, day.getText() + "-" + month.getText() + "-" + year.getText());
				ps.setString(5, remarksField.getText());
				ps.setString(6, dtf.format(now));
				ps.setString(7, TeacherProfilePage.getProName().getText());
				ps.executeUpdate();
				
				stdIDField.setText("XX.XX.XX.XXX");
				day.setText("DD");
				month.setText("MM");
				year.setText("YYYY");
				remarksField.setText("");
				offenceArea.setText("");
				
				if (HomePage.getLanguage().equals("bangla")) {

					Locale.setDefault(new Locale("bn", "BN"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					punNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
					punNoti.setForeground(Color.GREEN);
					punNoti.setText(r.getString("successful"));

				} else {

					Locale.setDefault(new Locale("en", "UK"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					punNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					punNoti.setForeground(Color.GREEN);
					punNoti.setText(r.getString("successful"));
				}
				
			} else {
				
				stdIDField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
				
				if (HomePage.getLanguage().equals("bangla")) {

					Locale.setDefault(new Locale("bn", "BN"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					punNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
					punNoti.setText(r.getString("invalid"));

				} else {

					Locale.setDefault(new Locale("en", "UK"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					punNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					punNoti.setText(r.getString("invalid"));
				}
			}	

		} catch (Exception e) {

			System.out.println(e);
		}
	}
	
	public boolean isStudentValid(String query) {

		java.sql.Connection c = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			java.sql.Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) {

				if (stdIDField.getText().equals(rs.getString(1)) && AllottedCoursePage.getSecField().getText().equals(rs.getString(2))) {

					return true;
				}
			}
			
			return false;

		} catch (Exception e) {

			System.out.println(e);
			return false;
		}
	}
}
