package br.com.controller;

public class Atendimento {
  private int idAtendimento;
  private String horarioInicio; 
  private String horarioFim;  
  private float valorTotal;  
  private String dataAtendimento;
  private Cliente cliente;   
  private Funcionario funcionario;   
  private int idCliente;
  private int idFuncionario;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
  
    public int getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(int idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(String horarioFim) {
        this.horarioFim = horarioFim;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(String dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Atendimento() {
    }

    public Atendimento(int idAtendimento, String horarioInicio, String horarioFim, float valorTotal, String dataAtendimento, Cliente cliente, Funcionario funcionario) {
        this.idAtendimento = idAtendimento;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.valorTotal = valorTotal;
        this.dataAtendimento = dataAtendimento;
        this.cliente = cliente;
        this.funcionario = funcionario;
    }
    
    public Atendimento(int idAtendimento, String horarioInicio, String horarioFim, float valorTotal, String dataAtendimento, int idCliente, int idFuncionario) {
        this.idAtendimento = idAtendimento;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.valorTotal = valorTotal;
        this.dataAtendimento = dataAtendimento;
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
    }
    
    public Atendimento(String horarioInicio, String horarioFim, float valorTotal, String dataAtendimento, int idCliente, int idFuncionario) {
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.valorTotal = valorTotal;
        this.dataAtendimento = dataAtendimento;
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
    }
    
}
