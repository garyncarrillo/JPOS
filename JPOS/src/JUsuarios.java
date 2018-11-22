
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
public class JUsuarios {
   
   String Codigo;
   String Nombre;
   String Estado;
   Vector ListAutorizacion;
   
   private JConeccion JBase_Datos;
   private Connection Cn;  
   public JUsuarios(JConeccion JBase_Datos3, Connection Cn2, String CodigoUsuario){
        this.JBase_Datos = JBase_Datos3;
        this.Cn = Cn2;
        this.Codigo = CodigoUsuario;
        ListAutorizacion = new Vector();
        //this.getInformacion(this.Codigo);
   }
   public String getNombre(){
       return this.Nombre;
   }
   public boolean getExiste(String CodigoUsuario){
       boolean Existe = false;
       try {
           String Str_Sql = "select * from JUsuarios where Codigo ='"+CodigoUsuario+"' and estado='activo'";
           ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
           if(Rs.next()){
               Existe = true;
           }
           Rs.close();
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Class:JUser:getExiste() "+e.getMessage());
       }
       return Existe;
   }
   public boolean isAutorizado(String Objeto){
       boolean Autorizado = false;
       try {
           int Cantidad = this.ListAutorizacion.size();
           for (int i = 0; i < Cantidad; i++) {
               if(this.ListAutorizacion.elementAt(i).toString().equals(Objeto)){
                   Autorizado = true;
               }
           }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Class:JUser:isAutorizado() "+e.getMessage());
       }
       return Autorizado;
   }
   
   public void getAutorizacion(){
       try {
           String Str_Sql = "select * from JAutorizaciones where CodigoUsuario='"+this.Codigo+"'"
                            + " and Estado='activo'";
           ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
           while(Rs.next()){
               ListAutorizacion.add(Rs.getString("CodigoObjeto"));
           }
           Rs.close();
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Form:getAutorizacion() "+e.getMessage());
       }
   }
   public void getInformacion(String CodigoUsuario){
       try {
           String Str_Sql = "select * from JUsuarios where Codigo ='"+CodigoUsuario+"' and estado='activo'";
           ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
           if(Rs.next()){
               this.Nombre = Rs.getString("Nombre");
               this.Estado = Rs.getString("Estado");
               this.getAutorizacion();
           }
           Rs.close();
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Form:getInformacion() "+e.getMessage());
       }
   }
   public boolean getValidate(String Codigo, String Clave){
        boolean Control = false;
        try {
            String Str_Sql =" select * from JUsuarios where codigo='"+Codigo+"' "
                            + " and clave= '"+Clave+"' and estado= 'activo' ";
            ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
	    if(Rs.next()){
                Control = true;
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"JAccesoUsuarios() "+e.getMessage());	
        }
        return  Control;
    }
   
 
}
