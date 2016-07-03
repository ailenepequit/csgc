package Graph.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GraphAdjacencyMatrix {
	private final int MAX_NO_OF_VERTICES;
	private int V;
	private int adjacency_matrix[][];
	private int numEdges;
	private int color[];
	int count=0;
	private Course c = new Course();
	private Room r = new Room();
	private ArrayList<Course> clist = c.offeringsList();
	private ArrayList<Room> rlist = r.roomList();

	public GraphAdjacencyMatrix(int number_of_vertices) {
		MAX_NO_OF_VERTICES = number_of_vertices;
		V = number_of_vertices;
		adjacency_matrix = new int[MAX_NO_OF_VERTICES][MAX_NO_OF_VERTICES];
		sortRoomsByCapacity();
	}

	public void setEdge(int from_vertex, int to_vertex, int edge) {
		try {
			adjacency_matrix[from_vertex][to_vertex] = edge;
			numEdges++;
		} catch (ArrayIndexOutOfBoundsException indexBounce) {
			System.out.println("the vertex entered is not present");
		}
	}

	public void sortRoomsByCapacity() {
		Collections.sort(rlist, new Comparator<Room>() {
			@Override
			public int compare(Room r1, Room r2) {
				return Integer.compare(r2.getCapacity(), r1.getCapacity());
			}
		});
	}
	public int getNumEdges() {
		return numEdges;
	}

	public int getEdge(int from_vertex, int to_vertex) {
		try {
			return adjacency_matrix[from_vertex][to_vertex];
		} catch (ArrayIndexOutOfBoundsException indexBounce) {
			System.out.println("the vertex entered is not present");
		}
		return -1;
	}

	// Scan the row corresponding to vertex in the adjacency matrix, counting
	// the number of neighbors
	public int degree(int index) {
		int numNeighbors = 0;

		for (int j = 0; j <= index; j++)
			if (adjacency_matrix[index][j] == 1)
				numNeighbors++;

		for (int j = index + 1; j < MAX_NO_OF_VERTICES; j++)
			if (adjacency_matrix[j][index] == 1)
				numNeighbors++;

		return numNeighbors;
	}

	public void printGraph() {
		System.out.println("The adjacency matrix for given graph is");

		System.out.print("    ");
		for (int i = 0; i < MAX_NO_OF_VERTICES; i++) {
			System.out.print(i + " ");
		}

		System.out.println();

		for (int i = 0; i < MAX_NO_OF_VERTICES; i++) {
			if (i < 10)
				System.out.print(" " + i + "  ");
			if (i >= 10 && i < 100)
				System.out.print(i + "  ");
			if (i >= 100)
				System.out.print(i + " ");
			for (int j = 0; j < MAX_NO_OF_VERTICES; j++) {
				if (j < 10)
					System.out.print(getEdge(i, j) + " ");
				if (j >= 10 && j < 100)
					System.out.print(" " + getEdge(i, j) + " ");
				if (j >= 100)
					System.out.print("  " + getEdge(i, j) + " ");
			}
			System.out.println();
		}
	}
	
	public boolean isSafe(int v, int graph[][], int color[], int c) {
		for (int i = 0; i < V; i++){
			if (graph[v][i] == 1 && c == color[i])
				return false;
//			if(clist.get(v).getClassSize() > rlist.get(color[i]).getCapacity())
//				System.out.println(clist.get(v).getClassSize() + rlist.get(color[i]).getCapacity());
//				return false;
		}
		return true;
	}

	/*
	 * A recursive utility function to solve m coloring problem
	 */
	public boolean graphColoringUtil(int graph[][], int m, int color[], int v) {
		/*
		 * base case: If all vertices are assigned a color then return true
		 */
		if (v == V)
			return true;

		/*
		 * Consider this vertex v and try different colors
		 */
		for (int c = 0; c < m; c++) {
			/*
			 * Check if assignment of color c to v is fine
			 */
			
			if (isSafe(v, graph, color, c)) {
					color[v] = c;
//					System.out.println(count);
//					count++;

				/*
				 * recur to assign colors to rest of the vertices
				 */
				if (graphColoringUtil(graph, m, color, v + 1))
					return true;

				/*
				 * If assigning color c doesn't lead to a solution then remove
				 * it
				 */
				color[v] = 0;
			}
		}

		/*
		 * If no color can be assigned to this vertex then return false
		 */
		return false;
	}

	/*
	 * This function solves the m Coloring problem using Backtracking. It mainly
	 * uses graphColoringUtil() to solve the problem. It returns false if the m
	 * colors cannot be assigned, otherwise return true and prints assignments
	 * of colors to all vertices. Please note that there may be more than one
	 * solutions, this function prints one of the feasible solutions.
	 */
	public boolean graphColoring(int m) {
		// Initialize all color values as 0. This
		// initialization is needed correct functioning
		// of isSafe()
		color = new int[V];
		for (int i = 0; i < V; i++)
			color[i] = 0;

		// Call graphColoringUtil() for vertex 0
		if (!graphColoringUtil(adjacency_matrix, m, color, 0)) {
			System.out.println("Solution does not exist");
			return false;
		}

		// Print the solution
		//printSolution(color);
		countOccurence();
		return true;
	}

	/* A utility function to print solution */
	public void printSolution(int color[]) {
		System.out.println("Solution Exists: Following" + " are the assigned colors");
		for (int i = 0; i < V; i++)
			System.out.print(" " + color[i] + " ");
		System.out.println();
		
	}
	
	public int[] getSolution(){
		return color;
	}
	
	public int countColor(int c){
		int count = 0;
		for(int i = 0; i < color.length; i++){
			if(color[i] == c)
				count++;
		}
		return count;
	}
	public void countOccurence(){
		try{
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < color.length; i++) {
            int key = color[i];
            if (map.containsKey(key)) {
                int occurrence = map.get(key);
                occurrence++;
                map.put(key, occurrence);
            } else {
                map.put(key, 1);
            }
        }

        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            int key = (Integer) iterator.next();
            int occurrence = map.get(key);

            System.out.println(key + " occur " + occurrence + " time(s).");
        }
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
    }
}