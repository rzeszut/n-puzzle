package geneticalgorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/**
 * 
 */

/**
 * Klasa algorytmu.
 * 
 * @author mateusz
 */
public class GARouletteWheel extends GeneticAlgorithm {
    /**
     * Przeprowadza pojedynczą iterację algorytmu genetycznego, dostaje starą
     * populację, oddaje nową. Wyrzuca wyjątek, jeśli znaleziono idealne
     * rozwiązanie. Etapy: 1. wylicza fitness dla każdego chromosomu 2. wylicza
     * prawdopodobieństwo dla każdego chromosomu 3. losuje chromosomy i krzyżuje
     * je 4. mutuje chromosomy w nowej populacji 5. return
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

        // krzyżowanie
        LinkedList<Chromosome> new_population = new LinkedList<Chromosome>();
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

    /**
     * Tworzy losową populację wg. wskazań zawartych w setupie.
     * 
     * @param setup
     *            setup
     * @return losowa populacja
     */
    List<Chromosome> createStartPopulation(Configuration setup) {
        LinkedList<Chromosome> population = new LinkedList<Chromosome>();

        for (int i = 0; i < setup.getPopulationSize(); i++) {
            List<Integer> genes = new LinkedList<Integer>();
            for (int j = 0; j < setup.getChromosomeLength(); ++j) {
                genes.add(setup.getGeneFunc().randomGene());
            }

            population.add(new Chromosome(setup, genes));
        }

        return population;
    }

    /**
     * Metoda "roulette wheel". Dostaje populację i liczbę z zakresu (0,1),
     * wybiera chromosom na tej podstawie.
     * 
     * @param population
     *            Populacja
     * @param random_number
     *            liczba z zakresu (0,1)
     * @return chromosom wybrany metodą 'roulette wheel'
     */
    Chromosome chooseChromosome(List<Chromosome> population,
            double random_number) {
        Chromosome chosen = null;
        for (Chromosome c : population) {
            if (random_number <= c.getProbability()) {
                chosen = c;
                break;
            }
        }
        if (chosen == null) {
            chosen = population.get(population.size() - 1);
        }
        return chosen;
    }

}
