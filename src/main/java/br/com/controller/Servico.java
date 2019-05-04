package br.com.controller;

public class Servico {
    private int idServico;
    private float valor;
    private String descricao, tempoMedioAtendimento;

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTempoMedioAtendimento() {
        return tempoMedioAtendimento;
    }

    public void setTempoMedioAtendimento(String tempoMedioAtendimento) {
        this.tempoMedioAtendimento = tempoMedioAtendimento;
    }

    public Servico() {
    }

    public Servico(int idServico, float valor, String descricao, String tempoMedioAtendimento) {
        this.idServico = idServico;
        this.valor = valor;
        this.descricao = descricao;
        this.tempoMedioAtendimento = tempoMedioAtendimento;
    }
}
