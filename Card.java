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
 *
 * @author Michael <GrubenM@GMail.com>
 */
public class Card {
    private int val;
    private char suit;
    public Card(String c) {
        val = parseVal(c.charAt(0));
        if (c.charAt(1) != 'S' && c.charAt(1) != 'D' &&
                c.charAt(1) != 'C' && c.charAt(1) != 'H') {
            throw new IllegalArgumentException("Invalid suit");
        }
        suit = c.charAt(1);
    }

    private int parseVal(char c) {
        if (c >= '2' && c <= '9') return Character.getNumericValue(c);
        else if (c == 'T') return 10;
        else if (c == 'J') return 11;
        else if (c == 'Q') return 12;
        else if (c == 'K') return 13;
        else if (c == 'A') return 14;

        return -1;
    }
    
    public static Comparator<Card> valueOrder() {
        return new cardComparator();
    }
    public static class cardComparator implements Comparator<Card> {
        @Override
        public int compare(Card a, Card b) {
            if (a.val < b.val) return -1;
            else if (a.val > b.val) return 1;
            else return 0;
        }
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(val);
        sb.append(suit);
        return sb.toString();
    }

    public int getVal() { return val; }
    public char getSuit() { return suit; }
}
