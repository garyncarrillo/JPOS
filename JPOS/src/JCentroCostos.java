
import java.sql.Connection;
import java.sql.ResultSet;
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
public class JCentroCostos {
    
    private JConeccion JBase_Datos;
    private Connection Cn; 
    private String Codigo, Descripcion, Estado;
    private Vector Detalle;
    private Vector Detalle_Fila;
    private Vector ListDescripcion;
    public JCentroCostos(JConeccion JBase_Datos3, Connection Cn2){
        this.JBase_Datos = JBase_Datos3;
        this.Detalle_Fila = new Vector();
        this.ListDescripcion = new Vector();
        this.Cn = Cn2;
    }
    public void setCentroCostos(String Codigo){
        this.Codigo = Codigo;
    }
    public void setNombre(String Nombre){
        this.Descripcion = Nombre;
    }
    public void setEstado(String Estado){
        this.Estado = Estado;
    }
    public String getCodigoCentroCosto(){
        return this.Codigo;
    }
    public String getNombre(){
        return this.Descripcion;
    }
    public String getEstado(){
        return this.Estado;
    }
    public boolean Existe(String Costo){
        boolean Sw = false;
        try {
                String Str_Sql = "Select * from JCentroCosto where CodigoCentro='"+Costo+"'";
                ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
                if(Rs.next()){
                    Sw=true;
                }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null," Existe(Codigo) "+e.getMessage());
        }
        return Sw;
    }
    public boolean setCentroCosto(String Costo , String TDescripcion, String TEstado){
        this.Codigo=Costo;
        this.Descripcion = Descripcion;
        boolean Rs=false;
        try {
            String Str_Sql ="";
            if(!Existe(Costo)){
                Str_Sql  = "insert into JCentroCosto (CodigoCentro,descripcion,estado) values ('"+Costo.trim()+"','"+TDescripcion.trim()+"','"+TEstado.trim()+"')";
            }else{
                Str_Sql  = "update JCentroCosto "
                          +" set descripcion='"+TDescripcion.trim()+"'"
                          +" , Estado ='"+TEstado.trim()+"'"
                          +" where CodigoCentro='"+Costo.trim()+"'";
                
            }
            Rs =  JBase_Datos.Exc_Sql(this.Cn,Str_Sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null," setCentroCosto() "+e.getMessage());
        }
        return Rs;
    }
    public boolean setDelete(String Codigo){
        boolean Rs=false;
        try {
            if(this.Existe(Codigo)){
               String Str_Sql = "delete from JCentroCosto where CodigoCentro='"+this.Codigo.trim()+"'";;
               Rs =  JBase_Datos.Exc_Sql(this.Cn,Str_Sql);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null," setDelete() "+e.getMessage());
        }
        return Rs;
    }
    public  Vector getCentroCosto(){
        try {
            String Str_Sql = "select * from JCentroCosto ";
            ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
            this.Detalle_Fila.clear();
            while(Rs.next()){
                this.Detalle = new Vector();
                this.Detalle.add(Rs.getString("CodigoCentro"));
                this.Detalle.add(Rs.getString("Descripcion"));
                this.Detalle.add(Rs.getString("Estado"));
                this.Detalle_Fila.add(this.Detalle);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null," getCentroCosto() "+e.getMessage());
        }
        return this.Detalle_Fila;
    }
    public Vector getListDescripcion(){
        try {
            
            String Str_Sql ="select Descripcion from JCentroCosto where estado='activo'";
            ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
            this.ListDescripcion.clear();
            while(Rs.next()){
                this.ListDescripcion.add(Rs.getString("Descripcion"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null," getCentroCosto() "+e.getMessage());
        }
        return this.ListDescripcion;
    }
    public String getBuscarDescripcion(String TCodigo){
        String TNombre = null;
        try {
            
            String Str_Sql ="select Descripcion from JCentroCosto where CodigoCentro='"+TCodigo.trim()+"'";
            ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
            if(Rs.next()){
               TNombre = Rs.getString("Descripcion");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"  getBuscarDescripcion(TCodigo) "+e.getMessage());
        }
        return TNombre;
    }
    
}
