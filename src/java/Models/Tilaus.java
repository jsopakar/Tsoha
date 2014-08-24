package Models;

import java.util.ArrayList;

/**
 * Yhtä käyttäjän tilausta tekemä luokka.
 * Tilaus voi sisältää useampia tilaustuotteita.
 */
public class Tilaus {
    
    private int id;
    
    private ArrayList tilaukset;
    
    private String asiakkaanNimi;
    private int puhelinnumero;
    private String osoite;

    public Tilaus() {
        this.puhelinnumero = 0;
    }

    public int getId() {
        return id;
    }

    public String getAsiakkaanNimi() {
        return asiakkaanNimi;
    }

    public int getPuhelinnumero() {
        return puhelinnumero;
    }

    public String getOsoite() {
        return osoite;
    }

    public void setAsiakkaanNimi(String asiakkaanNimi) {
        this.asiakkaanNimi = asiakkaanNimi;
    }

    public void setPuhelinnumero(int puhelinnumero) {
        this.puhelinnumero = puhelinnumero;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }
    
    
    
    
    private boolean onkoKelvollinen() {
        boolean ok = true;
        if (tilaukset == null)
            ok = false;
        else if (asiakkaanNimi == null || asiakkaanNimi.isEmpty())
            ok = false;
        else if (puhelinnumero == 0)
            ok = false;
        else if (osoite == null || osoite.isEmpty())
            ok = false;
        return ok;
    }
    
    
}
