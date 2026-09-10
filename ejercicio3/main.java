package ejercicio3;
import java.util.NoSuchElementException;
class Libro {
    private String titulo;
    private String autor;
    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return autor;
    }

    public String Datos() {
        return "Título=" + titulo + "\nAutor=" + autor;
    }
}
class Biblioteca{
	private Libro[] libros;
    private int contadorLibros;
    public Biblioteca(int capacidad) {
        this.libros = new Libro[capacidad];
        this.contadorLibros = 0;
    }
    public void agregarLibro(Libro libro) {
        if (libro == null || libro.getTitulo() == null || libro.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("El título del libro no puede ser nulo o estar vacío.");
        }
        if (contadorLibros >= libros.length) {
            throw new IllegalStateException("La biblioteca está llena, no se pueden agregar más libros.");
        }
        libros[contadorLibros] = libro;
        contadorLibros++;
    }
    public Libro buscarLibro(String tituloBuscado) {
        for (int i = 0; i < contadorLibros; i++) {
            if (libros[i].getTitulo().equalsIgnoreCase(tituloBuscado)) {
                return libros[i];
            }
        }
        throw new NoSuchElementException("El libro con el título '" + tituloBuscado + "' no se encuentra en la biblioteca.");
    }
}
public class main {
	public static void main(String[] args) {
        Biblioteca miBiblioteca = new Biblioteca(5);
        try {
            miBiblioteca.agregarLibro(new Libro("El principito", "Antoine de Saint-Exupéry"));
            miBiblioteca.agregarLibro(new Libro("Cien años de soledad", "Gabriel García Márquez"));
            System.out.println("Libros agregados exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            miBiblioteca.agregarLibro(new Libro("", "Autor Desconocido"));
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción capturada (IllegalArgumentException): " + e.getMessage());
        }
        try {
            Libro encontrado = miBiblioteca.buscarLibro("El principito");
            System.out.println("Libro encontrado: \n" + encontrado.Datos());
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            Libro noEncontrado = miBiblioteca.buscarLibro("Don Quijote");
            System.out.println("Libro encontrado: " + noEncontrado);
        } catch (NoSuchElementException e) {
            System.out.println("Excepción capturada (NoSuchElementException): " + e.getMessage());
        }
    }
}
