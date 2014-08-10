package Models;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 */
public class Tayte {
    
    private int id;
    private String nimi;
    private String kuvaus;
    private double hinta;
    private boolean onkoLisatayte;

    public Tayte(int id, String nimi, String kuvaus, double hinta, boolean onkoLisatayte) {
        this.id = id;
        this.nimi = nimi;
        this.kuvaus = kuvaus;
        this.hinta = hinta;
        this.onkoLisatayte = onkoLisatayte;
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

    public boolean isOnkoLisatayte() {
        return onkoLisatayte;
    }
    
    
    
    public static List<Tayte> listTaytteet() throws SQLException {
        
        ArrayList<Tayte> taytteet = new ArrayList<Tayte>();
        
        String SQL = "SELECT * FROM Tayte ORDER BY id;";
        
        Connection yhteys = Tietokanta.getYhteys();
        PreparedStatement kysely = null;
        try {
            kysely = yhteys.prepareStatement(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(Tayte.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet tulokset = kysely.executeQuery();

        while (tulokset.next()) {
            Tayte t = new Tayte(tulokset.getInt("id"),
                    tulokset.getString("nimi"),
                    tulokset.getString("kuvaus"),
                    tulokset.getDouble("hinta"),
                    tulokset.getBoolean("onkolisatayte"));
            taytteet.add(t);
        }

        //Suljetaan kaikki resutuloksetsit:
        try { tulokset.close(); } catch (Exception e) {}
        try { kysely.close(); } catch (Exception e) {}
        try { yhteys.close(); } catch (Exception e) {}        
        
        
        return taytteet;
        
    }
    
}
