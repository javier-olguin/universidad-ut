package org.example.dao;

import com.sun.security.jgss.GSSUtil;
import org.example.config.conexion;
import org.example.modelo.alumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlumnoDAO {
    public boolean inscribirAlumno(alumno alumno) {
        boolean inscrito = false;
        String sql = "INSERT INTO alumnos VALUES (?,?,?,?,?)";
        try(Connection conexion = org.example.config.conexion.conectar();
            PreparedStatement stm =  conexion.prepareStatement(sql);) {

            stm.setInt(1,alumno.getNumExpediente());
            stm.setString(2, alumno.getNombre());
            stm.setString(3, alumno.getCurp());
            stm.setString(4,alumno.getGrupo());
            stm.setDouble(5,alumno.getPromedio());
            stm.executeUpdate();
            System.out.println("Registro realizado Correctamente");

        } catch (SQLException err) {
            System.out.println("Error "+err.getMessage());
        }

        return inscrito;
    }
    public ArrayList<alumno> extraerAlumno(){
        ArrayList<alumno> alumnos = new ArrayList<alumno>();
        String  sql = "SELECT * FROM alumnos";
        try(Connection conexion = org.example.config.conexion.conectar();
            PreparedStatement stm = conexion.prepareStatement(sql);){
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                alumno alumno = new alumno();
                alumno.setNumExpediente(rs.getInt("numExpediente"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setCurp(rs.getString("curp"));
                alumno.setGrupo(rs.getString("grupo"));
                alumno.setPromedio(rs.getDouble("promedio"));
                alumnos.add(alumno);
            }

        }catch(SQLException err){
            System.out.println("Error al extraer alumnos"+err.getMessage());
        }
        return alumnos;

    }
    public boolean actualizar(alumno alumno){
        boolean actualizado = false;
        String sql = "UPDATE alumnos SET nombre = ?, curp = ?, grupo = ?, promedio = ? WHERE numExpediente = ? ";
        try(Connection conexion = org.example.config.conexion.conectar();
            PreparedStatement stm =  conexion.prepareStatement(sql);){
            stm.setString(1,alumno.getNombre());
            stm.setString(2,alumno.getCurp());
            stm.setString(3,alumno.getGrupo());
            stm.setDouble(4,alumno.getPromedio());
            stm.setInt(5,alumno.getNumExpediente());
            int registrosAfectados = stm.executeUpdate();
            if(registrosAfectados > 0 ){
                System.out.println("Alumno Actualizado Correctamente");
                actualizado = true;
            }else{
                System.out.println("Te la volaste master tu Expediente no existe");
            }
        }catch(SQLException err){
            System.out.println("Error al actualizar Alumno"+err.getMessage());
        }
        return actualizado;
    }

}
