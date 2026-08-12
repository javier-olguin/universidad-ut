package org.example.dao;

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
        String sql = "INSERT INTO alumnos (numExpediente, nombre, curp, grupo, promedio) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = conexion.conectar()) {
            if (con == null) {
                System.out.println("[ERROR DAO] No hay conexión con la base de datos.");
                return false;
            }

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, alumno.getNumExpediente());
                stm.setString(2, alumno.getNombre());
                stm.setString(3, alumno.getCurp());
                stm.setString(4, alumno.getGrupo());
                stm.setDouble(5, alumno.getPromedio());

                int filas = stm.executeUpdate();
                if (filas > 0) {
                    System.out.println(">>> Alumno registrado correctamente en la Base de Datos <<<");
                    inscrito = true;
                }
            }
        } catch (SQLException err) {
            System.out.println("[ERROR MySQL] Error al inscribir alumno: " + err.getMessage());
        }

        return inscrito;
    }

    public ArrayList<alumno> extraerAlumno() {
        ArrayList<alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumnos";

        try (Connection con = conexion.conectar()) {
            if (con == null) return alumnos;

            try (PreparedStatement stm = con.prepareStatement(sql);
                 ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {
                    alumno alu = new alumno();
                    alu.setNumExpediente(rs.getInt("numExpediente"));
                    alu.setNombre(rs.getString("nombre"));
                    alu.setCurp(rs.getString("curp"));
                    alu.setGrupo(rs.getString("grupo"));
                    alu.setPromedio(rs.getDouble("promedio"));
                    alumnos.add(alu);
                }
            }
        } catch (SQLException err) {
            System.out.println("[ERROR MySQL] " + err.getMessage());
        }

        return alumnos;
    }

    public boolean actualizar(alumno alumno) {
        boolean actualizado = false;
        String sql = "UPDATE alumnos SET nombre = ?, curp = ?, grupo = ?, promedio = ? WHERE numExpediente = ?";

        try (Connection con = conexion.conectar()) {
            if (con == null) return false;

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, alumno.getNombre());
                stm.setString(2, alumno.getCurp());
                stm.setString(3, alumno.getGrupo());
                stm.setDouble(4, alumno.getPromedio());
                stm.setInt(5, alumno.getNumExpediente());

                int registrosAfectados = stm.executeUpdate();
                if (registrosAfectados > 0) {
                    System.out.println(">>> Alumno actualizado correctamente <<<");
                    actualizado = true;
                } else {
                    System.out.println("[ADVERTENCIA] No existe el expediente ingresado.");
                }
            }
        } catch (SQLException err) {
            System.out.println("[ERROR MySQL] " + err.getMessage());
        }

        return actualizado;
    }
}
