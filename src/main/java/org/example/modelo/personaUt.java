package org.example.modelo;

public class personaUt {
    private String nombre;
    private String curp;

    public personaUt(){
        this.nombre = "Sin nombre";
        this.curp = "SIN_CURP_VALIDA";
    }

    public personaUt(String nombre, String curp) {
        setNombre(nombre);
        setCurp(curp);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre != null && !nombre.trim().isEmpty()){
            this.nombre = nombre;
        }else{
            System.out.println("Dato Invalido ");
        }
    }

    public String getCurp() {
        return curp.toUpperCase();
    }

    public void setCurp(String curp) {
        if(curp != null && curp.trim().length() == 18 && !curp.isBlank()){
            this.curp = curp;
        }
        else{
            System.out.println("CURP invalido");
        }
    }
    @Override
    public String toString(){
        return "Nombre: "+getNombre()+"\n"+
                "Curp: "+getCurp();
    }
}
