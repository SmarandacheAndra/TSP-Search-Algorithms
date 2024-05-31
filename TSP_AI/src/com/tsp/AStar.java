package com.tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Implements the A* search algorithm for solving the TSP problem.
 */
public class AStar {

	/**
	 * Executes the A* search algorithm.
	 * 
	 * @param matrix The distance matrix.
	 * @param start The starting city index.
	 * @return The path found by the A* algorithm.
	 */
	public static List<Integer> aStarTSP(double[][] matrix, int start) {
		int n = matrix.length;
		PriorityQueue<Node> frontier = new PriorityQueue<>();
		Set<Integer> initialVisited = new HashSet<>();
		initialVisited.add(start);
		frontier.add(new Node(start, List.of(start + 1), initialVisited, 0, heuristic(start, initialVisited, matrix)));

		List<Integer> bestPath = null;
		double minCost = Double.MAX_VALUE;

		while (!frontier.isEmpty()) {
			Node current = frontier.poll();

			// Check if all cities are visited
			if (current.getPath().size() == n) {
				double costToReturn = matrix[current.getCity()][start];
				List<Integer> finalPath = new ArrayList<>(current.getPath());
				finalPath.add(start + 1); // Complete the cycle
				double totalCost = current.getCost() + costToReturn;
				if (totalCost < minCost) {
					minCost = totalCost;
					bestPath = finalPath;
				}
				continue;
			}

			// Explore neighbors
			for (int i = 0; i < n; i++) {
				if (!current.getVisited().contains(i)) {
					List<Integer> newPath = new ArrayList<>(current.getPath());
					newPath.add(i + 1);
					Set<Integer> newVisited = new HashSet<>(current.getVisited());
					newVisited.add(i);
					// Calculate the new cost to reach this neighbor
					double newCost = current.getCost() + matrix[current.getCity()][i];
					// Calculate the priority for the priority queue
					double newPriority = newCost + heuristic(i, newVisited, matrix);
					// Add the new state to the frontier
					frontier.add(new Node(i, newPath, newVisited, newCost, newPriority));
				}
			}
		}

		return bestPath != null ? bestPath : Collections.emptyList();
	}

	/**
	 * Heuristic function to estimate the minimum distance to the remaining cities.
	 * 
	 * @param city The current city index.
	 * @param visited The set of visited city indices.
	 * @param matrix The distance matrix.
	 * @return The estimated minimum distance to the remaining cities.
	 */
	private static double heuristic(int city, Set<Integer> visited, double[][] matrix) {
		double minDist = Double.MAX_VALUE;
		for (int i = 0; i < matrix.length; i++) {
			if (!visited.contains(i) && i != city) {
				minDist = Math.min(minDist, matrix[city][i]);
			}
		}
		return minDist == Double.MAX_VALUE ? 0 : minDist;
	}
}
