package poker;

public class Card implements Comparable<Card> {
   public enum Suite {
      HEARTS, CLUBS, DIAMONDS, SPADES
   }
   
   public enum Value {
      TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE 
   }
   
   public Suite suite;
   public Value value;

   public Card() {
   }

   public Card(Value value, Suite suite) {
      this.value = value;
      this.suite = suite;
   }

   @Override
   public String toString() {
      return this.value + " of " + this.suite;
   }

   @Override
   public int compareTo(Card c) {
      return this.value.ordinal() - c.value.ordinal();
   }
}
