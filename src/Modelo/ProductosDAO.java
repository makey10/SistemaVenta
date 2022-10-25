/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author pc
 */
public class ProductosDAO {
    
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean registrarProducto(Productos pro){
        
        String sql = "INSERT INTO productos(codigo, descripcion, proveedor, stock, precio) VALUES (?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,pro.getCodigo());
            ps.setString(2,pro.getDescripcion());
            ps.setString(3,pro.getProveedor());
            ps.setInt(4, pro.getStock());
            ps.setDouble(5, pro.getPrecio());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
 
    }
    
    public List listarProducto(){
        
        List <Productos> listarPro = new ArrayList();
        String sql = "SELECT * FROM productos";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Productos pro = new Productos();
                pro.setId(rs.getInt("id"));
                pro.setCodigo(rs.getString("codigo"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setProveedor(rs.getString("proveedor"));
                pro.setStock(rs.getInt("stock"));
                pro.setPrecio(rs.getDouble("precio"));
                
                listarPro.add(pro);
            }
            
            
            
        } catch (SQLException e) {
            System.out.println(e.toString());
            
        }
        return listarPro;
    }
   
    
    public void consultarProveedor(JComboBox proveedor){
        
        proveedor.removeAllItems();
        String sql = "SELECT nombre FROM proveedor";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                proveedor.addItem(rs.getString("nombre"));
            }
            } catch (SQLException e) {
                System.out.println(e.toString());
        }
        
    }
    
    public boolean eliminarProducto(int id){
        
        String sql = "DELETE FROM productos WHERE id = ? ";
        
        try {
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
            
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
        
        
    }
    
    
    
}
