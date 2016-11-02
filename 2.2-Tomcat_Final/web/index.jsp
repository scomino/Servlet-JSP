<%-- 
    Document   : index
    Created on : 20-oct-2016, 17:13:50
    Author     : sergi
--%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ResultSet rs = (ResultSet) request.getAttribute("rsname");
%>
<!DOCTYPE html>
<html>
    <style>
        #xmldiv{
            margin-top: 10%;
        }
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de alumnos</title>
    </head>
    <body>
        <div id="contenedor">
            <center><div id="tabla">
                    <center><h1 class="titulo">Mostrar por pantalla la lista de asignaturas del alumno</h1></center>
                    <form id="caja" action="alumnos" method="post">
                        <select name="jobCat">
                            <%
                                while (rs.next()) {
                                    out.println("<option> " + rs.getInt("codi") + " " + rs.getNString("nom") + "</option>");
                                }
                            %>
                        </select>
                        <input type="submit" value="Consultar Tutorias" />
                    </form>
                </div></center>
            <center><div id="xmldiv">
                    <center><h1 class="titulo">Guardar datos en un XML</h1></center>
                    <form id="xml" action="ShowXML" method="post">
                        <select name="jobCat2" >
                            <%
                                rs.beforeFirst();
                                while (rs.next()) {
                                    out.println("<option> " + rs.getInt("codi") + " " + rs.getNString("nom") + "</option>");
                                }
                            %>
                        </select>
                        <input type="submit" value="Guardar en xml" />
                    </form>
                </div>  </center> 
        </div>
    </body>
</html>
