package gal.iespazodamerce.visteisminasfx.view;

import gal.iespazodamerce.visteisminasfx.control.Game;
import gal.iespazodamerce.visteisminasfx.model.Cell;

import java.util.Scanner;


/**
 * Clase que implementa a interface do xogo, en modo texto
 */
public class VisTeisMinasMenu {
    private int ROWS = 6;
    private int COLUMNS = 6;
    private int MINES = 8;

    /**
     * Mostra o panel de minas
     *
     * @param game referencia a partida actual
     */
    private void showPanel(Game game) {
        System.out.println("---      Estado do panel        ---");
        System.out.print("\n  ");
        for (int i = 0; i < COLUMNS; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(" -------------");
        for (int i = 0; i < ROWS; i++) {

            String row = Integer.toString(i);
            for (int j = 0; j < COLUMNS; j++) {
                /*
                1 tapada
                2 marcada
                3 destapada
                 */
                switch (game.getCell(i, j).getState()) {
                    case 1 -> row = row.concat("| ");
                    case 2 -> row = row.concat("|X");
                    case 3 -> {
                        if (game.getCell(i, j).isMined()) {
                            row = row.concat("|!");
                        } else {
                            row = row.concat("|" + game.getAdjacentMines(game.getCell(i, j)));
                        }
                    }

                }
            }
            row = row.concat("|");
            System.out.println(row);
            System.out.println(" -------------");
        }
    }

    /**
     * Inicia unha partida, mostra o panel e pide unha accion
     * de forma repetitiva hasta que acabe a partida, preguntando
     * se se quere xogar outra ao rematar
     */
    public void startNewGame() {
        Scanner scanner = new Scanner(System.in);

        do {
            int choice;
            System.out.println("""
                    Seleccione un nivel de dificultade:
                    1.Baixo
                    2.Medio
                    3.Alto""");

            do {
                System.out.print("Eleccion: ");
                choice = scanner.nextInt();
                scanner.nextLine();
            } while (choice < 1 || choice > 3);

            if (choice == 2) {
                ROWS = COLUMNS = 8; MINES = 20;
            } else if (choice == 3) {
                ROWS = COLUMNS = 10; MINES = 40;
            }

            Game game = new Game(ROWS, COLUMNS, MINES);
            boolean failed = false;

            do {
                failed = playTurn(game, scanner, failed);
            } while (game.checkCellsToOpen() && !failed);

        } while (nextGame(scanner));
    }

    private boolean playTurn(Game game, Scanner scanner, boolean failed) {
        System.out.println("---Estas xogando ao VisteisMinas---");
        System.out.println("---  version por: Xaquin Alves  ---");
        showPanel(game);
        System.out.print("Introduce a accion(s:Sair, m:Marcar unha celda, d: Desmarcar unha celda, a:Abrir unha celda): ");
        char choice = scanner.nextLine().charAt(0);

        switch (choice) {
            case 's' -> failed = true;
            case 'm' -> selectCell(game, scanner, "Selecciona a celda a marcar: ").setState(Cell.getMarcada());
            case 'd' -> {
                Cell cell = selectCell(game, scanner, "Selecciona a celda a desmarcar: ");
                if (cell.getState() == Cell.getTapada()) {
                    System.out.println("A celda seleccionada non esta marcada");
                } else if (cell.getState() == Cell.getDestapada()) {
                    System.out.println("A celda xa esta aberta");
                } else {
                    cell.setState(Cell.getMarcada());
                }
            }
            case 'a' -> {
                Cell cell = selectCell(game, scanner, "Selecciona unha celda para abrir: ");
                game.openCell(cell);
                if (cell.isMined()) {
                    game.openAllMines();
                    showPanel(game);
                    failed = true;
                }
            }
        }
        return failed;
    }

    /**
     * Usado para pedir ao usuario que selecione unha celda, devolvendo a celda
     * seleccionada
     *
     * @param game    referencia a partida actual
     * @param scanner para pedir datos
     * @param text    mensaxe inicial do metodo
     * @return celda seleccionada
     */
    private Cell selectCell(Game game, Scanner scanner, String text) {
        System.out.println(text);
        System.out.print("Introduce a Fila:");
        int row = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Introduce a Columna:");
        int column = scanner.nextInt();
        scanner.nextLine();

        while (row >= ROWS || row < 0 || column >= COLUMNS || column < 0) {
            System.out.println("Posicion non valida. Introduza outra posicion: ");
            System.out.print("Introduce a Fila:");
            row = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Introduce a Columna:");
            column = scanner.nextInt();
            scanner.nextLine();
        }

        return game.getCell(row, column);
    }

    /**
     * Pregunta se se quere xogar unha nova partida, devolvendo un booleano
     * en funcion da resposta
     *
     * @param scanner para pedir datos
     * @return se se quere xogar outra partida
     */
    private boolean nextGame(Scanner scanner) {
        System.out.print("Desexa xogar outra partida?(s/n):");
        char choice = scanner.nextLine().charAt(0);

        return choice == 's' || choice == 'S';
    }
}
