package br.com.DAO;

import br.com.controller.Atendimento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ManterAtendimentoDAO extends DAO {

    /*Variaveis globais para usar em todas as Query,
    para assim ficar mais facil se mudar o nome da coluna na tabela*/
    public static String NOME_TABELA_ATENDIMENTO = "Atendimento"; //nome da tabela
    public static String ID_ATENDIMENTO = "idAtendimento"; // PK da tablea
    public static String HORARIO_INICIO_ATENDIMENTO = "horarioInicio";
    public static String HORARIO_FIM_ATENDIMENTO = "horarioFim";
    public static String VALOR_TOTAL_ATENDIMENTO = "valorTotal";
    public static String DATA_ATENDIMENTO_ATENDIMENTO = "dataAtendimento";
    public static String ID_CLIENTE_ATENDIMENTO = "idCliente";// FK da tabela
    public static String ID_FUNCIONARIO_ATENDIMENTO = "idFuncionario";// FK da tabela

    public void inserir(Atendimento atendimento) throws Exception {
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
            pst.setInt(5, atendimento.getCliente().getIdCliente());
            pst.setInt(5, atendimento.getFuncionario().getIdFuncionario());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
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
}
