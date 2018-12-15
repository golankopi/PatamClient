package pipeGame;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

import exceptions.MatrixException;
import logic.Matrix;
import logic.Problem;
import logic.Solution;
import logic.State;
import logic.algo.BFS;
import logic.algo.DFS;
import logic.algo.HC_DFS;
import logic.algo.HC_Heap;
import managment.Solver;

public class PGS implements Solver{

	
	@Override
	public Solution solve(Problem problem) {
		State s = new PipeState((Matrix) problem);
		
		s.print();
		System.out.println("**************Starting PGS*****************");
		System.out.println("**************Starting PGS BFS*****************");
		Long bfsStart = new Date().getTime();
		System.out.println("BFS:"+bfsStart);
		//ArrayList<State> list = new BFS().search(new PipeSearchable(s));
		Long bfsEnd = new Date().getTime();
		System.out.println("BFS:"+bfsEnd);
		long bfsTotal = bfsEnd - bfsStart;
		
		System.out.println("**************Starting PGS DFS*****************");
		Long dfsStart = new Date().getTime();
		System.out.println("DFS:"+dfsStart);
		//ArrayList<State> list2 = new DFS().search(new PipeSearchable(s));
		Long dfsEnd = new Date().getTime();
		System.out.println("DFS:"+dfsEnd);
		long dfsTotal = dfsEnd - dfsStart;

		System.out.println("**************Starting PGS HC_DFS*****************");
		Long hc_dfsStart = new Date().getTime();
		System.out.println("HC_DFS:"+ hc_dfsStart);
		//ArrayList<State> list3 = new HC_DFS().search(new PipeSearchable(s));
		Long hc_dfsEnd = new Date().getTime();
		System.out.println("HC_DFS:"+new Date().getTime());
		long hc_dfsTotal = hc_dfsEnd-hc_dfsStart;
		
		System.out.println("**************Starting PGS HC_HEAP*****************");
		Long hc_heapStart = new Date().getTime();
		System.out.println("HC_HEAP:"+hc_heapStart);
		ArrayList<State> list4 = new HC_Heap().search(new PipeSearchable(s));
		Long hc_heapEnd = new Date().getTime();

		System.out.println("HC_HEAP:"+hc_heapEnd);
		long hc_heapTotal = hc_heapEnd-hc_heapStart;
		
		System.out.println("**************PGS BFS*****************");
		//printList(list);
		System.out.println(bfsTotal);
		System.out.println("**************PGS DFS*****************");
		//printList(list2);
		System.out.println(dfsTotal);
		System.out.println("**************PGS HC_DFS*****************");
		//printList(list2);
		System.out.println(hc_dfsTotal);
		System.out.println("**************PGS HC_HEAP*****************");
		//printList(list2);
		System.out.println(hc_heapTotal);
		
		Solution ps = new PipeSolution(list4,s);
		return ps;
	}

	@Override
	public Problem createProblem(Problem problem) {
		// TODO Auto-generated method stub
		return null;
	}

	public void printList(ArrayList<State> list)
	{
		if(list !=null){
			for(State s1: list)
			{
				s1.print();
				try{
					System.out.print("p:");
					s1.getCameFrom().getLocation().print();
				}catch(Exception e){}
			}
		}
	}
 	
  	public static void main(String[]args)
	{
		File file = new File("C:\\test\\level3.txt");
		File file2 = new File("C:\\test\\level7.txt");

	    try {
			Matrix mat = new Matrix(file);
			//mat.print();
			Matrix mat2 = new Matrix(file2);
			PipeState s = new PipeState(mat2);
			PGS pgs = new PGS();
			pgs.solve(mat2);
			
		} catch (MatrixException e) {
			e.printStackTrace();
		}
	}
}
