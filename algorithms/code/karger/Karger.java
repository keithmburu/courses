import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ConcurrentSkipListSet;
public class Karger extends RecursiveAction {
    ConcurrentSkipListSet<Double> cutsSet; //TODO: share this between instances to store cuts of multiple runs
    Graph g;
    public Karger(Graph graph) {
	this.g = graph;
	this.cutsSet = new ConcurrentSkipListSet<Double>();
    }

    public Karger(Graph graph, ConcurrentSkipListSet cuts) {
	this.g = graph;
	this.cutsSet = cuts;
    }

    public void compute() {
	//TODO
    }
    
    public static void main(String[] args) {
	Graph graph = Graph.generateGraphFromText();
	Karger k = new Karger(graph);
	//System.out.println(fastMincut(graph, graph.V));
	//System.out.println(karger(graph));
    }

    public void run() {
	//TODO
    }


    public static double fastMincut(Graph g) {
	double mincut = Double.POSITIVE_INFINITY;
	//TODO: implement Karger-Stein
	return mincut;
    }


    public static double mincut(Graph g) {
	double mincut = Double.POSITIVE_INFINITY;
	//TODO: run karger(g) tn^2=t|V|^2times and take the min
	return mincut;
    }


    /**
     * Karger
     **/
    public static double fastKarger(Graph g, double t) {
	List<Edge> edges = g.edges;
	
	// Create V subsets for single vertices
	for (int v = 0; v < g.V; v++) {
	    g.subsets.add(new Subset());
	    g.subsets.get(v).parent = v;
	    g.subsets.get(v).rank = 0;
	}
	//initialize contracted verices count
	g.vertices = g.V;
	
	while (g.vertices > t) {
	    // Pick a random edge from all edges
	    int i = (int)(Math.random() * g.E);
	    
	    // Find vertices (or sets) of two corners of current edge
	    int subset1 = Subset.find(g.subsets, edges.get(i).s());
	    int subset2 = Subset.find(g.subsets, edges.get(i).t());
	    if (subset1 == subset2) {
		continue;
	    }
	    else {
		g.vertices--; //count removed vertices
		//merge vertices of contracted edge
		Subset.union(g.subsets, subset1, subset2);
	    }
	}
	
	double cutedges = 0D;
	for (int i = 0; i < g.E; i++) {
	    int subset1 = Subset.find(g.subsets, g.edges.get(i).s());
	    int subset2 = Subset.find(g.subsets, g.edges.get(i).t());
	    if (subset1 != subset2) {
		cutedges++; //TODO: chanage to handle weights
	    } 
	}
	return cutedges;
	
    }
	
    public static double karger(Graph graph) {
	// Get data of given graph
	int V = graph.V;
	int E = graph.E;
	List<Edge> edges = graph.edges;	
	List<Subset> subsets = graph.subsets;
	
	for (int v = 0; v < V; v++) {
	    subsets.add(new Subset());
	    subsets.get(v).parent = v;
	    subsets.get(v).rank = 0;
	}
	
	graph.vertices = V;    
	while (graph.vertices > 2) {
	    // Pick a random edge
	    int i = (int)(Math.random() * E);
	    
	    int subset1 = Subset.find(subsets, edges.get(i).s());
	    int subset2 = Subset.find(subsets, edges.get(i).t());
	    
	    if (subset1 == subset2) {
		continue;
	    } else {
		graph.vertices--;
		Subset.union(subsets, subset1, subset2);
	    }
	} //while
	

	double cutedges = 0D;
	for (int i = 0; i < E; i++) {
	    int subset1 = Subset.find(subsets, edges.get(i).s());
	    int subset2 = Subset.find(subsets, edges.get(i).t());
	    if (subset1 != subset2) {
		cutedges++; //TODO: change to handle weights

	    } 
	}
	return cutedges;
    }
}

