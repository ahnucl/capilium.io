package br.com.controller;

public class Cliente extends Usuario{
   private int idCliente;
   private String telefone,email,cpf;
   private Usuario usuario;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente() {
    }

    public Cliente(int idCliente, String telefone, String email, String cpf, Usuario usuario, int idUsuario, String nome, String login, String senha) {
        super(idUsuario, nome, login, senha);
        this.idCliente = idCliente;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
        this.usuario = usuario;
    }
  
}
