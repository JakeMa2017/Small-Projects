import java.util.Random;


//  CARD. A playing card. You are not allowed to change this class in any way.

final class Card
{

//  RANK NAME. Printable names of card suits. We don't use the name at index 0,
//  because no card has rank 0.

  private static final String [] rankName =
   { "",
     "ace",      //  1
     "two",      //  2
     "three",    //  3
     "four",     //  4
     "five",     //  5
     "six",      //  6
     "seven",    //  7
     "eight",    //  8
     "nine",     //  9
     "ten",      //  10
     "jack",     //  11
     "queen",    //  12
     "king" };   //  13

//  SUIT NAME. Printable names of card suits.

  private static final String [] suitName =
   { "spade",    //  0
     "heart",    //  1
     "diamond",  //  2
     "club" };   //  3

  private int rank;  //  Card rank, between 1 and 13 inclusive.
  private int suit;  //  Card suit, between 0 and 3 inclusive.

//  CARD. Constructor. Make a new CARD with the given RANK and SUIT.

  public Card(int rank, int suit)
  {
    if (0 <= suit && suit <= 3 && 1 <= rank && rank <= 13)
    {
      this.rank = rank;
      this.suit = suit;
    }
    else
    {
      throw new IllegalArgumentException("No such card.");
    }
  }

//  GET RANK. Return the RANK of this card.

  public int getRank()
  {
    return rank;
  }

//  GET SUIT. Return the SUIT of this card.

  public int getSuit()
  {
    return suit;
  }

//  TO STRING. Return a string that describes this card, for printing only. For
//  example, we might return "the queen of diamonds" or "the ace of hearts".

  public String toString()
  {
    return "the " + rankName[rank] + " of " + suitName[suit] + "s";
  }
}

class Deck
{
	public Card[] cards = new Card[52]; // Card string with 52 cards.
	private int cardsRemain = 52;  // Counting the number of cards that are remain in the deck.
	public Deck()
	{
		int index = 51;
		for (int k=0; k<=3; k++)
		{
			for (int j=1; j<=13; j++)
			{
				cards[index] = new Card (j, k);
				index --;
			}
		}
	}

	public void shuffle()
	{
		if (cardsRemain == 52)
		{
			for (int i= cards.length - 1; i>0; i--)
			{

				Random r = new Random();
				int j = Math.abs(r.nextInt()) % (i+1);
				Card temp = cards[i];
				cards[i] = cards [j];
				cards[j] = temp;
			}
		}
		else
			throw new IllegalStateException("Can't shuffle after dealt any cards.");
	}

	public boolean canDeal()
	{
		return cardsRemain > 0;
	}

	public Card deal()
	{
		Card cardDealt;
		cardDealt = cards[cardsRemain-1];
		cards[cardsRemain -1] = null; // Pop the last card in the stack.
		cardsRemain -= 1; // Number of cards that remain in the deck -1.
		return cardDealt;
	}
}

class Tableau
{
	private class Pile
	{
		private Card card;
		private Pile next;
		private Pile (Card card, Pile next)
		{
			this.card = card;
			this.next = next;
		}
	}
	private Pile top;

	public Tableau()
	{
		top = null; // Initially empty.
	}

	private void addPile (Card card)
	{
		top = new Pile (card, top);
		System.out.println("Add " + card.toString());
	}

	private boolean canMerge()
	{
		if (manyPiles())
		{
			if (canPutOn(top.card, top.next.card))
			{
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}

	private boolean canPutOn(Card left, Card right)
	{
		return (left.getRank()>right.getRank()) || (left.getSuit()==right.getSuit());
	}

	private boolean manyPiles()
	{
		return top.next != null; // Test if there's >1 piles.
	}

	private void mergePiles()
	{
		if (canMerge())
		{
			Pile left = top;
			Pile right = left.next;
			System.out.println("Merge " + left.card.toString() + " and " + right.card.toString() + ".");
			left.next = right.next;		
		}
	}

	private void results()
	{
		if (manyPiles())
		{
			System.out.println("Lost the game.");
		}
		else
			System.out.println("Won the game.");
	}

	

	public void play()
	{
		Deck cardsPlay = new Deck(); // Create a new instance of deck to play the game.
		cardsPlay.shuffle();
		while (cardsPlay.canDeal())
		{
			addPile(cardsPlay.deal());
			while (canMerge())
			{
				mergePiles();
			}
		}
		results();
	}
}

class Driver
{
	public static void main (String [] args)
	{
		Tableau game = new Tableau();
		game.play();
	}
}

//---------------------------------------An Example of Output----------------------------------------------
/*
Add the ten of hearts
Add the four of hearts
Merge the four of hearts and the ten of hearts.
Add the queen of clubs
Merge the queen of clubs and the four of hearts.
Add the six of clubs
Merge the six of clubs and the queen of clubs.
Add the nine of diamonds
Merge the nine of diamonds and the six of clubs.
Add the seven of diamonds
Merge the seven of diamonds and the nine of diamonds.
Add the eight of spades
Merge the eight of spades and the seven of diamonds.
Add the jack of diamonds
Merge the jack of diamonds and the eight of spades.
Add the nine of clubs
Add the two of spades
Add the jack of hearts
Merge the jack of hearts and the two of spades.
Merge the jack of hearts and the nine of clubs.
Add the jack of spades
Add the ace of spades
Merge the ace of spades and the jack of spades.
Add the two of clubs
Merge the two of clubs and the ace of spades.
Add the eight of hearts
Merge the eight of hearts and the two of clubs.
Merge the eight of hearts and the jack of hearts.
Add the three of clubs
Add the ten of diamonds
Merge the ten of diamonds and the three of clubs.
Merge the ten of diamonds and the eight of hearts.
Merge the ten of diamonds and the jack of diamonds.
Add the four of spades
Add the king of hearts
Merge the king of hearts and the four of spades.
Merge the king of hearts and the ten of diamonds.
Add the two of diamonds
Add the four of diamonds
Merge the four of diamonds and the two of diamonds.
Add the king of diamonds
Merge the king of diamonds and the four of diamonds.
Add the nine of spades
Add the queen of hearts
Merge the queen of hearts and the nine of spades.
Add the king of spades
Merge the king of spades and the queen of hearts.
Add the five of clubs
Add the four of clubs
Merge the four of clubs and the five of clubs.
Add the seven of spades
Merge the seven of spades and the four of clubs.
Merge the seven of spades and the king of spades.
Add the ace of clubs
Add the nine of hearts
Merge the nine of hearts and the ace of clubs.
Merge the nine of hearts and the seven of spades.
Add the three of hearts
Merge the three of hearts and the nine of hearts.
Add the six of diamonds
Merge the six of diamonds and the three of hearts.
Merge the six of diamonds and the king of diamonds.
Add the ten of clubs
Merge the ten of clubs and the six of diamonds.
Add the queen of diamonds
Merge the queen of diamonds and the ten of clubs.
Add the eight of clubs
Add the six of spades
Add the five of spades
Merge the five of spades and the six of spades.
Add the five of diamonds
Add the ace of diamonds
Merge the ace of diamonds and the five of diamonds.
Add the king of clubs
Merge the king of clubs and the ace of diamonds.
Merge the king of clubs and the five of spades.
Merge the king of clubs and the eight of clubs.
Merge the king of clubs and the queen of diamonds.
Add the three of diamonds
Add the eight of diamonds
Merge the eight of diamonds and the three of diamonds.
Add the seven of clubs
Add the jack of clubs
Merge the jack of clubs and the seven of clubs.
Merge the jack of clubs and the eight of diamonds.
Merge the jack of clubs and the king of clubs.
Add the two of hearts
Add the three of spades
Merge the three of spades and the two of hearts.
Add the five of hearts
Merge the five of hearts and the three of spades.
Add the ace of hearts
Merge the ace of hearts and the five of hearts.
Add the six of hearts
Merge the six of hearts and the ace of hearts.
Add the queen of spades
Merge the queen of spades and the six of hearts.
Merge the queen of spades and the jack of clubs.
Add the seven of hearts
Add the ten of spades
Merge the ten of spades and the seven of hearts.
Merge the ten of spades and the queen of spades.
Lost the game.
*/


































