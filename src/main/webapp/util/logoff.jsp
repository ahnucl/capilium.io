<%@page import="javax.faces.context.FacesContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Insert title here</title>
    </head>
    <body>
        <%
            session.removeAttribute("nomeUsuarioLogado");
            session.removeAttribute("idUsuario");
            session.removeAttribute("id");
            session.invalidate();
            
//            FacesContext.getCurrentInstance().getExternalContext().getRealPath("/capilium.io");
//            request.getRequestDispatcher("index.jsp").forward(request, response);
              response.sendRedirect(request.getContextPath()+"/index.jsp");
//            response.sendRedirect("/capilium.io/index.jsp");
        %>
    </body>
</html>