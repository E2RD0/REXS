/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.modal;

import java.text.SimpleDateFormat;
import rexsdesktop.controller.Activities;

/**
 *
 * @author Carlos Herrera
 */
public class ModalNuevaActividad extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    public ModalNuevaActividad() {
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

        btnAceptarModal = new javax.swing.JButton();
        btnCancelarModal = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        cbxUbicacionModal = new javax.swing.JComboBox<>();
        jLabel91 = new javax.swing.JLabel();
        txtDescripcionModal = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        txtNombreActividadModal = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        dateFechaInicio = new com.toedter.calendar.JDateChooser();
        dateFechaFinalizacion = new com.toedter.calendar.JDateChooser();
        jLabel70 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(470, 325));

        btnAceptarModal.setBackground(new java.awt.Color(213, 222, 255));
        btnAceptarModal.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        btnAceptarModal.setForeground(new java.awt.Color(46, 91, 255));
        btnAceptarModal.setText("Aceptar");
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

        jSeparator1.setForeground(new java.awt.Color(164, 164, 164));

        cbxUbicacionModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        cbxUbicacionModal.setForeground(new java.awt.Color(46, 56, 77));
        cbxUbicacionModal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxUbicacionModal.setPreferredSize(new java.awt.Dimension(56, 27));

        jLabel91.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(176, 186, 201));
        jLabel91.setText("UBICACIÓN");

        txtDescripcionModal.setBackground(new java.awt.Color(249, 250, 255));
        txtDescripcionModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtDescripcionModal.setForeground(new java.awt.Color(46, 56, 77));
        txtDescripcionModal.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));

        jLabel69.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(176, 186, 201));
        jLabel69.setText("DESCRIPCIÓN");

        txtNombreActividadModal.setBackground(new java.awt.Color(249, 250, 255));
        txtNombreActividadModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtNombreActividadModal.setForeground(new java.awt.Color(46, 56, 77));
        txtNombreActividadModal.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));

        jLabel67.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(176, 186, 201));
        jLabel67.setText("NOMBRE ACTIVIDAD");

        jLabel68.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(176, 186, 201));
        jLabel68.setText("FECHA INICIO");

        dateFechaInicio.setDateFormatString("yyyy-MM-dd HH:mm:ss");

        dateFechaFinalizacion.setDateFormatString("yyyy-MM-dd HH:mm:ss");

        jLabel70.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(176, 186, 201));
        jLabel70.setText("FECHA FINALIZACIÓN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtDescripcionModal, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbxUbicacionModal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel91))
                                    .addGap(40, 40, 40))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel70)
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(dateFechaFinalizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addContainerGap())))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel69)
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel67)
                            .addComponent(txtNombreActividadModal, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel68))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(jLabel68))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombreActividadModal, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel69)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateFechaFinalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel91)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxUbicacionModal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDescripcionModal))
                .addGap(30, 30, 30)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarModalActionPerformed
        String nombre = txtNombreActividadModal.getText();
        String descripcion = txtDescripcionModal.getText();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaInicio = formato.format(dateFechaInicio.getDate());
        String fechaFin = formato.format(dateFechaFinalizacion.getDate());
        int idUbicacion = cbxUbicacionModal.getSelectedIndex() + 1;

        if (Activities.nuevaActividad(nombre, descripcion, fechaInicio, fechaFin, idUbicacion)) {
            System.out.println("Actividad Ingresada");
        } else {
            System.out.println("Nel, falló");
        }
    }//GEN-LAST:event_btnAceptarModalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptarModal;
    private javax.swing.JButton btnCancelarModal;
    private javax.swing.JComboBox<String> cbxUbicacionModal;
    private com.toedter.calendar.JDateChooser dateFechaFinalizacion;
    private com.toedter.calendar.JDateChooser dateFechaInicio;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtDescripcionModal;
    private javax.swing.JTextField txtNombreActividadModal;
    // End of variables declaration//GEN-END:variables
}