package org.example.modelo;

public class Profesor extends personaUt {
    private int numEmpleado;
    private String puesto;
    private double sueldo;

    public Profesor() {
        super();
        this.puesto = "Sin asignar";
        this.sueldo = 0.0;
    }

    public Profesor(String nombre, String curp, int numEmpleado, String puesto, double sueldo) {
        super(nombre, curp);
        setNumEmpleado(numEmpleado);
        setPuesto(puesto);
        setSueldo(sueldo);
    }

    public int getNumEmpleado() {
        return numEmpleado;
    }

    public String getPuesto() {
        return (puesto != null) ? puesto.trim() : "";
    }

    public double getSueldo() {
        return Math.round(this.sueldo * 100.0) / 100.0;
    }

    public String getSueldoFormateado() {
        return String.format("$%.2f", getSueldo());
    }

    public void setNumEmpleado(int numEmpleado) {
        if (numEmpleado > 0) {
            this.numEmpleado = numEmpleado;
        } else {
            System.out.println("[ERROR] Número de empleado inválido. Debe ser un número entero positivo.");
        }
    }

    public void setPuesto(String puesto) {
        if (puesto != null && !puesto.trim().isEmpty()) {
            this.puesto = puesto.trim();
        } else {
            System.out.println("[ERROR] El puesto es requerido y no puede estar vacío.");
        }
    }

    public void setSueldo(double sueldo) {
        if (sueldo > 0) {
            this.sueldo = sueldo;
        } else {
            System.out.println("[ERROR] El sueldo debe ser mayor a $0.00.");
        }
    }

    @Override
    public String toString() {
        return "================ PROFESOR ================\n" +
                "Número de Empleado: " + getNumEmpleado() + "\n" +
                super.toString() + "\n" +
                "Puesto: " + getPuesto() + "\n" +
                "Sueldo: " + getSueldoFormateado() + "\n" +
                "==========================================";
    }
}
