
package Pizzapalvelu.Models;

import Models.Tietokanta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Tuote-tieto
 */
public class Tuote {

    private int id;
    private String nimi;
    private String kuvaus;
    private double hinta;
    private int tuotetyyppi;
    private int tuotenumero;

    public Tuote(int id, String nimi, String kuvaus, double hinta, int tuotetyyppi, int tuotenumero) {
        this.id = id;
        this.nimi = nimi;
        this.kuvaus = kuvaus;
        this.hinta = hinta;
        this.tuotetyyppi = tuotetyyppi;
        this.tuotenumero = tuotenumero;
    }
    
    private Tuote(ResultSet tulos) throws SQLException {
        this(
            tulos.getInt("id"),
            tulos.getString("nimi"),
            tulos.getString("kuvaus"),
            tulos.getDouble("hinta"),
            tulos.getInt("tuotetyyppi"),
            tulos.getInt("tuotenumero")
        );
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
    
    
    
    public static Tuote haeTuote(int id) throws SQLException {
        
        Connection yhteys = null;
        PreparedStatement kysely = null;
        ResultSet tulokset = null;
        
        try {
            String SQL = "SELECT * FROM Tuote WHERE id = ?";
            yhteys = Tietokanta.getYhteys();
            kysely = yhteys.prepareStatement(SQL);
            kysely.setInt(1, id);
            tulokset = kysely.executeQuery();

            if (tulokset.next())
                return new Tuote(tulokset);
            else
                return null;
            
        } finally {
            try { tulokset.close(); } catch (Exception e) {  }
            try { kysely.close(); } catch (Exception e) {  }
            try { yhteys.close(); } catch (Exception e) {  }            
        }
        
        
        
    }

    
    
    
}
