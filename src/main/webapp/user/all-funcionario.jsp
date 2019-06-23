<%@page import="br.com.controller.Usuario"%>
<%@page import="br.com.DAO.ManterUsuarioDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.controller.Funcionario"%>
<%@page import="br.com.DAO.ManterFuncionarioDAO"%>
<%@include file="../util/header.jsp" %> 
<body>
    <%@include file="../util/navbar-in-user.jsp" %>
    <div id="main" class="container-fluid bg-light p-3" style="margin-top: 4em">


        <%
            session.removeAttribute("id");//limpando a session

            String msg = "";
            msg = (String) request.getAttribute("msg"); // Mensagem de aviso ou validaçõe que vem da servlets
            boolean show = (msg == null || msg.isEmpty()) ? false : true;

            if (show) {
        %>

        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <strong><%= msg%></strong>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <%
            }
        %>
        <div id="top" class="row">
            <div class="col-sm-9 pl-4">
                <div class="input-group mb-3">
                    <form class="input-group col-sm-12" method ="POST" action="#">
                        <input type="text" class="form-control" name="busca" placeholder="Informe o nome ou a matricula">
                        <div class="input-group-append">
                            <button class="btn btn-outline-primary" type="submit"><span class="fa fa-search fa-fw"></span></button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="text-right col-sm-3 pr-4">
                <a href="/capilium.io/user/form-funcionario.jsp" class="btn btn-outline-success h2"><span class="fa fa-plus fa-fw"></span>Cadastrar Funcionario</a>
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

                    ArrayList<Funcionario> listaFuncionario = daoFuncionario.pesquisarTudo();

                    for (int i = 0; i < listaFuncionario.size(); i++) {
                        funcionario = listaFuncionario.get(i);
                        /*Criar uma session para armazenar o valor do id 
                        para realizar a exclusão ou alteração do funcionario selecionado*/
//                        session.setAttribute("id", String.valueOf(funcionario.getIdFuncionario()));
                %>
                <tr>
                    <td><%=funcionario.getMatricula()%></td>
                    <td><%=funcionario.getNome()%></td>
                    <td class="actions">
                        <!--<a class="btn btn-outline-success btn-xs" <%="href='BuscarFuncionario?idFuncionario=" + String.valueOf(funcionario.getIdFuncionario()) + "&view=1'"%>><span class="fa fa-eye fa-fw"></span></a>-->
                        <a class="btn btn-outline-warning btn-xs" <%="href='/capilium.io/BuscarFuncionario?idFuncionario=" + String.valueOf(funcionario.getIdFuncionario()) + "'"%>><span class="fa fa-pencil fa-fw"></span></a>
                        <a class="delete btn btn-outline-danger btn-xs"  href="#" data-toggle="modal" data-target="#delete-modal"><span class="fa fa-trash-o fa-fw"></span></a>
                    </td>
                </tr>
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
                            <form method="POST" action="/capilium.io/DeletarFuncionario">
                                <div class="modal-footer">
                                    <input type="hidden" name="idFuncionario" id="idFuncionario" 
                                           <%="value='" + String.valueOf(funcionario.getIdFuncionario()) + "'"%>/>
                                    <button type="submit" class="btn btn-primary">Sim</button>
                                    <button type="button" class="btn btn-danger" data-dismiss="modal">N&atilde;o</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <%}
                %>
                </tbody>
            </table>
        </div>

    </div> <!-- /#list -->


<!--     Modal 
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
                <form method="POST" action="DeletarFuncionario">
                    <div class="modal-footer">
                        <input type="hidden" name="idFuncionario" id="funcionarioId" 
                               />
                        <button type="submit" class="btn btn-primary">Sim</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">N&atilde;o</button>
                    </div>
                </form>
            </div>
        </div>
    </div>-->
</body>
<%@include file="../util/footer.jsp" %> 
