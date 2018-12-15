package logic;

public class Location {
	private int i;
	private int j;
	private char value;
	

	public Location(int i,int j,char value)
	{
		this.i = i;
		this.j = j;
		this.value = value;
	}
	public Location(Location loc)
	{
		this.i = loc.i;
		this.j = loc.j;
		this.value = loc.value;
	}
	public Location(int i,int j)
	{
		this.i = i;
		this.j = j;
	}
	public Location() 
	{
	}
	
	public char getValue() {
		return value;
	}
	public void setValue(char value) {
		this.value = value;
	}
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}
	
	public void print()
	{
		//System.out.println("["+this.getI()+","+this.getJ()+"] and thier value=" + this.value);
	}
	
	@Override
	public String toString()
	{
		return "["+this.getI()+","+this.getJ()+"] and thier value=" + this.value;
	}
	
	public Location getStart(Matrix state) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int diff(Location l2)
	{
		return Math.abs(this.getI() - l2.getI()) + Math.abs(this.getJ() - l2.getJ());
	}
	
}
