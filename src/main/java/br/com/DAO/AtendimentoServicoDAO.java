/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.DAO;

import br.com.controller.AtendimentoServico;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Leo
 */
public class AtendimentoServicoDAO extends DAO{
    public static final String NOME_TABELA_ATENDIMENTO_SERVICO = "atendimento_servico"; //nome da tabela
    public static final String ID_ATENDIMENTO = "idAtendimento"; // PK da tablea
    public static final String ID_SERVICO = "idServico"; // PK da tablea
    
    public int inserir(AtendimentoServico as) throws Exception {
        try {
            abrirBanco();
            String query = "INSERT INTO " + NOME_TABELA_ATENDIMENTO_SERVICO + " (" + ID_ATENDIMENTO + ", " + ID_SERVICO + ") "
                    + "VALUES(?, ?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, as.getIdAtendimento());
            pst.setInt(2, as.getIdServico());
            pst.execute();
            
            // buscar o ultimo ID na cadastrado na tabela durante a mesma conexão
            String queryReturnId = "SELECT LAST_INSERT_ID() as id FROM " + NOME_TABELA_ATENDIMENTO_SERVICO;
            pst = con.prepareStatement(queryReturnId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return (int) rs.getInt("id");
            }
            
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return 0;
    }
}
