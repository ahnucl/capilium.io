package br.com.DAO;

import br.com.controller.Usuario;
import br.com.util.MD5;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ManterUsuarioDAO extends DAO {

    /*Variaveis globais para usar em todas as Query,
     para assim ficar mais facil se mudar o nome da coluna na tabela*/
    private static String nomeTabela = "Usuario"; //Nome da tabela
    private static String idUsuario = "idUsuario";//PK da tabela
    private static String login = "login";
    private static String senha = "senha";

    public int inserir(Usuario user) throws Exception {
        try {

            abrirBanco();
            String query = "INSERT INTO " + nomeTabela + " "
                    + "(" + idUsuario + ", " + login + ", " + senha + ") "
                    + "VALUES(null, ?, ?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, MD5.stringHexa(MD5.gerarHash(user.getLogin(), "MD5")));
            pst.setString(2, MD5.stringHexa(MD5.gerarHash(user.getSenha(), "MD5")));
            pst.execute();

            // buscar o ultimo ID na cadastrado na tabela durante a mesma conexão
            String queryReturnId = "SELECT LAST_INSERT_ID() as id FROM " + nomeTabela;
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
        String query = "DELETE FROM " + nomeTabela + " WHERE " + idUsuario + " = ?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setInt(1, id);
        pst.execute();
        fecharBanco();
    }

    public void alterar(Usuario user) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE " + nomeTabela + " "
                    + "SET " + login + " = ?, " + senha + " = ?"
                    + "WHERE " + idUsuario + " = ?;";
            pst = con.prepareStatement(query);
            pst.setString(1, MD5.stringHexa(MD5.gerarHash(user.getLogin(), "MD5")));
            pst.setString(2, MD5.stringHexa(MD5.gerarHash(user.getSenha(), "MD5")));

            pst.setInt(3, user.getIdUsuario());
            pst.execute();
            fecharBanco();

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public void esqueciSenha(Usuario user) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE " + nomeTabela + " "
                    + "SET " + senha + " = ?"
                    + "WHERE " + login + " = ?;";
            pst = con.prepareStatement(query);
            pst.setString(1, MD5.stringHexa(MD5.gerarHash(user.getSenha(), "MD5")));

            pst.setString(2, MD5.stringHexa(MD5.gerarHash(user.getLogin(), "MD5")));
            pst.execute();
            fecharBanco();

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public boolean verificaLoginExistente(String verificaLogin) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT COUNT(*) valor "
                    + "FROM " + nomeTabela + " "
                    + "WHERE " + login + " = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, MD5.stringHexa(MD5.gerarHash(verificaLogin, "MD5")));
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

    public Usuario verificarAcesso(Usuario user) throws Exception {
        try {
            Usuario obj = new Usuario();
            abrirBanco();
            String query = "SELECT " + idUsuario + " FROM "
                    + nomeTabela + ""
                    + " WHERE " + login + " = ? AND " + senha + " = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, MD5.stringHexa(MD5.gerarHash(user.getLogin(), "MD5")));
            pst.setString(2, MD5.stringHexa(MD5.gerarHash(user.getSenha(), "MD5")));
            ResultSet rs = pst.executeQuery();
            //Retornar esses dados para criar Session
            while (rs.next()) {
                obj.setIdUsuario(rs.getInt(idUsuario));
                return obj;
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return null;
    }

    public boolean tipoUsuario(int id) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT COUNT(*) AS valor FROM "
                    + "Cliente"
                    + " WHERE " + idUsuario + " = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            //Retornar esse dado para saber o tipo de usuario
            if (rs.next()) {
                return rs.getBoolean("valor");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return false;
    }
    
    public String nomeUsuario(int id) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT c.nome AS nome FROM "
                    + "Cliente AS c "
                    + "INNER JOIN "
                    + nomeTabela + " AS u "
                    + "ON u." + idUsuario + " = c.idUsuario "
                    + "WHERE " + idUsuario + " = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            //Retornar esse dado para saber o tipo de usuario
            if (rs.next()) {
                return rs.getString("nome");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return null;
    }
    
    public Usuario pesquisar(int id) throws Exception {
        try {
            Usuario userbean = new Usuario();
            abrirBanco();
            String query = "SELECT * FROM " + nomeTabela + " WHERE " + idUsuario + " = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                userbean.setIdUsuario(rs.getInt(idUsuario));
                userbean.setLogin(rs.getString(login));
                return userbean;
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return null;
    }
}
