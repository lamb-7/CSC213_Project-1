package edu.canisius.csc213.project1;
import java.util.Objects;

/**
 * Represents a playing card with a suit and rank.
 */
public class Card {

    public enum Suit { HEARTS, DIAMONDS, CLUBS, SPADES }
    public enum Rank { 
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, 
        JACK, QUEEN, KING, ACE 
    }

    private Suit suit;
    private Rank rank;
    
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
    
    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        else if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Card otherCard = (Card)obj;
        return suit == otherCard.suit && rank == otherCard.rank;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }
}