package ejer6;
import java.util.ArrayList;
import java.util.List;
class Direccion {
    private String calle;
    private String ciudad;

    public Direccion(String calle, String ciudad) {
        this.calle = calle;
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return calle + ", " + ciudad;
    }
}
class GrupoContacto {
    private String nombre;
    private String descripcion;

    public GrupoContacto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre(){ 
    return nombre; }
}

class Contacto {
    private String nombre;
    private String telefono;
    private String correo;
    private Direccion direccion;
    private GrupoContacto grupo;
    public Contacto(String nombre, String telefono, String correo, Direccion direccion, GrupoContacto grupo) throws ContactoException {
        setNombre(nombre);
        setTelefono(telefono);
        setCorreo(correo);
        this.direccion = direccion;
        this.grupo = grupo;
    }
    public String getNombre() { 
    return nombre; 
    }
    public void setNombre(String nombre) throws ContactoException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ContactoException("Error: El nombre del contacto no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) throws ContactoException {
        if (telefono == null || !telefono.matches("\\d{7,15}")) {
            throw new ContactoException("Error: El teléfono '" + telefono + "' es inválido. Debe contener entre 7 y 15 dígitos.");
        }
        this.telefono = telefono;
    }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) throws ContactoException {
        if (correo == null || !correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new ContactoException("Error: El formato del correo '" + correo + "' no es válido.");
        }
        this.correo = correo;
    }

    public void setDireccion(Direccion direccion) { this.direccion = direccion; }
    public void setGrupo(GrupoContacto grupo) { this.grupo = grupo; }

    @Override
    public String toString() {
        String nombreGrupo = (grupo != null) ? grupo.getNombre() : "Sin grupo";
        return String.format("Nombre: %s | Tel: %s | Correo: %s | Dir: %s | Grupo: %s", 
                              nombre, telefono, correo, direccion, nombreGrupo);
    }
}

class GestorContactos {
    private List<Contacto> listaContactos;

    public GestorContactos() {
        this.listaContactos = new ArrayList<>();
    }

    public void agregarContacto(Contacto nuevoContacto) throws ContactoException {
        if (nuevoContacto == null) {
            throw new ContactoException("Error: No se puede agregar un contacto nulo.");
        }
        for (Contacto c : listaContactos) {
            if (c.getTelefono().equals(nuevoContacto.getTelefono())) {
                throw new ContactoException("Error: Ya existe un contacto con el teléfono " + nuevoContacto.getTelefono());
            }
        }
        listaContactos.add(nuevoContacto);
        System.out.println("Contacto agregado exitosamente: " + nuevoContacto.getNombre());
    }

    public void modificarContacto(String telefonoBuscar, String nuevoNombre, String nuevoCorreo, Direccion nuevaDir, GrupoContacto nuevoGrupo) throws ContactoException {
        Contacto contacto = buscarContacto(telefonoBuscar);
        if (contacto == null) {
            throw new ContactoException("Error al modificar: No se encontró ningún contacto con el teléfono " + telefonoBuscar);
        }
        contacto.setNombre(nuevoNombre);
        contacto.setCorreo(nuevoCorreo);
        contacto.setDireccion(nuevaDir);
        contacto.setGrupo(nuevoGrupo);
        System.out.println("Contacto modificado exitosamente.");
    }

    public void eliminarContacto(String telefono) throws ContactoException {
        Contacto contacto = buscarContacto(telefono);
        if (contacto == null) {
            throw new ContactoException("Error al eliminar: No se encontró ningún contacto con el teléfono " + telefono);
        }
        listaContactos.remove(contacto);
        System.out.println("Contacto eliminado exitosamente.");
    }

    public Contacto buscarContacto(String telefono) {
        for (Contacto c : listaContactos) {
            if (c.getTelefono().equals(telefono)) {
                return c;
            }
        }
        return null;
    }

    public void listarContactos() {
        if (listaContactos.isEmpty()) {
            System.out.println("La agenda está vacía.");
            return;
        }
        System.out.println("\nLista de Contactos");
        for (Contacto c : listaContactos) {
            System.out.println(c.toString());
        }
    }
}
public class main {
	public static void main(String[] args) {
        GestorContactos gestor = new GestorContactos();
        try {
            Direccion dir1 = new Direccion("Av. Independencia s/n", "Arequipa");
            GrupoContacto grupoUni = new GrupoContacto("Universidad", "Compañeros de la UCSM");
            Direccion dir2 = new Direccion("Calle Mercaderes 123", "Arequipa");
            GrupoContacto grupoTrabajo = new GrupoContacto("Trabajo", "Proyectos de desarrollo");
            Contacto c1 = new Contacto("Carlos Rivera", "987654321", "carlos@email.com", dir1, grupoUni);
            Contacto c2 = new Contacto("Ana Mendoza", "912345678", "ana@email.com", dir2, grupoTrabajo);
            gestor.agregarContacto(c1);
            gestor.agregarContacto(c2);
            gestor.listarContactos();
            gestor.agregarContacto(new Contacto("Carlos Clon", "987654321", "clon@email.com", dir1, grupoUni));

        } catch (ContactoException e) {
            System.err.println(e.getMessage());
        }
        try {
            Direccion nuevaDir = new Direccion("Av. Ejército 456", "Arequipa");
            gestor.modificarContacto("912345678", "Ana M. Torres", "ana.torres@email.com", nuevaDir, null);
            gestor.listarContactos();
            gestor.eliminarContacto("987654321");
            gestor.listarContactos();
            System.out.println("PRUEBA DE ERROR (Dato inválido)");
            Contacto c3 = new Contacto("Luis", "999888777", "correo-invalido", null, null);
        } catch (ContactoException e) {
            System.err.println(e.getMessage());
        }
    }
}
