/**
 * 
 */
package geneticalgorithm;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/**
 * Algorytm genetyczny oparty na roulette wheel, ale zawsze kopiuje najlepszego
 * osobnika do następnej populacji.
 * 
 * @author mateusz
 */
public class GARouletteElitism extends GARouletteWheel {
    /**
     * Przeprowadza pojedynczą iterację algorytmu. Od zwykłej metody roulette
     * wheel różni się tym, że kopiuje najlepszego osobnika.
     * 
     * @param population
     *            stara populacja
     * @return nowa populacja
     */
    List<Chromosome> iterate(List<Chromosome> population) throws SolutionFound {
        double sum_of_fitnesses = 0.f;
        double sum_of_probabilities = 0.f;

        // wylicza fitness score
        for (Chromosome c : population) {
            if (c.isIdealSolution()) {
                throw new SolutionFound(c);
            } else {
                sum_of_fitnesses += c.getFitness();
            }
        }

        // wylicza prawdopodobieństwo
        for (Chromosome c : population) {
            c.setProbability(sum_of_probabilities + c.getFitness()
                    / sum_of_fitnesses);
            sum_of_probabilities = c.getProbability();
        }

        LinkedList<Chromosome> new_population = new LinkedList<Chromosome>();

        // dodaje najlepszego osobnika
        new_population.add(Collections.max(population,
                new Comparator<Chromosome>() {

                    @Override
                    public int compare(Chromosome o1, Chromosome o2) {
                        if (o1.getFitness() > o2.getFitness()) {
                            return 1;
                        } else if (o1.getFitness() < o2.getFitness()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }

                }));

        // krzyżowanie
        Random rand = new Random();
        while (new_population.size() < population.size()) {
            Chromosome c1 = chooseChromosome(population, rand.nextDouble());
            Chromosome c2 = chooseChromosome(population, rand.nextDouble());
            new_population.addAll(c1.crossover(c2));
        }

        // mutacja
        for (Chromosome c : new_population) {
            c.mutate();
        }

        return new_population;
    }
}
