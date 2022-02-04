package poker;

import java.lang.Exception;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import poker.Card.Value;

public class Hand {
    // private transient int i=10;
    // marked transient they will not serialized / deserialized

    public enum HandType {
        HIGH_CARD, PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT, FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH,
        ROYAL_FLUSH;
    }

    private Card[] cards;

    public Hand() {
        this.cards = new Card[5];
    }

    public Hand(Card[] cards) {
        this();

        for (int i = 0; i < 5; i++) {
            this.cards[i] = cards[i];
        }

        Arrays.sort(this.cards, Collections.reverseOrder());
    }

    public void swapCard(int index, Card newCard) {
        this.cards[index] = newCard.duplicate();

        Arrays.sort(this.cards, Collections.reverseOrder());
    }

    public boolean is_better_than(Hand h) {
        if (this.check_type().ordinal() == h.check_type().ordinal()) {
            ArrayList<Card> this_unique_cards = this.highest_unique_card();
            ArrayList<Card> h_unique_cards = h.highest_unique_card();

            for (int i = 0; i < this_unique_cards.size(); i++) {
                if (this_unique_cards.get(i).compareTo(h_unique_cards.get(i)) != 0) {
                    return this_unique_cards.get(i).compareTo(h_unique_cards.get(i)) > 0;
                }
            }
        } else {
            return this.check_type().ordinal() > h.check_type().ordinal();
        }

        return false;
    }

    public boolean is_equal(Hand h) {
        boolean is_equal = true;

        if (this.check_type().ordinal() == h.check_type().ordinal()) {
            ArrayList<Card> this_unique_cards = this.highest_unique_card();
            ArrayList<Card> h_unique_cards = h.highest_unique_card();

            for (int i = 0; i < this_unique_cards.size(); i++) {
                if (this_unique_cards.get(i).compareTo(h_unique_cards.get(i)) != 0) {
                    is_equal = false;
                }
            }
        } else {
            is_equal = false;
        }

        return is_equal;
    }

    protected ArrayList<Card> highest_unique_card() {
        ArrayList<Card> unique_cards = new ArrayList<>();

        for (int i =0; i < 4; i++) {
            if (this.cards[i].compareTo(this.cards[i + 1]) != 0) {
                unique_cards.add(this.cards[i]);
                if (i == 4) {
                    unique_cards.add(this.cards[5]);
                }
            }
        }

        return unique_cards;
    }

    public HandType check_type() {
        HandType type = HandType.HIGH_CARD;

        boolean straight = true;
        boolean flush = true;

        int[] kinds = { 1, 1 };
        int kind_index = 0;

        for (int i = 0; i < 4; i++) {
            if (this.cards[i].compareTo(this.cards[i + 1]) != -1) {
                straight = false;
            }

            if (this.cards[i].compareTo(this.cards[i + 1]) == 0) {
                kinds[kind_index]++;
            } else if (kinds[0] > 1 && this.cards[i].compareTo(this.cards[i + 1]) != 0) {
                kind_index++;
            }

            if (!this.cards[i].compareSuiteTo(this.cards[i + 1])) {
                flush = false;
            }
        }

        if (straight && flush && (this.cards[4].value == Value.ACE)) {
            type = HandType.ROYAL_FLUSH;
        } else if (straight && flush) {
            type = HandType.STRAIGHT_FLUSH;
        } else if (kinds[0] == 4) {
            type = HandType.FOUR_OF_A_KIND;
        } else if ((kinds[0] == 2 && kinds[1] == 3) || (kinds[0] == 3 && kinds[1] == 2)) {
            type = HandType.FULL_HOUSE;
        } else if (!straight && flush) {
            type = HandType.FLUSH;
        } else if (straight && !flush) {
            type = HandType.STRAIGHT;
        } else if (kinds[0] == 3 && kinds[1] == 1) {
            type = HandType.THREE_OF_A_KIND;
        } else if (kinds[0] == 2 && kinds[1] == 2) {
            type = HandType.TWO_PAIR;
        } else if (kinds[0] == 2 && kinds[1] == 1) {
            type = HandType.PAIR;
        } else {
            type = HandType.HIGH_CARD;
        }

        return type;
    }

    public Hand duplicate() {
        return new Hand(this.cards);
    }

    @Override
    public String toString() {
        StringBuilder hand = new StringBuilder(this.check_type().toString() + "\n");

        for (Card c : this.cards) {
            hand.append(c.toString() + "\n");
        }

        return hand.toString();
    }
}