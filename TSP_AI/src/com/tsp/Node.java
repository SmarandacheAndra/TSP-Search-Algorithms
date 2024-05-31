package com.tsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Represents a node in the search space for the TSP problem.
 */
public class Node implements Comparable<Node> {
	private int city;
	private List<Integer> path;
	private Set<Integer> visited;
	private double cost;
	private int priority;
	private double heuristic;

	public Node(int city, List<Integer> path, Set<Integer> visited, double cost, double heuristic) {
		this.city = city;
		this.path = path;
		this.visited = visited;
		this.cost = cost;
		this.heuristic = heuristic;
	}

	public Node(int city, List<Integer> path, double cost, int priority) {
		this.setCity(city);
		this.setPath(new ArrayList<>(path));
		this.setCost(cost);
		this.priority = priority;
	}

	public Node(int city, List<Integer> path, double cost) {
		this.setCity(city);
		this.setPath(new ArrayList<>(path));
		this.setCost(cost);
		this.priority = 0;
	}

	@Override
	public int compareTo(Node other) {
		return Double.compare(this.priority, other.priority);
	}

	public List<Integer> getPath() {
		return path;
	}

	public void setPath(List<Integer> path) {
		this.path = path;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public Set<Integer> getVisited() {
		return visited;
	}

	public double getHeuristic() {
		return heuristic;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
}
