/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.controller;

import java.awt.image.BufferedImage;
import rexsdesktop.Session;
import rexsdesktop.model.UserModel;
import rexsdesktop.model.UserRepository;

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

    private UserRepository uRepository;
    
    public User_(){
        uRepository = new UserModel();
    }
    public boolean userExists(String email) {
        return uRepository.userExists(email);
    }
    public boolean logIn(String email, String password) {
        if (new UserModel().userExists(email)) {
            String hash = uRepository.getHash(email);
            if (hash != null) {
                hash = hash.trim();
                if (Authentication.compareHash(password, hash)) {
                    User_ u = uRepository.getUser(email);
                    Session.getInstance().setUser(u);
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
    public void reloadData(){
        User_ u = Session.getInstance().getUser();
        int id = u.getIdUsuario();
        Session.getInstance().setUser(uRepository.getUser(id));
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
