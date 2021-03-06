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
 * Clase que contiene el Panel para modificar un tipo de usuario.
 * @author artur
 */
public class ModalModificarTipoUsuario extends javax.swing.JPanel {

    /**
     * Creates new form ModalModificarTipoUsuario
     */
    DefaultTableModel modelo = new DefaultTableModel();

    public ModalModificarTipoUsuario() {
        initComponents();
        //Tabla
        String[] titulo = new String[]{"Id", "Tipo Usuario"};
        modelo.setColumnIdentifiers(titulo);
        jTTabla.setModel(modelo);
        cargarTabla();
        //Enviar datos de tabla txt
        jTTabla.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent Mouse_evt) {
                JTable table = (JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);
                if (Mouse_evt.getClickCount() == 1) {
                    txtIdModal.setText(jTTabla.getValueAt(jTTabla.getSelectedRow(), 0).toString());
                    txtTipoUsuarioModal.setText(jTTabla.getValueAt(jTTabla.getSelectedRow(), 1).toString());
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

        btnCancelarModal = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel67 = new javax.swing.JLabel();
        txtTipoUsuarioModal = new javax.swing.JTextField();
        btnModificarModal = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTTabla = new javax.swing.JTable();
        idTipo = new javax.swing.JLabel();
        txtIdModal = new javax.swing.JTextField();

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

        jLabel67.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(176, 186, 201));
        jLabel67.setText("Tipo usuario");

        txtTipoUsuarioModal.setBackground(new java.awt.Color(249, 250, 255));
        txtTipoUsuarioModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtTipoUsuarioModal.setForeground(new java.awt.Color(46, 56, 77));
        txtTipoUsuarioModal.setToolTipText("Nombre del tipo de usuario");
        txtTipoUsuarioModal.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtTipoUsuarioModal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTipoUsuarioModalKeyTyped(evt);
            }
        });

        btnModificarModal.setBackground(new java.awt.Color(213, 222, 255));
        btnModificarModal.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        btnModificarModal.setForeground(new java.awt.Color(46, 91, 255));
        btnModificarModal.setText("Modficar");
        btnModificarModal.setToolTipText("Modificar tipo de usuario");
        btnModificarModal.setBorderPainted(false);
        btnModificarModal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarModalActionPerformed(evt);
            }
        });

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

        idTipo.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        idTipo.setForeground(new java.awt.Color(176, 186, 201));
        idTipo.setText("Id");

        txtIdModal.setEditable(false);
        txtIdModal.setBackground(new java.awt.Color(249, 250, 255));
        txtIdModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtIdModal.setForeground(new java.awt.Color(46, 56, 77));
        txtIdModal.setToolTipText("Identificador del tipo de usuario");
        txtIdModal.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtIdModal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdModalKeyTyped(evt);
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
                        .addComponent(btnModificarModal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelarModal))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel67)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtTipoUsuarioModal))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdModal, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idTipo))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(idTipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTipoUsuarioModal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdModal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public void cargarTabla() {
        DbConnection cone = new DbConnection();
        Connection con = cone.conectar();
        try {
            Statement stm = con.createStatement();
            ResultSet rst = stm.executeQuery("select idTipoUsuario, tipo from tipoUsuario");
            while (rst.next()) {
                modelo.addRow(new Object[]{
                    rst.getInt(1),
                    rst.getString(2),});
            }
        } catch (Exception e) {
        }
    }

    private void btnModificarModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarModalActionPerformed
        User Conexion = new User();
        //JOptionPane.showMessageDialog(null, String.valueOf(jComboBox1.getSelectedIndex() + 1));

        try {
            if (txtIdModal.getText().equals("") || txtTipoUsuarioModal.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Existen campos vacios");
            } else {
                Conexion.setTipoUsuario(txtTipoUsuarioModal.getText());
                Conexion.setIdTipoUsuario(Integer.parseInt(txtIdModal.getText()));
                jTTabla.setModel(modelo);
                if (Conexion.ActualizarTipoUsuario()) {
                    modelo.setRowCount(0);
                    cargarTabla();
                    JOptionPane.showMessageDialog(null, "Datos modficados correctamente correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al modficar datos");
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR GLOBAL");
        }
    }//GEN-LAST:event_btnModificarModalActionPerformed

    private void txtTipoUsuarioModalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoUsuarioModalKeyTyped
        // TODO add your handling code here:
        char validar = evt.getKeyChar();

        if (Character.isDigit(validar)) {
            getToolkit().beep();

            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar solo Letras");
        }
    }//GEN-LAST:event_txtTipoUsuarioModalKeyTyped

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
    private javax.swing.JButton btnModificarModal;
    private javax.swing.JLabel idTipo;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTTabla;
    private javax.swing.JTextField txtIdModal;
    private javax.swing.JTextField txtTipoUsuarioModal;
    // End of variables declaration//GEN-END:variables
}
