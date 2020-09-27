package dev.aumi.IUMS;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TeacherProfilePage extends JPanel {
	
	private Image dpFrameIcon = new ImageIcon(this.getClass().getResource("/dp.png")).getImage();
	private Image Dp = new ImageIcon(this.getClass().getResource("/tempdp.png")).getImage();
	private Image dpFrameBigIcon = new ImageIcon(this.getClass().getResource("/dpbig.png")).getImage();
	private Image notiNull = new ImageIcon(this.getClass().getResource("/notification null.png")).getImage();
	private Image menuIcon = new ImageIcon(this.getClass().getResource("/menu.png")).getImage();
	private Image openMenuIcon = new ImageIcon(this.getClass().getResource("/openmenu.png")).getImage();
	private Image courseIcon = new ImageIcon(this.getClass().getResource("/result.png")).getImage();
	private Image openCourseIcon = new ImageIcon(this.getClass().getResource("/openresult.png")).getImage();
	private Image settingsIcon = new ImageIcon(this.getClass().getResource("/settings.png")).getImage();
	private Image openSettingsIcon = new ImageIcon(this.getClass().getResource("/opensettings.png")).getImage();
	private Image logOutIcon = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
	private Image openLogOutIcon = new ImageIcon(this.getClass().getResource("/openlogout.png")).getImage();
	
	private ImageIcon imageSmall; 
	private ImageIcon imageBig;
	
	private JLabel proPic = new JLabel("");
	private JLabel txt = new JLabel("Welcome To Your Profile");
	private JLabel nameTxt = new JLabel("Name :");
	private JLabel idTxt = new JLabel("ID :");
	private JLabel semTxt = new JLabel("Designation :");
	private JLabel secTxt = new JLabel("Contact No :");
	
	private AnimatedLabel menuBar = new AnimatedLabel("", 11);
	private AnimatedLabel dpFrameBig = new AnimatedLabel("", 12);
	private AnimatedLabel proPicBig = new AnimatedLabel("", 12);
	private static AnimatedLabel proName = new AnimatedLabel("", 13);
	private AnimatedLabel allottedCorseTxt = new AnimatedLabel("Allotted Courses", 13);
	private AnimatedLabel settingsTxt = new AnimatedLabel("Settings", 13);
	private AnimatedLabel logOutTxt = new AnimatedLabel("Log Out", 13);
	
	private String path;
	private JTextField NameField = new JTextField();
	private JTextField idField = new JTextField();
	private JTextField desField = new JTextField();
	private JTextField contactField = new JTextField();

	/**
	 * Create the panel.
	 */
	public TeacherProfilePage() {
		
		setLayout(null);
		setBounds(0, 0, 1420, 800);
		setBackground(new Color(30, 30, 36));
		
		if(LogInPage.getTeacherID() / 1000 == 4) {
			
			readDataFromTeachersTable("select Teacher_Name, Teacher_ID, Designation, Contact_no from cse_teachers");
			
		} else if(LogInPage.getTeacherID() / 1000 == 5) {
				
			readDataFromTeachersTable("select Teacher_Name, Teacher_ID, Designation, Contact_no from eee_teachers");
			
		} else if(LogInPage.getTeacherID() / 1000 == 10) {
			
			readDataFromTeachersTable("select Teacher_Name, Teacher_ID, Designation, Contact_no from arts_science_teachers");
			
		}  else if(LogInPage.getTeacherID() / 1000 == 8) {
			
			readDataFromTeachersTable("select Teacher_Name, Teacher_ID, Designation, Contact_no from me_teachers");
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
		
		if(LogInPage.getTeacherID() / 1000 == 4) {
				
			readImagefromTeachersTable("select Teacher_ID, Pro_Pic from cse_teachers");
			
		} else if(LogInPage.getTeacherID() / 1000 == 5) {
				
			readImagefromTeachersTable("select Teacher_ID, Pro_Pic from eee_teachers");
			
		} else if(LogInPage.getTeacherID() / 1000 == 10) {
			
			readImagefromTeachersTable("select Teacher_ID, Pro_Pic from arts_science_teachers");
			
		} else if(LogInPage.getTeacherID() / 1000 == 8) {
				
			readImagefromTeachersTable("select Teacher_ID, Pro_Pic from me_teachers");
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
				
				getLogOutTxt().getTimer().start();
				getSettingsTxt().getTimer().start();
				getAllottedCorseTxt().getTimer().start();
				getProName().getTimer().start();
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
					
					getAllottedCorseTxt().setBounds(50, 450, 350, 40);
					getAllottedCorseTxt().setOpaque(true);
					getAllottedCorseTxt().setBackground(new Color(43, 44, 52));
				}
				
				courceIcn.setIcon(new ImageIcon(openCourseIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
				if (menuBar.getBounds().x == -500) {
					
					getAllottedCorseTxt().setBounds(-450, 450, 350, 40);
					getAllottedCorseTxt().setOpaque(false);
				}

				courceIcn.setIcon(new ImageIcon(courseIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
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
		courceIcn.setIcon(new ImageIcon(courseIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
		add(courceIcn);
		
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
						
					}  else if(LogInPage.getTeacherID() / 1000 == 8) {
						
					tsp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from me_teachers");
					
					} else if(LogInPage.getTeacherID() / 1000 == 10) {
						
						tsp.readImagefromTeachersTable("select Teacher_ID, Pro_Pic from arts_science_teachers");
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
		
		dpFrameBig.setBounds(-375, 90, 200, 200);
		dpFrameBig.gimmiBounds();
		dpFrameBig.setIcon(new ImageIcon(dpFrameBigIcon.getScaledInstance(dpFrameBig.getWidth(), dpFrameBig.getHeight(), Image.SCALE_SMOOTH)));
		add(dpFrameBig);
		
		proPicBig.setBounds(-375, 90, 200, 200);
		proPicBig.gimmiBounds();
		proPicBig.setIcon(new ImageIcon(Dp.getScaledInstance(proPicBig.getWidth(), proPicBig.getHeight(), Image.SCALE_SMOOTH)));
		add(proPicBig);
		
		getProName().setForeground(new Color(118,185,0));
		getProName().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		getProName().setHorizontalAlignment(SwingConstants.CENTER);
		getProName().setBounds(-450, 291, 350, 60);
		getProName().gimmiBounds();
		add(getProName());
		
		getAllottedCorseTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getAllottedCorseTxt().setForeground(new Color(118, 185, 0));
		getAllottedCorseTxt().setBounds(-450, 450, 350, 40);
		getAllottedCorseTxt().gimmiBounds();
		add(getAllottedCorseTxt());
		
		getSettingsTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getSettingsTxt().setForeground(new Color(118, 185, 0));
		getSettingsTxt().setBounds(-450, 630, 350, 40);
		getSettingsTxt().gimmiBounds();
		add(getSettingsTxt());
		
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
		
		NameField.setHorizontalAlignment(SwingConstants.CENTER);
		NameField.setForeground(Color.GRAY);
		NameField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		NameField.setEditable(false);
		NameField.setColumns(10);
		NameField.setCaretColor(Color.GRAY);
		NameField.setBorder(null);
		NameField.setBackground(new Color(52, 52, 61));
		NameField.setBounds(600, 229, 300, 30);
		add(NameField);
		
		getNameTxt().setHorizontalAlignment(SwingConstants.TRAILING);
		getNameTxt().setForeground(new Color(118, 185, 0));
		getNameTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		getNameTxt().setBounds(435, 233, 150, 20);
		add(getNameTxt());
		
		getTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getTxt().setForeground(new Color(118, 185, 0));
		getTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 40));
		getTxt().setBounds(410, 80, 600, 60);
		add(getTxt());
		
		idField.setHorizontalAlignment(SwingConstants.CENTER);
		idField.setForeground(Color.GRAY);
		idField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		idField.setEditable(false);
		idField.setColumns(10);
		idField.setCaretColor(Color.GRAY);
		idField.setBorder(null);
		idField.setBackground(new Color(52, 52, 61));
		idField.setBounds(600, 309, 300, 30);
		add(idField);
		
		getIdTxt().setHorizontalAlignment(SwingConstants.TRAILING);
		getIdTxt().setForeground(new Color(118, 185, 0));
		getIdTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		getIdTxt().setBounds(435, 313, 150, 20);
		add(getIdTxt());
		
		desField.setHorizontalAlignment(SwingConstants.CENTER);
		desField.setForeground(Color.GRAY);
		desField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		desField.setEditable(false);
		desField.setColumns(10);
		desField.setCaretColor(Color.GRAY);
		desField.setBorder(null);
		desField.setBackground(new Color(52, 52, 61));
		desField.setBounds(600, 389, 300, 30);
		add(desField);
		
		getSemTxt().setHorizontalAlignment(SwingConstants.TRAILING);
		getSemTxt().setForeground(new Color(118, 185, 0));
		getSemTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		getSemTxt().setBounds(435, 393, 150, 25);
		add(getSemTxt());
		
		contactField.setHorizontalAlignment(SwingConstants.CENTER);
		contactField.setForeground(Color.GRAY);
		contactField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		contactField.setEditable(false);
		contactField.setColumns(10);
		contactField.setCaretColor(Color.GRAY);
		contactField.setBorder(null);
		contactField.setBackground(new Color(52, 52, 61));
		contactField.setBounds(600, 469, 300, 30);
		add(contactField);
		
		getSecTxt().setHorizontalAlignment(SwingConstants.TRAILING);
		getSecTxt().setForeground(new Color(118, 185, 0));
		getSecTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		getSecTxt().setBounds(435, 473, 150, 20);
		add(getSecTxt());
		
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

	public static AnimatedLabel getProName() {
		return proName;
	}

	public static void setProName(AnimatedLabel proName) {
		TeacherProfilePage.proName = proName;
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

			while (rs.next()) {

				if (LogInPage.getLogInID().getText().equals(rs.getString(2))) {

					NameField.setText(rs.getString(1));
					idField.setText(rs.getString(2));
					desField.setText(rs.getString(3));
					contactField.setText(rs.getString(4));
					proName.setText(TeacherProfilePage.getProName().getText());
				}
			
			}

		} catch (Exception e) {

			System.out.println(e);
		}
	}

	public JLabel getTxt() {
		return txt;
	}

	public void setTxt(JLabel txt) {
		this.txt = txt;
	}

	public JLabel getNameTxt() {
		return nameTxt;
	}

	public void setNameTxt(JLabel nameTxt) {
		this.nameTxt = nameTxt;
	}

	public JLabel getIdTxt() {
		return idTxt;
	}

	public void setIdTxt(JLabel idTxt) {
		this.idTxt = idTxt;
	}

	public JLabel getSemTxt() {
		return semTxt;
	}

	public void setSemTxt(JLabel semTxt) {
		this.semTxt = semTxt;
	}

	public JLabel getSecTxt() {
		return secTxt;
	}

	public void setSecTxt(JLabel secTxt) {
		this.secTxt = secTxt;
	}
}
