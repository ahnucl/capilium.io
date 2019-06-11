package br.com.DAO;

import br.com.controller.Funcionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ManterFuncionarioDAO extends DAO {

    /*Variaveis globais para usar em todas as Query,
    para assim ficar mais facil se mudar o nome da coluna na tabela*/
    public static String NOME_TABELA_FUNCIONARIO = "Funcionario";//Nome da tabela
    public static String ID_FUNCIONARIO = "idFuncionario";//PK da tabela
    public static String MATRICULA_FUNCIONARIO = "matricula";
    public static String NOME_FUNCIONARIO = "nome";//campo da tabela usuario

    public void inserir(Funcionario funcionario) throws Exception {

        try {
            abrirBanco();
            String query = "INSERT INTO " + NOME_TABELA_FUNCIONARIO + " "
                    + "(" + ID_FUNCIONARIO + ", " + MATRICULA_FUNCIONARIO + ", " + NOME_FUNCIONARIO + ") "
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
                    + "FROM " + NOME_TABELA_FUNCIONARIO + " "
                    + "ORDER BY(" + ID_FUNCIONARIO + ")"
                    + "DESC LIMIT 0,5";

            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Funcionario funcionariobean;

            while (rs.next()) {
                funcionariobean = new Funcionario();

                funcionariobean.setIdFuncionario(rs.getInt(ID_FUNCIONARIO));
                funcionariobean.setMatricula(rs.getString(MATRICULA_FUNCIONARIO));
                funcionariobean.setNome(rs.getString(NOME_FUNCIONARIO));

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
                    + "FROM " + NOME_TABELA_FUNCIONARIO + " "
                    + "WHERE " + MATRICULA_FUNCIONARIO + " = ? OR "
                    + NOME_FUNCIONARIO + " = ?"
                    + "ORDER BY(" + ID_FUNCIONARIO + ")";

            pst = con.prepareStatement(query);
            pst.setString(1, busca);
            pst.setString(2, busca);
            
            ResultSet rs = pst.executeQuery();
            
            Funcionario funcionariobean;

            while (rs.next()) {
                funcionariobean = new Funcionario();

                funcionariobean.setIdFuncionario(rs.getInt(ID_FUNCIONARIO));
                funcionariobean.setMatricula(rs.getString(MATRICULA_FUNCIONARIO));
                funcionariobean.setNome(rs.getString(NOME_FUNCIONARIO));

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
        String query = "DELETE FROM " + NOME_TABELA_FUNCIONARIO + " WHERE " + ID_FUNCIONARIO + " = ?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setInt(1, id);
        pst.execute();
        fecharBanco();
    }

    public void alterar(Funcionario funcionario) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE " + NOME_TABELA_FUNCIONARIO + " SET "
                    + MATRICULA_FUNCIONARIO + " = ?, "
                    + NOME_FUNCIONARIO + " = ? "
                    + "WHERE " + ID_FUNCIONARIO + " = ?";
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
// Alterar essa função para que o campo pesquisar funcione com os parametros de MATRICULA_FUNCIONARIO ou NOME_FUNCIONARIO

    public Funcionario pesquisar(int id) throws Exception {
        try {
            Funcionario funcionariobean = new Funcionario();

            abrirBanco();
            String query = "SELECT * FROM " + NOME_TABELA_FUNCIONARIO + " WHERE " + ID_FUNCIONARIO + " = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                funcionariobean.setIdFuncionario(rs.getInt(ID_FUNCIONARIO));
                funcionariobean.setMatricula(rs.getString(MATRICULA_FUNCIONARIO));
                funcionariobean.setNome(rs.getString(NOME_FUNCIONARIO));
                return funcionariobean;
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return null;
    }

    public boolean verificaMatriculaExistente(String matricula, int id) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT COUNT(*) valor "
                    + "FROM " + NOME_TABELA_FUNCIONARIO + " "
                    + "WHERE " + MATRICULA_FUNCIONARIO + " = ?"
                    + "AND" + ID_FUNCIONARIO + " != ?";
            pst = con.prepareStatement(query);
            pst.setString(1, matricula);
            pst.setInt(2, id);
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
