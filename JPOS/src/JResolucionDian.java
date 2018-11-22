
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Garyn Carrillo
 */
public class JResolucionDian {
    
     private int CodigoCentroCosto;
     private String Secuencia;
     private int RandoIncial;
     private int RangoFinal;
     private int RangoActual;
     public JResolucionDian(int PCodigoCentroCosto){
         this.CodigoCentroCosto  = PCodigoCentroCosto;
     }
     public void getResolucionDian(){
         try {
             String Str_Sql = "Select * From  Where ";
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"Class:JResolucionDian:getResolucionDian()"+e.getMessage()); 
         }
     }
     public int setResolucion(int CodigoCentroCosto){
         int TemporalRango = -1;
         if(  ((this.RangoActual+1) >= this.RandoIncial) && ((this.RangoActual+1) <= this.RangoFinal) ) {
             this.RangoActual++;
             this.UpdateResolucionActual();
             TemporalRango = this.RangoActual;
         }
         return TemporalRango;
     }
     public void UpdateResolucionActual(){
         try {
             String Str_Sql = "Update "
                            + "Set "
                            + "Where ";
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"Class:JResolucionDian:UpdateResolucionActual()"+e.getMessage()); 
         }
     }
}

