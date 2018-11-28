
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
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
public class JVentasDiarias extends JInternalFrame{
    private JConeccion JBase_Datos;
    private Connection Cn;
    private Vector Cabecera;
    private Vector M_Detalle;
    private Vector Detalle;
    
    
    private Vector VCabecera;
    private Vector V_M_Detalle;
    private Vector VDetalle;
    
    
    private String NumeroFormato = "###,###,###,###.##";
    private DecimalFormat JFormato ;
    private JArticulos Articulo;
    
    private Vector Comision_Cab;
    private Vector Comision_Det;
    private Vector Comision_Fila;
    
    /**
     * Creates new form JVentasDiarias
     */
    public JVentasDiarias(JConeccion JBase_Datos3, Connection Cn2) {
        this.JBase_Datos = JBase_Datos3;
        this.Cn = Cn2;
        this.Articulo = new JArticulos(this.JBase_Datos, this.Cn);
        this.Cabecera = new Vector();
        this.Detalle = new Vector();
        Comision_Cab = new Vector();
        Comision_Det = new Vector();
        this.Comision_Fila = new Vector();
        JFormato= new DecimalFormat(NumeroFormato);
        
        this.Comision_Cab.add("Vendedor");
        this.Comision_Cab.add("Comision-Costo");
        this.Comision_Cab.add("Precio de Venta");
        
        
        this.Cabecera.add("Num Factura");
        this.Cabecera.add("Fecha");
        this.Cabecera.add("Total Iva");
        this.Cabecera.add("Total otros Impuesto");
        this.Cabecera.add("Descuento");
        this.Cabecera.add("Total a pagar");
        this.Cabecera.add("Medio");
        this.VDetalle = new Vector();
        this.VCabecera = new Vector();
        this.VCabecera.add("Num Factura");
        this.VCabecera.add("Fecha");
        this.VCabecera.add("Plu");
        this.VCabecera.add("Cantidad");
        this.VCabecera.add("Precio Venta Unitario");
        this.VCabecera.add("Costo Unitario");
        this.VCabecera.add("Precio Venta ");
        this.VCabecera.add("Costo ");
        this.VCabecera.add("Utilidad Uni");
        this.VCabecera.add("Utilidad Tot");
        
        initComponents();
        this.getFechas();
    }
    public void  getFechas(){
        try {
           String Str_Sql = "select distinct fecha from JCabFactura order by 1 desc";
           ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
           while(Rs.next()){
                jCFechas.addItem(Rs.getString("fecha"));   
           }  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Cargar_Fecha "+e.getMessage());
        }
    }
    
    public void getIngresos(String Fecha, String Fecha2){
        try {
            String Str_Sql = "select t1.numero, t1.fecha, Plu, PrecioUnitario,Cantidad, TotalItem from JCabFactura as t1 ,JDetFactura as t2  "
                + "where t1.numero = t2.numero and "
                + " fecha>='"+Fecha+"' and fecha <='"+Fecha2+"' and Estado ='C'";
            double UCosto = 0, UUnit = 0, UTotal= 0, TotalCosto = 0, TotalPrecioVenta = 0;
            ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
            String Plu;
            double Cantidad;
            double PrecioUnitario, PrecioTotal;
            String NumeroFactura;
            this.VDetalle.clear();
            this.Articulo = new JArticulos(this.JBase_Datos, this.Cn);
            while(Rs.next()){
                Plu = Rs.getString("Plu");
                Articulo.getArticulo_Plu(Plu);
                this.V_M_Detalle = new Vector();
                NumeroFactura = Rs.getString("numero");
                this.V_M_Detalle.add(NumeroFactura);
                this.V_M_Detalle.add(Rs.getString("fecha"));
                this.V_M_Detalle.add(Plu);
                Cantidad = Rs.getDouble("Cantidad");
                this.V_M_Detalle.add(JFormato.format(Cantidad));
                PrecioUnitario = Rs.getDouble("PrecioUnitario");
                this.V_M_Detalle.add(JFormato.format(PrecioUnitario));
                this.V_M_Detalle.add(JFormato.format(this.Articulo.getCosto()));
                PrecioTotal= Rs.getDouble("TotalItem");
                this.V_M_Detalle.add(JFormato.format(PrecioTotal));
                UCosto = this.Articulo.getCosto();
                this.V_M_Detalle.add(JFormato.format(UCosto*Cantidad));
                this.V_M_Detalle.add(JFormato.format(PrecioUnitario-UCosto));
                this.V_M_Detalle.add(JFormato.format(PrecioTotal-(UCosto*Cantidad)));
                UUnit = UUnit + (PrecioUnitario-UCosto);
                UTotal= UTotal + (PrecioTotal - (UCosto*Cantidad));
                this.VDetalle.add(this.V_M_Detalle);
            }
            if(UUnit!=0){
               this.V_M_Detalle = new Vector();
               this.V_M_Detalle.add("TOTAL");
               this.V_M_Detalle.add("-");
               this.V_M_Detalle.add("-");
               this.V_M_Detalle.add("-");
               this.V_M_Detalle.add("-");
               this.V_M_Detalle.add("-");
               this.V_M_Detalle.add("-");
               this.V_M_Detalle.add("----->>>>>");
               this.V_M_Detalle.add(JFormato.format(UUnit));
               this.V_M_Detalle.add(JFormato.format(UTotal));
               this.VDetalle.add(this.V_M_Detalle);
            }
            this.Articulo = null;
            JTUtillidad.setModel(new javax.swing.table.DefaultTableModel(this.VDetalle, this.VCabecera));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"getIngresos() "+e.getMessage());
        }
    }
    public void  getVentas_Dia(String Fecha, String Fecha2, int Tipo){
        try {
           String Str_Sql="";
           String Str_Sql_Comision ="";
           if(Tipo==1){
            Str_Sql = "select Numero,Fecha,TotalIva,TotalOtroImpuesto,Descuento,TotaPagar,CodigoMedio from JCabFactura where fecha='"+Fecha+"' and Estado ='C' ";
            
            Str_Sql_Comision = "SELECT IdVendedora , sum(Costo*Cantidad) as C_Costo ,  sum(PrecioUnitario*Cantidad) as C_PrecioVenta  FROM ( " +
                    "       SELECT idVendedora , T2.Plu,  NombreLargo,T2.Cantidad, T2.PrecioUnitario, Costo " +
                    "       FROM JCabFactura as T1 , JDetFactura as T2 , JArticulos as T3 " +
                    "       Where T1.Numero=T2.Numero AND T2.Plu=T3.Plu " +
                    "       and fecha='"+Fecha+"' and T1.Estado ='C' "+
                    "      ) as T1 " +
                    " group by idVendedora";
           }else{
               Str_Sql = "select Numero,Fecha,TotalIva,TotalOtroImpuesto,Descuento,TotaPagar,CodigoMedio from JCabFactura where fecha>='"+Fecha+"' and fecha <='"+Fecha2+"' and Estado ='C'";
               
               Str_Sql_Comision = "SELECT idVendedora ,  T2.Plu, T2.Cantidad, T2.PrecioUnitario, Costo " +
                               " FROM JCabFactura as T1 , JDetFactura as T2 , JArticulos as T3 " +
                               " Where T1.Numero=T2.Numero AND T2.Plu=T3.Plu " +
                               " fecha>='"+Fecha+"' and fecha <='"+Fecha2+"' and Estado ='C'";
           }
           ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
           this.Detalle.clear();
           double TIva = 0, TImpuesto =0, TDescuento = 0, TPagar = 0; 
           while(Rs.next()){
                this.M_Detalle = new Vector();
                this.M_Detalle.add(Rs.getString("Numero"));
                this.M_Detalle.add(Rs.getString("Fecha"));
                double Tmp = Rs.getDouble("TotalIva");
                TIva = TIva+ Tmp;
                this.M_Detalle.add(JFormato.format(Tmp));
                Tmp =  Rs.getDouble("TotalOtroImpuesto");
                TImpuesto = TImpuesto +Tmp;
                this.M_Detalle.add(JFormato.format(Tmp));
                Tmp = Rs.getDouble("Descuento");
                TDescuento = TDescuento + Tmp;
                this.M_Detalle.add(JFormato.format(Tmp));
                Tmp = Rs.getDouble("TotaPagar");
                TPagar = TPagar + Tmp;
                this.M_Detalle.add(JFormato.format(Tmp));
                this.M_Detalle.add(Rs.getString("CodigoMedio"));
                this.Detalle.add(M_Detalle);
           }  
           
           
           // Version 2.11.28 adicion de comision
           //System.out.println(" "+Str_Sql_Comision);
           ResultSet Result_comision =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql_Comision);
           this.Comision_Fila.clear();
           double C_Total_Costo = 0 , C_Total_Venta = 0;
           while(Result_comision.next()){
               this.Comision_Det= new Vector();
               this.Comision_Det.add(Result_comision.getString("idVendedora"));
               double tmp_costo = Result_comision.getDouble("C_Costo");
               double tmp_venta =  Result_comision.getDouble("C_PrecioVenta");
               C_Total_Costo =  C_Total_Costo + tmp_costo;
               C_Total_Venta = C_Total_Venta + tmp_venta;
               this.Comision_Det.add(JFormato.format(tmp_costo));
               this.Comision_Det.add(JFormato.format(tmp_venta));
               this.Comision_Fila.add(this.Comision_Det);
           }
           
           if(TPagar!= 0){
                this.M_Detalle = new Vector();
                this.M_Detalle.add("Total");
                this.M_Detalle.add("->");
                this.M_Detalle.add(JFormato.format(TIva));
                this.M_Detalle.add(JFormato.format(TImpuesto));
                this.M_Detalle.add(JFormato.format(TDescuento));
                this.M_Detalle.add(JFormato.format(TPagar));
                this.Detalle.add(M_Detalle);
                
                // adicion de tabla de comision 
               this.Comision_Det= new Vector();
               this.Comision_Det.add("Total --->>  ");
               this.Comision_Det.add(JFormato.format(C_Total_Costo));
               this.Comision_Det.add(JFormato.format(C_Total_Venta));
               this.Comision_Fila.add(this.Comision_Det);
                
           }
           if(Tipo==1){
                jFacturas.setModel(new javax.swing.table.DefaultTableModel(this.Detalle, Cabecera));
                jTComision.setModel(new javax.swing.table.DefaultTableModel(this.Comision_Fila, this.Comision_Cab));
           }else{
               jFactura2.setModel(new javax.swing.table.DefaultTableModel(this.Detalle, Cabecera));
           }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Cargar_Fecha "+e.getMessage());
        }
    }


     /* This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jCFechas = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jFacturas = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTComision = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jTFechaInicial = new javax.swing.JTextField();
        jTFechaFinal = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jFactura2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jTFechaFinal1 = new javax.swing.JTextField();
        jTFechaInicial1 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        JTUtillidad = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setTitle("Informe de Ventas por Dias.");

        jButton1.setText("Buscar");
        jButton1.setToolTipText("Solo estado = 'C' sin anular");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jFacturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Num Factura", "Fecha", "Total Iva", "Total otros Impuesto", "Descuento", "Total a Pagar", "Medio"
            }
        ));
        jScrollPane1.setViewportView(jFacturas);

        jTComision.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vendedor", "Comision-Costo", "Precio de Venta"
            }
        ));
        jScrollPane4.setViewportView(jTComision);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCFechas, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1068, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCFechas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Por Fecha", jPanel1);

        jButton2.setText("Buscar");
        jButton2.setToolTipText("Solo estado = 'C' sin anular");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jFactura2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Num Factura", "Fecha", "Total Iva", "Total otros Impuesto", "Total Descuentos", "Tota a Pagar", "Medios"
            }
        ));
        jScrollPane2.setViewportView(jFactura2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTFechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jTFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1089, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Rango de Fechas", jPanel2);

        jButton3.setText("Buscar");
        jButton3.setToolTipText("Solo estado = 'C' sin anular");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        JTUtillidad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Num Factura", "Fecha", "Plu", "Cantidad", "Precio de Venta Unitario", "Costo Unitario", "Precio Venta", "Costo", "Utilidad Uni", "Utilidad Tot"
            }
        ));
        jScrollPane3.setViewportView(JTUtillidad);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTFechaInicial1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jTFechaFinal1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jButton3))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1089, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFechaFinal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFechaInicial1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Rango de Fechas Utilidad", jPanel3);

        jButton4.setText("Ver");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jMenu1.setText("Ejecutar");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItem2.setText("Salir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton4))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.getVentas_Dia(jCFechas.getSelectedItem().toString() ," ", 1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if( !this.jTFechaInicial.getText().equals("") && !this.jTFechaFinal.getText().equals("")){
            this.getVentas_Dia(this.jTFechaInicial.getText(), this.jTFechaFinal.getText(), 2);
        }else{
            JOptionPane.showMessageDialog(this,"ingresar fecha en forma AAAAMMDD ");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         if( !this.jTFechaInicial1.getText().equals("") && !this.jTFechaFinal1.getText().equals("")){
                this.getIngresos(jTFechaInicial1.getText(), this.jTFechaFinal1.getText());
         }else{
            JOptionPane.showMessageDialog(this,"ingresar fecha en forma AAAAMMDD ");
        }
                 
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String P_Id_Vendedor = jTComision.getValueAt(jTComision.getSelectedRow(),0).toString().trim();
        String P_Fecha = jCFechas.getSelectedItem().toString().trim();
        JDetalle_x_Vendedora Frm_JDetalle_x_Vendedora = new JDetalle_x_Vendedora(this.JBase_Datos, this.Cn ,P_Id_Vendedor, P_Fecha);
        Frm_JDetalle_x_Vendedora.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(JVentasDiarias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JVentasDiarias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JVentasDiarias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JVentasDiarias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new JVentasDiarias(null,null).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTUtillidad;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jCFechas;
    private javax.swing.JTable jFactura2;
    private javax.swing.JTable jFacturas;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTComision;
    private javax.swing.JTextField jTFechaFinal;
    private javax.swing.JTextField jTFechaFinal1;
    private javax.swing.JTextField jTFechaInicial;
    private javax.swing.JTextField jTFechaInicial1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
