package edu.canisius.csc213.project1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * UniqueHands class to analyze how long it takes to see every possible hand 
 * for different deck sizes and hand sizes.
 */
public class UniqueHands {
    public static void main(String[] args) {
        int[] deckSizes = {24, 28}; // Deck sizes to test
        int[] handSizes = {6, 7}; // Hand sizes to test
        int trials = 5; // Number of trials per deck-hand combination

        System.out.println("🃏 Deck Simulation: How long to see every possible hand?");
        System.out.println("------------------------------------------------------");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("unique_hands_results.csv");
            PrintStream filePrintStream = new PrintStream(fileOutputStream, true);
            filePrintStream.println("Deck Size,Hand Size,Trial,Attempts,Time (sec)");

        for (int deckSize : deckSizes) {
            for (int handSize : handSizes) {
                long numUniqueHands = calculateTotalUniqueHands(deckSize, handSize);
                for(int i = 0; i < trials; ++i) {
                    long startTime = System.nanoTime();
                    int totalAttempts = countAttemptsToSeeAllHands(deckSize, handSize);
                    long endTime = System.nanoTime();
                    double attemptTimeSecs = ((double)endTime - (double)startTime) / 1000000000.0;
                    System.out.println("100.00% coverage reached after " + totalAttempts + " attempts (Unique Hands: " + numUniqueHands + " / " + numUniqueHands + " | Needed: 0)");
                    System.out.printf("Deck Size: %d | Hand Size: %d | Trial %d | Attempts: %d | Time: %.3f sec\n", deckSize, handSize, i+1, totalAttempts, attemptTimeSecs);
                    filePrintStream.printf("%d,%d,%d,%d,%.3f\n", deckSize, handSize, i+1, totalAttempts, attemptTimeSecs);
                }
            }
        }
        filePrintStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public static int countAttemptsToSeeAllHands(int deckSize, int handSize) {
        long uniqueHands = calculateTotalUniqueHands(deckSize, handSize);
        int currentAttempts = 0;
        Set<Set<Card>> uniqueHandSet = new HashSet<>();

        while (uniqueHandSet.size() < uniqueHands) {
            Deck deck = new Deck(deckSize);
            deck.shuffle();
            Set<Card> hand = new HashSet<>();
            
            for (int i = 0; i < handSize; ++i) {
                hand.add(deck.draw());
            }

            uniqueHandSet.add(hand);
            ++currentAttempts;

            if (currentAttempts % 100000 == 0) {
                double percentage = 100.0 * ((double)uniqueHandSet.size() / uniqueHands);
                if (percentage >= 99.994 && uniqueHandSet.size() < uniqueHands) {
                    percentage = 99.990;
                }
                System.out.printf("Progress: %.2f%% coverage after %d attempts (Unique Hands: %d / %d | Needed: %d)\n", percentage, currentAttempts, uniqueHandSet.size(), uniqueHands, uniqueHands - uniqueHandSet.size());
            }
        }
        return currentAttempts;
    }

    public static long calculateTotalUniqueHands(int deckSize, int handSize) {
        long numUniqueHands = 1;
        for (int i = 1; i <= handSize; i++) {
            numUniqueHands *= deckSize - (handSize - i);
            numUniqueHands /= i; 
        }
        return numUniqueHands;
    }
}
