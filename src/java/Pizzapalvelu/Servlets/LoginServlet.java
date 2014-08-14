
package Pizzapalvelu.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
public class LoginServlet extends HttpServlet {
    

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
        
        String tunnus = request.getParameter("tunnus");
        String salasana = request.getParameter("salasana");
//        if (tunnus == null) tunnus = "";
//        if (salasana == null) salasana = "";
        
        
        
        if (tunnus!=null && salasana!=null)
            loginOk = tarkastaKirjautuminen(tunnus, salasana);
        
        
        if (loginOk) {
            RequestDispatcher disp = request.getRequestDispatcher("index.jsp");
            disp.forward(request, response);
        } else {
            
            if (tunnus!=null)
                request.setAttribute("tunnus", tunnus);
                virheviesti = "Kirjautuminen epäonnistui!";
                
            if (virheviesti!=null)
                request.setAttribute("virheviesti", virheviesti);
            naytaJSP("login.jsp", request, response);
        }
        
    }
    
    private boolean tarkastaKirjautuminen(String tunnus, String salasana) {
        
        if (tunnus.equals("oikea") && salasana.equals("sala")) {
            return true;
        } else {
            return false;
        }
            
    }
    
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
