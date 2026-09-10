package ejer8;
import java.util.ArrayList;
import java.util.List;
class Tipo {
    private String nombreTipo;

    public Tipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }
    @Override
    public String toString() {
        return nombreTipo;
    }
}
class Movimiento {
    private String nombreMovimiento;
    public Movimiento(String nombreMovimiento) {
        this.nombreMovimiento = nombreMovimiento;
    }
    @Override
    public String toString() {
        return nombreMovimiento;
    }
}
class Pokemon {
    private String especie;
    private String apodo;
    private int nivel;
    private Tipo tipo;
    private Movimiento movimientoFirma;
    public Pokemon(String especie, String apodo, int nivel, Tipo tipo, Movimiento movimientoFirma) {
        this.especie = especie;
        this.apodo = apodo;
        this.nivel = nivel;
        this.tipo = tipo;
        this.movimientoFirma = movimientoFirma;
    }
    public String getApodo() {
        return apodo;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    @Override
    public String toString() {
        return "Pokémon: " + apodo + " (Especie: " + especie + ") | Nivel: " + nivel + 
               " | Tipo: " + tipo + " | Movimiento: " + movimientoFirma;
    }
}
class Entrenador {
    private String nombre;
    private String id;
    private List<Pokemon> equipo; 
    public Entrenador(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.equipo = new ArrayList<>();
    }
    public void agregarPokemon(Pokemon p) throws EquipoLlenoException {
        if (equipo.size() >= 6) {
            throw new EquipoLlenoException("¡Regla Pokémon! No puedes llevar más de 6 Pokémon en tu equipo.");
        }
        equipo.add(p);
        System.out.println(" -> " + p.getApodo() + " ha sido registrado en el equipo.");
    }
    public Pokemon buscarPokemon(String apodo) throws PokemonNoEncontradoException {
        for (Pokemon p : equipo) {
            if (p.getApodo().equalsIgnoreCase(apodo)) {
                return p;
            }
        }
        throw new PokemonNoEncontradoException("No se encontró ningún Pokémon con el apodo '" + apodo + "' en tu equipo.");
    }
    public void liberarPokemon(String apodo) throws PokemonNoEncontradoException {
        Pokemon p = buscarPokemon(apodo);
        equipo.remove(p);
        System.out.println("Has liberado a " + apodo + ". ¡Adiós amigo!");
    }
    public void entrenarPokemon(String apodo, int nuevoNivel) throws PokemonNoEncontradoException {
        Pokemon p = buscarPokemon(apodo);
        p.setNivel(nuevoNivel);
        System.out.println("¡" + apodo + " ha subido al nivel " + nuevoNivel + "!");
    }
    public void mostrarEquipo() {
        System.out.println("\n--- Equipo de " + this.nombre + " (ID: " + this.id + ") ---");
        if (equipo.isEmpty()) {
            System.out.println("El equipo está vacío.");
        } else {
            for (int i = 0; i < equipo.size(); i++) {
                System.out.println((i + 1) + ". " + equipo.get(i).toString());
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO PRUEBAS DEL SISTEMA POKÉMON ===\n");
        Entrenador miEntrenador = new Entrenador("Gloria", "10205");
        Tipo electrico = new Tipo("Eléctrico");
        Tipo fuego = new Tipo("Fuego");
        Tipo planta = new Tipo("Planta");
        Tipo agua = new Tipo("Agua");
        Movimiento rayo = new Movimiento("Rayo");
        Movimiento ascuas = new Movimiento("Ascuas");
        Movimiento rayoBurbuja = new Movimiento("Rayo burbuja");
        Movimiento rayosolar = new Movimiento("Rayo solar");
        Pokemon p1 = new Pokemon("Pikachu", "Sparky", 10, electrico, rayo);
        Pokemon p2 = new Pokemon("Charmander", "Fueguito", 12, fuego, ascuas);
        Pokemon p3 = new Pokemon("Bulbasaur", "Bulby", 11, planta, rayosolar);
        Pokemon p4 = new Pokemon("Cyndaquil", "Cynda", 8, fuego, ascuas);
        Pokemon p5 = new Pokemon("Piplup", "Piplup", 9, agua, rayoBurbuja);
        Pokemon p6 = new Pokemon("Pichu", "Peque", 5, electrico, rayo);
        Pokemon p7 = new Pokemon("Dialga", "CHAINYYYY", 36, fuego, ascuas);
        try {
            miEntrenador.agregarPokemon(p1);
            miEntrenador.agregarPokemon(p2);
            miEntrenador.agregarPokemon(p3);
            miEntrenador.agregarPokemon(p4);
            miEntrenador.agregarPokemon(p5);
            miEntrenador.agregarPokemon(p6);
            miEntrenador.mostrarEquipo();
            System.out.println("Intentando agregar un 7mo Pokémon (Dragón)...");
            miEntrenador.agregarPokemon(p7); 
        } catch (EquipoLlenoException e) {
            System.err.println("[EXCEPCIÓN CAPTURADA 1]: " + e.getMessage());
        }
        try {
            miEntrenador.entrenarPokemon("Sparky", 25);
            System.out.println("Intentando entrenar a 'Mewtwo'...");
            miEntrenador.entrenarPokemon("Mewtwo", 100); 
        } catch (PokemonNoEncontradoException e) {
            System.err.println("[EXCEPCIÓN CAPTURADA 2]: " + e.getMessage());
        }
        try {
            miEntrenador.liberarPokemon("Bulby");
            miEntrenador.mostrarEquipo(); 
        } catch (PokemonNoEncontradoException e) {
            System.err.println("[EXCEPCIÓN CAPTURADA 3]: " + e.getMessage());
        }
    }
}