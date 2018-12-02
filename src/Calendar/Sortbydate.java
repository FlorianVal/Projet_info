/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

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
        
        public static void btween2d ( List<RendezVous> rdv ) {
            System.out.println("Choisir une date, pour afficher votre liste de rdv entre aujourdhui et cette date");
            Scanner scan = new Scanner(System.in);
            String date_limit = scan.next();
            LocalDate limit_localdate = LocalDate.parse(date_limit);
            String h_limit;
            h_limit = "23:59";
            long limit_TimeSTAMP = RendezVous.date_to_timestamp(limit_localdate , LocalTime.parse(h_limit)); 
            
                for (int i =0; i < rdv.size(); i++)
                {
                        if (rdv.get(i).getTimeSTAMP() < limit_TimeSTAMP )
                        {
                             System.out.println(rdv.get(i).toString());
                        }
                }         
        }
    }
    
        
