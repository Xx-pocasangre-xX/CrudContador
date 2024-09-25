package Controlador;

import Modelo.ClassContador;
import Vista.jfrContador;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ctrlContador implements MouseListener, KeyListener{
    
    private ClassContador modelo;
    private jfrContador vista;
   
    public ctrlContador(ClassContador modelo, jfrContador vista){
        this.modelo = modelo;
        this.vista = vista;

        vista.btnAgregar.addMouseListener(this);   
        modelo.Mostrar(vista.jtbContador);
        vista.btnEliminar.addMouseListener(this);
        vista.jtbContador.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vista.btnAgregar){
            modelo.setNombre_Contador(vista.txtNombre.getText());
            modelo.setEdad_Contador(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPeso_Contador(Double.parseDouble(vista.txtPeso.getText()));
            modelo.setCorreo_Contador(vista.txtCorreo.getText());
            System.out.println("Clicked");
            modelo.Guardar();
            modelo.Mostrar(vista.jtbContador);
        }
        
        if(e.getSource() == vista.btnEliminar){
            modelo.Eliminar(vista.jtbContador);
            modelo.Mostrar(vista.jtbContador);
             System.err.println("Clicked");
        }
        
        if(e.getSource() == vista.jtbContador){
            modelo.cargarDatosTabla(vista);
             System.err.println("Clicked");
        }
        
        if(e.getSource() == vista.btnActualizar){
            modelo.setNombre_Contador(vista.txtNombre.getText());
            modelo.setEdad_Contador(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPeso_Contador(Double.parseDouble(vista.txtPeso.getText()));
            modelo.setCorreo_Contador(vista.txtCorreo.getText());
            System.err.println("Clicked");
            
            
            modelo.Actualizar(vista.jtbContador);
            modelo.Mostrar(vista.jtbContador);
        }
        
        if(e.getSource() == vista.btnLimpiar){
            modelo.limpiar(vista);
        }
        
        
    }
   
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
      
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
