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
    void royal_flush_vs_straight_flush() {
        Gson gson = new Gson();

        HandType test1 = HandType.ROYAL_FLUSH;
        HandType test2 = HandType.STRAIGHT_FLUSH;

        Hand test_royal_flush = gson.fromJson(this.royal_flush, Hand.class);
        Hand test_straight_flush = gson.fromJson(this.straight_flush, Hand.class);

        boolean is_better = test_royal_flush.is_better_than(test_straight_flush);
        boolean is_better_expected = true;

        String string_result = "[FAIL]";
        if (is_better_expected == is_better) {
            string_result = "[PASS]";
        }

        LOGGER.debug("Testing " + test1 + " vs. " + test2);
        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " > " + test2);
        LOGGER.debug("\t\tExpected:\t" + is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_better);

        boolean is_worse = test_straight_flush.is_better_than(test_royal_flush);

        if (!is_better_expected == is_worse) {
            string_result = "[PASS]";
        } else {
            string_result = "[FAIL]";
        }

        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " < " + test2);
        LOGGER.debug("\t\tExpected:\t" + !is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_worse);

        boolean is_equal = test_royal_flush.is_equal(test_straight_flush);

        if (!is_better_expected == is_equal) {
            string_result = "[PASS]";
        } else {
            string_result = "[FAIL]";
        }

        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " == " + test2);
        LOGGER.debug("\t\tExpected:\t" + !is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_equal);

        assertTrue((is_better_expected == is_better) && (!is_better_expected == is_worse)
                && (!is_better_expected == is_equal));
    }

    @Test
    void straight_flush_vs_4_of_a_kind() {
        Gson gson = new Gson();

        HandType test1 = HandType.STRAIGHT_FLUSH;
        HandType test2 = HandType.FOUR_OF_A_KIND;

        Hand test_straight_flush = gson.fromJson(this.straight_flush, Hand.class);
        Hand test_four_of_a_kind = gson.fromJson(this.four_of_a_kind, Hand.class);

        boolean is_better = test_straight_flush.is_better_than(test_four_of_a_kind);
        boolean is_better_expected = true;

        String string_result = "[FAIL]";
        if (is_better_expected == is_better) {
            string_result = "[PASS]";
        }

        LOGGER.debug("Testing " + test1 + " vs. " + test2);
        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " > " + test2);
        LOGGER.debug("\t\tExpected:\t" + is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_better);

        boolean is_worse = test_four_of_a_kind.is_better_than(test_straight_flush);

        if (!is_better_expected == is_worse) {
            string_result = "[PASS]";
        } else {
            string_result = "[FAIL]";
        }

        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " < " + test2);
        LOGGER.debug("\t\tExpected:\t" + !is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_worse);

        boolean is_equal = test_straight_flush.is_equal(test_four_of_a_kind);

        if (!is_better_expected == is_equal) {
            string_result = "[PASS]";
        } else {
            string_result = "[FAIL]";
        }

        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " == " + test2);
        LOGGER.debug("\t\tExpected:\t" + !is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_equal);

        assertTrue((is_better_expected == is_better) && (!is_better_expected == is_worse)
                && (!is_better_expected == is_equal));
    }

    @Test
    void _4_of_a_kind_vs_full_house() {
        Gson gson = new Gson();

        HandType test1 = HandType.FOUR_OF_A_KIND;
        HandType test2 = HandType.FULL_HOUSE;

        Hand test_4_of_a_kind = gson.fromJson(this.four_of_a_kind, Hand.class);
        Hand test_full_house = gson.fromJson(this.full_house, Hand.class);

        boolean is_better = test_4_of_a_kind.is_better_than(test_full_house);
        boolean is_better_expected = true;

        String string_result = "[FAIL]";
        if (is_better_expected == is_better) {
            string_result = "[PASS]";
        }

        LOGGER.debug("Testing " + test1 + " vs. " + test2);
        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " > " + test2);
        LOGGER.debug("\t\tExpected:\t" + is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_better);

        boolean is_worse = test_full_house.is_better_than(test_4_of_a_kind);

        if (!is_better_expected == is_worse) {
            string_result = "[PASS]";
        } else {
            string_result = "[FAIL]";
        }

        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " < " + test2);
        LOGGER.debug("\t\tExpected:\t" + !is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_worse);

        boolean is_equal = test_4_of_a_kind.is_equal(test_full_house);

        if (!is_better_expected == is_equal) {
            string_result = "[PASS]";
        } else {
            string_result = "[FAIL]";
        }

        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " == " + test2);
        LOGGER.debug("\t\tExpected:\t" + !is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_equal);

        assertTrue((is_better_expected == is_better) && (!is_better_expected == is_worse)
                && (!is_better_expected == is_equal));
    }

    @Test
    void full_house_vs_flush() {
        Gson gson = new Gson();

        HandType test1 = HandType.FULL_HOUSE;
        HandType test2 = HandType.FLUSH;

        Hand test_full_house = gson.fromJson(this.full_house, Hand.class);
        Hand test_flush = gson.fromJson(this.flush, Hand.class);

        boolean is_better = test_full_house.is_better_than(test_flush);
        boolean is_better_expected = true;

        String string_result = "[FAIL]";
        if (is_better_expected == is_better) {
            string_result = "[PASS]";
        }

        LOGGER.debug("Testing " + test1 + " vs. " + test2);
        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " > " + test2);
        LOGGER.debug("\t\tExpected:\t" + is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_better);

        boolean is_worse = test_flush.is_better_than(test_full_house);

        if (!is_better_expected == is_worse) {
            string_result = "[PASS]";
        } else {
            string_result = "[FAIL]";
        }

        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " < " + test2);
        LOGGER.debug("\t\tExpected:\t" + !is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_worse);

        boolean is_equal = test_full_house.is_equal(test_flush);

        if (!is_better_expected == is_equal) {
            string_result = "[PASS]";
        } else {
            string_result = "[FAIL]";
        }

        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " == " + test2);
        LOGGER.debug("\t\tExpected:\t" + !is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_equal);

        assertTrue((is_better_expected == is_better) && (!is_better_expected == is_worse)
                && (!is_better_expected == is_equal));
    }

    @Test
    void flush_vs_straight() {
        Gson gson = new Gson();

        HandType test1 = HandType.FLUSH;
        HandType test2 = HandType.STRAIGHT;

        Hand test_flush = gson.fromJson(this.flush, Hand.class);
        Hand test_straight = gson.fromJson(this.straight, Hand.class);

        boolean is_better = test_flush.is_better_than(test_straight);
        boolean is_better_expected = true;

        String string_result = "[FAIL]";
        if (is_better_expected == is_better) {
            string_result = "[PASS]";
        }

        LOGGER.debug("Testing " + test1 + " vs. " + test2);
        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " > " + test2);
        LOGGER.debug("\t\tExpected:\t" + is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_better);

        boolean is_worse = test_straight.is_better_than(test_flush);

        if (!is_better_expected == is_worse) {
            string_result = "[PASS]";
        } else {
            string_result = "[FAIL]";
        }

        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " < " + test2);
        LOGGER.debug("\t\tExpected:\t" + !is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_worse);

        boolean is_equal = test_flush.is_equal(test_straight);

        if (!is_better_expected == is_equal) {
            string_result = "[PASS]";
        } else {
            string_result = "[FAIL]";
        }

        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " == " + test2);
        LOGGER.debug("\t\tExpected:\t" + !is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_equal);

        assertTrue((is_better_expected == is_better) && (!is_better_expected == is_worse)
                && (!is_better_expected == is_equal));
    }

    @Test
    void straight_vs_3_of_a_kind() {
        Gson gson = new Gson();

        HandType test1 = HandType.STRAIGHT;
        HandType test2 = HandType.THREE_OF_A_KIND;

        Hand test_straight = gson.fromJson(this.straight, Hand.class);
        Hand test_3_of_a_kind = gson.fromJson(this.three_of_a_kind, Hand.class);

        boolean is_better = test_straight.is_better_than(test_3_of_a_kind);
        boolean is_better_expected = true;

        String string_result = "[FAIL]";
        if (is_better_expected == is_better) {
            string_result = "[PASS]";
        }

        LOGGER.debug("Testing " + test1 + " vs. " + test2);
        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " > " + test2);
        LOGGER.debug("\t\tExpected:\t" + is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_better);

        boolean is_worse = test_3_of_a_kind.is_better_than(test_straight);

        if (!is_better_expected == is_worse) {
            string_result = "[PASS]";
        } else {
            string_result = "[FAIL]";
        }

        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " < " + test2);
        LOGGER.debug("\t\tExpected:\t" + !is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_worse);

        boolean is_equal = test_straight.is_equal(test_3_of_a_kind);

        if (!is_better_expected == is_equal) {
            string_result = "[PASS]";
        } else {
            string_result = "[FAIL]";
        }

        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " == " + test2);
        LOGGER.debug("\t\tExpected:\t" + !is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_equal);

        assertTrue((is_better_expected == is_better) && (!is_better_expected == is_worse)
                && (!is_better_expected == is_equal));
    }

    @Test
    void _3_of_a_kind_vs_2_pair() {
        Gson gson = new Gson();

        HandType test1 = HandType.THREE_OF_A_KIND;
        HandType test2 = HandType.TWO_PAIR;

        Hand test_3_of_a_kind = gson.fromJson(this.three_of_a_kind, Hand.class);
        Hand test_2_pair = gson.fromJson(this.two_pair, Hand.class);

        boolean is_better = test_3_of_a_kind.is_better_than(test_2_pair);
        boolean is_better_expected = true;

        String string_result = "[FAIL]";
        if (is_better_expected == is_better) {
            string_result = "[PASS]";
        }

        LOGGER.debug("Testing " + test1 + " vs. " + test2);
        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " > " + test2);
        LOGGER.debug("\t\tExpected:\t" + is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_better);

        boolean is_worse = test_2_pair.is_better_than(test_3_of_a_kind);

        if (!is_better_expected == is_worse) {
            string_result = "[PASS]";
        } else {
            string_result = "[FAIL]";
        }

        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " < " + test2);
        LOGGER.debug("\t\tExpected:\t" + !is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_worse);

        boolean is_equal = test_3_of_a_kind.is_equal(test_2_pair);

        if (!is_better_expected == is_equal) {
            string_result = "[PASS]";
        } else {
            string_result = "[FAIL]";
        }

        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " == " + test2);
        LOGGER.debug("\t\tExpected:\t" + !is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_equal);

        assertTrue((is_better_expected == is_better) && (!is_better_expected == is_worse)
                && (!is_better_expected == is_equal));
    }

    @Test
    void _2_pair_vs_pair() {
        Gson gson = new Gson();

        HandType test1 = HandType.TWO_PAIR;
        HandType test2 = HandType.PAIR;

        Hand test_2_pair = gson.fromJson(this.two_pair, Hand.class);
        Hand test_pair = gson.fromJson(this.pair, Hand.class);

        boolean is_better = test_2_pair.is_better_than(test_pair);
        boolean is_better_expected = true;

        String string_result = "[FAIL]";
        if (is_better_expected == is_better) {
            string_result = "[PASS]";
        }

        LOGGER.debug("Testing " + test1 + " vs. " + test2);
        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " > " + test2);
        LOGGER.debug("\t\tExpected:\t" + is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_better);

        boolean is_worse = test_pair.is_better_than(test_2_pair);

        if (!is_better_expected == is_worse) {
            string_result = "[PASS]";
        } else {
            string_result = "[FAIL]";
        }

        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " < " + test2);
        LOGGER.debug("\t\tExpected:\t" + !is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_worse);

        boolean is_equal = test_2_pair.is_equal(test_pair);

        if (!is_better_expected == is_equal) {
            string_result = "[PASS]";
        } else {
            string_result = "[FAIL]";
        }

        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " == " + test2);
        LOGGER.debug("\t\tExpected:\t" + !is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_equal);

        assertTrue((is_better_expected == is_better) && (!is_better_expected == is_worse)
                && (!is_better_expected == is_equal));
    }

    @Test
    void pair_vs_high_card() {
        Gson gson = new Gson();

        HandType test1 = HandType.PAIR;
        HandType test2 = HandType.HIGH_CARD;

        Hand test_pair = gson.fromJson(this.pair, Hand.class);
        Hand test_high_card = gson.fromJson(this.high_card, Hand.class);

        boolean is_better = test_pair.is_better_than(test_high_card);
        boolean is_better_expected = true;

        String string_result = "[FAIL]";
        if (is_better_expected == is_better) {
            string_result = "[PASS]";
        }

        LOGGER.debug("Testing " + test1 + " vs. " + test2);
        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " > " + test2);
        LOGGER.debug("\t\tExpected:\t" + is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_better);

        boolean is_worse = test_high_card.is_better_than(test_pair);

        if (!is_better_expected == is_worse) {
            string_result = "[PASS]";
        } else {
            string_result = "[FAIL]";
        }

        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " < " + test2);
        LOGGER.debug("\t\tExpected:\t" + !is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_worse);

        boolean is_equal = test_pair.is_equal(test_high_card);

        if (!is_better_expected == is_equal) {
            string_result = "[PASS]";
        } else {
            string_result = "[FAIL]";
        }

        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " == " + test2);
        LOGGER.debug("\t\tExpected:\t" + !is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_equal);

        assertTrue((is_better_expected == is_better) && (!is_better_expected == is_worse)
                && (!is_better_expected == is_equal));
    }

    @Test
    void high_card_vs_high_card() {
        Gson gson = new Gson();

        HandType test1 = HandType.HIGH_CARD;
        HandType test2 = HandType.HIGH_CARD;

        Hand test_high_card_strong = gson.fromJson(this.high_card, Hand.class);
        Hand test_high_card_weak = gson.fromJson(
                "{'cards':[{'suite':'SPADES','value':'TWO'},{'suite':'CLUBS','value':'FOUR'},{'suite':'CLUBS','value':'SIX'},{'suite':'HEARTS','value':'EIGHT'},{'suite':'SPADES','value':'SEVEN'}]}",
                Hand.class);

        boolean is_better = test_high_card_strong.is_better_than(test_high_card_weak);
        boolean is_better_expected = true;

        String string_result = "[FAIL]";
        if (is_better_expected == is_better) {
            string_result = "[PASS]";
        }

        LOGGER.debug("Testing " + test1 + " vs. " + test2);
        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " > " + test2);
        LOGGER.debug("\t\tExpected:\t" + is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_better);

        boolean is_worse = test_high_card_weak.is_better_than(test_high_card_strong);

        if (!is_better_expected == is_worse) {
            string_result = "[PASS]";
        } else {
            string_result = "[FAIL]";
        }

        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " < " + test2);
        LOGGER.debug("\t\tExpected:\t" + !is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_worse);

        boolean is_equal = test_high_card_strong.is_equal(test_high_card_weak);

        if (!is_better_expected == is_equal) {
            string_result = "[PASS]";
        } else {
            string_result = "[FAIL]";
        }

        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " == " + test2);
        LOGGER.debug("\t\tExpected:\t" + !is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_equal);

        assertTrue((is_better_expected == is_better) && (!is_better_expected == is_worse)
                && (!is_better_expected == is_equal));
    }

    @Test
    void royal_flush_vs_royal_flush() {
        Gson gson = new Gson();

        HandType test1 = HandType.ROYAL_FLUSH;
        HandType test2 = HandType.ROYAL_FLUSH;

        Hand test_royal_flush = gson.fromJson(this.royal_flush, Hand.class);
        Hand test_royal_flush_2 = gson.fromJson(
                "{'cards':[{'suite':'DIAMONDS','value':'ACE'},{'suite':'DIAMONDS','value':'KING'},{'suite':'DIAMONDS','value':'QUEEN'},{'suite':'DIAMONDS','value':'JACK'},{'suite':'DIAMONDS','value':'TEN'}]}",
                Hand.class);

        boolean is_better = test_royal_flush.is_better_than(test_royal_flush_2);
        boolean is_better_expected = false;

        String string_result = "[FAIL]";
        if (is_better_expected == is_better) {
            string_result = "[PASS]";
        }

        LOGGER.debug("Testing " + test1 + " vs. " + test2);
        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " > " + test2);
        LOGGER.debug("\t\tExpected:\t" + is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_better);

        boolean is_worse = test_royal_flush_2.is_better_than(test_royal_flush);

        if (is_better_expected == is_worse) {
            string_result = "[PASS]";
        } else {
            string_result = "[FAIL]";
        }

        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " < " + test2);
        LOGGER.debug("\t\tExpected:\t" + !is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_worse);

        boolean is_equal = test_royal_flush.is_equal(test_royal_flush_2);

        if (!is_better_expected == is_equal) {
            string_result = "[PASS]";
        } else {
            string_result = "[FAIL]";
        }

        LOGGER.debug("\t" + string_result + " Testing if " + test1 + " == " + test2);
        LOGGER.debug("\t\tExpected:\t" + !is_better_expected);
        LOGGER.debug("\t\tResult:\t\t" + is_equal);

        assertTrue((is_better_expected == is_better) && (is_better_expected == is_worse)
                && (!is_better_expected == is_equal));
    }
}
