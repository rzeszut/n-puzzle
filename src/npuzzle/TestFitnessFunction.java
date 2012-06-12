/**
 * 
 */
package npuzzle;

import geneticalgorithm.FitnessFunction;

import java.util.List;


/**
 * @author mateusz
 */
public class TestFitnessFunction implements FitnessFunction {
    public TestFitnessFunction(NPuzzle beginning_state) {
        this.beginning_state = beginning_state;
        this.goal_state = NPuzzle.createGoalState(beginning_state
                .getDimension());
    }

    /*
     * (non-Javadoc)
     * @see geneticalgorithm.FitnessFunction#calculateFitness(java.util.List)
     */
    @Override
    public double calculateFitness(List<Integer> genes) {
        NPuzzle temp = Decoder.applyGenes(beginning_state, genes);
        int dimension = beginning_state.getDimension();
        return 18 / (double) temp.countMisplacedTiles(goal_state) + 6 * dimension
                / (double) temp.calculateManhattanDistance(goal_state);
    }

    /*
     * (non-Javadoc)
     * @see geneticalgorithm.FitnessFunction#isIdealSolution(double)
     */
    @Override
    public boolean isIdealSolution(double fitness) {
        return Double.isInfinite(fitness);
    }

    private NPuzzle beginning_state;
    private NPuzzle goal_state;
}
