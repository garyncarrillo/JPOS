
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.print.PrinterException;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.DecimalFormat;
import javax.swing.JTextPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Garyn Carrillo
 */
public class JReporte_Factura {
    
    private Document documento;
    private String Numero_Factura, Fecha;
    private String NumeroFormato = "###,###,###,###.##";
    private DecimalFormat JFormato ;
    private PdfPTable tabla ;
    private JCentroCostos Centro;
    private JCajero_Pos Caja;
    private JFacturacion Factura;
 
    
    public JReporte_Factura(String NumeroFactura, String Fecha ,JCentroCostos Centro, JCajero_Pos Caja, JFacturacion Factura ){
        this.Numero_Factura = NumeroFactura;
        this.Fecha =Fecha;
        JFormato= new DecimalFormat(NumeroFormato);
        this.Centro = Centro;
        this.Caja = Caja;
        this.Factura =Factura;
        documento = new Document(PageSize.B2.rotate(),100,690,50,20);//(Izqu,dere,arriba,abajo)
        tabla = new PdfPTable(4);
        
        this.Save();
        this.Generar_Encabezado();
        //this.Generar_Detalle();
        this.Generar_Pie_Pagina();
        documento.close();
        
    }
    public void Save(){
        try {
            String Ruta1= "C:\\Factura\\Factura.pdf";
            FileOutputStream ficheroPdf = new FileOutputStream(Ruta1);
            PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
            documento.open();
            documento.addTitle("Facturacion Sistema Pos");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"JReporte_Factura:Save() "+e.getMessage());
        }
    }
    public void Salto_Linea(){
        try{
            documento.add(new Paragraph("         ",FontFactory.getFont("tahoma",   
				12,Font.BOLD,BaseColor.BLACK))); 
        }catch(Exception e ){
            JOptionPane.showMessageDialog(null, "JReport:Salto_Linea "+e.getMessage());            
        }
    }
    public void Generar_Encabezado(){
        try {
            Paragraph Parrafo1 = new Paragraph();
            Paragraph Parrafo2 = new Paragraph();
            Paragraph Parrafo3 = new Paragraph();
            Paragraph Parrafo4 = new Paragraph();
            Paragraph Parrafo5 = new Paragraph();
            Paragraph Parrafo6 = new Paragraph();
            
            Paragraph Parrafo7 = new Paragraph();
            Paragraph Parrafo8 = new Paragraph();
            
            Parrafo1.setAlignment(Element.ALIGN_LEFT);
            Parrafo1.setFont(FontFactory.getFont("CourierThai",15,Font.NORMAL,BaseColor.BLACK));    
            
            Parrafo2.setAlignment(Element.ALIGN_LEFT);
            Parrafo2.setFont(FontFactory.getFont("CourierThai",15,Font.BOLD,BaseColor.BLACK));    
            
            Parrafo3.setAlignment(Element.ALIGN_LEFT);
            Parrafo3.setFont(FontFactory.getFont("CourierThai",15,Font.BOLD,BaseColor.BLACK));    
            
            Parrafo4.setAlignment(Element.ALIGN_LEFT);
            Parrafo4.setFont(FontFactory.getFont("CourierThai",15,Font.BOLD,BaseColor.BLACK));    
            
            Parrafo5.setAlignment(Element.ALIGN_LEFT);
            Parrafo5.setFont(FontFactory.getFont("CourierThai",15,Font.BOLD,BaseColor.BLACK));    
            
            Parrafo5.setAlignment(Element.ALIGN_LEFT);
            Parrafo5.setFont(FontFactory.getFont("CourierThai",15,Font.BOLD,BaseColor.BLACK));    
            
            Parrafo6.setAlignment(Element.ALIGN_LEFT);
            Parrafo6.setFont(FontFactory.getFont("CourierThai",15,Font.BOLD,BaseColor.BLACK));    
            
            Parrafo7.setAlignment(Element.ALIGN_LEFT);
            Parrafo7.setFont(FontFactory.getFont("CourierThai",15,Font.NORMAL,BaseColor.BLACK)); 
            
            Parrafo8.setAlignment(Element.ALIGN_LEFT);
            Parrafo8.setFont(FontFactory.getFont("CourierThai",15,Font.NORMAL,BaseColor.BLACK)); 
            
            Parrafo1.add("                  VENTAS  SOCIEDAD  S.A"); 
            documento.add(Parrafo1);
            
            Parrafo2.add("   SV01 CALLE 22 NRO 43-102 BARRIO LA PLAZA" ); 
            documento.add(Parrafo2);
            
            Parrafo3.add("  Nit: 890-107.777-1" );
            documento.add(Parrafo3);
            
            this.Salto_Linea();
            this.Salto_Linea();
            
            Parrafo4.add("  CAJERO: "+this.Caja.getNombre().trim() );
            Parrafo5.add("  TELEFONO: 304-367-8183" );
            Parrafo6.add(" FECHA DE EXPEDICION: " );
            documento.add(Parrafo4);
            documento.add(Parrafo5);
            documento.add(Parrafo6);
            this.Salto_Linea();
            this.Salto_Linea();
            
            Parrafo7.add("              Cod  Descripcion      Cnt Valor           " );
            Parrafo8.add("-----------------  ---------------    --- ----------------" );
            documento.add(Parrafo7);
            documento.add(Parrafo8); 
            int Cantidad =  this.Factura.getTablaArticulo().getRowCount();
            int Item = 0;
            
            while(Item < Cantidad){
               String Plu = String.valueOf(this.Factura.getTablaArticulo().getValueAt(Item,0));
               String Descripcion = String.valueOf(this.Factura.getTablaArticulo().getValueAt(Item,1));     
               if(Descripcion.trim().length()>=15){
                   Descripcion = Descripcion.trim().substring(0, 15);
               }
               int Cnt = 0;
               String Tmp= String.valueOf(this.Factura.getTablaArticulo().getValueAt(Item,2));
               Cnt = Integer.parseInt(Tmp.trim());     
               double Pru = Double.parseDouble(this.Factura.getTablaArticulo().getValueAt(Item,3).toString()); 
              
               Paragraph Parrafo9 = new Paragraph();
               Parrafo9.setAlignment(Element.ALIGN_LEFT);
               //Parrafo9.setFont(FontFactory.getFont("CourierThai",13,Font.NORMAL,BaseColor.BLACK)); 
               
               String Dos = "    ";
               Plu=getCaracteres(Plu.trim().length(), 14)+Plu.trim();
               Descripcion = getCaracteres(Descripcion.trim().length(), 15)+Descripcion.trim();
               System.out.println(""+Descripcion.length());
               String Ct = String.valueOf(Cnt);
               Ct = getCaracteres(Ct.trim().length(), 3)+Ct.trim();
               String Line = Plu+Dos+Descripcion.toLowerCase()+ Dos+Ct +Dos+this.JFormato.format(Pru);
               Parrafo9.add( ""+Line);
               
               
               
               
               documento.add(Parrafo9);
               Item++;
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"JReporte_Factura:Generar_Encabezado() "+e.getMessage());
        }
        
    }
    public void Imprimir(){
         JTextPane jtp = new JTextPane();
        jtp.setBackground(Color.white);
        jtp.setText("text to print");
        boolean show = true;
        
        try {
            jtp.print(null, null, show, null, null, show);
        } catch (java.awt.print.PrinterException ex) {
                ex.printStackTrace();
        }
    }
    
    
    public String getCaracteres(int Cantidad, int Limite){
        String Txt = "";
        try {
            int Diferencia = (Limite-Cantidad);
            for (int i = 1; i <= Diferencia; i++) {
                Txt=Txt+"X";
                
            }
        } catch (Exception e) {
            
        }
        return Txt;
    }
    public void Generar_Detalle(){
        try {
            
            float  AnchoCelda []  = new float[4]; 
            AnchoCelda [0] = (float) 3.0;
            AnchoCelda [1] = (float) 3.0;
            AnchoCelda [2] = (float) 3.0;
            AnchoCelda [3] = (float) 3.0;
            
            tabla.setWidths(AnchoCelda);  
            
            Paragraph Parrafo100 = new Paragraph();
            Parrafo100.setAlignment(Element.ALIGN_LEFT);
            Parrafo100.setFont(FontFactory.getFont("tahoma",14,Font.BOLD,BaseColor.BLACK));
            
            Paragraph Parrafo101 = new Paragraph();
            Parrafo101.setAlignment(Element.ALIGN_LEFT);
            Parrafo101.setFont(FontFactory.getFont("tahoma",14,Font.BOLD,BaseColor.BLACK));
            
            Paragraph Parrafo102 = new Paragraph();
            Parrafo102.setAlignment(Element.ALIGN_LEFT);
            Parrafo102.setFont(FontFactory.getFont("tahoma",14,Font.BOLD,BaseColor.BLACK));
            
            Paragraph Parrafo103 = new Paragraph();
            Parrafo103.setAlignment(Element.ALIGN_LEFT);
            Parrafo103.setFont(FontFactory.getFont("tahoma",14,Font.BOLD,BaseColor.BLACK));
            
            
            Parrafo100.add("Codigo" );
            PdfPCell Celda100 = new PdfPCell ( Parrafo100);
            tabla.addCell(Celda100); 
            
            Parrafo101.add("Descripcion" );
            PdfPCell Celda101 = new PdfPCell ( Parrafo101);
            tabla.addCell(Celda101); 
            
            Parrafo102.add("Cantidad" );
            PdfPCell Celda102 = new PdfPCell ( Parrafo102);
            tabla.addCell(Celda102);
            
            Parrafo103.add("Precio" );
            PdfPCell Celda103 = new PdfPCell ( Parrafo103);
            tabla.addCell(Celda103); 
            
            documento.add(tabla);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"JReporte_Factura:Generar_Detalle() "+e.getMessage());
        }
    }
    public void Generar_Pie_Pagina(){
        
    }
}
