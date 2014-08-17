
package Models;

import Pizzapalvelu.Models.Tuote_test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class Yllapitaja {
    
    private String tunnus;
    private String salasana;
    private int tyyppi;

    public Yllapitaja(String tunnus, String salasana, int tyyppi) {
        this.tunnus = tunnus;
        this.salasana = salasana;
        this.tyyppi = tyyppi;
    }

    public String getTunnus() {
        return tunnus;
    }

    public String getSalasana() {
        return salasana;
    }

    public int getTyyppi() {
        return tyyppi;
    }
    
    public static Yllapitaja haeTunnus(String tunnus, String salasana) throws SQLException {

        Yllapitaja yp = null;
        
        String SQL = "SELECT * FROM yllapitotunnukset WHERE tunnus = ? and salasana = ?";
        Connection yhteys = Tietokanta.getYhteys();
        
        PreparedStatement kysely = yhteys.prepareStatement(SQL);

        kysely.setString(1, tunnus);
        kysely.setString(2, salasana);
        ResultSet tulokset = kysely.executeQuery();

        if (tulokset.next()) {
            yp = new Yllapitaja(
                    tulokset.getString("tunnus"),
                    tulokset.getString("salasana"),
                    tulokset.getInt("tyyppi"));
        }
        
        try { tulokset.close(); } catch (Exception e) {  }
        try { kysely.close(); } catch (Exception e) {  }
        try { yhteys.close(); } catch (Exception e) {  }
        
        return yp;
        
    }
    
    
    
    
   

}
