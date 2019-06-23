package br.com.DAO;

import br.com.controller.Atendimento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ManterAtendimentoDAO extends DAO {

    /*Variaveis globais para usar em todas as Query,
    para assim ficar mais facil se mudar o nome da coluna na tabela*/
    public static final String NOME_TABELA_ATENDIMENTO = "Atendimento"; //nome da tabela
    public static final String ID_ATENDIMENTO = "idAtendimento"; // PK da tablea
    public static final String HORARIO_INICIO_ATENDIMENTO = "horarioInicio";
    public static final String HORARIO_FIM_ATENDIMENTO = "horarioFim";
    public static final String VALOR_TOTAL_ATENDIMENTO = "valorTotal";
    public static final String DATA_ATENDIMENTO_ATENDIMENTO = "dataAtendimento";
    public static final String ID_CLIENTE_ATENDIMENTO = "idCliente";// FK da tabela
    public static final String ID_FUNCIONARIO_ATENDIMENTO = "idFuncionario";// FK da tabela

    public int inserir(Atendimento atendimento) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO " + NOME_TABELA_ATENDIMENTO + " (" + ID_ATENDIMENTO + ", " + HORARIO_INICIO_ATENDIMENTO + ", " + HORARIO_FIM_ATENDIMENTO + ", "
                    + VALOR_TOTAL_ATENDIMENTO + ", " + DATA_ATENDIMENTO_ATENDIMENTO + ", " + ID_CLIENTE_ATENDIMENTO + ", " + ID_FUNCIONARIO_ATENDIMENTO + ") "
                    + "VALUES(NULL, ?, ?, ?, ?, ?, ?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, atendimento.getHorarioInicio());
            pst.setString(2, atendimento.getHorarioFim());
            pst.setFloat(3, atendimento.getValorTotal());
            pst.setString(4, atendimento.getDataAtendimento());
//            pst.setInt(5, atendimento.getCliente().getIdCliente());
//            pst.setInt(6, atendimento.getFuncionario().getIdFuncionario());
            pst.setInt(5, atendimento.getIdCliente());
            pst.setInt(6, atendimento.getIdFuncionario());
            pst.execute();
            
            // buscar o ultimo ID na cadastrado na tabela durante a mesma conexão
            String queryReturnId = "SELECT LAST_INSERT_ID() as id FROM " + NOME_TABELA_ATENDIMENTO;
            pst = con.prepareStatement(queryReturnId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return (int) rs.getInt("id");
            }
            
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return 0;
    }

    public void deletar(int id) throws Exception {
        abrirBanco();
        String query = "DELETE FROM " + NOME_TABELA_ATENDIMENTO + " WHERE " + ID_ATENDIMENTO + " = ?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setInt(1, id);
        pst.execute();
        fecharBanco();
    }
    //Não está concluido.
    public boolean verificaHorarioDeAtendimento(Atendimento atendimento) throws Exception {
        try {
            abrirBanco();
            /*Verificar se o horario está disponivel, para não ter 
            o mais de um agendamento no mesmo horario e no mesmo cabeleleiro */
            String query = "SELECT COUNT(*) valor \n"
                    + "FROM " + NOME_TABELA_ATENDIMENTO + " \n"
                    + "WHERE " + HORARIO_INICIO_ATENDIMENTO + " = ?"
                    + "" + HORARIO_FIM_ATENDIMENTO + " = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, atendimento.getHorarioInicio());
            pst.setString(2, atendimento.getHorarioFim());
            pst.setInt(3, atendimento.getFuncionario().getIdFuncionario());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getBoolean("valor");
            }

            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return false;
    }
    
    public ArrayList<Atendimento> atendimentosDoFuncionarioNoDia(int idFuncionario, String dataAtendimento){
        ArrayList<Atendimento> lista = new ArrayList<Atendimento>();
        try {
            abrirBanco();
            String query = "SELECT * FROM " + NOME_TABELA_ATENDIMENTO 
                         + " WHERE " + ID_FUNCIONARIO_ATENDIMENTO + " = " + idFuncionario 
                         + " AND " + DATA_ATENDIMENTO_ATENDIMENTO + " = '" + dataAtendimento + "'";            

            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Atendimento a;
            while(rs.next()){
                a = new Atendimento();
                a.setIdAtendimento(rs.getInt(ID_ATENDIMENTO));
                a.setHorarioInicio(rs.getString(HORARIO_INICIO_ATENDIMENTO));
                a.setHorarioFim(rs.getString(HORARIO_FIM_ATENDIMENTO));
                a.setValorTotal(rs.getFloat(VALOR_TOTAL_ATENDIMENTO));
                a.setDataAtendimento(rs.getString(DATA_ATENDIMENTO_ATENDIMENTO));
                a.setIdCliente(rs.getInt(ID_CLIENTE_ATENDIMENTO));
                a.setIdFuncionario(rs.getInt(ID_FUNCIONARIO_ATENDIMENTO));
                lista.add(a);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        
        return lista;
    }
    
    public ArrayList<Atendimento> pesquisarTudo() throws Exception {
        ArrayList<Atendimento> lista = new ArrayList<Atendimento>();
        try {
            abrirBanco();
            String query = 
                    "SELECT * "
                    + "FROM " + NOME_TABELA_ATENDIMENTO + " "
                    + "ORDER BY "
                    + ID_ATENDIMENTO + " DESC, "
                    + DATA_ATENDIMENTO_ATENDIMENTO + " DESC, "
                    + HORARIO_INICIO_ATENDIMENTO + " DESC "
                    ;

//                    "select at.*, CONCAT(c.nome,' - ',c.cpf) as nome_cliente, f.nome as nome_funcionario "
//                  + "from "+ NOME_TABELA_ATENDIMENTO + " at "
//                  + "left join cliente c on c.idCliente = at.idCliente "
//                  + "left join funcionario f on f.idFuncionario = at.idFuncionario "
//                  + "ORDER BY at.dataAtendimento "  ;
            
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Atendimento atbean;

            while (rs.next()) {
                atbean = new Atendimento();

                atbean.setIdAtendimento(rs.getInt(ID_ATENDIMENTO));
                atbean.setHorarioInicio(rs.getString(HORARIO_INICIO_ATENDIMENTO));
                atbean.setHorarioFim(rs.getString(HORARIO_FIM_ATENDIMENTO));
                atbean.setValorTotal(rs.getFloat(VALOR_TOTAL_ATENDIMENTO));
                atbean.setDataAtendimento(rs.getString(DATA_ATENDIMENTO_ATENDIMENTO));
                atbean.setIdCliente(rs.getInt(ID_CLIENTE_ATENDIMENTO));
                atbean.setIdFuncionario(rs.getInt(ID_FUNCIONARIO_ATENDIMENTO));

                lista.add(atbean);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return lista;
    }
    public ArrayList<Atendimento> pesquisarOsMeus(String idCliente) throws Exception {
        ArrayList<Atendimento> lista = new ArrayList<Atendimento>();
        try {
            abrirBanco();
            String query = 
                    "SELECT * "
                    + "FROM " + NOME_TABELA_ATENDIMENTO + " WHERE " + ID_CLIENTE_ATENDIMENTO + " = ? "
                    + "ORDER BY " 
                    + ID_ATENDIMENTO + " DESC, "
                    + DATA_ATENDIMENTO_ATENDIMENTO + " DESC, "
                    + HORARIO_INICIO_ATENDIMENTO + " DESC ";
           
            pst = con.prepareStatement(query);
            pst.setString(1, idCliente);
            ResultSet rs = pst.executeQuery();
            Atendimento atbean;

            while (rs.next()) {
                atbean = new Atendimento();

                atbean.setIdAtendimento(rs.getInt(ID_ATENDIMENTO));
                atbean.setHorarioInicio(rs.getString(HORARIO_INICIO_ATENDIMENTO));
                atbean.setHorarioFim(rs.getString(HORARIO_FIM_ATENDIMENTO));
                atbean.setValorTotal(rs.getFloat(VALOR_TOTAL_ATENDIMENTO));
                atbean.setDataAtendimento(rs.getString(DATA_ATENDIMENTO_ATENDIMENTO));
                atbean.setIdCliente(rs.getInt(ID_CLIENTE_ATENDIMENTO));
                atbean.setIdFuncionario(rs.getInt(ID_FUNCIONARIO_ATENDIMENTO));

                lista.add(atbean);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return lista;
    }

}