package Pizzapalvelu.Servlets;

import Models.Tayte;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jsopakar
 */
public class AdminTaytteetServlet extends PizzaPalveluServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String virhe = null;
        ArrayList<String> virheet = new ArrayList<String>() {
        };

        List<Tayte> taytteet = new ArrayList<Tayte>();
        Tayte muokattava = null;
        
        // Kirjautumisen tarkastelu
        if (!onKirjautunut(request)) {
            response.sendRedirect("../admin");
            return;
        }

        // Jos halutaan muokata, haetana sen tiedot:
        if (request.getParameter("edit") != null) {
            //virheet.add("Editointi");        //debug
            request.setAttribute("muokkaustila", true);
            int id = Integer.parseInt(request.getParameter("edit"));
            try {
                muokattava = Tayte.haeIdlla(id);
            } catch (SQLException ex) {
            }
        }

        // ---------------------------------------------------------------------
        // Muokkauksen submitin käsittely:
        // ---------------------------------------------------------------------
        if (request.getParameter("tallennaMuokkaus") != null) {
            virheet.add("Tallennuksen käsittely, ID=" + request.getParameter("id"));   //debug

            String temp = muokattu(request);
            if (temp == null) {
                virheet.add("Ei muutoksia tallennuksessa");
            } else {
                virhe = temp;
                int id = Integer.parseInt(request.getParameter("id"));
                Tayte t = null;
                try {
                    t = Tayte.haeIdlla(id);
                    virheet.add("Haettu kannasta...");
                } catch (SQLException ex) {
                }

                t.setNimi(request.getParameter("nimi"));
                t.setKuvaus(request.getParameter("kuvaus"));
                t.setHinta((Double.parseDouble(request.getParameter("hinta"))));

                try {
                    virheet.add(t.tallennaMuutokset());
                } catch (SQLException ex) {
                    virheet.add("TIETOKANTAVIRHE TALLENNUKSESSA!");
                }

                //virheet.add(t.getNimi()+", "+t.getKuvaus()+","+t.getHinta());
            }

        }

        // ---------------------------------------------------------------------
        // Uuden lisäys:
        // ---------------------------------------------------------------------
        if (request.getParameter("tallennaLisays") != null) {
            virheet.add("Uuden lisäksen käsittely");

            muokattava = new Tayte();
            boolean tiedotAnnettu = true;
            
            if (request.getParameter("nimi").equals("")) {
                virheet.add("Et antanut tuotteelle nimeä!");
                tiedotAnnettu = false;
            }
            else
                muokattava.setNimi(request.getParameter("nimi"));
            
            if (request.getParameter("kuvaus").equals("")) {
                virheet.add("Et antanut täytteellekuvausta");
                tiedotAnnettu = false;
            }
            else
                muokattava.setKuvaus(request.getParameter("kuvaus"));
            
            if (request.getParameter("hinta").equals("")) {
                virheet.add("Et antanut tuotteelle hintaa!");
                tiedotAnnettu = false;
            }
            else
                muokattava.setHinta(Double.parseDouble(request.getParameter("hinta")));
            
            // TODO: luettava syöte
            muokattava.setOnkoLisatayte(true);
            
            if (tiedotAnnettu && muokattava.onkoKelvollinen()) {
                try {
                    virheet.add(muokattava.lisaaTietokantaan());
                } catch (SQLException ex) {
                    virheet.add("Tietokantavirhe tallennuksessa!" + ex.getLocalizedMessage());
                }
            }
        }
        
        // ---------------------------------------------------------------------
        // Poiston käsittely:
        // ---------------------------------------------------------------------
        if (request.getParameter("poista") != null) {
            virheet.add("Käsitellään poistoa");
            try {
                int poistettavaId = Integer.parseInt(request.getParameter("poista"));
            } catch (Exception e) {
                virheet.add("Epäkelpo parametri poistettavaksi");
            }
            
            if (request.getParameter("vahvistus") == null) {
                request.setAttribute("poistovahvistus", request.getParameter("poista"));
            } else {
                virheet.add("Voidaan tehdä poisto");
                try {
                    Tayte t = Tayte.haeIdlla(Integer.parseInt(request.getParameter("poista")));
                    if (t.voikoPoistaa()) {
                        virheet.add(t.poistaTietokannasta());
                    }
                } catch (SQLException ex) {
                    virheet.add("Tietokantavirhe poistettaessa!");
                }
            }
            
                
            
        }

        // Haetaan lista kaikista täytteistä:
        try {
            taytteet = Tayte.listTaytteet();
        } catch (SQLException ex) {
            Logger.getLogger(AdminTaytteetServlet.class.getName()).log(Level.SEVERE, null, ex);
            virhe += "Tietokantavirhe!";
        }

        request.setAttribute("taytteet", taytteet);
        if (muokattava != null) {
            request.setAttribute("muokattava", muokattava);
        }
        if (virhe != null) {
            request.setAttribute("virhe", virhe);
        }
        if (virheet != null) {
            request.setAttribute("virheet", virheet);
        }

        naytaJSP("taytteet.jsp", request, response);

        //RequestDispatcher disp = request.getRequestDispatcher("../taytteet.jsp");
        //disp.forward(request, response);
    }

    /**
     * Metodi, jolla katsotaan onko muokkauksessa muutettu kenttien
     * entisiä arvoja.
     * @param req
     * @return 
     */
    private String muokattu(HttpServletRequest req) {

        boolean muokattu = false;

        if (!req.getParameter("nimi").equals(req.getParameter("nimiVanha"))) {
            return "Muutettu: NIMI";
        }
        if (!req.getParameter("kuvaus").equals(req.getParameter("kuvausVanha"))) {
            return "KUVAUS";
        }
        if (!req.getParameter("hinta").equals(req.getParameter("hintaVanha"))) {
            return "HINTA";
        }

//        boolean lisatayte = (req.getParameter("lisatayte") == null);
//        boolean lisatayteVanha = (req.getParameter("lisatayteVanha") == null);
//        if (lisatayte)
//            //return req.getParameter("lisatayteVanha");
//            return "ASD";
//        String lisatayte = "---";
//        if (req.getParameter("lisatayte") == null) lisatayte = "false";
//        if (!lisatayte.equals(req.getParameter("lisatayteVanha")))
//            return lisatayte;
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
