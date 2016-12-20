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
 *
 * @author Michael <GrubenM@GMail.com>
 */
public class Hand implements Iterable<Card> {
    Card[] h;
    int len;
    int rank;
    
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
        
        setRank();
    }
    
    private void setRank() {
        // 10. Royal Flush
        
        // 9. Straight Flush
        
        // 8. 4-of-a-kind
        
        // 7. Full House (ahhh ahhh ahh ahh ahhhhh....)
        
        // 6. Flush
        
        // 5. Straight
        
        // 4. 3-of-a-kind
        
        // 3. 2 pairs
        
        // 2. single pair
        
        // Assign Rank 1
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
