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

import java.util.Iterator;

/**
 * A Hand object, representing 5 cards in a game of poker.
 * @author Michael <GrubenM@GMail.com>
 */
public class Hand implements Iterable<Card> {
    
    // The structure in which to store our Cards
    private Card[] h;
    
    // How many cards are currently in this Hand?
    private int len;
    
    // The primitive to represent which Hand beats which
    private int rank;
    
    // The primitive to break ties, for Hands of the same rank
    private int tie;
    
    public Hand() {
        len = 0;
        h = new Card[5];
    }
    
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
    
    private int parseRank() {
        
        int count = 0;
        
        // Build some structures to query
        int[] a = new int[15];
        StringBuilder sb = new StringBuilder();
        for (Card c: h) {
            a[c.getVal()]++;
            sb.append(c.getSuit());
        }
        String suits = sb.toString();
        
        // Set our sameSuit flag
        boolean sameSuit = false;
        if (suits.equals("CCCCC") || suits.equals("SSSSS") ||
                suits.equals("HHHHH") || suits.equals("DDDDD")) {
            sameSuit = true;
        }
        
        // 10. Royal Flush
        if (a[10] == 1 && a[11] == 1 && a[12] == 1 && a[13] == 1 && a[14] == 1
                && sameSuit) {
            tie = 0;
            return 10;
        }
        
        // 9. Straight Flush
        // Note that we've already checked from i = 10 to i = 14 above,
        // and so can stop our search here from i = 9 to i = 13.
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
        
        // 8. 4-of-a-kind
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 4) {
                tie = i;
                return 8;
            }
        }
        
        // 7. Full House (ahhh ahhh ahh ahh ahhhhh....)
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
        
        // 6. Flush
        if (sameSuit) return 6;
        
        // 5. Straight
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
                
        // 4. 3-of-a-kind
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 3) {
                tie = i;
                return 4;
            }
        }
        
        // 3. 2 pairs
        count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 2) {
                tie = i;
                count++;
            }
        }
        if (count == 2) return 3;
        // 2. single pair
        else if (count == 1) return 2;
        
        // Assign Rank 1
        for (int i = a.length - 1; i > -1; i--) {
            if (a[i] > 0) {
                tie = i;
                break;
            }
        }
        return 1;
    }
    
    public int compareTo(Hand that) {
        if (this.rank < that.rank) return -1;
        else if (this.rank > that.rank) return 1;
        else {
            if (this.tie < that.tie) return -1;
            else if (this.tie > that.tie) return 1;
        }
        return 0;
    }
    
    public void add(Card c) {
        if (len == 5) throw new ArrayIndexOutOfBoundsException("Hand is full");
        else h[len++] = c;
    }

    @Override
    public Iterator<Card> iterator() {
        return new HandIterator();
    }
    
    private class HandIterator implements Iterator<Card> {
        private int lenCopy;
        private final Card[] copy;
        
        private HandIterator() {
            lenCopy = len;
            copy = new Card[len];
            for (int i = 0; i < len; i++) {
                copy[i] = h[i];
            }
        }
        
        @Override
        public boolean hasNext() {
            return lenCopy > 0;
        }

        @Override
        public Card next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            return copy[--lenCopy];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
    }
    
    
}
