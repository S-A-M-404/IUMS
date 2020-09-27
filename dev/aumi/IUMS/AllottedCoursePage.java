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
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

@SuppressWarnings("serial")
public class AllottedCoursePage extends JPanel {

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
	private Image settingsIcon = new ImageIcon(this.getClass().getResource("/settings.png")).getImage();
	private Image openSettingsIcon = new ImageIcon(this.getClass().getResource("/opensettings.png")).getImage();
	private Image logOutIcon = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
	private Image openLogOutIcon = new ImageIcon(this.getClass().getResource("/openlogout.png")).getImage();
	
	private ImageIcon imageSmall; 
	private ImageIcon imageBig;
	
	private JLabel proPic = new JLabel("");
	private JLabel courseTitle = new JLabel("Allotted Courses");
	private JLabel courseTxt = new JLabel("Course No");
	private JLabel punTxt = new JLabel("Create Punishment form");
	private JLabel secTxt = new JLabel("Section");
	private JLabel allNoti = new JLabel("");
	
	private AnimatedLabel menuBar = new AnimatedLabel("", 11);
	private AnimatedLabel dpFrameBig = new AnimatedLabel("", 12);
	private AnimatedLabel proPicBig = new AnimatedLabel("", 12);
	private AnimatedLabel proName = new AnimatedLabel("", 13);
	private AnimatedLabel settingsTxt = new AnimatedLabel("Settings", 13);
	private AnimatedLabel logOutTxt = new AnimatedLabel("Log Out", 13);
	
	private String path;
	
	private static JTextField courseField;
	private static JTextField secField;
	
	private JTable courseTable;
	
	/**
	 * Create the panel.
	 */
	public AllottedCoursePage() {

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
				
				logOutTxt.getTimer().start();
				settingsTxt.getTimer().start();
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
					
					settingsTxt.setBounds(50, 630, 350, 40);
					settingsTxt.setOpaque(true);
					settingsTxt.setBackground(new Color(43, 44, 52));
				}

				settingsIcn.setIcon(new ImageIcon(openSettingsIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
				if (menuBar.getBounds().x == -500) {
					
					settingsTxt.setBounds(-450, 630, 350, 40);
					settingsTxt.setOpaque(false);
				}

				settingsIcn.setIcon(new ImageIcon(settingsIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
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
		settingsIcn.setBounds(4, 630, 40, 40);
		settingsIcn.setIcon(new ImageIcon(
				settingsIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
		add(settingsIcn);
		
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
		proName.setText(TeacherProfilePage.getProName().getText());
		proName.gimmiBounds();
		add(proName);
		
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
		
		
		getCourseTitle().setHorizontalAlignment(SwingConstants.CENTER);
		getCourseTitle().setForeground(new Color(118, 185, 0));
		getCourseTitle().setBounds(410, 80, 600, 60);
		add(getCourseTitle());
		
		getCourseTxt().setForeground(new Color(118, 185, 0));
		getCourseTxt().setBounds(120, 180, 150, 25);
		add(getCourseTxt());
		
		setCourseField(new JTextField());
		getCourseField().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				getCourseField().setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				getSecField().setBorder(null);
				allNoti.setText("");
			}
		});
		getCourseField().setHorizontalAlignment(SwingConstants.CENTER);
		getCourseField().setForeground(Color.GRAY);
		getCourseField().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		getCourseField().setColumns(10);
		getCourseField().setCaretColor(Color.GRAY);
		getCourseField().setBorder(null);
		getCourseField().setBackground(new Color(52, 52, 61));
		getCourseField().setBounds(120, 216, 300, 30);
		add(getCourseField());
		
		getSecTxt().setForeground(new Color(118, 185, 0));
		getSecTxt().setBounds(120, 296, 150, 25);
		add(getSecTxt());
		
		setSecField(new JTextField());
		getSecField().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				getCourseField().setBorder(null);
				getSecField().setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				allNoti.setText("");
			}
		});
		getSecField().setHorizontalAlignment(SwingConstants.CENTER);
		getSecField().setForeground(Color.GRAY);
		getSecField().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		getSecField().setColumns(10);
		getSecField().setCaretColor(Color.GRAY);
		getSecField().setBorder(null);
		getSecField().setBackground(new Color(52, 52, 61));
		getSecField().setBounds(120, 332, 300, 30);
		add(getSecField());
		
		getPunTxt().setForeground(Color.WHITE);
		getPunTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getPunTxt().setBounds(145, 412, 250, 52);
		add(getPunTxt());
		
		JLabel punBtn = new JLabel("");
		punBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				punBtn.setIcon(new ImageIcon(btnMouseIn.getScaledInstance(punBtn.getWidth(), punBtn.getHeight(), Image.SCALE_SMOOTH)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				punBtn.setIcon(new ImageIcon(btnDefault.getScaledInstance(punBtn.getWidth(), punBtn.getHeight(), Image.SCALE_SMOOTH)));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int i = 0;
				
				boolean validCourse = false;
				
				try {
					
					while(true) {
						
						if(courseTable.getValueAt(i,0).equals(courseField.getText()) && courseTable.getValueAt(i,2).equals(getSecField().getText())) {
							
							validCourse = true;
							break;
							
						}
						
						i++;
					}
					
						
				}catch(Exception e1){
					
					
					validCourse = false;
					
					getSecField().setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
					courseField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
					
					if (HomePage.getLanguage().equals("bangla")) {

						Locale.setDefault(new Locale("bn", "BN"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						allNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
						allNoti.setText(r.getString("invalid"));

					} else {

						Locale.setDefault(new Locale("en", "UK"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						allNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
						allNoti.setText(r.getString("invalid"));
					}
				}
				
				if(validCourse) {
					
					if (HomePage.getLanguage().equals("bangla")) {

						Locale.setDefault(new Locale("bn", "BN"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						PunishmentForm pf = new PunishmentForm();
						
						pf.readImagefromTeachersTable();

						pf.getAllottedCorseTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
						pf.getAllottedCorseTxt().setText(r.getString("allotted"));
						
						pf.getSettingsTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
						pf.getSettingsTxt().setText(r.getString("settings"));
						
						pf.getLogOutTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
						pf.getLogOutTxt().setText(r.getString("logout"));
						
						pf.getPunTitle().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 35));
						pf.getPunTitle().setText(r.getString("puntitle"));
						
						pf.getStdIDTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
						pf.getStdIDTxt().setText(r.getString("stdid"));
						
						pf.getDateTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
						pf.getDateTxt().setText(r.getString("date"));
						
						pf.getRemarksTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
						pf.getRemarksTxt().setText(r.getString("remarks"));
						
						pf.getOffenceTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
						pf.getOffenceTxt().setText(r.getString("offence"));
						
						pf.getSubmitTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
						pf.getSubmitTxt().setText(r.getString("submit"));
						
						removeAll();
						revalidate();
						repaint();
						add(pf);

					} else {

						Locale.setDefault(new Locale("en", "UK"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						PunishmentForm pf = new PunishmentForm();
						
						pf.readImagefromTeachersTable();

						pf.getAllottedCorseTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
						pf.getAllottedCorseTxt().setText(r.getString("allotted"));
						
						pf.getSettingsTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
						pf.getSettingsTxt().setText(r.getString("settings"));
						
						pf.getLogOutTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
						pf.getLogOutTxt().setText(r.getString("logout"));
						
						pf.getPunTitle().setFont(new Font("Eras Medium ITC", Font.PLAIN, 40));
						pf.getPunTitle().setText(r.getString("puntitle"));
						
						pf.getStdIDTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
						pf.getStdIDTxt().setText(r.getString("stdid"));
						
						pf.getDateTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
						pf.getDateTxt().setText(r.getString("date"));
						
						pf.getRemarksTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
						pf.getRemarksTxt().setText(r.getString("remarks"));
						
						pf.getOffenceTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
						pf.getOffenceTxt().setText(r.getString("offence"));
						
						pf.getSubmitTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 24));
						pf.getSubmitTxt().setText(r.getString("submit"));

						removeAll();
						revalidate();
						repaint();
						add(pf);

					}
				}
			}
		});
		punBtn.setHorizontalAlignment(SwingConstants.CENTER);
		punBtn.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
		punBtn.setBounds(145, 412, 250, 52);
		punBtn.setIcon(new ImageIcon(btnDefault.getScaledInstance(punBtn.getWidth(), punBtn.getHeight(), Image.SCALE_SMOOTH)));
		add(punBtn);
		
		allNoti.setForeground(Color.RED);
		allNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
		allNoti.setBounds(120, 376, 300, 25);
		add(allNoti);
		
		JScrollPane courseScrollPane = new JScrollPane();
		courseScrollPane.setBounds(710, 180, 700, 610);
		add(courseScrollPane);
		
		courseTable = new JTable();
		courseTable.setBorder(null);
		courseTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Course No", "Course Title", "Section", "Room No"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		courseTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		courseTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		courseTable.setForeground(new Color(118, 185, 0));
		courseTable.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		courseTable.setBackground(new Color(52, 52, 61));
		courseTable.setRowHeight(courseTable.getRowHeight() + 20);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		courseTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		courseTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		courseTable.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		courseTable.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		
		JTableHeader tableHeader = courseTable.getTableHeader();
		tableHeader.setBackground(new Color(52, 52, 61));
		tableHeader.setFont(new Font("Eras Medium ITC", Font.PLAIN, 25));
		tableHeader.setForeground(new Color(118, 185, 0));
		courseScrollPane.setViewportView(courseTable);
		
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

	public JLabel getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(JLabel courseTitle) {
		this.courseTitle = courseTitle;
	}

	public JLabel getCourseTxt() {
		return courseTxt;
	}

	public void setCourseTxt(JLabel courseTxt) {
		this.courseTxt = courseTxt;
	}

	public JLabel getPunTxt() {
		return punTxt;
	}

	public void setPunTxt(JLabel punTxt) {
		this.punTxt = punTxt;
	}

	public JLabel getSecTxt() {
		return secTxt;
	}

	public void setSecTxt(JLabel secTxt) {
		this.secTxt = secTxt;
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
	
	public void showAllottedCourses(String query ) {
		
		java.sql.Connection c = null;
		java.sql.Statement s = null;
		ResultSet rs = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			s = c.createStatement();
			rs = s.executeQuery(query);
			
			DefaultTableModel model = (DefaultTableModel)courseTable.getModel();
			
			while(rs.next()) {
				
				if(rs.getString("Teacher_ID").equals(LogInPage.getLogInID().getText())) {
					
					String[] breakCourseNo = rs.getString("Course_no").split(" ");
					
					model.addRow(new Object[] {breakCourseNo[0] + " " + breakCourseNo[1], rs.getString("Course_title"), breakCourseNo[2], rs.getString("Room_no")});
				}
			}

		} catch (Exception e) {

			System.out.println(e);
		}
	}

	public static JTextField getCourseField() {
		return courseField;
	}

	public static void setCourseField(JTextField courseField) {
		AllottedCoursePage.courseField = courseField;
	}

	public static JTextField getSecField() {
		return secField;
	}

	public static void setSecField(JTextField secField) {
		AllottedCoursePage.secField = secField;
	}
}
