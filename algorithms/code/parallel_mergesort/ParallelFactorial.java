import java.util.concurrent.RecursiveAction;
import java.util.concurrent.*;
import java.util.ArrayList;
class ParallelFactorial extends RecursiveAction {
    final int i;
    public ParallelFactorial(int i) {
	this.i = i;
    }
    public void compute() {
	if(i > 1000) {
	    return;
	}
	ArrayList<String> l = new ArrayList<>(0);
	for(int i = 0; i < 10000; i++) {
	    l.add("h");
	}
	ParallelFactorial r = new ParallelFactorial(i + 1);
	ParallelFactorial s = new ParallelFactorial(i + 1);
	//r.compute();
	invokeAll(r,s);
	System.out.println("in task " + i);
    }

    public static void main(String[] args) {
	ParallelFactorial f = new ParallelFactorial(0);
	f.compute();
    }

}
