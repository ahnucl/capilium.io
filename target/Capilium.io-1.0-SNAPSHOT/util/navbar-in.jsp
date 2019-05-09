<nav class="navbar navbar-expand-md navbar-light fixed-top bg-light">
    <a class="navbar-brand" href="home.jsp">
        <img src="img/logo.jpg" width="30" height="30" class="d-inline-block align-top" alt="">
        Capillium.io
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav bd-navbar-nav flex-row">
            <li class="nav-item">
                <a class="nav-link" href="all-cliente.jsp">Clientes <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="all-funcionario.jsp">Funcionario</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="all-servico.jsp">Servi�os</a>
            </li>
            <!--<li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Dropdown
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>-->
        </ul>
        <ul class="navbar-nav flex-row ml-md-auto d-none d-md-flex">
            <li class="nav-item dropdown ">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="fa fa-user fa-fw"></span><%= session.getAttribute("nomeUsuarioLogado")%>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#"><span class="fa fa-pencil fa-fw"></span>Atualizar dados</a>
                    <a class="dropdown-item" href="#"><span class="fa fa-trash-o fa-fw"></span>Excluir conta</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="util/logoff.jsp"><span class="fa fa-sign-out fa-fw"></span>sair</a>
                </div>
            </li>
        </ul>
    </div>
</nav>