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
        
        String SQL = "SELECT * FROM tuote ORDER BY tuotenumero;";
        yhteys = Tietokanta.getYhteys();
        kysely = yhteys.prepareStatement(SQL);
        tulokset = kysely.executeQuery();
        
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
        
        /*
        Tuote t1 = new Tuote(10, "Koe", "Koekuvaus", 7.90, 1, 8);
        Tuote t2 = new Tuote(10, "Koe2", "Koekuvaus2", 9.90, 1, 9);
        tuotteet.add(t1);
        tuotteet.add(t2);
        */
        
        try { tulokset.close(); } catch (Exception e) {  }
        try { kysely.close(); } catch (Exception e) {  }
        try { yhteys.close(); } catch (Exception e) {  }            
        
        return tuotteet;
    }
    
    

}
