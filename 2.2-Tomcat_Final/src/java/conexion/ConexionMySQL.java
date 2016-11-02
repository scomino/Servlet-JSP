/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sergio
 * 
 */
public class ConexionMySQL {

    public String bd = "base_acceso";
    public String login = "root";
    public String password = "";
    public String url = "jdbc:mysql://localhost/" + bd;
    Connection link = null;

    public Connection conectar() {
        
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            link = DriverManager.getConnection(this.url, this.login, this.password);
             if (link!=null){
            System.out.println("Conectado a "+link.toString() );
  }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return link;
    }

    
    public void closeConnection(){
  try {
   link.close();
   System.out.println("Conexión cerrada");
  } catch (SQLException e) {
   e.printStackTrace();
   System.out.println("Fallo al cerrar la conexión");
  }
 }

    
    
    
    
    /**
     * muestra todo el contenido de la tabla que le pasamos por parametro
     * @param titols nombre de los atributos de cada tabla
     * @param num_atri numero de atributos que quiere
     * @param nom_tabla nombre de la tabla
     * @param tblConsulta tabla donde se volcara los resultados
     */
    public void consultar(String[] titols, int num_atri, String nom_tabla, javax.swing.JTable tblConsulta) {
        String vSQL = new String();
        String[] registro = new String[num_atri];

        DefaultTableModel modelo = new DefaultTableModel(null, titols);

        ConexionMySQL mysql = new ConexionMySQL();
        Connection cn = mysql.conectar();

        vSQL = "SELECT * from " + nom_tabla;

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(vSQL);

            while (rs.next()) {
                for (int i = 0; i < num_atri; i++) {
                    registro[i] = rs.getString(titols[i]);
                }
                modelo.addRow(registro);
            }
            tblConsulta.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    /**
     * metodo para borrar los registros donde el usuario el atributo que quieree consultar
     * y el valor que tiene que tener para que sea borrado
     * @param nom_tabla nmombre de la tabla
     * @param campo campo por el cual vamos a buscar 
     * @param intro valor que deseamos encontrar, sera introducido por usuario
     */
    public void Borrar(String nom_tabla, String campo, String intro) {
        try {
            String sql = "DELETE  from " + nom_tabla + " WHERE " + campo + " = '" + intro + "'";
            ConexionMySQL mysql = new ConexionMySQL();
            Connection cn = mysql.conectar();
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }
    
   /**
 * metodo para borrar los registros donde el usuario el atributo que quieree consultar
 * y un valor inicial y uno final y se borraran todos los registros que esten en este rango
 * @param nom_tabla
 * @param campo
 * @param intro
 * @param intro2 
 */
    public void BorrarIntervalos(String nom_tabla, String campo, String intro, String intro2) {
        try {
            String sql = "DELETE  from " + nom_tabla + " WHERE " + campo + " BETWEEN '" + intro + "' AND '" + intro2 + "'";
            ConexionMySQL mysql = new ConexionMySQL();
            Connection cn = mysql.conectar();
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    /**
     * metodo para mostrar los registro que conciden con lo que introduce el usuario por teclado
     * @param nom_tabla nombre de la tabla donde se va a buscar
     * @param campo atributo por el cual se va a buscar lo que introduce el usuario
     * @param intro texto que el usuario introduce
     * @param titols nombre de los atributos
     * @param num_atri numero de atributos que tiene la tabla
     * @param tblConsulta jTable donde se mostrar el resultado
     */
    public void ConsultaCondicion(String nom_tabla, String campo, String intro,String[] titols, int num_atri,javax.swing.JTable tblConsulta) {
        String sql = "SELECT *  from " + nom_tabla + " WHERE " + campo + " = '" + intro + "'";
        String vSQL = new String();
        String[] registro = new String[num_atri];
        
       DefaultTableModel modelo = new DefaultTableModel(null, titols);

        ConexionMySQL mysql = new ConexionMySQL();
        Connection cn = mysql.conectar();

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
              for (int i = 0; i < num_atri; i++) {
                    registro[i] = rs.getString(titols[i]);
                }
                modelo.addRow(registro);
            }
            tblConsulta.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    /**
     * metodo para mostrar los registro que se encuentra en el rango de los dos textos qu ha introducido el usuario
     * @param nom_tabla nombre de la tabla donde se va a buscar
     * @param campo atributo por el cual se va a buscar lo que introduce el usuario
     * @param intro  texto que el usuario introduce
     * @param intro2  texto que el usuario introduce
     * @param titols nombre de los atributos
     * @param memoria  numero de atributos que tiene la tabla
     * @param tblConsulta  jTable donde se mostrar el resultado
     */
     public void ConsultaCondicionIntervalos(String nom_tabla, String campo, String intro,String intro2,String[] titols, int memoria,javax.swing.JTable tblConsulta) {
       String sql = "select *  from " + nom_tabla + " WHERE " + campo + " BETWEEN '" + intro + "' AND '" + intro2 + "'";
        String vSQL = new String();
        String[] registro = new String[memoria];
        
       DefaultTableModel modelo = new DefaultTableModel(null, titols);

        ConexionMySQL mysql = new ConexionMySQL();
        Connection cn = mysql.conectar();

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
              for (int i = 0; i < memoria; i++) {
                    registro[i] = rs.getString(titols[i]);
                }
                modelo.addRow(registro);
            }
            tblConsulta.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
  
}
