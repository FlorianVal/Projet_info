/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Calendar;
import static Calendar.Sortbydate.TrieRDV;
import static Calendar.Sortbydate.btween2d;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author florianvalade
 */

public class Affichage extends JFrame implements ActionListener, ListSelectionListener, WindowListener{
    private int width;
    private int height;
    ArrayList<Agenda> agendas;
    Agenda agenda;
    JPanel pan = new JPanel();
    JButton bouton_add ;
    JButton bouton_edit ;
    JButton bouton_delete ;
    JButton bouton_tri;
    JList list;

    /**
     *
     * @param agendas
     */
    public Affichage(ArrayList<Agenda> agendas) {
        this.agendas = agendas;
        this.bouton_tri = new JButton("Chercher les rdvs entre 2 dates");
        this.bouton_add = new JButton("Add RDV");
        this.bouton_edit = new JButton("edit RDV");
        this.bouton_delete = new JButton("delete RDV");     
        this.list = new JList();
        bouton_add.addActionListener(this);
        bouton_tri.addActionListener(this);
        bouton_edit.addActionListener(this);
        bouton_delete.addActionListener(this);
        bouton_add.setActionCommand("add");
        bouton_tri.setActionCommand("tri");
        bouton_edit.setActionCommand("edit");
        bouton_delete.setActionCommand("delete");
        this.width = 800;
        this.height = 600;
        this.setTitle("Agenda");
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        this.addWindowListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            
        this.setContentPane(pan);
        this.setVisible(true);
    }
    
    /**
     *
     * @return
     */
    public Agenda Create_agenda(){
        JOptionPane jop = new JOptionPane();        
        String name = (String)jop.showInputDialog(this.pan,
                        "Entrez votre nom:", null);
        Agenda agend;
        if(name != null){
        agend = new Agenda(name);
        }
        else{
            agend = null;
        }
        return agend;
    }
    
    /**
     *
     * @return
     */
    public Agenda Menu_select_agenda(){
        String nom = null;   
        Agenda new_agenda = null;

        while(nom == null || nom.equals("Creer un nouvel agenda")){
        while(this.agendas.size() == 0){
            //create new agenda
            new_agenda = this.Create_agenda();
            if(new_agenda != null){
                this.agendas.add(new_agenda);}
            System.out.println(this.agendas.size());
        }
        // Transform array list to tab
        String[] agend = new String[this.agendas.size() +1];
        for(int i = 0; i<this.agendas.size(); i++){
            agend[i] = this.agendas.get(i).getUsername();
        }
        agend[this.agendas.size()] = "Creer un nouvel agenda";
        // show menu swing
        JOptionPane jop = new JOptionPane();
        nom = (String)jop.showInputDialog(null, 
            "Qui êtes vous ?",
            "Sélectionnez :",
            JOptionPane.QUESTION_MESSAGE,
            null,
            agend,
            agend[0]);
        // get back to array index
        if(nom == null){
            System.exit(0);
        }
        if(nom.equals("Creer un nouvel agenda")){
            Agenda new_agend = this.Create_agenda();
            this.agendas.add(new_agend);
        }}
        for (int i = 0; i<this.agendas.size(); i++) {
            if(this.agendas.get(i).getUsername() == nom)
                return this.agendas.get(i);
        }
        return null;
    }
    
    /**
     * Classe de gestion de l'interface graphique 
     * @param agenda
     */
    public void Traiter_Agenda(Agenda agenda){
        this.pan.removeAll();
        this.pan.add(bouton_add);
        this.pan.add(bouton_edit);
        this.pan.add(bouton_delete);
        this.pan.add(bouton_tri);
        this.agenda = agenda;
        //check reminder
        for(int i = 0; i<agenda.getRdv().size(); i ++){
            if(agenda.getRdv().get(i).isReminder()){
                if(LocalDate.now().equals(agenda.getRdv().get(i).getDate())){
                    // check if rdv is in 15 min or less
                    if(LocalTime.now().isBefore(agenda.getRdv().get(i).getH_start()) && LocalTime.now().plusMinutes(15).isAfter(agenda.getRdv().get(i).getH_start())){
                        JOptionPane.showMessageDialog(this.pan, "Rappel :"+agenda.getRdv().get(i).toString());
                    }
                    // TODO verif date 
                }
            }
        }
        List<RendezVous> rdvs = TrieRDV(this.agenda.getRdv());
        RendezVous[] rdv_tab = Agenda.getRdvList(rdvs);
        this.list = new JList(rdv_tab);
        this.list.addListSelectionListener(this);
        this.list.setAutoscrolls(true);
        this.pan.setBackground(Color.red);
        this.pan.add(this.list);
        this.setContentPane(pan);
        //creer fenetre avec boutton et liste des rdvs
    }
    
    /**
     *
     */
    public void Popup_rdv(){
        this.agenda.add_rdv(this.Popup_rdv(null,null,null,null));
    }
    
    /**
     *
     * @param date
     * @param Hstart
     * @param Hend
     * @param label
     * @return
     */
    public RendezVous Popup_rdv(String date, String Hstart, String Hend, String label){
        //do popup to create rdv
        JOptionPane jop = new JOptionPane();        
        String new_date = (String)jop.showInputDialog(this.pan,
                        "Date: aaaa-mm-jj", date);
        String new_Hstart = (String)jop.showInputDialog(this.pan,
                        "Heure de début: hh:mm ", Hstart);
        String new_Hend = (String)jop.showInputDialog(this.pan,
                        "Heure de fin: hh:mm", Hend);
        String new_label = (String)jop.showInputDialog(this.pan,
                        "Label:", label);
        int n = JOptionPane.showConfirmDialog(
            this.pan,
            "rappel ?",
            null,
            JOptionPane.YES_NO_OPTION); 
        boolean new_reminder = false;
        if(n == 0){
            new_reminder = true;
        }
    RendezVous rdv = new RendezVous(new_date,new_Hstart,new_Hend,new_label,new_reminder);
        return rdv;
    }
    
    /**
     *
     */
    public void DeleteRdv(){
        int index = this.list.getSelectedIndex();
        this.agenda.remove_rdv(index);
        this.Traiter_Agenda(this.agenda);
    }

    /**
     *
     */
    public void edit_rdv(){
        int index = this.list.getSelectedIndex();
        RendezVous rdv = this.agenda.getRdv().get(index);
        rdv = this.Popup_rdv(rdv.getDate().toString(), rdv.getH_start().toString(), rdv.getH_end().toString(), rdv.getLabel());
        this.agenda.getRdv().set(index, rdv);
    }
    
    public void tri_rdv(){
        RendezVous[] filtered_rdv = new RendezVous[this.agenda.getRdv().size()];
        int count = 0;
        JOptionPane jop = new JOptionPane();        
        String date1 = (String)jop.showInputDialog(this.pan,
                        "Première date: aaaa-mm-jj", null);
        String date2 = (String)jop.showInputDialog(this.pan,
                        "Seconde date: aaaa-mm-jj", null);
        for(int i =0;i< this.agenda.getRdv().size(); i++){
            if(LocalDate.parse(date1).isBefore(this.agenda.getRdv().get(i).getDate()) && LocalDate.parse(date2).isAfter(this.agenda.getRdv().get(i).getDate())){
                filtered_rdv[count] = this.agenda.getRdv().get(i);
                count =+1;
            }
        }
        String rdvs = "";
        for(int i =0;i<filtered_rdv.length;i++){
            rdvs += filtered_rdv[i].toString();
            rdvs += "\n";}
        JOptionPane.showMessageDialog(this.pan, "rdv trouvé :"+ rdvs);

    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        RendezVous rdv;
        switch (e.getActionCommand()){
            case "add":
                System.out.println("Add !!!!");
                this.Popup_rdv();
                break;
            case "delete":
                System.out.println("delete");
                this.DeleteRdv();
                break;
            case "edit":
                System.out.println("edit");
                this.edit_rdv();
            case "tri":
                System.out.println("TRI !");
                this.tri_rdv();
                
                
                
        }
        this.Traiter_Agenda(this.agenda);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println(this.list.getSelectedIndex());
    }

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("open window");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Saving...");
        System.out.println(this.agenda.getUsername());
        try {
            Agenda.save_agenda(this.agendas);
        } catch (IOException ex) {
            Logger.getLogger(Affichage.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Done");
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("is closed");
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
