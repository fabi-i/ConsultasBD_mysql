import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    Connection con;
    public Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practica3_bdd", "root", "root");

            if(con==null) {
                System.out.println("FAK, NO SE CONECTÓ");
            } else {
                System.out.println("wuw<3");
            } //Comprobación en consola de si conectó o no

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
            e.printStackTrace();
        }
        return con;
    }


}
