/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import rexsdesktop.CurrentUser;
import rexsdesktop.controller.Locations;

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

    public ArrayList<String> ubiArrayList;

    public HashMap cargarUbicacionesLogin() {
        try {
            HashMap<String, String> ubicaciones = new HashMap<String, String>();
            String sql = "select ubicacion from ubicacion";
            Statement cmd = cn.createStatement();
            ResultSet rs = cmd.executeQuery(sql);
            while (rs.next()) {
                String idUbicacion = rs.getString(1);
                ubicaciones.put(idUbicacion, Locations.getPlaceNameMapwize(idUbicacion));
            }
            return ubicaciones;
        } catch (Exception e) {
            System.out.println("Db.cargarUbicacionesLogin() error: " + e.getMessage());
        }
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="Projects">
    // <editor-fold defaultstate="collapsed" desc="Mostrar-Proyectos">
    public int CantidadProyecto;
    public ArrayList<Integer> PjId;
    public ArrayList<String> PjNombre;
    public ArrayList<String> PjNivel;
    public ArrayList<String> PjSeccion;
    public ArrayList<String> PjEspecialidad;
    public ArrayList<String> PjUbicacion;
    public ArrayList<String> PjEdicion;
    public ArrayList<String> PjDescripcion;
    public ArrayList<BufferedImage> PjImagenes;
    public ArrayList<BufferedImage> RecImagenes;
    public ArrayList<Integer> RecIdRecurso;

    public void NumProyectos(String edicion) {
        try {

            String sql = "select COUNT(idProyecto) from proyecto where edicion= ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, edicion);
            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                setCantidadProyecto(rs.getInt(1));

            }
        } catch (Exception e) {
            System.out.println("Aqui" + e.getMessage());
        }
    }

    public ResultSet ViewProyecto(int id) {
        try {
            String query = "select  nombre, nivel, seccion, especialidad, "
                    + "ubicacion, descripcion from proyecto p  INNER JOIN seccionNivel s on p.idSeccionNivel=s.idSeccionNivel "
                    + "INNER JOIN ubicacion u  on s.idUbicacion=u.idUbicacion INNER JOIN especialidad e on s.idEspecialidad=e.idEspecialidad "
                    + "INNER JOIN nivel n on s.idNivel=n.idNivel where idProyecto=?";
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

    public void getIMGresources(int id) {
        try {

            String sql = "select idRecurso, streamRecurso from recursoProyecto  where nombreRecurso like '%.jpg' and idProyecto=? or nombreRecurso like '%.png' and idProyecto=?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, id);
            cmd.setInt(2, id);
            ResultSet rs = cmd.executeQuery();
            RecIdRecurso = new ArrayList<>();
            RecImagenes = new ArrayList<>();
            Blob imgAsBlob;
            while (rs.next()) {
                RecIdRecurso.add(rs.getInt(1));
                imgAsBlob = rs.getBlob(2);
                try {
                    if (imgAsBlob != null) {
                        byte[] imgAsBytes = imgAsBlob.getBytes(1, (int) imgAsBlob.length());
                        InputStream in = new ByteArrayInputStream(imgAsBytes);
                        BufferedImage imgFromDb = ImageIO.read(in);
                        RecImagenes.add(imgFromDb);
                    } else {
                        RecImagenes.add(null);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public ResultSet getNumIMGresources(int id) {
        try {
            String query = "select COUNT(idRecurso) from recursoProyecto  where nombreRecurso like '%.jpg' and idProyecto=? or nombreRecurso like '%.png' and idProyecto=?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setInt(1, id);
            cmd.setInt(2, id);
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

    public void Proyectos(String edicion) {
        try {

            String sql = "select  idProyecto, nombre, nivel, seccion, especialidad, ubicacion, descripcion, edicion, fotoPortada from proyecto p  INNER JOIN seccionNivel s on p.idSeccionNivel=s.idSeccionNivel\n"
                    + " INNER JOIN ubicacion u  on s.idUbicacion=u.idUbicacion INNER JOIN especialidad e on s.idEspecialidad=e.idEspecialidad INNER JOIN nivel n on s.idNivel\n"
                    + " =n.idNivel where edicion = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, edicion);
            ResultSet rs = cmd.executeQuery();
            PjId = new ArrayList<>();
            PjNombre = new ArrayList<>();
            PjNivel = new ArrayList<>();
            PjSeccion = new ArrayList<>();
            PjEspecialidad = new ArrayList<>();
            PjUbicacion = new ArrayList<>();
            PjDescripcion = new ArrayList<>();
            PjImagenes = new ArrayList<>();
            PjEdicion = new ArrayList<>();
            Blob imgAsBlob;
            while (rs.next()) {
                PjId.add(rs.getInt(1));
                PjNombre.add(rs.getString(2));
                PjNivel.add(rs.getString(3));
                PjSeccion.add(rs.getString(4));
                PjEspecialidad.add(rs.getString(5));
                PjUbicacion.add(rs.getString(6));
                PjDescripcion.add(rs.getString(7));
                PjEdicion.add(rs.getString(8));
                imgAsBlob = rs.getBlob(9);
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
            System.out.println("Db " + e.getMessage());

        }
    }

    public double DetalleVoto;

    public void PromDetalleVotos(int idCriterio, int idProyecto) {
        try {

            String sql = "SELECT AVG(puntuacion) from detalleVotacion d INNER JOIN votacion v on d.idVotacion=v.idVotacion where idCriterioVotacion=(?) and idProyecto=(?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, idCriterio);
            cmd.setInt(2, idProyecto);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                DetalleVoto = rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.out.println("Db " + e.getMessage());

        }
    }
    public int COUNTVotosPorProyecto;

    public void COUNTvotos(int idProyecto) {
        try {

            String sql = "SELECT COUNT(idVotacion) from votacion where idProyecto=(?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, idProyecto);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                COUNTVotosPorProyecto = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Db " + e.getMessage());

        }
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

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Mostrar-Proyectos-Filtrados">
    public ArrayList<Integer> PjId2;
    public ArrayList<String> PjNombre2;
    public ArrayList<String> PjNivel2;
    public ArrayList<String> PjSeccion2;
    public ArrayList<String> PjEspecialidad2;
    public ArrayList<String> PjUbicacion2;
    public ArrayList<String> PjDescripcion2;
    public ArrayList<BufferedImage> PjImagenes2;

    public void FiltroProyectos(String nivel, String especialidad, String seccion, String edicion) {
        try {

            String sql = "select  idProyecto, nombre, nivel, seccion, especialidad, ubicacion, descripcion, fotoPortada from proyecto p  INNER JOIN seccionNivel s on p.idSeccionNivel=s.idSeccionNivel\n"
                    + " INNER JOIN ubicacion u  on s.idUbicacion=u.idUbicacion INNER JOIN especialidad e on s.idEspecialidad=e.idEspecialidad INNER JOIN nivel n on s.idNivel\n"
                    + " =n.idNivel where nivel=(?) and especialidad=(?) and seccion=(?) and edicion=(?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, nivel);
            cmd.setString(2, especialidad);
            cmd.setString(3, seccion);
            cmd.setString(4, edicion);
            ResultSet rs = cmd.executeQuery();
            PjId2 = new ArrayList<>();
            PjNombre2 = new ArrayList<>();
            PjNivel2 = new ArrayList<>();
            PjSeccion2 = new ArrayList<>();
            PjEspecialidad2 = new ArrayList<>();
            PjUbicacion2 = new ArrayList<>();
            PjDescripcion2 = new ArrayList<>();
            PjImagenes2 = new ArrayList<>();
            Blob imgAsBlob;
            while (rs.next()) {
                PjId2.add(rs.getInt(1));
                PjNombre2.add(rs.getString(2));
                PjNivel2.add(rs.getString(3));
                PjSeccion2.add(rs.getString(4));
                PjEspecialidad2.add(rs.getString(5));
                PjUbicacion2.add(rs.getString(6));
                PjDescripcion2.add(rs.getString(7));
                imgAsBlob = rs.getBlob(8);
                try {
                    if (imgAsBlob != null) {
                        byte[] imgAsBytes = imgAsBlob.getBytes(1, (int) imgAsBlob.length());
                        InputStream in = new ByteArrayInputStream(imgAsBytes);
                        BufferedImage imgFromDb = ImageIO.read(in);
                        PjImagenes2.add(imgFromDb);
                    } else {
                        PjImagenes2.add(null);

                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public ResultSet NumProyectosFiltrados(String nivel, String especialidad, String seccion, String edicion) {
        try {
            String query = "Select COUNT(idProyecto) from proyecto p  INNER JOIN seccionNivel s on p.idSeccionNivel=s.idSeccionNivel "
                    + "INNER JOIN ubicacion u  on s.idUbicacion=u.idUbicacion INNER JOIN especialidad e on s.idEspecialidad=e.idEspecialidad "
                    + "INNER JOIN nivel n on s.idNivel=n.idNivel where nivel =(?) and especialidad=(?) and seccion=(?) and edicion=(?)";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, nivel);
            cmd.setString(2, especialidad);
            cmd.setString(3, seccion);
            cmd.setString(4, edicion);
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

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="MostrarMejoresProyectos">
    public ArrayList<Double> promedioSimple;
    public ArrayList<String> NombreMejorProyecto;
    public ArrayList<String> EspecialidadMejorProyecto;
    public ArrayList<Integer> CantidadVotosMejorProyecto;

    public void ViewMejoresProyectos(String edicion) {
        try {

            String sql = "SELECT TOP 4 AVG(promedioSimple), nombre,especialidad,COUNT(idVotacion) "
                    + "from votacion v INNER JOIN proyecto p on v.idProyecto=p.idProyecto INNER JOIN "
                    + "seccionNivel s on p.idSeccionNivel=s.idSeccionNivel INNER JOIN especialidad e on "
                    + "s.idEspecialidad=e.idEspecialidad where edicion=(?) group by p.nombre,e.especialidad "
                    + "order by AVG(promedioSimple) desc";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, edicion);
            ResultSet rs = cmd.executeQuery();
            promedioSimple = new ArrayList<>();
            NombreMejorProyecto = new ArrayList<>();
            EspecialidadMejorProyecto = new ArrayList<>();
            CantidadVotosMejorProyecto = new ArrayList<>();
            while (rs.next()) {
                promedioSimple.add(rs.getDouble(1));
                NombreMejorProyecto.add(rs.getString(2));
                EspecialidadMejorProyecto.add(rs.getString(3));
                CantidadVotosMejorProyecto.add(rs.getInt(4));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public int countVotos;

    public void CountVotosMejoresProyectos(String edicion) {
        try {

            String sql = "select COUNT(idvotacion) from votacion v INNER JOIN proyecto p on v.idProyecto=p.idProyecto  where edicion=?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, edicion);
            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                countVotos = rs.getInt(1);
            }
            System.out.println(countVotos);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public int Prom;

    public void PromProyecto(int id) {
        try {

            String sql = "SELECT AVG(promedioSimple)from votacion where idProyecto=(?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                Prom = rs.getInt(1);
            }
            System.out.println(Prom);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="CRUD-Projects">
    public boolean agregarProyecto(String nombre, String descripcion, String edicion, int idSeccionNivel, byte[] immAsBytes) {
        boolean r = false;
        try {
            String query = "INSERT INTO proyecto (nombre, descripcion, edicion, idSeccionNivel, fotoportada) " + "VALUES (?,?,?,?,?);";
            PreparedStatement cmd = cn.prepareStatement(query);
            ByteArrayInputStream bais = new ByteArrayInputStream(immAsBytes);
            cmd.setString(1, nombre);
            cmd.setString(2, descripcion);
            cmd.setString(3, edicion);
            cmd.setInt(4, idSeccionNivel);
            cmd.setBinaryStream(5, bais, immAsBytes.length);
            if (cmd.executeUpdate() > 0) {
                r = true;
            }
            cmd.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Error db : " + e);
        }

        return r;
    }

    public boolean agregarProyectoSinImagen(String nombre, String descripcion, String edicion, int idSeccionNivel) {
        boolean r = false;
        try {
            String query = "INSERT INTO proyecto (nombre, descripcion, edicion, idSeccionNivel) " + "VALUES (?,?,?,?);";
            PreparedStatement cmd = cn.prepareStatement(query);

            cmd.setString(1, nombre);
            cmd.setString(2, descripcion);
            cmd.setString(3, edicion);
            cmd.setInt(4, idSeccionNivel);
            if (cmd.executeUpdate() > 0) {
                r = true;
            }
            cmd.close();
            cn.close();
        } catch (Exception e) {
            System.out.println("Error db : " + e);
        }

        return r;
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

    public boolean eliminarProyecto(int id) {
        boolean bandera = false;
        try {
            String sql = "DELETE FROM proyecto WHERE idProyecto = (?)";
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

    public boolean proyectoExiste(String nombre) {
        try {
            String query = "SELECT * FROM proyecto WHERE nombre = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, nombre);
            ResultSet rs = cmd.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("proyectoExiste : " + e);
            return true;
        }
    }

    public boolean agregarRecursoImg(String nombreRecurso, int tamanoRecurso, byte[] streamRecurso, int idP) {
        boolean r = false;
        try {
            String query = "INSERT INTO recursoProyecto (nombreRecurso, tamanoRecurso, streamRecurso, idProyecto) " + "VALUES (?,?,?,?);";
            PreparedStatement cmd = cn.prepareStatement(query);
            ByteArrayInputStream bais = new ByteArrayInputStream(streamRecurso);
            cmd.setString(1, nombreRecurso);
            cmd.setInt(2, tamanoRecurso);
            cmd.setBinaryStream(3, bais, streamRecurso.length);
            cmd.setInt(4, idP);
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

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="SeccionNivel">
    public ArrayList<String> SNnivel;
    public ArrayList<String> SNespecialidad;
    public ArrayList<String> SNseccion;

    public void obtenerNivel() {
        try {

            String sql = "select nivel from nivel";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            SNnivel = new ArrayList<>();
            while (rs.next()) {
                SNnivel.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void obtenerEspecialidad() {
        try {

            String sql = "select especialidad from especialidad";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            SNespecialidad = new ArrayList<>();
            while (rs.next()) {
                SNespecialidad.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void obtenerSeccion(String nivel, String Especialidad) {
        try {
            String query = "select seccion from seccionNivel s INNER JOIN nivel n on s.idNivel=n.idNivel INNER JOIN especialidad e on e.idEspecialidad=s.idEspecialidad where nivel=(?) and especialidad=(?)";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, nivel);
            cmd.setString(2, Especialidad);
            ResultSet rs = cmd.executeQuery();
            SNseccion = new ArrayList<>();
            while (rs.next()) {
                SNseccion.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);

        }
    }

    public ResultSet getIdSeccionNivel(String nivel, String especialidad, String seccion) {

        try {
            String sql = "select idSeccionNivel from seccionNivel s INNER JOIN nivel n on s.idNivel=n.idNivel INNER JOIN especialidad e on e.idEspecialidad=s.idEspecialidad where nivel=(?) and especialidad=(?) and seccion=(?)";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, nivel);
            cmd.setString(2, especialidad);
            cmd.setString(3, seccion);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                return rs;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("ERROR" + e.getLocalizedMessage());
        }

        return null;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Integrantes">
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
    public ArrayList<String> nombreInte;

    public void ViewIntegrantes(int id) {
        try {
            String query = "select nombreIntegrante from integranteProyecto where idProyecto= ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();

            nombreInte = new ArrayList<>();
            while (rs.next()) {

                nombreInte.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);

        }
    }

    public boolean agregarIntegrante(String nombre, int id) {
        boolean r = false;
        try {
            String query = "INSERT INTO integranteProyecto (nombreIntegrante, idProyecto) " + "VALUES (?,?);";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, nombre);
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

    public boolean IntegranteExiste(String nombre) {
        try {
            String query = "SELECT * FROM integranteProyecto WHERE nombreIntegrante = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, nombre);
            ResultSet rs = cmd.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return true;
        }
    }

    public boolean actualizarIntegrante(String nombreAct, String nombre, int idP) {
        boolean r = false;
        try {
            String query = "update integranteProyecto set nombreIntegrante= ? where nombreIntegrante= ? and idProyecto= ? ";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, nombreAct);
            cmd.setString(2, nombre);
            cmd.setInt(3, idP);
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

    // </editor-fold>
    // </editor-fold>
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
    public boolean agregarActividad(String nombre, String descripcion, String fechaInicio, String edicion, String fechaFin, String encargado, int idUbicacion) {
        boolean bandera = false;
        try {
            String sql = "INSERT INTO actividad (nombre, descripcion, fechaInicio, fechaFin, edicion, encargado, idUbicacion) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, nombre);
            stm.setString(2, descripcion);
            stm.setString(3, fechaInicio);
            stm.setString(4, fechaFin);
            stm.setString(5, edicion);
            stm.setString(6, encargado);
            stm.setInt(7, idUbicacion);

            if (stm.executeUpdate() > 0) {
                bandera = true;
            }
        } catch (SQLException e) {
            System.out.println("ERROR al agregar" + e);
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
    public boolean actualizarActividad(String nombre, String descripcion, String fechaInicio, String fechaFin, String encargado, int idUbicacion, int id) {
        boolean bandera = false;
        try {
            String sql = "UPDATE actividad set nombre = (?), descripcion = (?), fechaInicio = (?), fechaFin = (?),encargado = (?), idUbicacion = (?) where idActividad = (?)";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, nombre);
            stm.setString(2, descripcion);
            stm.setString(3, fechaInicio);
            stm.setString(4, fechaFin);
            stm.setString(5, encargado);
            stm.setInt(6, idUbicacion);
            stm.setInt(7, id);

            if (stm.executeUpdate() > 0) {
                bandera = true;
            }

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
     * Método utilizado para obtener la descripción de una actividad
     * seleccionada.
     *
     * @param id identificador de la actividad
     * @return retorna la descripción.
     */
    public String getUbicacion(int id) {
        try {
            String sql = "select ubicacion from actividad ac inner join ubicacion ubi on ac.idUbicacion = ubi.idUbicacion where idActividad = (?)";
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

    public String getEncargadoActividad(int id) {
        try {
            String sql = "select encargado from actividad where idActividad = (?)";
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
    private int CantidadUsuarios2;
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
            int tipoU = CurrentUser.idTipoUsuario;
            String sql;

            sql = "select COUNT(idUsuario) from usuario where idTipoUsuario != " + 1 + "and idUsuario != " + CurrentUser.idUsuario;
            String sql2 = "select COUNT(idUsuario) from usuario where idUsuario !=" + tipoU;
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                setCantidadUsuarios(rs.getInt(1));
                //System.out.println(getCantidadProyecto());
            }

            Statement st2 = cn.createStatement();
            ResultSet rs2 = st.executeQuery(sql2);

            while (rs2.next()) {
                setCantidadUsuarios2(rs2.getInt(1));
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

            int tipoU = CurrentUser.idTipoUsuario;
            String sql;
            if (tipoU != 1) {
                sql = "select idUsuario,nombreCompleto, email, fechaRegistro, tipo, estado  from usuario u INNER JOIN estadoUsuario e on u.idEstadoUsuario=e.idEstadoUsuario INNER JOIN tipoUsuario t on u.idTipoUsuario=t.idTipoUsuario and u.idTipoUsuario != " + 1 + "and u.idUsuario != " + CurrentUser.idUsuario;
            } else {
                sql = "select idUsuario,nombreCompleto, email, fechaRegistro, tipo, estado  from usuario u INNER JOIN estadoUsuario e on u.idEstadoUsuario=e.idEstadoUsuario INNER JOIN tipoUsuario t on u.idTipoUsuario=t.idTipoUsuario and u.idUsuario != " + CurrentUser.idUsuario;
            }

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
    private int NivelSeleccionado;
    private ArrayList<Integer> idSeccion;
    private ArrayList<String> Seccion;
    private ArrayList<String> Nivel_Seccion;
    private ArrayList<String> Especialidad_Seccion;
    private ArrayList<String> Ubicacion_Seccion;

    public ResultSet NumSeccion(int idNiv) {
        boolean respuesta = false;
        try {
            String sql = "select COUNT(idSeccionNivel) from seccionNivel where idNivel = ?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, idNiv);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                return rs;
            } else {
                return null;
            }
        } catch (Exception e) {
        }
        return null;

    }

    public void MostrarSeccion(int idNiv) {
        try {

            String sql = "select idSeccionNivel, seccion, nivel, especialidad, ubicacion from seccionNivel,nivel, especialidad, ubicacion "
                    + "where seccionNivel.idNivel =  nivel.idNivel and seccionNivel.idEspecialidad = especialidad.idEspecialidad and seccionNivel.idUbicacion = ubicacion.idUbicacion"
                    + " and seccionNivel.idNivel=?";
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setInt(1, idNiv);
            ResultSet rs = cmd.executeQuery();

            idSeccion = new ArrayList<>();
            Seccion = new ArrayList<>();
            Nivel_Seccion = new ArrayList<>();
            Especialidad_Seccion = new ArrayList<>();
            Ubicacion_Seccion = new ArrayList<>();
            while (rs.next()) {

                idSeccion.add(rs.getInt(1));
                Seccion.add(rs.getString(2));
                Nivel_Seccion.add(rs.getString(3));
                Especialidad_Seccion.add(rs.getString(4));
                Ubicacion_Seccion.add(rs.getString(5));

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
    //Filtrados
    private int CantidadUsuariosFiltrados;
    private String nombreFiltrado;
    private int estadoFiltrado;
    private int tipoFiltrado;

    public ArrayList<Integer> idUsuario2;
    public ArrayList<String> nombreCompleto2;
    public ArrayList<String> email2;
    public ArrayList<String> tipo2;
    public ArrayList<String> fecha2;
    public ArrayList<String> estado2;

    //Filtrar
    public ResultSet NumUsuariosFiltrados(String nombre, String idE, String idT) {
        boolean respuesta = false;
        try {
            String sql = "select COUNT(idUsuario)  from usuario, estadoUsuario, tipoUsuario where usuario.idTipoUsuario=tipoUsuario.idTipoUsuario and usuario.idEstadoUsuario=estadoUsuario.idEstadoUsuario and estadoUsuario.estado=? and tipoUsuario.tipo = ? and nombreCompleto like ? and idUsuario != " + CurrentUser.idUsuario;
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, idE);
            cmd.setString(2, idT);
            cmd.setString(3, "%" + nombre + "%");
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                return rs;
            } else {
                return null;
            }
        } catch (Exception e) {
        }
        return null;

    }

    public void MostrarUsuariosFiltrados(String nombre, String idE, String idT) {
        try {

            String sql = "select idUsuario,nombreCompleto, email, fechaRegistro, tipo, estado  from usuario, estadoUsuario, tipoUsuario where usuario.idTipoUsuario=tipoUsuario.idTipoUsuario and usuario.idEstadoUsuario=estadoUsuario.idEstadoUsuario and estadoUsuario.estado=? and tipoUsuario.tipo =? and nombreCompleto like ? and idUsuario != " + CurrentUser.idUsuario;
            PreparedStatement cmd = cn.prepareStatement(sql);
            cmd.setString(1, idE);
            cmd.setString(2, idT);
            cmd.setString(3, "%" + nombre + "%");
            ResultSet rs = cmd.executeQuery();

            idUsuario2 = new ArrayList<>();
            nombreCompleto2 = new ArrayList<>();
            email2 = new ArrayList<>();
            fecha2 = new ArrayList<>();
            tipo2 = new ArrayList<>();
            estado2 = new ArrayList<>();
            while (rs.next()) {

                idUsuario2.add(rs.getInt(1));
                nombreCompleto2.add(rs.getString(2));
                email2.add(rs.getString(3));
                fecha2.add(rs.getString(4));
                tipo2.add(rs.getString(5));
                estado2.add(rs.getString(6));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    //Filtrar
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

    public int usuariosNuevosHoy() {
        try {
            String query = "SELECT COUNT(idUsuario) from usuario where fechaRegistro BETWEEN DATEADD(dd, 0, DATEDIFF(dd, 0, GETDATE())) AND GETDATE()";
            PreparedStatement cmd = cn.prepareStatement(query);
            ResultSet rs = cmd.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return 0;
    }

    public int usuariosNuevosAyer() {
        try {
            String query = "SELECT COUNT(idUsuario) from usuario where fechaRegistro BETWEEN DATEADD(dd, 0, DATEDIFF(dd, 1, GETDATE())) AND DATEADD(dd, 0, DATEDIFF(dd, 0, GETDATE()))";
            PreparedStatement cmd = cn.prepareStatement(query);
            ResultSet rs = cmd.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return 0;
    }

    public int votosNuevosHoy() {
        try {
            String query = "SELECT COUNT(idVotacion) from votacion where fecha BETWEEN DATEADD(dd, 0, DATEDIFF(dd, 0, GETDATE())) AND GETDATE()";
            PreparedStatement cmd = cn.prepareStatement(query);
            ResultSet rs = cmd.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return 0;
    }

    public int votosNuevosAyer() {
        try {
            String query = "SELECT COUNT(idVotacion) from votacion where fecha BETWEEN DATEADD(dd, 0, DATEDIFF(dd, 1, GETDATE())) AND DATEADD(dd, 0, DATEDIFF(dd, 0, GETDATE()))";
            PreparedStatement cmd = cn.prepareStatement(query);
            ResultSet rs = cmd.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return 0;
    }

    public int countIniciosSesion(int diffHora, int diffDia) {
        try {
            String query = "declare @dt datetime;select @dt = cast(cast(getdate() as date) as datetime)+cast(datepart(hour,getdate()) as float)/24;SELECT COUNT(*) from bitacora where fecha BETWEEN DATEADD(dd, ?, DATEADD(hh,?,@dt)) AND DATEADD(dd, ?, DATEADD(hh,?,@dt)) AND idAccionBitacora = 1";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setInt(1, diffDia);
            cmd.setInt(2, diffHora - 1);
            cmd.setInt(3, diffDia);
            cmd.setInt(4, diffHora);
            ResultSet rs = cmd.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return 0;
    }

    public String horaInicioSesion(int diff) {
        try {
            String query = "declare @dt datetime;select @dt = cast(cast(getdate() as date) as datetime)+cast(datepart(hour,getdate()) as float)/24;SELECT CONVERT(VARCHAR(5),DATEADD(hh,?,@dt),108)";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setInt(1, diff);
            ResultSet rs = cmd.executeQuery();
            rs.next();
            return rs.getString(1);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return "";
    }

    public int countUsuarios() {
        try {
            String query = "SELECT COUNT(idUsuario) from usuario";
            PreparedStatement cmd = cn.prepareStatement(query);
            ResultSet rs = cmd.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return 0;
    }

    public int countVotos() {
        try {
            String query = "SELECT COUNT(idVotacion) from votacion";
            PreparedStatement cmd = cn.prepareStatement(query);
            ResultSet rs = cmd.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return 0;
    }

    public int countProyectos() {
        try {
            String query = "SELECT COUNT(idProyecto) from proyecto";
            PreparedStatement cmd = cn.prepareStatement(query);
            ResultSet rs = cmd.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return 0;
    }

    public int countUbicacion() {
        try {
            String query = "SELECT COUNT(idUbicacion) from ubicacion";
            PreparedStatement cmd = cn.prepareStatement(query);
            ResultSet rs = cmd.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return 0;
    }

    public int countUsuarios(int idTipoUsuario) {
        try {
            String query = "SELECT COUNT(idUsuario) from usuario where idTipoUsuario = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setInt(1, idTipoUsuario);
            ResultSet rs = cmd.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return 0;
    }

    public ResultSet tiposUsuario() {
        try {
            String query = "SELECT idTipoUsuario, tipo from tipoUsuario";
            PreparedStatement cmd = cn.prepareStatement(query);
            return cmd.executeQuery();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    /**
     * @return the NivelSeleccionado
     */
    public int getNivelSeleccionado() {
        return NivelSeleccionado;
    }

    /**
     * @param NivelSeleccionado the NivelSeleccionado to set
     */
    public void setNivelSeleccionado(int NivelSeleccionado) {
        this.NivelSeleccionado = NivelSeleccionado;
    }

    /**
     * @return the CantidadUsuarios2
     */
    public int getCantidadUsuarios2() {
        return CantidadUsuarios2;
    }

    /**
     * @param CantidadUsuarios2 the CantidadUsuarios2 to set
     */
    public void setCantidadUsuarios2(int CantidadUsuarios2) {
        this.CantidadUsuarios2 = CantidadUsuarios2;
    }

    public int getIdCriterioVotacion(String criterio) {
        try {
            String query = "SELECT idCriterioVotacion FROM criterioVotacion where criterio=?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setString(1, criterio);
            ResultSet rs = cmd.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return -1;
    }

    public int getIdVotacion(int idUsuario, int idProyecto) {
        try {
            String query = "SELECT idVotacion FROM votacion WHERE idUsuario= ? AND idProyecto = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setInt(1, idUsuario);
            cmd.setInt(2, idProyecto);
            ResultSet rs = cmd.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return -1;
    }

    public boolean addVotacion(int idUsuario, int idProyecto) {
        try {
            String query = "INSERT INTO votacion(idUsuario, idProyecto) VALUES(?, ?)";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setInt(1, idUsuario);
            cmd.setInt(2, idProyecto);
            if (cmd.executeUpdate() > 0) {
                cmd.close();
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }

    public boolean addDetalleVotacion(double puntuacion, int idCriterioVotacion, int idVotacion) {
        try {
            String query = "INSERT INTO detalleVotacion (puntuacion, idCriterioVotacion, idVotacion) VALUES (?,?,?)";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setDouble(1, puntuacion);
            cmd.setInt(2, idCriterioVotacion);
            cmd.setInt(3, idVotacion);
            if (cmd.executeUpdate() > 0) {
                cmd.close();
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }

    public boolean addPromedio(double promedio, int idVotacion) {
        try {
            String query = "UPDATE votacion set promedioSimple = ? WHERE idVotacion = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setDouble(1, promedio);
            cmd.setInt(2, idVotacion);
            if (cmd.executeUpdate() > 0) {
                cmd.close();
                cn.close();
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }

    public double getPromedioFinalVoto(int idUsuario, int idProyecto) {
        try {
            String query = "SELECT promedioSimple FROM votacion WHERE idUsuario= ? AND idProyecto = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setInt(1, idUsuario);
            cmd.setInt(2, idProyecto);
            ResultSet rs = cmd.executeQuery();
            rs.next();
            return rs.getDouble(1);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return -1;
    }

    public boolean existeVoto(int idUsuario, int idProyecto) {
        try {
            String query = "SELECT * FROM votacion WHERE idUsuario= ? AND idProyecto = ?";
            PreparedStatement cmd = cn.prepareStatement(query);
            cmd.setInt(1, idUsuario);
            cmd.setInt(2, idProyecto);
            ResultSet rs = cmd.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }
}
