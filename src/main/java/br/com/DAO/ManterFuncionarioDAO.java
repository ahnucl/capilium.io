package br.com.DAO;

import br.com.controller.Funcionario;
import br.com.controller.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ManterFuncionarioDAO extends DAO {

    /*Variaveis globais para usar em todas as Query,
    para assim ficar mais facil se mudar o nome da coluna na tabela*/
    private static String nomeTabela = "Funcionario";//Nome da tabela
    private static String idFuncionario = "idFuncionario";//PK da tabela
    private static String matricula = "matricula";
    private static String idUsuario = "idUsuario";

    public void inserir(Funcionario funcionario, Usuario usuario) throws Exception {

        try {
            abrirBanco();
            String query = "INSERT INTO " + nomeTabela + ""
                    + "(" + idFuncionario + ", " + matricula + ", " + idUsuario + ") "
                    + "VALUES(NULL, ?, ?, ?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, funcionario.getMatricula());
            pst.setInt(2, usuario.getIdUsuario());
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
            String query = "SELECT * FROM " + nomeTabela + " ORDER BY(" + idFuncionario + ")DESC LIMIT 0,5";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Funcionario funcionariobean;
            Usuario usuariobean;
            while (rs.next()) {
                funcionariobean = new Funcionario();
                usuariobean = new Usuario();

                funcionariobean.setIdFuncionario(rs.getInt(idFuncionario));
                funcionariobean.setMatricula(rs.getString(matricula));
                usuariobean.setIdUsuario(rs.getInt(idUsuario));
                funcionariobean.setUsuario(usuariobean);
                listaFuncionario.add(funcionariobean);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return listaFuncionario;
    }

    public void deletar(Funcionario funcionario) throws Exception {
        abrirBanco();
        String query = "DELETE FROM " + nomeTabela + " WHERE " + idFuncionario + " = ?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setInt(1, funcionario.getIdFuncionario());
        pst.execute();
        fecharBanco();
    }

    public void alterar(Funcionario funcionario) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE " + nomeTabela + " SET "
                    + matricula + " = ?"
                    + "WHERE " + idFuncionario + " = ?;";
            pst = con.prepareStatement(query);
            pst.setString(1, funcionario.getMatricula());
            pst.setInt(2, funcionario.getIdFuncionario());
            pst.execute();
            fecharBanco();

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public Funcionario pesquisar(Funcionario funcionario) throws Exception {
        try {
            Funcionario funcionariobean = new Funcionario();
            Usuario usuariobean = new Usuario();
            abrirBanco();
            String query = "SELECT * FROM " + nomeTabela + " WHERE " + idFuncionario + " = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, funcionario.getIdFuncionario());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                funcionariobean.setIdFuncionario(rs.getInt(idFuncionario));
                funcionariobean.setMatricula(rs.getString(matricula));
                usuariobean.setIdUsuario(rs.getInt(idUsuario));
                funcionariobean.setUsuario(usuariobean);
                return funcionariobean;
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return null;
    }

    public boolean verificaMatriculaExistente(String verificaMatricula) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT COUNT(*) valor "
                    + "FROM " + nomeTabela + " "
                    + "WHERE " + matricula + " = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, verificaMatricula);
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
