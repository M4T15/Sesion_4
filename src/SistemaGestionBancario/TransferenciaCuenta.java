package SistemaGestionBancario;

public class TransferenciaCuenta implements OperacionesBancarias{
	
	private CuentaBancaria cuentaDestino;
	
	public TransferenciaCuenta(CuentaBancaria cuentaDestino) {
		if(cuentaDestino == null) {
			throw new CuentaNoEncontradaException("La cuenta destino no existe");
		}
		this.cuentaDestino = cuentaDestino;
	}
	
	@Override
	public boolean ejecutar(CuentaBancaria cuentaOrigen, double monto) {
		if(cuentaOrigen == null) {
			throw new CuentaNoEncontradaException("La cuenta destino no existe");
		}
		
		if(monto <= 0) {
			throw new IllegalArgumentException("Monto negativo");
		}
			
		cuentaOrigen.retiro(monto);
		cuentaDestino.deposito(monto);
		
		System.out.println("Transferencia exitosa: S/" + monto + " a la cuenta: " + cuentaDestino.getNumroCuenta());
		return true;
	}
}
