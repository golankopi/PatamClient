package pipeGame;

import java.util.ArrayList;

import logic.Matrix;
import logic.Solution;
import logic.State;
import util.Dictionary;

public class PipeSolution implements Solution {

	ArrayList<String> solutionList = new ArrayList<>();
	public ArrayList<String> getSolutionList() {
		return solutionList;
	}

	public void setSolutionList(ArrayList<String> solutionList) {
		this.solutionList = solutionList;
	}

	Dictionary dic = new Dictionary();
	
	public PipeSolution(ArrayList<State> list, State s) {
		//System.out.println("my list is"+list);
		for(State st: list)
		{
			char wanted = st.getLocation().getValue();
			char current = ((Matrix) s.getproblem()).getValue(st.getLocation().getI(),st.getLocation().getJ());
			////System.out.println("old val:" + current);
			////System.out.println("new val:" + wanted);
			int rightRotation = dic.getRotationRight(current,wanted);
			String temp = st.getLocation().getI()+","+st.getLocation().getJ()+"," + rightRotation;
			
			//System.out.println(temp+","+wanted);
			solutionList.add(temp);
		}
	}

}
