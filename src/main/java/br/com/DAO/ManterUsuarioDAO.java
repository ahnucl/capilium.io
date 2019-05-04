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
    private static String nome = "nome";
    private static String login = "login";
    private static String senha = "senha";

    public void inserir(Usuario user) throws Exception {
        try {

            abrirBanco();
            String query = "INSERT INTO " + nomeTabela + " "
                    + "(" + idUsuario + ", " + nome + ", " + login + ", " + senha + ") "
                    + "VALUES(null, ?, ?, ?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, user.getNome());
            pst.setString(2, MD5.stringHexa(MD5.gerarHash(user.getLogin(), "MD5")));
            pst.setString(3, MD5.stringHexa(MD5.gerarHash(user.getSenha(), "MD5")));
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public void deletar(Usuario user) throws Exception {
        abrirBanco();
        String query = "DELETE FROM " + nomeTabela + " WHERE " + idUsuario + " = ?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setInt(1, user.getIdUsuario());
        pst.execute();
        fecharBanco();
    }

    public void alterar(Usuario user) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE " + nomeTabela + " "
                    + "SET " + nome + " = ?, " + login + " = ?, " + senha + " = ?"
                    + "WHERE " + idUsuario + " = ?;";
            pst = con.prepareStatement(query);
            pst.setString(1, user.getNome());
            pst.setString(2, MD5.stringHexa(MD5.gerarHash(user.getLogin(), "MD5")));
            pst.setString(3, MD5.stringHexa(MD5.gerarHash(user.getSenha(), "MD5")));

            pst.setInt(4, user.getIdUsuario());
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
            pst.setString(1, verificaLogin);
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

    public Usuario permissaoAcesso(Usuario user) throws Exception {
        try {
            Usuario obj = new Usuario();
            abrirBanco();
            String query = "SELECT " + idUsuario + ", " + nome + " FROM "
                    + nomeTabela + ""
                    + " WHERE " + login + " = ? AND " + senha + " = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, MD5.stringHexa(MD5.gerarHash(user.getLogin(), "MD5")));
            pst.setString(2, MD5.stringHexa(MD5.gerarHash(user.getSenha(), "MD5")));
            ResultSet rs = pst.executeQuery();
            //Retornar esses dados para criar Session
            while (rs.next()) {
                obj.setIdUsuario(rs.getInt(idUsuario));
                obj.setNome(rs.getString(nome));
                return obj;
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return null;
    }

    public String tipoUsuario(Usuario user) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT COUNT(*) AS valor FROM "
                    + "Funcionario"
                    + " WHERE " + idUsuario + " = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, user.getIdUsuario());
            ResultSet rs = pst.executeQuery();
            //Retornar esse dado para saber o tipo de usuario
            if (rs.next()) {
                return rs.getInt("valor") == 1 ? "funcionario" : "cliente";
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return null;
    }
}
