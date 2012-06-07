package geneticalgorithm;

/**
 * 
 */

/**
 * Klasa zawierająca ustawienia algorytmu genetycznego; definiuje problem.
 * 
 * @author mateusz
 */
public class Configuration {
    public Configuration(FitnessFunction ffunc, GeneFunctions gfunc) {
        fitFunc = ffunc;
        geneFunc = gfunc;
    }

    public int getChromosomeLength() {
        return chromosomeLength;
    }

    public void setChromosomeLength(int chromosomeLength) {
        this.chromosomeLength = chromosomeLength;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public double getCrossoverRate() {
        return crossoverRate;
    }

    public void setCrossoverRate(double crossoverRate) {
        this.crossoverRate = crossoverRate;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public FitnessFunction getFitFunc() {
        return fitFunc;
    }

    public GeneFunctions getGeneFunc() {
        return geneFunc;
    }

    private int chromosomeLength = 10;
    private int populationSize = 40;
    private int iterations = 40;
    private double crossoverRate = 0.7;
    private double mutationRate = 0.1;

    private FitnessFunction fitFunc = null;
    private GeneFunctions geneFunc = null;
}
