# Lab 9: Part-of-speech Tagging Lab

For this lab, you will use the Stanford POS tagger to familiarize yourself with part-of-speech tags and tagets.  (You may use another tagger if you like, such as one of the statistical taggers in NLTK, but this lab assume that you are using the [Stanford Tagger](https://nlp.stanford.edu/software/tagger.shtml).

You can download the tagger directly with

```bash
wget https://nlp.stanford.edu/software/stanford-tagger-4.2.0.zip
```

Unzip with:

```bash
unzip stanford-tagger-4.2.0.zip
```

Change to the appropriate directory to test the tagger.  There are various models included for English, Chinese, German, and French.  See the Stanford Tagger website for documentation or the `readme.txt` file for documentation of the various models.  You can use the following command to use a basic English model that uses the left three words to tag.

```bash
java -mx300m -cp 'stanford-postagger.jar:' edu.stanford.nlp.tagger.maxent.MaxentTagger -model models/english-left3words-distsim.tagger
```

You can download models for many other languages here: https://stanfordnlp.github.io/stanfordnlp/models.html

You may want to make your own text file with test cases.  Alternatively, to type sentences directly (i.e., read from `stdin`), simply leave off the `-textfile` argument.  You can change the model to a different model in the `models` subdirectory.

From the web site:

> **Part-of-speech name abbreviations:** The English taggers use the Penn Treebank tag set.  Here are some links to documentation of the Penn Treebank English POS tag set: [1993 *Computational Linguistics* article in PDF](http://acl.ldc.upenn.edu/J/J93/J93-2004.pdf), [Chameleon Metadata list (which includes recent additions to the set)](https://chameleonmetadata.com/Education/NLP-3/ref_nlp_nlp4j_pos_tags_list.php). The French, German, and Spanish models all use [the UD (v2) tagset](https://universaldependencies.org/u/pos/). See the included README-Models.txt in the models directory for more information about the tagset for each language.

## Assignment

For this lab, you will attempt to find test sentences that the model tags incorrectly ([adversarial examples](https://arxiv.org/pdf/1312.6199.pdf)).  Due to their relative inscrutability, adversarial examples are often discussed in the context of neural networks, but other models can fall prey to them as well.

Try to devise and construct examples of sentences that the model fails to tag correctly. Describe what you tried, why, and any successes you had.  Analyze your results across at least two different models. (They can be in the same language or in different language, but you should be systematic.)  Include in your short report the relevant test cases that you used

For the assignment, you should submit a report.

You may find search engines and/or your own mind useful.