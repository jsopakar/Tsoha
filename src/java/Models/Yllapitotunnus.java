
package Models;

import Pizzapalvelu.Models.Tuote;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class Yllapitotunnus {
    
    private String tunnus;
    private String salasana;
    private int tyyppi;

    public Yllapitotunnus(String tunnus, String salasana, int tyyppi) {
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
    
    public static Yllapitotunnus haeTunnus(String tunnus) throws SQLException {

        Connection yhteys = null;
        PreparedStatement kysely = null;
        ResultSet tulokset = null;
        
        try {
            String SQL = "SELECT * FROM yllapitotunnukset WHERE id = ?";
            yhteys = Tietokanta.getYhteys();
            kysely = yhteys.prepareStatement(SQL);
            kysely.setString(1, tunnus);
            tulokset = kysely.executeQuery();

            if (tulokset.next())
                return new Yllapitotunnus(
                        tulokset.getString("tunnus"),
                        tulokset.getString("salasana"),
                        tulokset.getInt("tyyppi")
                );
            
            else
                return null;
            
        } finally {
            try { tulokset.close(); } catch (Exception e) {  }
            try { kysely.close(); } catch (Exception e) {  }
            try { yhteys.close(); } catch (Exception e) {  }            
        }
        
    }
    
    
    
    
   

}
