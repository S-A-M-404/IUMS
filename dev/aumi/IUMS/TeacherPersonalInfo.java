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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class TeacherPersonalInfo extends JPanel {

	private Image btnReturnImg = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
	private Image btnReturnImgMouseIn = new ImageIcon(this.getClass().getResource("/return(mouse in).png")).getImage();
	private Image notiNull = new ImageIcon(this.getClass().getResource("/notification null.png")).getImage();
	// private Image notifill = new ImageIcon(this.getClass().getResource("/notificationfilled.png")).getImage();
	private Image menuIcon = new ImageIcon(this.getClass().getResource("/menu.png")).getImage();
	private Image openMenuIcon = new ImageIcon(this.getClass().getResource("/openmenu.png")).getImage();
	private Image courseIcon = new ImageIcon(this.getClass().getResource("/result.png")).getImage();
	private Image openCourseIcon = new ImageIcon(this.getClass().getResource("/openresult.png")).getImage();
	private Image logOutIcon = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
	private Image openLogOutIcon = new ImageIcon(this.getClass().getResource("/openlogout.png")).getImage();
	private Image dpFrameIcon = new ImageIcon(this.getClass().getResource("/dp.png")).getImage();
	private Image dpFrameBigIcon = new ImageIcon(this.getClass().getResource("/dpbig.png")).getImage();
	private Image Dp = new ImageIcon(this.getClass().getResource("/tempdp.png")).getImage();
	
	private Image btnDefault = new ImageIcon(this.getClass().getResource("/btn layout.png")).getImage();
	private Image btnMouseIn = new ImageIcon(this.getClass().getResource("/btn layout(mouse in).png")).getImage();
	
	private JLabel proPic = new JLabel("");
	
	private AnimatedLabel menuBar = new AnimatedLabel("", 11);
	private AnimatedLabel dpFrameBig = new AnimatedLabel("", 12);
	private AnimatedLabel proPicBig = new AnimatedLabel("", 12);
	private AnimatedLabel proName = new AnimatedLabel("", 13);
	private AnimatedLabel allottedCorseTxt = new AnimatedLabel("Allotted Courses", 13);
	private AnimatedLabel logOutTxt = new AnimatedLabel("Log Out", 13);
	
	private ImageIcon imageSmall; 
	private ImageIcon imageBig;
	
	private String path;
	
	private JTextArea addressArea = new JTextArea();
	
	private final JTextField signField = new JTextField();
	private final JTextField emailField = new JTextField();
	private final JTextField contactField = new JTextField();
	private final JTextField genderField = new JTextField();
	private final JTextField bloodField = new JTextField();
	private final JTextField religionField = new JTextField();
	private final JTextField designationField = new JTextField();
	
	private final JLabel personalTxt = new JLabel("Edit Personal Information");
	private final JLabel signTxt = new JLabel("Signature");
	private final JLabel emailTxt = new JLabel("Email");
	private final JLabel contactTxt = new JLabel("Contact No");
	private final JLabel GenderTxt = new JLabel("Gender");
	private final JLabel bloodTxt = new JLabel("Blood Group");
	private final JLabel religionTxt = new JLabel("Religion");
	private final JLabel designationTxt = new JLabel("Designation");
	private final JLabel addressTxt = new JLabel("Address");
	private final JLabel editTxt = new JLabel("Edit");
	private final JLabel editBtn = new JLabel("");
	
	/**
	 * Create the panel.
	 */
	public TeacherPersonalInfo() {
		
		setLayout(null);
		setBounds(0, 0, 1420, 800);
		setBackground(new Color(30, 30, 36));

		if(LogInPage.getTeacherID() / 1000 == 4) {
			
			readDataFromTeachersTable("select Teacher_Name, Email, Contact_no, Signature, Designation, Gender, Address, Blood_Group, Religion from cse_teachers");
			
		} else if(LogInPage.getTeacherID() / 1000 == 5) {
				
			readDataFromTeachersTable("select Teacher_Name, Email, Contact_no, Signature, Designation, Gender, Address, Blood_Group, Religion from eee_teachers");
			
		} else if(LogInPage.getTeacherID() / 1000 == 10) {
			
			readDataFromTeachersTable("select Teacher_Name, Email, Contact_no, Signature, Designation, Gender, Address, Blood_Group, Religion from arts_science_teachers");
			
		}  else if(LogInPage.getTeacherID() / 1000 == 8) {
			
			readDataFromTeachersTable("select Teacher_Name, Email, Contact_no, Signature, Designation, Gender, Address, Blood_Group, Religion from me_teachers");
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
		
		// to add the title

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
						
					}  else if(LogInPage.getTeacherID() / 1000 == 10) {
						
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
		btnReturn.setIcon(new ImageIcon(btnReturnImg.getScaledInstance(btnReturn.getWidth(), btnReturn.getHeight(), Image.SCALE_SMOOTH)));
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
		getPersonalTxt().setForeground(new Color(118, 185, 0));
		getPersonalTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getPersonalTxt().setBounds(410, 70, 600, 60);
		add(getPersonalTxt());
		
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
		
		getSignTxt().setForeground(new Color(118, 185, 0));
		getSignTxt().setBounds(100, 155, 150, 25);
		add(getSignTxt());
		signField.setEditable(false);
		
		signField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				signField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				emailField.setBorder(null);
				contactField.setBorder(null);
				genderField.setBorder(null);
				bloodField.setBorder(null);
				religionField.setBorder(null);
				designationField.setBorder(null);
				addressArea.setBorder(null);
				
			}
		});
		signField.setHorizontalAlignment(SwingConstants.CENTER);
		signField.setForeground(Color.GRAY);
		signField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		signField.setColumns(10);
		signField.setCaretColor(Color.GRAY);
		signField.setBorder(null);
		signField.setBackground(new Color(52, 52, 61));
		signField.setBounds(100, 191, 300, 30);
		add(signField);
		
		getEmailTxt().setForeground(new Color(118, 185, 0));
		getEmailTxt().setBounds(100, 271, 150, 20);
		add(getEmailTxt());
		emailField.setEditable(false);
		
		emailField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				signField.setBorder(null);
				emailField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				contactField.setBorder(null);
				genderField.setBorder(null);
				bloodField.setBorder(null);
				religionField.setBorder(null);
				designationField.setBorder(null);
				addressArea.setBorder(null);
			
			}
		});
		emailField.setHorizontalAlignment(SwingConstants.CENTER);
		emailField.setForeground(Color.GRAY);
		emailField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		emailField.setColumns(10);
		emailField.setCaretColor(Color.GRAY);
		emailField.setBorder(null);
		emailField.setBackground(new Color(52, 52, 61));
		emailField.setBounds(100, 302, 300, 30);
		add(emailField);
		
		getContactTxt().setForeground(new Color(118, 185, 0));
		getContactTxt().setBounds(100, 382, 150, 20);
		add(getContactTxt());
		contactField.setEditable(false);
		
		contactField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				signField.setBorder(null);
				emailField.setBorder(null);
				contactField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				genderField.setBorder(null);
				bloodField.setBorder(null);
				religionField.setBorder(null);
				designationField.setBorder(null);
				addressArea.setBorder(null);
			}
		});	
		contactField.setHorizontalAlignment(SwingConstants.CENTER);
		contactField.setForeground(Color.GRAY);
		contactField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		contactField.setColumns(10);
		contactField.setCaretColor(Color.GRAY);
		contactField.setBorder(null);
		contactField.setBackground(new Color(52, 52, 61));
		contactField.setBounds(100, 413, 300, 30);
		add(contactField);
		
		getGenderTxt().setForeground(new Color(118, 185, 0));
		getGenderTxt().setBounds(585, 160, 150, 20);
		add(getGenderTxt());
		genderField.setEditable(false);
		
		genderField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				signField.setBorder(null);
				emailField.setBorder(null);
				contactField.setBorder(null);
				genderField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				bloodField.setBorder(null);
				religionField.setBorder(null);
				designationField.setBorder(null);
				addressArea.setBorder(null);
			}
		});
		genderField.setHorizontalAlignment(SwingConstants.CENTER);
		genderField.setForeground(Color.GRAY);
		genderField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		genderField.setColumns(10);
		genderField.setCaretColor(Color.GRAY);
		genderField.setBorder(null);
		genderField.setBackground(new Color(52, 52, 61));
		genderField.setBounds(585, 191, 300, 30);
		add(genderField);
		
		getBloodTxt().setForeground(new Color(118, 185, 0));
		getBloodTxt().setBounds(585, 271, 150, 20);
		add(getBloodTxt());
		bloodField.setEditable(false);
		
		bloodField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				signField.setBorder(null);
				emailField.setBorder(null);
				contactField.setBorder(null);
				genderField.setBorder(null);
				bloodField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				religionField.setBorder(null);
				designationField.setBorder(null);
				addressArea.setBorder(null);
			}
		});	
		bloodField.setHorizontalAlignment(SwingConstants.CENTER);
		bloodField.setForeground(Color.GRAY);
		bloodField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		bloodField.setColumns(10);
		bloodField.setCaretColor(Color.GRAY);
		bloodField.setBorder(null);
		bloodField.setBackground(new Color(52, 52, 61));
		bloodField.setBounds(585, 302, 300, 30);
		add(bloodField);
		
		getReligionTxt().setForeground(new Color(118, 185, 0));
		getReligionTxt().setBounds(585, 377, 150, 25);
		add(getReligionTxt());
		religionField.setEditable(false);
		
		religionField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				signField.setBorder(null);
				emailField.setBorder(null);
				contactField.setBorder(null);
				genderField.setBorder(null);
				bloodField.setBorder(null);
				religionField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				designationField.setBorder(null);
				addressArea.setBorder(null);
			}
		});	
		religionField.setHorizontalAlignment(SwingConstants.CENTER);
		religionField.setForeground(Color.GRAY);
		religionField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		religionField.setColumns(10);
		religionField.setCaretColor(Color.GRAY);
		religionField.setBorder(null);
		religionField.setBackground(new Color(52, 52, 61));
		religionField.setBounds(585, 413, 300, 30);
		add(religionField);
		
		getDesignationTxt().setForeground(new Color(118, 185, 0));
		getDesignationTxt().setBounds(1070, 380, 150, 25);
		add(getDesignationTxt());
		designationField.setEditable(false);
		
		designationField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				signField.setBorder(null);
				emailField.setBorder(null);
				contactField.setBorder(null);
				genderField.setBorder(null);
				bloodField.setBorder(null);
				religionField.setBorder(null);
				designationField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				addressArea.setBorder(null);
			}
		});
		designationField.setHorizontalAlignment(SwingConstants.CENTER);
		designationField.setForeground(Color.GRAY);
		designationField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		designationField.setColumns(10);
		designationField.setCaretColor(Color.GRAY);
		designationField.setBorder(null);
		designationField.setBackground(new Color(52, 52, 61));
		designationField.setBounds(1070, 413, 300, 30);
		add(designationField);
		
		getAddressTxt().setForeground(new Color(118, 185, 0));
		getAddressTxt().setBounds(1070, 160, 150, 25);
		add(getAddressTxt());
		addressArea.setEditable(false);
		addressArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				signField.setBorder(null);
				emailField.setBorder(null);
				contactField.setBorder(null);
				genderField.setBorder(null);
				bloodField.setBorder(null);
				religionField.setBorder(null);
				designationField.setBorder(null);
				addressArea.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
			}
		});
		
		addressArea.setCaretColor(Color.GRAY);
		addressArea.setWrapStyleWord(true);
		addressArea.setBorder(null);
		addressArea.setLineWrap(true);
		addressArea.setForeground(Color.GRAY);
		addressArea.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		addressArea.setBackground(new Color(52, 52, 61));
		addressArea.setBounds(1070, 191, 300, 150);
		add(addressArea);
		
		getEditTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getEditTxt().setForeground(Color.WHITE);
		getEditTxt().setBounds(1208, 500, 162, 52);
		
		add(getEditTxt());
		editBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				editBtn.setIcon(new ImageIcon(btnMouseIn));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				editBtn.setIcon(new ImageIcon(btnDefault));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (HomePage.getLanguage().equals("bangla")) {

					Locale.setDefault(new Locale("bn", "BN"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					if(editTxt.getText().equals(r.getString("edit"))) {
						
						editTxt.setText(r.getString("save"));
						signField.setEditable(true);
						designationField.setEditable(true);
						emailField.setEditable(true);
						contactField.setEditable(true);
						genderField.setEditable(true);
						bloodField.setEditable(true);
						addressArea.setEditable(true);
						religionField.setEditable(true);
						
					} else {
						
						if(LogInPage.getTeacherID() / 1000 == 4) {
							
							updateDataIntoTeachersTable("UPDATE cse_teachers SET Email = ?, Contact_no = ?, Signature = ?, Designation = ?, Gender = ?, Address = ?, Blood_Group = ?, Religion = ? WHERE Teacher_ID = ?");
							
						} else if(LogInPage.getTeacherID() / 1000 == 5) {
								
							updateDataIntoTeachersTable("UPDATE eee_teachers SET Email = ?, Contact_no = ?, Signature = ?, Designation = ?, Gender = ?, Address = ?, Blood_Group = ?, Religion = ? WHERE Teacher_ID = ?");
							
						} else if(LogInPage.getTeacherID() / 1000 == 10) {
							
							updateDataIntoTeachersTable("UPDATE arts_science_teachers SET Email = ?, Contact_no = ?, Signature = ?, Designation = ?, Gender = ?, Address = ?, Blood_Group = ?, Religion = ? WHERE Teacher_ID = ?");
							
						} else if(LogInPage.getTeacherID() / 1000 == 8) {
							
							updateDataIntoTeachersTable("UPDATE me_teachers SET Email = ?, Contact_no = ?, Signature = ?, Designation = ?, Gender = ?, Address = ?, Blood_Group = ?, Religion = ? WHERE Teacher_ID = ?");
						}
						
						editTxt.setText(r.getString("edit"));
						signField.setEditable(false);
						designationField.setEditable(false);
						emailField.setEditable(false);
						contactField.setEditable(false);
						genderField.setEditable(false);
						bloodField.setEditable(false);
						addressArea.setEditable(false);
						religionField.setEditable(false);
					}

				} else {

					Locale.setDefault(new Locale("en", "UK"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					if(editTxt.getText().equals(r.getString("edit"))) {
						
						editTxt.setText(r.getString("save"));
						signField.setEditable(true);
						designationField.setEditable(true);
						emailField.setEditable(true);
						contactField.setEditable(true);
						genderField.setEditable(true);
						bloodField.setEditable(true);
						addressArea.setEditable(true);
						religionField.setEditable(true);
						
					} else {
						
						if(LogInPage.getTeacherID() / 1000 == 4) {
							
							updateDataIntoTeachersTable("UPDATE cse_teachers SET Email = ?, Contact_no = ?, Signature = ?, Designation = ?, Gender = ?, Address = ?, Blood_Group = ?, Religion = ? WHERE Teacher_ID = ?");
							
						} else if(LogInPage.getTeacherID() / 1000 == 5) {
								
							updateDataIntoTeachersTable("UPDATE eee_teachers SET Email = ?, Contact_no = ?, Signature = ?, Designation = ?, Gender = ?, Address = ?, Blood_Group = ?, Religion = ? WHERE Teacher_ID = ?");
							
						} else if(LogInPage.getTeacherID() / 1000 == 10) {
							
							updateDataIntoTeachersTable("UPDATE arts_science_teachers SET Email = ?, Contact_no = ?, Signature = ?, Designation = ?, Gender = ?, Address = ?, Blood_Group = ?, Religion = ? WHERE Teacher_ID = ?");
							
						} else if(LogInPage.getTeacherID() / 1000 == 8) {
							
							updateDataIntoTeachersTable("UPDATE me_teachers SET Email = ?, Contact_no = ?, Signature = ?, Designation = ?, Gender = ?, Address = ?, Blood_Group = ?, Religion = ? WHERE Teacher_ID = ?");
						}
						
						editTxt.setText(r.getString("edit"));
						signField.setEditable(false);
						designationField.setEditable(false);
						emailField.setEditable(false);
						contactField.setEditable(false);
						genderField.setEditable(false);
						bloodField.setEditable(false);
						addressArea.setEditable(false);
						religionField.setEditable(false);
					}

				}
			}
		});
		editBtn.setBounds(1208, 500, 162, 52);
		editBtn.setIcon(new ImageIcon(btnDefault));
		add(editBtn);
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

	public JLabel getPersonalTxt() {
		return personalTxt;
	}

	public JLabel getSignTxt() {
		return signTxt;
	}

	public JLabel getEmailTxt() {
		return emailTxt;
	}

	public JLabel getContactTxt() {
		return contactTxt;
	}

	public JLabel getGenderTxt() {
		return GenderTxt;
	}

	public JLabel getBloodTxt() {
		return bloodTxt;
	}

	public JLabel getReligionTxt() {
		return religionTxt;
	}

	public JLabel getAddressTxt() {
		return addressTxt;
	}

	public JLabel getDesignationTxt() {
		return designationTxt;
	}

	public JLabel getEditTxt() {
		return editTxt;
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

				if (TeacherProfilePage.getProName().getText().equals(rs.getString(1))) {

					signField.setText(rs.getString(4));
					designationField.setText(rs.getString(5));
					emailField.setText(rs.getString(2));
					contactField.setText(rs.getString(3));
					
					if(rs.getString(6).equals("M")) {
						
						genderField.setText("Male");
					} else {
						
						genderField.setText("Female");
					}
					
					bloodField.setText(rs.getString(8));
					addressArea.setText(rs.getString(7));
					religionField.setText(rs.getString(9));
					proName.setText(TeacherProfilePage.getProName().getText());
				}
			
			}

		} catch (Exception e) {

			System.out.println(e);
		}
	}
	
	public void updateDataIntoTeachersTable(String query) {

		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			ps = c.prepareStatement(query);

			ps.setString(1, emailField.getText());
			ps.setString(2, contactField.getText());
			ps.setString(3, signField.getText());
			ps.setString(4, designationField.getText());
			
			if(genderField.getText().equals("Male")) {
				
				ps.setString(5, "M");
				
			} else if(genderField.getText().equals("Female")) {
				
				ps.setString(5, "F");
			}
			
			
			ps.setString(6, addressArea.getText());
			ps.setString(7, bloodField.getText());
			ps.setString(8, religionField.getText());
			ps.setString(9, LogInPage.getLogInID().getText());
			ps.executeUpdate();

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
