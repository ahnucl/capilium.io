<%
    String nomeUsuarioLogado = (String) session.getAttribute("nomeUsuarioLogado");
    String idUsuario = (String) session.getAttribute("idUsuario");
    String.valueOf(idUsuario);

    if (nomeUsuarioLogado == null || idUsuario == null) {

        session.invalidate();
        request.setAttribute("msg", "Efetue primeiro o login no sistema para ter acesso a pagina desejada!!!");
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

%>