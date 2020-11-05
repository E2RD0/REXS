/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.modal;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import rexsdesktop.Session;
import rexsdesktop.controller.General;
import rexsdesktop.controller.Projects;
import static rexsdesktop.controller.Projects.getIdSeccionNivel;
import rexsdesktop.controller.Validation;
import rexsdesktop.model.Db;
import rexsdesktop.view.Admin;
import static rexsdesktop.view.Admin.cdProyectos;
import static rexsdesktop.view.Admin.color;
import static rexsdesktop.view.Admin.jsProyectos;
import static rexsdesktop.view.Admin.pnlViewProyectos;

/**
 * Clase que contiene el Panel para agregar un nuevo proyecto.
 *
 * @author Lulac
 */
public class ModalNuevoProyecto extends javax.swing.JPanel {

    public static BufferedImage img;
    public static JLabel label;
    
    private Session s = Session.getInstance();
    //BufferedImage fotoProyecto = new javax.swing.b(getClass().getResource("/rexsdesktop/view/resources/fotoProyecto.png"));

    public Db db = new Db();

    /**
     * Creates new form ModalNuevoProyecto
     */
    public ModalNuevoProyecto() {
        initComponents();
        db.obtenerNivel();
        for (int i = 0; i < db.SNnivel.size(); i++) {
            cbxNivel.addItem(db.SNnivel.get(i));
        }
        cbxEspecialidad.disable();
        cbxSeccionNivel.disable();
    }

    public void cargarProyectos() {
        try {
            General.getEdicion();
            db.NumProyectos(s.getEdicionExpotecnica());
            if (label != null) {
                pnlViewProyectos.remove(label);
            }
            cdProyectos.removeAll();
            pnlViewProyectos.repaint();
            pnlViewProyectos.revalidate();
            cdProyectos.setLayout(new GridLayout(0, 2, 15, 20));
            Projects cargarPaneles = new Projects();
            cargarPaneles.CrearPanelesProyectos(cdProyectos, s.getEdicionExpotecnica());
            jsProyectos.setBorder(null);
            if (color == 0) {

                jsProyectos.setBackground(new Color(244, 246, 252));
                cdProyectos.setBackground(new Color(244, 246, 252));

            } else {
                jsProyectos.setBackground(new Color(52, 48, 57));
                cdProyectos.setBackground(new Color(52, 48, 57));

            }
            switch (db.getCantidadProyecto()) {
                case 0:
                    jsProyectos.disable();
                    cdProyectos.disable();
                    label = new JLabel("<html>" + "No existen proyectos, por favor ingrese un proyecto" + "</html>", SwingConstants.CENTER);
                    label.setBounds(170, 200, 350, 50);
                    label.setFont(new java.awt.Font("Rubik Medium", 0, 12));
                    label.setForeground(new Color(46, 56, 77));
                    pnlViewProyectos.add(label);
                    pnlViewProyectos.repaint();
                    pnlViewProyectos.revalidate();
                    cdProyectos.repaint();
                    cdProyectos.revalidate();
                    break;
                case 1:
                    jsProyectos.setBounds(0, 70, 377, 120);
                    cdProyectos.setLayout(null);
                    pnlViewProyectos.repaint();
                    pnlViewProyectos.revalidate();
                    cdProyectos.repaint();
                    cdProyectos.revalidate();
                    break;
                case 2:
                    jsProyectos.setBounds(0, 70, 808, 120);
                    pnlViewProyectos.repaint();
                    pnlViewProyectos.revalidate();
                    cdProyectos.repaint();
                    cdProyectos.revalidate();
                    break;
                case 3:
                    jsProyectos.setBounds(0, 70, 808, 260);
                    pnlViewProyectos.repaint();
                    pnlViewProyectos.revalidate();
                    cdProyectos.repaint();
                    cdProyectos.revalidate();
                    break;
                case 4:
                    jsProyectos.setBounds(0, 70, 808, 260);
                    pnlViewProyectos.repaint();
                    pnlViewProyectos.revalidate();
                    cdProyectos.repaint();
                    cdProyectos.revalidate();
                    break;
                case 5:
                    jsProyectos.setBounds(0, 70, 808, 363);
                    pnlViewProyectos.repaint();
                    pnlViewProyectos.revalidate();
                    cdProyectos.repaint();
                    cdProyectos.revalidate();
                    break;
                default:
                    jsProyectos.setBounds(0, 70, 808, 363);
                    pnlViewProyectos.repaint();
                    pnlViewProyectos.revalidate();
                    cdProyectos.repaint();
                    cdProyectos.revalidate();
            }
            pnlViewProyectos.add(jsProyectos);
            pnlViewProyectos.repaint();
            pnlViewProyectos.revalidate();
            cdProyectos.repaint();
            cdProyectos.revalidate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jIMG = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        txtnombreProyecto = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        btnCambiarFotoPro = new javax.swing.JButton();
        btnEliminarFotoPro = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnAceptarModal = new javax.swing.JButton();
        btnCancelarModal = new javax.swing.JButton();
        cbxNivel = new javax.swing.JComboBox<>();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        cbxEspecialidad = new javax.swing.JComboBox<>();
        cbxSeccionNivel = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDesc1 = new javax.swing.JTextArea();

        jIMG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/profilePicture.png"))); // NOI18N

        jLabel67.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(176, 186, 201));
        jLabel67.setText("NOMBRE DEL PROYECTO");

        txtnombreProyecto.setBackground(new java.awt.Color(249, 250, 255));
        txtnombreProyecto.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtnombreProyecto.setForeground(new java.awt.Color(46, 56, 77));
        txtnombreProyecto.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));
        txtnombreProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreProyectoActionPerformed(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(176, 186, 201));
        jLabel69.setText("DESCRIPCIÓN");

        btnCambiarFotoPro.setBackground(new java.awt.Color(238, 238, 238));
        btnCambiarFotoPro.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        btnCambiarFotoPro.setForeground(new java.awt.Color(107, 107, 107));
        btnCambiarFotoPro.setText("Cambiar Foto");
        btnCambiarFotoPro.setBorderPainted(false);
        btnCambiarFotoPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarFotoProActionPerformed(evt);
            }
        });

        btnEliminarFotoPro.setBackground(new java.awt.Color(238, 238, 238));
        btnEliminarFotoPro.setFont(new java.awt.Font("Rubik", 0, 10)); // NOI18N
        btnEliminarFotoPro.setForeground(new java.awt.Color(107, 107, 107));
        btnEliminarFotoPro.setText("Eliminar Foto");
        btnEliminarFotoPro.setBorderPainted(false);
        btnEliminarFotoPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarFotoProActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(164, 164, 164));

        btnAceptarModal.setBackground(new java.awt.Color(213, 222, 255));
        btnAceptarModal.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        btnAceptarModal.setForeground(new java.awt.Color(46, 91, 255));
        btnAceptarModal.setText("Aceptar");
        btnAceptarModal.setBorderPainted(false);
        btnAceptarModal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarModalActionPerformed(evt);
            }
        });

        btnCancelarModal.setBackground(new java.awt.Color(247, 214, 218));
        btnCancelarModal.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        btnCancelarModal.setForeground(new java.awt.Color(214, 54, 73));
        btnCancelarModal.setText("Cancelar");
        btnCancelarModal.setBorderPainted(false);
        btnCancelarModal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarModalActionPerformed(evt);
            }
        });

        cbxNivel.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        cbxNivel.setForeground(new java.awt.Color(46, 56, 77));
        cbxNivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un nivel" }));
        cbxNivel.setPreferredSize(new java.awt.Dimension(56, 27));
        cbxNivel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxNivelItemStateChanged(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(176, 186, 201));
        jLabel70.setText("NIVEL");

        jLabel71.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(176, 186, 201));
        jLabel71.setText("ESPECIALIDAD");

        jLabel72.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(176, 186, 201));
        jLabel72.setText("SECCION");

        cbxEspecialidad.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        cbxEspecialidad.setForeground(new java.awt.Color(46, 56, 77));
        cbxEspecialidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una especialidad" }));
        cbxEspecialidad.setPreferredSize(new java.awt.Dimension(56, 27));
        cbxEspecialidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxEspecialidadItemStateChanged(evt);
            }
        });

        cbxSeccionNivel.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        cbxSeccionNivel.setForeground(new java.awt.Color(46, 56, 77));
        cbxSeccionNivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una sección" }));
        cbxSeccionNivel.setPreferredSize(new java.awt.Dimension(56, 27));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtDesc1.setBackground(new java.awt.Color(249, 250, 255));
        txtDesc1.setColumns(20);
        txtDesc1.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtDesc1.setLineWrap(true);
        txtDesc1.setRows(5);
        txtDesc1.setBorder(null);
        txtDesc1.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jScrollPane1.setViewportView(txtDesc1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jIMG))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnCambiarFotoPro)
                                    .addComponent(btnEliminarFotoPro, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel70)
                                        .addGap(51, 51, 51)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jLabel71)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel72)
                                .addGap(33, 33, 33))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel67)
                                        .addComponent(jLabel69)
                                        .addComponent(txtnombreProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 236, Short.MAX_VALUE)
                                .addComponent(btnAceptarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbxNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(cbxEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxSeccionNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jIMG)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCambiarFotoPro, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarFotoPro, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel67)
                        .addGap(1, 1, 1)
                        .addComponent(txtnombreProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel69)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(jLabel71)
                    .addComponent(jLabel72))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSeccionNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarModalActionPerformed
        try {
            btnAceptarModal.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (!txtnombreProyecto.getText().isEmpty() && !txtDesc1.getText().isEmpty()) {
                if (cbxNivel.getSelectedIndex() > 0) {
                    if (cbxSeccionNivel.getSelectedIndex() >= 0) {
                        if (!db.proyectoExiste(txtnombreProyecto.getText())) {
                            if (img != null) {
                                Projects p = getIdSeccionNivel(cbxNivel.getSelectedItem().toString(), cbxEspecialidad.getSelectedItem().toString(), cbxSeccionNivel.getSelectedItem().toString());
                                if (Projects.nuevoProyecto(txtnombreProyecto.getText(), txtDesc1.getText(), s.getEdicionExpotecnica(), p.getIdSeccionNivel, img)) {
                                    txtnombreProyecto.setText(null);
                                    txtDesc1.setText(null);
                                    Admin.cdProyectos.removeAll();
                                    cargarProyectos();
                                    pnlViewProyectos.repaint();
                                    pnlViewProyectos.revalidate();
                                    cdProyectos.repaint();
                                    cdProyectos.revalidate();

                                } else {
                                    JOptionPane.showMessageDialog(this, "Error al ingresar el proyecto, inténtelo de nuevo.");
                                }
                            } else {

                                Projects p = getIdSeccionNivel(cbxNivel.getSelectedItem().toString(), cbxEspecialidad.getSelectedItem().toString(), cbxSeccionNivel.getSelectedItem().toString());
                                if (Projects.nuevoProyecto(txtnombreProyecto.getText(), txtDesc1.getText(), s.getEdicionExpotecnica(), p.getIdSeccionNivel, img)) {
                                    txtnombreProyecto.setText(null);
                                    txtDesc1.setText(null);
                                    Admin.cdProyectos.removeAll();
                                    cargarProyectos();

                                    pnlViewProyectos.repaint();
                                    pnlViewProyectos.revalidate();
                                    cdProyectos.repaint();
                                    cdProyectos.revalidate();

                                } else {
                                    JOptionPane.showMessageDialog(this, "Error al ingresar el proyecto, inténtelo de nuevo.");
                                }
                            }

                        } else {
                            JOptionPane.showMessageDialog(this, "Existe un proyecto con el mismo nombre, por favor colocar uno nuevo.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Por favor, seleccione un nivel el cuál contenga una sección asignada.");

                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Seleccione el nivel al que desea agregar el proyecto");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Algunos campos están vacíos.");
            }

        } catch (Exception e) {
            System.out.println("Agregar proyecto" + e.getMessage());
        } finally {

            btnAceptarModal.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }


    }//GEN-LAST:event_btnAceptarModalActionPerformed

    private void btnCambiarFotoProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarFotoProActionPerformed
        JFileChooser fc = new JFileChooser();
        FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        fc.addChoosableFileFilter(imageFilter);
        fc.setAcceptAllFileFilterUsed(false);
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {

                File file = fc.getSelectedFile();
                if (Validation.VerificadorImagen.verifyFile(file)) {
                    FileInputStream fis = new FileInputStream(file);
                    BufferedImage imgOriginal = ImageIO.read(fis);
                    if (Validation.VerificadorImagen.verifyIMG(imgOriginal)) {
                        img = new BufferedImage(imgOriginal.getWidth(),
                                imgOriginal.getHeight(), BufferedImage.TYPE_INT_RGB);
                        img.createGraphics().drawImage(imgOriginal, 0, 0, Color.WHITE, null);
                        jIMG.setIcon(new ImageIcon(img.getScaledInstance(jIMG.getWidth(), jIMG.getHeight(), Image.SCALE_DEFAULT)));

                    } else {
                        JOptionPane.showMessageDialog(this, Validation.VerificadorImagen.mensaje, "Seleccionar imagen", JOptionPane.WARNING_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(this, Validation.VerificadorImagen.mensaje, "Seleccionar imagen", JOptionPane.WARNING_MESSAGE);
                }

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }//GEN-LAST:event_btnCambiarFotoProActionPerformed

    private void cbxNivelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxNivelItemStateChanged
        db.obtenerEspecialidad();
        cbxSeccionNivel.removeAllItems();
        cbxSeccionNivel.addItem("Seleccione una sección");
        try {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                if (cbxNivel.getSelectedIndex() > 0) {
                    if (cbxNivel.getSelectedItem().equals(String.valueOf("Primer año")) || cbxNivel.getSelectedItem().equals(String.valueOf("Segundo año")) || cbxNivel.getSelectedItem().equals(String.valueOf("Tercer año"))) {
                        cbxEspecialidad.enable();
                        cbxEspecialidad.removeAllItems();
                        for (int i = 0; i < db.SNespecialidad.size(); i++) {
                            cbxEspecialidad.addItem(db.SNespecialidad.get(i));
                        }
                        cbxEspecialidad.removeItem(String.valueOf("Basica"));
                    } else {
                        cbxEspecialidad.removeAllItems();
                        cbxEspecialidad.disable();
                        cbxSeccionNivel.removeAllItems();
                        for (int i = 0; i < db.SNespecialidad.size(); i++) {
                            cbxEspecialidad.addItem(db.SNespecialidad.get(i));
                        }
                        cbxEspecialidad.setSelectedItem("Basica");

                        db.obtenerSeccion(cbxNivel.getSelectedItem().toString(), cbxEspecialidad.getSelectedItem().toString());
                        cbxSeccionNivel.enable();
                        for (int i = 0; i < db.SNseccion.size(); i++) {
                            cbxSeccionNivel.addItem(db.SNseccion.get(i));
                        }
                    }

                } else {
                    cbxEspecialidad.removeAllItems();
                    cbxEspecialidad.addItem("Seleccione una especialidad");
                    cbxEspecialidad.disable();
                    cbxSeccionNivel.disable();
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }//GEN-LAST:event_cbxNivelItemStateChanged

    private void btnEliminarFotoProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarFotoProActionPerformed
        img = null;
        jIMG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/profilePicture.png")));

        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarFotoProActionPerformed

    private void cbxEspecialidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxEspecialidadItemStateChanged
        if (cbxNivel.getSelectedIndex() > 0) {
            if (cbxEspecialidad.getSelectedIndex() > 0) {
                cbxSeccionNivel.removeAllItems();
                db.obtenerSeccion(cbxNivel.getSelectedItem().toString(), cbxEspecialidad.getSelectedItem().toString());
                for (int i = 0; i < db.SNseccion.size(); i++) {
                    cbxSeccionNivel.addItem(db.SNseccion.get(i));
                }
                cbxSeccionNivel.enable();

            }
        }
    }//GEN-LAST:event_cbxEspecialidadItemStateChanged

    private void btnCancelarModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarModalActionPerformed
        Window w = SwingUtilities.getWindowAncestor(ModalNuevoProyecto.this);
        w.setVisible(false);
    }//GEN-LAST:event_btnCancelarModalActionPerformed

    private void txtnombreProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreProyectoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreProyectoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptarModal;
    private javax.swing.JButton btnCambiarFotoPro;
    private javax.swing.JButton btnCancelarModal;
    private javax.swing.JButton btnEliminarFotoPro;
    private javax.swing.JComboBox<String> cbxEspecialidad;
    private javax.swing.JComboBox<String> cbxNivel;
    private javax.swing.JComboBox<String> cbxSeccionNivel;
    private javax.swing.JLabel jIMG;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JTextArea txtDesc1;
    private javax.swing.JTextField txtnombreProyecto;
    // End of variables declaration//GEN-END:variables
}
