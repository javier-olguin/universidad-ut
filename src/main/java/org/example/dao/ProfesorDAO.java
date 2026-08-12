package org.example.dao;

import org.example.config.conexion;
import org.example.modelo.Profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfesorDAO {

    public boolean registrarProfesor(Profesor profesor) {
        boolean registrado = false;
        String sql = "INSERT INTO profesores (numEmpleado, nombre, curp, puesto, sueldo) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = conexion.conectar()) {
            if (con == null) return false;

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, profesor.getNumEmpleado());
                stm.setString(2, profesor.getNombre());
                stm.setString(3, profesor.getCurp());
                stm.setString(4, profesor.getPuesto());
                stm.setDouble(5, profesor.getSueldo());

                int filas = stm.executeUpdate();
                if (filas > 0) {
                    System.out.println(">>> Profesor registrado correctamente en MySQL <<<");
                    registrado = true;
                }
            }
        } catch (SQLException err) {
            System.out.println("[ERROR MySQL] Error al registrar profesor: " + err.getMessage());
        }

        return registrado;
    }

    public ArrayList<Profesor> extraerProfesores() {
        ArrayList<Profesor> profesores = new ArrayList<>();
        String sql = "SELECT * FROM profesores";

        try (Connection con = conexion.conectar()) {
            if (con == null) return profesores;

            try (PreparedStatement stm = con.prepareStatement(sql);
                 ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {
                    Profesor profesor = new Profesor();
                    profesor.setNumEmpleado(rs.getInt("numEmpleado"));
                    profesor.setNombre(rs.getString("nombre"));
                    profesor.setCurp(rs.getString("curp"));
                    profesor.setPuesto(rs.getString("puesto"));
                    profesor.setSueldo(rs.getDouble("sueldo"));

                    profesores.add(profesor);
                }
            }
        } catch (SQLException err) {
            System.out.println("[ERROR MySQL] " + err.getMessage());
        }

        return profesores;
    }

    public boolean actualizar(Profesor profesor) {
        boolean actualizado = false;
        String sql = "UPDATE profesores SET nombre = ?, curp = ?, puesto = ?, sueldo = ? WHERE numEmpleado = ?";

        try (Connection con = conexion.conectar()) {
            if (con == null) return false;

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, profesor.getNombre());
                stm.setString(2, profesor.getCurp());
                stm.setString(3, profesor.getPuesto());
                stm.setDouble(4, profesor.getSueldo());
                stm.setInt(5, profesor.getNumEmpleado());

                int registrosAfectados = stm.executeUpdate();
                if (registrosAfectados > 0) {
                    System.out.println(">>> Profesor actualizado correctamente en MySQL <<<");
                    actualizado = true;
                } else {
                    System.out.println("[ADVERTENCIA] No se encontró ningún profesor con ese número de empleado.");
                }
            }
        } catch (SQLException err) {
            System.out.println("[ERROR MySQL] " + err.getMessage());
        }

        return actualizado;
    }
    public boolean bajaProfesor(int numEmpleado) {
        boolean eliminado = false;
        String sql = "DELETE FROM profesores WHERE numEmpleado = ?";

        try (Connection con = conexion.conectar()) {
            if (con == null) {
                System.out.println("[ERROR DAO] No hay conexión activa a la base de datos.");
                return false;
            }

            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, numEmpleado);

                int registrosAfectados = stm.executeUpdate();
                if (registrosAfectados > 0) {
                    System.out.println(">>> Profesor dado de baja correctamente en MySQL <<<");
                    eliminado = true;
                } else {
                    System.out.println("[ADVERTENCIA] No se encontró ningún profesor con el número de empleado ingresado.");
                }
            }
        } catch (SQLException err) {
            System.out.println("[ERROR MySQL] Error al dar de baja al profesor: " + err.getMessage());
        }

        return eliminado;
    }
}