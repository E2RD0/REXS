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
 * @author user
 */
public class RexsDesktop {

    /**
     * @param args the command line arguments
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
