
package br.com.bean.agendamento;

import br.com.DAO.ManterAtendimentoDAO;
import br.com.DAO.ManterClienteDAO;
import br.com.DAO.ManterFuncionarioDAO;
import br.com.DAO.ManterServicoDAO;
import br.com.controller.Atendimento;
import br.com.controller.Cliente;
import br.com.controller.Funcionario;
import br.com.controller.Servico;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Leo
 */
public class AjaxServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {;
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet AjaxServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet AjaxServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
        
            String acao = request.getParameter("action");

            if (acao.equals("funcionario") ) {
                ManterFuncionarioDAO daoFuncionario = new ManterFuncionarioDAO();
                Funcionario funcionario = new Funcionario();
                List<Funcionario> listaFuncionario = daoFuncionario.pesquisarTudo();

                String respostaJson = new Gson().toJson(listaFuncionario);
//                System.out.println(respostaJson);
//                Gson gson = new GsonBuilder().setPrettyPrinting().create();   // Imprimir um JSON com a estrutura do objeto
//                System.out.println(gson.toJson(listaFuncionario));            // Imprimir um JSON com a estrutura do objeto
                
                out.println(respostaJson);
            }
            
            if (acao.equals("cliente") ) {
                ManterClienteDAO daoCliente = new ManterClienteDAO();
                Cliente cliente = new Cliente();
                List<Cliente> listaCliente = daoCliente.pesquisarTudo();
                
                String respostaJson = new Gson().toJson(listaCliente);
//                System.out.println(respostaJson);
                out.println(respostaJson);
            }
            
            if(acao.equals("servico")) {
                ManterServicoDAO daoServico = new ManterServicoDAO();
                Servico servico = new Servico();
                List<Servico> listaServ = daoServico.pesquisarTudo();
                
                String respostaJson = new Gson().toJson(listaServ);
//                System.out.println(respostaJson);
                out.println(respostaJson);
                
            }
            
            if (acao.equals("horarios") ) {
                int idFuncionario = Integer.parseInt(request.getParameter("idFuncionario"));
                String dataAtendimento = request.getParameter("dataAtendimento");
//                System.out.println(dataAtendimento + " ... "+ idFuncionario);
                ManterAtendimentoDAO daoAtendimento = new ManterAtendimentoDAO();
                List<Atendimento> listaAtendimentos = daoAtendimento.atendimentosDoFuncionarioNoDia(idFuncionario, dataAtendimento);
//                Gson gson = new GsonBuilder().setPrettyPrinting().create();   // Imprimir um JSON com a estrutura do objeto
//                System.out.println(gson.toJson(listaAtendimentos));           // Imprimir um JSON com a estrutura do objeto
                
                String respostaJson = new Gson().toJson(listaAtendimentos);
                out.println(respostaJson);
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
            Logger.getLogger(AjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
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
