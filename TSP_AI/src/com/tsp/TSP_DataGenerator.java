package com.tsp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;

/**
 * Utility class to generate TSP input data files.
 */
public class TSP_DataGenerator {

	/**
	 * Generates a TSP input file with randomly generated coordinates.
	 * 
	 * @param filename The name of the file to write the data to.
	 * @param numCities The number of cities.
	 * @throws IOException If an I/O error occurs.
	 */
	public static void generateTSPFile(String filename, int numCities) throws IOException {
		Random rand = new Random();
		double[][] coordinates = new double[numCities][2];

		for (int i = 0; i < numCities; i++) {
			// Generate random double between 10 and 99(based on the ulysses input samples)
			coordinates[i][0] = 10 + (89 * rand.nextDouble()); 
			coordinates[i][1] = 10 + (89 * rand.nextDouble()); 
		}

		// Write the headers and coordinates in a style similar to the ulysses input from TSP-LIB
		try (FileWriter writer = new FileWriter(filename)) {
			writer.write("NAME : TSP Generated Input" + numCities + "\n");
			writer.write("COMMENT : " + numCities + "-city problem (Randomly generated)\n");
			writer.write("TYPE : TSP\n");
			writer.write("DIMENSION : " + numCities + "\n");
			writer.write("EDGE_WEIGHT_TYPE : EUC_2D\n");
			writer.write("NODE_COORD_SECTION\n");

			for (int i = 0; i < numCities; i++) {
				writer.write(String.format(Locale.US, "%d %.2f %.2f%n", (i + 1), coordinates[i][0], coordinates[i][1]));
			}

			writer.write("EOF\n");
		}
	}
}
