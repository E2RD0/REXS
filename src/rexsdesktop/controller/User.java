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
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import rexsdesktop.model.Db;
import java.sql.ResultSet;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javax.imageio.ImageIO;
import rexsdesktop.CurrentUser;
import java.util.UUID;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import rexsdesktop.modal.ModalModificarUsuario;
import rexsdesktop.model.DbConnection;

/**
 * Clase que contiene los atributos y métodos de un usuario.
 *
 * @author Arturo
 * @version 1.2
 */
public class User {

    public int idUsuario;
    public String nombreCompleto;
    public String email;
    public String hash;
    public BufferedImage fotoPerfil;
    public int idTipoUsuario;
    public int idEstadoUsuario;
    //Arturo
    private Connection cn;
    private String Clave;
    //AGREGAR ESTADO USUARIO
    private String estadoUsuario;
    //MODIFICAR ESTADO USUARIO
    public Integer NumUserFiltrados;
    //MODRIFICAR TIPO USUARIO0
    private String tipoUsuario;
    //Mo
    //Arturo
    public static String mensajeError = "";

    //Arturo
    public User() {
        DbConnection clase1 = new DbConnection();
        cn = clase1.conectar();
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

    public DefaultComboBoxModel obtenerTipoUsuarioSuperAdministrador() {
        DefaultComboBoxModel listaModelo = new DefaultComboBoxModel();
        ResultSet rst = this.consulta("select * from tipoUsuario order by idTipoUsuario");
        try {
            while (rst.next()) {
                listaModelo.addElement(rst.getString("tipo"));
            }
            rst.close();
        } catch (Exception e) {
        }
        return listaModelo;
    }

    public DefaultComboBoxModel obtenerTipoUsuarioAdministrador() {
        DefaultComboBoxModel listaModelo = new DefaultComboBoxModel();
        ResultSet rst = this.consulta("select * from tipoUsuario where idTipoUsuario != " + 1 + " order by idTipoUsuario");
        try {
            while (rst.next()) {
                listaModelo.addElement(rst.getString("tipo"));
            }
            rst.close();
        } catch (Exception e) {
        }
        return listaModelo;
    }

    public DefaultComboBoxModel obtenerEstadoUsuario() {
        DefaultComboBoxModel listaModelo = new DefaultComboBoxModel();
        ResultSet rst = this.consulta("Select * from estadoUsuario order by idEstadoUsuario");
        try {
            while (rst.next()) {
                listaModelo.addElement(rst.getString("estado"));
            }
            rst.close();
        } catch (Exception e) {
        }
        return listaModelo;
    }
    //FILTRAR USUARIOS
    JPanel ContenedorFiltrarUsuarios;

    ArrayList<JPanel> panelFiltrarUsuarios;
    ImageIcon iconEditCyan = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditCyan.png"));

    public void CrearPanelesUsuariosFiltrados(javax.swing.JPanel panel, String nombre, String idE, String idT) {

        Db db = new Db();
        User u = getNumUserFiltrados(nombre, idE, idT);
        db.MostrarUsuariosFiltrados(nombre, idE, idT);

        JLabel nombreuser = null;
        panelFiltrarUsuarios = new ArrayList<>();

        for (int i = 0; i < u.NumUserFiltrados; i++) {
            ContenedorFiltrarUsuarios = new JPanel();
            panel.add(ContenedorFiltrarUsuarios);
            panelFiltrarUsuarios.add(ContenedorFiltrarUsuarios);
            ContenedorFiltrarUsuarios.setName(String.valueOf(db.idUsuario2.get(i)));
            ContenedorFiltrarUsuarios.setBackground(Color.white);
            ContenedorFiltrarUsuarios.setPreferredSize(new Dimension(576, 52));
            ContenedorFiltrarUsuarios.setLayout(null);
            Border borde = new LineBorder(Color.CYAN, 1, true);
            ContenedorFiltrarUsuarios.setBorder(borde);

            JLabel id = new JLabel();
            id.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            id.setForeground(new Color(46, 56, 77));
            id.setHorizontalAlignment(SwingConstants.LEADING);
            id.setBounds(20, 15, 20, 20);
            id.setText("<html>" + db.idUsuario2.get(i) + "</html>");
            //id.setBorder(new EtchedBorder());
            ContenedorFiltrarUsuarios.add(id);

            nombreuser = new JLabel();
            nombreuser.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            nombreuser.setForeground(new Color(46, 56, 77));
            nombreuser.setHorizontalAlignment(SwingConstants.CENTER);
            nombreuser.setBounds(40, 15, 110, 20);
            nombreuser.setText(db.nombreCompleto2.get(i));
            //nombreuser.setBorder(new EtchedBorder());
            ContenedorFiltrarUsuarios.add(nombreuser);

            JLabel email = new JLabel();
            email.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            email.setForeground(new Color(46, 56, 77));
            email.setHorizontalAlignment(SwingConstants.CENTER);
            email.setBounds(150, 15, 150, 20);
            email.setText(db.email2.get(i));
            // email.setBorder(new EtchedBorder());
            ContenedorFiltrarUsuarios.add(email);

            JLabel fecha = new JLabel();
            fecha.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            fecha.setForeground(new Color(46, 56, 77));
            fecha.setHorizontalAlignment(SwingConstants.CENTER);
            fecha.setBounds(320, 15, 70, 20);
            fecha.setText(db.fecha2.get(i).trim());
            //fecha.setBorder(new EtchedBorder());
            ContenedorFiltrarUsuarios.add(fecha);

            JLabel tipo = new JLabel();
            tipo.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            tipo.setForeground(new Color(46, 56, 77));
            tipo.setHorizontalAlignment(SwingConstants.CENTER);
            tipo.setBounds(400, 15, 70, 20);
            tipo.setText(db.tipo2.get(i));
            // tipo.setBorder(new EtchedBorder());
            ContenedorFiltrarUsuarios.add(tipo);

            JLabel estado = new JLabel();
            estado.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            estado.setForeground(new Color(46, 56, 77));
            estado.setHorizontalAlignment(SwingConstants.CENTER);
            estado.setBounds(490, 15, 70, 20);
            estado.setText(db.estado2.get(i));
            // estado.setBorder(new EtchedBorder());
            ContenedorFiltrarUsuarios.add(estado);

            ContenedorFiltrarUsuarios.addMouseListener(new MouseListener() {
                Frame fr;

                @Override
                public void mouseClicked(MouseEvent e) {
                    ContenedorFiltrarUsuarios = (JPanel) e.getSource();

                    //System.out.println(Contenedor1.getName());
                    ModalModificarUsuario Modal = new ModalModificarUsuario();
                    Modal.jLabel70.setText(ContenedorFiltrarUsuarios.getName());
                    String nombreCo = "";
                    String correo = "";
                    String clave = "";
                    String tipoUsuario;
                    String estadoUsuario;
                    //Consulta
                    Db db = new Db();
                    correo = db.getCorreoUsuario(Integer.parseInt(ContenedorFiltrarUsuarios.getName()));
                    nombreCo = db.getNombreUsuario(Integer.parseInt(ContenedorFiltrarUsuarios.getName()));
                    tipoUsuario = db.getTipoUsuario(Integer.parseInt(ContenedorFiltrarUsuarios.getName()));
                    estadoUsuario = db.getEstadoUsuario(Integer.parseInt(ContenedorFiltrarUsuarios.getName()));
                    Modal.txtNombreUsuarioModal.setText(nombreCo);
                    Modal.txtEmailUsuarioModal.setText(correo);
                    Modal.txtClaveUsuarioModal.setText(clave);
                    Modal.cbxTipoUsuarioModal.setSelectedItem(tipoUsuario);
                    Modal.cbxEstadoUsuarioModal.setSelectedItem(estadoUsuario);
                    JDialog modal1 = new JDialog(fr, "Modificar Usuario", true);
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
    //CRUD USUARIO    

    public boolean agregarUsuario() {
        boolean respuesta = false;

        try {
            String sql = "INSERT INTO usuario (nombreCompleto,email,clave,idTipoUsuario,idEstadoUsuario) " + "VALUES (?,?,?,?,?);";
            PreparedStatement stm = getCn().prepareStatement(sql);
            String Encriptado = hashPW(getClave());
            stm.setString(1, getNombreCompleto());
            stm.setString(2, getEmail());
            stm.setString(3, Encriptado);
            stm.setInt(4, getIdTipoUsuario());
            stm.setInt(5, getIdEstadoUsuario());

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

    public boolean agregarUsuarioPoint() {
        boolean respuesta = false;

        try {
            String sql = "INSERT INTO usuario (nombreCompleto,email,clave,idTipoUsuario,idEstadoUsuario) " + "VALUES (?,?,?,?,?);";
            try (PreparedStatement stm = getCn().prepareStatement(sql)) {
                String Encriptado = hashPW(getClave());
                stm.setString(1, getNombreCompleto());
                stm.setString(2, getEmail());
                stm.setString(3, Encriptado);
                stm.setInt(4, getIdTipoUsuario());
                stm.setInt(5, getIdEstadoUsuario());

                if (!stm.execute()) {
                    respuesta = true;
                }
            }
            getCn().close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    public boolean ActualizarUsuario() {
        boolean respuesta = false;
        try {
            String sql = "UPDATE usuario SET nombreCompleto= ?, email = ?, clave = ?, idTipoUsuario = ?, idEstadoUsuario = ? WHERE idUsuario = ?;";
            PreparedStatement stm = cn.prepareStatement(sql);
            String Encriptado = hashPW(getClave());
            stm.setString(1, nombreCompleto);
            stm.setString(2, email);
            stm.setString(3, Encriptado);
            stm.setInt(4, idTipoUsuario);
            stm.setInt(5, idEstadoUsuario);
            stm.setInt(6, getIdUsuario());

            if (!stm.execute()) {
                respuesta = true;
            }
            stm.close();
            cn.close();
        } catch (Exception e) {
        }
        return respuesta;
    }

    public boolean agregarEstadoUsuario() {
        boolean respuesta = false;

        try {
            String sql = "INSERT INTO estadoUsuario (estado) " + "VALUES (?);";
            PreparedStatement stm = getCn().prepareStatement(sql);

            stm.setString(1, getEstadoUsuario());

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

    public boolean ActualizarEstadoUsuario() {
        boolean respuesta = false;
        try {
            String sql = "UPDATE estadoUsuario SET estado = ? WHERE idEstadoUsuario = ?;";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, estadoUsuario);
            stm.setInt(2, getIdEstadoUsuario());

            if (!stm.execute()) {
                respuesta = true;
            }
            stm.close();
            cn.close();
        } catch (Exception e) {
        }
        return respuesta;
    }

    //CRUD TIPO USUARIO
    public boolean agregarTipoUsuario() {
        boolean respuesta = false;

        try {
            String sql = "INSERT INTO tipoUsuario (tipo) " + "VALUES (?);";
            PreparedStatement stm = getCn().prepareStatement(sql);

            stm.setString(1, getTipoUsuario());

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

    public boolean ActualizarTipoUsuario() {
        boolean respuesta = false;
        try {
            String sql = "UPDATE tipoUsuario SET tipo= ? WHERE idTipoUsuario = ?;";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, tipoUsuario);
            stm.setInt(2, getIdTipoUsuario());

            if (!stm.execute()) {
                respuesta = true;
            }
            stm.close();
            cn.close();
        } catch (Exception e) {
        }
        return respuesta;
    }

    public boolean ElminarTipoUsuario() {
        boolean respuesta = false;
        try {
            String sql = "DELETE FROM tipoUsuario WHERE idTipoUsuario = ?;";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, getIdTipoUsuario());
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

    //Arturo
    public static boolean nuevoUsuario(String nombreCompleto, String email, String clave, String tipoUsuario, String estadoUsuario) {
        Db db = new Db();
        if (!db.usuarioExiste(email)) {
            String hash = hashPW(clave);
            int idTipoUsuario = db.getIdTipoUsuario(tipoUsuario);
            int idEstadoUsuario = db.getIdEstadoUsuario(estadoUsuario);
            if (db.agregarUsuario(nombreCompleto, email, hash, idTipoUsuario, idEstadoUsuario)) {
                mensajeError = "";
                return true;
            } else {
                mensajeError = "Hubo un error al registrar el usuario. Intenta de nuevo.";
                return false;
            }
        } else {
            mensajeError = "<html>Ya existe un usuario con la dirección de<br>correo electrónico.</html>";
            return false;
        }
    }

    public static boolean enviarCorreo(String destino) {
        try {
            Db db = new Db();
            int idUsuario = db.getIdUsuario(destino);
            if (idUsuario != 0) {
                String pin = UUID.randomUUID().toString().toUpperCase().substring(0, 5);
                if (db.insertarPin(pin, idUsuario)) {
                    String remitente = "rexsDesktop@gmail.com";
                    String clave = "@Rexsdesktop123";

                    Properties props = new Properties();

                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.port", "587");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.user", remitente);
                    props.put("mail.smtp.clave", clave);

                    Session session = Session.getDefaultInstance(props);
                    MimeMessage mensaje = new MimeMessage(session);
                    mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));

                    mensaje.setSubject("Recuperar Contraseña - Rexs");

                    BodyPart parteTexto = new MimeBodyPart();
                    parteTexto.setContent("Su PIN para recuperar clave es: \n" + "<h2> <b>" + pin + "</b> </h2>", "text/html");

                    MimeMultipart parts = new MimeMultipart();
                    parts.addBodyPart(parteTexto);

                    mensaje.setContent(parts);

                    Transport transport = session.getTransport("smtp");
                    transport.connect("smtp.gmail.com", remitente, clave);
                    transport.sendMessage(mensaje, mensaje.getAllRecipients());
                    transport.close();
                    System.out.println("Correo enviado");
                    mensajeError = "";
                    return true;
                } else {
                    mensajeError = "Hubo un error al enviar el pin.";
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static boolean verificarPin(String pin, String correo) {
        Db db = new Db();
        int id = db.getIdUsuario(correo);
        String pinDB = db.getPin(id);
        if (pin.equals(pinDB)) {
            return true;
        }
        return false;
    }

    public static boolean actualizarPerfilUsuario(String nombreCompleto, String email, int id) {
        Db db = new Db();
        if (!db.usuarioExiste(email) || email.equals(CurrentUser.email)) {
            if (db.actualizarPerfilUsuario(nombreCompleto, email, id)) {
                mensajeError = "";
                return true;
            } else {
                mensajeError = "Hubo un error al actualizar el usuario. Intenta de nuevo.";
                return false;
            }
        } else {
            mensajeError = "<html>Ya existe un usuario con la dirección de<br>correo electrónico.</html>";
            return false;
        }
    }

    public static boolean actualizarContraUsuario(String oldPassword, String newPassword, int id) {
        Db db = new Db();
        mensajeError = "";
        if (compareHash(oldPassword, db.getHash(id))) {
            String hash = hashPW(newPassword);
            if (db.actualizarContraUsuario(hash, id)) {
                mensajeError = "";
                return true;
            } else {
                mensajeError = "Hubo un error al actualizar el usuario. Intenta de nuevo.";
            }
        }
        return false;
    }

    public static boolean recuperarContraUsuario(String newPassword, String correo) {
        Db db = new Db();
        mensajeError = "";
        String hash = hashPW(newPassword);
        if (db.actualizarContraUsuario2(hash, correo)) {
            mensajeError = "";
            return true;
        } else {
            mensajeError = "Hubo un error al restaurar su clave. Intente de nuevo.";
        }

        return false;
    }

    public static boolean usuarioExiste(String email) {
        Db db = new Db();
        return db.usuarioExiste(email);
    }

    public static String hashPW(String pw) {
        String bcHash = "";
        try {
            String pw256 = toSHA256(pw);
            bcHash = BCrypt.hashpw(pw256, BCrypt.gensalt(12));
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error hashing: " + ex);
        }
        return bcHash;
    }

    public int getCantidadUsuarios() {
        Db db = new Db();
        try {
            db.NumUsuarios();
            int Numero;
            System.out.println(CurrentUser.idTipoUsuario);
            if (CurrentUser.idTipoUsuario == 1) {
                Numero = db.getCantidadUsuarios2();
            } else {
                Numero = db.getCantidadUsuarios();
            }
            System.out.println(Numero+"");

            return Numero;
        } catch (Exception e) {
        }
        return 0;
    }

    public int getCantidadUsuariosActivos() {
        Db db = new Db();
        try {
            int Numero = db.getUsuariosActivados();
            return Numero;
        } catch (Exception e) {
        }
        return 0;
    }

    public static boolean iniciarSesion(String email, String password) {
        Db db = new Db();
        if (usuarioExiste(email)) {
            String hash = db.getHash(email);
            if (hash != null) {
                hash = hash.trim();
                if (compareHash(password, hash)) {
                    User u = getUser(email);
                    CurrentUser.idUsuario = u.idUsuario;
                    CurrentUser.nombreCompleto = u.nombreCompleto;
                    CurrentUser.email = u.email;
                    CurrentUser.fotoPerfil = u.fotoPerfil;
                    CurrentUser.hash = u.hash;
                    CurrentUser.idTipoUsuario = u.idTipoUsuario;
                    CurrentUser.idEstadoUsuario = u.idEstadoUsuario;
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void cargarDatosUsuarioActual(int id) {
        User u = getUser(id);
        CurrentUser.idUsuario = u.idUsuario;
        CurrentUser.nombreCompleto = u.nombreCompleto;
        CurrentUser.email = u.email;
        CurrentUser.fotoPerfil = u.fotoPerfil;
        CurrentUser.hash = u.hash;
        CurrentUser.idTipoUsuario = u.idTipoUsuario;
        CurrentUser.idEstadoUsuario = u.idEstadoUsuario;
    }

    public static User getUser(String email) {
        try {
            Db db = new Db();
            ResultSet rs = db.getUsuario(email);
            User u = new User();
            u.idUsuario = rs.getInt(1);
            u.nombreCompleto = rs.getString(2);
            Blob imgAsBlob = rs.getBlob(3);
            if (imgAsBlob != null) {
                byte[] imgAsBytes = imgAsBlob.getBytes(1, (int) imgAsBlob.length());
                InputStream in = new ByteArrayInputStream(imgAsBytes);
                BufferedImage imgFromDb = ImageIO.read(in);
                u.fotoPerfil = imgFromDb;
            }
            u.email = rs.getString(4);
            u.hash = rs.getString(5);
            u.idTipoUsuario = rs.getInt(6);
            u.idEstadoUsuario = rs.getInt(7);
            return u;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return null;
        }
    }

    public static User getUser(int id) {
        try {
            Db db = new Db();
            ResultSet rs = db.getUsuario(id);
            User u = new User();
            u.idUsuario = rs.getInt(1);
            u.nombreCompleto = rs.getString(2);
            Blob imgAsBlob = rs.getBlob(3);
            if (imgAsBlob != null) {
                byte[] imgAsBytes = imgAsBlob.getBytes(1, (int) imgAsBlob.length());
                InputStream in = new ByteArrayInputStream(imgAsBytes);
                BufferedImage imgFromDb = ImageIO.read(in);
                u.fotoPerfil = imgFromDb;
            }
            u.email = rs.getString(4);
            u.hash = rs.getString(5);
            u.idTipoUsuario = rs.getInt(6);
            u.idEstadoUsuario = rs.getInt(7);
            return u;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return null;
        }
    }

    private static boolean compareHash(String pw, String hash) {
        try {
            pw = toSHA256(pw);
            return BCrypt.checkpw(pw, hash);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error hashing: " + ex);
            return false;
        }
    }

    private static String toSHA256(String text) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));

        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static int getIdEstadoUsuario(String estado) {
        Db db = new Db();
        return db.getIdEstadoUsuario(estado);
    }

    public static int getIdTipoUsuario(String tipo) {
        Db db = new Db();
        return db.getIdTipoUsuario(tipo);
    }

    public static boolean actualizarFotoPerfil(BufferedImage img, int idUsuario) {
        Db db = new Db();
        try {
            img = General.resizeAndCropIMG(img);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            if (ImageIO.write(img, "jpg", baos)) {
                byte[] immAsBytes = baos.toByteArray();
                baos.flush();
                baos.close();
                return db.actualizarFotoPerfil(immAsBytes, idUsuario);
            }

        } catch (IOException ex) {
            System.out.println(ex);
        }
        return false;
    }

    public static boolean eliminarFotoPerfil(int idUsuario) {
        Db db = new Db();
        return db.eliminarFotoPerfil(idUsuario);
    }

//    ArrayList<JPanel> panelesUsuarios;
//    ImageIcon iconEditCyan = new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/iconEditCyan.png"));
//
//    public void CrearPanelesUsuarios(javax.swing.JPanel panel) {
//
//        Db db = new Db();
//        db.MostrarUsuarios();
//        db.NumUsuarios();
//
//        panelesUsuarios = new ArrayList<>();
//
//        for (int i = 0; i < db.getCantidadUsuarios(); i++) {
//            JPanel Contenedor1 = new JPanel();
//            panel.add(Contenedor1);
//            panelesUsuarios.add(Contenedor1);
//
//            Contenedor1.setBackground(Color.white);
//            Contenedor1.setPreferredSize(new Dimension(576, 52));
//            Contenedor1.setLayout(null);
//            Border borde = new LineBorder(Color.CYAN, 1, true);
//            Contenedor1.setBorder(borde);
//
//            JLabel id = new JLabel();
//            id.setFont(new java.awt.Font("Rubik Medium", 0, 11));
//            id.setForeground(new Color(46, 56, 77));
//            id.setHorizontalAlignment(SwingConstants.LEADING);
//            id.setBounds(20, 15, 20, 20);
//            id.setText("<html>" + db.idUsuario.get(i) + "</html>");
//            //id.setBorder(new EtchedBorder());
//            Contenedor1.add(id);
//
//            JLabel nombreuser = new JLabel();
//            nombreuser.setFont(new java.awt.Font("Rubik Medium", 0, 11));
//            nombreuser.setForeground(new Color(46, 56, 77));
//            nombreuser.setHorizontalAlignment(SwingConstants.CENTER);
//            nombreuser.setBounds(40, 15, 110, 20);
//            nombreuser.setText(db.nombreCompleto.get(i));
//            //nombreuser.setBorder(new EtchedBorder());
//            Contenedor1.add(nombreuser);
//
//            JLabel email = new JLabel();
//            email.setFont(new java.awt.Font("Rubik Medium", 0, 11));
//            email.setForeground(new Color(46, 56, 77));
//            email.setHorizontalAlignment(SwingConstants.CENTER);
//            email.setBounds(150, 15, 150, 20);
//            email.setText(db.email.get(i));
//            // email.setBorder(new EtchedBorder());
//            Contenedor1.add(email);
//
//            JLabel fecha = new JLabel();
//            fecha.setFont(new java.awt.Font("Rubik Medium", 0, 11));
//            fecha.setForeground(new Color(46, 56, 77));
//            fecha.setHorizontalAlignment(SwingConstants.CENTER);
//            fecha.setBounds(320, 15, 70, 20);
//            fecha.setText(db.fecha.get(i).trim());
//            //fecha.setBorder(new EtchedBorder());
//            Contenedor1.add(fecha);
//
//            JLabel tipo = new JLabel();
//            tipo.setFont(new java.awt.Font("Rubik Medium", 0, 11));
//            tipo.setForeground(new Color(46, 56, 77));
//            tipo.setHorizontalAlignment(SwingConstants.CENTER);
//            tipo.setBounds(400, 15, 70, 20);
//            tipo.setText(db.tipo.get(i));
//            // tipo.setBorder(new EtchedBorder());
//            Contenedor1.add(tipo);
//
//            JLabel estado = new JLabel();
//            estado.setFont(new java.awt.Font("Rubik Medium", 0, 11));
//            estado.setForeground(new Color(46, 56, 77));
//            estado.setHorizontalAlignment(SwingConstants.CENTER);
//            estado.setBounds(490, 15, 70, 20);
//            estado.setText(db.estado.get(i));
//            // estado.setBorder(new EtchedBorder());
//            Contenedor1.add(estado);
//
//        }
//    }
    JPanel Contenedor1;

    ArrayList<JPanel> panelesUsuarios;

    public void CrearPanelesUsuarios(javax.swing.JPanel panel) {

        Db db = new Db();
        db.MostrarUsuarios();
        db.NumUsuarios();

        JLabel nombreuser = null;
        panelesUsuarios = new ArrayList<>();

        int canti = 0;
        int usu = CurrentUser.idTipoUsuario;
        if (usu == 1) {
            canti = db.getCantidadUsuarios2();
        } else {
            canti = db.getCantidadUsuarios();
        }

        for (int i = 0; i < canti; i++) {
            Contenedor1 = new JPanel();
            panel.add(Contenedor1);
            panelesUsuarios.add(Contenedor1);
            Contenedor1.setName(String.valueOf(db.idUsuario.get(i)));
            Contenedor1.setBackground(Color.white);
            Contenedor1.setPreferredSize(new Dimension(576, 52));
            Contenedor1.setLayout(null);
            Border borde = new LineBorder(Color.CYAN, 1, true);
            Contenedor1.setBorder(borde);

            JLabel id = new JLabel();
            id.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            id.setForeground(new Color(46, 56, 77));
            id.setHorizontalAlignment(SwingConstants.LEADING);
            id.setBounds(20, 15, 20, 20);
            id.setText("<html>" + db.idUsuario.get(i) + "</html>");
            //id.setBorder(new EtchedBorder());
            Contenedor1.add(id);

            nombreuser = new JLabel();
            nombreuser.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            nombreuser.setForeground(new Color(46, 56, 77));
            nombreuser.setHorizontalAlignment(SwingConstants.CENTER);
            nombreuser.setBounds(40, 15, 110, 20);
            nombreuser.setText(db.nombreCompleto.get(i));
            //nombreuser.setBorder(new EtchedBorder());
            Contenedor1.add(nombreuser);

            JLabel email = new JLabel();
            email.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            email.setForeground(new Color(46, 56, 77));
            email.setHorizontalAlignment(SwingConstants.CENTER);
            email.setBounds(150, 15, 150, 20);
            email.setText(db.email.get(i));
            // email.setBorder(new EtchedBorder());
            Contenedor1.add(email);

            JLabel fecha = new JLabel();
            fecha.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            fecha.setForeground(new Color(46, 56, 77));
            fecha.setHorizontalAlignment(SwingConstants.CENTER);
            fecha.setBounds(320, 15, 70, 20);
            fecha.setText(db.fecha.get(i).trim());
            //fecha.setBorder(new EtchedBorder());
            Contenedor1.add(fecha);

            JLabel tipo = new JLabel();
            tipo.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            tipo.setForeground(new Color(46, 56, 77));
            tipo.setHorizontalAlignment(SwingConstants.CENTER);
            tipo.setBounds(400, 15, 70, 20);
            tipo.setText(db.tipo.get(i));
            // tipo.setBorder(new EtchedBorder());
            Contenedor1.add(tipo);

            JLabel estado = new JLabel();
            estado.setFont(new java.awt.Font("Rubik Medium", 0, 11));
            estado.setForeground(new Color(46, 56, 77));
            estado.setHorizontalAlignment(SwingConstants.CENTER);
            estado.setBounds(490, 15, 70, 20);
            estado.setText(db.estado.get(i));
            // estado.setBorder(new EtchedBorder());
            Contenedor1.add(estado);

            Contenedor1.addMouseListener(new MouseListener() {
                Frame fr;

                @Override
                public void mouseClicked(MouseEvent e) {
                    Contenedor1 = (JPanel) e.getSource();

                    //System.out.println(Contenedor1.getName());
                    ModalModificarUsuario Modal = new ModalModificarUsuario();
                    Modal.jLabel70.setText(Contenedor1.getName());
                    String nombreCo = "";
                    String correo = "";
                    String clave = "";
                    String tipoUsuario;
                    String estadoUsuario;
                    //Consulta
                    Db db = new Db();
                    correo = db.getCorreoUsuario(Integer.parseInt(Contenedor1.getName()));
                    nombreCo = db.getNombreUsuario(Integer.parseInt(Contenedor1.getName()));
                    tipoUsuario = db.getTipoUsuario(Integer.parseInt(Contenedor1.getName()));
                    estadoUsuario = db.getEstadoUsuario(Integer.parseInt(Contenedor1.getName()));
                    Modal.txtNombreUsuarioModal.setText(nombreCo);
                    Modal.txtEmailUsuarioModal.setText(correo);
                    Modal.txtClaveUsuarioModal.setText(clave);
                    Modal.cbxTipoUsuarioModal.setSelectedItem(tipoUsuario);
                    Modal.cbxEstadoUsuarioModal.setSelectedItem(estadoUsuario);
                    JDialog modal1 = new JDialog(fr, "Modificar Usuario", true);
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

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the nombreCompleto
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * @param nombreCompleto the nombreCompleto to set
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * @param hash the hash to set
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * @return the fotoPerfil
     */
    public BufferedImage getFotoPerfil() {
        return fotoPerfil;
    }

    /**
     * @param fotoPerfil the fotoPerfil to set
     */
    public void setFotoPerfil(BufferedImage fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    /**
     * @return the idTipoUsuario
     */
    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    /**
     * @param idTipoUsuario the idTipoUsuario to set
     */
    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    /**
     * @return the idEstadoUsuario
     */
    public int getIdEstadoUsuario() {
        return idEstadoUsuario;
    }

    /**
     * @param idEstadoUsuario the idEstadoUsuario to set
     */
    public void setIdEstadoUsuario(int idEstadoUsuario) {
        this.idEstadoUsuario = idEstadoUsuario;
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
     * @return the estadoUsuario
     */
    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    /**
     * @param estadoUsuario the estadoUsuario to set
     */
    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    /**
     * @return the tipoUsuario
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @param tipoUsuario the tipoUsuario to set
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * @return the Clave
     */
    public String getClave() {
        return Clave;
    }

    /**
     * @param Clave the Clave to set
     */
    public void setClave(String Clave) {
        this.Clave = Clave;
    }

    public static User getNumUserFiltrados(String nombre, String idE, String idT) {
        try {
            Db db = new Db();
            ResultSet rs = db.NumUsuariosFiltrados(nombre, idE, idT);
            User h = new User();
            h.NumUserFiltrados = rs.getInt(1);
            return h;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return null;
        }
    }
}
