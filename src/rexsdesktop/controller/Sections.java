/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import rexsdesktop.modal.ModalModificarEspecialidad;
import rexsdesktop.modal.ModalModificarNivel;
import rexsdesktop.modal.ModalModificarSecciones;
import rexsdesktop.modal.ModalSecciones;
import rexsdesktop.model.Db;
import rexsdesktop.model.DbConnection;

/**
 * Clase que contiene los atributos y métodos de una sección.
 *
 * @author artur
 */
public class Sections {

    //CRUD SECCION
    private static JDialog modal;
    private Connection cn;
    private String seccion;
    private int idSeccion;
    private int idNivel;
    private int idEspecialidad;
    private int idUbicacion;
    //AGREGAR NIVEL
    private String Nivel;
    //AGREGAR ESPECIALIDAD
    private String Especialidad;

    public Sections() {
        DbConnection clase1 = new DbConnection();
        cn = clase1.conectar();
    }

    JPanel Contenedor1;

    ArrayList<JPanel> panelesEspecialidades;
    ImageIcon iconEditCyan = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditCyan.png"));

    public void CrearPanelesEspecialidades(javax.swing.JPanel panel) {

        Db db = new Db();
        db.MostrarEspecialidad();
        db.NumEspecialidades();

        panelesEspecialidades = new ArrayList<>();

        for (int i = 0; i < db.getCantidadEspecialidad(); i++) {
            Contenedor1 = new JPanel();
            panel.add(Contenedor1);
            panelesEspecialidades.add(Contenedor1);
            Contenedor1.setName(String.valueOf(db.getIdEspecialidad().get(i)));
            Contenedor1.setBackground(Color.white);
            Contenedor1.setPreferredSize(new Dimension(300, 52));
            Contenedor1.setLayout(null);
            Border borde = new LineBorder(Color.CYAN, 1, true);
            Contenedor1.setBorder(borde);

            JLabel id = new JLabel();
            id.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            id.setForeground(new Color(46, 56, 77));
            id.setHorizontalAlignment(SwingConstants.LEADING);
            id.setBounds(20, 12, 20, 20);
            //id.setBorder(new EtchedBorder());
            id.setText("<html>" + db.getIdEspecialidad().get(i) + "</html>");
            //id.setBorder(new EtchedBorder());
            Contenedor1.add(id);

            JLabel Especialidades = new JLabel();
            Especialidades.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            Especialidades.setForeground(new Color(46, 56, 77));
            Especialidades.setHorizontalAlignment(SwingConstants.CENTER);
            Especialidades.setBounds(220, 11, 150, 20);
            //Especialidades.setBorder(new EtchedBorder());
            Especialidades.setText(db.getEspecialidad().get(i));
            // email.setBorder(new EtchedBorder());
            Contenedor1.add(Especialidades);

            Contenedor1.addMouseListener(new MouseListener() {
                Frame fr;

                @Override
                public void mouseClicked(MouseEvent e) {
                    Contenedor1 = (JPanel) e.getSource();

                    System.out.println(Contenedor1.getName());
                    ModalModificarEspecialidad Modal = new ModalModificarEspecialidad();
                    Modal.jLabel70.setText(Contenedor1.getName());
                    String nombreEspecialidad = "";
                    //Consulta
                    Db db = new Db();
                    nombreEspecialidad = db.getNombreEspecialidad(Integer.parseInt(Contenedor1.getName()));
                    Modal.txtEspecialidadModal.setText(nombreEspecialidad);

                    JDialog modal1 = new JDialog(fr, "Modificar Especialidad", true);
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
    }

   JPanel ContenedorNivel;

    ArrayList<JPanel> panelesNivel;
    //ImageIcon iconEditCyan = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditCyan.png"));

    public void CrearPanelesNivel(javax.swing.JPanel panel) {

        Db db = new Db();
        db.MostrarNivel();
        db.NumNivel();

        panelesNivel = new ArrayList<>();

        for (int i = 0; i < db.getCantidadNiveles(); i++) {
            ContenedorNivel = new JPanel();
            panel.add(ContenedorNivel);
            panelesNivel.add(ContenedorNivel);
            ContenedorNivel.setName(String.valueOf(db.getIdNivel().get(i)));
            ContenedorNivel.setBackground(Color.white);
            ContenedorNivel.setPreferredSize(new Dimension(300, 52));
            ContenedorNivel.setLayout(null);
            Border borde = new LineBorder(Color.CYAN, 1, true);
            ContenedorNivel.setBorder(borde);

            JLabel id = new JLabel();
            id.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            id.setForeground(new Color(46, 56, 77));
            id.setHorizontalAlignment(SwingConstants.LEADING);
            id.setBounds(20, 12, 20, 20);
            //id.setBorder(new EtchedBorder());
            id.setText("<html>" + db.getIdNivel().get(i) + "</html>");
            //id.setBorder(new EtchedBorder());
            ContenedorNivel.add(id);

            JLabel Nivel = new JLabel();
            Nivel.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            Nivel.setForeground(new Color(46, 56, 77));
            Nivel.setHorizontalAlignment(SwingConstants.CENTER);
            Nivel.setBounds(220, 11, 150, 20);
            //Especialidades.setBorder(new EtchedBorder());
            Nivel.setText(db.getNivel().get(i));
            // email.setBorder(new EtchedBorder());
            ContenedorNivel.add(Nivel);

            ContenedorNivel.addMouseListener(new MouseListener() {
                Frame fr;

                @Override
                public void mouseClicked(MouseEvent e) {

                    ContenedorNivel = (JPanel) e.getSource();

                    System.out.println(ContenedorNivel.getName());
                    ModalSecciones Modal = new ModalSecciones();
                    Modal.jLId.setText(ContenedorNivel.getName());

                    Sections CargarSecciones = new Sections();
                    CargarSecciones.CrearPanelesSecciones(ModalSecciones.jPanelSecciones, Integer.parseInt(ModalSecciones.jLId.getText()));

                    JDialog modal1 = new JDialog(fr, "Modificar Nivel", true);
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

            ContenedorNivel.addMouseMotionListener(new MouseAdapter() {
                Frame fr;

                @Override
                public void mouseDragged(MouseEvent evento) {
                    ContenedorNivel = (JPanel) evento.getSource();

                    System.out.println(ContenedorNivel.getName());
                    ModalModificarNivel Modal = new ModalModificarNivel();

                    Modal.jLidNivel.setText(ContenedorNivel.getName());
                    System.out.println("Prueba =" + ContenedorNivel.getName());
                    String nombreNivel = "";
                    //Consulta
                    Db db = new Db();
                    nombreNivel = db.getNombreNivel(Integer.parseInt(ContenedorNivel.getName()));
                    Modal.txtNivelModal.setText(nombreNivel);

                    JDialog modal1 = new JDialog(fr, "Modificar Nivel", true);
                    modal1.getContentPane().add(Modal);
                    modal1.pack();
                    modal1.setLocationRelativeTo(null);
                    modal1.setVisible(true);

                }
            });
        }
    }

    public Integer NumSecciones;
    JPanel ContenedorSecciones;

    ArrayList<JPanel> panelesSecciones;
    //ImageIcon iconEditCyan = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditCyan.png"));

    public void CrearPanelesSecciones(javax.swing.JPanel panel, int idNiv) {

        Db db = new Db();
        Sections u = getNumSecciones(idNiv);
        db.MostrarSeccion(idNiv);

        panelesSecciones = new ArrayList<>();

        for (int i = 0; i < u.NumSecciones; i++) {
            ContenedorSecciones = new JPanel();
            panel.add(ContenedorSecciones);
            panelesSecciones.add(ContenedorSecciones);
            ContenedorSecciones.setName(String.valueOf(db.getIdSeccion().get(i)));
            ContenedorSecciones.setBackground(Color.white);
            ContenedorSecciones.setPreferredSize(new Dimension(576, 52));
            ContenedorSecciones.setLayout(null);
            Border borde = new LineBorder(Color.CYAN, 1, true);
            ContenedorSecciones.setBorder(borde);

            JLabel id = new JLabel();
            id.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            id.setForeground(new Color(10, 56, 77));
            id.setHorizontalAlignment(SwingConstants.LEADING);
            id.setBounds(20, 15, 20, 20);
            //id.setBorder(new EtchedBorder());
            id.setText("<html>" + db.getIdSeccion().get(i) + "</html>");
            //id.setBorder(new EtchedBorder());
            ContenedorSecciones.add(id);

            JLabel Seccion = new JLabel();
            Seccion.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            Seccion.setForeground(new Color(46, 56, 77));
            Seccion.setHorizontalAlignment(SwingConstants.CENTER);
            Seccion.setBounds(40, 15, 110, 20);
            //Especialidades.setBorder(new EtchedBorder());
            Seccion.setText(db.getSeccion().get(i));
            // email.setBorder(new EtchedBorder());
            ContenedorSecciones.add(Seccion);

            JLabel Nivel = new JLabel();
            Nivel.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            Nivel.setForeground(new Color(46, 56, 77));
            Nivel.setHorizontalAlignment(SwingConstants.CENTER);
            Nivel.setBounds(150, 15, 150, 20);
            //Especialidades.setBorder(new EtchedBorder());
            Nivel.setText(db.getNivel_Seccion().get(i));
            // email.setBorder(new EtchedBorder());
            ContenedorSecciones.add(Nivel);

            JLabel Especialidad = new JLabel();
            Especialidad.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            Especialidad.setForeground(new Color(46, 56, 77));
            Especialidad.setHorizontalAlignment(SwingConstants.CENTER);
            Especialidad.setBounds(320, 15, 70, 20);
            //Especialidades.setBorder(new EtchedBorder());
            Especialidad.setText(db.getEspecialidad_Seccion().get(i));
            // email.setBorder(new EtchedBorder());
            ContenedorSecciones.add(Especialidad);

            JLabel Ubicacion = new JLabel();
            Ubicacion.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            Ubicacion.setForeground(new Color(46, 56, 77));
            Ubicacion.setHorizontalAlignment(SwingConstants.CENTER);
            Ubicacion.setBounds(450, 15, 150, 20);
            //Especialidades.setBorder(new EtchedBorder());
            Ubicacion.setText(Locations.getPlaceName(db.getUbicacion_Seccion().get(i)));
            // email.setBorder(new EtchedBorder());
            ContenedorSecciones.add(Ubicacion);

            ContenedorSecciones.addMouseListener(new MouseListener() {
                Frame fr;

                @Override
                public void mouseClicked(MouseEvent e) {

                    ContenedorSecciones = (JPanel) e.getSource();

                    System.out.println(ContenedorSecciones.getName());
                    ModalModificarSecciones Modal = new ModalModificarSecciones();
                    Modal.jLId.setText(ContenedorSecciones.getName());
                    String nombreSeccion = "";
                    String Nivel;
                    String Especialidad;
                    String Ubicacion;
                    //Consulta
                    Db db = new Db();
                    nombreSeccion = db.getNombreSeccion(Integer.parseInt(ContenedorSecciones.getName()));
                    Nivel = db.getNivel_Seccion(Integer.parseInt(ContenedorSecciones.getName()));
                    Especialidad = db.getEspecialidad_Seccion(Integer.parseInt(ContenedorSecciones.getName()));
                    Ubicacion = Locations.getPlaceName(db.getUbicacion_Seccion(Integer.parseInt(ContenedorSecciones.getName())));
                    Modal.txtSeccionModal.setText(nombreSeccion);
                    Modal.cbxNivelModal.setSelectedItem(Nivel);
                    Modal.cbxEspecialidadModal.setSelectedItem(Especialidad);
                    Modal.cbxUbicacionModal.setSelectedItem(Ubicacion);

                    JDialog modal1 = new JDialog(fr, "Modificar Seccion", true);
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
    }

    public ResultSet consulta(String sql) {
        ResultSet res = null;
        try {
            PreparedStatement pstm = getCn().prepareStatement(sql);
            res = pstm.executeQuery();
        } catch (Exception e) {
        }
        return res;
    }

    public DefaultComboBoxModel obtenerNivel() {
        DefaultComboBoxModel listaModelo = new DefaultComboBoxModel();
        ResultSet rst = this.consulta("Select * from nivel order by idNivel");
        try {
            while (rst.next()) {
                listaModelo.addElement(rst.getString("nivel"));
            }
            rst.close();
        } catch (Exception e) {
        }
        return listaModelo;
    }

    public DefaultComboBoxModel obtenerEspecialidad() {
        DefaultComboBoxModel listaModelo = new DefaultComboBoxModel();
        ResultSet rst = this.consulta("Select * from especialidad order by idEspecialidad");
        try {
            while (rst.next()) {
                listaModelo.addElement(rst.getString("especialidad"));
            }
            rst.close();
        } catch (Exception e) {
        }
        return listaModelo;
    }

    public DefaultComboBoxModel obtenerUbicacion() {
        DefaultComboBoxModel listaModelo = new DefaultComboBoxModel();
        ResultSet rst = this.consulta("Select * from ubicacion order by idUbicacion");
        try {
            while (rst.next()) {
                listaModelo.addElement(rst.getString("ubicacion"));
            }
            rst.close();
        } catch (Exception e) {
        }
        return listaModelo;
    }

    //CRUD AGREGAR SECCION
    public boolean agregarSeccion() {
        boolean respuesta = false;

        try {
            String sql = "INSERT INTO seccionNivel (seccion, idNivel, idEspecialidad, idUbicacion) " + "VALUES (?,?,?,?);";
            PreparedStatement stm = cn.prepareStatement(sql);

            stm.setString(1, getSeccion());
            stm.setInt(2, getIdNivel() + 1);
            stm.setInt(3, getIdEspecialidad() + 1);
            stm.setInt(4, getIdUbicacion() + 1);

            if (!stm.execute()) {
                respuesta = true;
            }
            stm.close();
//            getCn().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    public boolean ActualizarSeccion() {
        boolean respuesta = false;
        try {
            String sql = "UPDATE seccionNivel SET seccion= ?, idNivel = ?, idEspecialidad = ?, idUbicacion = ? WHERE idSeccionNivel = ?;";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, seccion);
            stm.setInt(2, idNivel + 1);
            stm.setInt(3, idEspecialidad + 1);
            stm.setInt(4, idUbicacion + 1);
            stm.setInt(5, getIdSeccion());

            if (!stm.execute()) {
                respuesta = true;
            }
            stm.close();
            cn.close();
        } catch (Exception e) {
        }
        return respuesta;
    }

    public boolean ElminarSeccion() {
        boolean respuesta = false;
        try {
            String sql = "DELETE FROM seccionNivel WHERE idSeccionNivel = ?;";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, getIdSeccion());
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

    //CRUD NIVEL
    public boolean agregarNivel() {
        boolean respuesta = false;

        try {
            String sql = "INSERT INTO nivel (nivel) " + "VALUES (?);";
            PreparedStatement stm = getCn().prepareStatement(sql);

            stm.setString(1, getNivel());

            if (!stm.execute()) {
                respuesta = true;
            }
            stm.close();
            getCn().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    public boolean ActualizarNivel() {
        boolean respuesta = false;
        try {
            String sql = "UPDATE nivel SET nivel= ? WHERE idNivel = ?;";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, Nivel);
            stm.setInt(2, getIdNivel());

            if (!stm.execute()) {
                respuesta = true;
            }
            stm.close();
            cn.close();
        } catch (Exception e) {
        }
        return respuesta;
    }
    //CRUD ESPECIALIDAD

    public boolean agregarEspecialidad() {
        boolean respuesta = false;

        try {
            String sql = "INSERT INTO especialidad (especialidad) " + "VALUES (?);";
            PreparedStatement stm = getCn().prepareStatement(sql);

            stm.setString(1, getEspecialidad());

            if (!stm.execute()) {
                respuesta = true;
            }
            stm.close();
            getCn().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    public boolean ActualizarEspecialidad() {
        boolean respuesta = false;
        try {
            String sql = "UPDATE especialidad SET especialidad= ? WHERE idEspecialidad = ?;";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, Especialidad);
            stm.setInt(2, getIdEspecialidad());

            if (!stm.execute()) {
                respuesta = true;
            }
            stm.close();
            cn.close();
        } catch (Exception e) {
        }
        return respuesta;
    }

    public boolean ElminarEspecialidad() {
        boolean respuesta = false;
        try {
            String sql = "DELETE FROM especialidad WHERE idEspecialidad = ?;";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, getIdEspecialidad());
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
     * @return the seccion
     */
    public String getSeccion() {
        return seccion;
    }

    /**
     * @param seccion the seccion to set
     */
    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    /**
     * @return the idNivel
     */
    public int getIdNivel() {
        return idNivel;
    }

    /**
     * @param idNivel the idNivel to set
     */
    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }

    /**
     * @return the idEspecialidad
     */
    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    /**
     * @param idEspecialidad the idEspecialidad to set
     */
    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    /**
     * @return the idUbicacion
     */
    public int getIdUbicacion() {
        return idUbicacion;
    }

    /**
     * @param idUbicacion the idUbicacion to set
     */
    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    /**
     * @return the cn
     */
    public Connection getCn() {
        return cn;
    }

    /**
     * @param cn the cn to set
     */
    public void setCn(Connection cn) {
        this.cn = cn;
    }

    /**
     * @return the Nivel
     */
    public String getNivel() {
        return Nivel;
    }

    /**
     * @param Nivel the Nivel to set
     */
    public void setNivel(String Nivel) {
        this.Nivel = Nivel;
    }

    /**
     * @return the Especialidad
     */
    public String getEspecialidad() {
        return Especialidad;
    }

    /**
     * @param Especialidad the Especialidad to set
     */
    public void setEspecialidad(String Especialidad) {
        this.Especialidad = Especialidad;
    }

    /**
     * @return the idSeccion
     */
    public int getIdSeccion() {
        return idSeccion;
    }

    /**
     * @param idSeccion the idSeccion to set
     */
    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    public static Sections getNumSecciones(int idNiv) {
        try {
            Db db = new Db();
            ResultSet rs = db.NumSeccion(idNiv);
            Sections h = new Sections();
            h.NumSecciones = rs.getInt(1);
            return h;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return null;
        }

    }
}
