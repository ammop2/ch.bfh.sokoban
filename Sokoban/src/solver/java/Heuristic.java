package solver.java;

/**
 * Command interface for varying heuristics in greedy BFS and A*
 * 
 * All implementing classes:
 * 		solver.java.BoxGoalHeuristic
 * 		solver.java.ManhattanHeuristic
 * @author Stephen Zhou
 * @uni szz2002
 *
 */
public interface Heuristic {
	
	public void score(BoardState state);

}
