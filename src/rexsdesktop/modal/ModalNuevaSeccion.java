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
import static rexsdesktop.modal.ModalModificarSecciones.jLId;

/**
 * Clase que contiene el Panel para agregar una nueva sección.
 *
 * @author artur
 */
public class ModalNuevaSeccion extends javax.swing.JPanel {

    /**
     * Creates new form ModalNuevaSeccion1
     */
    public ModalNuevaSeccion() {
        initComponents();
        Sections Conexion = new Sections();
        cbxEspecialidadModal.setModel(Conexion.obtenerEspecialidad());
        cbxUbicacionModal.setModel(Conexion.obtenerUbicacion());
        cbxNivelModal.setModel(Conexion.obtenerNivel());
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
        jLabel92 = new javax.swing.JLabel();
        btnAceptarModal = new javax.swing.JButton();
        cbxUbicacionModal = new javax.swing.JComboBox<>();
        jLabel93 = new javax.swing.JLabel();
        txtSeccionModal = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        cbxNivelModal = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        cbxEspecialidadModal = new javax.swing.JComboBox<>();
        btnCancelarModal = new javax.swing.JButton();

        jLabel67.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(176, 186, 201));
        jLabel67.setText("SECCION");

        jLabel92.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(176, 186, 201));
        jLabel92.setText("ESPECIALIDAD");

        btnAceptarModal.setBackground(new java.awt.Color(213, 222, 255));
        btnAceptarModal.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        btnAceptarModal.setForeground(new java.awt.Color(46, 91, 255));
        btnAceptarModal.setText("Aceptar");
        btnAceptarModal.setToolTipText("Ingresar nueva sección");
        btnAceptarModal.setBorderPainted(false);
        btnAceptarModal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarModalActionPerformed(evt);
            }
        });

        cbxUbicacionModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        cbxUbicacionModal.setForeground(new java.awt.Color(46, 56, 77));
        cbxUbicacionModal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxUbicacionModal.setToolTipText("Ubicación");
        cbxUbicacionModal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxUbicacionModalActionPerformed(evt);
            }
        });

        jLabel93.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(176, 186, 201));
        jLabel93.setText("UBICACION");

        txtSeccionModal.setBackground(new java.awt.Color(249, 250, 255));
        txtSeccionModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtSeccionModal.setForeground(new java.awt.Color(46, 56, 77));
        txtSeccionModal.setToolTipText("Nombre de la nueva sección");
        txtSeccionModal.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtSeccionModal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSeccionModalKeyTyped(evt);
            }
        });

        jLabel91.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(176, 186, 201));
        jLabel91.setText("NIVEL");

        cbxNivelModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        cbxNivelModal.setForeground(new java.awt.Color(46, 56, 77));
        cbxNivelModal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxNivelModal.setToolTipText("Nivel");
        cbxNivelModal.setPreferredSize(new java.awt.Dimension(56, 27));
        cbxNivelModal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxNivelModalActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(164, 164, 164));

        cbxEspecialidadModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        cbxEspecialidadModal.setForeground(new java.awt.Color(46, 56, 77));
        cbxEspecialidadModal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxEspecialidadModal.setToolTipText("Especialidad");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAceptarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSeccionModal)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel67)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel91)
                                            .addComponent(cbxNivelModal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(15, 15, 15)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel92)
                                            .addComponent(cbxEspecialidadModal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel93)
                                            .addComponent(cbxUbicacionModal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel67)
                .addGap(1, 1, 1)
                .addComponent(txtSeccionModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel91)
                            .addGap(1, 1, 1)
                            .addComponent(cbxNivelModal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel92)
                            .addGap(1, 1, 1)
                            .addComponent(cbxEspecialidadModal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel93)
                        .addGap(1, 1, 1)
                        .addComponent(cbxUbicacionModal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarModalActionPerformed

        
        Sections Conexion = new Sections();
        //JOptionPane.showMessageDialog(null, String.valueOf(jComboBox1.getSelectedIndex() + 1));

        try {
            if (txtSeccionModal.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Existen campos vacios");
            } else {
                Conexion.setSeccion(txtSeccionModal.getText());
                Conexion.setIdEspecialidad(cbxEspecialidadModal.getSelectedIndex());
                Conexion.setIdNivel(cbxNivelModal.getSelectedIndex());
                Conexion.setIdUbicacion(cbxUbicacionModal.getSelectedIndex());
                if (Conexion.agregarSeccion()) {
                    Sections CargarSecciones = new Sections();
                    ModalSecciones.jPanelSecciones.removeAll();
                    CargarSecciones.CrearPanelesSecciones(ModalSecciones.jPanelSecciones, Integer.parseInt(ModalSecciones.jLId.getText()));
                    JOptionPane.showMessageDialog(null, "Datos ingresados correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al insertar datos");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR GLOBAL");
        }
    }//GEN-LAST:event_btnAceptarModalActionPerformed

    private void cbxUbicacionModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxUbicacionModalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxUbicacionModalActionPerformed

    private void txtSeccionModalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSeccionModalKeyTyped
        // TODO add your handling code here:
        char validar = evt.getKeyChar();

        if (Character.isDigit(validar)) {
            getToolkit().beep();

            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar solo Letras");
        }
    }//GEN-LAST:event_txtSeccionModalKeyTyped

    private void btnCancelarModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarModalActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnCancelarModalActionPerformed

    private void cbxNivelModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxNivelModalActionPerformed
//                if(cbxNivelModal.getSelectedItem() == "Septimo" || cbxNivelModal.getSelectedItem() == "Octavo" || cbxNivelModal.getSelectedItem() == "Noveno"){
//            cbxEspecialidadModal.setSelectedIndex(7);
//            cbxEspecialidadModal.removeItemAt(1);
//            cbxEspecialidadModal.removeItemAt(2);
//            cbxEspecialidadModal.removeItemAt(3);
//        }
    }//GEN-LAST:event_cbxNivelModalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptarModal;
    private javax.swing.JButton btnCancelarModal;
    private javax.swing.JComboBox<String> cbxEspecialidadModal;
    private javax.swing.JComboBox<String> cbxNivelModal;
    private javax.swing.JComboBox<String> cbxUbicacionModal;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtSeccionModal;
    // End of variables declaration//GEN-END:variables
}
