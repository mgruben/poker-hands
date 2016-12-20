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

import java.util.Arrays;

/**
 *
 * @author Michael <GrubenM@GMail.com>
 */
public class Solution {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String h1 = "5H 5C 6S 7S KD 2C 3S 8S 8D TD";
        
        Card[] a = new Card[10];
        int i = 0;
        for (String s: h1.split(" ")) {
            a[i++] = new Card(s);
        }
        Arrays.sort(a, Card.valueOrder());
        
        for (Card c: a) {
            System.out.print(c.getVal());
            System.out.println(c.getSuit());
        }
    }
    
}
