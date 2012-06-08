/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package npuzzle.gui;

import geneticalgorithm.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import npuzzle.Decoder;
import npuzzle.NPuzzle;
import npuzzle.TestFitnessFunction;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author M-Art
 */
public class GUINPuzzle extends javax.swing.JFrame {

    /**
     * Creates new form NPuzzleGUI
     */
    public GUINPuzzle() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        GUIPuzzleSize = new javax.swing.JSpinner();
        GUIChromosomeLenght = new javax.swing.JSpinner();
        GUIIterations = new javax.swing.JSpinner();
        GUIPopulationSize = new javax.swing.JSpinner();
        GUICrossoverRate = new javax.swing.JSpinner();
        GUIMutationRate = new javax.swing.JSpinner();
        GUIStart = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NPuzzleGUI");

        jLabel1.setText("Rozmiar puzzli:");

        jLabel2.setText("Długość chromosomu:");

        jLabel3.setText("Wielkość populacji:");

        jLabel4.setText("Ilość iteracji:");

        jLabel5.setText("Szansa krzyżowania:");

        jLabel6.setText("Szansa mutacji:");

        GUIPuzzleSize.setRequestFocusEnabled(false);

        GUIStart.setText("Rozpocznij ruchanie");
        GUIStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GUIStartMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 181, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(GUIChromosomeLenght, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                            .addComponent(GUIIterations)
                            .addComponent(GUIPopulationSize)
                            .addComponent(GUICrossoverRate)
                            .addComponent(GUIMutationRate)
                            .addComponent(GUIPuzzleSize)))
                    .addComponent(GUIStart))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(GUIPuzzleSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(GUIChromosomeLenght, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(GUIIterations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(GUIPopulationSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(GUICrossoverRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(GUIMutationRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GUIStart)
                        .addGap(0, 99, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        GUIPuzzleSize.setModel(new javax.swing.SpinnerNumberModel(4, 3, 10, 1));
        GUIChromosomeLenght.setModel(new javax.swing.SpinnerNumberModel(20, 8, 30, 1));
        GUIIterations.setModel(new javax.swing.SpinnerNumberModel(50, 10, 100000, 1));
        GUIPopulationSize.setModel(new javax.swing.SpinnerNumberModel(50, 8, 100, 1));
        GUICrossoverRate.setModel(new javax.swing.SpinnerNumberModel(0.5, 0, 1, 0.1));
        GUIMutationRate.setModel(new javax.swing.SpinnerNumberModel(0.1, 0, 1, 0.1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GUIStartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GUIStartMouseClicked
        // TODO add your handling code here:      
        GeneFunctions gfunc = new GeneFunctions() {

            private Random rand = new Random();

            @Override
            public int randomGene() {
                return rand.nextInt(5);
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

        int[][] arr = {{0, 1, 2}, {4, 6, 3}, {7, 5, 8}};
        int[][] arr1 = {{1, 2, 5}, {7, 8, 3}, {0, 4, 6}};
        int[][] arr2 = {{8, 7, 6}, {5, 4, 3}, {2, 1, 0}};
        NPuzzle begginning_state = new NPuzzle(3, arr);
        //NPuzzle begginning_state = NPuzzle.createRandomPuzzle(3);
        Configuration conf = new Configuration(new TestFitnessFunction(
                begginning_state), gfunc);
        conf.setChromosomeLength(Integer.parseInt(GUIChromosomeLenght.getValue().toString()));
        conf.setIterations(Integer.parseInt(GUIIterations.getValue().toString()));
        conf.setPopulationSize(Integer.parseInt(GUIPopulationSize.getValue().toString()));
        conf.setCrossoverRate(Double.parseDouble(GUICrossoverRate.getValue().toString()));
        conf.setMutationRate(Double.parseDouble(GUIMutationRate.getValue().toString()));
        begginning_state.print();

        GUIDispFunction dfunc = new GUIDispFunction();
        
        List<Chromosome> population = null;
        try {
            GeneticAlgorithm alg = new GARouletteElitism();
            alg.addObserver(dfunc);
            population = alg.solveProblem(conf);
        } catch (SolutionFound e) {
            System.out.format(
                    "Znaleziono idealne rozwiązanie w %d iteracji.\n",
                    e.getIterations());
            System.out.println("Rozwiązanie:");
            for (Integer g : e.getSolution().getGenes()) {
                System.out.format("%s ", Decoder.geneToString(g));
            }
            System.out.println();
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
        new GUIChart(dfunc.getDataset()).setVisible(true);
    }//GEN-LAST:event_GUIStartMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUINPuzzle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUINPuzzle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUINPuzzle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUINPuzzle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new GUINPuzzle().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner GUIChromosomeLenght;
    private javax.swing.JSpinner GUICrossoverRate;
    private javax.swing.JSpinner GUIIterations;
    private javax.swing.JSpinner GUIMutationRate;
    private javax.swing.JSpinner GUIPopulationSize;
    private javax.swing.JSpinner GUIPuzzleSize;
    private javax.swing.JButton GUIStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
