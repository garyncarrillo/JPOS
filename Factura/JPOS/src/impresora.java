
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Date;
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Garyn Carrillo
 */
public class impresora {
//Variables de  acceso   al dispositivo
 private JConeccion JBase_Datos;
 private Connection Cn;   
 private boolean EstadoFactura;
 private JArticulos Art;
 private String NumeroFormato = "###,###,###,###.##";
 private DecimalFormat JFormato ;
 private double TotalFactura;
 private double Descuento_General;
 public impresora(JConeccion JBase_Datos3, Connection Cn2){
     this.JBase_Datos = JBase_Datos3;
     this.Cn = Cn2;
     Art = new JArticulos(this.JBase_Datos, this.Cn);
     EstadoFactura = false;
     JFormato= new DecimalFormat(NumeroFormato);
     this.Descuento_General = 0;
 }
 private void Dibuja_Linea(PrintWriter ps) {
        try {
            ps.println("-----------------------------------");
        } catch (Exception e) {
            System.out.print(e);
        }
    }

//para cortar el papel de mi ticketera
 private void cortar(PrintWriter ps) {

        try {
            char[] ESC_CUT_PAPER = new char[]{0x1B, 'm'};
            ps.write(ESC_CUT_PAPER);

        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void correr(int fin, PrintWriter pw) {
        try {
            int i = 0;
            for (i = 1; i <= fin; i++) {
                pw.println("");
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void setFormato(double formato, PrintWriter pw) {
        try {
            char[] ESC_CUT_PAPER = new char[]{0x1B, '!', (char) formato};
            pw.write(ESC_CUT_PAPER);

        } catch (Exception e) {
            System.out.print(e);
        }
    }

// para el color de mi ticketera
private void setRojo(PrintWriter pw) {
        try {
            char[] ESC_CUT_PAPER = new char[]{0x1B, 'r', 1};
            pw.write(ESC_CUT_PAPER);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

private void setNegro(PrintWriter pw) {
        try {
            char[] ESC_CUT_PAPER = new char[]{0x1B, 'r', 0};
            pw.write(ESC_CUT_PAPER);

        } catch (Exception e) {
            System.out.print(e);
        }
    }
    private boolean BuscarFactura(String Fac){
        try {
            String Str_Sql = "select * from JCabFactura where Estado = 'C' and Numero='"+Fac.trim()+"'";
            ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
            EstadoFactura = false;
            this.Descuento_General = 0;
            if(Rs.next()){
                this.Descuento_General =Rs.getDouble("Descuento");
                EstadoFactura = true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"BuscarFactura() -> "+e.getMessage());
        }
        return EstadoFactura;
    }
    public String Espacios(String Txt ,int Cantidad){
        String Esp="";
        int Cant = Cantidad - Txt.length();
        for (int i = 1; i <=Cant; i++) {
            Esp=Esp+" ";
        }
        return  Esp;
    }
            
    public void Imprimir(String NumeroFactura) throws IOException{
        
                if ( this.BuscarFactura(NumeroFactura) ){
                    FileWriter file = new FileWriter("c://Factura//Factura.ps");
                    BufferedWriter buffer = new BufferedWriter(file);
                    PrintWriter ps = new PrintWriter(buffer);

                    setFormato(1, ps);
                    ps.println("");
                    ps.println("                ALMACEN FOR KIDS");
                    ps.println("               Calle 22 No 17-48");
                    ps.println("             Sabanalarga-Atlantico");
                    ps.println("                NIT :8.638.611-9" );
                    ps.println("              Cels: 300-664-43-27 ");
                    Dibuja_Linea(ps);
                    ps.println("Ticket    : 1 - 2" );
                    ps.println("S/N       : S");
                    ps.println("Fecha     : "+this.getFecha());
                    ps.println("Factura Numero  : "+NumeroFactura.trim());
                    ps.println("Caj   : " );
                    Dibuja_Linea(ps);
                    ps.println("Sr(a)     :" );
                    Dibuja_Linea(ps);
                    ps.println("Cant     " + "Descripcion" + "          " + "PVP");
                    Dibuja_Linea(ps);
                    int lineas = 7;
                    
                    // aqui recorro mis productos y los imprimo
                    try {
                        String Str_Sql = "select * from JDetFactura where Numero='"+NumeroFactura.trim()+"'";
                        ResultSet Rs =  JBase_Datos.SQL_QRY(this.Cn,Str_Sql);
                        EstadoFactura = false;
                        TotalFactura = 0;
                        while(Rs.next()){
                            String Plu = Rs.getString("Plu");
                            int Cantidad = Rs.getInt("Cantidad");
                            this.Art.getArticulo_Plu(Plu);
                            int Precio = Rs.getInt("PrecioUnitario");
                            String Descripcion =  Art.getNombreLargo().trim() +this.Espacios(Art.getNombreLargo(), 20);
                            String Detalle = this.Espacios(String.valueOf(Cantidad),5)+Cantidad+"  " +Descripcion+ "  " + JFormato.format(Precio);
                            TotalFactura = TotalFactura +(Precio*Cantidad);
                            ps.println(Detalle);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,"Exception de Detalle de Factura en Impresora "+e.getMessage());
                    }
                    
                    
                    
                    
                    Dibuja_Linea(ps);
                    if(this.Descuento_General!= 0){
                        ps.println("DESCUENTO AUTORIZADO       :"+this.JFormato.format(this.Descuento_General) );
                    }
                    ps.println("TOTAL         :"+this.JFormato.format(TotalFactura-this.Descuento_General) +" S./ " );
                    ps.println();
                    String ultimo = "              " ;
                    ultimo += "                   " ;
                    ps.println(ultimo);
                    ps.println("                 CONSERVE SU FACTURA PARA ");
                    ps.println("                     CUALQUIER CAMBIO ");
                    ps.println("                  GRACIAS POR SU COMPRA          ");
                    ps.close();
                    this.correr(7, ps);
               }else{
                    
                }
        }
       public String getFecha(){
         Date Fecha = new Date();
         return Fecha.toString();
       }
       public void Bandeja() throws FileNotFoundException, PrintException{
    
            FileInputStream textStream = new FileInputStream("c://Factura//Factura.ps");
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            //aset.add(MediaSizeName.EXECUTIVE);
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc mydoc = new SimpleDoc(textStream, flavor, null);
            

            PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, aset);
            PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();

            if(services.length == 0) {
                JOptionPane.showMessageDialog(null,"Impresora sin servicio");
                if(defaultService == null) {
                 //no printer found
                    JOptionPane.showMessageDialog(null,"Impresora sin Servicio");
                } else {
                    JOptionPane.showMessageDialog(null,"Imprimiendo");
                    //print using default
                    DocPrintJob job = defaultService.createPrintJob();
                    job.print(mydoc, aset);
                }
            } else {
                
                //built in UI for printing you may not use this
                aset.add(MediaSizeName.EXECUTIVE);
                PrintService service = ServiceUI.printDialog(null, 50, 50, services, defaultService, flavor, aset);
                
                if (service != null)
                {
                    DocPrintJob job = service.createPrintJob();
                    
                    job.print(mydoc, aset);
                    
                 }

            }   
       }
}
