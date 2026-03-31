/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import SessionBean.CurrencyConvertBean;
import jakarta.ejb.EJB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.lang.System.out;



@WebServlet(name = "CurrencyServlet", urlPatterns = {"/CurrencyServlet"})
public class CurrencyServlet extends HttpServlet {

 @EJB
 CurrencyConvertBean cb;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try 
        {
           PrintWriter out = response.getWriter();
           String res = "";
           
           if(request.getParameter("sub1")  != null)
           {
               String from = request.getParameter("from1");
               String to = request.getParameter("to1");
               double amt =  Double.parseDouble(request.getParameter("amt1"));
               
            double converted = cb.convert(from, to, amt);

            res = "Converted Amount = " + converted;
            
           }
           
           
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CurrencyServlet</title>");
            out.println("</head>");
            out.println("<body>");
           out.println("<h2>Currency Converter</h2>");

            out.println("<form method='post'>");

            out.println("From currency = <input type='text' name='from1'/><br><br>");
            out.println("To currency = <input type='text' name='to1'/><br><br>");
            out.println("Amount = <input type='text' name='amt1'/><br><br>");

            out.println("<input type='submit' name='sub1' value='Convert'/><br><br>");

            out.println("</form>");

            out.println("<h3>" + res + "</h3>");

 
        
            out.println("</body>");
            out.println("</html>");
        
    }catch (Exception ex) {
         out.print(ex);
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