/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Eduardo
 */
public class Db {

    private Connection cn;

    public Db() {
        cn = new DbConnection().conectar();
    }

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

    // <editor-fold defaultstate="collapsed" desc="User Methods">  
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

    // </editor-fold>  
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

    private int CantidadActividades;
    public ArrayList<String> nombreAct;

    public void NumActividades() {
        try {

            String sql = "select COUNT(idActividad) from actividad";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                setCantidadActividades(rs.getInt(1));
                //System.out.println(getNumProyecto());
            }
        } catch (Exception e) {
        }
    }

    public void Actividades() {
        try {

            String sql = "select nombre from actividad";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            nombreAct = new ArrayList<>();

            while (rs.next()) {

                nombreAct.add(rs.getString(1));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

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
}
