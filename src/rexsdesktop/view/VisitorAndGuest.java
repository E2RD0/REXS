/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ItemEvent;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import rexsdesktop.CurrentUser;
import rexsdesktop.controller.General;
import java.awt.Cursor;
import java.awt.event.ItemEvent;

import groovy.swing.factory.SwingBorderFactory;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

import javax.swing.JOptionPane;
import rexsdesktop.CurrentUser;
import javax.swing.SwingConstants;
import static rexsdesktop.controller.Projects.getNumProyectosFiltrados;
import rexsdesktop.controller.Sections;
import rexsdesktop.controller.Validation;
import rexsdesktop.modal.ModalModificarEstadoUsuario;
import rexsdesktop.modal.ModalModificarTipoUsuario;
import rexsdesktop.modal.ModalNuevaActividad;
import rexsdesktop.modal.ModalNuevaEspecialidad;
import rexsdesktop.modal.ModalNuevaSeccion;
import rexsdesktop.modal.ModalNuevoEstadoUsuario;
import rexsdesktop.modal.ModalNuevoNivel;
import rexsdesktop.modal.ModalNuevoProyecto;
import static rexsdesktop.modal.ModalNuevoProyecto.label;
import rexsdesktop.modal.ModalNuevoTipoUsuario;
import rexsdesktop.modal.ModalNuevoUsuario;
import rexsdesktop.model.Db;
import rexsdesktop.model.DbConnection;

import rexsdesktop.controller.Activities;
import rexsdesktop.controller.Projects;
import rexsdesktop.modal.ModalInformacionEspecialidades;
import rexsdesktop.modal.ModalNuevoProyecto;
import rexsdesktop.model.Db;

/**
 *
 * @author Carlos Herrera
 */
public class VisitorAndGuest extends javax.swing.JFrame {

    private static JDialog modal;
    private int especialidad = 0;
    public int color = 0;
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

    private ImageIcon iconDashboard = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconHome.png"));
    private ImageIcon iconDashboardActive = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconHomeActive.png"));
    private ImageIcon iconProyectos = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconProjects.png"));
    private ImageIcon iconProyectosActive = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconProjectsActive.png"));
    private ImageIcon iconUbicaciones = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconMap.png"));
    private ImageIcon iconUbicacionesActive = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconMapActive.png"));
    private ImageIcon iconActividades = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconCalendar.png"));
    private ImageIcon iconActividadesActive = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconCalendarActive.png"));

    private ImageIcon iconNegro = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/LogoNegroREXS.png"));
    private ImageIcon iconBlanco = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/LogoBlancoREXS.png"));
    private ImageIcon iconDia = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Day.png"));
    private ImageIcon iconNoche = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Night.png"));

    /**
     * Creates new form VisitorAndGuest
     */
    public VisitorAndGuest() {
        this.setExtendedState(this.MAXIMIZED_BOTH);
        initComponents();
        lblNombreUsuario.setText(CurrentUser.nombreCompleto);
        pnlActiveInicio.setBackground(bgNormal);
        pnlActiveProyectos.setBackground(bgNormal);
        pnlActiveUbicaciones.setBackground(bgNormal);
        pnlActiveActividades.setBackground(bgNormal);
        makeActiveMenuItem(btnInicio, pnlActiveInicio, lblInicio, "Inicio");
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
        panelFondo = new javax.swing.JPanel();
        panelCardLayout = new javax.swing.JPanel();
        panelInicio = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel9 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        panelUbicaciones = new javax.swing.JPanel();
        panelProyectos = new javax.swing.JPanel();
        pnlViewProyectos = new javax.swing.JPanel();
        jLabel214 = new javax.swing.JLabel();
        jLabel228 = new javax.swing.JLabel();
        jcNivel = new javax.swing.JComboBox<>();
        jcEspecialidad = new javax.swing.JComboBox<>();
        jLabel215 = new javax.swing.JLabel();
        jcSeccion = new javax.swing.JComboBox<>();
        jLabel229 = new javax.swing.JLabel();
        btnFiltrarLista1 = new javax.swing.JButton();
        jLabel230 = new javax.swing.JLabel();
        jsProyectos = new javax.swing.JScrollPane();
        cdProyectos = new javax.swing.JPanel();
        panelActividades = new javax.swing.JPanel();
        panelInnerActividades = new javax.swing.JPanel();
        jLabel109 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        lblFechaDia1 = new javax.swing.JLabel();
        jPanel29 = new RoundedPanel(90, new Color(213, 222, 255));
        lblCantidadActividades1 = new javax.swing.JLabel();
        lblDia1 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        lblFechaDia2 = new javax.swing.JLabel();
        jPanel30 = new RoundedPanel(90, new Color(213, 222, 255));
        lblCantidadActividades2 = new javax.swing.JLabel();
        lblDia2 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        lblFechaDia3 = new javax.swing.JLabel();
        jPanel40 = new RoundedPanel(90, new Color(213, 222, 255));
        lblCantidadActividades3 = new javax.swing.JLabel();
        lblDia3 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        lblFechaDia4 = new javax.swing.JLabel();
        jPanel42 = new RoundedPanel(90, new Color(213, 222, 255));
        lblCantidadActividades4 = new javax.swing.JLabel();
        lblDia4 = new javax.swing.JLabel();
        jPanel47 = new javax.swing.JPanel();
        lblFechaDia5 = new javax.swing.JLabel();
        jPanel48 = new RoundedPanel(90, new Color(213, 222, 255));
        lblCantidadActividades5 = new javax.swing.JLabel();
        lblDia5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        panelMenu = new javax.swing.JPanel();
        btnProyectos = new javax.swing.JPanel();
        pnlActiveProyectos = new javax.swing.JPanel();
        lblProyectos = new javax.swing.JLabel();
        btnUbicaciones = new javax.swing.JPanel();
        pnlActiveUbicaciones = new javax.swing.JPanel();
        lblUbicaciones = new javax.swing.JLabel();
        btnActividades = new javax.swing.JPanel();
        pnlActiveActividades = new javax.swing.JPanel();
        lblActividades = new javax.swing.JLabel();
        btnInicio = new javax.swing.JPanel();
        pnlActiveInicio = new javax.swing.JPanel();
        lblInicio = new javax.swing.JLabel();
        panelSuperior = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblAyuda = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        lblNombreUsuario = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JLabel();

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel9)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        pnlModalAyudaLayout.setVerticalGroup(
            pnlModalAyudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlModalAyudaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlModalAyudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(23, 23, 23)
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

        panelFondo.setBackground(new java.awt.Color(255, 255, 255));
        panelFondo.setPreferredSize(new java.awt.Dimension(1000, 600));
        panelFondo.setRequestFocusEnabled(false);

        panelCardLayout.setBackground(new java.awt.Color(0, 255, 204));
        panelCardLayout.setLayout(new java.awt.CardLayout());

        panelInicio.setBackground(new java.awt.Color(244, 246, 252));

        jLabel5.setFont(new java.awt.Font("Rubik Light", 0, 36)); // NOI18N
        jLabel5.setText("Oferta Académica");

        jSeparator1.setForeground(new java.awt.Color(164, 164, 164));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(148, 171, 255)));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Tercer Ciclo.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Rubik Light", 0, 18)); // NOI18N
        jLabel14.setText("<html> <p align=\"center\"> Tercer Ciclo </p> </html>");

        jLabel15.setFont(new java.awt.Font("Rubik Light", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(46, 91, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Ver más...");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(5, 5, 5))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(148, 171, 255)));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
        });

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Itec.png"))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Rubik Light", 0, 18)); // NOI18N
        jLabel17.setText("<html>\n<p align=\"center\">\n           ITEC\t\n</p>\n</html>");

        jLabel18.setFont(new java.awt.Font("Rubik Light", 0, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(46, 91, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Ver más...");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addGap(5, 5, 5))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(148, 171, 255)));
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel11MouseClicked(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Software.png"))); // NOI18N

        jLabel20.setFont(new java.awt.Font("Rubik Light", 0, 18)); // NOI18N
        jLabel20.setText("<html>\n<p align=\"center\">\nDesarrollo de Software\n</p>\n</html>");

        jLabel21.setFont(new java.awt.Font("Rubik Light", 0, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(46, 91, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Ver más...");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addGap(5, 5, 5))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(148, 171, 255)));
        jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel12MouseClicked(evt);
            }
        });

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Disenio.png"))); // NOI18N

        jLabel23.setFont(new java.awt.Font("Rubik Light", 0, 18)); // NOI18N
        jLabel23.setText("<html> <p align=\"center\"> Diseño Gráfico </p> </html>");

        jLabel24.setFont(new java.awt.Font("Rubik Light", 0, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(46, 91, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Ver más...");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(5, 5, 5))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(148, 171, 255)));
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel13MouseClicked(evt);
            }
        });

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Arquitectura.png"))); // NOI18N

        jLabel26.setFont(new java.awt.Font("Rubik Light", 0, 18)); // NOI18N
        jLabel26.setText("<html> <p align=\"center\"> Arquitectura </p> </html>");

        jLabel27.setFont(new java.awt.Font("Rubik Light", 0, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(46, 91, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Ver más...");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addGap(5, 5, 5))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(148, 171, 255)));
        jPanel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel14MouseClicked(evt);
            }
        });

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Auto.png"))); // NOI18N

        jLabel29.setFont(new java.awt.Font("Rubik Light", 0, 16)); // NOI18N
        jLabel29.setText("<html>\n<p align=\"center\">\nMantenimiento Automotriz\n</p>\n</html>");

        jLabel30.setFont(new java.awt.Font("Rubik Light", 0, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(46, 91, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Ver más...");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addGap(5, 5, 5))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(148, 171, 255)));
        jPanel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel15MouseClicked(evt);
            }
        });

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Eca.png"))); // NOI18N

        jLabel33.setFont(new java.awt.Font("Rubik Light", 0, 18)); // NOI18N
        jLabel33.setText("<html>\n<p align=\"center\">\nElectrónica\n</p>\n</html>");

        jLabel34.setFont(new java.awt.Font("Rubik Light", 0, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(46, 91, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Ver más...");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31)
                .addGap(5, 5, 5))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(148, 171, 255)));
        jPanel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel16MouseClicked(evt);
            }
        });

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Emca.png"))); // NOI18N

        jLabel36.setFont(new java.awt.Font("Rubik Light", 0, 15)); // NOI18N
        jLabel36.setText("<html>\n<p align=\"center\">\nElectromécanica\n</p>\n</html>");

        jLabel37.setFont(new java.awt.Font("Rubik Light", 0, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(46, 91, 255));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Ver más...");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jLabel35)
                .addGap(5, 5, 5))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(148, 171, 255)));
        jPanel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel17MouseClicked(evt);
            }
        });

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Contaduria.png"))); // NOI18N

        jLabel39.setFont(new java.awt.Font("Rubik Light", 0, 16)); // NOI18N
        jLabel39.setText("<html> <p align=\"center\"> Administrativo Contable </p> </html>");

        jLabel40.setFont(new java.awt.Font("Rubik Light", 0, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(46, 91, 255));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Ver más...");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel38)
                .addGap(5, 5, 5))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap(13, Short.MAX_VALUE)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelInicioLayout = new javax.swing.GroupLayout(panelInicio);
        panelInicio.setLayout(panelInicioLayout);
        panelInicioLayout.setHorizontalGroup(
            panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelInicioLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addGroup(panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addGroup(panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInicioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelInicioLayout.setVerticalGroup(
            panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInicioLayout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(8, 8, 8)
                .addGroup(panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelInicioLayout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelCardLayout.add(panelInicio, "Inicio");

        javax.swing.GroupLayout panelUbicacionesLayout = new javax.swing.GroupLayout(panelUbicaciones);
        panelUbicaciones.setLayout(panelUbicacionesLayout);
        panelUbicacionesLayout.setHorizontalGroup(
            panelUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1031, Short.MAX_VALUE)
        );
        panelUbicacionesLayout.setVerticalGroup(
            panelUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 551, Short.MAX_VALUE)
        );

        panelCardLayout.add(panelUbicaciones, "Ubicaciones");

        pnlViewProyectos.setBackground(new java.awt.Color(244, 246, 252));
        pnlViewProyectos.setPreferredSize(new java.awt.Dimension(808, 545));

        jLabel214.setFont(new java.awt.Font("Rubik Light", 0, 22)); // NOI18N
        jLabel214.setForeground(new java.awt.Color(46, 56, 77));
        jLabel214.setText("Proyectos");

        jLabel228.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel228.setForeground(new java.awt.Color(46, 56, 77));
        jLabel228.setText("Nivel");

        jcNivel.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jcNivel.setForeground(new java.awt.Color(135, 152, 173));
        jcNivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un nivel" }));
        jcNivel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true));
        jcNivel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcNivelItemStateChanged(evt);
            }
        });

        jcEspecialidad.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jcEspecialidad.setForeground(new java.awt.Color(135, 152, 173));
        jcEspecialidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una especialidad" }));
        jcEspecialidad.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true));

        jLabel215.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel215.setForeground(new java.awt.Color(46, 56, 77));
        jLabel215.setText("Especialidad");

        jcSeccion.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jcSeccion.setForeground(new java.awt.Color(135, 152, 173));
        jcSeccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una sección" }));
        jcSeccion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true));

        jLabel229.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel229.setForeground(new java.awt.Color(46, 56, 77));
        jLabel229.setText("Sección");

        btnFiltrarLista1.setBackground(new java.awt.Color(46, 91, 255));
        btnFiltrarLista1.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        btnFiltrarLista1.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltrarLista1.setText("Filtrar Lista");
        btnFiltrarLista1.setBorderPainted(false);
        btnFiltrarLista1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarLista1ActionPerformed(evt);
            }
        });

        jLabel230.setText("240 en total ");

        jsProyectos.setBackground(new java.awt.Color(244, 246, 252));
        jsProyectos.setBorder(null);

        cdProyectos.setBackground(new java.awt.Color(244, 246, 252));

        javax.swing.GroupLayout cdProyectosLayout = new javax.swing.GroupLayout(cdProyectos);
        cdProyectos.setLayout(cdProyectosLayout);
        cdProyectosLayout.setHorizontalGroup(
            cdProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        cdProyectosLayout.setVerticalGroup(
            cdProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jsProyectos.setViewportView(cdProyectos);

        javax.swing.GroupLayout pnlViewProyectosLayout = new javax.swing.GroupLayout(pnlViewProyectos);
        pnlViewProyectos.setLayout(pnlViewProyectosLayout);
        pnlViewProyectosLayout.setHorizontalGroup(
            pnlViewProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlViewProyectosLayout.createSequentialGroup()
                .addGroup(pnlViewProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlViewProyectosLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(pnlViewProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel214)
                            .addGroup(pnlViewProyectosLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel230, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addGroup(pnlViewProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel228)
                            .addComponent(jcNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addGroup(pnlViewProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel215)
                            .addComponent(jcEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addGroup(pnlViewProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel229)
                            .addComponent(jcSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(btnFiltrarLista1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jsProyectos))
                .addContainerGap())
        );
        pnlViewProyectosLayout.setVerticalGroup(
            pnlViewProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlViewProyectosLayout.createSequentialGroup()
                .addGroup(pnlViewProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlViewProyectosLayout.createSequentialGroup()
                        .addComponent(jLabel214)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel230))
                    .addGroup(pnlViewProyectosLayout.createSequentialGroup()
                        .addComponent(jLabel228)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlViewProyectosLayout.createSequentialGroup()
                        .addComponent(jLabel215)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnFiltrarLista1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlViewProyectosLayout.createSequentialGroup()
                        .addComponent(jLabel229)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jsProyectos, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelProyectosLayout = new javax.swing.GroupLayout(panelProyectos);
        panelProyectos.setLayout(panelProyectosLayout);
        panelProyectosLayout.setHorizontalGroup(
            panelProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlViewProyectos, javax.swing.GroupLayout.DEFAULT_SIZE, 1031, Short.MAX_VALUE)
        );
        panelProyectosLayout.setVerticalGroup(
            panelProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProyectosLayout.createSequentialGroup()
                .addComponent(pnlViewProyectos, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelCardLayout.add(panelProyectos, "Proyectos");

        panelActividades.setBackground(new java.awt.Color(244, 246, 252));

        panelInnerActividades.setBackground(new java.awt.Color(244, 246, 252));

        jLabel109.setFont(new java.awt.Font("Rubik Light", 0, 24)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(46, 56, 77));
        jLabel109.setText("Actividades");

        jPanel1.setBackground(new java.awt.Color(244, 246, 252));

        jPanel26.setBackground(new java.awt.Color(244, 246, 252));

        lblFechaDia1.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        lblFechaDia1.setForeground(new java.awt.Color(176, 186, 201));
        lblFechaDia1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFechaDia1.setText("(25/09/2019)");

        jPanel29.setBackground(new java.awt.Color(244, 246, 252));

        lblCantidadActividades1.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        lblCantidadActividades1.setForeground(new java.awt.Color(46, 91, 255));
        lblCantidadActividades1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantidadActividades1.setText("0");
        lblCantidadActividades1.setToolTipText("Cantidad de actividades");
        lblCantidadActividades1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblCantidadActividades1.setPreferredSize(new java.awt.Dimension(24, 24));
        lblCantidadActividades1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblCantidadActividades1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCantidadActividades1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        lblDia1.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        lblDia1.setForeground(new java.awt.Color(176, 186, 201));
        lblDia1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDia1.setText("MIÉRCOLES");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(lblDia1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lblFechaDia1)
                .addGap(3, 3, 3)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFechaDia1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblDia1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel27.setBackground(new java.awt.Color(244, 246, 252));

        lblFechaDia2.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        lblFechaDia2.setForeground(new java.awt.Color(176, 186, 201));
        lblFechaDia2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFechaDia2.setText("(26/09/2019)");

        jPanel30.setBackground(new java.awt.Color(244, 246, 252));

        lblCantidadActividades2.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        lblCantidadActividades2.setForeground(new java.awt.Color(46, 91, 255));
        lblCantidadActividades2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantidadActividades2.setText("0");
        lblCantidadActividades2.setToolTipText("Cantidad de actividades");
        lblCantidadActividades2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblCantidadActividades2.setPreferredSize(new java.awt.Dimension(24, 24));
        lblCantidadActividades2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblCantidadActividades2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCantidadActividades2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        lblDia2.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        lblDia2.setForeground(new java.awt.Color(176, 186, 201));
        lblDia2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDia2.setText("JUEVES");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(lblDia2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lblFechaDia2)
                .addGap(3, 3, 3)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFechaDia2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblDia2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel39.setBackground(new java.awt.Color(244, 246, 252));

        lblFechaDia3.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        lblFechaDia3.setForeground(new java.awt.Color(176, 186, 201));
        lblFechaDia3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFechaDia3.setText("(27/09/2019)");

        jPanel40.setBackground(new java.awt.Color(244, 246, 252));

        lblCantidadActividades3.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        lblCantidadActividades3.setForeground(new java.awt.Color(46, 91, 255));
        lblCantidadActividades3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantidadActividades3.setText("0");
        lblCantidadActividades3.setToolTipText("Cantidad de actividades");
        lblCantidadActividades3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblCantidadActividades3.setPreferredSize(new java.awt.Dimension(24, 24));
        lblCantidadActividades3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblCantidadActividades3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCantidadActividades3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        lblDia3.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        lblDia3.setForeground(new java.awt.Color(176, 186, 201));
        lblDia3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDia3.setText("VIERNES");

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addComponent(lblDia3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lblFechaDia3)
                .addGap(3, 3, 3)
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFechaDia3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblDia3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel41.setBackground(new java.awt.Color(244, 246, 252));

        lblFechaDia4.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        lblFechaDia4.setForeground(new java.awt.Color(176, 186, 201));
        lblFechaDia4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFechaDia4.setText("(28/09/2019)");

        jPanel42.setBackground(new java.awt.Color(244, 246, 252));

        lblCantidadActividades4.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        lblCantidadActividades4.setForeground(new java.awt.Color(46, 91, 255));
        lblCantidadActividades4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantidadActividades4.setText("0");
        lblCantidadActividades4.setToolTipText("Cantidad de actividades");
        lblCantidadActividades4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblCantidadActividades4.setPreferredSize(new java.awt.Dimension(24, 24));
        lblCantidadActividades4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblCantidadActividades4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCantidadActividades4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        lblDia4.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        lblDia4.setForeground(new java.awt.Color(176, 186, 201));
        lblDia4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDia4.setText("SÁBADO");

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addComponent(lblDia4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lblFechaDia4)
                .addGap(3, 3, 3)
                .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFechaDia4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblDia4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel47.setBackground(new java.awt.Color(244, 246, 252));

        lblFechaDia5.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        lblFechaDia5.setForeground(new java.awt.Color(176, 186, 201));
        lblFechaDia5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFechaDia5.setText("(29/09/2019)");

        jPanel48.setBackground(new java.awt.Color(244, 246, 252));

        lblCantidadActividades5.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        lblCantidadActividades5.setForeground(new java.awt.Color(46, 91, 255));
        lblCantidadActividades5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantidadActividades5.setText("0");
        lblCantidadActividades5.setToolTipText("Cantidad de actividades");
        lblCantidadActividades5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblCantidadActividades5.setPreferredSize(new java.awt.Dimension(24, 24));
        lblCantidadActividades5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblCantidadActividades5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCantidadActividades5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        lblDia5.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        lblDia5.setForeground(new java.awt.Color(176, 186, 201));
        lblDia5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDia5.setText("DOMINGO");

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addComponent(lblDia5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lblFechaDia5)
                .addGap(3, 3, 3)
                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFechaDia5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblDia5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel48, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setMinimumSize(new java.awt.Dimension(166, 323));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(100, 120));

        jPanel4.setBackground(new java.awt.Color(244, 246, 252));
        jPanel4.setMinimumSize(new java.awt.Dimension(0, 15));
        jPanel4.setLayout(new java.awt.GridLayout(0, 1, 10, 11));
        jScrollPane2.setViewportView(jPanel4);

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setMinimumSize(new java.awt.Dimension(166, 323));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(100, 120));

        jPanel5.setBackground(new java.awt.Color(244, 246, 252));
        jPanel5.setMinimumSize(new java.awt.Dimension(0, 15));
        jPanel5.setLayout(new java.awt.GridLayout(0, 1, 10, 11));
        jScrollPane3.setViewportView(jPanel5);

        jScrollPane4.setBorder(null);
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setMinimumSize(new java.awt.Dimension(166, 323));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(100, 120));

        jPanel6.setBackground(new java.awt.Color(244, 246, 252));
        jPanel6.setMinimumSize(new java.awt.Dimension(0, 15));
        jPanel6.setLayout(new java.awt.GridLayout(0, 1, 10, 11));
        jScrollPane4.setViewportView(jPanel6);

        jScrollPane5.setBorder(null);
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setMinimumSize(new java.awt.Dimension(166, 323));
        jScrollPane5.setPreferredSize(new java.awt.Dimension(100, 120));

        jPanel7.setBackground(new java.awt.Color(244, 246, 252));
        jPanel7.setMinimumSize(new java.awt.Dimension(0, 15));
        jPanel7.setLayout(new java.awt.GridLayout(0, 1, 10, 11));
        jScrollPane5.setViewportView(jPanel7);

        jScrollPane6.setBorder(null);
        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setMinimumSize(new java.awt.Dimension(166, 323));
        jScrollPane6.setPreferredSize(new java.awt.Dimension(100, 120));

        jPanel8.setBackground(new java.awt.Color(244, 246, 252));
        jPanel8.setMinimumSize(new java.awt.Dimension(0, 15));
        jPanel8.setLayout(new java.awt.GridLayout(0, 1, 10, 11));
        jScrollPane6.setViewportView(jPanel8);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout panelInnerActividadesLayout = new javax.swing.GroupLayout(panelInnerActividades);
        panelInnerActividades.setLayout(panelInnerActividadesLayout);
        panelInnerActividadesLayout.setHorizontalGroup(
            panelInnerActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelInnerActividadesLayout.createSequentialGroup()
                .addComponent(jLabel109)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelInnerActividadesLayout.setVerticalGroup(
            panelInnerActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInnerActividadesLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel109)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelActividadesLayout = new javax.swing.GroupLayout(panelActividades);
        panelActividades.setLayout(panelActividadesLayout);
        panelActividadesLayout.setHorizontalGroup(
            panelActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelActividadesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelInnerActividades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelActividadesLayout.setVerticalGroup(
            panelActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelActividadesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelInnerActividades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelCardLayout.add(panelActividades, "Actividades");

        panelMenu.setBackground(new java.awt.Color(255, 255, 255));

        btnProyectos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProyectosMouseClicked(evt);
            }
        });

        pnlActiveProyectos.setBackground(new java.awt.Color(46, 91, 255));
        pnlActiveProyectos.setPreferredSize(new java.awt.Dimension(3, 40));

        javax.swing.GroupLayout pnlActiveProyectosLayout = new javax.swing.GroupLayout(pnlActiveProyectos);
        pnlActiveProyectos.setLayout(pnlActiveProyectosLayout);
        pnlActiveProyectosLayout.setHorizontalGroup(
            pnlActiveProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlActiveProyectosLayout.setVerticalGroup(
            pnlActiveProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        lblProyectos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProyectos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconProjects.png"))); // NOI18N

        javax.swing.GroupLayout btnProyectosLayout = new javax.swing.GroupLayout(btnProyectos);
        btnProyectos.setLayout(btnProyectosLayout);
        btnProyectosLayout.setHorizontalGroup(
            btnProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlActiveProyectos, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
            .addComponent(lblProyectos, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
        );
        btnProyectosLayout.setVerticalGroup(
            btnProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnProyectosLayout.createSequentialGroup()
                .addComponent(lblProyectos, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlActiveProyectos, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnUbicaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUbicacionesMouseClicked(evt);
            }
        });

        pnlActiveUbicaciones.setBackground(new java.awt.Color(46, 91, 255));
        pnlActiveUbicaciones.setPreferredSize(new java.awt.Dimension(3, 40));

        javax.swing.GroupLayout pnlActiveUbicacionesLayout = new javax.swing.GroupLayout(pnlActiveUbicaciones);
        pnlActiveUbicaciones.setLayout(pnlActiveUbicacionesLayout);
        pnlActiveUbicacionesLayout.setHorizontalGroup(
            pnlActiveUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlActiveUbicacionesLayout.setVerticalGroup(
            pnlActiveUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        lblUbicaciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUbicaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconMap.png"))); // NOI18N

        javax.swing.GroupLayout btnUbicacionesLayout = new javax.swing.GroupLayout(btnUbicaciones);
        btnUbicaciones.setLayout(btnUbicacionesLayout);
        btnUbicacionesLayout.setHorizontalGroup(
            btnUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlActiveUbicaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
            .addComponent(lblUbicaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
        );
        btnUbicacionesLayout.setVerticalGroup(
            btnUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnUbicacionesLayout.createSequentialGroup()
                .addComponent(lblUbicaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlActiveUbicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnActividades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActividadesMouseClicked(evt);
            }
        });

        pnlActiveActividades.setBackground(new java.awt.Color(46, 91, 255));
        pnlActiveActividades.setPreferredSize(new java.awt.Dimension(3, 40));

        javax.swing.GroupLayout pnlActiveActividadesLayout = new javax.swing.GroupLayout(pnlActiveActividades);
        pnlActiveActividades.setLayout(pnlActiveActividadesLayout);
        pnlActiveActividadesLayout.setHorizontalGroup(
            pnlActiveActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlActiveActividadesLayout.setVerticalGroup(
            pnlActiveActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        lblActividades.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblActividades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconCalendar.png"))); // NOI18N

        javax.swing.GroupLayout btnActividadesLayout = new javax.swing.GroupLayout(btnActividades);
        btnActividades.setLayout(btnActividadesLayout);
        btnActividadesLayout.setHorizontalGroup(
            btnActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlActiveActividades, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
            .addComponent(lblActividades, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
        );
        btnActividadesLayout.setVerticalGroup(
            btnActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnActividadesLayout.createSequentialGroup()
                .addComponent(lblActividades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlActiveActividades, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInicioMouseExited(evt);
            }
        });

        pnlActiveInicio.setBackground(new java.awt.Color(46, 91, 255));
        pnlActiveInicio.setPreferredSize(new java.awt.Dimension(3, 40));

        javax.swing.GroupLayout pnlActiveInicioLayout = new javax.swing.GroupLayout(pnlActiveInicio);
        pnlActiveInicio.setLayout(pnlActiveInicioLayout);
        pnlActiveInicioLayout.setHorizontalGroup(
            pnlActiveInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlActiveInicioLayout.setVerticalGroup(
            pnlActiveInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        lblInicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconHomeActive.png"))); // NOI18N

        javax.swing.GroupLayout btnInicioLayout = new javax.swing.GroupLayout(btnInicio);
        btnInicio.setLayout(btnInicioLayout);
        btnInicioLayout.setHorizontalGroup(
            btnInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlActiveInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
            .addComponent(lblInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
        );
        btnInicioLayout.setVerticalGroup(
            btnInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnInicioLayout.createSequentialGroup()
                .addComponent(lblInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlActiveInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnUbicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnProyectos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnActividades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnProyectos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUbicaciones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActividades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        panelSuperior.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/LogoNegroREXS.png"))); // NOI18N

        lblAyuda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/info (1).png"))); // NOI18N
        lblAyuda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAyudaMouseClicked(evt);
            }
        });

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Day.png"))); // NOI18N
        jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel32MouseClicked(evt);
            }
        });

        lblNombreUsuario.setFont(new java.awt.Font("Rubik Medium", 0, 18)); // NOI18N
        lblNombreUsuario.setForeground(new java.awt.Color(46, 91, 255));
        lblNombreUsuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNombreUsuario.setText("Visitante");
        lblNombreUsuario.setIconTextGap(14);

        btnCerrarSesion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/signout.png"))); // NOI18N
        btnCerrarSesion.setToolTipText("Cerrar sesión");
        btnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelSuperiorLayout = new javax.swing.GroupLayout(panelSuperior);
        panelSuperior.setLayout(panelSuperiorLayout);
        panelSuperiorLayout.setHorizontalGroup(
            panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(300, 300, 300)
                .addComponent(lblNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelSuperiorLayout.setVerticalGroup(
            panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelSuperiorLayout.createSequentialGroup()
                .addGroup(panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSuperiorLayout.createSequentialGroup()
                        .addContainerGap(16, Short.MAX_VALUE)
                        .addComponent(lblNombreUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(lblAyuda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelCardLayout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelSuperior, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoLayout.createSequentialGroup()
                .addComponent(panelSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(panelCardLayout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 1031, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setColorInterfaz() {
        Color superior;
        Color menu;
        Color btn;
        Color fondo;
        Color Blue;
        Color panel;
        Color normal;
        Color letrasNormales;
        int color2 = this.color;

        if (color2 == 0) {
            superior = darkSuperior;
            menu = darkMenu;
            btn = darkbtn;
            fondo = darkfondo;
            Blue = darkBlue;
            panel = darkPanel;
            normal = Color.WHITE;
            bgNormal = fondo;
            letrasNormales = Color.WHITE;

            jLabel32.setIcon(iconNoche);
            jLabel1.setIcon(iconBlanco);
            color = 1;
        } else {
            panelSuperior.setBackground(new Color(255, 255, 255));
            panelMenu.setBackground(new Color(255, 255, 255));
            superior = new Color(255, 255, 255);
            menu = new Color(255, 255, 255);
            btn = new Color(255, 255, 255);
            fondo = new Color(244, 246, 252);
            Blue = new Color(46, 56, 77);
            panel = new Color(255, 255, 255);
            normal = new Color(46, 56, 77);
            letrasNormales = new Color(0, 0, 0);
            bgNormal = new Color(255, 255, 255);
            jLabel32.setIcon(iconDia);
            jLabel1.setIcon(iconNegro);

            color = 0;
        }
        //Barras
        panelFondo.setBackground(superior);
        panelSuperior.setBackground(superior);
        panelMenu.setBackground(menu);
        jSeparator1.setBackground(fondo);
        jSeparator2.setBackground(fondo);

        lblProyectos.setBackground(bgNormal);

        //Paneles
        panelInicio.setBackground(fondo);
        panelUbicaciones.setBackground(fondo);
        panelProyectos.setBackground(fondo);
        panelActividades.setBackground(fondo);

        //Botones
        btnInicio.setBackground(btn);
        btnProyectos.setBackground(btn);
        btnActividades.setBackground(btn);
        btnUbicaciones.setBackground(btn);

        //Inicio
        jPanel9.setBackground(panel);
        jPanel10.setBackground(panel);
        jPanel11.setBackground(panel);
        jPanel12.setBackground(panel);
        jPanel13.setBackground(panel);
        jPanel14.setBackground(panel);
        jPanel15.setBackground(panel);
        jPanel16.setBackground(panel);
        jPanel17.setBackground(panel);

        jLabel5.setForeground(Blue);
        jLabel14.setForeground(letrasNormales);
        jLabel17.setForeground(letrasNormales);
        jLabel20.setForeground(letrasNormales);
        jLabel23.setForeground(letrasNormales);
//        jLabel24.setForeground(letrasNormales);
        jLabel26.setForeground(letrasNormales);
        jLabel29.setForeground(letrasNormales);
        jLabel33.setForeground(letrasNormales);
        jLabel36.setForeground(letrasNormales);
        jLabel39.setForeground(letrasNormales);

        //Projects
        pnlViewProyectos.setBackground(panel);
        panelProyectos.setBackground(panel);
        cdProyectos.setBackground(panel);
        
        jLabel214.setForeground(letrasNormales);
        jLabel215.setForeground(letrasNormales);
        jLabel228.setForeground(letrasNormales);
        jLabel229.setForeground(letrasNormales);
        jLabel230.setForeground(letrasNormales);
        //Actividades
        jLabel109.setForeground(Blue);
        panelActividades.setBackground(fondo);
        panelInnerActividades.setBackground(fondo);
        jPanel1.setBackground(panel);
        jPanel4.setBackground(panel);
        jPanel5.setBackground(panel);
        jPanel6.setBackground(panel);
        jPanel7.setBackground(panel);
        jPanel8.setBackground(panel);
        jPanel26.setBackground(panel);
        jPanel27.setBackground(panel);
        jPanel29.setBackground(panel);
        jPanel30.setBackground(panel);
        jPanel39.setBackground(panel);
        jPanel40.setBackground(panel);
        jPanel41.setBackground(panel);
        jPanel41.setBackground(panel);
        jPanel42.setBackground(panel);
        jPanel47.setBackground(panel);
        jPanel48.setBackground(panel);

        jPanel4.setBackground(panel);
    }

    /**
     * Este Método sirve para hacer el Side Menu interactivo.
     *
     * @param btn utilizado para cambiar el color del botón
     * @param activeInd utilizado para cambiar el color del panel.
     * @param lbl utilizado para cambiar el icono.
     * @param img utilizado para cambiar la imagen.
     */
    private void makeActiveMenuItem(JPanel btn, JPanel activeInd, JLabel lbl, String img) {
        CardLayout cl = (CardLayout) (panelCardLayout.getLayout());
//        if (color == 1) {
//            bgNormal = darkbtn;
//        } else if (color == 0) {
//            bgNormal = new Color(255, 255, 255);
//        }

//        if (pnlActiveAnaliticas.getBackground() == colorActive) {
//            btnAnaliticas.setBackground(bgNormal);
//            pnlActiveAnaliticas.setBackground(bgNormal);
//            lblAnaliticas.setIcon(iconAnaliticas);
//            lblAnaliticas.setForeground(colorNormal);
//        } else 
        if (pnlActiveInicio.getBackground() == colorActive) {
            btnInicio.setBackground(bgNormal);
            pnlActiveInicio.setBackground(bgNormal);
//            lblProyectos.setIcon(iconProyectos);
            lblInicio.setForeground(colorNormal);
        } else if (pnlActiveProyectos.getBackground() == colorActive) {
            btnProyectos.setBackground(bgNormal);
            pnlActiveProyectos.setBackground(bgNormal);
//            lblProyectos.setIcon(iconProyectos);
            lblProyectos.setForeground(colorNormal);
        } else if (pnlActiveUbicaciones.getBackground() == colorActive) {
            btnUbicaciones.setBackground(bgNormal);
            pnlActiveUbicaciones.setBackground(bgNormal);
//            lblUbicaciones.setIcon(iconUbicaciones);
            lblUbicaciones.setForeground(colorNormal);
        } else if (pnlActiveActividades.getBackground() == colorActive) {
            btnActividades.setBackground(bgNormal);
            pnlActiveActividades.setBackground(bgNormal);
//            lblActividades.setIcon(iconActividades);
            lblActividades.setForeground(colorNormal);
        }
        btn.setBackground(bgActive);
        activeInd.setBackground(colorActive);
        lbl.setForeground(colorActive);
        switch (img) {
            case "Dashboard":
                lbl.setIcon(iconDashboardActive);
                lblActividades.setIcon(iconActividades);
                lblProyectos.setIcon(iconProyectos);
                lblUbicaciones.setIcon(iconUbicaciones);
                break;
            case "Proyectos":
                lbl.setIcon(iconProyectosActive);
                lblInicio.setIcon(iconDashboard);
                lblActividades.setIcon(iconActividades);
                lblUbicaciones.setIcon(iconUbicaciones);
                break;
            case "Ubicaciones":
                lbl.setIcon(iconUbicacionesActive);
                lblInicio.setIcon(iconDashboard);
                lblActividades.setIcon(iconActividades);
                lblProyectos.setIcon(iconProyectos);
                break;
            case "Actividades":
                lbl.setIcon(iconActividadesActive);
                lblInicio.setIcon(iconDashboard);
                lblProyectos.setIcon(iconProyectos);
                lblUbicaciones.setIcon(iconUbicaciones);
                break;
        }
        cl.show(panelCardLayout, img);
    }

    public static void cargarActividades() {
        jPanel4.removeAll();
        jPanel5.removeAll();
        jPanel6.removeAll();
        jPanel7.removeAll();
        jPanel8.removeAll();

        Activities actividades = new Activities();
        final String dia1 = "actividadesFechaInicio";
        final String dia2 = "actividadesDia2";
        final String dia3 = "actividadesDia3";
        final String dia4 = "actividadesDia4";
        final String dia5 = "actividadesFechaFin";

        String fecha1 = actividades.getDia(dia1);
        String fecha2 = actividades.getDia(dia2);
        String fecha3 = actividades.getDia(dia3);
        String fecha4 = actividades.getDia(dia4);
        String fecha5 = actividades.getDia(dia5);

        //Obtener nombres de dia de la fecha
        lblDia1.setText(actividades.getNombreDia(fecha1).toUpperCase());
        lblDia2.setText(actividades.getNombreDia(fecha2).toUpperCase());
        lblDia3.setText(actividades.getNombreDia(fecha3).toUpperCase());
        lblDia4.setText(actividades.getNombreDia(fecha4).toUpperCase());
        lblDia5.setText(actividades.getNombreDia(fecha5).toUpperCase());

        //Colocar fechas en los JLabels
        lblFechaDia1.setText("(" + fecha1 + ")");
        lblFechaDia2.setText("(" + fecha2 + ")");
        lblFechaDia3.setText("(" + fecha3 + ")");
        lblFechaDia4.setText("(" + fecha4 + ")");
        lblFechaDia5.setText("(" + fecha5 + ")");

        //Fechas de inicio y de fin de la Expotécnica
        String fecha1Inicio = fecha1 + " 00:00:00";
        String fecha2Inicio = fecha2 + " 00:00:00";
        String fecha3Inicio = fecha3 + " 00:00:00";
        String fecha4Inicio = fecha4 + " 00:00:00";
        String fecha5Inicio = fecha5 + " 00:00:00";

        String fecha1Fin = fecha1 + " 23:59:59";
        String fecha2Fin = fecha2 + " 23:59:59";
        String fecha3Fin = fecha3 + " 23:59:59";
        String fecha4Fin = fecha4 + " 23:59:59";
        String fecha5Fin = fecha5 + " 23:59:59";

        int contador = 1;
        actividades.CrearPanelesActividadesPoint(jPanel4, fecha1Inicio, CurrentUser.edicionExpotecnica, fecha1Fin, contador);
        lblCantidadActividades1.setText(String.valueOf(actividades.getCantidadDia1()));
        contador++;

        actividades.CrearPanelesActividadesPoint(jPanel5, fecha2Inicio, CurrentUser.edicionExpotecnica, fecha2Fin, contador);
        lblCantidadActividades2.setText(String.valueOf(actividades.getCantidadDia1()));
        contador++;

        actividades.CrearPanelesActividadesPoint(jPanel6, fecha3Inicio, CurrentUser.edicionExpotecnica, fecha3Fin, contador);
        lblCantidadActividades3.setText(String.valueOf(actividades.getCantidadDia1()));
        contador++;

        actividades.CrearPanelesActividadesPoint(jPanel7, fecha4Inicio, CurrentUser.edicionExpotecnica, fecha4Fin, contador);
        lblCantidadActividades4.setText(String.valueOf(actividades.getCantidadDia1()));
        contador++;

        actividades.CrearPanelesActividadesPoint(jPanel8, fecha5Inicio, CurrentUser.edicionExpotecnica, fecha5Fin, contador);
        lblCantidadActividades5.setText(String.valueOf(actividades.getCantidadDia1()));
//        actividades.resetearIdioma();
    }

    public void cargarProyectos() {
        int canti = 0;
        cdProyectos.removeAll();
        if (label != null) {
            pnlViewProyectos.remove(label);
        }
        try {
            Db db = new Db();
            General.getEdicion();
            db.NumProyectos(CurrentUser.edicionExpotecnica);
            db.obtenerNivel();
            for (int i = 0; i < db.SNnivel.size(); i++) {
                jcNivel.addItem(db.SNnivel.get(i));
            }

            jcEspecialidad.disable();
            jcSeccion.disable();
            cdProyectos.setLayout(new GridLayout(0, 2, 15, 20));
            try {
                Projects cargarPaneles = new Projects();
                cargarPaneles.CrearPanelesProyectosPoint(cdProyectos, CurrentUser.edicionExpotecnica);
            } catch (Exception e) {
                System.out.println("hi " + e.getMessage());
            }

            jsProyectos.setBorder(null);
            if (color == 0) {

                jsProyectos.setBackground(new Color(244, 246, 252));
                cdProyectos.setBackground(new Color(244, 246, 252));

            } else {
                jsProyectos.setBackground(darkfondo);
                cdProyectos.setBackground(darkfondo);

            }
            canti = db.getCantidadProyecto();
            switch (canti) {
                case 0:
                    jsProyectos.disable();
                    cdProyectos.disable();
                    label = new JLabel("<html>" + "No existen proyectos, por favor ingrese un proyecto" + "</html>", SwingConstants.CENTER);
                    label.setBounds(170, 200, 350, 50);
                    label.setFont(new java.awt.Font("Rubik Medium", 0, 12));
                    label.setForeground(new Color(46, 56, 77));
                    pnlViewProyectos.add(label);
                    break;
                case 1:
                    jsProyectos.setBounds(0, 70, 377, 120);
                    cdProyectos.setLayout(null);
                    break;
                case 2:
                    jsProyectos.setBounds(0, 70, 808, 120);
                    break;
                case 3:
                    jsProyectos.setBounds(0, 70, 808, 260);
                    break;
                case 4:
                    jsProyectos.setBounds(0, 70, 808, 260);
                    break;
                case 5:
                    jsProyectos.setBounds(0, 70, 808, 363);
                    break;
                default:
                    jsProyectos.setBounds(0, 70, 808, 363);
            }
            pnlViewProyectos.add(jsProyectos);
        } catch (Exception e) {
            System.out.println("Admin " + e.toString());
        } finally {
            jLabel230.setText(String.valueOf(canti) + " en total");
        }

    }


    private void btnCancelarModal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarModal1ActionPerformed
        modal.dispose();
    }//GEN-LAST:event_btnCancelarModal1ActionPerformed

    private void lblAyudaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAyudaMouseClicked
        modal = new JDialog(this, "Ayuda", true);
        modal.getContentPane().add(pnlModalAyuda);
        modal.pack();
        modal.setLocationRelativeTo(null);
        modal.setVisible(true);
    }//GEN-LAST:event_lblAyudaMouseClicked

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked
        setColorInterfaz();
    }//GEN-LAST:event_jLabel32MouseClicked

    private void btnCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseClicked
        General.agregarBitacora("CerrarSesion", CurrentUser.idUsuario);
        CurrentUser.clear();
        LoginPoint login = new LoginPoint();
        this.setVisible(false);
        login.setLocationRelativeTo(null);
        login.setDefaultCloseOperation(EXIT_ON_CLOSE);
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesionMouseClicked

    private void btnProyectosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProyectosMouseClicked
        jcNivel.removeAllItems();
        jcNivel.addItem("Seleccione un nivel");
        jcEspecialidad.removeAllItems();
        jcEspecialidad.addItem("Seleccione una especialidad");
        cargarProyectos();

        pnlViewProyectos.repaint();
        pnlViewProyectos.revalidate();
        cdProyectos.repaint();
        cdProyectos.revalidate();
        makeActiveMenuItem(btnProyectos, pnlActiveProyectos, lblProyectos, "Proyectos");
    }//GEN-LAST:event_btnProyectosMouseClicked

    private void btnUbicacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUbicacionesMouseClicked
        makeActiveMenuItem(btnUbicaciones, pnlActiveUbicaciones, lblUbicaciones, "Ubicaciones");
    }//GEN-LAST:event_btnUbicacionesMouseClicked

    private void btnActividadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActividadesMouseClicked
        cargarActividades();
        makeActiveMenuItem(btnActividades, pnlActiveActividades, lblActividades, "Actividades");
    }//GEN-LAST:event_btnActividadesMouseClicked

    private void btnInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseClicked
        makeActiveMenuItem(btnInicio, pnlActiveInicio, lblInicio, "Inicio");
    }//GEN-LAST:event_btnInicioMouseClicked

    private void btnInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseEntered
        if (pnlActiveInicio.getBackground() != colorActive) {
            lblInicio.setIcon(iconDashboardActive);
            lblInicio.setForeground(colorActive);
        }
    }//GEN-LAST:event_btnInicioMouseEntered

    private void btnInicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseExited
        if (pnlActiveInicio.getBackground() != colorActive) {
            lblInicio.setIcon(iconDashboard);
            lblInicio.setForeground(colorNormal);
        }
    }//GEN-LAST:event_btnInicioMouseExited

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        modal = new JDialog(this, "Información", true);
        especialidad = 1;
        ModalInformacionEspecialidades modalo = new ModalInformacionEspecialidades(modal, especialidad);
        modal.getContentPane().add(modalo);
        modal.setResizable(false);
        modal.pack();
        modal.setLocationRelativeTo(null);
        modal.setVisible(true);
    }//GEN-LAST:event_jPanel9MouseClicked

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseClicked
        modal = new JDialog(this, "Información", true);
        especialidad = 4;
        ModalInformacionEspecialidades modalo = new ModalInformacionEspecialidades(modal, especialidad);
        modal.getContentPane().add(modalo);
        modal.setResizable(false);
        modal.pack();
        modal.setLocationRelativeTo(null);
        modal.setVisible(true);
    }//GEN-LAST:event_jPanel11MouseClicked

    private void jPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseClicked
        modal = new JDialog(this, "Información", true);
        especialidad = 2;
        ModalInformacionEspecialidades modalo = new ModalInformacionEspecialidades(modal, especialidad);
        modal.getContentPane().add(modalo);
        modal.setResizable(false);
        modal.pack();
        modal.setLocationRelativeTo(null);
        modal.setVisible(true);
    }//GEN-LAST:event_jPanel12MouseClicked

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked
        modal = new JDialog(this, "Información", true);
        especialidad = 3;
        ModalInformacionEspecialidades modalo = new ModalInformacionEspecialidades(modal, especialidad);
        modal.getContentPane().add(modalo);
        modal.setResizable(false);
        modal.pack();
        modal.setLocationRelativeTo(null);
        modal.setVisible(true);
    }//GEN-LAST:event_jPanel13MouseClicked

    private void jPanel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel15MouseClicked
        modal = new JDialog(this, "Información", true);
        especialidad = 5;
        ModalInformacionEspecialidades modalo = new ModalInformacionEspecialidades(modal, especialidad);
        modal.getContentPane().add(modalo);
        modal.setResizable(false);
        modal.pack();
        modal.setLocationRelativeTo(null);
        modal.setVisible(true);
    }//GEN-LAST:event_jPanel15MouseClicked

    private void jPanel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseClicked
        modal = new JDialog(this, "Información", true);
        especialidad = 6;
        ModalInformacionEspecialidades modalo = new ModalInformacionEspecialidades(modal, especialidad);
        modal.getContentPane().add(modalo);
        modal.setResizable(false);
        modal.pack();
        modal.setLocationRelativeTo(null);
        modal.setVisible(true);
    }//GEN-LAST:event_jPanel16MouseClicked

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        modal = new JDialog(this, "Información", true);
        especialidad = 7;
        ModalInformacionEspecialidades modalo = new ModalInformacionEspecialidades(modal, especialidad);
        modal.getContentPane().add(modalo);
        modal.setResizable(false);
        modal.pack();
        modal.setLocationRelativeTo(null);
        modal.setVisible(true);
    }//GEN-LAST:event_jPanel10MouseClicked

    private void jPanel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14MouseClicked
        modal = new JDialog(this, "Información", true);
        especialidad = 8;
        ModalInformacionEspecialidades modalo = new ModalInformacionEspecialidades(modal, especialidad);
        modal.getContentPane().add(modalo);
        modal.setResizable(false);
        modal.pack();
        modal.setLocationRelativeTo(null);
        modal.setVisible(true);
    }//GEN-LAST:event_jPanel14MouseClicked

    private void jPanel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseClicked
        modal = new JDialog(this, "Información", true);
        especialidad = 9;
        ModalInformacionEspecialidades modalo = new ModalInformacionEspecialidades(modal, especialidad);
        modal.getContentPane().add(modalo);
        modal.setResizable(false);
        modal.pack();
        modal.setLocationRelativeTo(null);
        modal.setVisible(true);
    }//GEN-LAST:event_jPanel17MouseClicked

    private void jcNivelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcNivelItemStateChanged
        Db db = new Db();
        db.obtenerEspecialidad();
        jcSeccion.removeAllItems();
        jcSeccion.addItem("Seleccione una sección");
        try {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                if (jcNivel.getSelectedIndex() > 0) {
                    if (jcNivel.getSelectedItem().equals(String.valueOf("Primer año")) || jcNivel.getSelectedItem().equals(String.valueOf("Segundo año")) || jcNivel.getSelectedItem().equals(String.valueOf("Tercer año"))) {
                        jcEspecialidad.enable();
                        jcEspecialidad.removeAllItems();
                        jcSeccion.removeAllItems();
                        for (int i = 0; i < db.SNespecialidad.size(); i++) {
                            jcEspecialidad.addItem(db.SNespecialidad.get(i));
                        }
                        jcEspecialidad.removeItem(String.valueOf("Basica"));
                        db.obtenerSeccion(jcNivel.getSelectedItem().toString(), jcEspecialidad.getSelectedItem().toString());
                        jcSeccion.enable();
                        for (int i = 0; i < db.SNseccion.size(); i++) {
                            jcSeccion.addItem(db.SNseccion.get(i));
                        }
                    } else {
                        jcEspecialidad.removeAllItems();
                        jcEspecialidad.disable();
                        jcSeccion.removeAllItems();

                        for (int i = 0; i < db.SNespecialidad.size(); i++) {
                            jcEspecialidad.addItem(db.SNespecialidad.get(i));
                        }
                        jcEspecialidad.setSelectedItem("Basica");

                        db.obtenerSeccion(jcNivel.getSelectedItem().toString(), jcEspecialidad.getSelectedItem().toString());
                        jcSeccion.enable();
                        for (int i = 0; i < db.SNseccion.size(); i++) {
                            jcSeccion.addItem(db.SNseccion.get(i));
                        }
                    }

                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jcNivelItemStateChanged

    private void btnFiltrarLista1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarLista1ActionPerformed

        btnFiltrarLista1.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (jcNivel.getSelectedIndex() > 0) {
            if (jcSeccion.getItemCount() != 0) {

                if (label != null) {
                    pnlViewProyectos.remove(label);
                }
                cdProyectos.removeAll();
                pnlViewProyectos.repaint();
                pnlViewProyectos.revalidate();
                General.getEdicion();
                Projects p = getNumProyectosFiltrados(jcNivel.getSelectedItem().toString(), jcEspecialidad.getSelectedItem().toString(),
                        jcSeccion.getSelectedItem().toString(), CurrentUser.edicionExpotecnica);
                cdProyectos.setLayout(new GridLayout(0, 2, 15, 20));
                Projects cargar = new Projects();
                cargar.FiltroPanelesProyectos(cdProyectos, jcNivel.getSelectedItem().toString(), jcEspecialidad.getSelectedItem().toString(), jcSeccion.getSelectedItem().toString(), CurrentUser.edicionExpotecnica);
                jsProyectos.setBorder(null);
                if (color == 0) {

                    jsProyectos.setBackground(new Color(244, 246, 252));
                    cdProyectos.setBackground(new Color(244, 246, 252));

                } else {
                    jsProyectos.setBackground(new Color(52, 48, 57));
                    cdProyectos.setBackground(new Color(52, 48, 57));

                }
                switch (p.getNumProyectosFiltrados) {
                    case 0:
                        label = new JLabel("<html>" + "No existen proyectos, por favor ingrese un proyecto" + "</html>", SwingConstants.CENTER);
                        label.setBounds(170, 200, 350, 50);
                        label.setFont(new java.awt.Font("Rubik Medium", 0, 12));
                        label.setForeground(new Color(46, 56, 77));
                        pnlViewProyectos.add(label);
                        pnlViewProyectos.repaint();
                        pnlViewProyectos.revalidate();
                        cdProyectos.repaint();
                        cdProyectos.revalidate();
                        break;
                    case 1:
                        jsProyectos.setBounds(0, 70, 377, 120);
                        cdProyectos.setLayout(null);
                        pnlViewProyectos.repaint();
                        pnlViewProyectos.revalidate();
                        cdProyectos.repaint();
                        cdProyectos.revalidate();
                        break;
                    case 2:
                        jsProyectos.setBounds(0, 70, 808, 120);
                        pnlViewProyectos.repaint();
                        pnlViewProyectos.revalidate();
                        cdProyectos.repaint();
                        cdProyectos.revalidate();

                        break;
                    case 3:
                        jsProyectos.setBounds(0, 70, 808, 260);
                        pnlViewProyectos.repaint();
                        pnlViewProyectos.revalidate();
                        cdProyectos.repaint();
                        cdProyectos.revalidate();

                        break;
                    case 4:
                        jsProyectos.setBounds(0, 70, 808, 260);
                        pnlViewProyectos.repaint();
                        pnlViewProyectos.revalidate();
                        cdProyectos.repaint();
                        cdProyectos.revalidate();

                        break;
                    case 5:
                        jsProyectos.setBounds(0, 70, 808, 363);
                        pnlViewProyectos.repaint();
                        pnlViewProyectos.revalidate();
                        cdProyectos.repaint();
                        cdProyectos.revalidate();

                        break;
                    default:
                        jsProyectos.setBounds(0, 70, 808, 363);
                        pnlViewProyectos.repaint();
                        pnlViewProyectos.revalidate();
                        cdProyectos.repaint();
                        cdProyectos.revalidate();

                }
                pnlViewProyectos.add(jsProyectos);
                pnlViewProyectos.repaint();
                pnlViewProyectos.revalidate();
                cdProyectos.repaint();
                cdProyectos.revalidate();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione el nivel, especialidad y la sección que desea buscar.");

            }

        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione el nivel, especialidad y la sección que desea buscar.");
        }
        btnFiltrarLista1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnFiltrarLista1ActionPerformed

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
            java.util.logging.Logger.getLogger(VisitorAndGuest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisitorAndGuest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisitorAndGuest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisitorAndGuest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisitorAndGuest().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnActividades;
    private javax.swing.JButton btnCancelarModal1;
    private javax.swing.JLabel btnCerrarSesion;
    private javax.swing.JButton btnFiltrarLista1;
    private javax.swing.JPanel btnInicio;
    private javax.swing.JPanel btnProyectos;
    private javax.swing.JPanel btnUbicaciones;
    public static javax.swing.JPanel cdProyectos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel214;
    private javax.swing.JLabel jLabel215;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel228;
    private javax.swing.JLabel jLabel229;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel230;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel39;
    public static javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    public static javax.swing.JPanel jPanel5;
    public static javax.swing.JPanel jPanel6;
    public static javax.swing.JPanel jPanel7;
    public static javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public static javax.swing.JComboBox<String> jcEspecialidad;
    public static javax.swing.JComboBox<String> jcNivel;
    public static javax.swing.JComboBox<String> jcSeccion;
    public static javax.swing.JScrollPane jsProyectos;
    private javax.swing.JLabel lblActividades;
    private javax.swing.JLabel lblAyuda;
    public static javax.swing.JLabel lblCantidadActividades1;
    public static javax.swing.JLabel lblCantidadActividades2;
    public static javax.swing.JLabel lblCantidadActividades3;
    public static javax.swing.JLabel lblCantidadActividades4;
    public static javax.swing.JLabel lblCantidadActividades5;
    private static javax.swing.JLabel lblDia1;
    private static javax.swing.JLabel lblDia2;
    private static javax.swing.JLabel lblDia3;
    private static javax.swing.JLabel lblDia4;
    private static javax.swing.JLabel lblDia5;
    private static javax.swing.JLabel lblFechaDia1;
    private static javax.swing.JLabel lblFechaDia2;
    private static javax.swing.JLabel lblFechaDia3;
    private static javax.swing.JLabel lblFechaDia4;
    private static javax.swing.JLabel lblFechaDia5;
    private javax.swing.JLabel lblInicio;
    public static javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JLabel lblProyectos;
    private javax.swing.JLabel lblUbicaciones;
    private javax.swing.JPanel panelActividades;
    private javax.swing.JPanel panelCardLayout;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JPanel panelInicio;
    private javax.swing.JPanel panelInnerActividades;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelProyectos;
    private javax.swing.JPanel panelSuperior;
    private javax.swing.JPanel panelUbicaciones;
    private javax.swing.JPanel pnlActiveActividades;
    private javax.swing.JPanel pnlActiveInicio;
    private javax.swing.JPanel pnlActiveProyectos;
    private javax.swing.JPanel pnlActiveUbicaciones;
    private javax.swing.JPanel pnlModalAyuda;
    public static javax.swing.JPanel pnlViewProyectos;
    // End of variables declaration//GEN-END:variables
}
