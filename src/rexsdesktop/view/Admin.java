/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ColorUIResource;
import rexsdesktop.CurrentUser;
import rexsdesktop.controller.Activities;
import rexsdesktop.controller.General;
import rexsdesktop.controller.Locations;
import rexsdesktop.controller.Projects;
import rexsdesktop.controller.User;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import rexsdesktop.controller.Sections;
import rexsdesktop.controller.Validation;
import rexsdesktop.modal.ModalModificarEstadoUsuario;
import rexsdesktop.modal.ModalModificarSecciones;
import rexsdesktop.modal.ModalModificarTipoUsuario;
import rexsdesktop.modal.ModalNuevaActividad;
import rexsdesktop.modal.ModalNuevaEspecialidad;
import rexsdesktop.modal.ModalNuevaSeccion;
import rexsdesktop.modal.ModalNuevoEstadoUsuario;
import rexsdesktop.modal.ModalNuevoNivel;
import rexsdesktop.modal.ModalNuevoProyecto;
import rexsdesktop.modal.ModalNuevoTipoUsuario;
import rexsdesktop.modal.ModalNuevoUsuario;
import rexsdesktop.model.DbConnection;

/**
 *
 * Este formulario contiene la interfaz del Administrador, donde puede
 * administrar las actividades, proyectos, usuarios, ubicaciones, secciones,
 * especialidades, además de visualizar gráficos y analiticas referentes a la
 * información almacenada en el sistema.
 *
 * @author Eduardo
 * @version 1.2
 */
public class Admin extends javax.swing.JFrame {

    /**
     * Creates new form Admin
     */
    private static JDialog modal;
    private Date dia2;
    private Date dia3;
    private Date dia4;
    private int color = 0;

    public Admin() {
        /*Scrollbar Look and Feel*/
        UIManager.put("ScrollBar.width", 8);
        UIManager.put("ScrollBar.thumb", new ColorUIResource(new Color(189, 189, 189)));
        UIManager.put("ScrollBar.thumbDarkShadow", new ColorUIResource(new Color(189, 189, 189)));
        UIManager.put("ScrollBar.thumbShadow", new ColorUIResource(new Color(189, 189, 189)));
        UIManager.put("ScrollBar.thumbHighlight", new ColorUIResource(new Color(189, 189, 189)));
        UIManager.put("ScrollBarUI", "rexsdesktop.view.MyScrollbarUI");
        /*END Scrollbar Look and Feel*/
        initComponents();
        /*Form Stuff*/
        jLabel80.setText(CurrentUser.nombreCompleto);
        setIconImage(new ImageIcon(getClass().getResource("resources/Icono REXS.png")).getImage());
        this.setTitle("REXS");
        //Permisos de usuarios
        if (CurrentUser.idTipoUsuario == 1) {
            btnAjustesActividades.setVisible(true);
            btnEliminarActividades.setVisible(true);
            jPanel43.setVisible(true);
            jPanel130.setVisible(true);
            jPanel143.setVisible(true);
            jLabel81.setVisible(true);
            jPanel2.setPreferredSize(new Dimension(944, 1450));
        } else {
            btnAjustesActividades.setVisible(false);
            btnEliminarActividades.setVisible(false);
            jPanel130.setVisible(false);
            jPanel43.setVisible(false);
            jPanel143.setVisible(false);
            jPanel144.setVisible(false);
            jLabel81.setVisible(false);
            jPanel2.setPreferredSize(new Dimension(944, 1000));
        }

        /*Projects*/
        Projects cargarPaneles = new Projects();
        cargarPaneles.CrearPanelesProyectos(cdProyectos);

        /*User*/
        User cargar = new User();
        cargar.CrearPanelesUsuarios(jPanel1);
        cargar.getCantidadUsuarios();
        cantidadesUsuarios();
        /*Especialidad*/
        Sections CargarEspecialidad = new Sections();
        CargarEspecialidad.CrearPanelesEspecialidades(jPanel18);
        /*Nivel*/
        Sections CargarNivel = new Sections();
        CargarNivel.CrearPanelesNivel(jPanel17);
        /*Sección*/
        Sections CargarSeccion = new Sections();
        CargarSeccion.CrearPanelesSecciones(jPanel24);
        /*ComboBox*/
        User Conexion = new User();
        jComboBox1.setModel(Conexion.obtenerTipoUsuario());
        jComboBox2.setModel(Conexion.obtenerEstadoUsuario());

        /*Menu*/
        btnMenu.setContentAreaFilled(false);
        btnMenu.setFocusPainted(false);
        pnlActiveInicio.setBackground(bgNormal);
        pnlActiveAnaliticas.setBackground(bgNormal);
        pnlActiveUsuarios.setBackground(bgNormal);
        pnlActiveProyectos.setBackground(bgNormal);
        pnlActiveUbicaciones.setBackground(bgNormal);
        pnlActiveActividades.setBackground(bgNormal);
        pnlActiveAjustes.setBackground(bgNormal);
        makeActiveMenuItem(btnInicio, pnlActiveInicio, lblDashboard, "Dashboard");
        /*END Menu*/
 /*Scrollbar movement*/
        pnlAjustes.getVerticalScrollBar().setPreferredSize(new Dimension(15, Integer.MAX_VALUE));
        pnlAjustes.getVerticalScrollBar().setUnitIncrement(16);
        pnlDashboard.getVerticalScrollBar().setPreferredSize(new Dimension(15, Integer.MAX_VALUE));
        pnlDashboard.getVerticalScrollBar().setUnitIncrement(16);
        jpnlTableUsuarios.getVerticalScrollBar().setUnitIncrement(10);
        //jPanel112.getVerticalScrollBar().setPreferredSize(new Dimension(6, Integer.MAX_VALUE));
        //jPanel112.getVerticalScrollBar().setUnitIncrement(10);
        jpnlDia2.getVerticalScrollBar().setPreferredSize(new Dimension(6, Integer.MAX_VALUE));
        jpnlDia2.getVerticalScrollBar().setUnitIncrement(10);
        jpnlDia3.getVerticalScrollBar().setPreferredSize(new Dimension(6, Integer.MAX_VALUE));
        jpnlDia3.getVerticalScrollBar().setUnitIncrement(10);
        jpnlDia4.getVerticalScrollBar().setPreferredSize(new Dimension(6, Integer.MAX_VALUE));
        jpnlDia4.getVerticalScrollBar().setUnitIncrement(10);
        jpnlDia5.getVerticalScrollBar().setPreferredSize(new Dimension(6, Integer.MAX_VALUE));
        jpnlDia5.getVerticalScrollBar().setUnitIncrement(10);
        /*END Scrollbar movement*/
 /*Datos de usuario en ajustes*/
        loadAjustes();
        /*Datos de usuario en ajustes*/
 /*Activities*/
//        cargarActividades();
    }

    private void cantidadesUsuarios() {
        User cargar = new User();
        jLUsuarioTotal.setText(String.valueOf((cargar.getCantidadUsuarios())));
        jLUsuarioActivos.setText(String.valueOf((cargar.getCantidadUsuariosActivos())));
    }

    private void resetearModalUsuario() {
        txtEmailUsuarioModal.setText("");
        txtNombreUsuarioModal.setText("");
        txtClaveUsuarioModal.setText("");
        cbxTipoUsuarioModal.setSelectedIndex(0);
        cbxEstadoUsuarioModal.setSelectedIndex(0);
    }
    //final JDialog modalUsuario = new JDialog(this, "Prueba", true);
    private ImageIcon iconDashboard = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconHome.png"));
    private ImageIcon iconDashboardActive = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconHomeActive.png"));
    private ImageIcon iconAnaliticas = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconAnalytics.png"));
    private ImageIcon iconAnaliticasActive = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconAnalyticsActive.png"));
    private ImageIcon iconUsuarios = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconUsers.png"));
    private ImageIcon iconUsuariosActive = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconUsersActive.png"));
    private ImageIcon iconProyectos = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconProjects.png"));
    private ImageIcon iconProyectosActive = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconProjectsActive.png"));
    private ImageIcon iconUbicaciones = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconMap.png"));
    private ImageIcon iconUbicacionesActive = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconMapActive.png"));
    private ImageIcon iconActividades = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconCalendar.png"));
    private ImageIcon iconActividadesActive = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconCalendarActive.png"));
    private ImageIcon iconAjustes = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconSettings.png"));
    private ImageIcon iconAjustesActive = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconSettingsActive.png"));
    private ImageIcon iconNegro = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/LogoNegroREXS.png"));
    private ImageIcon iconBlanco = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/LogoBlancoREXS.png"));
    private ImageIcon iconDia = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Day.png"));
    private ImageIcon iconNoche = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Night.png"));

    Color colorActive = new Color(46, 91, 255);
    Color colorNormal = new Color(176, 186, 201);
    Color bgActive = new Color(238, 242, 255);
    Color bgNormal = new Color(255, 255, 255);
    Color darkSuperior = new Color(18, 20, 18);
    Color darkMenu = new Color(30, 31, 36);
    Color darkbtn = new Color(41, 41, 41);
    Color darkfondo = new Color(52, 48, 57);
    Color darkBlue = new Color(46, 91, 255);
    Color darkPanel = new Color(37, 37, 37);
    Color darknormal = new Color(46, 56, 77);

    /**
     * Este Método sirve para hacer el Side Menu interactivo.
     *
     * @param btn utilizado para cambiar el color del botón
     * @param activeInd utilizado para cambiar el color del panel.
     * @param lbl utilizado para cambiar el icono.
     * @param img utilizado para cambiar la imagen.
     */
    private void makeActiveMenuItem(JPanel btn, JPanel activeInd, JLabel lbl, String img) {
        CardLayout cl = (CardLayout) (pnlCardLayoutAdmin.getLayout());
        if (color == 1) {
            bgNormal = darkbtn;
        } else if (color == 0) {
            bgNormal = new Color(255, 255, 255);
        }

        if (pnlActiveInicio.getBackground() == colorActive) {
            btnInicio.setBackground(bgNormal);
            pnlActiveInicio.setBackground(bgNormal);
            lblDashboard.setIcon(iconDashboard);
            lblDashboard.setForeground(colorNormal);
        } else if (pnlActiveAnaliticas.getBackground() == colorActive) {
            btnAnaliticas.setBackground(bgNormal);
            pnlActiveAnaliticas.setBackground(bgNormal);
            lblAnaliticas.setIcon(iconAnaliticas);
            lblAnaliticas.setForeground(colorNormal);
        } else if (pnlActiveUsuarios.getBackground() == colorActive) {
            btnUsuarios.setBackground(bgNormal);
            pnlActiveUsuarios.setBackground(bgNormal);
            lblUsuarios.setIcon(iconUsuarios);
            lblUsuarios.setForeground(colorNormal);
        } else if (pnlActiveProyectos.getBackground() == colorActive) {
            btnProyectos.setBackground(bgNormal);
            pnlActiveProyectos.setBackground(bgNormal);
            lblProyectos.setIcon(iconProyectos);
            lblProyectos.setForeground(colorNormal);
        } else if (pnlActiveUbicaciones.getBackground() == colorActive) {
            btnUbicaciones.setBackground(bgNormal);
            pnlActiveUbicaciones.setBackground(bgNormal);
            lblUbicaciones.setIcon(iconUbicaciones);
            lblUbicaciones.setForeground(colorNormal);
        } else if (pnlActiveActividades.getBackground() == colorActive) {
            btnActividades.setBackground(bgNormal);
            pnlActiveActividades.setBackground(bgNormal);
            lblActividades.setIcon(iconActividades);
            lblActividades.setForeground(colorNormal);
        } else if (pnlActiveAjustes.getBackground() == colorActive) {
            btnAjustes.setBackground(bgNormal);
            pnlActiveAjustes.setBackground(bgNormal);
            lblAjustes.setIcon(iconAjustes);
            lblAjustes.setForeground(colorNormal);
        }
        btn.setBackground(bgActive);
        activeInd.setBackground(colorActive);
        lbl.setForeground(colorActive);
        switch (img) {
            case "Dashboard":
                lbl.setIcon(iconDashboardActive);
                break;
            case "Analiticas":
                lbl.setIcon(iconAnaliticasActive);
                break;
            case "Usuarios":
                lbl.setIcon(iconUsuariosActive);
                break;
            case "Proyectos":
                lbl.setIcon(iconProyectosActive);
                break;
            case "Ubicaciones":
                lbl.setIcon(iconUbicacionesActive);
                break;
            case "Actividades":
                lbl.setIcon(iconActividadesActive);
                break;
            case "Ajustes":
                lbl.setIcon(iconAjustesActive);
                break;
        }
        cl.show(pnlCardLayoutAdmin, img);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modalUsuario = new javax.swing.JDialog();
        pnlModalNuevoUsuario = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnCancelarModal = new javax.swing.JButton();
        btnAceptarModal = new javax.swing.JButton();
        txtNombreUsuarioModal = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        txtClaveUsuarioModal = new javax.swing.JPasswordField();
        jLabel69 = new javax.swing.JLabel();
        txtEmailUsuarioModal = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        cbxTipoUsuarioModal = new javax.swing.JComboBox<>();
        cbxEstadoUsuarioModal = new javax.swing.JComboBox<>();
        jLabel92 = new javax.swing.JLabel();
        pnlModalAjustesActividades = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnAceptarModalFechas = new javax.swing.JButton();
        btnCancelarModal1 = new javax.swing.JButton();
        dateFechaInicio = new com.toedter.calendar.JDateChooser();
        dateFechaFin = new com.toedter.calendar.JDateChooser();
        jLabel96 = new javax.swing.JLabel();
        cbEdicionExpotecnica = new javax.swing.JComboBox<>();
        modalAjustesActividades = new javax.swing.JDialog();
        buttonGroupTablasCatalogo = new javax.swing.ButtonGroup();
        buttonGroupReportes = new javax.swing.ButtonGroup();
        pnlMenu = new javax.swing.JPanel();
        btnMenu = new javax.swing.JButton();
        btnUsuarios = new javax.swing.JPanel();
        lblUsuarios = new javax.swing.JLabel();
        pnlActiveUsuarios = new javax.swing.JPanel();
        btnAnaliticas = new javax.swing.JPanel();
        lblAnaliticas = new javax.swing.JLabel();
        pnlActiveAnaliticas = new javax.swing.JPanel();
        btnProyectos = new javax.swing.JPanel();
        lblProyectos = new javax.swing.JLabel();
        pnlActiveProyectos = new javax.swing.JPanel();
        btnUbicaciones = new javax.swing.JPanel();
        lblUbicaciones = new javax.swing.JLabel();
        pnlActiveUbicaciones = new javax.swing.JPanel();
        btnActividades = new javax.swing.JPanel();
        lblActividades = new javax.swing.JLabel();
        pnlActiveActividades = new javax.swing.JPanel();
        btnAjustes = new javax.swing.JPanel();
        lblAjustes = new javax.swing.JLabel();
        pnlActiveAjustes = new javax.swing.JPanel();
        btnInicio = new javax.swing.JPanel();
        pnlActiveInicio = new javax.swing.JPanel();
        lblDashboard = new javax.swing.JLabel();
        pnlBackground = new javax.swing.JPanel();
        pnlSuperior = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        pnlCardLayoutAdmin = new javax.swing.JPanel();
        pnlDashboard = new javax.swing.JScrollPane();
        jPanel115 = new javax.swing.JPanel();
        jPanel116 = new javax.swing.JPanel();
        jPanel117 = new javax.swing.JPanel();
        jLabel285 = new javax.swing.JLabel();
        jLabel286 = new javax.swing.JLabel();
        jLabel252 = new javax.swing.JLabel("<html>Con Ricaldone Expotécnica System puedes gestionar distinta información referentes al evento.</html>");
        jLabel294 = new javax.swing.JLabel();
        jLabel295 = new javax.swing.JLabel();
        lblEdicion = new javax.swing.JLabel();
        btnAjustesActividades = new javax.swing.JLabel();
        jPanel118 = new javax.swing.JPanel();
        jLabel253 = new javax.swing.JLabel();
        jLabel287 = new javax.swing.JLabel();
        jLabel288 = new javax.swing.JLabel();
        jLabel254 = new javax.swing.JLabel();
        jPanel125 = new javax.swing.JPanel();
        jLabel289 = new javax.swing.JLabel();
        jLabel290 = new javax.swing.JLabel();
        jLabel291 = new javax.swing.JLabel();
        jLabel292 = new javax.swing.JLabel();
        jPanel126 = new javax.swing.JPanel();
        jLabel250 = new javax.swing.JLabel();
        jLabel251 = new javax.swing.JLabel();
        jPanel127 = new javax.swing.JPanel();
        jPanel128 = new javax.swing.JPanel();
        jLabel293 = new javax.swing.JLabel();
        jPanel135 = new javax.swing.JPanel();
        jLabel306 = new javax.swing.JLabel();
        jLabel307 = new javax.swing.JLabel();
        jLabel308 = new javax.swing.JLabel();
        jPanel136 = new RoundedPanel(90, new Color(140, 140, 140));
        jLabel309 = new javax.swing.JLabel();
        jLabel310 = new javax.swing.JLabel();
        jPanel137 = new javax.swing.JPanel();
        jLabel311 = new javax.swing.JLabel();
        jLabel312 = new javax.swing.JLabel();
        jLabel313 = new javax.swing.JLabel();
        jPanel138 = new RoundedPanel(90, new Color(140, 140, 140));
        jLabel314 = new javax.swing.JLabel();
        jLabel315 = new javax.swing.JLabel();
        jPanel139 = new javax.swing.JPanel();
        jLabel316 = new javax.swing.JLabel();
        jLabel317 = new javax.swing.JLabel();
        jLabel318 = new javax.swing.JLabel();
        jPanel140 = new RoundedPanel(90, new Color(140, 140, 140));
        jLabel319 = new javax.swing.JLabel();
        jLabel320 = new javax.swing.JLabel();
        jPanel141 = new javax.swing.JPanel();
        jLabel321 = new javax.swing.JLabel();
        jLabel322 = new javax.swing.JLabel();
        jLabel323 = new javax.swing.JLabel();
        jPanel142 = new RoundedPanel(90, new Color(140, 140, 140));
        jLabel324 = new javax.swing.JLabel();
        jLabel325 = new javax.swing.JLabel();
        jPanel129 = new javax.swing.JPanel();
        jLabel298 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        btnFiltrarLista3 = new javax.swing.JButton();
        jPanel130 = new javax.swing.JPanel();
        jLabel300 = new javax.swing.JLabel();
        checkUsuarios = new javax.swing.JCheckBox();
        checkProyectos = new javax.swing.JCheckBox();
        checkVotaciones = new javax.swing.JCheckBox();
        checkActividades = new javax.swing.JCheckBox();
        checkUbicaciones = new javax.swing.JCheckBox();
        btnBackup = new javax.swing.JButton();
        jLabel70 = new javax.swing.JLabel();
        checkTodo = new javax.swing.JCheckBox();
        pnlAnaliticas = new javax.swing.JPanel();
        jPanel145 = new javax.swing.JPanel();
        jLabel266 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jrbVotaciones = new javax.swing.JRadioButton();
        jrbBitacoras = new javax.swing.JRadioButton();
        jrbUsuarios = new javax.swing.JRadioButton();
        jrbProyectos = new javax.swing.JRadioButton();
        btnReporte = new javax.swing.JButton();
        jPanel146 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel150 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel151 = new javax.swing.JPanel();
        jLabel90 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel152 = new javax.swing.JPanel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        pnlUsuarios = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLUsuarioActivos = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLUsuarioTotal = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txtBusquedaFiltro = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnAgregarUsuario = new javax.swing.JButton();
        btnFiltrarLista = new javax.swing.JButton();
        jpnlTableUsuarios = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        pnlProyectos = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cdProyectos = new javax.swing.JPanel();
        jLabel214 = new javax.swing.JLabel();
        btnAgregarEspecialidad1 = new javax.swing.JButton();
        jLabel228 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel215 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel229 = new javax.swing.JLabel();
        btnFiltrarLista1 = new javax.swing.JButton();
        jLabel230 = new javax.swing.JLabel();
        pnlUbicaciones = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        btnAgregarNivel = new javax.swing.JButton();
        btnAgregarEspecialidad = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        btnModificarSeccionModal = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel17 = new javax.swing.JPanel();
        pnlSeccion = new javax.swing.JPanel();
        jPanel113 = new javax.swing.JPanel();
        jLabel209 = new javax.swing.JLabel();
        btnAgregarSeccion = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel24 = new javax.swing.JPanel();
        jLabel210 = new javax.swing.JLabel();
        pnlAjustes = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtAjustesEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtAjustesContraA = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        txtAjustesNombre = new javax.swing.JTextField();
        txtAjustesContraN = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        txtAjustesContraNC = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        btnActualizarPerfil = new javax.swing.JButton();
        lblAErrorEmail = new javax.swing.JLabel();
        lblAErrorNombre = new javax.swing.JLabel();
        lblAErrorContra = new javax.swing.JLabel();
        lblAErrorContraN = new javax.swing.JLabel();
        lblAErrorContraNC = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        btnCambiarFotoPerfil = new javax.swing.JButton();
        btnEliminarFotoPerfil = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        lblFotoPerfil = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        checkCombinar = new javax.swing.JCheckBox();
        lblAjustesBackup = new javax.swing.JLabel();
        jPanel143 = new javax.swing.JPanel();
        jLabel264 = new javax.swing.JLabel();
        txtAPIKey = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtVenueID = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btnLoadPlaces = new javax.swing.JButton();
        lblAErrorPlaces = new javax.swing.JLabel();
        jPanel144 = new javax.swing.JPanel();
        jLabel265 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jrbEstado = new javax.swing.JRadioButton();
        jrbTipo = new javax.swing.JRadioButton();
        jrbCriterio = new javax.swing.JRadioButton();
        jrbAccion = new javax.swing.JRadioButton();
        btnTablasCatalogo = new javax.swing.JButton();
        pnlActividades = new javax.swing.JPanel();
        pnlInnerActividades = new javax.swing.JPanel();
        jLabel109 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        btnAgregarActividad = new javax.swing.JButton();
        jpnlDia2 = new javax.swing.JScrollPane();
        jPanel12 = new javax.swing.JPanel();
        jpnlDia3 = new javax.swing.JScrollPane();
        jPanel13 = new javax.swing.JPanel();
        jpnlDia4 = new javax.swing.JScrollPane();
        jPanel14 = new javax.swing.JPanel();
        jpnlDia5 = new javax.swing.JScrollPane();
        jPanel15 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        lblFechaDia1 = new javax.swing.JLabel();
        jPanel28 = new RoundedPanel(90, new Color(213, 222, 255));
        lblCantidadActividades1 = new javax.swing.JLabel();
        lblDia1 = new javax.swing.JLabel();
        jPanel53 = new javax.swing.JPanel();
        lblFechaDia2 = new javax.swing.JLabel();
        jPanel54 = new RoundedPanel(90, new Color(232, 221, 255));
        lblCantidadActividades2 = new javax.swing.JLabel();
        lblDia2 = new javax.swing.JLabel();
        jPanel55 = new javax.swing.JPanel();
        lblFechaDia3 = new javax.swing.JLabel();
        jPanel56 = new RoundedPanel(90, new Color(204, 243, 247));
        lblCantidadActividades3 = new javax.swing.JLabel();
        lblDia3 = new javax.swing.JLabel();
        jPanel57 = new javax.swing.JPanel();
        lblFechaDia4 = new javax.swing.JLabel();
        jPanel58 = new RoundedPanel(90, new Color(254, 243, 215));
        lblCantidadActividades4 = new javax.swing.JLabel();
        lblDia4 = new javax.swing.JLabel();
        jPanel59 = new javax.swing.JPanel();
        lblFechaDia5 = new javax.swing.JLabel();
        jPanel60 = new RoundedPanel(90, new Color(214, 238, 213));
        lblCantidadActividades5 = new javax.swing.JLabel();
        lblDia5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        btnEliminarActividades = new javax.swing.JButton();

        javax.swing.GroupLayout modalUsuarioLayout = new javax.swing.GroupLayout(modalUsuario.getContentPane());
        modalUsuario.getContentPane().setLayout(modalUsuarioLayout);
        modalUsuarioLayout.setHorizontalGroup(
            modalUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        modalUsuarioLayout.setVerticalGroup(
            modalUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pnlModalNuevoUsuario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(193, 193, 193)));
        jPanel19.setPreferredSize(new java.awt.Dimension(130, 130));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/profilePicture.png"))); // NOI18N
        jPanel19.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, -1, -1));

        jButton3.setBackground(new java.awt.Color(238, 238, 238));
        jButton3.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jButton3.setForeground(new java.awt.Color(107, 107, 107));
        jButton3.setText("Cambiar Foto");
        jButton3.setBorderPainted(false);

        jButton4.setBackground(new java.awt.Color(238, 238, 238));
        jButton4.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jButton4.setForeground(new java.awt.Color(107, 107, 107));
        jButton4.setText("Eliminar Foto");
        jButton4.setBorderPainted(false);

        jSeparator1.setForeground(new java.awt.Color(164, 164, 164));

        btnCancelarModal.setBackground(new java.awt.Color(247, 214, 218));
        btnCancelarModal.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        btnCancelarModal.setForeground(new java.awt.Color(214, 54, 73));
        btnCancelarModal.setText("Cancelar");
        btnCancelarModal.setBorderPainted(false);

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

        txtNombreUsuarioModal.setBackground(new java.awt.Color(249, 250, 255));
        txtNombreUsuarioModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtNombreUsuarioModal.setForeground(new java.awt.Color(46, 56, 77));
        txtNombreUsuarioModal.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));

        jLabel67.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(176, 186, 201));
        jLabel67.setText("NOMBRE COMPLETO");

        jLabel68.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(176, 186, 201));
        jLabel68.setText("CONTRASEÑA");

        txtClaveUsuarioModal.setBackground(new java.awt.Color(249, 250, 255));
        txtClaveUsuarioModal.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtClaveUsuarioModal.setForeground(new java.awt.Color(46, 56, 77));
        txtClaveUsuarioModal.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtClaveUsuarioModal.setEchoChar('\u2022');

        jLabel69.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(176, 186, 201));
        jLabel69.setText("CORREO ELECTRÓNICO");

        txtEmailUsuarioModal.setBackground(new java.awt.Color(249, 250, 255));
        txtEmailUsuarioModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtEmailUsuarioModal.setForeground(new java.awt.Color(46, 56, 77));
        txtEmailUsuarioModal.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));

        jLabel91.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(176, 186, 201));
        jLabel91.setText("TIPO DE USUARIO");

        cbxTipoUsuarioModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        cbxTipoUsuarioModal.setForeground(new java.awt.Color(46, 56, 77));
        cbxTipoUsuarioModal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxTipoUsuarioModal.setPreferredSize(new java.awt.Dimension(56, 27));

        cbxEstadoUsuarioModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        cbxEstadoUsuarioModal.setForeground(new java.awt.Color(46, 56, 77));
        cbxEstadoUsuarioModal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel92.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(176, 186, 201));
        jLabel92.setText("ESTADO DE USUARIO");

        javax.swing.GroupLayout pnlModalNuevoUsuarioLayout = new javax.swing.GroupLayout(pnlModalNuevoUsuario);
        pnlModalNuevoUsuario.setLayout(pnlModalNuevoUsuarioLayout);
        pnlModalNuevoUsuarioLayout.setHorizontalGroup(
            pnlModalNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlModalNuevoUsuarioLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlModalNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(pnlModalNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel68)
                    .addComponent(jLabel67)
                    .addGroup(pnlModalNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(pnlModalNuevoUsuarioLayout.createSequentialGroup()
                            .addGroup(pnlModalNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel91)
                                .addComponent(cbxTipoUsuarioModal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlModalNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel92)
                                .addComponent(cbxEstadoUsuarioModal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(txtClaveUsuarioModal)
                        .addComponent(txtNombreUsuarioModal, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel69, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtEmailUsuarioModal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlModalNuevoUsuarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        pnlModalNuevoUsuarioLayout.setVerticalGroup(
            pnlModalNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlModalNuevoUsuarioLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlModalNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlModalNuevoUsuarioLayout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlModalNuevoUsuarioLayout.createSequentialGroup()
                        .addComponent(jLabel67)
                        .addGap(1, 1, 1)
                        .addComponent(txtNombreUsuarioModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel69)
                        .addGap(1, 1, 1)
                        .addComponent(txtEmailUsuarioModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel68)
                        .addGap(1, 1, 1)
                        .addComponent(txtClaveUsuarioModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addGroup(pnlModalNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlModalNuevoUsuarioLayout.createSequentialGroup()
                                .addComponent(jLabel91)
                                .addGap(1, 1, 1)
                                .addComponent(cbxTipoUsuarioModal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlModalNuevoUsuarioLayout.createSequentialGroup()
                                .addComponent(jLabel92)
                                .addGap(1, 1, 1)
                                .addComponent(cbxEstadoUsuarioModal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(22, 22, 22)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlModalNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel78.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(176, 186, 201));
        jLabel78.setText("FECHA DE INICIO DE EXPOTÉCNICA");

        jLabel79.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(176, 186, 201));
        jLabel79.setText("FECHA FIN DE EXPOTÉCNICA");

        jSeparator2.setForeground(new java.awt.Color(164, 164, 164));

        btnAceptarModalFechas.setBackground(new java.awt.Color(213, 222, 255));
        btnAceptarModalFechas.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        btnAceptarModalFechas.setForeground(new java.awt.Color(46, 91, 255));
        btnAceptarModalFechas.setText("Aceptar");
        btnAceptarModalFechas.setToolTipText("Aceptar los cambios");
        btnAceptarModalFechas.setBorderPainted(false);
        btnAceptarModalFechas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarModalFechasActionPerformed(evt);
            }
        });

        btnCancelarModal1.setBackground(new java.awt.Color(247, 214, 218));
        btnCancelarModal1.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        btnCancelarModal1.setForeground(new java.awt.Color(214, 54, 73));
        btnCancelarModal1.setText("Cancelar");
        btnCancelarModal1.setBorderPainted(false);

        dateFechaInicio.setToolTipText("Ingresar la fecha de inicio de la Expotécnica");
        dateFechaInicio.setDateFormatString("yyyy-MM-dd HH:mm:ss");
        dateFechaInicio.setPreferredSize(new java.awt.Dimension(137, 28));
        dateFechaInicio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateFechaInicioPropertyChange(evt);
            }
        });

        dateFechaFin.setToolTipText("Fecha de finalización de la Expotécnica");
        dateFechaFin.setDateFormatString("yyyy-MM-dd HH:mm:ss");
        dateFechaFin.setEnabled(false);
        dateFechaFin.setPreferredSize(new java.awt.Dimension(137, 28));

        jLabel96.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(176, 186, 201));
        jLabel96.setText("EDICIÓN DE EXPOTÉCNICA");

        cbEdicionExpotecnica.setToolTipText("Modificar o seleccionar una edición de Expotécnica");

        javax.swing.GroupLayout pnlModalAjustesActividadesLayout = new javax.swing.GroupLayout(pnlModalAjustesActividades);
        pnlModalAjustesActividades.setLayout(pnlModalAjustesActividadesLayout);
        pnlModalAjustesActividadesLayout.setHorizontalGroup(
            pnlModalAjustesActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(pnlModalAjustesActividadesLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnlModalAjustesActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlModalAjustesActividadesLayout.createSequentialGroup()
                        .addComponent(cbEdicionExpotecnica, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addComponent(btnAceptarModalFechas, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelarModal1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlModalAjustesActividadesLayout.createSequentialGroup()
                        .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlModalAjustesActividadesLayout.createSequentialGroup()
                        .addGroup(pnlModalAjustesActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dateFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel78))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlModalAjustesActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel79))
                        .addGap(33, 33, 33))))
        );
        pnlModalAjustesActividadesLayout.setVerticalGroup(
            pnlModalAjustesActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlModalAjustesActividadesLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlModalAjustesActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlModalAjustesActividadesLayout.createSequentialGroup()
                        .addComponent(jLabel79)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlModalAjustesActividadesLayout.createSequentialGroup()
                        .addComponent(jLabel78)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(pnlModalAjustesActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarModal1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptarModalFechas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbEdicionExpotecnica, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout modalAjustesActividadesLayout = new javax.swing.GroupLayout(modalAjustesActividades.getContentPane());
        modalAjustesActividades.getContentPane().setLayout(modalAjustesActividadesLayout);
        modalAjustesActividadesLayout.setHorizontalGroup(
            modalAjustesActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        modalAjustesActividadesLayout.setVerticalGroup(
            modalAjustesActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(1000, 625));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlMenu.setBackground(new java.awt.Color(255, 255, 255));
        pnlMenu.setPreferredSize(new java.awt.Dimension(150, 625));
        pnlMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMenu.setBackground(new java.awt.Color(255, 255, 255));
        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIcon.png"))); // NOI18N
        btnMenu.setToolTipText("Extender el menú");
        btnMenu.setBorderPainted(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenu.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconActive.png"))); // NOI18N
        btnMenu.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconActive.png"))); // NOI18N
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });
        pnlMenu.add(btnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 24, 32, 32));

        btnUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        btnUsuarios.setPreferredSize(new java.awt.Dimension(0, 40));
        btnUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUsuariosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUsuariosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUsuariosMouseExited(evt);
            }
        });

        lblUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        lblUsuarios.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        lblUsuarios.setForeground(new java.awt.Color(176, 186, 201));
        lblUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconUsers.png"))); // NOI18N
        lblUsuarios.setText("Usuarios");
        lblUsuarios.setIconTextGap(16);

        pnlActiveUsuarios.setBackground(new java.awt.Color(46, 91, 255));
        pnlActiveUsuarios.setPreferredSize(new java.awt.Dimension(3, 40));

        javax.swing.GroupLayout pnlActiveUsuariosLayout = new javax.swing.GroupLayout(pnlActiveUsuarios);
        pnlActiveUsuarios.setLayout(pnlActiveUsuariosLayout);
        pnlActiveUsuariosLayout.setHorizontalGroup(
            pnlActiveUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        pnlActiveUsuariosLayout.setVerticalGroup(
            pnlActiveUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btnUsuariosLayout = new javax.swing.GroupLayout(btnUsuarios);
        btnUsuarios.setLayout(btnUsuariosLayout);
        btnUsuariosLayout.setHorizontalGroup(
            btnUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnUsuariosLayout.createSequentialGroup()
                .addComponent(pnlActiveUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(lblUsuarios)
                .addGap(0, 41, Short.MAX_VALUE))
        );
        btnUsuariosLayout.setVerticalGroup(
            btnUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnUsuariosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(btnUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlActiveUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)))
        );

        pnlMenu.add(btnUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 256, 150, -1));

        btnAnaliticas.setBackground(new java.awt.Color(255, 255, 255));
        btnAnaliticas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAnaliticasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAnaliticasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAnaliticasMouseExited(evt);
            }
        });

        lblAnaliticas.setBackground(new java.awt.Color(255, 255, 255));
        lblAnaliticas.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        lblAnaliticas.setForeground(new java.awt.Color(176, 186, 201));
        lblAnaliticas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconAnalytics.png"))); // NOI18N
        lblAnaliticas.setText("Analíticas");
        lblAnaliticas.setIconTextGap(18);

        pnlActiveAnaliticas.setBackground(new java.awt.Color(46, 91, 255));
        pnlActiveAnaliticas.setPreferredSize(new java.awt.Dimension(3, 40));

        javax.swing.GroupLayout pnlActiveAnaliticasLayout = new javax.swing.GroupLayout(pnlActiveAnaliticas);
        pnlActiveAnaliticas.setLayout(pnlActiveAnaliticasLayout);
        pnlActiveAnaliticasLayout.setHorizontalGroup(
            pnlActiveAnaliticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        pnlActiveAnaliticasLayout.setVerticalGroup(
            pnlActiveAnaliticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btnAnaliticasLayout = new javax.swing.GroupLayout(btnAnaliticas);
        btnAnaliticas.setLayout(btnAnaliticasLayout);
        btnAnaliticasLayout.setHorizontalGroup(
            btnAnaliticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAnaliticasLayout.createSequentialGroup()
                .addComponent(pnlActiveAnaliticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblAnaliticas)
                .addGap(0, 34, Short.MAX_VALUE))
        );
        btnAnaliticasLayout.setVerticalGroup(
            btnAnaliticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnAnaliticasLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(btnAnaliticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlActiveAnaliticas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAnaliticas, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)))
        );

        pnlMenu.add(btnAnaliticas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 206, 150, -1));

        btnProyectos.setBackground(new java.awt.Color(255, 255, 255));
        btnProyectos.setPreferredSize(new java.awt.Dimension(0, 40));
        btnProyectos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProyectosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnProyectosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnProyectosMouseExited(evt);
            }
        });

        lblProyectos.setBackground(new java.awt.Color(255, 255, 255));
        lblProyectos.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        lblProyectos.setForeground(new java.awt.Color(176, 186, 201));
        lblProyectos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconProjects.png"))); // NOI18N
        lblProyectos.setText("Proyectos");
        lblProyectos.setIconTextGap(22);

        pnlActiveProyectos.setBackground(new java.awt.Color(46, 91, 255));
        pnlActiveProyectos.setPreferredSize(new java.awt.Dimension(3, 40));

        javax.swing.GroupLayout pnlActiveProyectosLayout = new javax.swing.GroupLayout(pnlActiveProyectos);
        pnlActiveProyectos.setLayout(pnlActiveProyectosLayout);
        pnlActiveProyectosLayout.setHorizontalGroup(
            pnlActiveProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        pnlActiveProyectosLayout.setVerticalGroup(
            pnlActiveProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btnProyectosLayout = new javax.swing.GroupLayout(btnProyectos);
        btnProyectos.setLayout(btnProyectosLayout);
        btnProyectosLayout.setHorizontalGroup(
            btnProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnProyectosLayout.createSequentialGroup()
                .addComponent(pnlActiveProyectos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblProyectos)
                .addGap(0, 32, Short.MAX_VALUE))
        );
        btnProyectosLayout.setVerticalGroup(
            btnProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnProyectosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(btnProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlActiveProyectos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblProyectos, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)))
        );

        pnlMenu.add(btnProyectos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 306, 150, -1));

        btnUbicaciones.setBackground(new java.awt.Color(255, 255, 255));
        btnUbicaciones.setPreferredSize(new java.awt.Dimension(0, 40));
        btnUbicaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUbicacionesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUbicacionesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUbicacionesMouseExited(evt);
            }
        });

        lblUbicaciones.setBackground(new java.awt.Color(255, 255, 255));
        lblUbicaciones.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        lblUbicaciones.setForeground(new java.awt.Color(176, 186, 201));
        lblUbicaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconMap.png"))); // NOI18N
        lblUbicaciones.setText("Ubicaciones");
        lblUbicaciones.setIconTextGap(20);

        pnlActiveUbicaciones.setBackground(new java.awt.Color(46, 91, 255));
        pnlActiveUbicaciones.setPreferredSize(new java.awt.Dimension(3, 40));

        javax.swing.GroupLayout pnlActiveUbicacionesLayout = new javax.swing.GroupLayout(pnlActiveUbicaciones);
        pnlActiveUbicaciones.setLayout(pnlActiveUbicacionesLayout);
        pnlActiveUbicacionesLayout.setHorizontalGroup(
            pnlActiveUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        pnlActiveUbicacionesLayout.setVerticalGroup(
            pnlActiveUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btnUbicacionesLayout = new javax.swing.GroupLayout(btnUbicaciones);
        btnUbicaciones.setLayout(btnUbicacionesLayout);
        btnUbicacionesLayout.setHorizontalGroup(
            btnUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnUbicacionesLayout.createSequentialGroup()
                .addComponent(pnlActiveUbicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblUbicaciones)
                .addGap(0, 21, Short.MAX_VALUE))
        );
        btnUbicacionesLayout.setVerticalGroup(
            btnUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnUbicacionesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(btnUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlActiveUbicaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUbicaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)))
        );

        pnlMenu.add(btnUbicaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 356, 150, -1));

        btnActividades.setBackground(new java.awt.Color(255, 255, 255));
        btnActividades.setPreferredSize(new java.awt.Dimension(0, 40));
        btnActividades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActividadesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnActividadesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnActividadesMouseExited(evt);
            }
        });

        lblActividades.setBackground(new java.awt.Color(255, 255, 255));
        lblActividades.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        lblActividades.setForeground(new java.awt.Color(176, 186, 201));
        lblActividades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconCalendar.png"))); // NOI18N
        lblActividades.setText("Actividades");
        lblActividades.setIconTextGap(21);

        pnlActiveActividades.setBackground(new java.awt.Color(46, 91, 255));
        pnlActiveActividades.setPreferredSize(new java.awt.Dimension(3, 40));

        javax.swing.GroupLayout pnlActiveActividadesLayout = new javax.swing.GroupLayout(pnlActiveActividades);
        pnlActiveActividades.setLayout(pnlActiveActividadesLayout);
        pnlActiveActividadesLayout.setHorizontalGroup(
            pnlActiveActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        pnlActiveActividadesLayout.setVerticalGroup(
            pnlActiveActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btnActividadesLayout = new javax.swing.GroupLayout(btnActividades);
        btnActividades.setLayout(btnActividadesLayout);
        btnActividadesLayout.setHorizontalGroup(
            btnActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnActividadesLayout.createSequentialGroup()
                .addComponent(pnlActiveActividades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(lblActividades)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        btnActividadesLayout.setVerticalGroup(
            btnActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnActividadesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(btnActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlActiveActividades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblActividades, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)))
        );

        pnlMenu.add(btnActividades, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 406, 150, -1));

        btnAjustes.setBackground(new java.awt.Color(255, 255, 255));
        btnAjustes.setPreferredSize(new java.awt.Dimension(0, 40));
        btnAjustes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAjustesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAjustesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAjustesMouseExited(evt);
            }
        });

        lblAjustes.setBackground(new java.awt.Color(255, 255, 255));
        lblAjustes.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        lblAjustes.setForeground(new java.awt.Color(176, 186, 201));
        lblAjustes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconSettings.png"))); // NOI18N
        lblAjustes.setText("Ajustes");
        lblAjustes.setIconTextGap(21);

        pnlActiveAjustes.setBackground(new java.awt.Color(46, 91, 255));
        pnlActiveAjustes.setPreferredSize(new java.awt.Dimension(3, 40));

        javax.swing.GroupLayout pnlActiveAjustesLayout = new javax.swing.GroupLayout(pnlActiveAjustes);
        pnlActiveAjustes.setLayout(pnlActiveAjustesLayout);
        pnlActiveAjustesLayout.setHorizontalGroup(
            pnlActiveAjustesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        pnlActiveAjustesLayout.setVerticalGroup(
            pnlActiveAjustesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout btnAjustesLayout = new javax.swing.GroupLayout(btnAjustes);
        btnAjustes.setLayout(btnAjustesLayout);
        btnAjustesLayout.setHorizontalGroup(
            btnAjustesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAjustesLayout.createSequentialGroup()
                .addComponent(pnlActiveAjustes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(lblAjustes)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        btnAjustesLayout.setVerticalGroup(
            btnAjustesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnAjustesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(btnAjustesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlActiveAjustes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAjustes, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)))
        );

        pnlMenu.add(btnAjustes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 456, 150, -1));

        btnInicio.setBackground(new java.awt.Color(238, 242, 255));
        btnInicio.setPreferredSize(new java.awt.Dimension(0, 40));
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
            .addGap(0, 3, Short.MAX_VALUE)
        );
        pnlActiveInicioLayout.setVerticalGroup(
            pnlActiveInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        lblDashboard.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        lblDashboard.setForeground(new java.awt.Color(46, 91, 255));
        lblDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconHomeActive.png"))); // NOI18N
        lblDashboard.setText("Dashboard");
        lblDashboard.setIconTextGap(20);
        lblDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDashboardMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnInicioLayout = new javax.swing.GroupLayout(btnInicio);
        btnInicio.setLayout(btnInicioLayout);
        btnInicioLayout.setHorizontalGroup(
            btnInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnInicioLayout.createSequentialGroup()
                .addComponent(pnlActiveInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDashboard))
        );
        btnInicioLayout.setVerticalGroup(
            btnInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnInicioLayout.createSequentialGroup()
                .addComponent(pnlActiveInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(lblDashboard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlMenu.add(btnInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 156, 150, -1));

        getContentPane().add(pnlMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 56, -1));

        pnlBackground.setPreferredSize(new java.awt.Dimension(1000, 625));

        pnlSuperior.setBackground(new java.awt.Color(255, 255, 255));
        pnlSuperior.setPreferredSize(new java.awt.Dimension(944, 55));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/LogoNegroREXS.png"))); // NOI18N

        jLabel80.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(46, 91, 255));
        jLabel80.setText("Eduardo Estrada");

        btnCerrarSesion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/signout.png"))); // NOI18N
        btnCerrarSesion.setToolTipText("Cerrar sesión");
        btnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseClicked(evt);
            }
        });

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Day.png"))); // NOI18N
        jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel32MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlSuperiorLayout = new javax.swing.GroupLayout(pnlSuperior);
        pnlSuperior.setLayout(pnlSuperiorLayout);
        pnlSuperiorLayout.setHorizontalGroup(
            pnlSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuperiorLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel80)
                .addGap(18, 18, 18)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel32)
                .addGap(25, 25, 25))
        );
        pnlSuperiorLayout.setVerticalGroup(
            pnlSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSuperiorLayout.createSequentialGroup()
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlCardLayoutAdmin.setBackground(new java.awt.Color(244, 246, 252));
        pnlCardLayoutAdmin.setPreferredSize(new java.awt.Dimension(944, 570));
        pnlCardLayoutAdmin.setLayout(new java.awt.CardLayout());

        pnlDashboard.setBorder(null);
        pnlDashboard.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pnlDashboard.setPreferredSize(new java.awt.Dimension(944, 570));

        jPanel115.setBackground(new java.awt.Color(244, 246, 252));
        jPanel115.setPreferredSize(new java.awt.Dimension(944, 730));

        jPanel117.setBackground(new java.awt.Color(255, 255, 255));
        jPanel117.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));

        jLabel285.setFont(new java.awt.Font("Rubik Light", 0, 21)); // NOI18N
        jLabel285.setForeground(new java.awt.Color(46, 56, 77));
        jLabel285.setText("a REXS");

        jLabel286.setFont(new java.awt.Font("Rubik Light", 0, 21)); // NOI18N
        jLabel286.setForeground(new java.awt.Color(46, 56, 77));
        jLabel286.setText("Bienvenido");

        jLabel252.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jLabel252.setForeground(new java.awt.Color(135, 152, 173));

        jLabel294.setFont(new java.awt.Font("Rubik Light", 0, 21)); // NOI18N
        jLabel294.setForeground(new java.awt.Color(46, 56, 77));
        jLabel294.setText("Edición");
        jLabel294.setToolTipText("");

        jLabel295.setFont(new java.awt.Font("Rubik Light", 0, 21)); // NOI18N
        jLabel295.setForeground(new java.awt.Color(46, 56, 77));
        jLabel295.setText("Expotécnica");

        lblEdicion.setFont(jLabel295.getFont());
        lblEdicion.setForeground(new java.awt.Color(46, 91, 255));
        lblEdicion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEdicion.setText("2019");

        btnAjustesActividades.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAjustesActividades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditBlue.png"))); // NOI18N
        btnAjustesActividades.setToolTipText("Modificar la fecha de la Expotécnica");
        btnAjustesActividades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAjustesActividadesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel117Layout = new javax.swing.GroupLayout(jPanel117);
        jPanel117.setLayout(jPanel117Layout);
        jPanel117Layout.setHorizontalGroup(
            jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel117Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel286)
                    .addComponent(jLabel285)
                    .addComponent(jLabel252, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel294)
                    .addComponent(jLabel295)
                    .addGroup(jPanel117Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lblEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAjustesActividades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel117Layout.setVerticalGroup(
            jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel117Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel286)
                    .addComponent(jLabel294))
                .addGroup(jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel117Layout.createSequentialGroup()
                        .addComponent(jLabel285)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel252, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel117Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel295)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEdicion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
            .addComponent(btnAjustesActividades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel118.setBackground(new java.awt.Color(255, 255, 255));
        jPanel118.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));
        jPanel118.setPreferredSize(new java.awt.Dimension(185, 114));

        jLabel253.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jLabel253.setForeground(new java.awt.Color(135, 152, 173));
        jLabel253.setText("USUARIOS NUEVOS/DÍA");

        jLabel287.setFont(new java.awt.Font("Rubik Light", 0, 28)); // NOI18N
        jLabel287.setForeground(new java.awt.Color(46, 56, 77));
        jLabel287.setText("108");

        jLabel288.setFont(new java.awt.Font("Rubik Light", 0, 18)); // NOI18N
        jLabel288.setForeground(new java.awt.Color(46, 56, 77));
        jLabel288.setText("usuarios");

        jLabel254.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel254.setForeground(new java.awt.Color(232, 74, 80));
        jLabel254.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconDecenso.png"))); // NOI18N
        jLabel254.setText("-7.6%");

        javax.swing.GroupLayout jPanel118Layout = new javax.swing.GroupLayout(jPanel118);
        jPanel118.setLayout(jPanel118Layout);
        jPanel118Layout.setHorizontalGroup(
            jPanel118Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel118Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel118Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel254)
                    .addGroup(jPanel118Layout.createSequentialGroup()
                        .addComponent(jLabel287)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel288))
                    .addComponent(jLabel253))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel118Layout.setVerticalGroup(
            jPanel118Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel118Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel253)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel118Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel287)
                    .addComponent(jLabel288))
                .addGap(4, 4, 4)
                .addComponent(jLabel254)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel125.setBackground(new java.awt.Color(255, 255, 255));
        jPanel125.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));

        jLabel289.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jLabel289.setForeground(new java.awt.Color(135, 152, 173));
        jLabel289.setText("VOTOS NUEVOS/DÍA");

        jLabel290.setFont(new java.awt.Font("Rubik Light", 0, 28)); // NOI18N
        jLabel290.setForeground(new java.awt.Color(46, 56, 77));
        jLabel290.setText("158");

        jLabel291.setFont(new java.awt.Font("Rubik Light", 0, 18)); // NOI18N
        jLabel291.setForeground(new java.awt.Color(46, 56, 77));
        jLabel291.setText("votos");

        jLabel292.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel292.setForeground(new java.awt.Color(45, 183, 68));
        jLabel292.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconIncremento.png"))); // NOI18N
        jLabel292.setText("+2.4%");

        javax.swing.GroupLayout jPanel125Layout = new javax.swing.GroupLayout(jPanel125);
        jPanel125.setLayout(jPanel125Layout);
        jPanel125Layout.setHorizontalGroup(
            jPanel125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel125Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel292)
                    .addGroup(jPanel125Layout.createSequentialGroup()
                        .addComponent(jLabel290)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel291))
                    .addComponent(jLabel289))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel125Layout.setVerticalGroup(
            jPanel125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel125Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel289)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel290)
                    .addComponent(jLabel291))
                .addGap(4, 4, 4)
                .addComponent(jLabel292)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel126.setBackground(new java.awt.Color(255, 255, 255));
        jPanel126.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));
        jPanel126.setPreferredSize(new java.awt.Dimension(387, 275));

        jLabel250.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/grafica1.png"))); // NOI18N

        jLabel251.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jLabel251.setForeground(new java.awt.Color(135, 152, 173));
        jLabel251.setText("INICIO DE SESIÓN DE USUARIOS/HORA");

        javax.swing.GroupLayout jPanel126Layout = new javax.swing.GroupLayout(jPanel126);
        jPanel126.setLayout(jPanel126Layout);
        jPanel126Layout.setHorizontalGroup(
            jPanel126Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel126Layout.createSequentialGroup()
                .addGroup(jPanel126Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel126Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel250))
                    .addGroup(jPanel126Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel251)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel126Layout.setVerticalGroup(
            jPanel126Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel126Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel251)
                .addGap(14, 14, 14)
                .addComponent(jLabel250)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout jPanel116Layout = new javax.swing.GroupLayout(jPanel116);
        jPanel116.setLayout(jPanel116Layout);
        jPanel116Layout.setHorizontalGroup(
            jPanel116Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel116Layout.createSequentialGroup()
                .addGroup(jPanel116Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel116Layout.createSequentialGroup()
                        .addComponent(jPanel118, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel125, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel117, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jPanel126, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel116Layout.setVerticalGroup(
            jPanel116Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel116Layout.createSequentialGroup()
                .addComponent(jPanel117, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel116Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel125, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel118, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)))
            .addComponent(jPanel126, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
        );

        jPanel127.setPreferredSize(new java.awt.Dimension(804, 312));

        jPanel128.setBackground(new java.awt.Color(255, 255, 255));
        jPanel128.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));
        jPanel128.setPreferredSize(new java.awt.Dimension(254, 312));

        jLabel293.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jLabel293.setForeground(new java.awt.Color(135, 152, 173));
        jLabel293.setText("MEJORES PROYECTOS");

        jPanel135.setBackground(new java.awt.Color(255, 255, 255));
        jPanel135.setPreferredSize(new java.awt.Dimension(208, 52));

        jLabel306.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        jLabel306.setForeground(new java.awt.Color(46, 56, 77));
        jLabel306.setText("Nombre del proyecto...");

        jLabel307.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jLabel307.setForeground(new java.awt.Color(135, 152, 173));
        jLabel307.setText("Arquitectura");

        jLabel308.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel308.setForeground(new java.awt.Color(135, 152, 173));
        jLabel308.setText("39 votos");

        jPanel136.setBackground(new java.awt.Color(255, 255, 255));
        jPanel136.setPreferredSize(new java.awt.Dimension(36, 36));

        jLabel309.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel309.setForeground(new java.awt.Color(255, 255, 255));
        jLabel309.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel309.setText("3");

        javax.swing.GroupLayout jPanel136Layout = new javax.swing.GroupLayout(jPanel136);
        jPanel136.setLayout(jPanel136Layout);
        jPanel136Layout.setHorizontalGroup(
            jPanel136Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel309, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );
        jPanel136Layout.setVerticalGroup(
            jPanel136Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel309, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jLabel310.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jLabel310.setText("9.8");

        javax.swing.GroupLayout jPanel135Layout = new javax.swing.GroupLayout(jPanel135);
        jPanel135.setLayout(jPanel135Layout);
        jPanel135Layout.setHorizontalGroup(
            jPanel135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel135Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel136, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel135Layout.createSequentialGroup()
                        .addComponent(jLabel307)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel308))
                    .addGroup(jPanel135Layout.createSequentialGroup()
                        .addComponent(jLabel306)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(jLabel310)))
                .addGap(1, 1, 1))
        );
        jPanel135Layout.setVerticalGroup(
            jPanel135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel135Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel135Layout.createSequentialGroup()
                        .addGroup(jPanel135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel306)
                            .addComponent(jLabel310))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel307)
                            .addComponent(jLabel308)))
                    .addComponent(jPanel136, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel137.setBackground(new java.awt.Color(255, 255, 255));
        jPanel137.setPreferredSize(new java.awt.Dimension(208, 52));

        jLabel311.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        jLabel311.setForeground(new java.awt.Color(46, 56, 77));
        jLabel311.setText("Nombre del proyecto...");

        jLabel312.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jLabel312.setForeground(new java.awt.Color(135, 152, 173));
        jLabel312.setText("Arquitectura");

        jLabel313.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel313.setForeground(new java.awt.Color(135, 152, 173));
        jLabel313.setText("39 votos");

        jPanel138.setBackground(new java.awt.Color(255, 255, 255));
        jPanel138.setPreferredSize(new java.awt.Dimension(36, 36));

        jLabel314.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel314.setForeground(new java.awt.Color(255, 255, 255));
        jLabel314.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel314.setText("4");

        javax.swing.GroupLayout jPanel138Layout = new javax.swing.GroupLayout(jPanel138);
        jPanel138.setLayout(jPanel138Layout);
        jPanel138Layout.setHorizontalGroup(
            jPanel138Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel314, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );
        jPanel138Layout.setVerticalGroup(
            jPanel138Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel314, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jLabel315.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jLabel315.setText("9.8");

        javax.swing.GroupLayout jPanel137Layout = new javax.swing.GroupLayout(jPanel137);
        jPanel137.setLayout(jPanel137Layout);
        jPanel137Layout.setHorizontalGroup(
            jPanel137Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel137Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel138, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel137Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel137Layout.createSequentialGroup()
                        .addComponent(jLabel312)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel313))
                    .addGroup(jPanel137Layout.createSequentialGroup()
                        .addComponent(jLabel311)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(jLabel315)))
                .addGap(1, 1, 1))
        );
        jPanel137Layout.setVerticalGroup(
            jPanel137Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel137Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel137Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel137Layout.createSequentialGroup()
                        .addGroup(jPanel137Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel311)
                            .addComponent(jLabel315))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel137Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel312)
                            .addComponent(jLabel313)))
                    .addComponent(jPanel138, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel139.setBackground(new java.awt.Color(255, 255, 255));
        jPanel139.setPreferredSize(new java.awt.Dimension(208, 52));

        jLabel316.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        jLabel316.setForeground(new java.awt.Color(46, 56, 77));
        jLabel316.setText("Nombre del proyecto...");

        jLabel317.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jLabel317.setForeground(new java.awt.Color(135, 152, 173));
        jLabel317.setText("Administrativo Cont...");

        jLabel318.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel318.setForeground(new java.awt.Color(135, 152, 173));
        jLabel318.setText("39 votos");

        jPanel140.setBackground(new java.awt.Color(255, 255, 255));
        jPanel140.setPreferredSize(new java.awt.Dimension(36, 36));

        jLabel319.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel319.setForeground(new java.awt.Color(255, 255, 255));
        jLabel319.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel319.setText("2");

        javax.swing.GroupLayout jPanel140Layout = new javax.swing.GroupLayout(jPanel140);
        jPanel140.setLayout(jPanel140Layout);
        jPanel140Layout.setHorizontalGroup(
            jPanel140Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel319, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );
        jPanel140Layout.setVerticalGroup(
            jPanel140Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel319, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jLabel320.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jLabel320.setText("9.8");

        javax.swing.GroupLayout jPanel139Layout = new javax.swing.GroupLayout(jPanel139);
        jPanel139.setLayout(jPanel139Layout);
        jPanel139Layout.setHorizontalGroup(
            jPanel139Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel139Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel140, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel139Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel139Layout.createSequentialGroup()
                        .addComponent(jLabel317)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(jLabel318))
                    .addGroup(jPanel139Layout.createSequentialGroup()
                        .addComponent(jLabel316)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(jLabel320)))
                .addGap(1, 1, 1))
        );
        jPanel139Layout.setVerticalGroup(
            jPanel139Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel139Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel139Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel139Layout.createSequentialGroup()
                        .addGroup(jPanel139Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel316)
                            .addComponent(jLabel320))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel139Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel317)
                            .addComponent(jLabel318)))
                    .addComponent(jPanel140, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel141.setBackground(new java.awt.Color(255, 255, 255));
        jPanel141.setPreferredSize(new java.awt.Dimension(208, 52));

        jLabel321.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        jLabel321.setForeground(new java.awt.Color(46, 56, 77));
        jLabel321.setText("Nombre del proyecto...");

        jLabel322.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jLabel322.setForeground(new java.awt.Color(135, 152, 173));
        jLabel322.setText("Desarrollo de Softw...");

        jLabel323.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel323.setForeground(new java.awt.Color(135, 152, 173));
        jLabel323.setText("39 votos");

        jPanel142.setBackground(new java.awt.Color(255, 255, 255));
        jPanel142.setPreferredSize(new java.awt.Dimension(36, 36));

        jLabel324.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel324.setForeground(new java.awt.Color(255, 255, 255));
        jLabel324.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel324.setText("1");

        javax.swing.GroupLayout jPanel142Layout = new javax.swing.GroupLayout(jPanel142);
        jPanel142.setLayout(jPanel142Layout);
        jPanel142Layout.setHorizontalGroup(
            jPanel142Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel324, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );
        jPanel142Layout.setVerticalGroup(
            jPanel142Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel324, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jLabel325.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jLabel325.setText("9.8");

        javax.swing.GroupLayout jPanel141Layout = new javax.swing.GroupLayout(jPanel141);
        jPanel141.setLayout(jPanel141Layout);
        jPanel141Layout.setHorizontalGroup(
            jPanel141Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel141Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel142, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel141Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel141Layout.createSequentialGroup()
                        .addComponent(jLabel322)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jLabel323))
                    .addGroup(jPanel141Layout.createSequentialGroup()
                        .addComponent(jLabel321)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel325)))
                .addGap(1, 1, 1))
        );
        jPanel141Layout.setVerticalGroup(
            jPanel141Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel141Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel141Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel141Layout.createSequentialGroup()
                        .addGroup(jPanel141Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel321)
                            .addComponent(jLabel325))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel141Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel322)
                            .addComponent(jLabel323)))
                    .addComponent(jPanel142, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel128Layout = new javax.swing.GroupLayout(jPanel128);
        jPanel128.setLayout(jPanel128Layout);
        jPanel128Layout.setHorizontalGroup(
            jPanel128Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel128Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel128Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel141, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel139, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel137, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel135, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel293))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel128Layout.setVerticalGroup(
            jPanel128Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel128Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel293)
                .addGap(18, 18, 18)
                .addComponent(jPanel141, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel139, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel135, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel137, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel129.setBackground(new java.awt.Color(255, 255, 255));
        jPanel129.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));
        jPanel129.setPreferredSize(new java.awt.Dimension(254, 312));

        jLabel298.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jLabel298.setForeground(new java.awt.Color(135, 152, 173));
        jLabel298.setText("PRÓXIMAS ACTIVIDADES");

        jPanel20.setBackground(new java.awt.Color(160, 181, 255));
        jPanel20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(160, 181, 255), 1, true));

        jLabel72.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(46, 56, 77));
        jLabel72.setText("Nombre de actividad a realizarse");

        jLabel73.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(112, 112, 112));
        jLabel73.setText("8:00 AM - 10:30 AM");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel72)
                    .addComponent(jLabel73))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel72)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel73)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel71.setFont(new java.awt.Font("Rubik Medium", 0, 9)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(46, 91, 255));
        jLabel71.setText("JUE 26/SEPTIEMBRE");

        jPanel21.setBackground(new java.awt.Color(203, 178, 255));
        jPanel21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 178, 255), 1, true));

        jLabel74.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(46, 56, 77));
        jLabel74.setText("Nombre de actividad a realizarse");

        jLabel75.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(112, 112, 112));
        jLabel75.setText("8:00 AM - 10:30 AM");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel74)
                    .addComponent(jLabel75))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel74)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel75)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(112, 185, 236));
        jPanel22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(112, 185, 236), 1, true));

        jLabel76.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(46, 56, 77));
        jLabel76.setText("Nombre de actividad a realizarse");

        jLabel77.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(112, 112, 112));
        jLabel77.setText("8:00 AM - 10:30 AM");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel76)
                    .addComponent(jLabel77))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel76)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel77)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnFiltrarLista3.setBackground(new java.awt.Color(213, 222, 255));
        btnFiltrarLista3.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        btnFiltrarLista3.setForeground(new java.awt.Color(46, 91, 255));
        btnFiltrarLista3.setText("Ver Todas");
        btnFiltrarLista3.setBorderPainted(false);
        btnFiltrarLista3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarLista3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel129Layout = new javax.swing.GroupLayout(jPanel129);
        jPanel129.setLayout(jPanel129Layout);
        jPanel129Layout.setHorizontalGroup(
            jPanel129Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel129Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel129Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFiltrarLista3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel71)
                    .addComponent(jLabel298)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel129Layout.setVerticalGroup(
            jPanel129Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel129Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel298)
                .addGap(18, 18, 18)
                .addComponent(jLabel71)
                .addGap(8, 8, 8)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(btnFiltrarLista3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jPanel130.setBackground(new java.awt.Color(255, 255, 255));
        jPanel130.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));
        jPanel130.setPreferredSize(new java.awt.Dimension(254, 312));

        jLabel300.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jLabel300.setForeground(new java.awt.Color(135, 152, 173));
        jLabel300.setText("GENERAR BACKUP");

        checkUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        checkUsuarios.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        checkUsuarios.setForeground(new java.awt.Color(46, 56, 77));
        checkUsuarios.setText("Usuarios");
        checkUsuarios.setIconTextGap(12);

        checkProyectos.setBackground(new java.awt.Color(255, 255, 255));
        checkProyectos.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        checkProyectos.setForeground(new java.awt.Color(46, 56, 77));
        checkProyectos.setText("Proyectos");
        checkProyectos.setIconTextGap(12);

        checkVotaciones.setBackground(new java.awt.Color(255, 255, 255));
        checkVotaciones.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        checkVotaciones.setForeground(new java.awt.Color(46, 56, 77));
        checkVotaciones.setText("Votaciones");
        checkVotaciones.setIconTextGap(12);

        checkActividades.setBackground(new java.awt.Color(255, 255, 255));
        checkActividades.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        checkActividades.setForeground(new java.awt.Color(46, 56, 77));
        checkActividades.setText("Actividades");
        checkActividades.setIconTextGap(12);

        checkUbicaciones.setBackground(new java.awt.Color(255, 255, 255));
        checkUbicaciones.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        checkUbicaciones.setForeground(new java.awt.Color(46, 56, 77));
        checkUbicaciones.setText("Ubicaciones y secciones");
        checkUbicaciones.setIconTextGap(12);

        btnBackup.setBackground(new java.awt.Color(213, 222, 255));
        btnBackup.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        btnBackup.setForeground(new java.awt.Color(46, 91, 255));
        btnBackup.setText("Respaldar");
        btnBackup.setBorderPainted(false);
        btnBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackupActionPerformed(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(46, 56, 77));
        jLabel70.setText("Selecciona que deseas respaldar:");

        checkTodo.setBackground(new java.awt.Color(255, 255, 255));
        checkTodo.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        checkTodo.setForeground(new java.awt.Color(46, 91, 255));
        checkTodo.setText("Seleccionar todo");
        checkTodo.setIconTextGap(12);

        javax.swing.GroupLayout jPanel130Layout = new javax.swing.GroupLayout(jPanel130);
        jPanel130.setLayout(jPanel130Layout);
        jPanel130Layout.setHorizontalGroup(
            jPanel130Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel130Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel130Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkTodo)
                    .addComponent(checkActividades, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkVotaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkProyectos, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70)
                    .addComponent(btnBackup, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel300)
                    .addComponent(checkUbicaciones))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel130Layout.setVerticalGroup(
            jPanel130Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel130Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel300)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel70)
                .addGap(14, 14, 14)
                .addComponent(checkUsuarios)
                .addGap(5, 5, 5)
                .addComponent(checkProyectos)
                .addGap(5, 5, 5)
                .addComponent(checkVotaciones)
                .addGap(5, 5, 5)
                .addComponent(checkActividades)
                .addGap(5, 5, 5)
                .addComponent(checkUbicaciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 3, Short.MAX_VALUE)
                .addComponent(checkTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(btnBackup, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout jPanel127Layout = new javax.swing.GroupLayout(jPanel127);
        jPanel127.setLayout(jPanel127Layout);
        jPanel127Layout.setHorizontalGroup(
            jPanel127Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel127Layout.createSequentialGroup()
                .addComponent(jPanel128, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jPanel129, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jPanel130, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel127Layout.setVerticalGroup(
            jPanel127Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel128, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel129, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel130, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel115Layout = new javax.swing.GroupLayout(jPanel115);
        jPanel115.setLayout(jPanel115Layout);
        jPanel115Layout.setHorizontalGroup(
            jPanel115Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel115Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel115Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel116, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel127, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel115Layout.setVerticalGroup(
            jPanel115Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel115Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jPanel116, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel127, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pnlDashboard.setViewportView(jPanel115);

        pnlCardLayoutAdmin.add(pnlDashboard, "Dashboard");

        jPanel145.setBackground(new java.awt.Color(255, 255, 255));
        jPanel145.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));
        jPanel145.setPreferredSize(new java.awt.Dimension(387, 275));

        jLabel266.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jLabel266.setForeground(new java.awt.Color(135, 152, 173));
        jLabel266.setText("GENERAR REPORTE");

        jLabel85.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(46, 56, 77));
        jLabel85.setText("Selecciona el reporte a generar:");

        jrbVotaciones.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupReportes.add(jrbVotaciones);
        jrbVotaciones.setText("Votaciones");

        jrbBitacoras.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupReportes.add(jrbBitacoras);
        jrbBitacoras.setText("Bitácoras");

        jrbUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupReportes.add(jrbUsuarios);
        jrbUsuarios.setText("Usuarios");

        jrbProyectos.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupReportes.add(jrbProyectos);
        jrbProyectos.setText("Proyectos");

        btnReporte.setBackground(new java.awt.Color(46, 91, 255));
        btnReporte.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        btnReporte.setForeground(new java.awt.Color(255, 255, 255));
        btnReporte.setText("Siguiente");
        btnReporte.setBorder(null);
        btnReporte.setBorderPainted(false);
        btnReporte.setPreferredSize(new java.awt.Dimension(138, 28));
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel145Layout = new javax.swing.GroupLayout(jPanel145);
        jPanel145.setLayout(jPanel145Layout);
        jPanel145Layout.setHorizontalGroup(
            jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel145Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel145Layout.createSequentialGroup()
                        .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel145Layout.createSequentialGroup()
                        .addGroup(jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel85)
                            .addComponent(jLabel266)
                            .addGroup(jPanel145Layout.createSequentialGroup()
                                .addGroup(jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jrbUsuarios)
                                    .addComponent(jrbVotaciones))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jrbProyectos)
                                    .addComponent(jrbBitacoras))))
                        .addContainerGap(176, Short.MAX_VALUE))))
        );
        jPanel145Layout.setVerticalGroup(
            jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel145Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel266)
                .addGap(25, 25, 25)
                .addComponent(jLabel85)
                .addGap(18, 18, 18)
                .addGroup(jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbBitacoras)
                    .addComponent(jrbUsuarios))
                .addGap(14, 14, 14)
                .addGroup(jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbProyectos)
                    .addComponent(jrbVotaciones))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        jPanel146.setBackground(new java.awt.Color(255, 255, 255));
        jPanel146.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));
        jPanel146.setPreferredSize(new java.awt.Dimension(387, 275));

        jLabel86.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(135, 152, 173));
        jLabel86.setText("Usuarios registrados");

        jLabel87.setFont(new java.awt.Font("Rubik Light", 0, 36)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(46, 56, 77));
        jLabel87.setText("1008");

        javax.swing.GroupLayout jPanel146Layout = new javax.swing.GroupLayout(jPanel146);
        jPanel146.setLayout(jPanel146Layout);
        jPanel146Layout.setHorizontalGroup(
            jPanel146Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel146Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel146Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel87)
                    .addComponent(jLabel86))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel146Layout.setVerticalGroup(
            jPanel146Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel146Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel87)
                .addGap(2, 2, 2)
                .addComponent(jLabel86)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanel150.setBackground(new java.awt.Color(255, 255, 255));
        jPanel150.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));
        jPanel150.setPreferredSize(new java.awt.Dimension(387, 275));

        jLabel88.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(135, 152, 173));
        jLabel88.setText("Proyectos inscritos");

        jLabel89.setFont(new java.awt.Font("Rubik Light", 0, 36)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(46, 56, 77));
        jLabel89.setText("225");

        javax.swing.GroupLayout jPanel150Layout = new javax.swing.GroupLayout(jPanel150);
        jPanel150.setLayout(jPanel150Layout);
        jPanel150Layout.setHorizontalGroup(
            jPanel150Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel150Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel150Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel89)
                    .addComponent(jLabel88))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel150Layout.setVerticalGroup(
            jPanel150Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel150Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel89)
                .addGap(2, 2, 2)
                .addComponent(jLabel88)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanel151.setBackground(new java.awt.Color(255, 255, 255));
        jPanel151.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));
        jPanel151.setPreferredSize(new java.awt.Dimension(387, 275));

        jLabel90.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(135, 152, 173));
        jLabel90.setText("Ubicaciones totales");

        jLabel93.setFont(new java.awt.Font("Rubik Light", 0, 36)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(46, 56, 77));
        jLabel93.setText("38");

        javax.swing.GroupLayout jPanel151Layout = new javax.swing.GroupLayout(jPanel151);
        jPanel151.setLayout(jPanel151Layout);
        jPanel151Layout.setHorizontalGroup(
            jPanel151Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel151Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel151Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(jLabel93)
                    .addComponent(jLabel90))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel151Layout.setVerticalGroup(
            jPanel151Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel151Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel93)
                .addGap(2, 2, 2)
                .addComponent(jLabel90)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanel152.setBackground(new java.awt.Color(255, 255, 255));
        jPanel152.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));
        jPanel152.setPreferredSize(new java.awt.Dimension(387, 275));

        jLabel94.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(135, 152, 173));
        jLabel94.setText("Votos realizados");

        jLabel95.setFont(new java.awt.Font("Rubik Light", 0, 36)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(46, 56, 77));
        jLabel95.setText("694");

        javax.swing.GroupLayout jPanel152Layout = new javax.swing.GroupLayout(jPanel152);
        jPanel152.setLayout(jPanel152Layout);
        jPanel152Layout.setHorizontalGroup(
            jPanel152Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel152Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel152Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel95)
                    .addComponent(jLabel94))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel152Layout.setVerticalGroup(
            jPanel152Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel152Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel95)
                .addGap(2, 2, 2)
                .addComponent(jLabel94)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlAnaliticasLayout = new javax.swing.GroupLayout(pnlAnaliticas);
        pnlAnaliticas.setLayout(pnlAnaliticasLayout);
        pnlAnaliticasLayout.setHorizontalGroup(
            pnlAnaliticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAnaliticasLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(pnlAnaliticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel145, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlAnaliticasLayout.createSequentialGroup()
                        .addComponent(jPanel146, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jPanel150, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jPanel151, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jPanel152, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        pnlAnaliticasLayout.setVerticalGroup(
            pnlAnaliticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAnaliticasLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlAnaliticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel146, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel150, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel151, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel152, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(jPanel145, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(138, Short.MAX_VALUE))
        );

        pnlCardLayoutAdmin.add(pnlAnaliticas, "Analiticas");

        pnlUsuarios.setBackground(new java.awt.Color(244, 246, 252));

        jLabel10.setFont(new java.awt.Font("Rubik Light", 0, 22)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(46, 56, 77));
        jLabel10.setText("Gestión de Usuarios");

        jPanel5.setBackground(new java.awt.Color(244, 246, 252));
        jPanel5.setPreferredSize(new java.awt.Dimension(600, 303));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel7.setToolTipText("");
        jPanel7.setPreferredSize(new java.awt.Dimension(185, 90));

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconCircleActiveUsers.png"))); // NOI18N

        jLUsuarioActivos.setFont(new java.awt.Font("Rubik Light", 0, 32)); // NOI18N
        jLUsuarioActivos.setForeground(new java.awt.Color(46, 56, 77));
        jLUsuarioActivos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLUsuarioActivos.setText("0");

        jLabel47.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(135, 152, 173));
        jLabel47.setText("Usuarios Activos");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel45)
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47)
                    .addComponent(jLUsuarioActivos, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLUsuarioActivos)
                    .addComponent(jLabel45))
                .addGap(2, 2, 2)
                .addComponent(jLabel47)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel8.setPreferredSize(new java.awt.Dimension(185, 90));

        jLabel48.setFont(new java.awt.Font("Rubik Light", 0, 26)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(46, 56, 77));
        jLabel48.setText("6m:14s");

        jLabel63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconCircleTime.png"))); // NOI18N

        jLabel64.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(135, 152, 173));
        jLabel64.setText("Uso Promedio");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel63)
                .addGap(12, 12, 12)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel64)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addComponent(jLabel64)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel9.setToolTipText("");
        jPanel9.setPreferredSize(new java.awt.Dimension(185, 90));

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconCircleTotalUsers.png"))); // NOI18N

        jLUsuarioTotal.setFont(new java.awt.Font("Rubik Light", 0, 32)); // NOI18N
        jLUsuarioTotal.setForeground(new java.awt.Color(46, 56, 77));
        jLUsuarioTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLUsuarioTotal.setText("0");

        jLabel44.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(135, 152, 173));
        jLabel44.setText("Usuarios Totales");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel42)
                .addGap(12, 12, 12)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44)
                    .addComponent(jLUsuarioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLUsuarioTotal)
                    .addComponent(jLabel42))
                .addGap(2, 2, 2)
                .addComponent(jLabel44)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel10.setPreferredSize(new java.awt.Dimension(212, 254));

        jLabel24.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(135, 152, 173));
        jLabel24.setText("FILTRO");

        txtBusquedaFiltro.setBackground(new java.awt.Color(249, 250, 255));
        txtBusquedaFiltro.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtBusquedaFiltro.setForeground(new java.awt.Color(46, 56, 77));
        txtBusquedaFiltro.setToolTipText("Ingrese el nombre de un usuario");
        txtBusquedaFiltro.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));

        jLabel25.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(46, 56, 77));
        jLabel25.setText("Búsqueda");

        jLabel26.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(46, 56, 77));
        jLabel26.setText("Tipo de usuario");

        jComboBox1.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(135, 152, 173));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setToolTipText("Tipo de usuario");
        jComboBox1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true));

        jComboBox2.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(135, 152, 173));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setToolTipText("Estado de usuario");
        jComboBox2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true));

        jLabel27.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(46, 56, 77));
        jLabel27.setText("Estado de usuario");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel27)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel26)
                        .addComponent(txtBusquedaFiltro)
                        .addComponent(jLabel25)
                        .addComponent(jLabel24)
                        .addComponent(jComboBox1, 0, 144, Short.MAX_VALUE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel24)
                .addGap(20, 20, 20)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBusquedaFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(244, 246, 252));
        jPanel11.setPreferredSize(new java.awt.Dimension(579, 36));

        jLabel11.setBackground(new java.awt.Color(244, 246, 252));
        jLabel11.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(191, 197, 210));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("ID");

        jLabel12.setBackground(new java.awt.Color(244, 246, 252));
        jLabel12.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(191, 197, 210));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("EMAIL");

        jLabel13.setBackground(new java.awt.Color(244, 246, 252));
        jLabel13.setFont(new java.awt.Font("Rubik Medium", 0, 9)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(191, 197, 210));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("FECHA REGISTRO");

        jLabel14.setBackground(new java.awt.Color(244, 246, 252));
        jLabel14.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(191, 197, 210));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("TIPO");

        jLabel15.setBackground(new java.awt.Color(244, 246, 252));
        jLabel15.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(191, 197, 210));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("ESTADO");

        jLabel16.setBackground(new java.awt.Color(244, 246, 252));
        jLabel16.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(191, 197, 210));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("NOMBRE");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnAgregarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Button_Add.png"))); // NOI18N
        btnAgregarUsuario.setToolTipText("Agregar un usuario");
        btnAgregarUsuario.setBorder(null);
        btnAgregarUsuario.setBorderPainted(false);
        btnAgregarUsuario.setContentAreaFilled(false);
        btnAgregarUsuario.setPreferredSize(new java.awt.Dimension(52, 52));
        btnAgregarUsuario.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/ActiveButton_Add.png"))); // NOI18N
        btnAgregarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarUsuarioActionPerformed(evt);
            }
        });

        btnFiltrarLista.setBackground(new java.awt.Color(46, 91, 255));
        btnFiltrarLista.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        btnFiltrarLista.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltrarLista.setText("Filtrar Lista");
        btnFiltrarLista.setToolTipText("Buscar");
        btnFiltrarLista.setBorderPainted(false);
        btnFiltrarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarListaActionPerformed(evt);
            }
        });

        jpnlTableUsuarios.setBackground(new java.awt.Color(244, 246, 252));
        jpnlTableUsuarios.setBorder(null);
        jpnlTableUsuarios.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setLayout(new java.awt.GridLayout(0, 1, 15, 20));
        jpnlTableUsuarios.setViewportView(jPanel1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnlTableUsuarios))
                .addGap(0, 31, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrarLista, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpnlTableUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFiltrarLista, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(btnAgregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlUsuariosLayout = new javax.swing.GroupLayout(pnlUsuarios);
        pnlUsuarios.setLayout(pnlUsuariosLayout);
        pnlUsuariosLayout.setHorizontalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuariosLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        pnlUsuariosLayout.setVerticalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuariosLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel10)
                .addGap(45, 45, 45)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
        );

        pnlCardLayoutAdmin.add(pnlUsuarios, "Usuarios");

        pnlProyectos.setBackground(new java.awt.Color(244, 246, 252));

        jPanel31.setBackground(new java.awt.Color(244, 246, 252));
        jPanel31.setPreferredSize(new java.awt.Dimension(808, 545));

        jScrollPane1.setBorder(null);

        cdProyectos.setBackground(new java.awt.Color(244, 246, 252));
        cdProyectos.setLayout(new java.awt.GridLayout(0, 2, 15, 20));
        jScrollPane1.setViewportView(cdProyectos);

        jLabel214.setFont(new java.awt.Font("Rubik Light", 0, 22)); // NOI18N
        jLabel214.setForeground(new java.awt.Color(46, 56, 77));
        jLabel214.setText("Proyectos");

        btnAgregarEspecialidad1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Button_Add.png"))); // NOI18N
        btnAgregarEspecialidad1.setToolTipText("Agregar un proyecto");
        btnAgregarEspecialidad1.setBorder(null);
        btnAgregarEspecialidad1.setBorderPainted(false);
        btnAgregarEspecialidad1.setContentAreaFilled(false);
        btnAgregarEspecialidad1.setPreferredSize(new java.awt.Dimension(52, 52));
        btnAgregarEspecialidad1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/ActiveButton_Add.png"))); // NOI18N
        btnAgregarEspecialidad1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEspecialidad1ActionPerformed(evt);
            }
        });

        jLabel228.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel228.setForeground(new java.awt.Color(46, 56, 77));
        jLabel228.setText("Nivel");

        jComboBox4.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jComboBox4.setForeground(new java.awt.Color(135, 152, 173));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true));

        jComboBox3.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(135, 152, 173));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true));

        jLabel215.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel215.setForeground(new java.awt.Color(46, 56, 77));
        jLabel215.setText("Especialidad");

        jComboBox5.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jComboBox5.setForeground(new java.awt.Color(135, 152, 173));
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true));

        jLabel229.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel229.setForeground(new java.awt.Color(46, 56, 77));
        jLabel229.setText("Sección");

        btnFiltrarLista1.setBackground(new java.awt.Color(46, 91, 255));
        btnFiltrarLista1.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        btnFiltrarLista1.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltrarLista1.setText("Filtrar Lista");
        btnFiltrarLista1.setToolTipText("Buscar un proyecto");
        btnFiltrarLista1.setBorderPainted(false);
        btnFiltrarLista1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarLista1ActionPerformed(evt);
            }
        });

        jLabel230.setText("240 en total ");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel214, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel230, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(57, 57, 57)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel228)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel215)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel229)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(btnFiltrarLista1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregarEspecialidad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 808, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel214)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel230))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel228)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel215)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnFiltrarLista1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel229)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAgregarEspecialidad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout pnlProyectosLayout = new javax.swing.GroupLayout(pnlProyectos);
        pnlProyectos.setLayout(pnlProyectosLayout);
        pnlProyectosLayout.setHorizontalGroup(
            pnlProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProyectosLayout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        pnlProyectosLayout.setVerticalGroup(
            pnlProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProyectosLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pnlCardLayoutAdmin.add(pnlProyectos, "Proyectos");

        pnlUbicaciones.setBackground(new java.awt.Color(244, 246, 252));

        jPanel27.setBackground(new java.awt.Color(244, 246, 252));

        jLabel98.setFont(new java.awt.Font("Rubik Light", 0, 22)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(46, 56, 77));
        jLabel98.setText("Ubicaciones");

        jLabel160.setFont(new java.awt.Font("Rubik", 0, 18)); // NOI18N
        jLabel160.setForeground(new java.awt.Color(46, 56, 77));
        jLabel160.setText("Especialidades");

        jLabel161.setFont(new java.awt.Font("Rubik", 0, 18)); // NOI18N
        jLabel161.setForeground(new java.awt.Color(46, 56, 77));
        jLabel161.setText("Niveles");

        btnAgregarNivel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Button_Add.png"))); // NOI18N
        btnAgregarNivel.setToolTipText("Agregar un nuevo nivel");
        btnAgregarNivel.setBorder(null);
        btnAgregarNivel.setBorderPainted(false);
        btnAgregarNivel.setContentAreaFilled(false);
        btnAgregarNivel.setPreferredSize(new java.awt.Dimension(52, 52));
        btnAgregarNivel.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/ActiveButton_Add.png"))); // NOI18N
        btnAgregarNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarNivelActionPerformed(evt);
            }
        });

        btnAgregarEspecialidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Button_Add.png"))); // NOI18N
        btnAgregarEspecialidad.setToolTipText("Agregar una nueva especialidad");
        btnAgregarEspecialidad.setBorder(null);
        btnAgregarEspecialidad.setBorderPainted(false);
        btnAgregarEspecialidad.setContentAreaFilled(false);
        btnAgregarEspecialidad.setPreferredSize(new java.awt.Dimension(52, 52));
        btnAgregarEspecialidad.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/ActiveButton_Add.png"))); // NOI18N
        btnAgregarEspecialidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEspecialidadActionPerformed(evt);
            }
        });

        jLabel21.setBackground(new java.awt.Color(244, 246, 252));
        jLabel21.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(191, 197, 210));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("ID");

        jLabel22.setBackground(new java.awt.Color(244, 246, 252));
        jLabel22.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(191, 197, 210));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Niveles");

        jLabel28.setBackground(new java.awt.Color(244, 246, 252));
        jLabel28.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(191, 197, 210));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("ID");

        jLabel29.setBackground(new java.awt.Color(244, 246, 252));
        jLabel29.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(191, 197, 210));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Especialidades");

        btnModificarSeccionModal.setBackground(new java.awt.Color(213, 222, 255));
        btnModificarSeccionModal.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        btnModificarSeccionModal.setForeground(new java.awt.Color(46, 91, 255));
        btnModificarSeccionModal.setText("Secciones");
        btnModificarSeccionModal.setBorderPainted(false);
        btnModificarSeccionModal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarSeccionModalActionPerformed(evt);
            }
        });

        jPanel18.setLayout(new java.awt.GridLayout(0, 1, 0, 15));
        jScrollPane4.setViewportView(jPanel18);

        jPanel17.setLayout(new java.awt.GridLayout(0, 1, 0, 15));
        jScrollPane5.setViewportView(jPanel17);

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(btnModificarSeccionModal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregarNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel161)
                        .addGap(322, 322, 322)))
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                                .addComponent(jLabel160)
                                .addGap(225, 225, 225))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                                .addComponent(btnAgregarEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addGap(268, 268, 268)
                .addComponent(jLabel22)
                .addGap(143, 143, 143)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel29)
                .addGap(72, 72, 72))
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel98)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(jLabel98)
                .addGap(29, 29, 29)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel161)
                    .addComponent(jLabel160))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addGap(7, 7, 7)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarEspecialidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btnModificarSeccionModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57))
        );

        javax.swing.GroupLayout pnlUbicacionesLayout = new javax.swing.GroupLayout(pnlUbicaciones);
        pnlUbicaciones.setLayout(pnlUbicacionesLayout);
        pnlUbicacionesLayout.setHorizontalGroup(
            pnlUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUbicacionesLayout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        pnlUbicacionesLayout.setVerticalGroup(
            pnlUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUbicacionesLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pnlCardLayoutAdmin.add(pnlUbicaciones, "Ubicaciones");

        pnlSeccion.setBackground(new java.awt.Color(244, 246, 252));

        jPanel113.setBackground(new java.awt.Color(244, 246, 252));

        jLabel209.setFont(new java.awt.Font("Rubik Light", 0, 22)); // NOI18N
        jLabel209.setForeground(new java.awt.Color(46, 56, 77));
        jLabel209.setText("Secciones de 1º Año de Bachillerato");

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

        jPanel24.setLayout(new java.awt.GridLayout(0, 1, 0, 15));
        jScrollPane3.setViewportView(jPanel24);

        javax.swing.GroupLayout jPanel113Layout = new javax.swing.GroupLayout(jPanel113);
        jPanel113.setLayout(jPanel113Layout);
        jPanel113Layout.setHorizontalGroup(
            jPanel113Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel113Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(btnAgregarSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(jPanel113Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel209)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel113Layout.setVerticalGroup(
            jPanel113Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel113Layout.createSequentialGroup()
                .addComponent(jLabel209)
                .addGroup(jPanel113Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel113Layout.createSequentialGroup()
                        .addGap(430, 430, 430)
                        .addComponent(btnAgregarSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel113Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jLabel210.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconBack.png"))); // NOI18N
        jLabel210.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel210MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlSeccionLayout = new javax.swing.GroupLayout(pnlSeccion);
        pnlSeccion.setLayout(pnlSeccionLayout);
        pnlSeccionLayout.setHorizontalGroup(
            pnlSeccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSeccionLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(jLabel210)
                .addGap(0, 0, 0)
                .addComponent(jPanel113, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        pnlSeccionLayout.setVerticalGroup(
            pnlSeccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSeccionLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(pnlSeccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel210)
                    .addComponent(jPanel113, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnlCardLayoutAdmin.add(pnlSeccion, "Seccion");

        pnlAjustes.setBorder(null);
        pnlAjustes.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pnlAjustes.setPreferredSize(new java.awt.Dimension(944, 570));

        jPanel2.setBackground(new java.awt.Color(244, 246, 252));
        jPanel2.setPreferredSize(new java.awt.Dimension(944, 1450));

        jLabel2.setFont(new java.awt.Font("Rubik Light", 0, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(46, 56, 77));
        jLabel2.setText("Ajustes de la cuenta");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel3.setPreferredSize(new java.awt.Dimension(600, 303));

        jLabel3.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(135, 152, 173));
        jLabel3.setText("DATOS PERSONALES");

        jLabel4.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(46, 56, 77));
        jLabel4.setText("Usa esta página para actualizar tu información de contacto y cambiar tu contraseña.");

        txtAjustesEmail.setBackground(new java.awt.Color(249, 250, 255));
        txtAjustesEmail.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtAjustesEmail.setForeground(new java.awt.Color(46, 56, 77));
        txtAjustesEmail.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));

        jLabel5.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(176, 186, 201));
        jLabel5.setText("CORREO ELECTRÓNICO");

        jLabel6.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(176, 186, 201));
        jLabel6.setText("CONTRASEÑA ACTUAL");

        txtAjustesContraA.setBackground(new java.awt.Color(249, 250, 255));
        txtAjustesContraA.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtAjustesContraA.setForeground(new java.awt.Color(46, 56, 77));
        txtAjustesContraA.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtAjustesContraA.setEchoChar('\u2022');
        txtAjustesContraA.setPreferredSize(new java.awt.Dimension(188, 28));

        jLabel7.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(176, 186, 201));
        jLabel7.setText("NOMBRE COMPLETO");

        txtAjustesNombre.setBackground(new java.awt.Color(249, 250, 255));
        txtAjustesNombre.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtAjustesNombre.setForeground(new java.awt.Color(46, 56, 77));
        txtAjustesNombre.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));

        txtAjustesContraN.setBackground(new java.awt.Color(249, 250, 255));
        txtAjustesContraN.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtAjustesContraN.setForeground(new java.awt.Color(46, 56, 77));
        txtAjustesContraN.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtAjustesContraN.setEchoChar('\u2022');
        txtAjustesContraN.setPreferredSize(new java.awt.Dimension(188, 28));

        jLabel8.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(176, 186, 201));
        jLabel8.setText("NUEVA CONTRASEÑA");

        txtAjustesContraNC.setBackground(new java.awt.Color(249, 250, 255));
        txtAjustesContraNC.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtAjustesContraNC.setForeground(new java.awt.Color(46, 56, 77));
        txtAjustesContraNC.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtAjustesContraNC.setEchoChar('\u2022');
        txtAjustesContraNC.setPreferredSize(new java.awt.Dimension(188, 28));

        jLabel9.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(176, 186, 201));
        jLabel9.setText("CONFIRMAR CONTRASEÑA");

        btnActualizarPerfil.setBackground(new java.awt.Color(46, 91, 255));
        btnActualizarPerfil.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        btnActualizarPerfil.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarPerfil.setText("Guardar Cambios");
        btnActualizarPerfil.setBorder(null);
        btnActualizarPerfil.setBorderPainted(false);
        btnActualizarPerfil.setPreferredSize(new java.awt.Dimension(138, 28));
        btnActualizarPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarPerfilActionPerformed(evt);
            }
        });

        lblAErrorEmail.setFont(new java.awt.Font("Rubik Light", 0, 11)); // NOI18N
        lblAErrorEmail.setForeground(new java.awt.Color(255, 51, 51));

        lblAErrorNombre.setFont(new java.awt.Font("Rubik Light", 0, 11)); // NOI18N
        lblAErrorNombre.setForeground(new java.awt.Color(255, 51, 51));

        lblAErrorContra.setFont(new java.awt.Font("Rubik Light", 0, 11)); // NOI18N
        lblAErrorContra.setForeground(new java.awt.Color(255, 51, 51));

        lblAErrorContraN.setFont(new java.awt.Font("Rubik Light", 0, 11)); // NOI18N
        lblAErrorContraN.setForeground(new java.awt.Color(255, 51, 51));

        lblAErrorContraNC.setFont(new java.awt.Font("Rubik Light", 0, 11)); // NOI18N
        lblAErrorContraNC.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAErrorNombre)
                    .addComponent(lblAErrorEmail)
                    .addComponent(btnActualizarPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAjustesNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(90, 90, 90))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtAjustesContraA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAjustesEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblAErrorContra))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblAErrorContraN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAjustesContraN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAErrorContraNC)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtAjustesContraNC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addContainerGap(177, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(1, 1, 1)
                        .addComponent(txtAjustesContraNC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(1, 1, 1)
                        .addComponent(txtAjustesEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAErrorEmail)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(1, 1, 1)
                        .addComponent(txtAjustesNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAErrorNombre)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(1, 1, 1)
                                .addComponent(txtAjustesContraA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(1, 1, 1)
                                .addComponent(txtAjustesContraN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAErrorContra)
                    .addComponent(lblAErrorContraN)
                    .addComponent(lblAErrorContraNC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(btnActualizarPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel16.setPreferredSize(new java.awt.Dimension(600, 303));

        jLabel23.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(135, 152, 173));
        jLabel23.setText("FOTO DE PERFIL");

        jLabel66.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(46, 56, 77));
        jLabel66.setText("Actualiza o elimina tu foto de perfil.");

        btnCambiarFotoPerfil.setBackground(new java.awt.Color(238, 238, 238));
        btnCambiarFotoPerfil.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        btnCambiarFotoPerfil.setForeground(new java.awt.Color(107, 107, 107));
        btnCambiarFotoPerfil.setText("Cambiar Foto");
        btnCambiarFotoPerfil.setBorderPainted(false);
        btnCambiarFotoPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarFotoPerfilActionPerformed(evt);
            }
        });

        btnEliminarFotoPerfil.setBackground(new java.awt.Color(238, 238, 238));
        btnEliminarFotoPerfil.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        btnEliminarFotoPerfil.setForeground(new java.awt.Color(107, 107, 107));
        btnEliminarFotoPerfil.setText("Eliminar Foto");
        btnEliminarFotoPerfil.setBorderPainted(false);
        btnEliminarFotoPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarFotoPerfilActionPerformed(evt);
            }
        });

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(193, 193, 193)));
        jPanel23.setPreferredSize(new java.awt.Dimension(130, 130));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFotoPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/profilePicture.png"))); // NOI18N
        jPanel23.add(lblFotoPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, -1, -1));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCambiarFotoPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(btnEliminarFotoPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(469, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel66)
                            .addComponent(jLabel23))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel23)
                .addGap(25, 25, 25)
                .addComponent(jLabel66)
                .addGap(25, 25, 25)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(btnCambiarFotoPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarFotoPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jLabel81.setFont(new java.awt.Font("Rubik Light", 0, 22)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(46, 56, 77));
        jLabel81.setText("Ajustes del sistema");

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));
        jPanel43.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel43.setPreferredSize(new java.awt.Dimension(804, 221));

        jLabel82.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(135, 152, 173));
        jLabel82.setText("RESTAURAR BACKUP");

        jLabel83.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(46, 56, 77));
        jLabel83.setText("<html>Selecciona el archivo .zip del respaldo, ten presente que se eliminaran todos los datos actuales de las tablas que seleccionaste<br> al generar el backup.</html>");

        jButton8.setBackground(new java.awt.Color(238, 238, 238));
        jButton8.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jButton8.setForeground(new java.awt.Color(107, 107, 107));
        jButton8.setText("Seleccionar...");
        jButton8.setBorderPainted(false);

        jButton9.setBackground(new java.awt.Color(46, 91, 255));
        jButton9.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Restaurar");
        jButton9.setBorderPainted(false);

        checkCombinar.setBackground(new java.awt.Color(255, 255, 255));
        checkCombinar.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        checkCombinar.setText("Intentar combinar datos actuales con el respaldo.");
        checkCombinar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCombinarActionPerformed(evt);
            }
        });

        lblAjustesBackup.setBackground(new java.awt.Color(255, 255, 255));
        lblAjustesBackup.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        lblAjustesBackup.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAjustesBackup)
                    .addComponent(checkCombinar)
                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel82)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel82)
                .addGap(25, 25, 25)
                .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(checkCombinar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAjustesBackup)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel143.setBackground(new java.awt.Color(255, 255, 255));
        jPanel143.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));
        jPanel143.setPreferredSize(new java.awt.Dimension(387, 275));

        jLabel264.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jLabel264.setForeground(new java.awt.Color(135, 152, 173));
        jLabel264.setText("AÑADIR UBICACIONES DE MAPWIZE A LA BASE DE DATOS");

        txtAPIKey.setBackground(new java.awt.Color(249, 250, 255));
        txtAPIKey.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtAPIKey.setForeground(new java.awt.Color(46, 56, 77));
        txtAPIKey.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));

        jLabel17.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(176, 186, 201));
        jLabel17.setText("API KEY DE MAPWIZE");

        txtVenueID.setBackground(new java.awt.Color(249, 250, 255));
        txtVenueID.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtVenueID.setForeground(new java.awt.Color(46, 56, 77));
        txtVenueID.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));

        jLabel18.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(176, 186, 201));
        jLabel18.setText("VENUE ID");

        btnLoadPlaces.setBackground(new java.awt.Color(46, 91, 255));
        btnLoadPlaces.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        btnLoadPlaces.setForeground(new java.awt.Color(255, 255, 255));
        btnLoadPlaces.setText("Cargar ubicaciones");
        btnLoadPlaces.setBorder(null);
        btnLoadPlaces.setBorderPainted(false);
        btnLoadPlaces.setPreferredSize(new java.awt.Dimension(138, 28));
        btnLoadPlaces.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadPlacesActionPerformed(evt);
            }
        });

        lblAErrorPlaces.setFont(new java.awt.Font("Rubik Light", 0, 11)); // NOI18N
        lblAErrorPlaces.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel143Layout = new javax.swing.GroupLayout(jPanel143);
        jPanel143.setLayout(jPanel143Layout);
        jPanel143Layout.setHorizontalGroup(
            jPanel143Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel143Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel143Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel143Layout.createSequentialGroup()
                        .addGroup(jPanel143Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel264)
                            .addComponent(txtAPIKey, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(66, Short.MAX_VALUE))
                    .addGroup(jPanel143Layout.createSequentialGroup()
                        .addGroup(jPanel143Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAErrorPlaces, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(txtVenueID, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(btnLoadPlaces, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel143Layout.setVerticalGroup(
            jPanel143Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel143Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel264)
                .addGap(25, 25, 25)
                .addComponent(jLabel17)
                .addGap(1, 1, 1)
                .addComponent(txtAPIKey, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel18)
                .addGap(1, 1, 1)
                .addComponent(txtVenueID, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAErrorPlaces, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLoadPlaces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        jPanel144.setBackground(new java.awt.Color(255, 255, 255));
        jPanel144.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));
        jPanel144.setPreferredSize(new java.awt.Dimension(387, 275));

        jLabel265.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jLabel265.setForeground(new java.awt.Color(135, 152, 173));
        jLabel265.setText("GESTIÓN DE TABLAS CATÁLOGO");

        jLabel84.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(46, 56, 77));
        jLabel84.setText("Selecciona la tabla a gestionar.");

        jrbEstado.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupTablasCatalogo.add(jrbEstado);
        jrbEstado.setText("Estados de usuario");

        jrbTipo.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupTablasCatalogo.add(jrbTipo);
        jrbTipo.setText("Tipos de usuario");

        jrbCriterio.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupTablasCatalogo.add(jrbCriterio);
        jrbCriterio.setText("Criterios de votacion");

        jrbAccion.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupTablasCatalogo.add(jrbAccion);
        jrbAccion.setText("Acciones de bitácora");

        btnTablasCatalogo.setBackground(new java.awt.Color(46, 91, 255));
        btnTablasCatalogo.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        btnTablasCatalogo.setForeground(new java.awt.Color(255, 255, 255));
        btnTablasCatalogo.setText("Siguiente");
        btnTablasCatalogo.setBorder(null);
        btnTablasCatalogo.setBorderPainted(false);
        btnTablasCatalogo.setPreferredSize(new java.awt.Dimension(138, 28));
        btnTablasCatalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablasCatalogoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel144Layout = new javax.swing.GroupLayout(jPanel144);
        jPanel144.setLayout(jPanel144Layout);
        jPanel144Layout.setHorizontalGroup(
            jPanel144Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel144Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel144Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTablasCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel144Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel144Layout.createSequentialGroup()
                            .addGroup(jPanel144Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel84)
                                .addComponent(jLabel265)
                                .addGroup(jPanel144Layout.createSequentialGroup()
                                    .addComponent(jrbEstado)
                                    .addGap(32, 32, 32)
                                    .addComponent(jrbTipo)))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel144Layout.createSequentialGroup()
                            .addComponent(jrbCriterio)
                            .addGap(18, 18, 18)
                            .addComponent(jrbAccion)
                            .addGap(88, 88, 88)))))
        );
        jPanel144Layout.setVerticalGroup(
            jPanel144Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel144Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel265)
                .addGap(25, 25, 25)
                .addComponent(jLabel84)
                .addGap(18, 18, 18)
                .addGroup(jPanel144Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbEstado)
                    .addComponent(jrbTipo))
                .addGap(14, 14, 14)
                .addGroup(jPanel144Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbCriterio)
                    .addComponent(jrbAccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(btnTablasCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel81)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jPanel143, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel144, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel81)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jPanel143, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel144, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        pnlAjustes.setViewportView(jPanel2);

        pnlCardLayoutAdmin.add(pnlAjustes, "Ajustes");

        pnlActividades.setBackground(new java.awt.Color(244, 246, 252));
        pnlActividades.setPreferredSize(new java.awt.Dimension(944, 590));

        pnlInnerActividades.setBackground(new java.awt.Color(244, 246, 252));

        jLabel109.setFont(new java.awt.Font("Rubik Light", 0, 22)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(46, 56, 77));
        jLabel109.setText("Actividades");

        jPanel26.setBackground(new java.awt.Color(244, 246, 252));
        jPanel26.setPreferredSize(new java.awt.Dimension(600, 303));

        btnAgregarActividad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Button_Add.png"))); // NOI18N
        btnAgregarActividad.setToolTipText("Agregar una nueva actividad");
        btnAgregarActividad.setBorder(null);
        btnAgregarActividad.setBorderPainted(false);
        btnAgregarActividad.setContentAreaFilled(false);
        btnAgregarActividad.setPreferredSize(new java.awt.Dimension(52, 52));
        btnAgregarActividad.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/ActiveButton_Add.png"))); // NOI18N
        btnAgregarActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActividadActionPerformed(evt);
            }
        });

        jpnlDia2.setBackground(new java.awt.Color(244, 246, 252));
        jpnlDia2.setBorder(null);
        jpnlDia2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jpnlDia2.setPreferredSize(new java.awt.Dimension(158, 325));

        jPanel12.setBackground(new java.awt.Color(244, 246, 252));
        jPanel12.setMinimumSize(new java.awt.Dimension(0, 15));
        jPanel12.setLayout(new java.awt.GridLayout(0, 1, 10, 11));
        jpnlDia2.setViewportView(jPanel12);

        jpnlDia3.setBackground(new java.awt.Color(244, 246, 252));
        jpnlDia3.setBorder(null);
        jpnlDia3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jpnlDia3.setPreferredSize(new java.awt.Dimension(167, 325));

        jPanel13.setBackground(new java.awt.Color(244, 246, 252));
        jPanel13.setMinimumSize(new java.awt.Dimension(0, 15));
        jPanel13.setLayout(new java.awt.GridLayout(0, 1, 10, 11));
        jpnlDia3.setViewportView(jPanel13);

        jpnlDia4.setBackground(new java.awt.Color(244, 246, 252));
        jpnlDia4.setBorder(null);
        jpnlDia4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jpnlDia4.setPreferredSize(new java.awt.Dimension(167, 325));

        jPanel14.setBackground(new java.awt.Color(244, 246, 252));
        jPanel14.setMinimumSize(new java.awt.Dimension(0, 15));
        jPanel14.setLayout(new java.awt.GridLayout(0, 1, 10, 11));
        jpnlDia4.setViewportView(jPanel14);

        jpnlDia5.setBackground(new java.awt.Color(244, 246, 252));
        jpnlDia5.setBorder(null);
        jpnlDia5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jpnlDia5.setPreferredSize(new java.awt.Dimension(167, 325));

        jPanel15.setBackground(new java.awt.Color(244, 246, 252));
        jPanel15.setMinimumSize(new java.awt.Dimension(0, 15));
        jPanel15.setLayout(new java.awt.GridLayout(0, 1, 10, 11));
        jpnlDia5.setViewportView(jPanel15);

        jPanel25.setBackground(new java.awt.Color(244, 246, 252));

        lblFechaDia1.setFont(new java.awt.Font("Rubik Medium", 0, 9)); // NOI18N
        lblFechaDia1.setForeground(new java.awt.Color(176, 186, 201));
        lblFechaDia1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFechaDia1.setText("(25/09/2019)");

        jPanel28.setBackground(new java.awt.Color(244, 246, 252));

        lblCantidadActividades1.setFont(new java.awt.Font("Rubik Medium", 0, 9)); // NOI18N
        lblCantidadActividades1.setForeground(new java.awt.Color(46, 91, 255));
        lblCantidadActividades1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantidadActividades1.setText("0");
        lblCantidadActividades1.setToolTipText("Cantidad de actividades");
        lblCantidadActividades1.setPreferredSize(new java.awt.Dimension(24, 24));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblCantidadActividades1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblCantidadActividades1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblDia1.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblDia1.setForeground(new java.awt.Color(176, 186, 201));
        lblDia1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDia1.setText("MIÉRCOLES");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(lblDia1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFechaDia1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFechaDia1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lblDia1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel53.setBackground(new java.awt.Color(244, 246, 252));

        lblFechaDia2.setFont(new java.awt.Font("Rubik Medium", 0, 9)); // NOI18N
        lblFechaDia2.setForeground(new java.awt.Color(176, 186, 201));
        lblFechaDia2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFechaDia2.setText("(26/09/2019)");

        jPanel54.setBackground(new java.awt.Color(244, 246, 252));

        lblCantidadActividades2.setFont(new java.awt.Font("Rubik Medium", 0, 9)); // NOI18N
        lblCantidadActividades2.setForeground(new java.awt.Color(140, 84, 255));
        lblCantidadActividades2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantidadActividades2.setText("0");
        lblCantidadActividades2.setToolTipText("Cantidad de actividades");
        lblCantidadActividades2.setPreferredSize(new java.awt.Dimension(24, 24));

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblCantidadActividades2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblCantidadActividades2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblDia2.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblDia2.setForeground(new java.awt.Color(176, 186, 201));
        lblDia2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDia2.setText("JUEVES");

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addComponent(lblDia2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFechaDia2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lblDia2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblFechaDia2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel55.setBackground(new java.awt.Color(244, 246, 252));

        lblFechaDia3.setFont(new java.awt.Font("Rubik Medium", 0, 9)); // NOI18N
        lblFechaDia3.setForeground(new java.awt.Color(176, 186, 201));
        lblFechaDia3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFechaDia3.setText("(27/09/2019)");

        jPanel56.setBackground(new java.awt.Color(244, 246, 252));

        lblCantidadActividades3.setFont(new java.awt.Font("Rubik Medium", 0, 9)); // NOI18N
        lblCantidadActividades3.setForeground(new java.awt.Color(44, 194, 165));
        lblCantidadActividades3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantidadActividades3.setText("0");
        lblCantidadActividades3.setToolTipText("Cantidad de actividades");
        lblCantidadActividades3.setPreferredSize(new java.awt.Dimension(24, 24));

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel56Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblCantidadActividades3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel56Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblCantidadActividades3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblDia3.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblDia3.setForeground(new java.awt.Color(176, 186, 201));
        lblDia3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDia3.setText("VIERNES");

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addComponent(lblDia3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFechaDia3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lblDia3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblFechaDia3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel57.setBackground(new java.awt.Color(244, 246, 252));

        lblFechaDia4.setFont(new java.awt.Font("Rubik Medium", 0, 9)); // NOI18N
        lblFechaDia4.setForeground(new java.awt.Color(176, 186, 201));
        lblFechaDia4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFechaDia4.setText("(28/09/2019)");

        jPanel58.setBackground(new java.awt.Color(244, 246, 252));

        lblCantidadActividades4.setFont(new java.awt.Font("Rubik Medium", 0, 9)); // NOI18N
        lblCantidadActividades4.setForeground(new java.awt.Color(247, 193, 55));
        lblCantidadActividades4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantidadActividades4.setText("0");
        lblCantidadActividades4.setToolTipText("Cantidad de actividades");
        lblCantidadActividades4.setPreferredSize(new java.awt.Dimension(24, 24));

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCantidadActividades4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel58Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblCantidadActividades4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblDia4.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblDia4.setForeground(new java.awt.Color(176, 186, 201));
        lblDia4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDia4.setText("SABADO");

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addComponent(lblDia4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFechaDia4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lblDia4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblFechaDia4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel59.setBackground(new java.awt.Color(244, 246, 252));

        lblFechaDia5.setFont(new java.awt.Font("Rubik Medium", 0, 9)); // NOI18N
        lblFechaDia5.setForeground(new java.awt.Color(176, 186, 201));
        lblFechaDia5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFechaDia5.setText("(29/09/2019)");

        jPanel60.setBackground(new java.awt.Color(244, 246, 252));

        lblCantidadActividades5.setFont(new java.awt.Font("Rubik Medium", 0, 9)); // NOI18N
        lblCantidadActividades5.setForeground(new java.awt.Color(51, 172, 46));
        lblCantidadActividades5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantidadActividades5.setText("0");
        lblCantidadActividades5.setToolTipText("Cantidad de actividades");
        lblCantidadActividades5.setPreferredSize(new java.awt.Dimension(24, 24));

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel60Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblCantidadActividades5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel60Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblCantidadActividades5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblDia5.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblDia5.setForeground(new java.awt.Color(176, 186, 201));
        lblDia5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDia5.setText("DOMINGO");

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addComponent(lblDia5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFechaDia5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lblDia5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblFechaDia5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setMinimumSize(new java.awt.Dimension(166, 323));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(100, 120));

        jPanel4.setBackground(new java.awt.Color(244, 246, 252));
        jPanel4.setMinimumSize(new java.awt.Dimension(0, 15));
        jPanel4.setLayout(new java.awt.GridLayout(0, 1, 10, 11));
        jScrollPane2.setViewportView(jPanel4);
        jPanel4.getAccessibleContext().setAccessibleName("");
        jPanel4.getAccessibleContext().setAccessibleDescription("");

        btnEliminarActividades.setBackground(new java.awt.Color(247, 214, 218));
        btnEliminarActividades.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        btnEliminarActividades.setForeground(new java.awt.Color(214, 54, 73));
        btnEliminarActividades.setText("Eliminar actividades");
        btnEliminarActividades.setToolTipText("Eliminar todas las actividades");
        btnEliminarActividades.setBorderPainted(false);
        btnEliminarActividades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActividadesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnlDia2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnlDia3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnEliminarActividades)
                        .addComponent(jpnlDia4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jpnlDia5, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(btnAgregarActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnlDia5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnlDia2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnlDia3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnlDia4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAgregarActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarActividades, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout pnlInnerActividadesLayout = new javax.swing.GroupLayout(pnlInnerActividades);
        pnlInnerActividades.setLayout(pnlInnerActividadesLayout);
        pnlInnerActividadesLayout.setHorizontalGroup(
            pnlInnerActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInnerActividadesLayout.createSequentialGroup()
                .addComponent(jLabel109)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlInnerActividadesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, 887, Short.MAX_VALUE))
        );
        pnlInnerActividadesLayout.setVerticalGroup(
            pnlInnerActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInnerActividadesLayout.createSequentialGroup()
                .addComponent(jLabel109)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlActividadesLayout = new javax.swing.GroupLayout(pnlActividades);
        pnlActividades.setLayout(pnlActividadesLayout);
        pnlActividadesLayout.setHorizontalGroup(
            pnlActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlActividadesLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(pnlInnerActividades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlActividadesLayout.setVerticalGroup(
            pnlActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlActividadesLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(pnlInnerActividades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCardLayoutAdmin.add(pnlActividades, "Actividades");

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)
                    .addComponent(pnlCardLayoutAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)))
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addComponent(pnlSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlCardLayoutAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE))
        );

        getContentPane().add(pnlBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void cargarActividades() {
        jPanel4.removeAll();
        jPanel12.removeAll();
        jPanel13.removeAll();
        jPanel14.removeAll();
        jPanel15.removeAll();

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
        actividades.CrearPanelesActividades(jPanel4, fecha1Inicio, CurrentUser.edicionExpotecnica, fecha1Fin, contador);
        lblCantidadActividades1.setText(String.valueOf(actividades.getCantidadDia1()));
        contador++;

        actividades.CrearPanelesActividades(jPanel12, fecha2Inicio, CurrentUser.edicionExpotecnica, fecha2Fin, contador);
        lblCantidadActividades2.setText(String.valueOf(actividades.getCantidadDia1()));
        contador++;

        actividades.CrearPanelesActividades(jPanel13, fecha3Inicio, CurrentUser.edicionExpotecnica, fecha3Fin, contador);
        lblCantidadActividades3.setText(String.valueOf(actividades.getCantidadDia1()));
        contador++;

        actividades.CrearPanelesActividades(jPanel14, fecha4Inicio, CurrentUser.edicionExpotecnica, fecha4Fin, contador);
        lblCantidadActividades4.setText(String.valueOf(actividades.getCantidadDia1()));
        contador++;

        actividades.CrearPanelesActividades(jPanel15, fecha5Inicio, CurrentUser.edicionExpotecnica, fecha5Fin, contador);
        lblCantidadActividades5.setText(String.valueOf(actividades.getCantidadDia1()));
//        actividades.resetearIdioma();
    }

    private void cargarEdiciones() {
        int edicion = 2019;
        for (int i = 0; i < 17; i++) {
            cbEdicionExpotecnica.addItem(String.valueOf(edicion));
            edicion++;
        }
        cbEdicionExpotecnica.setSelectedItem(CurrentUser.edicionExpotecnica);
    }

    private void eliminarActividades() {
        try {
            Activities acti = new Activities();
            boolean bandera = acti.eliminarActividades();
            cargarActividades();
            if (bandera = true) {
                JOptionPane.showMessageDialog(null, "Actividades eliminadas correctamente", "Actividades", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar las actividades", "Actividades", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        if (pnlMenu.getWidth() == 150) {
            pnlMenu.setSize(56, pnlMenu.getHeight());
        } else if (pnlMenu.getWidth() == 56) {
            pnlMenu.setSize(150, pnlMenu.getHeight());
        }
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseClicked
        makeActiveMenuItem(btnInicio, pnlActiveInicio, lblDashboard, "Dashboard");
    }//GEN-LAST:event_btnInicioMouseClicked

    private void btnAnaliticasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAnaliticasMouseClicked
        makeActiveMenuItem(btnAnaliticas, pnlActiveAnaliticas, lblAnaliticas, "Analiticas");
    }//GEN-LAST:event_btnAnaliticasMouseClicked

    private void btnAnaliticasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAnaliticasMouseEntered
        if (pnlActiveAnaliticas.getBackground() != colorActive) {
            lblAnaliticas.setIcon(iconAnaliticasActive);
            lblAnaliticas.setForeground(colorActive);
        }
    }//GEN-LAST:event_btnAnaliticasMouseEntered

    private void btnAnaliticasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAnaliticasMouseExited
        if (pnlActiveAnaliticas.getBackground() != colorActive) {
            lblAnaliticas.setIcon(iconAnaliticas);
            lblAnaliticas.setForeground(colorNormal);
        }
    }//GEN-LAST:event_btnAnaliticasMouseExited

    private void btnInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseEntered
        if (pnlActiveInicio.getBackground() != colorActive) {
            lblDashboard.setIcon(iconDashboardActive);
            lblDashboard.setForeground(colorActive);
        }
    }//GEN-LAST:event_btnInicioMouseEntered

    private void btnInicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseExited
        if (pnlActiveInicio.getBackground() != colorActive) {
            lblDashboard.setIcon(iconDashboard);
            lblDashboard.setForeground(colorNormal);
        }
    }//GEN-LAST:event_btnInicioMouseExited

    private void btnFiltrarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarListaActionPerformed

    }//GEN-LAST:event_btnFiltrarListaActionPerformed

    private void btnAgregarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUsuarioActionPerformed
        ModalNuevoUsuario ModalUsuario = new ModalNuevoUsuario();

        JDialog Modal = new JDialog(this, "Nuevo Usuario", true);
        Modal.getContentPane().add(ModalUsuario);
        Modal.pack();
        Modal.setLocationRelativeTo(null);
        Modal.setVisible(true);
    }//GEN-LAST:event_btnAgregarUsuarioActionPerformed

    private void btnAceptarModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarModalActionPerformed
        modalUsuario.dispose();
        resetearModalUsuario();
    }//GEN-LAST:event_btnAceptarModalActionPerformed

    private void btnUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUsuariosMouseClicked
        makeActiveMenuItem(btnUsuarios, pnlActiveUsuarios, lblUsuarios, "Usuarios");
    }//GEN-LAST:event_btnUsuariosMouseClicked

    private void btnUsuariosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUsuariosMouseEntered
        if (pnlActiveUsuarios.getBackground() != colorActive) {
            lblUsuarios.setIcon(iconUsuariosActive);
            lblUsuarios.setForeground(colorActive);
        }
    }//GEN-LAST:event_btnUsuariosMouseEntered

    private void btnUsuariosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUsuariosMouseExited
        if (pnlActiveUsuarios.getBackground() != colorActive) {
            lblUsuarios.setIcon(iconUsuarios);
            lblUsuarios.setForeground(colorNormal);
        }
    }//GEN-LAST:event_btnUsuariosMouseExited

    private void btnProyectosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProyectosMouseClicked
        makeActiveMenuItem(btnProyectos, pnlActiveProyectos, lblProyectos, "Proyectos");
    }//GEN-LAST:event_btnProyectosMouseClicked

    private void btnProyectosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProyectosMouseEntered
        if (pnlActiveProyectos.getBackground() != colorActive) {
            lblProyectos.setIcon(iconProyectosActive);
            lblProyectos.setForeground(colorActive);
        }
    }//GEN-LAST:event_btnProyectosMouseEntered

    private void btnProyectosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProyectosMouseExited
        if (pnlActiveProyectos.getBackground() != colorActive) {
            lblProyectos.setIcon(iconProyectos);
            lblProyectos.setForeground(colorNormal);
        }
    }//GEN-LAST:event_btnProyectosMouseExited

    private void btnUbicacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUbicacionesMouseClicked
        makeActiveMenuItem(btnUbicaciones, pnlActiveUbicaciones, lblUbicaciones, "Ubicaciones");

    }//GEN-LAST:event_btnUbicacionesMouseClicked

    private void btnUbicacionesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUbicacionesMouseEntered
        if (pnlActiveUbicaciones.getBackground() != colorActive) {
            lblUbicaciones.setIcon(iconUbicacionesActive);
            lblUbicaciones.setForeground(colorActive);
        }
    }//GEN-LAST:event_btnUbicacionesMouseEntered

    private void btnUbicacionesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUbicacionesMouseExited
        if (pnlActiveUbicaciones.getBackground() != colorActive) {
            lblUbicaciones.setIcon(iconUbicaciones);
            lblUbicaciones.setForeground(colorNormal);
        }
    }//GEN-LAST:event_btnUbicacionesMouseExited

    private void btnActividadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActividadesMouseClicked
//        cargarActividades();
        makeActiveMenuItem(btnActividades, pnlActiveActividades, lblActividades, "Actividades");
    }//GEN-LAST:event_btnActividadesMouseClicked

    private void btnActividadesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActividadesMouseEntered
        if (pnlActiveActividades.getBackground() != colorActive) {
            lblActividades.setIcon(iconActividadesActive);
            lblActividades.setForeground(colorActive);
        }
    }//GEN-LAST:event_btnActividadesMouseEntered

    private void btnActividadesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActividadesMouseExited
        if (pnlActiveActividades.getBackground() != colorActive) {
            lblActividades.setIcon(iconActividades);
            lblActividades.setForeground(colorNormal);
        }
    }//GEN-LAST:event_btnActividadesMouseExited

    private void btnAjustesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAjustesMouseClicked
        loadAjustes();
        makeActiveMenuItem(btnAjustes, pnlActiveAjustes, lblAjustes, "Ajustes");
    }//GEN-LAST:event_btnAjustesMouseClicked

    private void btnAjustesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAjustesMouseEntered
        if (pnlActiveAjustes.getBackground() != colorActive) {
            lblAjustes.setIcon(iconAjustesActive);
            lblAjustes.setForeground(colorActive);
        }
    }//GEN-LAST:event_btnAjustesMouseEntered

    private void btnAjustesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAjustesMouseExited
        if (pnlActiveAjustes.getBackground() != colorActive) {
            lblAjustes.setIcon(iconAjustes);
            lblAjustes.setForeground(colorNormal);
        }
    }//GEN-LAST:event_btnAjustesMouseExited

    private void btnAgregarActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActividadActionPerformed
        ModalNuevaActividad modalo = new ModalNuevaActividad();
        modal = new JDialog(this, "Nueva Actividad", true);
        modal.getContentPane().add(modalo);
        modal.setResizable(false);
        modal.pack();
        modal.setLocationRelativeTo(null);
        modal.setVisible(true);
    }//GEN-LAST:event_btnAgregarActividadActionPerformed

    private void btnAgregarSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarSeccionActionPerformed
        // TODO add your handling code here:
        ModalNuevaSeccion ModalAgregarSeccion = new ModalNuevaSeccion();
        modal = new JDialog(this, "Nueva Seccion", true);
        modal.getContentPane().add(ModalAgregarSeccion);
        modal.setResizable(false);
        modal.pack();
        modal.setLocationRelativeTo(null);
        modal.setVisible(true);
    }//GEN-LAST:event_btnAgregarSeccionActionPerformed

    private void jLabel210MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel210MouseClicked
        CardLayout cl = (CardLayout) (pnlCardLayoutAdmin.getLayout());
        cl.show(pnlCardLayoutAdmin, "Ubicaciones");
    }//GEN-LAST:event_jLabel210MouseClicked

    private void btnBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackupActionPerformed
        int countSelected = 0;
        List<JCheckBox> buttons = new ArrayList<>();
        List<JCheckBox> checked = new ArrayList<>();
        buttons.add(checkUsuarios);
        buttons.add(checkProyectos);
        buttons.add(checkVotaciones);
        buttons.add(checkActividades);
        buttons.add(checkUbicaciones);
        for (JCheckBox checkbox : buttons) {
            if (checkbox.isSelected()) {
                checked.add(checkbox);
                countSelected++;
            }
        }
        if (countSelected > 0) {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Escoge la carpeta para guardar el respaldo");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                String usuarios[][] = {{"usuario", "users"}, {"tipoUsuario", "usersType"}, {"estadoUsuario", "usersState"}};
                String proyectos[][] = {{"proyecto", "projects"}, {"recursoProyecto", "projectsResources"}, {"integranteProyecto", "membersProject"}};
                String votaciones[][] = {{"votacion", "score"}, {"criterioVotacion", "scoreCriteria"}, {"detalleVotacion", "scoreDetails"}};
                String ubicaciones[][] = {{"seccionNivel", "sectionsLevel"}, {"nivel", "levels"}, {"ubicacion", "locations"}, {"especialidad", "especiality"}};
                String actividades[][] = {{"actividad", "activity"}, {"criterioVotacion", "scoreCriteria"}, {"detalleVotacion", "scoreDetails"}};
                String[][] a = null;
                for (JCheckBox checkbox : checked) {
                    switch (checkbox.getText()) {
                        case "Usuarios":
                            a = General.merge(a, usuarios);
                            break;
                        case "Proyectos":
                            a = General.merge(a, proyectos);
                            break;
                        case "Votaciones":
                            a = General.merge(a, votaciones);
                            break;
                        case "Actividades":
                            a = General.merge(a, actividades);
                            break;
                        case "Ubicaciones y secciones":
                            a = General.merge(a, ubicaciones);
                            break;
                    }
                }
                if (General.generarBackup(String.valueOf(chooser.getSelectedFile()), a)) {

                } else {
                }

            } else {
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debes seleccionar al menos un elemento.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBackupActionPerformed

    private void btnFiltrarLista3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarLista3ActionPerformed
        makeActiveMenuItem(btnActividades, pnlActiveActividades, lblActividades, "Actividades");
    }//GEN-LAST:event_btnFiltrarLista3ActionPerformed

    private void btnAceptarModalFechasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarModalFechasActionPerformed
        dia2 = (new Date(dateFechaInicio.getDate().getTime() + (86400000)));
        dia3 = (new Date(dateFechaInicio.getDate().getTime() + (86400000 + 86400000)));
        dia4 = (new Date(dateFechaInicio.getDate().getTime() + 86400000 + 86400000 + 86400000));

        try {
            SimpleDateFormat formatoDia = new SimpleDateFormat("yyyy-MM-dd");
            String fechaInicio = formatoDia.format(dateFechaInicio.getDate());
            String d2 = formatoDia.format(dia2);
            String d3 = formatoDia.format(dia3);
            String d4 = formatoDia.format(dia4);
            String fechaFin = formatoDia.format(dateFechaFin.getDate());
            String edicion = String.valueOf(cbEdicionExpotecnica.getSelectedItem());

            if (Activities.setFechas(fechaInicio, d2, d3, d4, fechaFin)) {
                if (General.setEdicion(edicion)) {
                    General.getEdicion();
                    modal.dispose();
                    JOptionPane.showMessageDialog(null, "Fechas y edición guardadas");
                    cargarActividades();
                    lblEdicion.setText(CurrentUser.edicionExpotecnica);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al ingresar las edición de Expotécnica");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error al ingresar las fechas");
            }
        } catch (HeadlessException e) {
            System.out.println("ERROR" + e);
        }
    }//GEN-LAST:event_btnAceptarModalFechasActionPerformed

    private void btnAjustesActividadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAjustesActividadesMouseClicked
        Date fechaActual = new Date();

        dateFechaInicio.setDateFormatString("yyyy-MM-dd");
        dateFechaFin.setDateFormatString("yyyy-MM-dd");
        dateFechaInicio.setDate(new Date(fechaActual.getTime() + 1 * 24 * 60 * 60 * 1000));
        dateFechaFin.setDate(new Date(fechaActual.getTime() + 5 * 24 * 60 * 60 * 1000));

        dateFechaInicio.setMinSelectableDate(new Date(fechaActual.getTime() + 1 * 24 * 60 * 60 * 1000));

        cargarEdiciones();

        modal = new JDialog(this, "Ajustes Actividades", true);
        modal.getContentPane().add(pnlModalAjustesActividades);
        modal.pack();
        modal.setLocationRelativeTo(null);
        modal.setVisible(true);
    }//GEN-LAST:event_btnAjustesActividadesMouseClicked

    private void checkCombinarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCombinarActionPerformed
        if (checkCombinar.isSelected()) {
            lblAjustesBackup.setText("(No se eliminara nada del sistema pero puede que se omitan datos del respaldo).");
        } else {
            lblAjustesBackup.setText("");
        }
    }//GEN-LAST:event_checkCombinarActionPerformed

    private void btnActualizarPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPerfilActionPerformed
        String nombre = txtAjustesNombre.getText();
        String correo = txtAjustesEmail.getText();
        String password = String.valueOf(txtAjustesContraA.getPassword());
        String newPassword = String.valueOf(txtAjustesContraN.getPassword());
        String newPasswordC = String.valueOf(txtAjustesContraNC.getPassword());

        if (!Validation.VerificadorNombre.verify(nombre)) {
            txtAjustesNombre.setBackground(new java.awt.Color(255, 204, 204));
        } else {
            txtAjustesNombre.setBackground(new java.awt.Color(249, 250, 255));
        }
        if (!Validation.VerificadorEmail.verify(correo)) {
            txtAjustesEmail.setBackground(new java.awt.Color(255, 204, 204));
        } else {
            txtAjustesEmail.setBackground(new java.awt.Color(249, 250, 255));
        }
        if (!(Validation.isStringEmptyOrNull(password) && Validation.isStringEmptyOrNull(newPassword) && Validation.isStringEmptyOrNull(newPasswordC))) {
            if (!Validation.VerificadorPassword.verify(newPassword)) {
                txtAjustesContraN.setBackground(new java.awt.Color(255, 204, 204));
            } else {
                txtAjustesContraN.setBackground(new java.awt.Color(249, 250, 255));
                if (newPassword.equals(newPasswordC)) {
                    txtAjustesContraNC.setBackground(new java.awt.Color(249, 250, 255));
                    lblAErrorContraNC.setText("");
                    if (User.actualizarContraUsuario(password, newPassword, CurrentUser.idUsuario)) {
                        JOptionPane.showMessageDialog(this, "Se cambio la contraseña.", "Cambio de contraseña", JOptionPane.INFORMATION_MESSAGE);
                        txtAjustesContraA.setBackground(new java.awt.Color(249, 250, 255));
                        txtAjustesContraN.setBackground(new java.awt.Color(249, 250, 255));
                        txtAjustesContraNC.setBackground(new java.awt.Color(249, 250, 255));
                        lblAErrorContra.setText("");
                        lblAErrorContraN.setText("");
                        lblAErrorContraNC.setText("");
                    } else {
                        if (User.mensajeError.equals("")) {
                            txtAjustesContraA.setBackground(new java.awt.Color(255, 204, 204));
                            lblAErrorContra.setText("Contraseña incorrecta.");
                        } else {
                            JOptionPane.showMessageDialog(this, "No se pudo cambio la contraseña.", "Cambio de contraseña", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }
                } else {
                    txtAjustesContraNC.setBackground(new java.awt.Color(255, 204, 204));
                    lblAErrorContraNC.setText("Las contraseñas no coinciden.");
                }
            }
            lblAErrorContraN.setText(Validation.VerificadorPassword.mensaje);
        } else {
            txtAjustesContraA.setBackground(new java.awt.Color(249, 250, 255));
            txtAjustesContraN.setBackground(new java.awt.Color(249, 250, 255));
            txtAjustesContraNC.setBackground(new java.awt.Color(249, 250, 255));
            lblAErrorContra.setText("");
            lblAErrorContraN.setText("");
            lblAErrorContraNC.setText("");
        }

        lblAErrorEmail.setText(Validation.VerificadorEmail.mensaje);
        lblAErrorNombre.setText(Validation.VerificadorNombre.mensaje);
        if (Validation.VerificadorNombre.verify(nombre) && Validation.VerificadorEmail.verify(correo)) {
            if (!(CurrentUser.email.equals(correo) && CurrentUser.nombreCompleto.equals(nombre))) {
                if (User.actualizarPerfilUsuario(nombre, correo, CurrentUser.idUsuario)) {
                    txtAjustesEmail.setBackground(new java.awt.Color(249, 250, 255));
                    lblAErrorEmail.setText("");
                    JOptionPane.showMessageDialog(this, "Datos actualizados con exito", "Actualizar perfil", JOptionPane.INFORMATION_MESSAGE);
                    loadAjustes();
                } else {
                    if ("<html>Ya existe un usuario con la dirección de<br>correo electrónico.</html>".equals(User.mensajeError)) {
                        txtAjustesEmail.setBackground(new java.awt.Color(255, 204, 204));
                        lblAErrorEmail.setText("Ya existe un usuario con la dirección de correo electrónico.");
                    } else {
                        JOptionPane.showMessageDialog(this, User.mensajeError, "Actualizar perfil", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        }
    }//GEN-LAST:event_btnActualizarPerfilActionPerformed

    private void btnCambiarFotoPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarFotoPerfilActionPerformed
        JFileChooser fc = new JFileChooser();
        FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        fc.addChoosableFileFilter(imageFilter);
        fc.setAcceptAllFileFilterUsed(false);
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {

                File file = fc.getSelectedFile();
                if (Validation.VerificadorImagen.verifyFile(file)) {
                    FileInputStream fis = new FileInputStream(file);
                    BufferedImage imgOriginal = ImageIO.read(fis);
                    if (Validation.VerificadorImagen.verifyIMG(imgOriginal)) {
                        BufferedImage img = new BufferedImage(imgOriginal.getWidth(),
                                imgOriginal.getHeight(), BufferedImage.TYPE_INT_RGB);
                        img.createGraphics().drawImage(imgOriginal, 0, 0, Color.WHITE, null);
                        if (User.actualizarFotoPerfil(img, CurrentUser.idUsuario)) {
                            JOptionPane.showMessageDialog(this, "Foto de perfil actualizada con éxito.", "Actualizar foto de perfil", JOptionPane.INFORMATION_MESSAGE);
                            loadAjustes();
                        } else {
                            JOptionPane.showMessageDialog(this, "Error al actualizar la foto de perfil.", "Actualizar foto de perfil", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, Validation.VerificadorImagen.mensaje, "Seleccionar imagen", JOptionPane.WARNING_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(this, Validation.VerificadorImagen.mensaje, "Seleccionar imagen", JOptionPane.WARNING_MESSAGE);
                }

            } catch (HeadlessException | IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnCambiarFotoPerfilActionPerformed

    private void btnEliminarFotoPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarFotoPerfilActionPerformed
        if (User.eliminarFotoPerfil(CurrentUser.idUsuario)) {
            JOptionPane.showMessageDialog(this, "Foto de perfil eliminada con éxito.", "Actualizar foto de perfil", JOptionPane.INFORMATION_MESSAGE);
            loadAjustes();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar la foto de perfil.", "Actualizar foto de perfil", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarFotoPerfilActionPerformed

    private void btnFiltrarLista1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarLista1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFiltrarLista1ActionPerformed

    private void btnAgregarEspecialidad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEspecialidad1ActionPerformed
        // TODO add your handling code here:
        ModalNuevoProyecto modalProye = new ModalNuevoProyecto();

        JDialog modal1 = new JDialog(this, "Nuevo Proyecto", true);
        modal1.getContentPane().add(modalProye);
        modal1.pack();
        modal1.setLocationRelativeTo(null);
        modal1.setVisible(true);
    }//GEN-LAST:event_btnAgregarEspecialidad1ActionPerformed

    private void btnTablasCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablasCatalogoActionPerformed
        int seleccion = JOptionPane.showOptionDialog(this, "¿Que desea hacer?",
                "Selector de opciones", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                new Object[]{"Agregar", "Modificar", "Cancelar"}, "Cancelar");
        if (jrbEstado.isSelected()) {
            if (seleccion == 0) {
                ModalNuevoEstadoUsuario ModalEstadoUsuario = new ModalNuevoEstadoUsuario();

                JDialog modal1 = new JDialog(this, "Nuevo Estado Usuario", true);
                modal1.getContentPane().add(ModalEstadoUsuario);
                modal1.pack();
                modal1.setLocationRelativeTo(null);
                modal1.setVisible(true);
            } else if (seleccion == 1) {
                ModalModificarEstadoUsuario ModalEstadoUsuario = new ModalModificarEstadoUsuario();

                JDialog modal1 = new JDialog(this, "Modificar Estado Usuario", true);
                modal1.getContentPane().add(ModalEstadoUsuario);
                modal1.pack();
                modal1.setLocationRelativeTo(null);
                modal1.setVisible(true);
            } else {
                JOptionPane.getRootFrame().dispose();
            }
        } else if (jrbTipo.isSelected()) {
            if (seleccion == 0) {
                ModalNuevoTipoUsuario ModalEstadoUsuario = new ModalNuevoTipoUsuario();

                JDialog modal1 = new JDialog(this, "Nuevo Tipo Usuario", true);
                modal1.getContentPane().add(ModalEstadoUsuario);
                modal1.pack();
                modal1.setLocationRelativeTo(null);
                modal1.setVisible(true);
            } else if (seleccion == 1) {
                ModalModificarTipoUsuario ModalEstadoUsuario = new ModalModificarTipoUsuario();

                JDialog modal1 = new JDialog(this, "Modificar Tipo Usuario", true);
                modal1.getContentPane().add(ModalEstadoUsuario);
                modal1.pack();
                modal1.setLocationRelativeTo(null);
                modal1.setVisible(true);
            } else {
                JOptionPane.getRootFrame().dispose();
            }
        }
    }//GEN-LAST:event_btnTablasCatalogoActionPerformed

    private void btnLoadPlacesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadPlacesActionPerformed
        lblAErrorPlaces.setText("");
        String api = txtAPIKey.getText();
        String venueID = txtVenueID.getText();
        if (Validation.isStringEmptyOrNull(api) || Validation.isStringEmptyOrNull(venueID)) {
            lblAErrorPlaces.setText("Debes de completar ambos campos");
        } else {
            if (JOptionPane.showConfirmDialog(this, "<html>Al cargar las nuevas ubicaciones se eliminaran las actuales "
                    + "y todos los datos<br> de la tabla que hacen referencia a estas(actividades, secciones, etc),<br> ¿Estás seguro que deseas continuar?</html>",
                    "Cargar ubicaciones", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                boolean r = false;
                String MAPWIZE_API_KEY = General.getMapwizeAPIKey();
                String MAPWIZE_VENUE_ID = General.getMapwizeVenueID();
                if (api.equals(MAPWIZE_API_KEY) && venueID.equals(MAPWIZE_VENUE_ID)) {
                    r = Locations.loadPlacesToDb();
                } else {
                    General.setMapwizeAPIKey(api);
                    General.setMapwizeVenueID(venueID);
                    r = Locations.loadPlacesToDb();
                }
                if (r) {
                    JOptionPane.showMessageDialog(this, "Ubicaciones cargadas con éxito", "Cargar ubicaciones", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    lblAErrorPlaces.setText("Error al cargar ubicaciones");
                }
            }

        }
    }//GEN-LAST:event_btnLoadPlacesActionPerformed

    private void dateFechaInicioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateFechaInicioPropertyChange
        dateFechaFin.setDate(new Date(dateFechaInicio.getDate().getTime() + 345600000));
    }//GEN-LAST:event_dateFechaInicioPropertyChange

    private void btnAgregarNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarNivelActionPerformed
        // TODO add your handling code here:
        ModalNuevoNivel ModalNivel = new ModalNuevoNivel();

        JDialog Modal = new JDialog(this, "Nuevo Nivel", true);
        Modal.getContentPane().add(ModalNivel);
        Modal.pack();
        Modal.setLocationRelativeTo(null);
        Modal.setVisible(true);
    }//GEN-LAST:event_btnAgregarNivelActionPerformed

    private void btnAgregarEspecialidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEspecialidadActionPerformed
        // TODO add your handling code here:
        ModalNuevaEspecialidad ModalEspecialidad = new ModalNuevaEspecialidad();

        JDialog Modal = new JDialog(this, "Nueva Especialidad", true);
        Modal.getContentPane().add(ModalEspecialidad);
        Modal.pack();
        Modal.setLocationRelativeTo(null);
        Modal.setVisible(true);
    }//GEN-LAST:event_btnAgregarEspecialidadActionPerformed

    private void btnModificarSeccionModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarSeccionModalActionPerformed
        CardLayout cl = (CardLayout) (pnlCardLayoutAdmin.getLayout());
        cl.show(pnlCardLayoutAdmin, "Seccion");

//        ModalModificarSecciones ModalSeccion = new ModalModificarSecciones();
//
//        JDialog Modal = new JDialog(this, "Nueva Seccion", true);
//        Modal.getContentPane().add(ModalSeccion);
//        Modal.pack();
//        Modal.setLocationRelativeTo(null);
//        Modal.setVisible(true);
    }//GEN-LAST:event_btnModificarSeccionModalActionPerformed

    private void lblDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDashboardMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblDashboardMouseClicked

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        String nombreReporte = "";
        if (buttonGroupReportes.isSelected(jrbUsuarios.getModel())) {
            nombreReporte = "usuarios";
        } else if (buttonGroupReportes.isSelected(jrbBitacoras.getModel())) {
            nombreReporte = "bitacora";
        }
        try {
            DbConnection con = new DbConnection();
            String archivo = getClass().getResource("/reports/" + nombreReporte + ".jrxml").getPath();
            archivo = URLDecoder.decode(archivo, "UTF-8");
            JasperReport report = JasperCompileManager.compileReport(archivo);
            Map parametros = new HashMap();
            parametros.put("NombreUsuario", CurrentUser.nombreCompleto);
            JasperPrint print = JasperFillManager.fillReport(report, parametros, con.conectar());
            JasperViewer visor = new JasperViewer(print, false);;
            visor.setVisible(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnReporteActionPerformed

    private void btnEliminarActividadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActividadesActionPerformed
        int a = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar esta actividad?");
        if (a == JOptionPane.YES_OPTION) {
            eliminarActividades();
        } else {

        }
    }//GEN-LAST:event_btnEliminarActividadesActionPerformed

    private void btnCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseClicked
        CurrentUser.clear();
        Login login = new Login();
        this.setVisible(false);
        login.setLocationRelativeTo(null);
        login.setDefaultCloseOperation(EXIT_ON_CLOSE);
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesionMouseClicked

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked
        setColorInterfaz();
    }//GEN-LAST:event_jLabel32MouseClicked

    private void setColorInterfaz() {
        //        setBackground(new Color(24, 25, 27) );
        Color superior;
        Color menu;
        Color btn;
        Color fondo;
        Color Blue;
        Color panel;
        Color normal;
        if (color == 0) {
            superior = darkSuperior;
            menu = darkMenu;
            btn = darkbtn;
            fondo = darkfondo;
            Blue = darkBlue;
            panel = darkPanel;
            normal = Color.WHITE;

            jLabel32.setIcon(iconNoche);
            jLabel1.setIcon(iconBlanco);
            color = 1;
        } else {
//            pnlSuperior.setBackground(new Color(255, 255, 255));
//            pnlMenu.setBackground(new Color(255, 255, 255));
            superior = new Color(255, 255, 255);
            menu = new Color(255, 255, 255);
            btn = new Color(255, 255, 255);
            fondo = new Color(244, 246, 252);
            Blue = new Color(46, 56, 77);
            panel = new Color(255, 255, 255);
            normal = new Color(46, 56, 77);
            jLabel32.setIcon(iconDia);
            jLabel1.setIcon(iconNegro);

            color = 0;
        }
        pnlSuperior.setBackground(superior);
        pnlMenu.setBackground(menu);
        //Botones
        btnUsuarios.setBackground(btn);
        btnAnaliticas.setBackground(btn);
        btnProyectos.setBackground(btn);
        btnActividades.setBackground(btn);
        btnUbicaciones.setBackground(btn);
        btnAjustes.setBackground(btn);
        //Dashboard
        jPanel115.setBackground(fondo);
        jPanel116.setBackground(fondo);
        jPanel127.setBackground(fondo);
//            //Paneles Dashboard
        jPanel117.setBackground(panel);
        jPanel118.setBackground(panel);
        jPanel125.setBackground(panel);
        jPanel126.setBackground(panel);
        jPanel128.setBackground(panel);
        jPanel129.setBackground(panel);
        jPanel130.setBackground(panel);
        jPanel139.setBackground(panel);
        jPanel141.setBackground(panel);
        jPanel135.setBackground(panel);

        checkActividades.setBackground(panel);
        checkCombinar.setBackground(panel);
        checkProyectos.setBackground(panel);
        checkUbicaciones.setBackground(panel);
        checkUsuarios.setBackground(panel);
        checkVotaciones.setBackground(panel);
        checkTodo.setBackground(panel);

        jLabel287.setForeground(Blue);
        jLabel288.setForeground(Blue);
        jLabel290.setForeground(Blue);
        jLabel291.setForeground(Blue);
        jLabel285.setForeground(normal);
        jLabel286.setForeground(normal);
        jLabel295.setForeground(normal);
        jLabel294.setForeground(normal);

        checkActividades.setForeground(normal);
        checkCombinar.setForeground(normal);
        checkProyectos.setForeground(normal);
        checkUbicaciones.setForeground(normal);
        checkUsuarios.setForeground(normal);
        checkVotaciones.setForeground(normal);
        checkTodo.setForeground(normal);

        //Analiticas
        pnlAnaliticas.setBackground(fondo);
        jPanel145.setBackground(panel);
        jPanel146.setBackground(panel);
        jPanel150.setBackground(panel);
        jPanel151.setBackground(panel);
        jPanel152.setBackground(panel);

        jLabel87.setForeground(new Color(46, 91, 255));
        jLabel89.setForeground(new Color(46, 91, 255));
        jLabel93.setForeground(new Color(46, 91, 255));
        jLabel95.setForeground(new Color(46, 91, 255));

        //Usuarios
        pnlUsuarios.setBackground(fondo);
        jPanel1.setBackground(panel);
        jPanel5.setBackground(fondo);
        jPanel9.setBackground(panel);
        jPanel7.setBackground(panel);
        jPanel8.setBackground(panel);
        jPanel10.setBackground(panel);
        jPanel11.setBackground(fondo);

        jLUsuarioActivos.setForeground(new Color(46, 91, 255));
        jLUsuarioTotal.setForeground(new Color(46, 91, 255));
        jLabel10.setForeground(new Color(46, 91, 255));
        jLabel48.setForeground(new Color(46, 91, 255));
        jLabel25.setForeground(normal);
        jLabel26.setForeground(normal);
        jLabel27.setForeground(normal);

        //Proyectos
        pnlProyectos.setBackground(fondo);
        cdProyectos.setBackground(panel);
        jPanel31.setBackground(fondo);

        jLabel214.setForeground(Blue);
        jLabel230.setForeground(new Color(135, 152, 173));
        jLabel215.setForeground(normal);
        jLabel228.setForeground(normal);
        jLabel229.setForeground(normal);

        //Ubicaciones
        pnlUbicaciones.setBackground(fondo);
        jPanel17.setBackground(panel);
        jPanel18.setBackground(panel);
        jPanel27.setBackground(fondo);

        jLabel98.setForeground(Blue);
        jLabel160.setForeground(Blue);
        jLabel161.setForeground(Blue);

        //Actividades
        pnlActividades.setBackground(fondo);
        pnlInnerActividades.setBackground(fondo);
        jPanel26.setBackground(fondo);
        jPanel4.setBackground(panel);
        jPanel12.setBackground(panel);
        jPanel13.setBackground(panel);
        jPanel14.setBackground(panel);
        jPanel15.setBackground(panel);

        jPanel25.setBackground(fondo);
        jPanel28.setBackground(fondo);
        jPanel53.setBackground(fondo);
        jPanel54.setBackground(fondo);
        jPanel55.setBackground(fondo);
        jPanel56.setBackground(fondo);
        jPanel57.setBackground(fondo);
        jPanel58.setBackground(fondo);
        jPanel59.setBackground(fondo);
        jPanel60.setBackground(fondo);
        

        jLabel109.setForeground(Blue);

        //Ajustes
        jPanel2.setBackground(fondo);
        jPanel3.setBackground(panel);
        jPanel16.setBackground(panel);
        
        jPanel43.setBackground(panel);
        jPanel143.setBackground(panel);
        jPanel144.setBackground(panel);
        jrbAccion.setBackground(panel);
        jrbCriterio.setBackground(panel);
        jrbTipo.setBackground(panel);
        jrbEstado.setBackground(panel);
        checkCombinar.setBackground(panel);

        jrbAccion.setForeground(normal);
        jrbCriterio.setForeground(normal);
        jrbTipo.setForeground(normal);
        jrbEstado.setForeground(normal);
        checkCombinar.setForeground(normal);

        jrbUsuarios.setBackground(panel);
        jrbVotaciones.setBackground(panel);
        jrbBitacoras.setBackground(panel);
        jrbProyectos.setBackground(panel);

        jrbUsuarios.setForeground(normal);
        jrbVotaciones.setForeground(normal);
        jrbBitacoras.setForeground(normal);
        jrbProyectos.setForeground(normal);

        jLabel2.setForeground(Blue);
        jLabel3.setForeground(Blue);
        jLabel4.setForeground(normal);
        jLabel66.setForeground(normal);
        jLabel83.setForeground(normal);
        jLabel84.setForeground(normal);
        jLabel82.setForeground(Blue);
        jLabel264.setForeground(Blue);
        jLabel265.setForeground(Blue);
    }

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
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptarModal;
    private javax.swing.JButton btnAceptarModalFechas;
    private javax.swing.JPanel btnActividades;
    private javax.swing.JButton btnActualizarPerfil;
    private javax.swing.JButton btnAgregarActividad;
    private javax.swing.JButton btnAgregarEspecialidad;
    private javax.swing.JButton btnAgregarEspecialidad1;
    private javax.swing.JButton btnAgregarNivel;
    private javax.swing.JButton btnAgregarSeccion;
    private javax.swing.JButton btnAgregarUsuario;
    private javax.swing.JPanel btnAjustes;
    private javax.swing.JLabel btnAjustesActividades;
    private javax.swing.JPanel btnAnaliticas;
    private javax.swing.JButton btnBackup;
    private javax.swing.JButton btnCambiarFotoPerfil;
    private javax.swing.JButton btnCancelarModal;
    private javax.swing.JButton btnCancelarModal1;
    private javax.swing.JLabel btnCerrarSesion;
    private javax.swing.JButton btnEliminarActividades;
    private javax.swing.JButton btnEliminarFotoPerfil;
    private javax.swing.JButton btnFiltrarLista;
    private javax.swing.JButton btnFiltrarLista1;
    private javax.swing.JButton btnFiltrarLista3;
    private javax.swing.JPanel btnInicio;
    private javax.swing.JButton btnLoadPlaces;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnModificarSeccionModal;
    private javax.swing.JPanel btnProyectos;
    private javax.swing.JButton btnReporte;
    private javax.swing.JButton btnTablasCatalogo;
    private javax.swing.JPanel btnUbicaciones;
    private javax.swing.JPanel btnUsuarios;
    private javax.swing.ButtonGroup buttonGroupReportes;
    private javax.swing.ButtonGroup buttonGroupTablasCatalogo;
    private javax.swing.JComboBox<String> cbEdicionExpotecnica;
    private javax.swing.JComboBox<String> cbxEstadoUsuarioModal;
    private javax.swing.JComboBox<String> cbxTipoUsuarioModal;
    public static javax.swing.JPanel cdProyectos;
    private javax.swing.JCheckBox checkActividades;
    private javax.swing.JCheckBox checkCombinar;
    private javax.swing.JCheckBox checkProyectos;
    private javax.swing.JCheckBox checkTodo;
    private javax.swing.JCheckBox checkUbicaciones;
    private javax.swing.JCheckBox checkUsuarios;
    private javax.swing.JCheckBox checkVotaciones;
    private com.toedter.calendar.JDateChooser dateFechaFin;
    private com.toedter.calendar.JDateChooser dateFechaInicio;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    public static javax.swing.JLabel jLUsuarioActivos;
    public static javax.swing.JLabel jLUsuarioTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel209;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel214;
    private javax.swing.JLabel jLabel215;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel228;
    private javax.swing.JLabel jLabel229;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel230;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel250;
    private javax.swing.JLabel jLabel251;
    private javax.swing.JLabel jLabel252;
    private javax.swing.JLabel jLabel253;
    private javax.swing.JLabel jLabel254;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel264;
    private javax.swing.JLabel jLabel265;
    private javax.swing.JLabel jLabel266;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel285;
    private javax.swing.JLabel jLabel286;
    private javax.swing.JLabel jLabel287;
    private javax.swing.JLabel jLabel288;
    private javax.swing.JLabel jLabel289;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel290;
    private javax.swing.JLabel jLabel291;
    private javax.swing.JLabel jLabel292;
    private javax.swing.JLabel jLabel293;
    private javax.swing.JLabel jLabel294;
    private javax.swing.JLabel jLabel295;
    private javax.swing.JLabel jLabel298;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel300;
    private javax.swing.JLabel jLabel306;
    private javax.swing.JLabel jLabel307;
    private javax.swing.JLabel jLabel308;
    private javax.swing.JLabel jLabel309;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel310;
    private javax.swing.JLabel jLabel311;
    private javax.swing.JLabel jLabel312;
    private javax.swing.JLabel jLabel313;
    private javax.swing.JLabel jLabel314;
    private javax.swing.JLabel jLabel315;
    private javax.swing.JLabel jLabel316;
    private javax.swing.JLabel jLabel317;
    private javax.swing.JLabel jLabel318;
    private javax.swing.JLabel jLabel319;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel320;
    private javax.swing.JLabel jLabel321;
    private javax.swing.JLabel jLabel322;
    private javax.swing.JLabel jLabel323;
    private javax.swing.JLabel jLabel324;
    private javax.swing.JLabel jLabel325;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel98;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel113;
    private javax.swing.JPanel jPanel115;
    private javax.swing.JPanel jPanel116;
    private javax.swing.JPanel jPanel117;
    private javax.swing.JPanel jPanel118;
    public static javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel125;
    private javax.swing.JPanel jPanel126;
    private javax.swing.JPanel jPanel127;
    private javax.swing.JPanel jPanel128;
    private javax.swing.JPanel jPanel129;
    public static javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel130;
    private javax.swing.JPanel jPanel135;
    private javax.swing.JPanel jPanel136;
    private javax.swing.JPanel jPanel137;
    private javax.swing.JPanel jPanel138;
    private javax.swing.JPanel jPanel139;
    public static javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel140;
    private javax.swing.JPanel jPanel141;
    private javax.swing.JPanel jPanel142;
    private javax.swing.JPanel jPanel143;
    private javax.swing.JPanel jPanel144;
    private javax.swing.JPanel jPanel145;
    private javax.swing.JPanel jPanel146;
    public static javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel150;
    private javax.swing.JPanel jPanel151;
    private javax.swing.JPanel jPanel152;
    private javax.swing.JPanel jPanel16;
    public static javax.swing.JPanel jPanel17;
    public static javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    public static javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel31;
    public static javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JScrollPane jpnlDia2;
    private javax.swing.JScrollPane jpnlDia3;
    private javax.swing.JScrollPane jpnlDia4;
    private javax.swing.JScrollPane jpnlDia5;
    private javax.swing.JScrollPane jpnlTableUsuarios;
    private javax.swing.JRadioButton jrbAccion;
    private javax.swing.JRadioButton jrbBitacoras;
    private javax.swing.JRadioButton jrbCriterio;
    private javax.swing.JRadioButton jrbEstado;
    private javax.swing.JRadioButton jrbProyectos;
    private javax.swing.JRadioButton jrbTipo;
    private javax.swing.JRadioButton jrbUsuarios;
    private javax.swing.JRadioButton jrbVotaciones;
    private javax.swing.JLabel lblAErrorContra;
    private javax.swing.JLabel lblAErrorContraN;
    private javax.swing.JLabel lblAErrorContraNC;
    private javax.swing.JLabel lblAErrorEmail;
    private javax.swing.JLabel lblAErrorNombre;
    private javax.swing.JLabel lblAErrorPlaces;
    private javax.swing.JLabel lblActividades;
    private javax.swing.JLabel lblAjustes;
    private javax.swing.JLabel lblAjustesBackup;
    private javax.swing.JLabel lblAnaliticas;
    public static javax.swing.JLabel lblCantidadActividades1;
    public static javax.swing.JLabel lblCantidadActividades2;
    public static javax.swing.JLabel lblCantidadActividades3;
    public static javax.swing.JLabel lblCantidadActividades4;
    public static javax.swing.JLabel lblCantidadActividades5;
    private javax.swing.JLabel lblDashboard;
    private static javax.swing.JLabel lblDia1;
    private static javax.swing.JLabel lblDia2;
    private static javax.swing.JLabel lblDia3;
    private static javax.swing.JLabel lblDia4;
    private static javax.swing.JLabel lblDia5;
    public static javax.swing.JLabel lblEdicion;
    private static javax.swing.JLabel lblFechaDia1;
    private static javax.swing.JLabel lblFechaDia2;
    private static javax.swing.JLabel lblFechaDia3;
    private static javax.swing.JLabel lblFechaDia4;
    private static javax.swing.JLabel lblFechaDia5;
    private javax.swing.JLabel lblFotoPerfil;
    private javax.swing.JLabel lblProyectos;
    private javax.swing.JLabel lblUbicaciones;
    private javax.swing.JLabel lblUsuarios;
    private javax.swing.JDialog modalAjustesActividades;
    private javax.swing.JDialog modalUsuario;
    private javax.swing.JPanel pnlActiveActividades;
    private javax.swing.JPanel pnlActiveAjustes;
    private javax.swing.JPanel pnlActiveAnaliticas;
    private javax.swing.JPanel pnlActiveInicio;
    private javax.swing.JPanel pnlActiveProyectos;
    private javax.swing.JPanel pnlActiveUbicaciones;
    private javax.swing.JPanel pnlActiveUsuarios;
    private javax.swing.JPanel pnlActividades;
    private javax.swing.JScrollPane pnlAjustes;
    private javax.swing.JPanel pnlAnaliticas;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlCardLayoutAdmin;
    private javax.swing.JScrollPane pnlDashboard;
    private javax.swing.JPanel pnlInnerActividades;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlModalAjustesActividades;
    private javax.swing.JPanel pnlModalNuevoUsuario;
    private javax.swing.JPanel pnlProyectos;
    private javax.swing.JPanel pnlSeccion;
    private javax.swing.JPanel pnlSuperior;
    private javax.swing.JPanel pnlUbicaciones;
    private javax.swing.JPanel pnlUsuarios;
    private javax.swing.JTextField txtAPIKey;
    private javax.swing.JPasswordField txtAjustesContraA;
    private javax.swing.JPasswordField txtAjustesContraN;
    private javax.swing.JPasswordField txtAjustesContraNC;
    private javax.swing.JTextField txtAjustesEmail;
    private javax.swing.JTextField txtAjustesNombre;
    private javax.swing.JTextField txtBusquedaFiltro;
    private javax.swing.JPasswordField txtClaveUsuarioModal;
    private javax.swing.JTextField txtEmailUsuarioModal;
    private javax.swing.JTextField txtNombreUsuarioModal;
    private javax.swing.JTextField txtVenueID;
    // End of variables declaration//GEN-END:variables

    /**
     * Método utilizado para cargar los ajustes del usuario que se encuentra en
     * el sistema.
     *
     */
    private void loadAjustes() {
        User.cargarDatosUsuarioActual(CurrentUser.idUsuario);
        txtAjustesNombre.setText(CurrentUser.nombreCompleto);
        txtAjustesEmail.setText(CurrentUser.email);
        if (CurrentUser.fotoPerfil != null) {
            btnEliminarFotoPerfil.setEnabled(true);
            try {
                BufferedImage icon = General.resizeSquare(CurrentUser.fotoPerfil, 128);
                lblFotoPerfil.setIcon(new ImageIcon(icon));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            btnEliminarFotoPerfil.setEnabled(false);
            lblFotoPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/profilePicture.png")));
        }
        txtAPIKey.setText(General.getMapwizeAPIKey());
        txtVenueID.setText(General.getMapwizeVenueID());
    }
}

class RoundedPanel extends JPanel {

    private Color backgroundColor;
    private int cornerRadius = 15;

    public RoundedPanel(LayoutManager layout, int radius) {
        super(layout);
        cornerRadius = radius;
    }

    public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
        super(layout);
        cornerRadius = radius;
        backgroundColor = bgColor;
    }

    public RoundedPanel(int radius) {
        super();
        cornerRadius = radius;

    }

    public RoundedPanel(int radius, Color bgColor) {
        super();
        cornerRadius = radius;
        backgroundColor = bgColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //Draws the rounded panel with borders.
        if (backgroundColor != null) {
            graphics.setColor(backgroundColor);
        } else {
            graphics.setColor(getBackground());
        }
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); //paint background
        graphics.setColor(getForeground());
//            graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
//
    }
}
