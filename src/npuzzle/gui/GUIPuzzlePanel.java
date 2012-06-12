/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package npuzzle.gui;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;
import java.awt.Dimension;
import javax.swing.JTextField;
import npuzzle.Coords;
import npuzzle.Decoder;
import npuzzle.NPuzzle;

/**
 *
 * @author M-Art
 */
public class GUIPuzzlePanel extends javax.swing.JPanel {

    private int dimension;
    private int offsetx = 50;
    private int offsety = 5;
    private int sizex = 25;
    private int sizey = 25;
    private int border = 0;
    private NPuzzle begginning_state;
    Chromosome solution;
    Configuration config;
    private NPuzzle puzzle;
    private JTextField[][] tilemap;
    private int actual_it = 0;

    /**
     * Creates new form NewJPanel
     */
    public GUIPuzzlePanel(NPuzzle beg_st, Configuration conf) {
        initComponents();
        doLayout();
        begginning_state = beg_st;
        dimension = beg_st.getDimension();
        config = conf;
        tilemap = new JTextField[dimension][dimension];
        this.setSize(new Dimension(2 * offsety + dimension * (sizey + border), 2 * offsetx + dimension * (sizex + border)));

        int tile;
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                tilemap[x][y] = new JTextField();
                tilemap[x][y].setBounds(offsety + y * (sizey + border), offsetx + x * (sizex + border), sizey, sizex);
                tilemap[x][y].setEditable(false);
                tilemap[x][y].setVisible(true);
                this.add(tilemap[x][y]);
            }
        }
        //setSolutionState();
    }

    private void update() {
        int tile;
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                tile = puzzle.getTile(new Coords(x, y));
                if (!tilemap[x][y].getText().equals("" + tile)) {
                    tilemap[x][y].setText("" + (tile == 0 ? "" : tile));
                }
            }
        }
    }

    private void setBegginningState() {
        puzzle = begginning_state.clone();
        update();
        actual_it = 0;
        ResetButton.setEnabled(false);
        NextButton.setEnabled(true);
        SolveButton.setEnabled(true);
    }

    private void setSolutionState() {
        if (solution == null) {
            return;
        }
        this.puzzle = Decoder.applyGenes(begginning_state, solution.getGenes());
        update();
        actual_it = config.getChromosomeLength();
        ResetButton.setEnabled(true);
        NextButton.setEnabled(false);
        SolveButton.setEnabled(false);
    }

    private void nextIteration() {
        if (solution == null) {
            return;
        }
        if (!NextButton.isEnabled()) {
            return;
        }
        ResetButton.setEnabled(true);
        int gene = solution.getGenes().get(actual_it);
        puzzle.move(Decoder.decodeGene(gene));
        update();
        ++actual_it;
        if (actual_it > config.getChromosomeLength() - 1) {
            setSolutionState();
        }

    }

    public void disablePanel() {
        ResetButton.setEnabled(false);
        NextButton.setEnabled(false);
        SolveButton.setEnabled(false);
    }

    public void activePanel(Chromosome solution) {
        this.begginning_state = begginning_state;
        this.solution = solution;
        this.setSolutionState();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ResetButton = new javax.swing.JButton();
        NextButton = new javax.swing.JButton();
        SolveButton = new javax.swing.JButton();

        ResetButton.setLabel("Resetuj");
        ResetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResetButtonMouseClicked(evt);
            }
        });

        NextButton.setEnabled(false);
        NextButton.setLabel("Dalej");
        NextButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NextButtonMouseClicked(evt);
            }
        });

        SolveButton.setLabel("Rozwiazanie");
        SolveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SolveButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ResetButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NextButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SolveButton)
                .addContainerGap(161, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ResetButton)
                    .addComponent(NextButton)
                    .addComponent(SolveButton))
                .addContainerGap(266, Short.MAX_VALUE))
        );

        NextButton.getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents

    private void ResetButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResetButtonMouseClicked
        // TODO add your handling code here:
        setBegginningState();
    }//GEN-LAST:event_ResetButtonMouseClicked

    private void NextButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NextButtonMouseClicked
        // TODO add your handling code here:
        nextIteration();
    }//GEN-LAST:event_NextButtonMouseClicked

    private void SolveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SolveButtonMouseClicked
        // TODO add your handling code here:
        setSolutionState();
    }//GEN-LAST:event_SolveButtonMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton NextButton;
    private javax.swing.JButton ResetButton;
    private javax.swing.JButton SolveButton;
    // End of variables declaration//GEN-END:variables
}
