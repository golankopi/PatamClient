package logic.algo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

import exceptions.MatrixException;
import logic.Matrix;
import logic.Searchable;
import logic.Searcher;
import logic.State;
import pipeGame.PipeSearchable;
import pipeGame.PipeState;

public class BFS implements Searcher {
	
	//PriorityQueue<State> open; // a priority queue of states to be evaluated
	LinkedList<State> open;
	HashSet<String> closed; // a set of states already evaluated
	
  	@Override
	public ArrayList<State> search(Searchable searchable) {  
  		System.out.println("Starting BFS");
  		int counter = 0;
		//this.open = new PriorityQueue<State>(searchable.getComp()) ;
		this.open = new LinkedList<State>();
		open.add(searchable.getInitialState());
		closed = new HashSet<String>();
		ArrayList<State> list;
		while(!open.isEmpty())
		{
			State n = open.poll();
			//System.out.println("we pooled n from q");
			n.print();

			//System.out.println("!closed.contains(n.toString()) : " + !closed.contains(n.toString()));
			if(!closed.contains(n.toString() ) || !n.isVisited()){
				//closed.add(n.toString());
				n.setVisited(true);

				if(searchable.isGoal(n)) //3 
				{
					n.getLocation().print();
					//System.out.println("We got to g");
					ArrayList<State> l1= searchable.backTrack(n);
					//if(l1.get(l1.size()-1).getCameFrom() == null)
					return l1;
				}
				list = searchable.getAllPossibleStates(n); // 4
				//System.out.println("Getting successors");
				printList(list);
				for(State s : list)
				{
					//System.out.println("for:" + s.toString() + " !inClosedList(s) && !open.contains(s): " + (!inClosedList(s) && !open.contains(s)));
					if(!inClosedList(s) && !open.contains(s)){ //5.a
						s.setCameFrom(n);
						//System.out.print("going to print node location: " ); s.getLocation().print();
						//System.out.print("going to print father location: " ); n.getLocation().print();
						open.add(s);
						counter++;
						//System.out.println("adding s: " + s.toString());
					}
				}
				
			}
		}
		//System.out.println("BFS done!");
		return null;
	}
  	
  	private boolean inClosedList(State s)
  	{
  		if(closed.contains(s.toString()))
  			return true;
  		return false;
  	}
  	
  	public void printList(ArrayList<State> list)
	{
		if(list !=null){
			for(State s1: list)
			{
				s1.print();
				try{
					//System.out.println("p:");
					s1.getCameFrom().getLocation().print();
				}catch(Exception e){}
			}
		}
	}
 	public void printListReverse(ArrayList<State> list)
	{
 		ArrayList<State> s2 =new ArrayList<State>();
		if(list !=null){
			int si = list.size()-1;
			for(State s1: list)
			{
				s2.add(si,s1);
				si--;
			}
			printList(s2);
		}
	} 	
 	
  	public static void main(String[]args)
	{
		File file = new File("C:\\test\\level4.txt");
		File file2 = new File("C:\\test\\level2.txt");
		File file3 = new File("C:\\test\\level3.txt");

	    try {
			Matrix mat = new Matrix(file);
			//mat.print();
			Matrix mat2 = new Matrix(file2);
			PipeState s = new PipeState(mat);
			PipeState t = new PipeState(mat2);
			//System.out.println(s.equals(t));
			//System.out.println(s.toString());
			PipeSearchable ps = new PipeSearchable(s);
			//ArrayList<State> list =ps.getAllPossibleStates(ps.getFirstState());
			BFS bfs=  new BFS();
			bfs.printList(bfs.search(ps));
		} catch (MatrixException e) {
			e.printStackTrace();
		}
	}
}
