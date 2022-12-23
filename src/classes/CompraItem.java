/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.text.DecimalFormat;

/**
 *
 * @author Christian
 */
public class CompraItem {

    private int idCompraItem;
    private String descricao;
    private int quantidade;
    private float valorUnitario;
    private Produto produto;

    public CompraItem(int id, String desc, int qtde, float valor) {
        this.idCompraItem = id;
        this.descricao = desc;
        this.quantidade = qtde;
        this.valorUnitario = valor;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String str = "Código Item: " + this.getIdCompraItem();
        str += "\nDescrição: " + this.getDescricao();
        str += "\nValor Unitário: " + df.format(this.getValorUnitario());
        str += "\nQuantidade: " + String.valueOf(this.getQuantidade());
        str += "\nTotal: " + df.format(this.getValorUnitario() * this.getQuantidade());
        return str;
    }

    public void setProduto(Produto p) {
        this.produto = p;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public void setIdCompraItem(int id) {
        this.idCompraItem = id;
    }

    public int getIdCompraItem() {
        return this.idCompraItem;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setQuantidade(int qtd) {
        this.quantidade = qtd;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setValorUnitario(float valor) {
        this.valorUnitario = valor;
    }

    public float getValorUnitario() {
        return this.valorUnitario;
    }
}
