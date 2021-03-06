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
 * Classe de gestion des rendez vous contenu dans les agendas.
 * @author florianvalade
 */

public class RendezVous implements Serializable {
    private LocalDate date;
    private LocalTime h_start;
    private LocalTime h_end;
    private String label;
    private boolean reminder;
    private long TimeSTAMP; 
    
    /**
     *
     * @param date
     * @param h_start
     * @param h_end
     */
    public RendezVous(String date, String h_start, String h_end) {
        this.date = LocalDate.parse(date);
        this.h_start = LocalTime.parse(h_start);
        this.h_end = LocalTime.parse(h_end);
        this.label = null;
        this.reminder = false;
        this.TimeSTAMP = RendezVous.date_to_timestamp(this.date, this.h_start); 

    }
    
            
    /**
     *
     * @param date
     * @param h_start
     * @param h_end
     * @param reminder
     */
    public RendezVous(String date, String h_start, String h_end, boolean reminder) {
        this(date, h_start, h_end);
        this.reminder = reminder;
    }

    /**
     *
     * @param date
     * @param h_start
     * @param h_end
     * @param label
     * @param reminder
     */
    public RendezVous(String date, String h_start, String h_end, String label, boolean reminder) {
        this(date, h_start, h_end, reminder);
        this.label = label;
    }
    
    /**
     *
     * @return
     */
    public String getLabel() {
        return label;
    }

    /**
     *
     * @return
     */
    public long getTimeSTAMP() {
        return TimeSTAMP;
    }

    /**
     *
     * @return
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     *
     * @return
     */
    public LocalTime getH_start() {
        return h_start;
    }

    /**
     *
     * @return
     */
    public boolean isReminder() {
        return reminder;
    }

    /**
     *
     * @return
     */
    public LocalTime getH_end() {
        return h_end;
    }

    /**
     *
     * @param date
     */
    public void setDate(LocalDate date) {
        this.date = date;
        this.TimeSTAMP = RendezVous.date_to_timestamp(this.date, this.h_start); 

    }

    /**
     *
     * @param h_start
     */
    public void setH_start(LocalTime h_start) {
        this.h_start = h_start;
        this.TimeSTAMP = RendezVous.date_to_timestamp(this.date, this.h_start); 

    }

    /**
     *
     * @param h_end
     */
    public void setH_end(LocalTime h_end) {
        this.h_end = h_end;
    }

    /**
     *
     * @param reminder
     */
    public void setReminder(boolean reminder) {
        this.reminder = reminder;
    }
   
    /**
     *
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "date=" + date + ", h_start=" + h_start + ", h_end=" + h_end + ", label=" + label + " " + reminder;
    }

    /**
     *
     * @param date
     * @param h_start
     * @return
     */
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

    /**
     *
     * @param rdvs
     * @return
     */
    public boolean check_avaibility(ArrayList<RendezVous> rdvs){
        for(int i = 0; i< rdvs.size(); i++){
            // check if one rdv in the list has the same date
            if(this.date.equals(rdvs.get(i).getDate())){
                if((this.h_start.isBefore(rdvs.get(i).getH_end()) && this.h_start.isAfter(rdvs.get(i).getH_start())) ||
                (this.h_end.isBefore(rdvs.get(i).getH_end()) && this.h_end.isAfter(rdvs.get(i).getH_start())) ||
                 this.h_start.equals(rdvs.get(i).getH_start()) || this.h_end.equals(rdvs.get(i).getH_end()))
                {
                    System.out.println("conflicts on rdv :");
                    System.out.println(rdvs.get(i).toString());
                    System.out.println(this.toString());
                    return false;
                }
            }
                
        }
        return true;
    }
    
    /**
     *
     * @param rdvs
     * @param criteria
     * @return
     */
    static public ArrayList<RendezVous> get_rdvs_with_criteria(ArrayList<RendezVous> rdvs, String criteria){
        ArrayList<RendezVous> rdvs_filtered;
        for(int i =0; i<rdvs.size(); i++){
        }
        return rdvs;
    }

    /**
     *
     * @param date
     * @return
     */
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
