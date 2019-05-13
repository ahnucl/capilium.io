/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.DAO;

import br.com.controller.Cliente;
import br.com.controller.Usuario;
import java.rmi.server.UID;

/**
 *
 * @author lukka
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Cliente c = new Cliente();
        Usuario u = new Usuario();
        ManterClienteDAO d = new ManterClienteDAO();
        c.setIdCliente(1);
        c.setCpf("151.159.159-15");
        c.setNome("151.159.159-15");
        c.setTelefone("151.159.159-15");
        c.setEmail("asdsa");
        u.setIdUsuario(2);
        c.setUsuario(u);
        
        d.inserir(c);
        
    }
    
}
