/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.modal;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import rexsdesktop.controller.Sections;
import rexsdesktop.controller.User;
import rexsdesktop.model.DbConnection;
import rexsdesktop.view.Admin;

/**
 * Clase que contiene el Panel para modificar una sección.
 * @author artur
 */
public class ModalModificarSecciones extends javax.swing.JPanel {

    /**
     * Creates new form ModalModificarSecciones
     */
    DefaultTableModel modelo = new DefaultTableModel();

    public ModalModificarSecciones() {
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
        btnModificarSeccionModal = new javax.swing.JButton();
        cbxUbicacionModal = new javax.swing.JComboBox<>();
        jLabel93 = new javax.swing.JLabel();
        txtSeccionModal = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        cbxNivelModal = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        cbxEspecialidadModal = new javax.swing.JComboBox<>();
        jLId = new javax.swing.JLabel();
        btnCancelarModal = new javax.swing.JButton();

        jLabel67.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(176, 186, 201));
        jLabel67.setText("SECCION");

        jLabel92.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(176, 186, 201));
        jLabel92.setText("ESPECIALIDAD");

        btnModificarSeccionModal.setBackground(new java.awt.Color(213, 222, 255));
        btnModificarSeccionModal.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        btnModificarSeccionModal.setForeground(new java.awt.Color(46, 91, 255));
        btnModificarSeccionModal.setText("Modificar");
        btnModificarSeccionModal.setToolTipText("Modificar sección");
        btnModificarSeccionModal.setBorderPainted(false);
        btnModificarSeccionModal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarSeccionModalActionPerformed(evt);
            }
        });

        cbxUbicacionModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        cbxUbicacionModal.setForeground(new java.awt.Color(46, 56, 77));
        cbxUbicacionModal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxUbicacionModal.setToolTipText("Ubicación");

        jLabel93.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(176, 186, 201));
        jLabel93.setText("UBICACION");

        txtSeccionModal.setBackground(new java.awt.Color(249, 250, 255));
        txtSeccionModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtSeccionModal.setForeground(new java.awt.Color(46, 56, 77));
        txtSeccionModal.setToolTipText("Nombre de la sección");
        txtSeccionModal.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtSeccionModal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSeccionModalActionPerformed(evt);
            }
        });
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

        jSeparator1.setForeground(new java.awt.Color(164, 164, 164));

        cbxEspecialidadModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        cbxEspecialidadModal.setForeground(new java.awt.Color(46, 56, 77));
        cbxEspecialidadModal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxEspecialidadModal.setToolTipText("Especialidad");

        jLId.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLId.setForeground(new java.awt.Color(176, 186, 201));

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
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel67, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel91)
                                            .addComponent(cbxNivelModal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(15, 15, 15)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel92)
                                            .addComponent(cbxEspecialidadModal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel93)
                                    .addComponent(cbxUbicacionModal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtSeccionModal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLId, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnModificarSeccionModal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnCancelarModal)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel67)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
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
                                .addComponent(cbxUbicacionModal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnModificarSeccionModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(btnCancelarModal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    private void btnModificarSeccionModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarSeccionModalActionPerformed
        Sections Conexion = new Sections();
        //JOptionPane.showMessageDialog(null, String.valueOf(jComboBox1.getSelectedIndex() + 1));

        try {
            if (txtSeccionModal.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Existen campos vacios");
            } else {
                Conexion.setSeccion(txtSeccionModal.getText());
                Conexion.setIdNivel(cbxNivelModal.getSelectedIndex());
                Conexion.setIdEspecialidad(cbxEspecialidadModal.getSelectedIndex());
                Conexion.setIdUbicacion(cbxUbicacionModal.getSelectedIndex());
                Conexion.setIdSeccion(Integer.parseInt(jLId.getText()));
                if (Conexion.ActualizarSeccion()) {
                    JOptionPane.showMessageDialog(null, "Datos modificados correctamente");
                    Admin.jPanel24.removeAll();
                    Sections CargarSeccion = new Sections();
                    CargarSeccion.CrearPanelesSecciones(Admin.jPanel24);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al modficar datos");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR GLOBAL");
        }
    }//GEN-LAST:event_btnModificarSeccionModalActionPerformed

    private void txtSeccionModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSeccionModalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSeccionModalActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarModal;
    private javax.swing.JButton btnModificarSeccionModal;
    public javax.swing.JComboBox<String> cbxEspecialidadModal;
    public javax.swing.JComboBox<String> cbxNivelModal;
    public javax.swing.JComboBox<String> cbxUbicacionModal;
    public static javax.swing.JLabel jLId;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JTextField txtSeccionModal;
    // End of variables declaration//GEN-END:variables
}
