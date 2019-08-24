/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop;

import java.awt.image.BufferedImage;

/**
 *
 * Esta clase sirve para almacenar los datos del usuario que se encuentra con la
 * sesión activada. Se utilizan los atributos para mostrar el nombre, iniciar
 * sesión, realizar operaciones.
 * 
 * @author Eduardo
 * @version 1.2
 */
public class CurrentUser {

    public static int idUsuario;
    public static String nombreCompleto;
    public static String email;
    public static String hash;
    public static BufferedImage fotoPerfil;
    public static int idTipoUsuario;
    public static int idEstadoUsuario;

    /**
     *
     * Este método es utilizado para limpiar los atributos de la clase
     */
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
