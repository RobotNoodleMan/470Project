import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ChangePropertyView extends JFrame {
	
	protected JPanel contentPane;
	protected JLabel lblPropID;
	protected JLabel lbNewState;
	protected JTextField txtPropID;
	protected JTextField txtNewState;
	protected JLabel lblFailedMsg; 
	protected JButton bttnConfirm;
	
	public ChangePropertyView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblPropID = new JLabel("Property ID: ");
		lblPropID.setBounds(61, 69, 128, 27);
		contentPane.add(lblPropID);
		
		lbNewState = new JLabel("New State: ");
		lbNewState.setBounds(61, 100, 101, 36);
		contentPane.add(lbNewState);
		
		txtPropID = new JTextField();
		txtPropID.setBounds(141, 69, 201, 27);
		contentPane.add(txtPropID);
		
		txtNewState = new JTextField();
		txtNewState.setBounds(141, 105, 201, 27);
		contentPane.add(txtNewState);
		
		bttnConfirm = new JButton("Confirm");
		bttnConfirm.setBounds(141,147,128,27);
		contentPane.add(bttnConfirm);
		
	}

public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				ChangePropertyView frame = new ChangePropertyView();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
  }
}
