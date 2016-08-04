package JSMatching.JSpackage;

public class VGdata {
	private int ID;
	private int numPep;
	private boolean truck;
	private boolean matched;
	
	VGdata(int i, int n, boolean t){
		ID = i;
		numPep = n;
		truck = t;
		matched = false;
	}
	VGdata(){
		ID = -1;
		numPep = -1;
		truck = false;
		matched = true;
	}
	public int getID(){
		return ID;
	}
	public int getPep(){
		return numPep;
	}
	public boolean getTruck(){
		return truck;
	}
	public boolean getMatched(){
		return matched;
	}
	public void setID(int i){
		ID = i;
	}
	public void setPep(int n){
		numPep = n;
	}
	public void setTruck(boolean t){
		truck = t;
	}
	public void setMatched(boolean t){
		matched = t;
	}
}
