/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.modal;

import java.awt.Window;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import rexsdesktop.controller.User;

/**
 * Clase que contiene el Panel para agregar un nuevo estado del Usuario.
 * @author artur
 */
public class ModalNuevoEstadoUsuario extends javax.swing.JPanel {

    /**
     * Creates new form ModalNuevoEstadoUsuario
     */
    public ModalNuevoEstadoUsuario() {
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

        txtEstadoUsuarioModal = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnCancelarModal = new javax.swing.JButton();
        btnAceptarModal = new javax.swing.JButton();

        txtEstadoUsuarioModal.setBackground(new java.awt.Color(249, 250, 255));
        txtEstadoUsuarioModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtEstadoUsuarioModal.setForeground(new java.awt.Color(46, 56, 77));
        txtEstadoUsuarioModal.setToolTipText("Nombre del estado de usuario");
        txtEstadoUsuarioModal.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtEstadoUsuarioModal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEstadoUsuarioModalKeyTyped(evt);
            }
        });

        jLabel67.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(176, 186, 201));
        jLabel67.setText("Estado usuario");

        jSeparator1.setForeground(new java.awt.Color(164, 164, 164));

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

        btnAceptarModal.setBackground(new java.awt.Color(213, 222, 255));
        btnAceptarModal.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        btnAceptarModal.setForeground(new java.awt.Color(46, 91, 255));
        btnAceptarModal.setText("Aceptar");
        btnAceptarModal.setToolTipText("Ingresar nuevo estado de usuario");
        btnAceptarModal.setBorderPainted(false);
        btnAceptarModal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarModalActionPerformed(evt);
            }
        });

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
                        .addGap(0, 8, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel67)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtEstadoUsuarioModal))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel67)
                .addGap(1, 1, 1)
                .addComponent(txtEstadoUsuarioModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        User Conexion = new User();
        //JOptionPane.showMessageDialog(null, String.valueOf(jComboBox1.getSelectedIndex() + 1));

        try {
            if (txtEstadoUsuarioModal.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Existen campos vacios");
            } else {
                Conexion.setEstadoUsuario(txtEstadoUsuarioModal.getText());

                if (Conexion.agregarEstadoUsuario()) {
                    JOptionPane.showMessageDialog(null, "Datos ingresados correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al insertar datos");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR GLOBAL");
        }
    }//GEN-LAST:event_btnAceptarModalActionPerformed

    private void txtEstadoUsuarioModalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstadoUsuarioModalKeyTyped
        // TODO add your handling code here:
        char validar = evt.getKeyChar();

        if (Character.isDigit(validar)) {
            getToolkit().beep();

            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar solo Letras");
        }
    }//GEN-LAST:event_txtEstadoUsuarioModalKeyTyped

    private void btnCancelarModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarModalActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnCancelarModalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptarModal;
    private javax.swing.JButton btnCancelarModal;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtEstadoUsuarioModal;
    // End of variables declaration//GEN-END:variables
}
