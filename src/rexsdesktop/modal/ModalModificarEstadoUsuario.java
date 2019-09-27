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
import rexsdesktop.controller.User;
import rexsdesktop.model.DbConnection;

/**
 * Clase que contiene el Panel para modificar un estado del usuario.
 *
 * @author artur
 */
public class ModalModificarEstadoUsuario extends javax.swing.JPanel {

    /**
     * Creates new form ModalModificarEstadoUsuario
     */
    DefaultTableModel modelo = new DefaultTableModel();

    public ModalModificarEstadoUsuario() {
        initComponents();
        //Tabla
        String[] titulo = new String[]{"Id", "Estado Usuario"};
        modelo.setColumnIdentifiers(titulo);
        jTTabla.setModel(modelo);
        cargarEstadosUsuario();
        //Enviar datos de tabla txt
        jTTabla.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent Mouse_evt) {
                JTable table = (JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);
                if (Mouse_evt.getClickCount() == 1) {
                    txtIdModal.setText(jTTabla.getValueAt(jTTabla.getSelectedRow(), 0).toString());
                    txtEstadoUsuarioModal.setText(jTTabla.getValueAt(jTTabla.getSelectedRow(), 1).toString());
                }
            }
        });
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
        jSeparator1 = new javax.swing.JSeparator();
        btnCancelarModal = new javax.swing.JButton();
        btnModificarEstadoUsuario = new javax.swing.JButton();
        txtEstadoUsuarioModal = new javax.swing.JTextField();
        txtIdModal = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTTabla = new javax.swing.JTable();

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

        btnModificarEstadoUsuario.setBackground(new java.awt.Color(213, 222, 255));
        btnModificarEstadoUsuario.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        btnModificarEstadoUsuario.setForeground(new java.awt.Color(46, 91, 255));
        btnModificarEstadoUsuario.setText("Modficar");
        btnModificarEstadoUsuario.setToolTipText("Modificar estado");
        btnModificarEstadoUsuario.setBorderPainted(false);
        btnModificarEstadoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarEstadoUsuarioActionPerformed(evt);
            }
        });

        txtEstadoUsuarioModal.setBackground(new java.awt.Color(249, 250, 255));
        txtEstadoUsuarioModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtEstadoUsuarioModal.setForeground(new java.awt.Color(46, 56, 77));
        txtEstadoUsuarioModal.setToolTipText("Estado del usuario");
        txtEstadoUsuarioModal.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtEstadoUsuarioModal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEstadoUsuarioModalKeyTyped(evt);
            }
        });

        txtIdModal.setEditable(false);
        txtIdModal.setBackground(new java.awt.Color(249, 250, 255));
        txtIdModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtIdModal.setForeground(new java.awt.Color(46, 56, 77));
        txtIdModal.setToolTipText("Identificaor del estado");
        txtIdModal.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtIdModal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdModalActionPerformed(evt);
            }
        });
        txtIdModal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdModalKeyTyped(evt);
            }
        });

        jLabel68.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(176, 186, 201));
        jLabel68.setText("Id");

        jTTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTTabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel67)
                            .addComponent(txtEstadoUsuarioModal, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel68)
                            .addComponent(txtIdModal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnModificarEstadoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnCancelarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(jLabel68))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEstadoUsuarioModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificarEstadoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void cargarEstadosUsuario() {
        DbConnection cone = new DbConnection();
        Connection con = cone.conectar();
        try {
            Statement stm = con.createStatement();
            ResultSet rst = stm.executeQuery("select idEstadoUsuario, estado from estadoUsuario");
            while (rst.next()) {
                modelo.addRow(new Object[]{
                    rst.getInt(1),
                    rst.getString(2),});
            }
        } catch (Exception e) {
        }
    }


    private void btnModificarEstadoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarEstadoUsuarioActionPerformed
        User Conexion = new User();
        //JOptionPane.showMessageDialog(null, String.valueOf(jComboBox1.getSelectedIndex() + 1));

        try {
            if (txtIdModal.getText().equals("") || txtEstadoUsuarioModal.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Existen campos vacios");
            } else {
                Conexion.setEstadoUsuario(txtEstadoUsuarioModal.getText());
                Conexion.setIdEstadoUsuario(Integer.parseInt(txtIdModal.getText()));
                jTTabla.setModel(modelo);
                if (Conexion.ActualizarEstadoUsuario()) {
                    modelo.setRowCount(0);
                    cargarEstadosUsuario();
                    JOptionPane.showMessageDialog(null, "Datos modificados correctamente ");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al modficar datos");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR GLOBAL");
        }
    }//GEN-LAST:event_btnModificarEstadoUsuarioActionPerformed

    private void txtEstadoUsuarioModalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstadoUsuarioModalKeyTyped
        // TODO add your handling code here:
        char validar = evt.getKeyChar();

        if (Character.isDigit(validar)) {
            getToolkit().beep();

            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar solo Letras");
        }
    }//GEN-LAST:event_txtEstadoUsuarioModalKeyTyped

    private void txtIdModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdModalActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtIdModalActionPerformed

    private void txtIdModalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdModalKeyTyped
        // TODO add your handling code here:
        char validar = evt.getKeyChar();

        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();

            JOptionPane.showMessageDialog(null, "Ingresar solo numeros");
        }
    }//GEN-LAST:event_txtIdModalKeyTyped

    private void btnCancelarModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarModalActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnCancelarModalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarModal;
    private javax.swing.JButton btnModificarEstadoUsuario;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTTabla;
    private javax.swing.JTextField txtEstadoUsuarioModal;
    private javax.swing.JTextField txtIdModal;
    // End of variables declaration//GEN-END:variables
}
