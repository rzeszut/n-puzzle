/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package npuzzle.gui;

import java.util.LinkedList;

/**
 *
 * @author M-Art
 */
public class GUIDataset {
    LinkedList<Double> min_fit;
    LinkedList<Double> max_fit;
    LinkedList<Double> avg_fit;
    
    public GUIDataset() {
        min_fit = new LinkedList<Double>();
        max_fit = new LinkedList<Double>();
        avg_fit = new LinkedList<Double>();
    }
    
    public void add(double min, double max, double avg) {
        min_fit.add(min);
        max_fit.add(max);
        avg_fit.add(avg);
    }
    
    public void addMin(double min) {
        min_fit.add(min);
    }
    
    public void addMax(double max) {
        min_fit.add(max);
    }
    
    public void addAvg(double avg) {
        min_fit.add(avg);
    }
    
    public LinkedList<Double> getMinFit() {
        return min_fit;
    }
    
    public LinkedList<Double> getMaxFit() {
        return max_fit;
    }
    
    public LinkedList<Double> getAvgFit() {
        return avg_fit;
    }
}
