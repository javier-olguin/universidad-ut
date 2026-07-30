package org.example.vista;

import org.example.dao.AlumnoDAO;
import org.example.modelo.alumno;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class menu {
    static  AlumnoDAO alumnoDAO = new AlumnoDAO();
    static alumno alumno = new alumno();
    private static void inscribir() throws IOException{
        System.out.print("Nombre: ");
        alumno.setNombre(leer.readLine());
        System.out.print("Curp: ");
        alumno.setCurp(leer.readLine());
        System.out.print("Número de Expediente: ");
        alumno.setNumExpediente(Integer.parseInt(leer.readLine()));
        System.out.print("Grupo: ");
        alumno.setGrupo(leer.readLine());
        System.out.print("Promedio: ");
        alumno.setPromedio(Double.parseDouble(leer.readLine()));

        alumnoDAO.inscribirAlumno(alumno);
    }
    private static void mostrarAlumnos(){
        ArrayList<alumno> alumnos = alumnoDAO.extraerAlumno();
        System.out.println("========== LISTA DE ALUMNOS ==========");
        for (alumno alumno:alumnos ){
            System.out.println(alumno);
        }
    }
    private static void actualizarALumno() throws IOException{
        System.out.println("NUMERO DE EXPEDIENTE: ");
        alumno.setNumExpediente(Integer.parseInt(leer.readLine()));
        System.out.println("NOMBRE: ");
        alumno.setNombre(leer.readLine());
        System.out.println("CURP: ");
        alumno.setCurp(leer.readLine());
        System.out.println("GRUPO: ");
        alumno.setGrupo(leer.readLine());
        System.out.println("PROMEDIO: ");
        alumno.setPromedio(Double.parseDouble(leer.readLine()));

        alumnoDAO.actualizar(alumno);
    }
    private static void bajaAlumno(){}
    private static void buscarAlumno(){}

    static  BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
    public static void menu() throws IOException {
        int salir = 0;
        do {
            System.out.println("==========MENU==========");
            System.out.println("Inscribir ALumno");
            System.out.println("2.- Mostrar ALumnos");
            System.out.println("3.- Actualizar Alumno");
            System.out.println("4.- Dar de baja Alumno");
            System.out.println("5.-Buscar Alumno");
            System.out.println("6.- Salir");
            System.out.println("========================");
            System.out.println("Elige tu Opción: ");
            salir = Integer.parseInt(leer.readLine());

            switch (salir){
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
                    System.out.println("Saliendo del programa: ");
                    break;
                default:
                    System.out.println("Opcion Invalida");
                    break;
            }
        }while(salir != 6);
    }
}