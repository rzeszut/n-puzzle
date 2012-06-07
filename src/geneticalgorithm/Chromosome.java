package geneticalgorithm;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/**
 * 
 */

/**
 * Klasa chromosomu.
 * 
 * @author mateusz
 */
public class Chromosome {
    public Chromosome(Configuration setup, List<Integer> genes) {
        this.setup = setup;
        this.genes = genes;
    }

    /**
     * Oblicza, jeśli trzeba, fitness score chromosomu, i zwraca go.
     * 
     * @return fitness score
     */
    public double getFitness() {
        if (fitness == 0.f) {
            fitness = setup.getFitFunc().calculateFitness(genes);
        }
        return fitness;
    }

    /**
     * Sprawdza, czy osobnik jest rozwiązaniem idealnym
     * 
     * @return true/false
     */
    public boolean isIdealSolution() {
        return setup.getFitFunc().isIdealSolution(getFitness());
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public List<Integer> getGenes() {
        return genes;
    }

    /**
     * Metoda dostaje inny chromosom, losuje, czy nastąpiło krzyżowanie
     * (prawdopodobieństwo jest w setupie) i krzyżuje w wylosowanym punkcie.
     * Zwraca (w formie listy czy czegoś tam, jakaś kolekcja) albo dwa nowe
     * chromosomy, albo listę [this, other].
     * 
     * @param other
     *            drugi chromosom
     * @return dwa nowe (bądź stare) chromosomy
     */
    public List<Chromosome> crossover(Chromosome other) {
        Random rand = new Random();
        LinkedList<Chromosome> pair = new LinkedList<Chromosome>();
        if (rand.nextDouble() < setup.getCrossoverRate()) {

            LinkedList<Integer> first = new LinkedList<Integer>();
            LinkedList<Integer> second = new LinkedList<Integer>();

            Iterator<Integer> it_this = this.genes.iterator();
            Iterator<Integer> it_other = other.genes.iterator();

            int crosspoint = rand.nextInt(genes.size());

            for (int i = 0; i < crosspoint; i++) {
                first.add(it_this.next());
                second.add(it_other.next());
            }

            for (int i = crosspoint; i < genes.size(); i++) {
                first.add(it_other.next());
                second.add(it_this.next());
            }

            pair.add(new Chromosome(this.setup, first));
            pair.add(new Chromosome(this.setup, second));
        } else {
            // po prostu this i other, nie ma potrzeba tworzenia nowych
            pair.add(this);
            pair.add(other);
        }
        return pair;
    }

    /**
     * Metoda mutuje chromosom
     */
    public void mutate() {
        Random rand = new Random();
        // LinkedList<Integer> list = (LinkedList<Integer>) genes;
        // for (int i = 0; i < genes.size(); i++) {
        // if (rand.nextDouble() < setup.getMutationRate()) {
        // list.set(
        // i,
        // new Integer(setup.getGeneFunc().mutateGene(list.get(i))));
        // }
        // }
        LinkedList<Integer> mutated_genes = new LinkedList<Integer>();
        for (Integer g : genes) {
            if (rand.nextDouble() < setup.getMutationRate()) {
                mutated_genes
                        .add(new Integer(setup.getGeneFunc().mutateGene(g)));
            } else {
                mutated_genes.add(g);
            }
        }
        genes = mutated_genes;
        // setup.getGeneFunc().mutateGenes(genes, setup.getMutationRate());

    }

    private double fitness = 0.f;
    private double probability = 0.f;

    private List<Integer> genes;

    private Configuration setup = null;
}
