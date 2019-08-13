/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.controller;

import java.awt.Color;
import java.awt.Dimension;
import rexsdesktop.model.Db;
import java.sql.ResultSet;
import java.sql.Blob;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


/**
 *
 * @author Carlos Herrera
 */
public class Activities {
    public int id;
    public String nombre;
    public String descripcion;
    public String fechaInicio;
    public String fechaFin;
    public int idUbicacion;
    
    
    ArrayList<JPanel> panelesActividades;
    ImageIcon iconEditCyan = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditCyan.png"));
    
    public void CrearPanelesActividades(javax.swing.JPanel panel) {

        Db db = new Db();
        db.NumActividades();
        db.Actividades();
        
        panelesActividades = new ArrayList<>();

        for (int i = 0; i < db.getCantidadActividades(); i++) {
            JPanel Contenedor = new JPanel();
            panel.add(Contenedor);
            panelesActividades.add(Contenedor);

            Contenedor.setBackground(Color.white);
            Contenedor.setPreferredSize(new Dimension(150,72));
            Contenedor.setLayout(null);
            Border borde = new LineBorder(Color.CYAN,1, true);
            Contenedor.setBorder(borde);
            
            JLabel nombre = new JLabel();
            nombre.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            nombre.setForeground(new Color(46, 56, 77));
            nombre.setHorizontalAlignment(SwingConstants.LEADING);
            
            nombre.setBounds(15,5 , 140, 40);
            nombre.setText("<html>"+db.nombreAct.get(i)+"</html>");
            //nombre.setBorder(new EtchedBorder());
            Contenedor.add(nombre);
            
            JLabel hora = new JLabel();
            hora.setFont(new java.awt.Font("Rubik", 0, 11));
            hora.setForeground(new Color(135, 156, 173));
            hora.setHorizontalAlignment(SwingConstants.CENTER);
            hora.setBounds(10, 40, 110, 20);
            hora.setText("8:00 AM - 9:00 AM");
            //hora.setBorder(new EtchedBorder());
            Contenedor.add(hora);
            
            JLabel edit = new JLabel();
            edit.setBounds(125, 44, 20, 20);
            edit.setIcon(iconEditCyan);
            //edit.setBorder(new EtchedBorder());
            Contenedor.add(edit);
        }
        /*
        JLabel img2 = new JLabel();
            img2.setBounds(5, 5, 14, 20);
            img2.setIcon(iconLocation);
            //img2.setBorder(hola);
            info2.add(img2);
        */
    }
    
    
    public static boolean nuevaActividad(String nombre, String descripcion, String fechaInicio, String fechaFin, int idUbicacion){
        Db db = new Db();
        try {
            if (db.agregarActividad(nombre, descripcion, fechaInicio, fechaFin, idUbicacion)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("ERROR 2"+e);
        }
        return false;
    }
    
    
    
}
