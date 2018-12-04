/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;
import java.io.Serializable;
import java.util.List;
import java.io.*;  
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author florianvalade
 */

public class Agenda implements Serializable{
    private String username;
    private ArrayList<RendezVous> rdv;

    public Agenda(String username) {
        this.username = username;
        this.rdv = new ArrayList<>();
    }

    public Agenda(String username, ArrayList<RendezVous> rdv) {
        this.username = username;
        this.rdv = rdv;
    }

    @Override
    public String toString() {
        return "Agenda{" + "username=" + username + ", rdv=" + rdv + '}';
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<RendezVous> getRdv() {
        return rdv;
    }
    
    public void add_rdv(RendezVous rdv){
        this.rdv.add(rdv);
    }
    
    public void remove_rdv(int index){
        this.rdv.remove(index);
    }
    
    public RendezVous[] getRdvList(){
        RendezVous rdvs[] = new RendezVous[this.rdv.size()];
        for(int i=0; i<this.rdv.size(); i++)
            rdvs[i] = this.rdv.get(i);
        return rdvs;
    }
    
    public static void save_agenda(ArrayList<Agenda> agend) throws IOException {
        File file = new File("agendas_save.txt");
        file.delete();
        FileOutputStream fout=new FileOutputStream("agendas_save.txt");  
        ObjectOutputStream out=new ObjectOutputStream(fout);  
        out.writeObject(agend);
        out.flush();  
        System.out.println("success");
    }
    
    public static ArrayList<Agenda> get_save() throws IOException {
        ObjectInputStream in=new ObjectInputStream(new FileInputStream("agendas_save.txt"));  
        ArrayList<Agenda> agendas;
        try {
            agendas = (ArrayList<Agenda>)in.readObject();
            System.out.println(agendas); 
        } catch (ClassNotFoundException ex) {
            
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);    
            return null;

        }

        in.close(); 
        return agendas;

    }
}
