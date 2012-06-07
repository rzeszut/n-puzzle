package geneticalgorithm;

import java.util.List;


/**
 * Interfejs funkcji fitness
 * 
 * @author mateusz
 */
public interface FitnessFunction {
    /**
     * Metoda licząca fitness dla danych genów.
     * 
     * @param genes
     *            geny
     * @return współczynnik fitness
     */
    public double calculateFitness(List<Integer> genes);

    /**
     * Metoda określająca, czy dany osobnik jest rozwiązaniem idealnym algorytmu
     * 
     * @param fitness
     *            fitness
     * @return true->osobnik jest rozwiązaniem idealnum, false->nie
     */
    public boolean isIdealSolution(double fitness);
}
