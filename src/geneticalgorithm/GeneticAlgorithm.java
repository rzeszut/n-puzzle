package geneticalgorithm;

import java.util.List;


/**
 * 
 */

/**
 * Klasa abstrakcyjna genetycznego algorytmu
 * 
 * @author mateusz
 */
public abstract class GeneticAlgorithm {
    /**
     * Przeprowadza pojedynczą iterację pewnego konkretnego algorytmu
     * genetycznego
     * 
     * @param population
     *            stara populacja
     * @return nowa populacja
     * @throws SolutionFound
     *             znaleziono rozwiazanie idealne
     */
    abstract List<Chromosome> iterate(List<Chromosome> population)
            throws SolutionFound;

    /**
     * Tworzy konkretną startową populację (np. losuje) dla konkretnego
     * algorytmu genetycznego.
     * 
     * @param setup
     *            Ustawienia algorytmu
     * @return startowa populacja
     */
    abstract List<Chromosome> createStartPopulation(Configuration setup);

    /**
     * Metoda rozwiązuje problem opisany w setupie, korzystając z powyższych.
     * Jeśli iterate wyrzuciło wyjątek, przechwytuje, dodaje ilość iteracji i
     * rzuca jeszcze raz. Można podać {@link DispFunction} jako null, wtedy
     * metoda po prostu nic nie robi co iterację. {@link DispFunction} powinna
     * dostać niemodyfikowalny kontener.
     * 
     * @param setup
     *            setup
     * @param func
     *            funkcja, która będzie wywoływana przy każdej iteracji - może
     *            np. wypisywać coś na ekran.
     * @return ostanie pokolenie, po wszystkich iteracjach
     */
    public List<Chromosome> solveProblem(Configuration setup, DispFunction func)
            throws SolutionFound {
        List<Chromosome> population = createStartPopulation(setup);

        for (int i = 0; i < setup.getIterations(); ++i) {
            // wywoluje func na populacjach 0 do iterations-1
            if (func != null) {
                func.call(population, i);
            }
            try {
                population = iterate(population);
            } catch (SolutionFound exc) {
                exc.setIterations(i);
                throw exc;
            }
        }
        // wywoluje func na ostatniej populacji
        if (func != null) {
            func.call(population, setup.getIterations());
        }

        return population;
    }
}
