/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package npuzzle.gui;

import geneticalgorithm.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author M-Art
 */
public class GUIDispFunction implements Observer {

    private GUIChart chart_panel;

    public GUIDispFunction(GUIChart cp) {
        chart_panel = cp;
    }

    @Override
    public void call(List<Chromosome> population, int iteration) {

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

        double max_fit = Collections.max(population, comp).getFitness();
        double min_fit = Collections.min(population, comp).getFitness();

        double avg_fit = 0;
        for (Chromosome c : population) {
            avg_fit += c.getFitness();
        }
        avg_fit = avg_fit / (double) population.size();

        chart_panel.updateChartPanel(min_fit, avg_fit, max_fit);

        //System.out.format("%d. min-fitness: %f; avg-fitness: %f; max-fitness: %f\n", iteration, min_fit, avg_fit, max_fit);
    }
}
