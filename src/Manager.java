import java.util.ArrayList;

public class Manager extends LandLord 
{
	
	private ArrayList<Property> propertiesReport;
	
	public Manager(Database da) 
	{
		super(da);
	}
	
	public  ArrayList<Property>  getReport()
	{
		return propertiesReport;
	}
	
	
	public void setReport(ArrayList<Property> report) {
		this.propertiesReport = report;
	}
	
	
	

}
