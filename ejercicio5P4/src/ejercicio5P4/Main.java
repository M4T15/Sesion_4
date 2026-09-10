package ejercicio5P4;

import java.util.NoSuchElementException;

class RegistroEstudiantes {
    private String[] estudiantes;
    private int contador;

    public RegistroEstudiantes(int capacidad) {
        estudiantes = new String[capacidad];
        contador = 0;
    }

    public void agregarEstudiante(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("no puede faltar el nombre de del estudiante");
        }
        if (contador >= estudiantes.length) {
            throw new IllegalStateException("registros completos");
        }
        estudiantes[contador++] = nombre;
    }

    public void buscarEstudiante(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("el nombre a buscar no pusde esta vacio");
        }
        for (int i = 0; i < contador; i++) {
            if (estudiantes[i].equalsIgnoreCase(nombre)) {
                System.out.println("se encontro al estudiante:  " + estudiantes[i]);
                return;
            }
        }
        throw new NoSuchElementException("el estudiante " + nombre + " no se encuentra en el registro  ");
    }
}

public class Main {
    public static void main(String[] args) {
        RegistroEstudiantes registro = new RegistroEstudiantes(5);
        
        try {
            registro.agregarEstudiante("jim sucasaire");
            registro.agregarEstudiante("hector ono ");
            System.out.println("Estudiantes agregados ");
            
            System.out.println("intentraremso agregar un estudiate con nombre vacio ");
            registro.agregarEstudiante(""); 
        } catch (IllegalArgumentException e) {
            System.out.println("[Catch IllegalArgumentException] : " + e.getMessage());
        }

        try {
            System.out.println("\nBuscando a un estudiante que no esta (luis)");
            registro.buscarEstudiante("luis"); 
        } catch (NoSuchElementException | IllegalArgumentException e) {
            System.out.println("[Catch Excepción]: " + e.getMessage());
        }
    }
}