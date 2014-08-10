
package Servlets;

import Models.Tayte;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jsopakar
 */
public class ListaustestiServlet extends HttpServlet {

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

        PrintWriter out = response.getWriter();
        List<Tayte> taytteet = null;
        
        try {
            taytteet = Tayte.listTaytteet();          
        } catch (SQLException ex) {
            Logger.getLogger(TestiServlet.class.getName()).log(Level.SEVERE, null, ex);
            out.println(ex);
        }
        
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestiServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<table>");
            out.println("<tr><th>ID</th><th>Nimi</th><th>Kuvaus</th><th>Hinta</th><th>Lisätäyte</th></tr>");
            
            for (Tayte t : taytteet ) {
                out.println("<tr>");
                out.println("<td>" + t.getId() + "</td>");
                out.println("<td>" + t.getNimi() + "</td>");
                out.println("<td>" + t.getKuvaus() + "</td>");
                out.println("<td>" + t.getHinta() + "</td>");
                out.println("<td>" + t.isOnkoLisatayte() + "</td>");
                out.println("</tr>");
                
            }
            
            out.println("</table>");
            
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
