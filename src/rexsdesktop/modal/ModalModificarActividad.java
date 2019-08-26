/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rexsdesktop.modal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import rexsdesktop.controller.Activities;
import rexsdesktop.controller.Locations;
import rexsdesktop.view.Admin;

/**
 * Clase que contiene el Panel para agregar una nueva actividad.
 *
 * @author Carlos Herrera
 * @version 1.2
 */
public class ModalModificarActividad extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    Map<String, Location> map = createMap();
    private SpinnerDateModel modelInicio = new SpinnerDateModel();
    private SpinnerDateModel modelFin = new SpinnerDateModel();
    public int id = 0;

    public ModalModificarActividad() {
        initComponents();
        Calendar min = Calendar.getInstance();
        min.set(Calendar.YEAR, 2019);
        min.set(Calendar.MONTH, 9);
        min.set(Calendar.DATE, 25);

        Calendar max = Calendar.getInstance();
        max.set(Calendar.YEAR, 2019);
        max.set(Calendar.MONTH, 9);
        max.set(Calendar.DATE, 29);

        dateFechaInicio.setMinSelectableDate(min.getTime());
        dateFechaInicio.setMaxSelectableDate(max.getTime());

        cbxUbicacionModal.removeAllItems();
        createComboBox(map, cbxUbicacionModal);
        dateFechaInicio.setDateFormatString("yyyy-MM-dd");
//        dateFechaInicio.setDate(new Date());

        modelInicio.setCalendarField(Calendar.MINUTE);
        modelFin.setCalendarField(Calendar.MINUTE);
        spHoraInicio.setModel(modelInicio);
        spHoraInicio.setEditor(new JSpinner.DateEditor(spHoraInicio, "h:mma"));
        spHoraFin.setModel(modelFin);
        spHoraFin.setEditor(new JSpinner.DateEditor(spHoraFin, "h:mma"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(345, 325));
        setMinimumSize(new java.awt.Dimension(345, 325));
        setPreferredSize(new java.awt.Dimension(345, 325));

        btnActualizarModal.setBackground(new java.awt.Color(68, 236, 114));
        btnActualizarModal.setFont(new java.awt.Font("Rubik Medium", 0, 11)); // NOI18N
        btnActualizarModal.setForeground(new java.awt.Color(51, 153, 0));
        btnActualizarModal.setText("Actualizar");
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
        cbxUbicacionModal.setPreferredSize(new java.awt.Dimension(56, 27));

        txtNombreActividadModal.setBackground(new java.awt.Color(249, 250, 255));
        txtNombreActividadModal.setFont(new java.awt.Font("Rubik", 0, 11)); // NOI18N
        txtNombreActividadModal.setForeground(new java.awt.Color(46, 56, 77));
        txtNombreActividadModal.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(224, 231, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15)));

        jLabel67.setFont(new java.awt.Font("Rubik Medium", 0, 10)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(176, 186, 201));
        jLabel67.setText("NOMBRE DE LA ACTIVIDAD");

        dateFechaInicio.setDateFormatString("yyyy-MM-dd HH:mm:ss");
        dateFechaInicio.setPreferredSize(new java.awt.Dimension(137, 28));

        jLabel1.setFont(new java.awt.Font("Rubik Medium", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("-");

        txtDescripcionModal.setColumns(20);
        txtDescripcionModal.setRows(5);
        jScrollPane1.setViewportView(txtDescripcionModal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtNombreActividadModal, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel67))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbxUbicacionModal, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(dateFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(spHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(spHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnActualizarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel67)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreActividadModal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spHoraFin, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(spHoraInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(dateFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxUbicacionModal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizarModal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        if (txtDescripcionModal.getText().equals("") || txtNombreActividadModal.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Exisen campos vacíos");
        } else {

            String nombre = txtNombreActividadModal.getText();
            String descripcion = txtDescripcionModal.getText();
            SimpleDateFormat formatoDia = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatoHora = new SimpleDateFormat(" HH:mm:ss");
            String fechaInicio = formatoDia.format(dateFechaInicio.getDate());
            String dInicio = fechaInicio + formatoHora.format(modelInicio.getDate());
            String dFin = fechaInicio + formatoHora.format(modelFin.getDate());
            String ubicacion = (String) cbxUbicacionModal.getSelectedItem();
            int idUbicacion = Locations.getIdUbicacion(String.valueOf(map.get(ubicacion)));

            if (Activities.actualizarActividad(nombre, descripcion, dInicio, dFin, idUbicacion, id)) {
                JOptionPane.showMessageDialog(null, "Actividad actualizada");
                resetarCampos();
                Activities acti = new Activities();
                Admin.jPanel4.removeAll();
                Admin.jPanel12.removeAll();
                Admin.jPanel13.removeAll();
                Admin.jPanel14.removeAll();
                Admin.jPanel15.removeAll();
                cargarActividades();
            } else {
                JOptionPane.showMessageDialog(null, "ERROR");
            }
        }

    }

    public void cargarActividades() {
        Activities actividades = new Activities();
        String dia1_Inicio = "2019-10-25 00:00:00";
        String dia1_Fin = "2019-10-25 23:59:59";

        String dia2_Inicio = "2019-10-26 00:00:00";
        String dia2_Fin = "2019-10-26 23:59:59";

        String dia3_Inicio = "2019-10-27 00:00:00";
        String dia3_Fin = "2019-10-27 23:59:59";

        String dia4_Inicio = "2019-10-28 00:00:00";
        String dia4_Fin = "2019-10-28 23:59:59";

        String dia5_Inicio = "2019-10-29 00:00:00";
        String dia5_Fin = "2019-10-29 23:59:59";

        int contador = 1;
        actividades.CrearPanelesActividades(Admin.jPanel4, dia1_Inicio, dia1_Fin, contador);
        Admin.lblCantidadActividades1.setText(String.valueOf(actividades.getCantidadDia1()));
        contador++;

        actividades.CrearPanelesActividades(Admin.jPanel12, dia2_Inicio, dia2_Fin, contador);
        Admin.lblCantidadActividades2.setText(String.valueOf(actividades.getCantidadDia1()));
        contador++;

        actividades.CrearPanelesActividades(Admin.jPanel13, dia3_Inicio, dia3_Fin, contador);
        Admin.lblCantidadActividades3.setText(String.valueOf(actividades.getCantidadDia1()));
        contador++;

        actividades.CrearPanelesActividades(Admin.jPanel14, dia4_Inicio, dia4_Fin, contador);
        Admin.lblCantidadActividades4.setText(String.valueOf(actividades.getCantidadDia1()));
        contador++;

        actividades.CrearPanelesActividades(Admin.jPanel15, dia5_Inicio, dia5_Fin, contador);
        Admin.lblCantidadActividades5.setText(String.valueOf(actividades.getCantidadDia1()));
    }
    private void btnActualizarModalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarModalActionPerformed
        actualizar();
    }//GEN-LAST:event_btnActualizarModalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarModal;
    public static javax.swing.JComboBox<String> cbxUbicacionModal;
    public static com.toedter.calendar.JDateChooser dateFechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JSpinner spHoraFin;
    public static javax.swing.JSpinner spHoraInicio;
    public static javax.swing.JTextArea txtDescripcionModal;
    public static javax.swing.JTextField txtNombreActividadModal;
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
