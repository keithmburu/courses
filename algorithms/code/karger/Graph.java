import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class Graph {
    private final int V, E; //original number of vertices and edges
    private final List<Edge> edges; //graph defined by edges
    private List<Subset> subsets;
    private int vertices; //vertices remaining after contraction

    public Graph (int V, int E) {
	this.V = V;
	this.E = E;
	this.vertices = V;
	this.edges = new ArrayList<Edge>(E);
	this.subsets = new ArrayList<Subset>(V); 
    }

    public int getOriginalVertexCount() {
	return V;
    }

    public int getOriginalEdgesCount() {
	return E;
    }

    public List<Edge> getEdges() {
	return edges;
    }

    public List<Subset> getSubsets() {
	return subsets;
    }


    /**
     * Decreases number of vertices after contraction.
     **/
    public void decrementVertexCount() {
	vertices--;
    }

    /**
     * Returns count of vertices afer contraction
     **/
    public int getCurrentVertexCount() {
	return vertices;
    }

    public void setCurrentVertexCount(int vertices) {
	this.vertices = vertices;
    }

    /**
     * Copy constructor to duplicate Graph
     * Usage: Graph copy = new Graph(g);
     * @param o original graph
     **/
    public Graph(Graph o) {
	this.V = o.V;
	this.E = o.E;
	this.vertices = o.getCurrentVertexCount();
	this.edges = new ArrayList<Edge>(E);
	for(Subset s : o.subsets) {
	    this.subsets.add(new Subset(s));
	}
	for(Edge e : o.edges) {
	    this.edges.add(new Edge(e));
	}
    }

    /**
     * Generae new graph from stdin
     * @return new graph
     **/
    public static Graph  generateGraphFromText() {
	Scanner scanner = new Scanner(System.in);
	/*try {
	  scanner = new Scanner(f);
	  } catch(FileNotFoundException e) {
	  System.err.println("File not found. Exiting.");
	  System.exit(-1);
	  }*/
	int numVertices = Integer.parseInt(scanner.nextLine());
	int numEdges = Integer.parseInt(scanner.nextLine());
	Graph g = new Graph(numVertices, numEdges);
	int lineNum = 1;
	while(scanner.hasNextLine()) {
	    String line = scanner.nextLine();
	    String[] toks = line.split(" ");
	    int s = Integer.parseInt(toks[0]); //start vertex
	    int t = Integer.parseInt(toks[1]); //end vertex
	    double weight = Double.parseDouble(toks[2]);
	    g.addEdge(s, t, weight);
	}
	return g;
    }

    /**
     * Adds new edge with values of e
     * @param e edge representng edge to be added
     **/
    public void addEdge(Edge e) {
	addEdge(e.s(), e.t(), e.weight());
    }

    public void addEdge(int s, int t, double weight) {
	this.edges.add(new Edge(s, t, weight));
    }

    public static void main(String[] args)
    {
	//read from stdin
	Graph graph = generateGraphFromText();
    }

    /**
     * Generate a Markdown file readable by Typora
     * of graph.  Doesn't consider contractions
     **/
    public String toMarkdown(boolean includeWeights) {
	String str = "```mermaid\ngraph LR\n"; 
	for(Edge e : edges) {
	    if(includeWeights) {
		str += "    ";
		str +=  e.s() + "((" + e.s() + "))";
		str += " -- ";
		str += e.weight();
		str += " --> ";
		str += e.t() + "((" + e.t() + "))";
		str += "\n";
	    } else {
		str += "    ";
		str += e.s() + "((" + e.s() + "))";
		str +=" --> ";
		str += e.t() + "((" + e.t() + "))";
		str += "\n";
	    }
	}
	return str;
    }

    /**
     * Generate a GraphViz dot file readable by Typora
     * of graph.  Doesn't consider contractions
     **/
    public String toGraphViz(boolean includeWeights) {
	String str = "digraph G {"; 
	for(Edge e : edges) {
	    if(includeWeights) {
		str += "    ";
		str +=  e.s();
		str += " -> ";
		str += e.t();
		str += " [label=\"" + e.weight() + "\"];";
		str += "\n";
	    } else {
		str += "    ";
		str += e.s();
		str +=" -> ";
		str += e.t() + ";";
		str += "\n";
	    }
	}
	str += "}";
	return str;
    }
}

class Subset {
    int parent, rank;

    public Subset(Subset o) {
	this.parent = o.parent;
	this.rank = o.rank;
    }

    public Subset() {};
    @Override
    public boolean equals(Object o) {
	Subset s = (Subset)o;
	if(s.parent == this.parent
	   && s.rank == this.rank) {
	    return true;
	}
	return false;
    }

    public static void union(List<Subset> subsets, int x, int y) {
	int xroot = find(subsets, x);
	int yroot = find(subsets, y);

	if (subsets.get(xroot).rank < subsets.get(yroot).rank) {
	    subsets.get(xroot).parent = yroot;
	}
	else if (subsets.get(xroot).rank > subsets.get(yroot).rank) {
	    subsets.get(yroot).parent = xroot;
	}
	else {
	    subsets.get(yroot).parent = xroot;
	    subsets.get(xroot).rank++;
	}
    } 
    
    public static int find(List<Subset> subsets, int i) {
	if (subsets.get(i).parent != i) {
	    subsets.get(i).parent = find(subsets, subsets.get(i).parent);
	}
	return subsets.get(i).parent;
    }
}
