package ejer7;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
class Propiedad {
    private String direccion;
    private double precio;
    private double tamano;
    public Propiedad(String direccion, double precio, double tamano) throws DatosInvalidosException {
        if (precio <= 0 || tamano <= 0) {
            throw new DatosInvalidosException("El precio y el tamaño deben ser valores positivos.");
        }
        this.direccion = direccion;
        this.precio = precio;
        this.tamano = tamano;
    }
    public double getPrecio() {
        return precio;
    }
}
class TransaccionInmobiliaria {
    private String id;
    private Propiedad propiedad;
    private double precio;
    public TransaccionInmobiliaria(String id, Propiedad propiedad, double precio) throws PrecioInferiorException {
        if (precio < propiedad.getPrecio()) {
            throw new PrecioInferiorException("El precio de la transacción no puede ser menor al precio base de la propiedad.");
        }
        this.id = id;
        this.propiedad = propiedad;
        this.precio = precio;
    }
}
class ContratoAlquiler {
    private int duracionMeses;
    private double montoAlquiler;
    public void establecerDuracion(int duracionMeses) throws ContratoInvalidoException {
        if (duracionMeses < 0) {
            throw new ContratoInvalidoException("La duración del contrato no puede ser negativa.");
        }
        this.duracionMeses = duracionMeses;
    }
    public void establecerMonto(double montoAlquiler) throws ContratoInvalidoException {
        if (montoAlquiler <= 0) {
            throw new ContratoInvalidoException("El monto del alquiler no puede ser cero ni negativo.");
        }
        this.montoAlquiler = montoAlquiler;
    }
}
class Pago {
    private LocalDateTime fecha;
    private double monto;
    public Pago(LocalDateTime fecha, double monto) {
        this.fecha = fecha;
        this.monto = monto;
    }
}
class HistorialDePagos {
    private List<Pago> pagos;
    public HistorialDePagos() {
        this.pagos = new ArrayList<>();
    }
    public void registrarPago(LocalDateTime fecha, double monto) throws PagoInvalidoException {
        if (fecha.isAfter(LocalDateTime.now())) {
            throw new PagoInvalidoException("No se puede registrar un pago con una fecha futura.");
        }
        if (monto < 0) {
            throw new PagoInvalidoException("El monto del pago no puede ser negativo.");
        }
        this.pagos.add(new Pago(fecha, monto));
    }
}
public class main {
    public static void main(String[] args) {
        Propiedad casa = null;
        try {
            casa = new Propiedad("Av. Siempreviva 742", 150000.0, 120.0);
            System.out.println("Propiedad creada con éxito.");
            Propiedad terrenoMalo = new Propiedad("Calle Falsa 123", -500, 100); 
        } catch (DatosInvalidosException e) {
            System.out.println("Error al crear propiedad: " + e.getMessage());
        }

        try {
            if (casa != null) {
                TransaccionInmobiliaria transaccion = new TransaccionInmobiliaria("TX-001", casa, 140000.0);
            }
        } catch (PrecioInferiorException e) {
            System.out.println("Error en transacción: " + e.getMessage());
        }
        try {
            ContratoAlquiler contrato = new ContratoAlquiler();
            contrato.establecerDuracion(12);
            contrato.establecerMonto(0);
        } catch (ContratoInvalidoException e) {
            System.out.println("Error en contrato: " + e.getMessage());
        }
        try {
            HistorialDePagos historial = new HistorialDePagos();
            historial.registrarPago(LocalDateTime.now(), 500.0);
            System.out.println("Pago válido registrado con éxito.");
            LocalDateTime fechaFutura = LocalDateTime.now().plusDays(5);
            historial.registrarPago(fechaFutura, 500.0);
        } catch (PagoInvalidoException e) {
            System.out.println("Error en el pago: " + e.getMessage());
        }
    }
}
