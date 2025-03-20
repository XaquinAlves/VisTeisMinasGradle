package gal.iespazodamerce.visteisminasfx.model;

/**
 * Clase que representa unha celda da pantalla de xogo
 * do buscaminas
 *
 * @author Xaquin Alves Gonzalez
 */
public class Cell {

    private final static int TAPADA = 1;
    private final static int MARCADA = 2;
    private final static int DESTAPADA = 3;
    private boolean mined;
    private int state;
    private int row;
    private int column;

    /**
     * Crea unha nova instancia de Cell, sen mina e TAPADA, coa
     * posicion indicada como parametros
     *
     * @param row fila da celda a crear
     * @param column columna da celda a crear
     */
    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.mined = false;
        this.state = TAPADA;
    }

    /**
     * Obten o valor equivalente a estar TAPADA
     *
     * @return valor de estado cando TAPADA
     */
    public static int getTapada() {
        return TAPADA;
    }

    /**
     * Obten o valor equivalente a estar MARCADA
     *
     * @return valor de estado cando MARCADA
     */
    public static int getMarcada() {
        return MARCADA;
    }

    /**
     * Obten o valor equivalente a estar DESTAPADA
     *
     * @return valor de estado cando DESTAPADA
     */
    public static int getDestapada() {
        return DESTAPADA;
    }

    /**
     * @return se esta minada
     */
    public boolean isMined() {
        return mined;
    }

    /**
     * @param mined establece se esta minada
     */
    public void setMined(boolean mined) {
        this.mined = mined;
    }

    /**
     * Obten o estado da celda:
     * 1 = TAPADA
     * 2 = MARCADA
     * 3 = DESTAPADA
     *
     * @return estado da celda
     */
    public int getState() {
        return state;
    }

    /**
     * Define o estado da celda:
     * 1 = TAPADA
     * 2 = MARCADA
     * 3 = DESTAPADA
     *
     * @param state estado a establecer
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * @return fila da celda
     */
    public int getRow() {
        return row;
    }

    /**
     * @param row fila da celda a establecer
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * @return columna da celda
     */
    public int getColumn() {
        return column;
    }

    /**
     * @param column columna da celda a establecer
     */
    public void setColumn(int column) {
        this.column = column;
    }
}
