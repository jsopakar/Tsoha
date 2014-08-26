package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Tuote-tietokohdetta kuvaava luokka.
 */
public class Tuote {
    
    private int id;
    private String nimi;
    private String kuvaus;
    private double hinta;
    private int tuotetyyppi;
    private int tuotenumero;
    
    private ArrayList<Tayte> taytteet;
    
    
    // TODO: Tähän lisättävä vielä lista täytteistä...

    public Tuote(int id, String nimi, String kuvaus, double hinta, int tuotetyyppi, int tuotenumero) {
        this.id = id;
        this.nimi = nimi;
        this.kuvaus = kuvaus;
        this.hinta = hinta;
        this.tuotetyyppi = tuotetyyppi;
        this.tuotenumero = tuotenumero;
    }

    public int getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public double getHinta() {
        return hinta;
    }

    public int getTuotenumero() {
        return tuotenumero;
    }

    public int getTuotetyyppi() {
        return tuotetyyppi;
    }

    public ArrayList<Tayte> getTaytteet() {
        return taytteet;
    }
    
    public void lisaaTayte(Tayte t) {
        if (t != null)
            this.taytteet.add(t);
    }
    
    /**
     * Hakee listan kaikista tuotteista.
     * @return
     * @throws SQLException 
     */
    public static ArrayList<Tuote> haeTuotteet() throws SQLException {
        
        ArrayList<Tuote> tuotteet = new ArrayList<Tuote>();
        
        Connection yhteys = null;
        PreparedStatement kysely = null;
        ResultSet tulokset = null;
        
        //String SQL = "SELECT * FROM tuote ORDER BY tuotenumero;";
        String SQL = "SELECT tuote.*, ta.id as tayteid, ta.nimi as taytenimi, ta.kuvaus as taytekuvaus, ta.hinta as taytehinta, ta.onkolisatayte as lisatayte "
                   + "FROM tuote, linktuotetayte as li, tayte as ta "
                   + "WHERE li.tuoteid=tuote.id AND li.tayteid=ta.id "
                   + "ORDER BY tuote.tuotenumero;";
        yhteys = Tietokanta.getYhteys();
        kysely = yhteys.prepareStatement(SQL);
        tulokset = kysely.executeQuery();
        
        int id = 0;
        Tuote t = null;
       
        while (tulokset.next()) {
            
            // Jos on uusi tuoterivi
            if (tulokset.getInt("id") != id) {
                if (t != null)
                    tuotteet.add(t);
                
                id = tulokset.getInt("id");
                t = new Tuote(
                        tulokset.getInt("id"),
                        tulokset.getString("nimi"),
                        tulokset.getString("kuvaus"),
                        tulokset.getDouble("hinta"),
                        tulokset.getInt("tuotetyyppi"),
                        tulokset.getInt("tuotenumero"));
            }
            
            Tayte tayte = new Tayte(
                    tulokset.getInt("tayteid"),
                    tulokset.getString("taytenimi"),
                    tulokset.getString("taytekuvaus"),
                    tulokset.getDouble("hinta"),
                    tulokset.getBoolean("lisatayte"));
            t.lisaaTayte(null);
        }
        
        if (t != null)
            tuotteet.add(t);
        
        /*
        while (tulokset.next()) {
            Tuote t = new Tuote(
                    tulokset.getInt("id"),
                    tulokset.getString("nimi"),
                    tulokset.getString("kuvaus"),
                    tulokset.getDouble("hinta"),
                    tulokset.getInt("tuotetyyppi"),
                    tulokset.getInt("tuotenumero"));
            tuotteet.add(t);
        }
        */
        
        try { tulokset.close(); } catch (Exception e) {  }
        try { kysely.close(); } catch (Exception e) {  }
        try { yhteys.close(); } catch (Exception e) {  }            
        
        return tuotteet;
    }
    
    

}
