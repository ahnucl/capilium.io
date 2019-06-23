<%
    String nomeUsuarioLogado = "";
    String idUsuario = "";

    nomeUsuarioLogado = (String) session.getAttribute("nomeUsuarioLogado");
    idUsuario = (String) session.getAttribute("idUsuario");

    try {
        if ((String) session.getAttribute("nomeUsuarioLogado") == null) {
            if (!session.isNew()) {
                session.removeAttribute("nomeUsuarioLogado");
                session.removeAttribute("idUsuario");

                session.invalidate();
            }
            request.setAttribute("msg", "Efetue primeiro o login no sistema para ter acesso a pagina desejada!!!");
            request.getRequestDispatcher("index.jsp").forward(request, response);

        }
        if (nomeUsuarioLogado != "Administrador" || !nomeUsuarioLogado.equals("Administrador")) {
            if (!session.isNew()) {
                session.removeAttribute("nomeUsuarioLogado");
                session.removeAttribute("idUsuario");

                session.invalidate();
            }
            request.setAttribute("msg", "Essa pagina é restrita somente aos Administradores do sistema!! " + nomeUsuarioLogado);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

%>