/**
 * 
 */
package npuzzle;

import geneticalgorithm.FitnessFunction;

import java.util.List;


/**
 * @author mateusz
 */
public class SimpleFitnessFunction implements FitnessFunction {
    public SimpleFitnessFunction(NPuzzle begginning_state) {
        this.beginning_state = begginning_state;
        this.goal_state = NPuzzle.createGoalState(begginning_state
                .getDimension());
    }

    /*
     * (non-Javadoc)
     * @see geneticalgorithm.FitnessFunction#calculateFitness(java.util.List)
     */
    @Override
    public double calculateFitness(List<Integer> genes) {
        int distance = Decoder.applyGenes(
                beginning_state, genes).calculateManhattanDistance(goal_state);
        return 1 / (double) distance;
    }

    @Override
    public boolean isIdealSolution(double fitness) {
        return Double.isInfinite(fitness);
    }

    private NPuzzle beginning_state = null;
    private NPuzzle goal_state = null;
}
