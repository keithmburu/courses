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
	//TODO for parallel Karger-Stein
    }
    
    public static void main(String[] args) {
	Graph graph = Graph.generateGraphFromText();
	Karger k = new Karger(graph);
	//System.out.println(fastMincut(graph, graph.V));
	//System.out.println(karger(graph));
    }

    public void run() {
	//TODO for parallel Karger
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
     * Runs one iteration
     * Karger's algorithm down to t vertices.
     * Unless you intend to modify original graph,
     * it is recommended that you copy the graph 
     * before calling.
     * @param graph graph that will be contracted
     * @return cut weight after run
     **/

    public static double fastKarger(Graph g, double t) {
	double cutWeight = Double.POSITIVE_INFINITY;
	//TODO: Implement this function.  Exactly the same
	// as karger(), but stops at t vertices.
	return cutWeight;
    }

    /**
     * Runs one iteration
     * Karger's algorithm down to 2 vertices.
     * Unless you intend to modify original graph,
     * it is recommended that you copy the graph 
     * before calling.
     * @param graph graph that will be contracted
     * @return cut weight
     **/
    public static double karger(Graph graph) {
	// Get data of given graph
	int V = graph.getOriginalVertexCount();
	int E = graph.getOriginalEdgesCount();
	List<Edge> edges = graph.getEdges();	
	List<Subset> subsets = graph.getSubsets();
	
	for (int v = 0; v < V; v++) {
	    subsets.add(new Subset());
	    subsets.get(v).parent = v;
	    subsets.get(v).rank = 0;
	}
	
	graph.setCurrentVertexCount(V);    
	while (graph.getCurrentVertexCount() > 2) {
	    // Pick a random edge
	    int i = (int)(Math.random() * E);
	    
	    int subset1 = Subset.find(subsets, edges.get(i).s());
	    int subset2 = Subset.find(subsets, edges.get(i).t());
	    
	    if (subset1 == subset2) {
		continue;
	    } else {
		graph.decrementVertexCount();
		Subset.union(subsets, subset1, subset2);
	    }
	} //while
	

	double cutEdges = 0D;
	for (int i = 0; i < E; i++) {
	    int subset1 = Subset.find(subsets, edges.get(i).s());
	    int subset2 = Subset.find(subsets, edges.get(i).t());
	    if (subset1 != subset2) {
		cutEdges++; //TODO: change to handle weights

	    } 
	}
	return cutEdges;
    }
}

