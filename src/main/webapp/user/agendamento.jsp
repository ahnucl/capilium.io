<%-- 
    Document   : agendamento
    Created on : 09/06/2019, 13:42:18
    Author     : Leo
--%>
        
<%@include file="../util/header.jsp" %> 
    <body>
        <script src="/capilium.io/js/agendamento.js" type="text/javascript"></script>
        <%@include file="../util/navbar-in-user.jsp" %>

        <div id="main" class="container-fluid bg-light p-3" style="margin-top: 4em">
            <h3>Agendamento</h3>
            
            <form class=" ">
                <table class="table w-25">
                    <tr>
                        <td> <label>Funcionário:</label> </td>
                        <td> <select onChange="carregarAgenda()" id="funcionario" name="funcionario"></select> </td>
                    </tr>
                    <tr>
                        <td><label>Cliente:</label></td>
                        <!-- Colocar uma lógica pra esse campo ficar travado se for um cliente logado. Se for o usuário terminal, deixar liberado. -->
                        <td><select id="cliente" name="cliente"></select></td>
                    </tr>
                </table>
                <div class="pb-4">
                    <input type="text" id="data_atendimento" readonly/>
                    <input type="text" id="horario_inicio" readonly/>
                    
                </div>
                <div id="agenda" class="table w-100"></div>
                
            </form>
            
        </div>
    </body>
<%@include file="../util/footer.jsp" %> 
