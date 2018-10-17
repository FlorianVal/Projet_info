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
        //menu
        menu_agenda();
    }
    public static void menu_agenda(){      
        Scanner scan = new Scanner(System.in);
        int choix = 0;
        while (choix == 0){
            System.out.println("Menu :\n 1-Créer un Agenda\n 2- Ouvrir un Agenda");
            choix = scan.nextInt();
            if(choix != 1 && choix != 2 ){
                choix = 0;
                System.out.println("please choose between 1 and 2");
            }
            if(choix == 1){
                System.out.println("Rentrez votre nom :");
                String nom = scan.next();
                Agenda agenda = new Agenda(nom);

            }
        }
    }
}
