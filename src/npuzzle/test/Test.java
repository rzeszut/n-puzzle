/**
 * 
 */
package npuzzle.test;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;
import geneticalgorithm.DispFunction;
import geneticalgorithm.GARouletteElitism;
import geneticalgorithm.GeneFunctions;
import geneticalgorithm.GeneticAlgorithm;
import geneticalgorithm.SolutionFound;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import npuzzle.Decoder;
import npuzzle.NPuzzle;
import npuzzle.TestFitnessFunction;


/**
 * @author mateusz
 */
public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        GeneFunctions gfunc = new GeneFunctions() {
            private Random rand = new Random();

            @Override
            public int randomGene() {
                return rand.nextInt(5);
                // return rand.nextInt(4) + 1;
            }

            @Override
            public int mutateGene(int gene) {
                gene = (rand.nextBoolean() ? ++gene : --gene);

                if (gene < 0) {
                    gene = 4;
                } else if (gene > 4) {
                    gene = 0;
                }

                return gene;
            }

        };

        NPuzzle begginning_state = NPuzzle.createRandomSolvablePuzzle(7, 40);
        Configuration conf = new Configuration(new TestFitnessFunction(
                begginning_state), gfunc);
        conf.setChromosomeLength(60);
        conf.setIterations(50000);
        conf.setPopulationSize(200);
        conf.setCrossoverRate(0.2);
        conf.setMutationRate(0.6);

        begginning_state.print();
        List<Chromosome> population = null;

        try {
            GeneticAlgorithm alg = new GARouletteElitism();
            population = alg.solveProblem(conf, new DispFunction() {

                @Override
                public void call(List<Chromosome> population, int iteration) {
                    if (iteration % 100 != 0) {
                        return;
                    }

                    Comparator<Chromosome> comp = new Comparator<Chromosome>() {

                        @Override
                        public int compare(Chromosome o1, Chromosome o2) {
                            if (o1.getFitness() < o2.getFitness()) {
                                return -1;
                            } else if (o1.getFitness() == o2.getFitness()) {
                                return 0;
                            } else {
                                return 1;
                            }
                        }
                    };

                    double max_fit = Collections.max(population, comp)
                            .getFitness();
                    double min_fit = Collections.min(population, comp)
                            .getFitness();

                    double avg_fit = 0;
                    for (Chromosome c : population) {
                        avg_fit += c.getFitness();
                    }
                    avg_fit = avg_fit / (double) population.size();

                    System.out
                            .format("%d. min-fitness: %f; avg-fitness: %f; max-fitness: %f\n",
                                    iteration, min_fit, avg_fit, max_fit);
                }
            });
        } catch (SolutionFound e) {
            System.out.format(
                    "Znaleziono idealne rozwiązanie w %d iteracji.\n",
                    e.getIterations());
            System.out.println("Rozwiązanie:");
            for (Integer g : e.getSolution().getGenes()) {
                System.out.format("%s ", Decoder.geneToString(g));
            }
            System.out.println();
            System.exit(0);
        }

        Comparator<Chromosome> comp = new Comparator<Chromosome>() {

            @Override
            public int compare(Chromosome o1, Chromosome o2) {
                if (o1.getFitness() < o2.getFitness()) {
                    return -1;
                } else if (o1.getFitness() == o2.getFitness()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        };

        Chromosome best_fit = Collections.max(population, comp);
        System.out.println("Najlepsze znalezione rozwiązanie:");
        for (Integer g : best_fit.getGenes()) {
            System.out.format("%s ", Decoder.geneToString(g));
        }
        System.out.println();
        Decoder.applyGenes(begginning_state, best_fit.getGenes()).print();
    }
}
