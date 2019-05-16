package br.com.DAO;

import br.com.controller.Funcionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ManterFuncionarioDAO extends DAO {

    /*Variaveis globais para usar em todas as Query,
    para assim ficar mais facil se mudar o nome da coluna na tabela*/
    private static String nomeTabela = "Funcionario";//Nome da tabela
    private static String idFuncionario = "idFuncionario";//PK da tabela
    private static String matricula = "matricula";
    private static String nome = "nome";//campo da tabela usuario

    public void inserir(Funcionario funcionario) throws Exception {

        try {
            abrirBanco();
            String query = "INSERT INTO " + nomeTabela + " "
                    + "(" + idFuncionario + ", " + matricula + ", " + nome + ") "
                    + "VALUES(NULL, ?, ?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, funcionario.getMatricula());
            pst.setString(2, funcionario.getNome());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public ArrayList<Funcionario> pesquisarTudo() throws Exception {
        ArrayList<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
        try {
            abrirBanco();
            String query = "SELECT * "
                    + "FROM " + nomeTabela + " "
                    + "ORDER BY(" + idFuncionario + ")"
                    + "DESC LIMIT 0,5";

            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Funcionario funcionariobean;

            while (rs.next()) {
                funcionariobean = new Funcionario();

                funcionariobean.setIdFuncionario(rs.getInt(idFuncionario));
                funcionariobean.setMatricula(rs.getString(matricula));
                funcionariobean.setNome(rs.getString(nome));

                listaFuncionario.add(funcionariobean);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return listaFuncionario;
    }

    public ArrayList<Funcionario> pesquisarMatriculaNome(String busca) throws Exception {
        ArrayList<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
        try {
            abrirBanco();
            String query = "SELECT * "
                    + "FROM " + nomeTabela + " "
                    + "WHERE " + matricula + " = ? OR "
                    + nome + " = ?"
                    + "ORDER BY(" + idFuncionario + ")";

            pst = con.prepareStatement(query);
            pst.setString(1, busca);
            pst.setString(2, busca);
            
            ResultSet rs = pst.executeQuery();
            
            Funcionario funcionariobean;

            while (rs.next()) {
                funcionariobean = new Funcionario();

                funcionariobean.setIdFuncionario(rs.getInt(idFuncionario));
                funcionariobean.setMatricula(rs.getString(matricula));
                funcionariobean.setNome(rs.getString(nome));

                listaFuncionario.add(funcionariobean);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return listaFuncionario;
    }

    public void deletar(int id) throws Exception {
        abrirBanco();
        String query = "DELETE FROM " + nomeTabela + " WHERE " + idFuncionario + " = ?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setInt(1, id);
        pst.execute();
        fecharBanco();
    }

    public void alterar(Funcionario funcionario) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE " + nomeTabela + " SET "
                    + matricula + " = ?, "
                    + nome + " = ? "
                    + "WHERE " + idFuncionario + " = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, funcionario.getMatricula());
            pst.setString(2, funcionario.getNome());
            pst.setInt(3, funcionario.getIdFuncionario());
            pst.execute();
            fecharBanco();

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
// Alterar essa função para que o campo pesquisar funcione com os parametros de matricula ou nome

    public Funcionario pesquisar(int id) throws Exception {
        try {
            Funcionario funcionariobean = new Funcionario();

            abrirBanco();
            String query = "SELECT * FROM " + nomeTabela + " WHERE " + idFuncionario + " = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                funcionariobean.setIdFuncionario(rs.getInt(idFuncionario));
                funcionariobean.setMatricula(rs.getString(matricula));
                funcionariobean.setNome(rs.getString(nome));
                return funcionariobean;
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return null;
    }

    public boolean verificaMatriculaExistente(String verificaMatricula, int idFuncionario) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT COUNT(*) valor "
                    + "FROM " + nomeTabela + " "
                    + "WHERE " + matricula + " = ?"
                    + "AND" + idFuncionario + " != ?";
            pst = con.prepareStatement(query);
            pst.setString(1, verificaMatricula);
            pst.setInt(2, idFuncionario);
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
