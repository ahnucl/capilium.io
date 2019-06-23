package br.com.DAO;

import br.com.controller.Usuario;
import br.com.util.MD5;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ManterUsuarioDAO extends DAO {

    /*Variaveis globais para usar em todas as Query,
     para assim ficar mais facil se mudar o nome da coluna na tabela*/
    public static final String NOME_TABELA_USUARIO = "Usuario"; //Nome da tabela
    public static final String ID_USUARIO = "idUsuario";//PK da tabela
    public static final String LOGIN_USUARIO = "login";
    public static final String SENHA_USUARIO = "senha";

    public int inserir(Usuario user) throws Exception {
        try {

            abrirBanco();
            String query = "INSERT INTO " + NOME_TABELA_USUARIO + " "
                    + "(" + ID_USUARIO + ", " + LOGIN_USUARIO + ", " + SENHA_USUARIO + ") "
                    + "VALUES(null, ?, ?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, MD5.stringHexa(MD5.gerarHash(user.getLogin(), "MD5")));
            pst.setString(2, MD5.stringHexa(MD5.gerarHash(user.getSenha(), "MD5")));
            pst.execute();

            // buscar o ultimo ID na cadastrado na tabela durante a mesma conexão
            String queryReturnId = "SELECT LAST_INSERT_ID() as id FROM " + NOME_TABELA_USUARIO;
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
        String query = "DELETE FROM " + NOME_TABELA_USUARIO + " WHERE " + ID_USUARIO + " = ?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setInt(1, id);
        pst.execute();
        fecharBanco();
    }

    public void alterar(Usuario user) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE " + NOME_TABELA_USUARIO + " "
                    + "SET " + LOGIN_USUARIO + " = ?, " + SENHA_USUARIO + " = ?"
                    + "WHERE " + ID_USUARIO + " = ?;";
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
            String query = "UPDATE " + NOME_TABELA_USUARIO + " "
                    + "SET " + SENHA_USUARIO + " = ?"
                    + "WHERE " + LOGIN_USUARIO + " = ?;";
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
                    + "FROM " + NOME_TABELA_USUARIO + " "
                    + "WHERE " + LOGIN_USUARIO + " = ?";
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
            String query = "SELECT " + ID_USUARIO + " FROM "
                    + NOME_TABELA_USUARIO + ""
                    + " WHERE " + LOGIN_USUARIO + " = ? AND " + SENHA_USUARIO + " = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, MD5.stringHexa(MD5.gerarHash(user.getLogin(), "MD5")));
            pst.setString(2, MD5.stringHexa(MD5.gerarHash(user.getSenha(), "MD5")));
            ResultSet rs = pst.executeQuery();
            //Retornar esses dados para criar Session
            while (rs.next()) {
                obj.setIdUsuario(rs.getInt(ID_USUARIO));
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
                    + ManterClienteDAO.NOME_TABELA_CLIENTE
                    + " WHERE " + ID_USUARIO + " = ?";
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
            String query = "SELECT "
                    /* + "CASE " 
                       + "WHEN c.nome IS NULL THEN 'Administrador' "
                        + "WHEN LENGTH(IFNULL(c.nome,'')) = 0 THEN 'Administrador' "
                        + "WHEN LEN(CAST(c.nome AS VARCHAR(MAX))) = 0 THEN 'Administrador' "
                        + "ELSE c.nome "
                    + "END "*/
                    + "c." + ManterClienteDAO.NOME_CLIENTE + " AS nome, COUNT(c." + ManterClienteDAO.NOME_CLIENTE + ") AS valor FROM "
                    + ManterClienteDAO.NOME_TABELA_CLIENTE + " AS c "
                    + "INNER JOIN "
                    + NOME_TABELA_USUARIO + " AS u "
                    + "ON c." + ID_USUARIO + " = u." + ID_USUARIO
                    + " WHERE u." + ID_USUARIO + " = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            //Retornar esse dado para saber o tipo de usuario
            if (rs.next()) {
                return rs.getInt("valor") == 0 ? "Administrador" : rs.getString("nome");
                //return rs.getString("nome");
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
            String query = "SELECT * FROM " + NOME_TABELA_USUARIO + " WHERE " + ID_USUARIO + " = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                userbean.setIdUsuario(rs.getInt(ID_USUARIO));
                userbean.setLogin(rs.getString(LOGIN_USUARIO));
                return userbean;
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return null;
    }

}
