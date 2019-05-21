<%
    String nomeUsuarioLogado = (String) session.getAttribute("nomeUsuarioLogado");
    String idUsuario = (String) session.getAttribute("idUsuario");
    String.valueOf(idUsuario);
    
    if(nomeUsuarioLogado != "Administrador" || !nomeUsuarioLogado.equals("Administrador") ){
        session.invalidate();
        request.setAttribute("msg", "Essa pagina é restrita somente aos Administradores do sistema!! "+ nomeUsuarioLogado);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
    if (nomeUsuarioLogado == null || idUsuario == null) {

        session.invalidate();
        request.setAttribute("msg", "Efetue primeiro o login no sistema para ter acesso a pagina desejada!!!");
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

%>