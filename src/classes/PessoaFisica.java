/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.Date;

/**
 *
 * @author Christian
 */
public class PessoaFisica extends Pessoa {

    private String cpf;
    private int identidade;

    public PessoaFisica(int id, Date dataNasc, String nome, String cpf, int identidade) {
        this.setIdPessoa(id);
        this.setDataNascimento(dataNasc);
        this.setNome(nome);
        this.cpf = cpf;
        this.identidade = identidade;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    public String getCPF() {
        return this.cpf;
    }

    public void setIdentidade(int identidade) {
        this.identidade = identidade;
    }

    public int getIdentidade() {
        return this.identidade;
    }

}
