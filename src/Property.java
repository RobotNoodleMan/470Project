
public class Property {
	protected int numOfRooms;
	protected int numOfBaths;
	protected String isFurnished;
	protected String quadrant;
	protected String propertyType;
	protected int propertyState;
	protected int propertyID;
	protected LandLord owner;
	protected String address;
	protected String indexString;
	
	
	public Property()
	{
		//:D
	}
	public Property (int nR, int nB, String iF, String q, String pT, int pS, int pID, LandLord o, String a) {
		numOfRooms = nR;     
		numOfBaths = nB;     
		isFurnished = iF;
		quadrant = q;
		propertyType = pT;
		propertyState = pS;  
		propertyID = pID;     
		owner = o;     
		address = a;     
	}
	
	public void setIndexString()
	{
		indexString = "ID: " + String.valueOf(propertyID)
							 + " Address: " + address
							 +  " baths: " + String.valueOf(numOfBaths)
							 +  " beds: " + String.valueOf(numOfRooms)
							 +  " quadrant: " + quadrant
							 +  " type: "	+ propertyType
							 +  " furnished?: " + isFurnished;
	}
	
	public String getIndexString()
	{
		return indexString;
	}
	
	public int getNumOfRooms() {
		return numOfRooms;
	}
	
	public void setNumOfRooms(int numOfRooms) {
		this.numOfRooms = numOfRooms;
	}
	
	public int getNumOfBaths() {
		return numOfBaths;
	}
	
	public void setNumOfBaths(int numOfBaths) {
		this.numOfBaths = numOfBaths;
	}
	
	public String isFurnished() {
		return isFurnished;
	}
	
	public void setFurnished(String isFurnished) {
		this.isFurnished = isFurnished;
	}
	
	public String getQuadrant() {
		return quadrant;
	}
	
	public void setQuadrant(String quadrant) {
		this.quadrant = quadrant;
	}
	
	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	
	public int getPropertyState() {
		return propertyState;
	}
	
	public void setPropertyState(int propertyState) {
		this.propertyState = propertyState;
	}
	
	public int getPropertyID() {
		return propertyID;
	}
	
	public void setPropertyID(int propertyID) {
		this.propertyID = propertyID;
	}
	
	public LandLord getOwner() {
		return owner;
	}
	
	public void setOwner(LandLord owner) {
		this.owner = owner;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
}
