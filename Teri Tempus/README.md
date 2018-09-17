# Teri-Tempus


### 0. Introduction.
**Teri Tempus** is a solitaire game played with a standard deck of cards. According to Google, its name means _Time Waster_ in Latin. In this project, you will write a Java program that plays the game of Teri Tempus. As the name suggests, the program does nothing useful—but it may still be interesting because it demonstrates array stacks and linked stacks, as discussed in the lectures.

### 1. Theory.
A standard deck has 52 cards. Each card has one of
four suits, called _spade_(♠), _club_(♣), _heart_(♥), and _diamond_(♦). Each card also has one of thirteen _ranks_, called _ace_, _two_, _three_, _four_, _five_, _six_, _seven_, _eight_, _nine_, _ten_, _jack_, _queen_, and _king_. The deck also has two _joker_ cards, but they aren’t used in Teri Tempus.
Teri Tempus is played in the following way. First, the deck is shuffled. Then one card is dealt, and put on the table. Play now continues according to the following rules.

1. If all the cards have been dealt, then the game is over.
2. Deal a new card from the deck. During the game,there will be a row of piles of cards on the table. Each pile consists of one or more cards. This row is called the _tableau_ (pronounced tab-LOW). Put the new card at the start of the tableau.
3. Suppose there are two or more piles of cards in the tableau. If the suit of the card on top of the first pile is the same as the suit of the card on top of the second pile, then put the first pile on top of the second.
4. Similarly,if the rank of the card on top of the first pile is greater than the rank of the card on top of the second pile, then put the first pile on top of the second.
5. Go back to step 1.

At the end of the game, if the tableau has exactly one pile of cards, then the game is won. If the tableau has more than one pile, then the game is lost.
_Stacks_ can be used to model the game of Teri Tempus. The deck of cards is a stack, because cards are removed from its top and added to the tableau. The tableau is also a stack of piles of cards, because new cards are added to the start of the tableau as they are dealt.

### 2. Example.
Here’s an example game of Teri Tempus, or at least part of one. It was played automatically by the program that you’re asked to write for this assignment. We shuffle the deck and deal the first card, the ten of hearts (10♥). We put it into the tableau, so it looks like this.

**10♥**

We deal the next card, the four of spades (**4♠**), and put it down at the start of the tableau, so it looks like this.

**4♠ 10♥**

The rank on top of the first pile (**4**) is not greater than the rank on top of the second pile (**10**). Also, the suit on top of the first pile (**♠**) is not equal to the suit on top of the second pile (**♥**). That means we can’t merge the first two piles. All we can do is deal another card, the five of hearts (**5♥**), and put it in the tableau, like this.

**5♥ 4♠ 10♥**

Now, however, the rank of the card on the first pile (**5**) is greater than the rank of the card on the second pile (**4**). That means we can merge the first two piles. 

**5♥ 10♥**

The card **4♣** can no longer be seen, because it’s under **5♥** in the first pile. Only the cards on top of the piles determine how to play the game. The first two piles now have the same suit (**♥**), so we can merge them also.

**5♥**

We’re left with one pile, but again only its top card (**5♥**) can be seen. We deal yet another card, the ten of diamonds (**10♦**), and put it into the tableau. 

**10♦ 5♥**

The rank **10** is greater than **5**, so we merge the two piles of cards with **10♦** on top.

**10♦**

The next card we deal is the four of clubs (**4♣**). We put it down at the start of the tableau.

**4♣ 10♦**

We can’t merge these two piles, so we’ll continue playing. Or else we won’t.
Teri Tempus gets boring very fast, so we’ll stop the example here.
