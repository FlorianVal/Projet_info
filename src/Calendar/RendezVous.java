/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;
import java.util.Date;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.lang.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author florianvalade
 */

public class RendezVous implements Serializable {
    private LocalDate date;
    private LocalTime h_start;
    private LocalTime h_end;
    private String label;
    private boolean reminder;
    private long TimeSTAMP; 
    
    public RendezVous(String date, String h_start, String h_end) {
        this.date = LocalDate.parse(date);
        this.h_start = LocalTime.parse(h_start);
        this.h_end = LocalTime.parse(h_end);
        this.label = null;
        this.reminder = false;
        this.TimeSTAMP = RendezVous.date_to_timestamp(this.date, this.h_start); 
        System.out.print("rdv created !\n");

    }
 
    public RendezVous(String date, String h_start, String h_end, boolean reminder) {
        this(date, h_start, h_end);
        this.reminder = reminder;
    }

    public RendezVous(String date, String h_start, String h_end, String label, boolean reminder) {
        this(date, h_start, h_end, reminder);
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public long getTimeSTAMP() {
        return TimeSTAMP;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getH_start() {
        return h_start;
    }

    public LocalTime getH_end() {
        return h_end;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        this.TimeSTAMP = RendezVous.date_to_timestamp(this.date, this.h_start); 

    }

    public void setH_start(LocalTime h_start) {
        this.h_start = h_start;
        this.TimeSTAMP = RendezVous.date_to_timestamp(this.date, this.h_start); 

    }

    public void setH_end(LocalTime h_end) {
        this.h_end = h_end;
    }

    public void setReminder(boolean reminder) {
        this.reminder = reminder;
    }
   
   

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "date=" + date + ", h_start=" + h_start + ", h_end=" + h_end + ", label=" + label + ", reminder=" + reminder  + ", times=" + TimeSTAMP    ;
    }

    public static long date_to_timestamp(LocalDate date, LocalTime h_start) {
        try{
                      
            int day = date.getDayOfMonth();
            int month = date.getMonthValue();
            int year = date.getYear();
            int hour = h_start.getHour();
            int minutes = h_start.getMinute();
            Date timestamp = new Date(year, month, day, hour, minutes);
            System.out.println(timestamp);
            return timestamp.getTime();

        }
        catch(java.lang.NumberFormatException e){
                System.out.println("Invalid input");
                return 0;
                }
    }
    //check if there is another rdv on the same hour
    public boolean check_avaibility(ArrayList<RendezVous> rdvs){
        for(int i = 0; i< rdvs.size(); i++){
            // check if one rdv in the list has the same date
            if(this.date == rdvs.get(i).getDate()){
                if((this.h_start.isBefore(rdvs.get(i).getH_end()) && this.h_start.isAfter(rdvs.get(i).getH_start()))  )
                {
                    return false;
                }
            }
                
        }
        return true;
    }
    
    static public boolean CheckDate(LocalDate date){
        if (date.isBefore(LocalDate.now())){
                System.out.print("Date given is before today's date\n");
                return false;
                }
            else {
                return true;
        }
    }
    
    
    


}
