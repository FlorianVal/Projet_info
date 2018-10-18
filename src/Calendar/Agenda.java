/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;

import java.util.List;

/**
 *
 * @author florianvalade
 */
public class Agenda {
    private String username;
    private List<RendezVous> rdv;

    public Agenda(String username) {
        this.username = username;
    }

    public Agenda(String username, List<RendezVous> rdv) {
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

    public List<RendezVous> getRdv() {
        return rdv;
    }
    
    
}
