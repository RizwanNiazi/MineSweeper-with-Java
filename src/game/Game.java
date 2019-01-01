/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import static java.awt.image.ImageObserver.HEIGHT;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;

/**
 *
 * @author l164251
 */
public class Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
    Connection con= null;
    Statement st=null;
    ResultSet rs=null;
    String query="Select * from MAJOR.player";
    try{
        con=DriverManager.getConnection("jdbc:derby://localhost:1527/my_game", "major", "major");
        st=con.createStatement();
        rs=st.executeQuery(query);
        while(rs.next())
        {
            String n=rs.getString("name");
            int score= rs.getInt("score");
               System.out.print("Name  :  ");
      System.out.println(n);
         System.out.print("Score  :  ");
      System.out.println(score);
        }
    }catch(SQLException e)
    {
               e.printStackTrace();
    }
    
    
}
}
