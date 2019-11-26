package guiView;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ChangePropertyView extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
    protected JTextField txtPropID;
    protected JLabel lblPropID;
    protected JComboBox<String> dropState;
    protected JLabel lblState;
    protected JLabel lblStateNames;
    protected JButton btnConfirm;

    public ChangePropertyView() {
	        //construct preComponents
	        String[] dropStateItems = {"0", "1", "2"};
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	setBounds(100, 100, 375, 260);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
	        //construct components
	        txtPropID = new JTextField (5);
	        lblPropID = new JLabel ("Property ID:");
	        dropState = new JComboBox<String> (dropStateItems);
	        lblState = new JLabel ("New State:");
	        lblStateNames = new JLabel ("cancelled = 0, active = 1, rented = 2");
	        btnConfirm = new JButton ("Confirm");

	        //add components
	        contentPane.add (txtPropID);
	        contentPane.add (lblPropID);
	        contentPane.add (dropState);
	        contentPane.add (lblState);
	        contentPane.add (lblStateNames);
	        contentPane.add (btnConfirm);

	        txtPropID.setBounds (155, 55, 100, 25);
	        lblPropID.setBounds (45, 55, 100, 25);
	        dropState.setBounds (155, 95, 100, 25);
	        lblState.setBounds (45, 95, 100, 25);
	        lblStateNames.setBounds (115, 120, 230, 25);
	        btnConfirm.setBounds (125, 190, 100, 25);
	}

    public ChangePropertyView(int i) {
        //construct preComponents
        String[] dropStateItems = {"0", "1", "2", "3"};
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(100, 100, 375, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
        //construct components
        txtPropID = new JTextField (5);
        lblPropID = new JLabel ("Property ID:");
        dropState = new JComboBox<String> (dropStateItems);
        lblState = new JLabel ("New State:");
        lblStateNames = new JLabel ("cancelled = 0, active = 1, rented = 2, \nsuspended = 3");
        btnConfirm = new JButton ("Confirm");

        //add components
        contentPane.add (txtPropID);
        contentPane.add (lblPropID);
        contentPane.add (dropState);
        contentPane.add (lblState);
        contentPane.add (lblStateNames);
        contentPane.add (btnConfirm);

        txtPropID.setBounds (155, 55, 100, 25);
        lblPropID.setBounds (45, 55, 100, 25);
        dropState.setBounds (155, 95, 100, 25);
        lblState.setBounds (45, 95, 100, 25);
        lblStateNames.setBounds (45, 125, 300, 20);
        btnConfirm.setBounds (125, 190, 100, 25);
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
