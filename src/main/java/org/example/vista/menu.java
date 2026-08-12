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
        System.out.println("\n--- INSCRIBIR ALUMNO ---");
        alumno alumnoNuevo = new alumno();

        System.out.print("Número de Expediente (Número entero mayor a 0, ej: 101): ");
        alumnoNuevo.setNumExpediente(Integer.parseInt(leer.readLine()));

        System.out.print("Nombre completo (Solo letras): ");
        alumnoNuevo.setNombre(leer.readLine());

        System.out.print("CURP (Exactamente 18 caracteres alfanuméricos): ");
        alumnoNuevo.setCurp(leer.readLine());

        System.out.print("Grupo (ej: TI-31): ");
        alumnoNuevo.setGrupo(leer.readLine());

        System.out.print("Promedio (Valor entre 0.0 y 10.0, ej: 9.5): ");
        alumnoNuevo.setPromedio(Double.parseDouble(leer.readLine()));

        // Guardar en la base de datos
        alumnoDAO.inscribirAlumno(alumnoNuevo);
    }

    private static void mostrarAlumnos() {
        ArrayList<alumno> alumnos = alumnoDAO.extraerAlumno();

        if (alumnos.isEmpty()) {
            System.out.println("\nNo hay alumnos registrados en la base de datos.");
        } else {
            System.out.println("\n========== LISTA DE ALUMNOS ==========");
            for (alumno a : alumnos) {
                System.out.println(a);
            }
        }
    }

    private static void actualizarALumno() throws IOException {
        System.out.println("\n--- ACTUALIZAR ALUMNO ---");
        alumno alumnoAct = new alumno();

        System.out.print("Número de Expediente del alumno a modificar: ");
        alumnoAct.setNumExpediente(Integer.parseInt(leer.readLine()));

        System.out.print("Nuevo Nombre: ");
        alumnoAct.setNombre(leer.readLine());

        System.out.print("Nueva CURP (18 caracteres): ");
        alumnoAct.setCurp(leer.readLine());

        System.out.print("Nuevo Grupo: ");
        alumnoAct.setGrupo(leer.readLine());

        System.out.print("Nuevo Promedio (0.0 - 10.0): ");
        alumnoAct.setPromedio(Double.parseDouble(leer.readLine()));

        alumnoDAO.actualizar(alumnoAct);
    }

    private static void bajaAlumno() {}
    private static void buscarAlumno() {}

    private static void registrarProfesor() throws IOException {
        System.out.println("\n--- REGISTRO DE PROFESOR ---");
        Profesor profe = new Profesor();

        System.out.print("Número de Empleado (Número entero mayor a 0, ej: 201): ");
        profe.setNumEmpleado(Integer.parseInt(leer.readLine()));

        System.out.print("Nombre completo (Solo letras): ");
        profe.setNombre(leer.readLine());

        System.out.print("CURP (Exactamente 18 caracteres alfanuméricos): ");
        profe.setCurp(leer.readLine());

        System.out.print("Puesto (ej: Profesor Titular): ");
        profe.setPuesto(leer.readLine());

        System.out.print("Sueldo (Valor positivo mayor a $0.0, ej: 15000.50): ");
        profe.setSueldo(Double.parseDouble(leer.readLine()));

        // Guardar en la base de datos
        profesorDAO.registrarProfesor(profe);
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

    private static void actualizarProfesor() throws IOException {
        System.out.println("\n--- ACTUALIZAR PROFESOR ---");
        Profesor profeAct = new Profesor();

        System.out.print("Número de Empleado del profesor a modificar: ");
        profeAct.setNumEmpleado(Integer.parseInt(leer.readLine()));

        System.out.print("Nuevo Nombre: ");
        profeAct.setNombre(leer.readLine());

        System.out.print("Nueva CURP (18 caracteres): ");
        profeAct.setCurp(leer.readLine());

        System.out.print("Nuevo Puesto: ");
        profeAct.setPuesto(leer.readLine());

        System.out.print("Nuevo Sueldo: ");
        profeAct.setSueldo(Double.parseDouble(leer.readLine()));

        profesorDAO.actualizar(profeAct);
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
            System.out.println("8.- Actualizar Profesor");
            System.out.println("9.- Salir");
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
                    actualizarProfesor();
                    break;
                case 9:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción Inválida");
                    break;
            }
        } while (salir != 9);
    }
}