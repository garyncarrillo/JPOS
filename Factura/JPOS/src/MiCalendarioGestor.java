/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author GACA1186
 */


import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.io.*;

/**
 *
 * @author Invitado
 */

public class MiCalendarioGestor extends JPanel implements MouseListener{
    private JAnulacion_Facturacion Frm;
    private int Tipo;
    MiLabelCalendario select = null;
    JTextField jtf= new JTextField();
    Calendar cal;
    JPanel pan = new JPanel();

    static String[] meses={"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    static String[] dias={"Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Dom"};

    JButton bAlante;
    JButton bAtras;
    private int JAño = 0 ;
    private int JMes = 0 ;
    private int JDia = 0 ;
    
    
    public MiCalendarioGestor() {
        this(Calendar.getInstance());
    }
    public void SetFormulario(JAnulacion_Facturacion Frm, int Tipo){
        this.Tipo = Tipo;
        this.Frm = Frm;
    }
    public int getAño(){
        return this.JAño;
    }
    public int getMes(){
        return this.JMes;
    }
    public int getDia(){
        return this.JDia;
    }
    public void setDate(){
        JDia = cal.get(Calendar.DATE);
        JMes = cal.get(Calendar.MONTH);
        JAño = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DATE);
        String xday = String.valueOf(day);
        if(day<9){
            xday = "0"+xday;
        }
        String Fecha = cal.get(Calendar.YEAR)+ meses[cal.get(Calendar.MONTH)]+xday ;
        jtf.setText( cal.get(Calendar.DATE) + " de " + meses[cal.get(Calendar.MONTH)] + " de " + cal.get(Calendar.YEAR) + "" );
        
        if(Tipo==1){
            this.Frm.setFechaBusqueda_1(Fecha.trim());
        }
        if(Tipo==2){
            this.Frm.setFechaBusqueda_2(Fecha.trim());
        }
    }

    public MiCalendarioGestor( Calendar dt ) {

        setLayout( new BorderLayout());
        bAlante = new JButton(">");
        bAtras = new JButton("<");

        // action listener para los botones
        ActionListener miAL =  new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                Calendar auxCal=Calendar.getInstance();
                auxCal.set(Calendar.DATE, 1);
                auxCal.set(Calendar.YEAR, cal.get(Calendar.YEAR));

                if (e.getActionCommand().equals(">")){


                    auxCal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1);

                    if ( cal.get(Calendar.DATE) > auxCal.getActualMaximum( Calendar.DAY_OF_MONTH)){
                        cal.set(Calendar.DATE, 1);
                        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1);
                    }else

                    cal.set( Calendar.MONTH, cal.get( Calendar.MONTH ) +1 );

                }else{

                    auxCal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1);

                    if ( cal.get(Calendar.DATE) > auxCal.getActualMaximum( Calendar.DAY_OF_MONTH) ){

                        cal.set( Calendar.DATE, 1 );
                        cal.set( Calendar.MONTH, cal.get( Calendar.MONTH) -1 );
                    }else

                    cal.set( Calendar.MONTH, cal.get( Calendar.MONTH) -1 );

                }
                setDate();
                cargarDatos();
            }
        };

        bAlante.addActionListener(miAL);
        bAtras.addActionListener(miAL);

        JToolBar tb = new JToolBar();
        tb.setFloatable(false);
        tb.add( bAtras );
        jtf.setHorizontalAlignment( JTextField.CENTER);
        jtf.setEditable(false);
        tb.add( jtf );
        tb.add( bAlante );
        //add( jp, BorderLayout.NORTH );
        add( tb, BorderLayout.NORTH );
        cal= dt;
        setDate();
        
        add( pan, BorderLayout.CENTER );
        cargarDatos();
    }

    public void cargarDatos(){

        select=null;
        remove(1);
        pan= new JPanel();
        pan.setBackground( java.awt.Color.lightGray);
        pan.setLayout( new GridLayout(7, 7));

        Calendar calAux = (Calendar) cal.clone();
        calAux.set( Calendar.DATE, 1);

        int primerDia = calAux.get(Calendar.DAY_OF_WEEK);

        primerDia = primerDia>1?primerDia-2:6;

        for ( int i =0; i<7; i++ ){

            JLabel jlaux = new JLabel (dias[i]);
            jlaux.setHorizontalAlignment( JLabel.CENTER);
            pan.add( jlaux );
        }

        int contador =0;
        for ( int i =0 ; i<  primerDia; i++ ){

            pan.add( new JLabel());
            contador++;
        }

        int diasMes= cal.getActualMaximum( Calendar.DAY_OF_MONTH);
        MiLabelCalendario aux;
        for ( int i=1 ; i<=diasMes; i++ ){

            String numCadena = i+"";
            aux = new MiLabelCalendario( numCadena );
            aux.addMouseListener( this );
            pan.add( aux );
            contador++;

            if ( i==cal.get(Calendar.DATE) ){
                aux.seleccionar();
                select=aux;
            }
        }

        while ( contador < 42 ){

            pan.add( new JLabel() );
            contador++;
        }
        add( pan, BorderLayout.CENTER );

        pan.updateUI();

    }

    public void mouseClicked(MouseEvent e) {

        if (select!=null){
            select.seleccionar(null);
        }

        MiLabelCalendario lb = (MiLabelCalendario) e.getSource();
        lb.seleccionar();
        select=lb;
        
        cal.set( Calendar.DAY_OF_MONTH, Integer.parseInt(select.getText()));
        setDate();
    }
    public void mousePressed(MouseEvent e) {
        this.getDia();
        //System.out.println("Click"+this.getDia());
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {
        MiLabelCalendario lb = (MiLabelCalendario) e.getSource();
        lb.setBorder( new LineBorder( java.awt.Color.BLACK) );
    }
    public void mouseExited(MouseEvent e) {
        MiLabelCalendario lb = (MiLabelCalendario) e.getSource();
        lb.setBorder( lb.lb );
    }
}
