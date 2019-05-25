<%@include file="util/header.jsp" %> 
<%@include file="util/navbar-out.jsp" %> 
<body>
    <div class="container" style="margin-top: 4em">
        <%
            String msg = "";
            String verde = "";
            msg = (String) request.getAttribute("msg"); // Mensagem de aviso ou validaçõe que vem da servlets
            verde = (String) request.getAttribute("verde"); // Mensagem de aviso ou validaçõe que vem da servlets
            
            boolean show = (msg == null || msg.isEmpty()) ? false : true;
            if (show) {
        %>

        <div <%=(verde == null || verde.isEmpty()) ? "class='alert alert-danger alert-dismissible fade show'" : "class='alert alert-success alert-dismissible fade show'"%> role="alert">
            <strong><%= msg%></strong>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <%
            }
        %>
        <div class="row">
            <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                <div class="card card-signin my-5">
                    <div class="card-body">
                        <h5 class="card-title text-center">Capillum.io</h5>
                        <!-- action="UsuarioControllers" -->
                        <form class="form-signin" method="POST" action="UsuarioControllers">
                            <div class="form-label-group">
                                <input type="text" id="inputLogin" name="login" class="form-control" placeholder="Digite seu usuário" required autofocus>
                            </div>

                            <div class="form-label-group pt-2">
                                <input type="password" id="inputSenha" name="senha" class="form-control" placeholder="Digite sua senha" required>
                            </div>
                            <hr class="my-2">
                            <div class="pb-3 text-right">
                                <a  href="client/esqueciSenha.jsp">Esqueceu sua senha?</a>
                            </div>
                            <button class=" btn btn-lg btn-primary btn-block text-uppercase" type="submit"><span class="fa fa-sign-in fa-fw"></span>Entrar</button>
                        </form>
                    </div>
                </div>
            </div>
            
        </div>
    </div>
</body>
<%@include file="util/footer.jsp" %> 