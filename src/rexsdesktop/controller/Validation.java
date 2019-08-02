/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.controller;

/**
 *
 * @author Eduardo
 */
public class Validation {
    
    
    public static boolean isStringEmptyOrNull(String string){
        return !(string != null && !string.trim().isEmpty() && !string.trim().isBlank());
    }
    public static class VerificadorNombre{
        public static String mensaje = "";
	public static boolean verify(String texto) {
            mensaje = "";
                if (isStringEmptyOrNull(texto)) {
                    mensaje = "El campo no puede estar vacío.";
                    return false;
                }
                else{
                    texto = texto.trim();
                    if (texto.matches("^[a-zñáéíóúüA-ZÑÁÉÍÓÚÜ]+( [a-zñáéíóúüA-ZÑÁÉÍÓÚÜ]+)*$")) {
                            if (texto.length() < 5) {
                                mensaje = "Por favor ingresa tu nombre completo.";
                                return false;
                            }
                            else{
                                if (texto.length() > 50) {
                                mensaje = "La longitud máxima es de 50 caracteres.";
                                return false;
                                }
                                else{
                                    mensaje = "";
                                    return true;
                                }
                            }
                        }
                        else{
                            mensaje = "No se aceptan caractéres númericos o especiales.";
                            return false;
                        }

                }
	}
    }
    
    public static class VerificadorEmail{
        public static String mensaje = "";
	public static boolean verify(String email) {
            mensaje = "";
                if (isStringEmptyOrNull(email)) {
                    mensaje = "El campo no puede estar vacío.";
                    return false;
                }
                else{
                    email = email.trim();
                    if (email.matches("^[\\w!#$%&’'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,8}$")) {
                            if (email.length() > 75) {
                            mensaje = "La longitud máxima es de 75 caracteres.";
                            return false;
                            }
                            else{
                                mensaje = "";
                                return true;
                            }
                        }
                        else{
                            mensaje = "Escribe una dirección de correo electrónico válida.";
                            return false;
                        }

                }
	}
    }
    public static class VerificadorPassword{
        public static String mensaje = "";
	public static boolean verify(String password) {
            mensaje = "";
                if (isStringEmptyOrNull(password)) {
                    mensaje = "El campo no puede estar vacío.";
                    return false;
                }
                else{
                    password = password.trim();
                    if (password.length() > 1024) {
                        mensaje = "La longitud máxima es de 1024 caracteres.";
                        return false;
                    }
                    else{
                        if (password.length()< 6) {
                            mensaje = "La longitud mínima es de 6 caracteres.";
                            return false;
                        }
                        else{
                            if (password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$")) {
                                mensaje = "";
                                return true;
                            }
                            else{
                                mensaje= "<html>La clave debe tener al menos un dígito, una<br>letra mayúscula y una minúscula</html>";
                                return false;
                            }
                        }
                    }

                }
	}
    }
    
    public static class VerificadorLogin{
        public static String mensajeEmail = "";
        public static String mensajePassword = "";
	public static boolean verify(String email, String password) {
            mensajeEmail = "";
            mensajePassword = "";
                if (isStringEmptyOrNull(email) || isStringEmptyOrNull(password)) {
                    if (isStringEmptyOrNull(password)) {
                        mensajePassword = "El campo no puede estar vacío.";
                    }
                    if (isStringEmptyOrNull(email)) {
                        mensajeEmail = "El campo no puede estar vacío.";
                    }
                    return false;
                }
                else{
                    mensajeEmail ="";
                    mensajePassword = "";
                    return true;
                }
	}
    }
    
}
