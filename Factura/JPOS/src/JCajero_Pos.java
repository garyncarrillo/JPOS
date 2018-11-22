
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

public class JCajero_Pos {
    private String  CodigoCaja, CodigoCentro;
    private String  Descripcion, Estado;
    private JConeccion JBase_Datos;
    private Connection Cn; 
    private Vector Detalle;
    private Vector Detalle_Fila;
    
    public JCajero_Pos(JConeccion JBase_Datos3, Connection Cn2){
        this.JBase_Datos = JBase_Datos3;
        Detalle_Fila = new Vector();
        this.Cn = Cn2;
    }
    public void setCodigoCajeros(String Codigo){
        this.CodigoCaja = Codigo;
    }
    public String getCodigoCajeros(){
        return this.CodigoCaja;
    }
    public void setNombre(String Nom){
        this.Descripcion =  Nom;
    }
    public String getNombre(){
        return this.Descripcion;
    }
    public void setCentroCosto(String Codigo){
        this.CodigoCentro = Codigo;
    }
    public String getCentroCosto(){
        return this.CodigoCentro;
    }
    public void setEstado(String Est){
        this.Estado = Est;
    }
    public String getEstado(){
        return this.Estado;
    }
    public boolean Existe(String TCodigo){
       boolean Sw=false;
       try {
            String Str_Sql = "select CodigoCaja from jcajas where CodigoCaja='"+TCodigo.trim()+"'";
            ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
            if(Rs.next()){
                Sw=true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"getCajeros() "+e.getMessage());
        }
       return Sw;
    }
    public Vector getCajeros(){
        try {
            String Str_Sql = "select t1.*,t2.Descripcion from jcajas as t1, JCentroCosto as t2 where t1.CodigoCentro = t2.CodigoCentro";
            ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
            this.Detalle_Fila.clear();
            while(Rs.next()){
                this.Detalle = new Vector();
                this.Detalle.add(Rs.getString("CodigoCaja"));
                this.Detalle.add(Rs.getString("NombreCaja"));
                this.Detalle.add(Rs.getString("CodigoCentro"));
                this.Detalle.add(Rs.getString("Descripcion"));
                this.Detalle.add(Rs.getString("Estado"));
                this.Detalle_Fila.add(this.Detalle);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"getCajeros() "+e.getMessage());
        }
        return this.Detalle_Fila;
    }
    public boolean setCajas(String TCodigo, String TNombre, String TCentroCosto, String TEstado){
        boolean Rs =false;
        try {
            String Str_Sql = "";
            if(!this.Existe(TCodigo)){
                Str_Sql ="insert into jcajas(CodigoCaja,CodigoCentro,NombreCaja,Estado) values('"+TCodigo.trim()+"','"+TCentroCosto.trim()+"','"+TNombre.trim()+"','"+TEstado+"')";
            }else{
                Str_Sql =" update jcajas"
                        + " set CodigoCentro='"+TCentroCosto.trim()+"'"
                        + " ,NombreCaja='"+TNombre.trim()+"'"
                        + " ,Estado='"+TEstado.trim()+"'"
                        + " where CodigoCaja='"+TCodigo.trim()+"'";
            }
            Rs =  JBase_Datos.Exc_Sql(this.Cn,Str_Sql);
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null,"setCajas(.....) "+e.getMessage());  
        }
        return Rs;
    }

}
