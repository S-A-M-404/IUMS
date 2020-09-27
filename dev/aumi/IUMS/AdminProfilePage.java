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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

@SuppressWarnings("serial")
public class AdminProfilePage extends JPanel {
	
	private Image btnDefault = new ImageIcon(this.getClass().getResource("/btn layout.png")).getImage();
	private Image btnMouseIn = new ImageIcon(this.getClass().getResource("/btn layout(mouse in).png")).getImage();
	
	private Image dpFrameIcon = new ImageIcon(this.getClass().getResource("/dp.png")).getImage();
	private Image Dp = new ImageIcon(this.getClass().getResource("/tempdp.png")).getImage();
	private Image dpFrameBigIcon = new ImageIcon(this.getClass().getResource("/dpbig.png")).getImage();
	private Image menuIcon = new ImageIcon(this.getClass().getResource("/menu.png")).getImage();
	private Image openMenuIcon = new ImageIcon(this.getClass().getResource("/openmenu.png")).getImage();
	private Image logOutIcon = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
	private Image openLogOutIcon = new ImageIcon(this.getClass().getResource("/openlogout.png")).getImage();
	
	private ImageIcon imageSmall; 
	private ImageIcon imageBig;
	
	private JLabel proPic = new JLabel("");
	private JLabel courseTitle = new JLabel("Fine Adding Form");
	private JLabel courseTxt = new JLabel("Serial No");
	private JLabel punTxt = new JLabel("Submit");
	private JLabel secTxt = new JLabel("Fine Amount");
	private JLabel allNoti = new JLabel("");
	
	private AnimatedLabel menuBar = new AnimatedLabel("", 11);
	private AnimatedLabel dpFrameBig = new AnimatedLabel("", 12);
	private AnimatedLabel proPicBig = new AnimatedLabel("", 12);
	private static AnimatedLabel proName = new AnimatedLabel("", 13);
	private AnimatedLabel logOutTxt = new AnimatedLabel("Log Out", 13);
	
	private String path;
	
	private static JTextField courseField = new JTextField();
	private static JTextField secField = new JTextField();
	
	private JTable offenceTable;

	/**
	 * Create the panel.
	 */
	public AdminProfilePage() {

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
				getProName().getTimer().start();
				dpFrameBig.getTimer().start();
				proPicBig.getTimer().start();
				menuBar.getTimer().start();	
			}
		});
		menuIcn.setBounds(4, 80, 40, 40);
		menuIcn.setIcon(new ImageIcon(menuIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
		add(menuIcn);
		
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
		
		getProName().setForeground(new Color(118,185,0));
		getProName().setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		getProName().setHorizontalAlignment(SwingConstants.CENTER);
		getProName().setBounds(-450, 291, 350, 60);
		getProName().setText(TeacherProfilePage.getProName().getText());
		getProName().gimmiBounds();
		add(getProName());
		
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
		getPunTxt().setBounds(185, 412, 162, 52);
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
						
						if(offenceTable.getValueAt(i,0).equals(courseField.getText())) {
							
							validCourse = true;
							break;
							
						}
						
						i++;
					}
					
					if(validCourse) {
						
						updateFine();
						
						secField.setText("");
						courseField.setText("");
						
						allNoti.setForeground(Color.GREEN);
						DefaultTableModel model = (DefaultTableModel)offenceTable.getModel();
						model.removeRow(i);
						
						if (HomePage.getLanguage().equals("bangla")) {

							Locale.setDefault(new Locale("bn", "BN"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							allNoti.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
							allNoti.setText(r.getString("successful"));

						} else {

							Locale.setDefault(new Locale("en", "UK"));
							ResourceBundle r = ResourceBundle.getBundle("bundle");

							allNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
							allNoti.setText(r.getString("successful"));
						}
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
				
			}
		});
		punBtn.setHorizontalAlignment(SwingConstants.CENTER);
		punBtn.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
		punBtn.setBounds(185, 412, 162, 52);
		punBtn.setIcon(new ImageIcon(btnDefault.getScaledInstance(punBtn.getWidth(), punBtn.getHeight(), Image.SCALE_SMOOTH)));
		add(punBtn);
		
		allNoti.setForeground(Color.RED);
		allNoti.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
		allNoti.setBounds(120, 376, 300, 25);
		add(allNoti);
		
		JScrollPane courseScrollPane = new JScrollPane();
		courseScrollPane.setBounds(526, 180, 884, 610);
		add(courseScrollPane);
		
		offenceTable = new JTable();
		offenceTable.setBorder(null);
		offenceTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Serial No", "Student ID", "Course No", "Offence", "Offence Date", "Submitted By", "Submission Date"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		offenceTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		offenceTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		offenceTable.getColumnModel().getColumn(5).setPreferredWidth(78);
		offenceTable.getColumnModel().getColumn(6).setPreferredWidth(100);
		offenceTable.setForeground(new Color(118, 185, 0));
		offenceTable.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		offenceTable.setBackground(new Color(52, 52, 61));
		offenceTable.setRowHeight(offenceTable.getRowHeight() + 20);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		JTableHeader tableHeader = offenceTable.getTableHeader();
		tableHeader.setBackground(new Color(52, 52, 61));
		tableHeader.setFont(new Font("Eras Medium ITC", Font.PLAIN, 25));
		tableHeader.setForeground(new Color(118, 185, 0));
		courseScrollPane.setViewportView(offenceTable);
		
		
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
	
	public void showPunishmentTable() {
		
		java.sql.Connection c = null;
		java.sql.Statement s = null;
		ResultSet rs = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			s = c.createStatement();
			String query = "select Index_no, ID, Course_no, offence, offence_Date, submitted_by, Submitted_Date from punishment_table where fine_amount is NULL";
			rs = s.executeQuery(query);
			
			DefaultTableModel model = (DefaultTableModel)offenceTable.getModel();
			
			while(rs.next()) {
						
				String[] breakCourseNo = rs.getString("Course_no").split(" ");
					
				model.addRow(new Object[] {rs.getString("Index_no"), rs.getString("ID"), breakCourseNo[0] + " " + breakCourseNo[1], rs.getString("offence"), rs.getString("offence_Date"), rs.getString("submitted_by"), rs.getString("Submitted_Date")});
					
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	public void updateFine() {

		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			ps = c.prepareStatement("update punishment_table set fine_amount = ? where Index_no = ?");
			
			ps.setString(1, secField.getText());
			ps.setString(2, courseField.getText());
			ps.executeUpdate();

		} catch (Exception e) {

			System.out.println(e);
		}
	}

	public static JTextField getCourseField() {
		return courseField;
	}

	public static void setCourseField(JTextField courseField) {
		AdminProfilePage.courseField = courseField;
	}

	public static JTextField getSecField() {
		return secField;
	}

	public static void setSecField(JTextField secField) {
		AdminProfilePage.secField = secField;
	}

	public static AnimatedLabel getProName() {
		return proName;
	}

	public static void setProName(AnimatedLabel proName) {
		AdminProfilePage.proName = proName;
	}

}
