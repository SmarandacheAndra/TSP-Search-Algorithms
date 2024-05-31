# Traveling Salesman Problem Solver

This repository contains a Java application for solving the Traveling Salesman Problem (TSP) using three search algorithms: Breadth-First Search (BFS), Uniform Cost Search (UCS), and A* Search.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- A Java IDE (e.g., IntelliJ IDEA, Eclipse) or a text editor and command line tools

## Project Structure

```
TSP-Search-Algorithms/
├── src/
│   └── com/
│        └── tsp/
│            ├── App.java
│            ├── BFS.java
│            ├── Node.java
│            ├── UCS.java
│            ├── AStar.java
│            ├── TSP_DataGenerator.java
│            ├── TSP_FileReader.java
├── resources/
│   ├── generated11.tsp
│   └── generated4.tsp
│   └── generated7.tsp
│   └── ulysses16.tsp
│   └── ulysses22.tsp
├── README.md
```

## Compiling and Running the Application

1. **Clone the Repository**

    Clone this repository to your local machine using the following command:
    ```sh
    git clone https://github.com/SmarandacheAndra/TSP-Search-Algorithms.git
    cd TSP-Search-Algorithms
    ```

2. **Using a Java IDE**

    - **IntelliJ IDEA**
        1. Open IntelliJ IDEA.
        2. Click on `File > Open` and select the `TSP-Search-Algorithms` directory.
        3. Wait for the IDE to index the project and download dependencies.
        4. Right-click on `App.java` in the `Project` explorer.
        5. Click `Run 'App.main()'`.

    - **Eclipse**
        1. Open Eclipse.
        2. Click on `File > Open Projects from File System...`.
        3. Select the `TSP-Search-Algorithms` directory and click `Finish`.
        4. Wait for Eclipse to import and build the project.
        5. Right-click on `App.java` in the `Package Explorer`.
        6. Click `Run As > Java Application`.

3. **Using Command Line**

    - **Compile the Code**

      Open a terminal and navigate to the `TSP-Search-Algorithms` directory. Run the following commands to compile the code:
      ```sh
      javac -d out TSP_AI/src/com/tsp/*.java
      ```
       ```sh
      xcopy /E /I TSP_AI\resources out\resources
       ```
      ```sh
      cd out
       ```

    - **Run the Application**

      After successful compilation, run the application with the following command:
      ```sh
      java com.tsp.App
      ```
  4. **Using Command Line with Maven**
    - **Compile the Code**

      Open a terminal and navigate to the `TSP-Search-Algorithms` directory. Run the following command to compile the code:
      ```sh
      mvn compile
      ```
     - **Build the Application**

      After compiling the code, build the application JAR file using the following command:
      ```sh
      mvn package
      ```

      This will create a JAR file in the `target` directory.

 - **Run the Application**

      Run the application with the following command:
      ```sh
      java -cp target/TSP-Search-Algorithms-1.0-SNAPSHOT.jar com.tsp.App
      ```

## Example Input Files

The project includes sample input files in the `resources` directory. These files define the coordinates of cities for the TSP problem.

- `generated11.tsp`: Randomly generated 11-city problem.
- `generated7.tsp`: Randomly generated 7-city problem.
- `generated4.tsp`: Randomly generated 4-city problem.
- `ulysses22.tsp`: TSP instance with 22 cities from the TSP-LIB.
- `ulysses16.tsp`: TSP instance with 16 cities from the TSP-LIB.

## Generating TSP Input Files

To generate new TSP input files, you can use the `TSP_DataGenerator` in `App.java` and specify the desired number of cities:
```java
TSP_DataGenerator.generateTSPFile("resources/generated11.tsp", 11);
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
