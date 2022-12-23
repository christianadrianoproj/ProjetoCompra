/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Christian
 */
public class Pessoa {

    private int idPessoa;
    private Date dataNascimento;
    private String nome;

    /* public Pessoa(int id, Date dataNasc, String nome) {
        this.idPessoa = id;
        this.dataNascimento = dataNasc;
        this.nome = nome;
    }*/
    public int getIdPessoa() {
        return this.idPessoa;
    }

    public void setIdPessoa(int id) {
        this.idPessoa = id;
    }

    public int getIdade() {
        if (dataNascimento != null) {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            int anoNascimento = Integer.parseInt(df.format(dataNascimento));
            return (cal.get(Calendar.YEAR) - anoNascimento);
        }
        return 0;
    }

    public void setDataNascimento(Date data) {
        this.dataNascimento = data;
    }

    public Date getDataNascimento() {
        return this.dataNascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }
    
    @Override
    public String toString() {
        return this.getIdPessoa() + " - " + this.getNome();
    }

}
