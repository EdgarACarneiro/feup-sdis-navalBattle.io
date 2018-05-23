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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JPasswordField;
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
import javax.swing.JComboBox;

public class UI_API {

	private JFrame frmBattleshipio;
	private JPasswordField passwordField;
	private JTextField usernameddd;
	private boolean login = true;
    private JTextPane textPane;
    private JTextField yField;
    private JTextField xField;
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
		frmBattleshipio.setBounds(100, 100, 500, 419);
		frmBattleshipio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmBattleshipio.getContentPane().setLayout(new CardLayout(0, 0));
        
        //Declarations
        textPane = new JTextPane();
        JPanel loginPanel = new JPanel();
        JPanel welcomePanel = new JPanel();
        JPanel mainPanel = new JPanel();
        JPanel aboutPanel = new JPanel();
        JPanel profilePanel = new JPanel();
        JMenu jmHelp = new JMenu("Help");
        JMenu jmOptions = new JMenu("Options");
        JMenu jmFile = new JMenu("File");
        JMenu moreOptions = new JMenu("More Options");
        JMenuItem jmiConnect = new JMenuItem("Play");
        JMenuItem editProfile = new JMenuItem("Edit Profile");
        JMenuItem jmiExit = new JMenuItem("Exit");
        JMenuItem jmiAbout = new JMenuItem("About");
        JLabel lblLogin = new JLabel("Login");
        JLabel lblWelcome = new JLabel("Welcome to BattleShip.io");
        JLabel lblEditProfile = new JLabel("Edit Profile");
        lblEditProfile.setFont(new Font("Dialog", Font.BOLD, 18));
        lblWelcome.setFont(new Font("Dialog", Font.BOLD, 18));
        JButton btnLogin = new JButton("Login");
        JButton btnToLogin = new JButton("Login");
        JButton btnToRegister = new JButton("Register");
        JButton sendButton = new JButton("Send");
        JButton btnSubmit = new JButton("Save");   
        JTextArea aboutText = new JTextArea();
		JMenuBar jmb = new JMenuBar();
        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setFont(new Font("Dialog", Font.BOLD, 14));
        comboBox.addItem("Red");
        comboBox.addItem("Blue");
        comboBox.addItem("Yellow");
        comboBox.addItem("Green");
        username = new JTextField();
        username.setFont(new Font("Dialog", Font.PLAIN, 14));
        username.setText("username");
        username.setColumns(10);
        textPane.setEditable(false);   
        
        yField = new JTextField();
        yField.setText("y");
        yField.setColumns(10);
        
        xField = new JTextField();
        xField.setText("x");
        xField.setColumns(10);
        
		//Menu Bar
        jmb.add(jmFile);
        jmb.add(jmOptions);
        jmb.add(jmHelp);

        jmOptions.add(editProfile);
        jmOptions.add(moreOptions);
        
        moreOptions.add(new JMenuItem("A"));
        moreOptions.add(new JMenuItem("B"));
        
        jmFile.add(jmiConnect);
        jmFile.addSeparator();
        jmFile.add(jmiExit);
        
        jmHelp.add(jmiAbout);
        
        frmBattleshipio.setJMenuBar(jmb);
        
        //Layouts		
        GroupLayout gl_welcomePanel = new GroupLayout(welcomePanel);
        GroupLayout gl_loginPanel = new GroupLayout(loginPanel);
        GroupLayout gl_aboutPanel = new GroupLayout(aboutPanel);
        GroupLayout gl_mainPanel = new GroupLayout(mainPanel);
        GroupLayout gl_profilePanel = new GroupLayout(profilePanel);

        frmBattleshipio.getContentPane().add(welcomePanel, "name_3507367092678");
        frmBattleshipio.getContentPane().add(mainPanel, "name_2194987317518");
        frmBattleshipio.getContentPane().add(loginPanel, "name_2179697515965");
        frmBattleshipio.getContentPane().add(profilePanel, "name_2209926227877");
        frmBattleshipio.getContentPane().add(aboutPanel);
        aboutPanel.setLayout(gl_aboutPanel);     
        welcomePanel.setLayout(gl_welcomePanel);        
        loginPanel.setLayout(gl_loginPanel);
        profilePanel.setLayout(gl_profilePanel);
        mainPanel.setLayout(gl_mainPanel);

        //Login Page
        usernameddd = new JTextField();
        usernameddd.setHorizontalAlignment(SwingConstants.CENTER);
        usernameddd.setColumns(10);
        passwordField = new JPasswordField();
        passwordField.setToolTipText("");
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);      
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);   
        
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
        gl_mainPanel.setHorizontalGroup(
        	gl_mainPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_mainPanel.createSequentialGroup()
        			.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING)
        				.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        				.addGroup(gl_mainPanel.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(xField, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(yField, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
        					.addGap(18)
        					.addComponent(sendButton, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
        					.addGap(21)))
        			.addGap(0))
        );
        gl_mainPanel.setVerticalGroup(
        	gl_mainPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_mainPanel.createSequentialGroup()
        			.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
        			.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING)
        				.addGroup(Alignment.TRAILING, gl_mainPanel.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_mainPanel.createSequentialGroup()
        					.addGap(7)
        					.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(yField, Alignment.TRAILING)
        						.addComponent(xField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))))
        			.addGap(0))
        );
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
        						.addComponent(usernameddd, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))))
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
        			.addComponent(usernameddd, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(btnLogin)
        			.addContainerGap(59, Short.MAX_VALUE))
        );
        
        gl_profilePanel.setHorizontalGroup(
        	gl_profilePanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_profilePanel.createSequentialGroup()
        			.addGroup(gl_profilePanel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_profilePanel.createSequentialGroup()
        					.addGap(182)
        					.addComponent(lblEditProfile, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addGap(18))
        				.addGroup(gl_profilePanel.createSequentialGroup()
        					.addGap(169)
        					.addGroup(gl_profilePanel.createParallelGroup(Alignment.LEADING)
        						.addComponent(btnSubmit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(comboBox, Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(username, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))))
        			.addGap(188))
        );
        
        gl_profilePanel.setVerticalGroup(
        	gl_profilePanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_profilePanel.createSequentialGroup()
        			.addGap(66)
        			.addComponent(lblEditProfile)
        			.addGap(39)
        			.addComponent(username, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
        			.addGap(32)
        			.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(29)
        			.addComponent(btnSubmit)
        			.addContainerGap(83, Short.MAX_VALUE))
        );
        
        //Action Listeners
        btnToLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		lblLogin.setText("Login");
                btnLogin.setText("Login");
                login = true;
        		profilePanel.setVisible(false);
                welcomePanel.setVisible(false);
                mainPanel.setVisible(true);
                aboutPanel.setVisible(false);
        	}
        });
        
        btnToRegister.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                lblLogin.setText("Register");
                btnLogin.setText("Register");
                login = false;
        		profilePanel.setVisible(true);
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
        
        jmiConnect.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		lblLogin.setText("Login");
                btnLogin.setText("Login");
                login = true;
        		profilePanel.setVisible(false);
                welcomePanel.setVisible(false);
                mainPanel.setVisible(true);
                aboutPanel.setVisible(false);
                frmBattleshipio.setTitle("BattleShip.io : Play");  
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
        		profilePanel.setVisible(false);
        		mainPanel.setVisible(false);
        		aboutPanel.setVisible(true);
        		frmBattleshipio.setTitle("BattleShip.io : About");        		
        	}
        });
        
        editProfile.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		welcomePanel.setVisible(false);
        		profilePanel.setVisible(true);
        		mainPanel.setVisible(false);
        		aboutPanel.setVisible(false);
        		frmBattleshipio.setTitle("BattleShip.io : Edit Profile");
        	}
        });
        
        btnSubmit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		//TODO - Send Changes
        	}
        });  
        
        username.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent fe) {
            	if (username.getText().equals("username")) {
                    username.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                if (username.getText().length() <=1 || username.getText().equals("username")) {
                    username.setText("username");
                }
            }
        });   
        
        xField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent fe) {
            	if (xField.getText().equals("x")) {
            		xField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                if (xField.getText().length() == 0 || xField.getText().equals("x")) {
                	xField.setText("x");
                }
            }
        });   
        
        yField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent fe) {
            	if (yField.getText().equals("y")) {
            		yField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                if (yField.getText().length() == 0 || yField.getText().equals("y")) {
                	yField.setText("y");
                }
            }
        }); 
               
        frmBattleshipio.setVisible(true);
	}
	
	public void printMap(String map) {
		textPane.setText(map);
	}
}
