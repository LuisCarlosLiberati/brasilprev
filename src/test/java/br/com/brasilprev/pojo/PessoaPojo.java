package br.com.brasilprev.pojo;

import java.util.ArrayList;

public class PessoaPojo {

    private String nome;
    private String cpf;
    private ArrayList<EnderecoPojo> enderecos;
    private ArrayList<ContatoPojo> telefones;


    public ArrayList<EnderecoPojo> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(ArrayList<EnderecoPojo> enderecos) {
        this.enderecos = enderecos;
    }

    public ArrayList<ContatoPojo> getTelefones() {
        return telefones;
    }

    public void setTelefones(ArrayList<ContatoPojo> telefones) {
        this.telefones = telefones;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
