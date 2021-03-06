/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.view;

/**
 * Clase utilizada para importar la tipografia RubikFonts
 * @author user
 * @version 1.2
 */
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public final class RubikFonts {
    static Font light;
    static Font medium;
    static Font bold;  
    public RubikFonts() {
            try {
                 RubikFonts.light = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("resources/Rubik-Light.ttf"));
            } catch (FontFormatException | IOException ex) {
                ex.printStackTrace();
            }
        }
}
