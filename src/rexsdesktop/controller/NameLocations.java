/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.controller;

import rexsdesktop.model.Db;

/**
 *
 * @author Carlos Herrera
 */
public class NameLocations implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                Db db = new Db();
                db.cargarUbicacionesLogin();
                System.out.println("Hola");
            } catch (Exception e) {
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
        }

    }

}
