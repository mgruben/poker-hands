/*
 * Copyright (C) 2016 Michael <GrubenM@GMail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package solution;

/**
 * A Hand object, representing 5 cards in a game of poker.
 * @author Michael <GrubenM@GMail.com>
 */
public class Hand {
    
    // The structure in which to store our Cards
    private Card[] h;
    
    // How many cards are currently in this Hand?
    private int len;
    
    // The primitive to represent which Hand beats which
    private int rank;
    
    // The primitive to break ties, for Hands of the same rank
    private int tie;
        
    /**
     * Given a String representing a full hand (5 cards), construct that Hand
     * @param s 
     */
    public Hand(String s) {
        len = 0;
        h = new Card[5];
        
        for (String c: s.split(" ")) add(new Card(c));
        
        rank = parseRank();
    }
    
    /**
     * Given a Card c, add that Card to this Hand, if possible
     * @param c, the Card to add 
     */
    private void add(Card c) {
        if (len == 5) throw new ArrayIndexOutOfBoundsException("Hand is full");
        else h[len++] = c;
    }
    
    private int parseRank() {
        
        // A temporary variable to store counts
        int count = 0;
        
        /**
         * Because each card has a unique value, and because most of our rank
         * checking involves whether the cards are in sequence, flatten the
         * Hand into an array Data Structure, to be able to quickly iterate
         * through, count duplicates, and find neighbors.
         * 
         * At the same time, store the suit of the checked cards, to be able
         * to tell whether all of the cards are in the same suit.
         */
        int[] a = new int[15];
        StringBuilder sb = new StringBuilder();
        for (Card c: h) {
            a[c.getVal()]++;
            sb.append(c.getSuit());
        }
        String suits = sb.toString();
        
        // Are all of the Cards in this Hand in the same suit?
        boolean sameSuit = false;
        if (suits.equals("CCCCC") || suits.equals("SSSSS") ||
                suits.equals("HHHHH") || suits.equals("DDDDD")) {
            sameSuit = true;
        }
        
        /**
         * Now, we assign a rank and tie to the hand.
         * 
         * Keep in mind that a hand may qualify for multiple valid ranks,
         * but it is assigned its highest-possible rank.
         */
        
        /** 
         * Rank 10. Royal Flush
         * 
         * Ten, Jack, Queen, King, Ace, in same suit
         */
        if (a[10] == 1 && a[11] == 1 && a[12] == 1 && a[13] == 1 && a[14] == 1
                && sameSuit) {
            tie = 0;
            return 10;
        }
        
        /**
         * Rank 9. Straight Flush
         * 
         * All cards are consecutive values of same suit
         * 
         * Note that we've already checked from i = 10 to i = 14 above,
         * and so can stop our search here once i exceeds 9.
         */
        for (int i = 0; i < a.length - 5; i++) {
            count = 0;
            while (a[i] == 1) {
                count++;
                i++;
            }
            if (count == 5 && sameSuit) {
                tie = i;
                return 9;
            }
        }
        
        /**
         * Rank 8. Four of a Kind
         * 
         * Four cards of the same value
         */
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 4) {
                tie = i;
                return 8;
            }
        }
        
        /** 
         * Rank 7. Full House
         * 
         * Three of a kind and a pair
         */
        boolean triplet = false;
        boolean pair = false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 2) pair = true;
            if (a[i] == 3) {
                tie = i;
                triplet = true;
            }
            if (pair && triplet) return 7;
        }
        
        /** 
         * Rank 6. Flush
         * 
         * All cards of the same suit
         */
        if (sameSuit) return 6;
        
        /**
         * Rank 5. Straight
         * 
         * All cards are consecutive values
         */
        for (int i = 0; i < a.length - 5; i++) {
            count = 0;
            while (a[i] == 1) {
                count++;
                i++;
            }
            if (count == 5) {
                tie = i;
                return 5;
            }
        }
                
        /** 
         * Rank 4. Three of a Kind
         * 
         * Three cards of the same value
         */
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 3) {
                tie = i;
                return 4;
            }
        }
        
        /** 
         * Rank 3. Two Pairs
         * 
         * Two different pairs
         */
        count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 2) {
                tie = i;
                count++;
            }
        }
        if (count == 2) return 3;
        
        /**
         * Rank 2. One Pair
         * 
         * Two cards of the same value
         * 
         * Recall that we've already summed the number of pairs from above,
         * so rather than repeat that iteration, we continue the conditional
         * with an `else` clause here.
         */
        else if (count == 1) return 2;
        
        /** 
         * Rank 1. High Card
         * 
         * Highest value card
         */
        for (int i = a.length - 1; i > -1; i--) {
            if (a[i] > 0) {
                tie = i;
                break;
            }
        }
        return 1;
    }
    
    /**
     * Does this Hand beat that Hand?
     * @param that, the Hand played against this hand
     * @return -1 if that Hand beats this Hand,
     *          1 if this Hand beats that Hand,
     *          0 otherwise.
     */
    public int compareTo(Hand that) {
        if (this.rank < that.rank) return -1;
        else if (this.rank > that.rank) return 1;
        else {
            if (this.tie < that.tie) return -1;
            else if (this.tie > that.tie) return 1;
        }
        return 0;
    }
}
