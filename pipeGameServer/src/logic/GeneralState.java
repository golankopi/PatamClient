package logic;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class GeneralState implements State {
	
	protected Location location;
	protected Problem problem;
	protected State cameFrom;	
	protected HashSet<String> fathers ; // a set of states already evaluated
	protected int cost = 0;
	protected boolean visited = false;

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public State getCameFrom() {
		return cameFrom;
	}

	public void setCameFrom(State cameFrom) {
		this.cameFrom = cameFrom;
		this.fathers.add(cameFrom.toString());
		//System.out.println("Came----------------:" + cameFrom.toString());
	}
	
	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	private void printList(ArrayList<State> list)
	{
		for(State s1: list)
		{
			s1.print();
		}
	}
	
	public Problem getproblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public void print()
	{
		//problem.print();
		//location.print();
	}

}
