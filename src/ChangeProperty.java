import java.util.ArrayList;

public class ChangeProperty {
	protected Database db;
	protected ArrayList<Property> theProperties;
	
	public ChangeProperty(Database d) {
		db = d;
		theProperties = db.fillList();
	}
	
	public boolean searchDatabase(Property toSearch) {
		for (int i = 0; i < theProperties.size(); i++) {
			if (theProperties.get(i).getPropertyID() == toSearch.getPropertyID())
				return true;
		}
		return false;
	}
	
	// return a string to use as a query.
	public void setState(Property p, int newState) {
		String s;
		if (searchDatabase(p)) {
			s = "UPDATE **table name** \nSET state = " + newState + " \nWHERE propertyID = " + p.getPropertyID() + ";";
			db.executeQuery(s);
		}
		else {
			// ERROR MESSAGE
		}
	}
	
	public boolean addProperty(Property p) {
		String s;
		if (!searchDatabase(p)) { // if property is not in list
			s = "INSERT INTO **table name** "
					 + "(property_id, property_address, bath_num, bed_num, furnished, state, user_owner, created_at, type, "
					 + "floorNum, yard, garage, roomNumber, description, quadrant)"
					 + " values (" + p.getPropertyID() + ", '" + p.getAddress() + "', " + p.getNumOfBaths() + ", " + p.getNumOfRooms()
					 + ", '" + p.isFurnished() + "', " + p.getPropertyState() + ", '" + p.getOwner() + "', " + 0 + ", '" + p.getPropertyType()
					 + "', " + 0 + ", False, False, 0, test, '" + p.getQuadrant() + "')";
			db.executeQuery(s);
			this.theProperties.add(p);
			return true;
		}
		return false;
	}
	
	public void removeProperty(Property p) {
		String s;
		if (searchDatabase(p)) {
			s = "DELETE FROM **table name**\nWHERE property id = " + p.getPropertyID() + ";";
			db.executeQuery(s);
		}
	}

	public static void main(String[] args) {
		String s;
		s = "UPDATE **table name** \nSET state = " + 1 + " \nWHERE propertyID = " + 1251 + ";";
		System.out.println(s);
		
		s = "INSERT INTO **table name** "
				 + "(property_id, property_address, bath_num, bed_num, furnished, state, user_owner, created_at, type, "
				 + "floorNum, yard, garage, roomNumber, description, quadrant)"
				 + " values (" + 123 + ", '" + "test" + "', " + 6 + ", " + 4
				 + ", '" + "True" + "', " + 2 + ", '" + "Bob" + "', " + 0 + ", '" + "House"
				 + "', " + 0 + ", False, False, 0, test, '" + "NW" + "')";
		System.out.println(s);
		
		s = "DELETE FROM **table name**\nWHERE property id = " + 1251 + ";";
		
		System.out.println(s);
	}
}
