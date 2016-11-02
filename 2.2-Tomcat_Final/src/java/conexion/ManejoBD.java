/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import Servlets.Servlet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergi
 */
public class ManejoBD {
 ConexionMySQL mysql;
  
    Statement st;
    ResultSet rs;   
    
    /**
     * 
     * @param cn
     * @return 
     */
public ResultSet allAlumne(Connection cn){
    
   try {
            String vSQL = "SELECT nom,codi from alumne";
             mysql = new ConexionMySQL();
            cn = mysql.conectar();
            st = cn.createStatement();
            rs = st.executeQuery(vSQL);
           
            
            } catch (SQLException ex) {
         Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
     }    
   return rs;         
}

    
 
    
    /**
     * devuelve la informacion del alumno con el codigo que tu le pasas por parametro
     * @param codi
     * @param cn
     * @return 
     */
  public ResultSet allAlumne3(int codi,Connection cn){   
   try {
            String vSQL = 
"select alumne.codi as codialumno, alumne.nom as nombalumno, tutoria.nom as tutonombre, assignatura.nom as asignanombre    "
                + "from alumne   "
                + "inner JOIN tutoriaalumne on tutoriaalumne.codiAlumne = alumne.codi  "
                + "inner join tutoria on tutoria.codi = tutoriaalumne.codiTutoria  "
                + "inner join assignatura on assignatura.codi=tutoria.codiAssignatura  "
                + "where alumne.codi=" + codi;
            mysql = new ConexionMySQL();
            cn = mysql.conectar();
            st = cn.createStatement();
            rs = st.executeQuery(vSQL);
           
            
            } catch (SQLException ex) {
         Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
     }    
   return rs;         
}  
  
}
