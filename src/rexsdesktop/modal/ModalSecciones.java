/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.modal;

import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import rexsdesktop.controller.Sections;
import static rexsdesktop.modal.ModalModificarSecciones.jLId;

/**
 *
 * @author artur
 */
public class ModalSecciones extends javax.swing.JPanel {

    private static JDialog modal;

    /**
     * Creates new form ModalSecciones
     */
    public ModalSecciones() {
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

        jLId = new javax.swing.JLabel();
        jLabel210 = new javax.swing.JLabel();
        jLabel209 = new javax.swing.JLabel();
        btnAgregarSeccion = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanelSecciones = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        jLId.setForeground(new java.awt.Color(204, 204, 204));

        jLabel210.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconBack.png"))); // NOI18N
        jLabel210.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel210MouseClicked(evt);
            }
        });

        jLabel209.setFont(new java.awt.Font("Rubik Light", 0, 22)); // NOI18N
        jLabel209.setForeground(new java.awt.Color(46, 56, 77));
        jLabel209.setText("Secciones");

        btnAgregarSeccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Button_Add.png"))); // NOI18N
        btnAgregarSeccion.setBorder(null);
        btnAgregarSeccion.setBorderPainted(false);
        btnAgregarSeccion.setContentAreaFilled(false);
        btnAgregarSeccion.setPreferredSize(new java.awt.Dimension(52, 52));
        btnAgregarSeccion.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/ActiveButton_Add.png"))); // NOI18N
        btnAgregarSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarSeccionActionPerformed(evt);
            }
        });

        jPanelSecciones.setLayout(new java.awt.GridLayout(0, 1, 0, 5));
        jScrollPane2.setViewportView(jPanelSecciones);

        jLabel11.setBackground(new java.awt.Color(244, 246, 252));
        jLabel11.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(191, 197, 210));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Sección");

        jLabel12.setBackground(new java.awt.Color(244, 246, 252));
        jLabel12.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(191, 197, 210));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("ID");

        jLabel13.setBackground(new java.awt.Color(244, 246, 252));
        jLabel13.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(191, 197, 210));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Especialidad");

        jLabel14.setBackground(new java.awt.Color(244, 246, 252));
        jLabel14.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(191, 197, 210));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Nivel");

        jLabel15.setBackground(new java.awt.Color(244, 246, 252));
        jLabel15.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(191, 197, 210));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Ubicación");

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
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel210)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel209)))
                        .addGap(38, 38, 38)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(jLabel13)
                        .addGap(128, 128, 128)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAgregarSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel210)
                    .addComponent(jLabel209))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 299, Short.MAX_VALUE)
                        .addComponent(btnAgregarSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel210MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel210MouseClicked
        Window w = SwingUtilities.getWindowAncestor(ModalSecciones.this);
        w.setVisible(false);
    }//GEN-LAST:event_jLabel210MouseClicked

    private void btnAgregarSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarSeccionActionPerformed
        // TODO add your handling code here:
        Frame fr = null;
        ModalNuevaSeccion ModalAgregarSeccion = new ModalNuevaSeccion();
        modal = new JDialog(fr, "Nueva Seccion", true);
        modal.getContentPane().add(ModalAgregarSeccion);
        modal.setResizable(false);
        modal.pack();
        modal.setLocationRelativeTo(null);
        modal.setVisible(true);
    }//GEN-LAST:event_btnAgregarSeccionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarSeccion;
    public static javax.swing.JLabel jLId;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel209;
    private javax.swing.JLabel jLabel210;
    public static javax.swing.JPanel jPanelSecciones;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
