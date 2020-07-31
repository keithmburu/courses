class POSTag {
    public static final String[] nom = {"unusualness","applicability", "carelessness", "difficulty", "failure", "personality", "personalization", "politicization", "militarism", "fascism", "reader", "believer", "computer", "antimatter", "day", "wednesday", "appliication", "computability", "post-structuralism", "belief", "unbelief", "non-believer", "monday"};
    public static final String[] adv = {"unusually","quickly", "personally", "carelessly", "hardly", "here", "there", "everywhere", "somewhere", "daily", "undemocratically"};
    public static final String[] adj = {"unusual","pre-computer","undemocratic","applicable", "personal", "careless", "hard", "militaristic", "median", "fascistic", "democratic", "computable", "fearsome","anti-democratic", "Christian", "anti-person","direst","premature", "Islamic","post-structural", "post-partum","thirty-second", "previous", "post-structuralistic", "worrisome", "first,","second", "third", "fourth", "fifth", "sixth", "seventh", "quickest", "Chomskyan"};
    public static final String[] verb = {"apply","quicken", "care", "harden", "fail", "personalize", "compute", "politicize", "militarize", "believe"};


    
    public static String[] getTagGuesses(String[] words) {
	String[] guesses = new String[words.length];
	for(int i = 0; i < words.length; i++) {
	    guesses[i] = guessTag(words[i]);
	}
	return guesses;
    }

    public static String guessTag(String word) {
	//TODO: Write the code to guess the tag of an arbitrary word.
	String tagGuess = "";
	if(word.endsWith("ly")) {
	    tagGuess = "ADV";
	}

	return tagGuess;
    }

    public static float accuracy(String trueTag, String[] guesses) {
	float correct = 0F;
	for(String g : guesses) {
	    if(g.equals(trueTag)) {
		correct++;	
	    }
	}
	return correct / (float)guesses.length;
    }
    
    public static void main(String[] args) {
	System.out.println("Noun Accuracy: " + accuracy("N",getTagGuesses(nom)));
	System.out.println("Adjective Accuracy: "+ accuracy("ADJ",getTagGuesses(adj)));
	System.out.println("Adverb Accuracy: "+ accuracy("ADV",getTagGuesses(adv)));
	System.out.println("Verb Accuracy: "+ accuracy("V",getTagGuesses(adv)));
    }

}
