import java.util.Random;
public class GraphGenerator {

    public static void main(String[] args) {
	if(args.length != 2) {
	    System.err.println("Usage: java GraphGenerator [vertices] [edges]");
	    return;
	}
	
	int vertices = Integer.parseInt(args[0]);
	int edges =  Integer.parseInt(args[1]);
	randomGraph(vertices, edges);
	
    }

    public static void randomGraph(int vertices, int edges) {
	Random rand = new Random();
	System.out.println(edges);
	System.out.println(vertices);
	for(int i = 0; i < edges; i++) {
	    final int s = rand.nextInt(vertices);
	    final int t = rand.nextInt(vertices);
	    final double weight = rand.nextDouble();
	    System.out.println(s + " " + t + " " + weight);
	}
	
    }
}
