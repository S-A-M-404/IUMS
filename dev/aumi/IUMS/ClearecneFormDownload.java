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
public class ClearecneFormDownload extends JPanel {
	
	private Image btnDefault = new ImageIcon(this.getClass().getResource("/btn layout.png")).getImage();
	private Image btnMouseIn = new ImageIcon(this.getClass().getResource("/btn layout(mouse in).png")).getImage();
	
	private Image btnReturnImg = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
	private Image btnReturnImgMouseIn = new ImageIcon(this.getClass().getResource("/return(mouse in).png")).getImage();
	
	private Image notiNull = new ImageIcon(this.getClass().getResource("/notification null.png")).getImage();
	// private Image notifill = new ImageIcon(this.getClass().getResource("/notificationfilled.png")).getImage();
	private Image menuIcon = new ImageIcon(this.getClass().getResource("/menu.png")).getImage();
	private Image openMenuIcon = new ImageIcon(this.getClass().getResource("/openmenu.png")).getImage();;
	private Image clearenceIcon = new ImageIcon(this.getClass().getResource("/clearence.png")).getImage();
	private Image openClearenceIcon = new ImageIcon(this.getClass().getResource("/openclearence.png")).getImage();
	private Image settingsIcon = new ImageIcon(this.getClass().getResource("/settings.png")).getImage();
	private Image openSettingsIcon = new ImageIcon(this.getClass().getResource("/opensettings.png")).getImage();
	private Image logOutIcon = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
	private Image openLogOutIcon = new ImageIcon(this.getClass().getResource("/openlogout.png")).getImage();
	private Image dpFrameIcon = new ImageIcon(this.getClass().getResource("/dp.png")).getImage();
	private Image dpFrameBigIcon = new ImageIcon(this.getClass().getResource("/dpbig.png")).getImage();
	private Image Dp = new ImageIcon(this.getClass().getResource("/tempdp.png")).getImage();
	
	private ImageIcon imageSmall; 
	private ImageIcon imageBig;

	private JLabel proPic = new JLabel("");
	private JLabel clickTxt = new JLabel("Click the button to download clearance form");
	private JLabel dloadTxt = new JLabel("Download");
	
	private AnimatedLabel menuBar = new AnimatedLabel("", 11);
	private AnimatedLabel dpFrameBig = new AnimatedLabel("", 12);
	private AnimatedLabel proPicBig = new AnimatedLabel("", 12);
	private AnimatedLabel proName = new AnimatedLabel("", 13);
	private AnimatedLabel clearancePunTxt = new AnimatedLabel("Clearance Punishment", 13);
	private AnimatedLabel settingsTxt = new AnimatedLabel("Settings", 13);
	private AnimatedLabel logOutTxt = new AnimatedLabel("Log Out", 13);

	private String path;

	/**
	 * Create the panel.
	 */
	public ClearecneFormDownload() {

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

					StudentProfilePage spp = new StudentProfilePage();
					
					spp.readImagefromStudentsTable();

					spp.getClearenceFormTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					spp.getClearenceFormTxt().setText(r.getString("form"));
					
					spp.getClearancePunTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					spp.getClearancePunTxt().setText(r.getString("clrpun"));
					
					spp.getSettingsTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					spp.getSettingsTxt().setText(r.getString("settings"));
					
					spp.getLogOutTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					spp.getLogOutTxt().setText(r.getString("logout"));

					removeAll();
					revalidate();
					repaint();
					add(spp);

				} else {

					Locale.setDefault(new Locale("en", "UK"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					StudentProfilePage spp = new StudentProfilePage();
					
					spp.readImagefromStudentsTable();
					
					spp.getClearenceFormTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					spp.getClearenceFormTxt().setText(r.getString("form"));
					
					spp.getClearancePunTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					spp.getClearancePunTxt().setText(r.getString("clrpun"));
					
					spp.getSettingsTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					spp.getSettingsTxt().setText(r.getString("settings"));
					
					spp.getLogOutTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					spp.getLogOutTxt().setText(r.getString("logout"));

					removeAll();
					revalidate();
					repaint();
					add(spp);

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
					
					updateImageIntoStudentsTable();
					
					readImagefromStudentsTable();
					
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
				clearancePunTxt.getTimer().start();
				proName.getTimer().start();
				dpFrameBig.getTimer().start();
				proPicBig.getTimer().start();
				menuBar.getTimer().start();	
			}
		});
		menuIcn.setBounds(4, 80, 40, 40);
		menuIcn.setIcon(new ImageIcon(menuIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
		add(menuIcn);

		JLabel clearencePunIcn = new JLabel("");
		clearencePunIcn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				if (menuBar.getBounds().x == -500) {
					
					clearancePunTxt.setBounds(50, 540, 350, 40);
					clearancePunTxt.setOpaque(true);
					clearancePunTxt.setBackground(new Color(43, 44, 52));
				}
				
				clearencePunIcn.setIcon(new ImageIcon(openClearenceIcon.getScaledInstance(menuIcn.getWidth(),
						menuIcn.getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
				if (menuBar.getBounds().x == -500) {
					
					clearancePunTxt.setBounds(-450, 540, 350, 40);
					clearancePunTxt.setOpaque(false);
				}

				clearencePunIcn.setIcon(new ImageIcon(
						clearenceIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (HomePage.getLanguage().equals("bangla")) {

					Locale.setDefault(new Locale("bn", "BN"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					ClearencePunishmentPage cpp = new ClearencePunishmentPage();
					
					cpp.readImagefromStudentsTable();
					
					cpp.getClearenceFormTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					cpp.getClearenceFormTxt().setText(r.getString("form"));
					
					cpp.getSettingsTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					cpp.getSettingsTxt().setText(r.getString("settings"));
					
					cpp.getLogOutTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					cpp.getLogOutTxt().setText(r.getString("logout"));
					
					cpp.getSuperbTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 35));
					cpp.getSuperbTxt().setText(r.getString("superb"));
					
					cpp.getNoPunTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 25));
					cpp.getNoPunTxt().setText(r.getString("nopun"));
					
					cpp.getDloadTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					cpp.getDloadTxt().setText(r.getString("download"));
					
					cpp.showPunCourses("SELECT * FROM cse_lab inner join punishment_table on cse_lab.Course_no = punishment_table.Course_no");

					removeAll();
					revalidate();
					repaint();
					add(cpp);

				} else {

					Locale.setDefault(new Locale("en", "UK"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					ClearencePunishmentPage cpp = new ClearencePunishmentPage();
					
					cpp.readImagefromStudentsTable();
					
					cpp.getClearenceFormTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					cpp.getClearenceFormTxt().setText(r.getString("form"));
					
					cpp.getSettingsTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					cpp.getSettingsTxt().setText(r.getString("settings"));
					
					cpp.getLogOutTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					cpp.getLogOutTxt().setText(r.getString("logout"));
					
					cpp.getSuperbTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 40));
					cpp.getSuperbTxt().setText(r.getString("superb"));
					
					cpp.getNoPunTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 30));
					cpp.getNoPunTxt().setText(r.getString("nopun"));
					
					cpp.getDloadTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					cpp.getDloadTxt().setText(r.getString("download"));
					
					cpp.showPunCourses("SELECT * FROM cse_lab inner join punishment_table on cse_lab.Course_no = punishment_table.Course_no");

					removeAll();
					revalidate();
					repaint();
					add(cpp);

				}
			}
		});
		clearencePunIcn.setBounds(4, 540, 40, 40);
		clearencePunIcn.setIcon(new ImageIcon(
				clearenceIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
		add(clearencePunIcn);

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

					StudentSettingsPage ssp = new StudentSettingsPage();
					
					ssp.readImagefromStudentsTable();
					
					ssp.getClearenceFormTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					ssp.getClearenceFormTxt().setText(r.getString("form"));
					
					ssp.getClearancePunTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					ssp.getClearancePunTxt().setText(r.getString("clrpun"));
					
					ssp.getLblSettings().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 35));
					ssp.getLblSettings().setText(r.getString("settings"));

					ssp.getLogOutTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					ssp.getLogOutTxt().setText(r.getString("logout"));

					ssp.getEditTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
					ssp.getEditTxt().setText(r.getString("edittxt"));
					
					ssp.getLblChangePassword().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 18));
					ssp.getLblChangePassword().setText(r.getString("changepass"));
					
					removeAll();
					revalidate();
					repaint();
					add(ssp);

				} else {

					Locale.setDefault(new Locale("en", "UK"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					StudentSettingsPage ssp = new StudentSettingsPage();
					
					ssp.readImagefromStudentsTable();
					
					ssp.getLogOutTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					ssp.getLogOutTxt().setText(r.getString("logout"));
					
					ssp.getLblSettings().setFont(new Font("Eras Medium ITC", Font.PLAIN, 40));
					ssp.getLblSettings().setText(r.getString("settings"));
					
					ssp.getEditTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					ssp.getEditTxt().setText(r.getString("edittxt"));
					
					ssp.getLblChangePassword().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					ssp.getLblChangePassword().setText(r.getString("changepass"));
					
					removeAll();
					revalidate();
					repaint();
					add(ssp);

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
				
				logOutIcn.setIcon(new ImageIcon(
						openLogOutIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
				if (menuBar.getBounds().x == -500) {
					
					logOutTxt.setBounds(-450, 720, 350, 40);
					logOutTxt.setOpaque(false);
				}

				logOutIcn.setIcon(new ImageIcon(logOutIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
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
		logOutIcn.setIcon(new ImageIcon(logOutIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
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
		proName.setText(StudentProfilePage.getProName().getText());
		proName.gimmiBounds();
		add(proName);
		
		clearancePunTxt.setHorizontalAlignment(SwingConstants.CENTER);
		clearancePunTxt.setForeground(new Color(118, 185, 0));
		clearancePunTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
		clearancePunTxt.setBounds(-450, 540, 350, 40);
		clearancePunTxt.gimmiBounds();
		add(clearancePunTxt);
		
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
		
		getClickTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getClickTxt().setForeground(new Color(118, 185, 0));
		getClickTxt().setBounds(210, 100, 1000, 60);
		add(getClickTxt());
		
		getDloadTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getDloadTxt().setForeground(Color.WHITE);
		getDloadTxt().setBounds(629, 230, 162, 52);
		add(getDloadTxt());
		
		JLabel dloadBtn = new JLabel("");
		dloadBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				dloadBtn.setIcon(new ImageIcon(btnMouseIn));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				dloadBtn.setIcon(new ImageIcon(btnDefault));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				new FormDownload(StudentProfilePage.getIDField().getText(), StudentProfilePage.getNameField().getText(), Integer.parseInt(StudentProfilePage.getSemField().getText()), StudentProfilePage.getSecField().getText());
			}
		});
		dloadBtn.setHorizontalAlignment(SwingConstants.CENTER);
		dloadBtn.setForeground(Color.WHITE);
		dloadBtn.setFont(new Font("Eras Light ITC", Font.PLAIN, 28));
		dloadBtn.setBounds(629, 230, 162, 52);
		dloadBtn.setIcon(new ImageIcon(btnDefault));
		add(dloadBtn);
		
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

	public AnimatedLabel getClearancePunTxt() {
		return clearancePunTxt;
	}

	public void setClearancePunTxt(AnimatedLabel clearancePunTxt) {
		this.clearancePunTxt = clearancePunTxt;
	}

	public JLabel getClickTxt() {
		return clickTxt;
	}

	public void setClickTxt(JLabel clickTxt) {
		this.clickTxt = clickTxt;
	}

	public JLabel getDloadTxt() {
		return dloadTxt;
	}

	public void setDloadTxt(JLabel dloadTxt) {
		this.dloadTxt = dloadTxt;
	}
	
	public void updateImageIntoStudentsTable() {

		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			ps = c.prepareStatement("UPDATE cse_students SET Pro_Pic = ? WHERE Student_Name = ?");
			
			InputStream is = new FileInputStream(new File(path));
			
			ps.setBlob(1, is);
			ps.setString(2, proName.getText());
			ps.executeUpdate();

		} catch (Exception e) {

			System.out.println(e);
		}
	}
	
	public void readImagefromStudentsTable() {

		java.sql.Connection c = null;
		java.sql.Statement ps = null;	
		ResultSet rs = null;
		
		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			String query = "select Student_Name, Pro_Pic from cse_students";
			ps = c.createStatement();
			rs = ps.executeQuery(query);

			while (rs.next()) {

				if (StudentProfilePage.getProName().getText().equals(rs.getString(1))) {
					
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

}
