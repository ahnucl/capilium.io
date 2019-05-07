<%@include file="util/header.jsp" %> 
<body>
    <%@include file="util/navbar.jsp" %>


    <div id="main" class="container-fluid" style="margin-top: 10px">

        <div id="top" class="row">
            <div class="col-sm-12">
                <a href="#" class="btn btn-primary h2">Novo Item</a>
            </div>
        </div> <!-- /#top -->
        <hr class="col-sm-12" />


        <div class="table-responsive col-sm-12">
            <h3>Teste de listar</h3>
            <table class="table table-striped" cellspacing="0" cellpadding="0">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Contato</th>
                        <th class="actions">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1001</td>
                        <td>Lorem ipsum dolor sit amet, consectetur adipiscing</td>
                        <td>6516516</td>
                        <td class="actions">
                            <a class="btn btn-success btn-xs" href="#">Visualizar</a>
                            <a class="btn btn-warning btn-xs" href="#">Editar</a>
                            <a class="btn btn-danger btn-xs"  href="#" data-toggle="modal" data-target="#delete-modal">Excluir</a>
                        </td>
                    </tr>
                    <tr>
                        <td>1002</td>
                        <td>Lorem ipsum dolor sit amet, consectetur adipiscing</td>
                        <td>060650695</td>
                        <td class="actions">
                            <a class="btn btn-success btn-xs" href="#">Visualizar</a>
                            <a class="btn btn-warning btn-xs" href="#">Editar</a>
                            <a class="btn btn-danger btn-xs"  href="#" data-toggle="modal" data-target="#delete-modal">Excluir</a>
                        </td>
                    </tr>

                </tbody>
            </table>
        </div>

    </div> <!-- /#list -->


    <!-- Modal -->
    <div class="modal fade" id="delete-modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="modalLabel">Excluir Item</h4>
                </div>
                <div class="modal-body">
                    Deseja realmente excluir este item?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary">Sim</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">N&atilde;o</button>
                </div>
            </div>
        </div>
    </div>
</body>
<%@include file="util/footer.jsp" %> 
