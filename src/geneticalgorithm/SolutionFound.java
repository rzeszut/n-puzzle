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
class SolutionFound extends Exception {
    public SolutionFound(Chromosome solution) {
        this.solution = solution;
    }

    public Chromosome getSolution() {
        return solution;
    }

    private Chromosome solution = null;
}
