<%@page import="br.com.controller.Cliente"%>
<%@page import="br.com.DAO.ManterClienteDAO"%>
<%@page import="java.util.ArrayList"%>
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
                        <input type="text" class="form-control" name="busca" placeholder="Informe o nome CPF">
                        <div class="input-group-append">
                            <button class="btn btn-outline-primary" type="submit"><span class="fa fa-search fa-fw"></span></button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="text-right col-sm-3 pr-4">
                <!--<a href="/capilium.io/user/form-cliente.jsp" class="btn btn-outline-success h2"><span class="fa fa-plus fa-fw"></span>Cadastrar Cliente</a>-->
            </div>
        </div> 

        <div class="table-responsive col-sm-12">
            <h3>Clientes</h3>
            <table class="table table-striped" cellspacing="0" cellpadding="0">
                <thead>
                    <tr>
                        <th>CPF</th>
                        <th>Nome</th>
                        <th>Telefone</th>
                        <th class="actions">Ações</th>
                    </tr>
                </thead>
                <%
                    ManterClienteDAO daoCliente = new ManterClienteDAO();
                    Cliente cliente = new Cliente();

                    ArrayList<Cliente> listaCliente = daoCliente.pesquisarTudo();

                    for (int i = 0; i < listaCliente.size(); i++) {
                        cliente = listaCliente.get(i);
                        /*Criar uma session para armazenar o valor do id 
                        para realizar a exclusão ou alteração do cliente selecionado*/
                        session.setAttribute("id", String.valueOf(cliente.getIdCliente())); 
                %>
                <tr>
                    <td class="mascara-cpf"><%=cliente.getCpf()%></td>
                    <td><%=cliente.getNome()%></td>
                    <td><%=cliente.getTelefone()%></td>
                    <td class="actions">
                        <a class="btn btn-outline-success btn-xs" <%="href='/capilium.io/BuscarCliente?id="+String.valueOf(cliente.getIdCliente())+"&view=1'"%>><span class="fa fa-eye fa-fw"></span></a>
<!--                        <a class="btn btn-outline-warning btn-xs" <%="href='/capilium.io/BuscarCliente?id="+String.valueOf(cliente.getIdCliente())+"'"%>><span class="fa fa-pencil fa-fw"></span></a>
                        <a class="delete btn btn-outline-danger btn-xs"  <%="href='/capilium.io/DeletarCliente?id="+String.valueOf(cliente.getIdCliente())+"'"%>> data-toggle="modal" data-target="#delete-modal" <span class="fa fa-trash-o fa-fw"></span></a>-->
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
                    <h4 class="modal-title" id="modalLabel">Excluir Cliente</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-body">
                    <strong >Deseja realmente prosseguir?</strong></br>
                    Após a confirmação essa ação não poderá ser desfeita.
                </div>
                <form method="POST" <%="action='/capilium.io/DeletarCliente'"%>>
                    <div class="modal-footer">
                        <input type="hidden" name="id" id="clienteId" 
                               <%="value='" + String.valueOf(session.getAttribute("id")) + "'"%>/>
                        <button type="submit" class="btn btn-primary">Sim</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">N&atilde;o</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
<%@include file="../util/footer.jsp" %> 
<%@include file="../util/mascara-input.jsp" %>
