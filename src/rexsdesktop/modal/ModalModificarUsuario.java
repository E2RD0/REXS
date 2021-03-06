/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.modal;

import javax.swing.JOptionPane;
import rexsdesktop.CurrentUser;
import rexsdesktop.controller.User;
import rexsdesktop.controller.Validation;
import rexsdesktop.view.Admin;

/**
 * Clase que contiene el Panel para modificar un Usuario.
 * @author artur
 */
public class ModalModificarUsuario extends javax.swing.JPanel {

    /**
     * Creates new form ModalModificarUsuario
     */
    public ModalModificarUsuario() {
        initComponents();
        User Conexion = new User();
        int tipoU = CurrentUser.idTipoUsuario;
        User CargarUsuario = new User();
        if (tipoU == 1) {
            cbxTipoUsuarioModal.setModel(CargarUsuario.obtenerTipoUsuarioSuperAdministrador());
            cbxEstadoUsuarioModal.setModel(CargarUsuario.obtenerEstadoUsuario());
        }else if (tipoU == 2) {
            cbxTipoUsuarioModal.setModel(CargarUsuario.obtenerTipoUsuarioAdministrador());
            cbxEstadoUsuarioModal.setModel(CargarUsuario.obtenerEstadoUsuario());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel91 = new javax.swing.JLabel();
        cbxTipoUsuarioModal = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        cbxEstadoUsuarioModal = new javax.swing.JComboBox<>();
        btnCancelarModal = new javax.swing.JButton();
        jLabel92 = new javax.swing.JLabel();
        btnModficarModal = new javax.swing.JButton();
        txtNombreUsuarioModal = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        txtEmailUsuarioModal = new javax.swing.JTextField();
        txtClaveUsuarioModal = new javax.swing.JPasswordField();
        jLabel70 = new javax.swing.JLabel();
        lblErrorEmailR = new javax.swing.JLabel();
        lblErrorNombre = new javax.swing.JLabel();
        lblErrorPasswordR = new javax.swing.JLabel();

        jLabel91.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(176, 186, 201));
        jLabel91.setText("TIPO DE USUARIO");

        cbxTipoUsuarioModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        cbxTipoUsuarioModal.setForeground(new java.awt.Color(46, 56, 77));
        cbxTipoUsuarioModal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxTipoUsuarioModal.setToolTipText("Tipo de usuario");
        cbxTipoUsuarioModal.setPreferredSize(new java.awt.Dimension(56, 27));

        jSeparator1.setForeground(new java.awt.Color(164, 164, 164));

        cbxEstadoUsuarioModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        cbxEstadoUsuarioModal.setForeground(new java.awt.Color(46, 56, 77));
        cbxEstadoUsuarioModal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxEstadoUsuarioModal.setToolTipText("Estado del usuario");

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

        jLabel92.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(176, 186, 201));
        jLabel92.setText("ESTADO DE USUARIO");

        btnModficarModal.setBackground(new java.awt.Color(213, 222, 255));
        btnModficarModal.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        btnModficarModal.setForeground(new java.awt.Color(46, 91, 255));
        btnModficarModal.setText("Modficar");
        btnModficarModal.setToolTipText("Modificar usuario");
        btnModficarModal.setBorderPainted(false);
        btnModficarModal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModficarModalActionPerformed(evt);
            }
        });

        txtNombreUsuarioModal.setBackground(new java.awt.Color(249, 250, 255));
        txtNombreUsuarioModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtNombreUsuarioModal.setForeground(new java.awt.Color(46, 56, 77));
        txtNombreUsuarioModal.setToolTipText("Nombre completo del usuario");
        txtNombreUsuarioModal.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtNombreUsuarioModal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreUsuarioModalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreUsuarioModalKeyTyped(evt);
            }
        });

        jLabel67.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(176, 186, 201));
        jLabel67.setText("NOMBRE COMPLETO");

        jLabel69.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(176, 186, 201));
        jLabel69.setText("CORREO ELECTRÓNICO");

        jLabel68.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(176, 186, 201));
        jLabel68.setText("CONTRASEÑA");

        txtEmailUsuarioModal.setBackground(new java.awt.Color(249, 250, 255));
        txtEmailUsuarioModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtEmailUsuarioModal.setForeground(new java.awt.Color(46, 56, 77));
        txtEmailUsuarioModal.setToolTipText("Correo electrónico del usuario");
        txtEmailUsuarioModal.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtEmailUsuarioModal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailUsuarioModalKeyReleased(evt);
            }
        });

        txtClaveUsuarioModal.setBackground(new java.awt.Color(249, 250, 255));
        txtClaveUsuarioModal.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtClaveUsuarioModal.setForeground(new java.awt.Color(46, 56, 77));
        txtClaveUsuarioModal.setToolTipText("Contraseña del usuario");
        txtClaveUsuarioModal.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtClaveUsuarioModal.setEchoChar('\u2022');
        txtClaveUsuarioModal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClaveUsuarioModalKeyReleased(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(176, 186, 201));

        lblErrorEmailR.setFont(new java.awt.Font("Rubik Light", 0, 11)); // NOI18N
        lblErrorEmailR.setForeground(new java.awt.Color(255, 51, 51));

        lblErrorNombre.setFont(new java.awt.Font("Rubik Light", 0, 11)); // NOI18N
        lblErrorNombre.setForeground(new java.awt.Color(255, 51, 51));

        lblErrorPasswordR.setFont(new java.awt.Font("Rubik Light", 0, 11)); // NOI18N
        lblErrorPasswordR.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblErrorPasswordR, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblErrorEmailR, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblErrorNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel68)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel91)
                                                .addComponent(cbxTipoUsuarioModal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel92)
                                                .addComponent(cbxEstadoUsuarioModal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(txtClaveUsuarioModal)
                                        .addComponent(txtNombreUsuarioModal, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel69, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtEmailUsuarioModal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
                                    .addComponent(jLabel67)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(btnModficarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnCancelarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(3, 3, 3)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel67)
                .addGap(1, 1, 1)
                .addComponent(txtNombreUsuarioModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErrorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel69)
                .addGap(1, 1, 1)
                .addComponent(txtEmailUsuarioModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(lblErrorEmailR, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel68)
                .addGap(1, 1, 1)
                .addComponent(txtClaveUsuarioModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(lblErrorPasswordR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel91)
                        .addGap(1, 1, 1)
                        .addComponent(cbxTipoUsuarioModal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel92)
                        .addGap(1, 1, 1)
                        .addComponent(cbxEstadoUsuarioModal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCancelarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnModficarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarModalActionPerformed
        
    }//GEN-LAST:event_btnCancelarModalActionPerformed

    private void btnModficarModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModficarModalActionPerformed
        User Conexion = new User();
        //JOptionPane.showMessageDialog(null, String.valueOf(jComboBox1.getSelectedIndex() + 1));

        try {
            if (txtNombreUsuarioModal.getText().equals("") || txtEmailUsuarioModal.getText().equals("") || txtClaveUsuarioModal.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Existen campos vacios");
            } else {
                Conexion.setNombreCompleto(txtNombreUsuarioModal.getText());
                Conexion.setEmail(txtEmailUsuarioModal.getText());
                Conexion.setClave(String.valueOf(txtClaveUsuarioModal.getPassword()));
                Conexion.setIdTipoUsuario(User.getIdTipoUsuario((String)cbxTipoUsuarioModal.getSelectedItem()));
                Conexion.setIdEstadoUsuario(User.getIdEstadoUsuario((String)cbxEstadoUsuarioModal.getSelectedItem()));
                Conexion.setIdUsuario(Integer.parseInt(jLabel70.getText()));
                String nombre = txtNombreUsuarioModal.getText();
                String correo = txtEmailUsuarioModal.getText();
                String password = String.valueOf(txtClaveUsuarioModal.getPassword());

                if (!Validation.VerificadorNombre.verify(nombre)) {
                    txtNombreUsuarioModal.setBackground(new java.awt.Color(255, 204, 204));
                } else {
                    txtNombreUsuarioModal.setBackground(new java.awt.Color(249, 250, 255));
                }
                if (!Validation.VerificadorEmail.verify(correo)) {
                    txtEmailUsuarioModal.setBackground(new java.awt.Color(255, 204, 204));
                } else {
                    txtEmailUsuarioModal.setBackground(new java.awt.Color(249, 250, 255));
                }
                if (!Validation.VerificadorPassword.verify(password)) {
                    txtClaveUsuarioModal.setBackground(new java.awt.Color(255, 204, 204));
                } else {
                    txtClaveUsuarioModal.setBackground(new java.awt.Color(249, 250, 255));
                }
                lblErrorPasswordR.setText(Validation.VerificadorPassword.mensaje);
                lblErrorEmailR.setText(Validation.VerificadorEmail.mensaje);
                lblErrorNombre.setText(Validation.VerificadorNombre.mensaje);
                if (Validation.VerificadorNombre.verify(nombre) && Validation.VerificadorEmail.verify(correo) && Validation.VerificadorPassword.verify(password)) {
                    if (Conexion.ActualizarUsuario()) {
                        cantidadesUsuarios();
                        Admin.jPanel1.removeAll();
                        User CargarUsuario = new User();
                        CargarUsuario.CrearPanelesUsuarios(Admin.jPanel1);
                        JOptionPane.showMessageDialog(null, "Datos modificados correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al modficar datos");
                    }

                    if ("<html>Ya existe un usuario con la dirección de<br>correo electrónico.</html>".equals(User.mensajeError)) {
                        txtEmailUsuarioModal.setBackground(new java.awt.Color(255, 204, 204));
                        lblErrorEmailR.setText(User.mensajeError);
                    } else {
                        //JOptionPane.showMessageDialog(this, User.mensajeError);
                    }
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR GLOBAL");
        }
    }//GEN-LAST:event_btnModficarModalActionPerformed

    private void cantidadesUsuarios() {
        User cargar = new User();
        Admin.jLUsuarioTotal.setText(String.valueOf((cargar.getCantidadUsuarios())));
        Admin.jLUsuarioActivos.setText(String.valueOf((cargar.getCantidadUsuariosActivos())));
    }

    private void txtNombreUsuarioModalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreUsuarioModalKeyReleased
        // TODO add your handling code here:
        if (!Validation.VerificadorNombre.verify(txtNombreUsuarioModal.getText())) {
            txtNombreUsuarioModal.setBackground(new java.awt.Color(255, 204, 204));
        } else {
            txtNombreUsuarioModal.setBackground(new java.awt.Color(249, 250, 255));
        }
        lblErrorNombre.setText(Validation.VerificadorNombre.mensaje);
    }//GEN-LAST:event_txtNombreUsuarioModalKeyReleased

    private void txtEmailUsuarioModalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailUsuarioModalKeyReleased
        // TODO add your handling code here:
        if (!Validation.VerificadorEmail.verify(txtEmailUsuarioModal.getText())) {
            txtEmailUsuarioModal.setBackground(new java.awt.Color(255, 204, 204));
        } else {
            txtEmailUsuarioModal.setBackground(new java.awt.Color(249, 250, 255));
        }
        lblErrorEmailR.setText(Validation.VerificadorEmail.mensaje);
    }//GEN-LAST:event_txtEmailUsuarioModalKeyReleased

    private void txtClaveUsuarioModalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClaveUsuarioModalKeyReleased
        // TODO add your handling code here:
        String password = String.valueOf(txtClaveUsuarioModal.getPassword());
        if (!Validation.VerificadorPassword.verify(password)) {
            txtClaveUsuarioModal.setBackground(new java.awt.Color(255, 204, 204));
        } else {
            txtClaveUsuarioModal.setBackground(new java.awt.Color(249, 250, 255));
        }
        lblErrorPasswordR.setText(Validation.VerificadorPassword.mensaje);
    }//GEN-LAST:event_txtClaveUsuarioModalKeyReleased

    private void txtNombreUsuarioModalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreUsuarioModalKeyTyped
        // TODO add your handling code here:
        char validar = evt.getKeyChar();

        if (Character.isDigit(validar)) {
            getToolkit().beep();

            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar solo Letras");
        }
    }//GEN-LAST:event_txtNombreUsuarioModalKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarModal;
    private javax.swing.JButton btnModficarModal;
    public javax.swing.JComboBox<String> cbxEstadoUsuarioModal;
    public javax.swing.JComboBox<String> cbxTipoUsuarioModal;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    public javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblErrorEmailR;
    private javax.swing.JLabel lblErrorNombre;
    private javax.swing.JLabel lblErrorPasswordR;
    public javax.swing.JPasswordField txtClaveUsuarioModal;
    public javax.swing.JTextField txtEmailUsuarioModal;
    public javax.swing.JTextField txtNombreUsuarioModal;
    // End of variables declaration//GEN-END:variables
}
