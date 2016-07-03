package Graph.Models;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

public class Graph {
	private int V; // No. of vertices
	private LinkedList<Integer> adj[]; // Adjacency List
	int result[];
	int numEdges;
	int[] colors;

	// Constructor
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Graph(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList();
	}

	public LinkedList<Integer> adjacencyList() {
		return null;

	}

	// Function to add an edge into the graph
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v); // Graph is undirected
		numEdges++;
	}

	public int getNumEdges(){
		return numEdges;
	}
	
	// Assigns colors (starting from 0) to all vertices and
	// prints the assignment of colors
	public void greedyColoring() {
		result = new int[V];
		// Assign the first color to first vertex
		result[0] = 0;
		int count =0;
		// Initialize remaining V-1 vertices as unassigned
		for (int u = 1; u < V; u++)
			result[u] = -1; // no color is assigned to u

		// A temporary array to store the available colors. True
		// value of available[cr] would mean that the color cr is
		// assigned to one of its adjacent vertices
		boolean available[] = new boolean[V];
		for (int cr = 0; cr < V; cr++){
//			if(count == 12){ 
//				available[cr]= false;
//				count = 0;
//			}
			available[cr] = false;
		}
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

			for (cr = 0; cr < V; cr++) {
				if (available[cr] == false)
					break;

//				if (countColor(cr) == 12) {
//					System.out.println(cr + " = " +countColor(cr));
//					break;
//				}
			}
			// insert constraints : before assignment check constraints
			//
			//if(countColor(cr) < 12)
			
			//System.out.println(countColor(cr));

			
				result[u] = cr; // Assign the found color
				//count++;
			// Reset the values back to false for the next iteration
			it = adj[u].iterator();
			while (it.hasNext()) {
				int i = it.next();
				if (result[i] != -1)
					available[result[i]] = false;
			}
		}
	}
	
	public void countOccurrence(){
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < colors.length; i++) {
            int key = colors[i];
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
    }

	public int degree(int index) {
		int numNeighbors = 0;

		// Scan the row corresponding to vertex in the adjacency matrix,
		// counting the number of neighbors
		for (int j = 0; j <= index; j++)
			if (adj[index].contains(adj[j]))
				numNeighbors++;

		for (int j = index + 1; j < V; j++)
			if (adj[j].contains(adj[index]))
				numNeighbors++;

		return numNeighbors;
	}

	public int[] getResult() {
		//countOccurrence();
		color();
		return result;
	}

	public int numColorsUsed() {
		int max = result[0];
		for (int counter = 1; counter < result.length; counter++) {
			if (result[counter] > max) {
				max = result[counter];
			}
		}
		return max + 1;
	}
	
	public int countColor(int color){
		int occurrence = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < result.length; i++) {
            int key = result[i];
            if (map.containsKey(key)) {
                occurrence = map.get(key);
                occurrence++;
                map.put(key, occurrence);
            } else {
                map.put(key, 1);
            }
        }
        return occurrence;
	}
	
	public int[] color() {
	    int n = adj.length;
	    BitSet[] used = new BitSet[n];
	    colors = new int[n];
	    PriorityQueue<Long> q = new PriorityQueue<>(n);
	    for (int i = 0; i < n; i++) {
	      used[i] = new BitSet();
	      colors[i] = -1;
	      q.add((long) i);
	    }
	    for (int i = 0; i < n; i++) {
	      int bestu;
	      while (true) {
	        bestu = q.remove().intValue();
	        if (colors[bestu] == -1)
	          break;
	      }
	      int c = used[bestu].nextClearBit(0);
	      colors[bestu] = c;
	      for (int v : adj[bestu]) {
	        if (!used[v].get(c)) {
	          used[v].set(c);
	          if (colors[v] == -1)
	            q.add(v - ((long) used[v].cardinality() << 32));
	        }
	      }
	    }
//	    for(int i = 0; i < n; i++)
//	    	System.out.println(colors[i]);
	    countOccurrence();
	    return colors;
	  }
	
}
