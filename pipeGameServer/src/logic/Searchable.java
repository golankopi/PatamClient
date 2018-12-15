package logic;

import java.util.ArrayList;
import java.util.Comparator;

public interface Searchable {
	State getInitialState();
	State getGoalState();
	ArrayList<State> getAllPossibleStates(State s);
	boolean isGoal(State v);
	ArrayList<State> backTrack(State n);
	Comparator<? super State> getComp();
	
	class StateComperator implements Comparator<State>
	{

		@Override
		public int compare(State o1, State o2) {
			if(o1.getCost() >= o2.getCost())
				return 1;
			else 
				return -1;
		}
	}
}
