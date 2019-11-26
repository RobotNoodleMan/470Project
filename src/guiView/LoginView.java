package guiView;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class LoginView extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JLabel lblUsername;
	protected JLabel lblPassword;
	protected JTextField txtUsername;
	protected JTextField txtPassword;
	protected JLabel lblFailedMsg; 
	protected JButton bttnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUsername = new JLabel("username: ");
		lblUsername.setBounds(61, 69, 128, 27);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("password: ");
		lblPassword.setBounds(61, 100, 101, 36);
		contentPane.add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(141, 69, 201, 27);
		contentPane.add(txtUsername);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(141, 105, 201, 27);
		contentPane.add(txtPassword);
		
		bttnLogin = new JButton("Login");
		bttnLogin.setBounds(141,147,128,27);
		contentPane.add(bttnLogin);
		
		lblFailedMsg = new JLabel("Failed login");
		lblFailedMsg.setBounds(141,147,128,27);
		contentPane.add(lblFailedMsg);
		
		//this.setVisible(true);
		
		
	}
}
