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
        this.rdv = new ArrayList<RendezVous>();
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
    
    public static RendezVous[] getRdvList(List<RendezVous> rdv_array){
        RendezVous rdvs[] = new RendezVous[rdv_array.size()];
        for(int i=0; i<rdv_array.size(); i++)
            rdvs[i] = rdv_array.get(i);
        return rdvs;
    }
    public static boolean save_agenda(Agenda agend) throws IOException{
    ArrayList<Agenda> agendas = new ArrayList<>();
        try {
            System.out.println("adding to already save agendas");
            agendas = Agenda.get_save();
            if(agendas.size()== 0){
                agendas.add(agend);}
            else{
            for(int i = 0;i <agendas.size();i++){
                if(agendas.get(i).getUsername().equals(agend.getUsername())){
                    agendas.set(i, agend);
                }
            }}
        } catch (IOException ex) {
            System.out.println("no agenda previously saved");
            return false;

        }
    
        Agenda.save_agenda(agendas);
        return true;
    
    }
    
    public static void save_agenda(ArrayList<Agenda> agend) throws IOException {
        File file = new File("agendas_save.txt");
        file.delete();
        FileOutputStream fout=new FileOutputStream("agendas_save.txt");  
        ObjectOutputStream out=new ObjectOutputStream(fout);  
        out.writeObject(agend);
        System.out.print("Agenda save :");
        System.out.println(agend.size());
        out.flush();  
    }
    
    public static ArrayList<Agenda> get_save() throws IOException {
        ArrayList<Agenda> agendas;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("agendas_save.txt"));
            try {
                agendas = (ArrayList<Agenda>)in.readObject();
            } catch (ClassNotFoundException ex) {
                
                Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
                return null;
                
            } 
        }
        catch(java.io.FileNotFoundException e){
            System.out.println("No agenda in memory");
            agendas = new ArrayList<>();
        }
        
        return agendas;

    }
}
