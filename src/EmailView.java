import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

public class EmailView extends JFrame {
	protected JPanel contentPane;
    protected JTextField txtPropID;
    protected JLabel lblPropID;
    protected JLabel lblMsgLandlord;
    protected JTextArea txtMsgLandlord;
    protected JButton btnSendEmail;

    public EmailView() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(100, 100, 525, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
        txtPropID = new JTextField ();
        lblPropID = new JLabel ("Property ID:");
        lblMsgLandlord = new JLabel ("Message to Landlord:");
        txtMsgLandlord = new JTextArea (5, 5);
        btnSendEmail = new JButton ("Send Email");

        txtPropID.setBounds (100, 35, 75, 25);
        lblPropID.setBounds (30, 35, 100, 25);
        lblMsgLandlord.setBounds (30, 90, 130, 20);
        txtMsgLandlord.setBounds (30, 110, 455, 95);
        btnSendEmail.setBounds (205, 230, 100, 25);
        
        contentPane.add (txtPropID);
        contentPane.add (lblPropID);
        contentPane.add (lblMsgLandlord);
        contentPane.add (txtMsgLandlord);
        contentPane.add (btnSendEmail);


        
    }


    public static void main (String[] args) {
        EmailView frame = new EmailView ();

        frame.setVisible (true);
    }
}
