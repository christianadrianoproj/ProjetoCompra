/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.util.ArrayList;

/**
 *
 * @author Christian
 */
public class SimpleTableModel {

    private ArrayList linhas = null;
    private String[] colunas = null;

    public ArrayList getLinhas() {
        return linhas;
    }

    public String[] getColunas() {
        return colunas;
    }

    private void setColunas(String[] strings) {
        colunas = strings;

    }

    private void setLinhas(ArrayList list) {
        linhas = list;
    }

    public int getRowCount() {
        return getLinhas().size();
    }

    public int getColumnCount() {
        return getColunas().length;
    }

    public String getColumnName(int column) {
        return colunas[column];
    }

    public Object getValueAt(int rowindex, int columnindex) {
        String[] linha = (String[]) getLinhas().get(rowindex);
        return linha[columnindex];
    }

    public SimpleTableModel(ArrayList dados, String[] colunas) {
        this.setColunas(colunas);
        this.setLinhas(dados);
    }    
}
