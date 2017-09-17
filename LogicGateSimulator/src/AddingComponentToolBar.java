/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alexander
 */
public class AddingComponentToolBar extends javax.swing.JFrame {

    String[] comboBoxGate = new String[] { "AND", "OR", "XOR", "NAND", "NOR", "XNOR" };
    String[] comboBoxInput = new String[] {"Basic Switch"};

    public AddingComponentToolBar() {
        initComponents();
        setTitle("Toolbar");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addGateToolbarButton = new javax.swing.JButton();
        gateComboBox = new javax.swing.JComboBox();
        addInputToolbarButton = new javax.swing.JButton();
        inputComboBox = new javax.swing.JComboBox();
        addOutputToolbarButton = new javax.swing.JButton();
        toggleMoveToolbar = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        addGateToolbarButton.setText("Add Gate");
        addGateToolbarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGateToolbarButtonActionPerformed(evt);
            }
        });

        gateComboBox.setModel(new javax.swing.DefaultComboBoxModel(comboBoxGate));

        addInputToolbarButton.setText("Add Input");
        addInputToolbarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addInputToolbarButtonActionPerformed(evt);
            }
        });

        inputComboBox.setModel(new javax.swing.DefaultComboBoxModel(comboBoxInput));

        addOutputToolbarButton.setText("Add Output");
        addOutputToolbarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOutputToolbarButtonActionPerformed(evt);
            }
        });

        toggleMoveToolbar.setText("Toggle Move Mode");
        toggleMoveToolbar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleMoveToolbarActionPerformed(evt);
            }
        });

        jToggleButton2.setText("Toggle Remove Mode");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jToggleButton3.setText("Clear Screen");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToggleButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addOutputToolbarButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(toggleMoveToolbar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addGateToolbarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addInputToolbarButton, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gateComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(gateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(addGateToolbarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inputComboBox)
                    .addComponent(addInputToolbarButton, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addOutputToolbarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(toggleMoveToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addGateToolbarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGateToolbarButtonActionPerformed
        
        
        
        LogicGateGUI.addingLogicGate = true;
        LogicGateGUI.gateType = comboBoxGate[gateComboBox.getSelectedIndex()];
        
        
    }//GEN-LAST:event_addGateToolbarButtonActionPerformed

    private void addInputToolbarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addInputToolbarButtonActionPerformed
        
        LogicGateGUI.addingSwitch = true;
        
    }//GEN-LAST:event_addInputToolbarButtonActionPerformed

    private void addOutputToolbarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOutputToolbarButtonActionPerformed
        
        LogicGateGUI.addingOutput = true;
        
    }//GEN-LAST:event_addOutputToolbarButtonActionPerformed

    private void toggleMoveToolbarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleMoveToolbarActionPerformed
        
        if(LogicGateGUI.moveOption == false){
            LogicGateGUI.moveModeOption.setSelected(true);
            LogicGateGUI.moveOption = true;
        }
        else{
            LogicGateGUI.moveModeOption.setSelected(false);
            LogicGateGUI.moveOption = false;
        }
        
    }//GEN-LAST:event_toggleMoveToolbarActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        
        if(LogicGateGUI.removingComponent == false){
            LogicGateGUI.removeModeOption.setSelected(true);
            LogicGateGUI.removingComponent = true;
        }
        else{
            LogicGateGUI.removeModeOption.setSelected(false);
            LogicGateGUI.removingComponent = false;
        }
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddingComponentToolBar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddingComponentToolBar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddingComponentToolBar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddingComponentToolBar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddingComponentToolBar().setVisible(true);
                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addGateToolbarButton;
    private javax.swing.JButton addInputToolbarButton;
    private javax.swing.JButton addOutputToolbarButton;
    private javax.swing.JComboBox gateComboBox;
    private javax.swing.JComboBox inputComboBox;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton toggleMoveToolbar;
    // End of variables declaration//GEN-END:variables
}
