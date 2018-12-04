/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author florianvalade
 */
public class Affichage extends JFrame implements ActionListener, ListSelectionListener{
    private int width;
    private int height;
    Agenda agenda;
    JPanel pan = new JPanel(new GridLayout(2,0));
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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            
        this.setContentPane(pan);
        this.setVisible(true);


    }
    
    
    public static Agenda Menu_select_agenda(ArrayList<Agenda> agendas){
        // Transform array list to tab
        String[] agend = new String[agendas.size()];
        for(int i = 0; i<agendas.size(); i++){
            agend[i] = agendas.get(i).getUsername();
        }
        // show menu swing
        JOptionPane jop = new JOptionPane();
        String nom = (String)jop.showInputDialog(null, 
            "Qui êtes vous ?",
            "Sélectionnez :",
            JOptionPane.QUESTION_MESSAGE,
            null,
            agend,
            agend[0]);
        // get back to array index
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
        this.list = new JList(agenda.getRdvList());
        this.list.addListSelectionListener(this);
        this.list.setAutoscrolls(true);
        this.pan.setBackground(Color.red);
        this.pan.add(this.list);
        this.setContentPane(pan);
        //creer fenetre avec boutton et liste des rdvs
    }
    
    public RendezVous Popup_add_rdv(){
        RendezVous rdv = new RendezVous("2018-12-18","17:00","17:30");
        //do popup to create rdv
        return rdv;
    }
    
    public void DeleteRdv(){
        int index = this.list.getSelectedIndex();
        this.agenda.remove_rdv(index);
        this.Traiter_Agenda(this.agenda);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        RendezVous rdv;
        switch (e.getActionCommand()){
            case "add":
                System.out.println("Add !!!!");
                rdv = Popup_add_rdv();
                this.agenda.add_rdv(rdv);
                break;
            case "delete":
                System.out.println("delete");
                DeleteRdv();
                break;
                
        }
        this.Traiter_Agenda(this.agenda);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println(this.list.getSelectedIndex());
    }
}
