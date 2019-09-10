/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.model;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import rexsdesktop.CurrentUser;

/**
 * Clase utilizada como modelo o capa de gestión de datos.
 *
 * @author Eduardo
 * @version 1.2
 */
public class Db {

    private Connection cn;

    public Db() {
        cn = new DbConnection().conectar();
    }

    // <editor-fold defaultstate="collapsed" desc="Activities">
    private int CantidadActividades;
    private ArrayList<String> HorasActividadesInicio;
    private ArrayList<String> nombreAct;
    private ArrayList<Time> HoraInicio;
    private ArrayList<Time> HoraFin;
    private int contador;

    /**
     * Método utilizado para agregar una nueva actividad.
     *
     * @param nombre el nombre de la actividad a agregar.
     * @param descripcion la descripcion de la actividad a agregar.
     * @param fechaInicio fecha de inicio de la actividad a agregar.
     * @param edicion edicion de la Expotécnica Seleccionada.
     * @param fechaFin fecha de finalización de la actividad a agregar.
     * @param idUbicacion identificador de la ubicación de la actividad a
     * agregar.
     * @return retorna un valor booleano para ser utilizado en el controlador.
     */
    public boolean agregarActividad(String nombre, String descripcion, String fechaInicio, String edicion, String fechaFin, int idUbicacion) {
        boolean bandera = false;
        try {
            String sql = "INSERT INTO actividad (nombre, descripcion, fechaInicio, fechaFin, edicion, idUbicacion) VALUES (?,?,?,?,?,?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, nombre);
            stm.setString(2, descripcion);
            stm.setString(3, fechaInicio);
            stm.setString(4, fechaFin);
            stm.setString(5, edicion);
            stm.setInt(6, idUbicacion);

            if (stm.executeUpdate() > 0) {
                bandera = true;
            }
        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }
        return bandera;
    }

    /**
     * Método utilizado para eliminar una actividad seleccionada.
     *
     * @param id identificador de la actividad a eliminar.
     * @return retorna un valor booleano para ser utilizado en el controlador.
     */
    public boolean eliminarActividad(int id) {
        boolean bandera = false;
        try {
            String sql = "DELETE FROM actividad WHERE idActividad = (?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, id);

            if (stm.executeUpdate() > 0) {
                bandera = true;
            }

            stm.close();
            cn.close();

        } catch (SQLException e) {
            System.out.println("ERROR" + e);
        }
        return bandera;
    }

    /**
     * Método utilizado para eliminar las actividades de la base de datos.
     *
     * @return retorna un valor booleano para ser utilizado en el controlador.
     */
    public boolean eliminarActividades() {
        boolean bandera = false;
        try {
            String sql = "DELETE FROM actividad";
            PreparedStatement stm = cn.prepareStatement(sql);

            if (stm.executeUpdate() > 0) {
                bandera = true;
            }

            stm.close();
            cn.close();

        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }
        return bandera;
    }

    /**
     * Método utilizado para actualizar una actividad seleccionada.
     *
     * @param nombre nombre de la actividad.
     * @param descripcion descripción de la actividad.
     * @param fechaInicio fecha de inicio de la actividad.
     * @param fechaFin fecha de finalización de la actividad.
     * @param idUbicacion identificador de la ubicación de la actividad.
     * @param id identificador de la actividad a eliminar.
     * @return retorna un valor booleano para ser utilizado en el controlador.
     */
    public boolean actualizarActividad(String nombre, String descripcion, String fechaInicio, String fechaFin, int idUbicacion, int id) {
        boolean bandera = false;
        try {
            String sql = "UPDATE actividad set nombre = (?), descripcion = (?), fechaInicio = (?), fechaFin = (?), idUbicacion = (?) where idActividad = (?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, nombre);
            stm.setString(2, descripcion);
            stm.setString(3, fechaInicio);
            stm.setString(4, fechaFin);
            stm.setInt(5, idUbicacion);
            stm.setInt(6, id);

            if (stm.executeUpdate() > 0) {
                bandera = true;
            }

            stm.close();
            cn.close();

        } catch (SQLException e) {
            System.out.println("ERROR" + e);
        }
        return bandera;
    }

    /**
     * Método utilizado para obtener el número de actividades que existen.
     *
     * @param inicio fecha de inicio de la actividad
     * @param fin fecha de finalización de la actividad
     */
    public void NumActividades(String inicio, String edicion, String fin) {
        try {

            String sql = "select COUNT(idActividad) from actividad where fechaInicio between ? AND ? AND edicion = ?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, inicio);
            st.setString(2, fin);
            st.setString(3, edicion);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                setCantidadActividades(rs.getInt(1));
                //System.out.println(getNumProyecto());
            }
//            st.close();
//            cn.close();
        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    /**
     * Método utilizado para obtener la hora de inicio de las actividades de una
     * edición de Expotécnica
     *
     * @param inicio fecha de inicio de la Expotécnica
     * @param edicion edición de Expotécnica
     * @param fin fecha de finalizacion de la Expotécnica
     */
    public void HorasInicioActividades(String inicio, String edicion, String fin) {
        try {

            String sql = "select fechaInicio from actividad where fechaInicio between (?) AND (?) AND edicion = ?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, inicio);
            st.setString(2, fin);
            st.setString(3, edicion);

            ResultSet rs = st.executeQuery();
            setHoraInicio(new ArrayList<>());
            while (rs.next()) {
                getHoraInicio().add(rs.getTime(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método utilizado para obtener la hora de finalización de las actividades
     * de una edición de Expotécnica
     *
     * @param inicio fecha de inicio de la Expotécnica
     * @param edicion edición de Expotécnica
     * @param fin fecha de finalizacion de la Expotécnica
     */
    public void HorasFinActividades(String inicio, String edicion, String fin) {
        try {

            String sql = "select fechaFin from actividad where fechaInicio between (?) AND (?) AND edicion = ?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, inicio);
            st.setString(2, fin);
            st.setString(3, edicion);

            ResultSet rs = st.executeQuery();
            setHoraFin(new ArrayList<>());
            while (rs.next()) {
                getHoraFin().add(rs.getTime(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método utilizado para obtener el nombre de las actividades que existen.
     *
     * @param inicio fecha de inicio de la actividad
     * @param edicion edición de Expotécnica
     * @param fin fecha de finalización de la actividad
     */
    public void Actividades(String inicio, String edicion, String fin) {
        try {

            String sql = "select nombre from actividad where fechaInicio between (?) AND (?) AND edicion = ?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, inicio);
            st.setString(2, fin);
            st.setString(3, edicion);

            ResultSet rs = st.executeQuery();
            setNombreAct(new ArrayList<>());
            while (rs.next()) {
                getNombreAct().add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método utilizado para obtener el identificador de una actividad
     * seleccionada.
     *
     * @param nombre nombre de la actividad seleccionada.
     * @return retorna un valor entero, el identificador.
     */
    public int getIdActividad(String nombre) {
        try {
            String sql = "select idActividad from actividad where nombre = (?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, nombre);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
        return 0;
    }

    /**
     * Método utilizado para obtener la descripción de una actividad
     * seleccionada.
     *
     * @param id identificador de la actividad
     * @return retorna la descripción.
     */
    public String getDescripcionActividad(int id) {
        try {
            String sql = "select descripcion from actividad where idActividad = (?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        return "";
    }

    /**
     * Método utilizado para obtener la fecha de inicio de una actividad
     * seleccionada.
     *
     * @param id identificador de la actividad
     * @return retorna la descripción.
     */
    public Date getFechaInicioActividad(int id) {
        try {
            String sql = "select fechaInicio from actividad where idActividad = (?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getDate(1);
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        return null;
    }

    /**
     * Método utilizado para obtener la hora de inicio de una actividad
     * seleccionada
     *
     * @param id identificador de la actividad
     * @return retorna el valor en horas
     */
    public Time getHoraInicio2(int id) {
        try {
            String sql = "select fechaInicio from actividad where idActividad = (?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getTime(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Método utilizado para obtener la hora de inicio de una actividad
     * seleccionada
     *
     * @param id identificador de la actividad
     * @return retorna el valor en String
     */
    public String getHoraInicio(int id) {
        try {
            String sql = "select fechaInicio from actividad where idActividad = (?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "Vacio";
    }

    /**
     * Método utilizado para obtener la hora de fin de una actividad
     * seleccionada
     *
     * @param id identificador de la actividad
     * @return retorna el valor en String
     */
    public String getHoraFinString(int id) {
        try {
            String sql = "select fechaFin from actividad where idActividad = (?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "Vacio";
    }

    public String getFechaDia(String dia) {
        try {
            String sql = "select valor from opcionesGenerales where nombre = (?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, dia);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "vacio";
    }

    public String getMinAnioInicio() {
        try {
            String sql = "select left(valor, 4) from opcionesGenerales where nombre = 'actividadesFechaInicio'";
            PreparedStatement stm = cn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "vacio";
    }

    public String getMinMesInicio() {
        try {
            String sql = "select substring(valor, 6,2) as valor from opcionesGenerales where nombre = 'actividadesFechaInicio'";
            PreparedStatement stm = cn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "vacio";
    }

    public String getMaxMesInicio() {
        try {
            String sql = "select substring(valor, 6,2) as valor from opcionesGenerales where nombre = 'actividadesFechaFin'";
            PreparedStatement stm = cn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "vacio";
    }

    public String getMinDiaInicio() {
        try {
            String sql = "select substring(valor, 9,10) as valor from opcionesGenerales where nombre = 'actividadesFechaInicio'";
            PreparedStatement stm = cn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "vacio";
    }

    public String getMaxDiaInicio() {
        try {
            String sql = "select substring(valor, 9,10) as valor from opcionesGenerales where nombre = 'actividadesFechaFin'";
            PreparedStatement stm = cn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "vacio";
    }

    public String getNombreDia(String dia) {
        String d = dia;
        try {
            if (contador == 0) {

                boolean bandera = cambiarIdiomaSql(1);
                if (bandera == true) {
                    String sql = "Select datename(weekday, (?))";
                    PreparedStatement stm = cn.prepareStatement(sql);
                    stm.setString(1, d);
                    ResultSet rs = stm.executeQuery();
                    if (rs.next()) {
                        return rs.getString(1);
                    }
                } else if (bandera == false) {
                    String sql2 = "Select datename(weekday, (?))";
                    PreparedStatement stm2 = cn.prepareStatement(sql2);
                    stm2.setString(1, d);
                    ResultSet rs = stm2.executeQuery();
                    if (rs.next()) {
                        return rs.getString(1);
                    }
                }
            } else {
                String sql = "Select datename(weekday, (?))";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, d);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "vacio";
    }

    /**
     * @return the CantidadActividades
     */
    public int getCantidadActividades() {
        return CantidadActividades;
    }

    /**
     * @param CantidadActividades the CantidadActividades to set
     */
    public void setCantidadActividades(int CantidadActividades) {
        this.CantidadActividades = CantidadActividades;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Users">
    /**
     * Método utilizado para obtener el identificador de un usuario por medio de
     * email.
     *
     * @param email el correo del usuario.
     * @return retorna el identificador.
     */
    public int getIdUsuario(String email) {
        try {
            String query = "SELECT idUsuario FROM usuario WHERE email = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, email);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return 0;
    }

    /**
     * Método utilizado para insertar el PIN generado en la base de datos.
     *
     * @param pin el PIN generado por el sistema.
     * @param id el identificador de usuario.
     * @return retorna un valor booleano para ser utilizado en el controlador.
     */
    public boolean insertarPin(String pin, int id) {
        try {
            String query = "INSERT INTO recuperarClave (pin, idUsuario) "
                    + "VALUES (?,?)";
            PreparedStatement stm = cn.prepareStatement(query);
            stm.setString(1, pin);
            stm.setInt(2, id);

            if (stm.executeUpdate() > 0) {
                return true;
            }
//            stm.close();
//            cn.close();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        return false;
    }

    /**
     * Método utilizado para obtener el PIN de un usuario.
     *
     * @param id identificador del usuario.
     * @return retorna el PIN
     */
    public String getPin(int id) {
        try {
            String query = "SELECT pin FROM recuperarClave where idUsuario = ? order by idRecuperarClave desc";
            PreparedStatement stm = cn.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    //Copiado
    public String getNombreUsuario(int id) {
        String nombre = "";
        try {
            String sql = "SELECT nombreCompleto from usuario where idUsuario = (?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        return nombre;
    }

    public String getCorreoUsuario(int id) {
        String email = "";
        try {
            String sql = "SELECT email from usuario where idUsuario = (?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        return email;
    }

    public String getTipoUsuario(int id) {
        String tipoUsuario = "";
        try {
            String sql = "SELECT tipo from usuario, tipoUsuario where usuario.idTipoUsuario = tipoUsuario.idTipoUsuario and idUsuario = (?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        return tipoUsuario;
    }

    public String getEstadoUsuario(int id) {
        String estadoUsuario = "";
        try {
            String sql = "SELECT estado from usuario, estadoUsuario where usuario.idEstadoUsuario = estadoUsuario.idEstadoUsuario and idUsuario = (?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        return estadoUsuario;
    }
    //Copiado

    /**
     * Método utilizado para agregar un usuario nuevo.
     *
     * @param nombreCompleto nombre completo del usuario.
     * @param email correo único del usuario.
     * @param claveHash clave encriptada del usuario.
     * @param idTipoUsuario identificador del tipo de usuario.
     * @param idEstadoUsuario identificador del estado del usuario.
     * @return retorna un valor booleano para ser utilizado en el controlador.
     */
    public boolean agregarUsuario(String nombreCompleto, String email, String claveHash, int idTipoUsuario, int idEstadoUsuario) {
        boolean r = false;
        try {
            String query = "INSERT INTO usuario(nombreCompleto, email, clave, idTipoUsuario, idEstadoUsuario)"
                    + "VALUES(?,?,?,?,?)";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, nombreCompleto);
            cmd.setString(2, email);
            cmd.setString(3, claveHash);
            cmd.setInt(4, idTipoUsuario);
            cmd.setInt(5, idEstadoUsuario);
            if (cmd.executeUpdate() > 0) {
                r = true;
            }
            cmd.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return r;
    }

    /**
     * Método utilizado para actualizar el perfil de un usuario seleccionado.
     *
     * @param nombreCompleto nombre completo del usuario.
     * @param email correo único del usuario.
     * @param id identificador del usuario.
     * @return retorna un valor booleano para ser utilizado en el controlador.
     */
    public boolean actualizarPerfilUsuario(String nombreCompleto, String email, int id) {
        boolean r = false;
        try {
            String query = "UPDATE usuario SET nombreCompleto = ?, email = ? WHERE idUsuario = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, nombreCompleto);
            cmd.setString(2, email);
            cmd.setInt(3, id);
            if (cmd.executeUpdate() > 0) {
                r = true;
            }
            cmd.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return r;
    }

    public boolean usuarioExiste(String email) {
        try {
            String query = "SELECT * FROM usuario WHERE email = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, email);
            ResultSet rs = cmd.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return true;
        }
    }

    public String getHash(String email) {
        try {
            String query = "SELECT clave from usuario where email = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, email);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            } else {
                return "";
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return "";
        }
    }

    public String getHash(int id) {
        try {
            String query = "SELECT clave from usuario where idUsuario = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            } else {
                return "";
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return "";
        }
    }

    public ResultSet getUsuario(String email) {
        try {
            String query = "SELECT idUsuario, nombreCompleto, fotoPerfil, email, clave, idTipoUsuario, idEstadoUsuario FROM usuario where email = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, email);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                return rs;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    public ResultSet getUsuario(int id) {
        try {
            String query = "SELECT idUsuario, nombreCompleto, fotoPerfil, email, clave, idTipoUsuario, idEstadoUsuario FROM usuario where idUsuario = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                return rs;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    public int getIdTipoUsuario(String tipo) {
        try {
            String query = "SELECT idTipoUsuario FROM tipoUsuario WHERE tipo = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, tipo);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 3;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return 3;
        }
    }

    public int getIdEstadoUsuario(String estado) {
        try {
            String query = "SELECT idEstadoUsuario FROM estadoUsuario WHERE estado = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, estado);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 1;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return 1;
        }
    }

    public boolean actualizarContraUsuario(String hash, int id) {
        boolean r = false;
        try {
            String query = "UPDATE usuario SET clave = ? WHERE idUsuario = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, hash);
            cmd.setInt(2, id);
            if (cmd.executeUpdate() > 0) {
                r = true;
            }
            cmd.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return r;
    }

    public boolean actualizarContraUsuario2(String hash, String correo) {
        boolean r = false;

        int id = getIdUsuario(correo);
        try {
            String query = "UPDATE usuario SET clave = ? WHERE idUsuario = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, hash);
            cmd.setInt(2, id);
            if (cmd.executeUpdate() > 0) {
                r = true;
            }
            cmd.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return r;
    }

    public ResultSet sqlToCSV(String table) {
        try {
            String query = "SELECT * FROM " + table;
            PreparedStatement cmd = cn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                rs.beforeFirst();
                return rs;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error csv:" + e);
            return null;

        }
    }

    /*try {
                BufferedReader csvReader = new BufferedReader(new FileReader("C:\\Users\\Eduardo\\Documents\\prueba\\backup.csv"));
                String row;

                    while ((row = csvReader.readLine()) != null) {
                        String[] data = row.split(",");
                        System.out.println(data);
                        // do something with the data
                    }
                    csvReader.close();
                } catch (IOException ex) {
                    System.out.println(ex);
                }*/
    public boolean actualizarFotoPerfil(byte[] immAsBytes, int idUsuario) {
        try {
            PreparedStatement pstmt = cn.prepareStatement("UPDATE usuario SET fotoPerfil = ? WHERE idUsuario = ?");
            ByteArrayInputStream bais = new ByteArrayInputStream(immAsBytes);
            pstmt.setBinaryStream(1, bais, immAsBytes.length);
            pstmt.setInt(2, idUsuario);
            if (pstmt.executeUpdate() > 0) {
                return true;
            }
            pstmt.close();
            cn.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /**
     * Método utilizado para eliminar la foto de perfil de un usuario.
     *
     * @param idUsuario identificador del usuario.
     * @return retorna un valor booleno para ser utilizado en el controlador.
     */
    public boolean eliminarFotoPerfil(int idUsuario) {
        try {
            PreparedStatement pstmt = cn.prepareStatement("UPDATE usuario SET fotoPerfil = null WHERE idUsuario = ?");
            pstmt.setInt(1, idUsuario);
            if (pstmt.executeUpdate() > 0) {
                return true;
            }
            pstmt.close();
            cn.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    private int CantidadUsuarios;
    public ArrayList<Integer> idUsuario;
    public ArrayList<String> nombreCompleto;
    public ArrayList<String> email;
    public ArrayList<String> tipo;
    public ArrayList<String> fecha;
    public ArrayList<String> estado;

    /**
     * Método utilizado para obtener la cantidad de usuarios registrados.
     */
    public void NumUsuarios() {
        try {

            String sql = "select COUNT(idUsuario) from usuario";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                setCantidadUsuarios(rs.getInt(1));
                //System.out.println(getCantidadProyecto());
            }
        } catch (Exception e) {
        }
    }

    public int getUsuariosActivados() {
        int Num = 0;
        try {

            String sql = "select COUNT(idUsuario) from usuario where idEstadoUsuario = 1";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Num = (rs.getInt(1));
                //System.out.println(getCantidadProyecto());
                return Num;
            }
        } catch (Exception e) {
        }
        return Num;
    }

    /**
     * Método utilizado para mostrar los usuarios registrados.
     */
    public void MostrarUsuarios() {
        try {

            String sql = "select idUsuario,nombreCompleto, email, fechaRegistro, tipo, estado  from usuario"
                    + " u INNER JOIN estadoUsuario e on u.idEstadoUsuario=e.idEstadoUsuario INNER JOIN"
                    + " tipoUsuario t on u.idTipoUsuario=t.idTipoUsuario";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            idUsuario = new ArrayList<>();
            nombreCompleto = new ArrayList<>();
            email = new ArrayList<>();
            fecha = new ArrayList<>();
            tipo = new ArrayList<>();
            estado = new ArrayList<>();
            while (rs.next()) {

                idUsuario.add(rs.getInt(1));
                nombreCompleto.add(rs.getString(2));
                email.add(rs.getString(3));
                fecha.add(rs.getString(4));
                tipo.add(rs.getString(5));
                estado.add(rs.getString(6));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * @return the CantidadUsuarios
     */
    public int getCantidadUsuarios() {
        return CantidadUsuarios;
    }

    /**
     * @param CantidadUsuarios the CantidadUsuarios to set
     */
    public void setCantidadUsuarios(int CantidadUsuarios) {
        this.CantidadUsuarios = CantidadUsuarios;
    }

    // </editor-fold>
    public boolean cambiarIdiomaSql(int idioma) {
        boolean bandera = false;
        try {
            String sql = null;
            String espaniol = "spanish";
            String ingles = "us_english";
            if (idioma == 1) {
                sql = "Set language " + espaniol;
            } else if (idioma == 2) {
                sql = "Set language " + ingles;
            }
            Statement st = cn.createStatement();
            if (st.execute(sql)) {
                bandera = true;
                return bandera;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bandera;
    }

    public String getEdicion() {
        try {
            String sql = "select valor from opcionesGenerales where nombre = 'edicion'";
            PreparedStatement stm = cn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }

        } catch (SQLException e) {
            System.out.println("Error Global" + e.getMessage());
        }
        return "vacio";
    }

    public String getMapwizeAPIKey() {
        try {
            String query = "SELECT valor from opcionesGenerales WHERE nombre = 'MAPWIZE_API_KEY'";
            PreparedStatement cmd = cn.prepareStatement(query);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            } else {
                return "";
            }
        } catch (Exception e) {
            System.out.println("Error getMapWizeApiKey(): " + e);
            return "";
        }
    }

    public String getMapwizeVenueID() {
        try {
            String query = "SELECT valor from opcionesGenerales WHERE nombre = 'MAPWIZE_VENUE_ID'";
            PreparedStatement cmd = cn.prepareStatement(query);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            } else {
                return "";
            }
        } catch (Exception e) {
            System.out.println("Error getMapWizeVenueID(): " + e);
            return "";
        }
    }

    public boolean setMapwizeAPIKey(String value) {
        try {
            String query = "UPDATE opcionesGenerales set valor = ? WHERE nombre = 'MAPWIZE_API_KEY'";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, value);
            if (cmd.executeUpdate() > 0) {
                return true;
            }
            cmd.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }

    public boolean setMapwizeVenueID(String value) {
        try {
            String query = "UPDATE opcionesGenerales set valor = ? WHERE nombre = 'MAPWIZE_VENUE_ID'";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, value);
            if (cmd.executeUpdate() > 0) {
                return true;
            }
            cmd.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return false;
    }

    public boolean setFechas(String fecha1, String dia2, String dia3, String dia4, String fecha2) {
        try {
            String query = "UPDATE opcionesGenerales set valor = (?) WHERE nombre = 'actividadesFechaInicio'";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, fecha1);
            if (cmd.executeUpdate() > 0) {
                String query2 = "UPDATE opcionesGenerales set valor = (?) WHERE nombre = 'actividadesFechaFin'";
                PreparedStatement cmd2 = cn.prepareStatement(query2);
                cmd2.setString(1, fecha2);
                if (cmd2.executeUpdate() > 0) {
                    String query3 = "UPDATE opcionesGenerales set valor = (?) WHERE nombre = 'actividadesDia2'";
                    PreparedStatement cmd3 = cn.prepareStatement(query3);
                    cmd3.setString(1, dia2);
                    if (cmd3.executeUpdate() > 0) {
                        String query4 = "UPDATE opcionesGenerales set valor = (?) WHERE nombre = 'actividadesDia3'";
                        PreparedStatement cmd4 = cn.prepareStatement(query4);
                        cmd4.setString(1, dia3);
                        if (cmd4.executeUpdate() > 0) {
                            String query5 = "UPDATE opcionesGenerales set valor = (?) WHERE nombre = 'actividadesDia4'";
                            PreparedStatement cmd5 = cn.prepareStatement(query5);
                            cmd5.setString(1, dia4);
                            if (cmd5.executeUpdate() > 0) {
                                return true;
                            }
                        }
                    }

                }
            }
            cmd.close();
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return false;
    }

    public boolean setEdicion(String edicion) {
        try {
            String sql = "update opcionesGenerales set valor = (?) where nombre = 'edicion'";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, edicion);

            if (stm.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean loadPlacesToDb(List<String> places) {
        try {
            String delete = "DELETE FROM ubicacion;DBCC CHECKIDENT (ubicacion, RESEED, 0);";
            PreparedStatement cmd = cn.prepareStatement(delete);
            cmd.executeUpdate();
            cmd.close();
            for (String place : places) {
                String query = "INSERT INTO ubicacion VALUES(?)";
                PreparedStatement cmd1 = cn.prepareStatement(query);
                cmd1.setString(1, place);
                cmd1.executeUpdate();
                cmd1.close();
            }
            cn.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error: loadPlaces " + e);
        }

        return false;
    }

    public ResultSet getPlaces() {
        try {
            String query = "SELECT ubicacion from ubicacion";
            PreparedStatement cmd = cn.prepareStatement(query);
            ResultSet rs = cmd.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Error getMapWizeVenueID(): " + e);
        }
        return null;
    }

    public int getIdUbicacion(String ubicacion) {
        try {
            String query = "SELECT idUbicacion FROM ubicacion WHERE ubicacion = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, ubicacion);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error getIdUbicacion(): " + e);
        }
        return -1;
    }

    public int getIdAccionBitacora(String accion) {
        try {
            String query = "SELECT idAccionBitacora FROM accionBitacora WHERE accion = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, accion);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error getIdAccionBitacora(): " + e);
        }
        return -1;
    }

    public boolean agregarBitacora(int idAccion, int idUsuario) {
        try {
            String query = "INSERT INTO bitacora(idAccionBitacora, idUsuario) VALUES(?,?)";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setInt(1, idAccion);
            cmd.setInt(2, idUsuario);
            return (cmd.executeUpdate() > 0);
        } catch (Exception e) {
            System.out.println("Error agregarBitacora(): " + e);
        }
        return false;
    }
    //Parte de Proyectos
    private int CantidadProyecto;
    public ArrayList<Integer> PjId;
    public ArrayList<String> PjNombre;
    public ArrayList<String> PjNivel;
    public ArrayList<String> PjSeccion;
    public ArrayList<String> PjEspecialidad;
    public ArrayList<String> PjUbicacion;
    public ArrayList<String> PjDescripcion;
    public ArrayList<BufferedImage> PjImagenes;

    public void NumProyectos() {
        try {

            String sql = "select COUNT(idProyecto) from proyecto";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                setCantidadProyecto(rs.getInt(1));

            }
        } catch (Exception e) {
        }
    }

    public ResultSet ViewProyecto(int id) {
        try {
            String query = "select  nombre, nivel, seccion, especialidad, ubicacion, descripcion from proyecto p  INNER JOIN seccionNivel s on p.idSeccionNivel=s.idSeccionNivel\n"
                    + " INNER JOIN ubicacion u  on s.idUbicacion=u.idUbicacion INNER JOIN especialidad e on s.idEspecialidad=e.idEspecialidad INNER JOIN nivel n on s.idNivel\n"
                    + " =n.idNivel where idProyecto=?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                return rs;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    public ResultSet NumIntegrantes(int id) {
        try {
            String query = "select COUNT(idIntegranteEquipo) from integranteProyecto where idProyecto= ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                return rs;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    public ResultSet ViewIntegrantes(int id) {
        try {
            String query = "select nombreIntegrante from integranteProyecto where idProyecto= ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                return rs;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    public boolean actualizarProyecto(String nombre, String desc, int idSeccion, int id) {
        boolean r = false;
        try {
            String query = "update proyecto set nombre= ?, descripcion= ?, idSeccionNivel= ? where idProyecto= ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, nombre);
            cmd.setString(2, desc);
            cmd.setInt(3, idSeccion);
            cmd.setInt(4, id);
            if (cmd.executeUpdate() > 0) {
                r = true;
            }
            cmd.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return r;
    }

    public void Proyectos() {
        try {

            String sql = "select  idProyecto, nombre, nivel, seccion, especialidad, ubicacion, descripcion, fotoPortada from proyecto p  INNER JOIN seccionNivel s on p.idSeccionNivel=s.idSeccionNivel\n"
                    + " INNER JOIN ubicacion u  on s.idUbicacion=u.idUbicacion INNER JOIN especialidad e on s.idEspecialidad=e.idEspecialidad INNER JOIN nivel n on s.idNivel\n"
                    + " =n.idNivel";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            PjId = new ArrayList<>();
            PjNombre = new ArrayList<>();
            PjNivel = new ArrayList<>();
            PjSeccion = new ArrayList<>();
            PjEspecialidad = new ArrayList<>();
            PjUbicacion = new ArrayList<>();
            PjDescripcion = new ArrayList<>();
            PjImagenes = new ArrayList<>();
            Blob imgAsBlob;
            while (rs.next()) {
                PjId.add(rs.getInt(1));
                PjNombre.add(rs.getString(2));
                PjNivel.add(rs.getString(3));
                PjSeccion.add(rs.getString(4));
                PjEspecialidad.add(rs.getString(5));
                PjUbicacion.add(rs.getString(6));
                PjDescripcion.add(rs.getString(7));
                imgAsBlob = rs.getBlob(8);
                try {

                    if (imgAsBlob != null) {
                        byte[] imgAsBytes = imgAsBlob.getBytes(1, (int) imgAsBlob.length());
                        InputStream in = new ByteArrayInputStream(imgAsBytes);
                        BufferedImage imgFromDb = ImageIO.read(in);
                        PjImagenes.add(imgFromDb);
                    } else {
                        PjImagenes.add(null);

                    }

                } catch (Exception e) {

                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public ResultSet consulta(String sql) {
        ResultSet res = null;
        try {
            PreparedStatement pstm = cn.prepareStatement(sql);
            res = pstm.executeQuery();
        } catch (Exception e) {
        }
        return res;
    }

    public ArrayList<String> SNnivel;
    public ArrayList<String> SNespecialidad;
    public ArrayList<String> SNseccion;

    public void obtenerSeccionNivel() {
        try {

            String sql = "select nivel, especialidad, seccion from seccionNivel s INNER JOIN nivel n on s.idNivel=n.idNivel INNER JOIN especialidad e on s.idEspecialidad=e.idEspecialidad";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            SNnivel = new ArrayList<>();
            SNespecialidad = new ArrayList<>();
            SNseccion = new ArrayList<>();
            while (rs.next()) {
                SNnivel.add(rs.getString(1));
                SNespecialidad.add(rs.getString(2));
                SNseccion.add(rs.getString(3));
            }

            //for (int i = 0; i < SNseccion.size(); i++) {
            // System.out.println(d.get(i)+ a.get(i)+ c.get(i));
            //}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean agregarProyecto(String nombre, String descripcion, int idSeccionNivel) {
        boolean r = false;
        try {
            String query = "INSERT INTO proyecto (nombre, descripcion, idSeccionNivel) " + "VALUES (?,?,?);";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, nombre);
            cmd.setString(2, descripcion);
            cmd.setInt(3, idSeccionNivel);
            if (cmd.executeUpdate() > 0) {
                r = true;
            }
            cmd.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return r;
    }

    public boolean proyectoExiste(String nombre) {
        try {
            String query = "SELECT * FROM proyecto WHERE nombre = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, nombre);
            ResultSet rs = cmd.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return true;
        }
    }

    public boolean actualizarFotoProyecto(byte[] immAsBytes, int idProyecto) {
        try {
            PreparedStatement pstmt = cn.prepareStatement("UPDATE proyecto SET fotoPortada = ? WHERE idProyecto = ?");
            ByteArrayInputStream bais = new ByteArrayInputStream(immAsBytes);
            pstmt.setBinaryStream(1, bais, immAsBytes.length);
            pstmt.setInt(2, idProyecto);
            if (pstmt.executeUpdate() > 0) {
                return true;
            }
            pstmt.close();
            cn.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    private int CantidadEspecialidad;
    private ArrayList<Integer> idEspecialidad;
    private ArrayList<String> Especialidad;

    public void NumEspecialidades() {
        try {

            String sql = "select COUNT(idEspecialidad) from especialidad";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                setCantidadEspecialidad(rs.getInt(1));
                //System.out.println(getCantidadProyecto());
            }
        } catch (Exception e) {
        }
    }

    public void MostrarEspecialidad() {
        try {

            String sql = "select idEspecialidad, Especialidad from especialidad ";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            setIdEspecialidad(new ArrayList<>());
            setEspecialidad(new ArrayList<>());
            while (rs.next()) {

                getIdEspecialidad().add(rs.getInt(1));
                getEspecialidad().add(rs.getString(2));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public String getNombreEspecialidad(int id) {
        String nombre = "";
        try {
            String sql = "SELECT especialidad from especialidad where idEspecialidad = (?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        return nombre;
    }

    private int CantidadSecciones;
    private ArrayList<Integer> idSeccion;
    private ArrayList<String> Seccion;
    private ArrayList<String> Nivel_Seccion;
    private ArrayList<String> Especialidad_Seccion;
    private ArrayList<String> Ubicacion_Seccion;

    public void NumSeccion() {
        try {

            String sql = "select COUNT(idSeccionNivel) from seccionNivel";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                setCantidadSecciones(rs.getInt(1));
                //System.out.println(getCantidadProyecto());
            }
        } catch (Exception e) {
        }
    }

    public void MostrarSeccion() {
        try {

            String sql = "select idSeccionNivel, seccion, nivel, especialidad, ubicacion from seccionNivel,nivel, especialidad, ubicacion where seccionNivel.idNivel =  nivel.idNivel and seccionNivel.idEspecialidad = especialidad.idEspecialidad and seccionNivel.idUbicacion = ubicacion.idUbicacion";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            setIdSeccion(new ArrayList<>());
            setSeccion(new ArrayList<>());
            setNivel_Seccion(new ArrayList<>());
            setEspecialidad_Seccion(new ArrayList<>());
            setUbicacion_Seccion(new ArrayList<>());
            while (rs.next()) {

                getIdSeccion().add(rs.getInt(1));
                getSeccion().add(rs.getString(2));
                getNivel_Seccion().add(rs.getString(3));
                getEspecialidad_Seccion().add(rs.getString(4));
                getUbicacion_Seccion().add(rs.getString(5));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public String getNombreUbicacion(int id) {
        String nombre = "";
        try {
            String sql = "SELECT ubicacion from ubicacion where idUbicacion = (?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        return nombre;
    }

    public String getNombreSeccion(int id) {
        String nombre = "";
        try {
            String sql = "SELECT seccion from seccionNivel where idSeccionNivel = (?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        return nombre;
    }

    private int CantidadNiveles;
    private ArrayList<Integer> idNivel;
    private ArrayList<String> Nivel;

    public void NumNivel() {
        try {

            String sql = "select COUNT(idNivel) from nivel";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                setCantidadNiveles(rs.getInt(1));
                //System.out.println(getCantidadProyecto());
            }
        } catch (Exception e) {
        }
    }

    public void MostrarNivel() {
        try {

            String sql = "select idNivel, nivel from nivel";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            idNivel = new ArrayList<>();
            Nivel = new ArrayList<>();
            while (rs.next()) {

                idNivel.add(rs.getInt(1));
                Nivel.add(rs.getString(2));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public String getNombreNivel(int id) {
        String nombre = "";
        try {
            String sql = "SELECT nivel from nivel where idNivel = (?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        return nombre;
    }

    public String getNivel_Seccion(int id) {
        String tipoUsuario = "";
        try {
            String sql = "SELECT nivel from nivel, seccionNivel where seccionNivel.idNivel = nivel.idNivel and idSeccionNivel = (?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        return tipoUsuario;
    }

    public String getEspecialidad_Seccion(int id) {
        String tipoUsuario = "";
        try {
            String sql = "SELECT especialidad from especialidad, seccionNivel where seccionNivel.idEspecialidad = especialidad.idEspecialidad and idSeccionNivel = (?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        return tipoUsuario;
    }

    public String getUbicacion_Seccion(int id) {
        String tipoUsuario = "";
        try {
            String sql = "SELECT ubicacion from ubicacion, seccionNivel where seccionNivel.idUbicacion = ubicacion.idUbicacion and idSeccionNivel = (?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        return tipoUsuario;
    }

    /**
     * @return the CantidadProyecto
     */
    public int getCantidadProyecto() {
        return CantidadProyecto;
    }

    /**
     * @param CantidadProyecto the CantidadProyecto to set
     */
    public void setCantidadProyecto(int CantidadProyecto) {
        this.CantidadProyecto = CantidadProyecto;
    }

    /**
     * @return the CantidadEspecialidad
     */
    public int getCantidadEspecialidad() {
        return CantidadEspecialidad;
    }

    /**
     * @param CantidadEspecialidad the CantidadEspecialidad to set
     */
    public void setCantidadEspecialidad(int CantidadEspecialidad) {
        this.CantidadEspecialidad = CantidadEspecialidad;
    }

    /**
     * @return the idEspecialidad
     */
    public ArrayList<Integer> getIdEspecialidad() {
        return idEspecialidad;
    }

    /**
     * @param idEspecialidad the idEspecialidad to set
     */
    public void setIdEspecialidad(ArrayList<Integer> idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    /**
     * @return the Especialidad
     */
    public ArrayList<String> getEspecialidad() {
        return Especialidad;
    }

    /**
     * @param Especialidad the Especialidad to set
     */
    public void setEspecialidad(ArrayList<String> Especialidad) {
        this.Especialidad = Especialidad;
    }

    /**
     * @return the CantidadNiveles
     */
    public int getCantidadNiveles() {
        return CantidadNiveles;
    }

    /**
     * @param CantidadNiveles the CantidadNiveles to set
     */
    public void setCantidadNiveles(int CantidadNiveles) {
        this.CantidadNiveles = CantidadNiveles;
    }

    /**
     * @return the idNivel
     */
    public ArrayList<Integer> getIdNivel() {
        return idNivel;
    }

    /**
     * @param idNivel the idNivel to set
     */
    public void setIdNivel(ArrayList<Integer> idNivel) {
        this.idNivel = idNivel;
    }

    /**
     * @return the Nivel
     */
    public ArrayList<String> getNivel() {
        return Nivel;
    }

    /**
     * @param Nivel the Nivel to set
     */
    public void setNivel(ArrayList<String> Nivel) {
        this.Nivel = Nivel;
    }

    /**
     * @return the CantidadSecciones
     */
    public int getCantidadSecciones() {
        return CantidadSecciones;
    }

    /**
     * @param CantidadSecciones the CantidadSecciones to set
     */
    public void setCantidadSecciones(int CantidadSecciones) {
        this.CantidadSecciones = CantidadSecciones;
    }

    /**
     * @return the idSeccion
     */
    public ArrayList<Integer> getIdSeccion() {
        return idSeccion;
    }

    /**
     * @param idSeccion the idSeccion to set
     */
    public void setIdSeccion(ArrayList<Integer> idSeccion) {
        this.idSeccion = idSeccion;
    }

    /**
     * @return the Seccion
     */
    public ArrayList<String> getSeccion() {
        return Seccion;
    }

    /**
     * @param Seccion the Seccion to set
     */
    public void setSeccion(ArrayList<String> Seccion) {
        this.Seccion = Seccion;
    }

    /**
     * @return the Nivel_Seccion
     */
    public ArrayList<String> getNivel_Seccion() {
        return Nivel_Seccion;
    }

    /**
     * @param Nivel_Seccion the Nivel_Seccion to set
     */
    public void setNivel_Seccion(ArrayList<String> Nivel_Seccion) {
        this.Nivel_Seccion = Nivel_Seccion;
    }

    /**
     * @return the Especialidad_Seccion
     */
    public ArrayList<String> getEspecialidad_Seccion() {
        return Especialidad_Seccion;
    }

    /**
     * @param Especialidad_Seccion the Especialidad_Seccion to set
     */
    public void setEspecialidad_Seccion(ArrayList<String> Especialidad_Seccion) {
        this.Especialidad_Seccion = Especialidad_Seccion;
    }

    /**
     * @return the Ubicacion_Seccion
     */
    public ArrayList<String> getUbicacion_Seccion() {
        return Ubicacion_Seccion;
    }

    /**
     * @param Ubicacion_Seccion the Ubicacion_Seccion to set
     */
    public void setUbicacion_Seccion(ArrayList<String> Ubicacion_Seccion) {
        this.Ubicacion_Seccion = Ubicacion_Seccion;
    }

    /**
     * @return the nombreAct
     */
    public ArrayList<String> getNombreAct() {
        return nombreAct;
    }

    /**
     * @param nombreAct the nombreAct to set
     */
    public void setNombreAct(ArrayList<String> nombreAct) {
        this.nombreAct = nombreAct;
    }

    /**
     * @return the HoraInicio
     */
    public ArrayList<Time> getHoraInicio() {
        return HoraInicio;
    }

    /**
     * @param HoraInicio the HoraInicio to set
     */
    public void setHoraInicio(ArrayList<Time> HoraInicio) {
        this.HoraInicio = HoraInicio;
    }

    /**
     * @return the HoraFin
     */
    public ArrayList<Time> getHoraFin() {
        return HoraFin;
    }

    /**
     * @param HoraFin the HoraFin to set
     */
    public void setHoraFin(ArrayList<Time> HoraFin) {
        this.HoraFin = HoraFin;
    }
}
