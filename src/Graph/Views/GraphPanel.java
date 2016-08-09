package Graph.Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JPanel;

import Graph.Models.Subject;

class Node {
	int id;
	Color color;
	double x;
	double y;
	String nodeLabel;
	EdgeList adj;
}

class Edge {
	int from;
	int to;
	int cap;
	int flow;
	Color color;

	Edge(int f, int t, int c) {
		from = f;
		to = t;
		flow = 0;
		cap = c;
		color = Color.black;
	}
}

class EdgeList {
	Edge edge;
	EdgeList next;

	EdgeList(Edge e, EdgeList next) {
		this.edge = e;
		this.next = next;
	}
}

@SuppressWarnings("serial")
public class GraphPanel extends JPanel {
	Subject subject = new Subject();
	ArrayList<Subject> subjectList = subject.subjectList("2nd");
	int N = subjectList.size();
	int maxNodes = N;
	int maxFlow = 99999;
	int numNodes;
	int numEdges;
	Node[] nodes = new Node[maxNodes + 4];
	Edge[] edges = new Edge[maxNodes];
	Graphics graph;

	/* return index of node matching nodeLabel */
	GraphPanel(Graphics graph) {
		this.graph = graph;
		numNodes = numEdges = 0;
		setBounds(253, 73, 1001, 400);
		setVisible(false);
	}

	int findNode(String nodeLabel) {
		for (int i = 0; i < numNodes; i++) {
			if (nodes[i].nodeLabel.equals(nodeLabel)) {
				return i;
			}
		}
		return addNode(nodeLabel);
	}

	/* add new node with label nodeLabel an random location, return index */
	int addNode(String nodeLabel) {
		return addNode(nodeLabel, 30 + (int) (300 * Math.random()), 30 + (int) (300 * Math.random()), Color.CYAN);
	}

	int addNode(String nodeLabel, double x, double y, Color color) {
		Node n = new Node();
		n.x = x;
		n.y = y;
		n.color = color;
		n.nodeLabel = nodeLabel;
		nodes[numNodes] = n;
		n.id = numNodes;
		return numNodes++;
	}

	void addEdge(String from, String to, int cap) {
		int fromSub = findNode(from);
		int toSub = findNode(to);
		Edge e = new Edge(fromSub, toSub, cap);
		edges[numEdges++] = e;
		nodes[fromSub].adj = new EdgeList(e, nodes[fromSub].adj);
		nodes[toSub].adj = new EdgeList(e, nodes[toSub].adj);
	}
	
	public String display(String from, String to, int cap){
		String log = "Connecting " + from + " -> " + to + " : " + cap + "\n";
		return log;
	}

	// Draw node n on graphics g knowing sizes of fonts fm
	public void paintNode(Graphics g, Node n, FontMetrics fm) {
		int x = (int) n.x; // grab current x location
		int y = (int) n.y; // grab current y location
		g.setColor(n.color);
		int w = fm.stringWidth(n.nodeLabel) + 10; // width of nodeLabel
		int h = fm.getHeight() + 4; // height of nodeLabel
		g.fillRect(x - w / 2, y - h / 2, w, h);
		g.setColor(Color.BLUE);
		g.drawRect(x - w / 2, y - h / 2, w - 1, h - 1);
		g.drawString(n.nodeLabel, x - (w - 10) / 2, (y - (h - 4) / 2) + fm.getAscent());
	}

	public synchronized void update() {
		Graphics g = getGraphics();
		update(g);
	}

	Node pick; // Node you have picked
	Image offscreen; // Double buffering so you don't get flicker
	Dimension offscreensize; // Size of screen
	Graphics offgraphics; // Graphics for offscreen
	final Color labelDistanceColor = Color.gray;

	public synchronized void update(Graphics g) {

		Dimension d = getSize(); // current size of graphics
		if ((offscreen == null) || (d.width != offscreensize.width) || (d.height != offscreensize.height)) {
			offscreen = createImage(d.width, d.height);
			offscreensize = d;
			offgraphics = offscreen.getGraphics();
			Font f = new Font("Helvetica", Font.PLAIN, 10);
			offgraphics.setFont(f);
		}
		offgraphics.setColor(getBackground());
		offgraphics.fillRect(0, 0, d.width, d.height);

		// Draw each edge
		for (int i = 0; i < numEdges; i++) {
			Edge e = edges[i];
			// System.out.println("drawEdge from " + e.from);
			int x1 = (int) nodes[e.from].x;
			int y1 = (int) nodes[e.from].y;
			int x2 = (int) nodes[e.to].x;
			int y2 = (int) nodes[e.to].y;

			offgraphics.setColor(e.color);
			offgraphics.drawLine(x1, y1, x2, y2);
			int rad = 4;
			int circlex = (int) (x1 + .85 * (x2 - x1)) - rad;
			int circley = (int) (y1 + .85 * (y2 - y1)) - rad;
			offgraphics.fillOval(circlex, circley, rad * 2, rad * 2);
			String nodeLabel = String.valueOf(e.cap);
			offgraphics.setColor(labelDistanceColor);
			offgraphics.drawString(nodeLabel, x1 + 2 * (x2 - x1) / 3, y1 + 2 * (y2 - y1) / 3);
			offgraphics.setColor(Color.black);

		}

		FontMetrics fm = offgraphics.getFontMetrics();
		// Draw each node
		for (int i = 0; i < numNodes; i++) {
			paintNode(offgraphics, nodes[i], fm);
		}
		// put the offscreen image to the screen
		g.drawImage(offscreen, 0, 0, null);

	}

	// match clicked location with known locations of nodes to find closest node
	// Note that node is "picked" so you can color it differently
	public synchronized boolean mouseDown(Event evt, int x, int y) {
		double bestdist = Double.MAX_VALUE; // Distance between mouse click and
											// closest node
		for (int i = 0; i < numNodes; i++) {
			Node n = nodes[i];
			double dist = (n.x - x) * (n.x - x) + (n.y - y) * (n.y - y);
			if (dist < bestdist) {
				pick = n;
				bestdist = dist;
			}
		}
		pick.x = x;
		pick.y = y;
		repaint();
		return true;
	}

	// Update the coordinates of picked node and redraw */
	public synchronized boolean mouseDrag(Event evt, int x, int y) {
		pick.x = x;
		pick.y = y;
		repaint();
		return true;
	}
	
	// When you release the mouse, the node is no longer selected
	// In our case, I made the node fixed (so it wouldn't change automatically)
	public synchronized boolean mouseUp(Event evt, int x, int y) {
		pick.x = x;
		pick.y = y;
		pick = null;
		repaint();
		return true;
	}
}
