package SistemaGestionBancario;

public class Prueba {

	public static void main(String[] args) {
		GestionCuentas gestor = new GestionCuentas();
        ReporteTransacciones reporte = new ReporteTransacciones();

        gestor.agregarCuenta(new CuentaBancaria(111, "Joel", 500.0, "9687", "61257284"));

        try {
            CuentaBancaria cuenta = gestor.buscarPorNumero(111);
            reporte.generarReporte(cuenta, 0); 
        } catch (HistorialVacioException e) {
            System.out.println(e.getMessage());
        }

        reporte.leerReporte("Archivo_Inexistente.txt");
	}
}
