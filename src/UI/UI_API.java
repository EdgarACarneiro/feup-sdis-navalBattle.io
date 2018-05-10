package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.CardLayout;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;


public class UI_API {

	private JFrame frmBattleshipio;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI_API window = new UI_API();
					window.frmBattleshipio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI_API() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBattleshipio = new JFrame();
		frmBattleshipio.setTitle("BattleShip.io");
		frmBattleshipio.setBounds(100, 100, 500, 400);
		frmBattleshipio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        passwordField = new JPasswordField();
        frmBattleshipio.getContentPane().add(passwordField);
        textPane.setVisible(true);
		passwordField.setVisible(false);
		
		JMenuBar jmb = new JMenuBar();

        JMenu jmFile = new JMenu("File");
        JMenuItem jmiLogin = new JMenuItem("Login");
        jmiLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		textPane.setVisible(false);
        		passwordField.setVisible(true);
        	}
        });
        JMenuItem jmiHost = new JMenuItem("Host");
        JMenuItem jmiExit = new JMenuItem("Exit");
        jmFile.add(jmiLogin);
        jmFile.add(jmiHost);
        jmFile.addSeparator();
        jmFile.add(jmiExit);
        jmb.add(jmFile);

        JMenu jmOptions = new JMenu("Options");
        JMenu profile = new JMenu("Profile");
        JMenuItem changeColor = new JMenuItem("Change Color");
        JMenuItem changeUsername = new JMenuItem("Change Username");
        profile.add(changeColor);
        profile.add(changeUsername);
        jmOptions.add(profile);

        JMenu moreOptions = new JMenu("More Options");
        moreOptions.add(new JMenuItem("A"));
        moreOptions.add(new JMenuItem("B"));
        jmOptions.add(moreOptions);

        jmb.add(jmOptions);

        JMenu jmHelp = new JMenu("Help");
        JMenuItem jmiAbout = new JMenuItem("About");
        jmHelp.add(jmiAbout);
        jmb.add(jmHelp);

        frmBattleshipio.getContentPane().add(textPane);
        frmBattleshipio.setJMenuBar(jmb);
        frmBattleshipio.getContentPane().setLayout(new CardLayout(0, 0));
        
        frmBattleshipio.setVisible(true);
	}
}
