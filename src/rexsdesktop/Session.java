/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop;

import java.awt.image.BufferedImage;
import rexsdesktop.controller.User_;

/**
 *
 * Esta clase sirve para almacenar los datos del usuario que se encuentra con la
 * sesión activada. Se utilizan los atributos para mostrar el nombre, iniciar
 * sesión, realizar operaciones.
 *
 * @author Eduardo
 * @version 1.2
 */
public class Session {

    /**
     * @return the idUsuario
     */
    private static Session session = null;
    private User_ user;
    private String edicionExpotecnica;

    private Session() {
    }

    public static Session getInstance() {
        if (session == null) {
            session = new Session();
        }
        return session;
    }

    public void setUser(User_ u) {
        this.user = u;
    }

    public User_ getUser() {
        return user;
    }

    public void reloadUserData() {
        User_ u = Session.getInstance().getUser();
        u.reloadData();
    }

    public void clear() {
        user = null;
        edicionExpotecnica = null;
    }

    public void setEdicionExpotecnica(String edicion) {
        this.edicionExpotecnica = edicion;
    }

    public String getEdicionExpotecnica() {
        return edicionExpotecnica;
    }

}
