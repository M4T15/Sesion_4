package SistemaGestionBancario;

public class RetiroCuenta implements OperacionesBancarias {
	public boolean ejecutar(CuentaBancaria cuenta, double monto) {
		if(monto <= 0) {
			throw new IllegalArgumentException("Monto negativo");
		}
		cuenta.retiro(monto);
		System.out.println("Retiro exitoso: -S/" + monto + " | Saldo: " + cuenta.getSaldo());
		return true;
	}
}
