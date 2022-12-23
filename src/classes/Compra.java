/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import persistencia.ServicoPessoa;

/**
 *
 * @author Christian
 */
public class Compra {

    private int idCompra;
    private Date dataCompra;
    private Pessoa pessoa;
    private ArrayList<CompraItem> itens = new ArrayList<CompraItem>();

    public Compra(int id, Date data, Pessoa pes) {
        this.idCompra = id;
        this.dataCompra = data;
        this.pessoa = pes;
    }

    public Compra() {
        this.idCompra = 0;
        this.dataCompra = null;
        this.pessoa = null;
    }

    public float getTotalCompra() {
        float totalCompra = 0.0f;
        for (CompraItem i : itens) {
            totalCompra += (i.getValorUnitario() * i.getQuantidade());
        }
        return totalCompra;
    }

    public float getDesconto() {
        float totalCompra = getTotalCompra();
        ServicoPessoa srvPessoa = new ServicoPessoa();

        if ((srvPessoa.isPessoaFisica(pessoa)) && (totalCompra > 800)) {
            totalCompra = (totalCompra * 0.08f);
        } else if ((srvPessoa.isPessoaJuridica(pessoa)) && (totalCompra > 5000)) {
            totalCompra = (totalCompra * 0.15f);
        } else {
            totalCompra = (totalCompra * 0.03f);
        }

        return totalCompra;
    }

    public void setIdCompra(int id) {
        this.idCompra = id;
    }

    public int getIdCompra() {
        return this.idCompra;
    }

    public void setDataCompra(Date data) {
        this.dataCompra = data;
    }

    public Date getDataCompra() {
        return this.dataCompra;
    }

    public void setPessoa(Pessoa pes) {
        this.pessoa = pes;
    }

    public Pessoa getPessoa() {
        return this.pessoa;
    }

    public void adicionaItem(CompraItem item) {
        this.itens.add(item);
    }

    public ArrayList<CompraItem> getItens() {
        return this.itens;
    }

    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat fdf = new DecimalFormat("#,##0.00");
        String str = "Código Compra: " + this.getIdCompra() + "\nData: " + df.format(this.getDataCompra());
        str += "\nPessoa - Id: " + this.getPessoa().getIdPessoa();
        str += " - Nome: " + this.getPessoa().getNome();
        str += " - Data Nasc/Fundação: " + df.format(this.getPessoa().getDataNascimento());
        str += "\nTotal: " + fdf.format(this.getTotalCompra());
        str += " - Desconto: " + fdf.format(this.getDesconto());
        str += " - Total Compra: " + fdf.format(this.getTotalCompra() - this.getDesconto());
        return str;
    }

}
