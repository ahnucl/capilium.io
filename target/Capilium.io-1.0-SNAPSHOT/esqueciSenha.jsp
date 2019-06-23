<%@include file="util/header.jsp" %>
<body>
    <%@include file="util/navbar-out.jsp" %>
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
        <h3>Esqueci minha senha</h3>
        <hr class="my-2">
        <form method="POST" action="EditarUsuario">
            <h4>Dados de acesso</h4>
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label>Login</label>
                    <input type="text" name="login" class="form-control" id="inputLogin" placeholder="" maxlength="15" required>
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
                    <button type="submit" class="btn btn-outline-primary"><span class="fa fa-check-square-o fa-fw"></span>Alterar senha</button>
                    <a class="btn btn-outline-secondary" href="javascript:history.back();"><span class="fa fa-arrow-left fa-fw"></span>Voltar</a>
                </div>
            </div>
        </form>
    </div>
</body>
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
<%@include file="util/footer.jsp" %> 
<%@include file="util/mascara-input.jsp" %>