/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import banco.*;
import classes.Compra;
import classes.CompraItem;
import classes.ItemVendido;
import classes.Pessoa;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Christian
 */
public class ServicoCompra {

    private ArrayList<Compra> listaCompra;

    public ArrayList<Compra> getCompras() {
        return listaCompra;
    }

    public ServicoCompra() {
        listaCompra = new ArrayList<Compra>();
        ServicoBancoCompra srv = new ServicoBancoCompra();
        try {
            listaCompra = srv.getAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public void persistirBanco(Compra compra) {
        ServicoBancoCompra srvCompra = new ServicoBancoCompra();
        ServicoBancoCompraItem srvCompraItem = new ServicoBancoCompraItem();
        try {
            srvCompra.insert(compra);
            srvCompra.setCodigoBancoCompra(compra);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }

        ArrayList<CompraItem> listaItens = compra.getItens();
        for (CompraItem p : listaItens) {
            try {
                srvCompraItem.insert(p, compra.getIdCompra());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public void adicionaCompra(Compra compra) {
        this.listaCompra.add(compra);
    }

    public ArrayList<Pessoa> getPessoasFisicaMaisComprou() {
        ServicoBancoCompra srv = new ServicoBancoCompra();
        ArrayList<Pessoa> list = null;
        try {
            list = srv.getPessoasFisicaMaisComprou();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return list;
    }

    public ArrayList<Pessoa> getPessoasJuridicaMenosComprou() {
        ServicoBancoCompra srv = new ServicoBancoCompra();
        ArrayList<Pessoa> list = null;
        try {
            list = srv.getPessoasJuridicaMenosComprou();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return list;
    }

    public ArrayList<ItemVendido> getProdutosVendidos() {
        ServicoBancoCompra srv = new ServicoBancoCompra();
        ArrayList<ItemVendido> list = null;
        try {
            list = srv.getProdutosVendidos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return list;
    }

    public String toString() {
        String res = "";
        for (Compra o : listaCompra) {
            res += o.toString() + "\n";
        }
        return res;
    }

}
