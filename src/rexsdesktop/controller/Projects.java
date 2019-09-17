/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import rexsdesktop.CurrentUser;

import rexsdesktop.modal.ModalViewProyecto;

import rexsdesktop.model.Db;
import rexsdesktop.model.DbConnection;

/**
 * Clase que contiene los atributos y métodos de un proyecto.
 *
 * @author Lulac
 * @version 1.2
 */
public class Projects {

    private Connection cn;
    //private List<JPanel> paneles;

    public JPanel nuevo;
    public static JPanel panelInte;
    public static JPanel pnlIntegrantes;
    public static JScrollPane jsIntegrantes;
    public static JLabel txtIntegrantes;
    public static JLabel labelIntegrante;
    JLabel lbIMG;
    JDialog modal1;

    ImageIcon iconLocation = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconLocation.png"));
    ImageIcon iconRating = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconRating.png"));
    ImageIcon fotoProyecto = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/fotoProyecto.png"));
    ImageIcon Member = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Member.png"));
    ImageIcon Member1 = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Member1.png"));
    ImageIcon Member2 = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Member2.png"));

    public static String mensajeError = "";

    public int idUsuario;
    public String nombreP;
    public String DescripcionP;
    public String nivelP;
    public String EspecialidadP;
    public String UbicacionP;
    public String SeccionP;
    public Integer CountInte;
    public Integer CountIMGresources;
    public Integer getIdSeccionNivel;
    public Integer getNumProyectosFiltrados;

    public void CrearPanelesProyectos(javax.swing.JPanel panel, String edicion) {
        Db db = new Db();
      
        db.Proyectos(edicion);
        db.NumProyectos(edicion);
        
        
        for (int i = 0; i < db.getCantidadProyecto(); i++) {

            //Creando el primer JPanel principal 
            nuevo = new JPanel();
            panel.add(nuevo);
            //paneles.add(nuevo);
            nuevo.setName(String.valueOf(db.PjId.get(i)));
            nuevo.setLayout(new BorderLayout(0, 0));
            nuevo.setPreferredSize(new Dimension(377, 120));
            nuevo.setSize(new Dimension(377, 120));
            nuevo.setBackground(Color.white);
            // Border hola = new EtchedBorder();
            Border borde = new LineBorder(new Color(46, 92, 255), 1, true);
            nuevo.setBorder(borde);
            //Creando el segundo JPanel que contendrá la imagen a la Izquierda 
            JPanel img = new JPanel();
            img.setBackground(Color.white);
            img.setLayout(new BorderLayout(0, 0));
            img.setPreferredSize(new Dimension(140, 120));
            img.setSize(new Dimension(140, 120));
            nuevo.add(img, BorderLayout.WEST);
            //Creando el Label que contendrá la imagen del proyecto
            JLabel imagen = new JLabel();
            BufferedImage icon = db.PjImagenes.get(i);
            if (icon != null) {

                try {
                    // BufferedImage icon = General.resizeSquare(CurrentUser.fotoPerfil, 128);
                    imagen.setIcon(new ImageIcon(icon));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                imagen.setIcon(fotoProyecto);
            }

            // imagen.setIcon(fotoProyecto);
            imagen.setHorizontalAlignment(SwingConstants.CENTER);
            img.add(imagen);
            //Creando el tercer JPanel para la informacion a la derecha
            JPanel info = new JPanel();
            info.setLayout(new GridLayout(0, 1, 0, 0));
            info.setBackground(Color.white);
            info.setPreferredSize(new Dimension(240, 120));
            info.setSize(new Dimension(240, 120));
            nuevo.add(info, BorderLayout.CENTER);

            //Creando el Label para el nombre del proyecto
            JLabel nombre = new JLabel();
            nombre.setFont(new java.awt.Font("Rubik Medium", 0, 14));
            nombre.setForeground(new Color(46, 56, 77));
            //nombre.setBorder(hola);
            nombre.setText("  " + db.PjNombre.get(i));

            info.add(nombre);
            //Creando el Label para el grado
            JLabel nivel = new JLabel();
            nivel.setFont(new java.awt.Font("Rubik", 0, 11));
            nivel.setForeground(new Color(136, 136, 136));
            //nivel.setBorder(hola);
            nivel.setText("   " + db.PjNivel.get(i) + " " + db.PjSeccion.get(i));

            info.add(nivel);
            //Creando el Label seccion-nivel del proyecto
            JLabel seccion = new JLabel();
            seccion.setFont(new java.awt.Font("Rubik", 0, 11));
            seccion.setForeground(new Color(136, 136, 136));
            seccion.setText("   " + db.PjEspecialidad.get(i));
            //seccion.setBorder(hola);
            info.add(seccion);

            //Creando el panel para la info con imagen
            JPanel info2 = new JPanel();
            info2.setLayout(null);
            info2.setBackground(Color.white);
            //info2.setBorder(hola);
            info.add(info2);

            JLabel img2 = new JLabel();
            img2.setBounds(5, 5, 14, 20);
            img2.setIcon(iconLocation);
            //img2.setBorder(hola);
            info2.add(img2);

            //Creando el Label de ubicacion del proyecto
            JLabel ubi = new JLabel();
            ubi.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            ubi.setForeground(new Color(34, 34, 34));

            ubi.setBounds(23, 4, 170, 22);
            //ubi.setBorder(hola);
            ubi.setText(Locations.getPlaceName(db.PjUbicacion.get(i).trim()));
            info2.add(ubi);

            JLabel img3 = new JLabel();
            img3.setBounds(200, 5, 18, 20);
            img3.setIcon(iconRating);
            //img3.setBorder(hola);
            info2.add(img3);

            JLabel voto = new JLabel();
            voto.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            voto.setForeground(new Color(34, 34, 34));
            voto.setBounds(220, 4, 27, 20);
            voto.setText("8.9");
            //voto.setBorder(hola);
            info2.add(voto);

            nuevo.addMouseListener(new MouseListener() {
                Frame fr;

                @Override
                public void mouseClicked(MouseEvent e) {
                    nuevo = (JPanel) e.getSource();
                    nuevo.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    ModalViewProyecto modalProye = new ModalViewProyecto();
                    Projects p = getProject(Integer.parseInt(nuevo.getName()));
                    modalProye.txtDescView.setText("<html>" + p.DescripcionP + "</html>");
                    modalProye.txtNivel.setText(p.nivelP);
                    modalProye.txtEspecialidad.setText(p.EspecialidadP);
                    modalProye.txtSeccion.setText(p.SeccionP);
                    modalProye.txtNombre.setText("<html>" + p.nombreP + "</html>");
                    modalProye.txtnombre.setText(p.nombreP);
                    modalProye.txtDesc.setText(p.DescripcionP);
                    modalProye.txtUbiView.setText(Locations.getPlaceName(p.UbicacionP));
                    modalProye.lbl.setText(nuevo.getName());

                    //CREACIÓN DEL PANEL DONDE SE MUESTRA LOS INTEGRANTES
                    modalProye.pnlViewIntegrantes.setLayout(null);
                   txtIntegrantes = new JLabel();
                    txtIntegrantes.setBounds(10, 10, 70, 20);
                    // txtIntegrantes.setBorder(new EtchedBorder());
                    txtIntegrantes.setText("Integrantes");
                    txtIntegrantes.setHorizontalTextPosition(CENTER);
                    txtIntegrantes.setFont(new java.awt.Font("Rubik", 1, 12));
                    pnlIntegrantes = new JPanel();
                    pnlIntegrantes.setLayout(new GridLayout(1, 0, 15, 15));
                    pnlIntegrantes.setBackground(Color.white);
                    jsIntegrantes = new JScrollPane(pnlIntegrantes);
                    jsIntegrantes.setBounds(10, 35, 90, 90);
                    jsIntegrantes.setBorder(null);
                    modalProye.pnlViewIntegrantes.add(txtIntegrantes);
                    modalProye.pnlViewIntegrantes.add(jsIntegrantes);
                    Projects g = getNumMembers(Integer.parseInt(nuevo.getName()));
                    db.ViewIntegrantes(Integer.parseInt(nuevo.getName()));
                    if (g.CountInte != 0) {
                        try {
                            int y = 0;
                            for (int k = 0; k < g.CountInte; k++) {
                                if (g.CountInte <= 3) {
                                    panelInte = new JPanel();
                                    panelInte.setName(db.nombreInte.get(k));
                                    panelInte.setLayout(new BorderLayout(0, 0));
                                    jsIntegrantes.setSize(new Dimension(105 + y, 112));
                                    panelInte.setPreferredSize(new Dimension(90, 90));
                                    panelInte.setBackground(Color.WHITE);
                                    panelInte.setBorder(null);
                                    JLabel imgMember = new JLabel();
                                    imgMember.setHorizontalAlignment(CENTER);
                                    imgMember.setIcon(Member);
                                    //imgMember.setBorder(new EtchedBorder());
                                    JLabel labelIntegrante = new JLabel("<html>" + db.nombreInte.get(k) + "</html>", SwingConstants.CENTER);
                                    labelIntegrante.setBounds(0, 15, 50, 20);
                                    //labelIntegrante.setBorder(new EtchedBorder());
                                    panelInte.add(imgMember, BorderLayout.NORTH);
                                    panelInte.add(labelIntegrante, BorderLayout.CENTER);
                                    pnlIntegrantes.add(panelInte);
                                    y = y + 105;
                                } else {
                                    panelInte = new JPanel();
                                    panelInte.setName(db.nombreInte.get(k));
                                    panelInte.setLayout(new BorderLayout(0, 0));
                                    jsIntegrantes.setSize(new Dimension(380, 112));
                                    panelInte.setPreferredSize(new Dimension(90, 90));
                                    panelInte.setBackground(Color.WHITE);
                                    JLabel imgMember = new JLabel();
                                    imgMember.setHorizontalAlignment(CENTER);
                                    imgMember.setIcon(Member);
                                    //imgMember.setBorder(new EtchedBorder());
                                    JLabel labelIntegrante = new JLabel("<html>" + db.nombreInte.get(k) + "</html>", SwingConstants.CENTER);
                                    labelIntegrante.setBounds(0, 15, 50, 20);
                                    //labelIntegrante.setBorder(new EtchedBorder());
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
                                        String nameMember = JOptionPane.showInputDialog(modalProye, "Ingrese correctamente el nombre del estudiante", panelInte.getName());
                                        Db db = new Db();
                                        if (!nameMember.isEmpty()) {

                                            if (!db.IntegranteExiste(nameMember)) {
                                                Projects.actualizarInte(nameMember, panelInte.getName(), Integer.parseInt(nuevo.getName()));
                                            } else {
                                                JOptionPane.showMessageDialog(modalProye, "El integrante ya existe");
                                            }

                                        } else {
                                            JOptionPane.showMessageDialog(modalProye, "El campo está vacío.");
                                        }

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

                        } catch (Exception f) {
                            System.out.println(f.getMessage());
                        }
                    } else {

                        txtIntegrantes.setText("No hay integrantes asignados a este proyecto.");
                        txtIntegrantes.setSize(new Dimension(300, 50));
                        jsIntegrantes.disable();

                    }

                    Projects t = getNumIMG(Integer.parseInt(nuevo.getName()));
                    db.getIMGresources(Integer.parseInt(nuevo.getName()));
                    if (t.CountIMGresources != 0) {
                        try {
                            modalProye.Img.setIcon(new ImageIcon(db.RecImagenes.get(0)));
                            for (int j = 0; j < t.CountIMGresources; j++) {
                                modalProye.pnlImagenes.setLayout(new GridLayout(1, 0, 15, 15));
                                lbIMG = new JLabel();
                                lbIMG.setPreferredSize(new Dimension(120, 70));

                                //lbIMG.setBorder(new EtchedBorder());
                                BufferedImage icon = db.RecImagenes.get(j);
                                if (icon != null) {
                                    try {
                                        lbIMG.setIcon(new ImageIcon(icon));
                                    } catch (Exception w) {
                                        System.out.println(w.getMessage());
                                    }
                                } else {
                                    lbIMG.setIcon(fotoProyecto);
                                }
                                lbIMG.setName(String.valueOf(db.RecIdRecurso.get(j)));
                                modalProye.pnlImagenes.add(lbIMG);

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
                            System.out.println(q.getMessage());
                        }

                    } else {
                        modalProye.pnlImagenes.setLayout(null);
                        lbIMG = new JLabel();
                        lbIMG.setBounds(60, 30, 300, 50);
                        //lbIMG.setBorder(new EtchedBorder());
                        lbIMG.setText("<html>" + "No hay imágenes que mostrar en este proyecto." + "</html>");
                        modalProye.pnlImagenes.add(lbIMG);
                    }
                    JDialog modal1 = new JDialog(fr, "Nuevo Proyecto", true);
                    modal1.getContentPane().add(modalProye);
                    modal1.pack();
                    modal1.setLocationRelativeTo(null);
                    modal1.setVisible(true);
                    nuevo.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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
    }

    public void FiltroPanelesProyectos(javax.swing.JPanel panel, String nivel1, String especialidad1, String seccion1, String edicion) {

        Db db = new Db();
        db.FiltroProyectos(nivel1, especialidad1, seccion1,edicion);
        Projects p = getNumProyectosFiltrados(nivel1, especialidad1, seccion1,edicion);
        for (int i = 0; i < p.getNumProyectosFiltrados; i++) {

            //Creando el primer JPanel principal 
            nuevo = new JPanel();
            panel.add(nuevo);
            //paneles.add(nuevo);
            nuevo.setName(String.valueOf(db.PjId2.get(i)));
            nuevo.setLayout(new BorderLayout(0, 0));
            nuevo.setPreferredSize(new Dimension(377, 120));
            nuevo.setSize(new Dimension(377, 120));
            nuevo.setBackground(Color.white);
            // Border hola = new EtchedBorder();
            Border borde = new LineBorder(new Color(46, 92, 255), 1, true);
            nuevo.setBorder(borde);
            //Creando el segundo JPanel que contendrá la imagen a la Izquierda 
            JPanel img = new JPanel();
            img.setBackground(Color.white);
            img.setLayout(new BorderLayout(0, 0));
            img.setPreferredSize(new Dimension(140, 120));
            img.setSize(new Dimension(140, 120));
            nuevo.add(img, BorderLayout.WEST);
            //Creando el Label que contendrá la imagen del proyecto
            JLabel imagen = new JLabel();
            BufferedImage icon = db.PjImagenes2.get(i);
            if (icon != null) {

                try {
                    // BufferedImage icon = General.resizeSquare(CurrentUser.fotoPerfil, 128);
                    imagen.setIcon(new ImageIcon(icon));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                imagen.setIcon(fotoProyecto);
            }

            // imagen.setIcon(fotoProyecto);
            imagen.setHorizontalAlignment(SwingConstants.CENTER);
            img.add(imagen);
            //Creando el tercer JPanel para la informacion a la derecha
            JPanel info = new JPanel();
            info.setLayout(new GridLayout(0, 1, 0, 0));
            info.setBackground(Color.white);
            info.setPreferredSize(new Dimension(240, 120));
            info.setSize(new Dimension(240, 120));
            nuevo.add(info, BorderLayout.CENTER);

            //Creando el Label para el nombre del proyecto
            JLabel nombre = new JLabel();
            nombre.setFont(new java.awt.Font("Rubik Medium", 0, 14));
            nombre.setForeground(new Color(46, 56, 77));
            //nombre.setBorder(hola);
            nombre.setText("  " + db.PjNombre2.get(i));

            info.add(nombre);
            //Creando el Label para el grado
            JLabel nivel = new JLabel();
            nivel.setFont(new java.awt.Font("Rubik", 0, 11));
            nivel.setForeground(new Color(136, 136, 136));
            //nivel.setBorder(hola);
            nivel.setText("   " + db.PjNivel2.get(i) + " " + db.PjSeccion2.get(i));

            info.add(nivel);
            //Creando el Label seccion-nivel del proyecto
            JLabel seccion = new JLabel();
            seccion.setFont(new java.awt.Font("Rubik", 0, 11));
            seccion.setForeground(new Color(136, 136, 136));
            seccion.setText("   " + db.PjEspecialidad2.get(i));
            //seccion.setBorder(hola);
            info.add(seccion);

            //Creando el panel para la info con imagen
            JPanel info2 = new JPanel();
            info2.setLayout(null);
            info2.setBackground(Color.white);
            //info2.setBorder(hola);
            info.add(info2);

            JLabel img2 = new JLabel();
            img2.setBounds(5, 5, 14, 20);
            img2.setIcon(iconLocation);
            //img2.setBorder(hola);
            info2.add(img2);

            //Creando el Label de ubicacion del proyecto
            JLabel ubi = new JLabel();
            ubi.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            ubi.setForeground(new Color(34, 34, 34));

            ubi.setBounds(23, 4, 170, 22);
            //ubi.setBorder(hola);
            ubi.setText(Locations.getPlaceName(db.PjUbicacion2.get(i).trim()));
            info2.add(ubi);

            JLabel img3 = new JLabel();
            img3.setBounds(200, 5, 18, 20);
            img3.setIcon(iconRating);
            //img3.setBorder(hola);
            info2.add(img3);

            JLabel voto = new JLabel();
            voto.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            voto.setForeground(new Color(34, 34, 34));
            voto.setBounds(220, 4, 27, 20);
            voto.setText("8.9");
            //voto.setBorder(hola);
            info2.add(voto);

            nuevo.addMouseListener(new MouseListener() {
                Frame fr;

                @Override
                public void mouseClicked(MouseEvent e) {
                    nuevo = (JPanel) e.getSource();
                    nuevo.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    ModalViewProyecto modalProye = new ModalViewProyecto();
                    Projects p = getProject(Integer.parseInt(nuevo.getName()));
                    modalProye.txtDescView.setText("<html>" + p.DescripcionP + "</html>");
                    modalProye.txtNivel.setText(p.nivelP);
                    modalProye.txtEspecialidad.setText(p.EspecialidadP);
                    modalProye.txtSeccion.setText(p.SeccionP);
                    modalProye.txtNombre.setText("<html>" + p.nombreP + "</html>");
                    modalProye.txtnombre.setText(p.nombreP);
                    modalProye.txtDesc.setText(p.DescripcionP);
                    modalProye.txtUbiView.setText(Locations.getPlaceName(p.UbicacionP));
                    modalProye.lbl.setText(nuevo.getName());

                    //CREACIÓN DEL PANEL DONDE SE MUESTRA LOS INTEGRANTES
                    modalProye.pnlViewIntegrantes.setLayout(null);
                    
                    txtIntegrantes = new JLabel();
                    txtIntegrantes.setBounds(10, 10, 70, 20);
                    // txtIntegrantes.setBorder(new EtchedBorder());
                    txtIntegrantes.setText("Integrantes");
                    txtIntegrantes.setHorizontalTextPosition(CENTER);
                    txtIntegrantes.setFont(new java.awt.Font("Rubik", 1, 12));
                    pnlIntegrantes = new JPanel();
                    pnlIntegrantes.setLayout(new GridLayout(1, 0, 15, 15));
                    pnlIntegrantes.setBackground(Color.white);
                     jsIntegrantes = new JScrollPane(pnlIntegrantes);
                    jsIntegrantes.setBounds(10, 35, 90, 90);
                    jsIntegrantes.setBorder(null);
                    modalProye.pnlViewIntegrantes.add(txtIntegrantes);
                    modalProye.pnlViewIntegrantes.add(jsIntegrantes);
                    Projects g = getNumMembers(Integer.parseInt(nuevo.getName()));
                    db.ViewIntegrantes(Integer.parseInt(nuevo.getName()));
                    if (g.CountInte != 0) {
                        try {
                            int y = 0;
                            for (int k = 0; k < g.CountInte; k++) {
                                if (g.CountInte <= 3) {
                                    panelInte = new JPanel();
                                    panelInte.setName(db.nombreInte.get(k));
                                    panelInte.setLayout(new BorderLayout(0, 0));
                                    jsIntegrantes.setSize(new Dimension(105 + y, 112));
                                    panelInte.setPreferredSize(new Dimension(90, 90));
                                    panelInte.setBackground(Color.WHITE);
                                    panelInte.setBorder(null);
                                    JLabel imgMember = new JLabel();
                                    imgMember.setHorizontalAlignment(CENTER);
                                    imgMember.setIcon(Member);
                                    //imgMember.setBorder(new EtchedBorder());
                                    labelIntegrante = new JLabel("<html>" + db.nombreInte.get(k) + "</html>", SwingConstants.CENTER);
                                    labelIntegrante.setBounds(0, 15, 50, 20);
                                    //labelIntegrante.setBorder(new EtchedBorder());
                                    panelInte.add(imgMember, BorderLayout.NORTH);
                                    panelInte.add(labelIntegrante, BorderLayout.CENTER);
                                    pnlIntegrantes.add(panelInte);
                                    y = y + 105;
                                } else {
                                    panelInte = new JPanel();
                                    panelInte.setName(db.nombreInte.get(k));
                                    panelInte.setLayout(new BorderLayout(0, 0));
                                    jsIntegrantes.setSize(new Dimension(380, 112));
                                    panelInte.setPreferredSize(new Dimension(90, 90));
                                    panelInte.setBackground(Color.WHITE);
                                    JLabel imgMember = new JLabel();
                                    imgMember.setHorizontalAlignment(CENTER);
                                    imgMember.setIcon(Member);
                                    //imgMember.setBorder(new EtchedBorder());
                                    labelIntegrante = new JLabel("<html>" + db.nombreInte.get(k) + "</html>", SwingConstants.CENTER);
                                    labelIntegrante.setBounds(0, 15, 50, 20);
                                    //labelIntegrante.setBorder(new EtchedBorder());
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
                                        String nameMember = JOptionPane.showInputDialog(modalProye, "Ingrese correctamente el nombre del estudiante", panelInte.getName());
                                        Db db = new Db();
                                        if (!nameMember.isEmpty()) {

                                            if (!db.IntegranteExiste(nameMember)) {
                                                Projects.actualizarInte(nameMember, panelInte.getName(), Integer.parseInt(nuevo.getName()));
                                            } else {
                                                JOptionPane.showMessageDialog(modalProye, "El integrante ya existe");
                                            }

                                        } else {
                                            JOptionPane.showMessageDialog(modalProye, "El campo está vacío.");
                                        }

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

                        } catch (Exception f) {
                            System.out.println(f.getMessage());
                        }
                    } else {

                        txtIntegrantes.setText("No hay integrantes asignados a este proyecto.");
                        txtIntegrantes.setSize(new Dimension(300, 50));
                        jsIntegrantes.disable();

                    }

                    Projects t = getNumIMG(Integer.parseInt(nuevo.getName()));
                    db.getIMGresources(Integer.parseInt(nuevo.getName()));
                    if (t.CountIMGresources != 0) {
                        try {
                            modalProye.Img.setIcon(new ImageIcon(db.RecImagenes.get(0)));
                            for (int j = 0; j < t.CountIMGresources; j++) {
                                modalProye.pnlImagenes.setLayout(new GridLayout(1, 0, 15, 15));
                                lbIMG = new JLabel();
                                lbIMG.setPreferredSize(new Dimension(120, 70));

                                //lbIMG.setBorder(new EtchedBorder());
                                BufferedImage icon = db.RecImagenes.get(j);
                                if (icon != null) {
                                    try {
                                        lbIMG.setIcon(new ImageIcon(icon));
                                    } catch (Exception w) {
                                        System.out.println(w.getMessage());
                                    }
                                } else {
                                    lbIMG.setIcon(fotoProyecto);
                                }
                                lbIMG.setName(String.valueOf(db.RecIdRecurso.get(j)));
                                modalProye.pnlImagenes.add(lbIMG);

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
                            System.out.println(q.getMessage());
                        }

                    } else {
                        modalProye.pnlImagenes.setLayout(null);
                        lbIMG = new JLabel();
                        lbIMG.setBounds(60, 30, 300, 50);
                        //lbIMG.setBorder(new EtchedBorder());
                        lbIMG.setText("<html>" + "No hay imágenes que mostrar en este proyecto." + "</html>");
                        modalProye.pnlImagenes.add(lbIMG);
                    }
                    modal1 = new JDialog(fr, "Nuevo Proyecto", true);
                    modal1.getContentPane().add(modalProye);
                    modal1.pack();
                    modal1.setLocationRelativeTo(null);
                    modal1.setVisible(true);
                    nuevo.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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
    }

    public static boolean nuevoProyecto(String nombre, String descripcion, String edicion, int idSeccionNivel, BufferedImage img) {
        Db db = new Db();
        if (!db.proyectoExiste(nombre)) {
            if (img != null) {
                try {
                    img = General.resizeAndCropIMG2(img, 140, 120);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    if (ImageIO.write(img, "jpg", baos)) {
                        byte[] immAsBytes = baos.toByteArray();
                        baos.flush();
                        baos.close();
                        return db.agregarProyecto(nombre, descripcion, edicion, idSeccionNivel, immAsBytes);
                    }
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            } else {
                return db.agregarProyecto(nombre, descripcion, edicion, idSeccionNivel, null);
            }

        } else {
            mensajeError = "<html>Ya existe un proyecto con la dirección de<br>correo electrónico.</html>";
            return false;
        }
        return false;
    }

    public static Projects getProject(int id) {
        try {
            Db db = new Db();
            ResultSet rs = db.ViewProyecto(id);
            Projects q = new Projects();
            q.nombreP = rs.getString(1);
            q.nivelP = rs.getString(2);
            q.SeccionP = rs.getString(3);
            q.EspecialidadP = rs.getString(4);
            q.UbicacionP = rs.getString(5);
            q.DescripcionP = rs.getString(6);
            return q;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return null;
        }
    }

    public static boolean actualizarPro(String nombre, String desc, int idSeccion, int id) {
        Db db = new Db();
        mensajeError = "";
        if (db.actualizarProyecto(nombre, desc, idSeccion, id)) {
            mensajeError = "";
            return true;
        } else {
            mensajeError = "Hubo un error al actualizar el usuario. Intenta de nuevo.";
        }

        return false;
    }

    public static boolean actualizarInte(String nombreAct, String nombre, int idP) {
        Db db = new Db();
        mensajeError = "";
        if (db.actualizarIntegrante(nombreAct, nombre, idP)) {
            mensajeError = "";
            return true;
        } else {
            mensajeError = "Hubo un error al actualizar el integrante. Intenta de nuevo.";
        }

        return false;
    }

    public static boolean actualizarFotoPortada(BufferedImage img, int idProyecto) {
        Db db = new Db();
        try {
            img = General.resizeAndCropIMG2(img, 140, 120);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            if (ImageIO.write(img, "jpg", baos)) {
                byte[] immAsBytes = baos.toByteArray();
                baos.flush();
                baos.close();
                return db.actualizarFotoProyecto(immAsBytes, idProyecto);
            }

        } catch (IOException ex) {
            System.out.println(ex);
        }
        return false;
    }

    public static boolean agregarRecursoIMG(String nombreRecurso, int w, int h, int tamanoRecurso, BufferedImage streamRecurso, int idP) {
        Db db = new Db();
        try {
            streamRecurso = General.resizeAndCropIMG2(streamRecurso, w, h);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            if (ImageIO.write(streamRecurso, "jpg", baos)) {
                byte[] immAsBytes = baos.toByteArray();
                baos.flush();
                baos.close();
                return db.agregarRecursoImg(nombreRecurso, tamanoRecurso, immAsBytes, idP);
            }

        } catch (IOException ex) {
            System.out.println(ex);
        }
        return false;
    }

    /**
     * Método utilizado obtener el número de integrantes de un proyecto.
     *
     * @param id identificador del proyecto.
     * @return retorna el número de integrantes de un proyecto.
     */
    public static Projects getNumMembers(int id) {
        try {
            Db db = new Db();
            ResultSet rs = db.NumIntegrantes(id);
            Projects r = new Projects();
            r.CountInte = rs.getInt(1);
            return r;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return null;
        }
    }

    public static Projects getNumIMG(int id) {
        try {
            Db db = new Db();
            ResultSet rs = db.getNumIMGresources(id);
            Projects h = new Projects();
            h.CountIMGresources = rs.getInt(1);
            return h;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return null;
        }
    }

    public boolean eliminarProyecto(int id) {
        Db db = new Db();
        try {
            if (db.eliminarProyecto(id)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }
        return false;

    }

    public static Projects getIdSeccionNivel(String nivel, String especialidad, String seccion) {

        try {
            Db db = new Db();
            ResultSet rs = db.getIdSeccionNivel(nivel, especialidad, seccion);
            Projects gg = new Projects();
            gg.getIdSeccionNivel = rs.getInt(1);
            return gg;

        } catch (SQLException e) {
            System.out.println("getIdSeccionNivel " + e.getCause());
        }
        return null;

    }

    public static Projects getNumProyectosFiltrados(String nivel, String especialidad, String seccion, String edicion) {
        try {
            Db db = new Db();
            ResultSet rs = db.NumProyectosFiltrados(nivel, especialidad, seccion, edicion);
            Projects h = new Projects();
            h.getNumProyectosFiltrados = rs.getInt(1);
            return h;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return null;
        }
    }

    public boolean nuevoIntegrante(String nombre, int id) {
        Db db = new Db();
        if (!db.IntegranteExiste(nombre)) {

            if (db.agregarIntegrante(nombre, id)) {
                mensajeError = "";
                return true;
            } else {
                mensajeError = "Hubo un error al registrar un nuevo integrante. Intenta de nuevo.";
                return false;
            }
        } else {
            mensajeError = "<html>Ya existe un estudainte con la dirección de<br>correo electrónico.</html>";
            return false;
        }
    }

}
