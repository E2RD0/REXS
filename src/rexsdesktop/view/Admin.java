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
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
/**
 *
 * @author user
 */
public class Admin extends javax.swing.JFrame {

    /**
     * Creates new form Admin
     */
    public Admin() {
        UIManager.put("ScrollBar.width", 8);
        UIManager.put("ScrollBar.thumb", new ColorUIResource(new Color(189,189,189)));
        UIManager.put("ScrollBar.thumbDarkShadow", new ColorUIResource(new Color(189,189,189)));
        UIManager.put("ScrollBar.thumbShadow", new ColorUIResource(new Color(189,189,189)));
        UIManager.put("ScrollBar.thumbHighlight", new ColorUIResource(new Color(189,189,189)));
        UIManager.put("ScrollBarUI", "rexsdesktop.view.MyScrollbarUI");
        initComponents();
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
        pnlAjustes.getVerticalScrollBar().setPreferredSize(new Dimension(10,Integer.MAX_VALUE));
        pnlAjustes.getVerticalScrollBar().setUnitIncrement(16);
        jpnlTableUsuarios.getVerticalScrollBar().setUnitIncrement(10);
        jpnlDia1.getVerticalScrollBar().setPreferredSize(new Dimension(6,Integer.MAX_VALUE));
        jpnlDia1.getVerticalScrollBar().setUnitIncrement(10);
        jpnlDia2.getVerticalScrollBar().setPreferredSize(new Dimension(6,Integer.MAX_VALUE));
        jpnlDia2.getVerticalScrollBar().setUnitIncrement(10);
        jpnlDia3.getVerticalScrollBar().setPreferredSize(new Dimension(6,Integer.MAX_VALUE));
        jpnlDia3.getVerticalScrollBar().setUnitIncrement(10);
        jpnlDia4.getVerticalScrollBar().setPreferredSize(new Dimension(6,Integer.MAX_VALUE));
        jpnlDia4.getVerticalScrollBar().setUnitIncrement(10);
        jpnlDia5.getVerticalScrollBar().setPreferredSize(new Dimension(6,Integer.MAX_VALUE));
        jpnlDia5.getVerticalScrollBar().setUnitIncrement(10);
        jpnlTableNiveles.getVerticalScrollBar().setUnitIncrement(10);
        jpnlTableEspecialidades.getVerticalScrollBar().setUnitIncrement(10);
        jpnlTableSecciones.getVerticalScrollBar().setUnitIncrement(10);
    }
    private void resetearModalUsuario(){
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
    ImageIcon iconActividadesActive= new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconCalendarActive.png"));
    ImageIcon iconAjustes = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconSettings.png"));
    ImageIcon iconAjustesActive = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/MenuIconSettingsActive.png"));

    Color colorActive = new Color(46,91,255);
    Color colorNormal = new Color(176, 186, 201);
    Color bgActive = new Color(238,242,255);
    Color bgNormal = new Color(255,255,255);

    private void makeActiveMenuItem(JPanel btn, JPanel activeInd, JLabel lbl, String img){
        CardLayout cl = (CardLayout)(pnlCardLayoutAdmin.getLayout());
        if(pnlActiveInicio.getBackground() == colorActive){
            btnInicio.setBackground(bgNormal);
            pnlActiveInicio.setBackground(bgNormal);
            lblDashboard.setIcon(iconDashboard);
            lblDashboard.setForeground(colorNormal);
        }
        else if(pnlActiveAnaliticas.getBackground() == colorActive){
            btnAnaliticas.setBackground(bgNormal);
            pnlActiveAnaliticas.setBackground(bgNormal);
            lblAnaliticas.setIcon(iconAnaliticas);
            lblAnaliticas.setForeground(colorNormal);
        }
        else if(pnlActiveUsuarios.getBackground() == colorActive){
            btnUsuarios.setBackground(bgNormal);
            pnlActiveUsuarios.setBackground(bgNormal);
            lblUsuarios.setIcon(iconUsuarios);
            lblUsuarios.setForeground(colorNormal);
        }
        else if(pnlActiveProyectos.getBackground() == colorActive){
            btnProyectos.setBackground(bgNormal);
            pnlActiveProyectos.setBackground(bgNormal);
            lblProyectos.setIcon(iconProyectos);
            lblProyectos.setForeground(colorNormal);
        }
        else if(pnlActiveUbicaciones.getBackground() == colorActive){
            btnUbicaciones.setBackground(bgNormal);
            pnlActiveUbicaciones.setBackground(bgNormal);
            lblUbicaciones.setIcon(iconUbicaciones);
            lblUbicaciones.setForeground(colorNormal);
        }
        else if(pnlActiveActividades.getBackground() == colorActive){
            btnActividades.setBackground(bgNormal);
            pnlActiveActividades.setBackground(bgNormal);
            lblActividades.setIcon(iconActividades);
            lblActividades.setForeground(colorNormal);
        }
        else if(pnlActiveAjustes.getBackground() == colorActive){
            btnAjustes.setBackground(bgNormal);
            pnlActiveAjustes.setBackground(bgNormal);
            lblAjustes.setIcon(iconAjustes);
            lblAjustes.setForeground(colorNormal);
        }
        btn.setBackground(bgActive);
        activeInd.setBackground(colorActive);
        lbl.setForeground(colorActive);
        switch (img){
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
        pnlNuevoUsuario = new javax.swing.JPanel();
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
        menuPanel = new javax.swing.JPanel();
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
        pnlCardLayoutAdmin = new javax.swing.JPanel();
        pnlSecciones = new javax.swing.JPanel();
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
        btnAgregarUsuario5 = new javax.swing.JButton();
        jLabel210 = new javax.swing.JLabel();
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
        jPanel31 = new javax.swing.JPanel();
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
        btnAgregarUsuario2 = new javax.swing.JButton();
        btnAgregarUsuario3 = new javax.swing.JButton();
        pnlActividades = new javax.swing.JPanel();
        pnlInnerActividades = new javax.swing.JPanel();
        jLabel109 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        btnAgregarUsuario1 = new javax.swing.JButton();
        jpnlDia1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        jPanel46 = new javax.swing.JPanel();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jPanel47 = new javax.swing.JPanel();
        jPanel48 = new javax.swing.JPanel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jPanel49 = new javax.swing.JPanel();
        jPanel50 = new javax.swing.JPanel();
        jLabel122 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jPanel51 = new javax.swing.JPanel();
        jPanel52 = new javax.swing.JPanel();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jpnlDia2 = new javax.swing.JScrollPane();
        jPanel61 = new javax.swing.JPanel();
        jPanel62 = new javax.swing.JPanel();
        jPanel63 = new javax.swing.JPanel();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jPanel64 = new javax.swing.JPanel();
        jPanel65 = new javax.swing.JPanel();
        jLabel139 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jLabel141 = new javax.swing.JLabel();
        jPanel66 = new javax.swing.JPanel();
        jPanel67 = new javax.swing.JPanel();
        jLabel142 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        jPanel68 = new javax.swing.JPanel();
        jPanel69 = new javax.swing.JPanel();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        jPanel70 = new javax.swing.JPanel();
        jPanel71 = new javax.swing.JPanel();
        jLabel148 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        jPanel74 = new javax.swing.JPanel();
        jPanel75 = new javax.swing.JPanel();
        jLabel154 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
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
        jPanel81 = new javax.swing.JPanel();
        jPanel82 = new javax.swing.JPanel();
        jLabel163 = new javax.swing.JLabel();
        jLabel164 = new javax.swing.JLabel();
        jLabel165 = new javax.swing.JLabel();
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
        jPanel105 = new javax.swing.JPanel();
        jPanel106 = new javax.swing.JPanel();
        jLabel193 = new javax.swing.JLabel();
        jLabel194 = new javax.swing.JLabel();
        jLabel195 = new javax.swing.JLabel();
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
        pnlAjustes = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        jPasswordField3 = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
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
        jpnlTableUsuarios = new javax.swing.JScrollPane();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblEditarUsuario = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        btnAgregarUsuario = new javax.swing.JButton();
        btnFiltrarLista = new javax.swing.JButton();
        pnlDashboard = new javax.swing.JPanel();
        pnlProyectos = new javax.swing.JPanel();

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

        pnlNuevoUsuario.setBackground(new java.awt.Color(255, 255, 255));

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

        javax.swing.GroupLayout pnlNuevoUsuarioLayout = new javax.swing.GroupLayout(pnlNuevoUsuario);
        pnlNuevoUsuario.setLayout(pnlNuevoUsuarioLayout);
        pnlNuevoUsuarioLayout.setHorizontalGroup(
            pnlNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNuevoUsuarioLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(pnlNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel68)
                    .addComponent(jLabel67)
                    .addGroup(pnlNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(pnlNuevoUsuarioLayout.createSequentialGroup()
                            .addGroup(pnlNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel91)
                                .addComponent(cbxTipoUsuarioModal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel92)
                                .addComponent(cbxEstadoUsuarioModal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(txtClaveUsuarioModal)
                        .addComponent(txtNombreUsuarioModal, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel69, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtEmailUsuarioModal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNuevoUsuarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        pnlNuevoUsuarioLayout.setVerticalGroup(
            pnlNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNuevoUsuarioLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNuevoUsuarioLayout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlNuevoUsuarioLayout.createSequentialGroup()
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
                        .addGroup(pnlNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlNuevoUsuarioLayout.createSequentialGroup()
                                .addComponent(jLabel91)
                                .addGap(1, 1, 1)
                                .addComponent(cbxTipoUsuarioModal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlNuevoUsuarioLayout.createSequentialGroup()
                                .addComponent(jLabel92)
                                .addGap(1, 1, 1)
                                .addComponent(cbxEstadoUsuarioModal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(22, 22, 22)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(1000, 625));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuPanel.setBackground(new java.awt.Color(255, 255, 255));
        menuPanel.setPreferredSize(new java.awt.Dimension(150, 625));
        menuPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        menuPanel.add(btnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 24, 32, 32));

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

        menuPanel.add(btnUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 256, 150, -1));

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

        menuPanel.add(btnAnaliticas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 206, 150, -1));

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

        menuPanel.add(btnProyectos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 306, 150, -1));

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

        menuPanel.add(btnUbicaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 356, 150, -1));

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

        menuPanel.add(btnActividades, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 406, 150, -1));

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

        menuPanel.add(btnAjustes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 456, 150, -1));

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

        menuPanel.add(btnInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 156, 150, -1));

        getContentPane().add(menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 56, -1));

        pnlBackground.setPreferredSize(new java.awt.Dimension(1000, 625));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(944, 55));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/rexslogo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(16, 16, 16))
        );

        pnlCardLayoutAdmin.setBackground(new java.awt.Color(244, 246, 252));
        pnlCardLayoutAdmin.setLayout(new java.awt.CardLayout());

        pnlSecciones.setBackground(new java.awt.Color(244, 246, 252));

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
        lblEditarUsuario16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarUsuario16MouseClicked(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 404, Short.MAX_VALUE)
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
        lblEditarUsuario18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarUsuario18MouseClicked(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 402, Short.MAX_VALUE)
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
        lblEditarUsuario19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarUsuario19MouseClicked(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 402, Short.MAX_VALUE)
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
        lblEditarUsuario17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarUsuario17MouseClicked(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 404, Short.MAX_VALUE)
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
        lblEditarUsuario20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarUsuario20MouseClicked(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 402, Short.MAX_VALUE)
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
        lblEditarUsuario21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarUsuario21MouseClicked(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 404, Short.MAX_VALUE)
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

        btnAgregarUsuario5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Button_Add.png"))); // NOI18N
        btnAgregarUsuario5.setBorder(null);
        btnAgregarUsuario5.setBorderPainted(false);
        btnAgregarUsuario5.setContentAreaFilled(false);
        btnAgregarUsuario5.setPreferredSize(new java.awt.Dimension(52, 52));
        btnAgregarUsuario5.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/ActiveButton_Add.png"))); // NOI18N
        btnAgregarUsuario5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarUsuario5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel113Layout = new javax.swing.GroupLayout(jPanel113);
        jPanel113.setLayout(jPanel113Layout);
        jPanel113Layout.setHorizontalGroup(
            jPanel113Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlTableSecciones, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel113Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregarUsuario5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(btnAgregarUsuario5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jLabel210.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconBack.png"))); // NOI18N

        javax.swing.GroupLayout pnlSeccionesLayout = new javax.swing.GroupLayout(pnlSecciones);
        pnlSecciones.setLayout(pnlSeccionesLayout);
        pnlSeccionesLayout.setHorizontalGroup(
            pnlSeccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSeccionesLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(jLabel210)
                .addGap(0, 0, 0)
                .addComponent(jPanel113, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        pnlSeccionesLayout.setVerticalGroup(
            pnlSeccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSeccionesLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(pnlSeccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel210)
                    .addComponent(jPanel113, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnlCardLayoutAdmin.add(pnlSecciones, "card6");

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
        lblEditarUsuario1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarUsuario1MouseClicked(evt);
            }
        });

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

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel31.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel190.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel190.setForeground(new java.awt.Color(46, 56, 77));
        jLabel190.setText("1º Año de Bachillerato");

        lblEditarUsuario2.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblEditarUsuario2.setForeground(new java.awt.Color(46, 91, 255));
        lblEditarUsuario2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditarUsuario2.setText("EDITAR");
        lblEditarUsuario2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarUsuario2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel190)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addComponent(lblEditarUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
        lblEditarUsuario8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarUsuario8MouseClicked(evt);
            }
        });

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
        lblEditarUsuario9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarUsuario9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel192)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 219, Short.MAX_VALUE)
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
        lblEditarUsuario10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarUsuario10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel202)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 220, Short.MAX_VALUE)
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
        lblEditarUsuario11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarUsuario11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel87Layout = new javax.swing.GroupLayout(jPanel87);
        jPanel87.setLayout(jPanel87Layout);
        jPanel87Layout.setHorizontalGroup(
            jPanel87Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel87Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel203)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 219, Short.MAX_VALUE)
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
            .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel87, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        lblEditarUsuario3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarUsuario3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel97Layout = new javax.swing.GroupLayout(jPanel97);
        jPanel97.setLayout(jPanel97Layout);
        jPanel97Layout.setHorizontalGroup(
            jPanel97Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel97Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel204)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
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
        lblEditarUsuario4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarUsuario4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel98Layout = new javax.swing.GroupLayout(jPanel98);
        jPanel98.setLayout(jPanel98Layout);
        jPanel98Layout.setHorizontalGroup(
            jPanel98Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel98Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel205)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
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
        lblEditarUsuario5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarUsuario5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel103Layout = new javax.swing.GroupLayout(jPanel103);
        jPanel103.setLayout(jPanel103Layout);
        jPanel103Layout.setHorizontalGroup(
            jPanel103Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel103Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel206)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
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
        lblEditarUsuario6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarUsuario6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel104Layout = new javax.swing.GroupLayout(jPanel104);
        jPanel104.setLayout(jPanel104Layout);
        jPanel104Layout.setHorizontalGroup(
            jPanel104Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel104Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel207)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
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
        lblEditarUsuario7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarUsuario7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel111Layout = new javax.swing.GroupLayout(jPanel111);
        jPanel111.setLayout(jPanel111Layout);
        jPanel111Layout.setHorizontalGroup(
            jPanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel111Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel208)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 207, Short.MAX_VALUE)
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

        btnAgregarUsuario2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Button_Add.png"))); // NOI18N
        btnAgregarUsuario2.setBorder(null);
        btnAgregarUsuario2.setBorderPainted(false);
        btnAgregarUsuario2.setContentAreaFilled(false);
        btnAgregarUsuario2.setPreferredSize(new java.awt.Dimension(52, 52));
        btnAgregarUsuario2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/ActiveButton_Add.png"))); // NOI18N
        btnAgregarUsuario2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarUsuario2ActionPerformed(evt);
            }
        });

        btnAgregarUsuario3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Button_Add.png"))); // NOI18N
        btnAgregarUsuario3.setBorder(null);
        btnAgregarUsuario3.setBorderPainted(false);
        btnAgregarUsuario3.setContentAreaFilled(false);
        btnAgregarUsuario3.setPreferredSize(new java.awt.Dimension(52, 52));
        btnAgregarUsuario3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/ActiveButton_Add.png"))); // NOI18N
        btnAgregarUsuario3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarUsuario3ActionPerformed(evt);
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
                .addComponent(btnAgregarUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregarUsuario3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(btnAgregarUsuario3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAgregarUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pnlCardLayoutAdmin.add(pnlUbicaciones, "card5");

        pnlActividades.setBackground(new java.awt.Color(244, 246, 252));
        pnlActividades.setPreferredSize(new java.awt.Dimension(944, 570));

        pnlInnerActividades.setBackground(new java.awt.Color(244, 246, 252));

        jLabel109.setFont(new java.awt.Font("Rubik Light", 0, 22)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(46, 56, 77));
        jLabel109.setText("Actividades");

        jPanel26.setBackground(new java.awt.Color(244, 246, 252));
        jPanel26.setPreferredSize(new java.awt.Dimension(600, 303));

        btnAgregarUsuario1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Button_Add.png"))); // NOI18N
        btnAgregarUsuario1.setBorder(null);
        btnAgregarUsuario1.setBorderPainted(false);
        btnAgregarUsuario1.setContentAreaFilled(false);
        btnAgregarUsuario1.setPreferredSize(new java.awt.Dimension(52, 52));
        btnAgregarUsuario1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/ActiveButton_Add.png"))); // NOI18N
        btnAgregarUsuario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarUsuario1ActionPerformed(evt);
            }
        });

        jpnlDia1.setBackground(new java.awt.Color(244, 246, 252));
        jpnlDia1.setBorder(null);
        jpnlDia1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jpnlDia1.setPreferredSize(new java.awt.Dimension(158, 325));

        jPanel1.setBackground(new java.awt.Color(244, 246, 252));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));
        jPanel4.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel24.setBackground(new java.awt.Color(46, 91, 255));
        jPanel24.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel94.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(135, 152, 173));
        jLabel94.setText("8:00 AM - 9:00 AM");

        jLabel95.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(46, 56, 77));
        jLabel95.setText("Titulo de actividad prueba");

        jLabel96.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditBlue.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel95)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel94)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel96)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel95)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel94)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel96)
                        .addContainerGap())))
        );

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));
        jPanel33.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel34.setBackground(new java.awt.Color(46, 91, 255));
        jPanel34.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel100.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(135, 152, 173));
        jLabel100.setText("8:00 AM - 9:00 AM");

        jLabel101.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(46, 56, 77));
        jLabel101.setText("Titulo de actividad prueba");

        jLabel102.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditBlue.png"))); // NOI18N

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jLabel101)
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jLabel100)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel102)))
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel101)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel100)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel102)
                        .addContainerGap())))
        );

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));
        jPanel37.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));
        jPanel37.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel38.setBackground(new java.awt.Color(46, 91, 255));
        jPanel38.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel103.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(135, 152, 173));
        jLabel103.setText("8:00 AM - 9:00 AM");

        jLabel104.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(46, 56, 77));
        jLabel104.setText("Titulo de actividad prueba");

        jLabel105.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditBlue.png"))); // NOI18N

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(jLabel104)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(jLabel103)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel105)))
                .addContainerGap())
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel104)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel103)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel105)
                        .addContainerGap())))
        );

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));
        jPanel39.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));
        jPanel39.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel40.setBackground(new java.awt.Color(46, 91, 255));
        jPanel40.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel106.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(135, 152, 173));
        jLabel106.setText("8:00 AM - 9:00 AM");

        jLabel107.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(46, 56, 77));
        jLabel107.setText("Titulo de actividad prueba");

        jLabel108.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditBlue.png"))); // NOI18N

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(jLabel107)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(jLabel106)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel108)))
                .addContainerGap())
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel107)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel106)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel108)
                        .addContainerGap())))
        );

        jPanel41.setBackground(new java.awt.Color(255, 255, 255));
        jPanel41.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));
        jPanel41.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel42.setBackground(new java.awt.Color(46, 91, 255));
        jPanel42.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel110.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(135, 152, 173));
        jLabel110.setText("8:00 AM - 9:00 AM");

        jLabel111.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(46, 56, 77));
        jLabel111.setText("Titulo de actividad prueba");

        jLabel112.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditBlue.png"))); // NOI18N

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addComponent(jLabel111)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addComponent(jLabel110)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel112)))
                .addContainerGap())
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel111)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel110)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel112)
                        .addContainerGap())))
        );

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));
        jPanel43.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));
        jPanel43.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel44.setBackground(new java.awt.Color(46, 91, 255));
        jPanel44.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel113.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(135, 152, 173));
        jLabel113.setText("8:00 AM - 9:00 AM");

        jLabel114.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(46, 56, 77));
        jLabel114.setText("Titulo de actividad prueba");

        jLabel115.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditBlue.png"))); // NOI18N

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(jLabel114)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(jLabel113)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel115)))
                .addContainerGap())
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel114)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel113)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel115)
                        .addContainerGap())))
        );

        jPanel45.setBackground(new java.awt.Color(255, 255, 255));
        jPanel45.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));
        jPanel45.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel46.setBackground(new java.awt.Color(46, 91, 255));
        jPanel46.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel116.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel116.setForeground(new java.awt.Color(135, 152, 173));
        jLabel116.setText("8:00 AM - 9:00 AM");

        jLabel117.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(46, 56, 77));
        jLabel117.setText("Titulo de actividad prueba");

        jLabel118.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditBlue.png"))); // NOI18N

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addComponent(jLabel117)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addComponent(jLabel116)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel118)))
                .addContainerGap())
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel117)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel116)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel45Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel118)
                        .addContainerGap())))
        );

        jPanel47.setBackground(new java.awt.Color(255, 255, 255));
        jPanel47.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));
        jPanel47.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel48.setBackground(new java.awt.Color(46, 91, 255));
        jPanel48.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel119.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel119.setForeground(new java.awt.Color(135, 152, 173));
        jLabel119.setText("8:00 AM - 9:00 AM");

        jLabel120.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel120.setForeground(new java.awt.Color(46, 56, 77));
        jLabel120.setText("Titulo de actividad prueba");

        jLabel121.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditBlue.png"))); // NOI18N

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addComponent(jLabel120)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addComponent(jLabel119)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel121)))
                .addContainerGap())
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel120)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel119)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel121)
                        .addContainerGap())))
        );

        jPanel49.setBackground(new java.awt.Color(255, 255, 255));
        jPanel49.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));
        jPanel49.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel50.setBackground(new java.awt.Color(46, 91, 255));
        jPanel50.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel122.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel122.setForeground(new java.awt.Color(135, 152, 173));
        jLabel122.setText("8:00 AM - 9:00 AM");

        jLabel123.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel123.setForeground(new java.awt.Color(46, 56, 77));
        jLabel123.setText("Titulo de actividad prueba");

        jLabel124.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditBlue.png"))); // NOI18N

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addComponent(jLabel123)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addComponent(jLabel122)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel124)))
                .addContainerGap())
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel50, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel123)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel122)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel49Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel124)
                        .addContainerGap())))
        );

        jPanel51.setBackground(new java.awt.Color(255, 255, 255));
        jPanel51.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));
        jPanel51.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel52.setBackground(new java.awt.Color(46, 91, 255));
        jPanel52.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel125.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel125.setForeground(new java.awt.Color(135, 152, 173));
        jLabel125.setText("8:00 AM - 9:00 AM");

        jLabel126.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel126.setForeground(new java.awt.Color(46, 56, 77));
        jLabel126.setText("Titulo de actividad prueba");

        jLabel127.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditBlue.png"))); // NOI18N

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addComponent(jLabel126)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addComponent(jLabel125)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel127)))
                .addContainerGap())
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel52, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel126)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel125)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel51Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel127)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jpnlDia1.setViewportView(jPanel1);

        jpnlDia2.setBackground(new java.awt.Color(244, 246, 252));
        jpnlDia2.setBorder(null);
        jpnlDia2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jpnlDia2.setPreferredSize(new java.awt.Dimension(158, 325));

        jPanel61.setBackground(new java.awt.Color(244, 246, 252));

        jPanel62.setBackground(new java.awt.Color(255, 255, 255));
        jPanel62.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(140, 84, 255), 1, true));
        jPanel62.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel63.setBackground(new java.awt.Color(140, 84, 255));
        jPanel63.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel63Layout = new javax.swing.GroupLayout(jPanel63);
        jPanel63.setLayout(jPanel63Layout);
        jPanel63Layout.setHorizontalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel63Layout.setVerticalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel136.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel136.setForeground(new java.awt.Color(135, 152, 173));
        jLabel136.setText("8:00 AM - 9:00 AM");

        jLabel137.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel137.setForeground(new java.awt.Color(46, 56, 77));
        jLabel137.setText("Titulo de actividad prueba");

        jLabel138.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditPurple.png"))); // NOI18N

        javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
        jPanel62.setLayout(jPanel62Layout);
        jPanel62Layout.setHorizontalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addComponent(jPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel62Layout.createSequentialGroup()
                        .addComponent(jLabel137)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel62Layout.createSequentialGroup()
                        .addComponent(jLabel136)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel138)))
                .addContainerGap())
        );
        jPanel62Layout.setVerticalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel63, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel137)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel62Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel136)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel62Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel138)
                        .addContainerGap())))
        );

        jPanel64.setBackground(new java.awt.Color(255, 255, 255));
        jPanel64.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(140, 84, 255), 1, true));
        jPanel64.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel65.setBackground(new java.awt.Color(140, 84, 255));
        jPanel65.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel65Layout = new javax.swing.GroupLayout(jPanel65);
        jPanel65.setLayout(jPanel65Layout);
        jPanel65Layout.setHorizontalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel65Layout.setVerticalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel139.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel139.setForeground(new java.awt.Color(135, 152, 173));
        jLabel139.setText("8:00 AM - 9:00 AM");

        jLabel140.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel140.setForeground(new java.awt.Color(46, 56, 77));
        jLabel140.setText("Titulo de actividad prueba");

        jLabel141.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditPurple.png"))); // NOI18N

        javax.swing.GroupLayout jPanel64Layout = new javax.swing.GroupLayout(jPanel64);
        jPanel64.setLayout(jPanel64Layout);
        jPanel64Layout.setHorizontalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel64Layout.createSequentialGroup()
                .addComponent(jPanel65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel64Layout.createSequentialGroup()
                        .addComponent(jLabel140)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel64Layout.createSequentialGroup()
                        .addComponent(jLabel139)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel141)))
                .addContainerGap())
        );
        jPanel64Layout.setVerticalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel65, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel64Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel140)
                .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel64Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel139)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel64Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel141)
                        .addContainerGap())))
        );

        jPanel66.setBackground(new java.awt.Color(255, 255, 255));
        jPanel66.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(140, 84, 255), 1, true));
        jPanel66.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel67.setBackground(new java.awt.Color(140, 84, 255));
        jPanel67.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel67Layout = new javax.swing.GroupLayout(jPanel67);
        jPanel67.setLayout(jPanel67Layout);
        jPanel67Layout.setHorizontalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel67Layout.setVerticalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel142.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel142.setForeground(new java.awt.Color(135, 152, 173));
        jLabel142.setText("8:00 AM - 9:00 AM");

        jLabel143.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel143.setForeground(new java.awt.Color(46, 56, 77));
        jLabel143.setText("Titulo de actividad prueba");

        jLabel144.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditPurple.png"))); // NOI18N

        javax.swing.GroupLayout jPanel66Layout = new javax.swing.GroupLayout(jPanel66);
        jPanel66.setLayout(jPanel66Layout);
        jPanel66Layout.setHorizontalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel66Layout.createSequentialGroup()
                .addComponent(jPanel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel66Layout.createSequentialGroup()
                        .addComponent(jLabel143)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel66Layout.createSequentialGroup()
                        .addComponent(jLabel142)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel144)))
                .addContainerGap())
        );
        jPanel66Layout.setVerticalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel67, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel66Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel143)
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel66Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel142)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel66Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel144)
                        .addContainerGap())))
        );

        jPanel68.setBackground(new java.awt.Color(255, 255, 255));
        jPanel68.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(140, 84, 255), 1, true));
        jPanel68.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel69.setBackground(new java.awt.Color(140, 84, 255));
        jPanel69.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel69Layout = new javax.swing.GroupLayout(jPanel69);
        jPanel69.setLayout(jPanel69Layout);
        jPanel69Layout.setHorizontalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel69Layout.setVerticalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel145.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel145.setForeground(new java.awt.Color(135, 152, 173));
        jLabel145.setText("8:00 AM - 9:00 AM");

        jLabel146.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel146.setForeground(new java.awt.Color(46, 56, 77));
        jLabel146.setText("Titulo de actividad prueba");

        jLabel147.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditPurple.png"))); // NOI18N

        javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
        jPanel68.setLayout(jPanel68Layout);
        jPanel68Layout.setHorizontalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel68Layout.createSequentialGroup()
                .addComponent(jPanel69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel68Layout.createSequentialGroup()
                        .addComponent(jLabel146)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel68Layout.createSequentialGroup()
                        .addComponent(jLabel145)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel147)))
                .addContainerGap())
        );
        jPanel68Layout.setVerticalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel69, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel68Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel146)
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel68Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel145)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel68Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel147)
                        .addContainerGap())))
        );

        jPanel70.setBackground(new java.awt.Color(255, 255, 255));
        jPanel70.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(140, 84, 255), 1, true));
        jPanel70.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel71.setBackground(new java.awt.Color(140, 84, 255));
        jPanel71.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel71Layout = new javax.swing.GroupLayout(jPanel71);
        jPanel71.setLayout(jPanel71Layout);
        jPanel71Layout.setHorizontalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel71Layout.setVerticalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel148.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel148.setForeground(new java.awt.Color(135, 152, 173));
        jLabel148.setText("8:00 AM - 9:00 AM");

        jLabel149.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel149.setForeground(new java.awt.Color(46, 56, 77));
        jLabel149.setText("Titulo de actividad prueba");

        jLabel150.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditPurple.png"))); // NOI18N

        javax.swing.GroupLayout jPanel70Layout = new javax.swing.GroupLayout(jPanel70);
        jPanel70.setLayout(jPanel70Layout);
        jPanel70Layout.setHorizontalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel70Layout.createSequentialGroup()
                .addComponent(jPanel71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel70Layout.createSequentialGroup()
                        .addComponent(jLabel149)
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addGroup(jPanel70Layout.createSequentialGroup()
                        .addComponent(jLabel148)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel150)))
                .addContainerGap())
        );
        jPanel70Layout.setVerticalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel71, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel70Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel149)
                .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel70Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel148)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel70Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel150)
                        .addContainerGap())))
        );

        jPanel74.setBackground(new java.awt.Color(255, 255, 255));
        jPanel74.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(140, 84, 255), 1, true));
        jPanel74.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel75.setBackground(new java.awt.Color(140, 84, 255));
        jPanel75.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel75Layout = new javax.swing.GroupLayout(jPanel75);
        jPanel75.setLayout(jPanel75Layout);
        jPanel75Layout.setHorizontalGroup(
            jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel75Layout.setVerticalGroup(
            jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel154.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel154.setForeground(new java.awt.Color(135, 152, 173));
        jLabel154.setText("8:00 AM - 9:00 AM");

        jLabel155.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel155.setForeground(new java.awt.Color(46, 56, 77));
        jLabel155.setText("Titulo de actividad prueba");

        jLabel156.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditPurple.png"))); // NOI18N

        javax.swing.GroupLayout jPanel74Layout = new javax.swing.GroupLayout(jPanel74);
        jPanel74.setLayout(jPanel74Layout);
        jPanel74Layout.setHorizontalGroup(
            jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel74Layout.createSequentialGroup()
                .addComponent(jPanel75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel74Layout.createSequentialGroup()
                        .addComponent(jLabel155)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel74Layout.createSequentialGroup()
                        .addComponent(jLabel154)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel156)))
                .addContainerGap())
        );
        jPanel74Layout.setVerticalGroup(
            jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel75, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel74Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel155)
                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel74Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel154)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel74Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel156)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel66, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel64, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel68, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel62, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel74, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addGroup(jPanel73Layout.createSequentialGroup()
                        .addComponent(jLabel151)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel153)))
                .addContainerGap())
        );
        jPanel73Layout.setVerticalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel76, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel73Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel152)
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel73Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel151)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel73Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
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
            .addComponent(jPanel78, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel77Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel158)
                .addGroup(jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel77Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel157)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel77Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel159)
                        .addContainerGap())))
        );

        jPanel81.setBackground(new java.awt.Color(255, 255, 255));
        jPanel81.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 193, 212), 1, true));
        jPanel81.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel82.setBackground(new java.awt.Color(0, 193, 212));
        jPanel82.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel82Layout = new javax.swing.GroupLayout(jPanel82);
        jPanel82.setLayout(jPanel82Layout);
        jPanel82Layout.setHorizontalGroup(
            jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel82Layout.setVerticalGroup(
            jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel163.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel163.setForeground(new java.awt.Color(135, 152, 173));
        jLabel163.setText("8:00 AM - 9:00 AM");

        jLabel164.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel164.setForeground(new java.awt.Color(46, 56, 77));
        jLabel164.setText("Titulo de actividad prueba");

        jLabel165.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditCyan.png"))); // NOI18N

        javax.swing.GroupLayout jPanel81Layout = new javax.swing.GroupLayout(jPanel81);
        jPanel81.setLayout(jPanel81Layout);
        jPanel81Layout.setHorizontalGroup(
            jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel81Layout.createSequentialGroup()
                .addComponent(jPanel82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel81Layout.createSequentialGroup()
                        .addComponent(jLabel164)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel81Layout.createSequentialGroup()
                        .addComponent(jLabel163)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel165)))
                .addContainerGap())
        );
        jPanel81Layout.setVerticalGroup(
            jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel82, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel81Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel164)
                .addGroup(jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel81Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel163)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel81Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel165)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel72Layout = new javax.swing.GroupLayout(jPanel72);
        jPanel72.setLayout(jPanel72Layout);
        jPanel72Layout.setHorizontalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel72Layout.createSequentialGroup()
                .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel73, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel77, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel81, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel72Layout.setVerticalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel72Layout.createSequentialGroup()
                .addComponent(jPanel73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
            .addComponent(jPanel84, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel83Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel167)
                .addGroup(jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel83Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel166)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel83Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel85Layout.createSequentialGroup()
                        .addComponent(jLabel169)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel171)))
                .addContainerGap())
        );
        jPanel85Layout.setVerticalGroup(
            jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel86, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel85Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel170)
                .addGroup(jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel85Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel169)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel85Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
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
            .addComponent(jPanel100, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel99Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel185)
                .addGroup(jPanel99Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel99Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel184)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel99Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
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
            .addComponent(jPanel102, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel101Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel188)
                .addGroup(jPanel101Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel101Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel187)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel101Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel189)
                        .addContainerGap())))
        );

        jPanel105.setBackground(new java.awt.Color(255, 255, 255));
        jPanel105.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(247, 193, 55), 1, true));
        jPanel105.setPreferredSize(new java.awt.Dimension(150, 72));

        jPanel106.setBackground(new java.awt.Color(247, 193, 55));
        jPanel106.setPreferredSize(new java.awt.Dimension(1, 67));

        javax.swing.GroupLayout jPanel106Layout = new javax.swing.GroupLayout(jPanel106);
        jPanel106.setLayout(jPanel106Layout);
        jPanel106Layout.setHorizontalGroup(
            jPanel106Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel106Layout.setVerticalGroup(
            jPanel106Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel193.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel193.setForeground(new java.awt.Color(135, 152, 173));
        jLabel193.setText("8:00 AM - 9:00 AM");

        jLabel194.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel194.setForeground(new java.awt.Color(46, 56, 77));
        jLabel194.setText("Titulo de actividad prueba");

        jLabel195.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditOrange.png"))); // NOI18N

        javax.swing.GroupLayout jPanel105Layout = new javax.swing.GroupLayout(jPanel105);
        jPanel105.setLayout(jPanel105Layout);
        jPanel105Layout.setHorizontalGroup(
            jPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel105Layout.createSequentialGroup()
                .addComponent(jPanel106, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel105Layout.createSequentialGroup()
                        .addComponent(jLabel194)
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addGroup(jPanel105Layout.createSequentialGroup()
                        .addComponent(jLabel193)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel195)))
                .addContainerGap())
        );
        jPanel105Layout.setVerticalGroup(
            jPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel106, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel105Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel194)
                .addGroup(jPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel105Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel193)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel105Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(jLabel195)
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
            .addComponent(jPanel108, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel107Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel197)
                .addGroup(jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel107Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel196)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel107Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
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
            .addComponent(jPanel110, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel109Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel200)
                .addGroup(jPanel109Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel109Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel199)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel109Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
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
                    .addComponent(jPanel107, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel105, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel105, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addGroup(jPanel89Layout.createSequentialGroup()
                        .addComponent(jLabel172)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel174)))
                .addContainerGap())
        );
        jPanel89Layout.setVerticalGroup(
            jPanel89Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel90, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel89Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel173)
                .addGroup(jPanel89Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel89Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel172)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel89Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
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
            .addComponent(jPanel92, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel91Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel176)
                .addGroup(jPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel91Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel175)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel91Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
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
            .addComponent(jPanel94, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel93Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel179)
                .addGroup(jPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel93Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel178)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel93Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
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
            .addComponent(jPanel96, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addGroup(jPanel95Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel182)
                .addGroup(jPanel95Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel95Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel181)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel95Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
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
        jLabel99.setText("10");
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
        jLabel129.setText("6");
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

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregarUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnlDia1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
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
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpnlDia5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnlDia1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnlDia2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnlDia3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnlDia4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(btnAgregarUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlInnerActividadesLayout = new javax.swing.GroupLayout(pnlInnerActividades);
        pnlInnerActividades.setLayout(pnlInnerActividadesLayout);
        pnlInnerActividadesLayout.setHorizontalGroup(
            pnlInnerActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInnerActividadesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlInnerActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 867, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel109))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlInnerActividadesLayout.setVerticalGroup(
            pnlInnerActividadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInnerActividadesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
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
                .addGap(45, 45, 45)
                .addComponent(pnlInnerActividades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCardLayoutAdmin.add(pnlActividades, "Actividades");

        pnlAjustes.setBorder(null);
        pnlAjustes.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pnlAjustes.setPreferredSize(new java.awt.Dimension(944, 570));

        jPanel2.setBackground(new java.awt.Color(244, 246, 252));
        jPanel2.setPreferredSize(new java.awt.Dimension(944, 825));

        jLabel2.setFont(new java.awt.Font("Rubik Light", 0, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(46, 56, 77));
        jLabel2.setText("Ajustes");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel3.setPreferredSize(new java.awt.Dimension(600, 303));

        jLabel3.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(135, 152, 173));
        jLabel3.setText("DATOS PERSONALES");

        jLabel4.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(46, 56, 77));
        jLabel4.setText("Usa esta página para actualizar tu información de contacto y cambiar tu contraseña.");

        jTextField1.setBackground(new java.awt.Color(249, 250, 255));
        jTextField1.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(46, 56, 77));
        jTextField1.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));

        jLabel5.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(176, 186, 201));
        jLabel5.setText("CORREO ELECTRÓNICO");

        jLabel6.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(176, 186, 201));
        jLabel6.setText("CONTRASEÑA ACTUAL");

        jPasswordField1.setBackground(new java.awt.Color(249, 250, 255));
        jPasswordField1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jPasswordField1.setForeground(new java.awt.Color(46, 56, 77));
        jPasswordField1.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        jPasswordField1.setEchoChar('\u2022');
        jPasswordField1.setPreferredSize(new java.awt.Dimension(188, 28));

        jLabel7.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(176, 186, 201));
        jLabel7.setText("NOMBRE COMPLETO");

        jTextField2.setBackground(new java.awt.Color(249, 250, 255));
        jTextField2.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(46, 56, 77));
        jTextField2.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));

        jPasswordField2.setBackground(new java.awt.Color(249, 250, 255));
        jPasswordField2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jPasswordField2.setForeground(new java.awt.Color(46, 56, 77));
        jPasswordField2.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        jPasswordField2.setEchoChar('\u2022');
        jPasswordField2.setPreferredSize(new java.awt.Dimension(188, 28));

        jLabel8.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(176, 186, 201));
        jLabel8.setText("NUEVA CONTRASEÑA");

        jPasswordField3.setBackground(new java.awt.Color(249, 250, 255));
        jPasswordField3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jPasswordField3.setForeground(new java.awt.Color(46, 56, 77));
        jPasswordField3.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        jPasswordField3.setEchoChar('\u2022');
        jPasswordField3.setPreferredSize(new java.awt.Dimension(188, 28));

        jLabel9.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(176, 186, 201));
        jLabel9.setText("CONFIRMAR CONTRASEÑA");

        jButton1.setBackground(new java.awt.Color(46, 91, 255));
        jButton1.setFont(new java.awt.Font("Rubik Medium", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Guardar Cambios");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setPreferredSize(new java.awt.Dimension(138, 28));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(90, 90, 90))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPasswordField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPasswordField3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING))))
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
                        .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel5)
                        .addGap(1, 1, 1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel7)
                        .addGap(1, 1, 1)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(1, 1, 1)
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(1, 1, 1)
                                .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(28, 28, 28)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
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

        jButton5.setBackground(new java.awt.Color(238, 238, 238));
        jButton5.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jButton5.setForeground(new java.awt.Color(107, 107, 107));
        jButton5.setText("Cambiar Foto");
        jButton5.setBorderPainted(false);

        jButton6.setBackground(new java.awt.Color(238, 238, 238));
        jButton6.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jButton6.setForeground(new java.awt.Color(107, 107, 107));
        jButton6.setText("Eliminar Foto");
        jButton6.setBorderPainted(false);

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(193, 193, 193)));
        jPanel23.setPreferredSize(new java.awt.Dimension(130, 130));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel93.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/profilePicture.png"))); // NOI18N
        jPanel23.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, -1, -1));

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
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pnlAjustes.setViewportView(jPanel2);

        pnlCardLayoutAdmin.add(pnlAjustes, "Ajustes");

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
                .addContainerGap(13, Short.MAX_VALUE))
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

        jpnlTableUsuarios.setBackground(new java.awt.Color(244, 246, 252));
        jpnlTableUsuarios.setBorder(null);
        jpnlTableUsuarios.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel12.setBackground(new java.awt.Color(244, 246, 252));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel13.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel17.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(135, 152, 173));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("#1055");

        jLabel18.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(46, 56, 77));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Eduardo David E...");

        jLabel19.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(46, 56, 77));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("eduardo...@gmail.com");
        jLabel19.setPreferredSize(new java.awt.Dimension(120, 14));

        jLabel20.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(46, 56, 77));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("19/04/2019");

        jLabel21.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(46, 56, 77));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Administrador");

        jLabel22.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(46, 56, 77));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Eliminado");

        lblEditarUsuario.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        lblEditarUsuario.setForeground(new java.awt.Color(46, 91, 255));
        lblEditarUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditarUsuario.setText("EDITAR");
        lblEditarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditarUsuarioMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblEditarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblEditarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel14.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel28.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(135, 152, 173));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("#1055");

        jLabel29.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(46, 56, 77));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Eduardo David E...");

        jLabel30.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(46, 56, 77));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("eduardo...@gmail.com");
        jLabel30.setPreferredSize(new java.awt.Dimension(120, 14));

        jLabel31.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(46, 56, 77));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("19/04/2019");

        jLabel32.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(46, 56, 77));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Administrador");

        jLabel33.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(46, 56, 77));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Eliminado");

        jLabel34.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(46, 91, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("EDITAR");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel15.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel35.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(135, 152, 173));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("#1055");

        jLabel36.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(46, 56, 77));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Eduardo David E...");

        jLabel37.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(46, 56, 77));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("eduardo...@gmail.com");
        jLabel37.setPreferredSize(new java.awt.Dimension(120, 14));

        jLabel38.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(46, 56, 77));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("19/04/2019");

        jLabel39.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(46, 56, 77));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Administrador");

        jLabel40.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(46, 56, 77));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Eliminado");

        jLabel41.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(46, 91, 255));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("EDITAR");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel17.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel49.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(135, 152, 173));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("#1055");

        jLabel50.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(46, 56, 77));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("Eduardo David E...");

        jLabel51.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(46, 56, 77));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("eduardo...@gmail.com");
        jLabel51.setPreferredSize(new java.awt.Dimension(120, 14));

        jLabel52.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(46, 56, 77));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("19/04/2019");

        jLabel53.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(46, 56, 77));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("Administrador");

        jLabel54.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(46, 56, 77));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("Eliminado");

        jLabel55.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(46, 91, 255));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("EDITAR");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel18.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel56.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(135, 152, 173));
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("#1055");

        jLabel57.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(46, 56, 77));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("Eduardo David E...");

        jLabel58.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(46, 56, 77));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("eduardo...@gmail.com");
        jLabel58.setPreferredSize(new java.awt.Dimension(120, 14));

        jLabel59.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(46, 56, 77));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("19/04/2019");

        jLabel60.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(46, 56, 77));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("Administrador");

        jLabel61.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(46, 56, 77));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setText("Eliminado");

        jLabel62.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(46, 91, 255));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setText("EDITAR");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel20.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel70.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(135, 152, 173));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setText("#1055");

        jLabel71.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(46, 56, 77));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel71.setText("Eduardo David E...");

        jLabel72.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(46, 56, 77));
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel72.setText("eduardo...@gmail.com");
        jLabel72.setPreferredSize(new java.awt.Dimension(120, 14));

        jLabel73.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(46, 56, 77));
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel73.setText("19/04/2019");

        jLabel74.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(46, 56, 77));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("Administrador");

        jLabel75.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(46, 56, 77));
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel75.setText("Eliminado");

        jLabel76.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(46, 91, 255));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel76.setText("EDITAR");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel21.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel77.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(135, 152, 173));
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel77.setText("#1055");

        jLabel78.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(46, 56, 77));
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel78.setText("Eduardo David E...");

        jLabel79.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(46, 56, 77));
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel79.setText("eduardo...@gmail.com");
        jLabel79.setPreferredSize(new java.awt.Dimension(120, 14));

        jLabel80.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(46, 56, 77));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setText("19/04/2019");

        jLabel81.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(46, 56, 77));
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel81.setText("Administrador");

        jLabel82.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(46, 56, 77));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("Eliminado");

        jLabel83.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(46, 91, 255));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setText("EDITAR");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(148, 171, 255), 1, true));
        jPanel22.setPreferredSize(new java.awt.Dimension(576, 52));

        jLabel84.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(135, 152, 173));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("#1055");

        jLabel85.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(46, 56, 77));
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setText("Eduardo David E...");

        jLabel86.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(46, 56, 77));
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel86.setText("eduardo...@gmail.com");
        jLabel86.setPreferredSize(new java.awt.Dimension(120, 14));

        jLabel87.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(46, 56, 77));
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setText("19/04/2019");

        jLabel88.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(46, 56, 77));
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel88.setText("Administrador");

        jLabel89.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(46, 56, 77));
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel89.setText("Eliminado");

        jLabel90.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(46, 91, 255));
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel90.setText("EDITAR");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 15, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        jpnlTableUsuarios.setViewportView(jPanel12);

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 13, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jpnlTableUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)))
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
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jpnlTableUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFiltrarLista, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
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
                .addContainerGap(70, Short.MAX_VALUE))
        );
        pnlUsuariosLayout.setVerticalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuariosLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel10)
                .addGap(45, 45, 45)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE))
        );

        pnlCardLayoutAdmin.add(pnlUsuarios, "Usuarios");

        javax.swing.GroupLayout pnlDashboardLayout = new javax.swing.GroupLayout(pnlDashboard);
        pnlDashboard.setLayout(pnlDashboardLayout);
        pnlDashboardLayout.setHorizontalGroup(
            pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 944, Short.MAX_VALUE)
        );
        pnlDashboardLayout.setVerticalGroup(
            pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );

        pnlCardLayoutAdmin.add(pnlDashboard, "card7");

        javax.swing.GroupLayout pnlProyectosLayout = new javax.swing.GroupLayout(pnlProyectos);
        pnlProyectos.setLayout(pnlProyectosLayout);
        pnlProyectosLayout.setHorizontalGroup(
            pnlProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 944, Short.MAX_VALUE)
        );
        pnlProyectosLayout.setVerticalGroup(
            pnlProyectosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );

        pnlCardLayoutAdmin.add(pnlProyectos, "card8");

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCardLayoutAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlCardLayoutAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pnlBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        if(menuPanel.getWidth() == 150){
        menuPanel.setSize(56, menuPanel.getHeight());
        }
        else if(menuPanel.getWidth() == 56){
        menuPanel.setSize(150, menuPanel.getHeight());
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
        modalUsuario.getContentPane().add(pnlNuevoUsuario);
        modalUsuario.pack();
        modalUsuario.setLocationRelativeTo(null);
        modalUsuario.setVisible(true);
    }//GEN-LAST:event_btnAgregarUsuarioActionPerformed

    private void btnAceptarModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarModalActionPerformed
        modalUsuario.dispose();
        resetearModalUsuario();
    }//GEN-LAST:event_btnAceptarModalActionPerformed

    private void lblEditarUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarUsuarioMouseClicked
        modalUsuario = new JDialog(this, "Nuevo usuario", true);
        modalUsuario.getContentPane().add(pnlNuevoUsuario);
        modalUsuario.pack();
        modalUsuario.setLocationRelativeTo(null);
        modalUsuario.setVisible(true);
    }//GEN-LAST:event_lblEditarUsuarioMouseClicked

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
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProyectosMouseClicked

    private void btnProyectosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProyectosMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProyectosMouseEntered

    private void btnProyectosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProyectosMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProyectosMouseExited

    private void btnUbicacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUbicacionesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUbicacionesMouseClicked

    private void btnUbicacionesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUbicacionesMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUbicacionesMouseEntered

    private void btnUbicacionesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUbicacionesMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUbicacionesMouseExited

    private void btnActividadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActividadesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActividadesMouseClicked

    private void btnActividadesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActividadesMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActividadesMouseEntered

    private void btnActividadesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActividadesMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActividadesMouseExited

    private void btnAjustesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAjustesMouseClicked
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

    private void btnAgregarUsuario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUsuario1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarUsuario1ActionPerformed

    private void lblEditarUsuario1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarUsuario1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblEditarUsuario1MouseClicked

    private void lblEditarUsuario2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarUsuario2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblEditarUsuario2MouseClicked

    private void lblEditarUsuario8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarUsuario8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblEditarUsuario8MouseClicked

    private void lblEditarUsuario9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarUsuario9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblEditarUsuario9MouseClicked

    private void lblEditarUsuario10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarUsuario10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblEditarUsuario10MouseClicked

    private void lblEditarUsuario11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarUsuario11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblEditarUsuario11MouseClicked

    private void lblEditarUsuario3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarUsuario3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblEditarUsuario3MouseClicked

    private void lblEditarUsuario4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarUsuario4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblEditarUsuario4MouseClicked

    private void lblEditarUsuario5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarUsuario5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblEditarUsuario5MouseClicked

    private void lblEditarUsuario6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarUsuario6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblEditarUsuario6MouseClicked

    private void lblEditarUsuario7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarUsuario7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblEditarUsuario7MouseClicked

    private void btnAgregarUsuario2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUsuario2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarUsuario2ActionPerformed

    private void btnAgregarUsuario3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUsuario3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarUsuario3ActionPerformed

    private void btnAgregarUsuario5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUsuario5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarUsuario5ActionPerformed

    private void lblEditarUsuario16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarUsuario16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblEditarUsuario16MouseClicked

    private void lblEditarUsuario18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarUsuario18MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblEditarUsuario18MouseClicked

    private void lblEditarUsuario19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarUsuario19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblEditarUsuario19MouseClicked

    private void lblEditarUsuario17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarUsuario17MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblEditarUsuario17MouseClicked

    private void lblEditarUsuario20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarUsuario20MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblEditarUsuario20MouseClicked

    private void lblEditarUsuario21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditarUsuario21MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblEditarUsuario21MouseClicked

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
    private javax.swing.JPanel btnActividades;
    private javax.swing.JButton btnAgregarUsuario;
    private javax.swing.JButton btnAgregarUsuario1;
    private javax.swing.JButton btnAgregarUsuario2;
    private javax.swing.JButton btnAgregarUsuario3;
    private javax.swing.JButton btnAgregarUsuario5;
    private javax.swing.JPanel btnAjustes;
    private javax.swing.JPanel btnAnaliticas;
    private javax.swing.JButton btnCancelarModal;
    private javax.swing.JButton btnFiltrarLista;
    private javax.swing.JPanel btnInicio;
    private javax.swing.JButton btnMenu;
    private javax.swing.JPanel btnProyectos;
    private javax.swing.JPanel btnUbicaciones;
    private javax.swing.JPanel btnUsuarios;
    private javax.swing.JComboBox<String> cbxEstadoUsuarioModal;
    private javax.swing.JComboBox<String> cbxTipoUsuarioModal;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel190;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel192;
    private javax.swing.JLabel jLabel193;
    private javax.swing.JLabel jLabel194;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel196;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel199;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel211;
    private javax.swing.JLabel jLabel212;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JLabel jLabel216;
    private javax.swing.JLabel jLabel217;
    private javax.swing.JLabel jLabel218;
    private javax.swing.JLabel jLabel219;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel220;
    private javax.swing.JLabel jLabel221;
    private javax.swing.JLabel jLabel222;
    private javax.swing.JLabel jLabel223;
    private javax.swing.JLabel jLabel224;
    private javax.swing.JLabel jLabel225;
    private javax.swing.JLabel jLabel226;
    private javax.swing.JLabel jLabel227;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
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
    private javax.swing.JPanel jPanel105;
    private javax.swing.JPanel jPanel106;
    private javax.swing.JPanel jPanel107;
    private javax.swing.JPanel jPanel108;
    private javax.swing.JPanel jPanel109;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel110;
    private javax.swing.JPanel jPanel111;
    private javax.swing.JPanel jPanel113;
    private javax.swing.JPanel jPanel114;
    private javax.swing.JPanel jPanel119;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel120;
    private javax.swing.JPanel jPanel121;
    private javax.swing.JPanel jPanel122;
    private javax.swing.JPanel jPanel123;
    private javax.swing.JPanel jPanel124;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
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
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel74;
    private javax.swing.JPanel jPanel75;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel78;
    private javax.swing.JPanel jPanel79;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel80;
    private javax.swing.JPanel jPanel81;
    private javax.swing.JPanel jPanel82;
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
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JScrollPane jpnlDia1;
    private javax.swing.JScrollPane jpnlDia2;
    private javax.swing.JScrollPane jpnlDia3;
    private javax.swing.JScrollPane jpnlDia4;
    private javax.swing.JScrollPane jpnlDia5;
    private javax.swing.JScrollPane jpnlTableEspecialidades;
    private javax.swing.JScrollPane jpnlTableNiveles;
    private javax.swing.JScrollPane jpnlTableSecciones;
    private javax.swing.JScrollPane jpnlTableUsuarios;
    private javax.swing.JLabel lblActividades;
    private javax.swing.JLabel lblAjustes;
    private javax.swing.JLabel lblAnaliticas;
    private javax.swing.JLabel lblDashboard;
    private javax.swing.JLabel lblEditarUsuario;
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
    private javax.swing.JLabel lblProyectos;
    private javax.swing.JLabel lblUbicaciones;
    private javax.swing.JLabel lblUsuarios;
    private javax.swing.JPanel menuPanel;
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
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlCardLayoutAdmin;
    private javax.swing.JPanel pnlDashboard;
    private javax.swing.JPanel pnlInnerActividades;
    private javax.swing.JPanel pnlNuevoUsuario;
    private javax.swing.JPanel pnlProyectos;
    private javax.swing.JPanel pnlSecciones;
    private javax.swing.JPanel pnlUbicaciones;
    private javax.swing.JPanel pnlUsuarios;
    private javax.swing.JTextField txtBusquedaFiltro;
    private javax.swing.JPasswordField txtClaveUsuarioModal;
    private javax.swing.JTextField txtEmailUsuarioModal;
    private javax.swing.JTextField txtNombreUsuarioModal;
    // End of variables declaration//GEN-END:variables
}
   class RoundedPanel extends JPanel
    {
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
            graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint background
            graphics.setColor(getForeground());
//            graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
//            
        }
    }
