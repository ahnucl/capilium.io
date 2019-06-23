/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.cliente;

import br.com.DAO.ManterUsuarioDAO;
import br.com.DAO.ManterClienteDAO;
import br.com.controller.Usuario;
import br.com.controller.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alisson
 */
public class EditarCliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Usuario user = new Usuario();
            ManterUsuarioDAO userdao = new ManterUsuarioDAO();
            Cliente cliente = new Cliente();
            ManterClienteDAO clientedao = new ManterClienteDAO();
            if (request.getParameter("senha") == request.getParameter("csenha") || request.getParameter("csenha").equals(request.getParameter("senha"))) {// validar se as senhas são iguais
                user.setLogin(request.getParameter("login"));
                user.setSenha(request.getParameter("senha"));

                if (!userdao.verificaLoginExistente(user.getLogin())) {// validar se o login já está em uso

                    user.setIdUsuario(userdao.inserir(user));//inserir o usuario na tabela é buscar a ID da inserção.
                    cliente.setCpf(request.getParameter("cpf"));
                    cliente.setEmail(request.getParameter("email"));
                    cliente.setNome(request.getParameter("nome"));
                    cliente.setTelefone(request.getParameter("telefone"));
                    cliente.setUsuario(user);

                    if (!clientedao.verificaCPF_Existe(cliente.getCpf())) {//Validar se o CPF já está em uso
                        if (!clientedao.verificaEmail_Existe(cliente.getEmail())) {//Validar se o E-mail já está em uso

                            clientedao.alterar(cliente);//Alterar o cliente com seu respectivo login

                            request.setAttribute("msg", "Dados Alterados com sucesso!!!");
                            request.setAttribute("verde", "OK");//alterar a cor do alerta
                            request.getRequestDispatcher("client/home.jsp").forward(request, response);
                        } else {
                            request.setAttribute("msg", "O E-mail informado já está em uso!!!");
                            /*Para saber se a ação veio do cliente ou usuario*/
                            request.getRequestDispatcher("update-cliente.jsp").forward(request, response);

                        }
                    } else {
                        request.setAttribute("msg", "O CPF informado já está em uso!!!");
                        request.getRequestDispatcher("update-cliente.jsp").forward(request, response);
                    }

                } else {
                    request.setAttribute("msg", "O login informado já está em uso!!!");
                    request.getRequestDispatcher("update-cliente.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("msg", "Senhas informadas não conferem!!!");
                request.getRequestDispatcher("update-cliente.jsp").forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(EditarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(EditarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
