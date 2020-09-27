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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

@SuppressWarnings("serial")
public class ClearencePunishmentPage extends JPanel {
	
	private Image btnDefault = new ImageIcon(this.getClass().getResource("/btn layout.png")).getImage();
	private Image btnMouseIn = new ImageIcon(this.getClass().getResource("/btn layout(mouse in).png")).getImage();

	private Image btnReturnImg = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
	private Image btnReturnImgMouseIn = new ImageIcon(this.getClass().getResource("/return(mouse in).png")).getImage();
	private Image notiNull = new ImageIcon(this.getClass().getResource("/notification null.png")).getImage();
	// private Image notifill = new ImageIcon(this.getClass().getResource("/notificationfilled.png")).getImage();
	private Image menuIcon = new ImageIcon(this.getClass().getResource("/menu.png")).getImage();
	private Image openMenuIcon = new ImageIcon(this.getClass().getResource("/openmenu.png")).getImage();
	private Image routineIcon = new ImageIcon(this.getClass().getResource("/routine.png")).getImage();
	private Image openRoutineIcon = new ImageIcon(this.getClass().getResource("/openroutine.png")).getImage();
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
	private JLabel superbTxt = new JLabel("Superb!!!");
	private JLabel noPunTxt = new JLabel("You don't have any clearance punishment");
	
	private AnimatedLabel menuBar = new AnimatedLabel("", 11);
	private AnimatedLabel dpFrameBig = new AnimatedLabel("", 12);
	private AnimatedLabel proPicBig = new AnimatedLabel("", 12);
	private AnimatedLabel proName = new AnimatedLabel("", 13);
	private AnimatedLabel clearanceFormTxt = new AnimatedLabel("Clearence Form", 13);
	private AnimatedLabel settingsTxt = new AnimatedLabel("Settings", 13);
	private AnimatedLabel logOutTxt = new AnimatedLabel("Log Out", 13);

	private JLabel dloadTxt = new JLabel("Download");
	private JLabel dloadBtn = new JLabel("");
	
	private String path;
	private JTable punTable = new JTable();
	
	private JScrollPane scrollPane = new JScrollPane();
	
	/**
	 * Create the panel.
	 */
	public ClearencePunishmentPage() {

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
				clearanceFormTxt.getTimer().start();
				proName.getTimer().start();
				dpFrameBig.getTimer().start();
				proPicBig.getTimer().start();
				menuBar.getTimer().start();	
			}
		});
		menuIcn.setBounds(4, 80, 40, 40);
		menuIcn.setIcon(new ImageIcon(menuIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
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
				
				clearenceFormIcn.setIcon(new ImageIcon(openRoutineIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void mouseExited(MouseEvent e) {

				if (menuBar.getBounds().x == -500) {
					
					clearanceFormTxt.setBounds(-450, 450, 350, 40);
					clearanceFormTxt.setOpaque(false);
					clearanceFormTxt.setBackground(new Color(43, 44, 52));
				}

				clearenceFormIcn.setIcon(new ImageIcon(routineIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
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
		clearenceFormIcn.setIcon(new ImageIcon(routineIcon.getScaledInstance(menuIcn.getWidth(), menuIcn.getHeight(), Image.SCALE_SMOOTH)));
		add(clearenceFormIcn);

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
		
		scrollPane.setBounds(75, 199, 1313, 474);
		scrollPane.setVisible(false);
		add(scrollPane);
		
		punTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Course No", "Course Name", "Fine Amount", "Offence Date", "Submission Date", "Submitted By"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		punTable.setRowHeight(36);
		punTable.setForeground(new Color(118, 185, 0));
		punTable.setFont(new Font("Eras Medium ITC", Font.PLAIN, 20));
		punTable.setBorder(null);
		punTable.setBackground(new Color(52, 52, 61));
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		punTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		punTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		punTable.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		punTable.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		
		JTableHeader tableHeader = punTable.getTableHeader();
		tableHeader.setBackground(new Color(52, 52, 61));
		tableHeader.setFont(new Font("Eras Medium ITC", Font.PLAIN, 25));
		tableHeader.setForeground(new Color(118, 185, 0));
		scrollPane.setViewportView(punTable);
		
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
		
		clearanceFormTxt.setHorizontalAlignment(SwingConstants.CENTER);
		clearanceFormTxt.setForeground(new Color(118, 185, 0));
		clearanceFormTxt.setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
		clearanceFormTxt.setBounds(-450, 450, 350, 40);
		clearanceFormTxt.gimmiBounds();
		add(clearanceFormTxt);
		
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
		
		getSuperbTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getSuperbTxt().setForeground(new Color(118, 185, 0));
		getSuperbTxt().setBounds(310, 100, 800, 60);
		add(getSuperbTxt());
		
		getNoPunTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getNoPunTxt().setForeground(new Color(118, 185, 0));
		getNoPunTxt().setBounds(410, 190, 600, 60);
		add(getNoPunTxt());
		
		
		getDloadTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getDloadTxt().setForeground(Color.WHITE);
		getDloadTxt().setBounds(646, 708, 162, 52);
		getDloadTxt().setVisible(false);
		add(getDloadTxt());
		
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
				
				new Reciept(StudentProfilePage.getIDField().getText(), StudentProfilePage.getNameField().getText(), Integer.parseInt(StudentProfilePage.getSemField().getText()), StudentProfilePage.getSecField().getText());
			}
		});
		dloadBtn.setHorizontalAlignment(SwingConstants.CENTER);
		dloadBtn.setForeground(Color.WHITE);
		dloadBtn.setFont(new Font("Eras Light ITC", Font.PLAIN, 28));
		dloadBtn.setBounds(646, 708, 162, 52);
		dloadBtn.setIcon(new ImageIcon(btnDefault));
		dloadBtn.setVisible(false);
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

	public AnimatedLabel getClearenceFormTxt() {
		return clearanceFormTxt;
	}

	public void setClearenceFormTxt(AnimatedLabel clearenceFormTxt) {
		this.clearanceFormTxt = clearenceFormTxt;
	}

	public JLabel getSuperbTxt() {
		return superbTxt;
	}

	public void setSuperbTxt(JLabel superbTxt) {
		this.superbTxt = superbTxt;
	}

	public JLabel getNoPunTxt() {
		return noPunTxt;
	}

	public void setNoPunTxt(JLabel noPunTxt) {
		this.noPunTxt = noPunTxt;
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
	
	public void showPunCourses(String query ) {
		
		java.sql.Connection c = null;
		java.sql.Statement s = null;
		ResultSet rs = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/iums", "root", "");
			s = c.createStatement();
			rs = s.executeQuery(query);
			
			DefaultTableModel model = (DefaultTableModel)punTable.getModel();
			
			while(rs.next()) {
				
				if(rs.getString("ID").equals(LogInPage.getLogInID().getText())) {
					
					scrollPane.setVisible(true);
					getDloadTxt().setVisible(true);
					dloadBtn.setVisible(true);
					
					if (HomePage.getLanguage().equals("bangla")) {

						Locale.setDefault(new Locale("bn", "BN"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");
						
						superbTxt.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 35));
						superbTxt.setText(r.getString("punished"));


					} else {

						Locale.setDefault(new Locale("en", "UK"));
						ResourceBundle r = ResourceBundle.getBundle("bundle");

						superbTxt.setFont(new Font("Eras Medium ITC", Font.PLAIN, 40));
						superbTxt.setText(r.getString("punished"));

						
					}
					
					String[] breakCourseNo = rs.getString("Course_no").split(" ");
					
					model.addRow(new Object[] {breakCourseNo[0] + " " + breakCourseNo[1], rs.getString("Course_title"),rs.getString("fine_amount") , rs.getString("Offence_Date"), rs.getString("Submitted_Date"), rs.getString("submitted_by")});
				}
			}

		} catch (Exception e) {

			System.out.println(e);
		}
	}

	public JLabel getDloadTxt() {
		return dloadTxt;
	}

	public void setDloadTxt(JLabel dloadTxt) {
		this.dloadTxt = dloadTxt;
	}
}
