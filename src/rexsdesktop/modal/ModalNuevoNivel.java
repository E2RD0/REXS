/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.modal;

import java.awt.Window;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import rexsdesktop.controller.Sections;
import rexsdesktop.view.Admin;

/**
 * Clase que contiene el Panel para agregar un nuevo nivel.
 * @author artur
 */
public class ModalNuevoNivel extends javax.swing.JPanel {

    /**
     * Creates new form ModalNuevoNivel1
     */
    public ModalNuevoNivel() {
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

        jLabel67 = new javax.swing.JLabel();
        txtNivelModal = new javax.swing.JTextField();
        btnAceptarModal = new javax.swing.JButton();
        btnCancelarModal = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        jLabel67.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(176, 186, 201));
        jLabel67.setText("Nivel");

        txtNivelModal.setBackground(new java.awt.Color(249, 250, 255));
        txtNivelModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtNivelModal.setForeground(new java.awt.Color(46, 56, 77));
        txtNivelModal.setToolTipText("Nombre del nuevo nivel");
        txtNivelModal.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtNivelModal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNivelModalKeyTyped(evt);
            }
        });

        btnAceptarModal.setBackground(new java.awt.Color(213, 222, 255));
        btnAceptarModal.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        btnAceptarModal.setForeground(new java.awt.Color(46, 91, 255));
        btnAceptarModal.setText("Aceptar");
        btnAceptarModal.setToolTipText("Ingresar nuevo nivel");
        btnAceptarModal.setBorderPainted(false);
        btnAceptarModal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarModalActionPerformed(evt);
            }
        });

        btnCancelarModal.setBackground(new java.awt.Color(247, 214, 218));
        btnCancelarModal.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        btnCancelarModal.setForeground(new java.awt.Color(214, 54, 73));
        btnCancelarModal.setText("Cancelar");
        btnCancelarModal.setBorderPainted(false);
        btnCancelarModal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarModalActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(164, 164, 164));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAceptarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCancelarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 16, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel67)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtNivelModal))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel67)
                .addGap(1, 1, 1)
                .addComponent(txtNivelModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarModalActionPerformed
        Sections Conexion = new Sections();
        //JOptionPane.showMessageDialog(null, String.valueOf(jComboBox1.getSelectedIndex() + 1));

        try {
            if (txtNivelModal.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Existen campos vacios");}else{
            Conexion.setNivel(txtNivelModal.getText());

            if (Conexion.agregarNivel()) {
                Admin.jPanel17.removeAll();
                Sections CargarNivel = new Sections();
                CargarNivel.CrearPanelesNivel(Admin.jPanel17);
                JOptionPane.showMessageDialog(null, "Datos ingresados correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al insertar datos");
            }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR GLOBAL");
        }
    }//GEN-LAST:event_btnAceptarModalActionPerformed

    private void txtNivelModalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNivelModalKeyTyped
        // TODO add your handling code here:
        char validar=evt.getKeyChar();
        
        if(Character.isDigit(validar)){
            getToolkit().beep();
            
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar solo Letras");
        }
    }//GEN-LAST:event_txtNivelModalKeyTyped

    private void btnCancelarModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarModalActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnCancelarModalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptarModal;
    private javax.swing.JButton btnCancelarModal;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtNivelModal;
    // End of variables declaration//GEN-END:variables
}
