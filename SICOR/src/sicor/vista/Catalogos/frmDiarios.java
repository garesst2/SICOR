/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicor.vista.Catalogos;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import sicor.controlador.DiarioJpaController;
import sicor.controlador.EmpresasJpaController;
import sicor.modelo.Diario;
import sicor.vista.Menu.Inicio;
import static sicor.vista.Menu.Inicio.empresaGlobal;

/**
 *
 * @author Administrador
 */
public class frmDiarios extends javax.swing.JDialog {

    /**
     * Creates new form frmDiarios
     */
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SICORPU");
    private EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("SICORPU");
    public List<Diario> listDiarios = mostrarDiarioPorEmpresas();
    public static Diario diario;

    public frmDiarios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        txtIdDiario.setVisible(false);
    }

    private List<Diario> mostrarDiarioPorEmpresas() {
        try {
            DiarioJpaController ctrDiario = new DiarioJpaController(emf);
            return ctrDiario.getDiarioByEmpresa(empresaGlobal);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Ocurrio un error: " + e.toString());
            return null;
        }
    }

    public List<Diario> getListDiarios() {
        return listDiarios;
    }

    public void setListDiarios(List<Diario> listDiarios) {
        this.listDiarios = listDiarios;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDiarios = new javax.swing.JTable();
        txtIdDiario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtnombDiario = new javax.swing.JTextField();
        lblNomDiario = new javax.swing.JLabel();
        txtAño = new javax.swing.JTextField();
        lblaño = new javax.swing.JLabel();
        txtMes = new javax.swing.JTextField();
        lñblmes = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnCrear = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnSeleccionDiario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${listDiarios}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tblDiarios);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${idDiario}"));
        columnBinding.setColumnName("Id Diario");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nombDiario}"));
        columnBinding.setColumnName("Nomb Diario");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${año}"));
        columnBinding.setColumnName("Año");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${mes}"));
        columnBinding.setColumnName("Mes");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${abonos}"));
        columnBinding.setColumnName("Abonos");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${cargos}"));
        columnBinding.setColumnName("Cargos");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${activo}"));
        columnBinding.setColumnName("Activo");
        columnBinding.setColumnClass(Boolean.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${movimientos}"));
        columnBinding.setColumnName("Movimientos");
        columnBinding.setColumnClass(Integer.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(tblDiarios);
        if (tblDiarios.getColumnModel().getColumnCount() > 0) {
            tblDiarios.getColumnModel().getColumn(0).setMinWidth(0);
            tblDiarios.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblDiarios.getColumnModel().getColumn(0).setMaxWidth(0);
            tblDiarios.getColumnModel().getColumn(6).setMinWidth(0);
            tblDiarios.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblDiarios.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        jLabel5.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        jLabel5.setText("DIARIOS");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tblDiarios, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.nombDiario}"), txtnombDiario, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        lblNomDiario.setText("Nombre Diario");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tblDiarios, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.año}"), txtAño, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        lblaño.setText("Año");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tblDiarios, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.mes}"), txtMes, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        lñblmes.setText("Mes");

        btnCrear.setText("CREAR");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnSeleccionDiario.setText("SELECCIONAR DIARIO");
        btnSeleccionDiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionDiarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNomDiario)
                            .addComponent(lblaño)
                            .addComponent(lñblmes))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMes)
                            .addComponent(txtAño)
                            .addComponent(txtnombDiario)))
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtIdDiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73))
                            .addComponent(btnSeleccionDiario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtIdDiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNomDiario)
                            .addComponent(txtnombDiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblaño)
                            .addComponent(txtAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lñblmes)
                            .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCrear)
                            .addComponent(btnLimpiar))
                        .addGap(18, 18, 18)
                        .addComponent(btnSeleccionDiario)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        if (cargarDatos() && validarDatos()) {
            try {
                cargarDatos();
                DiarioJpaController ctrDiario = new DiarioJpaController(emf);
                EmpresasJpaController ctrEmpresa = new EmpresasJpaController(emf2);
                diario.setIdEmpresa(ctrEmpresa.findEmpresas(empresaGlobal.getIdEmpresa()));
                ctrDiario.create(diario);
                limpiar();
                listDiarios = mostrarDiarioPorEmpresas();
                actualizarTabla();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error: " + e.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hay datos que no se han completados ");
        }
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnSeleccionDiarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionDiarioActionPerformed
        Inicio.diarioGlobal = diario;
        JOptionPane.showMessageDialog(null, "El diario ha sido correctamente seleccionado para poder trabajar");
        Inicio.estadoDiarioSeleccion=true;
        this.dispose();
    }//GEN-LAST:event_btnSeleccionDiarioActionPerformed

    private boolean validarDatos() {
        return (txtAño.getText().length() == 0) ? !(txtMes.getText().length() == 0) : (txtMes.getText().length() != 0);
    }

    private void actualizarTabla() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${listDiarios}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tblDiarios);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${idDiario}"));
        columnBinding.setColumnName("Id Diario");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nombDiario}"));
        columnBinding.setColumnName("Nomb Diario");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${año}"));
        columnBinding.setColumnName("Año");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${mes}"));
        columnBinding.setColumnName("Mes");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${abonos}"));
        columnBinding.setColumnName("Abonos");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${cargos}"));
        columnBinding.setColumnName("Cargos");
        columnBinding.setColumnClass(java.math.BigDecimal.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${activo}"));
        columnBinding.setColumnName("Activo");
        columnBinding.setColumnClass(Boolean.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${movimientos}"));
        columnBinding.setColumnName("Movimientos");
        columnBinding.setColumnClass(Integer.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(tblDiarios);
        if (tblDiarios.getColumnModel().getColumnCount() > 0) {
            tblDiarios.getColumnModel().getColumn(0).setMinWidth(0);
            tblDiarios.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblDiarios.getColumnModel().getColumn(0).setMaxWidth(0);
        }
    }

    private boolean cargarDatos() {
        boolean retorno = false;
        diario = new Diario();
        diario.setNombDiario(txtnombDiario.getText());
        if (isNumeric(txtAño.getText()) && isNumeric(txtMes.getText())) {
            diario.setAño(Integer.parseInt(txtAño.getText()));
            diario.setMes(Integer.parseInt(txtMes.getText()));
            retorno = true;
        }
        diario.setCargos(new BigDecimal("0.00"));
        diario.setAbonos(new BigDecimal("0.00"));
        diario.setMovimientos(0);
        diario.setActivo(true);
        return retorno;
    }

    private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public void limpiar() {
        tblDiarios.clearSelection();
        txtAño.setText("");
        txtIdDiario.setText("");
        txtMes.setText("");
        txtnombDiario.setText("");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmDiarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmDiarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmDiarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmDiarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmDiarios dialog = new frmDiarios(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSeleccionDiario;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblNomDiario;
    private javax.swing.JLabel lblaño;
    private javax.swing.JLabel lñblmes;
    private javax.swing.JTable tblDiarios;
    private javax.swing.JTextField txtAño;
    private javax.swing.JTextField txtIdDiario;
    private javax.swing.JTextField txtMes;
    private javax.swing.JTextField txtnombDiario;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
