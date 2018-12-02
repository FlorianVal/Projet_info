/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author florianvalade
 */
public class Affichage extends JFrame{
    private int width;
    private int height;
    JPanel pan = new JPanel();
    JButton bouton_add = new JButton("Add RDV");
    JButton bouton_edit = new JButton("edit RDV");
    JButton bouton_delete = new JButton("delete RDV");


    public Affichage() {
        this.width = 800;
        this.height = 600;
        this.setTitle("Agenda");
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
        this.pan.setBackground(Color.ORANGE); 
        this.pan.add(bouton_add);
        this.pan.add(bouton_edit);
        this.pan.add(bouton_delete);
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
        this.pan.setBackground(Color.red);
        //creer fenetre avec boutton et liste des rdvs
    }
}
