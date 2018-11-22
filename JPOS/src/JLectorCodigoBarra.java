
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Garyn Carrillo
 */
public class JLectorCodigoBarra implements ActionListener {
    private JFacturacion Fac;
    public JLectorCodigoBarra(JFacturacion Padre){
        Fac = Padre;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("prueba ");
        if(e.hashCode()==12){
            System.out.println("prueba ");
        }
    }
    
}
