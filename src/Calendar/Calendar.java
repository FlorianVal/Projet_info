/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;

import static Calendar.Sortbydate.Filtre;
import static Calendar.Sortbydate.TrieRDV;
import static Calendar.Sortbydate.btween2d;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Classe de gestion du calendrier avec les differents agenda et rendez vous qui y sont contenus.
 * @author florianvalade
 */
public class Calendar {

    /**
     * @param args the command line arguments
     */
   static Agenda setAgenda;

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //rdv init
        
        //RendezVous rdv1 = new RendezVous("2018-12-18","17:00","17:30");
        //RendezVous rdv2 = new RendezVous("2018-12-17","17:00","17:30");
        //RendezVous rdv3 = new RendezVous("2018-12-16","17:00","17:30");
        
        //TODO find agenda on disk
        // list rdv init
        //ArrayList<RendezVous> rdvs = new ArrayList<>();
        //rdvs.add(rdv1);
        //rdvs.add(rdv2);    
        //rdvs.add(rdv3);
        //rdvs.get(0).setLabel("Le deuxième :)");
        
        

        //init agenda list
        ArrayList<Agenda> agendas = new ArrayList<>();
        agendas = Agenda.get_save();
        System.out.println(agendas.size());
        //agendas.add(new Agenda("Florian",rdvs));

        //menu_agenda(agendas);
        Agenda agend;
        Affichage window = new Affichage(agendas) ;
        agend = window.Menu_select_agenda();
        window.Traiter_Agenda(agend);
    }

    /**
     *
     * @param agendas
     * @throws IOException
     */
    public static void menu_agenda(ArrayList<Agenda> agendas) throws IOException{      
        Scanner scan = new Scanner(System.in);
        int choix = 0;
        int status = 0;
        while (choix == 0){
            if (status != 0){
                // exiting loop if menu_rdv ask it
                System.out.println("exiting agenda");
                choix = 0;
                status = 0;
                continue;
            }
            System.out.println("Menu :\n 1-Créer un Agenda\n 2- Ouvrir un Agenda\n 3- quitter");
            choix = scan.nextInt();
            ArrayList<RendezVous> rdvs = new ArrayList<>();

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
                            // if the name of agenda is the right one
                            if(agendas.get(i).getUsername().equals(name_agenda)){
                                System.out.println("agenda found with rdvs :");
                                //list rdvs of agenda
                                rdvs = get_print_rdvs(agendas.get(i));
                                agenda_number = i;
                                
                                setAgenda = agendas.get(i);
                                break; 
                        }
                        }   
                        //temporary then go in rdv menu
                        if (agenda_number != -1){
                        //rdvs = Affichage.Menu_agenda(agendas);
                        status = menu_rdv(rdvs ,scan);//}
                        choix = 0;
                        break;
                        }
                    
                    case 3:
                        System.out.print("exiting\n");
                        Agenda.save_agenda(agendas);
                        break;
                    default:
                        choix = 0;
                        System.out.println("Veuillez choisir une option 1, 2 or 3");
                        break;
            
            }
        }
    }
    
    /**
     *
     * @param rdv
     * @param scan
     * @return
     */
    public static int menu_rdv(ArrayList<RendezVous> rdv, Scanner scan){
        int status = -1;
        int choix_rdv = 0;        
        while(status == -1){
            System.out.println("Menu :\n 1-Créer un rdv\n 2- editer un rdv\n 3- Supprimer un RDV \n 4-  Actualiser la liste de rendez-vous\n 5- Trier  la liste de RDV par date\n 6- Afficher les rdv jusqu'a la date de votre choix  \n 7- Filtrer selon un attribut  \n 8- quitter");
            choix_rdv = scan.nextInt();
            switch(choix_rdv){
                case 1: /* Créeation d'un nouveau rendez vous */ 
                    String date;
                    String h_start;
                    String h_end;
                    RendezVous new_rdv;
                    
                    System.out.println("Entrez la date :");
                    date = scan.next();     
                    System.out.println("Entrez l'heure de début");
                    h_start = scan.next();
                    System.out.println("Entrez l'heure de fin");
                    h_end = scan.next();
                    new_rdv = new RendezVous(date,h_start,h_end);
                    //check if there is another rdv on same time
                    if(!new_rdv.check_avaibility(rdv)){
                        System.out.println("Un rendez vous est sur ce créneau !");
                    }else{ 
                        rdv.add(new_rdv);
                    }
                    break;
               

                case 2: /* Edition d'un rendez vous */ 
                    int rdv_to_edit;
                    System.out.println("Quel rdv voulez vous changer ?");
                    for (int i =0; i < rdv.size(); i++){
                        System.out.print(i + 1);
                        System.out.print(" - ");
                        System.out.println(rdv.get(i).toString());
                    }
                    rdv_to_edit =  scan.nextInt();
                    rdv_to_edit -= 1;
                    if(rdv_to_edit < rdv.size() && rdv_to_edit >=0){
                        System.out.print("changement sur : ");
                        System.out.println(rdv.get(rdv_to_edit));
                        edit_rdv(rdv.get(rdv_to_edit),scan);
                    }
                    break;
                    
                case 3: /* Supression d'un rendez vous */   
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
                    
                case 4: /* Acctualisation d'un rendez vous */ 
                    get_print_rdvs(setAgenda);
               
                    break; 
                    
                case 5: /* Trie de la liste de rendez vous par date*/
                    System.out.println("Trie de la liste de RDV par date ...\n");
                    TrieRDV(rdv);                   
                
                    break; 
                    
                case 6 : /* RDv entre 2 date */ 
                    btween2d(rdv); 
                    break;
                
                case 7 : // Filtre
                    Filtre (rdv); 
                    break; 
                    
                case 8 : 
                    status = 1;
                    break;
            }
        }
        return status;
    }
    
    /**
     *
     * @param rdv
     * @param scan
     */
    public static void edit_rdv(RendezVous rdv, Scanner scan){
    //menu to choose what to changes
        int choice;
        String new_date;
        LocalTime new_localtime;
        System.out.println("Que changez ?");
        System.out.println("1- Date \n2- Heure de début\n3- Heure de fin\n4- label");
        choice = scan.nextInt();
        switch(choice){
            case 1:
                System.out.println("Nouvelle date :");
                new_date = scan.next();
                LocalDate new_localdate = LocalDate.parse(new_date);
                rdv.setDate(new_localdate);
                break;
            case 2:
                System.out.println("Nouvelle Heure de début :");
                new_date = scan.next();
                new_localtime = LocalTime.parse(new_date);
                rdv.setH_start(new_localtime);
                break;
            case 3:
                System.out.println("Nouvelle Heure de fin :");
                new_date = scan.next();
                new_localtime = LocalTime.parse(new_date);
                rdv.setH_end(new_localtime);
                break;
            case 4:
                System.out.println("Nouveau label :");
                new_date = scan.next();
                rdv.setLabel(new_date);
                break;
            default:
                System.out.println("not understood");
                break;
        }       

        
    
    
    }
    
    /**
     *
     * @param agenda
     * @return
     */
    public static ArrayList<RendezVous> get_print_rdvs(Agenda agenda){
        ArrayList<RendezVous> rdv_agenda;
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
