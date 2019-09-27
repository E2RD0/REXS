/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.view;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import rexsdesktop.CurrentUser;
import rexsdesktop.controller.General;
import rexsdesktop.controller.User;
import rexsdesktop.controller.Validation;

/**
 *
 * Formulario que contiene el incio de sesion y recuperar clave
 *
 * @author Eduardo
 * @version 1.2
 */
public class Login extends javax.swing.JFrame {

    RubikFonts f = new RubikFonts();
    Timer t;
    String correo = null;

    public Login() {
        initComponents();
        this.setTitle("REXS");
        cambiarCardLayoutPanel("InicioSesion");
        setIconImage(new ImageIcon(getClass().getResource("resources/IconoREXS.png")).getImage());
        LoginBG.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("resources/loginbg.png")).getImage().getScaledInstance(500, 625, Image.SCALE_SMOOTH)));
//        jLabel1.setFont(f.light.deriveFont(30f));
        btnCardRecuperarClave.setContentAreaFilled(false);
        btnCardRegistro.setContentAreaFilled(false);
//        btnSignGoogle.setContentAreaFilled(false);
//        btnSignFacebook.setContentAreaFilled(false);
        btnCardInicioSesion.setContentAreaFilled(false);
        btnCardInicioSesion1.setContentAreaFilled(false);

//        btnSignFacebook.setVisible(false);
//        btnSignFacebook1.setVisible(false);
//        btnSignGoogle1.setVisible(false);
//        btnSignGoogle.setVisible(false);
    }

    /**
     * Método para cambiar de panel, Iniciar sesión o Recuperar clave
     *
     * @param nombre recibe como parametro el nombre del Panel
     */
    public void cambiarCardLayoutPanel(String nombre) {
        CardLayout cl = (CardLayout) (CardLayoutPanel.getLayout());
        cl.show(CardLayoutPanel, nombre);
    }

    /**
     * Método para volver a los valores predeterminados cada elemento de la
     * interfaz.
     */
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

    /**
     * Método para iniciar sesión en el sistema
     *
     */
    private void iniciarSesion() {
        setCursor(Cursor.WAIT_CURSOR);
        String email = txtEmail.getText();
        String password = String.valueOf(txtPassword.getPassword());
        try {
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
//                    System.out.println("Inicio Correcto");
                    if (CurrentUser.idEstadoUsuario == User.getIdEstadoUsuario("Activo")) {
                        if (CurrentUser.idTipoUsuario == User.getIdTipoUsuario("Administrador") || CurrentUser.idTipoUsuario == User.getIdTipoUsuario("Superadministrador")) {
                            Admin fAdmin = new Admin();
                            this.setVisible(false);
                            fAdmin.setLocationRelativeTo(null);
                            fAdmin.setDefaultCloseOperation(EXIT_ON_CLOSE);
                            fAdmin.setVisible(true);
                            this.dispose();
                            General.agregarBitacora("IniciarSesion", CurrentUser.idUsuario);
                            General.getEdicion();
                            Admin.lblEdicion.setText(CurrentUser.edicionExpotecnica);
                            Admin.cargarActividades();
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

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar", "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            setCursor(Cursor.DEFAULT_CURSOR);
        }
    }

    /**
     * Método para registrar un usuario en el sistema
     *
     */
    private void registrarse() {
        try {
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
                if (User.nuevoUsuario(nombre, correo, password, "Administrador", "Activo")) {
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

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método para enviar el correo con el PIN para recuperar la clave de un
     * usuario.
     *
     */
    private void enviarCorreo() {
        try {
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al enviar el correo", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método para verificar el PIN ingresado con el enviado al correo del
     * usuario.
     *
     */
    private void verificarPIN() {
        try {
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al verificar el PIN", "ERROR", JOptionPane.ERROR_MESSAGE);
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
        jLabel7 = new javax.swing.JLabel();
        btnCardRegistro = new javax.swing.JButton();
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
        jLabel14 = new javax.swing.JLabel();
        btnCardInicioSesion = new javax.swing.JButton();
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

        jLabel1.setFont(new java.awt.Font("Rubik", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(46, 56, 77));
        jLabel1.setText("Inicia sesión");
        jLabel1.setPreferredSize(new java.awt.Dimension(131, 28));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/LogoNegroREXS.png"))); // NOI18N

        txtEmail.setBackground(new java.awt.Color(249, 250, 255));
        txtEmail.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(46, 56, 77));
        txtEmail.setToolTipText("Ingresa tu correo electrónico");
        txtEmail.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmailKeyPressed(evt);
            }
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
        txtPassword.setToolTipText("Ingresa tu contraseña");
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
        btnCardRecuperarClave.setToolTipText("Recupera tu contraseña");
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
        btnIniciarSesion.setToolTipText("Iniciar sesión");
        btnIniciarSesion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnIniciarSesion.setBorderPainted(false);
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(176, 186, 201));
        jLabel7.setText("¿No tienes una cuenta?");

        btnCardRegistro.setBackground(new java.awt.Color(255, 255, 255));
        btnCardRegistro.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        btnCardRegistro.setForeground(new java.awt.Color(46, 91, 255));
        btnCardRegistro.setText("Registrate");
        btnCardRegistro.setToolTipText("Registra tu usuario aquí");
        btnCardRegistro.setBorder(null);
        btnCardRegistro.setBorderPainted(false);
        btnCardRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCardRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCardRegistroActionPerformed(evt);
            }
        });

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
                        .addGroup(pnlIniciarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblErrorGeneral)
                            .addComponent(lblErrorPassword))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlIniciarSesionLayout.createSequentialGroup()
                        .addGroup(pnlIniciarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlIniciarSesionLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnCardRecuperarClave))
                            .addComponent(btnIniciarSesion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addGap(0, 16, Short.MAX_VALUE)))
                        .addGap(136, 136, 136))))
        );
        pnlIniciarSesionLayout.setVerticalGroup(
            pnlIniciarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIniciarSesionLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel3)
                .addGap(63, 63, 63)
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
                .addGap(1, 1, 1)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCardRecuperarClave)
                .addGap(8, 8, 8)
                .addComponent(lblErrorPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlIniciarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(btnCardRegistro))
                .addGap(237, 237, 237))
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
        txtEmailR.setToolTipText("Registra tu correo electrónico");
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
        txtPasswordR.setToolTipText("Registra tu contraseña");
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
        btnCrearCuenta.setToolTipText("Crear cuenta");
        btnCrearCuenta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnCrearCuenta.setBorderPainted(false);
        btnCrearCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearCuentaActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(176, 186, 201));
        jLabel14.setText("¿Ya tienes una cuenta?");

        btnCardInicioSesion.setBackground(new java.awt.Color(255, 255, 255));
        btnCardInicioSesion.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        btnCardInicioSesion.setForeground(new java.awt.Color(46, 91, 255));
        btnCardInicioSesion.setText("Inicia sesión");
        btnCardInicioSesion.setToolTipText("Inicia sesión");
        btnCardInicioSesion.setBorder(null);
        btnCardInicioSesion.setBorderPainted(false);
        btnCardInicioSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCardInicioSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCardInicioSesionActionPerformed(evt);
            }
        });

        txtNombreCompletoR.setBackground(new java.awt.Color(249, 250, 255));
        txtNombreCompletoR.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtNombreCompletoR.setForeground(new java.awt.Color(46, 56, 77));
        txtNombreCompletoR.setToolTipText("Registra tu nombre completo");
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
                                .addGap(0, 13, Short.MAX_VALUE)))
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
                .addGap(50, 50, 50)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCrearCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(btnCardInicioSesion))
                .addContainerGap(215, Short.MAX_VALUE))
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
        txtEmailRecu.setToolTipText("Ingresa el correo con el que te registraste");
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
        btnEnviarCodigo.setToolTipText("Enviar código a tu correo");
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
        btnCardInicioSesion1.setToolTipText("Inicia sesión");
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
        txtPIN.setToolTipText("Ingresa el código que recibiste");
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
        btnVerificarCodigo.setToolTipText("Verificar el código enviado");
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
        btnCardInicioSesion2.setToolTipText("Inicia sesión");
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
        btnCambiarClave.setToolTipText("Cambiar la contraseña");
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
        txtClaveNueva.setToolTipText("Ingresa tu nueva contraseña");
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
        txtConfirmarClave.setToolTipText("Confirma tu nueva contraseña");
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
        lblTituloExito.setText("¡Registro Exitoso!");
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
            btnIniciarSesion.requestFocus();
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

    private void txtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyPressed
        int teclaPresionada = evt.getKeyCode();

        if (teclaPresionada == KeyEvent.VK_F6) {
            LoginPoint login2 = new LoginPoint();
            login2.setVisible(true);
            General.getEdicion();
            LoginPoint.lblEdicion.setText(CurrentUser.edicionExpotecnica);
            this.dispose();
        }
    }//GEN-LAST:event_txtEmailKeyPressed

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
    private javax.swing.JButton btnVerificarCodigo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
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
