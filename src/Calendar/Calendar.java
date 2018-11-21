/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;

import java.util.*;
import java.util.Arrays;
/**
 *
 * @author florianvalade
 */
public class Calendar {

    /**
     * @param args the command line arguments
     */
   static Agenda setAgenda;
    public static void main(String[] args) {
        //rdv init
        
        RendezVous rdv1 = new RendezVous("17/12/2018","17:00","17:30");
        RendezVous rdv2 = new RendezVous("17/12/2018","17:00","17:30");
        //TODO find agenda on disk
        // list rdv init
        List<RendezVous> rdvs = new ArrayList<>();
        rdvs.add(rdv1);
        rdvs.add(rdv2);    
        rdvs.get(0).setLabel("Le deuxième :)");

        //init agenda list
        List<Agenda> agendas = new ArrayList<>();
        agendas.add(new Agenda("Florian",rdvs));
        //menu
        menu_agenda(agendas);
        
    }
    public static void menu_agenda(List<Agenda> agendas){      
        Scanner scan = new Scanner(System.in);
        int choix = 0;
        int status = 0;
        while (choix == 0){
            if (status != 0){
                // exiting loop if menu_rdv ask it
                System.out.println("exiting by status");
                choix = -1;
                continue;
            }
            System.out.println("Menu :\n 1-Créer un Agenda\n 2- Ouvrir un Agenda\n 3- quitter");
            choix = scan.nextInt();
            switch(choix){
                    case 1:
                        //add new agenda in agenda list
                        System.out.println("Rentrez votre nom :");
                        String name = scan.next();  
                        
                        //create new agenda with name of user
                        Agenda new_agenda; 
                        new_agenda = new Agenda(name);
                        
                        //add to list
                        agendas.add(new_agenda);
                        
                        choix = 0;
                        break;
                    case 2:
                        // print all agenda in memory  
                        System.out.println("Users found :");
                        int agenda_number = -1;
                        for (int i =0; i<agendas.size(); i++){
                            System.out.println(agendas.get(i).getUsername());}
                        System.out.println("\nRentrez votre nom :");
                        String name_agenda = scan.next();
                        //search in agenda list the right one
                        for (int i =0; i<agendas.size(); i++){
                            //if the name of agenda is the right one
                            if(agendas.get(i).getUsername().equals(name_agenda)){
                                System.out.println("agenda found with rdvs :");
                                //list rdvs of agenda
                                get_print_rdvs(agendas.get(i));
                                agenda_number = i;
                                
                                setAgenda = agendas.get(i);
                                break; 
                        }}
                        //temporary then go in rdv menu
                        if (agenda_number != -1){
                            status = menu_rdv(agendas.get(agenda_number).getRdv(),scan);}
                        choix = 0;
                        break;
                    
                    case 3:
                        System.out.print("exiting\n");
                        break;
                    default:
                        choix = 0;
                        System.out.println("Veuillez choisir une option 1, 2 or 3");
                        break;
            
            }
        }
    }
    
    public static int menu_rdv(List<RendezVous> rdv, Scanner scan){
        int status = -1;
        int choix_rdv = 0;        
        while(status == -1){
            System.out.println("Menu :\n 1-Créer un rdv\n 2- editer un rdv\n 3- Supprimer un RDV \n 4-  Actualiser la liste de rendez-vous\n 5- quitter");
            choix_rdv = scan.nextInt();
            switch(choix_rdv){
                case 1:
                    String date;
                    String h_start;
                    String h_end;
                    RendezVous new_rdv;
                    
                    System.out.println("Entrez la date :");
                    date = scan.next();     
                    while(RendezVous.CheckDate(date) != true){
                        System.out.println("Entrez invalide");
                        date=scan.next();
                    }

                    System.out.println("Entrez l'heure de début");
                    h_start = scan.next();
                    System.out.println("Entrez l'heure de fin");
                    h_end = scan.next();
                    new_rdv = new RendezVous(date,h_start,h_end);
                    rdv.add(new_rdv);
                    break;
               

                case 2: 
                    int rdv_to_edit;
                    System.out.println("Quel rdv voulez vous changer ?");
                    for (int i =0; i < rdv.size(); i++){
                        System.out.print(i + 1);
                        System.out.print(" - ");
                        System.out.println(rdv.get(i).toString());
                    }
                    rdv_to_edit =  scan.nextInt();
                    rdv_to_edit +=1;
                    if(rdv_to_edit < rdv.size() && rdv_to_edit >0){
                        System.out.print("changement sur : ");
                        System.out.println(rdv.get(rdv_to_edit));
                        edit_rdv(rdv.get(rdv_to_edit),scan);
                    }
                    break;
                    
                case 3: 
                    int rdv_to_delete; 
                    System.out.println("Quel rdv voulez vous supprimer");
                     for (int i =0; i < rdv.size(); i++){
                        System.out.print(i + 1);
                        System.out.print(" - ");
                        System.out.println(rdv.get(i).toString());
                    }
                    rdv_to_delete  = scan.nextInt(); 
                    rdv_to_delete -= 1; 
                    rdv.remove(rdv_to_delete);
                    System.out.println("Acctualisation de la liste de RDV...");
                      for (int i =0; i < rdv.size(); i++){
                        System.out.print(i + 1);
                        System.out.print(" - ");
                        System.out.println(rdv.get(i).toString());
                    }
                    break; 
                    
                case 4:
                    System.out.println("Acctualisation de la liste de RDV ...\n");
                    get_print_rdvs(setAgenda); 
                    break; 
                    
                case 5:
                    status = 1;
                    break;
            }
        }
        return status;
    }
    
    public static void edit_rdv(RendezVous rdv, Scanner scan){
    //menu to choose what to changes
        int choice;
        String new_date;
        System.out.println("Que changez ?");
        System.out.println("1- Date \n2- Heure de début\n3- Heure de fin\n4- label\n5- reminder");
        choice = scan.nextInt();
        switch(choice){
            case 1:
                System.out.println("Nouvelle date :");
                new_date = scan.next();
                rdv.setDate(new_date);

        }

        
    
    
    }
    
    public static List<RendezVous> get_print_rdvs(Agenda agenda){
        List<RendezVous> rdv_agenda;
        rdv_agenda = agenda.getRdv();
        
        // if rdvs exist print them and return them
        if(rdv_agenda != null){
        for(int j = 0; j<rdv_agenda.size(); j++){
            System.out.println(rdv_agenda.get(j).toString());
        }}
        else{System.out.println("No rdv found");}
        return rdv_agenda;
    }
}
