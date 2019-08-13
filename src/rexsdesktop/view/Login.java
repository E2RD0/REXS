/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;
import rexsdesktop.CurrentUser;
import rexsdesktop.controller.User;
import rexsdesktop.controller.Validation;

/**
 *
 * @author user
 */
public class Login extends javax.swing.JFrame {

    RubikFonts f = new RubikFonts();
    Timer t;
    String correo = null;

    public Login() {
        initComponents();
        this.setTitle("REXS");
        cambiarCardLayoutPanel("InicioSesion");
        LoginBG.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("resources/loginbg.png")).getImage().getScaledInstance(500, 625, Image.SCALE_SMOOTH)));
//        jLabel1.setFont(f.light.deriveFont(30f));
        btnCardRecuperarClave.setContentAreaFilled(false);
        btnCardRegistro.setContentAreaFilled(false);
        btnSignGoogle.setContentAreaFilled(false);
        btnSignFacebook.setContentAreaFilled(false);
        btnCardInicioSesion.setContentAreaFilled(false);
        btnCardInicioSesion1.setContentAreaFilled(false);
        btnSignGoogle1.setContentAreaFilled(false);
        btnSignFacebook1.setContentAreaFilled(false);
    }

    public void cambiarCardLayoutPanel(String nombre) {
        CardLayout cl = (CardLayout) (CardLayoutPanel.getLayout());
        cl.show(CardLayoutPanel, nombre);
    }

    public void resetCampos() {
        txtNombreCompletoR.setText("");
        txtEmailR.setText("");
        txtPasswordR.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        lblErrorPasswordR.setText("");
        lblErrorEmailR.setText("");
        lblErrorNombre.setText("");
        lblErrorEmail.setText("");
        lblErrorPasswordR.setText("");
        lblErrorGeneral.setText("");
        txtEmailR.setBackground(new java.awt.Color(249, 250, 255));
        txtNombreCompletoR.setBackground(new java.awt.Color(249, 250, 255));
        txtPasswordR.setBackground(new java.awt.Color(249, 250, 255));
        txtEmail.setBackground(new java.awt.Color(249, 250, 255));
        txtPassword.setBackground(new java.awt.Color(249, 250, 255));
    }

    private void iniciarSesion() {
        String email = txtEmail.getText();
        String password = String.valueOf(txtPassword.getPassword());
        if (!Validation.VerificadorLogin.verify(email, password)) {
            if (!"".equals(Validation.VerificadorLogin.mensajeEmail)) {
                txtEmail.setBackground(new java.awt.Color(255, 204, 204));
            } else {
                txtEmail.setBackground(new java.awt.Color(249, 250, 255));
            }
            if (!"".equals(Validation.VerificadorLogin.mensajePassword)) {
                txtPassword.setBackground(new java.awt.Color(255, 204, 204));
            } else {
                txtPassword.setBackground(new java.awt.Color(249, 250, 255));
            }
            lblErrorEmail.setText(Validation.VerificadorLogin.mensajeEmail);
            lblErrorPassword.setText(Validation.VerificadorLogin.mensajePassword);
        } else {
            txtEmail.setBackground(new java.awt.Color(249, 250, 255));
            txtPassword.setBackground(new java.awt.Color(249, 250, 255));
            lblErrorEmail.setText("");
            lblErrorPassword.setText("");
            if (User.iniciarSesion(email, password)) {
                System.out.println("Inicio Correcto");
                if (CurrentUser.idEstadoUsuario == User.getIdEstadoUsuario("Activo")) {
                    if (CurrentUser.idTipoUsuario == User.getIdTipoUsuario("Administrador") || CurrentUser.idTipoUsuario == User.getIdTipoUsuario("Superadministrador")) {
                        Admin fAdmin = new Admin();
                        this.setVisible(false);
                        fAdmin.setLocationRelativeTo(null);
                        fAdmin.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        fAdmin.setVisible(true);
                        this.dispose();
                    } else {
                        lblErrorGeneral.setText("El usuario no tiene los permisos necesarios.");
                        txtEmail.setBackground(new java.awt.Color(255, 204, 204));
                        txtPassword.setBackground(new java.awt.Color(255, 204, 204));
                    }
                } else {
                    lblErrorGeneral.setText("El usuario ha sido bloqueado o eliminado.");
                    txtEmail.setBackground(new java.awt.Color(255, 204, 204));
                    txtPassword.setBackground(new java.awt.Color(255, 204, 204));
                }
            } else {
                lblErrorGeneral.setText("Usuario o contraseña incorrectos");
                txtEmail.setBackground(new java.awt.Color(255, 204, 204));
                txtPassword.setBackground(new java.awt.Color(255, 204, 204));
                System.out.println("Incio Incorrecto");
            }
        }
    }

    private void registrarse() {
        String nombre = txtNombreCompletoR.getText();
        String correo = txtEmailR.getText();
        String password = String.valueOf(txtPasswordR.getPassword());

        if (!Validation.VerificadorNombre.verify(nombre)) {
            txtNombreCompletoR.setBackground(new java.awt.Color(255, 204, 204));
        } else {
            txtNombreCompletoR.setBackground(new java.awt.Color(249, 250, 255));
        }
        if (!Validation.VerificadorEmail.verify(correo)) {
            txtEmailR.setBackground(new java.awt.Color(255, 204, 204));
        } else {
            txtEmailR.setBackground(new java.awt.Color(249, 250, 255));
        }
        if (!Validation.VerificadorPassword.verify(password)) {
            txtPasswordR.setBackground(new java.awt.Color(255, 204, 204));
        } else {
            txtPasswordR.setBackground(new java.awt.Color(249, 250, 255));
        }
        lblErrorPasswordR.setText(Validation.VerificadorPassword.mensaje);
        lblErrorEmailR.setText(Validation.VerificadorEmail.mensaje);
        lblErrorNombre.setText(Validation.VerificadorNombre.mensaje);
        if (Validation.VerificadorNombre.verify(nombre) && Validation.VerificadorEmail.verify(correo) && Validation.VerificadorPassword.verify(password)) {
            if (User.nuevoUsuario(nombre, correo, password, "Visitante", "Activo")) {
                txtEmailR.setBackground(new java.awt.Color(249, 250, 255));
                lblErrorEmailR.setText("");
                cambiarCardLayoutPanel("Exito");
                resetCampos();
                ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                executorService.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        cambiarCardLayoutPanel("InicioSesion");
                        executorService.shutdownNow();
                    }
                }, 2, 1, TimeUnit.SECONDS);

            } else {
                if ("<html>Ya existe un usuario con la dirección de<br>correo electrónico.</html>".equals(User.mensajeError)) {
                    txtEmailR.setBackground(new java.awt.Color(255, 204, 204));
                    lblErrorEmailR.setText(User.mensajeError);
                } else {
                    JOptionPane.showMessageDialog(this, User.mensajeError);
                }
            }
        }

    }

    private void enviarCorreo() {
        correo = txtEmailRecu.getText();

        if (!Validation.VerificadorEmail.verify(correo)) {
            txtEmailRecu.setBackground(new java.awt.Color(255, 204, 204));
        } else {
            txtEmailRecu.setBackground(new java.awt.Color(249, 250, 255));
            cambiarCardLayoutPanel("RecuperarClaveCodigo");
            jLabel24.setText("<html> Ingresa el código alfanumérico que fue<br> enviado a " + correo + "</html>");
            User.enviarCorreo(correo);
        }
        lblErrorEmailRecu.setText(Validation.VerificadorEmail.mensaje);
    }

    private void verificarPIN() {
        String pin = txtPIN.getText();
        pin = pin.trim();
        if (Validation.isStringEmptyOrNull(pin)) {
            lblErrorPIN.setText("El campo no puede estar vacío.");
        } else {
            lblErrorPIN.setText("");
            if (User.verificarPin(pin, correo)) {
                System.out.println("Verificado correctamente");
                cambiarCardLayoutPanel("RecuperarClaveCambiar");
            } else {
                System.out.println("Pin equivocado");
                txtPIN.setBackground(new java.awt.Color(255, 204, 204));
                lblErrorPIN.setText("PIN incorrecto");
            }
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

        jPanel2 = new javax.swing.JPanel();
        LoginBG = new javax.swing.JLabel();
        CardLayoutPanel = new javax.swing.JPanel();
        pnlIniciarSesion = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnCardRecuperarClave = new javax.swing.JButton();
        btnIniciarSesion = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        btnCardRegistro = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        btnSignGoogle = new javax.swing.JButton();
        btnSignFacebook = new javax.swing.JButton();
        lblErrorEmail = new javax.swing.JLabel();
        lblErrorPassword = new javax.swing.JLabel();
        lblErrorGeneral = new javax.swing.JLabel();
        pnlRegistro = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtEmailR = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtPasswordR = new javax.swing.JPasswordField();
        btnCrearCuenta = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        btnCardInicioSesion = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        btnSignGoogle1 = new javax.swing.JButton();
        btnSignFacebook1 = new javax.swing.JButton();
        txtNombreCompletoR = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        lblErrorNombre = new javax.swing.JLabel();
        lblErrorEmailR = new javax.swing.JLabel();
        lblErrorPasswordR = new javax.swing.JLabel();
        pnlRecuperarClave = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtEmailRecu = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel("<html>Ingresa el correo electrónico con el que te registraste.</html>");
        jLabel19 = new javax.swing.JLabel();
        btnEnviarCodigo = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        btnCardInicioSesion1 = new javax.swing.JButton();
        lblErrorEmailRecu = new javax.swing.JLabel("<html>Enviaremos un código de verificación<br> para comprobar que eres tú.</html>");
        pnlRecuperarClaveCodigo = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtPIN = new javax.swing.JTextField();
        btnVerificarCodigo = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        btnCardInicioSesion2 = new javax.swing.JButton();
        lblErrorPIN = new javax.swing.JLabel();
        pnlRecuperarClaveCambiar = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        btnCambiarClave = new javax.swing.JButton();
        txtClaveNueva = new javax.swing.JPasswordField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtConfirmarClave = new javax.swing.JPasswordField();
        lblErrorPasswordCambio = new javax.swing.JLabel();
        pnlExito = new javax.swing.JPanel();
        lblTituloExito = new javax.swing.JLabel();
        lblMensajeExito = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(1000, 630));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setAlignmentX(0.0F);
        jPanel2.setAlignmentY(0.0F);
        jPanel2.setAutoscrolls(true);
        jPanel2.setEnabled(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(500, 625));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LoginBG.setAlignmentY(0.0F);
        LoginBG.setIconTextGap(0);
        LoginBG.setPreferredSize(new java.awt.Dimension(500, 625));
        jPanel2.add(LoginBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, -1, 625));

        CardLayoutPanel.setPreferredSize(new java.awt.Dimension(500, 625));
        CardLayoutPanel.setLayout(new java.awt.CardLayout());

        pnlIniciarSesion.setBackground(new java.awt.Color(255, 255, 255));
        pnlIniciarSesion.setPreferredSize(new java.awt.Dimension(500, 625));

        jLabel1.setFont(new java.awt.Font("Rubik Light", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(46, 56, 77));
        jLabel1.setText("Inicia sesión");
        jLabel1.setPreferredSize(new java.awt.Dimension(131, 28));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/rexslogo.png"))); // NOI18N

        txtEmail.setBackground(new java.awt.Color(249, 250, 255));
        txtEmail.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(46, 56, 77));
        txtEmail.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(135, 152, 173));
        jLabel2.setText("Ingresa tus datos para continuar.");

        jLabel4.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(176, 186, 201));
        jLabel4.setText("CORREO ELECTRÓNICO");

        jLabel5.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(176, 186, 201));
        jLabel5.setText("CONTRASEÑA");

        txtPassword.setBackground(new java.awt.Color(249, 250, 255));
        txtPassword.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(46, 56, 77));
        txtPassword.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtPassword.setEchoChar('\u2022');
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPasswordKeyTyped(evt);
            }
        });

        btnCardRecuperarClave.setBackground(new java.awt.Color(255, 255, 255));
        btnCardRecuperarClave.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        btnCardRecuperarClave.setForeground(new java.awt.Color(176, 186, 201));
        btnCardRecuperarClave.setText("¿OLVIDASTE TU CONTRASEÑA? ");
        btnCardRecuperarClave.setBorder(null);
        btnCardRecuperarClave.setBorderPainted(false);
        btnCardRecuperarClave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCardRecuperarClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCardRecuperarClaveActionPerformed(evt);
            }
        });

        btnIniciarSesion.setBackground(new java.awt.Color(46, 91, 255));
        btnIniciarSesion.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        btnIniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarSesion.setText("Iniciar sesión");
        btnIniciarSesion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnIniciarSesion.setBorderPainted(false);
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(112, 112, 112));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("o");

        jSeparator2.setForeground(new java.awt.Color(193, 193, 193));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(193, 193, 193)));
        jSeparator2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSeparator2.setPreferredSize(new java.awt.Dimension(100, 2));

        jLabel7.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(176, 186, 201));
        jLabel7.setText("¿No tienes una cuenta?");

        btnCardRegistro.setBackground(new java.awt.Color(255, 255, 255));
        btnCardRegistro.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        btnCardRegistro.setForeground(new java.awt.Color(46, 91, 255));
        btnCardRegistro.setText("Registrate");
        btnCardRegistro.setBorder(null);
        btnCardRegistro.setBorderPainted(false);
        btnCardRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCardRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCardRegistroActionPerformed(evt);
            }
        });

        jSeparator3.setForeground(new java.awt.Color(193, 193, 193));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(193, 193, 193)));
        jSeparator3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSeparator3.setPreferredSize(new java.awt.Dimension(100, 2));

        btnSignGoogle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/btn_google_signin_light_normal_web.png"))); // NOI18N
        btnSignGoogle.setBorderPainted(false);
        btnSignGoogle.setIconTextGap(0);
        btnSignGoogle.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/btn_google_signin_light_pressed_web.png"))); // NOI18N

        btnSignFacebook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/continuefacebook.png"))); // NOI18N
        btnSignFacebook.setBorderPainted(false);
        btnSignFacebook.setIconTextGap(0);

        lblErrorEmail.setFont(new java.awt.Font("Rubik Light", 0, 11)); // NOI18N
        lblErrorEmail.setForeground(new java.awt.Color(255, 51, 51));

        lblErrorPassword.setFont(new java.awt.Font("Rubik Light", 0, 11)); // NOI18N
        lblErrorPassword.setForeground(new java.awt.Color(255, 51, 51));

        lblErrorGeneral.setFont(new java.awt.Font("Rubik Light", 0, 11)); // NOI18N
        lblErrorGeneral.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout pnlIniciarSesionLayout = new javax.swing.GroupLayout(pnlIniciarSesion);
        pnlIniciarSesion.setLayout(pnlIniciarSesionLayout);
        pnlIniciarSesionLayout.setHorizontalGroup(
            pnlIniciarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIniciarSesionLayout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addGroup(pnlIniciarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlIniciarSesionLayout.createSequentialGroup()
                        .addComponent(lblErrorEmail)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlIniciarSesionLayout.createSequentialGroup()
                        .addGroup(pnlIniciarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnIniciarSesion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlIniciarSesionLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnCardRecuperarClave))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlIniciarSesionLayout.createSequentialGroup()
                                .addGroup(pnlIniciarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlIniciarSesionLayout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCardRegistro)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlIniciarSesionLayout.createSequentialGroup()
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(137, 137, 137))
                    .addGroup(pnlIniciarSesionLayout.createSequentialGroup()
                        .addGroup(pnlIniciarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblErrorGeneral)
                            .addComponent(lblErrorPassword))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(pnlIniciarSesionLayout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addGroup(pnlIniciarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSignGoogle, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSignFacebook, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlIniciarSesionLayout.setVerticalGroup(
            pnlIniciarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIniciarSesionLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3)
                .addGap(60, 60, 60)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(13, 13, 13)
                .addComponent(lblErrorGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel4)
                .addGap(1, 1, 1)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(lblErrorEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGroup(pnlIniciarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlIniciarSesionLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(lblErrorPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCardRecuperarClave)
                        .addGap(18, 18, 18)
                        .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlIniciarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(btnCardRegistro))
                        .addGroup(pnlIniciarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlIniciarSesionLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlIniciarSesionLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnSignGoogle, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSignFacebook, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlIniciarSesionLayout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60))
        );

        CardLayoutPanel.add(pnlIniciarSesion, "InicioSesion");

        pnlRegistro.setBackground(new java.awt.Color(255, 255, 255));
        pnlRegistro.setPreferredSize(new java.awt.Dimension(500, 625));

        jLabel8.setFont(new java.awt.Font("Rubik Light", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(46, 56, 77));
        jLabel8.setText("Regístrate ahora");
        jLabel8.setPreferredSize(new java.awt.Dimension(131, 28));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/rexslogo.png"))); // NOI18N

        txtEmailR.setBackground(new java.awt.Color(249, 250, 255));
        txtEmailR.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtEmailR.setForeground(new java.awt.Color(46, 56, 77));
        txtEmailR.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtEmailR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailRKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(135, 152, 173));
        jLabel10.setText("Explora la Expotécnica Ricaldone.");

        jLabel11.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(176, 186, 201));
        jLabel11.setText("CORREO ELECTRÓNICO");

        jLabel12.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(176, 186, 201));
        jLabel12.setText("CONTRASEÑA");

        txtPasswordR.setBackground(new java.awt.Color(249, 250, 255));
        txtPasswordR.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtPasswordR.setForeground(new java.awt.Color(46, 56, 77));
        txtPasswordR.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtPasswordR.setEchoChar('\u2022');
        txtPasswordR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordRKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPasswordRKeyTyped(evt);
            }
        });

        btnCrearCuenta.setBackground(new java.awt.Color(46, 91, 255));
        btnCrearCuenta.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        btnCrearCuenta.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearCuenta.setText("Crear cuenta");
        btnCrearCuenta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnCrearCuenta.setBorderPainted(false);
        btnCrearCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearCuentaActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(112, 112, 112));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("o");

        jSeparator4.setForeground(new java.awt.Color(193, 193, 193));
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(193, 193, 193)));
        jSeparator4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSeparator4.setPreferredSize(new java.awt.Dimension(100, 2));

        jLabel14.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(176, 186, 201));
        jLabel14.setText("¿Ya tienes una cuenta?");

        btnCardInicioSesion.setBackground(new java.awt.Color(255, 255, 255));
        btnCardInicioSesion.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        btnCardInicioSesion.setForeground(new java.awt.Color(46, 91, 255));
        btnCardInicioSesion.setText("Inicia sesión");
        btnCardInicioSesion.setBorder(null);
        btnCardInicioSesion.setBorderPainted(false);
        btnCardInicioSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCardInicioSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCardInicioSesionActionPerformed(evt);
            }
        });

        jSeparator5.setForeground(new java.awt.Color(193, 193, 193));
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(193, 193, 193)));
        jSeparator5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSeparator5.setPreferredSize(new java.awt.Dimension(100, 2));

        btnSignGoogle1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/btn_google_signin_light_normal_web.png"))); // NOI18N
        btnSignGoogle1.setBorderPainted(false);
        btnSignGoogle1.setIconTextGap(0);
        btnSignGoogle1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/btn_google_signin_light_pressed_web.png"))); // NOI18N

        btnSignFacebook1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/continuefacebook.png"))); // NOI18N
        btnSignFacebook1.setBorderPainted(false);
        btnSignFacebook1.setIconTextGap(0);

        txtNombreCompletoR.setBackground(new java.awt.Color(249, 250, 255));
        txtNombreCompletoR.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtNombreCompletoR.setForeground(new java.awt.Color(46, 56, 77));
        txtNombreCompletoR.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtNombreCompletoR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreCompletoRKeyReleased(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(176, 186, 201));
        jLabel15.setText("NOMBRE COMPLETO");

        lblErrorNombre.setFont(new java.awt.Font("Rubik Light", 0, 11)); // NOI18N
        lblErrorNombre.setForeground(new java.awt.Color(255, 51, 51));

        lblErrorEmailR.setFont(new java.awt.Font("Rubik Light", 0, 11)); // NOI18N
        lblErrorEmailR.setForeground(new java.awt.Color(255, 51, 51));

        lblErrorPasswordR.setFont(new java.awt.Font("Rubik Light", 0, 11)); // NOI18N
        lblErrorPasswordR.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout pnlRegistroLayout = new javax.swing.GroupLayout(pnlRegistro);
        pnlRegistro.setLayout(pnlRegistroLayout);
        pnlRegistroLayout.setHorizontalGroup(
            pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistroLayout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSignFacebook1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSignGoogle1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlRegistroLayout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRegistroLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlRegistroLayout.createSequentialGroup()
                        .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNombreCompletoR)
                            .addComponent(btnCrearCuenta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmailR, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPasswordR, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlRegistroLayout.createSequentialGroup()
                                .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlRegistroLayout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCardInicioSesion)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlRegistroLayout.createSequentialGroup()
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(137, 137, 137))
                    .addGroup(pnlRegistroLayout.createSequentialGroup()
                        .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblErrorPasswordR)
                            .addComponent(lblErrorEmailR)
                            .addComponent(lblErrorNombre))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnlRegistroLayout.setVerticalGroup(
            pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistroLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel9)
                .addGap(33, 33, 33)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(30, 30, 30)
                .addComponent(jLabel15)
                .addGap(1, 1, 1)
                .addComponent(txtNombreCompletoR, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(lblErrorNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addGap(1, 1, 1)
                .addComponent(txtEmailR, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lblErrorEmailR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addGap(1, 1, 1)
                .addComponent(txtPasswordR, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lblErrorPasswordR)
                .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRegistroLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCrearCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(btnCardInicioSesion))
                        .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlRegistroLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlRegistroLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnSignGoogle1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSignFacebook1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlRegistroLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        CardLayoutPanel.add(pnlRegistro, "Registro");

        pnlRecuperarClave.setBackground(new java.awt.Color(255, 255, 255));
        pnlRecuperarClave.setPreferredSize(new java.awt.Dimension(500, 625));

        jLabel16.setFont(new java.awt.Font("Rubik Light", 0, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(46, 56, 77));
        jLabel16.setText("Recupera tu contraseña");
        jLabel16.setPreferredSize(new java.awt.Dimension(131, 28));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/rexslogo.png"))); // NOI18N

        txtEmailRecu.setBackground(new java.awt.Color(249, 250, 255));
        txtEmailRecu.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtEmailRecu.setForeground(new java.awt.Color(46, 56, 77));
        txtEmailRecu.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtEmailRecu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailRecuKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmailRecuKeyTyped(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(135, 152, 173));

        jLabel19.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(176, 186, 201));
        jLabel19.setText("CORREO ELECTRÓNICO");

        btnEnviarCodigo.setBackground(new java.awt.Color(46, 91, 255));
        btnEnviarCodigo.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        btnEnviarCodigo.setForeground(new java.awt.Color(255, 255, 255));
        btnEnviarCodigo.setText("Enviar código");
        btnEnviarCodigo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnEnviarCodigo.setBorderPainted(false);
        btnEnviarCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarCodigoActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(176, 186, 201));
        jLabel22.setText("Regresa para");

        btnCardInicioSesion1.setBackground(new java.awt.Color(255, 255, 255));
        btnCardInicioSesion1.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        btnCardInicioSesion1.setForeground(new java.awt.Color(46, 91, 255));
        btnCardInicioSesion1.setText("Iniciar sesión");
        btnCardInicioSesion1.setBorder(null);
        btnCardInicioSesion1.setBorderPainted(false);
        btnCardInicioSesion1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCardInicioSesion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCardInicioSesion1ActionPerformed(evt);
            }
        });

        lblErrorEmailRecu.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        lblErrorEmailRecu.setForeground(new java.awt.Color(135, 152, 173));

        javax.swing.GroupLayout pnlRecuperarClaveLayout = new javax.swing.GroupLayout(pnlRecuperarClave);
        pnlRecuperarClave.setLayout(pnlRecuperarClaveLayout);
        pnlRecuperarClaveLayout.setHorizontalGroup(
            pnlRecuperarClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRecuperarClaveLayout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addGroup(pnlRecuperarClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRecuperarClaveLayout.createSequentialGroup()
                        .addComponent(lblErrorEmailRecu)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRecuperarClaveLayout.createSequentialGroup()
                        .addGroup(pnlRecuperarClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEnviarCodigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmailRecu, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlRecuperarClaveLayout.createSequentialGroup()
                                .addGroup(pnlRecuperarClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlRecuperarClaveLayout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(jLabel22)
                                        .addGap(5, 5, 5)
                                        .addComponent(btnCardInicioSesion1)))
                                .addGap(0, 35, Short.MAX_VALUE)))
                        .addGap(137, 137, 137))))
        );
        pnlRecuperarClaveLayout.setVerticalGroup(
            pnlRecuperarClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRecuperarClaveLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel17)
                .addGap(100, 100, 100)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addGap(1, 1, 1)
                .addComponent(txtEmailRecu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblErrorEmailRecu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEnviarCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlRecuperarClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(btnCardInicioSesion1))
                .addGap(291, 291, 291))
        );

        CardLayoutPanel.add(pnlRecuperarClave, "RecuperarClave");

        pnlRecuperarClaveCodigo.setBackground(new java.awt.Color(255, 255, 255));
        pnlRecuperarClaveCodigo.setPreferredSize(new java.awt.Dimension(500, 625));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/rexslogo.png"))); // NOI18N

        jLabel23.setFont(new java.awt.Font("Rubik Light", 0, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(46, 56, 77));
        jLabel23.setText("Recupera tu contraseña");
        jLabel23.setPreferredSize(new java.awt.Dimension(131, 28));

        jLabel24.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(135, 152, 173));

        jLabel25.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(176, 186, 201));
        jLabel25.setText("CÓDIGO");

        txtPIN.setBackground(new java.awt.Color(249, 250, 255));
        txtPIN.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtPIN.setForeground(new java.awt.Color(46, 56, 77));
        txtPIN.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtPIN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPINKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPINKeyTyped(evt);
            }
        });

        btnVerificarCodigo.setBackground(new java.awt.Color(46, 91, 255));
        btnVerificarCodigo.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        btnVerificarCodigo.setForeground(new java.awt.Color(255, 255, 255));
        btnVerificarCodigo.setText("Verificar");
        btnVerificarCodigo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnVerificarCodigo.setBorderPainted(false);
        btnVerificarCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarCodigoActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(176, 186, 201));
        jLabel27.setText("Regresa para");

        btnCardInicioSesion2.setBackground(new java.awt.Color(255, 255, 255));
        btnCardInicioSesion2.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        btnCardInicioSesion2.setForeground(new java.awt.Color(46, 91, 255));
        btnCardInicioSesion2.setText("Iniciar sesión");
        btnCardInicioSesion2.setBorder(null);
        btnCardInicioSesion2.setBorderPainted(false);
        btnCardInicioSesion2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCardInicioSesion2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCardInicioSesion2ActionPerformed(evt);
            }
        });

        lblErrorPIN.setFont(new java.awt.Font("Rubik Light", 0, 11)); // NOI18N
        lblErrorPIN.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout pnlRecuperarClaveCodigoLayout = new javax.swing.GroupLayout(pnlRecuperarClaveCodigo);
        pnlRecuperarClaveCodigo.setLayout(pnlRecuperarClaveCodigoLayout);
        pnlRecuperarClaveCodigoLayout.setHorizontalGroup(
            pnlRecuperarClaveCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRecuperarClaveCodigoLayout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addGroup(pnlRecuperarClaveCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlRecuperarClaveCodigoLayout.createSequentialGroup()
                        .addGroup(pnlRecuperarClaveCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnVerificarCodigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblErrorPIN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPIN, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlRecuperarClaveCodigoLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel27)
                                .addGap(5, 5, 5)
                                .addComponent(btnCardInicioSesion2))
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
                        .addGap(0, 69, Short.MAX_VALUE)))
                .addGap(68, 68, 68))
        );
        pnlRecuperarClaveCodigoLayout.setVerticalGroup(
            pnlRecuperarClaveCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRecuperarClaveCodigoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel21)
                .addGap(100, 100, 100)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel25)
                .addGap(1, 1, 1)
                .addComponent(txtPIN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lblErrorPIN, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVerificarCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlRecuperarClaveCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(btnCardInicioSesion2))
                .addContainerGap(247, Short.MAX_VALUE))
        );

        CardLayoutPanel.add(pnlRecuperarClaveCodigo, "RecuperarClaveCodigo");

        pnlRecuperarClaveCambiar.setBackground(new java.awt.Color(255, 255, 255));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/rexslogo.png"))); // NOI18N

        jLabel28.setFont(new java.awt.Font("Rubik Light", 0, 20)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(46, 56, 77));
        jLabel28.setText("Recupera tu contraseña");
        jLabel28.setPreferredSize(new java.awt.Dimension(131, 28));

        jLabel29.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(135, 152, 173));
        jLabel29.setText("Ingresa tu  nueva contraseña");

        btnCambiarClave.setBackground(new java.awt.Color(46, 91, 255));
        btnCambiarClave.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        btnCambiarClave.setForeground(new java.awt.Color(255, 255, 255));
        btnCambiarClave.setText("Cambiar Contraseña");
        btnCambiarClave.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnCambiarClave.setBorderPainted(false);
        btnCambiarClave.setPreferredSize(new java.awt.Dimension(53, 17));
        btnCambiarClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarClaveActionPerformed(evt);
            }
        });

        txtClaveNueva.setBackground(new java.awt.Color(249, 250, 255));
        txtClaveNueva.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtClaveNueva.setForeground(new java.awt.Color(46, 56, 77));
        txtClaveNueva.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtClaveNueva.setEchoChar('\u2022');

        jLabel32.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(176, 186, 201));
        jLabel32.setText("CONTRASEÑA NUEVA");

        jLabel33.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(176, 186, 201));
        jLabel33.setText("CONFIRMAR CONTRASEÑA");

        txtConfirmarClave.setBackground(new java.awt.Color(249, 250, 255));
        txtConfirmarClave.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtConfirmarClave.setForeground(new java.awt.Color(46, 56, 77));
        txtConfirmarClave.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtConfirmarClave.setEchoChar('\u2022');

        lblErrorPasswordCambio.setFont(new java.awt.Font("Rubik Light", 0, 11)); // NOI18N
        lblErrorPasswordCambio.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout pnlRecuperarClaveCambiarLayout = new javax.swing.GroupLayout(pnlRecuperarClaveCambiar);
        pnlRecuperarClaveCambiar.setLayout(pnlRecuperarClaveCambiarLayout);
        pnlRecuperarClaveCambiarLayout.setHorizontalGroup(
            pnlRecuperarClaveCambiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRecuperarClaveCambiarLayout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addGroup(pnlRecuperarClaveCambiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRecuperarClaveCambiarLayout.createSequentialGroup()
                        .addComponent(lblErrorPasswordCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlRecuperarClaveCambiarLayout.createSequentialGroup()
                        .addGroup(pnlRecuperarClaveCambiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(btnCambiarClave, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtClaveNueva, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtConfirmarClave, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlRecuperarClaveCambiarLayout.createSequentialGroup()
                                .addGroup(pnlRecuperarClaveCambiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(137, 137, 137))))
        );
        pnlRecuperarClaveCambiarLayout.setVerticalGroup(
            pnlRecuperarClaveCambiarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRecuperarClaveCambiarLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel26)
                .addGap(100, 100, 100)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addComponent(jLabel32)
                .addGap(1, 1, 1)
                .addComponent(txtClaveNueva, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel33)
                .addGap(1, 1, 1)
                .addComponent(txtConfirmarClave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lblErrorPasswordCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCambiarClave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(247, Short.MAX_VALUE))
        );

        CardLayoutPanel.add(pnlRecuperarClaveCambiar, "RecuperarClaveCambiar");

        pnlExito.setBackground(new java.awt.Color(255, 255, 255));

        lblTituloExito.setFont(new java.awt.Font("Rubik Light", 0, 24)); // NOI18N
        lblTituloExito.setForeground(new java.awt.Color(46, 56, 77));
        lblTituloExito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloExito.setText("¡Registro exitoso!");
        lblTituloExito.setPreferredSize(new java.awt.Dimension(131, 28));

        lblMensajeExito.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        lblMensajeExito.setForeground(new java.awt.Color(135, 152, 173));
        lblMensajeExito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensajeExito.setText("<html> <div style='text-align: center;'>Ya puedes iniciar sesión con las<br>credenciales con las que te registraste</div></html>");

        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/success.png"))); // NOI18N

        javax.swing.GroupLayout pnlExitoLayout = new javax.swing.GroupLayout(pnlExito);
        pnlExito.setLayout(pnlExitoLayout);
        pnlExitoLayout.setHorizontalGroup(
            pnlExitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExitoLayout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addGroup(pnlExitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTituloExito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMensajeExito, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        pnlExitoLayout.setVerticalGroup(
            pnlExitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlExitoLayout.createSequentialGroup()
                .addContainerGap(182, Short.MAX_VALUE)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTituloExito, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMensajeExito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(220, 220, 220))
        );

        CardLayoutPanel.add(pnlExito, "Exito");

        getContentPane().add(CardLayoutPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCardRecuperarClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCardRecuperarClaveActionPerformed
        cambiarCardLayoutPanel("RecuperarClave");
    }//GEN-LAST:event_btnCardRecuperarClaveActionPerformed

    private void btnCardRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCardRegistroActionPerformed
        cambiarCardLayoutPanel("Registro");
        resetCampos();
    }//GEN-LAST:event_btnCardRegistroActionPerformed

    private void btnCardInicioSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCardInicioSesionActionPerformed
        cambiarCardLayoutPanel("InicioSesion");
        resetCampos();
    }//GEN-LAST:event_btnCardInicioSesionActionPerformed

    private void btnCardInicioSesion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCardInicioSesion1ActionPerformed
        cambiarCardLayoutPanel("InicioSesion");
    }//GEN-LAST:event_btnCardInicioSesion1ActionPerformed

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        iniciarSesion();
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void btnCardInicioSesion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCardInicioSesion2ActionPerformed
        cambiarCardLayoutPanel("InicioSesion");
    }//GEN-LAST:event_btnCardInicioSesion2ActionPerformed

    private void btnEnviarCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarCodigoActionPerformed
        enviarCorreo();
    }//GEN-LAST:event_btnEnviarCodigoActionPerformed

    @SuppressWarnings("deprecation")
    private void btnCambiarClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarClaveActionPerformed
        String NuevaClave = String.valueOf(txtClaveNueva.getPassword());
        String claveConfirmada = String.valueOf(txtConfirmarClave.getPassword());
        if (NuevaClave.equals(claveConfirmada)) {
            if (User.recuperarContraUsuario(NuevaClave, correo)) {
                cambiarCardLayoutPanel("InicioSesion");
            }
        } else {
            lblErrorPasswordCambio.setText("Las contraseñas no coinciden");
        }
    }//GEN-LAST:event_btnCambiarClaveActionPerformed

    private void btnVerificarCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarCodigoActionPerformed
        verificarPIN();
    }//GEN-LAST:event_btnVerificarCodigoActionPerformed

    private void btnCrearCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearCuentaActionPerformed
        registrarse();
    }//GEN-LAST:event_btnCrearCuentaActionPerformed

    private void txtNombreCompletoRKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreCompletoRKeyReleased
        if (!Validation.VerificadorNombre.verify(txtNombreCompletoR.getText())) {
            txtNombreCompletoR.setBackground(new java.awt.Color(255, 204, 204));
        } else {
            txtNombreCompletoR.setBackground(new java.awt.Color(249, 250, 255));
        }
        lblErrorNombre.setText(Validation.VerificadorNombre.mensaje);
    }//GEN-LAST:event_txtNombreCompletoRKeyReleased

    private void txtEmailRKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailRKeyReleased
        if (!Validation.VerificadorEmail.verify(txtEmailR.getText())) {
            txtEmailR.setBackground(new java.awt.Color(255, 204, 204));
        } else {
            txtEmailR.setBackground(new java.awt.Color(249, 250, 255));
        }
        lblErrorEmailR.setText(Validation.VerificadorEmail.mensaje);
    }//GEN-LAST:event_txtEmailRKeyReleased

    private void txtPasswordRKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordRKeyReleased
        String password = String.valueOf(txtPasswordR.getPassword());
        if (!Validation.VerificadorPassword.verify(password)) {
            txtPasswordR.setBackground(new java.awt.Color(255, 204, 204));
        } else {
            txtPasswordR.setBackground(new java.awt.Color(249, 250, 255));
        }
        lblErrorPasswordR.setText(Validation.VerificadorPassword.mensaje);
    }//GEN-LAST:event_txtPasswordRKeyReleased

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        txtEmail.setBackground(new java.awt.Color(249, 250, 255));
        txtPassword.setBackground(new java.awt.Color(249, 250, 255));
        lblErrorGeneral.setText("");
        lblErrorEmail.setText("");
        lblErrorPassword.setText("");
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyReleased
        txtEmail.setBackground(new java.awt.Color(249, 250, 255));
        txtPassword.setBackground(new java.awt.Color(249, 250, 255));
        lblErrorGeneral.setText("");
        lblErrorEmail.setText("");
        lblErrorPassword.setText("");
    }//GEN-LAST:event_txtPasswordKeyReleased

    private void txtEmailRecuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailRecuKeyReleased

    }//GEN-LAST:event_txtEmailRecuKeyReleased

    private void txtPINKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPINKeyReleased
        lblErrorPIN.setText("");
        txtPIN.setBackground(new java.awt.Color(249, 250, 255));
    }//GEN-LAST:event_txtPINKeyReleased

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed

    }//GEN-LAST:event_txtPasswordKeyPressed

    private void txtPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyTyped
        char teclaPresionada = evt.getKeyChar();

        if (teclaPresionada == KeyEvent.VK_ENTER) {
            iniciarSesion();
        }
    }//GEN-LAST:event_txtPasswordKeyTyped

    private void txtPasswordRKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordRKeyTyped
        char teclaPresionada = evt.getKeyChar();

        if (teclaPresionada == KeyEvent.VK_ENTER) {
            registrarse();
        }
    }//GEN-LAST:event_txtPasswordRKeyTyped

    private void txtEmailRecuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailRecuKeyTyped
        char teclaPresionada = evt.getKeyChar();

        if (teclaPresionada == KeyEvent.VK_ENTER) {
            enviarCorreo();
        }
    }//GEN-LAST:event_txtEmailRecuKeyTyped

    private void txtPINKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPINKeyTyped
        char teclaPresionada = evt.getKeyChar();

        if (teclaPresionada == KeyEvent.VK_ENTER) {
            verificarPIN();
        }
    }//GEN-LAST:event_txtPINKeyTyped

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
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CardLayoutPanel;
    private javax.swing.JLabel LoginBG;
    private javax.swing.JButton btnCambiarClave;
    private javax.swing.JButton btnCardInicioSesion;
    private javax.swing.JButton btnCardInicioSesion1;
    private javax.swing.JButton btnCardInicioSesion2;
    private javax.swing.JButton btnCardRecuperarClave;
    private javax.swing.JButton btnCardRegistro;
    private javax.swing.JButton btnCrearCuenta;
    private javax.swing.JButton btnEnviarCodigo;
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton btnSignFacebook;
    private javax.swing.JButton btnSignFacebook1;
    private javax.swing.JButton btnSignGoogle;
    private javax.swing.JButton btnSignGoogle1;
    private javax.swing.JButton btnVerificarCodigo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lblErrorEmail;
    private javax.swing.JLabel lblErrorEmailR;
    private javax.swing.JLabel lblErrorEmailRecu;
    private javax.swing.JLabel lblErrorGeneral;
    private javax.swing.JLabel lblErrorNombre;
    private javax.swing.JLabel lblErrorPIN;
    private javax.swing.JLabel lblErrorPassword;
    private javax.swing.JLabel lblErrorPasswordCambio;
    private javax.swing.JLabel lblErrorPasswordR;
    private javax.swing.JLabel lblMensajeExito;
    private javax.swing.JLabel lblTituloExito;
    private javax.swing.JPanel pnlExito;
    private javax.swing.JPanel pnlIniciarSesion;
    private javax.swing.JPanel pnlRecuperarClave;
    private javax.swing.JPanel pnlRecuperarClaveCambiar;
    private javax.swing.JPanel pnlRecuperarClaveCodigo;
    private javax.swing.JPanel pnlRegistro;
    private javax.swing.JPasswordField txtClaveNueva;
    private javax.swing.JPasswordField txtConfirmarClave;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmailR;
    private javax.swing.JTextField txtEmailRecu;
    private javax.swing.JTextField txtNombreCompletoR;
    private javax.swing.JTextField txtPIN;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtPasswordR;
    // End of variables declaration//GEN-END:variables
}
