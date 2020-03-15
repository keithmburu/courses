import java.util.LinkedList;
import java.lang.reflect.Modifier;
public class IntLListTest {

    public static boolean testAdd(LinkedList<Integer> a, IntLList iv, int val) {
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

    public static boolean testRemove(LinkedList<Integer> a, IntLList iv, int val) {
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

    public static boolean testSet(LinkedList<Integer> a, IntLList iv, int index, int val) {
	a.set(index, val);
	try {
	    iv.set(index, val);
	} catch(ArrayIndexOutOfBoundsException e) {
	    System.out.println("set(" + index + "," + val + ") failed.");
	    return false;

	}

	if(iv.toString().equals(a.toString())) {
	    System.out.println("set(" + index + "," + val + ") passed.");
	    return true;
	} else {
	    System.out.println("set(" + index + ","+  val + ") failed.");
	}
	return false;
    }

    public static boolean testClassMembership() {
	IntLList ill = new IntLList();
	try {
	    System.err.println("Testing isEmpty()");
	    ill.isEmpty();
	}
	catch(Exception e) {
	    System.err.println("IntLList isEmpty() test failed with the following error:");
	    System.err.println(e.getMessage());
	    return false;
	}
	
	try {
	    System.err.println("Testing removeLast() on empty  list");
	    ill.removeLast();
	}
	catch(Exception e) {
	    System.err.println("IntLList removeLast() test failed with the following error:");
	    System.err.println(e.getMessage());
	    return false;
	}

	
	    System.err.println("Checking IntList abstractness.");
	    if(Modifier.isAbstract(IntList.class.getModifiers())) {
		System.err.println("Passed.");
	    } else {
		System.err.println("Failed.  IntList should be abstract.");
		return false;
	    }



	try {
	    System.err.println("Testing class  hierarchy of IntArrayList");
	    IntList il = new IntArrayList(3);
	}
	catch(Exception e) {
	    System.err.println("IntArrayList does not extend IntList: failed");
	    return false;
	}


	
	try {
	    System.err.println("Testing class  hierarchy of IntLList");
	    IntList il = new IntLList();
	    il.add(4);
	}
	catch(Exception e) {
	    System.err.println("IntLList does not extend IntList: failed");
	    return false;
	}

	try {
	    System.err.println("Testing inheritance of IntLList");
	    IntList il = new IntLList();
	    try {
		System.err.println("empry->add(3)");
		il.add(3);
	    } catch(AbstractMethodError e) {
		System.err.println("add(int) appears to be abstract in IntList: passed");

	    } catch(Exception e) {
		System.err.println("Failed with error " + e.getMessage());
		return false;
	    }

	    int oldSize = -1;
	    try {
		IntLList illl = (IntLList)il;
		oldSize = illl.size();

	    } catch(Exception e) {
		System.err.println("Failed with error " + e.getMessage());
		return false;
	    }

	    
	    System.err.println("Testing upward cast from IntLList to IntList");
	    System.out.println("empty->add(3)");
	    System.out.println("IntLList->IntList");
	    il = (IntList)il;
	    System.err.println("Testing to see if size() of superclas and subclass are the same");
	    if(il.size() == oldSize) {
		System.err.println("Passed.");
	    } else {
		System.err.println("Failed.  Did you define the instance variable size or the function size in both the superclass and the subclass?  Don't do this.");
		return false;
	    }
	    
	}
	catch(Exception e) {
	    System.err.println("Failed to case IntLList to IntList with error:");
	    System.err.println(e.getMessage());
	    return false;
	}
	

	
	return true;
    }



    public static void main(String[] args) {

	LinkedList<Integer> a = new LinkedList<Integer>();
	IntLList iv = new IntLList();
	boolean phaseOnePass = true;
	boolean phaseTwoPass = true;
	boolean phaseThreePass = true;
	boolean phaseFourPass = true;
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
	    totalPoints += 25;
	}

	if(phaseTwoPass) {
	    totalPoints += 25;
	}

	if(phaseThreePass) {
	    totalPoints += 25;
	}

	System.err.println();
	System.err.println("Phase 4");
	if(testClassMembership() ==  true) {
	    totalPoints += 25;
	};
	
	System.out.println("Expected: " + a);
	System.out.println("Actual: " + iv);
	System.out.println("Estimated grade: " + totalPoints);
    }

}
