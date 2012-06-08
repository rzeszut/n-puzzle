package geneticalgorithm;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
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
     * rzuca jeszcze raz. Można podać {@link Observer} jako null, wtedy
     * metoda po prostu nic nie robi co iterację. {@link Observer} powinna
     * dostać niemodyfikowalny kontener.
     * 
     * @param setup
     *            setup
     * @param func
     *            funkcja, która będzie wywoływana przy każdej iteracji - może
     *            np. wypisywać coś na ekran.
     * @return ostanie pokolenie, po wszystkich iteracjach
     */
    public Chromosome solveProblem(Configuration setup) {
        List<Chromosome> population = createStartPopulation(setup);

        for (int i = 0; i < setup.getIterations(); ++i) {
            // wywoluje func na populacjach 0 do iterations-1
            for (Observer o : observers) {
                o.call(population, i);
            }
            
            try {
                if (terminated == true) {
                    throw new SolutionFound(population.get(0));
                }
                population = iterate(population);
            } catch (SolutionFound exc) {
                return exc.getSolution();
            }
        }
        // wywoluje func na ostatniej populacji
        for (Observer o : observers) {  
            o.call(population, setup.getIterations());
        }

        return Collections.max(population, new Comparator<Chromosome>() {

            @Override
            public int compare(Chromosome t, Chromosome t1) {
                if (t.getFitness() < t1.getFitness()) {
                    return -1;
                } else if (t.getFitness() == t1.getFitness()) {
                    return 0;
                } else {
                    return 1;
                }
            }
            
        });
    }
    
    public void terminate() {
        terminated = true;
    }
    
    /**
     * Dodaje obserwatora, który będzie powiadamiany w trakcie działania algorytmu.
     * 
     * @param o 
     */
    public void addObserver(Observer o) {
        observers.add(o);
    }
    
    private LinkedList<Observer> observers = new LinkedList<Observer>();
    private boolean terminated = false;
}
