package logic;

import java.util.ArrayList;

public interface State {

	public void print();
	
	public void setCameFrom(State v);
	public State getCameFrom();
	
	public boolean isVisited();
	public void setVisited(boolean b);

	
	public Location getLocation();

	public ArrayList<State> getNeighbors();

	public Problem getproblem();

	public int getCost();

	public int genCost();



}
