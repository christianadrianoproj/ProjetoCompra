/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import classes.*;
import java.sql.ResultSet;

/**
 *
 * @author Christian
 */
public class ServicoBancoCompraItem {

    private ServicoConexao conexao = new ServicoConexao();

    public void insert(CompraItem item, int idCompra) throws SQLException {
        String SQL = "insert into tb_compra_item "
                + "(id_compra_item,descricao,quantidade,valor_unitario,id_compra,id_produto)"
                + " values (0,?,?,?,?,?)";
        PreparedStatement pst = conexao.getConexao().prepareStatement(SQL);
        pst.setString(1, item.getDescricao());
        pst.setInt(2, item.getQuantidade());
        pst.setFloat(3, item.getValorUnitario());
        pst.setInt(4, idCompra);
        pst.setInt(5, item.getProduto().getIdProduto());
        pst.executeUpdate();
        pst.close();
        conexao.close();
    }

    public void update(CompraItem item) throws SQLException {
        String SQL = "update tb_compra_item "
                + "set descricao = ?,quantidade=?,valor_unitario=?, id_produto=? "
                + " where id_compra_item = ?";
        PreparedStatement pst = conexao.getConexao().prepareStatement(SQL);
        pst.setString(1, item.getDescricao());
        pst.setInt(2, item.getQuantidade());
        pst.setFloat(3, item.getValorUnitario());
        pst.setFloat(4, item.getProduto().getIdProduto());
        pst.setInt(5, item.getIdCompraItem());
        pst.executeUpdate();
        pst.close();
        conexao.close();
    }

    public void delete(CompraItem item) throws SQLException {
        String SQL = "delete from tb_compra_item where id_compra_item = ?";
        PreparedStatement pst = conexao.getConexao().prepareStatement(SQL);
        pst.setInt(1, item.getIdCompraItem());
        pst.executeUpdate();
        pst.close();
        conexao.close();
    }

    public void setCodigoBancoCompraItem(CompraItem item, int idCompra) throws SQLException {
        String SQL = "select id_compra_item from tb_compra_item where descricao = ?,quantidade=?,valor_unitario=?,id_compra=?,id_produto=?";
        PreparedStatement pst = conexao.getConexao().prepareStatement(SQL);
        pst.setString(1, item.getDescricao());
        pst.setInt(2, item.getQuantidade());
        pst.setFloat(3, item.getValorUnitario());
        pst.setInt(4, idCompra);
        pst.setInt(5, item.getProduto().getIdProduto());

        ResultSet rs = pst.executeQuery();
        rs.first();
        item.setIdCompraItem(rs.getInt("id_compra_item"));

        rs.close();
        pst.close();
        conexao.close();
    }

}
