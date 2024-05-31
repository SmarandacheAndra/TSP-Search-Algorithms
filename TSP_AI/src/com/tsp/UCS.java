package com.tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Implements the Uniform Cost Search (UCS) algorithm for solving the TSP problem.
 */
public class UCS {

	/**
	 * Executes the UCS algorithm.
	 * 
	 * @param matrix The distance matrix.
	 * @param start The starting city index.
	 * @return The path found by the UCS algorithm.
	 */
	public static List<Integer> ucsTSP(double[][] matrix, int start) {
		int n = matrix.length;
		PriorityQueue<Node> frontier = new PriorityQueue<>();
		frontier.add(new Node(start, List.of(start + 1), 0.0));
		List<Integer> bestPath = null;
		double minCost = Double.MAX_VALUE;

		while (!frontier.isEmpty()) {
			Node current = frontier.poll();

			// Check if all cities are visited
			if (current.getPath().size() == n) {
				double costToReturn = matrix[current.getCity()][start];
				current.getPath().add(start + 1); // Complete the cycle
				current.setCost(current.getCost() + costToReturn);
				if (current.getCost() < minCost) {
					minCost = current.getCost();
					bestPath = current.getPath();
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

		return bestPath != null ? bestPath : Collections.emptyList();
	}
}
