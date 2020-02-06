import java.util.Random;

public class GenerateWords {
    public static void main(String[] args) {
	if(args.length != 3) {
	    System.err.println("Usage: java GenerateWords [minimum word Size] [maximum word size] [num words]");
	    System.err.println("For example: 'java GenerateWords 1 5 10000' will generate 10,000 random words (numbers) ranging from length 1 to length 5.");
	} else {
	    int minSize = 0;
	    int maxSize = 0;
	    int numWords = 0;
	    try {
		minSize = Integer.parseInt(args[0]);
		maxSize = Integer.parseInt(args[1]);
		numWords = Integer.parseInt(args[2]);
	    } catch(NumberFormatException e) {
		System.err.println("Malformed number. Exiting");
		System.exit(-1);
	    }
	    
	    if(maxSize < minSize || numWords < 1) {
		System.err.println("Invalid values. Ensure that maxSize > minSize and numWords > 0.");
	    } else {
		System.err.println("Generating " + numWords + " words from size " + minSize + " to " + maxSize + ".");
		generateRandomWords(minSize, maxSize, numWords);
		System.err.println("Done.");
	    }
	}

	
    }

    /**
       Generate random "words" consisting of numbers from 0-9
       @param minSize minimum word size
       @param maxSize maximum word size
       @param numWords the number of words to generate
     */
    
    public static void generateRandomWords(int minSize, int maxSize, int numWords) {
	Random rand = new Random();
	for(int i = 0; i < numWords; i++) {
	    String word = "";
	    int wordSize = (int)(rand.nextInt(maxSize) + minSize);
	    for(int j = 0; j < wordSize; j++) {
		int randomInt  = (int)(rand.nextInt(10)); //generate number in [0,9]
		word += randomInt; //add num to new word
	    }
	    if(i < numWords - 1) {
		System.out.print(word + " ");
	    } else {
		System.out.println(word);
	    }
	}
    }
}
