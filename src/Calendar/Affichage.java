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
    Agenda agenda;
    JPanel pan = new JPanel();
    JButton bouton_add ;
    JButton bouton_edit ;
    JButton bouton_delete ;
    JList list;



    public Affichage() {
        this.bouton_add = new JButton("Add RDV");
        this.bouton_edit = new JButton("edit RDV");
        this.bouton_delete = new JButton("delete RDV");     
        this.list = new JList();
        bouton_add.addActionListener(this);
        bouton_edit.addActionListener(this);
        bouton_delete.addActionListener(this);
        bouton_add.setActionCommand("add");
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
    
    public Agenda Menu_select_agenda(ArrayList<Agenda> agendas){
        String nom = null;   
        Agenda new_agenda = null;

        while(nom == null || nom.equals("Creer un nouvel agenda")){
        while(agendas.size() == 0){
            //create new agenda
            new_agenda = this.Create_agenda();
            if(new_agenda != null){
                agendas.add(new_agenda);}
            System.out.println(agendas.size());
        }
        // Transform array list to tab
        String[] agend = new String[agendas.size() +1];
        for(int i = 0; i<agendas.size(); i++){
            agend[i] = agendas.get(i).getUsername();
        }
        agend[agendas.size()] = "Creer un nouvel agenda";
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
            agendas.add(new_agend);
        }}
        for (int i = 0; i<agendas.size(); i++) {
            if(agendas.get(i).getUsername() == nom)
                return agendas.get(i);
        }
        return null;
    }
    
    public void Traiter_Agenda(Agenda agenda){
        this.pan.removeAll();
        this.pan.setBackground(Color.ORANGE); 
        this.pan.add(bouton_add);
        this.pan.add(bouton_edit);
        this.pan.add(bouton_delete);
        this.agenda = agenda;
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
    
    public void Popup_rdv(){
        this.agenda.add_rdv(this.Popup_rdv(null,null,null));
    }
    
    public RendezVous Popup_rdv(String date, String Hstart, String Hend){
        //do popup to create rdv
        JOptionPane jop = new JOptionPane();        
        String new_date = (String)jop.showInputDialog(this.pan,
                        "Date:", date);
        String new_Hstart = (String)jop.showInputDialog(this.pan,
                        "Heure de début:", Hstart);
        String new_Hend = (String)jop.showInputDialog(this.pan,
                        "Heure de fin:", Hend);
        RendezVous rdv = new RendezVous(new_date,new_Hstart,new_Hend);
        return rdv;
    }
    
    public void DeleteRdv(){
        int index = this.list.getSelectedIndex();
        this.agenda.remove_rdv(index);
        this.Traiter_Agenda(this.agenda);
    }
    public void edit_rdv(){
        int index = this.list.getSelectedIndex();
        RendezVous rdv = this.agenda.getRdv().get(index);
        rdv = this.Popup_rdv(rdv.getDate().toString(), rdv.getH_start().toString(), rdv.getH_end().toString());
        this.agenda.getRdv().set(index, rdv);
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
            Agenda.save_agenda(this.agenda);
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
