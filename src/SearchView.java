import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class SearchView extends JFrame {

	private JPanel contentPane;
	private JLabel fieldpropType;
	private JLabel labelBed;
	private JLabel labelBath;
	private JLabel labelQuadrant;
	private JLabel labelFurniture;
	protected JTextField textBed;
	protected JTextField textBath;
	protected JComboBox boxQuadrant;
	protected DefaultComboBoxModel quadrantModel;
	private Vector <String> quadrants;
	protected JComboBox boxpropType;
	private Vector <String> propertyType;
	protected DefaultComboBoxModel propertyModel;
	protected JComboBox checkFurniture;
	protected DefaultComboBoxModel furnitureModel;
	private Vector <String> furniture;
	protected JButton bttnsetSearch;

	/**
	 * Create the frame.
	 */
	public SearchView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 349, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		buildVectors();
		
		fieldpropType = new JLabel("Property Type");
		fieldpropType.setBounds(55, 57, 101, 20);
		contentPane.add(fieldpropType);
		
		boxpropType = new JComboBox (propertyModel);
		boxpropType.setBounds(166, 57, 101, 20);
		contentPane.add(boxpropType);
		
		labelBed = new JLabel ("Number of Beds");
		labelBed.setBounds(55,88,101,20);
		contentPane.add(labelBed);
		
		textBed = new JTextField();
		textBed.setBounds(166,119,101,20);
		contentPane.add(textBed);
		
		labelBath = new JLabel ("Number of Baths");
		labelBath.setBounds(55,119,101,20);
		contentPane.add(labelBath);
		
		textBath = new JTextField();
		textBath.setBounds(166,88,101,20);
		contentPane.add(textBath);
		
		labelFurniture = new JLabel("Furniture: ");
		labelFurniture.setBounds(55, 142, 101, 20);
		contentPane.add(labelFurniture);
		
		checkFurniture = new JComboBox(furnitureModel);
		checkFurniture.setBounds(166,146,101,20);
		contentPane.add(checkFurniture);
		
		labelQuadrant = new JLabel("Quadrant: ");
		labelQuadrant.setBounds(55, 27, 101, 20);
		contentPane.add(labelQuadrant);
		
		boxQuadrant = new JComboBox (quadrantModel);
		boxQuadrant.setBounds(166, 27, 101, 20);
		contentPane.add(boxQuadrant);
		
		bttnsetSearch = new JButton("Set search criteria");
		bttnsetSearch.setBounds(95, 173, 172, 20);
		contentPane.add(bttnsetSearch);
		
	}
	
	public void buildVectors()
	{
		quadrants = new Vector();
		quadrants.add("");
		quadrants.add("NW");
		quadrants.add("NE");
		quadrants.add("SW");
		quadrants.add("SE");
		quadrantModel = new DefaultComboBoxModel (quadrants);
		propertyType = new Vector();
		propertyType.add("");
		propertyType.add("House");
		propertyType.add("Apartment");
		propertyModel = new DefaultComboBoxModel (propertyType);
		furniture = new Vector();
		furniture.add("");
		furniture.add("True");
		furniture.add("False");
		furnitureModel = new DefaultComboBoxModel (furniture);
		
	}
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchView frame = new SearchView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
