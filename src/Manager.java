import java.util.ArrayList;

public class Manager extends LandLord 
{
	
	private ArrayList<Property> propertiesReport;
	private int numOfHouses;
	private int numOfRented;
	private int numOfActive;
	
	public Manager(Database da) 
	{
		super(da);
	}
	
	public ArrayList<Property> getReport()
	{
		return propertiesReport;
	}
	
	
	public void setPostedProperties(ArrayList<Property> report) {
		this.propertiesReport = report;
	}
	
	
	

}
