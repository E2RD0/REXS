/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.modal;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import rexsdesktop.CurrentUser;
import rexsdesktop.controller.Activities;
import rexsdesktop.controller.Locations;
import rexsdesktop.view.Admin;

/**
 * Clase que contiene el Panel para modificar y eliminar una actividad.
 *
 * @author Carlos Herrera
 * @version 1.2
 */
public class ModalModificarActividad extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    Map<String, Location> map = createMap();
    public final SpinnerDateModel modelInicio = new SpinnerDateModel();
    public final SpinnerDateModel modelFin = new SpinnerDateModel();
    public int id = Activities.getId2();

    public ModalModificarActividad() {
        initComponents();
        Calendar min = Calendar.getInstance();
        min.set(Calendar.YEAR, Integer.parseInt(Activities.getMinAnioInicio()));
        min.set(Calendar.MONTH, Integer.parseInt(Activities.getMinMesInicio()) - 1);
        min.set(Calendar.DATE, Integer.parseInt(Activities.getMinDiaInicio()));

        Calendar max = Calendar.getInstance();
        max.set(Calendar.YEAR, Integer.parseInt(Activities.getMinAnioInicio()));
        max.set(Calendar.MONTH, Integer.parseInt(Activities.getMaxMesInicio()) - 1);
        max.set(Calendar.DATE, Integer.parseInt(Activities.getMaxDiaInicio()));

        dateFechaInicio.setMinSelectableDate(min.getTime());
        dateFechaInicio.setMaxSelectableDate(max.getTime());
        cbxUbicacionModal.removeAllItems();

        createComboBox(map, cbxUbicacionModal);
        dateFechaInicio.setDateFormatString("yyyy-MM-dd");
        //Spinner
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblPrueba = new javax.swing.JTextField();
        txtPrueba = new javax.swing.JTextField();
        btnActualizarModal = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        cbxUbicacionModal = new javax.swing.JComboBox<>();
        txtNombreActividadModal = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        dateFechaInicio = new com.toedter.calendar.JDateChooser();
        spHoraInicio = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        spHoraFin = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcionModal = new javax.swing.JTextArea();
        btnEliminarModal = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        txtNombreEncargadoModal = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(345, 450));
        setMinimumSize(new java.awt.Dimension(345, 325));
        setPreferredSize(new java.awt.Dimension(345, 325));

        btnActualizarModal.setBackground(new java.awt.Color(68, 236, 114));
        btnActualizarModal.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        btnActualizarModal.setForeground(new java.awt.Color(51, 153, 0));
        btnActualizarModal.setText("Actualizar");
        btnActualizarModal.setToolTipText("Actualizar la actividad");
        btnActualizarModal.setBorderPainted(false);
        btnActualizarModal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarModalActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(164, 164, 164));

        cbxUbicacionModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        cbxUbicacionModal.setForeground(new java.awt.Color(46, 56, 77));
        cbxUbicacionModal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxUbicacionModal.setToolTipText("Ubicación de la actividad");
        cbxUbicacionModal.setPreferredSize(new java.awt.Dimension(56, 27));

        txtNombreActividadModal.setBackground(new java.awt.Color(249, 250, 255));
        txtNombreActividadModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtNombreActividadModal.setForeground(new java.awt.Color(46, 56, 77));
        txtNombreActividadModal.setToolTipText("Nombre de la actividad seleccionada.");
        txtNombreActividadModal.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));

        jLabel67.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(176, 186, 201));
        jLabel67.setText("NOMBRE DE LA ACTIVIDAD");

        dateFechaInicio.setToolTipText("Fecha en la que se realizará la actividad");
        dateFechaInicio.setDateFormatString("yyyy-MM-dd HH:mm:ss");
        dateFechaInicio.setPreferredSize(new java.awt.Dimension(137, 28));

        spHoraInicio.setToolTipText("Hora Inicio de la actividad");

        jLabel1.setFont(new java.awt.Font("Rubik Medium", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("-");

        spHoraFin.setToolTipText("Hora de finalización de la actividad");

        txtDescripcionModal.setColumns(20);
        txtDescripcionModal.setRows(5);
        txtDescripcionModal.setToolTipText("Descripción de la actividad");
        jScrollPane1.setViewportView(txtDescripcionModal);

        btnEliminarModal.setBackground(new java.awt.Color(247, 214, 218));
        btnEliminarModal.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        btnEliminarModal.setForeground(new java.awt.Color(214, 54, 73));
        btnEliminarModal.setText("Eliminar");
        btnEliminarModal.setToolTipText("Eliminar la actividad");
        btnEliminarModal.setBorderPainted(false);
        btnEliminarModal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarModalActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/LocationActivities.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rexsdesktop/view/resources/Clock.png"))); // NOI18N

        jLabel69.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(176, 186, 201));
        jLabel69.setText("DESCRIPCIÓN");

        txtNombreEncargadoModal.setBackground(new java.awt.Color(249, 250, 255));
        txtNombreEncargadoModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtNombreEncargadoModal.setForeground(new java.awt.Color(46, 56, 77));
        txtNombreEncargadoModal.setToolTipText("Nombre de la actividad seleccionada.");
        txtNombreEncargadoModal.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));

        jLabel68.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(176, 186, 201));
        jLabel68.setText("ENCARGADO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnActualizarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 26, Short.MAX_VALUE)
                        .addComponent(jLabel67)
                        .addGap(163, 163, 163))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNombreActividadModal)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(dateFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(spHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbxUbicacionModal, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel68)
                                            .addComponent(txtNombreEncargadoModal, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel69)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel67)
                .addGap(11, 11, 11)
                .addComponent(txtNombreActividadModal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spHoraFin)
                            .addComponent(spHoraInicio)
                            .addComponent(dateFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(7, 7, 7)
                        .addComponent(jLabel68)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxUbicacionModal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreEncargadoModal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel69)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 13, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminarModal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método utilizado para volver a nulos los valores de los campos de
     * actividad.
     */
    private void resetarCampos() {
        txtDescripcionModal.setText(null);
        txtNombreActividadModal.setText(null);
    }

    /**
     * Método utilizado actualizar los datos de una actividad
     */
    private void actualizar() {
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        String prueba = formato.format(modelInicio.getDate());
        String prueba2 = formato.format(modelFin.getDate());
        System.out.println(prueba);
        String max = "20:00:00";
        String min = "08:00:00";
        try {
            SimpleDateFormat sdf = (SimpleDateFormat) SimpleDateFormat.getDateTimeInstance();
            sdf.applyPattern("HH:mm:ss");
            Date d = null;
            Date d2 = null;
            Date d3 = null;
            Date d4 = null;
            try {
                d = sdf.parse(prueba);
                d2 = sdf.parse(prueba2);
                d3 = sdf.parse(max);
                d4 = sdf.parse(min);

                if (txtDescripcionModal.getText().trim().equals("") || txtNombreActividadModal.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Exisen campos vacíos", "Atención", JOptionPane.INFORMATION_MESSAGE);
                } else if (modelFin.getDate().toString().equals(modelInicio.getDate().toString())) {
                    JOptionPane.showMessageDialog(null, "Las horas no deben ser iguales", "Atención", JOptionPane.INFORMATION_MESSAGE);
                } else if (d2.before(d4)) {
                    JOptionPane.showMessageDialog(null, "La hora de finalización no puede ser anterior a las 8:00 a.m.", "Atención", JOptionPane.INFORMATION_MESSAGE);
                } else if (d.before(d4)) {
                    JOptionPane.showMessageDialog(null, "La hora de inicio no puede ser anterior a las 8:00 a.m.", "Atención", JOptionPane.INFORMATION_MESSAGE);
                } else if (d2.after(d3)) {
                    JOptionPane.showMessageDialog(null, "La hora de finalización no puede excederse de las 8:00 p.m", "Atención", JOptionPane.INFORMATION_MESSAGE);
                } else if (d.after(d3)) {
                    JOptionPane.showMessageDialog(null, "La hora de inicio no puede excederse de las 8:00 p.m", "Atención", JOptionPane.INFORMATION_MESSAGE);
                } else if (d2.before(d)) {
                    JOptionPane.showMessageDialog(null, "La hora de finalización no puede ser anterior a la de inicio", "Atención", JOptionPane.INFORMATION_MESSAGE);
                } else if (d.after(d2)) {
                    JOptionPane.showMessageDialog(null, "La hora de inicio no puede ser luego de la de finalización", "Atención", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String nombre = txtNombreActividadModal.getText();
                    String encargado = txtNombreEncargadoModal.getText();
                    String descripcion = txtDescripcionModal.getText();
                    SimpleDateFormat formatoDia = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat formatoHora = new SimpleDateFormat(" HH:mm:ss");
                    String fechaInicio = formatoDia.format(dateFechaInicio.getDate());
                    String dInicio = fechaInicio + formatoHora.format(modelInicio.getDate());
                    String dFin = fechaInicio + formatoHora.format(modelFin.getDate());
                    String ubicacion = (String) cbxUbicacionModal.getSelectedItem();
                    int idUbicacion = Locations.getIdUbicacion(String.valueOf(map.get(ubicacion)));

                    if (Activities.actualizarActividad(nombre, descripcion, dInicio, dFin, encargado, idUbicacion, id)) {
                        JOptionPane.showMessageDialog(null, "Actividad actualizada correctamente", "Actividades", JOptionPane.INFORMATION_MESSAGE);
                        resetarCampos();
                        Activities acti = new Activities();
                        Admin.cargarActividades();
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error global" + e.getMessage());
        }

    }

    private void eliminar() {
        try {
            if (Activities.eliminarActividad(id)) {
                JOptionPane.showMessageDialog(null, "Actividad eliminada correctamente", "Actividades", JOptionPane.INFORMATION_MESSAGE);
                resetarCampos();
                Activities acti = new Activities();
                Admin.cargarActividades();
            }
        } catch (HeadlessException e) {
            System.out.println("ERROR");
        }
    }

    private void cargarActividades() {
        Admin.cargarActividades();
    }

    private void btnActualizarModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarModalActionPerformed
        actualizar();
    }//GEN-LAST:event_btnActualizarModalActionPerformed

    private void btnEliminarModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarModalActionPerformed
        int a = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar esta actividad?");
        if (a == JOptionPane.YES_OPTION) {
            eliminar();
        } else {

        }
    }//GEN-LAST:event_btnEliminarModalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarModal;
    private javax.swing.JButton btnEliminarModal;
    public static javax.swing.JComboBox<String> cbxUbicacionModal;
    public static com.toedter.calendar.JDateChooser dateFechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JTextField lblPrueba;
    public static javax.swing.JSpinner spHoraFin;
    public static javax.swing.JSpinner spHoraInicio;
    public static javax.swing.JTextArea txtDescripcionModal;
    public static javax.swing.JTextField txtNombreActividadModal;
    public static javax.swing.JTextField txtNombreEncargadoModal;
    public static javax.swing.JTextField txtPrueba;
    // End of variables declaration//GEN-END:variables

    private Map<String, Location> createMap() {
        Map<String, Location> map = new HashMap<>();
        List<String> ubicaciones = Locations.getPlacesFromDb();
        for (String ubicacion : ubicaciones) {
            Location s = new Location(ubicacion, Locations.getPlaceName(ubicacion));
            map.put(s.getName(), s);
        }
        return map;
    }

    private void createComboBox(final Map<String, Location> map, JComboBox cbox) {
        for (String name : map.keySet()) {
            cbox.addItem(name);
        }

        cbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String) cbox.getSelectedItem();
                System.out.println(map.get(name));
            }
        });

    }

    class Location {

        String name;
        String id;

        public Location(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return id;
        }
    }
}
