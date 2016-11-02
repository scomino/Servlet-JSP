<%-- 
    Document   : newjsp
    Created on : 26-oct-2016, 15:30:59
    Author     : sergi
--%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ResultSet rs = (ResultSet) request.getAttribute("rsname");
    int codi;
%>

<!DOCTYPE html>


<html>
    <style>
        table{
            border: black 1px solid;
            margin-top: 10%;

        } 
        th{
            border: black 1px solid; 
            background-color: cyan;
        }
        td{
            border: black 1px solid;
        }
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="login">
            <center><table>
                    <tr>
                        <th>Código Alumno</th>
                        <th>Nombre Alumno </th>
                        <th>Asignaturas</th>
                        <th>Tutorías</th>
                    </tr>
                    <%
                        while (rs.next()) {
                            out.println("<tr>");
                            out.println("<td>" + rs.getInt("codialumno") + "</td>");
                            out.println("<td>" + rs.getString("nombalumno") + "</td>");
                            out.println("<td>" + rs.getString("asignanombre") + "</td>");
                            out.println("<td>" + rs.getString("tutonombre") + "</td>");
                            out.println("</tr>");
                        }
                    %>
                </table></center>
        </div>
    </body>
</html>
