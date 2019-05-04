<%@include file="util/header.jsp" %> 
<%@include file="util/navbar.jsp" %> 
<body>
    <div class="container">
        <div class="row">
            <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                <div class="card card-signin my-5">
                    <div class="card-body">
                        <h5 class="card-title text-center">Capillum.io</h5>
                         <!-- action="UsuarioControllers" -->
                        <form class="form-signin" method="POST">
                            <div class="form-label-group">
                                <input type="text" id="inputLogin" name="login" class="form-control" placeholder="Digite seu usuário" required autofocus>
                            </div>

                            <div class="form-label-group pt-2">
                                <input type="password" id="inputSenha" name="senha" class="form-control" placeholder="Digite sua senha" required>
                            </div>
                            <hr class="my-4">

                            <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Entrar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<%@include file="util/footer.jsp" %> 