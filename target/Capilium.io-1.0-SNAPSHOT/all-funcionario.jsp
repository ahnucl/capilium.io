<%@page import="br.com.controller.Usuario"%>
<%@page import="br.com.DAO.ManterUsuarioDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.controller.Funcionario"%>
<%@page import="br.com.DAO.ManterFuncionarioDAO"%>
<%@include file="util/header.jsp" %> 
<body>
    <%@include file="util/navbar-in.jsp" %>


    <div id="main" class="container-fluid bg-light pt-3 pr-4 pl-4" style="margin-top: 80px">

        <div id="top" class="row">
            <div class="col-sm-9 pl-4">
                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="Informe o nome ou a matricula" aria-label="Recipient's username" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                        <button class="btn btn-outline-primary" type="button"><span class="fa fa-search fa-fw"></span></button>
                    </div>
                </div>
            </div>
            <div class="text-right col-sm-3 pr-4">
                <a href="insert-funcionario.jsp" class="btn btn-outline-success h2"><span class="fa fa-plus fa-fw"></span>Cadastrar funcionario</a>
            </div>
        </div> 

        <div class="table-responsive col-sm-12">
            <h3>Funcionario</h3>
            <table class="table table-striped" cellspacing="0" cellpadding="0">
                <thead>
                    <tr>
                        <th>Matricula</th>
                        <th>Nome</th>
                        <th class="actions">Ações</th>
                    </tr>
                </thead>
                <%
                    ManterFuncionarioDAO daoFuncionario = new ManterFuncionarioDAO();
                    Funcionario funcionario = new Funcionario();
                    // Descomentar essas linhas se for fazer o array sem usar o inner join na listagem
                    //ManterUsuarioDAO daoUsuario = new ManterUsuarioDAO();
                    //Usuario usuario = new Usuario();

                    ArrayList<Funcionario> listaFuncionario = daoFuncionario.pesquisarTudo();

                    for (int i = 0; i < listaFuncionario.size(); i++) {

                        funcionario = listaFuncionario.get(i);
                        funcionario.getUsuario().getIdUsuario();
                        //String.valueOf(funcionario.getIdFuncionario());
                        //usuario = daoUsuario.pesquisar(cod);
                %>
                <tr>
                    <td><%=funcionario.getMatricula()%></td>
                    <td><%=funcionario.getUsuario().getNome()%></td>
                    <td class="actions">
                        <a class="btn btn-success btn-xs" href="#"><span class="fa fa-eye fa-fw"></span></a>
                        <a class="btn btn-warning btn-xs" href="#"><span class="fa fa-pencil fa-fw"></span></a>
                        <a class="delete btn btn-danger btn-xs"  href="#" <%="data-id=" + String.valueOf(funcionario.getIdFuncionario())%> data-toggle="modal" data-target="#delete-modal"><span class="fa fa-trash-o fa-fw"></span></a>
                    </td>
                </tr>
                <%}
                %>
                </tbody>
            </table>
        </div>

    </div> <!-- /#list -->


    <!-- Modal -->
    <div class="modal fade" id="delete-modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="modalLabel">Excluir Funcionario</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-body">
                    <strong >Deseja realmente prosseguir?</strong></br>
                    Após a confirmação essa ação não poderá ser desfeita?
                </div>
                <form method="POST" action="Deletar">
                    <div class="modal-footer">
                        <input type="hidden" name="id" id="funcionarioId" 
                               value="<script>document.getElementById('idfuncionario');</script>"/>
                        <button type="submit" class="btn btn-primary">Sim</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">N&atilde;o</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $(function () {
            $(".delete").click(function () {
                $('#funcionarioId').val($(this).data('id'));
                document.getElementById("idfuncionario").innerHTML = $(this).data('id');
            });
        });
    </script>
</body>
<%@include file="util/footer.jsp" %> 
