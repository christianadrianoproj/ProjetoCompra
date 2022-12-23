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
import java.sql.Statement;
import java.util.ArrayList;
import persistencia.ServicoPessoa;

/**
 *
 * @author Christian
 */
public class ServicoBancoPessoa {

    private ServicoConexao conexao = new ServicoConexao();

    public void insert(Pessoa pessoa) throws SQLException {
        ServicoPessoa srv = new ServicoPessoa();
        if (srv.isPessoaFisica(pessoa)) {
            PessoaFisica pf = (PessoaFisica) pessoa;
            String SQL
                    = "insert into tb_pessoa (id_pessoa,data_nacimento,idade,nome,cpf_cnpj,identidade)"
                    + " values (0,?,?,?,?,?)";
            PreparedStatement pst = conexao.getConexao().prepareStatement(SQL);
            pst.setDate(1, new java.sql.Date(pf.getDataNascimento().getTime()));
            pst.setInt(2, pf.getIdade());
            pst.setString(3, pf.getNome());
            pst.setString(4, pf.getCPF());
            pst.setInt(5, pf.getIdentidade());

            pst.executeUpdate();
            pst.close();
            conexao.close();
        }
        if (srv.isPessoaJuridica(pessoa)) {
            PessoaJuridica pj = (PessoaJuridica) pessoa;
            String SQL
                    = "insert into tb_pessoa (id_pessoa,nome,cpf_cnpj,inscricao_estadual,data_nacimento,idade)"
                    + " values (0,?,?,?,?,?,?)";
            PreparedStatement pst = conexao.getConexao().prepareStatement(SQL);
            pst.setString(1, pj.getNome());
            pst.setString(2, pj.getCnpj());
            pst.setInt(3, pj.getInscricaoEstadual());
            pst.setDate(4, new java.sql.Date(pj.getDataNascimento().getTime()));
            pst.setInt(5, pj.getIdade());

            pst.executeUpdate();
            pst.close();
            conexao.close();
        }
    }

    public void update(Pessoa pessoa) throws SQLException {
        ServicoPessoa srv = new ServicoPessoa();
        if (srv.isPessoaFisica(pessoa)) {
            PessoaFisica pf = (PessoaFisica) pessoa;
            String SQL = "update tb_pessoa "
                    + "set data_nacimento = ?,idade=?,nome=?,cpf_cnpj=?,identidade=?"
                    + " where id_pessoa = ?";
            PreparedStatement pst = conexao.getConexao().prepareStatement(SQL);
            pst.setDate(1, new java.sql.Date(pf.getDataNascimento().getTime()));
            pst.setInt(2, pf.getIdade());
            pst.setString(3, pf.getNome());
            pst.setString(4, pf.getCPF());
            pst.setInt(5, pf.getIdentidade());
            pst.setInt(6, pf.getIdPessoa());
            pst.executeUpdate();
            pst.close();
            conexao.close();
        }

        if (srv.isPessoaJuridica(pessoa)) {
            PessoaJuridica pj = (PessoaJuridica) pessoa;
            String SQL = "update tb_pessoa "
                    + "set nome=?,cpf_cnpj=?,inscricao_estadual=?,idade=?,data_nacimento = ? "
                    + " where id_pessoa = ?";
            PreparedStatement pst = conexao.getConexao().prepareStatement(SQL);
            pst.setString(1, pj.getNome());
            pst.setString(2, pj.getCnpj());
            pst.setInt(3, pj.getInscricaoEstadual());
            pst.setInt(4, pj.getIdade());
            pst.setDate(5, new java.sql.Date(pj.getDataNascimento().getTime()));
            pst.setInt(6, pj.getIdPessoa());
            pst.executeUpdate();
            pst.close();
            conexao.close();
        }
    }

    public void delete(Pessoa pessoa) throws SQLException {
        PreparedStatement pst = conexao.getConexao().prepareStatement("delete from tb_pessoa where id_pessoa = ?");
        pst.setInt(1, pessoa.getIdPessoa());
        pst.executeUpdate();
        pst.close();
        conexao.close();
    }

    public void setCodigoBancoPessoa(Pessoa pessoa) throws SQLException {
        ServicoPessoa srv = new ServicoPessoa();
        if (srv.isPessoaFisica(pessoa)) {
            PessoaFisica pf = (PessoaFisica) pessoa;
            String SQL
                    = "select id_pessoa from tb_pessoa "
                    + "where data_nacimento = ? "
                    + " and idade = ? "
                    + " and nome =? "
                    + " and cpf_cnpj = ? "
                    + " and identidade = ?";
            PreparedStatement pst = conexao.getConexao().prepareStatement(SQL);
            pst.setDate(1, new java.sql.Date(pf.getDataNascimento().getTime()));
            pst.setInt(2, pf.getIdade());
            pst.setString(3, pf.getNome());
            pst.setString(4, pf.getCPF());
            pst.setInt(5, pf.getIdentidade());

            ResultSet rs = pst.executeQuery();
            rs.first();
            int qtde = rs.getRow();
            rs.first();
            if (qtde > 0) {
                pessoa.setIdPessoa(rs.getInt("id_pessoa"));
            }
            rs.close();
            pst.close();
            conexao.close();
        }
        if (srv.isPessoaJuridica(pessoa)) {
            PessoaJuridica pj = (PessoaJuridica) pessoa;
            String SQL
                    = "select id_pessoa from tb_pessoa "
                    + "where nome =? "
                    + " and cpf_cnpj = ? "
                    + " and inscricao_estadual = ? "
                    + " and idade = ? "
                    + " and data_nacimento = ?";
            PreparedStatement pst = conexao.getConexao().prepareStatement(SQL);
            pst.setString(1, pj.getNome());
            pst.setString(2, pj.getCnpj());
            pst.setInt(3, pj.getInscricaoEstadual());
            pst.setInt(4, pj.getIdade());
            pst.setDate(5, new java.sql.Date(pj.getDataNascimento().getTime()));

            ResultSet rs = pst.executeQuery();
            rs.last();
            int qtde = rs.getRow();
            rs.first();
            if (qtde > 0) {
                pessoa.setIdPessoa(rs.getInt("id_pessoa"));
            }
            rs.close();
            pst.close();
            conexao.close();
        }

    }

    public ArrayList<Pessoa> getAll() throws SQLException {
        Statement st = conexao.getConexao().createStatement();
        ResultSet rs = st.executeQuery("select * from tb_pessoa");
        ArrayList<Pessoa> lista = new ArrayList<Pessoa>();
        while (rs.next()) {
            String cpfCnpj = rs.getString("cpf_cnpj");
            if (cpfCnpj.length() <= 11) {
                PessoaFisica pf = new PessoaFisica(rs.getInt("id_pessoa"), rs.getDate("data_nacimento"), rs.getString("nome"), rs.getString("cpf_cnpj"), rs.getInt("identidade"));
                lista.add(pf);
            }
            if (cpfCnpj.length() > 11) {
                PessoaJuridica pj = new PessoaJuridica(rs.getInt("id_pessoa"), rs.getDate("data_nacimento"), rs.getString("nome"), rs.getString("cpf_cnpj"), rs.getInt("inscricao_estadual"));
                lista.add(pj);
            }
        }
        rs.close();
        st.close();
        conexao.close();
        return lista;
    }

    public Pessoa getPessoa(int id) throws SQLException {
        Pessoa p = null;
        String SQL = "select * from tb_pessoa where id_pessoa = " + String.valueOf(id);
        Statement st = conexao.getConexao().createStatement();
        ResultSet rs = st.executeQuery(SQL);
        while (rs.next()) {
            String cpfCnpj = rs.getString("cpf_cnpj");
            if (cpfCnpj.length() <= 11) {
                p = new PessoaFisica(rs.getInt("id_pessoa"), rs.getDate("data_nacimento"), rs.getString("nome"), rs.getString("cpf_cnpj"), rs.getInt("identidade"));
            }
            if (cpfCnpj.length() > 11) {
                p = new PessoaJuridica(rs.getInt("id_pessoa"), rs.getDate("data_nacimento"), rs.getString("nome"), rs.getString("cpf_cnpj"), rs.getInt("inscricao_estadual"));
            }
        }
        rs.close();
        st.close();
        conexao.close();
        return p;
    }

}
