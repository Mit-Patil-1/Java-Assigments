/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatefulEjbClass.java to edit this template
 */
package SessionBran;

import jakarta.ejb.Stateful;
import java.sql.*;
/**
 *
 * @author DELL
 */
@Stateful
public class VisitBeanSessionBean implements VisitBeanSessionBeanLocal {

    @Override
    public int recordvisite(String ipadd) {
        
        int c = 0;
        
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bscit_db?zeroDateTimeBehavior=CONVERT_TO_NULL","root","root");
            
            PreparedStatement ps = con.prepareStatement(
            "select visite_count from visite_record where ip_add=?");
            
            ps.setString(1, ipadd);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                c = rs.getInt("visite_count")+1;
                
                PreparedStatement ps1 = con.prepareStatement(
                "update visite_record set visite_count=? where ip_add=?");
                
                ps1.setInt(1, c);
                ps1.setString(2, ipadd);
                ps1.executeUpdate();
            }
            else
                
            {
               c = 1;

                PreparedStatement ps2 = con.prepareStatement(
                "INSERT INTO visite_record (ip_add, visite_count) VALUES (?, ?)");

                ps2.setString(1, ipadd);
                ps2.setInt(2, c);
                ps2.executeUpdate();
            }
            
            con.close();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
                
        return c;
        
    }

}
