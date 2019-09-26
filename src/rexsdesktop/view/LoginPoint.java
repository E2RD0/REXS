/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import rexsdesktop.CurrentUser;
import rexsdesktop.controller.User;
import rexsdesktop.controller.Validation;

/**
 *
 * @author Carlos Herrera
 */
public class LoginPoint extends javax.swing.JFrame {

    private static JDialog modal;
    
    private ImageIcon iconNegroVisitante = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/WhiteVisitante.png"));
    private ImageIcon iconNegroInvitado = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/WhiteInvitado.png"));
    private ImageIcon iconBlancoVisitante = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/BlackVisitante.png"));
    private ImageIcon iconBlancoInvitado = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/BlackInvitado.png"));
    private ImageIcon iconNegro = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/LogoNegroREXS.png"));
    private ImageIcon iconBlanco = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/LogoBlancoREXS.png"));
    private ImageIcon iconDia = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Day.png"));
    private ImageIcon iconNoche = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Night.png"));

    private int color = 0;
    
    private Color colorActive = new Color(46, 91, 255);
    private Color colorNormal = new Color(176, 186, 201);
    private Color bgActive = new Color(238, 242, 255);
    private Color bgNormal = new Color(255, 255, 255);
    private Color darkSuperior = new Color(18, 20, 18);
    private Color darkMenu = new Color(30, 31, 36);
    private Color darkbtn = new Color(41, 41, 41);
    private Color darkfondo = new Color(52, 48, 57);
    private Color darkBlue = new Color(46, 91, 255);
    private Color darkPanel = new Color(37, 37, 37);
    private Color darknormal = new Color(46, 56, 77);
    
    /**
     * Creates new form LoginPoint
     */
    public LoginPoint() {
        this.setExtendedState(this.MAXIMIZED_BOTH);
        initComponents();
        lblImagenDerecha.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("resources/loginbg.png")).getImage().getScaledInstance(500, 625, Image.SCALE_SMOOTH)));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlModalAyuda = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        btnCancelarModal1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panelCardLayout = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblImagenDerecha = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnCrearCuenta = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel210 = new javax.swing.JLabel();
        lblErrorNombre = new javax.swing.JLabel();
        lblErrorEmailR = new javax.swing.JLabel();
        lblColor = new javax.swing.JLabel();
        lblAyuda = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblEdicion = new javax.swing.JLabel();

        pnlModalAyuda.setMaximumSize(new java.awt.Dimension(511, 413));

        jSeparator2.setForeground(new java.awt.Color(164, 164, 164));

        btnCancelarModal1.setBackground(new java.awt.Color(247, 214, 218));
        btnCancelarModal1.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        btnCancelarModal1.setForeground(new java.awt.Color(214, 54, 73));
        btnCancelarModal1.setText("Cancelar");
        btnCancelarModal1.setBorderPainted(false);
        btnCancelarModal1.setDefaultCapable(false);
        btnCancelarModal1.setFocusPainted(false);
        btnCancelarModal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarModal1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Rubik Medium", 0, 48)); // NOI18N
        jLabel9.setText("?");

        jLabel10.setFont(new java.awt.Font("Rubik Medium", 0, 48)); // NOI18N
        jLabel10.setText("¿Qué es ");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/LogoTactil.png"))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Rubik", 0, 16)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("<html>\n<p align=\"justify\">\nEl proyecto consta de una aplicación de escritorio la cual,\n brinda la ubicación, descripción; horarios, de los \ndiferentes proyectos, actividades y\neventos a los visitantes e invitados de la\nExpotécnica, además los visitantes podrán buscar\nun proyecto en específico y visualizar su ubicación, descripción, integrantes, resumen,\nfotografías del proyecto y podrán votar por este.\nLa ubicación de cada proyecto y actividad se\ndarán por medio de un mapa de las instalaciones del Instituto Técnico Ricaldone.\n</p>\n</html>");

        javax.swing.GroupLayout pnlModalAyudaLayout = new javax.swing.GroupLayout(pnlModalAyuda);
        pnlModalAyuda.setLayout(pnlModalAyudaLayout);
        pnlModalAyudaLayout.setHorizontalGroup(
            pnlModalAyudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlModalAyudaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCancelarModal1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
            .addGroup(pnlModalAyudaLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(pnlModalAyudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlModalAyudaLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        pnlModalAyudaLayout.setVerticalGroup(
            pnlModalAyudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlModalAyudaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlModalAyudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlModalAyudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel11)
                        .addComponent(jLabel10))
                    .addGroup(pnlModalAyudaLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel9)))
                .addGap(18, 18, 18)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelarModal1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(1300, 700));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(1000, 786));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/LogoTactil.png"))); // NOI18N

        panelCardLayout.setBackground(new java.awt.Color(153, 153, 0));
        panelCardLayout.setLayout(new java.awt.CardLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/WhiteInvitado.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/WhiteVisitante.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(91, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(197, 197, 197)
                .addComponent(jLabel1)
                .addContainerGap(114, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        panelCardLayout.add(jPanel2, "Principal");

        jPanel4.setBackground(new java.awt.Color(255, 250, 250));

        jLabel4.setFont(new java.awt.Font("Rubik", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(46, 56, 77));
        jLabel4.setText("Crea tu cuenta");
        jLabel4.setPreferredSize(new java.awt.Dimension(131, 28));

        jLabel5.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(135, 152, 173));
        jLabel5.setText("Ingresa tus datos para continuar.");

        jLabel6.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(176, 186, 201));
        jLabel6.setText("CORREO ELECTRÓNICO");

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

        txtNombre.setBackground(new java.awt.Color(249, 250, 255));
        txtNombre.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(46, 56, 77));
        txtNombre.setToolTipText("Ingresa tu correo electrónico");
        txtNombre.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(176, 186, 201));
        jLabel7.setText("NOMBRE COMPLETO");

        btnCrearCuenta.setBackground(new java.awt.Color(46, 91, 255));
        btnCrearCuenta.setFont(new java.awt.Font("Rubik Medium", 0, 18)); // NOI18N
        btnCrearCuenta.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearCuenta.setText("Crear Cuenta");
        btnCrearCuenta.setToolTipText("Iniciar sesión");
        btnCrearCuenta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnCrearCuenta.setBorderPainted(false);
        btnCrearCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearCuentaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Rubik", 0, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(46, 56, 77));
        jLabel8.setText("¡Bienvenido!");
        jLabel8.setPreferredSize(new java.awt.Dimension(131, 28));

        jLabel210.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconBack.png"))); // NOI18N
        jLabel210.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel210MouseClicked(evt);
            }
        });

        lblErrorNombre.setFont(new java.awt.Font("Rubik Light", 0, 11)); // NOI18N
        lblErrorNombre.setForeground(new java.awt.Color(255, 51, 51));

        lblErrorEmailR.setFont(new java.awt.Font("Rubik Light", 0, 11)); // NOI18N
        lblErrorEmailR.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel210)
                        .addGap(45, 45, 45)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(19, 19, 19)))
                                .addGap(112, 112, 112))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(134, 134, 134))
                                    .addComponent(txtEmail)
                                    .addComponent(txtNombre)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(148, 148, 148))
                                    .addComponent(btnCrearCuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(82, 82, 82))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblErrorEmailR)
                                    .addComponent(lblErrorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addComponent(lblImagenDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagenDerecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel210)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addComponent(lblErrorEmailR)
                .addGap(29, 29, 29)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblErrorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(btnCrearCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
        );

        panelCardLayout.add(jPanel4, "CrearCuenta");

        lblColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Dia.png"))); // NOI18N
        lblColor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblColorMouseClicked(evt);
            }
        });

        lblAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/info.png"))); // NOI18N
        lblAyuda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAyudaMouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Rubik Light", 0, 40)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(46, 91, 255));
        jLabel13.setText("Expotécnica");

        lblEdicion.setFont(new java.awt.Font("Rubik Light", 0, 40)); // NOI18N
        lblEdicion.setForeground(new java.awt.Color(46, 91, 255));
        lblEdicion.setText("2019");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCardLayout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblAyuda)
                .addGap(386, 386, 386)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblColor)
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAyuda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 21, Short.MAX_VALUE)
                .addComponent(panelCardLayout, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setColorInterfaz() {
        Color superior;
        Color menu;
        Color btn;
        Color fondo;
        Color Blue;
        Color panel;
        Color normal;
        Color letrasNormales;
        if (color == 0) {
            superior = darkSuperior;
            menu = darkMenu;
            btn = darkbtn;
            fondo = darkfondo;
            Blue = darkBlue;
            panel = darkPanel;
            normal = Color.WHITE;
            bgNormal = fondo;
            letrasNormales = Color.WHITE;
            
            lblColor.setIcon(iconNoche);
            jLabel2.setIcon(iconBlanco);
            
            jLabel3.setIcon(iconBlancoVisitante);
            jLabel1.setIcon(iconBlancoInvitado);
            
            color = 1;
        } else {
            superior = new Color(255, 255, 255);
            menu = new Color(255, 255, 255);
            btn = new Color(255, 255, 255);
            fondo = new Color(244, 246, 252);
            Blue = new Color(46, 56, 77);
            panel = new Color(255, 255, 255);
            normal = new Color(46, 56, 77);
            letrasNormales = new Color(0, 0, 0);
            bgNormal = new Color(255, 255, 255);
            lblColor.setIcon(iconDia);
            jLabel2.setIcon(iconNegro);
            
            jLabel3.setIcon(iconNegroVisitante);
            jLabel1.setIcon(iconNegroInvitado);
            
            color = 0;
        }
        //Barras
        jPanel2.setBackground(fondo);
        jPanel3.setBackground(fondo);
        jPanel4.setBackground(fondo);
        jSeparator2.setBackground(fondo);
        
        jLabel4.setForeground(letrasNormales);
        jLabel8.setForeground(letrasNormales);
    }
    
    private void ingresar(String nombre) {
        VisitorAndGuest clase1 = new VisitorAndGuest();
        if (color == 1) {
            color = 0;
        } else {
            color = 1;
        }
        VisitorAndGuest.lblNombreUsuario.setText(nombre);
        clase1.color = color;
        clase1.setColorInterfaz();
        clase1.setVisible(true);
        //Falta agregar el nombre o por lo menos enviarlo a la base
        this.dispose();
    }

    public void cambiarCardLayoutPanel(String nombre) {
        CardLayout cl = (CardLayout) (panelCardLayout.getLayout());
        cl.show(panelCardLayout, nombre);
    }

    private void lblColorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblColorMouseClicked
        setColorInterfaz();
    }//GEN-LAST:event_lblColorMouseClicked

    private void txtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyPressed

    }//GEN-LAST:event_txtEmailKeyPressed

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        if (!Validation.VerificadorEmail.verify(txtEmail.getText())) {
            txtEmail.setBackground(new java.awt.Color(255, 204, 204));
        } else {
            txtEmail.setBackground(new java.awt.Color(249, 250, 255));
        }
        lblErrorEmailR.setText(Validation.VerificadorEmail.mensaje);
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyPressed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        if (!Validation.VerificadorNombre.verify(txtNombre.getText())) {
            txtNombre.setBackground(new java.awt.Color(255, 204, 204));
        } else {
            txtNombre.setBackground(new java.awt.Color(249, 250, 255));
        }
        lblErrorNombre.setText(Validation.VerificadorNombre.mensaje);
    }//GEN-LAST:event_txtNombreKeyReleased

    private void btnCrearCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearCuentaActionPerformed
        try {
            String nombre = txtNombre.getText();
            String correo = txtEmail.getText();
            String password = nombre.toUpperCase() + "123";
            
            if (!Validation.VerificadorNombre.verify(nombre)) {
                txtNombre.setBackground(new java.awt.Color(255, 204, 204));
            } else {
                txtNombre.setBackground(new java.awt.Color(249, 250, 255));
            }
            if (!Validation.VerificadorEmail.verify(correo)) {
                txtEmail.setBackground(new java.awt.Color(255, 204, 204));
            } else {
                txtEmail.setBackground(new java.awt.Color(249, 250, 255));
            }
            lblErrorEmailR.setText(Validation.VerificadorEmail.mensaje);
            lblErrorNombre.setText(Validation.VerificadorNombre.mensaje);
            if (Validation.VerificadorNombre.verify(nombre) && Validation.VerificadorEmail.verify(correo)) {
                if (User.nuevoUsuario(nombre, correo, password, "Visitante", "Activo")) {
                    txtEmail.setBackground(new java.awt.Color(249, 250, 255));
                    lblErrorEmailR.setText("");
                    CurrentUser.idTipoUsuario = 3;
                    ingresar(nombre);
                } else {
                    if ("<html>Ya existe un usuario con la dirección de<br>correo electrónico.</html>".equals(User.mensajeError)) {
                        txtEmail.setBackground(new java.awt.Color(255, 204, 204));
                        lblErrorEmailR.setText(User.mensajeError);
                    } else {
                        JOptionPane.showMessageDialog(this, User.mensajeError);
                    }
                }
            }
            
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCrearCuentaActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        cambiarCardLayoutPanel("CrearCuenta");
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel210MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel210MouseClicked
        cambiarCardLayoutPanel("Principal");
    }//GEN-LAST:event_jLabel210MouseClicked

    private void lblAyudaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAyudaMouseClicked

        modal = new JDialog(this, "Ayuda", true);
        modal.getContentPane().add(pnlModalAyuda);
        modal.pack();
        modal.setLocationRelativeTo(null);
        modal.setVisible(true);
    }//GEN-LAST:event_lblAyudaMouseClicked

    private void btnCancelarModal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarModal1ActionPerformed
        modal.dispose();
    }//GEN-LAST:event_btnCancelarModal1ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        VisitorAndGuest clase1 = new VisitorAndGuest();
        clase1.setVisible(true);
        //Falta agregar el nombre o por lo menos enviarlo a la base
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isDigit(validar)) {
            getToolkit().beep();

            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar solo Letras");
        }
    }//GEN-LAST:event_txtNombreKeyTyped

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
            java.util.logging.Logger.getLogger(LoginPoint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginPoint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginPoint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPoint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPoint().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarModal1;
    private javax.swing.JButton btnCrearCuenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblAyuda;
    private javax.swing.JLabel lblColor;
    public static javax.swing.JLabel lblEdicion;
    private javax.swing.JLabel lblErrorEmailR;
    private javax.swing.JLabel lblErrorNombre;
    private javax.swing.JLabel lblImagenDerecha;
    private javax.swing.JPanel panelCardLayout;
    private javax.swing.JPanel pnlModalAyuda;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
