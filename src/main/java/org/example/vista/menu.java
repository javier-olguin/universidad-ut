package org.example.vista;

import org.example.dao.AlumnoDAO;
import org.example.dao.ProfesorDAO;
import org.example.modelo.alumno;
import org.example.modelo.Profesor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class menu {
    static AlumnoDAO alumnoDAO = new AlumnoDAO();
    static ProfesorDAO profesorDAO = new ProfesorDAO();
    static BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));

    private static void inscribir() throws IOException {
        alumno alumnoNuevo = new alumno();
        System.out.print("Nombre: ");
        alumnoNuevo.setNombre(leer.readLine());
        System.out.print("Curp: ");
        alumnoNuevo.setCurp(leer.readLine());
        System.out.print("Número de Expediente: ");
        alumnoNuevo.setNumExpediente(Integer.parseInt(leer.readLine()));
        System.out.print("Grupo: ");
        alumnoNuevo.setGrupo(leer.readLine());
        System.out.print("Promedio: ");
        alumnoNuevo.setPromedio(Double.parseDouble(leer.readLine()));

        alumnoDAO.inscribirAlumno(alumnoNuevo);
    }

    private static void mostrarAlumnos() {
        ArrayList<alumno> alumnos = alumnoDAO.extraerAlumno();
        System.out.println("========== LISTA DE ALUMNOS ==========");
        for (alumno a : alumnos) {
            System.out.println(a);
        }
    }

    private static void actualizarALumno() throws IOException {
        alumno alumnoAct = new alumno();
        System.out.print("NUMERO DE EXPEDIENTE: ");
        alumnoAct.setNumExpediente(Integer.parseInt(leer.readLine()));
        System.out.print("NOMBRE: ");
        alumnoAct.setNombre(leer.readLine());
        System.out.print("CURP: ");
        alumnoAct.setCurp(leer.readLine());
        System.out.print("GRUPO: ");
        alumnoAct.setGrupo(leer.readLine());
        System.out.print("PROMEDIO: ");
        alumnoAct.setPromedio(Double.parseDouble(leer.readLine()));

        alumnoDAO.actualizar(alumnoAct);
    }

    private static void bajaAlumno() {}
    private static void buscarAlumno() {}

    private static void registrarProfesor() throws IOException {
        System.out.println("\n--- REGISTRO DE PROFESOR ---");
        Profesor profe = new Profesor();

        System.out.print("Nombre: ");
        profe.setNombre(leer.readLine());

        System.out.print("CURP: ");
        profe.setCurp(leer.readLine());

        System.out.print("Número de Empleado: ");
        profe.setNumEmpleado(Integer.parseInt(leer.readLine()));

        System.out.print("Puesto: ");
        profe.setPuesto(leer.readLine());

        System.out.print("Sueldo: ");
        profe.setSueldo(Double.parseDouble(leer.readLine()));

        System.out.println("\nProfesor capturado con éxito:");
        System.out.println(profe);
    }

    private static void mostrarProfesores() {
        ArrayList<Profesor> profesores = profesorDAO.extraerProfesores();

        if (profesores.isEmpty()) {
            System.out.println("\nNo hay profesores registrados en la base de datos.");
        } else {
            System.out.println("\n========== LISTA DE PROFESORES ==========");
            for (Profesor p : profesores) {
                System.out.println(p);
            }
        }
    }

    public static void menu() throws IOException {
        int salir = 0;
        do {
            System.out.println("\n========== MENU ==========");
            System.out.println("1.- Inscribir Alumno");
            System.out.println("2.- Mostrar Alumnos");
            System.out.println("3.- Actualizar Alumno");
            System.out.println("4.- Dar de baja Alumno");
            System.out.println("5.- Buscar Alumno");
            System.out.println("6.- Registrar Profesor");
            System.out.println("7.- Mostrar Profesores");
            System.out.println("8.- Salir");
            System.out.println("========================");
            System.out.print("Elige tu Opción: ");

            try {
                salir = Integer.parseInt(leer.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingresa un número válido.");
                continue;
            }

            switch (salir) {
                case 1:
                    inscribir();
                    break;
                case 2:
                    mostrarAlumnos();
                    break;
                case 3:
                    actualizarALumno();
                    break;
                case 4:
                    bajaAlumno();
                    break;
                case 5:
                    buscarAlumno();
                    break;
                case 6:
                    registrarProfesor();
                    break;
                case 7:
                    mostrarProfesores();
                    break;
                case 8:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción Inválida");
                    break;
            }
        } while (salir != 8);
    }
}