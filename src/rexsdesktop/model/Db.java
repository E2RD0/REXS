/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Eduardo
 */
public class Db {
    private Connection cn;
    
    public Db(){
        cn = new DbConnection().conectar();
    }
    
    public boolean agregarUsuario(String nombreCompleto, String email, String claveHash, int idTipoUsuario, int idEstadoUsuario){
        boolean r = false;
        try{
        String query = "INSERT INTO usuario(nombreCompleto, email, clave, idTipoUsuario, idEstadoUsuario)" +
                        "VALUES(?,?,?,?,?)";
        PreparedStatement cmd = cn.prepareStatement(query);
        cmd.setString(1, nombreCompleto);
        cmd.setString(2, email);
        cmd.setString(3, claveHash);
        cmd.setInt(4, idTipoUsuario);
        cmd.setInt(5, idEstadoUsuario);
        if(cmd.executeUpdate() > 0){
            r = true;
            }
        cmd.close();
        cn.close();
        }
        catch(Exception e){
            System.out.println("Error: "+ e);
        }
                
        return r;
    }
    public boolean usuarioExiste(String email){
        try{
        String query = "SELECT * FROM usuario WHERE email = ?";
        PreparedStatement cmd = cn.prepareStatement(query);
        cmd.setString(1, email);
        ResultSet rs = cmd.executeQuery();
        return rs.next();
        }
        catch(Exception e){
            System.out.println("Error: "+ e);
            return true;
        }
    }
    public String getHash(String email){
        try{
        String query = "SELECT clave from usuario where email = ?";
        PreparedStatement cmd = cn.prepareStatement(query);
        cmd.setString(1, email);
        ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
            else{
                return "";
            }
        }
        catch(Exception e){
            System.out.println("Error: "+ e);
            return "";
        }
    }
    public ResultSet getUsuario(String email){
        try{
        String query = "SELECT idUsuario, nombreCompleto, fotoPerfil, email, clave, idTipoUsuario, idEstadoUsuario FROM usuario where email = ?";
        PreparedStatement cmd = cn.prepareStatement(query);
        cmd.setString(1, email);
        ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                return rs;
            }
            else{
                return null;
            }
        }
        catch(Exception e){
            System.out.println("Error: "+ e);
            return null;
        }
    }
    
    public ResultSet getUsuario(int id){
        try{
        String query = "SELECT idUsuario, nombreCompleto, fotoPerfil, email, clave, idTipoUsuario, idEstadoUsuario FROM usuario where idUsuario = ?";
        PreparedStatement cmd = cn.prepareStatement(query);
        cmd.setInt(1, id);
        ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                return rs;
            }
            else{
                return null;
            }
        }
        catch(Exception e){
            System.out.println("Error: "+ e);
            return null;
        }
    }
    
    public int getIdTipoUsuario(String tipo){
        try{
        String query = "SELECT idTipoUsuario FROM tipoUsuario WHERE tipo = ?";
        PreparedStatement cmd = cn.prepareStatement(query);
        cmd.setString(1, tipo);
        ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            else{
                return 3;
            }
        }
        catch(Exception e){
            System.out.println("Error: "+ e);
            return 3;
        }
    }
    public int getIdEstadoUsuario(String estado){
        try{
        String query = "SELECT idEstadoUsuario FROM estadoUsuario WHERE estado = ?";
        PreparedStatement cmd = cn.prepareStatement(query);
        cmd.setString(1, estado);
        ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            else{
                return 1;
            }
        }
        catch(Exception e){
            System.out.println("Error: "+ e);
            return 1;
        }
    }
}
