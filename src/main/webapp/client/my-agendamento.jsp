<%-- 
    Document   : confirma
    Created on : 09/06/2019, 13:42:44
    Author     : Leo
--%>
<%@page import="br.com.DAO.ManterClienteDAO"%>
<%@page import="br.com.DAO.ManterAtendimentoDAO"%>
<%@page import="br.com.DAO.AtendimentoServicoDAO"%>
<%@page import="br.com.DAO.ManterFuncionarioDAO"%>
<%@page import="br.com.controller.Cliente"%>
<%@page import="br.com.controller.Atendimento"%>
<%@page import="br.com.controller.AtendimentoServico"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.controller.Funcionario"%>

<%@include file="../util/header.jsp" %> 
    <body>
        <%@include file="../util/navbar-in-client.jsp" %>
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
        
        <div class="table-responsive col-sm-12">
            <h3>Atendimentos</h3>
            <table class="table table-striped" cellspacing="0" cellpadding="0">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Data do atendimento</th>
                        <th>Início do atendimento</th>
                        <th>Fim do atendimento</th>
                        <th>Total</th>
                        <th>Cliente</th>
                        <th>Cabeleireiro</th>
                        <!--<th class="actions">Ações</th>-->
                    </tr>
                </thead>
                <%
                    ManterAtendimentoDAO daoAt = new ManterAtendimentoDAO();
                    ManterFuncionarioDAO daoF = new ManterFuncionarioDAO();
                    ManterClienteDAO daoC = new ManterClienteDAO();
                    Atendimento at = new Atendimento();
                    Funcionario f ;
                    Cliente c;
                    ArrayList<Atendimento> listaAt = daoAt.pesquisarOsMeus((String)session.getAttribute("idClienteLogado"));

                    for (int i = 0; i < listaAt.size(); i++) {
                        at = listaAt.get(i);
                        /*Criar uma session para armazenar o valor do id 
                        para realizar a exclusão ou alteração do funcionario selecionado*/
//                        session.setAttribute("id", String.valueOf(funcionario.getIdFuncionario()));
                        f = daoF.pesquisar(at.getIdFuncionario());
                        c = daoC.pesquisar(at.getIdCliente());
                       
                %>
                <tr>
                    <td><%=at.getIdAtendimento() %></td>
                    <td><%=at.getDataAtendimento()%></td>
                    <td><%=at.getHorarioInicio()%></td>
                    <td><%=at.getHorarioFim()%></td>
                    <td><%=at.getValorTotal()%></td>
                    <td><%=c.getNome() + "/CPF: " + c.getCpf() %></td>
                    <td><%=f.getNome()+ "/Matrícula nº " +f.getMatricula() %></td>

                </tr>

                
                <%}
                %>
                </tbody>
            </table>
        </div>
<!--                <input  class="form-control" type="text" value="<%= session.getAttribute("idUsuario")%>">
                <input  class="form-control" type="text" value="<%= session.getAttribute("idClienteLogado")%>">-->
        
        
        
    </body>
<%@include file="../util/footer.jsp" %> 