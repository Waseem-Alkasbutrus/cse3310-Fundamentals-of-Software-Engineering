import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import poker.Hand;
import poker.Card.Suite;
import poker.Card.Value;
import poker.Card;

public class Main {

   public static void main(String[] args) {
      // this function is provided as a convenient way to
      // run the code from the command line.
      //
      // it will not be graded, but it needs to compile and
      // execute without a failure.

      System.out.println("==================================\nStart");

      // allocate space for 5 card references
      Card[] cards = new Card[5];
      cards[0] = new Card(Value.ACE, Suite.SPADES);
      cards[1] = new Card(Value.FOUR, Suite.DIAMONDS);
      cards[2] = new Card(Value.FOUR, Suite.SPADES);
      cards[3] = new Card(Value.SEVEN, Suite.HEARTS);
      cards[4] = new Card(Value.TWO, Suite.SPADES);

      Card[] cards2 = new Card[5];
      cards2[0] = new Card(Value.TEN, Suite.SPADES);
      cards2[1] = new Card(Value.TEN, Suite.DIAMONDS);
      cards2[2] = new Card(Value.FIVE, Suite.SPADES);
      cards2[3] = new Card(Value.FOUR, Suite.HEARTS);
      cards2[4] = new Card(Value.TWO, Suite.SPADES);

      // Demonstrate converting json in and out of a 'Hand'
      //
      Hand H1 = new Hand(cards);

      // turn it into json
      Gson gson = new Gson();
      String jsonString = gson.toJson(H1);
      System.out.println("hand1:\n" + jsonString);

      // and back into a "hand"
      Hand H3 = new Hand();
      H3 = gson.fromJson(jsonString, Hand.class);

      // change something
      H3.swapCard(4, new Card(Value.FIVE, Suite.DIAMONDS));

      // show that it works
      System.out.println("\nhand3:\n" + gson.toJson(H3) + "\n");

      // show the comparison function
      Hand H2 = new Hand(cards2);

      System.out.println("\nH1: " + H1);
      System.out.println("\nH2: " + H2);
      System.out.println("\nH3: " + H3);

      if (H1.is_equal(H2)) {
         System.out.println("hand1 and hand2 are equal");
      } else if (H1.is_better_than(H2)) {
         System.out.println("hand1 is better than hand2");
      } else if (H2.is_better_than(H1)) {
         System.out.println("hand2 is better than hand1");
      }

      if (H1.is_equal(H3)) {
         System.out.println("hand1 and hand3 are equal");
      } else if (H1.is_better_than(H3)) {
         System.out.println("hand1 is better than hand3");
      } else {
         System.out.println("hand3 is better than hand1");
      }

      System.out.println("End\n==================================");
   }
}
