package br.com.DAO;

import br.com.controller.Cliente;
import br.com.controller.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ManterClienteDAO extends DAO {

    /*Variaveis globais para usar em todas as Query,
    para assim ficar mais facil se mudar o nome da coluna na tabela*/
    private static String nomeTabela = "Cliente"; //nome da tabela
    private static String idCliente = "idCliente"; // PK da tablea
    private static String telefone = "telefone";
    private static String email = "email";
    private static String cpf = "cpf";
    private static String idUsuario = "idUsuario";// FK da tablea

    public void inserir(Cliente cliente, Usuario usuario) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO " + nomeTabela + ""
                    + "(" + idCliente + ", " + telefone + ","
                    + email + ", " + cpf + ", " + idUsuario + ")"
                    + "VALUES(NULL, ?, ?, ?, ?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, cliente.getTelefone());
            pst.setString(2, cliente.getEmail());
            pst.setString(3, cliente.getCpf().replace(".", "").replace("-", ""));
            pst.setInt(4, usuario.getIdUsuario());
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
            String query = "SELECT * FROM " + nomeTabela + " ORDER BY(" + idCliente + ")DESC LIMIT 0,5";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Cliente clientebean;
            Usuario usuariobean;
            while (rs.next()) {
                clientebean = new Cliente();
                usuariobean = new Usuario();

                clientebean.setIdCliente(rs.getInt(idCliente));
                clientebean.setTelefone(rs.getString(telefone));
                clientebean.setEmail(rs.getString(email));
                clientebean.setCpf(rs.getString(cpf));
                usuariobean.setIdUsuario(rs.getInt(idUsuario));
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
        String query = "DELETE FROM " + nomeTabela + " WHERE " + idCliente + " = ?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setInt(1, id);
        pst.execute();
        fecharBanco();
    }

    public void alterar(Cliente cliente) throws Exception {
        try {
            abrirBanco();
            String query = "UPDATE " + nomeTabela + " SET "
                    + telefone + " = ?,"
                    + email + " = ?,"
                    + cpf + " = ? "
                    + "WHERE " + idCliente + "=?;";
            pst = con.prepareStatement(query);
            pst.setString(1, cliente.getTelefone());
            pst.setString(2, cliente.getEmail());
            pst.setString(3, cliente.getCpf().replace(".", "").replace("-", ""));

            pst.setInt(4, cliente.getIdCliente());
            pst.execute();
            fecharBanco();

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public Cliente pesquisar(Cliente cliente) throws Exception {
        try {
            Cliente clientebean = new Cliente();
            Usuario usuariobean = new Usuario();
            abrirBanco();
            String query = "SELECT * FROM " + nomeTabela + " WHERE " + idCliente + " = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, cliente.getIdCliente());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                clientebean = new Cliente();

                clientebean.setIdCliente(rs.getInt(idCliente));
                clientebean.setTelefone(rs.getString(telefone));
                clientebean.setEmail(rs.getString(email));
                clientebean.setCpf(rs.getString(cpf));
                usuariobean.setIdUsuario(rs.getInt(idUsuario));
                clientebean.setUsuario(usuariobean);

                return clientebean;
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return null;
    }

    public boolean verificaCPF_Existe(String verificaCPF) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT COUNT(*) valor \n"
                    + "FROM " + nomeTabela + " \n"
                    + "WHERE " + cpf + " = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, verificaCPF);
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

    public boolean verificaEmail_Existe(String verificaEmail) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT COUNT(*) valor \n"
                    + "FROM " + nomeTabela + " \n"
                    + "WHERE " + email + " = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, verificaEmail);
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
