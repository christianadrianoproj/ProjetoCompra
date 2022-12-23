/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import banco.*;
import classes.Pessoa;
import classes.PessoaFisica;
import classes.PessoaJuridica;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Christian
 */
public class ServicoPessoa {

    private ArrayList<Pessoa> listaPessoa;

    public ServicoPessoa() {
        listaPessoa = new ArrayList<Pessoa>();
        ServicoBancoPessoa srvPessoa = new ServicoBancoPessoa();
        try {
            listaPessoa = srvPessoa.getAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    
    public ArrayList<Pessoa> getPessoas() {
        return listaPessoa;
    }

    public void persistirBanco(Pessoa pessoa, boolean insert, boolean edit, boolean delete) {
        ServicoBancoPessoa srvPessoa = new ServicoBancoPessoa();
        try {
            if (insert) {
                srvPessoa.insert(pessoa);
                srvPessoa.setCodigoBancoPessoa(pessoa);
            }
            if (edit) {
                srvPessoa.update(pessoa);
            }
            if (delete) {
                srvPessoa.delete(pessoa);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public void adicionaPessoa(Pessoa pessoa) {
        this.listaPessoa.add(pessoa);
    }

    public boolean isPessoaFisica(Pessoa p) {
        try {
            PessoaFisica pf = (PessoaFisica) p;
            if (pf.getCPF().length() != 0) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public boolean isPessoaJuridica(Pessoa p) {
        try {
            PessoaJuridica pj = (PessoaJuridica) p;
            if (pj.getCnpj().length() != 0) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public Pessoa getPessoa(int id) {
        ServicoBancoPessoa srvPessoa = new ServicoBancoPessoa();
        try {
            return srvPessoa.getPessoa(id);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return null;
    }

    public String toString() {
        String res = "";
        for (Pessoa o : listaPessoa) {
            res += o.toString() + "\n";
        }
        return res;
    }
}
