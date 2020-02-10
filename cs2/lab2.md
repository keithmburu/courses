CS174: Object-oriented Programming, Spring 2020

Ursinus College



# Lab 2: Recursive Square Root

The purpose of this lab is to give you practice thinking about and implementing recursive programs.

Write a recursive Java program that implements the following function:
$$
\pi = \lim_{n\to\infty} 2^{n+1}\underbrace{\sqrt{2-\sqrt{2+\sqrt{2+\sqrt{2+\ldots+\sqrt{2}}}}}}_{n}
$$




This infinite radical is derived from [Viète's formula](https://en.wikipedia.org/wiki/Vi%C3%A8te%27s_formula), which has several variations. We can approximate the limit by repeating the radical *n* number of times, where *n* is sufficiently large.  In practice, *n* need not be very large to converge to $\pi$.  Start small and work your way up.



Show your lab to the professor or the TA when it is completed. 

## References

[S.M. Abramov and B.M. Quine. A formula for pi involving nestedradicals](https://arxiv.org/pdf/1610.07713.pdf)

[Vieta's Formula for Pi: ProofWiki](https://arxiv.org/pdf/1610.07713.pdf)







