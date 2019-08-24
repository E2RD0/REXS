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
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
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
import rexsdesktop.controller.Projects;
import rexsdesktop.controller.User;
import rexsdesktop.controller.Validation;
import rexsdesktop.modal.ModalNuevaActividad;
import rexsdesktop.modal.ModalNuevoProyecto;

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
        this.setTitle("REXS");
        /*Projects*/
        Projects cargarPaneles = new Projects();
        cargarPaneles.CrearPanelesProyectos(jPanel112);
        /*Activities*/
        Activities cargarPaneles1 = new Activities();
        cargarPaneles1.CrearPanelesActividades(jPanel4);
        User cargar = new User();
        cargar.CrearPanelesUsuarios(jPanel1);

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
        pnlAjustes.getVerticalScrollBar().setPreferredSize(new Dimension(10, Integer.MAX_VALUE));
        pnlAjustes.getVerticalScrollBar().setUnitIncrement(16);
        pnlDashboard.getVerticalScrollBar().setPreferredSize(new Dimension(10, Integer.MAX_VALUE));
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
        jpnlTableNiveles.getVerticalScrollBar().setUnitIncrement(10);
        jpnlTableEspecialidades.getVerticalScrollBar().setUnitIncrement(10);
        jpnlTableSecciones.getVerticalScrollBar().setUnitIncrement(10);
        /*END Scrollbar movement*/
 /*Datos de usuario en ajustes*/
        loadAjustes();
        /*Datos de usuario en ajustes*/
    }

    private void resetearModalUsuario() {
        txtEmailUsuarioModal.setText("");
        txtNombreUsuarioModal.setText("");
        txtClaveUsuarioModal.setText("");
        cbxTipoUsuarioModal.setSelectedIndex(0);
        cbxEstadoUsuarioModal.setSelectedIndex(0);
    }
    //final JDialog modalUsuario = new JDialog(this, "Prueba", true);
    ImageIcon iconDashboard = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconHome.png"));
    ImageIcon iconDashboardActive = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconHomeActive.png"));
    ImageIcon iconAnaliticas = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconAnalytics.png"));
    ImageIcon iconAnaliticasActive = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconAnalyticsActive.png"));
    ImageIcon iconUsuarios = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconUsers.png"));
    ImageIcon iconUsuariosActive = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconUsersActive.png"));
    ImageIcon iconProyectos = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconProjects.png"));
    ImageIcon iconProyectosActive = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconProjectsActive.png"));
    ImageIcon iconUbicaciones = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconMap.png"));
    ImageIcon iconUbicacionesActive = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconMapActive.png"));
    ImageIcon iconActividades = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconCalendar.png"));
    ImageIcon iconActividadesActive = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconCalendarActive.png"));
    ImageIcon iconAjustes = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconSettings.png"));
    ImageIcon iconAjustesActive = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconSettingsActive.png"));

    Color colorActive = new Color(46, 91, 255);
    Color colorNormal = new Color(176, 186, 201);
    Color bgActive = new Color(238, 242, 255);
    Color bgNormal = new Color(255, 255, 255);

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
        txtNombreUsuarioModal1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        txtNombreUsuarioModal2 = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnAceptarModal1 = new javax.swing.JButton();
        btnCancelarModal1 = new javax.swing.JButton();
        modalAjustesActividades = new javax.swing.JDialog();
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
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JLabel();
        pnlCardLayoutAdmin = new javax.swing.JPanel();
        pnlDashboard = new javax.swing.JScrollPane();
        jPanel115 = new javax.swing.JPanel();
        jPanel116 = new javax.swing.JPanel();
        jPanel117 = new javax.swing.JPanel();
        jLabel249 = new javax.swing.JLabel();
        jLabel285 = new javax.swing.JLabel();
        jLabel286 = new javax.swing.JLabel();
        jLabel252 = new javax.swing.JLabel("<html>Con Ricaldone Expotécnica System puedes gestionar distinta información referentes al evento.</html>");
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
        pnlUsuarios = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
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
        jPanel112 = new javax.swing.JPanel();
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
        btnFiltrarLista2 = new javax.swing.JButton();
        pnlUbicaciones = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        jpnlTableNiveles = new javax.swing.JScrollPane();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel162 = new javax.swing.JLabel();
        lblEditarUsuario1 = new javax.swing.JLabel();
        pnlNivel = new javax.swing.JPanel();
        jLabel190 = new javax.swing.JLabel();
        lblEditarUsuario2 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jLabel191 = new javax.swing.JLabel();
        lblEditarUsuario8 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jLabel192 = new javax.swing.JLabel();
        lblEditarUsuario9 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        jLabel202 = new javax.swing.JLabel();
        lblEditarUsuario10 = new javax.swing.JLabel();
        jPanel87 = new javax.swing.JPanel();
        jLabel203 = new javax.swing.JLabel();
        lblEditarUsuario11 = new javax.swing.JLabel();
        jpnlTableEspecialidades = new javax.swing.JScrollPane();
        jPanel88 = new javax.swing.JPanel();
        jPanel97 = new javax.swing.JPanel();
        jLabel204 = new javax.swing.JLabel();
        lblEditarUsuario3 = new javax.swing.JLabel();
        jPanel98 = new javax.swing.JPanel();
        jLabel205 = new javax.swing.JLabel();
        lblEditarUsuario4 = new javax.swing.JLabel();
        jPanel103 = new javax.swing.JPanel();
        jLabel206 = new javax.swing.JLabel();
        lblEditarUsuario5 = new javax.swing.JLabel();
        jPanel104 = new javax.swing.JPanel();
        jLabel207 = new javax.swing.JLabel();
        lblEditarUsuario6 = new javax.swing.JLabel();
        jPanel111 = new javax.swing.JPanel();
        jLabel208 = new javax.swing.JLabel();
        lblEditarUsuario7 = new javax.swing.JLabel();
        btnAgregarNivel = new javax.swing.JButton();
        btnAgregarEspecialidad = new javax.swing.JButton();
        pnlSeccion = new javax.swing.JPanel();
        jPanel113 = new javax.swing.JPanel();
        jLabel209 = new javax.swing.JLabel();
        jpnlTableSecciones = new javax.swing.JScrollPane();
        jPanel114 = new javax.swing.JPanel();
        jPanel119 = new javax.swing.JPanel();
        jLabel216 = new javax.swing.JLabel();
        lblEditarUsuario16 = new javax.swing.JLabel();
        jLabel217 = new javax.swing.JLabel();
        jLabel211 = new javax.swing.JLabel();
        jPanel121 = new javax.swing.JPanel();
        jLabel218 = new javax.swing.JLabel();
        lblEditarUsuario18 = new javax.swing.JLabel();
        jLabel219 = new javax.swing.JLabel();
        jPanel122 = new javax.swing.JPanel();
        jLabel220 = new javax.swing.JLabel();
        lblEditarUsuario19 = new javax.swing.JLabel();
        jLabel221 = new javax.swing.JLabel();
        jPanel120 = new javax.swing.JPanel();
        jLabel222 = new javax.swing.JLabel();
        lblEditarUsuario17 = new javax.swing.JLabel();
        jLabel223 = new javax.swing.JLabel();
        jLabel212 = new javax.swing.JLabel();
        jPanel123 = new javax.swing.JPanel();
        jLabel224 = new javax.swing.JLabel();
        lblEditarUsuario20 = new javax.swing.JLabel();
        jLabel225 = new javax.swing.JLabel();
        jPanel124 = new javax.swing.JPanel();
        jLabel226 = new javax.swing.JLabel();
        lblEditarUsuario21 = new javax.swing.JLabel();
        jLabel227 = new javax.swing.JLabel();
        jLabel213 = new javax.swing.JLabel();
        btnAgregarSeccion = new javax.swing.JButton();
        jLabel210 = new javax.swing.JLabel();
        pnlActividades = new javax.swing.JPanel();
        pnlInnerActividades = new javax.swing.JPanel();
        jLabel109 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        btnAgregarActividad = new javax.swing.JButton();
        jpnlDia2 = new javax.swing.JScrollPane();
        jPanel61 = new javax.swing.JPanel();
        jpnlDia3 = new javax.swing.JScrollPane();
        jPanel72 = new javax.swing.JPanel();
        jPanel73 = new javax.swing.JPanel();
        jPanel76 = new javax.swing.JPanel();
        jLabel151 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        jPanel77 = new javax.swing.JPanel();
        jPanel78 = new javax.swing.JPanel();
        jLabel157 = new javax.swing.JLabel();
        jLabel158 = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        jpnlDia4 = new javax.swing.JScrollPane();
        jPanel79 = new javax.swing.JPanel();
        jPanel83 = new javax.swing.JPanel();
        jPanel84 = new javax.swing.JPanel();
        jLabel166 = new javax.swing.JLabel();
        jLabel167 = new javax.swing.JLabel();
        jLabel168 = new javax.swing.JLabel();
        jPanel85 = new javax.swing.JPanel();
        jPanel86 = new javax.swing.JPanel();
        jLabel169 = new javax.swing.JLabel();
        jLabel170 = new javax.swing.JLabel();
        jLabel171 = new javax.swing.JLabel();
        jPanel99 = new javax.swing.JPanel();
        jPanel100 = new javax.swing.JPanel();
        jLabel184 = new javax.swing.JLabel();
        jLabel185 = new javax.swing.JLabel();
        jLabel186 = new javax.swing.JLabel();
        jPanel101 = new javax.swing.JPanel();
        jPanel102 = new javax.swing.JPanel();
        jLabel187 = new javax.swing.JLabel();
        jLabel188 = new javax.swing.JLabel();
        jLabel189 = new javax.swing.JLabel();
        jPanel107 = new javax.swing.JPanel();
        jPanel108 = new javax.swing.JPanel();
        jLabel196 = new javax.swing.JLabel();
        jLabel197 = new javax.swing.JLabel();
        jLabel198 = new javax.swing.JLabel();
        jPanel109 = new javax.swing.JPanel();
        jPanel110 = new javax.swing.JPanel();
        jLabel199 = new javax.swing.JLabel();
        jLabel200 = new javax.swing.JLabel();
        jLabel201 = new javax.swing.JLabel();
        jpnlDia5 = new javax.swing.JScrollPane();
        jPanel80 = new javax.swing.JPanel();
        jPanel89 = new javax.swing.JPanel();
        jPanel90 = new javax.swing.JPanel();
        jLabel172 = new javax.swing.JLabel();
        jLabel173 = new javax.swing.JLabel();
        jLabel174 = new javax.swing.JLabel();
        jPanel91 = new javax.swing.JPanel();
        jPanel92 = new javax.swing.JPanel();
        jLabel175 = new javax.swing.JLabel();
        jLabel176 = new javax.swing.JLabel();
        jLabel177 = new javax.swing.JLabel();
        jPanel93 = new javax.swing.JPanel();
        jPanel94 = new javax.swing.JPanel();
        jLabel178 = new javax.swing.JLabel();
        jLabel179 = new javax.swing.JLabel();
        jLabel180 = new javax.swing.JLabel();
        jPanel95 = new javax.swing.JPanel();
        jPanel96 = new javax.swing.JPanel();
        jLabel181 = new javax.swing.JLabel();
        jLabel182 = new javax.swing.JLabel();
        jLabel183 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel97 = new javax.swing.JLabel();
        jPanel28 = new RoundedPanel(90, new Color(213, 222, 255));
        jLabel99 = new javax.swing.JLabel();
        jPanel53 = new javax.swing.JPanel();
        jLabel128 = new javax.swing.JLabel();
        jPanel54 = new RoundedPanel(90, new Color(232, 221, 255));
        jLabel129 = new javax.swing.JLabel();
        jPanel55 = new javax.swing.JPanel();
        jLabel130 = new javax.swing.JLabel();
        jPanel56 = new RoundedPanel(90, new Color(204, 243, 247));
        jLabel131 = new javax.swing.JLabel();
        jPanel57 = new javax.swing.JPanel();
        jLabel132 = new javax.swing.JLabel();
        jPanel58 = new RoundedPanel(90, new Color(254, 243, 215));
        jLabel133 = new javax.swing.JLabel();
        jPanel59 = new javax.swing.JPanel();
        jLabel134 = new javax.swing.JLabel();
        jPanel60 = new RoundedPanel(90, new Color(214, 238, 213));
        jLabel135 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        btnAjustesActividades = new javax.swing.JLabel();
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

        txtNombreUsuarioModal1.setBackground(new java.awt.Color(249, 250, 255));
        txtNombreUsuarioModal1.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtNombreUsuarioModal1.setForeground(new java.awt.Color(46, 56, 77));
        txtNombreUsuarioModal1.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));

        jButton2.setText("...");

        txtNombreUsuarioModal2.setBackground(new java.awt.Color(249, 250, 255));
        txtNombreUsuarioModal2.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtNombreUsuarioModal2.setForeground(new java.awt.Color(46, 56, 77));
        txtNombreUsuarioModal2.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));

        jLabel79.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(176, 186, 201));
        jLabel79.setText("FECHA FIN DE EXPOTÉCNICA");

        jButton7.setText("...");

        jSeparator2.setForeground(new java.awt.Color(164, 164, 164));

        btnAceptarModal1.setBackground(new java.awt.Color(213, 222, 255));
        btnAceptarModal1.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        btnAceptarModal1.setForeground(new java.awt.Color(46, 91, 255));
        btnAceptarModal1.setText("Aceptar");
        btnAceptarModal1.setBorderPainted(false);
        btnAceptarModal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarModal1ActionPerformed(evt);
            }
        });

        btnCancelarModal1.setBackground(new java.awt.Color(247, 214, 218));
        btnCancelarModal1.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        btnCancelarModal1.setForeground(new java.awt.Color(214, 54, 73));
        btnCancelarModal1.setText("Cancelar");
        btnCancelarModal1.setBorderPainted(false);

        javax.swing.GroupLayout pnlModalAjustesActividadesLayout = new javax.swing.GroupLayout(pnlModalAjustesActividades);
        pnlModalAjustesActividades.setLayout(pnlModalAjustesActividadesLayout);
        pnlModalAjustesActividadesLayout.setHorizontalGroup(
            pnlModalAjustesActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlModalAjustesActividadesLayout.createSequentialGroup()
                .addGap(0, 30, Short.MAX_VALUE)
                .addGroup(pnlModalAjustesActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlModalAjustesActividadesLayout.createSequentialGroup()
                        .addComponent(btnAceptarModal1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelarModal1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlModalAjustesActividadesLayout.createSequentialGroup()
                        .addGroup(pnlModalAjustesActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel78)
                            .addGroup(pnlModalAjustesActividadesLayout.createSequentialGroup()
                                .addComponent(txtNombreUsuarioModal1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(50, 50, 50)
                        .addGroup(pnlModalAjustesActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel79)
                            .addGroup(pnlModalAjustesActividadesLayout.createSequentialGroup()
                                .addComponent(txtNombreUsuarioModal2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30))))
        );
        pnlModalAjustesActividadesLayout.setVerticalGroup(
            pnlModalAjustesActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlModalAjustesActividadesLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pnlModalAjustesActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlModalAjustesActividadesLayout.createSequentialGroup()
                        .addComponent(jLabel79)
                        .addGap(1, 1, 1)
                        .addGroup(pnlModalAjustesActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreUsuarioModal2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlModalAjustesActividadesLayout.createSequentialGroup()
                        .addComponent(jLabel78)
                        .addGap(1, 1, 1)
                        .addGroup(pnlModalAjustesActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreUsuarioModal1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(22, 22, 22)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlModalAjustesActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarModal1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptarModal1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(0, 21, Short.MAX_VALUE))
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
                .addGap(0, 46, Short.MAX_VALUE))
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

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(944, 55));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/rexslogo.png"))); // NOI18N

        jLabel80.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(46, 91, 255));
        jLabel80.setText("Eduardo Estrada");

        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/signout.png"))); // NOI18N
        btnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel80)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCerrarSesion)
                .addGap(80, 80, 80))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(16, 16, 16))
            .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnCerrarSesion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel249.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/grafica.png"))); // NOI18N

        jLabel285.setFont(new java.awt.Font("Rubik Light", 0, 21)); // NOI18N
        jLabel285.setForeground(new java.awt.Color(46, 56, 77));
        jLabel285.setText("a REXS");

        jLabel286.setFont(new java.awt.Font("Rubik Light", 0, 21)); // NOI18N
        jLabel286.setForeground(new java.awt.Color(46, 56, 77));
        jLabel286.setText("Bienvenido");

        jLabel252.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jLabel252.setForeground(new java.awt.Color(135, 152, 173));

        javax.swing.GroupLayout jPanel117Layout = new javax.swing.GroupLayout(jPanel117);
        jPanel117.setLayout(jPanel117Layout);
        jPanel117Layout.setHorizontalGroup(
            jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel117Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel249)
                .addGap(32, 32, 32)
                .addGroup(jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel285)
                    .addComponent(jLabel286)
                    .addComponent(jLabel252, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel117Layout.setVerticalGroup(
            jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel117Layout.createSequentialGroup()
                .addGroup(jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel117Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel249)
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addGroup(jPanel117Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel286)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel285)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel252, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addContainerGap(34, Short.MAX_VALUE))
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
                .addContainerGap(19, Short.MAX_VALUE))
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
                .addContainerGap(14, Short.MAX_VALUE))
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
                .addGroup(jPanel116Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel116Layout.createSequentialGroup()
                        .addComponent(jPanel118, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel125, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel117, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(jPanel118, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)))
            .addComponent(jPanel126, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
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
                .addContainerGap(29, Short.MAX_VALUE))
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
                .addContainerGap(65, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
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
                    .addComponent(jPanel127, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel115Layout.setVerticalGroup(
            jPanel115Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel115Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jPanel116, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel127, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        pnlDashboard.setViewportView(jPanel115);

        pnlCardLayoutAdmin.add(pnlDashboard, "Dashboard");

        javax.swing.GroupLayout pnlAnaliticasLayout = new javax.swing.GroupLayout(pnlAnaliticas);
        pnlAnaliticas.setLayout(pnlAnaliticasLayout);
        pnlAnaliticasLayout.setHorizontalGroup(
            pnlAnaliticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 944, Short.MAX_VALUE)
        );
        pnlAnaliticasLayout.setVerticalGroup(
            pnlAnaliticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
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
        jPanel7.setPreferredSize(new java.awt.Dimension(185, 90));

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconCircleActiveUsers.png"))); // NOI18N

        jLabel46.setFont(new java.awt.Font("Rubik Light", 0, 32)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(46, 56, 77));
        jLabel46.setText("433");

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
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46)
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
        jPanel9.setPreferredSize(new java.awt.Dimension(185, 90));

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconCircleTotalUsers.png"))); // NOI18N

        jLabel43.setFont(new java.awt.Font("Rubik Light", 0, 32)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(46, 56, 77));
        jLabel43.setText("1,055");

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
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addComponent(jLabel42))
                .addGap(2, 2, 2)
                .addComponent(jLabel44)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jComboBox1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true));

        jComboBox2.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(135, 152, 173));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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
                .addContainerGap(22, Short.MAX_VALUE))
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
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(btnAgregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrarLista, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpnlTableUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFiltrarLista, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addComponent(btnAgregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))))
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
                .addContainerGap(72, Short.MAX_VALUE))
        );
        pnlUsuariosLayout.setVerticalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuariosLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel10)
                .addGap(45, 45, 45)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE))
        );

        pnlCardLayoutAdmin.add(pnlUsuarios, "Usuarios");

        pnlProyectos.setBackground(new java.awt.Color(244, 246, 252));

        jPanel31.setBackground(new java.awt.Color(244, 246, 252));
        jPanel31.setPreferredSize(new java.awt.Dimension(808, 545));

        jScrollPane1.setBorder(null);

        jPanel112.setBackground(new java.awt.Color(244, 246, 252));
        jPanel112.setLayout(new java.awt.GridLayout(0, 2, 15, 20));
        jScrollPane1.setViewportView(jPanel112);

        jLabel214.setFont(new java.awt.Font("Rubik Light", 0, 22)); // NOI18N
        jLabel214.setForeground(new java.awt.Color(46, 56, 77));
        jLabel214.setText("Proyectos");

        btnAgregarEspecialidad1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Button_Add.png"))); // NOI18N
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
        btnFiltrarLista1.setBorderPainted(false);
        btnFiltrarLista1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarLista1ActionPerformed(evt);
            }
        });

        jLabel230.setText("240 en total ");

        btnFiltrarLista2.setBackground(new java.awt.Color(46, 91, 255));
        btnFiltrarLista2.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        btnFiltrarLista2.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltrarLista2.setText("Actualizar");
        btnFiltrarLista2.setBorderPainted(false);
        btnFiltrarLista2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarLista2ActionPerformed(evt);
            }
        });

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
                .addComponent(btnFiltrarLista2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregarEspecialidad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnFiltrarLista2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout pnlProyectosLayout = new javax.swing.GroupLayout(pnlProyectos);
        pnlProyectos.setLayout(pnlProyectosLayout);
        pnlProyectosLayout.setHorizontalGroup(
            pnlProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProyectosLayout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        pnlProyectosLayout.setVerticalGroup(
            pnlProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProyectosLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
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

        jpnlTableNiveles.setBackground(new java.awt.Color(244, 246, 252));
        jpnlTableNiveles.setBorder(null);
        jpnlTableNiveles.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel29.setBackground(new java.awt.Color(244, 246, 252));

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel30.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel162.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel162.setForeground(new java.awt.Color(46, 56, 77));
        jLabel162.setText("2º Año de Bachillerato");

        lblEditarUsuario1.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblEditarUsuario1.setForeground(new java.awt.Color(46, 91, 255));
        lblEditarUsuario1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditarUsuario1.setText("EDITAR");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel162)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                .addComponent(lblEditarUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel162)
                .addComponent(lblEditarUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlNivel.setBackground(new java.awt.Color(255, 255, 255));
        pnlNivel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        pnlNivel.setPreferredSize(new java.awt.Dimension(576, 52));
        pnlNivel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlNivelMouseClicked(evt);
            }
        });

        jLabel190.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel190.setForeground(new java.awt.Color(46, 56, 77));
        jLabel190.setText("1º Año de Bachillerato");

        lblEditarUsuario2.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblEditarUsuario2.setForeground(new java.awt.Color(46, 91, 255));
        lblEditarUsuario2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditarUsuario2.setText("EDITAR");

        javax.swing.GroupLayout pnlNivelLayout = new javax.swing.GroupLayout(pnlNivel);
        pnlNivel.setLayout(pnlNivelLayout);
        pnlNivelLayout.setHorizontalGroup(
            pnlNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNivelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel190)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                .addComponent(lblEditarUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        pnlNivelLayout.setVerticalGroup(
            pnlNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel190)
                .addComponent(lblEditarUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));
        jPanel32.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel32.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel191.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel191.setForeground(new java.awt.Color(46, 56, 77));
        jLabel191.setText("3º Año de Bachillerato");

        lblEditarUsuario8.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblEditarUsuario8.setForeground(new java.awt.Color(46, 91, 255));
        lblEditarUsuario8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditarUsuario8.setText("EDITAR");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel191)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                .addComponent(lblEditarUsuario8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel191)
                .addComponent(lblEditarUsuario8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel35.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel192.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel192.setForeground(new java.awt.Color(46, 56, 77));
        jLabel192.setText("8º Grado");

        lblEditarUsuario9.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblEditarUsuario9.setForeground(new java.awt.Color(46, 91, 255));
        lblEditarUsuario9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditarUsuario9.setText("EDITAR");

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel192)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 218, Short.MAX_VALUE)
                .addComponent(lblEditarUsuario9, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel192)
                .addComponent(lblEditarUsuario9, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));
        jPanel36.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel36.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel202.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel202.setForeground(new java.awt.Color(46, 56, 77));
        jLabel202.setText("7º Grado");

        lblEditarUsuario10.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblEditarUsuario10.setForeground(new java.awt.Color(46, 91, 255));
        lblEditarUsuario10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditarUsuario10.setText("EDITAR");

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel202)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 218, Short.MAX_VALUE)
                .addComponent(lblEditarUsuario10, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel202)
                .addComponent(lblEditarUsuario10, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel87.setBackground(new java.awt.Color(255, 255, 255));
        jPanel87.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel87.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel203.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel203.setForeground(new java.awt.Color(46, 56, 77));
        jLabel203.setText("9º Grado");

        lblEditarUsuario11.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblEditarUsuario11.setForeground(new java.awt.Color(46, 91, 255));
        lblEditarUsuario11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditarUsuario11.setText("EDITAR");

        javax.swing.GroupLayout jPanel87Layout = new javax.swing.GroupLayout(jPanel87);
        jPanel87.setLayout(jPanel87Layout);
        jPanel87Layout.setHorizontalGroup(
            jPanel87Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel87Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel203)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 218, Short.MAX_VALUE)
                .addComponent(lblEditarUsuario11, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel87Layout.setVerticalGroup(
            jPanel87Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel87Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel203)
                .addComponent(lblEditarUsuario11, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel87, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addComponent(pnlNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel87, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jpnlTableNiveles.setViewportView(jPanel29);

        jpnlTableEspecialidades.setBackground(new java.awt.Color(244, 246, 252));
        jpnlTableEspecialidades.setBorder(null);
        jpnlTableEspecialidades.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel88.setBackground(new java.awt.Color(244, 246, 252));

        jPanel97.setBackground(new java.awt.Color(255, 255, 255));
        jPanel97.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel97.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel204.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel204.setForeground(new java.awt.Color(46, 56, 77));
        jLabel204.setText("Administrativo Contable");

        lblEditarUsuario3.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblEditarUsuario3.setForeground(new java.awt.Color(46, 91, 255));
        lblEditarUsuario3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditarUsuario3.setText("EDITAR");

        javax.swing.GroupLayout jPanel97Layout = new javax.swing.GroupLayout(jPanel97);
        jPanel97.setLayout(jPanel97Layout);
        jPanel97Layout.setHorizontalGroup(
            jPanel97Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel97Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel204)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
                .addComponent(lblEditarUsuario3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel97Layout.setVerticalGroup(
            jPanel97Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel97Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel204)
                .addComponent(lblEditarUsuario3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel98.setBackground(new java.awt.Color(255, 255, 255));
        jPanel98.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel98.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel205.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel205.setForeground(new java.awt.Color(46, 56, 77));
        jLabel205.setText("Desarrollo de Software");

        lblEditarUsuario4.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblEditarUsuario4.setForeground(new java.awt.Color(46, 91, 255));
        lblEditarUsuario4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditarUsuario4.setText("EDITAR");

        javax.swing.GroupLayout jPanel98Layout = new javax.swing.GroupLayout(jPanel98);
        jPanel98.setLayout(jPanel98Layout);
        jPanel98Layout.setHorizontalGroup(
            jPanel98Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel98Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel205)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
                .addComponent(lblEditarUsuario4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel98Layout.setVerticalGroup(
            jPanel98Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel98Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel205)
                .addComponent(lblEditarUsuario4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel103.setBackground(new java.awt.Color(255, 255, 255));
        jPanel103.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel103.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel206.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel206.setForeground(new java.awt.Color(46, 56, 77));
        jLabel206.setText("Diseño Gráfico");

        lblEditarUsuario5.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblEditarUsuario5.setForeground(new java.awt.Color(46, 91, 255));
        lblEditarUsuario5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditarUsuario5.setText("EDITAR");

        javax.swing.GroupLayout jPanel103Layout = new javax.swing.GroupLayout(jPanel103);
        jPanel103.setLayout(jPanel103Layout);
        jPanel103Layout.setHorizontalGroup(
            jPanel103Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel103Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel206)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                .addComponent(lblEditarUsuario5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel103Layout.setVerticalGroup(
            jPanel103Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel103Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel206)
                .addComponent(lblEditarUsuario5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel104.setBackground(new java.awt.Color(255, 255, 255));
        jPanel104.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel104.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel207.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel207.setForeground(new java.awt.Color(46, 56, 77));
        jLabel207.setText("Electromecánica");

        lblEditarUsuario6.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblEditarUsuario6.setForeground(new java.awt.Color(46, 91, 255));
        lblEditarUsuario6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditarUsuario6.setText("EDITAR");

        javax.swing.GroupLayout jPanel104Layout = new javax.swing.GroupLayout(jPanel104);
        jPanel104.setLayout(jPanel104Layout);
        jPanel104Layout.setHorizontalGroup(
            jPanel104Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel104Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel207)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
                .addComponent(lblEditarUsuario6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel104Layout.setVerticalGroup(
            jPanel104Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel104Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel207)
                .addComponent(lblEditarUsuario6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel111.setBackground(new java.awt.Color(255, 255, 255));
        jPanel111.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel111.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel208.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel208.setForeground(new java.awt.Color(46, 56, 77));
        jLabel208.setText("Automotriz");

        lblEditarUsuario7.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblEditarUsuario7.setForeground(new java.awt.Color(46, 91, 255));
        lblEditarUsuario7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditarUsuario7.setText("EDITAR");

        javax.swing.GroupLayout jPanel111Layout = new javax.swing.GroupLayout(jPanel111);
        jPanel111.setLayout(jPanel111Layout);
        jPanel111Layout.setHorizontalGroup(
            jPanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel111Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel208)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 211, Short.MAX_VALUE)
                .addComponent(lblEditarUsuario7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel111Layout.setVerticalGroup(
            jPanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel208)
                .addComponent(lblEditarUsuario7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel88Layout = new javax.swing.GroupLayout(jPanel88);
        jPanel88.setLayout(jPanel88Layout);
        jPanel88Layout.setHorizontalGroup(
            jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel88Layout.createSequentialGroup()
                .addGroup(jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel97, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel98, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel103, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel104, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel111, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel88Layout.setVerticalGroup(
            jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel88Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel98, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel97, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel103, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel111, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel104, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jpnlTableEspecialidades.setViewportView(jPanel88);

        btnAgregarNivel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Button_Add.png"))); // NOI18N
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

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(jLabel98)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnlTableNiveles, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel161))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel160)
                    .addComponent(jpnlTableEspecialidades, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addComponent(btnAgregarNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregarEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(jLabel98)
                .addGap(29, 29, 29)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel161)
                    .addComponent(jLabel160))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jpnlTableEspecialidades, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                            .addComponent(jpnlTableNiveles, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(btnAgregarEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAgregarNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57))
        );

        javax.swing.GroupLayout pnlUbicacionesLayout = new javax.swing.GroupLayout(pnlUbicaciones);
        pnlUbicaciones.setLayout(pnlUbicacionesLayout);
        pnlUbicacionesLayout.setHorizontalGroup(
            pnlUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUbicacionesLayout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        pnlUbicacionesLayout.setVerticalGroup(
            pnlUbicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUbicacionesLayout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pnlCardLayoutAdmin.add(pnlUbicaciones, "Ubicaciones");

        pnlSeccion.setBackground(new java.awt.Color(244, 246, 252));

        jPanel113.setBackground(new java.awt.Color(244, 246, 252));

        jLabel209.setFont(new java.awt.Font("Rubik Light", 0, 22)); // NOI18N
        jLabel209.setForeground(new java.awt.Color(46, 56, 77));
        jLabel209.setText("Secciones de 1º Año de Bachillerato");

        jpnlTableSecciones.setBackground(new java.awt.Color(244, 246, 252));
        jpnlTableSecciones.setBorder(null);
        jpnlTableSecciones.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel114.setBackground(new java.awt.Color(244, 246, 252));

        jPanel119.setBackground(new java.awt.Color(255, 255, 255));
        jPanel119.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel119.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel216.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel216.setForeground(new java.awt.Color(46, 56, 77));
        jLabel216.setText("Grupo 1");

        lblEditarUsuario16.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblEditarUsuario16.setForeground(new java.awt.Color(46, 91, 255));
        lblEditarUsuario16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditarUsuario16.setText("EDITAR");

        jLabel217.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel217.setForeground(new java.awt.Color(46, 56, 77));
        jLabel217.setText("Ubicación: Laboratorio 1 Pabellón 2");

        javax.swing.GroupLayout jPanel119Layout = new javax.swing.GroupLayout(jPanel119);
        jPanel119.setLayout(jPanel119Layout);
        jPanel119Layout.setHorizontalGroup(
            jPanel119Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel119Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel216)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 399, Short.MAX_VALUE)
                .addComponent(jLabel217)
                .addGap(18, 18, 18)
                .addComponent(lblEditarUsuario16, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel119Layout.setVerticalGroup(
            jPanel119Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel119Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel216)
                .addComponent(lblEditarUsuario16, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel217))
        );

        jLabel211.setFont(new java.awt.Font("Rubik", 0, 18)); // NOI18N
        jLabel211.setForeground(new java.awt.Color(46, 56, 77));
        jLabel211.setText("Automotriz");

        jPanel121.setBackground(new java.awt.Color(255, 255, 255));
        jPanel121.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel121.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel218.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel218.setForeground(new java.awt.Color(46, 56, 77));
        jLabel218.setText("Grupo 2");

        lblEditarUsuario18.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblEditarUsuario18.setForeground(new java.awt.Color(46, 91, 255));
        lblEditarUsuario18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditarUsuario18.setText("EDITAR");

        jLabel219.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel219.setForeground(new java.awt.Color(46, 56, 77));
        jLabel219.setText("Ubicación: Laboratorio 1 Pabellón 4");

        javax.swing.GroupLayout jPanel121Layout = new javax.swing.GroupLayout(jPanel121);
        jPanel121.setLayout(jPanel121Layout);
        jPanel121Layout.setHorizontalGroup(
            jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel121Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel218)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 399, Short.MAX_VALUE)
                .addComponent(jLabel219)
                .addGap(18, 18, 18)
                .addComponent(lblEditarUsuario18, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel121Layout.setVerticalGroup(
            jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel218)
                .addComponent(lblEditarUsuario18, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel219))
        );

        jPanel122.setBackground(new java.awt.Color(255, 255, 255));
        jPanel122.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel122.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel220.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel220.setForeground(new java.awt.Color(46, 56, 77));
        jLabel220.setText("Grupo 2");

        lblEditarUsuario19.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblEditarUsuario19.setForeground(new java.awt.Color(46, 91, 255));
        lblEditarUsuario19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditarUsuario19.setText("EDITAR");

        jLabel221.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel221.setForeground(new java.awt.Color(46, 56, 77));
        jLabel221.setText("Ubicación: Laboratorio 1 Pabellón 4");

        javax.swing.GroupLayout jPanel122Layout = new javax.swing.GroupLayout(jPanel122);
        jPanel122.setLayout(jPanel122Layout);
        jPanel122Layout.setHorizontalGroup(
            jPanel122Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel122Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel220)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 399, Short.MAX_VALUE)
                .addComponent(jLabel221)
                .addGap(18, 18, 18)
                .addComponent(lblEditarUsuario19, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel122Layout.setVerticalGroup(
            jPanel122Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel122Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel220)
                .addComponent(lblEditarUsuario19, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel221))
        );

        jPanel120.setBackground(new java.awt.Color(255, 255, 255));
        jPanel120.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel120.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel222.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel222.setForeground(new java.awt.Color(46, 56, 77));
        jLabel222.setText("Grupo 1");

        lblEditarUsuario17.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblEditarUsuario17.setForeground(new java.awt.Color(46, 91, 255));
        lblEditarUsuario17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditarUsuario17.setText("EDITAR");

        jLabel223.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel223.setForeground(new java.awt.Color(46, 56, 77));
        jLabel223.setText("Ubicación: Laboratorio 1 Pabellón 2");

        javax.swing.GroupLayout jPanel120Layout = new javax.swing.GroupLayout(jPanel120);
        jPanel120.setLayout(jPanel120Layout);
        jPanel120Layout.setHorizontalGroup(
            jPanel120Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel120Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel222)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 399, Short.MAX_VALUE)
                .addComponent(jLabel223)
                .addGap(18, 18, 18)
                .addComponent(lblEditarUsuario17, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel120Layout.setVerticalGroup(
            jPanel120Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel120Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel222)
                .addComponent(lblEditarUsuario17, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel223))
        );

        jLabel212.setFont(new java.awt.Font("Rubik", 0, 18)); // NOI18N
        jLabel212.setForeground(new java.awt.Color(46, 56, 77));
        jLabel212.setText("Administrativo Contable");

        jPanel123.setBackground(new java.awt.Color(255, 255, 255));
        jPanel123.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel123.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel224.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel224.setForeground(new java.awt.Color(46, 56, 77));
        jLabel224.setText("Grupo 2");

        lblEditarUsuario20.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblEditarUsuario20.setForeground(new java.awt.Color(46, 91, 255));
        lblEditarUsuario20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditarUsuario20.setText("EDITAR");

        jLabel225.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel225.setForeground(new java.awt.Color(46, 56, 77));
        jLabel225.setText("Ubicación: Laboratorio 1 Pabellón 4");

        javax.swing.GroupLayout jPanel123Layout = new javax.swing.GroupLayout(jPanel123);
        jPanel123.setLayout(jPanel123Layout);
        jPanel123Layout.setHorizontalGroup(
            jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel123Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel224)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 399, Short.MAX_VALUE)
                .addComponent(jLabel225)
                .addGap(18, 18, 18)
                .addComponent(lblEditarUsuario20, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel123Layout.setVerticalGroup(
            jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel224)
                .addComponent(lblEditarUsuario20, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel225))
        );

        jPanel124.setBackground(new java.awt.Color(255, 255, 255));
        jPanel124.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel124.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel226.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel226.setForeground(new java.awt.Color(46, 56, 77));
        jLabel226.setText("Grupo 1");

        lblEditarUsuario21.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblEditarUsuario21.setForeground(new java.awt.Color(46, 91, 255));
        lblEditarUsuario21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditarUsuario21.setText("EDITAR");

        jLabel227.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel227.setForeground(new java.awt.Color(46, 56, 77));
        jLabel227.setText("Ubicación: Laboratorio 1 Pabellón 2");

        javax.swing.GroupLayout jPanel124Layout = new javax.swing.GroupLayout(jPanel124);
        jPanel124.setLayout(jPanel124Layout);
        jPanel124Layout.setHorizontalGroup(
            jPanel124Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel124Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel226)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 399, Short.MAX_VALUE)
                .addComponent(jLabel227)
                .addGap(18, 18, 18)
                .addComponent(lblEditarUsuario21, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel124Layout.setVerticalGroup(
            jPanel124Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel124Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel226)
                .addComponent(lblEditarUsuario21, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel227))
        );

        jLabel213.setFont(new java.awt.Font("Rubik", 0, 18)); // NOI18N
        jLabel213.setForeground(new java.awt.Color(46, 56, 77));
        jLabel213.setText("Electromecánica");

        javax.swing.GroupLayout jPanel114Layout = new javax.swing.GroupLayout(jPanel114);
        jPanel114.setLayout(jPanel114Layout);
        jPanel114Layout.setHorizontalGroup(
            jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel114Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel213)
                    .addComponent(jPanel123, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel124, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel212)
                    .addComponent(jPanel122, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel120, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel211)
                    .addComponent(jPanel121, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel119, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel114Layout.setVerticalGroup(
            jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel114Layout.createSequentialGroup()
                .addComponent(jLabel211)
                .addGap(18, 18, 18)
                .addComponent(jPanel119, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel121, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel212)
                .addGap(18, 18, 18)
                .addComponent(jPanel120, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel122, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jLabel213)
                .addGap(18, 18, 18)
                .addComponent(jPanel124, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel123, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpnlTableSecciones.setViewportView(jPanel114);

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

        javax.swing.GroupLayout jPanel113Layout = new javax.swing.GroupLayout(jPanel113);
        jPanel113.setLayout(jPanel113Layout);
        jPanel113Layout.setHorizontalGroup(
            jPanel113Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlTableSecciones, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel113Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(62, 62, 62)
                .addComponent(jpnlTableSecciones, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAgregarSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(pnlSeccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel210)
                    .addComponent(jPanel113, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnlCardLayoutAdmin.add(pnlSeccion, "Seccion");

        pnlActividades.setBackground(new java.awt.Color(244, 246, 252));
        pnlActividades.setPreferredSize(new java.awt.Dimension(944, 590));

        pnlInnerActividades.setBackground(new java.awt.Color(244, 246, 252));

        jLabel109.setFont(new java.awt.Font("Rubik Light", 0, 22)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(46, 56, 77));
        jLabel109.setText("Actividades");

        jPanel26.setBackground(new java.awt.Color(244, 246, 252));
        jPanel26.setPreferredSize(new java.awt.Dimension(600, 303));

        btnAgregarActividad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Button_Add.png"))); // NOI18N
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

        jPanel61.setBackground(new java.awt.Color(244, 246, 252));

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 167, Short.MAX_VALUE)
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 325, Short.MAX_VALUE)
        );

        jpnlDia2.setViewportView(jPanel61);

        jpnlDia3.setBackground(new java.awt.Color(244, 246, 252));
        jpnlDia3.setBorder(null);
        jpnlDia3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jpnlDia3.setPreferredSize(new java.awt.Dimension(167, 325));

        jPanel72.setBackground(new java.awt.Color(244, 246, 252));

        jPanel73.setBackground(new java.awt.Color(255, 255, 255));
        jPanel73.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 193, 212), 1, true));
        jPanel73.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel76.setBackground(new java.awt.Color(0, 193, 212));
        jPanel76.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel76Layout = new javax.swing.GroupLayout(jPanel76);
        jPanel76.setLayout(jPanel76Layout);
        jPanel76Layout.setHorizontalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel76Layout.setVerticalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel151.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel151.setForeground(new java.awt.Color(135, 152, 173));
        jLabel151.setText("8:00 AM - 9:00 AM");

        jLabel152.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel152.setForeground(new java.awt.Color(46, 56, 77));
        jLabel152.setText("Titulo de actividad prueba");

        jLabel153.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditCyan.png"))); // NOI18N

        javax.swing.GroupLayout jPanel73Layout = new javax.swing.GroupLayout(jPanel73);
        jPanel73.setLayout(jPanel73Layout);
        jPanel73Layout.setHorizontalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel73Layout.createSequentialGroup()
                .addComponent(jPanel76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel73Layout.createSequentialGroup()
                        .addComponent(jLabel152)
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addGroup(jPanel73Layout.createSequentialGroup()
                        .addComponent(jLabel151)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel153)))
                .addContainerGap())
        );
        jPanel73Layout.setVerticalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel76, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addGroup(jPanel73Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel152)
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel73Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel151)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel73Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jLabel153)
                        .addContainerGap())))
        );

        jPanel77.setBackground(new java.awt.Color(255, 255, 255));
        jPanel77.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 193, 212), 1, true));
        jPanel77.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel78.setBackground(new java.awt.Color(0, 193, 212));
        jPanel78.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel78Layout = new javax.swing.GroupLayout(jPanel78);
        jPanel78.setLayout(jPanel78Layout);
        jPanel78Layout.setHorizontalGroup(
            jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel78Layout.setVerticalGroup(
            jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel157.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel157.setForeground(new java.awt.Color(135, 152, 173));
        jLabel157.setText("8:00 AM - 9:00 AM");

        jLabel158.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel158.setForeground(new java.awt.Color(46, 56, 77));
        jLabel158.setText("Titulo de actividad prueba");

        jLabel159.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditCyan.png"))); // NOI18N

        javax.swing.GroupLayout jPanel77Layout = new javax.swing.GroupLayout(jPanel77);
        jPanel77.setLayout(jPanel77Layout);
        jPanel77Layout.setHorizontalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel77Layout.createSequentialGroup()
                .addComponent(jPanel78, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel77Layout.createSequentialGroup()
                        .addComponent(jLabel158)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel77Layout.createSequentialGroup()
                        .addComponent(jLabel157)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel159)))
                .addContainerGap())
        );
        jPanel77Layout.setVerticalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel78, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addGroup(jPanel77Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel158)
                .addGroup(jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel77Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel157)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel77Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jLabel159)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel72Layout = new javax.swing.GroupLayout(jPanel72);
        jPanel72.setLayout(jPanel72Layout);
        jPanel72Layout.setHorizontalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel72Layout.createSequentialGroup()
                .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel73, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel77, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel72Layout.setVerticalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel72Layout.createSequentialGroup()
                .addComponent(jPanel73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 170, Short.MAX_VALUE))
        );

        jpnlDia3.setViewportView(jPanel72);

        jpnlDia4.setBackground(new java.awt.Color(244, 246, 252));
        jpnlDia4.setBorder(null);
        jpnlDia4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jpnlDia4.setPreferredSize(new java.awt.Dimension(167, 325));

        jPanel79.setBackground(new java.awt.Color(244, 246, 252));
        jPanel79.setPreferredSize(new java.awt.Dimension(167, 653));

        jPanel83.setBackground(new java.awt.Color(255, 255, 255));
        jPanel83.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(247, 193, 55), 1, true));
        jPanel83.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel84.setBackground(new java.awt.Color(247, 193, 55));
        jPanel84.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel84Layout = new javax.swing.GroupLayout(jPanel84);
        jPanel84.setLayout(jPanel84Layout);
        jPanel84Layout.setHorizontalGroup(
            jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel84Layout.setVerticalGroup(
            jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel166.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel166.setForeground(new java.awt.Color(135, 152, 173));
        jLabel166.setText("8:00 AM - 9:00 AM");

        jLabel167.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel167.setForeground(new java.awt.Color(46, 56, 77));
        jLabel167.setText("Titulo de actividad prueba");

        jLabel168.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditOrange.png"))); // NOI18N

        javax.swing.GroupLayout jPanel83Layout = new javax.swing.GroupLayout(jPanel83);
        jPanel83.setLayout(jPanel83Layout);
        jPanel83Layout.setHorizontalGroup(
            jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel83Layout.createSequentialGroup()
                .addComponent(jPanel84, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel83Layout.createSequentialGroup()
                        .addComponent(jLabel167)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel83Layout.createSequentialGroup()
                        .addComponent(jLabel166)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel168)))
                .addContainerGap())
        );
        jPanel83Layout.setVerticalGroup(
            jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel84, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addGroup(jPanel83Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel167)
                .addGroup(jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel83Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel166)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel83Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jLabel168)
                        .addContainerGap())))
        );

        jPanel85.setBackground(new java.awt.Color(255, 255, 255));
        jPanel85.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(247, 193, 55), 1, true));
        jPanel85.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel86.setBackground(new java.awt.Color(247, 193, 55));
        jPanel86.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel86Layout = new javax.swing.GroupLayout(jPanel86);
        jPanel86.setLayout(jPanel86Layout);
        jPanel86Layout.setHorizontalGroup(
            jPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel86Layout.setVerticalGroup(
            jPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel169.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel169.setForeground(new java.awt.Color(135, 152, 173));
        jLabel169.setText("8:00 AM - 9:00 AM");

        jLabel170.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel170.setForeground(new java.awt.Color(46, 56, 77));
        jLabel170.setText("Titulo de actividad prueba");

        jLabel171.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditOrange.png"))); // NOI18N

        javax.swing.GroupLayout jPanel85Layout = new javax.swing.GroupLayout(jPanel85);
        jPanel85.setLayout(jPanel85Layout);
        jPanel85Layout.setHorizontalGroup(
            jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel85Layout.createSequentialGroup()
                .addComponent(jPanel86, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel85Layout.createSequentialGroup()
                        .addComponent(jLabel170)
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addGroup(jPanel85Layout.createSequentialGroup()
                        .addComponent(jLabel169)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel171)))
                .addContainerGap())
        );
        jPanel85Layout.setVerticalGroup(
            jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel86, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addGroup(jPanel85Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel170)
                .addGroup(jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel85Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel169)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel85Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jLabel171)
                        .addContainerGap())))
        );

        jPanel99.setBackground(new java.awt.Color(255, 255, 255));
        jPanel99.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(247, 193, 55), 1, true));
        jPanel99.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel100.setBackground(new java.awt.Color(247, 193, 55));
        jPanel100.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel100Layout = new javax.swing.GroupLayout(jPanel100);
        jPanel100.setLayout(jPanel100Layout);
        jPanel100Layout.setHorizontalGroup(
            jPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel100Layout.setVerticalGroup(
            jPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel184.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel184.setForeground(new java.awt.Color(135, 152, 173));
        jLabel184.setText("8:00 AM - 9:00 AM");

        jLabel185.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel185.setForeground(new java.awt.Color(46, 56, 77));
        jLabel185.setText("Titulo de actividad prueba");

        jLabel186.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditOrange.png"))); // NOI18N

        javax.swing.GroupLayout jPanel99Layout = new javax.swing.GroupLayout(jPanel99);
        jPanel99.setLayout(jPanel99Layout);
        jPanel99Layout.setHorizontalGroup(
            jPanel99Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel99Layout.createSequentialGroup()
                .addComponent(jPanel100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel99Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel99Layout.createSequentialGroup()
                        .addComponent(jLabel185)
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addGroup(jPanel99Layout.createSequentialGroup()
                        .addComponent(jLabel184)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel186)))
                .addContainerGap())
        );
        jPanel99Layout.setVerticalGroup(
            jPanel99Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel100, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addGroup(jPanel99Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel185)
                .addGroup(jPanel99Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel99Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel184)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel99Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jLabel186)
                        .addContainerGap())))
        );

        jPanel101.setBackground(new java.awt.Color(255, 255, 255));
        jPanel101.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(247, 193, 55), 1, true));
        jPanel101.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel102.setBackground(new java.awt.Color(247, 193, 55));
        jPanel102.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel102Layout = new javax.swing.GroupLayout(jPanel102);
        jPanel102.setLayout(jPanel102Layout);
        jPanel102Layout.setHorizontalGroup(
            jPanel102Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel102Layout.setVerticalGroup(
            jPanel102Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel187.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel187.setForeground(new java.awt.Color(135, 152, 173));
        jLabel187.setText("8:00 AM - 9:00 AM");

        jLabel188.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel188.setForeground(new java.awt.Color(46, 56, 77));
        jLabel188.setText("Titulo de actividad prueba");

        jLabel189.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditOrange.png"))); // NOI18N

        javax.swing.GroupLayout jPanel101Layout = new javax.swing.GroupLayout(jPanel101);
        jPanel101.setLayout(jPanel101Layout);
        jPanel101Layout.setHorizontalGroup(
            jPanel101Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel101Layout.createSequentialGroup()
                .addComponent(jPanel102, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel101Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel101Layout.createSequentialGroup()
                        .addComponent(jLabel188)
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addGroup(jPanel101Layout.createSequentialGroup()
                        .addComponent(jLabel187)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel189)))
                .addContainerGap())
        );
        jPanel101Layout.setVerticalGroup(
            jPanel101Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel102, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addGroup(jPanel101Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel188)
                .addGroup(jPanel101Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel101Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel187)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel101Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jLabel189)
                        .addContainerGap())))
        );

        jPanel107.setBackground(new java.awt.Color(255, 255, 255));
        jPanel107.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(247, 193, 55), 1, true));
        jPanel107.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel108.setBackground(new java.awt.Color(247, 193, 55));
        jPanel108.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel108Layout = new javax.swing.GroupLayout(jPanel108);
        jPanel108.setLayout(jPanel108Layout);
        jPanel108Layout.setHorizontalGroup(
            jPanel108Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel108Layout.setVerticalGroup(
            jPanel108Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel196.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel196.setForeground(new java.awt.Color(135, 152, 173));
        jLabel196.setText("8:00 AM - 9:00 AM");

        jLabel197.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel197.setForeground(new java.awt.Color(46, 56, 77));
        jLabel197.setText("Titulo de actividad prueba");

        jLabel198.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditOrange.png"))); // NOI18N

        javax.swing.GroupLayout jPanel107Layout = new javax.swing.GroupLayout(jPanel107);
        jPanel107.setLayout(jPanel107Layout);
        jPanel107Layout.setHorizontalGroup(
            jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel107Layout.createSequentialGroup()
                .addComponent(jPanel108, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel107Layout.createSequentialGroup()
                        .addComponent(jLabel197)
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addGroup(jPanel107Layout.createSequentialGroup()
                        .addComponent(jLabel196)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel198)))
                .addContainerGap())
        );
        jPanel107Layout.setVerticalGroup(
            jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel108, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addGroup(jPanel107Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel197)
                .addGroup(jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel107Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel196)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel107Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jLabel198)
                        .addContainerGap())))
        );

        jPanel109.setBackground(new java.awt.Color(255, 255, 255));
        jPanel109.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(247, 193, 55), 1, true));
        jPanel109.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel110.setBackground(new java.awt.Color(247, 193, 55));
        jPanel110.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel110Layout = new javax.swing.GroupLayout(jPanel110);
        jPanel110.setLayout(jPanel110Layout);
        jPanel110Layout.setHorizontalGroup(
            jPanel110Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel110Layout.setVerticalGroup(
            jPanel110Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel199.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel199.setForeground(new java.awt.Color(135, 152, 173));
        jLabel199.setText("8:00 AM - 9:00 AM");

        jLabel200.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel200.setForeground(new java.awt.Color(46, 56, 77));
        jLabel200.setText("Titulo de actividad prueba");

        jLabel201.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditOrange.png"))); // NOI18N

        javax.swing.GroupLayout jPanel109Layout = new javax.swing.GroupLayout(jPanel109);
        jPanel109.setLayout(jPanel109Layout);
        jPanel109Layout.setHorizontalGroup(
            jPanel109Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel109Layout.createSequentialGroup()
                .addComponent(jPanel110, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel109Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel109Layout.createSequentialGroup()
                        .addComponent(jLabel200)
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addGroup(jPanel109Layout.createSequentialGroup()
                        .addComponent(jLabel199)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel201)))
                .addContainerGap())
        );
        jPanel109Layout.setVerticalGroup(
            jPanel109Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel110, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addGroup(jPanel109Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel200)
                .addGroup(jPanel109Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel109Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel199)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel109Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jLabel201)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel79Layout = new javax.swing.GroupLayout(jPanel79);
        jPanel79.setLayout(jPanel79Layout);
        jPanel79Layout.setHorizontalGroup(
            jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel79Layout.createSequentialGroup()
                .addGroup(jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel85, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel83, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel101, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel99, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel109, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel107, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 17, Short.MAX_VALUE))
        );
        jPanel79Layout.setVerticalGroup(
            jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel79Layout.createSequentialGroup()
                .addComponent(jPanel83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel85, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel101, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel99, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel109, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel107, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnlDia4.setViewportView(jPanel79);

        jpnlDia5.setBackground(new java.awt.Color(244, 246, 252));
        jpnlDia5.setBorder(null);
        jpnlDia5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jpnlDia5.setPreferredSize(new java.awt.Dimension(167, 325));

        jPanel80.setBackground(new java.awt.Color(244, 246, 252));
        jPanel80.setPreferredSize(new java.awt.Dimension(167, 404));

        jPanel89.setBackground(new java.awt.Color(255, 255, 255));
        jPanel89.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 172, 46), 1, true));
        jPanel89.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel90.setBackground(new java.awt.Color(51, 172, 46));
        jPanel90.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel90Layout = new javax.swing.GroupLayout(jPanel90);
        jPanel90.setLayout(jPanel90Layout);
        jPanel90Layout.setHorizontalGroup(
            jPanel90Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel90Layout.setVerticalGroup(
            jPanel90Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel172.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel172.setForeground(new java.awt.Color(135, 152, 173));
        jLabel172.setText("8:00 AM - 9:00 AM");

        jLabel173.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel173.setForeground(new java.awt.Color(46, 56, 77));
        jLabel173.setText("Titulo de actividad prueba");

        jLabel174.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditGreen.png"))); // NOI18N

        javax.swing.GroupLayout jPanel89Layout = new javax.swing.GroupLayout(jPanel89);
        jPanel89.setLayout(jPanel89Layout);
        jPanel89Layout.setHorizontalGroup(
            jPanel89Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel89Layout.createSequentialGroup()
                .addComponent(jPanel90, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel89Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel89Layout.createSequentialGroup()
                        .addComponent(jLabel173)
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addGroup(jPanel89Layout.createSequentialGroup()
                        .addComponent(jLabel172)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel174)))
                .addContainerGap())
        );
        jPanel89Layout.setVerticalGroup(
            jPanel89Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel90, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addGroup(jPanel89Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel173)
                .addGroup(jPanel89Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel89Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel172)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel89Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jLabel174)
                        .addContainerGap())))
        );

        jPanel91.setBackground(new java.awt.Color(255, 255, 255));
        jPanel91.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 172, 46), 1, true));
        jPanel91.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel92.setBackground(new java.awt.Color(51, 172, 46));
        jPanel92.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel92Layout = new javax.swing.GroupLayout(jPanel92);
        jPanel92.setLayout(jPanel92Layout);
        jPanel92Layout.setHorizontalGroup(
            jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel92Layout.setVerticalGroup(
            jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel175.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel175.setForeground(new java.awt.Color(135, 152, 173));
        jLabel175.setText("8:00 AM - 9:00 AM");

        jLabel176.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel176.setForeground(new java.awt.Color(46, 56, 77));
        jLabel176.setText("Titulo de actividad prueba");

        jLabel177.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditGreen.png"))); // NOI18N

        javax.swing.GroupLayout jPanel91Layout = new javax.swing.GroupLayout(jPanel91);
        jPanel91.setLayout(jPanel91Layout);
        jPanel91Layout.setHorizontalGroup(
            jPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel91Layout.createSequentialGroup()
                .addComponent(jPanel92, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel91Layout.createSequentialGroup()
                        .addComponent(jLabel176)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel91Layout.createSequentialGroup()
                        .addComponent(jLabel175)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel177)))
                .addContainerGap())
        );
        jPanel91Layout.setVerticalGroup(
            jPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel92, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addGroup(jPanel91Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel176)
                .addGroup(jPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel91Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel175)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel91Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jLabel177)
                        .addContainerGap())))
        );

        jPanel93.setBackground(new java.awt.Color(255, 255, 255));
        jPanel93.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 172, 46), 1, true));
        jPanel93.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel94.setBackground(new java.awt.Color(51, 172, 46));
        jPanel94.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel94Layout = new javax.swing.GroupLayout(jPanel94);
        jPanel94.setLayout(jPanel94Layout);
        jPanel94Layout.setHorizontalGroup(
            jPanel94Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel94Layout.setVerticalGroup(
            jPanel94Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel178.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel178.setForeground(new java.awt.Color(135, 152, 173));
        jLabel178.setText("8:00 AM - 9:00 AM");

        jLabel179.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel179.setForeground(new java.awt.Color(46, 56, 77));
        jLabel179.setText("Titulo de actividad prueba");

        jLabel180.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditGreen.png"))); // NOI18N

        javax.swing.GroupLayout jPanel93Layout = new javax.swing.GroupLayout(jPanel93);
        jPanel93.setLayout(jPanel93Layout);
        jPanel93Layout.setHorizontalGroup(
            jPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel93Layout.createSequentialGroup()
                .addComponent(jPanel94, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel93Layout.createSequentialGroup()
                        .addComponent(jLabel179)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel93Layout.createSequentialGroup()
                        .addComponent(jLabel178)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel180)))
                .addContainerGap())
        );
        jPanel93Layout.setVerticalGroup(
            jPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel94, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addGroup(jPanel93Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel179)
                .addGroup(jPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel93Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel178)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel93Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jLabel180)
                        .addContainerGap())))
        );

        jPanel95.setBackground(new java.awt.Color(255, 255, 255));
        jPanel95.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 172, 46), 1, true));
        jPanel95.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel96.setBackground(new java.awt.Color(51, 172, 46));
        jPanel96.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel96Layout = new javax.swing.GroupLayout(jPanel96);
        jPanel96.setLayout(jPanel96Layout);
        jPanel96Layout.setHorizontalGroup(
            jPanel96Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel96Layout.setVerticalGroup(
            jPanel96Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel181.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel181.setForeground(new java.awt.Color(135, 152, 173));
        jLabel181.setText("8:00 AM - 9:00 AM");

        jLabel182.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel182.setForeground(new java.awt.Color(46, 56, 77));
        jLabel182.setText("Titulo de actividad prueba");

        jLabel183.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditGreen.png"))); // NOI18N

        javax.swing.GroupLayout jPanel95Layout = new javax.swing.GroupLayout(jPanel95);
        jPanel95.setLayout(jPanel95Layout);
        jPanel95Layout.setHorizontalGroup(
            jPanel95Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel95Layout.createSequentialGroup()
                .addComponent(jPanel96, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel95Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel95Layout.createSequentialGroup()
                        .addComponent(jLabel182)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel95Layout.createSequentialGroup()
                        .addComponent(jLabel181)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel183)))
                .addContainerGap())
        );
        jPanel95Layout.setVerticalGroup(
            jPanel95Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel96, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
            .addGroup(jPanel95Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel182)
                .addGroup(jPanel95Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel95Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel181)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel95Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jLabel183)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel80Layout = new javax.swing.GroupLayout(jPanel80);
        jPanel80.setLayout(jPanel80Layout);
        jPanel80Layout.setHorizontalGroup(
            jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel80Layout.createSequentialGroup()
                .addGroup(jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel93, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel95, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel91, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel80Layout.setVerticalGroup(
            jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel80Layout.createSequentialGroup()
                .addComponent(jPanel95, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel93, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel91, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel89, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpnlDia5.setViewportView(jPanel80);

        jPanel25.setBackground(new java.awt.Color(244, 246, 252));

        jLabel97.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(176, 186, 201));
        jLabel97.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel97.setText("MIÉRCOLES  (25/09/2019)");

        jPanel28.setBackground(new java.awt.Color(244, 246, 252));

        jLabel99.setFont(new java.awt.Font("Rubik Medium", 0, 9)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(46, 91, 255));
        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel99.setText("5");
        jLabel99.setPreferredSize(new java.awt.Dimension(24, 24));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel97)
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel97, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel53.setBackground(new java.awt.Color(244, 246, 252));

        jLabel128.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel128.setForeground(new java.awt.Color(176, 186, 201));
        jLabel128.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel128.setText("JUEVES  (26/09/2019)");

        jPanel54.setBackground(new java.awt.Color(244, 246, 252));

        jLabel129.setFont(new java.awt.Font("Rubik Medium", 0, 9)); // NOI18N
        jLabel129.setForeground(new java.awt.Color(140, 84, 255));
        jLabel129.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel129.setText("4");
        jLabel129.setPreferredSize(new java.awt.Dimension(24, 24));

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel129, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel129, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel128)
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel128, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel55.setBackground(new java.awt.Color(244, 246, 252));

        jLabel130.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel130.setForeground(new java.awt.Color(176, 186, 201));
        jLabel130.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel130.setText("VIERNES  (27/09/2019)");

        jPanel56.setBackground(new java.awt.Color(244, 246, 252));

        jLabel131.setFont(new java.awt.Font("Rubik Medium", 0, 9)); // NOI18N
        jLabel131.setForeground(new java.awt.Color(44, 194, 165));
        jLabel131.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel131.setText("3");
        jLabel131.setPreferredSize(new java.awt.Dimension(24, 24));

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel56Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel131, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel56Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel131, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel130)
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel130, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel57.setBackground(new java.awt.Color(244, 246, 252));

        jLabel132.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel132.setForeground(new java.awt.Color(176, 186, 201));
        jLabel132.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel132.setText("SÁBADO  (28/09/2019)");

        jPanel58.setBackground(new java.awt.Color(244, 246, 252));

        jLabel133.setFont(new java.awt.Font("Rubik Medium", 0, 9)); // NOI18N
        jLabel133.setForeground(new java.awt.Color(247, 193, 55));
        jLabel133.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel133.setText("7");
        jLabel133.setPreferredSize(new java.awt.Dimension(24, 24));

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel58Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel133, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel58Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel133, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel132)
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel132, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel59.setBackground(new java.awt.Color(244, 246, 252));

        jLabel134.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel134.setForeground(new java.awt.Color(176, 186, 201));
        jLabel134.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel134.setText("DOMINGO  (29/09/2019)");

        jPanel60.setBackground(new java.awt.Color(244, 246, 252));

        jLabel135.setFont(new java.awt.Font("Rubik Medium", 0, 9)); // NOI18N
        jLabel135.setForeground(new java.awt.Color(51, 172, 46));
        jLabel135.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel135.setText("4");
        jLabel135.setPreferredSize(new java.awt.Dimension(24, 24));

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel60Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel135, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel60Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel135, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel134)
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel134, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setMinimumSize(new java.awt.Dimension(166, 323));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(100, 120));

        jPanel4.setBackground(new java.awt.Color(244, 246, 252));
        jPanel4.setMinimumSize(new java.awt.Dimension(0, 15));
        jPanel4.setLayout(new java.awt.GridLayout(0, 1, 10, 11));
        jScrollPane2.setViewportView(jPanel4);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregarActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnlDia2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnlDia3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnlDia4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpnlDia5, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(btnAgregarActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        btnAjustesActividades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditBlue.png"))); // NOI18N
        btnAjustesActividades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAjustesActividadesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlInnerActividadesLayout = new javax.swing.GroupLayout(pnlInnerActividades);
        pnlInnerActividades.setLayout(pnlInnerActividadesLayout);
        pnlInnerActividadesLayout.setHorizontalGroup(
            pnlInnerActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInnerActividadesLayout.createSequentialGroup()
                .addComponent(jLabel109)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAjustesActividades)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlInnerActividadesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlInnerActividadesLayout.setVerticalGroup(
            pnlInnerActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInnerActividadesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlInnerActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel109, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAjustesActividades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(45, 45, 45)
                .addComponent(pnlInnerActividades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCardLayoutAdmin.add(pnlActividades, "Actividades");

        pnlAjustes.setBorder(null);
        pnlAjustes.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pnlAjustes.setPreferredSize(new java.awt.Dimension(944, 570));

        jPanel2.setBackground(new java.awt.Color(244, 246, 252));
        jPanel2.setPreferredSize(new java.awt.Dimension(944, 1158));

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
                .addContainerGap(171, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
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
                .addContainerGap(28, Short.MAX_VALUE))
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

        jButton9.setBackground(new java.awt.Color(238, 238, 238));
        jButton9.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jButton9.setForeground(new java.awt.Color(107, 107, 107));
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
                .addContainerGap(84, Short.MAX_VALUE))
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
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel81)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
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
                .addComponent(jLabel81)
                .addGap(18, 18, 18)
                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        pnlAjustes.setViewportView(jPanel2);

        pnlCardLayoutAdmin.add(pnlAjustes, "Ajustes");

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)
                    .addComponent(pnlCardLayoutAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlCardLayoutAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 590, Short.MAX_VALUE))
        );

        getContentPane().add(pnlBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        modalUsuario = new JDialog(this, "Nuevo usuario", true);
        modalUsuario.getContentPane().add(pnlModalNuevoUsuario);
        modalUsuario.pack();
        modalUsuario.setLocationRelativeTo(null);
        modalUsuario.setVisible(true);
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

        JDialog modal = new JDialog(this, "Nueva Actividad", true);
        modal.getContentPane().add(modalo);
        modal.pack();
        modal.setLocationRelativeTo(null);
        modal.setVisible(true);
    }//GEN-LAST:event_btnAgregarActividadActionPerformed

    private void btnAgregarNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarNivelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarNivelActionPerformed

    private void btnAgregarEspecialidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEspecialidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarEspecialidadActionPerformed

    private void btnAgregarSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarSeccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarSeccionActionPerformed

    private void pnlNivelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNivelMouseClicked
        CardLayout cl = (CardLayout) (pnlCardLayoutAdmin.getLayout());
        cl.show(pnlCardLayoutAdmin, "Seccion");
    }//GEN-LAST:event_pnlNivelMouseClicked

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
            chooser.setDialogTitle("Escoje la carpeta para guardar el respaldo");
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
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFiltrarLista3ActionPerformed

    private void btnCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseClicked
        CurrentUser.clear();
        Login login = new Login();
        this.setVisible(false);
        login.setLocationRelativeTo(null);
        login.setDefaultCloseOperation(EXIT_ON_CLOSE);
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesionMouseClicked

    private void btnAceptarModal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarModal1ActionPerformed
        modalAjustesActividades.dispose();
    }//GEN-LAST:event_btnAceptarModal1ActionPerformed

    private void btnAjustesActividadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAjustesActividadesMouseClicked
        modalAjustesActividades = new JDialog(this, "Ajustes Actividades", true);
        modalAjustesActividades.getContentPane().add(pnlModalAjustesActividades);
        modalAjustesActividades.pack();
        modalAjustesActividades.setLocationRelativeTo(null);
        modalAjustesActividades.setVisible(true);
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

            } catch (Exception ex) {
                System.out.println(ex);
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

    private void btnFiltrarLista2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarLista2ActionPerformed
        // TODO add your handling code here:
        Projects nuevo = new Projects();
        jPanel112.removeAll();
        nuevo.CrearPanelesProyectos(jPanel112);
    }//GEN-LAST:event_btnFiltrarLista2ActionPerformed

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
    private javax.swing.JButton btnAceptarModal1;
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
    private javax.swing.JButton btnEliminarFotoPerfil;
    private javax.swing.JButton btnFiltrarLista;
    private javax.swing.JButton btnFiltrarLista1;
    private javax.swing.JButton btnFiltrarLista2;
    private javax.swing.JButton btnFiltrarLista3;
    private javax.swing.JPanel btnInicio;
    private javax.swing.JButton btnMenu;
    private javax.swing.JPanel btnProyectos;
    private javax.swing.JPanel btnUbicaciones;
    private javax.swing.JPanel btnUsuarios;
    private javax.swing.JComboBox<String> cbxEstadoUsuarioModal;
    private javax.swing.JComboBox<String> cbxTipoUsuarioModal;
    private javax.swing.JCheckBox checkActividades;
    private javax.swing.JCheckBox checkCombinar;
    private javax.swing.JCheckBox checkProyectos;
    private javax.swing.JCheckBox checkTodo;
    private javax.swing.JCheckBox checkUbicaciones;
    private javax.swing.JCheckBox checkUsuarios;
    private javax.swing.JCheckBox checkVotaciones;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel178;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel180;
    private javax.swing.JLabel jLabel181;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel183;
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel185;
    private javax.swing.JLabel jLabel186;
    private javax.swing.JLabel jLabel187;
    private javax.swing.JLabel jLabel188;
    private javax.swing.JLabel jLabel189;
    private javax.swing.JLabel jLabel190;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel192;
    private javax.swing.JLabel jLabel196;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel199;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel200;
    private javax.swing.JLabel jLabel201;
    private javax.swing.JLabel jLabel202;
    private javax.swing.JLabel jLabel203;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabel206;
    private javax.swing.JLabel jLabel207;
    private javax.swing.JLabel jLabel208;
    private javax.swing.JLabel jLabel209;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel211;
    private javax.swing.JLabel jLabel212;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JLabel jLabel214;
    private javax.swing.JLabel jLabel215;
    private javax.swing.JLabel jLabel216;
    private javax.swing.JLabel jLabel217;
    private javax.swing.JLabel jLabel218;
    private javax.swing.JLabel jLabel219;
    private javax.swing.JLabel jLabel220;
    private javax.swing.JLabel jLabel221;
    private javax.swing.JLabel jLabel222;
    private javax.swing.JLabel jLabel223;
    private javax.swing.JLabel jLabel224;
    private javax.swing.JLabel jLabel225;
    private javax.swing.JLabel jLabel226;
    private javax.swing.JLabel jLabel227;
    private javax.swing.JLabel jLabel228;
    private javax.swing.JLabel jLabel229;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel230;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel249;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel250;
    private javax.swing.JLabel jLabel251;
    private javax.swing.JLabel jLabel252;
    private javax.swing.JLabel jLabel253;
    private javax.swing.JLabel jLabel254;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel285;
    private javax.swing.JLabel jLabel286;
    private javax.swing.JLabel jLabel287;
    private javax.swing.JLabel jLabel288;
    private javax.swing.JLabel jLabel289;
    private javax.swing.JLabel jLabel290;
    private javax.swing.JLabel jLabel291;
    private javax.swing.JLabel jLabel292;
    private javax.swing.JLabel jLabel293;
    private javax.swing.JLabel jLabel298;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel300;
    private javax.swing.JLabel jLabel306;
    private javax.swing.JLabel jLabel307;
    private javax.swing.JLabel jLabel308;
    private javax.swing.JLabel jLabel309;
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
    private javax.swing.JLabel jLabel320;
    private javax.swing.JLabel jLabel321;
    private javax.swing.JLabel jLabel322;
    private javax.swing.JLabel jLabel323;
    private javax.swing.JLabel jLabel324;
    private javax.swing.JLabel jLabel325;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel100;
    private javax.swing.JPanel jPanel101;
    private javax.swing.JPanel jPanel102;
    private javax.swing.JPanel jPanel103;
    private javax.swing.JPanel jPanel104;
    private javax.swing.JPanel jPanel107;
    private javax.swing.JPanel jPanel108;
    private javax.swing.JPanel jPanel109;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel110;
    private javax.swing.JPanel jPanel111;
    private javax.swing.JPanel jPanel112;
    private javax.swing.JPanel jPanel113;
    private javax.swing.JPanel jPanel114;
    private javax.swing.JPanel jPanel115;
    private javax.swing.JPanel jPanel116;
    private javax.swing.JPanel jPanel117;
    private javax.swing.JPanel jPanel118;
    private javax.swing.JPanel jPanel119;
    private javax.swing.JPanel jPanel120;
    private javax.swing.JPanel jPanel121;
    private javax.swing.JPanel jPanel122;
    private javax.swing.JPanel jPanel123;
    private javax.swing.JPanel jPanel124;
    private javax.swing.JPanel jPanel125;
    private javax.swing.JPanel jPanel126;
    private javax.swing.JPanel jPanel127;
    private javax.swing.JPanel jPanel128;
    private javax.swing.JPanel jPanel129;
    private javax.swing.JPanel jPanel130;
    private javax.swing.JPanel jPanel135;
    private javax.swing.JPanel jPanel136;
    private javax.swing.JPanel jPanel137;
    private javax.swing.JPanel jPanel138;
    private javax.swing.JPanel jPanel139;
    private javax.swing.JPanel jPanel140;
    private javax.swing.JPanel jPanel141;
    private javax.swing.JPanel jPanel142;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel78;
    private javax.swing.JPanel jPanel79;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel80;
    private javax.swing.JPanel jPanel83;
    private javax.swing.JPanel jPanel84;
    private javax.swing.JPanel jPanel85;
    private javax.swing.JPanel jPanel86;
    private javax.swing.JPanel jPanel87;
    private javax.swing.JPanel jPanel88;
    private javax.swing.JPanel jPanel89;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel90;
    private javax.swing.JPanel jPanel91;
    private javax.swing.JPanel jPanel92;
    private javax.swing.JPanel jPanel93;
    private javax.swing.JPanel jPanel94;
    private javax.swing.JPanel jPanel95;
    private javax.swing.JPanel jPanel96;
    private javax.swing.JPanel jPanel97;
    private javax.swing.JPanel jPanel98;
    private javax.swing.JPanel jPanel99;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JScrollPane jpnlDia2;
    private javax.swing.JScrollPane jpnlDia3;
    private javax.swing.JScrollPane jpnlDia4;
    private javax.swing.JScrollPane jpnlDia5;
    private javax.swing.JScrollPane jpnlTableEspecialidades;
    private javax.swing.JScrollPane jpnlTableNiveles;
    private javax.swing.JScrollPane jpnlTableSecciones;
    private javax.swing.JScrollPane jpnlTableUsuarios;
    private javax.swing.JLabel lblAErrorContra;
    private javax.swing.JLabel lblAErrorContraN;
    private javax.swing.JLabel lblAErrorContraNC;
    private javax.swing.JLabel lblAErrorEmail;
    private javax.swing.JLabel lblAErrorNombre;
    private javax.swing.JLabel lblActividades;
    private javax.swing.JLabel lblAjustes;
    private javax.swing.JLabel lblAjustesBackup;
    private javax.swing.JLabel lblAnaliticas;
    private javax.swing.JLabel lblDashboard;
    private javax.swing.JLabel lblEditarUsuario1;
    private javax.swing.JLabel lblEditarUsuario10;
    private javax.swing.JLabel lblEditarUsuario11;
    private javax.swing.JLabel lblEditarUsuario16;
    private javax.swing.JLabel lblEditarUsuario17;
    private javax.swing.JLabel lblEditarUsuario18;
    private javax.swing.JLabel lblEditarUsuario19;
    private javax.swing.JLabel lblEditarUsuario2;
    private javax.swing.JLabel lblEditarUsuario20;
    private javax.swing.JLabel lblEditarUsuario21;
    private javax.swing.JLabel lblEditarUsuario3;
    private javax.swing.JLabel lblEditarUsuario4;
    private javax.swing.JLabel lblEditarUsuario5;
    private javax.swing.JLabel lblEditarUsuario6;
    private javax.swing.JLabel lblEditarUsuario7;
    private javax.swing.JLabel lblEditarUsuario8;
    private javax.swing.JLabel lblEditarUsuario9;
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
    private javax.swing.JPanel pnlNivel;
    private javax.swing.JPanel pnlProyectos;
    private javax.swing.JPanel pnlSeccion;
    private javax.swing.JPanel pnlUbicaciones;
    private javax.swing.JPanel pnlUsuarios;
    private javax.swing.JPasswordField txtAjustesContraA;
    private javax.swing.JPasswordField txtAjustesContraN;
    private javax.swing.JPasswordField txtAjustesContraNC;
    private javax.swing.JTextField txtAjustesEmail;
    private javax.swing.JTextField txtAjustesNombre;
    private javax.swing.JTextField txtBusquedaFiltro;
    private javax.swing.JPasswordField txtClaveUsuarioModal;
    private javax.swing.JTextField txtEmailUsuarioModal;
    private javax.swing.JTextField txtNombreUsuarioModal;
    private javax.swing.JTextField txtNombreUsuarioModal1;
    private javax.swing.JTextField txtNombreUsuarioModal2;
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
