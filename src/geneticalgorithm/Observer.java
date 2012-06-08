/**
 * 
 */
package geneticalgorithm;


import java.util.List;



/**
 * @author mateusz
 */
public interface Observer {
    /**
     * Metoda robi "coś" z aktualną populacją, np. rysuje fragment wykresu.
     * 
     * @param population
     *            populacja
     * @param iteration
     *            numer aktualnej iteracji
     */
    public void call(List<Chromosome> population, int iteration);
}
