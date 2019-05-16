package br.com.DAO;

import br.com.controller.Servico;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ManterServicoDAO extends DAO {

    /*Variaveis globais para usar em todas as Query,
    para assim ficar mais facil se mudar o nome da coluna na tabela*/
    private static String nomeTabela = "Servico"; //nome da tabela
    private static String idServico = "idServico"; // PK da tablea
    private static String descricao = "descricao";
    private static String valor = "valor";
    private static String suspenso = "suspenso";
    private static String tempoMedioAtendimento = "tempoMedioAtendimento";

    public void inserir(Servico servico) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO " + nomeTabela + " "
                    + "(" + idServico + ", " + descricao + ", "
                    + valor + ", " + tempoMedioAtendimento + ", "
                    + suspenso + ")"
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
            String query = "SELECT * FROM " + nomeTabela + " ORDER BY(" + idServico + ")DESC LIMIT 0,5";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Servico servicobean;
            while (rs.next()) {
                servicobean = new Servico();
                servicobean.setIdServico(rs.getInt(idServico));
                servicobean.setDescricao(rs.getString(descricao));
                servicobean.setValor(rs.getFloat(valor));
                servicobean.setSuspenso(rs.getBoolean(suspenso));
                servicobean.setTempoMedioAtendimento(rs.getString(tempoMedioAtendimento));
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
        String query = "DELETE FROM " + nomeTabela + " WHERE " + idServico + " = ?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setInt(1, id);
        pst.execute();
        fecharBanco();
    }

    public void alterar(Servico servico) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE " + nomeTabela + " SET "
                    + descricao + " = ?,"
                    + valor + " = ?,"
                    + tempoMedioAtendimento + " = ?, "
                    + suspenso + " = ? "
                    + "WHERE " + idServico + "=?;";
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
            String query = "SELECT * FROM " + nomeTabela + " WHERE " + idServico + " = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                servicobean.setIdServico(rs.getInt(idServico));
                servicobean.setValor(rs.getFloat(valor));
                servicobean.setDescricao(rs.getString(descricao));
                servicobean.setSuspenso(rs.getBoolean(suspenso));
                servicobean.setTempoMedioAtendimento(rs.getString(tempoMedioAtendimento));
                return servicobean;
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return null;
    }
}
