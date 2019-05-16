package br.com.bean.usuario;

import br.com.DAO.ManterUsuarioDAO;
import br.com.controller.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditarUsuario extends HttpServlet {

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
            ManterUsuarioDAO userDAO = new ManterUsuarioDAO();
            Usuario user = new Usuario();

            if (request.getParameter("senha") == request.getParameter("csenha") || request.getParameter("csenha").equals(request.getParameter("senha"))) {// validar se as senhas são iguais
                user.setLogin(request.getParameter("login"));
                user.setSenha(request.getParameter("senha"));
                if (userDAO.verificaLoginExistente(request.getParameter("login"))) {
                    userDAO.esqueciSenha(user);
                    request.setAttribute("verde", "OK");//alterar a cor do alerta
                    request.setAttribute("msg", "Senha alterada com sucesso!!");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    request.setAttribute("msg", "O usuário informado não existe!!!");
                    request.getRequestDispatcher("esqueciSenha.jsp").forward(request, response);
                }

            } else {
                request.setAttribute("msg", "Senhas informadas não conferem!!!");
                request.getRequestDispatcher("esqueciSenha.jsp").forward(request, response);
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
            Logger.getLogger(EditarUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EditarUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
