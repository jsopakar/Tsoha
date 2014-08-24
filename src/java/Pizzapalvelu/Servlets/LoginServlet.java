
package Pizzapalvelu.Servlets;

import Models.Yllapitaja;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jsopakar
 */
public class LoginServlet extends PizzaPalveluServlet {
    

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
        
        boolean loginOk = false;
        String virheviesti = null;
        ArrayList<String> virheet = new ArrayList<String>();
        ArrayList<String> tiedotteet = new ArrayList<String>();
       
        String tunnus = request.getParameter("tunnus");
        String salasana = request.getParameter("salasana");
        
        // ---------------------------------------------------------------------
        // Uloskirjautuminen
        // ---------------------------------------------------------------------
        if (request.getParameter("logout") != null) {
            HttpSession sessio = request.getSession();
            sessio.removeAttribute("kirjautunut");
            tiedotteet.add("Olet kirjautunut ulos.");
            request.setAttribute("tiedotteet", tiedotteet);
        }
        
        // ---------------------------------------------------------------------
        // Kun ollaan jo kirjauduttu
        // ---------------------------------------------------------------------
        if (onKirjautunut(request)) {
            //response.sendRedirect("index.jsp");
//            HttpSession sessio = request.getSession();
//            Yllapitaja yp = (Yllapitaja)sessio.getAttribute("kirjautunut");
//            request.setAttribute("kirjautunut", yp.getTunnus());
            request.setAttribute("virheet", virheet);
            naytaJSP("index.jsp", request, response);
            return;
        }
        
        // ---------------------------------------------------------------------
        // Oletustilanne, kun tullaan sivulle kirjautumatta
        // ---------------------------------------------------------------------
        if (tunnus == null && salasana == null) {
            naytaJSP("index.jsp", request, response);
            return;
        }

        // ---------------------------------------------------------------------
        // Virhesyötteiden tarkastelut
        // ---------------------------------------------------------------------
        if (tunnus == null || tunnus.equals("")) {
            virheet.add("Et antanut käyttäjätunnusta!");
            request.setAttribute("virheet", virheet);
            naytaJSP("index.jsp", request, response);
            return;
        }
        
        request.setAttribute("tunnus", tunnus);
        
        if (salasana == null || salasana.equals("")) {
            virheet.add("Et antanut salasanaa!");
            request.setAttribute("virheet", virheet);
            naytaJSP("index.jsp", request, response);
            return;
        }
        
        // ---------------------------------------------------------------------
        // Varsinainen loginin tarkastelu
        // ---------------------------------------------------------------------
        if ((tunnus!=null) && (salasana!=null)) {
            try {
                loginOk = tarkastaKirjautuminen(tunnus, salasana, request);
            } catch (SQLException ex) {
                virheet.add("Tietokantavirhe!");
            }
        }
        
        // ---------------------------------------------------------------------
        if (loginOk) {
            //request.setAttribute("kirjautuminen", "Kirjautuminen onnistui!");
            //naytaJSP("index.jsp", request, response);
//            HttpSession sessio = request.getSession();
//            Yllapitaja yp = (Yllapitaja)sessio.getAttribute("kirjautunut");
//            request.setAttribute("kirjautunut", yp.getTunnus());
            asetaKirjautumistiedot(request);
            naytaJSP("index.jsp", request, response);
        } else {       
            if (!(tunnus == null))
                request.setAttribute("tunnus", tunnus);
                virheviesti = "Kirjautuminen epäonnistui!";
            if (virheviesti!=null)
                request.setAttribute("virheviesti", virheviesti);
            naytaJSP("login.jsp", request, response);
        }
    }
    
    private boolean tarkastaKirjautuminen(String tunnus, String salasana, HttpServletRequest request) throws SQLException {
        
        Yllapitaja yp = null;
        yp = Yllapitaja.haeTunnus(tunnus, salasana);
        
        if (yp == null) {
            return false;
        } else {
            HttpSession sessio = request.getSession();
            sessio.setAttribute("kirjautunut", yp);
            return true;
        }
    }
    
    private void asetaKirjautumistiedot(HttpServletRequest request) {
            HttpSession sessio = request.getSession();
            Yllapitaja yp = (Yllapitaja)sessio.getAttribute("kirjautunut");
            request.setAttribute("kirjautunut", yp.getTunnus());
        
        
    }
    
    /*
    private void naytaJSP(String url, HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher disp = request.getRequestDispatcher(url);
        try {
            disp.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */

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
