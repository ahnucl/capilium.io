package br.com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {

    //Dados para configuração do banco
    private static String IP_BD = "localhost";
    private static String NOME_BANCO = "capillium";
    private static String USER_BANCO = "web_app";
    private static String SENHA_BANCO = "1234";
    //private static String USER_BANCO = "root";
    //private static String SENHA_BANCO = "";

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public void abrirBanco() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + IP_BD + "/" + NOME_BANCO;

            con = (Connection) DriverManager.getConnection(url, USER_BANCO, SENHA_BANCO);
            System.out.println("Conectado ao banco de dados ");
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe não encontrada, adicione o driver nas bibliotecas.");
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public void fecharBanco() throws Exception {
        if (pst != null) {
            pst.close();
            System.out.println("Execuçao da Query fechada\n");
        }
    }
}
