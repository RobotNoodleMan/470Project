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
	private Login instance;
	private SearchView searchV;
	private Database db;
	private RegisteredUser currentUser;
	private LandLord landlord;
	private Manager manager;
	private NewPropertyView propV;
	private ChangePropertyView cpropV;
	private EmailView emailV;
	
	//Initial creation of the listView
	public GuiController()
	{
		list = new ListView();
		currentUser = new RegisteredUser();
		db = new Database();
		instance = Login.getInstance(db);
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

			instance.getLogin().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			instance.getLogin().setVisible(true);
			instance.getLogin().bttnLogin.addActionListener(new loginConfirmation());
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
			db.updateStateOfPropertiesIfOverduePayment();
			ArrayList <Property> propertyList;
			//String s = "" HERE IS WHERE WE UPDATE THE STATE!!!!!!!!!!!!! TODO

			if (db.verifyType(currentUser) == 2) {
				propertyList = db.fillManagerList();
			}
			else
				propertyList = db.fillList();
			ArrayList<Property> filterList = currentUser.searchCrit.FilterProperties(propertyList);
			for(int i =0;i<filterList.size();i++)
			{
				list.listModel.addElement(filterList.get(i).getIndexString());
			}
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
			currentUser.setName(instance.getLogin().txtUsername.getText());
			currentUser.setPassword(instance.getLogin().txtPassword.getText());
//			if(!db.queryUser(currentUser))
			if(!instance.validate(currentUser))
			{
				JOptionPane.showMessageDialog(instance.getLogin().contentPane,"Failed Login!");
			}
			else
			{
				// Registered User
				if (db.verifyType(currentUser) == 0) {
					list.frame.setTitle("Logged in as User");
					list.northPane.setLeftComponent(list.emptylbl);
					instance.getLogin().dispose();
					db.updateStateOfPropertiesIfOverduePayment();
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
				// Landlord
				else if (db.verifyType(currentUser) == 1) {
					list.frame.setTitle("Logged in as Landlord");
					list.northPane.setLeftComponent(list.emptylbl);
					instance.getLogin().dispose();
					landlord = new LandLord(db);
					landlord.setName(currentUser.getName());
					landlord.setPostedProperties(db.getLandLordProps(landlord.getName()));
					db.updateStateOfPropertiesIfOverduePayment();
					list.listModel.clear();
					list.initializeLandLord();
					cpropV = new ChangePropertyView();
					list.btnLandLordCreateProperty.addActionListener(new createPropertyControl());
					list.btnLandLordChangePropertyState.addActionListener(new changePropertyControl());
					list.btnLandLordMakePayment.addActionListener(new makePaymentControl());

					LandLord.setDBPaymentInfo(currentUser, db);
					for(int i =0; i <landlord.getPostedProperties().size();i++)
					{
						list.listModel.addElement(landlord.getPostedProperties().get(i).getIndexString());
					}
				}
				// Manager
				else if (db.verifyType(currentUser) == 2) {
					list.frame.setTitle("Logged in as Manager");
					list.northPane.setLeftComponent(list.emptylbl);
					list.rightPane.setLeftComponent(list.btnSearch);
					list.btnSearch.addActionListener(new searchActivate());

					instance.getLogin().dispose();
					manager = new Manager(db);;
					manager.setName(currentUser.getName());
					manager.setPostedProperties(db.getLandLordProps(manager.getName()));
					db.updateStateOfPropertiesIfOverduePayment();
					list.listModel.clear();
					list.initializeManager();
					cpropV = new ChangePropertyView(1);
					list.btnLandLordChangePropertyState.addActionListener(new changePropertyControl());
					list.btnGetRprt.addActionListener (new recieveReportControl());
					
					ArrayList <Property> propertyList = db.fillManagerList();
					
					for(int i =0;i<propertyList.size();i++)
					{
						list.listModel.addElement(propertyList.get(i).getIndexString());
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
			if(db.needtoPaid(currentUser))
			{
				System.out.println("test");
				Timestamp time = new Timestamp(System.currentTimeMillis());
				String timePaid = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(time);
				JOptionPane.showMessageDialog(list.frame,"Payment Made at :" + timePaid + "!");	
				String s;
				s = "UPDATE rentingdb.users SET payment_paid = CURRENT_TIMESTAMP WHERE users.user_name = '"+currentUser.getName()+"'";
				db.executeQuery(s);
				db.updateStateOfPropertiesIfPaid();
			}
			else
			{
				JOptionPane.showMessageDialog(list.frame, "Payment Already Made!");
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
			db.updateStateOfPropertiesIfOverduePayment();
			list.listModel.clear();
			propV.dispose();
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
			cpropV.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			cpropV.setVisible(true);
			cpropV.btnConfirm.addActionListener(new confirmPropertyControl());
		}
		
	};
	//This controls the confirm property button on the changePropertyView
	class confirmPropertyControl implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String s;			
			s = "UPDATE properties \nSET state = " + Integer.parseInt(cpropV.dropState.getSelectedItem().toString())
				+ " \nWHERE property_id = " + Integer.parseInt(cpropV.txtPropID.getText()) + ";";
			db.executeQuery(s);
			
			cpropV.dispose();
			list.listModel.clear();
			landlord.setPostedProperties(db.getLandLordProps(landlord.getName()));
			for(int i =0; i <landlord.getPostedProperties().size();i++)
			{
				list.listModel.addElement(landlord.getPostedProperties().get(i).getIndexString());
			}
		}
		
	};
	//Opens up the send email view
	class sendEmailControl implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			emailV = new EmailView();
			emailV.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			emailV.setVisible(true);
			emailV.btnSendEmail.addActionListener(new confirmEmailControl());
		}
		
	};
	//Controls the send email button on the email view
	class confirmEmailControl implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ArrayList<Property> fullList = new ArrayList<Property>();
			fullList = db.fillList();
			Property exists = null;
			for (int i = 0; i < fullList.size(); i++) {
				if (Integer.parseInt(emailV.txtPropID.getText()) == fullList.get(i).getPropertyID()) {
					exists = fullList.get(i);
				}
			}
			if (exists != null)
				JOptionPane.showMessageDialog(emailV.contentPane,"Email Sent Successfully!");
			else
				JOptionPane.showMessageDialog(emailV.contentPane, "Email not sent. Property ID not found.");
			
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
	
	class recieveReportControl implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			db.updateStateOfPropertiesIfOverduePayment();
			list.listModel.clear();
			ArrayList<Property> rentedList = new ArrayList<Property>();
			rentedList = db.fillReportList();
			
			manager.fillTotals();
			list.listModel.addElement("Today's summary:");
			list.listModel.addElement(manager.getReportVal());
			list.listModel.addElement("Properties that are rented:");

			for(int i =0; i < rentedList.size();i++)
			{
				list.listModel.addElement(rentedList.get(i).getIndexString());
			}
		}
	}
	
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