/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author SESA515057
 */
class Sortbydate implements Comparator<RendezVous> {            
        
    
        public int compare(RendezVous a, RendezVous b)
             { 
        return (int)(a.getTimeSTAMP() - b.getTimeSTAMP()); 
       
             }
        
        public static void TrieRDV (List<RendezVous> rdv) {
             Collections.sort(rdv, new Sortbydate());
        
            System.out.println( "\nSorted by date");
            for ( int i=0; i<rdv.size(); i++)
            {
                System.out.println(rdv.get(i));
            }
        }
    }
    
        
