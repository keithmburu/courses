CSCI H360 Machine Learning, Spring 2020

Prof. Alvin Grissom II

Due 2/2

# Homework 1 (50 points)

This assignment is to be done in LaTeX.  You may use Markdown + LaTeX, but you should turn in a PDF.  If you use pure LaTeX, use an assignment template, not an article template.  Please ensure that your submissions are readable.  If you do not know how to accomplish something in LaTeX, ask on Piazza.   Numbered exercises are from [Bayesian Reasoning and Machine Learning](http://web4.cs.ucl.ac.uk/staff/D.Barber/pmwiki/pmwiki.php?n=Brml.HomePage) by David Barber.

- **Problem 1 (10 pts):** Exercise 10.1 (Hint: See Example 10.2)
- **Problem 2 (10 pts):** 
  **(a)** Building off of the previous problem, suppose that instead of binary classes (> 60 or < 60), your data consists of a discrete set of ages, *A*, in [0,100] and probabilities *p* ∈ [0,1] for each age, respectively. Write an compact equation for the probability of people over age 60.
  - **Problem 2 (b)** Do the same for the continuous case.
- **Problem 3: (20 points)** Exercise 10.5 (all parts)
- **Problem 4:** The Dirichlet distribution (Chapter 8) is a distribution over multinomial distributions.  In a typical probability distribution, you  are sampling some value.  In a Dirichlet distribution, you are sampling a distribution, Q ~ Dir(**α**).  Here, **α** is a vector of parameters that determines how likely the distributions are to be sampled, akin to skewness in the two-dimensional case.  An **α** of <1,1,1> in the $\mathbb{R}^3$ case is uniform: all distributions are equally likely.   The Dirichlet  distribution models the randomness within a set of distributions, which  can be visualized as a simplex. (A simplex is an *n-1*-dimensional shape in $\mathbb{R}^n$).  That is, the Dirichlet is a distribution over a *family* of distributions with some random perturbation, parameterized by **α** to enforce either sparsity or density of the probability mass across  the simplex.  When enforcing sparsity, samples are more spread out in  the simplex; when enforcing density, they tend to be sampled from the  same area. A symmetric Dirichlet is a Dirichlet where all values of **α** are the same.

**(4a, 5 points)** Why might we want to sample a distribution itself?   Briefly explain your intuition.  If helpful for your explanation, use an example. 

**(4b, 5 points)** Consider the symmetric Dirichlet distributions Dir(<1,1,1>),   Dir(<2,2,2>), and Dir(<0.1,0.1,0.1>). Rank them by entropy.  Explain.  You may find the images on page 3 of the following tutorial  to be helpful: http://mayagupta.org/publications/FrigyikKapilaGuptaIntroToDirichlet.pdf



