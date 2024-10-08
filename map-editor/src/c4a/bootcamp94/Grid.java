package c4a.bootcamp94;

import c4a.bootcamp94.grid.Cell;

public class Grid {

    public static final int CELL_SIZE = 20;
    public static final int PADDING = 10;
    private int rows;
    private int cols;

    private Cell[][] cells;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        cells = new Cell[rows][cols];

        for (int row = 0; row < rows; row++){
            for (int col = 0; col < cols; col++){
                cells[row][col] = new Cell(row, col);
            }
        }
    }

    public void stringToGrid(String grid) {

        int index = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                Cell cell = cells[row][col];

                if (grid.charAt(index) == '0') {
                    cell.erase();
                } else {
                    cell.paint();
                }

                index++;
            }
            index++;
        }
    }

    public void clear() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cells[row][col].erase();
            }
        }
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
               stringBuilder.append(cells[row][col]);
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();

    }
}
