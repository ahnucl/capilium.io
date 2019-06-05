package br.com.DAO;

import br.com.controller.Servico;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ManterServicoDAO extends DAO {

    /*Variaveis globais para usar em todas as Query,
    para assim ficar mais facil se mudar o nome da coluna na tabela*/
    public static String NOME_TABELA_SERVICO = "Servico"; //nome da tabela
    public static String ID_SERVICO = "idServico"; // PK da tablea
    public static String DESCRICAO_SERVICO = "descricao";
    public static String VALOR_SERVICO = "valor";
    public static String SUSPENSO_SERVICO = "se_suspenso";
    public static String TEMPO_MEDIO_ATENDIMENTO_SERVICO = "tempoMedioAtendimento";

    public void inserir(Servico servico) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO " + NOME_TABELA_SERVICO + " "
                    + "(" + ID_SERVICO + ", " + DESCRICAO_SERVICO + ", "
                    + VALOR_SERVICO + ", " + TEMPO_MEDIO_ATENDIMENTO_SERVICO + ", "
                    + SUSPENSO_SERVICO + ")"
                    + "VALUES(NULL, ?, ?, ?, ?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, servico.getDescricao());
            pst.setFloat(2, servico.getValor());
            pst.setString(3, servico.getTempoMedioAtendimento());
            pst.setBoolean(4, servico.getSuspenso());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public ArrayList<Servico> pesquisarTudo() throws Exception {
        ArrayList<Servico> listaServico = new ArrayList<Servico>();
        try {
            abrirBanco();
            String query = "SELECT * FROM " + NOME_TABELA_SERVICO + " ORDER BY(" + ID_SERVICO + ")DESC LIMIT 0,5";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Servico servicobean;
            while (rs.next()) {
                servicobean = new Servico();
                servicobean.setIdServico(rs.getInt(ID_SERVICO));
                servicobean.setDescricao(rs.getString(DESCRICAO_SERVICO));
                servicobean.setValor(rs.getFloat(VALOR_SERVICO));
                servicobean.setSuspenso(rs.getBoolean(SUSPENSO_SERVICO));
                servicobean.setTempoMedioAtendimento(rs.getString(TEMPO_MEDIO_ATENDIMENTO_SERVICO));
                listaServico.add(servicobean);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return listaServico;
    }

    public void deletar(int id) throws Exception {
        abrirBanco();
        String query = "DELETE FROM " + NOME_TABELA_SERVICO + " WHERE " + ID_SERVICO + " = ?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setInt(1, id);
        pst.execute();
        fecharBanco();
    }

    public void alterar(Servico servico) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE " + NOME_TABELA_SERVICO + " SET "
                    + DESCRICAO_SERVICO + " = ?,"
                    + VALOR_SERVICO + " = ?,"
                    + TEMPO_MEDIO_ATENDIMENTO_SERVICO + " = ?, "
                    + SUSPENSO_SERVICO + " = ? "
                    + "WHERE " + ID_SERVICO + " = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, servico.getDescricao());
            pst.setFloat(2, servico.getValor());
            pst.setString(3, servico.getTempoMedioAtendimento());
            pst.setBoolean(4, servico.getSuspenso());

            pst.setInt(5, servico.getIdServico());
            pst.execute();
            fecharBanco();

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public Servico pesquisar(int id) throws Exception {
        try {
            Servico servicobean = new Servico();
            abrirBanco();
            String query = "SELECT * FROM " + NOME_TABELA_SERVICO + " WHERE " + ID_SERVICO + " = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                servicobean.setIdServico(rs.getInt(ID_SERVICO));
                servicobean.setValor(rs.getFloat(VALOR_SERVICO));
                servicobean.setDescricao(rs.getString(DESCRICAO_SERVICO));
                servicobean.setSuspenso(rs.getBoolean(SUSPENSO_SERVICO));
                servicobean.setTempoMedioAtendimento(rs.getString(TEMPO_MEDIO_ATENDIMENTO_SERVICO));
                return servicobean;
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return null;
    }
}
