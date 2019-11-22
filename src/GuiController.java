import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.security.auth.login.FailedLoginException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GuiController {
	
	private ListView list;
	private LoginView loginV;
	private SearchView searchV;
	private Database db;
	private RegisteredUser currentUser;
	private LandLord landlord;
	private NewPropertyView propV;
	//private SearchCriteria searchCriteria;
	
	public GuiController()
	{
		list = new ListView();
		currentUser = new RegisteredUser();
		db = new Database();
		list.btnLogin.addActionListener(new loginControl());
		list.btnSearch.addActionListener(new searchActivate());
		list.btnCriteria.addActionListener(new criteriaControl());
	}
	
	class loginControl implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			loginV = new LoginView();
			loginV.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			loginV.setVisible(true);
			loginV.bttnLogin.addActionListener(new loginConfirmation());
		}
		
	};
	
	class criteriaControl implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			searchV = new SearchView();
			searchV.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			searchV.setVisible(true);
			searchV.bttnsetSearch.addActionListener(new searchControl());
			//currentUser.searchCrit = new SearchCriteria();
			
			
		}
		
	};
	
	class searchActivate implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			list.listModel.clear();
			//currentUser.searchCrit = new SearchCriteria();
			//ArrayList <Property> propertyList = new ArrayList <Property> ();
			ArrayList <Property> propertyList = db.fillList();
			ArrayList<Property> filterList = currentUser.searchCrit.FilterProperties(propertyList);
			for(int i =0;i<filterList.size();i++)
			{
				list.listModel.addElement(filterList.get(i).getIndexString());
			}
			//currentUser.searchCrit = new SearchCriteria();
		}
		
	}
	
	class searchControl implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			currentUser.searchCrit.setQuadrant(searchV.boxQuadrant.getSelectedItem().toString());
			
			currentUser.searchCrit.setFurnished(searchV.checkFurniture.getSelectedItem().toString());
			
			currentUser.searchCrit.setPropertyType(searchV.boxpropType.getSelectedItem().toString());
			
			
			if(!searchV.textBed.getText().isEmpty())
			{
				currentUser.searchCrit.setNumOfBeds(Integer.parseInt(searchV.textBed.getText()));
			}
			
			if(!searchV.textBath.getText().isEmpty())
			{
				currentUser.searchCrit.setNumOfBaths(Integer.parseInt(searchV.textBath.getText()));
			}

			searchV.dispose();
		}
		
	};

	class loginConfirmation implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			currentUser = new RegisteredUser();
			currentUser.setName(loginV.txtUsername.getText());
			currentUser.setPassword(loginV.txtPassword.getText());
			if(!db.queryUser(currentUser))
			{
				JOptionPane.showMessageDialog(loginV.contentPane,"Failed Login!");
			}
			else
			{
				loginV.dispose();
				landlord = new LandLord(db);
				landlord.setName(currentUser.getName());
				landlord.setPostedProperties(db.getLandLordProps(landlord.getName()));
				list.listModel.clear();
				list.initializeLandLord();
				list.btnLandLordCreateProperty.addActionListener(new createPropertyControl());
				
				
				
				for(int i =0; i <landlord.getPostedProperties().size();i++)
				{
					list.listModel.addElement(landlord.getPostedProperties().get(i).getIndexString());
				}
				
			}
			
		}
		
	};
	
	class createPropertyControl implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			propV = new NewPropertyView();
			propV.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			propV.setVisible(true);
			propV.btnAddProperty.addActionListener(new addPropertyControl());
			
		}
		
	};
	
	class addPropertyControl implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			String s;
			s = "INSERT INTO properties "
					 + "(property_address, bath_num, bed_num, furnished, user_owner, type, "
					 + "quadrant)"
					 + " values ('" + propV.txtAddress.getText() + "', " + propV.txtBathrooms.getText() + ", " + propV.txtBedrooms.getText()
					 + ", '" + propV.txtFurnished.getText() + "', '" + landlord.getName() + "', '" + propV.txtPropertyType.getText()
					 + "', '" + propV.txtQuadrant.getText() + "')";
			db.executeQuery(s);
			landlord.setPostedProperties(db.getLandLordProps(landlord.getName()));
			
			list.listModel.clear();
			
			for(int i =0; i <landlord.getPostedProperties().size();i++)
			{
				list.listModel.addElement(landlord.getPostedProperties().get(i).getIndexString());
			}
			
		}
		
	};
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiController gc = new GuiController();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
