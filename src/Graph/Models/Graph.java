package Graph.Models;



class Node {
	int id;   // the subscript in nodes[]
	//Color color;  // color I should print in (red=source/sink)
    double x;  // x coordinate of node in drawing
    double y;  // y coordinate of node in drawing
    String nodeLabel;//name
    EdgeList adj;  // all edges touching Node in adjacency list form
}

class Edge {
    int from;   // endpoint of start node in terms of subscript in nodes[]
    int to;     // endpoint of end node in terms of subscript in nodes[]
    int cap; // capacity of edge in graph
    int flow; // current flow on edge
    //Color color; // current color of edge
    Edge(int f, int t, int l){
    	from = f; 
    	to = t; 
    	flow = 0; 
    	cap =l; 
    	//color = Color.black;
    }
}

class EdgeList{
	Edge edge;  // edge to store
	EdgeList next; // pointer to rest of edge list
	EdgeList(Edge e, EdgeList next) { this.edge = e; this.next = next;}
}
