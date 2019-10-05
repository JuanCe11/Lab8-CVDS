/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cvds.sampleprj.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class JDBCExample {
    
    public static void main(String args[]){
        try {
            String url="jdbc:mysql://desarrollo.is.escuelaing.edu.co:3306/bdprueba";
            String driver="com.mysql.jdbc.Driver";
            String user="bdprueba";
            String pwd="prueba2019";
                        
            Class.forName(driver);
            Connection con=DriverManager.getConnection(url,user,pwd);
            con.setAutoCommit(false);
                 
            
            System.out.println("Valor total pedido 1:"+valorTotalPedido(con, 1));
            
            List<String> prodsPedido=nombresProductosPedido(con, 1);
            
            
            System.out.println("Productos del pedido 1:");
            System.out.println("-----------------------");
            for (String nomprod:prodsPedido){
                System.out.println(nomprod);
            }
            System.out.println("-----------------------");
            
            
            registrarNuevoProducto(con, 2149194, "The best product", 99999999);            
            con.commit();
                      
            
            con.close();
                                   
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    /**
     * Agregar un nuevo producto con los parámetros dados
     * @param con la conexión JDBC
     * @param codigo
     * @param nombre
     * @param precio
     * @throws SQLException 
     */
    public static void registrarNuevoProducto(Connection con, int codigo, String nombre,int precio) throws SQLException{
        try {
            Statement s = con.createStatement();
            String sql = "INSERT INTO ORD_PRODUCTOS(codigo,nombre,precio) VALUES("+Integer.toString(codigo)+",'"+nombre+"',"+Integer.toString(precio)+");";
            int rs = s.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.commit();
        
    }
    
    /**
     * Consultar los nombres de los productos asociados a un pedido
     * @param con la conexión JDBC
     * @param codigoPedido el código del pedido
     * @return 
     */
    public static List<String> nombresProductosPedido(Connection con, int codigoPedido){
        List<String> np=new LinkedList<>();
        try {
            Statement s = con.createStatement();
            String sql = "SELECT nombre FROM ORD_PRODUCTOS WHERE codigo IN (SELECT producto_fk FROM ORD_DETALLE_PEDIDO WHERE pedido_fk ="+Integer.toString(codigoPedido)+");";
            ResultSet rs = s.executeQuery (sql);
            while (rs.next()){
                np.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        return np;
    }

    
    /**
     * Calcular el costo total de un pedido
     * @param con
     * @param codigoPedido código del pedido cuyo total se calculará
     * @return el costo total del pedido (suma de: cantidades*precios)
     */
    public static int valorTotalPedido(Connection con, int codigoPedido){
        int ans=0;
        try {
            Statement s = con.createStatement();
            String sql = "SELECT sum(precio*(SELECT cantidad FROM ORD_DETALLE_PEDIDO WHERE pedido_fk ="+Integer.toString(codigoPedido)+" && producto_fk = codigo)) "
                    + "FROM ORD_PRODUCTOS WHERE codigo IN (SELECT producto_fk FROM ORD_DETALLE_PEDIDO WHERE pedido_fk ="+Integer.toString(codigoPedido)+");";
            System.out.println(sql);
            ResultSet rs = s.executeQuery (sql);
            if (rs.next())ans=rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ans;
    }
    

    
    
    
}