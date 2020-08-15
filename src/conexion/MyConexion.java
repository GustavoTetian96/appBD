/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

/**
 *
 * @author Gustavo
 */
import com.mysql.jdbc.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
//import java.sql.SQLException;
import com.mysql.jdbc.Statement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
public class MyConexion {

    /**
     * @param args the command line arguments
     */
        private static String DRIVER = "com.mysql.jdbc.Driver";
        private static String URL = "jdbc:mysql://localhost:3306/ebanistasgo?useUnicode=true&use"
                +"JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        private static String USUARIO= "root";
        private static String PASSWORD= "Tetian96";
        
        
        static{
            try{
                Class.forName(DRIVER);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "error enn el driver"+e);
                e.printStackTrace();
            }
        
        }
        
        public  Connection getConnection(){
        Connection con= null;
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL,USUARIO,PASSWORD);           
            JOptionPane.showMessageDialog(null, "conexion exitosa");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "error en conexion"+ex);
            
        }
        return con;
    }
    
    
}
