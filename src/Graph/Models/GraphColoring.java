package Graph.Models;

//A Java program to implement greedy algorithm for graph coloring
import java.util.*;
import java.util.LinkedList;

//This class represents an undirected graph using adjacency list
class GraphColoring {
	private int V; // No. of vertices
	private LinkedList<Integer> adj[]; // Adjacency List

	@SuppressWarnings({ "unchecked", "rawtypes" })
	GraphColoring(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList();
	}

	// Function to add an edge into the graph
	void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v); 
	}

	// Assigns colors (starting from 0) to all vertices and prints the assignment of colors
	void greedyColoring() {
		int result[] = new int[V];

		// Assign the first color to first vertex
		result[0] = 0;

		// Initialize remaining V-1 vertices as unassigned
		for (int u = 1; u < V; u++)
			result[u] = -1; // no color is assigned to u

		// A temporary array to store the available colors. True
		// value of available[cr] would mean that the color cr is
		// assigned to one of its adjacent vertices
		boolean available[] = new boolean[V];
		for (int cr = 0; cr < V; cr++)
			available[cr] = false;

		// Assign colors to remaining V-1 vertices
		for (int u = 1; u < V; u++) {
			// Process all adjacent vertices and flag their colors
			// as unavailable
			Iterator<Integer> it = adj[u].iterator();
			while (it.hasNext()) {
				int i = it.next();
				if (result[i] != -1)
					available[result[i]] = true;
			}

			// Find the first available color
			int cr;
			for (cr = 0; cr < V; cr++)
				if (available[cr] == false)
					break;

			result[u] = cr; // Assign the found color

			// Reset the values back to false for the next iteration
			it = adj[u].iterator();
			while (it.hasNext()) {
				int i = it.next();
				if (result[i] != -1)
					available[result[i]] = false;
			}
		}

		// print the result
		for (int u = 0; u < V; u++)
			System.out.println("Vertex " + u + " --->  Color " + result[u]);
	}
}

/*public class GraphColoring {

	private Object[] graph, savedGraph, coloredGraph, colors;
	private int k = 1, d = 0, numVers, vertexNo;
	private int G[][], x[], n, m, soln;

	public void initGraph() {
		getGraphFromDB();
	}

	public void getGraphFromDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/absked";
			Connection conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			ResultSet srs = st.executeQuery("SELECT offerno, description, FROM subjects");
			while (srs.next()) {

			}
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	public void mColoring(int k) { // backtracking function
		for (int i = 1; i <= n; i++) {
			next_color(k); // coloring kth vertex
			if (x[k] == 0)
				return; // if unsuccessful then backtrack
			if (k == n) // if all colored then show
				write();
			else
				mColoring(k + 1);  successful but still left to color 
		}
	}

	private void write() {
		System.out.print("\nColoring(V C) #  " + (++soln) + "-->");
		for (int i = 1; i <= n; i++)
			System.out.print("\t(" + i + " " + x[i] + ")"); // solution vector
	}

	private void next_color(int k) {
		do {
			int i = 1;
			x[k] = (x[k] + 1) % (m + 1);
			if (x[k] == 0)
				return;
			for (i = 1; i <= n; i++)
				if (G[i][k] != 0 && x[k] == x[i]) // checking adjacency and not same color
					break;
			if (i == n + 1)
				return; // new color found
		} while (true);
	}
}
*/