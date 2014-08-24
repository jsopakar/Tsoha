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

    public Tayte() {
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

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    public void setOnkoLisatayte(boolean onkoLisatayte) {
        this.onkoLisatayte = onkoLisatayte;
    }

    /**
     * Metodi, joka tallentaa olemassa olevan täytteen muutokset kantaan.
     * @return
     * @throws SQLException 
     */
    public String tallennaMuutokset() throws SQLException {
        String temp = "";
        String SQL = "UPDATE tayte SET nimi = ?, kuvaus = ?, hinta = ?, onkolisatayte = ? WHERE id = ?;";
        Connection yhteys = Tietokanta.getYhteys();
        PreparedStatement kysely = null;
        kysely = yhteys.prepareStatement(SQL);
        kysely.setString(1, this.nimi);
        kysely.setString(2, this.kuvaus);
        kysely.setDouble(3, this.hinta);
        kysely.setBoolean(4, this.onkoLisatayte);
        kysely.setInt(5, this.id);
        temp = kysely.toString();
        int tulos = kysely.executeUpdate();
        
        //Suljetaan kaikki:
        try { kysely.close(); } catch (Exception e) {}
        try { yhteys.close(); } catch (Exception e) {}        
        
        return temp;
    }
    
    public String lisaaTietokantaan() throws SQLException {
        String temp = "";
        
        String SQL = "INSERT INTO tayte(nimi, kuvaus, hinta, onkolisatayte) VALUES (?,?,?,?) RETURNING id;";
        Connection yhteys = Tietokanta.getYhteys();
        PreparedStatement kysely = null;
        kysely = yhteys.prepareStatement(SQL);
        kysely.setString(1, this.nimi);
        kysely.setString(2, this.kuvaus);
        kysely.setDouble(3, this.hinta);
        //TODO: Hardcodattu true...
        kysely.setBoolean(4, true);
        temp = kysely.toString();
        
        ResultSet rs = kysely.executeQuery();
        rs.next();
        this.id = rs.getInt(1);

        //Suljetaan kaikki:
        try { rs.close(); } catch (Exception e) {}
        try { kysely.close(); } catch (Exception e) {}
        try { yhteys.close(); } catch (Exception e) {}        

        return temp + " ID oli " + this.id;
    }
    
    public String poistaTietokannasta() throws SQLException {
        String temp = "";
        
        if (!voikoPoistaa())
            return temp;
        
        String SQL = "DELETE FROM tayte WHERE id = ?;";
        Connection yhteys = Tietokanta.getYhteys();
        PreparedStatement kysely = null;
        kysely = yhteys.prepareStatement(SQL);
        kysely.setInt(1, this.id);
        
        temp = kysely.toString();
        
        kysely.execute();
        
        //Suljetaan kaikki:
        //try { rs.close(); } catch (Exception e) {}
        try { kysely.close(); } catch (Exception e) {}
        try { yhteys.close(); } catch (Exception e) {}        
        
        return temp;
    }

    /**
     * Tarkastaa onko lisättävät tai tallennettavat tiedot kelvollisia. TODO: Ei
     * tee vielä mitään.
     *
     * @return
     */
    public boolean onkoKelvollinen() {
        boolean ok = true;
        
        //nimi
        if (nimi == null || nimi.isEmpty() || nimi.length()>64)
            ok = false;
        //else if (nimi.length() > 64)
        //    ok = false;
        
        //kuvaus
        if (kuvaus == null || kuvaus.length()>256)
            ok = false;
        
        //hinta
        if (hinta < 0 || hinta > 999)
            ok = false;
        return ok;
    }

    //TODO: Tarkastettava viite-eheys...
    public boolean voikoPoistaa() {
        
        return true;
    }
    
    public static List<Tayte> listTaytteet() throws SQLException {
        
        ArrayList<Tayte> taytteet = new ArrayList<Tayte>();
        
        String SQL = "SELECT * FROM tayte ORDER BY id;";
        
        Connection yhteys = Tietokanta.getYhteys();
        PreparedStatement kysely = null;
        //try {
            kysely = yhteys.prepareStatement(SQL);
        //} catch (SQLException ex) {
        //    Logger.getLogger(Tayte.class.getName()).log(Level.SEVERE, null, ex);
        //}
            
        ResultSet tulokset = kysely.executeQuery();

        while (tulokset.next()) {
            Tayte t = new Tayte(tulokset.getInt("id"),
                    tulokset.getString("nimi"),
                    tulokset.getString("kuvaus"),
                    tulokset.getDouble("hinta"),
                    tulokset.getBoolean("onkolisatayte"));
            taytteet.add(t);
        }

        //Suljetaan kaikki:
        try { tulokset.close(); } catch (Exception e) {}
        try { kysely.close(); } catch (Exception e) {}
        try { yhteys.close(); } catch (Exception e) {}        
        
        return taytteet;
        
    }
    
    public static Tayte haeIdlla(int id) throws SQLException {
        
        Tayte t = null;
        
        if (id > 0) {
            String SQL = "SELECT * FROM Tayte WHERE id = ?";
            Connection yhteys = Tietokanta.getYhteys();
            PreparedStatement kysely = yhteys.prepareStatement(SQL);
            kysely.setInt(1, id);
            
            ResultSet rs = kysely.executeQuery();
            
            if (rs.next()) {
                t = new Tayte(
                        rs.getInt("id"),
                        rs.getString("nimi"),
                        rs.getString("kuvaus"),
                        rs.getDouble("hinta"),
                        rs.getBoolean("onkolisatayte"));
            }

        try { rs.close(); } catch (Exception e) {  }
        try { kysely.close(); } catch (Exception e) {  }
        try { yhteys.close(); } catch (Exception e) {  }
            
        }
        
        
        return t;
        
    }

}
