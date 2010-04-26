# Sample Code for Programming Clojure

http://www.pragprog.com/titles/shcloj/programming-clojure
Copyright 2008-2010 Stuart Halloway. All rights reserved. 

# Important Notice

If you are reading the book and just beginning to learn Clojure, make
sure you grab the master branch of this project instead, from

http://github.com/stuarthalloway/programming-clojure/

The master branch has all the files exactly where the book says they
will be.

# What's New in 1.2?

This branch of the Programming Clojure sample code has been updated to
run with Clojure 1.2. The code changes are small: Clojure has been
remarkably stable over the 1.0, 1.1, and 1.2 releases. You can see the
changes I made for 1.1 [here](http://bit.ly/9b9wln) and for 1.2
[here](http://bit.ly/9DyIX7). Even most of these changes were for
Clojure-contrib, not Clojure itself.

(While the code changes are small, almost every file has moved. Since I
wrote the book, conventions for directory structure and tools for
dependency management have emerged to support Clojure. To take
advantage of these changes I moved things around.)

# Getting Started

* Make sure you have Java installed.
* Make sure you have [leiningen](http://github.com/technomancy/leiningen) installed.
* Clone the labrepl project and change directory into it.
* Run `lein deps` to install all the dependent libraries.
* Run `script/repl` to launch a repl.

# Want more Clojure Practice?

[Labrepl](http://github.com/relevance/labrepl) is a free, open-source environment
for exploring the Clojure language. It includes:

* a web application that presents a set of lab exercises with
  step-by-step instructions
* an interactive repl for working with the lab exercises
* solutions with passing tests 
* up-to-date versions of Clojure, contrib, incanter, compojure and other libraries to explore

# Want Training?

Rich Hickey, the creator of Clojure, and Stuart Halloway, the author
of Programming Clojure, provide Clojure training through the 
[Pragmatic Studio](http://pragmaticstudio.com/clojure).
