
public class Apartment extends Property {
	protected int roomNumber;
	
	public Apartment(int nR, int nB, String iF, String q, String pT, int pS, int pID, LandLord o, String a, int rN) {
		super(nR, nB, iF, q, pT, pS, pID, o, a);

		roomNumber = rN;
	}

	public int getRoomNumber () {
		return roomNumber;
	}
	
	public void setRoomNumber(int rn) {
		roomNumber = rn;
	}
}
