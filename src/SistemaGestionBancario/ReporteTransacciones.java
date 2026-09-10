package SistemaGestionBancario;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ReporteTransacciones {

    public void generarReporte(CuentaBancaria cuenta, int cantidadTransacciones) {
        if (cantidadTransacciones == 0) {
            throw new HistorialVacioException("La cuenta " + cuenta.getNumroCuenta() + " no tiene transacciones registradas.");
        }

        String nombreArchivo = "Reporte_" + cuenta.getNumroCuenta() + ".txt";

        try (FileWriter fw = new FileWriter(nombreArchivo); 
             PrintWriter pw = new PrintWriter(fw)) {
            
            pw.println("Numero de Cuenta: " + cuenta.getNumroCuenta());
            pw.println("Titular: " + cuenta.getTitular());
            pw.println("Saldo: " + cuenta.getSaldo());
            
        } catch (IOException e) {
            System.out.println("Error de entrada/salida al escribir el archivo: " + e.getMessage());
        }
    }

    public void leerReporte(String nombreArchivo) {
        File archivo = new File(nombreArchivo);

        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encontro el archivo - " + e.getMessage());
        }
    }
}