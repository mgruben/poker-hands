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

import java.util.Comparator;

/**
 * A Card object, representing a single card in a poker hand.
 * @author Michael <GrubenM@GMail.com>
 */
public class Card {
    
    // The integer representation of the value of the card
    private int val;
    
    // The suit of the card
    private char suit;
    
    /**
     * Given a String of length 2, parses the String to create a Card
     * matching the value and suit from the String.
     * 
     * ex. Card("4H") -> a new Card, value 4, suit Hearts
     * 
     * @param c, the length-2 String to parse
     */
    public Card(String c) {
        
        // Obtain the value
        val = parseVal(c.charAt(0));
        
        // Check for invalid suit
        if (c.charAt(1) != 'S' && c.charAt(1) != 'D' &&
                c.charAt(1) != 'C' && c.charAt(1) != 'H') {
            throw new IllegalArgumentException("Invalid suit");
        }
        
        // Obtain the suit
        suit = c.charAt(1);
    }
    
    /**
     * Given the single character representing the value of the card,
     * returns the value of the card.
     * 
     * @param c, the character representing the card's value
     * @return an integer between 2 and 14, inclusive
     */
    private int parseVal(char c) {
        if (c >= '2' && c <= '9') return Character.getNumericValue(c);
        
        /**
         * Since we want to know about cards being sequential, we adopt
         * the convention that Jack, Queen, King, and Ace simply continue
         * the integer values from 10 upwards, respectively.
         * 
         * Also, in `poker.txt`, the value 10 is represented with a 'T'.
         */
        else if (c == 'T') return 10;
        else if (c == 'J') return 11;
        else if (c == 'Q') return 12;
        else if (c == 'K') return 13;
        else if (c == 'A') return 14;
        
        // If we're here, `c` isn't a valid suit
        else throw new IllegalArgumentException("Invalid suit: " + c);
    }
    
    /**
     * @return the value of the card
     */
    public int getVal() { return val; }
    
    /**
     * @return the suit of the card
     */
    public char getSuit() { return suit; }
}
