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
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;


public class UI_API {

	private JFrame frmBattleshipio;
	private JPasswordField passwordField;
	private JTextField username;
	private boolean login = true;
    private JTextPane textPane;

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
        
        //Declarations
        textPane = new JTextPane();
        JPanel loginPanel = new JPanel();
        JPanel welcomePanel = new JPanel();
        JPanel mainPanel = new JPanel();
        JPanel aboutPanel = new JPanel();
        JMenu jmHelp = new JMenu("Help");
        JMenu jmOptions = new JMenu("Options");
        JMenu jmFile = new JMenu("File");
        JMenu profile = new JMenu("Profile");
        JMenu moreOptions = new JMenu("More Options");
        JMenuItem jmiLogin = new JMenuItem("Login");
        JMenuItem jmiRegister = new JMenuItem("Register");
        JMenuItem jmiExit = new JMenuItem("Exit");
        JMenuItem jmiAbout = new JMenuItem("About");
        JMenuItem changeColor = new JMenuItem("Change Color");
        JMenuItem changeUsername = new JMenuItem("Change Username");
        JLabel lblLogin = new JLabel("Login");
        JLabel lblWelcome = new JLabel("Welcome to BattleShip.io");
        JButton btnLogin = new JButton("Login");
        JButton btnToLogin = new JButton("Login");
        JButton btnToRegister = new JButton("Register");
        JTextArea aboutText = new JTextArea();
		JMenuBar jmb = new JMenuBar();
		
		//Menu Bar
        jmb.add(jmFile);
        jmb.add(jmOptions);
        jmb.add(jmHelp);

        jmOptions.setEnabled(false);
        jmOptions.add(profile);
        jmOptions.add(moreOptions);
        
        moreOptions.add(new JMenuItem("A"));
        moreOptions.add(new JMenuItem("B"));
        
        profile.add(changeColor);
        profile.add(changeUsername);
        
        jmFile.add(jmiLogin);
        jmFile.add(jmiRegister);
        jmFile.addSeparator();
        jmFile.add(jmiExit);
        
        jmHelp.add(jmiAbout);
        
        frmBattleshipio.setJMenuBar(jmb);
        
        //Layouts		
        GroupLayout gl_welcomePanel = new GroupLayout(welcomePanel);
        GroupLayout gl_loginPanel = new GroupLayout(loginPanel);
        GroupLayout gl_aboutPanel = new GroupLayout(aboutPanel);

        frmBattleshipio.getContentPane().add(welcomePanel, "name_3507367092678");
        frmBattleshipio.getContentPane().add(mainPanel, "name_2194987317518");
        frmBattleshipio.getContentPane().add(loginPanel, "name_2179697515965");
        frmBattleshipio.getContentPane().add(aboutPanel);

        mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
        aboutPanel.setLayout(gl_aboutPanel);     
        welcomePanel.setLayout(gl_welcomePanel);        
        loginPanel.setLayout(gl_loginPanel);        
        mainPanel.add(textPane);

        //Login Page
        username = new JTextField();
        username.setHorizontalAlignment(SwingConstants.CENTER);
        username.setColumns(10);
        passwordField = new JPasswordField();
        passwordField.setToolTipText("");
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);      
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);        
        textPane.setEditable(false);   
        
        //About Page
        aboutText.setWrapStyleWord(true);
        aboutText.setBackground(UIManager.getColor("CheckBox.background"));
        aboutText.setLineWrap(true);
        aboutText.setFont(new Font("Garuda", Font.PLAIN, 15));
        aboutText.setTabSize(0);
        aboutText.setEditable(false);
        aboutText.setColumns(3);
        aboutText.setText("sda\ndasda\nda\nsd\nas\ndas\ndadas");
        
        //Group Layouts
        gl_welcomePanel.setHorizontalGroup(
        	gl_welcomePanel.createParallelGroup(Alignment.TRAILING)
        		.addComponent(lblWelcome, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        		.addGroup(gl_welcomePanel.createSequentialGroup()
        			.addGap(132)
        			.addComponent(btnToRegister, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addGap(38)
        			.addComponent(btnToLogin, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
        			.addGap(134))
        );
        
        gl_welcomePanel.setVerticalGroup(
        	gl_welcomePanel.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_welcomePanel.createSequentialGroup()
        			.addGap(60)
        			.addComponent(lblWelcome, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
        			.addGap(28)
        			.addGroup(gl_welcomePanel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnToRegister)
        				.addComponent(btnToLogin))
        			.addContainerGap(127, Short.MAX_VALUE))
        );
        
        gl_aboutPanel.setHorizontalGroup(
        	gl_aboutPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_aboutPanel.createSequentialGroup()
        			.addGap(54)
        			.addComponent(aboutText, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
        			.addGap(51))
        );
        
        gl_aboutPanel.setVerticalGroup(
        	gl_aboutPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_aboutPanel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(aboutText, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        			.addGap(32))
        );
        
        gl_loginPanel.setHorizontalGroup(
        	gl_loginPanel.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_loginPanel.createSequentialGroup()
        			.addGroup(gl_loginPanel.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_loginPanel.createSequentialGroup()
        					.addGap(262)
        					.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        				.addGroup(gl_loginPanel.createSequentialGroup()
        					.addGap(104)
        					.addGroup(gl_loginPanel.createParallelGroup(Alignment.TRAILING)
        						.addComponent(passwordField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
        						.addComponent(username, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))))
        			.addGap(113))
        		.addGroup(Alignment.LEADING, gl_loginPanel.createSequentialGroup()
        			.addGap(187)
        			.addComponent(lblLogin, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(202, Short.MAX_VALUE))
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
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(btnLogin)
        			.addContainerGap(59, Short.MAX_VALUE))
        );
        
        //Action Listeners
        btnToLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		lblLogin.setText("Login");
                btnLogin.setText("Login");
                login = true;
        		loginPanel.setVisible(true);
                welcomePanel.setVisible(false);
                mainPanel.setVisible(false);
                aboutPanel.setVisible(false);
        	}
        });
        
        btnToRegister.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                lblLogin.setText("Register");
                btnLogin.setText("Register");
                login = false;
        		loginPanel.setVisible(true);
                welcomePanel.setVisible(false);
                mainPanel.setVisible(false);
                aboutPanel.setVisible(false);
        	}
        });
        
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(login) {
        			System.out.println("Login");	
        		}
        		else {
        			System.out.println("Register");        			
        		}
        		jmOptions.setEnabled(true);
        	}
        });
        
        jmiLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		lblLogin.setText("Login");
                btnLogin.setText("Login");
                login = true;
        		loginPanel.setVisible(true);
                welcomePanel.setVisible(false);
                mainPanel.setVisible(false);
                aboutPanel.setVisible(false);
        	}
        });
        
        jmiRegister.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		lblLogin.setText("Register");
                btnLogin.setText("Register");
                login = false;
        		loginPanel.setVisible(true);
                welcomePanel.setVisible(false);
                mainPanel.setVisible(false);
                aboutPanel.setVisible(false);
        	}
        });
        
        jmiExit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
 
        jmiAbout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		welcomePanel.setVisible(false);
        		loginPanel.setVisible(false);
        		mainPanel.setVisible(false);
        		aboutPanel.setVisible(true);
        		frmBattleshipio.setTitle("BattleShip.io : About");
        		
        	}
        });
        
        frmBattleshipio.setVisible(true);
	}
	
	public void printMap(String map) {
		textPane.setText(map);
	}
}
