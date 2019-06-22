/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean.agendamento;

import br.com.DAO.AtendimentoServicoDAO;
import br.com.DAO.ManterAtendimentoDAO;
import br.com.controller.Atendimento;
import br.com.controller.AtendimentoServico;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
 * @author Leo
 */
public class CadastrarAgendamento extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            
            int servico = Integer.parseInt(request.getParameter("servicos")); // id do servico
            //String[] servico = request.getParameterValues("servicos");
            String horarioInicio = request.getParameter("horaInicio");
            String dataAtendimento = request.getParameter("dataAtendimento");
            int idFuncionario = Integer.parseInt(request.getParameter("funcionario"));
            int idCliente = Integer.parseInt(request.getParameter("cliente"));
            
            // teste das variáveis
//            out.println(servico); 
//            out.println(horarioInicio); 
//            out.println(dataAtendimento);
//            out.println(idFuncionario);
//            out.println(idCliente);
            
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();   // Imprimir um JSON com a estrutura do objeto
//            out.println(gson.toJson(servico));            // Imprimir um JSON com a estrutura do objeto
            
            // Inserindo atendimento
            Atendimento a = new Atendimento( horarioInicio, horarioInicio, 0f, dataAtendimento, idCliente, idFuncionario );
            ManterAtendimentoDAO aDAO = new ManterAtendimentoDAO();
            int idInserido = aDAO.inserir(a); // Tive que mudar a DAO para atender sem usar os objetos de cliente e funcionário (apenas com os ids)
            a.setIdAtendimento(idInserido);
                       
            //Inserindo serviços (Atendimento_servico) - Se for usar o checkbox, receber como array e fazer um loop
            AtendimentoServico as = new AtendimentoServico(a.getIdAtendimento(), servico);
            AtendimentoServicoDAO asDAO = new AtendimentoServicoDAO();
            asDAO.inserir(as);
            
            //Redirecionar
            request.setAttribute("msg", "Atendimento cadastrado com sucesso.");
            request.setAttribute("verde", "OK");//alterar a cor do alerta
            request.getRequestDispatcher("user/all-agendamento.jsp").forward(request, response);
            response.sendRedirect("user/all-agendamento.jsp");
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
            Logger.getLogger(CadastrarAgendamento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CadastrarAgendamento.class.getName()).log(Level.SEVERE, null, ex);
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
