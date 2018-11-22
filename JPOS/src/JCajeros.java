
import java.sql.Connection;
import java.util.Vector;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Garyn Carrillo
 */
public class JCajeros extends JInternalFrame {

    /**
     * Creates new form JCajeros
     */
    private JConeccion JBase_Datos;
    private Connection Cn;
    private JCajero_Pos Cajas;
    private JCentroCostos Centros;
    private Vector Detalle_Fila;
    private Vector Cabecera;
    private Vector ListCentros;
    private Vector Detalle;
    public JCajeros(JPrincipal pr,JConeccion JBase_Datos3, Connection Cn2) {
        this.JBase_Datos = JBase_Datos3;
        this.Cn = Cn2;
        Cajas = new JCajero_Pos(this.JBase_Datos, this.Cn);
        Centros= new JCentroCostos(this.JBase_Datos, this.Cn);
        this.Cabecera = new Vector();
        this.Detalle_Fila = new Vector();
        initComponents();
        this.Cargar_Centro_Costo();
        this.Cargar_Cajas();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTCodigo = new javax.swing.JTextField();
        jTNombre = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTCajas = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTCodigoCentro = new javax.swing.JTextField();
        jCEstado = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jCDescripcion = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Maestro de Cajeros");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Cajeros");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Codigo");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Descripcion");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTCajas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Descripcion", "Centro de Costo", "Descripcion del Centro", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTCajas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTCajasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTCajas);

        jButton3.setText("Guardar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Cerrar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(jButton4)))
                .addContainerGap())
        );

        jButton2.setText("+");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Codigo");

        jTCodigoCentro.setEditable(false);

        jCEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "activo", "inactivo" }));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Estado");

        jCDescripcion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Centro de Costo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(jTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTCodigoCentro, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel2)
                                .addGap(91, 91, 91)
                                .addComponent(jLabel3)
                                .addGap(88, 88, 88)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addGap(40, 40, 40)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jButton2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(114, 114, 114))))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jTCodigoCentro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jMenu1.setText("Ejecutar");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.Agregar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(this.Validar_Informacion()){
           if (!jTCajas.getValueAt(jTCajas.getSelectedRow(),0).toString().trim().equals("")){
              if(this.Cajas.setCajas(this.jTCodigo.getText().trim(), this.jTNombre.getText().trim(), jTCodigoCentro.getText().trim(), this.jCEstado.getSelectedItem().toString().trim())){
                 jTCajas.setValueAt(this.jTCodigo.getText().trim(),jTCajas.getSelectedRow(), 0);
                 jTCajas.setValueAt(this.jTNombre.getText().trim(),jTCajas.getSelectedRow(), 1);
                 jTCajas.setValueAt(this.jTCodigoCentro.getText().trim(),jTCajas.getSelectedRow(), 2);
                 jTCajas.setValueAt(jCDescripcion.getSelectedItem().toString().trim(),jTCajas.getSelectedRow(), 3);
                 jTCajas.setValueAt(jCEstado.getSelectedItem().toString().trim(),jTCajas.getSelectedRow(), 4);
                 JOptionPane.showMessageDialog(this,"Se Guardo correctamente ");
              }else{
                      JOptionPane.showMessageDialog(this,"No se pudo Guardar.");
             }
           }else{
                JOptionPane.showMessageDialog(this,"Debe seleccionar un registro de la tabla para guardar");
           }
        }else{
            JOptionPane.showMessageDialog(this,"Debe diligenciar todos los campos en el formato adecuado");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTCajasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTCajasMouseClicked
        // TODO add your handling code here:
        this.Cargar_Tabla();
    }//GEN-LAST:event_jTCajasMouseClicked
    public void Agregar(){
         if(Validar_Informacion()){
              if(!this.Cajas.Existe(jTCodigo.getText().trim())){
                  if(this.Cajas.setCajas(this.jTCodigo.getText().trim(), this.jTNombre.getText().trim(), jTCodigoCentro.getText().trim(), this.jCEstado.getSelectedItem().toString().trim())){
                     JOptionPane.showMessageDialog(this,"Se Guardo correctamente ");
                     Detalle = new Vector();
                     Detalle.add(this.jTCodigo.getText().trim());
                     Detalle.add(this.jTNombre.getText().trim());
                     Detalle.add(jTCodigoCentro.getText());
                     Detalle.add(jCDescripcion.getSelectedItem().toString().trim());
                     Detalle.add(this.jCEstado.getSelectedItem().toString().trim());
                     this.Detalle_Fila.add(Detalle);
                     jTCajas.setModel(new javax.swing.table.DefaultTableModel(this.Detalle_Fila, this.Cabecera));
                  }else{
                      JOptionPane.showMessageDialog(this,"No se pudo Guardar.");
                  }
              }else{
                JOptionPane.showMessageDialog(this,"Ya existe esta caja, debe modificar la existente ");
              }
           }else{
                JOptionPane.showMessageDialog(this,"Debe ingresar la informacion de forma correcta y en el formato adecuado ");   
           }
         
    }
    public void Cargar_Tabla(){
        String BCodigo,BDescripcion, BCentroCosto, BDecripcionCentroCosto, BEstado;
        try {
            BCodigo = jTCajas.getValueAt(jTCajas.getSelectedRow(),0).toString().trim();
            BDescripcion = jTCajas.getValueAt(jTCajas.getSelectedRow(),1).toString().trim();
            BCentroCosto= jTCajas.getValueAt(jTCajas.getSelectedRow(),2).toString().trim();
            BDecripcionCentroCosto = jTCajas.getValueAt(jTCajas.getSelectedRow(),3).toString().trim();
            BEstado = jTCajas.getValueAt(jTCajas.getSelectedRow(),4).toString().trim();
            
            jTCodigo.setText(BCodigo.trim());
            jTNombre.setText(BDescripcion);
            jTCodigoCentro.setText(BCentroCosto);
            if(BEstado.trim().equals("activo")){
                jCEstado.setSelectedIndex(1);
            }else{
                jCEstado.setSelectedIndex(2);
            }
            int Posi = this.BuscarPosicionCentro(BCentroCosto);
            jCDescripcion.setSelectedIndex(Posi+1);
        } catch (Exception e) {
            jTCodigo.setText("");
            jTNombre.setText("");
            jTCodigoCentro.setText("");   
            jCEstado.setSelectedIndex(0);
            jCDescripcion.setSelectedIndex(0);
        }
    }
    public int BuscarPosicionCentro(String TCodigo){
        int Posicion = -1;
        try {
            String Nombre = this.Centros.getBuscarDescripcion(TCodigo);
            if(Nombre!=null){
                for (int i = 0; i < this.ListCentros.size(); i++) {
                    if(Nombre.trim().equals(this.ListCentros.get(i).toString().trim())){
                        Posicion = i;
                    }
                }
            }else{
                JOptionPane.showMessageDialog(this," El Centro de Costo asigando a este cajero no existe o fue modificado ");   
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this," BuscarPosicionCentro(Codigo) "+e.getMessage());   
        }
        return Posicion;
    }
    
    public boolean Validar_Informacion(){
        boolean Sw =true;
        if(this.jTCodigo.getText().trim().equals("")){
            Sw=false;
        }
        if(this.jTNombre.getText().trim().equals("")){
            Sw=false;
        }
        if(jTCodigoCentro.getText().trim().equals("")){
            Sw=false;
        }
        if(this.jCEstado.getSelectedItem().toString().trim().equals("")){
            Sw=false;
        }
        return Sw;
    }
    public void Cargar_Centro_Costo(){
        ListCentros = this.Centros.getListDescripcion();
        for (int i = 0; i < ListCentros.size(); i++) {
            jCDescripcion.addItem(ListCentros.get(i).toString().trim());
            
        }
    }
    public void Cargar_Cajas(){
       this.Cabecera.add("Codigo");
       this.Cabecera.add("Descripcion");
       this.Cabecera.add("Centro de Costo");
       this.Cabecera.add("Descripcion del Centro");
       this.Cabecera.add("Estado");
       this.Detalle_Fila.clear();
       this.Detalle_Fila=  this.Cajas.getCajeros();
       jTCajas.setModel(new javax.swing.table.DefaultTableModel(this.Detalle_Fila, this.Cabecera));
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JCajeros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JCajeros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JCajeros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JCajeros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new JCajeros(null,null,null).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jCDescripcion;
    private javax.swing.JComboBox jCEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTCajas;
    private javax.swing.JTextField jTCodigo;
    private javax.swing.JTextField jTCodigoCentro;
    private javax.swing.JTextField jTNombre;
    // End of variables declaration//GEN-END:variables
}
