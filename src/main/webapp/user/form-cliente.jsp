<%@include file="util/header.jsp" %>
<body>
    <%@include file="util/navbar-in-client.jsp" %>
    <div id="main" class="container-fluid bg-light p-3" style="margin-top: 4em">
        <%
            String msg = "";
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
        <h3>Editar</h3>
        <hr class="my-2">
        <form method="POST" action="CadastrarCliente">
            <h4>Dados Pessoais</h4>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label>Nome Completo</label>
                    <input type="text" name="nome" class="form-control" id="inputNome" placeholder="Digite seu nome" maxlength="100" required>
                </div>
                <div class="form-group col-md-6">
                    <label>Telefone</label>
                    <input type="text" name="telefone" class="mascara-fone form-control" id="inputTelefone" placeholder="(XX) XXXXX-XXXX" maxlength="15" required>
                </div>
                <div class="form-group col-md-6">
                    <label>E-mail</label>
                    <input type="text" name="email" class="form-control" id="inputEmail" placeholder="email@email.com" maxlength="45">
                </div>
                <div class="form-group col-md-6">
                    <label>CPF</label>
                    <input type="text" name="cpf" class="mascara-cpf form-control" id="inputCPF" placeholder="000.000.000-00" maxlength="14" required>
                </div>
            </div>
            <h4>Dados de acesso</h4>
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label>Login</label>
                    <input type="text" name="login" class="form-control" id="inputLogin" placeholder="" maxlength="15" required>
                </div>
                <div class="form-group col-md-4">
                    <label>Senha</label>
                    <input type="password" name="senha" class="form-control" id="inputSenha" placeholder="" maxlength="15" required>
                </div>
                <div class="form-group col-md-4">
                    <label>Confirmar Senha</label>
                    <input type="password" name="csenha" class="form-control" id="inputConfSenha" placeholder="" maxlength="15" required>
                </div>
                <div class="text-center form-group col-md-12">
                    <button type="submit" class="btn btn-outline-primary"><span class="fa fa-check-square-o fa-fw"></span>Cadastrar-se</button>
                    <a class="btn btn-outline-secondary" href="javascript:history.back();"><span class="fa fa-arrow-left fa-fw"></span>Voltar</a>
                </div>
            </div>
        </form>
    </div>
</body>
 
<%@include file="util/footer.jsp" %> 
<%@include file="util/mascara-input.jsp" %>