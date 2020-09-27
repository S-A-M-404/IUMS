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
public class StudentPersonalInfo extends JPanel {

	private Image btnReturnImg = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
	private Image btnReturnImgMouseIn = new ImageIcon(this.getClass().getResource("/return(mouse in).png")).getImage();
	private Image notiNull = new ImageIcon(this.getClass().getResource("/notification null.png")).getImage();
	// private Image notifill = new ImageIcon(this.getClass().getResource("/notificationfilled.png")).getImage();
	private Image menuIcon = new ImageIcon(this.getClass().getResource("/menu.png")).getImage();
	private Image openMenuIcon = new ImageIcon(this.getClass().getResource("/openmenu.png")).getImage();
	private Image routineIcon = new ImageIcon(this.getClass().getResource("/routine.png")).getImage();
	private Image openRoutineIcon = new ImageIcon(this.getClass().getResource("/openroutine.png")).getImage();
	private Image clearenceIcon = new ImageIcon(this.getClass().getResource("/clearence.png")).getImage();
	private Image openClearenceIcon = new ImageIcon(this.getClass().getResource("/openclearence.png")).getImage();
	private Image logOutIcon = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
	private Image openLogOutIcon = new ImageIcon(this.getClass().getResource("/openlogout.png")).getImage();
	private Image dpFrameIcon = new ImageIcon(this.getClass().getResource("/dp.png")).getImage();
	private Image dpFrameBigIcon = new ImageIcon(this.getClass().getResource("/dpbig.png")).getImage();
	private Image Dp = new ImageIcon(this.getClass().getResource("/tempdp.png")).getImage();

	private Image btnDefault = new ImageIcon(this.getClass().getResource("/btn layout.png")).getImage();
	private Image btnMouseIn = new ImageIcon(this.getClass().getResource("/btn layout(mouse in).png")).getImage();

	private JLabel proPic = new JLabel("");
	
	private ImageIcon imageSmall; 
	private ImageIcon imageBig;

	private AnimatedLabel menuBar = new AnimatedLabel("", 11);
	private AnimatedLabel dpFrameBig = new AnimatedLabel("", 12);
	private AnimatedLabel proPicBig = new AnimatedLabel("", 12);
	private AnimatedLabel proName = new AnimatedLabel("", 13);
	private AnimatedLabel clearanceFormTxt = new AnimatedLabel("Clearence Form", 13);
	private AnimatedLabel clearancePunTxt = new AnimatedLabel("Clearence Punishment", 13);
	private AnimatedLabel settingsTxt = new AnimatedLabel("Settings", 13);
	private AnimatedLabel logOutTxt = new AnimatedLabel("Log Out", 13);

	private String path;
	private JTextArea addressArea = new JTextArea();
	private final JTextField emailField = new JTextField();
	private final JTextField contactField = new JTextField();
	private final JTextField genderField = new JTextField();
	private final JTextField bloodField = new JTextField();
	private final JTextField religionField = new JTextField();
	private JTextField fatherField = new JTextField();
	private JTextField motherField = new JTextField();

	private final JLabel personalTxt = new JLabel("Edit Personal Information");
	private final JLabel emailTxt = new JLabel("Email");
	private final JLabel contactTxt = new JLabel("Contact No");
	private final JLabel GenderTxt = new JLabel("Gender");
	private final JLabel bloodTxt = new JLabel("Blood Group");
	private final JLabel religionTxt = new JLabel("Religion");
	private final JLabel addressTxt = new JLabel("Address");
	private final JLabel editTxt = new JLabel("Edit");
	private final JLabel editBtn = new JLabel("");
	private final JLabel fatherTxt = new JLabel("Father's Name");
	private final JLabel motherTxt = new JLabel("Mother's Name");

	/**
	 * Create the panel.
	 */
	public StudentPersonalInfo() {

		setLayout(null);
		setBounds(0, 0, 1420, 800);
		setBackground(new Color(30, 30, 36));
		
		readDataFromStudentsTable();

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

				btnReturn.setIcon(new ImageIcon(btnReturnImgMouseIn.getScaledInstance(btnReturn.getWidth(),
						btnReturn.getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseExited(MouseEvent e) {

				btnReturn.setIcon(new ImageIcon(btnReturnImg.getScaledInstance(btnReturn.getWidth(),
						btnReturn.getHeight(), Image.SCALE_SMOOTH)));
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
		btnReturn.setBounds(new Rectangle(15, 31, 28, 28));
		btnReturn.setIcon(new ImageIcon(
				btnReturnImg.getScaledInstance(btnReturn.getWidth(), btnReturn.getHeight(), Image.SCALE_SMOOTH)));
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
		personalTxt.setForeground(new Color(118, 185, 0));
		personalTxt.setHorizontalAlignment(SwingConstants.CENTER);
		personalTxt.setFont(new Font("Eras Medium ITC", Font.PLAIN, 40));
		personalTxt.setBounds(410, 70, 600, 60);
		add(personalTxt);

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
				clearancePunTxt.getTimer().start();
				clearanceFormTxt.getTimer().start();
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

		JLabel clearenceFormIcn = new JLabel("");
		clearenceFormIcn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				if (menuBar.getBounds().x == -500) {

					clearanceFormTxt.setBounds(50, 450, 350, 40);
					clearanceFormTxt.setOpaque(true);
					clearanceFormTxt.setBackground(new Color(43, 44, 52));
				}

				clearenceFormIcn.setIcon(new ImageIcon(openRoutineIcon.getScaledInstance(menuIcn.getWidth(),
						menuIcn.getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseExited(MouseEvent e) {

				if (menuBar.getBounds().x == -500) {

					clearanceFormTxt.setBounds(-450, 450, 350, 40);
					clearanceFormTxt.setOpaque(false);
					clearanceFormTxt.setBackground(new Color(43, 44, 52));
				}

				clearenceFormIcn.setIcon(new ImageIcon(
						routineIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (HomePage.getLanguage().equals("bangla")) {

					Locale.setDefault(new Locale("bn", "BN"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					ClearecneFormDownload cfd = new ClearecneFormDownload();
					
					cfd.readImagefromStudentsTable();
					
					cfd.getClearancePunTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					cfd.getClearancePunTxt().setText(r.getString("clrpun"));
					
					cfd.getSettingsTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					cfd.getSettingsTxt().setText(r.getString("settings"));
					
					cfd.getLogOutTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					cfd.getLogOutTxt().setText(r.getString("logout"));
					
					cfd.getClickTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 35));
					cfd.getClickTxt().setText(r.getString("downtxt"));
					
					cfd.getDloadTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					cfd.getDloadTxt().setText(r.getString("download"));

					removeAll();
					revalidate();
					repaint();
					add(cfd);

				} else {

					Locale.setDefault(new Locale("en", "UK"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					ClearecneFormDownload cfd = new ClearecneFormDownload();
					
					cfd.readImagefromStudentsTable();
					
					cfd.getClearancePunTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					cfd.getClearancePunTxt().setText(r.getString("clrpun"));
					
					cfd.getSettingsTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					cfd.getSettingsTxt().setText(r.getString("settings"));
					
					cfd.getLogOutTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					cfd.getLogOutTxt().setText(r.getString("logout"));
					
					cfd.getClickTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 40));
					cfd.getClickTxt().setText(r.getString("downtxt"));
					
					cfd.getDloadTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 28));
					cfd.getDloadTxt().setText(r.getString("download"));

					removeAll();
					revalidate();
					repaint();
					add(cfd);

				}
			}
		});
		clearenceFormIcn.setBounds(4, 450, 40, 40);
		clearenceFormIcn.setIcon(new ImageIcon(
				routineIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
		add(clearenceFormIcn);

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

				logOutIcn.setIcon(new ImageIcon(
						logOutIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
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
				logOutIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
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
		proName.gimmiBounds();
		add(proName);

		clearanceFormTxt.setHorizontalAlignment(SwingConstants.CENTER);
		clearanceFormTxt.setForeground(new Color(118, 185, 0));
		clearanceFormTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
		clearanceFormTxt.setBounds(-450, 450, 350, 40);
		clearanceFormTxt.gimmiBounds();
		add(clearanceFormTxt);

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
		
		getFatherTxt().setForeground(new Color(118, 185, 0));
		getFatherTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
		getFatherTxt().setBounds(100, 160, 150, 20);
		add(getFatherTxt());
		
		fatherField.setEditable(false);
		fatherField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				fatherField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				emailField.setBorder(null);
				contactField.setBorder(null);
				genderField.setBorder(null);
				bloodField.setBorder(null);
				religionField.setBorder(null);
				motherField.setBorder(null);
				addressArea.setBorder(null);
			}
		});
		fatherField.setHorizontalAlignment(SwingConstants.CENTER);
		fatherField.setForeground(Color.GRAY);
		fatherField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		fatherField.setColumns(10);
		fatherField.setCaretColor(Color.GRAY);
		fatherField.setBorder(null);
		fatherField.setBackground(new Color(52, 52, 61));
		fatherField.setBounds(100, 191, 300, 30);
		add(fatherField);
	
		getMotherTxt().setForeground(new Color(118, 185, 0));
		getMotherTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
		getMotherTxt().setBounds(100, 271, 150, 20);
		add(getMotherTxt());
		
		motherField.setEditable(false);
		motherField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				fatherField.setBorder(null);
				emailField.setBorder(null);
				contactField.setBorder(null);
				genderField.setBorder(null);
				bloodField.setBorder(null);
				religionField.setBorder(null);
				motherField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				addressArea.setBorder(null);
			}
		});
		motherField.setHorizontalAlignment(SwingConstants.CENTER);
		motherField.setForeground(Color.GRAY);
		motherField.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		motherField.setColumns(10);
		motherField.setCaretColor(Color.GRAY);
		motherField.setBorder(null);
		motherField.setBackground(new Color(52, 52, 61));
		motherField.setBounds(100, 302, 300, 30);
		add(motherField);

		emailTxt.setForeground(new Color(118, 185, 0));
		emailTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
		emailTxt.setBounds(100, 382, 150, 20);
		add(emailTxt);
		emailField.setEditable(false);

		emailField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				fatherField.setBorder(null);
				emailField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				contactField.setBorder(null);
				genderField.setBorder(null);
				bloodField.setBorder(null);
				religionField.setBorder(null);
				motherField.setBorder(null);
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
		emailField.setBounds(100, 413, 300, 30);
		add(emailField);

		contactTxt.setForeground(new Color(118, 185, 0));
		contactTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
		contactTxt.setBounds(585, 160, 150, 20);
		add(contactTxt);
		contactField.setEditable(false);

		contactField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				fatherField.setBorder(null);
				emailField.setBorder(null);
				contactField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				genderField.setBorder(null);
				bloodField.setBorder(null);
				religionField.setBorder(null);
				motherField.setBorder(null);
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
		contactField.setBounds(585, 191, 300, 30);
		add(contactField);

		GenderTxt.setForeground(new Color(118, 185, 0));
		GenderTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
		GenderTxt.setBounds(585, 271, 150, 20);
		add(GenderTxt);
		genderField.setEditable(false);

		genderField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				fatherField.setBorder(null);
				emailField.setBorder(null);
				contactField.setBorder(null);
				genderField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				bloodField.setBorder(null);
				religionField.setBorder(null);
				motherField.setBorder(null);
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
		genderField.setBounds(585, 302, 300, 30);
		add(genderField);

		bloodTxt.setForeground(new Color(118, 185, 0));
		bloodTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
		bloodTxt.setBounds(585, 382, 150, 20);
		add(bloodTxt);
		bloodField.setEditable(false);

		bloodField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				fatherField.setBorder(null);
				emailField.setBorder(null);
				contactField.setBorder(null);
				genderField.setBorder(null);
				bloodField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				religionField.setBorder(null);
				motherField.setBorder(null);
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
		bloodField.setBounds(585, 413, 300, 30);
		add(bloodField);

		religionTxt.setForeground(new Color(118, 185, 0));
		religionTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
		religionTxt.setBounds(1070, 377, 150, 25);
		add(religionTxt);
		religionField.setEditable(false);

		religionField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				fatherField.setBorder(null);
				emailField.setBorder(null);
				contactField.setBorder(null);
				genderField.setBorder(null);
				bloodField.setBorder(null);
				religionField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(118, 185, 0)));
				motherField.setBorder(null);
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
		religionField.setBounds(1070, 413, 300, 30);
		add(religionField);

		addressTxt.setForeground(new Color(118, 185, 0));
		addressTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
		addressTxt.setBounds(1070, 160, 150, 25);
		add(addressTxt);
		addressArea.setEditable(false);
		addressArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				fatherField.setBorder(null);
				emailField.setBorder(null);
				contactField.setBorder(null);
				genderField.setBorder(null);
				bloodField.setBorder(null);
				religionField.setBorder(null);
				motherField.setBorder(null);
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
						fatherField.setEditable(true);
						motherField.setEditable(true);
						emailField.setEditable(true);
						contactField.setEditable(true);
						genderField.setEditable(true);
						bloodField.setEditable(true);
						addressArea.setEditable(true);
						religionField.setEditable(true);
						
					} else {
						
						updateDataIntoStudentsTable();
						
						editTxt.setText(r.getString("edit"));
						fatherField.setEditable(false);
						motherField.setEditable(false);
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
						fatherField.setEditable(true);
						motherField.setEditable(true);
						emailField.setEditable(true);
						contactField.setEditable(true);
						genderField.setEditable(true);
						bloodField.setEditable(true);
						addressArea.setEditable(true);
						religionField.setEditable(true);
						
					} else {
						
						updateDataIntoStudentsTable();
						
						editTxt.setText(r.getString("edit"));
						fatherField.setEditable(false);
						motherField.setEditable(false);
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
		
		editTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 28));
		editTxt.setHorizontalAlignment(SwingConstants.CENTER);
		editTxt.setForeground(Color.WHITE);
		editTxt.setBounds(1208, 500, 162, 52);
		add(editTxt);
		
		editBtn.setBounds(1208, 500, 162, 52);
		editBtn.setIcon(new ImageIcon(btnDefault));
		add(editBtn);

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

	public JLabel getEditTxt() {
		return editTxt;
	}
	
	public AnimatedLabel getClearenceFormTxt() {
		return clearanceFormTxt;
	}

	public void setClearenceFormTxt(AnimatedLabel clearenceFormTxt) {
		this.clearanceFormTxt = clearenceFormTxt;
	}

	public AnimatedLabel getClearancePunTxt() {
		return clearancePunTxt;
	}

	public void setClearancePunTxt(AnimatedLabel clearancePunTxt) {
		this.clearancePunTxt = clearancePunTxt;
	}

	public JLabel getFatherTxt() {
		return fatherTxt;
	}

	public JLabel getMotherTxt() {
		return motherTxt;
	}
	
	public void readDataFromStudentsTable() {

		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;	
		ResultSet rs = null;
		
		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			String query = "select Student_Name, Email, Phone, Father_name, Mother_name, Gender, Address, Blood_Group, Religion from cse_students";
			ps = c.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {

				if (StudentProfilePage.getProName().getText().equals(rs.getString(1))) {

					fatherField.setText(rs.getString(4));
					motherField.setText(rs.getString(5));
					emailField.setText(rs.getString(2));
					contactField.setText(rs.getString(3));
					genderField.setText(rs.getString(6));
					bloodField.setText(rs.getString(8));
					addressArea.setText(rs.getString(7));
					religionField.setText(rs.getString(9));
					proName.setText(rs.getString(1));
				}
			
			}

		} catch (Exception e) {

			System.out.println(e);
		}
	}
	
	public void updateDataIntoStudentsTable() {

		java.sql.Connection c = null;
		java.sql.PreparedStatement ps = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			ps = c.prepareStatement("UPDATE cse_students SET Email = ?, Phone = ?, Father_name = ?, Mother_name = ?, Gender = ?, Address = ?, Blood_Group = ?, Religion = ? WHERE Student_Name = ?");

			ps.setString(1, emailField.getText());
			ps.setString(2, contactField.getText());
			ps.setString(3, fatherField.getText());
			ps.setString(4, motherField.getText());
			ps.setString(5, genderField.getText());
			ps.setString(6, addressArea.getText());
			ps.setString(7, bloodField.getText());
			ps.setString(8, religionField.getText());
			ps.setString(9, proName.getText());
			ps.executeUpdate();

		} catch (Exception e) {

			System.out.println(e);
		}
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
