
package Models;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 */
public class Tietokanta {

    //InitialContext cxt = new InitialContext();
    //DataSource yhteysVarasto = (DataSource) cxt.lookup("java:/comp/env/jdbc/tietokanta");    

//    public Tietokanta() {
//        InitialContext cxt = new InitialContext();
//        DataSource yhteysVarasto = (DataSource) cxt.lookup("java:/comp/env/jdbc/tietokanta");    
//    }

    
    
    public static Connection getYhteys() {
        Connection yhteys = null;
        try {
            InitialContext cxt = new InitialContext();
            DataSource yhteysVarasto = (DataSource) cxt.lookup("java:/comp/env/jdbc/tietokanta");
            yhteys = yhteysVarasto.getConnection();
        } catch (Exception e) {
            System.out.println("Virhe");
        }
        
        return yhteys;
        
    }
}
