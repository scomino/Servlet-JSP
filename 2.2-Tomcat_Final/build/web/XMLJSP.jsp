<%-- 
    Document   : XMLJSP
    Created on : 31-oct-2016, 14:56:56
    Author     : sergi
--%>


<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ResultSet rs = (ResultSet) request.getAttribute("rsname");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <%
            out.println(" < ?xml version=\"1.0\" encoding=\"UTF-8\">");
            out.println("<br/>");
            out.println("< alumnos>");
            out.println("<br/>");
            while (rs.next()) {
                out.write("< alumno>");
                out.println("<br/>");
                out.write("< codigo>" + rs.getInt("codialumno") + "< /codigo>");
                out.println("<br/>");
                out.write("< nombre>" + rs.getString("nombalumno") + "< /nombre>");
                out.println("<br/>");
                out.write("< asignatura>" + rs.getString("asignanombre") + "< /asignatura>");
                out.println("<br/>");
                out.write("< tutoria>" + rs.getString("tutonombre") + "< /tutoria>");
                out.println("<br/>");
                out.write("< /alumno>");
                out.println("<br/>");
            }
            out.write("< /alumnos>");
            out.println("<br/>");
            out.println("<br/>");
            out.println("<br/>");
            out.println("<p>Se ha gurdado un archivo .xml con los datos del alumno en /\"Carpeta del proyecto\"/build/web");
            
        %>
    </body>
</html>
