/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package SessionBean;

import jakarta.ejb.Stateless;
import java.sql.DriverManager;
import java.sql.*;

/**
 *
 * @author DELL
 */
@Stateless
public class CurrencyConvertBean {

    private final String url= "jdbc:mysql://localhost:3306/bscit_db?zeroDateTimeBehavior=CONVERT_TO_NULL";
     private final String user = "root";
    private final String password = "root";
    
    
    public double convert(String from,String to,double amt)
    {
        double rate=0.0 ;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,user,password);
            
            PreparedStatement ps = con.prepareStatement(
            "select rate from currency_rates where from_cur=? and  to_cur=?");
            
            ps.setString(1, from);
            ps.setString(2, to);
            
            ResultSet rs = (ResultSet) ps.executeQuery();
            
            if(rs.next()){
                rate = rs.getDouble("rate");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return amt*rate;
    }
}
