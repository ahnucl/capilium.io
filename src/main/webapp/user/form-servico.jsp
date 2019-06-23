<%@include file="../util/header.jsp" %>
<body>
    <%@include file="../util/navbar-in-user.jsp" %>
    <div id="main" class="container-fluid bg-light p-3" style="margin-top: 4em">
        <%   String msg = "";
            msg = (String) request.getAttribute("msg");
            boolean show = (msg == null || msg.isEmpty()) ? false : true;
            if (show) {
        %>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <strong><%=msg%></strong>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <%
            }
        %>

        <% // Recuperando valores do objeto para o formulário
            String valor = "";
            String descricao = "";
            String tempoMedioAtendimento = "";
            String suspenso = "";
            String idServico = "";

            if (request.getAttribute("valor") != null) {
                valor = (String) request.getAttribute("valor");
                descricao = (String) request.getAttribute("descricao");
                tempoMedioAtendimento = (String) request.getAttribute("tempoMedioAtendimento");
                suspenso = (String) request.getAttribute("suspenso");
                idServico = (String) request.getAttribute("idServico");
            }

        %>
        <h3><%= valor.isEmpty() || valor == null ? "Cadastrar" : "Editar"%> Serviço</h3>

        <form method="POST" <%= valor.isEmpty() || valor == null ? "action='../CadastrarServico'" : "action='EditarServico'"%> >
            <div class="form-row">
                <div class="form-group col-md-6">
                    <input type="text" name="idServico" class="form-control" id="idServico" hidden 
                           <%= idServico.isEmpty()||idServico==null ? "":"value='"+idServico+"'" %> >
                    <label for="inputPassword4">Descrição</label>
                    <input type="text" name="descricao" class="form-control" id="inputNome" placeholder="Digite a descrição" maxlength="100" required
                           <%= descricao.isEmpty() || descricao == null ? "" : "value='" + descricao + "'"%> >
                </div>
                <div class="form-group col-md-1">
                    <label for="inputPassword4">valor</label>
                    <input type="number" name="valor" class="form-control" id="inputNome" placeholder="" maxlength="8" required
                           <%= valor.isEmpty() || valor == null ? "" : "value='" + valor + "'"%> >
                </div>
                <div class="form-group col-md-1">
                    <label for="inputPassword4">Tempo</label>
                    <input type="time" name="tempoMedioAtendimento" class="form-control" id="inputTempoMedioAtendimento" placeholder="" maxlength="8" required
                           <%= tempoMedioAtendimento.isEmpty() || tempoMedioAtendimento == null ? "" : "value='" + tempoMedioAtendimento + "'"%> >
                </div>
                <div class="form-group col-md-3">
                    <div class="form-group col-md-3">
                        <label for="ativo">Situação</label>
                    </div>  
                        <label class="radio-inline col-md-3 " for="ativo">
                            <input type="radio" class="radio-inline" id="suspenso" <%= (suspenso.isEmpty() || suspenso == null) &&(suspenso.equals("1")|| suspenso == "1") ? "" : "checked=''"%> value="1" name="suspenso" required> Ativo
                        </label>
                        <label class="radio-inline" for="ativo">
                            <input type="radio" class="radio-inline" id="suspenso" <%= (suspenso.isEmpty() || suspenso == null) &&(suspenso.equals("0")|| suspenso == "0") ? "" : "checked=''"%> value="0" name="suspenso" required> Inativo
                        </label>
                </div>
                <div class="text-center form-group col-md-12">
                    <button type="submit" class="btn btn-outline-primary"><span class="fa fa-check-square-o fa-fw"></span>Salvar</button>
                    <a class="btn btn-outline-secondary" href="javascript:history.back();"><span class="fa fa-arrow-left fa-fw"></span>Voltar</a>
                </div>
        </form>
    </div>
</body>
<%@include file="../util/footer.jsp" %> 