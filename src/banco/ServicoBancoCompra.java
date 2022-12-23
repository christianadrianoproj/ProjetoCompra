/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.sql.SQLException;
import classes.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Statement;
import persistencia.ServicoPessoa;
import persistencia.ServicoProduto;

/**
 *
 * @author Christian
 */
public class ServicoBancoCompra {

    private ServicoConexao conexao = new ServicoConexao();

    public void insert(Compra compra) throws SQLException {
        String SQL = "insert into tb_compra (id_compra,data_compra,id_pessoa)"
                + " values (0,?,?)";
        PreparedStatement pst = conexao.getConexao().prepareStatement(SQL);
        pst.setDate(1, new java.sql.Date(compra.getDataCompra().getTime()));
        pst.setInt(2, compra.getPessoa().getIdPessoa());
        pst.executeUpdate();
        pst.close();
        conexao.close();
    }

    public void update(Compra compra) throws SQLException {
        String SQL = "update tb_compra set data_compra=?,id_pessoa=? where id_compra = ?";
        PreparedStatement pst = conexao.getConexao().prepareStatement(SQL);
        pst.setDate(1, new java.sql.Date(compra.getDataCompra().getTime()));
        pst.setInt(2, compra.getPessoa().getIdPessoa());
        pst.setInt(3, compra.getIdCompra());
        pst.executeUpdate();
        pst.close();
        conexao.close();
    }

    public void delete(Compra compra) throws SQLException {
        String SQL = "delete from tb_compra where id_compra = ?";
        PreparedStatement pst = conexao.getConexao().prepareStatement(SQL);
        pst.setInt(1, compra.getIdCompra());
        pst.executeUpdate();
        pst.close();
        conexao.close();
    }

    public void setCodigoBancoCompra(Compra compra) throws SQLException {
        String SQL = "select id_compra from tb_compra where data_compra = ? and id_pessoa = ?";
        PreparedStatement pst = conexao.getConexao().prepareStatement(SQL);
        pst.setDate(1, new java.sql.Date(compra.getDataCompra().getTime()));
        pst.setInt(2, compra.getPessoa().getIdPessoa());

        ResultSet rs = pst.executeQuery();
        rs.first();
        compra.setIdCompra(rs.getInt("id_compra"));

        rs.close();
        pst.close();
        conexao.close();
    }

    public ArrayList<Compra> getAll() throws SQLException {
        Statement st = conexao.getConexao().createStatement();
        Statement st2 = conexao.getConexao().createStatement();
        ResultSet rs = st.executeQuery("select c.*, p.* from tb_compra c, tb_pessoa p where p.id_pessoa = c.id_pessoa order by p.nome, c.id_compra");
        ArrayList<Compra> lista = new ArrayList<Compra>();
        int i = 0;
        while (rs.next()) {
            String cpfCnpj = rs.getString("cpf_cnpj");
            if (cpfCnpj.length() <= 11) {
                PessoaFisica pf = new PessoaFisica(rs.getInt("id_pessoa"), rs.getDate("data_nacimento"), rs.getString("nome"), rs.getString("cpf_cnpj"), rs.getInt("identidade"));
                lista.add(new Compra(rs.getInt("id_compra"), rs.getDate("data_compra"), pf));
            }
            if (cpfCnpj.length() > 11) {
                PessoaJuridica pj = new PessoaJuridica(rs.getInt("id_pessoa"), rs.getDate("data_nacimento"), rs.getString("nome"), rs.getString("cpf_cnpj"), rs.getInt("inscricao_estadual"));
                lista.add(new Compra(rs.getInt("id_compra"), rs.getDate("data_compra"), pj));
            }
            ServicoProduto srvProduto = new ServicoProduto();
            ResultSet rp = st2.executeQuery("select * from tb_compra_item where id_compra = " + rs.getInt("id_compra") + " order by id_compra_item");
            while (rp.next()) {
                CompraItem item = new CompraItem(rp.getInt("id_compra_item"), rp.getString("descricao"), rp.getInt("quantidade"), rp.getFloat("valor_unitario"));
                item.setProduto(srvProduto.getProduto(rp.getInt("id_produto")));
                lista.get(i).adicionaItem(item);
            }
            rp.close();
            i++;
        }
        rs.close();
        st.close();
        st2.close();
        conexao.close();
        return lista;
    }

    public ArrayList<Pessoa> getPessoasFisicaMaisComprou() throws SQLException {
        Statement st = conexao.getConexao().createStatement();
        ServicoPessoa srvPessoa = new ServicoPessoa();
        String SQL
                = "select p.id_pessoa, count(*) as qtde_compra "
                + "  from tb_compra c, tb_pessoa p "
                + "where p.id_pessoa = c.id_pessoa "
                + "  and length(p.cpf_cnpj) <= 11 "
                + "group by p.id_pessoa "
                + "order by 2 desc limit 1";
        ResultSet rs = st.executeQuery(SQL);
        ArrayList<Pessoa> lista = new ArrayList<Pessoa>();
        while (rs.next()) {
            lista.add(srvPessoa.getPessoa(rs.getInt("id_pessoa")));
        }
        rs.close();
        st.close();
        conexao.close();
        return lista;
    }

    public ArrayList<Pessoa> getPessoasJuridicaMenosComprou() throws SQLException {
        Statement st = conexao.getConexao().createStatement();
        ServicoPessoa srvPessoa = new ServicoPessoa();
        String SQL
                = "select p.id_pessoa, count(*) as qtde_compra "
                + "  from tb_compra c, tb_pessoa p "
                + "where p.id_pessoa = c.id_pessoa "
                + "  and length(p.cpf_cnpj) > 11 "
                + "group by p.id_pessoa "
                + "order by 2 limit 1";
        ResultSet rs = st.executeQuery(SQL);
        ArrayList<Pessoa> lista = new ArrayList<Pessoa>();
        while (rs.next()) {
            lista.add(srvPessoa.getPessoa(rs.getInt("id_pessoa")));
        }
        rs.close();
        st.close();
        conexao.close();
        return lista;
    }

    public ArrayList<ItemVendido> getProdutosVendidos() throws SQLException {
        Statement st = conexao.getConexao().createStatement();
        ServicoProduto srvProduto = new ServicoProduto();
        String SQL
                = "select p.descricao, p.id_produto, sum(i.quantidade) as qtd "
                + "  from tb_compra c, tb_compra_item i, tb_produto p "
                + "where p.id_produto = i.id_produto "
                + "  and i.id_compra = c.id_compra "
                + "group by p.descricao, p.id_produto "
                + "order by 3 desc ";
        ResultSet rs = st.executeQuery(SQL);
        ArrayList<ItemVendido> lista = new ArrayList<ItemVendido>();
        while (rs.next()) {
            lista.add(new ItemVendido(srvProduto.getProduto(rs.getInt("id_produto")), rs.getInt("qtd")));
        }
        rs.close();
        st.close();
        conexao.close();
        return lista;
    }
}
