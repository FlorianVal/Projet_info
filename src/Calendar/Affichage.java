/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author florianvalade
 */
public class Affichage extends JFrame{
    private int width;
    private int height;
    
    public static ArrayList<RendezVous> Menu_agenda(ArrayList<Agenda> agendas){
        // Transform array list tab
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
                return agendas.get(i).getRdv();
        }
        return null;
    }
    
}
