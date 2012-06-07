package geneticalgorithm;

/**
 * 
 */

/**
 * Funkcje pomocnicze, potrzebne do operacji na genach.
 * 
 * @author mateusz
 */
public interface GeneFunctions {
    /**
     * Metoda zwraca randomowy gen
     * 
     * @return randomowy gen
     */
    public int randomGene();

    /**
     * Metoda mutuje gen i zwraca go
     * 
     * @param gene
     *            gen
     * @return zmutowany gen
     */
    public int mutateGene(int gene);
}
