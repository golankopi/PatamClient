package managment;

import logic.Problem;
import logic.Solution;

public interface Solver {
	public Solution solve(Problem problem);
	public Problem createProblem(Problem problem);
	
}
