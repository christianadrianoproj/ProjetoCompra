/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import banco.*;
import classes.Produto;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Christian
 */
public class ServicoProduto {

    private ArrayList<Produto> listaProduto;

    public ServicoProduto() {
        listaProduto = new ArrayList<Produto>();
        ServicoBancoProduto srv = new ServicoBancoProduto();
        try {
            listaProduto = srv.getAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public ArrayList<Produto> getProdutos() {
        return listaProduto;
    }

    public void persistirBanco(Produto produto, boolean insert, boolean edit, boolean delete) {
        ServicoBancoProduto srvProduto = new ServicoBancoProduto();
        try {
            if (insert) {
                srvProduto.insert(produto);
                srvProduto.setCodigoBancoProduto(produto);
            }
            if (edit) {
                srvProduto.update(produto);
            }
            if (delete) {
                srvProduto.delete(produto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public void adicionaProduto(Produto produto) {
        this.listaProduto.add(produto);
    }

    public Produto getProduto(int id) {
        ServicoBancoProduto srv = new ServicoBancoProduto();
        try {
            return srv.getProduto(id);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return null;
    }

    public String toString() {
        String res = "";
        for (Produto o : listaProduto) {
            res += o.toString() + "\n";
        }
        return res;
    }

}
