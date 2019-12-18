/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import javax.imageio.ImageIO;
import rexsdesktop.controller.User_;

/**
 *
 * @author Eduardo
 */
public class UserModel{
    
    public static boolean userExists(String email) {
        DbConnection dbc = new DbConnection();
        Connection cn = dbc.conectar();
        try {
            String query = "SELECT * FROM usuario WHERE email = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, email);
            ResultSet rs = cmd.executeQuery();
            boolean result = rs.next();
            rs.close();
            cmd.close();
            cn.close();
            return result;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return true;
        }
    }
    public static String getHash(String email) {
        try {
            DbConnection dbc = new DbConnection();
            Connection cn = dbc.conectar();
            String query = "SELECT clave from usuario where email = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, email);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                String hash = rs.getString(1);
                rs.close();
                cmd.close();
                cn.close();
                return hash;
            } else {
                return "";
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return "";
        }
    }
    public static User_ getUser(String email) {
        try {
            DbConnection dbc = new DbConnection();
            Connection cn = dbc.conectar();
            String query = "SELECT idUsuario, nombreCompleto, fotoPerfil, email, clave, idTipoUsuario, idEstadoUsuario FROM usuario where email = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, email);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                User_ u = new User_();
                u.setIdUsuario(rs.getInt(1));
                u.setNombreCompleto(rs.getString(2));
                Blob imgAsBlob = rs.getBlob(3);
                if (imgAsBlob != null) {
                    byte[] imgAsBytes = imgAsBlob.getBytes(1, (int) imgAsBlob.length());
                    InputStream in = new ByteArrayInputStream(imgAsBytes);
                    BufferedImage imgFromDb = ImageIO.read(in);
                    u.setFotoPerfil(imgFromDb);
                }
                u.setEmail(rs.getString(4));
                u.setHash(rs.getString(5));
                u.setIdTipoUsuario(rs.getInt(6));
                u.setIdEstadoUsuario(rs.getInt(7));
                return u;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }
}
