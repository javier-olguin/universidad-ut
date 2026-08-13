package org.example.modelo;

public abstract class personaUt {
    protected String nombre;
    protected String curp;

    public personaUt() {}

    public personaUt(String nombre, String curp) {
        setNombre(nombre);
        setCurp(curp);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            System.out.println("El nombre es requerido");
        }
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        if (curp != null && curp.trim().length() == 18) {
            this.curp = curp;
        } else {
            System.out.println("La CURP debe tener exactamente 18 caracteres");
        }
    }

    public abstract String mostrarTipoPersona();

    @Override
    public String toString() {
        return "Tipo: " + mostrarTipoPersona() + "\n" +
                "Nombre: " + getNombre() + "\n" +
                "CURP: " + getCurp();
    }
}