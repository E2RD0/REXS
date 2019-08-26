/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import rexsdesktop.model.Db;
import java.sql.ResultSet;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import rexsdesktop.modal.ModalModificarActividad;
import rexsdesktop.modal.ModalModificarUsuario;

/**
 * Clase que contiene los atributos y métodos de una actividad.
 *
 * @author Carlos Herrera
 * @version 1.2
 */
public class Activities {

    public int id;
    public String nombre;
    public String descripcion;
    public String fechaInicio;
    public String fechaFin;
    public int idUbicacion;
    private int cantidadDia1;

    Color colores = null;
    ImageIcon icono = null;
    JPanel Contenedor;

    ArrayList<JPanel> panelesActividades;
    ImageIcon iconEditCyan = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditCyan.png"));
    ImageIcon iconEditBlue = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditBLUE.png"));
    ImageIcon iconEditGreen = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditGreen.png"));
    ImageIcon iconEditOrange = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditOrange.png"));
    ImageIcon iconEditPurple = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditPurple.png"));

    /**
     * Método para visualizar los paneles con la información de la base de
     * datos.
     *
     * @param panel panel donde se visualizarán los datos
     */
    public void CrearPanelesActividades(javax.swing.JPanel panel, String inicio, String fin, int contador) {
        Db db = new Db();
        db.NumActividades(inicio, fin);
        db.Actividades(inicio, fin);

        cantidadDia1 = db.getCantidadActividades();

        panelesActividades = new ArrayList<>();

        for (int i = 0; i < db.getCantidadActividades(); i++) {
            Contenedor = new JPanel();
            panel.add(Contenedor);
            panelesActividades.add(Contenedor);

            Contenedor.setBackground(Color.white);
            Contenedor.setPreferredSize(new Dimension(150, 72));
            Contenedor.setLayout(null);
            switch (contador) {
                case 1:
                    colores = Color.CYAN;
                    break;

                case 2:
                    colores = Color.MAGENTA;
                    break;

                case 3:
                    colores = Color.BLUE;
                    break;

                case 4:
//                    colores = Color.getHSBColor(43f, 92f, 59f);
                    colores = Color.YELLOW;
                    break;

                case 5:
//                    colores = Color.getHSBColor(117f, 57f, 42f);
                    colores = Color.GREEN;
                    break;

                default:
                    colores = Color.BLACK;
                    break;

            }
            Border borde = new LineBorder(colores, 1, true);

            Contenedor.setBorder(borde);

            JLabel nombre = new JLabel();
            nombre.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            nombre.setForeground(new Color(46, 56, 77));
            nombre.setHorizontalAlignment(SwingConstants.LEADING);

            nombre.setBounds(15, 5, 140, 40);
            nombre.setText("<html>" + db.nombreAct.get(i) + "</html>");
            //nombre.setBorder(new EtchedBorder());
            String nombre2 = db.nombreAct.get(i);
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

            switch (contador) {
                case 1:
                    edit.setIcon(iconEditCyan);
                    break;

                case 2:
                    edit.setIcon(iconEditPurple);
                    break;

                case 3:
                    edit.setIcon(iconEditBlue);
                    break;

                case 4:
                    edit.setIcon(iconEditOrange);
                    break;

                case 5:
                    edit.setIcon(iconEditGreen);
                    break;

                default:
                    edit.setIcon(iconEditCyan);
                    break;

            }
            //edit.setBorder(new EtchedBorder());
            Contenedor.add(edit);

            Contenedor.addMouseListener(new MouseListener() {
                Frame fr;

                @Override
                public void mouseClicked(MouseEvent e) {
                    Contenedor = (JPanel) e.getSource();

                    //System.out.println(Contenedor1.getName());
                    ModalModificarActividad Modal = new ModalModificarActividad();
//                    Modal.jLabel70.setText(Contenedor.getName());
                    String nombreAc = "";
                    String descripcion = "";

                    Date fechaIni;
                    Date horaInicio;
                    Date horaFin;
                    String ubi = "";
                    //Consulta
                    Db db = new Db();
                    int id = db.getIdActividad(nombre2);
                    nombreAc = nombre2;
                    descripcion = db.getDescripcionActividad(id);

                    fechaIni = db.getFechaInicioActividad(id);
//                    horaInicio = db.getHoraInicioActividad(id);
                    horaFin = db.getHoraFinActividad(id);
                    
                    //Luego de consulta
                    ModalModificarActividad.txtNombreActividadModal.setText(nombreAc);
                    ModalModificarActividad.txtDescripcionModal.setText(descripcion);
                    ModalModificarActividad.dateFechaInicio.setDate(fechaIni);
                    ModalModificarActividad.cbxUbicacionModal.setSelectedItem(ubi);
                    Modal.id = id;

                    JDialog modal1 = new JDialog(fr, "Modificar Actividad", true);
                    modal1.getContentPane().add(Modal);
                    modal1.pack();
                    modal1.setLocationRelativeTo(null);
                    modal1.setVisible(true);
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

                @Override

                public void mouseExited(MouseEvent e) {

                }
            });
        }
        /*
        JLabel img2 = new JLabel();
            img2.setBounds(5, 5, 14, 20);
            img2.setIcon(iconLocation);
            //img2.setBorder(hola);
            info2.add(img2);
         */
    }

    /**
     * Método para registrar un usuario en el sistema
     *
     * @param nombre nombre de la actividad.
     * @param descripcion descripción de la actividad.
     * @param fechaInicio fecha inicio de la actividad.
     * @param fechaFin fecha fin de la actividad.
     * @param idUbicacion ubicación de la actividad.
     * @return retorna un valor booleano para ser utilizado en la capa modelo.
     */
    public static boolean nuevaActividad(String nombre, String descripcion, String fechaInicio, String fechaFin, int idUbicacion) {
        Db db = new Db();
        try {
            if (db.agregarActividad(nombre, descripcion, fechaInicio, fechaFin, idUbicacion)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("ERROR 2" + e);
        }
        return false;
    }
    
    public static boolean actualizarActividad(String nombre, String descripcion, String fechaInicio, String fechaFin, int idUbicacion, int id) {
        Db db = new Db();
        try {
            if (db.actualizarActividad(nombre, descripcion, fechaInicio, fechaFin, idUbicacion, id)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("ERROR 2" + e);
        }
        return false;
    }

    public static boolean ingresarFechas(String fecha1, String fecha2) {
        Db db = new Db();
        try {
            if (db.setFechas(fecha1, fecha2)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("ERROR 2" + e);
        }
        return false;
    }

    /**
     * @return the cantidadDia1
     */
    public int getCantidadDia1() {
        return cantidadDia1;
    }
}
