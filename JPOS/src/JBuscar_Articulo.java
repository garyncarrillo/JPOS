
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Garyn Carrillo
 */
public class JBuscar_Articulo extends javax.swing.JDialog {

    /**
     * Creates new form JBuscar_Articulo
     */
    private JConeccion JBase_Datos;
    private Connection Cn;
    private Vector Cabecera;
    private Vector M_Detalle;
    private Vector Detalle;
    private JFacturacion Frm;
    private String NumeroFormato = "###,###,###,###.##";
    private DecimalFormat JFormato ;
    private boolean SwControlBusqueda;
    private JMArticulos Maestro_Articulos;
    private String TipoMovimiento;
    
    public JBuscar_Articulo(JConeccion JBase_Datos3, Connection Cn2, JFacturacion Frm) {
       this.Frm = Frm;
       this.Maestro_Articulos = null;
       this.TipoMovimiento = null;
       this.JBase_Datos = JBase_Datos3;
       this.Cn = Cn2;
       Cabecera = new Vector();
       M_Detalle = new Vector();
       Cabecera.add("Plu");
       Cabecera.add("Descripcion");
       Cabecera.add("Precio de Venta");
       Cabecera.add("Marca");
       initComponents();
       jRadioButton1.setSelected(true);
       JFormato= new DecimalFormat(NumeroFormato);
       SwControlBusqueda= false;
    }
    

    
    
    public JBuscar_Articulo(JConeccion JBase_Datos3, Connection Cn2, JMArticulos MArticulos, String Tipo) {
       this.Maestro_Articulos = MArticulos;
       this.TipoMovimiento = Tipo;
       this.Frm = null;
       this.JBase_Datos = JBase_Datos3;
       this.Cn = Cn2;
       Cabecera = new Vector();
       M_Detalle = new Vector();
       Cabecera.add("Plu");
       Cabecera.add("Descripcion");
       Cabecera.add("Precio de Venta");
       Cabecera.add("Descripcion Larga");
       ;
       initComponents();
       jRadioButton1.setSelected(true);
       JFormato= new DecimalFormat(NumeroFormato);
       SwControlBusqueda= false;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableBuscarArticulos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        TxtBuscar = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar Articulo");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTableBuscarArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Plu", "Descripcion", "Precio Venta", "Marca"
            }
        ));
        jScrollPane1.setViewportView(jTableBuscarArticulos);

        jButton1.setText("Buscar..");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jRadioButton1.setText("Plu");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Descripcion");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jRadioButton3.setText("Marca");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton4.setText("Codigo de Barra");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jRadioButton1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButton2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButton3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButton4))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(TxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton1)))
                                .addGap(0, 29, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        jRadioButton1.setSelected(true);
        jRadioButton2.setSelected(false);
        jRadioButton3.setSelected(false);
        jRadioButton4.setSelected(false);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(true);
        jRadioButton3.setSelected(false);
        jRadioButton4.setSelected(false);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       if(SwControlBusqueda){
           this.M_Detalle.clear();
       }
       if ((jRadioButton1.getSelectedObjects()!=null) || (jRadioButton2.getSelectedObjects()!=null) || (jRadioButton3.getSelectedObjects()!=null) || (jRadioButton4.getSelectedObjects()!=null)){
           Buscar();
       }else{
           JOptionPane.showMessageDialog(this," Debes seleccionar la forma de Busqueda ");
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void Buscar_Articulo(int Plu){
        try {
            String Str_Sql=" select * from JArticulos where Plu='"+Plu+"'";
            ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
            if(Rs.next()){
                
                //(int plu, String NombreLargo, String Nombre, String CodigoBarra, String Catgoria, String Ubicacion, double Costo,
                     //double Precio_min , double precio_max, double iva, double Otro_imp, String Estado)
                this.Maestro_Articulos.set_Modificacion(Plu,Rs.getString("NombreLargo"),Rs.getString("NombreCorto"), Rs.getString("CodigoBarra"),Rs.getString("Categoria"),Rs.getString("Ubicacion"),Rs.getInt("Costo"),Rs.getInt("PrecioVentaMin"),Rs.getInt("PrecioVenta"),Rs.getInt("PorCentajeIva"),Rs.getInt("PorcentajeOtroImpuesto"),Rs.getString("Estado"));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this," Buscar_Articulo() "+e.getMessage());
        }
    }
    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(this.Frm!= null){
           try{
                int Plu = Integer.parseInt(jTableBuscarArticulos.getValueAt(jTableBuscarArticulos.getSelectedRow(),0).toString().trim());
                this.Frm.setTxtPlu(""+Plu);
                this.Frm.repaint();
                this.dispose();
           }catch(Exception e){
               JOptionPane.showMessageDialog(this," Debes seleccionar seleccionar un articulo ");
           }
        }
        if(this.Maestro_Articulos!= null){
          try{
                int  Plu = Integer.parseInt(jTableBuscarArticulos.getValueAt(jTableBuscarArticulos.getSelectedRow(),0).toString().trim());
                String  Descripcion = jTableBuscarArticulos.getValueAt(jTableBuscarArticulos.getSelectedRow(),1).toString().trim();
                if(this.TipoMovimiento.equals("+")){
                    this.Maestro_Articulos.setPluArtuculoPositivo(""+Plu , Descripcion);
                }
                if(this.TipoMovimiento.equals("-")){
                    this.Maestro_Articulos.setPluArtuculoNegativo(""+Plu ,Descripcion);
                }
                if(this.TipoMovimiento.equals("C")){
                    this.Maestro_Articulos.setPluArtuculoConsulta(""+Plu);
                }
                if(this.TipoMovimiento.equals("U_A")){
                    this.Buscar_Articulo(Plu);
                }
                this.Maestro_Articulos.repaint();
                this.dispose();
          }catch(Exception e ){
            JOptionPane.showMessageDialog(this," Debes seleccionar seleccionar un articulo ");
          }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        jRadioButton3.setSelected(true);
        jRadioButton4.setSelected(false);

    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        jRadioButton3.setSelected(false);
        jRadioButton4.setSelected(true);
    }//GEN-LAST:event_jRadioButton4ActionPerformed
    public void Buscar(){
        try {
           String Str_Sql = "";
           if (jRadioButton1.getSelectedObjects()!=null){
             Str_Sql = "Select Plu,NombreLargo,NombreCorto,PrecioVenta from JArticulos where plu like'"+TxtBuscar.getText().trim()+"%' "
                     + "order by plu asc";
           }
           if (jRadioButton2.getSelectedObjects()!=null){
             Str_Sql = "Select Plu,NombreLargo,NombreCorto,PrecioVenta from JArticulos where NombreLargo like '%"+TxtBuscar.getText().trim()+"%'"
                     + "order by plu asc";
           }
           if (jRadioButton3.getSelectedObjects()!=null){
             Str_Sql = "Select Plu,NombreLargo,NombreCorto,PrecioVenta from JArticulos where NombreCorto like '%"+TxtBuscar.getText().trim()+"%'"
                     + "order by plu asc";
           }
           if (jRadioButton4.getSelectedObjects()!=null){
             Str_Sql = "Select Plu,NombreLargo,NombreCorto,PrecioVenta from JArticulos where CodigoBarra like '%"+TxtBuscar.getText().trim()+"%'"
                     + "order by plu asc";
           }
           
           
           ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
           while(Rs.next()){
               this.Detalle = new Vector();
               this.Detalle.add(Rs.getString("Plu"));
               this.Detalle.add(Rs.getString("NombreLargo"));
               this.Detalle.add( JFormato.format(Rs.getInt("PrecioVenta")));
               this.Detalle.add(Rs.getString("NombreCorto"));
               
               this.M_Detalle.add(this.Detalle);
               this.SwControlBusqueda = true;
           }
           jTableBuscarArticulos.setModel(new javax.swing.table.DefaultTableModel(this.M_Detalle, Cabecera));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this," Class:JBuscar_Articulo:Buscar(): "+e.getMessage());
        }
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
            java.util.logging.Logger.getLogger(JBuscar_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JBuscar_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JBuscar_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JBuscar_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new JBuscar_Articulo(null, null, null,null).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtBuscar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableBuscarArticulos;
    // End of variables declaration//GEN-END:variables
}
