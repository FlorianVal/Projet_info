/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;

import java.time.Clock;
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

        public static List<RendezVous> TrieRDV (List<RendezVous> rdv) {
             Collections.sort(rdv, new Sortbydate());
        
            System.out.println( "\nSorted by date");
            for ( int i=0; i<rdv.size(); i++)
            {
                System.out.println(rdv.get(i));
            }
            return rdv;
          
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
        
        
            public static void Flitre(List<RendezVous> rdv) {   
            int choix;
            Scanner scan = new Scanner(System.in); 
            
                System.out.println("Filtrer les rendez vous selon :\n 1-Date \n 2-heur \n 3-Libelle ");            
                choix = scan.nextInt(); 
                
                switch(choix){
                    case 1:
                         //Filtre selon date 
                        System.out.println("Veuillew entrée une date sous le format yyyy-mm-dd");
                        String date; 
                        date = scan.next(); 
                        
                        for ( int i=0; i < rdv.size(); i++)
                            if ( rdv.get(i).getDate().equals(LocalDate.parse(date))){
                                System.out.println(rdv.get(i).toString());
                            }
                        
                 }
                 
            }        
    }
    
        
