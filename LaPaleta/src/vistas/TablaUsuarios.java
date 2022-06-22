package vistas;

import conexionDB.Conexion;
import javax.swing.table.AbstractTableModel;
import java.sql.*;


/**
 *
 * @author kevindominguez
 */
public class TablaUsuarios extends AbstractTableModel {
    private int numberOfRows = 10;
    private String[] columnNames = {"Clave", "Nombre", "Cargo", "Usuario", "Correo"};
    
    private Object[][] rows = new Object [numberOfRows][columnNames.length];
    
    public TablaUsuarios() {
        
        try {
            String attributes[] = {"usuario_id", "uNombre", "uCargo" 
                , "uUsuario", "uCorreoElectrónico"};
            String statement = "SELECT {columns} FROM usuario LIMIT "
                    + "{numberOfRows}";
            statement = statement.replace("{columns}", 
                    String.join(", ", attributes));
            statement = statement.replace("{numberOfRows}",
                    String.valueOf(numberOfRows));
            
            Conexion conexion = new Conexion();
            ResultSet users = conexion.executeQuery(statement);
            
            byte user = 0;
            while(users.next()){
                for (int attribute = 0; attribute < attributes.length; attribute++) {
                    rows[user][attribute] = users.getString(attributes[attribute]);
                }
                user++;
            }
            
        } catch (SQLException e){
            for (int i = 0; i < rows.length; i++) {
                for (int j = 0; j < rows[0].length; j++) {
                    rows[i][j] = "Error";
                }
            }
            System.out.println(e);
        }
    }

    @Override
    public int getRowCount() {
        return rows.length;
    }
    
    @Override
    public int getColumnCount() {
       return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rows[rowIndex][columnIndex];
    }
    
    @Override
    public String getColumnName(int col) {
            return columnNames[col];
          
    }
    
    
    
   
    
    
}

    

