/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;
import java.util.Date;
import java.io.Serializable;
import java.util.List;
import java.lang.*;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author florianvalade
 */

public class RendezVous implements Serializable {
    private String date;
    private String h_start;
    private String h_end;
    private String label;
    private boolean reminder;
    private long TimeSTAMP; 
    
    public RendezVous(String date, String h_start, String h_end) {
        // check if date is valid
        if(CheckDate(date)){
            System.out.print("rdv created !\n");
            this.date = date;
            this.h_start = h_start;
            this.h_end = h_end;
            this.TimeSTAMP = RendezVous.date_to_timestamp(date, h_start); 
        }
        else{
        System.out.println("Problème date");}
    }
    
        
    public static long date_to_timestamp(String date, String h_start) {
        try{
                      
            int day = Integer.parseInt(date.split("/")[0]);
            int month = Integer.parseInt(date.split("/")[1]) - 1;
            int year = Integer.parseInt(date.split("/")[2]) - 1900;
            int hour = Integer.parseInt(h_start.split(":")[0]);
            int minutes = Integer.parseInt(h_start.split(":")[1]);
            Date timestamp = new Date(year, month, day, hour, minutes);
            System.out.println(timestamp);
            return timestamp.getTime();

        }
        catch(java.lang.NumberFormatException e){
                System.out.println("Invalid input");
                return 0;
                }
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

    public boolean setDate(String date) {
        if(CheckDate(date)){
            this.date = date;
            return true;}
        else{return false;}
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

    public long getTimeSTAMP() {
        return TimeSTAMP;
    }
   
   

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "date=" + date + ", h_start=" + h_start + ", h_end=" + h_end + ", label=" + label + ", reminder=" + reminder  + ", times=" + TimeSTAMP    ;
    }

    
    
    


}
