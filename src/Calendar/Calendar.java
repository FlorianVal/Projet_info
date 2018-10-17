/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;

import java.util.*;

/**
 *
 * @author florianvalade
 */
public class Calendar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //test add rdv
        RendezVous rdv1 = new RendezVous("17/03/2018","17:00","17:30");
        System.out.print(rdv1.toString());
        //TODO find agenda on disk
        Agenda[] agenda;
        agenda = new Agenda[10];
        agenda[0] = new Agenda("Florian");
        //menu
        menu_agenda(agenda);
    }
    public static void menu_agenda(Agenda[] agenda){      
        Scanner scan = new Scanner(System.in);
        int choix = 0;
        while (choix == 0){
            System.out.println("Menu :\n 1-Créer un Agenda\n 2- Ouvrir un Agenda");
            choix = scan.nextInt();
            switch(choix){
                    case 1:
                        System.out.println("Rentrez votre nom :");
                        String nom = scan.next();
                        break;
                    case 2:
                        for (int i =0; i<10; i++){
                            if(agenda[i] != null){
                                System.out.print(agenda[i].toString());}
                        }
                        break;
                    default:
                        choix = 0;
                        System.out.println("please choose between 1 and 2");
                        break;
            
            }
        }
    }
}
