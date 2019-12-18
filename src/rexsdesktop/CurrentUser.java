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

    /**
     * @return the idUsuario
     */
    public static int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param aIdUsuario the idUsuario to set
     */
    public static void setIdUsuario(int aIdUsuario) {
        idUsuario = aIdUsuario;
    }

    /**
     * @return the nombreCompleto
     */
    public static String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * @param aNombreCompleto the nombreCompleto to set
     */
    public static void setNombreCompleto(String aNombreCompleto) {
        nombreCompleto = aNombreCompleto;
    }

    /**
     * @return the email
     */
    public static String getEmail() {
        return email;
    }

    /**
     * @param aEmail the email to set
     */
    public static void setEmail(String aEmail) {
        email = aEmail;
    }

    /**
     * @return the hash
     */
    public static String getHash() {
        return hash;
    }

    /**
     * @param aHash the hash to set
     */
    public static void setHash(String aHash) {
        hash = aHash;
    }

    /**
     * @return the fotoPerfil
     */
    public static BufferedImage getFotoPerfil() {
        return fotoPerfil;
    }

    /**
     * @param aFotoPerfil the fotoPerfil to set
     */
    public static void setFotoPerfil(BufferedImage aFotoPerfil) {
        fotoPerfil = aFotoPerfil;
    }

    /**
     * @return the idTipoUsuario
     */
    public static int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    /**
     * @param aIdTipoUsuario the idTipoUsuario to set
     */
    public static void setIdTipoUsuario(int aIdTipoUsuario) {
        idTipoUsuario = aIdTipoUsuario;
    }

    /**
     * @return the idEstadoUsuario
     */
    public static int getIdEstadoUsuario() {
        return idEstadoUsuario;
    }

    /**
     * @param aIdEstadoUsuario the idEstadoUsuario to set
     */
    public static void setIdEstadoUsuario(int aIdEstadoUsuario) {
        idEstadoUsuario = aIdEstadoUsuario;
    }

    /**
     * @return the edicionExpotecnica
     */
    public static String getEdicionExpotecnica() {
        return edicionExpotecnica;
    }

    /**
     * @param aEdicionExpotecnica the edicionExpotecnica to set
     */
    public static void setEdicionExpotecnica(String aEdicionExpotecnica) {
        edicionExpotecnica = aEdicionExpotecnica;
    }

    private static int idUsuario;
    private static String nombreCompleto;
    private static String email;
    private static String hash;
    private static BufferedImage fotoPerfil;
    private static int idTipoUsuario;
    private static int idEstadoUsuario;
    private static String edicionExpotecnica;

    /**
     *
     * Este método es utilizado para limpiar los atributos de la clase.
     */
    public static void clear() {
        setIdUsuario(0);
        setNombreCompleto(null);
        setEmail(null);
        setHash(null);
        setFotoPerfil(null);
        setIdTipoUsuario(0);
        setIdEstadoUsuario(0);
    }
}
