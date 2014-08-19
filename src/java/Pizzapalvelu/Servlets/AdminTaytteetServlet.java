
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
        
        String virhe = null;
        
        List<Tayte> taytteet = new ArrayList<Tayte>();
        Tayte muokattava = null;
        
        // Haetaan lista kaikista täytteistä:
        try {
            taytteet = Tayte.listTaytteet();
        } catch (SQLException ex) {
            Logger.getLogger(AdminTaytteetServlet.class.getName()).log(Level.SEVERE, null, ex);
            virhe += "Tietokantavirhe!";
        }
        
        // Jos halutaan muokata, haetana sen tiedot:
        if (request.getParameter("edit") != null) {
            virhe = "ASD";
            int id = Integer.parseInt(request.getParameter("edit"));
            try {
                muokattava = Tayte.haeIdlla(id);
            } catch (SQLException ex) { }
        }
        
        //virhe = "koe";
        
        request.setAttribute("taytteet", taytteet);
        if (muokattava != null)
            request.setAttribute("muokattava", muokattava);
        if (virhe != null)
            request.setAttribute("virhe", virhe);
        
        naytaJSP("../taytteet.jsp", request, response);
        
        //RequestDispatcher disp = request.getRequestDispatcher("../taytteet.jsp");
        //disp.forward(request, response);
        
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
