
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author GACA1186
 */
public class JVendedor {
    private JConeccion JBase_Datos;
    private Connection Cn; 
    public JVendedor(JConeccion JBase_Datos3, Connection Cn2){
        this.JBase_Datos = JBase_Datos3;
        this.Cn = Cn2;
    }
    
    
    public boolean Crear_Vendedor(String Documento , String Nombres, String Apellidos, String Telefono, String Direccion){
        boolean Rs = false;
        try {
            String Str_Sql = "";
            if(!this.Existe(Documento)){
                //Crear
                Str_Sql = "INSERT INTO JVendedoras "
                        + "(Documento, Nombres, Apellidos, Telefono, Direccion) "
                        + " VALUES("+Documento.trim()+",'"+Nombres.trim()+"','"+Apellidos.trim()+"','"+Telefono.trim()+"','"+Direccion.trim()+"')";
            }else{
                //Actualizar
                     Str_Sql = "update JVendedoras set "
                        + " Nombres='"+Nombres.trim()+"'"
                        + " ,Apellidos='"+Apellidos.trim()+"'"
                        + " ,Telefono='"+Telefono.trim()+"'"
                        + " ,Direccion='"+Direccion.trim()+"'"
                        + " WHERE Documento="+Documento.trim()+"";
            }
            Rs =  JBase_Datos.Exc_Sql(this.Cn,Str_Sql);
         } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Crear_Vendedoras(.....) "+e.getMessage());
        }
        return Rs;
    }
    
    public boolean Eliminar_Vendedor(String Documento ){
        boolean Rs = false;
        try {
            String Str_Sql = "";
            if(this.Existe(Documento)){
                //Crear
                Str_Sql = "DELETE FROM  JVendedoras WHERE Documento="+Documento.trim()+"";
                System.out.println(" "+Str_Sql);
                Rs =  JBase_Datos.Exc_Sql(this.Cn,Str_Sql);
            }else{
                JOptionPane.showMessageDialog(null," eL Vendedor no existe "+Documento);
            }
         }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Eliminar_Vendedoras(.....) "+e.getMessage());
        }
        return Rs;
    }
    
    public boolean Existe(String Documento){
        boolean Control = false;
        try {
            String Str_Sql ="select * from JVendedoras where Documento="+Documento.trim();
            System.out.println(""+Str_Sql);
            ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
            if(Rs.next()){
                Control = true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Existe(Dcoumentos) "+e.getMessage());
        }
        return Control;
    }
    
    
    public Vector getVendedores(){
        Vector Fila = new Vector();
        try {
            String Str_Sql="Select * from JVendedoras ";
            ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
            while(Rs.next()){
                 Vector DVendedores = new Vector();
                 DVendedores.add(Rs.getInt("Documento"));
                 DVendedores.add(Rs.getString("Nombres"));
                 DVendedores.add(Rs.getString("Telefono"));
                 DVendedores.add(Rs.getString("Direccion"));
                 Fila.add(DVendedores);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Existe(Plu) "+e.getMessage());
        }
        
        return Fila;
                
    }
}
