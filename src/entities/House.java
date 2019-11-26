package entities;

public class House extends Property {
	protected int numOfFloors;
	protected boolean hasYard;
	protected boolean hasGarage;
	
	public House(int nR, int nB, String iF, String q, String pT, int pS, int pID, LandLord o, String a, int nF, boolean y, boolean g) {
		super(nR, nB, iF, q, pT, pS, pID, o, a);

		numOfFloors = nF;
		hasYard = y;
		hasGarage = g;
	}
	
	public int getNumOfFloors() {
		return numOfFloors;
	}
	
	public void setNumOfFloors(int numOfFloors) {
		this.numOfFloors = numOfFloors;
	}
	
	public boolean isHasYard() {
		return hasYard;
	}
	
	public void setHasYard(boolean hasYard) {
		this.hasYard = hasYard;
	}
	
	public boolean isHasGarage() {
		return hasGarage;
	}
	
	public void setHasGarage(boolean hasGarage) {
		this.hasGarage = hasGarage;
	}
}
