/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

/**
 *
 * @author Leo
 */
public class AtendimentoServico {
    int idAtendimento;
    int idServico;

    public int getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(int idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }
 
    public AtendimentoServico(int idAtendimento, int idServico){
        this.idAtendimento = idAtendimento;
        this.idServico = idServico;
    }
}
