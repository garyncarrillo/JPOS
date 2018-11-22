 
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
public class JArticulos {
    private String NumeroFormato = "###,###,###,###.##";
    private DecimalFormat JFormato ;
    private String Plu, NombreLargo, NombreCorto,CodigoBarra,Categoria,Ubicacion, Estado;
    private double Costo, PrecioVenta, PorcentajeIva, PorcentaOtroImpuesto, PrecioVentaMinimo,Cantidad;
    private JConeccion JBase_Datos;
    private Connection Cn; 
    private Vector Detalle_Movimiento;
    private Vector Movimiento_Fila;
    
    private Vector Detalle_Inventario;
    private Vector Inventario_Fila;
    
    
    public JArticulos(JConeccion JBase_Datos3, Connection Cn2){
        this.JBase_Datos = JBase_Datos3;
        this.Cn = Cn2;
        this.Movimiento_Fila = new Vector();
        this.Inventario_Fila = new Vector ();
        JFormato= new DecimalFormat(NumeroFormato);
    }
    public String getPlu(){
        return  this.Plu;
    }
    public void setPlu(String PPlu){
        this.Plu = PPlu;
    }
    public String getNombreLargo(){
        return this.NombreLargo;
    }
    public void setNombreLargo(String Nombre){
        this.NombreLargo =  Nombre;
    }
    public String getNombreCorto(){
        return this.NombreCorto;
    }
    public void setNombreCorto(String Nom){
        this.NombreCorto = Nom;
    }
    public String getCodigoBarra(){
        return this.CodigoBarra;
    }
    public void setCodigoBarra(String Codigo){
        this.CodigoBarra = Codigo;
    }
    public String getCategoria(){
        return this.Categoria;
    }
    public void setCategoria(String Cat){
        this.Categoria = Cat;
    }
    public String getUbicacion(){
        return this.Ubicacion;
    }
    public void setUbicacion(String Ubi){
        this.Ubicacion = Ubi;
    }
    public String getEstado(   ){
        return this.Estado;
    }
    public void setEstado(String Est){
        this.Estado = Est;
    }
    public double getCosto(){
        return this.Costo;
    }
    public void setCosto(double Cs){
        this.Costo = Cs;
    }
    public double getPrecioVenta(){
        return this.PrecioVenta;
    }
    public void setPrecioVenta(double Valor){
        this.PrecioVenta = Valor;
    }
    public double getPrecioVentaMinimo(){
        return PrecioVentaMinimo;
    }
    public double getPorcentajeIva(){
        return this.PorcentajeIva;
    }
    public double getPorcentajeOtro(){
        return this.PorcentaOtroImpuesto;
    }
    public double getCantidad(){
        return this.Cantidad;
    }
    public boolean Movimiento(String Codigo, double Cantidad ,String Factura, String Tipo){
        boolean Rs=false;
        try {
            String Str_Sql ="select Plu, Cantidad from JArticulos where plu='"+Codigo+"'";
            ResultSet Rs2 =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
            double CantidadActual = 0;
            double Operacion = 0;
            boolean Control = false;
            if(Rs2.next()){
                CantidadActual = Rs2.getDouble("Cantidad");
                Control = true;
            }
            if(Control){
                if(Tipo.trim().equals("+")){
                    Operacion = CantidadActual+Cantidad;
                }else{
                    Operacion = CantidadActual-Cantidad;
                }
            
                if(this.Crear_Movimiento(Codigo, Factura,Tipo, Cantidad)){
                  try{
                        Str_Sql = "update JArticulos "
                                + "set cantidad="+Operacion
                                + " where plu='"+Codigo+"'";
                        Rs =  JBase_Datos.Exc_Sql(this.Cn,Str_Sql);
                  }catch(Exception e){
                      JOptionPane.showMessageDialog(null,"Actualiza Cantidad en el Maestro "+e.getMessage());
                  }
                }else{
                    Rs =false;
                }
            }else{
                Rs = false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Movimiento(Codigo, Cantidad , Tipo) "+e.getMessage());
        }
        return Rs;
    }
    private boolean Crear_Movimiento(String Plu, String Factura,String Tipo, double Cantidad){
        boolean Rs = false;
        try {
            String Str_Sql ="INSERT INTO JMovimientos_Articulo (NoFactura,Plu,Tipo,Cantidad) "
                    + "VALUES ('"+Factura+"','"+Plu.trim()+"','"+Tipo.trim()+"',"+Cantidad+")";
            Rs =  JBase_Datos.Exc_Sql(this.Cn,Str_Sql);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Crear_Movimiento(..) "+e.getMessage());
        }
        return Rs;
    }
    
    public boolean Crear_Articulo(String Codigo, String NombreLargo, String NombreCorto,String CodigoBarra, String Categoria, String Ubicacion, double Costo,
                               double PrecioMin, double PrecioMax, int Cantidad, double Iva, double OtroImpuesto, String Estado){
        boolean Rs = false;
        try {
            String Str_Sql = "";
            if(!this.Existe(Codigo)){
                //Crear
                Str_Sql = "INSERT INTO JArticulos "
                        + "(Plu,NombreLargo,NombreCorto,CodigoBarra,Categoria,Ubicacion,Costo,PrecioVentaMin,PrecioVenta,Cantidad,PorCentajeIva,PorcentajeOtroImpuesto,Estado)"
                        + "VALUES('"+Codigo.trim()+"','"+NombreLargo.trim()+"','"+NombreCorto.trim()+"','"+CodigoBarra.trim()+"','"+Categoria.trim()+"','"+Ubicacion.trim()+"',"+Costo+","+PrecioMin+","+PrecioMax+","+Cantidad+","+Iva+","+OtroImpuesto+",'"+Estado.trim()+"')";
            }else{
                //Actualizar
                     Str_Sql = "update JArticulos "
                        + "set NombreLargo ='"+NombreLargo.trim()+"'"
                        + " ,NombreCorto='"+NombreCorto.trim()+"'"
                        + " ,CodigoBarra='"+CodigoBarra.trim()+"'"
                        + " ,Categoria='"+Categoria.trim()+"'"
                        + " ,Ubicacion='"+Ubicacion.trim()+"'"
                        + " ,Costo="+Costo
                        + " ,PrecioVentaMin="+PrecioMin
                        + " ,PrecioVenta="+PrecioMax
                        + " ,Cantidad="+Cantidad
                        + " ,PorCentajeIva="+Iva
                        + " ,PorcentajeOtroImpuesto="+OtroImpuesto
                        + " ,Estado='"+Estado.trim()+"'"
                        + " WHERE plu='"+Codigo.trim()+"'";
            }
            Rs =  JBase_Datos.Exc_Sql(this.Cn,Str_Sql);
         } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Crear_Articulo(.....) "+e.getMessage());
        }
        return Rs;
    }
    
    
    public Vector getInventario(){
        try {
            String Str_Sql = "select * from JArticulos "
                    + "order by plu";
            ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
            this.Inventario_Fila.clear();
            int DCantidad = 0;
            double TCantidad = 0;
            double DCosto = 0,  DVentaMin = 0,  DVentaMax = 0;
            double TCosto = 0,  TVentaMin = 0,  TVentaMax = 0;
            boolean Sw = false;
            while(Rs.next()){
                    this.Detalle_Inventario = new Vector();
                    this.Detalle_Inventario.add(Rs.getString("Plu"));
                    this.Detalle_Inventario.add(Rs.getString("NombreLargo"));
                    DCantidad =  Rs.getInt("Cantidad");
                    TCantidad = TCantidad + DCantidad;
                    DCosto = Rs.getDouble("Costo");
                    TCosto = TCosto +  (DCosto*DCantidad);
                    DVentaMin =  Rs.getDouble("PrecioVentaMin");
                    TVentaMin = TVentaMin +  (DVentaMin*DCantidad);
                    DVentaMax = Rs.getDouble("PrecioVenta");
                    TVentaMax = TVentaMax +  (DVentaMax*DCantidad);
                    this.Detalle_Inventario.add(DCantidad);
                    this.Detalle_Inventario.add(this.JFormato.format(DCosto*DCantidad));
                    this.Detalle_Inventario.add(this.JFormato.format(DVentaMin*DCantidad));
                    this.Detalle_Inventario.add(this.JFormato.format(DVentaMax*DCantidad));
                    this.Inventario_Fila.add(this.Detalle_Inventario);
                    Sw=true;
            }
            if(Sw){
                    this.Detalle_Inventario = new Vector();
                    this.Detalle_Inventario.add("TOTAL");
                    this.Detalle_Inventario.add("->>>");
                    this.Detalle_Inventario.add(TCantidad);
                    this.Detalle_Inventario.add(this.JFormato.format(TCosto));
                    this.Detalle_Inventario.add(this.JFormato.format(TVentaMin));
                    this.Detalle_Inventario.add(this.JFormato.format(TVentaMax));
                    this.Inventario_Fila.add(this.Detalle_Inventario);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"getInventario() "+e.getMessage());
        }
        return this.Inventario_Fila;
    }
    
    
    
    
    public Vector getMovimiento(String Codigo){
        try {
            String Str_Sql = "select * from JMovimientos_Articulo where Plu ='"+Codigo+"' "
                    + "order by Tipo";
            ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
            this.Movimiento_Fila.clear();
            while(Rs.next()){
                    this.Detalle_Movimiento = new Vector();
                    this.Detalle_Movimiento.add(Rs.getString("Plu"));
                    this.Detalle_Movimiento.add(Rs.getString("NoFactura"));
                    this.Detalle_Movimiento.add(Rs.getString("Cantidad"));
                    this.Detalle_Movimiento.add(Rs.getString("Tipo"));
                    this.Movimiento_Fila.add(this.Detalle_Movimiento);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"getMovimiento() "+e.getMessage());
        }
        return this.Movimiento_Fila;
    }
    
    public boolean Existe(String Codigo){
        boolean Control = false;
        try {
            String Str_Sql ="select * from JArticulos where Estado='ACTIVO'  and plu='"+Codigo.trim()+"'";
            ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
            if(Rs.next()){
                Control = true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Existe(Plu) "+e.getMessage());
        }
        return Control;
    }
    public boolean getArticulo_Plu(String Plu){
        boolean Control = false;
        try {
            String Str_Sql ="select * from JArticulos where Estado='ACTIVO'  and plu='"+Plu+"'";
            ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
            while (Rs.next()){
                try{
                    this.Plu = Rs.getString("plu");
                }catch(Exception e ){}
                try{
                    this.NombreLargo = Rs.getString("NombreLargo");
                }catch(Exception e ){}
                try{
                    this.NombreCorto = Rs.getString("NombreCorto");
                }catch(Exception e ){}
                try{
                    this.CodigoBarra = Rs.getString("CodigoBarra");
                }catch(Exception e ){}
                try{
                    this.Categoria = Rs.getString("Categoria");
                }catch(Exception e ){}
                try{
                    this.Ubicacion = Rs.getString("Ubicacion");
                }catch(Exception e ){}
                try{
                    this.Costo = Rs.getDouble("Costo");
                }catch(Exception e ){}
                try{
                    this.PrecioVenta = Rs.getDouble("PrecioVenta");
                }catch(Exception e ){}
                try{
                    this.Estado = Rs.getString("Estado");
                }catch(Exception e ){}
                try{
                    this.PorcentajeIva= Rs.getDouble("PorCentajeIva");
                }catch(Exception e ){}
                try{
                    this.PorcentaOtroImpuesto= Rs.getDouble("PorcentajeOtroImpuesto");
                }catch(Exception e ){}
                try {
                    this.PrecioVentaMinimo = Rs.getDouble("PrecioVentaMin");
                } catch (Exception e) {
                }
                try {
                   this.Cantidad = Rs.getDouble("Cantidad"); 
                } catch (Exception e) {
                }
                Control= true;
            }
            Rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"getArticulo_Plu(Plu) "+e.getMessage());
        }
        return Control;
    }
    public boolean getArticulo_CodigoBarra(String CodigoBarra){
        boolean Control=false;
        try {
            String Str_Sql ="select * from JArticulos where Estado='ACTIVO'  and CodigoBarra='"+CodigoBarra.trim()+"'";
            ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
            while (Rs.next()){
                try{
                    this.Plu = Rs.getString("plu");
                }catch(Exception e ){}
                try{
                    this.NombreLargo = Rs.getString("NombreLargo");
                }catch(Exception e ){}
                try{
                    this.NombreCorto = Rs.getString("NombreCorto");
                }catch(Exception e ){}
                try{
                    this.CodigoBarra = Rs.getString("CodigoBarra");
                }catch(Exception e ){}
                try{
                    this.Categoria = Rs.getString("Categoria");
                }catch(Exception e ){}
                try{
                    this.Ubicacion = Rs.getString("Ubicacion");
                }catch(Exception e ){}
                try{
                    this.Costo = Rs.getDouble("Costo");
                }catch(Exception e ){}
                try{
                    this.PrecioVenta = Rs.getDouble("PrecioVenta");
                }catch(Exception e ){}
                try{
                    this.Estado = Rs.getString("Estado");
                }catch(Exception e ){}
                try{
                    this.PorcentajeIva= Rs.getDouble("PorCentajeIva");
                }catch(Exception e ){}
                try{
                    this.PorcentaOtroImpuesto= Rs.getDouble("PorcentajeOtroImpuesto");
                }catch(Exception e ){}
                try {
                    this.PrecioVentaMinimo = Rs.getDouble("PrecioVentaMin");
                } catch (Exception e) {}
                try {
                   this.Cantidad = Rs.getDouble("Cantidada"); 
                } catch (Exception e) {
                }
                
                Control=true;
            } 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"getArticulo_CodigoBarra(CodigoBarra) "+e.getMessage());
        }
        return Control;
    }
}

