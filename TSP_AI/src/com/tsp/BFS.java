package com.tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Implements the Breadth-First Search (BFS) algorithm for solving the TSP problem.
 */
public class BFS {

	/**
	 * Executes the BFS algorithm.
	 * 
	 * @param matrix The distance matrix.
	 * @param start The starting city index.
	 * @return The path found by the BFS algorithm.
	 */
	public static List<Integer> bfsTSP(double[][] matrix, int start) {
		int n = matrix.length;
		Queue<Node> frontier = new LinkedList<>();
		frontier.add(new Node(start, List.of(start + 1), 0));
		List<Integer> bestPath = null;
		double minCost = Double.MAX_VALUE;

		while (!frontier.isEmpty()) {
			Node current = frontier.poll();

			// Check if all cities are visited
			if (current.getPath().size() == n) {
				if (matrix[current.getCity()][start] != 0) {
					current.getPath().add(start + 1); // Complete the cycle
					current.setCost(current.getCost() + matrix[current.getCity()][start]);
					if (current.getCost() < minCost) {
						minCost = current.getCost();
						bestPath = new ArrayList<>(current.getPath());
					}
				}
				continue;
			}

			// Explore neighbors
			for (int i = 0; i < n; i++) {
				if (!current.getPath().contains(i + 1)) {
					List<Integer> newPath = new ArrayList<>(current.getPath());
					newPath.add(i + 1);
					double newCost = current.getCost() + matrix[current.getCity()][i];
					// Add the new state to the frontier
					frontier.add(new Node(i, newPath, newCost));
				}
			}
		}

		//Reverse the path
		if (bestPath != null) {
			Collections.reverse(bestPath);
		}

		return bestPath != null ? bestPath : Collections.emptyList();
	}
}
