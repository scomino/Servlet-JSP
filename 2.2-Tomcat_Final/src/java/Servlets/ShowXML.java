/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import conexion.ConexionMySQL;
import conexion.ManejoBD;
import generated.Alumnos;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author sergi
 */
public class ShowXML extends HttpServlet {

    ResultSet rs;
    Connection cn;
    ConexionMySQL mysql;

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowXML</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowXML at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        String codigo = request.getParameter("jobCat2");//cogemos el valor de la lista que el usuario ha selecionado
        int cod = Integer.parseInt(String.valueOf(codigo.charAt(0)));;//del String que recibimos hago un casteo y cojo el primer valor como un int
        ManejoBD manejo = new ManejoBD();
        rs = manejo.allAlumne3(cod, cn);
    
        /**
         *Indicamos la ruta que tendra el archivo xml
         */
        String contextPath = getServletContext().getRealPath("/");
        String xmlFilePath = contextPath + "tutorias_alumno"+cod+".xml";
        File myfile = new File(xmlFilePath);
        Alumnos al = new Alumnos();
        try {
            while (rs.next()) {
                al.getAlumno().add(new Alumnos.Alumno(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(3)));
                objectToXml(al, myfile);

            }

            rs.beforeFirst();//devolvemos el cursor del ResultSet al la primera posicion 
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("rsname", rs);
        RequestDispatcher a = request.getRequestDispatcher("/XMLJSP.jsp");
        a.forward(request, response);
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

    /*
    pasa objetos java a un archivo xml
     */
    private void objectToXml(Alumnos cds, File rf) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Alumnos.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Optional
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //Do the job
            jaxbMarshaller.marshal(cds, rf);

            //Optional: output pretty printed
            //jaxbMarshaller.marshal(cds, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
