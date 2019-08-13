/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.controller;

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
import java.util.Properties;
import javax.imageio.ImageIO;
import rexsdesktop.CurrentUser;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Eduardo
 */
public class User {

    public int idUsuario;
    public String nombreCompleto;
    public String email;
    public String hash;
    public BufferedImage fotoPerfil;
    public int idTipoUsuario;
    public int idEstadoUsuario;

    public static String mensajeError = "";

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
                    String remitente = "pluzedu@gmail.com";
                    String clave = "!3uCmXA3CFwXLK0a";

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
}
