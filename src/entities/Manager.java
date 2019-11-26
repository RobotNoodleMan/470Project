package entities;
import java.util.ArrayList;

import database.Database;

public class Manager extends LandLord 
{
	
	private ArrayList<Property> propertiesReport;
	private int numOfHouses;
	private int numOfRented;
	private int numOfActive;
	private String reportVal;
	
	public Manager(Database da) 
	{
		super(da);
		numOfHouses = 0;
		numOfRented = 0;
		numOfActive = 0;
	}
	
	public String getReportVal() {
		return reportVal;
	}

	public void setReportVal(String reportVal) {
		this.reportVal = reportVal;
	}

	public ArrayList<Property> getReport()
	{
		return propertiesReport;
	}
	
	
	public void setPostedProperties(ArrayList<Property> report) {
		this.propertiesReport = report;
	}
	
	public void fillTotals()
	{
		numOfHouses = 0;
		numOfRented = 0;
		numOfActive = 0;
		propertiesReport = db.fillManagerList();
		numOfHouses = propertiesReport.size();
		for(int i = 0;i<propertiesReport.size();i++)
		{
			if(propertiesReport.get(i).getPropertyState() == 1 )
				numOfActive++;
	
			if(propertiesReport.get(i).getPropertyState() == 2 )
				numOfRented++;

		}
		
		reportVal = "Number of properties = " + numOfHouses + "; Number of Active = " + numOfActive
				 	+ "; Number of Rented = " + numOfRented;
	}
}
