package npuzzle;

import geneticalgorithm.FitnessFunction;

import java.util.List;


public class QWERFitnessFunction implements FitnessFunction {
    public QWERFitnessFunction(NPuzzle beginning_state) {
        this.beggining_state = beginning_state;
        this.goal_state = NPuzzle.createGoalState(beginning_state
                .getDimension());
        this.max_fitness = beginning_state.getDimension()
                * beginning_state.getDimension();
    }

    @Override
    public double calculateFitness(List<Integer> genes) {
        double fitness = 0.0f;

        try {
            NPuzzle temp = Decoder.applyGenesAndCheckIfIllegal(beggining_state,
                    genes);
            fitness = max_fitness - goal_state.countMisplacedTiles(temp);
        } catch (IllegalMoveException e) {
            fitness = 0.000005;
        }

        return fitness;
    }

    @Override
    public boolean isIdealSolution(double fitness) {
        return fitness >= max_fitness;
    }

    private NPuzzle beggining_state;
    private NPuzzle goal_state;
    private double max_fitness;
}
