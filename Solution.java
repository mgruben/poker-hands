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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael <GrubenM@GMail.com>
 */
public class Solution {
    public static int parseLine(String line) {        
        Hand p1 = new Hand(line.substring(0, 14));
        Hand p2 = new Hand(line.substring(15, line.length()));
        return p1.compareTo(p2);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File f = new File("poker.txt");
        Scanner sc;
        try {
            int c = 0;
            sc = new Scanner(f);
            while (sc.hasNext()) {
                if (Solution.parseLine(sc.nextLine()) > 0) c++;
            }
            System.out.println(c);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Solution.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
