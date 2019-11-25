import java.util.ArrayList;

public class Manager extends LandLord 
{
	
	private ArrayList<Property> propertiesReport;
	
	public Manager(Database da) 
	{
		super(da);
	}
	
	public void getReport()
	{
		
	}
	
	
	public void setPostedProperties(ArrayList<Property> report) {
		this.propertiesReport = report;
	}
	
	
	

}
