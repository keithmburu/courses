import java.util.ArrayList;
public class IntVectorTest {

    public static boolean testAdd(ArrayList<Integer> a, IntVector iv, int val) {
	a.add(val);
	iv.add(val);

	if(iv.toString().equals(a.toString())) {
	    System.out.println("add(" + val + ") passed.");
	    return true;
	} else {
	    System.out.println("add(" + val + ") failed.");
	}
	return false;
    }

    public static boolean testRemove(ArrayList<Integer> a, IntVector iv, int val) {
	a.remove(val);
	try {
	    iv.remove(val);
	} catch(ArrayIndexOutOfBoundsException e) {
	    System.out.println("remove(" + val + ") failed.");
	    return false;

	}

	if(iv.toString().equals(a.toString())) {
	    System.out.println("remove(" + val + ") passed.");
	    return true;
	} else {
	    System.out.println("remove(" + val + ") failed.");
	}
	return false;
    }

    public static boolean testSet(ArrayList<Integer> a, IntVector iv, int index, int val) {
	a.set(index, val);
	try {
	    iv.set(index, val);
	} catch(ArrayIndexOutOfBoundsException e) {
	    System.out.println("set(" + val + ") failed.");
	    return false;

	}

	if(iv.toString().equals(a.toString())) {
	    System.out.println("set(" + val + ") passed.");
	    return true;
	} else {
	    System.out.println("set(" + val + ") failed.");
	}
	return false;
    }



    public static void main(String[] args) {
	ArrayList<Integer> a = new ArrayList<>();
	IntVector iv = new IntVector(3);
	boolean phaseOnePass = true;
	boolean phaseTwoPass = true;
	boolean phaseThreePass = true;
	for(int i = 0; i < 11; i++) {
	    if(testAdd(a,iv,i) == false) {
		phaseOnePass = false;
	    }
	}
	
	if(testSet(a,iv,0,5) == false) {
	    phaseTwoPass = false;
	}
	if(testSet(a,iv,1,1001) == false) {
	    phaseTwoPass = false;
	}

	if(testSet(a,iv,2,-1001) == false) {
	    phaseTwoPass = false;
	}


	for(int i = 0; i < 6; i += 2) {
	    if(testRemove(a, iv, i) == false) {
		phaseThreePass = false;
	    }
	}


	if(testAdd(a,iv,27) == false) {
	    phaseThreePass = false;
	}
	if(testRemove(a,iv,2) == false) {
	    phaseThreePass = false;
	}

	int totalPoints = 0;
	if(phaseOnePass) {
	    totalPoints += 33.3333333;
	}

	if(phaseTwoPass) {
	    totalPoints += 33.3333333;
	}

	if(phaseThreePass) {
	    totalPoints += 33.333333;
	}
	System.out.println("Expected: " + a);
	System.out.println("Actual: " + iv);
	System.out.println("Estimated grade: " + totalPoints);
    }

}
