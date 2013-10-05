# Who's the Ass?

[About](#about) »  
[Releases](#releases) »  
[Using this project yourself](#using-this-project-yourself) »  
[Implementing your own strategy](#implementing-your-own-strategy) »

## About

A JVM implementation of [Who's the Ass](http://en.wikipedia.org/wiki/Who's_the_Ass%3F) with the ability to inject each player's strategy.

Written in [Groovy](http://groovy.codehaus.org/) but implementations of `PlayStrategy` can be written in any JVM language.

Project written in [IntelliJ IDEA Community Edition](http://www.jetbrains.com/idea/free_java_ide.html).

Doesn't have unit tests (yet?) because I am lazy. :disappointed:

## Releases

### Complete

#### v1.0
* Rules and gameplay implemented
* Can play specified unmber of games with configurable play strategies
* Can implement your own play strategies

### In progress

#### v1.1
* *End-of-round summary passed to `PlayStrategy` ([#2](https://github.com/dnahodil/whostheass/issues/2))*
* *`BaseStrategy` class with common code and useful metrics made available. This will make implementing your own `PlayStrategy` much easier. ([#13](https://github.com/dnahodil/whostheass/issues/13))*
* *Project built as JAR (so you can write your own `PlayStrategy` without checking out the whole project) ([#11](https://github.com/dnahodil/whostheass/issues/11))*

## Using this project yourself

To use this project you need to:
* Install [Groovy](http://groovy.codehaus.org/)
* Install [IntelliJ IDEA Community Edition](http://www.jetbrains.com/idea/free_java_ide.html)
* Check out this project
* Open IntelliJ and the 'Open Project' and select the project from where you checked it out
(You will need to point the IntelliJ project to where your Groovy SDK is)

## Implementing your own strategy

To implement and use your own strategy you need to write a class that implements [`PlayStrategy`](https://github.com/dnahodil/whostheass/blob/master/src/com/fgi/whostheass/strategy/PlayStrategy.java).

You will need to know about the [`Card`](https://github.com/dnahodil/whostheass/blob/master/src/com/fgi/whostheass/cards/Card.groovy) enum and the [`OpponentView`](https://github.com/dnahodil/whostheass/blob/master/src/com/fgi/whostheass/player/OpponentView.java) interface.

The methods to implement:

### playNormalRound()

The method called to choose which card(s) to play on a normal round

<pre>
public List&lt;Card&gt; playNormalRound(
  List&lt;Card&gt; cardsInHand, // The cards you have in your hand
  List&lt;OpponentView&gt; playersWhoHavePlayed, // The OpponentViews of the people who have played in this round already
  List&lt;List&lt;Card&gt;&gt; movesPlayed, // A list of cards played by the people who have played already this round
  List&lt;OpponentView&gt; playersStillToPlay // The OpponentViews of the people who are still to play this round
)
</pre>

The return value must be a `java.util.List` of the `Card`(s) you wish to play. To play a single card there should be one element in the `List`. To pass you should return an empty `List` (ie. not `null`). To play multiple cards you should include many instances of the `Card` in the list. For example to play 3 x Sixes your `List` should contain the `Card.Six` value 3 times. You can also include any number of `Card.Joker`s in your `List` to use those Jokers to supplement your other cards.

### playAssRound()

The method called to choose which card(s) to play on an ass round

<pre>
public List&lt;Card&gt; playAssRound(
  List&lt;Card&gt; cardsInHand, // The cards you have in your hand
  List&lt;OpponentView&gt; playersWhoHavePlayed, // The OpponentViews of the people who have played in this round already
  List&lt;List&lt;Card&gt;&gt; movesPlayed, // A list of cards played by the people who have played already this round
  List&lt;OpponentView&gt; playersStillToPlay // The OpponentViews of the people who are still to play this round
)
</pre>

The return value must be a `java.util.List` of the `Card` you wish to play. There should normally only be 1 element in the returned `List`. The exception is that if you want to play a Joker you need to return 2 cards: The `Card.Joker` and the `Card` value that you want that Joker to be used as. To use a Joker as a Joker (ie. 14 points) you need to return 2 Jokers in the list.

### startRound()

This method is called when it is your turn to start a round.

<pre>
public List&lt;Card&gt; startRound(
  List&lt;Card&gt; cardsInHand, // The cards you have in your hand
  List&lt;OpponentView&gt; playersStillToPlay, // The OpponentViews of the players who will play after you
  boolean canLeadAss // Whether you can lead the Ass card or not
)
</pre>

The return value must be a `java.util.List` of the `Card`(s) you wish to play. The `List` cannot be empty.

</pre>
