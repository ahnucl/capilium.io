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

        String nome = "";
        String telefone = "";
        String email = "";
        String cpf = "";
        String login = "";
        String idCliente = "";

        if (request.getAttribute("nome") != null) {
            nome = (String) request.getAttribute("nome");
            telefone = (String) request.getAttribute("telefone");
            email = (String) request.getAttribute("email");
            cpf = (String) request.getAttribute("cpf");
            login = (String) request.getAttribute("login");
            idCliente = (String) request.getAttribute("idCliente");
        }
    %>
    <h3><%= nome.isEmpty() || nome == null ? "Cadastrar" : "Editar"%></h3>
    <hr class="my-2">
    <!--Esse linha é um teste para fazer a distinção se o cadastro foi feito por um cliente ou usuario do sistema
    
    <form method="POST" <%= nome.isEmpty() || nome == null ? "action='../CadastrarCliente?id=" + (String) session.getAttribute("idUsuario") + "'" : "action='../EditarCliente?id=" + (String) session.getAttribute("idUsuario") + "'"%>>-->
    <form method="POST" <%= nome.isEmpty() || nome == null ? "action='CadastrarCliente'" : "action='../EditarCliente'"%>>
        <h4>Dados Pessoais</h4>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label>Nome Completo</label>
                <input type="text" name="nome" <%= nome.isEmpty() || nome == null ? "" : "value='" + nome + "'"%> class="form-control" id="inputNome" placeholder="Digite seu nome" maxlength="100" required>
            </div>
            <div class="form-group col-md-6">
                <label>Telefone</label>
                <input type="text" name="telefone" <%= telefone.isEmpty() || telefone == null ? "" : "value='" + telefone + "'"%> class="mascara-fone form-control" id="inputTelefone" placeholder="(XX) XXXXX-XXXX" maxlength="15" required>
            </div>
            <div class="form-group col-md-6">
                <label>E-mail</label>
                <input type="text" name="email" <%= email.isEmpty() || email == null ? "" : "value='" + email + "'"%> class="form-control" id="inputEmail" placeholder="email@email.com" maxlength="45">
            </div>
            <div class="form-group col-md-6">
                <label>CPF</label>
                <input type="text" name="cpf" <%= cpf.isEmpty() || cpf == null ? "" : "value='" + cpf + "'"%> class="mascara-cpf form-control" id="inputCPF" placeholder="000.000.000-00" maxlength="14" required>
            </div>
        </div>
        <h4>Dados de acesso</h4>
        <div class="form-row">
            <div class="form-group col-md-4">
                <label>Login</label>
                <input type="text" name="login" <%= nome.isEmpty() || login == null ? "" : "value='" + login + "'"%> class="form-control" id="inputLogin" placeholder="" maxlength="15" required>
            </div>
            <div class="form-group col-md-4">
                <label>Senha</label>
                <input type="password" name="senha" class="form-control" id="inputSenha" placeholder="" maxlength="13" minlength="6" required>
            </div>
            <div class="form-group col-md-4">
                <label>Confirmar Senha</label>
                <input type="password" name="csenha" class="form-control" id="inputConfSenha" placeholder="" maxlength="13" minlength="6" required>
            </div>
            <div class="text-center form-group col-md-12">
                <button type="submit" class="btn btn-outline-primary"><span class="fa fa-check-square-o fa-fw"></span><%= nome.isEmpty() || nome == null ? "Cadastrar-se" : "Atualizar"%></button>
                <a class="btn btn-outline-secondary" href="javascript:history.back();"><span class="fa fa-arrow-left fa-fw"></span>Voltar</a>
            </div>
        </div>
    </form>
</div>
<script>

    var passwordForm = document.getElementById("inputSenha")
            , confirm_passwordForm = document.getElementById("inputConfSenha");

    function validatePassword() {
        if (passwordForm.value != confirm_passwordForm.value) {
            confirm_passwordForm.setCustomValidity("Senhas diferentes!");
        } else {
            confirm_passwordForm.setCustomValidity('');
        }
    }

    passwordForm.onchange = validatePassword;
    confirm_passwordForm.onkeyup = validatePassword;
</script>