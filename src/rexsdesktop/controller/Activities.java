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
import java.text.ParseException;
//import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import rexsdesktop.modal.ModalModificarActividad;

/**
 * Clase que contiene los atributos y métodos de una actividad.
 *
 * @author Carlos Herrera
 * @version 1.2
 */
public class Activities {

    public static String Inicio;
    private static int id2;
    private String nombre;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;
    private int idUbicacion;
    private int cantidadDia1;
    private JDialog modal1;
    public String prueba;

    private Color colores = null;
    private ImageIcon icono = null;
    private JPanel Contenedor;

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
    public void CrearPanelesActividades(javax.swing.JPanel panel, String inicio, String edicion, String fin, int contador) {
        Db db = new Db();
        db.NumActividades(inicio, edicion, fin);
        db.Actividades(inicio, edicion, fin);
        db.HorasInicioActividades(inicio, edicion, fin);
        db.HorasFinActividades(inicio, edicion, fin);
        cantidadDia1 = db.getCantidadActividades();

        panelesActividades = new ArrayList<>();

        for (int i = 0; i < db.getCantidadActividades(); i++) {

            Contenedor = new JPanel();
            panel.add(Contenedor);
            panelesActividades.add(Contenedor);

            Contenedor.setBackground(Color.white);
            Contenedor.setPreferredSize(new Dimension(150, 76));
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
            nombre.setFont(new java.awt.Font("Rubik", 0, 12));
            nombre.setForeground(new Color(46, 56, 77));
            nombre.setHorizontalAlignment(SwingConstants.LEADING);

            nombre.setBounds(15, 5, 140, 25);
            nombre.setText("<html> <b>" + db.getNombreAct().get(i) + "</html>");
            //nombre.setBorder(new EtchedBorder());
            String nombre2 = db.getNombreAct().get(i);
            Contenedor.add(nombre);

            //Hora
            JLabel hora = new JLabel();
            hora.setFont(new java.awt.Font("Rubik", 0, 10));
            hora.setForeground(new Color(135, 156, 173));
            hora.setHorizontalAlignment(SwingConstants.CENTER);
            hora.setBounds(10, 30, 110, 20);

            SimpleDateFormat formato = new SimpleDateFormat("hh:mm a");
            String formato2 = formato.format(db.getHoraInicio().get(i));
            String formato3 = formato.format(db.getHoraFin().get(i));

            hora.setText("<html>" + formato2 + "-" + formato3 + "</html>");
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

                    String nombreAc;
                    String descripcion;
                    Date fechaIni;
                    String ubi = "";
                    String horaInicio, horaFin;
                    String h;

                    //System.out.println(Contenedor1.getName());
                    ModalModificarActividad Modal = new ModalModificarActividad();
//                    Modal.jLabel70.setText(Contenedor.getName());

                    //Consulta
                    int id = (db.getIdActividad(nombre2));
                    nombreAc = nombre2;
                    descripcion = db.getDescripcionActividad(id);
                    fechaIni = db.getFechaInicioActividad(id);
                    horaInicio = db.getHoraInicio(id);
                    horaFin = db.getHoraFinString(id);
                    int ide = cambiarID(id);
//                    System.out.println("Activites ="+horaInicio);

                    //Luego de consulta
                    ModalModificarActividad.txtNombreActividadModal.setText(nombreAc);
                    ModalModificarActividad.txtDescripcionModal.setText(descripcion);
                    ModalModificarActividad.dateFechaInicio.setDate(fechaIni);
                    ModalModificarActividad.cbxUbicacionModal.setSelectedItem(ubi);
                    Modal.id = ide;

                    //Fecha
                    SimpleDateFormat sdf = (SimpleDateFormat) SimpleDateFormat.getDateTimeInstance();
                    sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
                    Date d = null;
                    Date d2 = null;
                    try {
                        d = sdf.parse(horaInicio);
                        d2 = sdf.parse(horaFin);
                    } catch (ParseException ex) {
                        System.out.println(ex.getMessage());
                    }

                    Modal.modelInicio.setValue(d);
                    ModalModificarActividad.spHoraInicio.setModel(Modal.modelInicio);
                    ModalModificarActividad.spHoraInicio.setEditor(new JSpinner.DateEditor(ModalModificarActividad.spHoraInicio, "h:mma"));

                    Modal.modelFin.setValue(d2);
                    ModalModificarActividad.spHoraFin.setModel(Modal.modelFin);
                    ModalModificarActividad.spHoraFin.setEditor(new JSpinner.DateEditor(ModalModificarActividad.spHoraFin, "h:mma"));

                    ModalModificarActividad.txtPrueba.setText(String.valueOf(ide));

                    modal1 = new JDialog(fr, "Modificar Actividad", true);
                    modal1.getContentPane().add(Modal);
                    modal1.pack();
                    modal1.invalidate();
                    modal1.validate();
                    modal1.repaint();
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

    public int cambiarID(int id) {
        try {
            id2 = id;
            return id2;
        } catch (Exception e) {
        }
        return 0;
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
    public static boolean nuevaActividad(String nombre, String descripcion, String fechaInicio, String edicion, String fechaFin, int idUbicacion) {
        Db db = new Db();
        try {
            if (db.agregarActividad(nombre, descripcion, fechaInicio, edicion, fechaFin, idUbicacion)) {
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

    public static boolean eliminarActividad(int id) {
        Db db = new Db();
        try {
            if (db.eliminarActividad(id)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }
        return false;
    }

    public static boolean setFechas(String fecha1, String dia2, String dia3, String dia4, String fecha2) {
        Db db = new Db();
        try {
            if (db.setFechas(fecha1, dia2, dia3, dia4, fecha2)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }
        return false;
    }

    public String getDia(String dia) {
        Db db = new Db();
        try {
            String fecha = db.getFechaDia(dia);
            return fecha;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Vacio";
    }

    public String getNombreDia(String dia) {
        Db db = new Db();
        try {
            String fecha = db.getNombreDia(dia);
            return fecha;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Vacio";
    }

    public static String getMinAnioInicio() {
        Db db = new Db();
        try {
            String fecha = db.getMinAnioInicio();
            return fecha;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Vacio";
    }

    public static String getMinMesInicio() {
        Db db = new Db();
        try {
            String fecha = db.getMinMesInicio();
            return fecha;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Vacio";
    }
    
    public static String getMinDiaInicio() {
        Db db = new Db();
        try {
            String fecha = db.getMinDiaInicio();
            return fecha;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Vacio";
    }
    
    public static String getMaxMesInicio() {
        Db db = new Db();
        try {
            String fecha = db.getMaxMesInicio();
            return fecha;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Vacio";
    }
    
    public static String getMaxDiaInicio() {
        Db db = new Db();
        try {
            String fecha = db.getMaxDiaInicio();
            return fecha;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Vacio";
    }

    public boolean eliminarActividades() {
        Db db = new Db();
        boolean bandera = false;
        try {
            bandera = db.eliminarActividades();
            return bandera;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return bandera;
    }

    /**
     * @return the cantidadDia1
     */
    public int getCantidadDia1() {
        return cantidadDia1;
    }

    /**
     * @return the id2
     */
    public static int getId2() {
        return id2;
    }

    /**
     * @param aId2 the id2 to set
     */
    public static void setId2(int aId2) {
        id2 = aId2;
    }
}
