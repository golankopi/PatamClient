package pipeGame;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

import exceptions.MatrixException;
import logic.Location;
import logic.Matrix;
import logic.Searchable;
import logic.State;

public class PipeSearchable implements Searchable {
	private State firstState;
	private int[][] tracker; // location that i have visit in
	protected StateComperator comp;
	
	public PipeSearchable(State s) 
	{
		this.firstState = s;
		tracker = new int[((Matrix) s.getproblem()).getRows()][((Matrix) s.getproblem()).getCols()];
		comp = new StateComperator();
	}
	
	
	public StateComperator getComp() {
		return comp;
	}


	public void setComp(StateComperator comp) {
		this.comp = comp;
	}


	@Override
	public State getInitialState() {
		return firstState;
	}

	@Override
	public State getGoalState() {
		return null;
	}

	@Override
	public ArrayList<State> getAllPossibleStates(State s) {
		ArrayList<State> list = new ArrayList<State>() ;
		//if(!allReadyVisited(s))
		//{
			list =  s.getNeighbors();
			//updateTracker(s);
		//}
		return list;
	}
	
	private void updateTracker(State s) {
		Location loc = s.getLocation();
		tracker[loc.getI()][loc.getJ()] = 1;
	}


	@Override
	public boolean isGoal(State n) {
		if(n.getLocation().getValue() == 'g')
			return true;
		return false;
	}

	@Override
	public ArrayList<State> backTrack(State n) {
		ArrayList<State> list = new ArrayList<State>();
		State papa = n.getCameFrom();
		while(papa != null)
		{
			if(n.getLocation().getValue() !='g')
				list.add(n);
			n = papa;
			papa = n.getCameFrom();
			//System.out.println("this is n" + n.toString());
			//System.out.println("this is papa" + papa.toString());
		}
		return list;
	}
	
	private boolean allReadyVisited(State s)
	{
		Location loc = s.getLocation();
		if(tracker[loc.getI()][loc.getJ()] == 1)
			return true;
		return false;
	}
	
	
	public static void main(String[]args)
	{
		File file = new File("C:\\test\\level2.txt");
	    try {
			Matrix mat = new Matrix(file);
			//mat.print();
			PipeState s = new PipeState(mat);
			
			//System.out.println(s.toString());
			PipeSearchable ps = new PipeSearchable(s);
			ArrayList<State> list =ps.getAllPossibleStates((PipeState) ps.firstState);
			} catch (MatrixException e) {
			e.printStackTrace();
		}
	}


	public PipeState getFirstState() {
		return (PipeState) firstState;
	}


	public void setFirstState(PipeState firstState) {
		this.firstState = firstState;
	}


	public int[][] getTracker() {
		return tracker;
	}


	public void setTracker(int[][] tracker) {
		this.tracker = tracker;
	}




}
