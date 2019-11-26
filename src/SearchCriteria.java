import java.util.ArrayList;

public class SearchCriteria {
	private int numOfBeds;
	private int numOfBaths;
	private String furnished;
	private String quadrant;
	private String propertyType;	
	private int numOfConditions;
	ArrayList<Property> currentProperties;
	
	public SearchCriteria() {
		numOfBeds = 0;
		numOfBaths = 0;
		furnished = "";
		quadrant = "";
		propertyType = "";
		numOfConditions = 0;
	}
	
	public ArrayList<Property> FilterProperties(ArrayList<Property> prop) {
		int counter = 0;
		this.recount();
		ArrayList<Property> filterList = new ArrayList<Property>();

		for (int i = 0; i < prop.size(); i++) {
			if (numOfBeds == prop.get(i).getNumOfRooms()) {
				counter++;
			}
			
			if (numOfBaths == prop.get(i).getNumOfBaths()) {
				counter++;
			}
			
			if (furnished.equals( prop.get(i).isFurnished())) {
				counter++;
			}
			
			if (quadrant.equals(prop.get(i).getQuadrant())) {
				
				counter++;
			}
			
			if (propertyType == prop.get(i).getPropertyType()) {
				counter++;
			}
			
			if (counter == numOfConditions) {
				filterList.add(prop.get(i));
			}
			
				counter = 0;
		}
		return filterList;
	}
	
	public void recount() {
		numOfConditions = 0;
		if (numOfBeds != 0)
			numOfConditions++;
		
		if (numOfBaths != 0)
			numOfConditions++;
		
		if (furnished != "")
			numOfConditions++;
		
		if (quadrant != "")
			numOfConditions++;
		
		if (propertyType != "")
			numOfConditions++;
	}
	
	public int getNumOfBeds() {
		return numOfBeds;
	}
	
	public void setNumOfBeds(int numOfBeds) {
		this.numOfBeds = numOfBeds;
		//this.recount();
	}
	
	public int getNumOfBaths() {
		return numOfBaths;
	}
	
	public void setNumOfBaths(int numOfBaths) {
		this.numOfBaths = numOfBaths;
		//this.recount();
	}
	
	public String isFurnished() {
		return furnished;
	}
	
	public void setFurnished(String furnished) {
		this.furnished = furnished;
		//this.recount();
	}
	
	public String getQuadrant() {
		return quadrant;
	}
	
	public void setQuadrant(String quadrant) {
		this.quadrant = quadrant;
		//this.recount();
	}

	public String getPropertyType() {
		return propertyType;
	}
	
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
		//this.recount();
	}

}
