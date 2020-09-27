package dev.aumi.IUMS;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class IUMS {

	private JFrame mainFrame = new JFrame();

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IUMS window = new IUMS();
					window.getMainFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IUMS() {

		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {

		getMainFrame().setBounds(50, 10, 1420, 800);
		getMainFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getMainFrame().setUndecorated(true); // to make border less
		
		getMainFrame().setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("logo.png"))); // This is to change the task bar icon

		HomePage hp = new HomePage();
		
		getMainFrame().getContentPane().add(hp);
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

}
