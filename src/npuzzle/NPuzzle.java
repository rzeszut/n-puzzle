/**
 * 
 */
package npuzzle;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/**
 * Klasa reprezentująca n-puzzle.
 * 
 * @author mateusz
 */
public class NPuzzle {
    /**
     * Konstruktor.
     * 
     * @param dimension
     *            wymiar układanki
     * @param puzzle_array
     *            tablica z układanką
     */
    public NPuzzle(int dimension, int[][] puzzle_array) {
        this.dimension = dimension;
        this.puzzleArray = puzzle_array;

        xCoords = new int[dimension * dimension];
        yCoords = new int[dimension * dimension];

        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; ++j) {
                int tile = puzzleArray[i][j];
                xCoords[tile] = i;
                yCoords[tile] = j;
            }
        }
    }

    /**
     * Konstruktor służący do kopiowania.
     */
    private NPuzzle(int dimension, int[][] puzzle_array, int[] xCoords,
            int[] yCoords) {
        this.dimension = dimension;
        this.puzzleArray = puzzle_array;
        this.xCoords = xCoords;
        this.yCoords = yCoords;
    }

    /**
     * Zwraca kopię stanu.
     * 
     * @return kopia
     */
    public NPuzzle clone() {
        // mam nadzieję, że to zadziała
        int[][] arr = new int[dimension][];
        for (int i = 0; i < dimension; ++i) {
            arr[i] = puzzleArray[i].clone();
        }
        return new NPuzzle(dimension, arr, xCoords.clone(), yCoords.clone());
    }

    public Coords getCoords(int tile) {
        return new Coords(xCoords[tile], yCoords[tile]);
    }

    public void setCoords(int tile, Coords coords) {
        xCoords[tile] = coords.getX();
        yCoords[tile] = coords.getY();
        puzzleArray[coords.getX()][coords.getY()] = tile;
    }

    public int getTile(Coords coords) {
        return puzzleArray[coords.getX()][coords.getY()];
    }

    public void setTile(Coords coords, int tile) {
        setCoords(tile, coords);
    }

    public int getDimension() {
        return dimension;
    }

    /**
     * Metoda przesuwa zero na miejsce wskazane w koordynatach, o ile to
     * możliwe.
     * 
     * @param coords
     *            nowa pozycja zera
     */
    public void move(Coords coords) {
        Coords zero_coords = getCoords(0);
        Coords new_zero_coords = zero_coords.add(coords);

        if (new_zero_coords.getX() >= 0 && new_zero_coords.getX() < dimension
                && new_zero_coords.getY() >= 0
                && new_zero_coords.getY() < dimension) {
            swapTiles(zero_coords, new_zero_coords);
        }
    }

    /**
     * Metoda zamienia miejscami dwa pola.
     * 
     * @param c1
     *            pozycja pierwszego pola
     * @param c2
     *            pozyzja drugiego pola
     */
    private void swapTiles(Coords c1, Coords c2) {
        int tile1 = getTile(c1);
        int tile2 = getTile(c2);
        setTile(c1, tile2);
        setTile(c2, tile1);
    }

    /**
     * Metoda liczy ilość pól nie na swoim miejscu; ilość różnic.
     * 
     * @param other
     *            drugi stan
     * @return ilość różnic
     */
    public int countMisplacedTiles(NPuzzle other) {
        int differences = 0;

        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; ++j) {
                if (puzzleArray[i][j] != other.puzzleArray[i][j]) {
                    ++differences;
                }
            }
        }

        return differences;
    }

    /**
     * Funkcja tworzy stan końcowy dla podanego wymiaru układanki.
     * 
     * @param dimension
     *            wymiar układanki
     * @return stan końcowy
     */
    public static NPuzzle createGoalState(int dimension) {
        int[][] arr = new int[dimension][dimension];

        int k = 1;
        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; ++j) {
                arr[i][j] = k++;
            }
        }
        arr[dimension - 1][dimension - 1] = 0;

        return new NPuzzle(dimension, arr);
    }

    /**
     * Tworzy randomową układankę (poprawną)
     * 
     * @param dimension
     *            wymiar ukłądanki
     * @return losowa układanka
     */
    public static NPuzzle createRandomPuzzle(int dimension) {
        int[][] arr = new int[dimension][dimension];

        LinkedList<Integer> tiles = new LinkedList<Integer>();
        for (int i = 0; i < dimension * dimension; ++i) {
            tiles.add(i);
        }
        Collections.shuffle(tiles);

        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; ++j) {
                arr[i][j] = tiles.pop();
            }
        }

        return new NPuzzle(dimension, arr);
    }

    /**
     * Metoda tworzy układankę, którą da się rozwiązać - tworzy losową listę
     * kroków i je aplikuje do stanu końcowego.
     * 
     * @param dimension
     *            wymiar układanki
     * @param changes
     *            ilość zmian (stopień skomplikowania układanki)
     * @return losowa rozwiązywalna układanka
     */
    public static NPuzzle createRandomSolvablePuzzle(int dimension, int changes) {
        Random rand = new Random();
        List<Integer> moves = new LinkedList<Integer>();

        for (int i = 0; i < changes; ++i) {
            moves.add(rand.nextInt(4) + 1);
        }

        return Decoder.applyGenes(createGoalState(dimension), moves);
    }

    /**
     * Pomocnicza funkcja wypisująca układankę na wyjście standardowe
     */
    public void print() {
        System.out.format("Układanka, wymiar %d:\n", dimension);
        for (int i = 0; i < dimension; ++i) {
            System.out.print("\t");
            for (int j = 0; j < dimension; ++j) {
                System.out.format("%d ", puzzleArray[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Metoda oblicza odległość pomiędzy dwoma stanami za pomocą metryki
     * taksówkowej.
     * 
     * @param nPuzzle
     *            TODO
     * @return odległość
     */
    public int calculateManhattanDistance(NPuzzle nPuzzle) {
        int distance = 0;

        for (int tile = 0; tile < nPuzzle.dimension * nPuzzle.dimension; ++tile) {
            distance += nPuzzle.getCoords(tile).sub(getCoords(tile))
                    .calculateTaxcabDistance();
        }

        return distance;
    }

    private int dimension = 0;
    private int[][] puzzleArray = null;
    private int[] xCoords = null;
    private int[] yCoords = null;
}
