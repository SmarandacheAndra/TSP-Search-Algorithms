package com.tsp;

import java.io.IOException;
import java.util.List;

/**
 * The main class to run the application. It generates the TSP input data,
 * reads the distance matrix, and executes BFS, UCS, and A* search algorithms.
 */
public class App {

	public static void main(String[] args) {
		try {
			// Generate a TSP input file
			TSP_DataGenerator.generateTSPFile("resources/generated4.tsp", 4); 
			// Read the distance matrix from the generated file(or from the TSP.lib files)
			double[][] matrix = TSP_FileReader.readInputFile("resources/generated4.tsp");
			System.out.println("Processing TSP of dimension " + matrix.length + ":\n");

			long startTime, endTime;

			// BFS
			startTime = System.nanoTime();
			List<Integer> path_bfs = BFS.bfsTSP(matrix, 0);
			endTime = System.nanoTime();
			System.out.println("BFS path:");
			display(matrix, path_bfs, endTime - startTime);

			// UCS
			startTime = System.nanoTime();
			List<Integer> path_ucs = UCS.ucsTSP(matrix, 0);
			endTime = System.nanoTime();
			System.out.println("UCS path:");
			display(matrix, path_ucs, endTime - startTime);

			// A*
			startTime = System.nanoTime();
			List<Integer> path_astar = AStar.aStarTSP(matrix, 0);
			endTime = System.nanoTime();
			System.out.println("AStar path:");
			display(matrix, path_astar, endTime - startTime);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Displays the path, total cost, and duration of the algorithm execution.
	 * 
	 * @param matrix The distance matrix.
	 * @param path The path taken by the algorithm.
	 * @param duration The time taken by the algorithm in nanoseconds.
	 */
	static public void display(double[][] matrix, List<Integer> path, long duration) {
		double totalCost = 0;
		if (!path.isEmpty()) {
			for (int j = 0; j < path.size() - 1; j++) {
				// Adjust index for 1-based indexing in the output
				int from = path.get(j) - 1;
				int to = path.get(j + 1) - 1;
				totalCost += matrix[from][to];
			}
			// Add the cost to return to the starting city
			int from = path.get(path.size() - 1) - 1;
			int to = path.get(0) - 1;
			totalCost += matrix[from][to];
		}

		System.out.printf("Path: %s%n", path);
		System.out.printf("Total cost: %.2f%n", totalCost);
		System.out.printf("Time taken: %.2f ms%n%n", duration / 1e6);
	}
}
