# Symbolic-Polynomial-Arithmetic

### 0. Introduction.
_Symbolic polynomial arithmetic_ is a classic programming problem that can be solved using linked lists. In this assignment, you will write a Java class that represents polynomials using singly linked circular lists. The class’s methods will add and subtract these polynomials.

### 1. Theory

For the purposes of this assignment, a _polynomial_ is a finite sum of zero or more terms, like this.

p = a_nx^_n_ + a_n−1x^_n−1_ + a_n−2x^_n−2_ ... + a_2x^_2_ + a_1x^_1_ + a_0x^_0_

Each term has a _coefficient_, shown as a subscripted _a_. It also has a
nonzero _variable_, shown as _x_, with an _exponent_. All coefficients are nonzero integers, and all exponents are nonnegative integers. No two exponents are equal, and the polynomial’s terms appear in decreasing order of their exponents. A polynomial with no terms is assumed to be 0. Now suppose we have two polynomials p and q that look like this.
p = 3x^_3_ +−2x^_2_ +5x^_0_
q = 7x^_5_ +1x^_3_ +2x^_2_ +1x^_1_
Then their sum r can be computed in the following way, by simple algebra.
r = p+q = 7x^_5_ +(3+1)x^_3_ +(−2+2)x^_2_ +1x^_1_ +5x^_0_ = 7x^_5_ +4x^_3_ +1x^_1_ +
5x^_0_

The algebra suggests an algorithm for adding _p_ and _q_, shown in pseudocode.
1. Let the polynomial _r_ be a copy of _q_.
2. For each term in _p_,do steps 3 – 7.
3. Search for a term in _r_ with the same exponent as the one from _p_.
4. If there is such a term in _r_, then add the coefficient of the term from _p_ to the coefficient of the term from _r_.
5. If the term from _r_ from step 4 has a coefficient of 0, then delete that term from _r_.
6. If there is no term in _r_ whose exponent is the same as one in _p_,then make a copy of the term from _p_, and add the copy to _r_. Keep the terms of _r_ in increasing order of their exponents.
7. Continue in this way until all the terms of _p_ are visited.Then _r_ is the sum of _p_ and _q_.

We can also find the difference of two polynomials _p_ − _q_ by computing _p_ + (− _q_) according to the same algorithm. The _negation_ − _q_ of the polynomial _q_ is obtained by simply reversing the signs of its coefficients. For example:

− _q_ = −(7x^_5_ +1x^_3_ +2x^_2_ +1x^_1_) = −7x^_5_ +−1x^_3_ +−2x^_2_ +−1x^_1_

As before, the algebra suggests an algorithm for negating q, shown below in pseudocode.
1. Let _r_ be a new polynomial with no terms.
2. For each term in _q_, do steps 3 and 4.
3. Make a copy of the term from q, but change the sign of its coefficient. Add the copied term to _r_. Keep the terms of _r_ in decreasing order of their exponents.
4. Continue in this way until all the terms of _q_ are visited.Then _r_ is the negation of _q_.

Polynomials like these can be represented as circular, singly linked lists with head nodes. These are different from the circular _doubly_ linked lists from the lectures, and from a laboratory assignment! They have only one pointer slot per node. (This is one of the few times where circular singly linked lists are good for anything.)
