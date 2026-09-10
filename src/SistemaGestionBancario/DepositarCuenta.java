package SistemaGestionBancario;

public class DepositarCuenta implements OperacionesBancarias {
	@Override
	public boolean ejecutar(CuentaBancaria cuenta, double monto){
		if(monto <= 0) {
			throw new IllegalArgumentException("El monto no puede ser negativo");
		}
		cuenta.deposito(monto);
		System.out.println("Deposito exitoso: +S/" + monto +" | Saldo: " + cuenta.getSaldo() );
		return true;
	}
}
