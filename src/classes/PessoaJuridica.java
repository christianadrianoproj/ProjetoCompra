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
public class PessoaJuridica extends Pessoa {

    private String cnpj;
    private int inscricaoEstadual;

    public PessoaJuridica(int id, Date dataNasc, String nome, String cnpj, int inscricaoEstadual) {
        this.setIdPessoa(id);
        this.setDataNascimento(dataNasc);
        this.setNome(nome);
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
    }    
    
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setInscricaoEstadual(int inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public int getInscricaoEstadual() {
        return this.inscricaoEstadual;
    }

}
