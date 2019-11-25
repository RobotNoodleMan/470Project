//Test comment

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
	private Manager manager;
	private NewPropertyView propV;
	private ChangePropertyView cpropV;
	private EmailView emailV;
	//private SearchCriteria searchCriteria;
	
	//Initial creation of the listView
	public GuiController()
	{
		list = new ListView();
		currentUser = new RegisteredUser();
		db = new Database();
		list.btnLogin.addActionListener(new loginControl());
		list.btnSearch.addActionListener(new searchActivate());
		list.btnCriteria.addActionListener(new criteriaControl());
		list.btnSendEmail.addActionListener(new sendEmailControl());
	}
	
	/*
	class registerControl implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if(!registerView.textUsername.getText().isEmpty() && !registerView.textPassword.getText().isEmpty() &&
					!registerView.)
			


			searchV.dispose();
		}
		
	};
	*/
	
	//This class controls the login button on the listView form
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
	//This class controls the search criteria button on listView
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
	//This class controls the search button on ListView
	class searchActivate implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			list.listModel.clear();
			System.out.println("test");
			//String s = "" HERE IS WHERE WE UPDATE THE STATE!!!!!!!!!!!!! TODO
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
	
	//This class controls the set search Criteria button on the SearchCriteriaView 
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
	//This controls the login button on LoginView
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

				if (db.verifyType(currentUser) == 0) {
					loginV.dispose();
					list.listModel.clear();
					list.initializeRegisteredUser();
					list.btnRegUserNotification.addActionListener(new notificationControl());
					ArrayList <Property> propertyList = db.fillList();
					ArrayList<Property> filterList = currentUser.searchCrit.FilterProperties(propertyList);
					for(int i =0;i<filterList.size();i++)
					{
						list.listModel.addElement(filterList.get(i).getIndexString());
					}
				}
				if (db.verifyType(currentUser) == 1) {
					loginV.dispose();
					System.out.println("TEST");
					landlord = new LandLord(db);
					landlord.setName(currentUser.getName());
					landlord.setPostedProperties(db.getLandLordProps(landlord.getName()));
					list.listModel.clear();
					list.initializeLandLord();
					list.btnLandLordCreateProperty.addActionListener(new createPropertyControl());
					list.btnLandLordChangePropertyState.addActionListener(new changePropertyControl());
					list.btnLandLordMakePayment.addActionListener(new makePaymentControl());

					LandLord.setDBPaymentInfo(currentUser, db);
					for(int i =0; i <landlord.getPostedProperties().size();i++)
					{
						list.listModel.addElement(landlord.getPostedProperties().get(i).getIndexString());
					}
				}
				
				else if (db.verifyType(currentUser) == 2) {
					loginV.dispose();
					manager = new Manager(db);;
					manager.setName(currentUser.getName());
					manager.setPostedProperties(db.getLandLordProps(manager.getName()));
					list.listModel.clear();
					list.initializeManager();
					list.btnLandLordChangePropertyState.addActionListener(new changePropertyControl());
					
				
				
					ArrayList <Property> propertyList = db.fillList();
					ArrayList<Property> filterList = currentUser.searchCrit.FilterProperties(propertyList);
					for(int i =0;i<filterList.size();i++)
					{
						list.listModel.addElement(filterList.get(i).getIndexString());
					}
				}

				

				
			}
			
		}
		
	};
	
	class makePaymentControl implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			if(true)		//Going to be if not already paid TODO ie if payment_paid is not null | need to wait until merge an then 
				//put in db query payment that checks if payment_paid is null or not
			{
				System.out.println("test");
				Timestamp time = new Timestamp(System.currentTimeMillis());
				String timePaid = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(time);
				JOptionPane.showMessageDialog(list.northPane,"Payment Made at :" + timePaid + "!");	
				String s;
				s = "UPDATE rentingdb.users SET payment_paid = CURRENT_TIMESTAMP WHERE users.user_name = '"+currentUser.getName()+"'";
				db.executeQuery(s);
			}
			else
			{
				JOptionPane.showMessageDialog(list.northPane, "Payment Already Made!");
			}

		}
		
	};
	
	
	//This controls the createProperty on the ListView for landlord
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
	//This controls the addProperty button on the NewPropertyView
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
	//This controls the change property button on the landlords listview
	class changePropertyControl implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			cpropV = new ChangePropertyView();
			cpropV.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			cpropV.setVisible(true);
			cpropV.bttnConfirm.addActionListener(new confirmPropertyControl());
		}
		
	};
	//This controls the confirm property button on the changePropertyView
	class confirmPropertyControl implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String s;			
			s = "UPDATE properties \nSET state = " + Integer.parseInt(cpropV.txtNewState.getText())
				+ " \nWHERE property_id = " + Integer.parseInt(cpropV.txtPropID.getText()) + ";";
			db.executeQuery(s);
			
			
		}
		
	};
	
	class sendEmailControl implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			emailV = new EmailView();
			emailV.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			emailV.setVisible(true);
			emailV.btnSendEmail.addActionListener(new confirmEmailControl());
		}
		
	};
	
	class confirmEmailControl implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(emailV.contentPane,"Email Sent Successfully!");
			emailV.dispose();
		}
	};
	
	class notificationControl implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (currentUser.recieveNotifications) {
				currentUser.setNotifcation(false);
				JOptionPane.showMessageDialog(list.frame, "You have unsubscribed successfully.");
			}
				
			else {
				currentUser.setNotifcation(true);
				JOptionPane.showMessageDialog(list.frame,"You have subscribed successfully!\n"
													   + "You will recieve email notifcations based your search criteria.");
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