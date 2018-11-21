/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;
import java.util.Date;
/**
 *
 * @author florianvalade
 */
public class RendezVous {
    private String date;
    private String h_start;
    private String h_end;
    private String label;
    private boolean reminder;
    
    public RendezVous(String date, String h_start, String h_end) {
        // check if date is valid
        CheckDate(date);
        System.out.print("rdv created !\n");
        this.date = date;
        this.h_start = h_start;
        this.h_end = h_end;
    }
    
    static public boolean CheckDate(String date){
        if (date.split("/")[0] != date && date.length() == 10){
            Date date_now = new Date();
            try{
            int day = Integer.parseInt(date.split("/")[0]);
            int month = Integer.parseInt(date.split("/")[1]) - 1;
            int year = Integer.parseInt(date.split("/")[2]) - 1900;
            Date date_given = new Date(year, month, day);
            if (date_now.after(date_given) == true){            
                System.out.print("Date given is before today's date\n");
                return false;
                }
            else {
                return true;
            }}
            catch(java.lang.NumberFormatException e){
                System.out.println("Invalid input");
                return false;
                }}
        else{
            System.out.print("Problem creating RendezVous please use date format jj/mm/aaaa\n");
            return false;
        }
    }
    
    
    public RendezVous(String date, String h_start, String h_end, boolean reminder) {
        this(date, h_start, h_end);
        this.reminder = reminder;
    }

    public RendezVous(String date, String h_start, String h_end, String label, boolean reminder) {
        this(date, h_start, h_end, reminder);
        this.label = label;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if(CheckDate(date)){
            this.date = date;}
    }

    public String getH_start() {
        return h_start;
    }

    public void setH_start(String h_start) {
        this.h_start = h_start;
    }

    public String getH_end() {
        return h_end;
    }

    public void setH_end(String h_end) {
        this.h_end = h_end;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "date=" + date + ", h_start=" + h_start + ", h_end=" + h_end + ", label=" + label + ", reminder=" + reminder ;
    }
    
    


}
