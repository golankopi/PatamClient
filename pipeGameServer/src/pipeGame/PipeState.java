package pipeGame;

import java.util.ArrayList;
import java.util.HashSet;

import exceptions.MatrixException;
import logic.GeneralState;
import logic.Location;
import logic.Matrix;
import logic.Problem;
import logic.State;
import util.Dictionary;

public class PipeState extends GeneralState {

	public PipeState(Problem problem){ // CTOR for first state
		try {
			this.problem = new Matrix((Matrix)problem);
		} catch (MatrixException e) {e.printStackTrace();}
		this.location = new Location(((Matrix) problem).getStart().getI(),((Matrix) problem).getStart().getJ(),((Matrix) problem).getStart().getValue())  ;
		this.cameFrom = null;
		fathers = new HashSet<String>();
		setCost(genCost());
	}

	public PipeState(PipeState s,Location newlocation){ // CTOR for first state
		try {
			this.problem = new Matrix((Matrix)s.problem);
		} catch (MatrixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.location = new Location(newlocation.getI(),newlocation.getJ(),newlocation.getValue());
		((Matrix) problem).setLocation(newlocation);
		fathers = new HashSet<String>(s.fathers);
		setCost(genCost());
		//this.setCameFrom(s);
	}

	public ArrayList<State> getNeighbors() {
		ArrayList<State> list = new ArrayList<State>();
		ArrayList<PipeState> up = new ArrayList<PipeState>();
		ArrayList<PipeState> down = new ArrayList<PipeState>();
		ArrayList<PipeState> left = new ArrayList<PipeState>();
		ArrayList<PipeState> right = new ArrayList<PipeState>();
		Character myParent = getParentDiraction();
		//System.out.println("my p:" + myParent);
		if(!(myParent.equals('u')))up = getPosibleUp();
		if(!(myParent.equals('d')))down = getPosibleDown();
		if(!(myParent.equals('l')))left = getPosibleLeft();
		if(!(myParent.equals('r')))right = getPosibleRight();
		//System.out.println(fathers.toString());
		for(PipeState s : up)
		{
			if(!fathers.contains(s.toString()))
				list.add(s);
		}
		for(PipeState s : down)
		{
			if(!fathers.contains(s.toString()))
				list.add(s);
		}
		for(PipeState s : left)
		{
			if(!fathers.contains(s.toString()))
				list.add(s);
		}
		for(PipeState s : right)
		{
			if(!fathers.contains(s.toString()))
				list.add(s);
		}
		return list;
	} 
	
	public char getParentDiraction()
	{
		try{
			if(cameFrom.getLocation() !=null)
			{
			Location loc = cameFrom.getLocation();
			if(location.getI() < loc.getI())
				return 'd';
			if(location.getI() > loc.getI())
				return 'u';
			if(location.getJ() < loc.getJ())
				return 'r';
			if(location.getJ() > loc.getJ())
				return 'l';
		
			}
		}catch(Exception e){}
		return 'q';
	}
	
	private ArrayList<PipeState> getPosibleUp()
	{
		////System.out.println("Checking neighbors UP");
		ArrayList<PipeState> list = new ArrayList<PipeState>();
		Dictionary dic = new Dictionary();
		char current = location.getValue();
		if(location.getI() > 0)
		{
			Location newLocation = new Location(location.getI()-1,location.getJ(),((Matrix) getproblem()).getValue(location.getI()-1,location.getJ()));
			PipeState up = new PipeState(this,newLocation);
			char newVal = newLocation.getValue();
			if(newVal == 'F' || newVal == '7' ||newVal == 'J' || newVal == 'L'  )
			{
				Location l = new Location(newLocation.getI(),newLocation.getJ(),'F');
				if(dic.validate(current,'F','u'))
					list.add(new PipeState(up,l));
				Location l2 = new Location(newLocation.getI(),newLocation.getJ(),'7');
				if(dic.validate(current,'7','u'))
					list.add(new PipeState(up,l2));
			}
			else if(newVal == '|' || newVal == '-')
			{
				Location l = new Location(newLocation.getI(),newLocation.getJ(),'|');
				if(dic.validate(current,'|','u'))
					list.add(new PipeState(up,l));
			}
			else if(newVal == 'g'){
				Location l = new Location(newLocation.getI(),newLocation.getJ(),'g');
				if(dic.validate(current,'g','u'))
					list.add(new PipeState(up,l));
			}
		}
		//printList(list);
		return list;
	}
	
	
	private ArrayList<PipeState> getPosibleDown()
	{
		Dictionary dic = new Dictionary();
		char current = location.getValue();
		////System.out.println("Checking neighbors Down");
		ArrayList<PipeState> list = new ArrayList<PipeState>();
		//location.print();
		if(location.getI() < ((Matrix) problem).getRows()-1)
		{
			Location newLocation = new Location(location.getI()+1,location.getJ(),((Matrix) getproblem()).getValue(location.getI()+1,location.getJ()));
			//newLocation.print();
			PipeState down = new PipeState(this,newLocation);
			char newVal = newLocation.getValue();
			////System.out.println("in down new val = "  + newVal);
			if(newVal == 'F' || newVal == '7' ||newVal == 'J' || newVal == 'L'  )
			{
				Location l2 = new Location(newLocation.getI(),newLocation.getJ(),'L');
				if(dic.validate(current,'L','d'))
					list.add(new PipeState(down,l2));
				Location l = new Location(newLocation.getI(),newLocation.getJ(),'J');
				if(dic.validate(current,'J','d'))
					list.add(new PipeState(down,l));
				
			}
			else if(newVal == '|' || newVal == '-')
			{
				Location l = new Location(newLocation.getI(),newLocation.getJ(),'|');
				if(dic.validate(current,'|','d'))
					list.add(new PipeState(down,l));
			}
			else if(newVal == 'g'){
				Location l = new Location(newLocation.getI(),newLocation.getJ(),'g');
				if(dic.validate(current,'g','d'))
					list.add(new PipeState(down,l));
			}
		}
		//printList(list);
		return list;
	}
	
	private ArrayList<PipeState> getPosibleRight()
	{
		Dictionary dic = new Dictionary();
		char current = location.getValue();

		////System.out.println("Checking neighbors Right");
		ArrayList<PipeState> list = new ArrayList<PipeState>();
		if(location.getJ() < ((Matrix) problem).getCols()-1)
		{
			Location newLocation = new Location(location.getI(),location.getJ()+1,((Matrix) getproblem()).getValue(location.getI(),location.getJ()+1));
			////System.out.println("in right mylocation: "  );
			//location.print();
			////System.out.println("in right newlocation: "  );
			//newLocation.print();
			PipeState right = new PipeState(this,newLocation);
			char newVal = newLocation.getValue();
			if(newVal == 'F' || newVal == '7' ||newVal == 'J' || newVal == 'L'  )
			{
				Location l = new Location(newLocation.getI(),newLocation.getJ(),'J');
				if(dic.validate(current,'J','r'))
					list.add(new PipeState(right,l));
				Location l2 = new Location(newLocation.getI(),newLocation.getJ(),'7');
				if(dic.validate(current,'7','r'))
					list.add(new PipeState(right,l2));
			}
			else if(newVal == '|' || newVal == '-')
			{
				Location l = new Location(newLocation.getI(),newLocation.getJ(),'-');
				if(dic.validate(current,'-','r'))
					list.add(new PipeState(right,l));
			}
			else if(newVal == 'g'){
				Location l = new Location(newLocation.getI(),newLocation.getJ(),'g');
				if(dic.validate(current,'g','r'))
					list.add(new PipeState(right,l));
			}
		}
		//printList(list);
		return list;
	}
	
	private ArrayList<PipeState> getPosibleLeft()
	{
		Dictionary dic = new Dictionary();
		char current = location.getValue();
		
		////System.out.println("Checking neighbors Left");
		ArrayList<PipeState> list = new ArrayList<PipeState>();
		if(location.getJ() > 0)
		{
			Location newLocation = new Location(location.getI(),location.getJ()-1,((Matrix) getproblem()).getValue(location.getI(),location.getJ()-1));
			PipeState left = new PipeState(this,newLocation);
			char newVal = newLocation.getValue();
			if(newVal == 'F' || newVal == '7' ||newVal == 'J' || newVal == 'L'  )
			{
				Location l = new Location(newLocation.getI(),newLocation.getJ(),'F');
				if(dic.validate(current,'F','l'))
					list.add(new PipeState(left,l));
				Location l2 = new Location(newLocation.getI(),newLocation.getJ(),'L');
				if(dic.validate(current,'L','l'))
					list.add(new PipeState(left,l2));
			}
			else if(newVal == '|' || newVal == '-')
			{
				Location l = new Location(newLocation.getI(),newLocation.getJ(),'-');
				if(dic.validate(current,'-','l'))
					list.add(new PipeState(left,l));
			}
			else if(newVal == 'g'){
				Location l = new Location(newLocation.getI(),newLocation.getJ(),'g');
				if(dic.validate(current,'g','l'))
					list.add(new PipeState(left,l));
			}
		}
		//printList(list);
		return list;
	}
	
	public boolean equals(PipeState s){ // it’s easier to simply overload
		return problem.equals(s.problem); // if matrix not equals
	}
	
	@Override
	public String toString()
	{
		//String s = "[" + this.location.getI() + this.location.getJ() + "]" + this.location.getValue();
		String s = "[" + this.location.getI() + this.location.getJ() + "]";

		return s;
	}

	@Override
	public int genCost() {
		Location curLoc =  this.getLocation();
		Location endLoc = ((Matrix) this.problem).getEnd();
		return curLoc.diff(endLoc) + genLocationCost();
	}

	private int genLocationCost() {
		char papaDir = getParentDiraction();
		
		return 0;
	}







}
