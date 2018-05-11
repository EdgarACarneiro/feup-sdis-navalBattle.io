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
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import java.awt.Font;


public class UI_API {

	private JFrame frmBattleshipio;
	private JPasswordField passwordField;
	private JTextField username;

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
		frmBattleshipio.setIconImage(Toolkit.getDefaultToolkit().getImage(UI_API.class.getResource("/UI/bomb (1).png")));
		frmBattleshipio.setTitle("BattleShip.io");
		frmBattleshipio.setBounds(100, 100, 500, 400);
		frmBattleshipio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmBattleshipio.getContentPane().setLayout(new CardLayout(0, 0));
        
        JPanel loginPanel = new JPanel();
        
        JPanel welcomePanel = new JPanel();
        frmBattleshipio.getContentPane().add(welcomePanel, "name_3507367092678");
        
        JLabel lblWelcome = new JLabel("Welcome to BattleShip.io");
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        
        JButton btnLogin = new JButton("Login");
        
        JButton btnHost = new JButton("Host");
        GroupLayout gl_welcomePanel = new GroupLayout(welcomePanel);
        gl_welcomePanel.setHorizontalGroup(
        	gl_welcomePanel.createParallelGroup(Alignment.LEADING)
        		.addComponent(lblWelcome, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
        		.addGroup(Alignment.TRAILING, gl_welcomePanel.createSequentialGroup()
        			.addContainerGap(148, Short.MAX_VALUE)
        			.addComponent(btnHost, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
        			.addGap(38)
        			.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
        			.addGap(151))
        );
        gl_welcomePanel.setVerticalGroup(
        	gl_welcomePanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, gl_welcomePanel.createSequentialGroup()
        			.addContainerGap(60, Short.MAX_VALUE)
        			.addComponent(lblWelcome, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
        			.addGap(28)
        			.addGroup(gl_welcomePanel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnHost)
        				.addComponent(btnLogin))
        			.addGap(127))
        );
        welcomePanel.setLayout(gl_welcomePanel);
        JPanel mainPanel = new JPanel();
        JTextPane textPane = new JTextPane();
        
        frmBattleshipio.getContentPane().add(mainPanel, "name_2194987317518");
        mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
        mainPanel.add(textPane);
        
        textPane.setEditable(false);
        frmBattleshipio.getContentPane().add(loginPanel, "name_2179697515965");
        
        username = new JTextField();
        username.setHorizontalAlignment(SwingConstants.CENTER);
        username.setColumns(10);
        passwordField = new JPasswordField();
        passwordField.setToolTipText("");
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel lblLogin = new JLabel("Login");
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        GroupLayout gl_loginPanel = new GroupLayout(loginPanel);
        gl_loginPanel.setHorizontalGroup(
        	gl_loginPanel.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_loginPanel.createSequentialGroup()
        			.addGap(223)
        			.addComponent(lblLogin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addGap(238))
        		.addGroup(Alignment.LEADING, gl_loginPanel.createSequentialGroup()
        			.addGap(104)
        			.addGroup(gl_loginPanel.createParallelGroup(Alignment.TRAILING)
        				.addComponent(passwordField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
        				.addComponent(username, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
        			.addGap(113))
        );
        gl_loginPanel.setVerticalGroup(
        	gl_loginPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_loginPanel.createSequentialGroup()
        			.addGap(80)
        			.addComponent(lblLogin)
        			.addGap(50)
        			.addComponent(username, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(98, Short.MAX_VALUE))
        );
        loginPanel.setLayout(gl_loginPanel);
        
        JPanel aboutPanel = new JPanel();
        frmBattleshipio.getContentPane().add(aboutPanel, "name_5666007561710");
        aboutPanel.setLayout(new BorderLayout(0, 0));
        
        JTextArea txtpnDasdas = new JTextArea();
        txtpnDasdas.setLineWrap(true);
        txtpnDasdas.setFont(new Font("Garuda", Font.PLAIN, 15));
        txtpnDasdas.setTabSize(0);
        txtpnDasdas.setEditable(false);
        txtpnDasdas.setColumns(3);
        txtpnDasdas.setText("sda\ndasda\nda\nsd\nas\ndas\ndadas");
        aboutPanel.add(txtpnDasdas, BorderLayout.CENTER);
		
		JMenuBar jmb = new JMenuBar();

        JMenu jmFile = new JMenu("File");
        JMenuItem jmiLogin = new JMenuItem("Login");
        jmiLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		loginPanel.setVisible(true);
        	}
        });
        JMenuItem jmiHost = new JMenuItem("Host");
        JMenuItem jmiExit = new JMenuItem("Exit");
        jmiExit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        jmFile.add(jmiLogin);
        jmFile.add(jmiHost);
        jmFile.addSeparator();
        jmFile.add(jmiExit);
        jmb.add(jmFile);

        JMenu jmOptions = new JMenu("Options");
        jmOptions.setEnabled(false);
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
        jmiAbout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		frmBattleshipio.setTitle("BattleShip.io : About");
        		aboutPanel.setVisible(true);
        		
        	}
        });
        jmHelp.add(jmiAbout);
        jmb.add(jmHelp);
        frmBattleshipio.setJMenuBar(jmb);
        
        frmBattleshipio.setVisible(true);
	}
}
