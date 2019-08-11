/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import rexsdesktop.model.Db;
import java.sql.ResultSet;
import java.sql.Blob;
import javax.imageio.ImageIO;
import rexsdesktop.CurrentUser;

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
    
    
    public static boolean nuevoUsuario(String nombreCompleto, String email, String clave, String tipoUsuario, String estadoUsuario){
        Db db = new Db();
        if (!db.usuarioExiste(email)) {
            String hash = hashPW(clave);
            int idTipoUsuario = db.getIdTipoUsuario(tipoUsuario);
            int idEstadoUsuario = db.getIdEstadoUsuario(estadoUsuario);
             if(db.agregarUsuario(nombreCompleto, email, hash, idTipoUsuario, idEstadoUsuario)){
                 mensajeError = "";
                 return true;
             }
             else{
                mensajeError = "Hubo un error al registrar el usuario. Intenta de nuevo.";
                return false;
             }
        }
        else{
            mensajeError = "<html>Ya existe un usuario con la dirección de<br>correo electrónico.</html>";
            return false;
        }
    }
    public static boolean actualizarPerfilUsuario(String nombreCompleto, String email, int id){
        Db db = new Db();
        if (!db.usuarioExiste(email) || email.equals(CurrentUser.email)) {
             if(db.actualizarPerfilUsuario(nombreCompleto, email, id)){
                 mensajeError = "";
                 return true;
             }
             else{
                mensajeError = "Hubo un error al actualizar el usuario. Intenta de nuevo.";
                return false;
             }
        }
        else{
            mensajeError = "<html>Ya existe un usuario con la dirección de<br>correo electrónico.</html>";
            return false;
        }
    }
    public static boolean usuarioExiste(String email){
        Db db = new Db();
        return db.usuarioExiste(email);
    }
    public static String hashPW(String pw){
        String bcHash = "";
        try {
            String pw256 = toSHA256(pw);
            bcHash = BCrypt.hashpw(pw256, BCrypt.gensalt(12));
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error hashing: "+ ex);
        }
        return bcHash;
    }
    public static boolean iniciarSesion(String email, String password){
        Db db = new Db();
        if (usuarioExiste(email)) {
            String hash = db.getHash(email);
            if (hash!= null) {
                hash = hash.trim();
                if (compareHash(password, hash)) {
                    User u = getUser(email);
                    CurrentUser.idUsuario = u.idUsuario;
                    CurrentUser.nombreCompleto = u.nombreCompleto;
                    CurrentUser.email = u.email;
                    CurrentUser.fotoPerfil = u.fotoPerfil;
                    CurrentUser.hash = u .hash;
                    CurrentUser.idTipoUsuario = u.idTipoUsuario;
                    CurrentUser.idEstadoUsuario = u.idEstadoUsuario;
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    public static void cargarDatosUsuarioActual(int id){
        User u = getUser(id);
        CurrentUser.idUsuario = u.idUsuario;
        CurrentUser.nombreCompleto = u.nombreCompleto;
        CurrentUser.email = u.email;
        CurrentUser.fotoPerfil = u.fotoPerfil;
        CurrentUser.hash = u .hash;
        CurrentUser.idTipoUsuario = u.idTipoUsuario;
        CurrentUser.idEstadoUsuario = u.idEstadoUsuario;
    }
    
    public static User getUser(String email){
        try {
            Db db = new Db();
            ResultSet rs = db.getUsuario(email);
            User u = new User();
            u.idUsuario = rs.getInt(1);
            u.nombreCompleto = rs.getString(2);
            Blob imgAsBlob = rs.getBlob(3);
            if (imgAsBlob!= null) {
                byte[] imgAsBytes = imgAsBlob.getBytes(1, (int)imgAsBlob.length());
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
    public static User getUser(int id){
        try {
            Db db = new Db();
            ResultSet rs = db.getUsuario(id);
            User u = new User();
            u.idUsuario = rs.getInt(1);
            u.nombreCompleto = rs.getString(2);
            Blob imgAsBlob = rs.getBlob(3);
            if (imgAsBlob!= null) {
                byte[] imgAsBytes = imgAsBlob.getBytes(1, (int)imgAsBlob.length());
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
    private static boolean compareHash(String pw, String hash){
        try {
            pw = toSHA256(pw);
            return BCrypt.checkpw(pw, hash);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error hashing: " + ex);
            return false;
        }      
    }
    private static String toSHA256(String text) throws NoSuchAlgorithmException{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
        
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
        String hex = Integer.toHexString(0xff & hash[i]);
        if(hex.length() == 1) hexString.append('0');
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
}
