
package br.com.controller;

public class Funcionario {
    private int idFuncionario;
    private String matricula;
    private String nome;

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Funcionario() {
    }

    public Funcionario(int idFuncionario, String matricula, String nome) {
        this.idFuncionario = idFuncionario;
        this.matricula = matricula;
        this.nome = nome;
    }
    
}
