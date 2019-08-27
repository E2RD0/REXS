/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import rexsdesktop.model.DbConnection;

/**
 * Clase que contiene los atributos y métodos de una sección.
 * @author artur
 */
public class Sections {

    //CRUD SECCION
    private Connection cn;
    private String seccion;
    private int idSeccion;
    private int idNivel;
    private int idEspecialidad;
    private int idUbicacion;
    //AGREGAR NIVEL
    private String Nivel;
    //AGREGAR ESPECIALIDAD
    private String Especialidad;

    public Sections() {
        DbConnection clase1 = new DbConnection();
        cn = clase1.conectar();
    }

    public ResultSet consulta(String sql) {
        ResultSet res = null;
        try {
            PreparedStatement pstm = getCn().prepareStatement(sql);
            res = pstm.executeQuery();
        } catch (Exception e) {
        }
        return res;
    }

    public DefaultComboBoxModel obtenerNivel() {
        DefaultComboBoxModel listaModelo = new DefaultComboBoxModel();
        ResultSet rst = this.consulta("Select * from nivel");
        try {
            while (rst.next()) {
                listaModelo.addElement(rst.getString("nivel"));
            }
            rst.close();
        } catch (Exception e) {
        }
        return listaModelo;
    }

    public DefaultComboBoxModel obtenerEspecialidad() {
        DefaultComboBoxModel listaModelo = new DefaultComboBoxModel();
        ResultSet rst = this.consulta("Select * from especialidad");
        try {
            while (rst.next()) {
                listaModelo.addElement(rst.getString("especialidad"));
            }
            rst.close();
        } catch (Exception e) {
        }
        return listaModelo;
    }

    public DefaultComboBoxModel obtenerUbicacion() {
        DefaultComboBoxModel listaModelo = new DefaultComboBoxModel();
        ResultSet rst = this.consulta("Select * from ubicacion");
        try {
            while (rst.next()) {
                listaModelo.addElement(rst.getString("ubicacion"));
            }
            rst.close();
        } catch (Exception e) {
        }
        return listaModelo;
    }

    //CRUD AGREGAR SECCION
    public boolean agregarSeccion() {
        boolean respuesta = false;

        try {
            String sql = "INSERT INTO seccionNivel (seccion, idNivel, idEspecialidad, idUbicacion) " + "VALUES (?,?,?,?);";
            PreparedStatement stm = getCn().prepareStatement(sql);

            stm.setString(1, getSeccion());
            stm.setInt(2, getIdNivel() + 1);
            stm.setInt(3, getIdEspecialidad() + 1);
            stm.setInt(4, getIdUbicacion() + 1);

            if (!stm.execute()) {
                respuesta = true;
            }
            stm.close();
            getCn().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    public boolean ActualizarSeccion() {
        boolean respuesta = false;
        try {
            String sql = "UPDATE seccionNivel SET seccion= ?, idNivel = ?, idEspecialidad = ?, idUbicacion = ? WHERE idSeccionNivel = ?;";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, seccion);
            stm.setInt(2, idNivel +1);
            stm.setInt(3, idEspecialidad + 1);
            stm.setInt(4, idUbicacion + 1);
            stm.setInt(5, getIdSeccion());

            if (!stm.execute()) {
                respuesta = true;
            }
            stm.close();
            cn.close();
        } catch (Exception e) {
        }
        return respuesta;
    }
     public boolean ElminarSeccion() {
        boolean respuesta = false;
        try {
            String sql = "DELETE FROM seccionNivel WHERE idSeccionNivel = ?;";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, getIdSeccion());
            if (!stm.execute()) {
                respuesta = true;
            }
            stm.close();
            cn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }
    //CRUD NIVEL
    public boolean agregarNivel() {
        boolean respuesta = false;

        try {
            String sql = "INSERT INTO nivel (nivel) " + "VALUES (?);";
            PreparedStatement stm = getCn().prepareStatement(sql);

            stm.setString(1, getNivel());

            if (!stm.execute()) {
                respuesta = true;
            }
            stm.close();
            getCn().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }
    //CRUD ESPECIALIDAD

    public boolean agregarEspecialidad() {
        boolean respuesta = false;

        try {
            String sql = "INSERT INTO especialidad (especialidad) " + "VALUES (?);";
            PreparedStatement stm = getCn().prepareStatement(sql);

            stm.setString(1, getEspecialidad());

            if (!stm.execute()) {
                respuesta = true;
            }
            stm.close();
            getCn().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }
    
    public boolean ActualizarEspecialidad() {
        boolean respuesta = false;
        try {
            String sql = "UPDATE especialidad SET especialidad= ? WHERE idEspecialidad = ?;";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setString(1, Especialidad);
            stm.setInt(2, getIdEspecialidad());

            if (!stm.execute()) {
                respuesta = true;
            }
            stm.close();
            cn.close();
        } catch (Exception e) {
        }
        return respuesta;
    }
    
    public boolean ElminarEspecialidad() {
        boolean respuesta = false;
        try {
            String sql = "DELETE FROM especialidad WHERE idEspecialidad = ?;";
            PreparedStatement stm = cn.prepareStatement(sql);
            stm.setInt(1, getIdEspecialidad());
            if (!stm.execute()) {
                respuesta = true;
            }
            stm.close();
            cn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    /**
     * @return the seccion
     */
    public String getSeccion() {
        return seccion;
    }

    /**
     * @param seccion the seccion to set
     */
    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    /**
     * @return the idNivel
     */
    public int getIdNivel() {
        return idNivel;
    }

    /**
     * @param idNivel the idNivel to set
     */
    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }

    /**
     * @return the idEspecialidad
     */
    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    /**
     * @param idEspecialidad the idEspecialidad to set
     */
    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    /**
     * @return the idUbicacion
     */
    public int getIdUbicacion() {
        return idUbicacion;
    }

    /**
     * @param idUbicacion the idUbicacion to set
     */
    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    /**
     * @return the cn
     */
    public Connection getCn() {
        return cn;
    }

    /**
     * @param cn the cn to set
     */
    public void setCn(Connection cn) {
        this.cn = cn;
    }

    /**
     * @return the Nivel
     */
    public String getNivel() {
        return Nivel;
    }

    /**
     * @param Nivel the Nivel to set
     */
    public void setNivel(String Nivel) {
        this.Nivel = Nivel;
    }

    /**
     * @return the Especialidad
     */
    public String getEspecialidad() {
        return Especialidad;
    }

    /**
     * @param Especialidad the Especialidad to set
     */
    public void setEspecialidad(String Especialidad) {
        this.Especialidad = Especialidad;
    }

    /**
     * @return the idSeccion
     */
    public int getIdSeccion() {
        return idSeccion;
    }

    /**
     * @param idSeccion the idSeccion to set
     */
    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }
}
