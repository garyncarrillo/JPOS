    
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Garyn Carrillo
 */
public class JFactura {
    
   private String Numero, Fecha, Hora, CodigoCentro,CodigoCajero, Estado;
   private double TotalIva, TotalOtroImpuesto,TotalPagar;
   private JConeccion JBase_Datos;
   private Connection Cn; 
   private Vector Cabecera;
   private Vector Cabecera_Fila;
   private Vector Detalle;
   private Vector Detalle_Fila;
   private String NumeroFormato = "###,###,###,###.##";
   private DecimalFormat JFormato ;
   private JArticulos Articulo;
   
   public JFactura(JConeccion JBase_Datos3, Connection Cn2){
        this.JBase_Datos = JBase_Datos3;
        this.Cn = Cn2;
        this.Cabecera_Fila =  new Vector();
        this.Detalle_Fila = new Vector();
        JFormato= new DecimalFormat(NumeroFormato);
        this.Articulo = new JArticulos(this.JBase_Datos, this.Cn);
   } 
   public void setEstado(String Estado){
       this.Estado = Estado;
   }
   public void setNumeroFactura(String Numero){
       this.Numero = Numero;
   }
   public void setFecga(String Fec){
       this.Fecha = Fec;
   }
   public void setHora(String Hour){
       this.Hora = Hour;
   }
   public void setCentroCosto(String Centro){
       this.CodigoCentro = Centro;
   }
   public String getCentroCosto(){
       return this.CodigoCentro;
   }
   public void setFecha(String Fecha){
       this.Fecha = Fecha;
   }
   public String getFecha(){
       return this.Fecha;
   }
   public String getHora(){
       return this.Hora;
   }
   public void setTotalIva(double Iva){
       this.TotalIva = Iva;
   }
   public double getTotalIva(){
       return this.TotalIva;
   }
   public void setTotalImpuestos(double Vlr){
       this.TotalOtroImpuesto = Vlr;
   }
   public double getTotalImpuesto(){
       return this.TotalOtroImpuesto;
   }
   public void setTotaPagar(double Pagar){
       this.TotalPagar = Pagar;
   }
   public double getTotalPagar(){
       return this.TotalPagar;
   }
   public boolean setAnulaFactura(String NumFactura){
       boolean Rs = false;
       try {
           String Str_Sql = "update JCabFactura set "
                    + " Estado='A'"
                    + "where Numero='"+NumFactura+"'";
           Rs =  JBase_Datos.Exc_Sql(this.Cn,Str_Sql);
           this.setAnulaDetalleFactura(NumFactura);
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Class:JFactura: setAnulaFactura()"+e.getMessage()); 
       }
       return Rs;
   }
   public boolean setAnulaDetalleFactura(String NumFactura){
       boolean Control=false;
       try {
           String Str_Sql =  "select plu, cantidad from JDetFactura where Numero='"+NumFactura+"'";
           ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
           String XPlu ;
           int XCant;
           while(Rs.next()){
               XPlu =  Rs.getString("plu");
               XCant = Rs.getInt("cantidad");
               this.Articulo.getArticulo_Plu(XPlu);
               Control = this.Articulo.Movimiento(XPlu, XCant, "Aplications:JAnulacion_Facturacion_"+NumFactura, "+");
           } 
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Class:JFactura: setAnulaDetalleFactura()"+e.getMessage()); 
       }
       return Control;
   }
   public void setCabecera(){
       try {
           String Str_Sql ="Select * from JCabFactura where Numero="+this.Numero+" and Fecha="+this.Fecha+" and "
                         + " CodigoCentroCosto="+this.CodigoCentro;
           ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
           if(!Rs.next()){
               Str_Sql ="Insert Into JCabFactura () values ";
           }else{
               Str_Sql ="Update JCabFactura set "
                       + " where Numero="+this.Numero+" and Fecha="+this.Fecha+" and "
                         + " CodigoCentroCosto="+this.CodigoCentro;
           }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Class:JFactura:setCabecera()"+e.getMessage()); 
       }
   }
   public void setDetalle(){
       try {
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Class:JFactura:setDetalle()"+e.getMessage()); 
       }
   }
   public void getCabeceraFactura(){
       try {
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Class:JFactura:getCabeceraFactura()"+e.getMessage()); 
       }
   }
   public int getNumeroFactura(String SinDian, String CentroCosto){
       int NumeroFactura = 0;
       try {
          if (SinDian.trim().equals("Sin")){
              String  Str_Sql = "select max(Numero) as Numero from JCabFactura";
              ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
              if(Rs.next()){
                  NumeroFactura = Rs.getInt("Numero");
            }
          }else{
             // Con resolucion de la Dian por Centro de Costo
          }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Class:JFactura:getCabeceraFactura()"+e.getMessage()); 
       }
       return NumeroFactura+1;
   }
   public Vector  getCabecera(String Busqueda, String Busqueda2, int Tipo){
       this.Cabecera_Fila.clear();
        try {
            String Str_Sql = "";
            if(Tipo==1){
                Str_Sql= "select * from JCabFactura where Numero like '%"+Busqueda.trim()+"%'";
            }
            if(Tipo==2){
                Str_Sql= "select * from JCabFactura where fecha='"+Busqueda.trim()+"'";
            }
            if(Tipo==3){
                Str_Sql= "select * from JCabFactura where fecha>='"+Busqueda.trim()+"' and Fecha <='"+Busqueda2.trim()+"'";
            }
            ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
            while(Rs.next()){
                this.Cabecera = new Vector();
                this.Cabecera.add(Rs.getString("Numero"));
                this.Cabecera.add(Rs.getString("Fecha"));
                this.Cabecera.add(JFormato.format( Rs.getDouble("TotaPagar")));
                this.Cabecera.add(JFormato.format( Rs.getDouble("Descuento")));
                this.Cabecera.add(Rs.getString("Estado"));
                this.Cabecera_Fila.add(this.Cabecera);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"getCabecera(Busqueda,Busqueda2,Tipo) "+e.getMessage());
        }
        return this.Cabecera_Fila;
    }
   public Vector getDetalleFactura(String Numero){
        this.Detalle_Fila.clear();
        try {
            String Str_Sql= "select t1.*, t2.NombreLargo from JDetFactura as t1 LEFT OUTER JOIN JArticulos as t2 on  t1.Plu =t2.Plu"
                    + " where  Numero ='"+Numero.trim()+"' ";
            ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
            while(Rs.next()){
                this.Detalle = new Vector();
                this.Detalle.add(Rs.getString("Plu"));
                this.Detalle.add(Rs.getString("NombreLargo"));
                this.Detalle.add(JFormato.format(Rs.getDouble("PrecioUnitario")));
                this.Detalle.add(Rs.getDouble("Cantidad"));
                this.Detalle.add(JFormato.format(Rs.getDouble("TotalItem")));
                this.Detalle_Fila.add(this.Detalle);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"getDetalle(Numero) "+e.getMessage());
        }
       return this.Detalle_Fila;
   }
}
