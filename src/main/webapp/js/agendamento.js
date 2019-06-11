$(document).ready(function(){    
    $.getJSON('../AjaxServlet?action=funcionario', function (dados){       
        $("#funcionario").empty();  
        if (dados.length > 0) {    
            
            var option = '<option value="">Selecione o cabeleireiro</option>';            
            $.each(dados, function(i, obj){                
                option += '<option value="'+obj.idFuncionario+'">' + obj.nome + ' / MAT: ' + obj.matricula + '</option>';
            });     
            $('#funcionario').html(option).show();
        }            
    });

});

$(document).ready(function(){    
    $.getJSON('../AjaxServlet?action=cliente', function (dados){       
        $("#cliente").empty();  
        if (dados.length > 0) {    
            
            var option = '<option value="">Selecione o cliente</option>';            
            $.each(dados, function(i, obj){                
                option += '<option value="'+obj.idCliente+'">' + obj.nome + ' / CPF: ' + obj.cpf+ '</option>';
            });     
            $('#cliente').html(option).show();
        }            
    });

});

function getSunday(d) {
  d = new Date(d);
  var day = d.getDay(),
      diff = d.getDate() - day ; //+ (day == 0 ? -6:1); // adjust when day is sunday
  return new Date(d.setDate(diff));
}

function selecAgenda(h){
    
    console.log(h.id);
    var selectedId = h.id;
    var idHorario = selectedId.substring(0,3);
    var idDia = selectedId.substring(3);
//    console.log(selectedId);
//    console.log(idHorario);
//    console.log(idDia);
   
    // Mudar a Margem da celula
    // Caluclar a hora de início
    // Calcular o dia 
    document.getElementById("data_atendimento").value = idDia;
    document.getElementById("horario_inicio").value = idHorario;
    
}
function carregarAgenda(){
     var idFuncionario = document.getElementById('funcionario').value;
     
     var d = getSunday(new Date());
     var domingo = d;
     var gradeHoraria = '<h3>Agenda semanal</h3><table class="table" border=1>'
+'		<thead class="tab-head">'
+'			<th></th>'
+'			<th id ="d1" class="dia-semana text-center">Domingo '+ d.getDate()+'/'+(d.getMonth()+1 )+'</th>';
     d.setDate(d.getDate()+1);
     var segunda = d;
     gradeHoraria +=  '<th id = "d2" class="dia-semana text-center">Segunda '+ d.getDate()+'/'+(d.getMonth()+1) +'</th>';
     d.setDate(d.getDate()+1);
     var terca = d;
     gradeHoraria +=  '<th id = "d3" class="dia-semana text-center">Quinta '+ d.getDate() +'/'+(d.getMonth()+1) +'</th>';
     d.setDate(d.getDate()+1);
     var quarta = d;
     gradeHoraria +=  '<th id = "d4" class="dia-semana text-center">Quarta '+ d.getDate() +'/'+(d.getMonth()+1) +'</th>';
     d.setDate(d.getDate()+1);
     var quinta = d;
     gradeHoraria +=  '<th id = "d5" class="dia-semana text-center">Terça '+ d.getDate()  +'/'+(d.getMonth()+1) +'</th>';
     d.setDate(d.getDate()+1);
     var sexta = d;
     gradeHoraria +=  '<th id = "d6" class="dia-semana text-center">Sexta '+ d.getDate()  +'/'+(d.getMonth()+1) +'</th>';
     d.setDate(d.getDate()+1);
     var sabado = d;
     gradeHoraria +=  '<th id = "d7" class="dia-semana text-center">Sábado '+ d.getDate() +'/'+(d.getMonth()+1) +'</th>';
     
     gradeHoraria +=  '</thead>'
                    +'<tbody>'
                    +'			<tr>'
+'				<td id="h01" >08:00</td>'
+'				<td id="h01d1" onClick="selecAgenda(this)"></td>'
+'				<td id="h01d2" onClick="selecAgenda(this)"></td>'
+'				<td id="h01d3" onClick="selecAgenda(this)"></td>'
+'				<td id="h01d4" onClick="selecAgenda(this)"></td>'
+'				<td id="h01d5" onClick="selecAgenda(this)"></td>'
+'				<td id="h01d6" onClick="selecAgenda(this)"></td>'
+'				<td id="h01d7" onClick="selecAgenda(this)"></td>'
+'			</tr>			'
+'			<tr>'
+'				<td id="h02">09:00</td>'
+'				<td id="h02d1" onClick="selecAgenda(this)"></td>'
+'				<td id="h02d2" onClick="selecAgenda(this)"></td>'
+'				<td id="h02d3" onClick="selecAgenda(this)"></td>'
+'				<td id="h02d4" onClick="selecAgenda(this)"></td>'
+'				<td id="h02d5" onClick="selecAgenda(this)"></td>'
+'				<td id="h02d6" onClick="selecAgenda(this)"></td>'
+'				<td id="h02d7" onClick="selecAgenda(this)"></td>'
+'			</tr>			'
+'			<tr>'
+'				<td id="h03">10:00</td>'
+'				<td id="h03d1" onClick="selecAgenda(this)"></td>'
+'				<td id="h03d2" onClick="selecAgenda(this)"></td>'
+'				<td id="h03d3" onClick="selecAgenda(this)"></td>'
+'				<td id="h03d4" onClick="selecAgenda(this)"></td>'
+'				<td id="h03d5" onClick="selecAgenda(this)"></td>'
+'				<td id="h03d6" onClick="selecAgenda(this)"></td>'
+'				<td id="h03d7" onClick="selecAgenda(this)"></td>'
+'			</tr>			'
+'			<tr>'
+'				<td id="h04">11:00</td>'
+'				<td id="h04d1" onClick="selecAgenda(this)"></td>'
+'				<td id="h04d2" onClick="selecAgenda(this)"></td>'
+'				<td id="h04d3" onClick="selecAgenda(this)"></td>'
+'				<td id="h04d4" onClick="selecAgenda(this)"></td>'
+'				<td id="h04d5" onClick="selecAgenda(this)"></td>'
+'				<td id="h04d6" onClick="selecAgenda(this)"></td>'
+'				<td id="h04d7" onClick="selecAgenda(this)"></td>'
+'			</tr>			'
+'			<tr>'
+'				<td id="h05">12:00</td>'
+'				<td id="h05d1" onClick="selecAgenda(this)"></td>'
+'				<td id="h05d2" onClick="selecAgenda(this)"></td>'
+'				<td id="h05d3" onClick="selecAgenda(this)"></td>'
+'				<td id="h05d4" onClick="selecAgenda(this)"></td>'
+'				<td id="h05d5" onClick="selecAgenda(this)"></td>'
+'				<td id="h05d6" onClick="selecAgenda(this)"></td>'
+'				<td id="h05d7" onClick="selecAgenda(this)"></td>'
+'			</tr>			'
+'			<tr>'
+'				<td id="h06">13:00</td>'
+'				<td id="h06d1" onClick="selecAgenda(this)"></td>'
+'				<td id="h06d2" onClick="selecAgenda(this)"></td>'
+'				<td id="h06d3" onClick="selecAgenda(this)"></td>'
+'				<td id="h06d4" onClick="selecAgenda(this)"></td>'
+'				<td id="h06d5" onClick="selecAgenda(this)"></td>'
+'				<td id="h06d6" onClick="selecAgenda(this)"></td>'
+'				<td id="h06d7" onClick="selecAgenda(this)"></td>'
+'			</tr>			'
+'			<tr>'
+'				<td id="h07">14:00</td>'
+'				<td id="h07d1" onClick="selecAgenda(this)"></td>'
+'				<td id="h07d2" onClick="selecAgenda(this)"></td>'
+'				<td id="h07d3" onClick="selecAgenda(this)"></td>'
+'				<td id="h07d4" onClick="selecAgenda(this)"></td>'
+'				<td id="h07d5" onClick="selecAgenda(this)"></td>'
+'				<td id="h07d6" onClick="selecAgenda(this)"></td>'
+'				<td id="h07d7" onClick="selecAgenda(this)"></td>'
+'			</tr>			'
+'			<tr>'
+'				<td id="h08">15:00</td>'
+'				<td id="h08d1" onClick="selecAgenda(this)"></td>'
+'				<td id="h08d2" onClick="selecAgenda(this)"></td>'
+'				<td id="h08d3" onClick="selecAgenda(this)"></td>'
+'				<td id="h08d4" onClick="selecAgenda(this)"></td>'
+'				<td id="h08d5" onClick="selecAgenda(this)"></td>'
+'				<td id="h08d6" onClick="selecAgenda(this)"></td>'
+'				<td id="h08d7" onClick="selecAgenda(this)"></td>'
+'			</tr>	'
+'			<tr>'
+'				<td id="h09">16:00</td>'
+'				<td id="h09d1" onClick="selecAgenda(this)"></td>'
+'				<td id="h09d2" onClick="selecAgenda(this)"></td>'
+'				<td id="h09d3" onClick="selecAgenda(this)"></td>'
+'				<td id="h09d4" onClick="selecAgenda(this)"></td>'
+'				<td id="h09d5" onClick="selecAgenda(this)"></td>'
+'				<td id="h09d6" onClick="selecAgenda(this)"></td>'
+'				<td id="h09d7" onClick="selecAgenda(this)"></td>'
+'			</tr>	'
+'			<tr>'
+'				<td id="h10">17:00</td>'
+'				<td id="h10d1" onClick="selecAgenda(this)"></td>'
+'				<td id="h10d2" onClick="selecAgenda(this)"></td>'
+'				<td id="h10d3" onClick="selecAgenda(this)"></td>'
+'				<td id="h10d4" onClick="selecAgenda(this)"></td>'
+'				<td id="h10d5" onClick="selecAgenda(this)"></td>'
+'				<td id="h10d6" onClick="selecAgenda(this)"></td>'
+'				<td id="h10d7" onClick="selecAgenda(this)"></td>'
+'			</tr>	'
+'			<tr>'
+'				<td id="h11">18:00</td>'
+'				<td id="h11d1" onClick="selecAgenda(this)"></td>'
+'				<td id="h11d2" onClick="selecAgenda(this)"></td>'
+'				<td id="h11d3" onClick="selecAgenda(this)"></td>'
+'				<td id="h11d4" onClick="selecAgenda(this)"></td>'
+'				<td id="h11d5" onClick="selecAgenda(this)"></td>'
+'				<td id="h11d6" onClick="selecAgenda(this)"></td>'
+'				<td id="h11d7" onClick="selecAgenda(this)"></td>'
+'			</tr>	'
+'		</tbody>'
+'	</table>';
     
     if (idFuncionario === "") {
      $('#agenda').hide();
    } else { $('#agenda').html(gradeHoraria).show(); }
}