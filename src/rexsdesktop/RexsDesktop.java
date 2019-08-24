/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import rexsdesktop.view.Login;

/**
 *
 * @author Eduardo
 * @version 1.2
 */
public class RexsDesktop {

    /**
     * REXS es una aplicación que permite llevar el control de los siguientes puntos de la Expotécnica Ricaldone:
     * Las actividades.
     * Los proyectos e integrantes de los mismos.
     * Además de llevar un control de los votos de los proyectos.
     * @param args the command line arguments
     * 
     */
    public static void main(String[] args) {
         SwingUtilities.invokeLater (new Runnable ()
        {
            public void run ()
            {
                Login inicio = new Login();
                inicio.setLocationRelativeTo(null);
                inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                inicio.setVisible(true);
            }
        } );
    }
    
}
