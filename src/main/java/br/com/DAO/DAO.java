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
    private static String ip = "localhost";
    private static String nomeBanco = "capillium";
<<<<<<< HEAD
    private static String user = "web_app";
    private static String senha = "1234";
=======
    private static String user = "root";
    private static String senha = "";
>>>>>>> d226d71dd23268a76ddb19095b1cbc514d80c3ec

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public void abrirBanco() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + ip + "/" + nomeBanco;

            con = (Connection) DriverManager.getConnection(url, user, senha);
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
