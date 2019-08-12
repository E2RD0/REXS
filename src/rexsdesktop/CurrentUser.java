/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop;

import java.awt.image.BufferedImage;

/**
 *
 * @author Eduardo
 */
public class CurrentUser{
    
    public static int idUsuario;
    public static String nombreCompleto;
    public static String email;
    public static String hash;
    public static BufferedImage fotoPerfil;
    public static int idTipoUsuario;
    public static int idEstadoUsuario;

    public static void clear() {
        idUsuario = 0;
        nombreCompleto = null;
        email = null;
        hash = null;
        fotoPerfil = null;
        idTipoUsuario = 0;
        idEstadoUsuario = 0;
    }
}
