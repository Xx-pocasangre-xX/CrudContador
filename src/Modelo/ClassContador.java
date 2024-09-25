
package Modelo;

import Vista.jfrContador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ClassContador {
    //1- Parametros
    private String UUID_Contador;
    private String Nombre_Contador;
    private int Edad_Contador;
    private double Peso_Contador;
    private String Correo_Contador;

   
    public String getUUID_Contador() {
        return UUID_Contador;
    }

    public void setUUID_Contador(String UUID_Contador) {
        this.UUID_Contador = UUID_Contador;
    }

    public String getNombre_Contador() {
        return Nombre_Contador;
    }

    public void setNombre_Contador(String Nombre_Contador) {
        this.Nombre_Contador = Nombre_Contador;
    }

    public int getEdad_Contador() {
        return Edad_Contador;
    }

    public void setEdad_Contador(int Edad_Contador) {
        this.Edad_Contador = Edad_Contador;
    }

    public Double getPeso_Contador() {
        return Peso_Contador;
    }

    public void setPeso_Contador(double Peso_Contador) {
        this.Peso_Contador = Peso_Contador;
    }

    public String getCorreo_Contador() {
        return Correo_Contador;
    }

    public void setCorreo_Contador(String Correo_Contador) {
        this.Correo_Contador = Correo_Contador;
    }
    
    public void Guardar() {
        
        Connection conexion = ClaseConexion.getConexion();
        try {
            PreparedStatement addContador = conexion.prepareStatement("INSERT INTO tbContador(UUID_Contador, Nombre_Contador, Edad_Contador, Peso_Contador, Correo_Contador) VALUES (?, ?, ?, ?, ?)");
            addContador.setString(1, UUID.randomUUID().toString());
            addContador.setString(2, getNombre_Contador());
            addContador.setInt(3, getEdad_Contador());
            addContador.setDouble(4, getPeso_Contador());
            addContador.setString(5, getCorreo_Contador());
 
            addContador.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
    public void Mostrar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_Contador", "Nombre_Contador", "Edad_Contador", "Peso_Contador", "Correo_Contador"});
        try {
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM tbContador");
            while (rs.next()) {
                modeloDeDatos.addRow(new Object[]{rs.getString("UUID_Contador"), 
                    rs.getString("Nombre_Contador"), 
                    rs.getInt("Edad_Contador"), 
                    rs.getDouble("Peso_Contador"),
                    rs.getString("Correo_Contador")});
            }
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
                e.printStackTrace();
        }
    }
    
    public void Eliminar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        int filaSeleccionada = tabla.getSelectedRow();
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("delete from tbContador where UUID_Contador = ?");
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
     public void cargarDatosTabla(jfrContador vista) {
        int filaSeleccionada = vista.jtbContador.getSelectedRow();

        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbContador.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtbContador.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = vista.jtbContador.getValueAt(filaSeleccionada, 2).toString();
            String PesoDeTB = vista.jtbContador.getValueAt(filaSeleccionada, 3).toString();
            String CorreoDeTB = vista.jtbContador.getValueAt(filaSeleccionada, 4).toString();

            vista.txtNombre.setText(NombreDeTB);
            vista.txtEdad.setText(EdadDeTb);
            vista.txtPeso.setText(PesoDeTB);
            vista.txtCorreo.setText(CorreoDeTB);
        }
    }
     
     public void Actualizar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();

        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                PreparedStatement updateContador = conexion.prepareStatement("update tbContador set Nombre_Contador = ?, Edad_Contador = ?, Peso_Contador = ?, Correo_Contador = ? where UUID_Contador = ?");

                updateContador.setString(1, getNombre_Contador());
                updateContador.setInt(2, getEdad_Contador());
                updateContador.setDouble(3, getPeso_Contador());
                updateContador.setString(4, getCorreo_Contador());
                updateContador.setString(5, miUUId);
                updateContador.executeUpdate();
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
     
    public void limpiar(jfrContador vista) {
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtPeso.setText("");
        vista.txtCorreo.setText("");
    }
}
