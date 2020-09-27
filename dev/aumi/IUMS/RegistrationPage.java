package dev.aumi.IUMS;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class RegistrationPage extends JPanel {

	private AnimatedLabel btnTxtStudent = new AnimatedLabel("Register", 1);
	private AnimatedLabel btnTxtTeacher = new AnimatedLabel("Register", 2);
	private AnimatedLabel lblStudentTxt = new AnimatedLabel("Student's section", 4);
	private AnimatedLabel lblTeacherTxt = new AnimatedLabel("Teacher's section", 5);

	private Image btnDefault = new ImageIcon(this.getClass().getResource("/btn layout.png")).getImage();
	private Image btnMouseIn = new ImageIcon(this.getClass().getResource("/btn layout(mouse in).png")).getImage();
	private Image btnReturnImg = new ImageIcon(this.getClass().getResource("/return.png")).getImage();
	private Image btnReturnImgMouseIn = new ImageIcon(this.getClass().getResource("/return(mouse in).png")).getImage();

	private Image imgStudent = new ImageIcon(this.getClass().getResource("/student.png")).getImage();
	private Image imgTeacher = new ImageIcon(this.getClass().getResource("/teacher.png")).getImage();

	/**
	 * Create the panel.
	 */
	public RegistrationPage() {

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

		FadeInImg lblStudent = new FadeInImg(imgStudent);
		lblStudent.setVerticalAlignment(SwingConstants.TOP);
		lblStudent.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudent.setBounds(new Rectangle(300, 150, 256, 256));
		add(lblStudent);

		getLblStudentTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getLblStudentTxt().setBounds(new Rectangle(230, 417, 400, 130));
		add(getLblStudentTxt());

		getBtnTxtStudent().setHorizontalAlignment(SwingConstants.CENTER);
		getBtnTxtStudent().setBounds(new Rectangle(350, 540, 162, 52));
		add(getBtnTxtStudent());

		FadeInImg btnStudent = new FadeInImg(btnDefault);
		btnStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				btnStudent.setIcon(new ImageIcon(btnMouseIn));
			}

			@Override
			public void mouseExited(MouseEvent e) {

				btnStudent.setIcon(new ImageIcon(btnDefault));
			}

			@Override
			public void mouseClicked(MouseEvent e) {


				if (HomePage.getLanguage().equals("bangla")) {

					Locale.setDefault(new Locale("bn", "BN"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					StudentRegistrationPage srp = new StudentRegistrationPage();
					
					srp.getTxtReg().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 24));
					srp.getTxtReg().setText(r.getString("studentReg"));
					srp.getTxtReg().setForeground(new Color(30, 30, 36));
					
					srp.getIDTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					srp.getIDTxt().setText(r.getString("ID"));
					srp.getIDTxt().setForeground(new Color(118 ,185, 0));
					
					srp.getEmailTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					srp.getEmailTxt().setText(r.getString("Email"));
					srp.getEmailTxt().setForeground(new Color(118 ,185, 0));
					
					srp.getRegTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					srp.getRegTxt().setText(r.getString("register"));
					srp.getRegTxt().setForeground(Color.WHITE);
					
					srp.getPassTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					srp.getPassTxt().setText(r.getString("password"));
					srp.getPassTxt().setForeground(new Color(118 ,185, 0));
					
					srp.getConPassTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					srp.getConPassTxt().setText(r.getString("conPass"));
					srp.getConPassTxt().setForeground(new Color(118 ,185, 0));
					
					srp.getRegPassNoti().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
					srp.getRegPassNoti().setText("<html>" + r.getString("regpassnoti") + "</html>");
					
					srp.getNotiSlide().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 19));
					srp.getNotiSlide().setText("<html>" + r.getString("mailnoti") + "</html>");
					
					srp.getOTPTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
					srp.getOTPTxt().setText(r.getString("insertotp"));
					
					srp.getOKTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					srp.getOKTxt().setText(r.getString("ok"));
					
					removeAll();
					revalidate();
					repaint();

					add(srp);

				} else {

					Locale.setDefault(new Locale("en", "UK")); 
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					StudentRegistrationPage srp = new StudentRegistrationPage();

					srp.getTxtReg().setFont(new Font("Eras Light ITC", Font.PLAIN, 24));
					srp.getTxtReg().setText(r.getString("studentReg"));
					srp.getTxtReg().setForeground(new Color(30, 30, 36));
					
					srp.getIDTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					srp.getIDTxt().setText(r.getString("ID"));
					srp.getIDTxt().setForeground(new Color(118, 185, 0));
					
					srp.getEmailTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					srp.getEmailTxt().setText(r.getString("Email"));
					srp.getEmailTxt().setForeground(new Color(118 ,185, 0));
					
					srp.getRegTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					srp.getRegTxt().setText(r.getString("register"));
					srp.getRegTxt().setForeground(Color.WHITE);
					
					srp.getPassTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					srp.getPassTxt().setText(r.getString("password"));
					srp.getPassTxt().setForeground(new Color(118 ,185, 0));
					
					srp.getConPassTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					srp.getConPassTxt().setText(r.getString("conPass"));
					srp.getConPassTxt().setForeground(new Color(118 ,185, 0));
					
					srp.getRegPassNoti().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					srp.getRegPassNoti().setText("<html>" + r.getString("regpassnoti") + "</html>");
					
					srp.getNotiSlide().setFont(new Font("Eras Light ITC", Font.PLAIN, 28));
					srp.getNotiSlide().setText("<html>" + r.getString("mailnoti") + "</html>");
					
					srp.getOTPTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					srp.getOTPTxt().setText(r.getString("insertotp"));
					
					srp.getOKTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 28));
					srp.getOKTxt().setText(r.getString("ok"));

					removeAll();
					revalidate();
					repaint();

					add(srp);

				}

			}

		});
		btnStudent.setBounds(new Rectangle(350, 540, 162, 52));
		add(btnStudent);

		getLblTeacherTxt().setHorizontalAlignment(SwingConstants.CENTER);
		getLblTeacherTxt().setBounds(new Rectangle(790, 417, 400, 130));
		add(getLblTeacherTxt());

		getBtnTxtTeacher().setHorizontalAlignment(SwingConstants.CENTER);
		getBtnTxtTeacher().setBounds(new Rectangle(910, 540, 162, 52));
		add(getBtnTxtTeacher());

		FadeInImg btnTeacher = new FadeInImg(btnDefault, 1);
		btnTeacher.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				btnTeacher.setIcon(new ImageIcon(btnMouseIn));
			}

			@Override
			public void mouseExited(MouseEvent e) {

				btnTeacher.setIcon(new ImageIcon(btnDefault));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (HomePage.getLanguage().equals("bangla")) {

					Locale.setDefault(new Locale("bn", "BN"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					TeacherRegistrationPage trp = new TeacherRegistrationPage();

					trp.getTxtReg().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 24));
					trp.getTxtReg().setText(r.getString("teacherReg"));
					trp.getTxtReg().setForeground(new Color(30, 30, 36));
					
					trp.getIDTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					trp.getIDTxt().setText(r.getString("ID"));
					trp.getIDTxt().setForeground(new Color(118 ,185, 0));
					
					trp.getEmailTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					trp.getEmailTxt().setText(r.getString("Email"));
					trp.getEmailTxt().setForeground(new Color(118 ,185, 0));
					
					trp.getRegTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					trp.getRegTxt().setText(r.getString("register"));
					trp.getRegTxt().setForeground(Color.WHITE);
					
					trp.getPassTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					trp.getPassTxt().setText(r.getString("password"));
					trp.getPassTxt().setForeground(new Color(118 ,185, 0));
					
					trp.getConPassTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
					trp.getConPassTxt().setText(r.getString("conPass"));
					trp.getConPassTxt().setForeground(new Color(118 ,185, 0));
					
					trp.getRegPassNoti().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
					trp.getRegPassNoti().setText("<html>" + r.getString("regpassnoti") + "</html>");
					
					trp.getNotiSlide().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 19));
					trp.getNotiSlide().setText("<html>" + r.getString("mailnoti") + "</html>");
					
					trp.getOTPTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
					trp.getOTPTxt().setText(r.getString("insertotp"));
					
					trp.getOKTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 19));
					trp.getOKTxt().setText(r.getString("ok"));
					
					removeAll();
					revalidate();
					repaint();

					add(trp);

				} else {

					Locale.setDefault(new Locale("en", "UK")); 
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					TeacherRegistrationPage trp = new TeacherRegistrationPage();

					trp.getTxtReg().setFont(new Font("Eras Light ITC", Font.PLAIN, 24));
					trp.getTxtReg().setText(r.getString("teacherReg"));
					trp.getTxtReg().setForeground(new Color(30, 30, 36));
					
					trp.getIDTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					trp.getIDTxt().setText(r.getString("ID"));
					trp.getIDTxt().setForeground(new Color(118, 185, 0));
					
					trp.getEmailTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					trp.getEmailTxt().setText(r.getString("Email"));
					trp.getEmailTxt().setForeground(new Color(118 ,185, 0));
					
					trp.getRegTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					trp.getRegTxt().setText(r.getString("register"));
					trp.getRegTxt().setForeground(Color.WHITE);
					
					trp.getPassTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					trp.getPassTxt().setText(r.getString("password"));
					trp.getPassTxt().setForeground(new Color(118 ,185, 0));
					
					trp.getConPassTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					trp.getConPassTxt().setText(r.getString("conPass"));
					trp.getConPassTxt().setForeground(new Color(118 ,185, 0));
					
					trp.getRegPassNoti().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					trp.getRegPassNoti().setText("<html>" + r.getString("regpassnoti") + "</html>");
					
					trp.getNotiSlide().setFont(new Font("Eras Light ITC", Font.PLAIN, 28));
					trp.getNotiSlide().setText("<html>" + r.getString("mailnoti") + "</html>");
					
					trp.getOTPTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 20));
					trp.getOTPTxt().setText(r.getString("insertotp"));
					
					trp.getOKTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 28));
					trp.getOKTxt().setText(r.getString("ok"));

					removeAll();
					revalidate();
					repaint();

					add(trp);

				}
				
			}
		});
		btnTeacher.setBounds(new Rectangle(910, 540, 162, 52));
		add(btnTeacher);

		FadeInImg lblTeacher = new FadeInImg(imgTeacher);
		lblTeacher.setVerticalAlignment(SwingConstants.TOP);
		lblTeacher.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeacher.setBounds(new Rectangle(864, 150, 256, 256));
		add(lblTeacher);

		// Return button

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

					LogInPage lip = new LogInPage();

					lip.getLogInTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 30));
					lip.getLogInTxt().setText(r.getString("account"));
					
					lip.getIDTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 25));
					lip.getIDTxt().setText(r.getString("ID"));
					
					lip.getPassTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 25));
					lip.getPassTxt().setText(r.getString("password"));
					
					lip.getRememberMe().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
					lip.getRememberMe().setText(r.getString("remember"));
					
					lip.getLogInBtnTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 24));
					lip.getLogInBtnTxt().setText(r.getString("login"));

					lip.getLblRegedOrNot().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 17));
					lip.getLblRegedOrNot().setBounds(new Rectangle(500, 750, 200, 50));
					lip.getLblRegedOrNot().setText(r.getString("regTxt"));
					lip.getLblRegedOrNot().setForeground(new Color(30, 30, 36));

					lip.getLblRegNowTxt().setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
					lip.getLblRegNowTxt().setText(r.getString("regNow"));
					lip.getLblRegNowTxt().setForeground(new Color(30, 30, 36));

					removeAll();
					revalidate();
					repaint();
					add(lip);

				} else {

					Locale.setDefault(new Locale("en", "UK"));
					ResourceBundle r = ResourceBundle.getBundle("bundle");

					LogInPage lip = new LogInPage();

					lip.getLogInTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 30));
					lip.getLogInTxt().setText(r.getString("account"));
					
					lip.getIDTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					lip.getIDTxt().setText(r.getString("ID"));
					
					lip.getPassTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 25));
					lip.getPassTxt().setText(r.getString("password"));
					
					lip.getRememberMe().setFont(new Font("Eras Light ITC", Font.PLAIN, 15));
					lip.getRememberMe().setText(r.getString("remember"));
					
					lip.getLogInBtnTxt().setFont(new Font("Eras Light ITC", Font.PLAIN, 24));
					lip.getLogInBtnTxt().setText(r.getString("login"));

					lip.getLblRegedOrNot().setFont(new Font("Eras Medium ITC", Font.PLAIN, 17));
					lip.getLblRegedOrNot().setBounds(new Rectangle(550, 750, 200, 50));
					lip.getLblRegedOrNot().setText(r.getString("regTxt"));
					lip.getLblRegedOrNot().setForeground(new Color(30, 30, 36));

					lip.getLblRegNowTxt().setFont(new Font("Eras Medium ITC", Font.PLAIN, 15));
					lip.getLblRegNowTxt().setText(r.getString("regNow"));
					lip.getLblRegNowTxt().setForeground(new Color(30, 30, 36));

					removeAll();
					revalidate();
					repaint();
					add(lip);

				}

			}
		});
		btnReturn.setBounds(new Rectangle(15, 50, 32, 32));
		btnReturn.setIcon(new ImageIcon(btnReturnImg.getScaledInstance(btnReturn.getWidth(), btnReturn.getHeight(), Image.SCALE_SMOOTH)));
		add(btnReturn);

	}

	public AnimatedLabel getBtnTxtStudent() {
		return btnTxtStudent;
	}

	public void setBtnTxtStudent(AnimatedLabel btnTxtStudent) {
		this.btnTxtStudent = btnTxtStudent;
	}

	public AnimatedLabel getBtnTxtTeacher() {
		return btnTxtTeacher;
	}

	public void setBtnTxtTeacher(AnimatedLabel btnTxtTeacher) {
		this.btnTxtTeacher = btnTxtTeacher;
	}

	public AnimatedLabel getLblStudentTxt() {
		return lblStudentTxt;
	}

	public void setLblStudentTxt(AnimatedLabel lblStudentTxt) {
		this.lblStudentTxt = lblStudentTxt;
	}

	public AnimatedLabel getLblTeacherTxt() {
		return lblTeacherTxt;
	}

	public void setLblTeacherTxt(AnimatedLabel lblTeacherTxt) {
		this.lblTeacherTxt = lblTeacherTxt;
	}

}
