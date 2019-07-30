/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import rexsdesktop.model.Db;

/**
 *
 * @author Eduardo
 */
public class User {
    public int idUsuario;
    public String nombreCompleto;
    public String email;
    public int tipoUsuario;
    public int estadoUsuario;
    
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
    public static boolean compareHash(String pw, String hash){
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
}
