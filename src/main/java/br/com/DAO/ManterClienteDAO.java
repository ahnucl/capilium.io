package br.com.DAO;

import br.com.controller.Cliente;
import br.com.controller.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ManterClienteDAO extends DAO {

    /*Variaveis globais para usar em todas as Query,
    para assim ficar mais facil se mudar o NOME_CLIENTE da coluna na tabela*/
    public static String NOME_TABELA_CLIENTE = "Cliente"; //NOME_CLIENTE da tabela
    public static String ID_CLIENTE = "idCliente"; // PK da tablea
    public static String TELEFONE_CLIENTE = "telefone";
    public static String NOME_CLIENTE = "nome";
    public static String EMAIL_CLIENTE = "email";
    public static String CPF_CLIENTE = "cpf";
    public static String ID_USUARIO_CLIENTE = "idUsuario";// FK da tablea

    public void inserir(Cliente cliente) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO " + NOME_TABELA_CLIENTE + " (" + ID_CLIENTE + ", " + NOME_CLIENTE + ", " + TELEFONE_CLIENTE + ", "
                    + EMAIL_CLIENTE + ", " + CPF_CLIENTE + ", " + ID_USUARIO_CLIENTE + ") "
                    + "VALUES(NULL, ?, ?, ?, ?, ?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getTelefone());
            pst.setString(3, cliente.getEmail());
            pst.setString(4, cliente.getCpf().replace(".", "").replace("-", ""));
            pst.setInt(5, cliente.getUsuario().getIdUsuario());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public ArrayList<Cliente> pesquisarTudo() throws Exception {
        ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
        try {
            abrirBanco();
            String query = "SELECT * FROM " + NOME_TABELA_CLIENTE + " ORDER BY(" + ID_CLIENTE + ")DESC LIMIT 0,5";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Cliente clientebean;
            Usuario usuariobean;
            while (rs.next()) {
                clientebean = new Cliente();
                usuariobean = new Usuario();

                clientebean.setIdCliente(rs.getInt(ID_CLIENTE));
                clientebean.setTelefone(rs.getString(TELEFONE_CLIENTE));
                clientebean.setEmail(rs.getString(EMAIL_CLIENTE));
                clientebean.setCpf(rs.getString(CPF_CLIENTE));
                clientebean.setNome(rs.getString(NOME_CLIENTE));
                usuariobean.setIdUsuario(rs.getInt(ID_USUARIO_CLIENTE));
                clientebean.setUsuario(usuariobean);

                listaCliente.add(clientebean);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return listaCliente;
    }

    public void deletar(int id) throws Exception {
        abrirBanco();
        String query = "DELETE FROM " + NOME_TABELA_CLIENTE + " WHERE " + ID_CLIENTE + " = ?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setInt(1, id);
        pst.execute();
        fecharBanco();
    }

    public void alterar(Cliente cliente) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE " + NOME_TABELA_CLIENTE + " SET "
                    + TELEFONE_CLIENTE + " = ?,"
                    + EMAIL_CLIENTE + " = ?,"
                    + CPF_CLIENTE + " = ? "
                    + NOME_CLIENTE + " = ? "
                    + "WHERE " + ID_CLIENTE + "=?;";
            pst = con.prepareStatement(query);
            pst.setString(1, cliente.getTelefone());
            pst.setString(2, cliente.getEmail());
            pst.setString(3, cliente.getCpf().replace(".", "").replace("-", ""));
            pst.setString(4, cliente.getNome());
            
            pst.setInt(5, cliente.getIdCliente());
            pst.execute();
            fecharBanco();

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public Cliente pesquisar(int id) throws Exception {
        try {
            Cliente clientebean = new Cliente();
            Usuario usuariobean = new Usuario();
            ManterUsuarioDAO dao = new ManterUsuarioDAO();
            abrirBanco();
            String query = "SELECT * FROM " + NOME_TABELA_CLIENTE + " WHERE " + ID_CLIENTE + " = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                clientebean.setIdCliente(rs.getInt(ID_CLIENTE));
                clientebean.setTelefone(rs.getString(TELEFONE_CLIENTE));
                clientebean.setEmail(rs.getString(EMAIL_CLIENTE));
                clientebean.setCpf(rs.getString(CPF_CLIENTE));
                clientebean.setNome(rs.getString(NOME_CLIENTE));
                usuariobean = dao.pesquisar(rs.getInt(ID_USUARIO_CLIENTE));
                clientebean.setUsuario(usuariobean);
                fecharBanco();
                return clientebean;
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return null;
    }

    public boolean verificaCPF_Existe(String CPF) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT COUNT(*) valor \n"
                    + "FROM " + NOME_TABELA_CLIENTE + " \n"
                    + "WHERE " + CPF_CLIENTE + " = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, CPF);
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

    public boolean verificaEmail_Existe(String email) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT COUNT(*) valor \n"
                    + "FROM " + NOME_TABELA_CLIENTE + " \n"
                    + "WHERE " + EMAIL_CLIENTE + " = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, email);
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
