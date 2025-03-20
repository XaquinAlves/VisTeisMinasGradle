package gal.iespazodamerce.visteisminasfx.control;

import java.util.ArrayList;
import gal.iespazodamerce.visteisminasfx.model.Cell;

/**
 * Clase que implementa a funcionalidade do buscaminas, representando
 * unha partida do xogo
 *
 * @author Xaquin Alves Gonzalez
 */
public class Game {

    private final int ROWS;
    private final int COLUMNS;
    private Cell[][] cells;

    /**
     * Crea unha nova instancia de Game coas medidas introducidas
     * como parametroe o numero de minas indicado
     *
     * @param rows    numero de filas a establecer
     * @param columns numero de columnas a establecer
     * @param mines numero de minas a establecer
     */
    public Game(int rows, int columns,int mines) {
        this.ROWS = rows;
        this.COLUMNS = columns;
        cells = new Cell[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                cells[i][j] = new Cell(i,j);
            }
        }

        fillMines(mines);
    }

    /**
     * Devolve a celda da posicion dada
     *
     * @param row    posicion nas filas
     * @param column posicion nas columnas
     * @return celda que se atopa na posicion dada
     */
    public Cell getCell(int row, int column) {
        return cells[row][column];
    }

    /**
     * Obten un array de celdas coas celdas adxacentes a introducida
     * como paramentro
     *
     * @param cell celda a analizar
     * @return ArrayList de celdas adxacentes
     */
    private ArrayList<Cell> getAdjacentCells(Cell cell) {
        ArrayList<Cell> adjacentCells = new ArrayList<>();
        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> columns = new ArrayList<>();

        //Gardamos en rows as posicions de fila adxacentes que sexan validas
        rows.add(cell.getRow());
        if ((cell.getRow() - 1) >= 0) {
            rows.add(cell.getRow() - 1);
        }
        if ((cell.getRow() + 1) < ROWS) {
            rows.add(cell.getRow() + 1);
        }
        //Mismo proceso coas columnas
        columns.add(cell.getColumn());
        if ((cell.getColumn() - 1) >= 0) {
            columns.add(cell.getColumn() - 1);
        }
        if ((cell.getColumn() + 1) < COLUMNS) {
            columns.add(cell.getColumn() + 1);
        }
        /* Cos arrays de posicions creados, obtemos as celdas adxacentes,
        asegurandonos que non se usa unha posicion fora dos limites do
        array de celdas */
        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < columns.size(); j++) {
                adjacentCells.add(cells[rows.get(i)][columns.get(j)]);
            }
        }

        return adjacentCells;
    }

    /**
     * Devolve o numero de minas adxacentes a celda pasada como parametro
     *
     * @param cell celda a analizar
     * @return numero de minas adxacentes
     */
    public int getAdjacentMines(Cell cell) {
        ArrayList<Cell> adjacentCells = getAdjacentCells(cell);
        int adjacentMines = 0;
        for (Cell cell1: adjacentCells){
            if (cell1.isMined()){
                adjacentMines++;
            }
        }
        return adjacentMines;
    }

    /**
     * Destapa unha celda, e en caso de que non haxa minas adxacentes,
     * destapa tamen todas as celdas adxacentes, de forma recursiva
     *
     * @param cell celda a destapar
     */
    public void openCell(Cell cell) {
        cell.setState(Cell.getDestapada());

        if(getAdjacentMines(cell) == 0) {
            ArrayList<Cell> adjacentCells = getAdjacentCells(cell);
            for (int i = 0; i < adjacentCells.size(); i++) {
                adjacentCells.get(i).setState(Cell.getDestapada());
            }
        }
    }

    /**
     * Destapa todas as celdas con minas, usado cando acaba a partida
     */
    public void openAllMines() {
        System.out.println("Parida perdida: ");

        for (Cell[] cells1 : cells) {
            for (Cell cell : cells1) {
                if (cell.isMined()) {
                    cell.setState(Cell.getDestapada());
                }
            }
        }
    }

    /**
     * Comproba se quedan celdas tapadas sen minas,
     * usado para saber se o xogador gañou a partida
     *
     * @return se quedan celdas tapadas sen minas
     */
    public boolean checkCellsToOpen() {
        for (Cell[] cells1 : cells) {
            for (Cell cell : cells1) {
                if (!cell.isMined() && cell.getState() != Cell.getDestapada()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Inche as celdas co numero de minas indicado en posicions aleatorias
     *
     * @param mines numero de minas
     */
    private void fillMines(int mines) {
        int row, colum;

        for (int i = 0; i < mines; i++) {
            do {
                row = new java.util.Random().nextInt(ROWS);
                colum = new java.util.Random().nextInt(COLUMNS);
            }while(cells[row][colum].isMined());

            cells[row][colum].setMined(true);
        }
    }

}
