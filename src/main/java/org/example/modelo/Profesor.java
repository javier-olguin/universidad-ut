package org.example.modelo;

public class Profesor extends personaUt implements Ensenador, Evaluador {
    private int numEmpleado;
    private String puesto;
    private double sueldo;

    public Profesor() {}

    public Profesor(String nombre, String curp, int numEmpleado, String puesto, double sueldo) {
        super(nombre, curp);
        setNumEmpleado(numEmpleado);
        setPuesto(puesto);
        setSueldo(sueldo);
    }

    @Override
    public String mostrarTipoPersona() {
        return "Profesor";
    }

    @Override
    public void ensenar() {
        System.out.println("El profesor " + getNombre() + " está impartiendo clase.");
    }

    @Override
    public void evaluar() {
        System.out.println("El profesor " + getNombre() + " está evaluando a sus alumnos.");
    }

    public int getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(int numEmpleado) {
        if (numEmpleado > 0) {
            this.numEmpleado = numEmpleado;
        } else {
            System.out.println("Número de empleado inválido");
        }
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        if (puesto != null && !puesto.trim().isEmpty()) {
            this.puesto = puesto;
        } else {
            System.out.println("El puesto es requerido");
        }
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        if (sueldo > 0) {
            this.sueldo = sueldo;
        } else {
            System.out.println("El sueldo debe ser mayor a 0");
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Número de Empleado: " + getNumEmpleado() + "\n" +
                "Puesto: " + getPuesto() + "\n" +
                "Sueldo: $" + getSueldo() + "\n" +
                "=====================================";
    }
}
