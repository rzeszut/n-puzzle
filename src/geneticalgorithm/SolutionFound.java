/**
 * 
 */
package geneticalgorithm;

/**
 * Wyjątek wyrzucany w momencie znalezienia idealnego rozwiązania
 * 
 * @author mateusz
 */
@SuppressWarnings("serial")
public class SolutionFound extends Exception {
    public SolutionFound(Chromosome solution) {
        this.solution = solution;
    }

    public SolutionFound(Chromosome solution, int iterations) {
        this.solution = solution;
        this.iterations = iterations;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public Chromosome getSolution() {
        return solution;
    }

    private Chromosome solution = null;
    private int iterations = 0;
}
