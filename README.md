# Who's the Ass?

A JVM implementation of [Who's the Ass](http://en.wikipedia.org/wiki/Who's_the_Ass%3F) with the ability to inject each player's strategy.

Written in [Groovy](http://groovy.codehaus.org/) but implementations of `PlayStrategy` should be able to be written in any JVM language.

To use this harness you will need to write a class that implements [`PlayStrategy`](https://github.com/dnahodil/whostheass/blob/master/src/com/fgi/whostheass/strategy/PlayStrategy.java). To write your implementations you will need to know about the interfaces [`Card`](https://github.com/dnahodil/whostheass/blob/master/src/com/fgi/whostheass/cards/Card.java) and [`OpponentView`](https://github.com/dnahodil/whostheass/blob/master/src/com/fgi/whostheass/player/OpponentView.java).

The interfaces people who want to write (or train) their own strategies to use with this harness are  and .

Project written in [IntelliJ IDEA Community Edition](http://www.jetbrains.com/idea/free_java_ide.html).
