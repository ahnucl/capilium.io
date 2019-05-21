<%@include file="util/header.jsp" %>
<%@include file="util/session.jsp"%> 
<body>
    <%@include file="util/navbar-in-user.jsp" %>
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
            String matricula = "";
            String nome = "";
            String idFuncionario = "";
            
            if (request.getAttribute("matricula")!= null) {
                matricula = (String) request.getAttribute("matricula"); 
                nome = (String) request.getAttribute("nome");
                idFuncionario = (String) request.getAttribute("idFuncionario");
            }   
            
        %>
        <h3>Cadastro de Funcionario</h3>

        <form method="POST" <%= matricula.isEmpty()||matricula==null ? "action='CadastrarFuncionario'":"action='EditarFuncionario'" %> >
            <div class="form-row">
                <div class="form-group col-md-6">
                    <input type="text" name="idFuncionario" class="form-control" id="idFuncionario" hidden 
                           <%= idFuncionario.isEmpty()||idFuncionario==null ? "":"value='"+idFuncionario+"'" %> >
                    <label for="inputEmail4">Matricula</label>
                    <input type="text" name="matricula" class="form-control" id="inputMatricula" placeholder="Matricula" maxlength="25" required
                           <%= matricula.isEmpty()||matricula==null ? "":"value='"+matricula+"'" %> >
                </div>
                <div class="form-group col-md-6">
                    <label for="inputPassword4">Nome Completo</label>
                    <input type="text" name="nome" class="form-control" id="inputNome" placeholder="Digite o nome" maxlength="100" required
                           <%= nome.isEmpty()||nome==null ? "":"value='"+nome+"'" %> >
                </div>
                <div class="text-center form-group col-md-12">
                    <button type="submit" class="btn btn-outline-primary"><span class="fa fa-check-square-o fa-fw"></span>Salvar</button>
                    <a class="btn btn-outline-secondary" href="javascript:history.back();"><span class="fa fa-arrow-left fa-fw"></span>Voltar</a>
                </div>
        </form>
    </div>
</body>
<%@include file="util/footer.jsp" %> 