
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {
    
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean registrarProveedor(Proveedor pr){
        
        String sql = "INSERT INTO proveedor(cuit, nombre, telefono, direccion, razon) VALUES (?,?,?,?,?)";
        try {
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pr.getCuit());
            ps.setString(2, pr.getDireccion());
            ps.setInt(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setString(5, pr.getRazon());
            ps.execute();
            return true;
            
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
    }
    
    public List listarProveedor(){
        List<Proveedor> listaPr = new ArrayList();
        String sql = "SELECT * FROM Proveedor";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor pr = new Proveedor();
                pr.setId(rs.getInt("id"));
                pr.setCuit(rs.getInt("cuit"));
                pr.setNombre(rs.getString("nombre"));
                pr.setTelefono(rs.getInt("telefono"));
                pr.setDireccion(rs.getString("direccion"));
                pr.setRazon(rs.getString("razon"));
                
                listaPr.add(pr);
                
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listaPr;
    }
    
    public boolean eliminarProveedor(int id){
        
        String sql = "DELETE FROM proveedor WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
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
    
    public boolean modificarProveedor(Proveedor pr){
        
        String sql = "UPDATE proveedor SET cuit = ?, nombre = ?, telefono = ?, direccion = ?, razon = ? WHERE id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pr.getCuit());
            ps.setString(2, pr.getNombre());
            ps.setInt(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setString(5, pr.getRazon());
            ps.setInt(6, pr.getId());
            
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
}
