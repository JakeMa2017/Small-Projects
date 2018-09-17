# Name Table

### 0. Introduction.
Compilers use a _name table_ to record information about things that have names, like classes, methods, and variables. In this assignment, you will write a Java class that implements a compiler’s name table. It uses stacks and binary search trees. It also shows how to build complex data structures by combining the simple ones that were discussed in the lectures.

### 1. Theory.

The table below shows a Java method mean that computes the integer average of the elements in an integer array a. For example, if mean is called on an array with elements 1, 2, and 3, then it returns 2, because (1 + 2 + 3) / 3 = 2. The table also shows what variables can be used in each line.



In Java, a local variable can be used only after it is declared, and then only between a pair of braces '{'' and '}'. The part of a program where a variable can be used is called a _scope_. For example, the method 'main' has four local variables: 'a', 't', 'k', and 'e'. The variable 'a' can be used in the scope between lines 02–15, the variable 't' can be used in the scope between lines 04–15, the variable 'k' can be used in the scope between lines 07–13, and the variable 'e' can be used in the scope between lines 10–11.

To record information about variables and their scopes, a compiler uses a name table. A name table can be implemented by using a stack whose elements are binary search trees (BST’s). Suppose that a compiler reads a method like 'mean' and translates it to machine code. For this assignment, we care about what actions the compiler performs on the name table when it reads open braces, declarations, variables, and closing braces.

If the compiler sees an open brace '{;, then it enters a new scope. To do that, it pushes a new empty BST on top of the stack. The BST’s keys are 'String'’ s: the names of the variables that will be declared in the new scope. The BST’s values are unspecified objects that hold information about the variables. The BST on top of the stack records information about variables that are declared in the current scope.

If the compiler sees a variable declaration, like 'int t', then it searches the BST on top of the stack to find out if the variable was already declared in the current scope. If it finds the variable, then it issues an error message, because you can’t declare a variable twice. Otherwise, the compiler adds the new variable to the top BST on the stack.

If the compiler sees a variable, like 't', then it must obtain information about that variable. To do that, it searches each BST on the stack, from top to bottom, until it finds the variable it’s looking for. If the compiler can’t find the variable in any of the BST’s, then the variable has never been declared, so it issues an error message.

If the compiler sees a closing brace '}', then it exits the current scope. To do that, it pops a BST off the top of the stack. This discards information about any variables that were declared in the current scope.
