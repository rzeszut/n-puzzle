/**
 * 
 */
package npuzzle;

import java.util.List;


/**
 * NieWiemJakNazwaćTąKlasęIPiszęKażdeSłowoZDużejLiteryAleFajnieZabawaNaCałyWieczór
 * 
 * @author mateusz
 */
public class Decoder {
    private static Coords decodeGene(int gene) {
        switch (gene) {
        case 1: // lewo
            return new Coords(0, -1);

        case 2: // prawo
            return new Coords(0, 1);

        case 3: // góra
            return new Coords(-1, 0);

        case 4: // dół
            return new Coords(1, 0);

        default: // nop
            return new Coords(0, 0);
        }
    }

    /**
     * Zamienia wartości liczbowe genu na słowa: prawo, lewo, góra, dół, nop
     * 
     * @param gene
     *            gen
     * @return string ze słowem, np "prawo"
     */
    public static String geneToString(int gene) {
        switch (gene) {
        case 1: // lewo
            return "lewo";

        case 2: // prawo
            return "prawo";

        case 3: // góra
            return "góra";

        case 4: // dół
            return "dół";

        default: // nop
            return "nop";
        }
    }

    /**
     * Funkcja wykonuje na układance zapisane w genach ruchy, zwraca nowy
     * obiekt, układankę po przejściach.
     * 
     * @param state
     *            układanka
     * @param genes
     *            geny
     * @return nowa układanka
     */
    public static NPuzzle applyGenes(NPuzzle state, List<Integer> genes) {
        NPuzzle new_state = state.clone();

        for (Integer g : genes) {
            new_state.move(decodeGene(g.intValue()));
        }

        return new_state;
    }

    /**
     * Metoda wykonuje ruchy, podobnie jak poprzednia, ale przy nielegalnym
     * ruchu wyrzuca wyjątek
     * 
     * @param state
     *            układanka
     * @param genes
     *            geny
     * @return nowa układanka
     */
    public static NPuzzle applyGenesAndCheckIfIllegal(NPuzzle state,
            List<Integer> genes) throws IllegalMoveException {
        NPuzzle new_state = state.clone();
        int dim = new_state.getDimension();

        for (Integer g : genes) {
            Coords move = decodeGene(g);
            Coords temp = move.add(new_state.getCoords(0));
            if (temp.getX() >= 0 && temp.getX() < dim && temp.getY() >= 0
                    && temp.getY() < dim) {
                new_state.move(move);
            } else {
                throw new IllegalMoveException();
            }
        }

        return new_state;
    }
}
