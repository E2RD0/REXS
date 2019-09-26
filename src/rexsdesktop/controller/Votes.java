/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.controller;

import java.util.List;
import rexsdesktop.model.Db;

/**
 *
 * @author Eduardo
 */
public class Votes {

    public static boolean ingresarVotacion(int idUsuario, int idProyecto, List<Object>... votos) {
        Db db = new Db();
        double total = 0;
        if (db.addVotacion(idUsuario, idProyecto)) {
            int idVotacion = db.getIdVotacion(idUsuario, idProyecto);
            for (List<Object> voto : votos) {
                double puntuacion = (double) voto.get(0);
                String criterioVotacion = String.valueOf(voto.get(1));
                int idCriterioVotacion = db.getIdCriterioVotacion(criterioVotacion);
                if (!db.addDetalleVotacion(puntuacion, idCriterioVotacion, idVotacion))
                    return false;
                total += puntuacion;
            }
            return (total != 0 ? db.addPromedio(total / votos.length, idVotacion) : false);
        }
        return false;
    }
}
