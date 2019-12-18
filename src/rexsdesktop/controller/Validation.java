/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.controller;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Clase utilizada para realizar las validaciones del sistema.
 *
 * @author Eduardo
 * @version 1.2
 */
public class Validation {

    public static boolean isStringEmptyOrNull(String string) {
        return !(string != null && !string.trim().isEmpty() && !string.trim().isBlank());
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[\\w!#$%&’'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,8}$");
    }

    /**
     * Clase utilizada para realizar las validaciones de nombres.
     *
     * @author Eduardo
     * @version 1.2
     */
    public static class VerificadorNombre {

        public static String mensaje = "";

        public static boolean verify(String texto) {
            mensaje = "";
            if (isStringEmptyOrNull(texto)) {
                mensaje = "El campo no puede estar vacío.";
                return false;
            } else {
                texto = texto.trim();
                if (texto.matches("^[a-zñáéíóúüA-ZÑÁÉÍÓÚÜ]+( [a-zñáéíóúüA-ZÑÁÉÍÓÚÜ]+)*$")) {
                    if (texto.length() < 5) {
                        mensaje = "Por favor ingresa tu nombre completo.";
                        return false;
                    } else {
                        if (texto.length() > 50) {
                            mensaje = "La longitud máxima es de 50 caracteres.";
                            return false;
                        } else {
                            mensaje = "";
                            return true;
                        }
                    }
                } else {
                    mensaje = "No se aceptan caractéres númericos o especiales.";
                    return false;
                }

            }
        }
    }

    /**
     * Clase utilizada para realizar las validaciones de los correos que se
     * ingresen al sistema.
     *
     * @author Eduardo
     * @version 1.2
     */
    public static class VerificadorEmail {

        public static String mensaje = "";

        public static boolean verify(String email) {
            mensaje = "";
            if (isStringEmptyOrNull(email)) {
                mensaje = "El campo no puede estar vacío.";
                return false;
            } else {
                email = email.trim();
                if (isValidEmail(email)) {
                    if (email.length() > 75) {
                        mensaje = "La longitud máxima es de 75 caracteres.";
                        return false;
                    } else {
                        mensaje = "";
                        return true;
                    }
                } else {
                    mensaje = "Escribe una dirección de correo electrónico válida.";
                    return false;
                }

            }
        }
    }

    /**
     * Clase utilizada para realizar validar las claves.
     *
     * @author Eduardo
     * @version 1.2
     */
    public static class VerificadorPassword {

        public static String mensaje = "";

        public static boolean verify(String password) {
            mensaje = "";
            if (isStringEmptyOrNull(password)) {
                mensaje = "El campo no puede estar vacío.";
                return false;
            } else {
                password = password.trim();
                if (password.length() > 1024) {
                    mensaje = "La longitud máxima es de 1024 caracteres.";
                    return false;
                } else {
                    if (password.length() < 6) {
                        mensaje = "La longitud mínima es de 6 caracteres.";
                        return false;
                    } else {
                        if (password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$")) {
                            mensaje = "";
                            return true;
                        } else {
                            mensaje = "<html>La clave debe tener al menos un dígito, una<br>letra mayúscula y una minúscula</html>";
                            return false;
                        }
                    }
                }

            }
        }
    }

    /**
     * Clase utilizada para realizar la validación principal del Inicio de
     * Sesión.
     *
     * @author Eduardo
     * @version 1.2
     */
    public class VerificadorLogin {

        /**
         * @return the mensajeEmail
         */
        public String getMensajeEmail() {
            return mensajeEmail;
        }

        /**
         * @param mensajeEmail the mensajeEmail to set
         */
        public void setMensajeEmail(String mensajeEmail) {
            this.mensajeEmail = mensajeEmail;
        }

        /**
         * @return the mensajePassword
         */
        public String getMensajePassword() {
            return mensajePassword;
        }

        /**
         * @param mensajePassword the mensajePassword to set
         */
        public void setMensajePassword(String mensajePassword) {
            this.mensajePassword = mensajePassword;
        }
        
        String email;
        String password;
        private String mensajeEmail = "";
        private String mensajePassword = "";
        
        public VerificadorLogin(String email, String password){
            this.email = email;
            this.password = password;
        }

        public boolean verify() {
            setMensajeEmail("");
            setMensajePassword("");
            if (isStringEmptyOrNull(email) || isStringEmptyOrNull(password)) {
                if (isStringEmptyOrNull(password)) {
                    setMensajePassword("El campo no puede estar vacío.");
                }
                if (isStringEmptyOrNull(email)) {
                    setMensajeEmail("El campo no puede estar vacío.");
                }
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * Clase utilizada para realizar la validación de las imagenes.
     *
     * @author Eduardo
     * @version 1.2
     */
    public static class VerificadorImagen {

        public static String mensaje = "";

        public static boolean verifyFile(File f) {
            mensaje = "";
            if (f.exists() && f.isFile()) {
                double size = (double) f.length() / (1024 * 1024);
                if (size > 0 && size <= 4) {
                    mensaje = "";
                    return true;
                } else {
                    mensaje = "El tamaño máximo del archivo es de 4MB";
                }
            } else {
                mensaje = "No se puede encontrar el archivo.";
            }
            return false;
        }

        public static boolean verifyIMG(BufferedImage img) {
            mensaje = "";
            if (img != null) {
                if (img.getHeight() >= 200 && img.getWidth() >= 200) {
                    mensaje = "";
                    return true;
                } else {
                    mensaje = "Las dimensiones de la imagen deben de ser de al menos 200PX";
                }
            } else {
                mensaje = "Error al cargar el archivo";
            }
            return false;
        }
    }

}
