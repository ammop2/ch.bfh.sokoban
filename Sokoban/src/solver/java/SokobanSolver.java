package solver.java;

import main.java.Map;

import java.io.IOException;


/**
 * A really shoddy command line interface for solving Sokoban with:
 * - BFS
 * - DFS
 * - Uniform cost search
 * - Greedy best first search
 * - A* search
 * 
 * @author Stephen Zhou
 * @uni szz2002
 *
 */
public class SokobanSolver {
	
	public static String solve(Map map) {
		try {
			// TODO some form of input validation
			String flag = "-b";
			BoardState initialBoard = BoardState.parseBoardInput(map);
			AbstractSolver solver = null;
			System.out.println(initialBoard);
			if (flag.equals("-b")) {
				solver = new BFSSolver(initialBoard);
			}
			else if (flag.equals("-d")) {
				solver = new DFSSolver(initialBoard);
			}
			else if (flag.equals("-u")) {
				solver = new UniformCostSolver(initialBoard);
			}
			else if (flag.equals("-ab")) {
				solver = new AStarSolver(initialBoard, new BoxGoalHeuristic());
			}
			else if (flag.equals("-gb")) {
				solver = new GreedyBFSSolver(initialBoard, new BoxGoalHeuristic());
			}
			else if (flag.equals("-am")) {
				solver = new AStarSolver(initialBoard, new ManhattanHeuristic());
			}
			else if (flag.equals("-gm")) {
				solver = new GreedyBFSSolver(initialBoard, new ManhattanHeuristic());
			}
			else {
				System.out.println("Invalid command");
			}
			
			if (solver != null) {
				String solution = solver.search();
				int nodesExplored = solver.getNodesExplored();
				int previouslySeen = solver.getPreviouslySeen();
				int queueLength = solver.getFringeLength();
				int visitedLength = solver.getVisitedLength();
				long timeElapsed = solver.getElapsedTimeMillis();
				System.out.println("Solution: " + solution);
				System.out.println("Nodes explored: " + nodesExplored);
				System.out.println("Previously seen: " + previouslySeen);
				System.out.println("Fringe: " + queueLength);
				System.out.println("Explored set: " + visitedLength);
				System.out.println("Millis elapsed: " + timeElapsed);
				return solution;
			}
		} catch (IOException e) {
			System.out.println("Puzzle file not found");
		} catch (NoSolutionException e) {
			System.out.println("Solution does not exist");
		}
		return "";
	}

}
