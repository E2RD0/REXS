/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.modal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;

import java.awt.Window;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import rexsdesktop.CurrentUser;
import rexsdesktop.controller.General;
import rexsdesktop.controller.Locations;
import rexsdesktop.controller.Projects;
import static rexsdesktop.controller.Projects.getIdSeccionNivel;
import static rexsdesktop.controller.Projects.getNumIMG;
import static rexsdesktop.controller.Projects.getNumMembers;
import static rexsdesktop.controller.Projects.getProject;
import static rexsdesktop.controller.Projects.jsImagenes;
import static rexsdesktop.controller.Projects.jsIntegrantes;
import static rexsdesktop.controller.Projects.lbIMG;
import static rexsdesktop.controller.Projects.panelInte;
import static rexsdesktop.controller.Projects.pnlImagenes;
import static rexsdesktop.controller.Projects.pnlIntegrantes;
import static rexsdesktop.controller.Projects.txtIntegrantes;
import rexsdesktop.controller.Validation;
import rexsdesktop.controller.Votes;
import static rexsdesktop.modal.ModalNuevoProyecto.label;
import rexsdesktop.model.Db;
import rexsdesktop.view.Admin;
import static rexsdesktop.view.Admin.cdProyectos;
import static rexsdesktop.view.Admin.color;
import static rexsdesktop.view.Admin.jcEspecialidad;
import static rexsdesktop.view.Admin.jcNivel;
import static rexsdesktop.view.Admin.jcSeccion;
import static rexsdesktop.view.Admin.jsProyectos;
import static rexsdesktop.view.Admin.pnlViewProyectos;

/**
 * Clase que contiene el Panel para visualizar y editar un proyecto.
 *
 * @author Lulac
 */
public class ModalViewProyectoPoint extends javax.swing.JPanel {
    
    public String idUbicacion;
    int contador = 0;
    ImageIcon Member = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Member.png"));
    ImageIcon Member2 = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Member2.png"));
    ImageIcon fotoProyecto = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/fotoProyecto.png"));

    /**
     * Creates new form ModalViewProyecto
     */
    public ModalViewProyectoPoint() {
        initComponents();
        jcpNormal.getVerticalScrollBar().setPreferredSize(new Dimension(6, Integer.MAX_VALUE));
        jcpNormal.getVerticalScrollBar().setUnitIncrement(10);
        jcpEdit.getVerticalScrollBar().setPreferredSize(new Dimension(6, Integer.MAX_VALUE));
        jcpEdit.getVerticalScrollBar().setUnitIncrement(10);
        
        criterio1.setMinimum(1);
        criterio2.setMinimum(1);
        criterio3.setMinimum(1);
        criterio1.setMaximum(10);
        criterio2.setMaximum(10);
        criterio3.setMaximum(10);
        
        Db db = new Db();
        db.obtenerNivel();
        for (int i = 0; i < db.SNnivel.size(); i++) {
            cbxNivel.addItem(db.SNnivel.get(i));
        }
        cbxEspecialidad.disable();
        cbxSeccionNivel.disable();
        jLabel14.disable();

    }

    public void cargarProyectos() {
        try {
            General.getEdicion();
            Db db = new Db();
            db.obtenerNivel();
            db.NumProyectos(CurrentUser.edicionExpotecnica);
            if (label != null) {
                pnlViewProyectos.remove(label);
            }
            jcNivel.removeAllItems();
            jcNivel.addItem("Seleccione un nivel");
            cdProyectos.removeAll();
            pnlViewProyectos.repaint();
            pnlViewProyectos.revalidate();
            for (int i = 0; i < db.SNnivel.size(); i++) {
                jcNivel.addItem(db.SNnivel.get(i));
            }
            jcEspecialidad.disable();
            jcSeccion.disable();
            cdProyectos.setLayout(new GridLayout(0, 2, 15, 20));
            Projects cargarPaneles = new Projects();
            cargarPaneles.CrearPanelesProyectos(cdProyectos, CurrentUser.edicionExpotecnica);
            jsProyectos.setBorder(null);
            if (color == 0) {

                jsProyectos.setBackground(new Color(244, 246, 252));
                cdProyectos.setBackground(new Color(244, 246, 252));

            } else {
                jsProyectos.setBackground(new Color(52, 48, 57));
                cdProyectos.setBackground(new Color(52, 48, 57));

            }
            switch (db.getCantidadProyecto()) {
                case 0:
                    jsProyectos.setBounds(0, 70, 0, 0);
                    cdProyectos.setLayout(null);
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void cargarIntegrantes() {
        Db db = new Db();
        pnlViewIntegrantes.remove(txtIntegrantes);
        pnlViewIntegrantes.repaint();
        pnlViewIntegrantes.revalidate();
        txtIntegrantes = new JLabel();
        txtIntegrantes.setBounds(10, 10, 100, 20);
        txtIntegrantes.setText("Integrantes");
        txtIntegrantes.setHorizontalTextPosition(CENTER);
        txtIntegrantes.setFont(new java.awt.Font("Rubik", 0, 12));
        pnlViewIntegrantes.add(txtIntegrantes);
        Projects k = getNumMembers(Integer.parseInt(id.getText()));
        db.ViewIntegrantes(Integer.parseInt(id.getText()));
        if (k.CountInte != 0) {
            int y = 0;
            System.out.println("Contador" + k.CountInte);
            for (int q = 0; q < k.CountInte; q++) {
                if (k.CountInte <= 3) {
                    panelInte = new JPanel();
                    panelInte.setName(db.nombreInte.get(q));
                    panelInte.setLayout(new BorderLayout(0, 0));
                    jsIntegrantes.setSize(new Dimension(105 + y, 112));
                    panelInte.setPreferredSize(new Dimension(90, 90));
                    panelInte.setBackground(Color.WHITE);
                    panelInte.setBorder(null);
                    JLabel imgMember = new JLabel();
                    imgMember.setHorizontalAlignment(CENTER);
                    imgMember.setIcon(Member2);

                    JLabel labelIntegrante = new JLabel("<html>" + db.nombreInte.get(q) + "</html>", SwingConstants.CENTER);
                    labelIntegrante.setBounds(0, 15, 50, 20);

                    panelInte.add(imgMember, BorderLayout.NORTH);
                    panelInte.add(labelIntegrante, BorderLayout.CENTER);
                    pnlIntegrantes.add(panelInte);
                    y = y + 105;
                } else {
                    panelInte = new JPanel();
                    panelInte.setName(db.nombreInte.get(q));
                    panelInte.setLayout(new BorderLayout(0, 0));
                    jsIntegrantes.setSize(new Dimension(380, 112));
                    panelInte.setPreferredSize(new Dimension(90, 90));
                    panelInte.setBackground(Color.WHITE);
                    JLabel imgMember = new JLabel();
                    imgMember.setHorizontalAlignment(CENTER);
                    imgMember.setIcon(Member);

                    JLabel labelIntegrante = new JLabel("<html>" + db.nombreInte.get(q) + "</html>", SwingConstants.CENTER);
                    labelIntegrante.setBounds(0, 15, 50, 20);

                    panelInte.add(imgMember, BorderLayout.NORTH);
                    panelInte.add(labelIntegrante, BorderLayout.CENTER);
                    panelInte.setBorder(null);
                    pnlIntegrantes.add(panelInte);
                }
                panelInte.addMouseListener(new MouseListener() {
                    Frame gr;

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        panelInte = (JPanel) e.getSource();
                        String nameMember = JOptionPane.showInputDialog(ModalViewProyectoPoint.this, "Ingrese correctamente el nombre del estudiante", panelInte.getName());
                        Db db = new Db();
                        if (!nameMember.isEmpty()) {

                            if (!db.IntegranteExiste(nameMember)) {
                                Projects.actualizarInte(nameMember, panelInte.getName(), Integer.parseInt(id.getText()));
                                pnlIntegrantes.removeAll();
                                cargarIntegrantes();
                                pnlIntegrantes.repaint();
                                pnlIntegrantes.revalidate();
                            } else {
                                JOptionPane.showMessageDialog(ModalViewProyectoPoint.this, "El integrante ya existe");
                            }

                        } else {
                            JOptionPane.showMessageDialog(ModalViewProyectoPoint.this, "El campo está vacío.");
                        }

                    }

                    @Override
                    public void mouseExited(MouseEvent e
                    ) {
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }
                }
                );

            }
        }
    }

    public void cargarImagenes() {
        Db db = new Db();

        Projects t = getNumIMG(Integer.parseInt(id.getText()));
        db.getIMGresources(Integer.parseInt(id.getText()));

        if (lbIMG != null && jsImagenes != null) {
            pnlImagenes.remove(lbIMG);
            pnlViewImagenes.remove(jsImagenes);
            pnlViewImagenes.repaint();
            pnlViewImagenes.revalidate();
            pnlImagenes.repaint();
            pnlImagenes.revalidate();
        } else {
            System.out.println("Son nulos");
        }

        pnlViewImagenes.remove(lbIMG);
        pnlViewImagenes.repaint();
        pnlViewImagenes.revalidate();

        if (t.CountIMGresources != 0) {
            try {

                pnlImagenes = new JPanel();
                pnlImagenes.setLayout(new GridLayout(1, 0, 15, 15));
                pnlImagenes.setBackground(Color.white);
                jsImagenes = new JScrollPane(pnlImagenes);
                jsImagenes.setBounds(2, 2, 140, 135);
                jsImagenes.setBorder(null);
                pnlViewImagenes.add(jsImagenes);
                System.out.println("cantidad image : " + t.CountIMGresources);
                int y2 = 0;
                Img.setIcon(new ImageIcon(db.RecImagenes.get(0)));
                for (int j = 0; j < t.CountIMGresources; j++) {
                    if (t.CountIMGresources <= 2) {
                        lbIMG = new JLabel();
                        jsImagenes.setBounds(2, 2, 140 + y2, 135);
                        BufferedImage icon = db.RecImagenes.get(j);
                        if (icon != null) {
                            try {
                                lbIMG.setIcon(new ImageIcon(icon.getScaledInstance(133, 125, Image.SCALE_DEFAULT)));
                            } catch (Exception w) {
                                System.out.println("Al agregar despues de 2 imagenes: " + w.getMessage());
                            }
                        } else {
                            lbIMG.setIcon(fotoProyecto);
                        }
                        lbIMG.setName(String.valueOf(db.RecIdRecurso.get(j)));
                        pnlImagenes.add(lbIMG);
                        y2 = y2 + 155;
                    } else {
                        lbIMG = new JLabel();
                        jsImagenes.setBounds(2, 2, 420, 140);
                        BufferedImage icon = db.RecImagenes.get(j);
                        if (icon != null) {
                            try {
                                lbIMG.setIcon(new ImageIcon(icon.getScaledInstance(133, 125, Image.SCALE_DEFAULT)));
                            } catch (Exception w) {
                                System.out.println("Al agregar despues de 3 imagenes: " + w.getMessage());
                            }
                        } else {
                            lbIMG.setIcon(fotoProyecto);
                        }
                        lbIMG.setName(String.valueOf(db.RecIdRecurso.get(j)));
                        pnlImagenes.add(lbIMG);

                    }

                    lbIMG.addMouseListener(new MouseListener() {
                        Frame gt;

                        @Override
                        public void mouseClicked(MouseEvent e) {
                            lbIMG = (JLabel) e.getSource();
                            System.out.println(lbIMG.getName());
                        }

                        @Override

                        public void mousePressed(MouseEvent e
                        ) {
                        }

                        @Override
                        public void mouseReleased(MouseEvent e
                        ) {
                        }

                        @Override
                        public void mouseEntered(MouseEvent e
                        ) {
                        }

                        @Override
                        public void mouseExited(MouseEvent e
                        ) {
                        }
                    }
                    );
                }
            } catch (Exception q) {
                System.out.println("cargar Imagenes: " + q.getMessage());
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

        pnlImg = new javax.swing.JPanel();
        jlAgregarImagen = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Img = new javax.swing.JLabel();
        pnlViewImagenes = new javax.swing.JPanel();
        pnlPrincipal = new javax.swing.JPanel();
        jcpNormal = new javax.swing.JScrollPane();
        pnlView = new javax.swing.JPanel();
        btnAddMember = new javax.swing.JLabel();
        txtNombre = new javax.swing.JLabel();
        txtDescView = new javax.swing.JLabel();
        txtNivel = new javax.swing.JLabel();
        txtEspecialidad = new javax.swing.JLabel();
        txtSeccion = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pnlVotos = new javax.swing.JPanel();
        stars = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        NumCreatividad = new javax.swing.JLabel();
        txtPromVoto = new javax.swing.JLabel();
        Exposicion = new javax.swing.JLabel();
        Creatividad = new javax.swing.JLabel();
        Innovacion = new javax.swing.JLabel();
        txtNumVotos = new javax.swing.JLabel();
        Separator2 = new javax.swing.JSeparator();
        pnlUbicacion = new javax.swing.JPanel();
        btnVerMapa = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jlPoint = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUbiView = new javax.swing.JLabel();
        jlMapa = new javax.swing.JLabel();
        pnlViewIntegrantes = new javax.swing.JPanel();
        lb = new javax.swing.JLabel();
        dot1 = new javax.swing.JLabel();
        dot2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        criterio1 = new javax.swing.JSlider();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        criterio2 = new javax.swing.JSlider();
        jLabel17 = new javax.swing.JLabel();
        criterio3 = new javax.swing.JSlider();
        btnVotar = new javax.swing.JButton();
        lblCriterio1 = new javax.swing.JLabel();
        lblCriterio2 = new javax.swing.JLabel();
        lblCriterio3 = new javax.swing.JLabel();
        jcpEdit = new javax.swing.JScrollPane();
        pnlViewEdit = new javax.swing.JPanel();
        cbxNivel = new javax.swing.JComboBox<>();
        lblFotoPortada = new javax.swing.JLabel();
        txtnombreEdit = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDesc = new javax.swing.JTextArea();
        btnActualizarProyecto = new javax.swing.JButton();
        jlBack = new javax.swing.JLabel();
        cbxEspecialidad = new javax.swing.JComboBox<>();
        cbxSeccionNivel = new javax.swing.JComboBox<>();
        lb1 = new javax.swing.JLabel();
        lb2 = new javax.swing.JLabel();
        PanelInfo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        lb3 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        PanelInfo1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(244, 246, 252));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(896, 586));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlImg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlAgregarImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlAgregarImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/plus.png"))); // NOI18N
        jlAgregarImagen.setEnabled(false);
        jlAgregarImagen.setFocusable(false);
        jlAgregarImagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlAgregarImagenMouseClicked(evt);
            }
        });
        pnlImg.add(jlAgregarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 350, 50, 40));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/002-left.png"))); // NOI18N
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        pnlImg.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 420));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/001-right.png"))); // NOI18N
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        pnlImg.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 50, 420));

        Img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/zz.png"))); // NOI18N
        pnlImg.add(Img, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 420));

        add(pnlImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnlViewImagenes.setBackground(new java.awt.Color(244, 246, 252));

        javax.swing.GroupLayout pnlViewImagenesLayout = new javax.swing.GroupLayout(pnlViewImagenes);
        pnlViewImagenes.setLayout(pnlViewImagenesLayout);
        pnlViewImagenesLayout.setHorizontalGroup(
            pnlViewImagenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );
        pnlViewImagenesLayout.setVerticalGroup(
            pnlViewImagenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );

        add(pnlViewImagenes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 420, 140));

        pnlPrincipal.setLayout(new java.awt.CardLayout());

        jcpNormal.setBorder(null);
        jcpNormal.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlView.setBackground(new java.awt.Color(244, 246, 252));
        pnlView.setMinimumSize(new java.awt.Dimension(442, 586));

        btnAddMember.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAddMember.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Button_Add.png"))); // NOI18N
        btnAddMember.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(46, 91, 255), 1, true));
        btnAddMember.setEnabled(false);
        btnAddMember.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMemberMouseClicked(evt);
            }
        });

        txtNombre.setBackground(new java.awt.Color(79, 54, 125));
        txtNombre.setFont(new java.awt.Font("Rubik Ligth",0,29)
        );
        txtNombre.setForeground(new java.awt.Color(46, 56, 77));
        txtNombre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtNombre.setToolTipText("");
        txtNombre.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        txtNombre.setIconTextGap(0);

        txtDescView.setFont(new java.awt.Font("Rubik", 0, 14)); // NOI18N
        txtDescView.setForeground(new java.awt.Color(136, 136, 136));
        txtDescView.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        txtNivel.setFont(new java.awt.Font("Rubik", 0, 14)); // NOI18N
        txtNivel.setForeground(new java.awt.Color(135, 152, 173));
        txtNivel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        txtEspecialidad.setFont(new java.awt.Font("Rubik", 0, 14)); // NOI18N
        txtEspecialidad.setForeground(new java.awt.Color(135, 152, 173));
        txtEspecialidad.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        txtSeccion.setFont(new java.awt.Font("Rubik", 0, 14)); // NOI18N
        txtSeccion.setForeground(new java.awt.Color(135, 152, 173));
        txtSeccion.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        id.setBackground(new java.awt.Color(244, 246, 252));
        id.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
        id.setForeground(new java.awt.Color(244, 246, 252));
        id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jSeparator1.setForeground(new java.awt.Color(221, 221, 221));
        jSeparator1.setPreferredSize(new java.awt.Dimension(70, 20));

        pnlVotos.setBackground(new java.awt.Color(255, 255, 255));

        stars.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/stars.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/1.png"))); // NOI18N
        jLabel9.setText("9.8");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel7.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/1.png"))); // NOI18N
        jLabel7.setText("7.0");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        NumCreatividad.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        NumCreatividad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NumCreatividad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/1.png"))); // NOI18N
        NumCreatividad.setText("8.9");
        NumCreatividad.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtPromVoto.setFont(new java.awt.Font("Rubik", 0, 20)); // NOI18N
        txtPromVoto.setText("8.9");

        Exposicion.setFont(new java.awt.Font("Rubik Light", 0, 12)); // NOI18N
        Exposicion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Exposicion.setText("Exposición");
        Exposicion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Creatividad.setFont(new java.awt.Font("Rubik Light", 0, 12)); // NOI18N
        Creatividad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Creatividad.setText("Creatividad");
        Creatividad.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Innovacion.setFont(new java.awt.Font("Rubik Light", 0, 12)); // NOI18N
        Innovacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Innovacion.setText("Innovación");
        Innovacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtNumVotos.setFont(new java.awt.Font("Rubik", 0, 13)); // NOI18N
        txtNumVotos.setForeground(new java.awt.Color(136, 136, 136));
        txtNumVotos.setText("+100 votos");

        Separator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout pnlVotosLayout = new javax.swing.GroupLayout(pnlVotos);
        pnlVotos.setLayout(pnlVotosLayout);
        pnlVotosLayout.setHorizontalGroup(
            pnlVotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVotosLayout.createSequentialGroup()
                .addGroup(pnlVotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVotosLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txtPromVoto)
                        .addGap(6, 6, 6)
                        .addComponent(txtNumVotos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVotosLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stars)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(Separator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlVotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVotosLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(Creatividad))
                    .addGroup(pnlVotosLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(NumCreatividad)))
                .addGap(2, 2, 2)
                .addGroup(pnlVotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVotosLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel7)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlVotosLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(Innovacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(Exposicion)
                        .addGap(22, 22, 22))))
        );
        pnlVotosLayout.setVerticalGroup(
            pnlVotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVotosLayout.createSequentialGroup()
                .addGroup(pnlVotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVotosLayout.createSequentialGroup()
                        .addGroup(pnlVotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlVotosLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(pnlVotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NumCreatividad))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtNumVotos, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPromVoto, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stars)
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVotosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlVotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Separator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVotosLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(pnlVotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Creatividad)
                                    .addComponent(Innovacion)
                                    .addComponent(Exposicion))))))
                .addContainerGap())
        );

        pnlUbicacion.setBackground(new java.awt.Color(255, 255, 255));
        pnlUbicacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnVerMapa.setBackground(new java.awt.Color(46, 91, 255));
        btnVerMapa.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        btnVerMapa.setForeground(new java.awt.Color(255, 255, 255));
        btnVerMapa.setText("Ver en mapa");
        btnVerMapa.setToolTipText("Iniciar sesión");
        btnVerMapa.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnVerMapa.setBorderPainted(false);
        btnVerMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerMapaActionPerformed(evt);
            }
        });
        pnlUbicacion.add(btnVerMapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 96, 26));

        jLabel5.setFont(new java.awt.Font("Rubik Light", 0, 12)); // NOI18N
        jLabel5.setText("Ubicación");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlUbicacion.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 90, 20));

        jlPoint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlPoint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Point.png"))); // NOI18N
        pnlUbicacion.add(jlPoint, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 40, 40));

        jLabel3.setFont(new java.awt.Font("Rubik Light", 0, 12)); // NOI18N
        jLabel3.setText("Encuentralo en:");
        pnlUbicacion.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 90, 20));

        txtUbiView.setBackground(new java.awt.Color(255, 255, 255));
        txtUbiView.setFont(new java.awt.Font("Rubik Medium", 0, 18)); // NOI18N
        txtUbiView.setForeground(new java.awt.Color(34, 34, 34));
        txtUbiView.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtUbiView.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        pnlUbicacion.add(txtUbiView, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 240, 30));

        jlMapa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlMapa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/map img.png"))); // NOI18N
        pnlUbicacion.add(jlMapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 140));

        pnlViewIntegrantes.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlViewIntegrantesLayout = new javax.swing.GroupLayout(pnlViewIntegrantes);
        pnlViewIntegrantes.setLayout(pnlViewIntegrantesLayout);
        pnlViewIntegrantesLayout.setHorizontalGroup(
            pnlViewIntegrantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlViewIntegrantesLayout.setVerticalGroup(
            pnlViewIntegrantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 155, Short.MAX_VALUE)
        );

        lb.setFont(new java.awt.Font("Rubik", 0, 13)); // NOI18N
        lb.setText("DESCRIPCIÓN");

        dot1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dot1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/dot.png"))); // NOI18N

        dot2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dot2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/dot.png"))); // NOI18N

        jLabel12.setText("Votación");

        criterio1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                criterio1StateChanged(evt);
            }
        });

        jLabel15.setText("Exposición");

        jLabel16.setText("Creatividad");

        criterio2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                criterio2StateChanged(evt);
            }
        });

        jLabel17.setText("Innovación");

        criterio3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                criterio3StateChanged(evt);
            }
        });

        btnVotar.setBackground(new java.awt.Color(46, 91, 255));
        btnVotar.setFont(new java.awt.Font("Rubik", 0, 12)); // NOI18N
        btnVotar.setForeground(new java.awt.Color(255, 255, 255));
        btnVotar.setText("Votar");
        btnVotar.setToolTipText("Iniciar sesión");
        btnVotar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnVotar.setBorderPainted(false);
        btnVotar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVotarActionPerformed(evt);
            }
        });

        lblCriterio1.setText("8.0");

        lblCriterio2.setText("9.0");

        lblCriterio3.setText("9.0");

        javax.swing.GroupLayout pnlViewLayout = new javax.swing.GroupLayout(pnlView);
        pnlView.setLayout(pnlViewLayout);
        pnlViewLayout.setHorizontalGroup(
            pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlViewLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlViewLayout.createSequentialGroup()
                        .addGroup(pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb)
                            .addGroup(pnlViewLayout.createSequentialGroup()
                                .addComponent(txtNivel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dot1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtEspecialidad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dot2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(txtSeccion)))
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addGap(328, 328, 328))
            .addGroup(pnlViewLayout.createSequentialGroup()
                .addGroup(pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlViewLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlViewLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(pnlViewIntegrantes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnlVotos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDescView, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnlViewLayout.createSequentialGroup()
                                    .addGap(139, 139, 139)
                                    .addComponent(btnAddMember, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(pnlUbicacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(pnlViewLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlViewLayout.createSequentialGroup()
                                .addComponent(criterio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblCriterio1))
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addGroup(pnlViewLayout.createSequentialGroup()
                                .addComponent(criterio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblCriterio2))
                            .addComponent(jLabel16)
                            .addGroup(pnlViewLayout.createSequentialGroup()
                                .addComponent(criterio3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblCriterio3))
                            .addComponent(jLabel17)
                            .addComponent(btnVotar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pnlViewLayout.setVerticalGroup(
            pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlViewLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addGroup(pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dot1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dot2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescView)
                .addGap(27, 27, 27)
                .addComponent(pnlVotos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlViewIntegrantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddMember, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addGap(2, 2, 2)
                .addGroup(pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCriterio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(criterio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addGroup(pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlViewLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(criterio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblCriterio2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addGroup(pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlViewLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(criterio3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblCriterio3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVotar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jcpNormal.setViewportView(pnlView);

        pnlPrincipal.add(jcpNormal, "card2");

        jcpEdit.setBorder(null);

        pnlViewEdit.setBackground(new java.awt.Color(244, 246, 252));

        cbxNivel.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        cbxNivel.setForeground(new java.awt.Color(46, 56, 77));
        cbxNivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un nivel" }));
        cbxNivel.setPreferredSize(new java.awt.Dimension(56, 27));
        cbxNivel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxNivelItemStateChanged(evt);
            }
        });
        cbxNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxNivelActionPerformed(evt);
            }
        });

        lblFotoPortada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/profilePicture.png"))); // NOI18N

        txtnombreEdit.setBackground(new java.awt.Color(244, 246, 252));
        txtnombreEdit.setFont(new java.awt.Font("Rubik Light", 0, 14)); // NOI18N
        txtnombreEdit.setBorder(null);

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtDesc.setBackground(new java.awt.Color(244, 246, 252));
        txtDesc.setColumns(20);
        txtDesc.setFont(new java.awt.Font("Rubik Light", 0, 14)); // NOI18N
        txtDesc.setLineWrap(true);
        txtDesc.setRows(5);
        txtDesc.setBorder(null);
        jScrollPane1.setViewportView(txtDesc);

        btnActualizarProyecto.setBackground(new java.awt.Color(46, 91, 255));
        btnActualizarProyecto.setFont(new java.awt.Font("Rubik Medium", 0, 14)); // NOI18N
        btnActualizarProyecto.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarProyecto.setText("Actualizar proyecto");
        btnActualizarProyecto.setBorder(null);
        btnActualizarProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarProyectoActionPerformed(evt);
            }
        });

        jlBack.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlBack.setForeground(new java.awt.Color(255, 255, 255));
        jlBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconBack.png"))); // NOI18N
        jlBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlBackMouseClicked(evt);
            }
        });

        cbxEspecialidad.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        cbxEspecialidad.setForeground(new java.awt.Color(46, 56, 77));
        cbxEspecialidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una especialidad" }));
        cbxEspecialidad.setPreferredSize(new java.awt.Dimension(56, 27));

        cbxSeccionNivel.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        cbxSeccionNivel.setForeground(new java.awt.Color(46, 56, 77));
        cbxSeccionNivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una sección" }));
        cbxSeccionNivel.setPreferredSize(new java.awt.Dimension(56, 27));

        lb1.setFont(new java.awt.Font("Rubik Medium", 0, 14)); // NOI18N
        lb1.setForeground(new java.awt.Color(46, 91, 255));
        lb1.setText("Nombre del proyecto a corregir:");

        lb2.setFont(new java.awt.Font("Rubik Medium", 0, 14)); // NOI18N
        lb2.setForeground(new java.awt.Color(46, 91, 255));
        lb2.setText("Descripción del Proyecto:");

        PanelInfo.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Rubik Medium", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(46, 91, 255));
        jLabel1.setText("Importante:");

        jLabel4.setFont(new java.awt.Font("Rubik Light", 0, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("<html>Seleccione el nivel, especialidad y sección, si no desea modificarlo, se colocará al nivel que pertenecía anteriomente.</html>");

        javax.swing.GroupLayout PanelInfoLayout = new javax.swing.GroupLayout(PanelInfo);
        PanelInfo.setLayout(PanelInfoLayout);
        PanelInfoLayout.setHorizontalGroup(
            PanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(PanelInfoLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 269, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelInfoLayout.setVerticalGroup(
            PanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        lb3.setFont(new java.awt.Font("Rubik Medium", 0, 14)); // NOI18N
        lb3.setForeground(new java.awt.Color(46, 91, 255));
        lb3.setText("Agregar o eliminar la foto de portada del proyecto:");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/btnFotoPortada1.png"))); // NOI18N

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/btnFotoPortada.png"))); // NOI18N
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        PanelInfo1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Rubik Medium", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(46, 91, 255));
        jLabel10.setText("Importante:");

        jLabel11.setFont(new java.awt.Font("Rubik Light", 0, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("<html>No es necesario modificar la foto de portada para actualizar.</html>");

        javax.swing.GroupLayout PanelInfo1Layout = new javax.swing.GroupLayout(PanelInfo1);
        PanelInfo1.setLayout(PanelInfo1Layout);
        PanelInfo1Layout.setHorizontalGroup(
            PanelInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInfo1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(PanelInfo1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 269, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelInfo1Layout.setVerticalGroup(
            PanelInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInfo1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout pnlViewEditLayout = new javax.swing.GroupLayout(pnlViewEdit);
        pnlViewEdit.setLayout(pnlViewEditLayout);
        pnlViewEditLayout.setHorizontalGroup(
            pnlViewEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlViewEditLayout.createSequentialGroup()
                .addGroup(pnlViewEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlViewEditLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator4))
                    .addGroup(pnlViewEditLayout.createSequentialGroup()
                        .addGroup(pnlViewEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlViewEditLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jlBack))
                            .addGroup(pnlViewEditLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(pnlViewEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(PanelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlViewEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lb1)
                                        .addComponent(txtnombreEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                                        .addComponent(lb2)
                                        .addComponent(jSeparator2)
                                        .addComponent(jSeparator3))))
                            .addGroup(pnlViewEditLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(cbxNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(cbxEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxSeccionNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlViewEditLayout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(lblFotoPortada)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlViewEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlViewEditLayout.createSequentialGroup()
                                        .addGap(144, 144, 144)
                                        .addComponent(jLabel8))
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel2)))
                            .addGroup(pnlViewEditLayout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(lb3))
                            .addGroup(pnlViewEditLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(PanelInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlViewEditLayout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(btnActualizarProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlViewEditLayout.setVerticalGroup(
            pnlViewEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlViewEditLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jlBack, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lb1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtnombreEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(lb2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PanelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(pnlViewEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSeccionNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(lb3)
                .addGap(18, 18, 18)
                .addGroup(pnlViewEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFotoPortada)
                    .addGroup(pnlViewEditLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18)
                .addComponent(PanelInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(btnActualizarProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jcpEdit.setViewportView(pnlViewEdit);

        pnlPrincipal.add(jcpEdit, "card3");

        add(pnlPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(446, 0, 450, 586));
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarProyectoActionPerformed

        try {
            btnActualizarProyecto.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

            if (cbxNivel.getSelectedIndex() > 0) {

                if (cbxSeccionNivel.getSelectedIndex() >= 0) {
                    Projects o = getIdSeccionNivel(cbxNivel.getSelectedItem().toString(), cbxEspecialidad.getSelectedItem().toString(), cbxSeccionNivel.getSelectedItem().toString());
                    if (Projects.actualizarPro(txtnombreEdit.getText(), txtDesc.getText(), o.getIdSeccionNivel, Integer.parseInt(id.getText()))) {
                        System.out.println("proyecto actualizado");
                        Projects p = getProject(Integer.parseInt(id.getText()));
                        txtDescView.setText("<html>" + p.DescripcionP + "</html>");
                        txtEspecialidad.setText(p.EspecialidadP);
                        txtSeccion.setText(p.SeccionP);
                        txtNombre.setText("<html>" + p.nombreP + "</html>");
                        txtnombreEdit.setText(null);
                        txtDesc.setText(null);
                        pnlPrincipal.removeAll();
                        pnlPrincipal.add(jcpNormal);
                        pnlPrincipal.repaint();
                        pnlPrincipal.revalidate();
                        Admin.cdProyectos.removeAll();
                        cargarProyectos();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al actualizar el proyecto.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione un nivel el cuál contenga una sección.");
                }

            } else {
                Projects s = getIdSeccionNivel(txtNivel.getText(), txtEspecialidad.getText(), txtSeccion.getText());
                if (Projects.actualizarPro(txtnombreEdit.getText(), txtDesc.getText(), s.getIdSeccionNivel, Integer.parseInt(id.getText()))) {
                    System.out.println("proyecto actualizado");
                    Projects p = getProject(Integer.parseInt(id.getText()));
                    txtDescView.setText("<html>" + p.DescripcionP + "</html>");
                    txtEspecialidad.setText(p.EspecialidadP);
                    txtSeccion.setText(p.SeccionP);
                    txtNombre.setText("<html>" + p.nombreP + "</html>");
                    txtnombreEdit.setText(null);
                    txtDesc.setText(null);
                    pnlPrincipal.removeAll();
                    pnlPrincipal.add(jcpNormal);
                    pnlPrincipal.repaint();
                    pnlPrincipal.revalidate();
                    Admin.cdProyectos.removeAll();
                    cargarProyectos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar el proyecto.");
                }
            }
        } catch (Exception e) {
        } finally {

            btnActualizarProyecto.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }


    }//GEN-LAST:event_btnActualizarProyectoActionPerformed


    private void btnAddMemberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMemberMouseClicked
        Db db = new Db();

        String Integrante = JOptionPane.showInputDialog(this, "Ingrese el nombre del estudiante", "Nuevo estudiante", JOptionPane.INFORMATION_MESSAGE);
        if (Integrante != null) {
            if (!Integrante.isEmpty()) {
                Projects g = getNumMembers(Integer.parseInt(id.getText()));
                if (g.CountInte <= 5) {
                    if (!db.IntegranteExiste(Integrante)) {
                        Projects p = new Projects();
                        p.nuevoIntegrante(Integrante, Integer.parseInt(id.getText()));
                        pnlIntegrantes.removeAll();
                        cargarIntegrantes();
                        pnlIntegrantes.repaint();
                        pnlIntegrantes.revalidate();

                    } else {
                        JOptionPane.showMessageDialog(this, "El integrante ya existe");
                    }
                } else {

                    JOptionPane.showMessageDialog(this, "No puede haber más de 6 integrantes en un mismo proyecto.");
                }

            } else {
                JOptionPane.showMessageDialog(this, "El campo está vacío.");
            }

        } else {

        }

    }//GEN-LAST:event_btnAddMemberMouseClicked

    private void jlAgregarImagenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlAgregarImagenMouseClicked

        Projects t = getNumIMG(Integer.parseInt(id.getText()));
        if (t.CountIMGresources <= 5) {
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
                            if (Projects.agregarRecursoIMG(file.getName(), Img.getWidth(), Img.getHeight(), 10, img, Integer.parseInt(id.getText()))) {

                                if (pnlImagenes != null) {
                                    pnlImagenes.removeAll();
                                    System.out.println("No es nulo");
                                } else {
                                    System.out.println("Es nulo");
                                }
                                cargarImagenes();
                                pnlViewImagenes.repaint();
                                pnlViewImagenes.revalidate();
                                pnlImagenes.repaint();
                                pnlImagenes.revalidate();
                                JOptionPane.showMessageDialog(this, "Foto agregada con éxito.", "Agregar foto de perfil", JOptionPane.INFORMATION_MESSAGE);

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

        } else {
            JOptionPane.showMessageDialog(this, "Solo se permiten un máximo de 6 imágenes.");
        }


    }//GEN-LAST:event_jlAgregarImagenMouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        Db db = new Db();
        Projects p = new Projects();
        Projects t = getNumIMG(Integer.parseInt(id.getText()));
        db.getIMGresources(Integer.parseInt(id.getText()));
        if (t.CountIMGresources != 0) {
            if (contador + 1 < t.CountIMGresources) {
                if (contador + 1 == t.CountIMGresources) {
                    contador = 0;
                }
                contador++;
                System.out.println("Aqui " + contador);
                jLabel14.enable();
                Img.setIcon(new ImageIcon(db.RecImagenes.get(contador)));
            }

        } else {
            JOptionPane.showMessageDialog(this, "No hay imágenes disponibles en este proyecto.");
        }


    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        if (jLabel14.isEnabled()) {
            Db db = new Db();
            Projects p = new Projects();
            Projects t = getNumIMG(Integer.parseInt(id.getText()));
            db.getIMGresources(Integer.parseInt(id.getText()));
            if (t.CountIMGresources != 0) {
                if (contador < t.CountIMGresources) {
                    if (contador == 0) {
                        contador = t.CountIMGresources;
                    }
                    contador--;
                    System.out.println("Aqui " + contador);
                    if (contador >= 0) {
                        Img.setIcon(new ImageIcon(db.RecImagenes.get(contador)));
                    }

                }

            } else {
                JOptionPane.showMessageDialog(this, "No hay imágenes disponibles en este proyecto.");
            }
        }


    }//GEN-LAST:event_jLabel14MouseClicked

    private void cbxNivelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxNivelItemStateChanged
        Db db = new Db();
        db.obtenerEspecialidad();
        cbxSeccionNivel.removeAllItems();
        cbxSeccionNivel.addItem("Seleccione una sección");
        try {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                if (cbxNivel.getSelectedIndex() > 0) {
                    if (cbxNivel.getSelectedItem().equals(String.valueOf("Primer año")) || cbxNivel.getSelectedItem().equals(String.valueOf("Segundo año")) || cbxNivel.getSelectedItem().equals(String.valueOf("Tercer año"))) {
                        cbxEspecialidad.enable();
                        cbxEspecialidad.removeAllItems();
                        cbxSeccionNivel.removeAllItems();
                        for (int i = 0; i < db.SNespecialidad.size(); i++) {
                            cbxEspecialidad.addItem(db.SNespecialidad.get(i));
                        }
                        cbxEspecialidad.removeItem(String.valueOf("Basica"));
                        db.obtenerSeccion(cbxNivel.getSelectedItem().toString(), cbxEspecialidad.getSelectedItem().toString());
                        cbxSeccionNivel.enable();
                        for (int i = 0; i < db.SNseccion.size(); i++) {
                            cbxSeccionNivel.addItem(db.SNseccion.get(i));
                        }
                    } else {
                        cbxEspecialidad.removeAllItems();
                        cbxSeccionNivel.removeAllItems();
                        for (int i = 0; i < db.SNespecialidad.size(); i++) {
                            cbxEspecialidad.addItem(db.SNespecialidad.get(i));
                        }
                        cbxEspecialidad.setSelectedItem("Basica");

                        db.obtenerSeccion(cbxNivel.getSelectedItem().toString(), cbxEspecialidad.getSelectedItem().toString());
                        cbxSeccionNivel.enable();
                        for (int i = 0; i < db.SNseccion.size(); i++) {
                            cbxSeccionNivel.addItem(db.SNseccion.get(i));
                        }
                    }

                } else {

                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_cbxNivelItemStateChanged

    private void cbxNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxNivelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxNivelActionPerformed

    private void jlBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlBackMouseClicked
        pnlPrincipal.removeAll();
        pnlPrincipal.add(jcpNormal);
        pnlPrincipal.repaint();
        pnlPrincipal.revalidate();

    }//GEN-LAST:event_jlBackMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
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

                        if (Projects.actualizarFotoPortada(img, Integer.parseInt(id.getText()))) {
                            lblFotoPortada.setIcon(new ImageIcon(img.getScaledInstance(lblFotoPortada.getWidth(), lblFotoPortada.getHeight(), Image.SCALE_DEFAULT)));

                            JOptionPane.showMessageDialog(this, "Foto de perfil actualizada con éxito.", "Actualizar foto de perfil", JOptionPane.INFORMATION_MESSAGE);

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

    }//GEN-LAST:event_jLabel6MouseClicked

    private void btnVerMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerMapaActionPerformed
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            String alias = Locations.getAlias(idUbicacion);
            try {
                Desktop.getDesktop().browse(new URI("https://maps.mapwize.io/#/p/ITR/"+alias+"?k=be2e22efcc70dfb3&embed=true&menu=false&venueId=5c8ef893f9e6100016da65ac&organizationId=5c8ef687f9e6100016da6590&z=19"));
            } catch (Exception ex) {
                System.out.println("Open map in web browser error: " + ex.getMessage());
            }
        }
        //
    }//GEN-LAST:event_btnVerMapaActionPerformed

    private void btnVotarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVotarActionPerformed
        List<Object> criterioExpo = new Vector<Object>();
        /*{criterio1.getValue(), "Exposición"};
        Votes.ingresarVotacion(CurrentUser.idUsuario, idProyecto, votos)*/
    }//GEN-LAST:event_btnVotarActionPerformed

    private void criterio1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_criterio1StateChanged
        lblCriterio1.setText(String.valueOf(criterio1.getValue()));
    }//GEN-LAST:event_criterio1StateChanged

    private void criterio2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_criterio2StateChanged
        lblCriterio2.setText(String.valueOf(criterio2.getValue()));
    }//GEN-LAST:event_criterio2StateChanged

    private void criterio3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_criterio3StateChanged
        lblCriterio3.setText(String.valueOf(criterio3.getValue()));
    }//GEN-LAST:event_criterio3StateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Creatividad;
    private javax.swing.JLabel Exposicion;
    public javax.swing.JLabel Img;
    private javax.swing.JLabel Innovacion;
    private javax.swing.JLabel NumCreatividad;
    private javax.swing.JPanel PanelInfo;
    private javax.swing.JPanel PanelInfo1;
    private javax.swing.JSeparator Separator2;
    private javax.swing.JButton btnActualizarProyecto;
    private javax.swing.JLabel btnAddMember;
    private javax.swing.JButton btnVerMapa;
    private javax.swing.JButton btnVotar;
    private javax.swing.JComboBox<String> cbxEspecialidad;
    private javax.swing.JComboBox<String> cbxNivel;
    private javax.swing.JComboBox<String> cbxSeccionNivel;
    private javax.swing.JSlider criterio1;
    private javax.swing.JSlider criterio2;
    private javax.swing.JSlider criterio3;
    private javax.swing.JLabel dot1;
    private javax.swing.JLabel dot2;
    public javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JScrollPane jcpEdit;
    private javax.swing.JScrollPane jcpNormal;
    private javax.swing.JLabel jlAgregarImagen;
    private javax.swing.JLabel jlBack;
    private javax.swing.JLabel jlMapa;
    private javax.swing.JLabel jlPoint;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lblCriterio1;
    private javax.swing.JLabel lblCriterio2;
    private javax.swing.JLabel lblCriterio3;
    public javax.swing.JLabel lblFotoPortada;
    private javax.swing.JPanel pnlImg;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JPanel pnlUbicacion;
    public static javax.swing.JPanel pnlView;
    public static javax.swing.JPanel pnlViewEdit;
    public static javax.swing.JPanel pnlViewImagenes;
    public javax.swing.JPanel pnlViewIntegrantes;
    private javax.swing.JPanel pnlVotos;
    private javax.swing.JLabel stars;
    public javax.swing.JTextArea txtDesc;
    public static javax.swing.JLabel txtDescView;
    public javax.swing.JLabel txtEspecialidad;
    public javax.swing.JLabel txtNivel;
    public static javax.swing.JLabel txtNombre;
    private javax.swing.JLabel txtNumVotos;
    private javax.swing.JLabel txtPromVoto;
    public javax.swing.JLabel txtSeccion;
    public javax.swing.JLabel txtUbiView;
    public javax.swing.JTextField txtnombreEdit;
    // End of variables declaration//GEN-END:variables

}
