import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import poker.Hand;
import poker.Hand.HandType;
import poker.Card;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.beans.Transient;

import org.junit.jupiter.api.Test;

class Assignment1Test {
    // Define some hands as json strings that can be used for many tests
    private final String royal_flush = "{'cards':[{'suite':'CLUBS','value':'ACE'},{'suite':'CLUBS','value':'KING'},{'suite':'CLUBS','value':'QUEEN'},{'suite':'CLUBS','value':'JACK'},{'suite':'CLUBS','value':'TEN'}]}";
    private final String straight_flush = "{'cards':[{'suite':'CLUBS','value':'TEN'},{'suite':'CLUBS','value':'NINE'},{'suite':'CLUBS','value':'EIGHT'},{'suite':'CLUBS','value':'SEVEN'},{'suite':'CLUBS','value':'SIX'}]}";
    private final String four_of_a_kind = "{'cards':[{'suite':'SPADES','value':'FIVE'},{'suite':'DIAMONDS','value':'FIVE'},{'suite':'CLUBS','value':'FIVE'},{'suite':'HEARTS','value':'FIVE'},{'suite':'SPADES','value':'ACE'}]}";
    private final String full_house = "{'cards':[{'suite':'SPADES','value':'SIX'},{'suite':'DIAMONDS','value':'SIX'},{'suite':'CLUBS','value':'SIX'},{'suite':'HEARTS','value':'TEN'},{'suite':'SPADES','value':'TEN'}]}";
    private final String flush = "{'cards':[{'suite':'DIAMONDS','value':'TWO'},{'suite':'DIAMONDS','value':'THREE'},{'suite':'DIAMONDS','value':'FIVE'},{'suite':'DIAMONDS','value':'SEVEN'},{'suite':'DIAMONDS','value':'NINE'}]}";
    private final String straight = "{'cards':[{'suite':'SPADES','value':'TWO'},{'suite':'DIAMONDS','value':'THREE'},{'suite':'CLUBS','value':'FOUR'},{'suite':'HEARTS','value':'FIVE'},{'suite':'SPADES','value':'SIX'}]}";
    private final String three_of_a_kind = "{'cards':[{'suite':'SPADES','value':'FOUR'},{'suite':'DIAMONDS','value':'FIVE'},{'suite':'CLUBS','value':'FIVE'},{'suite':'HEARTS','value':'FIVE'},{'suite':'SPADES','value':'ACE'}]}";
    private final String two_pair = "{'cards':[{'suite':'SPADES','value':'FOUR'},{'suite':'DIAMONDS','value':'FOUR'},{'suite':'CLUBS','value':'FIVE'},{'suite':'HEARTS','value':'FIVE'},{'suite':'SPADES','value':'ACE'}]}";
    private final String pair = "{'cards':[{'suite':'SPADES','value':'FOUR'},{'suite':'DIAMONDS','value':'FIVE'},{'suite':'CLUBS','value':'FIVE'},{'suite':'HEARTS','value':'SIX'},{'suite':'SPADES','value':'ACE'}]}";
    private final String high_card = "{'cards':[{'suite':'SPADES','value':'TWO'},{'suite':'CLUBS','value':'FOUR'},{'suite':'CLUBS','value':'SIX'},{'suite':'HEARTS','value':'EIGHT'},{'suite':'SPADES','value':'NINE'}]}";

    private final String[] hands = { high_card, pair, two_pair, three_of_a_kind, straight, flush, full_house,
            four_of_a_kind, straight_flush, royal_flush };

    private static final Logger LOGGER = LogManager.getLogger(Assignment1Test.class);

    @Test
    void royal_flush_vs_all_hands() {
        Gson gson = new Gson();

        Hand h1 = new Hand();
        h1 = gson.fromJson(
                "{'cards':[{'suite':'DIAMONDS','value':'ACE'},{'suite':'DIAMONDS','value':'KING'},{'suite':'DIAMONDS','value':'QUEEN'},{'suite':'DIAMONDS','value':'JACK'},{'suite':'DIAMONDS','value':'TEN'}]}",
                Hand.class);

        int total_tests = 0;
        int passed_tests = 0;

        LOGGER.debug("Testing ROYAL_FLUSH beats all other hands");
        for (int i = 0; i < 10; i++) {
            Hand h2 = new Hand();
            h2 = gson.fromJson(hands[i], Hand.class);

            boolean expected = HandType.ROYAL_FLUSH.ordinal() > i;
            boolean result = h1.is_better_than(h2);

            String test_result = "[FAIL]";

            total_tests++;
            if (expected == result) {
                test_result = "[PASS]";
                passed_tests++;
            }

            LOGGER.debug("\t" + test_result + " Testing if ROYAL_FLUSH > " + h2.check_type().toString());
            LOGGER.debug("\t\tExpected:\t" + expected);
            LOGGER.debug("\t\tResult:\t\t" + result);
        }

        assertTrue(total_tests == passed_tests);
    }

    @Test
    void straight_flush_vs_all_hands() {
        Gson gson = new Gson();

        // This hand is a weaker straight flush than the one in the hands array
        Hand h1 = new Hand();
        h1 = gson.fromJson(
                "{'cards':[{'suite':'HEARTS','value':'SEVEN'},{'suite':'HEARTS','value':'SIX'},{'suite':'HEARTS','value':'FIVE'},{'suite':'HEARTS','value':'FOUR'},{'suite':'HEARTS','value':'THREE'}]}",
                Hand.class);

        int total_tests = 0;
        int passed_tests = 0;

        LOGGER.debug("Testing STRAIGHT_FLUSH vs all other hands");
        for (int i = 0; i < 10; i++) {
            Hand h2 = new Hand();
            h2 = gson.fromJson(hands[i], Hand.class);

            boolean expected = HandType.STRAIGHT_FLUSH.ordinal() > i;
            boolean result = h1.is_better_than(h2);

            String test_result = "[FAIL]";

            total_tests++;
            if (expected == result) {
                test_result = "[PASS]";
                passed_tests++;
            }

            LOGGER.debug("\t" + test_result + " Testing if STRAIGHT_FLUSH > " + h2.check_type().toString());
            LOGGER.debug("\t\tExpected:\t" + expected);
            LOGGER.debug("\t\tResult:\t\t" + result);
        }

        assertTrue(total_tests == passed_tests);
    }

    @Test
    void four_of_a_kind_better_then_3_of_a_kind() {
        // Use the logger to see what is happening
        // with a test when it fails. it is writen
        // to a log file.

        LOGGER.debug(" in the 4 of a kind test");

        // turn it into json
        Gson gson = new Gson();

        Hand H1 = new Hand();
        H1 = gson.fromJson(four_of_a_kind, Hand.class);

        Hand H2 = new Hand();
        H2 = gson.fromJson(three_of_a_kind, Hand.class);

        // Now test it
        // assertTrue(H1.is_better_than(H2)); uncomment when the is_better_than function
        // is working !
        assertTrue(!H2.is_better_than(H1));
    }
}
