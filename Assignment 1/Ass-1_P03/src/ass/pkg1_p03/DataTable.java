/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ass.pkg1_p03;

import java.util.Hashtable;

/**
 *
 * @author kevil-gandhi
 */
public class DataTable implements Featurable {

    private int rows;
    private int cols;
    private String[][] table;

    private String foreground = "Black";
    private String background = "White";
    private String font = "Arial";

    public DataTable(int rows, int cols) throws TableException {

        if (rows > 200 || cols > 200) {
            throw new TableException("Rows or Cols can't exceed 200.");
        }

        this.rows = rows;
        this.cols = cols;
        table = new String[rows][cols];
    }

    public void setCell(int row, int col, String value) {
        table[row][col] = value;
    }

    public void display() {
        System.out.println("\n--- TABLE DATA ---");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print((table[i][j] == null ? "-" : table[i][j]) + "\t");
            }
            System.out.println();
        }
    }

    public void insertRow() {
        rows++;
        String[][] newTable = new String[rows][cols];
        System.arraycopy(table, 0, newTable, 0, table.length);
        table = newTable;
    }

    public void deleteRow(int row) {
        if (row < rows) {
            for (int i = row; i < rows - 1; i++) {
                table[i] = table[i + 1];
            }
            rows--;
        }
    }

    public void insertColumn() {
        cols++;
        String[][] newTable = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(table[i], 0, newTable[i], 0, table[i].length);
        }
        table = newTable;
    }

    public void deleteColumn(int col) {
        for (int i = 0; i < rows; i++) {
            for (int j = col; j < cols - 1; j++) {
                table[i][j] = table[i][j + 1];
            }
        }
        cols--;
    }

    /* ---------- Hashtable Population ---------- */
    public void populateFromHashtable(Hashtable<String, String> data) {
        int i = 0;
        for (String key : data.keySet()) {
            if (i < rows) {
                table[i][0] = key;
                table[i][1] = data.get(key);
                i++;
            }
        }
    }

    @Override
    public void setForeground(String color) {
        foreground = color;
    }

    @Override
    public void setBackground(String color) {
        background = color;
    }

    @Override
    public void setFont(String font) {
        this.font = font;
    }

    @Override
    public String getForeground() {
        return foreground;
    }

    @Override
    public String getBackground() {
        return background;
    }

    @Override
    public String getFont() {
        return font;
    }
}
