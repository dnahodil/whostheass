# Who's the Ass?

A JVM implementation of [Who's the Ass](http://en.wikipedia.org/wiki/Who's_the_Ass%3F) with the ability to inject each player's strategy.

Written in [Groovy](http://groovy.codehaus.org/) but implementations of `PlayStrategy` should be able to be written in any JVM language.

The interfaces people who want to write (or train) their own strategies to use with this harness are [`PlayStrategy`](https://github.com/dnahodil/whostheass/blob/master/src/com/fgi/whostheass/strategy/PlayStrategy.groovy) and [`Card`](https://github.com/dnahodil/whostheass/blob/master/src/com/fgi/whostheass/cards/Card.groovy).
