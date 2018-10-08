Deep Computer Vision and Language

Due October 18, 11:59pm.

# Midterm Project

### Description

For your midterm, you will work on an image dataset, unless you've cleared some other project with me.  Unlike previous homework assignments, I will not provide any guidance on writing the code, though I may answer general questions.  It is up to you to learn and apply whatever is necessary to complete the project.  You may use any of the datasets here, aside from MNIST:

 http://rodrigob.github.io/are_we_there_yet/build/classification_datasets_results.html

Note that the `examples` directory in the Keras Github repository already has functional code for CIFAR-10.  If you like, you can  use PyTorch, TensorFlow, Chainer, or any other deep learning library.    You may also use any code you find online, as long as you add footnotes in your paper that show where you found it.   What you should *not* do is turn in a paper that simply runs someone else's code without bothering to try anything new in your report, unless you have some justifiable, systematic reason for doing so.

Your grade will be primarily based on the report that you write, but I will look at your code **on Github**.  This means that you should **include a link to your code on Github** in a footnote.

Your goal is to achieve the highest accuracy possible on unseen (test) data.  If you do not manage to beat the best methods, your goal is to write a thorough analysis of what you tried and your results.  If you try something simple that outperforms something complicated, this is noteworthy.

It is highly recommended that you cite previous work.  If you implement and/or improve upon some state-of-the-art method, this is even better.  **At a minimum**, you should run a basic convolutional neural network (CNN) baseline, which should get you  at least 80% accuracy on CIFAR, for example.  Ideally, your baseline will be a more sophisticated model.  You are definitely **not** limited to techniques we have discussed in class.

### Report

Your report should be written in LaTeX, using the NIPS template.  You should have, at least, sections that roughly correspond to the following: Introduction, Related Work,  Approach/Model Description, Results, Analysis, and Conclusion.  Do not use filler to take up space.   This is a waste of everyone's time and bad writing.  I will not consider space when grading, but content.  Use other scientific papers as examples.

With the possible exception of some areas of Related Work and the Introduction, write nearly everything in the present tense.  It will be awkward at first, but you'll get used to it.  "We implement as a baseline a convolutional neural network (CNN) ..."  

Use appropriate, grammatical English for a scientific paper.

While I will not help you code this midterm, I am willing to give feedback on near-final report drafts if they are sent to me at a reasonable time (i.e., not immediately before the deadline).

### Grading

Your grade will be based on the overall content of your paper.  You may work with one other person (recommended,  especially if you don't have an NVIDIA GPU and someone else does), but if you do so, you **must document what each of you did**.

A+ Range:  Same as A range paper with state-of-the-art results with a new method or a tweak to a previous method.

A Range: Exceptionally written paper that does not necessarily achieve state-of-the-art results, but which provides thorough and logical analysis of results, related work, etc.  Uses graphs, tables, and formulas for clarity.  Contextualizes work in the current literature. Work is replicable from the paper.

B range:  Reasonable paper with some notable grammatical, organizational, or explicatory problems.  Some methodological problems, such as single-run experiments or not controlling various factors when making comparisons.

C range: Paper has major methodological or expository issues, but has some passable experiments that show effort.

D range: Paper is badly written with half-done experiments, but there is some salvagable content.



### What to Turn In

1. Your paper as a PDF file on Canvas.
2. Your paper should link to any origianl code in your Github repository.

### Tips

1. Write the skeleton of your paper first, or while your first experiments are running, so that you won't be stuck trying to write everything at the last second.  
2. Use a GPU machine.
3. Work with someone else.
4.  Use graphs and/or diagrams to describe models and results.
5. Use formulas where appropriate to make explicit what your models are doing.  Use other research papers as a guide.
6. Write in the present tense and try to make you sentences compact and readable.  You want to be clear.
7. Use bold and italics where necessary for emphasis and clarity.
8. Use subsections where appropriate.
9. Define your terms.  If you use an abbreviation, use parentheses before using it again.  For example, "Generative adversarial networks (GANs) are used for..." 
10. Except where actually necessary, avoid modals like "would," "could," "can," etc.  Sometimes, they're useful, but often they're superfluous.
11. Run your experiments multiple times to make sure that your results aren't a fluke.
12. Document your hyper parameters, preprocessing, etc., so that someone reading your paper can replicate your work.
13. If you find that you're running out of time, don't keep running far-fetched experiments; focus instead on the writing.
14. This should be obvious, but if your dev set accuracy is still increasing, you're not finished training.
15.  Describe your dataset.  In general, don't assume that the person reading your paper knows what you do.
16. **Start early**.  There will be no extensions.





