package com.tsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to read TSP input data files and convert them to a distance matrix.
 */
public class TSP_FileReader {
	public static double[][] readInputFile(String filename) throws IOException {
		List<double[]> coordinates = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			boolean readingCoords = false;

			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.equals("NODE_COORD_SECTION")) {
					readingCoords = true;
					continue;
				}
				if (line.equals("EOF")) {
					break;
				}
				if (readingCoords) {
					String[] parts = line.split("\\s+");
					if (parts.length != 3) {
						throw new IOException("Invalid coordinate line: " + line);
					}
					double latitude = Double.parseDouble(parts[1]);
					double longitude = Double.parseDouble(parts[2]);
					coordinates.add(new double[]{latitude, longitude});
				}
			}

			int size = coordinates.size();
			double[][] matrix = new double[size][size];
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (i != j) {
						matrix[i][j] = euclideanDistance(coordinates.get(i), coordinates.get(j));
					} else {
						matrix[i][j] = 0;
					}
				}
			}
			return matrix;
		}
	}

	/**
	 * Calculates the Euclidean distance between two coordinates.
	 * 
	 * @param coord1 The first coordinate.
	 * @param coord2 The second coordinate.
	 * @return The distance between the two coordinates.
	 */
	private static double euclideanDistance(double[] coord1, double[] coord2) {
		double x1 = coord1[0];
		double y1 = coord1[1];
		double x2 = coord2[0];
		double y2 = coord2[1];

		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
}
