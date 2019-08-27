/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import rexsdesktop.modal.ModalViewProyecto;

import rexsdesktop.model.Db;
import rexsdesktop.model.DbConnection;

/**
 * Clase que contiene los atributos y métodos de un proyecto.
 * @author Lulac
 * @version 1.2
 */
public class Projects {

    private Connection cn;
    //private List<JPanel> paneles;

    JPanel nuevo;
    JPanel Grid;
    JPanel panelInte;
    ImageIcon iconLocation = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconLocation.png"));
    ImageIcon iconRating = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconRating.png"));
    ImageIcon fotoProyecto = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/fotoProyecto.png"));

    public static String mensajeError = "";

    public int idUsuario;
    public String nombreP;
    public String DescripcionP;
    public String nivelP;
    public String EspecialidadP;
    public String UbicacionP;
    public String SeccionP;
    public Integer CountInte;
    public ArrayList<String> nombreInte;

    public void CrearPanelesProyectos(javax.swing.JPanel panel) {
        Db db = new Db();
        db.Proyectos();
        db.NumProyectos();

        //paneles = new ArrayList<>();
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
                // btnEliminarFotoPerfil.setEnabled(true);
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
            ubi.setText(db.PjUbicacion.get(i).trim());
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

                    ModalViewProyecto modalProye = new ModalViewProyecto();
                    Projects p = getProject(Integer.parseInt(nuevo.getName()));
                    modalProye.txtDescView.setText("<html>" + p.DescripcionP + "</html>");
                    modalProye.txtNivel.setText(p.nivelP);
                    modalProye.txtEspecialidad.setText(p.EspecialidadP);
                    modalProye.txtSeccion.setText(p.SeccionP);
                    modalProye.txtNombre.setText("<html>" + p.nombreP + "</html>");
                    modalProye.txtnombre.setText(p.nombreP);
                    modalProye.txtDesc.setText(p.DescripcionP);
                    modalProye.txtUbiView.setText(p.UbicacionP);
                    modalProye.lbl.setText(nuevo.getName());
                    Projects g = getNumMembers(Integer.parseInt(nuevo.getName()));
                    ModalViewProyecto.pnlPrueba.setLayout(null);
                    System.out.println(g.CountInte);
                   
                   if (g.CountInte != 0) {
                        try {
                            ModalViewProyecto.pnlContInte.setSize(410, 200);
                            ModalViewProyecto.pnlContInte.setLayout(null);
                            JLabel label = new JLabel();
                            label.setText("Integrantes");
                            label.setBounds(0, 12, 170, 22);
                            ModalViewProyecto.pnlContInte.add(label);
                            int y = 0;
                            for (int j = 0; j < g.CountInte; j++) {
                                if (g.CountInte == 1) {
                                    panelInte = new JPanel();
                                    panelInte.setLayout(null);
                                    panelInte.setBounds(0, 36 + y, 410, 50);
                                    panelInte.setBackground(new Color(156, 117, 226));
                                    panelInte.setBorder(new EtchedBorder());
                                    ModalViewProyecto.pnlContInte.setSize(410, 100);
                                  //  JLabel Integrante = new JLabel();
                                   // Integrante.setBounds(0, 12, 170, 22);
                                    //Integrante.setText(w.nombreInte.get(j));
                                   // panelInte.add(Integrante);
                                    //ModalViewProyecto.pnlContInte.add(panelInte);
                                    y = y + 56;
                                } else if (g.CountInte == 2) {
                                    panelInte = new JPanel();
                                    panelInte.setLayout(null);
                                    panelInte.setBounds(0, 36 + y, 410, 50);
                                    panelInte.setBackground(new Color(156, 117, 226));
                                    panelInte.setBorder(new EtchedBorder());
                                    ModalViewProyecto.pnlContInte.setSize(410, 200);
                                    //JLabel Integrante = new JLabel();
                             //       Integrante.setBounds(0, 12, 170, 22);
                              //      Integrante.setText(w.nombreInte.get(j));
                                //    panelInte.add(Integrante);
                                    ModalViewProyecto.pnlContInte.add(panelInte);
                                    y = y + 56;
                                } else if (g.CountInte == 3) {
                                    panelInte = new JPanel();
                                    panelInte.setLayout(null);
                                    panelInte.setBounds(0, 36 + y, 410, 50);
                                    panelInte.setBackground(new Color(156, 117, 226));
                                    panelInte.setBorder(new EtchedBorder());
                                    ModalViewProyecto.pnlContInte.setSize(410, 300);
                    
                                    ModalViewProyecto.pnlContInte.add(panelInte);
                                    y = y + 56;
                                }

                            }
                        } catch (Exception f) {
                            System.out.println(f.getMessage());
                        }

                    } else {
                        try {
                            // ModalViewProyecto.Gridpnl.disable();
                            ModalViewProyecto.pnlContInte.setSize(410, 50);
                        } catch (Exception f) {
                            System.out.println(f.getMessage());
                        }

                    }

                    // ImageIcon i = new ImageIcon("src/rexsdesktop/view/resources/prueba.jpg");
                    //Icon icono = new ImageIcon(i.getImage().getScaledInstance(440, 420, Image.SCALE_SMOOTH));
                    // modalProye.Img.setIcon(i);
                    JDialog modal1 = new JDialog(fr, "Nuevo Proyecto", true);
                    modal1.getContentPane().add(modalProye);
                    modal1.pack();
                    modal1.setLocationRelativeTo(null);
                    modal1.setVisible(true);

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

    public Projects() {
        DbConnection clase1 = new DbConnection();
        cn = clase1.conectar();
    }

    public ResultSet consulta(String sql) {
        ResultSet res = null;
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            res = pstm.executeQuery();
        } catch (Exception e) {
        }
        return res;
    }
    
    
    public static boolean nuevoProyecto(String nombre, String descripcion, int SeccionNivel) {
        Db db = new Db();
        if (!db.proyectoExiste(nombre)) {

            if (db.agregarProyecto(nombre, descripcion, SeccionNivel)) {
                mensajeError = "";
                return true;
            } else {
                mensajeError = "Hubo un error al registrar el proyecto. Intenta de nuevo.";
                return false;
            }
        } else {
            mensajeError = "<html>Ya existe un proyecto con la dirección de<br>correo electrónico.</html>";
            return false;
        }
    }

    public static Projects getProject(int id) {
        try {
            Db db = new Db();
            ResultSet rs = db.ViewProyecto(id);
            Projects p = new Projects();
            p.nombreP = rs.getString(1);
            p.nivelP = rs.getString(2);
            p.SeccionP = rs.getString(3);
            p.EspecialidadP = rs.getString(4);
            p.UbicacionP = rs.getString(5);
            p.DescripcionP = rs.getString(6);
            return p;
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
    
    /**
     * Método utilizado obtener el nombre de integrantes de un proyecto.
     * 
     * @param id identificador del proyecto.
     * @return nombres de los integrantes.
     */
    
    public static Projects getMembers(int id) {
        try {
            Db db = new Db();
            ResultSet rs = db.ViewIntegrantes(id);
            Projects po = new Projects();
           
                po.nombreInte.add(rs.getString(1));
          
            return po;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return null;
        }
    }
    /**
     * Método utilizado obtener el número de integrantes de un proyecto.
     * @param id identificador del proyecto.
     * @return retorna el número de integrantes de un proyecto.
     */
    public static Projects getNumMembers(int id) {
        try {
            Db db = new Db();
            ResultSet rs = db.NumIntegrantes(id);
            Projects h = new Projects();
            h.CountInte = rs.getInt(1);
            return h;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return null;
        }
    }

}
