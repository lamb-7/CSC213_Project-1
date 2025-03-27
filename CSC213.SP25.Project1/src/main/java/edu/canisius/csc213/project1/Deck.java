package edu.canisius.csc213.project1;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.random.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

/**
 * Represents a deck of playing cards with a configurable size.
 */
public class Deck {
    
    private int size;
    private ArrayList<Card> cards;

    /**
     * Creates a deck with a given size.
     * The size must be a multiple of 4 and at most 52.
     * 
     * @param size The number of cards in the deck.
     * @throws IllegalArgumentException if size is invalid.
     */

    public Deck(int size) {
        this.size = size;

        if(size % 4 == 0 && size >= 4 && size <= 52) {
            cards = new ArrayList<>(size);
            int repeat = size / 4;
            for(int i = 12; i >= 12 - repeat + 1; --i) {
                Card.Rank rank = Card.Rank.values()[i];
                for(Card.Suit suit : Card.Suit.values()) {
                    cards.add(new Card(suit, rank));
                }
            }
        }
        else {
            throw new IllegalArgumentException("Deck size must be a multiple of 4, at least 4, and at most 52.");
        }
    }

    /**
     * Shuffles the deck.
     */
    public void shuffle() {
        Random random = new Random();
        for(int i = 0; i < this.size; ++i) {
            int rand = random.nextInt(this.size);
            Card temp = cards.get(i);
            cards.set(i, cards.get(rand));
            cards.set(rand, temp);
        }
    }

    /**
     * Draws the top card from the deck.
     * 
     * @return The drawn card.
     * @throws NoSuchElementException if the deck is empty.
     */
    public Card draw() {
        if (this.size < 1) {
            throw new NoSuchElementException("The deck is empty.");
        }

        Card drawnCard = cards.get(0);
        cards.remove(0);
        -- this.size;
        return drawnCard;
    }

    /**
     * Gets the number of remaining cards in the deck.
     *
     * @return The number of cards left.
     */
    public int size() {
        return this.size;
    }

}