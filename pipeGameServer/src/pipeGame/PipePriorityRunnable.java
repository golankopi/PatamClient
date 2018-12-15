package pipeGame;



public abstract class PipePriorityRunnable implements Comparable<PipePriorityRunnable>, Runnable {
	int priority;
	
	@Override
	public int compareTo(PipePriorityRunnable o) {
		return this.priority - o.priority;
	}

	public PipePriorityRunnable(int priority){
		this.priority = priority;
	}

}