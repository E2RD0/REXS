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
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;

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
    /**
     * Método utilizado para agregar una nueva actividad.
     *
     * @param nombre el nombre de la actividad a agregar.
     * @param descripcion la descripcion de la actividad a agregar.
     * @param fechaInicio fecha de inicio de la actividad a agregar.
     * @param fechaFin fecha de finalización de la actividad a agregar.
     * @param idUbicacion identificador de la ubicación de la actividad a
     * agregar.
     * @return retorna un valor booleano para ser utilizado en el controlador.
     */
    public boolean agregarActividad(String nombre, String descripcion, String fechaInicio, String fechaFin, int idUbicacion) {
        boolean bandera = false;
        try {
            String sql = "INSERT INTO actividad (nombre, descripcion, fechaInicio, fechaFin, idUbicacion) VALUES (?,?,?,?,?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, nombre);
            stm.setString(2, descripcion);
            stm.setString(3, fechaInicio);
            stm.setString(4, fechaFin);
            stm.setInt(5, idUbicacion);

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

        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }
        return bandera;
    }

    private int CantidadActividades;
    public ArrayList<String> nombreAct;

    /**
     * Método utilizado para obtener el número de actividades que existen.
     *
     * @param inicio fecha de inicio de la actividad
     * @param fin fecha de finalización de la actividad
     */
    public void NumActividades(String inicio, String fin) {
        try {

            String sql = "select COUNT(idActividad) from actividad where fechaInicio between ? AND ?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, inicio);
            st.setString(2, fin);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                setCantidadActividades(rs.getInt(1));
                //System.out.println(getNumProyecto());
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    /**
     * Método utilizado para obtener el nombre de las actividades que existen.
     *
     * @param inicio fecha de inicio de la actividad
     * @param fin fecha de finalización de la actividad
     */
    public void Actividades(String inicio, String fin) {
        try {

            String sql = "select nombre from actividad where fechaInicio between (?) AND (?)";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, inicio);
            st.setString(2, fin);

            ResultSet rs = st.executeQuery();
            nombreAct = new ArrayList<>();

            while (rs.next()) {

                nombreAct.add(rs.getString(1));

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
        } catch (Exception e) {
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

//    public String getHoraInicioActividad(int id) {
//        try {
//            String sql = "SELECT (convert(time, fechaInicio, 1)) from actividad where idActividad = (?)";
//            PreparedStatement stm = cn.prepareStatement(sql);
//            stm.setInt(1, id);
//            ResultSet rs = stm.executeQuery();
//            if (rs.next()) {
//                return rs.getString(1);
//            }
//        } catch (Exception e) {
//            System.out.println("ERROR");
//        }
//        return null;
//    }
//
//    public Date getHoraFinActividad(int id) {
//        try {
//            String sql = "SELECT (convert(time, fechaFin, 1)) from actividad where idActividad = (?)";
//            PreparedStatement stm = cn.prepareStatement(sql);
//            stm.setInt(1, id);
//            ResultSet rs = stm.executeQuery();
//            if (rs.next()) {
//                return rs.getDate(1);
//            }
//        } catch (Exception e) {
//            System.out.println("ERROR");
//        }
//        return null;
//    }
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
     * Método utilizado para obtener el identificador de un usuario por medio de email.
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
            stm.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        return false;
    }

    /**
     * Método utilizado para obtener el PIN de un usuario.
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

    /**
     * Método utilizado para agregar un usuario nuevo.
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

    public boolean setFechas(String fecha1, String fecha2) {
        try {
            String query = "UPDATE opcionesGenerales set valor = (?) WHERE nombre = 'actividadesFechaInicio'";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, fecha1);
            if (cmd.executeUpdate() > 0) {
                String query2 = "UPDATE opcionesGenerales set valor = (?) WHERE nombre = 'actividadesFechaFin'";
                PreparedStatement cmd2 = cn.prepareStatement(query2);
                cmd2.setString(1, fecha2);
                if (cmd2.executeUpdate() > 0) {
                    return true;
                }
            }
            cmd.close();
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
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
}
