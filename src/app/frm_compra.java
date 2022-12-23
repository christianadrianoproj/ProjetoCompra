/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import classes.Compra;
import classes.CompraItem;
import classes.Pessoa;
import classes.Produto;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import persistencia.ServicoCompra;
import persistencia.ServicoPessoa;
import persistencia.ServicoProduto;

/**
 *
 * @author Christian
 */
public class frm_compra extends javax.swing.JFrame {

    private Compra compra = new Compra();

    public void inicializaControles() {
        jLabelDescontoCompra.setText("Desconto: 0,00");
        jLabelTotalCompra.setText("Total Compra: 0,00");
        jTextDataCompra.setSize(100, 20);
        jTextDataCompra.setText((new SimpleDateFormat("dd/MM/yyyy")).format(new Date()));
        jTextDataCompra.setEnabled(false);
        limpaCamposProduto();
        carregaPessoas();
        carregaProdutos();
        carregaItens();
    }

    /**
     * Creates new form frm_compra
     */
    public frm_compra() {
        initComponents();
        this.setResizable(false);
        inicializaControles();
    }

    private void carregaPessoas() {
        jComboBoxPessoas.removeAllItems();
        ServicoPessoa srv = new ServicoPessoa();
        ArrayList<Pessoa> pessoas = srv.getPessoas();
        for (int i = 0; i < pessoas.size(); i++) {
            jComboBoxPessoas.addItem(pessoas.get(i).toString());
        }
        jComboBoxPessoas.setSelectedItem(null);
    }

    private void carregaProdutos() {
        jComboBoxProdutos.removeAllItems();
        ServicoProduto srv = new ServicoProduto();
        ArrayList<Produto> produtos = srv.getProdutos();
        for (int i = 0; i < produtos.size(); i++) {
            jComboBoxProdutos.addItem(produtos.get(i).toString());
        }
        jComboBoxProdutos.setSelectedItem(null);
    }

    private void limpaCamposProduto() {
        jComboBoxProdutos.setSelectedItem(null);
        jTextQTD.setText("01");
    }

    private void adicionaItem() {
        String itemSelecionado = jComboBoxProdutos.getSelectedItem().toString();
        ServicoProduto srvProduto = new ServicoProduto();
        int posfinal = itemSelecionado.indexOf("-");
        itemSelecionado = itemSelecionado.substring(0, posfinal - 1);
        Produto p = srvProduto.getProduto(Integer.parseInt(itemSelecionado));
        CompraItem item = new CompraItem(compra.getItens().size() + 1, p.getDescricao(), Integer.parseInt(jTextQTD.getText()), p.getValor());
        item.setProduto(p);
        compra.adicionaItem(item);

        limpaCamposProduto();
        carregaItens();

        DecimalFormat df = new DecimalFormat("#,##0.00");
        jLabelDescontoCompra.setText("Desconto: " + df.format(compra.getDesconto()));
        jLabelTotalCompra.setText("Total Compra: " + df.format(compra.getTotalCompra()));
    }

    private int carregaItens() {
        int retorno = 0;
        DefaultTableModel modelo = new DefaultTableModelPersonal();
        jTableItens.setModel(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("Descrição");
        modelo.addColumn("Valor");
        modelo.addColumn("Quantidade");
        modelo.addColumn("Total");
        jTableItens.getColumnModel().getColumn(0).setPreferredWidth(10);
        jTableItens.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTableItens.getColumnModel().getColumn(2).setPreferredWidth(20);
        jTableItens.getColumnModel().getColumn(3).setPreferredWidth(10);
        jTableItens.getColumnModel().getColumn(4).setPreferredWidth(20);
        modelo.setNumRows(0);
        retorno = compra.getItens().size();
        DecimalFormat df = new DecimalFormat("#,##0.00");
        for (CompraItem p : compra.getItens()) {
            modelo.addRow(new Object[]{p.getIdCompraItem(), p.getDescricao(), df.format(p.getValorUnitario()), p.getQuantidade(), df.format(p.getValorUnitario() * p.getQuantidade())});
        }
        return retorno;
    }

    private void efetivaPedidoCompra() {
        ServicoCompra srv = new ServicoCompra();
        
        Date dt = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dt = formatter.parse(jTextDataCompra.getText());
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.toString());
        }
        
        String itemSelecionado = jComboBoxPessoas.getSelectedItem().toString();
        ServicoPessoa srvPessoa = new ServicoPessoa();
        int posfinal = itemSelecionado.indexOf("-");
        itemSelecionado = itemSelecionado.substring(0, posfinal - 1);

        Pessoa p = srvPessoa.getPessoa(Integer.parseInt(itemSelecionado));
        compra.setPessoa(p);
        compra.setDataCompra(dt);
        srv.persistirBanco(compra);

        JOptionPane.showMessageDialog(null, "Pedido de compra salvo com sucesso!");
        compra = new Compra();
        inicializaControles();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelDataCompra = new javax.swing.JLabel();
        jTextDataCompra = new javax.swing.JTextField();
        jComboBoxPessoas = new javax.swing.JComboBox<>();
        jLabelPessoa = new javax.swing.JLabel();
        jComboBoxProdutos = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButtonLancar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextQTD = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableItens = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButtonFechar = new javax.swing.JButton();
        jButtonComprar = new javax.swing.JButton();
        jLabelTotalCompra = new javax.swing.JLabel();
        jLabelDescontoCompra = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pedido de Compra");

        jLabelDataCompra.setText("Data Compra :");

        jTextDataCompra.setText("23/12/2000");

        jComboBoxPessoas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelPessoa.setText("Pessoa :");

        jComboBoxProdutos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Produto :");

        jButtonLancar.setText("Lançar...");
        jButtonLancar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonLancarMouseClicked(evt);
            }
        });
        jButtonLancar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLancarActionPerformed(evt);
            }
        });

        jLabel2.setText("QTD: ");

        jTextQTD.setText("566");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelDataCompra)
                    .addComponent(jLabelPessoa)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxPessoas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextDataCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBoxProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextQTD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonLancar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDataCompra)
                    .addComponent(jTextDataCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxPessoas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPessoa))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLancar)
                    .addComponent(jLabel2)
                    .addComponent(jTextQTD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTableItens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableItens);

        jButtonFechar.setText("Fechar");
        jButtonFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonFecharMouseClicked(evt);
            }
        });
        jButtonFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFecharActionPerformed(evt);
            }
        });

        jButtonComprar.setText("Efetivar pedido de comprar");
        jButtonComprar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonComprarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonComprar)
                .addGap(18, 18, 18)
                .addComponent(jButtonFechar)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButtonFechar)
                .addComponent(jButtonComprar))
        );

        jLabelTotalCompra.setText("total Compra");

        jLabelDescontoCompra.setText("Desconto Compra");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabelDescontoCompra)
                        .addGap(110, 110, 110)
                        .addComponent(jLabelTotalCompra)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTotalCompra)
                    .addComponent(jLabelDescontoCompra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharActionPerformed

    }//GEN-LAST:event_jButtonFecharActionPerformed

    private void jButtonFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonFecharMouseClicked
       this.setVisible(false);
    }//GEN-LAST:event_jButtonFecharMouseClicked

    private void jButtonLancarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonLancarMouseClicked
        adicionaItem();
    }//GEN-LAST:event_jButtonLancarMouseClicked

    private void jButtonComprarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonComprarMouseClicked
        efetivaPedidoCompra();
    }//GEN-LAST:event_jButtonComprarMouseClicked

    private void jButtonLancarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLancarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonLancarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_compra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonComprar;
    private javax.swing.JButton jButtonFechar;
    private javax.swing.JButton jButtonLancar;
    private javax.swing.JComboBox<String> jComboBoxPessoas;
    private javax.swing.JComboBox<String> jComboBoxProdutos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelDataCompra;
    private javax.swing.JLabel jLabelDescontoCompra;
    private javax.swing.JLabel jLabelPessoa;
    private javax.swing.JLabel jLabelTotalCompra;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableItens;
    private javax.swing.JTextField jTextDataCompra;
    private javax.swing.JTextField jTextQTD;
    // End of variables declaration//GEN-END:variables
}
