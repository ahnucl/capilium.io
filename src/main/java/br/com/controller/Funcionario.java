
package br.com.controller;

public class Funcionario extends Usuario{
    private int idFuncionario;
    private String matricula;
    private Usuario usuario;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Funcionario() {
    }

    public Funcionario(int idFuncionario, String matricula, Usuario usuario, int idUsuario, String nome, String login, String senha) {
        super(idUsuario, nome, login, senha);
        this.idFuncionario = idFuncionario;
        this.matricula = matricula;
        this.usuario = usuario;
    }
    
}
