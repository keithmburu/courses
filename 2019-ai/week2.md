Week 2 Homework (40pts)
--
This assignment is to be done in LaTeX or Markdown. Numbered assignments are from [Bayesian Reasoning and Machine Learning by Barber](http://web4.cs.ucl.ac.uk/staff/D.Barber/pmwiki/pmwiki.php?n=Brml.HomePage). Earn 40 points will result in full credit, but it is possible to earn up to 50.

1. (20 pts) 
 * (a) Exercise 10.1 
 * (b) Suppose that instead of binary classes (> 60 or < 60), your data consists of a discrete set of ages, *A*, in [0,100] and probabilities *p* ∈ [0,1] for each age, respectively. Write an equation (with variables, not numbers) for the probability of people over age 60.  
2. (20 pts) 
 * (a) Exercise 10.5
 * (b) Write a program to calculate the correct answer to problem (2) from the data (matrix) given.  Call it 2b.py
3. (10 pts) The Dirichlet distribution (Chapter 8) is a distribution over multinomial distributions.  In a typical probability distribution, you are sampling some value.  In a Dirichlet distribution, you are sampling a distribution, Q ~ Dir(**α**).  Here, **α** is a vector of parameters that determines how likely the distributions are to be sampled, akin to skewness in the two-dimensional case.  An **α** of <1,1,1> in the **R**<sup>3</sup> case is uniform: all distributions are equally likely.   The Dirichlet distribution models the randomness within a set of distributions, which can be visualized as a simplex. (A simplex is an *n-1*-dimensional shape in **R**<sup>n</sup>).  That is to say, the Dirichlet is a distribution over a *family* of distributions with some random perturbation, parameterized by **α** to enforce either sparsity or density of the probability mass across the simplex.  When enforcing sparsity, samples are more spread out in the simplex; when enforcing density, they tend to be sampled from the same area. A symmetric Dirichlet is a Dirichlet where all values of **α** are the same.

(a) Why might we want to sample a distribution itself?  Briefly explain your intuition.  If helpful for your explanation, use an example.
(b) Consider the symmetric Dirichlet distributions Dir(<1,1,1>),  Dir(<2,2,2>), and Dir(<0.1,0.1,0.1>). Rank them by entropy.  Explain.  You may find the images on page 3 of the following tutorial to be helpful: http://mayagupta.org/publications/FrigyikKapilaGuptaIntroToDirichlet.pdf
