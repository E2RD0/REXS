/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import rexsdesktop.model.Db;
import rexsdesktop.model.DbConnection;

/**
 *
 * @author Lulac
 */
public class Projects {

    private Connection cn;
    private List<JPanel> paneles;
    ImageIcon iconLocation = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconLocation.png"));
    ImageIcon iconRating = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconRating.png"));
    ImageIcon fotoProyecto = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/fotoProyecto.png"));
    private String nombre;
    private String descripcion;
    private Integer idSeccionNivel;

    public void CrearPanelesProyectos(javax.swing.JPanel panel) {
        Db db = new Db();
        db.Proyectos();
        db.NumProyectos();

        paneles = new ArrayList<>();
        for (int i = 0; i < db.getCantidadProyecto(); i++) {

            //Creando el primer JPanel principal 
            JPanel nuevo = new JPanel();
            panel.add(nuevo);
            paneles.add(nuevo);
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
            imagen.setIcon(fotoProyecto);
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
            nombre.setText("  " + db.nombre.get(i));

            info.add(nombre);
            //Creando el Label para el grado
            JLabel nivel = new JLabel();
            nivel.setFont(new java.awt.Font("Rubik", 0, 11));
            nivel.setForeground(new Color(136, 136, 136));
            //nivel.setBorder(hola);
            nivel.setText("   " + db.nivel.get(i) + " " + db.seccion.get(i));

            info.add(nivel);
            //Creando el Label seccion-nivel del proyecto
            JLabel seccion = new JLabel();
            seccion.setFont(new java.awt.Font("Rubik", 0, 11));
            seccion.setForeground(new Color(136, 136, 136));
            seccion.setText("   " + db.especialidad.get(i));
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
            ubi.setText(db.ubicacion.get(i).trim());
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

    public boolean agregarProyecto() {
        boolean respuesta = false;

        try {
            String sql = "INSERT INTO proyecto (nombre, descripcion, idSeccionNivel) " + "VALUES (?,?,?);";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, getNombre());
            stm.setString(2, getDescripcion());
            stm.setInt(3, getIdSeccionNivel() + 1);

            if (!stm.execute()) {
                respuesta = true;
            }
            stm.close();
            cn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the idSeccionNivel
     */
    public Integer getIdSeccionNivel() {
        return idSeccionNivel;
    }

    /**
     * @param idSeccionNivel the idSeccionNivel to set
     */
    public void setIdSeccionNivel(Integer idSeccionNivel) {
        this.idSeccionNivel = idSeccionNivel;
    }

 
}
