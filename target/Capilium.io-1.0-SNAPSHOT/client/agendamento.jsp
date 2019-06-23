<%-- 
    Document   : agendamento
    Created on : 09/06/2019, 13:42:18
    Author     : Leo
--%>
        
<%@include file="../util/header.jsp" %> 
    <body>
        <script src="/capilium.io/js/agendamento.js" type="text/javascript"></script>
        <%@include file="../util/navbar-in-client.jsp" %>

        <div id="main" class="container-fluid bg-light p-3" style="margin-top: 4em">
            <h3>Agendamento</h3>
            
            <form class="needs-validation" method="POST" action="../CadastrarAgendamento" novalidate>
                <input id="clienteLogado" class="form-control" hidden="" value="<%=session.getAttribute("idClienteLogado")%>" name="cliente" >
                <div class=" w-50" >
                    <div class="form-group row">
                         <label class="col-lg-4 col-form-label">Funcionário:</label> 
                         <div class="col-lg-8">
                             <select onChange="carregarHorarios()" id="funcionario" class="form-control" name="funcionario"></select> 
                         </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">Data do atendimento:</label>
                        <div class="col-sm-8">
                            <input id="dataAtendimento" onChange="carregarHorarios()" type="date" class="form-control" name="dataAtendimento"/>
                        </div>                        
                    </div>
                    <div class="pb-4 row">
                        <div id="servico" class=" col-sm-6 "  >

                            <div class="invalid-feedback">Selecione o serviço.</div>
                        </div>
                        <div id="agenda" class="col-sm-6"></div>
                        
                    </div>
                </div>
            </form>
        </div>
        <script>
            // Example starter JavaScript for disabling form submissions if there are invalid fields
            (function() {
              'use strict';
              window.addEventListener('load', function() {
                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.getElementsByClassName('needs-validation');
                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function(form) {
                  form.addEventListener('submit', function(event) {
                    if (form.checkValidity() === false) {
                      event.preventDefault();
                      event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                  }, false);
                });
              }, false);
            })();
            
            // Definir data mínima como "hoje"
            var today = new Date();
            var dd = today.getDate();
            var mm = today.getMonth()+1; //January is 0!
            var yyyy = today.getFullYear();
             if(dd<10){
                    dd='0'+dd;
                } 
                if(mm<10){
                    mm='0'+mm;
                } 

            today = yyyy+'-'+mm+'-'+dd;
            document.getElementById("dataAtendimento").setAttribute("min", today);
            
        </script>
    </body>
<%@include file="../util/footer.jsp" %> 
