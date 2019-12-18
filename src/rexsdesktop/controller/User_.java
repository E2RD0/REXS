/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.controller;

import java.awt.image.BufferedImage;
import rexsdesktop.CurrentUser;
import rexsdesktop.model.UserModel;

/**
 *
 * @author Eduardo
 */
public class User_{
    
    private int idUsuario;
    private String nombreCompleto;
    private String email;
    private String hash;
    private BufferedImage fotoPerfil;
    private int idTipoUsuario;
    private int idEstadoUsuario;
    /*public User_(int id, String nombre, String email, String hash, BufferedImage foto, int idTipoUsuario, int idEstadoUsuario){
        setIdUsuario(id);
        setNombreCompleto(nombre);
        setEmail(email);
        setHash(hash);
        setFotoPerfil(foto);
        setIdTipoUsuario(idTipoUsuario);
        setIdEstadoUsuario(idEstadoUsuario);
    }*/
    public static boolean usuarioExiste(String email) {
        return new UserModel().userExists(email);
    }
    public static boolean logIn(String email, String password) {
        if (UserModel.userExists(email)) {
            String hash = UserModel.getHash(email);
            if (hash != null) {
                hash = hash.trim();
                if (Authentication.compareHash(password, hash)) {
                    User_ u = UserModel.getUser(email);
                    CurrentUser.setIdUsuario(u.getIdUsuario());
                    CurrentUser.setNombreCompleto(u.getNombreCompleto());
                    CurrentUser.setEmail(u.getEmail());
                    CurrentUser.setFotoPerfil(u.getFotoPerfil());
                    CurrentUser.setHash(u.getHash());
                    CurrentUser.setIdTipoUsuario(u.getIdTipoUsuario());
                    CurrentUser.setIdEstadoUsuario(u.getIdEstadoUsuario());
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
    
    
}
