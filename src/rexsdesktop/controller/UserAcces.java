/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.controller;

import rexsdesktop.model.Db;
import rexsdesktop.model.UserInterface;
import rexsdesktop.model.UserModel;

/**
 *
 * @author Carlos Herrera
 */
public class UserAcces implements UserInterface{

    protected static UserModel userM = new UserModel();
    
    @Override
    public void register(String name, String email, String password, String type, String state) {
        if (!userM.usuarioExiste(email)) {
            String hash = Encryption.hashPW(password);
            int idTipoUsuario = userM.getIdTipoUsuario(type);
            int idEstadoUsuario = userM.getIdEstadoUsuario(state);
            if (userM.agregarUsuario(name, email, hash, idTipoUsuario, idEstadoUsuario)) {
                User.mensajeError = "";
            } else {
                User.mensajeError = "Hubo un error al registrar el usuario. Intenta de nuevo.";
            }
        } else {
            User.mensajeError = "<html>Ya existe un usuario con la dirección de<br>correo electrónico.</html>";
        }
    }

    @Override
    public void login() {
        
    }
    
}
