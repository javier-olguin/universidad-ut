package org.example.dao;

import org.example.config.conexion;
import org.example.modelo.Profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfesorDAO {

    public ArrayList<Profesor> extraerProfesores() {
        ArrayList<Profesor> profesores = new ArrayList<>();
        String sql = "SELECT * FROM profesores";

        Connection con = conexion.conectar();

        if (con == null) {
            System.out.println("[ERROR] No se pudo establecer la conexión con la base de datos.");
            return profesores;
        }

        try (PreparedStatement stm = con.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                // Creamos un objeto Profesor por cada fila encontrada
                Profesor profesor = new Profesor();

                profesor.setNumEmpleado(rs.getInt("numEmpleado"));
                profesor.setNombre(rs.getString("nombre"));
                profesor.setCurp(rs.getString("curp"));
                profesor.setPuesto(rs.getString("puesto"));
                profesor.setSueldo(rs.getDouble("sueldo"));

                profesores.add(profesor);
            }

        } catch (SQLException err) {
            System.out.println("[ERROR MySQL] " + err.getMessage());
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("[ERROR CLOSING] " + e.getMessage());
            }
        }

        return profesores;
    }
}